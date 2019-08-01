package com.example.company.device_library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "device_manufacturers")
public class DeviceManufacturer extends BasicEntityField{

    @Column
    private String manufacturerName;

    @OneToMany(mappedBy = "deviceManufacturer")
    private Collection<DeviceType> deviceTypeCollection;
}
