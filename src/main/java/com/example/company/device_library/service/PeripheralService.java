package com.example.company.device_library.service;

import com.example.company.device_library.model.Peripheral;
import com.example.company.device_library.repository.PeripheralRepository;
import com.example.company.device_library.util.dtos.PeripheralDto;
import com.example.company.device_library.util.mappers.PeripheralMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class PeripheralService {
    private PeripheralRepository peripheralRepository;
    private PeripheralMapper peripheralMapper;

    public PeripheralService(PeripheralRepository peripheralRepository, PeripheralMapper peripheralMapper) {
        this.peripheralRepository = peripheralRepository;
        this.peripheralMapper = peripheralMapper;
    }

    public Collection<PeripheralDto> getAllPeripherals() {
        return peripheralRepository.findAll()
                .stream()
                .map(peripheralMapper::map)
                .collect(Collectors.toList());
    }

    public Peripheral addPeripheral(PeripheralDto peripheralDto) {
        return peripheralRepository.save(peripheralMapper.reverse(peripheralDto));
    }

    public PeripheralDto getPeripheralById(Long peripheralId) {
        return peripheralMapper.map(peripheralRepository.getOne(peripheralId));
    }

    public void updatePeripheral(PeripheralDto peripheralDto) {
        peripheralRepository.getPeripheralById(peripheralDto.getPeripheralId())
                .ifPresent(p -> {
                    p.setTypeInterface(peripheralDto.getTypeInterface());
                    p.setNamePeripheral(peripheralDto.getNamePeripheral());
                    p.setComputer(peripheralDto.getComputer());
                    peripheralRepository.save(p);
                });
    }
}
