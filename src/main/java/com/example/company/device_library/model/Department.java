package com.example.company.device_library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Getter
@Setter
@Table(name = "departments")
public class Department extends BasicEntityField {

    @Column(unique = true)
    private String departmentName;

    @Column(unique = true)
    private String departmentShortName;

}
