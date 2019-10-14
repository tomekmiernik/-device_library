package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.MobileDevice;
import com.example.company.device_library.model.SimCard;
import com.example.company.device_library.model.UserApp;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MobileDeviceDto {
    private Long mobileDeviceId;

    @NotEmpty(message = "To pole jest wymagane")
    private String deviceManufacturer;

    @NotEmpty(message = "To pole jest wymagane")
    private String deviceType;

    @NotEmpty(message = "To pole jest wymagane")
    private String serialNumber;

    @NotEmpty(message = "To pole jest wymagane")
    private String imeiNumber;

    @NotNull(message = "To pole jest wymagane")
    private MobileDevice.PhoneType phoneType;
    private SimCard simCard;
    private UserApp userApp;
    private Boolean isUse;
}
