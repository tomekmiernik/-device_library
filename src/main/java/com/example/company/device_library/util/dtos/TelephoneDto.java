package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.DeviceManufacturer;
import com.example.company.device_library.model.DeviceType;
import com.example.company.device_library.model.Telephone;
import com.example.company.device_library.model.User;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TelephoneDto {
    private Long telephoneId;
    private DeviceManufacturer deviceManufacturer;
    private DeviceType deviceType;
    private String serialNumber;
    private String phoneNumber;
    private String imeiNumber;
    private Telephone.PhoneType phoneType;
    private User user;
}
