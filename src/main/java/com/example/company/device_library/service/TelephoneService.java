package com.example.company.device_library.service;

import com.example.company.device_library.repository.TelephoneRepository;
import com.example.company.device_library.util.dtos.TelephoneDto;
import com.example.company.device_library.util.mappers.TelephoneMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class TelephoneService {
    private TelephoneRepository telephoneRepository;
    private TelephoneMapper telephoneMapper;

    public TelephoneService(TelephoneRepository telephoneRepository, TelephoneMapper telephoneMapper) {
        this.telephoneRepository = telephoneRepository;
        this.telephoneMapper = telephoneMapper;
    }

    public Collection<TelephoneDto> getAllTelephones() {
        return telephoneRepository.findAll()
                .stream()
                .map(telephoneMapper::map)
                .collect(Collectors.toList());
    }

    public boolean addTelephone(TelephoneDto telephoneDto) {
        boolean checked = checkSerialNumberOfDeviceBeforeSave(telephoneDto);
        if (checked) {
            try {
                telephoneRepository.save(telephoneMapper.reverse(telephoneDto));
            } catch (DataIntegrityViolationException dive) {
                checked = false;
            }
        }
        return checked;
    }

    public TelephoneDto getTelephoneById(Long phoneId) {
        return telephoneMapper.map(telephoneRepository.getOne(phoneId));
    }

    public void updateTelephone(TelephoneDto telephoneDto) {
        telephoneRepository.getTelephoneById(telephoneDto.getTelephoneId())
                .ifPresent(t -> {
                    t.setDeviceManufacturer(telephoneDto.getDeviceManufacturer());
                    t.setDeviceType(telephoneDto.getDeviceType());
                    t.setSerialNumber(telephoneDto.getSerialNumber());
                    t.setPhoneNumber(telephoneDto.getPhoneNumber());
                    t.setInternalNumber(telephoneDto.getInternalNumber());
                    t.setPhoneType(telephoneDto.getPhoneType());
                    telephoneRepository.save(t);
                });
    }

    private boolean checkSerialNumberOfDeviceBeforeSave(TelephoneDto telephoneDto) {
        return telephoneRepository.findAll().stream()
                .noneMatch(t -> t.getSerialNumber().matches(telephoneDto.getSerialNumber()));
    }
}
