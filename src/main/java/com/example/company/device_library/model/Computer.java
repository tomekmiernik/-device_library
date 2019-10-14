package com.example.company.device_library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
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

    @Column
    private Boolean isUse;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "comp_soft",
            joinColumns = @JoinColumn(name = "computer_id"),
            inverseJoinColumns = @JoinColumn(name = "software_id"))
    private Collection<Software> softwareCollection;

    @OneToMany(mappedBy = "computer", cascade = CascadeType.ALL)
    private Collection<Peripheral> peripheralCollection;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "monitor_id")
    private Monitor monitor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "printer_id")
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
}
