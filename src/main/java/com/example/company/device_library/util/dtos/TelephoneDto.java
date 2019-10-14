package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.Telephone;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelephoneDto {
    private Long telephoneId;

    @NotEmpty(message = "To pole jest wymagane")
    private String deviceManufacturer;

    @NotEmpty(message = "To pole jest wymagane")
    private String deviceType;

    @NotEmpty(message = "To pole jest wymagane")
    private String serialNumber;

    @NotEmpty(message = "To pole jest wymagane")
    private String phoneNumber;

    private String internalNumber;

    @NotNull(message = "To pole jest wymagane")
    private Telephone.PhoneType phoneType;
}
