package com.example.company.device_library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "device_types")
public class DeviceType extends BasicEntityField{

    @Column
    private String typeName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manufacturer_id")
    private DeviceManufacturer deviceManufacturer;
}
