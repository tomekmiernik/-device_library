package com.example.company.device_library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@MappedSuperclass
public abstract class Device extends BasicEntityField {
    
    private DeviceManufacturer deviceManufacturer;

    private DeviceType deviceType;

    private String serialNumber;

}
