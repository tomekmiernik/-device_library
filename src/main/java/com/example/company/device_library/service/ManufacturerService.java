package com.example.company.device_library.service;

import com.example.company.device_library.model.DeviceManufacturer;
import com.example.company.device_library.repository.ManufacturerRepository;
import com.example.company.device_library.util.dtos.ManufacturerDto;
import com.example.company.device_library.util.mappers.ManufacturerMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ManufacturerService {
    private ManufacturerRepository manufacturerRepository;
    private ManufacturerMapper manufacturerMapper;

    public ManufacturerService(ManufacturerRepository manufacturerRepository,
                               ManufacturerMapper manufacturerMapper){
        this.manufacturerRepository = manufacturerRepository;
        this.manufacturerMapper = manufacturerMapper;
    }

    public DeviceManufacturer addDeviceManufacturer(ManufacturerDto manufacturerDto){
        return manufacturerRepository.save(manufacturerMapper.reverse(manufacturerDto));
    }

    public Collection<ManufacturerDto> getAllManufacturers(){
        return manufacturerRepository.findAll()
                .stream()
                .map(manufacturerMapper::map)
                .collect(Collectors.toList());
    }

    public DeviceManufacturer getDeviceManufacturerById(Long manufacturerId){
        return manufacturerRepository.getOne(manufacturerId);
    }
}
