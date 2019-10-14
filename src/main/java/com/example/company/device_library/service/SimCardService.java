package com.example.company.device_library.service;

import com.example.company.device_library.repository.SimCardRepository;
import com.example.company.device_library.util.dtos.SimCardDto;
import com.example.company.device_library.util.mappers.SimCardMapper;
import org.springframework.dao.DataIntegrityViolationException;
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

    public Collection<SimCardDto> getAllFreeSimCards() {
        return simCardRepository.findAllFreeSimCards()
                .stream()
                .map(simCardMapper::map)
                .collect(Collectors.toList());
    }

    public Collection<SimCardDto> getAllNotFreeSimCards() {
        return simCardRepository.findAllNotFreeSimCards()
                .stream()
                .map(simCardMapper::map)
                .collect(Collectors.toList());
    }

    public boolean addSimCard(SimCardDto simCardDto) {
        boolean checked = checkNumbersOfSimCard(simCardDto);
        if (checked) {
            try {
                simCardDto.setIsUse(Boolean.FALSE);
                simCardRepository.save(simCardMapper.reverse(simCardDto));
            } catch (DataIntegrityViolationException dive) {
                checked = false;
            }
        }
        return checked;
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

    public void changeSimOnReadyToUse(Long simId) {
        simCardRepository.getSimCardById(simId)
                .ifPresent(s -> {
                    s.setIsUse(Boolean.FALSE);
                    simCardRepository.save(s);
                });
    }

    private boolean checkNumbersOfSimCard(SimCardDto simCardDto) {
        boolean checkCardNumb = simCardRepository.findAll().stream()
                .noneMatch(sc -> sc.getSimCardNumber().matches(simCardDto.getSimCardNumber()));
        boolean checkCardPin = simCardRepository.findAll().stream()
                .noneMatch(sc -> sc.getPinNumber().matches(simCardDto.getPinNumber()));
        boolean checkCardPuk = simCardRepository.findAll().stream()
                .noneMatch(sc -> sc.getPukNumber().matches(simCardDto.getPukNumber()));
        return checkCardNumb && checkCardPin && checkCardPuk;
    }
}
