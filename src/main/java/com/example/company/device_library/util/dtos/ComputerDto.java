package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.*;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComputerDto {
    private Long computerId;

    @NotEmpty(message = "To pole jest wymagane")
    private String deviceManufacturer;

    @NotEmpty(message = "To pole jest wymagane")
    private String deviceType;

    @NotEmpty(message = "To pole jest wymagane")
    private String serialNumber;

    @NotEmpty(message = "To pole jest wymagane")
    private String computerAdName;

    @NotNull(message = "To pole jest wymagane")
    private Computer.ComputerType computerType;

    private Printer printer;

    private Monitor monitor;

    private Boolean isUse;

    private Collection<Software> softwareCollection;
    private Collection<Peripheral> peripheralCollection;

}
