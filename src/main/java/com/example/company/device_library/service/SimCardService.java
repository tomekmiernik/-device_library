package com.example.company.device_library.service;

import com.example.company.device_library.model.SimCard;
import com.example.company.device_library.repository.SimCardRepository;
import com.example.company.device_library.util.dtos.SimCardDto;
import com.example.company.device_library.util.mappers.SimCardMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SimCardService {
    private SimCardRepository simCardRepository;
    private SimCardMapper simCardMapper;

    public SimCardService(SimCardRepository simCardRepository, SimCardMapper simCardMapper) {
        this.simCardRepository = simCardRepository;
        this.simCardMapper = simCardMapper;
    }

    public Collection<SimCardDto> getAllCardSims() {
        return simCardRepository.findAll()
                .stream()
                .map(simCardMapper::map)
                .collect(Collectors.toList());
    }

    public SimCard addSimCard(SimCardDto simCardDto) {
        return simCardRepository.save(simCardMapper.reverse(simCardDto));
    }

    public SimCardDto getSimCardById(Long simCardId) {
        return simCardMapper.map(simCardRepository.getOne(simCardId));
    }

    public void updateSimCard(SimCardDto simCardDto) {
        simCardRepository.getSimCardById(simCardDto.getSimCardId()).ifPresent(
                s -> {
                    s.setPhoneNumber(simCardDto.getPhoneNumber());
                    s.setPinNumber(simCardDto.getPinNumber());
                    s.setPukNumber(simCardDto.getPukNumber());
                    s.setSimCardNumber(simCardDto.getSimCardNumber());
                    simCardRepository.save(s);
                });
    }
}
