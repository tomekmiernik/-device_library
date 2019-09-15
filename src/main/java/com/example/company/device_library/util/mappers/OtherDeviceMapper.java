package com.example.company.device_library.util.mappers;

import com.example.company.device_library.model.OtherDevice;
import com.example.company.device_library.util.dtos.OtherDeviceDto;
import com.example.company.device_library.util.mappers.Mapper;
import org.springframework.stereotype.Component;

@Component
public class OtherDeviceMapper implements Mapper<OtherDevice, OtherDeviceDto> {

    @Override
    public OtherDeviceDto map(OtherDevice from) {
        return OtherDeviceDto.builder()
                .otherDeviceId(from.getId())
                .deviceManufacturer(from.getDeviceManufacturer())
                .deviceType(from.getDeviceType())
                .serialNumber(from.getSerialNumber())
                .otherDeviceType(from.getOtherDeviceType())
                .description(from.getDescription())
                .build();
    }

    @Override
    public OtherDevice reverse(OtherDeviceDto to) {
        OtherDevice otherDevice = new OtherDevice();
        otherDevice.setId(to.getOtherDeviceId());
        otherDevice.setDeviceManufacturer(to.getDeviceManufacturer());
        otherDevice.setDeviceType(to.getDeviceType());
        otherDevice.setSerialNumber(to.getSerialNumber());
        otherDevice.setDescription(to.getDescription());
        otherDevice.setOtherDeviceType(to.getOtherDeviceType());
        return otherDevice;
    }
}
