package com.example.company.device_library.service;

import com.example.company.device_library.model.Computer;
import com.example.company.device_library.model.Peripheral;
import com.example.company.device_library.model.Software;
import com.example.company.device_library.repository.*;
import com.example.company.device_library.util.dtos.ComputerDto;
import com.example.company.device_library.util.mappers.ComputerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComputerService {
    private ComputerRepository computerRepository;
    private MonitorRepository monitorRepository;
    private PrinterRepository printerRepository;
    private PeripheralRepository peripheralRepository;
    private SoftwareRepository softwareRepository;
    private ComputerMapper computerMapper;

    @Autowired
    public ComputerService(ComputerRepository computerRepository,
                           MonitorRepository monitorRepository,
                           PrinterRepository printerRepository,
                           PeripheralRepository peripheralRepository,
                           SoftwareRepository softwareRepository,
                           ComputerMapper computerMapper) {
        this.computerRepository = computerRepository;
        this.monitorRepository = monitorRepository;
        this.printerRepository = printerRepository;
        this.peripheralRepository = peripheralRepository;
        this.softwareRepository = softwareRepository;
        this.computerMapper = computerMapper;
    }

    public boolean addComputer(ComputerDto computerDto) {
        boolean checked = checkDataBeforeSave(computerDto);
        if(checked){
            try {
                computerDto.setIsUse(Boolean.FALSE);
                computerRepository.save(computerMapper.reverse(computerDto));
            }catch (DataIntegrityViolationException dive){
                checked = false;
            }
        }
        return checked;
    }

    public Collection<ComputerDto> getAllComputers() {
        return computerRepository.findAll()
                .stream()
                .map(computerMapper::map)
                .collect(Collectors.toList());
    }

    public Collection<ComputerDto> getAllFreeComputers() {
        return computerRepository.findFreeComputers()
                .stream()
                .map(computerMapper::map)
                .collect(Collectors.toList());
    }

    public Collection<ComputerDto> getAllNotFreeComputers() {
        return computerRepository.findNotFreeComputers()
                .stream()
                .map(computerMapper::map)
                .collect(Collectors.toList());
    }

    public ComputerDto getComputerById(Long computerId) {
        Computer getOne = computerRepository.getOne(computerId);
        return computerMapper.map(getOne);
    }

    public void updateComputer(ComputerDto computerDto) {
        computerRepository.getComputerById(computerDto.getComputerId())
                .ifPresent(c -> {
                    c.setDeviceManufacturer(computerDto.getDeviceManufacturer());
                    c.setDeviceType(computerDto.getDeviceType());
                    c.setSerialNumber(computerDto.getSerialNumber());
                    c.setComputerAdName(computerDto.getComputerAdName());
                    c.setComputerType(computerDto.getComputerType());
                    c.setSoftwareCollection(computerDto.getSoftwareCollection());
                    c.setPeripheralCollection(computerDto.getPeripheralCollection());
                    computerRepository.save(c);
                });

    }

    public void getComputerAndAddMonitorHim(Long monitorId, ComputerDto databaseComputer) {
        databaseComputer.setMonitor(monitorRepository.getOne(monitorId));
        computerRepository.save(computerMapper.reverse(databaseComputer));
    }

    public void getComputerAndAddPrinterHim(Long printerId, ComputerDto databaseComputer) {
        databaseComputer.setPrinter(printerRepository.getOne(printerId));
        computerRepository.save(computerMapper.reverse(databaseComputer));
    }

    public void getComputerAndAddPeripheralHim(Collection<Long> peripheralListId, ComputerDto databaseComputer) {
        Collection<Peripheral> peripheralCollection = databaseComputer.getPeripheralCollection();
        for (Long ids : peripheralListId) {
            peripheralRepository.getPeripheralById(ids)
                    .ifPresent(p -> {
                        p.setIsUse(Boolean.TRUE);
                        peripheralCollection.add(p);
                        p.setComputer(computerMapper.reverse(databaseComputer));
                    });
        }
        computerRepository.save(computerMapper.reverse(databaseComputer));
    }

    public void getComputerAndAddSoftwareHim(Collection<Long> softwareListId, ComputerDto databaseComputer) {
        Collection<Software> softwareCollection = databaseComputer.getSoftwareCollection();
        for (Long ids : softwareListId) {
            softwareRepository.getSoftwareById(ids)
                    .ifPresent(softwareCollection::add);
        }
        computerRepository.save(computerMapper.reverse(databaseComputer));
    }

    public void changeComputerOnReadyToUse(Long computerId) {
        computerRepository.getComputerById(computerId)
                .ifPresent(c -> {
                    c.setIsUse(Boolean.FALSE);
                    computerRepository.save(c);
                });
    }

    private boolean checkDataBeforeSave(ComputerDto computerDto) {
        return computerRepository.findAll()
                .stream()
                .noneMatch(m -> m.getSerialNumber()
                        .matches(computerDto.getSerialNumber()));
    }
}
