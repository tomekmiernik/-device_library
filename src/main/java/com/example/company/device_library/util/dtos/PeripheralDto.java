package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.Computer;
import com.example.company.device_library.model.Peripheral;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PeripheralDto {
    private Long peripheralId;

    @NotEmpty(message = "To pole jest wymagane")
    private String deviceManufacturer;

    @NotEmpty(message = "To pole jest wymagane")
    private String deviceType;

    @NotEmpty(message = "To pole jest wymagane")
    private String serialNumber;

    private Boolean isUse;

    @NotNull(message = "To pole jest wymagane")
    private Peripheral.Interface typeInterface;

    @NotEmpty(message = "To pole jest wymagane")
    private String namePeripheral;

    private Computer computer;
}
