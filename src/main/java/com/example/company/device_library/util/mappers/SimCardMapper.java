package com.example.company.device_library.util.mappers;

import com.example.company.device_library.model.SimCard;
import com.example.company.device_library.util.dtos.SimCardDto;
import org.springframework.stereotype.Component;

@Component
public class SimCardMapper implements Mapper<SimCard, SimCardDto> {
    @Override
    public SimCardDto map(SimCard from) {
        return SimCardDto.builder()
                .simCardId(from.getId())
                .phoneNumber(from.getPhoneNumber())
                .pinNumber(from.getPinNumber())
                .pukNumber(from.getPukNumber())
                .simCardNumber(from.getSimCardNumber())
                .isUse(from.getIsUse())
                .build();
    }

    @Override
    public SimCard reverse(SimCardDto to) {
        SimCard simCard = new SimCard();
        simCard.setId(to.getSimCardId());
        simCard.setPhoneNumber(to.getPhoneNumber());
        simCard.setPinNumber(to.getPinNumber());
        simCard.setPukNumber(to.getPukNumber());
        simCard.setSimCardNumber(to.getSimCardNumber());
        simCard.setIsUse(to.getIsUse());
        return simCard;
    }
}
