package com.example.company.device_library.util.mappers;

import com.example.company.device_library.model.Computer;
import com.example.company.device_library.util.dtos.ComputerDto;
import org.springframework.stereotype.Component;

@Component
public class ComputerMapper implements Mapper<Computer, ComputerDto> {
    @Override
    public ComputerDto map(Computer from) {
        return ComputerDto.builder()
                .computerId(from.getId())
                .computerAdName(from.getComputerAdName())
                .deviceManufacturer(from.getDeviceManufacturer())
                .deviceType(from.getDeviceType())
                .serialNumber(from.getSerialNumber())
                .computerType(from.getComputerType())
                .isUse(from.getIsUse())
                .softwareCollection(from.getSoftwareCollection())
                .peripheralCollection(from.getPeripheralCollection())
                .monitor(from.getMonitor())
                .printer(from.getPrinter())
                .build();
    }

    @Override
    public Computer reverse(ComputerDto to) {
        Computer computer = new Computer();
        computer.setId(to.getComputerId());
        computer.setComputerAdName(to.getComputerAdName());
        computer.setDeviceManufacturer(to.getDeviceManufacturer());
        computer.setDeviceType(to.getDeviceType());
        computer.setSerialNumber(to.getSerialNumber());
        computer.setComputerType(to.getComputerType());
        computer.setIsUse(to.getIsUse());
        computer.setSoftwareCollection(to.getSoftwareCollection());
        computer.setPeripheralCollection(to.getPeripheralCollection());
        computer.setMonitor(to.getMonitor());
        computer.setPrinter(to.getPrinter());
        return computer;
    }
}
