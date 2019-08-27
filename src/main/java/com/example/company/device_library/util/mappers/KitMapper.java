package com.example.company.device_library.util.mappers;

import com.example.company.device_library.model.Kit;
import com.example.company.device_library.util.dtos.KitDto;
import org.springframework.stereotype.Component;

@Component
public class KitMapper implements Mapper<Kit, KitDto> {
    @Override
    public KitDto map(Kit from) {
        return KitDto.builder()
                .userId(from.getUserId())
                .mobileDeviceId(from.getMobileDeviceId())
                .computerId(from.getComputerId())
                .monitorId(from.getMonitorId())
                .printerId(from.getPrinterId())
                .build();
    }

    @Override
    public Kit reverse(KitDto to) {
        Kit kit = new Kit();
        kit.setUserId(to.getUserId());
        kit.setMobileDeviceId(to.getMobileDeviceId());
        kit.setComputerId(to.getComputerId());
        kit.setMonitorId(to.getMonitorId());
        kit.setPrinterId(to.getPrinterId());
        return kit;
    }
}
