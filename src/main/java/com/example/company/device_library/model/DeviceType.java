package com.example.company.device_library.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "device_types")
public class DeviceType extends BasicEntityField {

    @Column(unique = true)
    private String typeName;

}
