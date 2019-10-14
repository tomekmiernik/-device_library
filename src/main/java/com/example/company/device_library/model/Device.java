package com.example.company.device_library.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.UniqueConstraint;


@Getter
@Setter
@MappedSuperclass

public class Device extends BasicEntityField {
    private String deviceManufacturer;
    private String deviceType;

    @Column(unique = true)
    private String serialNumber;
}
