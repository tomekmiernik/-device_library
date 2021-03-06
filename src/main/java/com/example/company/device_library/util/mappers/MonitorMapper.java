package com.example.company.device_library.util.mappers;

import com.example.company.device_library.model.Monitor;
import com.example.company.device_library.util.dtos.MonitorDto;
import org.springframework.stereotype.Component;

@Component
public class MonitorMapper implements Mapper<Monitor, MonitorDto> {
    @Override
    public MonitorDto map(Monitor from) {
        return MonitorDto.builder()
                .monitorId(from.getId())
                .deviceManufacturer(from.getDeviceManufacturer())
                .deviceType(from.getDeviceType())
                .serialNumber(from.getSerialNumber())
                .inchValue(from.getInchValue())
                .isUse(from.getIsUse())
                .monitorType(from.getMonitorType())
                .build();
    }

    @Override
    public Monitor reverse(MonitorDto to) {
        Monitor monitor = new Monitor();
        monitor.setId(to.getMonitorId());
        monitor.setDeviceManufacturer(to.getDeviceManufacturer());
        monitor.setDeviceType(to.getDeviceType());
        monitor.setSerialNumber(to.getSerialNumber());
        monitor.setIsUse(to.getIsUse());
        monitor.setInchValue(to.getInchValue());
        monitor.setMonitorType(to.getMonitorType());
        return monitor;
    }
}
