package com.example.company.device_library.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "printers")
public class Printer extends Device {

    @Column
    private String ipAddress;

    @OneToMany(mappedBy = "printer", cascade = CascadeType.ALL)
    private Collection<Consumable> consumableCollection = new HashSet<>();


    public void addConsumable(Consumable consumable) {
        if (consumableCollection == null) {
            consumableCollection = new HashSet<>();
        }
        consumable.setPrinter(this);
        consumableCollection.add(consumable);
    }
}
