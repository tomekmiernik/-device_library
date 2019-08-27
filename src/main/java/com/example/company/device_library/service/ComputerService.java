package com.example.company.device_library.service;

import com.example.company.device_library.model.Computer;
import com.example.company.device_library.repository.ComputerRepository;
import com.example.company.device_library.util.dtos.ComputerDto;
import com.example.company.device_library.util.mappers.ComputerMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ComputerService {
    private ComputerRepository computerRepository;
    private ComputerMapper computerMapper;

    public ComputerService(ComputerRepository computerRepository, ComputerMapper computerMapper) {
        this.computerRepository = computerRepository;
        this.computerMapper = computerMapper;
    }

    public Computer addComputer(ComputerDto computerDto) {
        return computerRepository.save(computerMapper.reverse(computerDto));
    }

    public Collection<ComputerDto> getAllComputers() {
        return computerRepository.findAll()
                .stream()
                .map(computerMapper::map)
                .collect(Collectors.toList());
    }

    public ComputerDto getComputerById(Long computerId) {
        Computer getOne = computerRepository.getOne(computerId);
        return computerMapper.map(getOne);
    }

    public void updateComputer(ComputerDto computerDto) {
        computerRepository.getComputerById(computerDto.getComputerId())
                .ifPresent(c -> {
                    c.setDeviceManufacturer(computerDto.getDeviceManufacturer());
                    c.setDeviceType(computerDto.getDeviceType());
                    c.setSerialNumber(computerDto.getSerialNumber());
                    c.setComputerAdName(computerDto.getComputerAdName());
                    c.setComputerType(computerDto.getComputerType());
                    c.setSoftwareCollection(computerDto.getSoftwareCollection());
                    c.setPeripheralCollection(computerDto.getPeripheralCollection());
                    computerRepository.save(c);
                });

    }
}
