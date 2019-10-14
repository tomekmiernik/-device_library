package com.example.company.device_library.service;

import com.example.company.device_library.model.SimCard;
import com.example.company.device_library.repository.MobileDeviceRepository;
import com.example.company.device_library.repository.SimCardRepository;
import com.example.company.device_library.util.dtos.MobileDeviceDto;
import com.example.company.device_library.util.mappers.MobileDeviceMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class MobileDeviceService {
    private MobileDeviceRepository mobileDeviceRepository;
    private SimCardRepository simCardRepository;
    private MobileDeviceMapper mobileDeviceMapper;

    public MobileDeviceService(MobileDeviceRepository mobileDeviceRepository,
                               SimCardRepository simCardRepository,
                               MobileDeviceMapper mobileDeviceMapper) {
        this.mobileDeviceRepository = mobileDeviceRepository;
        this.simCardRepository = simCardRepository;
        this.mobileDeviceMapper = mobileDeviceMapper;
    }

    public Collection<MobileDeviceDto> getAllMobileDevices() {
        Collection<MobileDeviceDto> mobileDevices = mobileDeviceRepository.findAll()
                .stream()
                .map(mobileDeviceMapper::map)
                .collect(Collectors.toList());
        setDefaultCharsWhenMobileDeviceHasNotSimCard(mobileDevices);
        return mobileDevices;
    }

    public Collection<MobileDeviceDto> getAllFreeMobiles() {
        Collection<MobileDeviceDto> mobileDevices = mobileDeviceRepository.findFreeMobiles()
                .stream()
                .map(mobileDeviceMapper::map)
                .collect(Collectors.toList());
        setDefaultCharsWhenMobileDeviceHasNotSimCard(mobileDevices);
        return mobileDevices;
    }

    public Collection<MobileDeviceDto> getAllNotFreeMobiles() {
        Collection<MobileDeviceDto> mobileDevices = mobileDeviceRepository.findNotFreeMobiles()
                .stream()
                .map(mobileDeviceMapper::map)
                .collect(Collectors.toList());
        setDefaultCharsWhenMobileDeviceHasNotSimCard(mobileDevices);
        return mobileDevices;
    }

    public boolean addMobileDevice(MobileDeviceDto mobileDeviceDto) {
        boolean checked = checkDataBeforeSave(mobileDeviceDto);
        if (checked) {
            try {
                mobileDeviceRepository.save(mobileDeviceMapper.reverse(mobileDeviceDto));
            } catch (DataIntegrityViolationException dive) {
                checked = false;
            }
        }
        return checked;
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
                    t.setSerialNumber(mobileDeviceDto.getSerialNumber());
                    t.setDeviceType(mobileDeviceDto.getDeviceType());
                    mobileDeviceRepository.save(t);
                });
    }

    public void getMobileDeviceAndAddSimCardHim(Long simCardId, MobileDeviceDto databaseMobileDevice) {
        SimCard databaseSimCard = simCardRepository.getOne(simCardId);
        databaseSimCard.setIsUse(Boolean.TRUE);
        databaseMobileDevice.setSimCard(databaseSimCard);
        mobileDeviceRepository.save(mobileDeviceMapper.reverse(databaseMobileDevice));

    }

    private void setDefaultCharsWhenMobileDeviceHasNotSimCard(Collection<MobileDeviceDto> mobileDevices) {
        mobileDevices.stream().filter(md -> md.getSimCard() == null)
                .forEach(md -> {
                    md.setSimCard(new SimCard());
                    md.getSimCard().setPhoneNumber("Brak karty SIM");
                });
    }

    public void changeMobileOnReadyToUse(Long mobileId) {
        mobileDeviceRepository.getMobileDeviceById(mobileId)
                .ifPresent(m -> {
                    m.setIsUse(Boolean.FALSE);
                    mobileDeviceRepository.save(m);
                });
    }

    private boolean checkDataBeforeSave(MobileDeviceDto mobileDeviceDto) {
        boolean checkSerialNumb = mobileDeviceRepository.findAll().stream()
                .noneMatch(md -> md.getSerialNumber().matches(mobileDeviceDto.getSerialNumber()));
        boolean checkImeiNumb = mobileDeviceRepository.findAll().stream()
                .noneMatch(md -> md.getImeiNumber().matches(mobileDeviceDto.getImeiNumber()));
        return checkSerialNumb && checkImeiNumb;
    }
}
