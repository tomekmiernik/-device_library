package com.example.company.device_library.util.mappers;

import com.example.company.device_library.model.Peripheral;
import com.example.company.device_library.util.dtos.PeripheralDto;

public class PeripheralMapper implements Mapper<Peripheral, PeripheralDto> {
    @Override
    public PeripheralDto map(Peripheral from) {
        return PeripheralDto.builder()
                .peripheralId(from.getId())
                .typeInterface(from.getTypeInterface())
                .namePeripheral(from.getNamePeripheral())
                .computer(from.getComputer())
                .build();
    }

    @Override
    public Peripheral reverse(PeripheralDto to) {
        Peripheral peripheral = new Peripheral();
        peripheral.setId(to.getPeripheralId());
        peripheral.setTypeInterface(to.getTypeInterface());
        peripheral.setNamePeripheral(to.getNamePeripheral());
        peripheral.setComputer(to.getComputer());
        return peripheral;
    }
}
