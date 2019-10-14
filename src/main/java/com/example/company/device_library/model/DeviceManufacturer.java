package com.example.company.device_library.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Getter
@Setter
@Table(name = "device_manufacturers")
public class DeviceManufacturer extends BasicEntityField {

    @Column(unique = true)
    private String manufacturerName;

}
