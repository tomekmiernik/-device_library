package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.MobileDevice;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimCardDto {
    private Long simCardId;
    private String phoneNumber;
    private String pinNumber;
    private String pukNumber;
    private String simCardNumber;
    private MobileDevice mobileDevice;
}
