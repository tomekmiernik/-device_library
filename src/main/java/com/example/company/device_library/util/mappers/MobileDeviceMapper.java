package com.example.company.device_library.util.mappers;

import com.example.company.device_library.model.MobileDevice;
import com.example.company.device_library.util.dtos.MobileDeviceDto;
import org.springframework.stereotype.Component;

@Component
public class MobileDeviceMapper implements Mapper<MobileDevice, MobileDeviceDto> {
    @Override
    public MobileDeviceDto map(MobileDevice from) {
        return MobileDeviceDto.builder()
                .mobileDeviceId(from.getId())
                .deviceManufacturer(from.getDeviceManufacturer())
                .deviceType(from.getDeviceType())
                .serialNumber(from.getSerialNumber())
                .phoneNumber(from.getPhoneNumber())
                .imeiNumber(from.getImeiNumber())
                .phoneType(from.getPhoneType())
                .user(from.getUser())
                .build();
    }

    @Override
    public MobileDevice reverse(MobileDeviceDto to) {
        MobileDevice mobileDevice = new MobileDevice();
        mobileDevice.setId(to.getMobileDeviceId());
        mobileDevice.setDeviceManufacturer(to.getDeviceManufacturer());
        mobileDevice.setDeviceType(to.getDeviceType());
        mobileDevice.setSerialNumber(to.getSerialNumber());
        mobileDevice.setPhoneNumber(to.getPhoneNumber());
        mobileDevice.setImeiNumber(to.getImeiNumber());
        mobileDevice.setPhoneType(to.getPhoneType());
        mobileDevice.setUser(to.getUser());
        return mobileDevice;
    }
}
