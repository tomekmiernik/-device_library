package com.example.company.device_library.service;

import com.example.company.device_library.model.Software;
import com.example.company.device_library.repository.SoftwareRepository;
import com.example.company.device_library.util.dtos.SoftwareDto;
import com.example.company.device_library.util.mappers.SoftwareMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SoftwareService {
    private SoftwareRepository softwareRepository;
    private SoftwareMapper softwareMapper;

    public SoftwareService(SoftwareRepository softwareRepository, SoftwareMapper softwareMapper) {
        this.softwareRepository = softwareRepository;
        this.softwareMapper = softwareMapper;
    }

    public Collection<SoftwareDto> getAllSoftwares() {
        return softwareRepository.findAll()
                .stream()
                .map(softwareMapper::map)
                .collect(Collectors.toList());
    }

    public Software addSoftware(SoftwareDto softwareDto) {
        return softwareRepository.save(softwareMapper.reverse(softwareDto));
    }

    public SoftwareDto getSoftwareById(Long softwareId) {
        return softwareMapper.map(softwareRepository.getOne(softwareId));
    }

    public void updateSoftware(SoftwareDto softwareDto) {
        softwareRepository.getSoftwareById(softwareDto.getSoftwareId())
                .ifPresent( s-> {
                    s.setSoftwareName(softwareDto.getSoftwareName());
                    s.setLicenseNumber(softwareDto.getLicenseNumber());
                    s.setComputer(softwareDto.getComputer());
                    softwareRepository.save(s);
                });
    }
}
