package com.example.company.device_library.service;

import com.example.company.device_library.repository.PeripheralRepository;
import com.example.company.device_library.util.dtos.PeripheralDto;
import com.example.company.device_library.util.mappers.PeripheralMapper;
import org.springframework.dao.DataIntegrityViolationException;
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

    public Collection<PeripheralDto> getNotFreePeripherals() {
        return peripheralRepository.findNotFreePeripheral()
                .stream()
                .map(peripheralMapper::map)
                .collect(Collectors.toList());
    }

    public Collection<PeripheralDto> getAllFreePeripherals() {
        return peripheralRepository.findFreePeripheral()
                .stream()
                .map(peripheralMapper::map)
                .collect(Collectors.toList());
    }

    public boolean addPeripheral(PeripheralDto peripheralDto) {
        boolean checked = checkSerialNumberOfDeviceBeforeSave(peripheralDto);
        if (checked) {
            try {
                peripheralDto.setIsUse(Boolean.FALSE);
                peripheralRepository.save(peripheralMapper.reverse(peripheralDto));
            }catch (DataIntegrityViolationException dive){
                checked = false;
            }
        }
        return checked;
    }

    public PeripheralDto getPeripheralById(Long peripheralId) {
        return peripheralMapper.map(peripheralRepository.getOne(peripheralId));
    }

    public void updatePeripheral(PeripheralDto peripheralDto) {
        peripheralRepository.getPeripheralById(peripheralDto.getPeripheralId())
                .ifPresent(p -> {
                    p.setTypeInterface(peripheralDto.getTypeInterface());
                    p.setNamePeripheral(peripheralDto.getNamePeripheral());
                    p.setSerialNumber(peripheralDto.getSerialNumber());
                    peripheralRepository.save(p);
                });
    }

    public void changePeripheralOnReadyToUse(Long peripheralId) {
        peripheralRepository.getPeripheralById(peripheralId)
                .ifPresent(p -> {
                    p.setIsUse(Boolean.FALSE);
                    peripheralRepository.save(p);
                });
    }

    private boolean checkSerialNumberOfDeviceBeforeSave(PeripheralDto peripheralDto) {
        return peripheralRepository.findAll().stream()
                .noneMatch(p -> p.getSerialNumber().matches(peripheralDto.getSerialNumber()));
    }
}
