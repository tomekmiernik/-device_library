package com.example.company.device_library.service;

import com.example.company.device_library.model.*;
import com.example.company.device_library.repository.ComputerRepository;
import com.example.company.device_library.repository.PeripheralRepository;
import com.example.company.device_library.util.dtos.ComputerDto;
import com.example.company.device_library.util.dtos.MonitorDto;
import com.example.company.device_library.util.dtos.PrinterDto;
import com.example.company.device_library.util.mappers.ComputerMapper;
import com.example.company.device_library.util.mappers.MonitorMapper;
import com.example.company.device_library.util.mappers.PrinterMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ComputerService {
    private ComputerRepository computerRepository;
    private PeripheralRepository peripheralRepository;
    private ComputerMapper computerMapper;
    private MonitorMapper monitorMapper;
    private PrinterMapper printerMapper;


    public ComputerService(ComputerRepository computerRepository,
                           ComputerMapper computerMapper,
                           MonitorMapper monitorMapper,
                           PrinterMapper printerMapper) {
        this.computerRepository = computerRepository;
        this.computerMapper = computerMapper;
        this.monitorMapper = monitorMapper;
        this.printerMapper = printerMapper;
    }

    public Computer addComputer(ComputerDto computerDto) {
        return computerRepository.save(computerMapper.reverse(computerDto));
    }

    public Collection<ComputerDto> getAllComputers() {
        return computerRepository.findAll()
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

    public void getComputerAndAddMonitorHim(Monitor monitor, ComputerDto databaseComputer) {
        MonitorDto monitorDto = monitorMapper.map(monitor);
        databaseComputer.setMonitor(monitorMapper.reverse(monitorDto));
        computerRepository.save(computerMapper.reverse(databaseComputer));
    }

    public void getComputerAndAddPrinterHim(Printer printer, ComputerDto databaseComputer) {
        PrinterDto printerDto = printerMapper.map(printer);
        databaseComputer.setPrinter(printerMapper.reverse(printerDto));
        computerRepository.save(computerMapper.reverse(databaseComputer));
    }

    public void getComputerAndAddPeripheralHim(Peripheral peripheral, ComputerDto databaseComputer) {
        peripheral.setComputer(computerMapper.reverse(databaseComputer));
        computerMapper.reverse(databaseComputer).addPeripheral(peripheral);
        computerRepository.save(computerMapper.reverse(databaseComputer));
    }

    public void getComputerAndAddSoftwareHim(Software software, ComputerDto databaseComputer) {
        software.setComputer(computerMapper.reverse(databaseComputer));
        computerMapper.reverse(databaseComputer).addSoftware(software);
        computerRepository.save(computerMapper.reverse(databaseComputer));
    }
}
