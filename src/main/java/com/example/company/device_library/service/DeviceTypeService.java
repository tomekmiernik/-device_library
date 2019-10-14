package com.example.company.device_library.service;

import com.example.company.device_library.model.DeviceType;
import com.example.company.device_library.repository.DeviceTypeRepository;
import com.example.company.device_library.util.dtos.DeviceTypesDto;
import com.example.company.device_library.util.mappers.DeviceTypeMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class DeviceTypeService {
    private DeviceTypeRepository deviceTypeRepository;
    private DeviceTypeMapper deviceTypeMapper;

    public DeviceTypeService(DeviceTypeRepository deviceTypeRepository,
                             DeviceTypeMapper deviceTypeMapper) {
        this.deviceTypeRepository = deviceTypeRepository;
        this.deviceTypeMapper = deviceTypeMapper;
    }

    public boolean addDeviceType(DeviceTypesDto deviceTypesDto) {
        boolean checked = checkDataBeforeSave(deviceTypesDto);
        if (checked) {
            try {
                deviceTypeRepository.save(deviceTypeMapper.reverse(deviceTypesDto));
            } catch (DataIntegrityViolationException dive) {
                checked = false;
            }
        }
        return checked;
    }

    public Collection<DeviceTypesDto> getAllDeviceTypes() {
        return deviceTypeRepository.findAll()
                .stream()
                .map(deviceTypeMapper::map)
                .collect(Collectors.toList());
    }

    public DeviceType getDeviceTypeById(Long deviceTypeId) {
        return deviceTypeRepository.getOne(deviceTypeId);
    }


    public void updateDeviceType(DeviceTypesDto typeDto) {
        deviceTypeRepository.getDeviceTypeById(typeDto.getDeviceTypeId())
                .ifPresent(t -> {
                    t.setTypeName(typeDto.getTypeName());
                    deviceTypeRepository.save(t);
                });
    }

    private boolean checkDataBeforeSave(DeviceTypesDto deviceTypesDto) {
        return deviceTypeRepository.findAll().stream()
                .noneMatch(dt -> dt.getTypeName().matches(deviceTypesDto.getTypeName()));
    }
}
