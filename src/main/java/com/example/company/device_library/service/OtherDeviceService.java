package com.example.company.device_library.service;

import com.example.company.device_library.repository.OtherDeviceRepository;
import com.example.company.device_library.util.dtos.OtherDeviceDto;
import com.example.company.device_library.util.mappers.OtherDeviceMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class OtherDeviceService {
    private OtherDeviceRepository otherDeviceRepository;
    private OtherDeviceMapper otherDeviceMapper;

    public OtherDeviceService(OtherDeviceRepository otherDeviceRepository,
                              OtherDeviceMapper otherDeviceMapper) {
        this.otherDeviceRepository = otherDeviceRepository;
        this.otherDeviceMapper = otherDeviceMapper;
    }

    public Collection<OtherDeviceDto> getAllDevices() {
        return otherDeviceRepository.findAll()
                .stream()
                .map(otherDeviceMapper::map)
                .collect(Collectors.toList());
    }

    public boolean addOtherDevice(OtherDeviceDto otherDeviceDto) {
        boolean checked = checkDataBeforeSave(otherDeviceDto);
        if (checked) {
            try {
                otherDeviceRepository.save(otherDeviceMapper.reverse(otherDeviceDto));
            } catch (DataIntegrityViolationException dive) {
                checked = false;
            }
        }
        return checked;
    }

    public OtherDeviceDto getOtherDeviceById(Long otherDeviceId) {
        return otherDeviceMapper.map(otherDeviceRepository.getOne(otherDeviceId));
    }

    public void updateOtherDevice(OtherDeviceDto otherDeviceDto) {
        otherDeviceRepository.getOtherDeviceById(otherDeviceDto.getOtherDeviceId())
                .ifPresent(od -> {
                    od.setDeviceManufacturer(otherDeviceDto.getDeviceManufacturer());
                    od.setOtherDeviceType(otherDeviceDto.getOtherDeviceType());
                    od.setSerialNumber(otherDeviceDto.getSerialNumber());
                    od.setDescription(otherDeviceDto.getDescription());
                    od.setOtherDeviceType(otherDeviceDto.getOtherDeviceType());
                    otherDeviceRepository.save(od);
                });
    }

    private boolean checkDataBeforeSave(OtherDeviceDto otherDeviceDto) {
        return otherDeviceRepository.findAll().stream()
                .noneMatch(od -> od.getSerialNumber().matches(otherDeviceDto.getSerialNumber()));
    }
}
