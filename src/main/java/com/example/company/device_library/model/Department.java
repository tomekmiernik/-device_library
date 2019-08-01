package com.example.company.device_library.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "departments")
public class Department extends BasicEntityField{

    @Column
    private String departmentName;

    @Column
    private String departmentShortName;

}
