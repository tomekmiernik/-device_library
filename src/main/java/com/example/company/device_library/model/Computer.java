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

    @Enumerated(EnumType.STRING)
    @Column
    private ComputerType computerType;

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

    public enum ComputerType{
        DESKTOP("Stacjonarny"),
        LAPTOP("Laptop");

        private String type;

        ComputerType(String type){
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
}
