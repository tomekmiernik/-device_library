package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.*;
import lombok.*;

import java.util.Collection;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComputerDto {
    private Long computerId;
    private DeviceManufacturer deviceManufacturer;
    private DeviceType deviceType;
    private String serialNumber;
    private Computer.ComputerType computerType;
    private Collection<Software> softwareCollection;
    private Collection<Peripheral> peripheralCollection;
    private User user;
    private Printer printer;

}
