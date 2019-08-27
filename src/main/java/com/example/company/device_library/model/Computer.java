package com.example.company.device_library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Getter
@Setter
@Table(name = "computers")
public class Computer extends Device {

    @Column
    private String computerAdName;

    @Enumerated(EnumType.STRING)
    @Column
    private ComputerType computerType;

    @OneToMany(mappedBy = "computer", cascade = CascadeType.ALL)
    private Collection<Software> softwareCollection = new HashSet<>();

    @OneToMany(mappedBy = "computer", cascade = CascadeType.ALL)
    private Collection<Peripheral> peripheralCollection = new HashSet<>();

    @OneToOne(mappedBy = "computer", cascade = CascadeType.ALL)
    private User user;

    @OneToOne(mappedBy = "computer", cascade = CascadeType.ALL)
    private Monitor monitor;

    @OneToOne(mappedBy = "computer", cascade = CascadeType.ALL)
    private Printer printer;

    public enum ComputerType {
        AIO("AIO"),
        DESKTOP("Stacjonarny"),
        LAPTOP("Laptop");

        private String type;

        ComputerType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public void addSoftware(Software software) {
        if (softwareCollection == null) {
            softwareCollection = new HashSet<>();
        }
        software.setComputer(this);
        softwareCollection.add(software);
    }

    public void addPeripheral(Peripheral peripheral) {
        if (peripheralCollection == null) {
            peripheralCollection = new HashSet<>();
        }
        peripheral.setComputer(this);
        peripheralCollection.add(peripheral);
    }
}
