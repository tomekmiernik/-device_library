package com.example.company.device_library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "printers")
public class Printer extends Device {

    @Column
    private String ipAddress;

    @OneToOne
    @JoinColumn(name = "computer_id")
    private Computer computer;

    @OneToMany(mappedBy = "printer", cascade = CascadeType.ALL)
    private Collection<Consumable> consumableCollection;
}
