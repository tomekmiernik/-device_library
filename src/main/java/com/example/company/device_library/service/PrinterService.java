package com.example.company.device_library.service;

import com.example.company.device_library.model.Consumable;
import com.example.company.device_library.repository.ConsumableRepository;
import com.example.company.device_library.repository.PrinterRepository;
import com.example.company.device_library.util.dtos.PrinterDto;
import com.example.company.device_library.util.mappers.PrinterMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class PrinterService {
    private PrinterRepository printerRepository;
    private ConsumableRepository consumableRepository;
    private PrinterMapper printerMapper;

    public PrinterService(PrinterRepository printerRepository,
                          ConsumableRepository consumableRepository,
                          PrinterMapper printerMapper) {
        this.printerRepository = printerRepository;
        this.consumableRepository = consumableRepository;
        this.printerMapper = printerMapper;
    }

    public Collection<PrinterDto> getAllPrinters() {
        return printerRepository.findAll()
                .stream()
                .map(printerMapper::map)
                .collect(Collectors.toList());
    }

    public boolean addPrinter(PrinterDto printerDto) {
        boolean checked = checkSerialNumberOfDeviceBeforeSave(printerDto);
        if (checked) {
            try {
                printerRepository.save(printerMapper.reverse(printerDto));
            } catch (DataIntegrityViolationException dive) {
                checked = false;
            }
        }
        return checked;
    }

    public PrinterDto getPrinterById(Long printerId) {
        return printerMapper.map(printerRepository.getOne(printerId));
    }

    public void updatePrinter(PrinterDto printerDto) {
        printerRepository.findById(printerDto.getPrinterId())
                .ifPresent(p -> {
                    p.setDeviceManufacturer(printerDto.getDeviceManufacturer());
                    p.setDeviceType(printerDto.getDeviceType());
                    p.setIpAddress(printerDto.getIpAddress());
                    p.setSerialNumber(printerDto.getSerialNumber());
                    printerRepository.save(p);
                });
    }

    public void getPrinterAndAddConsumableHer(Collection<Long> consumableIds, PrinterDto databasePrinter) {
        Collection<Consumable> consumableCollection = databasePrinter.getConsumableCollection();
        for (Long ids : consumableIds) {
            consumableRepository.getConsumableById(ids)
                    .ifPresent(consumableCollection::add);
        }
        printerRepository.save(printerMapper.reverse(databasePrinter));
    }

    private boolean checkSerialNumberOfDeviceBeforeSave(PrinterDto printerDto) {
        return printerRepository.findAll().stream()
                .noneMatch(p -> p.getSerialNumber().matches(printerDto.getSerialNumber()));
    }
}
