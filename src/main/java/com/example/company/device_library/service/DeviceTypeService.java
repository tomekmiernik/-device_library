package com.example.company.device_library.service;

import com.example.company.device_library.model.DeviceType;
import com.example.company.device_library.repository.DeviceTypeRepository;
import com.example.company.device_library.util.dtos.DeviceTypesDto;
import com.example.company.device_library.util.mappers.DeviceTypeMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class DeviceTypeService {
    private DeviceTypeRepository deviceTypeRepository;
    private DeviceTypeMapper deviceTypeMapper;

    public DeviceTypeService(DeviceTypeRepository deviceTypeRepository,
        DeviceTypeMapper deviceTypeMapper){
        this.deviceTypeRepository = deviceTypeRepository;
        this.deviceTypeMapper = deviceTypeMapper;
    }

    public DeviceType addDeviceType(DeviceTypesDto deviceTypesDto){
        return deviceTypeRepository.save(deviceTypeMapper.reverse(deviceTypesDto));
    }

    public Collection<DeviceTypesDto> getAllDeviceTypes(){
        return deviceTypeRepository.findAll()
                .stream()
                .map(deviceTypeMapper::map)
                .collect(Collectors.toList());
    }

    public DeviceType getDeviceTypeById(Long deviceTypeId){
        return deviceTypeRepository.getOne(deviceTypeId);
    }

    public DeviceTypesDto getDeviceTypeByName(String typeName) {
        return deviceTypeRepository.getDeviceTypeByName(typeName)
                .map(deviceTypeMapper::map)
                .get();
    }

    public void updateDeviceType(DeviceTypesDto typeDto) {
        deviceTypeRepository.getDeviceTypeById(typeDto.getDeviceTypeId())
                .ifPresent(t-> {
                    t.setTypeName(typeDto.getTypeName());
                    deviceTypeRepository.save(t);
                });
    }
}
