package com.example.company.device_library.util.mappers;

import com.example.company.device_library.model.Telephone;
import com.example.company.device_library.util.dtos.TelephoneDto;

public class TelephoneMapper implements Mapper<Telephone, TelephoneDto> {
    @Override
    public TelephoneDto map(Telephone from) {
        return TelephoneDto.builder()
                .telephoneId(from.getId())
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
    public Telephone reverse(TelephoneDto to) {
        Telephone telephone = new Telephone();
        telephone.setId(to.getTelephoneId());
        telephone.setDeviceManufacturer(to.getDeviceManufacturer());
        telephone.setDeviceType(to.getDeviceType());
        telephone.setSerialNumber(to.getSerialNumber());
        telephone.setPhoneNumber(to.getPhoneNumber());
        telephone.setImeiNumber(to.getImeiNumber());
        telephone.setPhoneType(to.getPhoneType());
        telephone.setUser(to.getUser());
        return telephone;
    }
}
