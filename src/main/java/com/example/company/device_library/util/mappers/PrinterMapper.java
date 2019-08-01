package com.example.company.device_library.util.mappers;

import com.example.company.device_library.model.Printer;
import com.example.company.device_library.util.dtos.PrinterDto;

public class PrinterMapper implements Mapper<Printer, PrinterDto> {
    @Override
    public PrinterDto map(Printer from) {
        return PrinterDto.builder()
                .printerId(from.getId())
                .deviceManufacturer(from.getDeviceManufacturer())
                .deviceType(from.getDeviceType())
                .serialNumber(from.getSerialNumber())
                .ipAddress(from.getIpAddress())
                .computer(from.getComputer())
                .consumableCollection(from.getConsumableCollection())
                .build();
    }

    @Override
    public Printer reverse(PrinterDto to) {
        Printer printer = new Printer();
        printer.setId(to.getPrinterId());
        printer.setDeviceManufacturer(to.getDeviceManufacturer());
        printer.setDeviceType(to.getDeviceType());
        printer.setSerialNumber(to.getSerialNumber());
        printer.setIpAddress(to.getIpAddress());
        printer.setComputer(to.getComputer());
        printer.setConsumableCollection(to.getConsumableCollection());
        return printer;
    }
}