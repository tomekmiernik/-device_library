package com.example.company.device_library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "device_types")
public class DeviceType extends BasicEntityField {

    @Column(unique = true)
    private String typeName;

}
