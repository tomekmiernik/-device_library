package com.example.company.device_library.service;

import com.example.company.device_library.model.DeviceManufacturer;
import com.example.company.device_library.repository.ManufacturerRepository;
import com.example.company.device_library.util.dtos.ManufacturerDto;
import com.example.company.device_library.util.mappers.ManufacturerMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ManufacturerService {
    private ManufacturerRepository manufacturerRepository;
    private ManufacturerMapper manufacturerMapper;

    public ManufacturerService(ManufacturerRepository manufacturerRepository,
                               ManufacturerMapper manufacturerMapper) {
        this.manufacturerRepository = manufacturerRepository;
        this.manufacturerMapper = manufacturerMapper;
    }

    public boolean addDeviceManufacturer(ManufacturerDto manufacturerDto) {
        boolean checked = checkDataBeforeSave(manufacturerDto);
        if (checked) {
            try {
                manufacturerRepository.save(manufacturerMapper.reverse(manufacturerDto));
            }catch (DataIntegrityViolationException dive){
                checked = false;
            }
        }
        return checked;
    }

    public Collection<ManufacturerDto> getAllManufacturers() {
        return manufacturerRepository.findAll()
                .stream()
                .map(manufacturerMapper::map)
                .collect(Collectors.toList());
    }

    public ManufacturerDto getDeviceManufacturerById(Long manufacturerId) {
        DeviceManufacturer getOne = manufacturerRepository.getOne(manufacturerId);
        return manufacturerMapper.map(getOne);
    }


    public void updateManufacturer(ManufacturerDto manufacturerDto) {
        manufacturerRepository.getManufacturerById(manufacturerDto.getManufacturerId())
                .ifPresent(p -> {
                    p.setManufacturerName(manufacturerDto.getManufacturerName());
                    manufacturerRepository.save(p);
                });
    }

    private boolean checkDataBeforeSave(ManufacturerDto manufacturerDto) {
        return manufacturerRepository.findAll().stream()
                .noneMatch(m -> m.getManufacturerName().matches(manufacturerDto.getManufacturerName()));
    }
}
