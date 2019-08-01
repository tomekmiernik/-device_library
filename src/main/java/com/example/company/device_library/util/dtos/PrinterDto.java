package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.Computer;
import com.example.company.device_library.model.Consumable;
import com.example.company.device_library.model.DeviceManufacturer;
import com.example.company.device_library.model.DeviceType;
import lombok.*;

import java.util.Collection;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrinterDto {
    private Long printerId;
    private DeviceManufacturer deviceManufacturer;
    private DeviceType deviceType;
    private String serialNumber;
    private String ipAddress;
    private Computer computer;
    private Collection<Consumable> consumableCollection;

}
