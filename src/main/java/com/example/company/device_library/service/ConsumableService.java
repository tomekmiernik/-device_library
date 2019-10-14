package com.example.company.device_library.service;

import com.example.company.device_library.model.Consumable;
import com.example.company.device_library.repository.ConsumableRepository;
import com.example.company.device_library.util.dtos.ConsumableDto;
import com.example.company.device_library.util.mappers.ConsumableMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ConsumableService {
    private ConsumableRepository consumableRepository;
    private ConsumableMapper consumableMapper;

    public ConsumableService(ConsumableRepository consumableRepository,
                             ConsumableMapper consumableMapper) {
        this.consumableRepository = consumableRepository;
        this.consumableMapper = consumableMapper;
    }

    public Collection<ConsumableDto> getAllConsumables() {
        return consumableRepository.findAll()
                .stream()
                .map(consumableMapper::map)
                .collect(Collectors.toList());
    }

    public boolean addConsumable(ConsumableDto consumableDto) {
        boolean checked = checkDataBeforeSave(consumableDto);
        if (checked) {
            try {
                consumableRepository.save(consumableMapper.reverse(consumableDto));
            }catch (DataIntegrityViolationException dive){
                checked = false;
            }
        }
        return checked;
    }

    public ConsumableDto getConsumableById(Long consumableId) {
        return consumableMapper.map(consumableRepository.getOne(consumableId));
    }

    public void updateConsumable(ConsumableDto consumableDto) {
        consumableRepository.getConsumableById(consumableDto.getConsumableId())
                .ifPresent(c -> {
                    c.setColor(consumableDto.getColor());
                    c.setConsumableMark(consumableDto.getConsumableMark());
                    c.setConsumableType(consumableDto.getConsumableType());
                    c.setPrinter(consumableDto.getPrinter());
                    consumableRepository.save(c);
                });
    }

    private boolean checkDataBeforeSave(ConsumableDto consumableDto) {
        return consumableRepository.findAll()
                .stream()
                .noneMatch(c -> c.getConsumableMark()
                        .matches(consumableDto.getConsumableMark()));
    }
}
