package com.example.company.device_library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@MappedSuperclass
public class Device extends BasicEntityField {

    @Column
    private String deviceManufacturer;
    @Column
    private String deviceType;
    @Column
    private String serialNumber;

}
