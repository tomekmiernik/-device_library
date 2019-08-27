package com.example.company.device_library.service;

import com.example.company.device_library.model.Printer;
import com.example.company.device_library.repository.PrinterRepository;
import com.example.company.device_library.util.dtos.PrinterDto;
import com.example.company.device_library.util.mappers.PrinterMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class PrinterService {
    private PrinterRepository printerRepository;
    private PrinterMapper printerMapper;

    public PrinterService(PrinterRepository printerRepository, PrinterMapper printerMapper) {
        this.printerRepository = printerRepository;
        this.printerMapper = printerMapper;
    }

    public Collection<PrinterDto> getAllPrinters() {
        return printerRepository.findAll()
                .stream()
                .map(printerMapper::map)
                .collect(Collectors.toList());
    }

    public Printer addPrinter(PrinterDto printerDto) {
        return printerRepository.save(printerMapper.reverse(printerDto));
    }

    public PrinterDto getPrinterById(Long printerId) {
        return printerMapper.map(printerRepository.getOne(printerId));
    }

    public void updatePrinter(PrinterDto printerDto) {
        printerRepository.findById(printerDto.getPrinterId())
                .ifPresent(p-> {
                    p.setDeviceManufacturer(printerDto.getDeviceManufacturer());
                    p.setDeviceType(printerDto.getDeviceType());
                    p.setComputer(printerDto.getComputer());
                    p.setIpAddress(printerDto.getIpAddress());
                    p.setSerialNumber(printerDto.getSerialNumber());
                    printerRepository.save(p);
                });
    }
}
