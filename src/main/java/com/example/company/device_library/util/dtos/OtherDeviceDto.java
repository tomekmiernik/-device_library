package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.OtherDevice;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OtherDeviceDto {
    private Long otherDeviceId;

    @NotEmpty(message = "To pole jest wymagane")
    private String deviceManufacturer;

    @NotEmpty(message = "To pole jest wymagane")
    private String deviceType;

    @NotEmpty(message = "To pole jest wymagane")
    private String serialNumber;

    @NotEmpty(message = "To pole jest wymagane")
    private String description;

    @NotNull(message = "To pole jest wymagane")
    private OtherDevice.OtherDeviceType otherDeviceType;
}
