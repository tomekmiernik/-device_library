package com.example.company.device_library.util.mappers;

import com.example.company.device_library.model.DeviceType;
import com.example.company.device_library.util.dtos.DeviceTypesDto;
import org.springframework.stereotype.Component;

@Component
public class DeviceTypeMapper implements Mapper<DeviceType, DeviceTypesDto> {
    @Override
    public DeviceTypesDto map(DeviceType from) {
        return DeviceTypesDto.builder()
                .deviceTypeId(from.getId())
                .typeName(from.getTypeName())
                .build();

    }

    @Override
    public DeviceType reverse(DeviceTypesDto to) {
        DeviceType deviceType = new DeviceType();
        deviceType.setId(to.getDeviceTypeId());
        deviceType.setTypeName(to.getTypeName());
        return deviceType;
    }
}
