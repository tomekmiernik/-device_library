package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.Telephone;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelephoneDto {
    private Long telephoneId;
    private String deviceManufacturer;
    private String deviceType;
    private String serialNumber;
    private String phoneNumber;
    private String internalNumber;
    private Telephone.PhoneType phoneType;
}
