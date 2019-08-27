package com.example.company.device_library.service;

import com.example.company.device_library.model.Kit;
import com.example.company.device_library.repository.KitRepository;
import com.example.company.device_library.util.dtos.KitDto;
import com.example.company.device_library.util.mappers.KitMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class KitService {
    private KitRepository kitRepository;
    private KitMapper kitMapper;

    public KitService(KitRepository kitRepository,
                      KitMapper kitMapper) {
        this.kitRepository = kitRepository;
        this.kitMapper = kitMapper;
    }

    public Collection<KitDto> getAllKits() {
        return kitRepository.findAll()
                .stream()
                .map(kitMapper::map)
                .collect(Collectors.toList());
    }

    public Kit addKit(KitDto kitDto) {
        return kitRepository.save(kitMapper.reverse(kitDto));
    }

    public KitDto getKitById(Long kitId) {
        return kitMapper.map(kitRepository.getOne(kitId));
    }

    public void updateKit(KitDto kitDto) {
        kitRepository.findKitById(kitDto.getKitId())
                .ifPresent(k->{
                   k.setUserId(kitDto.getUserId());
                   k.setComputerId(kitDto.getComputerId());
                   k.setMonitorId(kitDto.getMonitorId());
                   k.setMobileDeviceId(kitDto.getMobileDeviceId());
                   k.setPrinterId(kitDto.getPrinterId());
                });
    }
}
