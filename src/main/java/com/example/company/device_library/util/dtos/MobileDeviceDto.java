package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.MobileDevice;
import com.example.company.device_library.model.SimCard;
import com.example.company.device_library.model.User;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MobileDeviceDto {
    private Long mobileDeviceId;
    private String deviceManufacturer;
    private String deviceType;
    private String serialNumber;
    private String phoneNumber;
    private String imeiNumber;
    private MobileDevice.PhoneType phoneType;
    private SimCard simCard;
    private User user;
}
