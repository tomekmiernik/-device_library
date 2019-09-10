package com.example.company.device_library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;


@Getter
@Setter
@MappedSuperclass
public class Device extends BasicEntityField {
    private String deviceManufacturer;
    private String deviceType;
    private String serialNumber;
}
