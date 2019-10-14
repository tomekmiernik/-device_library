package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.Computer;
import com.example.company.device_library.model.Monitor;
import lombok.*;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonitorDto {
    private Long monitorId;

    @NotEmpty(message = "To pole jest wymagane")
    private String deviceManufacturer;

    @NotEmpty(message = "To pole jest wymagane")
    private String deviceType;


    @NotEmpty(message = "To pole jest wymagane")
    private String serialNumber;

    @NotEmpty(message = "To pole jest wymagane")
    private String inchValue;

    private Monitor.MonitorType monitorType;

    private Boolean isUse;

}
