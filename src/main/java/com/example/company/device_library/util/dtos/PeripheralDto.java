package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.Computer;
import com.example.company.device_library.model.Peripheral;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PeripheralDto {
    private Long peripheralId;
    private Peripheral.Interface typeInterface;
    private String namePeripheral;
    private Computer computer;
}
