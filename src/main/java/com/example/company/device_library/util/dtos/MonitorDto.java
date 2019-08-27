package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.Computer;
import com.example.company.device_library.model.Monitor;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class MonitorDto {
    private Long monitorId;
    private String deviceManufacturer;
    private String deviceType;
    private String serialNumber;
    private String inchValue;
    private Monitor.MonitorType monitorType;
    private Computer computer;
}
