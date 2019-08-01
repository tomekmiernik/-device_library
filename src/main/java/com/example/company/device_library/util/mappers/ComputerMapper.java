package com.example.company.device_library.util.mappers;

import com.example.company.device_library.model.Computer;
import com.example.company.device_library.util.dtos.ComputerDto;

public class ComputerMapper implements Mapper<Computer, ComputerDto> {
    @Override
    public ComputerDto map(Computer from) {
        return ComputerDto.builder()
                .computerId(from.getId())
                .deviceManufacturer(from.getDeviceManufacturer())
                .deviceType(from.getDeviceType())
                .serialNumber(from.getSerialNumber())
                .computerType(from.getComputerType())
                .softwareCollection(from.getSoftwareCollection())
                .peripheralCollection(from.getPeripheralCollection())
                .user(from.getUser())
                .printer(from.getPrinter())
                .build();
    }

    @Override
    public Computer reverse(ComputerDto to) {
        Computer computer = new Computer();
        computer.setId(to.getComputerId());
        computer.setDeviceManufacturer(to.getDeviceManufacturer());
        computer.setDeviceType(to.getDeviceType());
        computer.setSerialNumber(to.getSerialNumber());
        computer.setComputerType(to.getComputerType());
        computer.setSoftwareCollection(to.getSoftwareCollection());
        computer.setPeripheralCollection(to.getPeripheralCollection());
        computer.setUser(to.getUser());
        computer.setPrinter(to.getPrinter());
        return computer;
    }
}
