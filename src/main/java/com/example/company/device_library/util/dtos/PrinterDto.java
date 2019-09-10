package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.Computer;
import com.example.company.device_library.model.Consumable;
import lombok.*;

import java.util.Collection;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrinterDto {
    private Long printerId;
    private String deviceManufacturer;
    private String deviceType;
    private String serialNumber;
    private String ipAddress;
    private Computer computer;
    private Collection<Consumable> consumableCollection;

}
