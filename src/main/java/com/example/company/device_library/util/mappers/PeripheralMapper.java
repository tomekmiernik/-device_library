package com.example.company.device_library.util.mappers;

import com.example.company.device_library.model.Peripheral;
import com.example.company.device_library.util.dtos.PeripheralDto;
import org.springframework.stereotype.Component;

@Component
public class PeripheralMapper implements Mapper<Peripheral, PeripheralDto> {
    @Override
    public PeripheralDto map(Peripheral from) {
        return PeripheralDto.builder()
                .peripheralId(from.getId())
                .deviceManufacturer(from.getDeviceManufacturer())
                .deviceType(from.getDeviceType())
                .typeInterface(from.getTypeInterface())
                .serialNumber(from.getSerialNumber())
                .namePeripheral(from.getNamePeripheral())
                .build();
    }

    @Override
    public Peripheral reverse(PeripheralDto to) {
        Peripheral peripheral = new Peripheral();
        peripheral.setId(to.getPeripheralId());
        peripheral.setDeviceManufacturer(to.getDeviceManufacturer());
        peripheral.setDeviceType(to.getDeviceType());
        peripheral.setSerialNumber(to.getSerialNumber());
        peripheral.setTypeInterface(to.getTypeInterface());
        peripheral.setNamePeripheral(to.getNamePeripheral());
        return peripheral;
    }
}
