package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.MobileDevice;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimCardDto {
    private Long simCardId;

    @NotEmpty(message = "To pole jest wymagane")
    private String phoneNumber;

    @NotEmpty(message = "To pole jest wymagane")
    private String pinNumber;

    @NotEmpty(message = "To pole jest wymagane")
    private String pukNumber;

    @NotEmpty(message = "To pole jest wymagane")
    private String simCardNumber;

    private Boolean isUse;

    private MobileDevice mobileDevice;
}
