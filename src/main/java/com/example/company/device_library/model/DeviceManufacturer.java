package com.example.company.device_library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Getter
@Setter
@Table(name = "device_manufacturers")
public class DeviceManufacturer extends BasicEntityField {

    @Column
    private String manufacturerName;

    @OneToMany(mappedBy = "deviceManufacturer", cascade = CascadeType.ALL)
    private Collection<DeviceType> deviceTypeCollection = new HashSet<>();

    public void addDeviceType(DeviceType deviceType) {
        if (deviceTypeCollection == null) {
            deviceTypeCollection = new HashSet<>();
        }
        deviceType.setId(this.getId());
        deviceTypeCollection.add(deviceType);
    }
}
