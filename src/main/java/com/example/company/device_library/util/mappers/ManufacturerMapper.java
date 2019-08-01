package com.example.company.device_library.util.mappers;

import com.example.company.device_library.model.DeviceManufacturer;
import com.example.company.device_library.util.dtos.ManufacturerDto;
import org.springframework.stereotype.Component;

@Component
public class ManufacturerMapper implements Mapper<DeviceManufacturer, ManufacturerDto> {
    @Override
    public ManufacturerDto map(DeviceManufacturer from) {
        return ManufacturerDto.builder()
                .manufacturerId(from.getId())
                .manufacturerName(from.getManufacturerName())
                .build();
    }

    @Override
    public DeviceManufacturer reverse(ManufacturerDto to) {
        DeviceManufacturer deviceManufacturer = new DeviceManufacturer();
        deviceManufacturer.setId(to.getManufacturerId());
        deviceManufacturer.setManufacturerName(to.getManufacturerName());
        return deviceManufacturer;
    }
}
