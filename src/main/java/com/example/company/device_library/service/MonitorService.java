package com.example.company.device_library.service;

import com.example.company.device_library.repository.MonitorRepository;
import com.example.company.device_library.util.dtos.MonitorDto;
import com.example.company.device_library.util.mappers.MonitorMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class MonitorService {
    private MonitorRepository monitorRepository;
    private MonitorMapper monitorMapper;

    public MonitorService(MonitorRepository monitorRepository, MonitorMapper monitorMapper) {
        this.monitorRepository = monitorRepository;
        this.monitorMapper = monitorMapper;
    }

    public Collection<MonitorDto> getAllMonitors() {
        return monitorRepository.findAll()
                .stream()
                .map(monitorMapper::map)
                .collect(Collectors.toList());
    }


    public boolean addMonitor(MonitorDto monitorDto) {
        boolean checked = checkDataBeforeSave(monitorDto);
        if(checked){
            try {
                monitorRepository.save(monitorMapper.reverse(monitorDto));
            }catch (DataIntegrityViolationException dive){
                checked = false;
            }
        }
        return checked;
    }

    public MonitorDto getMonitorById(Long monitorId) {
        return monitorMapper.map(monitorRepository.getOne(monitorId));
    }

    public void updateMonitor(MonitorDto monitorDto) {
        monitorRepository.getMonitorsById(monitorDto.getMonitorId())
                .ifPresent(m -> {
                    m.setDeviceManufacturer(monitorDto.getDeviceManufacturer());
                    m.setDeviceType(monitorDto.getDeviceType());
                    m.setSerialNumber(monitorDto.getSerialNumber());
                    m.setInchValue(monitorDto.getInchValue());
                    m.setMonitorType(monitorDto.getMonitorType());
                    monitorRepository.save(m);
                });
    }

    private boolean checkDataBeforeSave(MonitorDto monitorDto) {
        return monitorRepository.findAll().stream()
                .noneMatch(m -> m.getSerialNumber().matches(monitorDto.getSerialNumber()));
    }
}
