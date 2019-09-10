package com.example.company.device_library.service;

import com.example.company.device_library.model.MobileDevice;
import com.example.company.device_library.model.SimCard;
import com.example.company.device_library.repository.MobileDeviceRepository;
import com.example.company.device_library.util.dtos.MobileDeviceDto;
import com.example.company.device_library.util.mappers.MobileDeviceMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class MobileDeviceService {
    private MobileDeviceRepository mobileDeviceRepository;
    private MobileDeviceMapper mobileDeviceMapper;

    public MobileDeviceService(MobileDeviceRepository mobileDeviceRepository, MobileDeviceMapper mobileDeviceMapper) {
        this.mobileDeviceRepository = mobileDeviceRepository;
        this.mobileDeviceMapper = mobileDeviceMapper;
    }

    public Collection<MobileDeviceDto> getAllMobileDevices() {
        return mobileDeviceRepository.findAll()
                .stream()
                .map(mobileDeviceMapper::map)
                .collect(Collectors.toList());
    }

    public MobileDevice addMobileDevice(MobileDeviceDto mobileDeviceDto) {
        return mobileDeviceRepository.save(mobileDeviceMapper.reverse(mobileDeviceDto));
    }

    public MobileDeviceDto getMobileDeviceById(Long mobileDeviceId) {
        return mobileDeviceMapper.map(mobileDeviceRepository.getOne(mobileDeviceId));
    }

    public void updateMobileDevice(MobileDeviceDto mobileDeviceDto) {
        mobileDeviceRepository.getMobileDeviceById(mobileDeviceDto.getMobileDeviceId())
                .ifPresent(t -> {
                    t.setImeiNumber(mobileDeviceDto.getImeiNumber());
                    t.setPhoneType(mobileDeviceDto.getPhoneType());
                    t.setDeviceManufacturer(mobileDeviceDto.getDeviceManufacturer());
                    t.setDeviceType(mobileDeviceDto.getDeviceType());
                    mobileDeviceRepository.save(t);
                });
    }

    public void getComputerAndAddSimCardHim(SimCard simCard, MobileDeviceDto databaseMobileDevice) {
        databaseMobileDevice.setSimCard(simCard);
        mobileDeviceRepository.save(mobileDeviceMapper.reverse(databaseMobileDevice));

    }
}
