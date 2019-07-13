package com.example.company.device_library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "computers")
public class Computer extends Device {

    @OneToMany(mappedBy = "computer")
    private Collection<Software> softwareCollection;

    @OneToMany(mappedBy = "computer")
    private Collection<Peripheral> peripheralCollection;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "printer_id")
    private Printer printer;

}
