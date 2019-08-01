package com.example.company.device_library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "softwares")
public class Software extends BasicEntityField{

    @Column
    private String softwareName;
    @Column
    private String licenseNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "computer_id")
    private Computer computer;
}
