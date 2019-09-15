package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.OtherDevice;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OtherDeviceDto {
    private Long otherDeviceId;
    private String deviceManufacturer;
    private String deviceType;
    private String serialNumber;
    private String description;
    private OtherDevice.OtherDeviceType otherDeviceType;
}
