package com.example.company.device_library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "peripherals")
public class Peripheral extends BasicEntityField{

    @Enumerated(EnumType.STRING)
    @Column
    private Interface typeDevice;

    @Column
    private String namePeripheral;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "computer_id")
    private Computer computer;

    enum Interface{
        WIRELESSLY("Bezprzewodowa"),
        WIRE("Przewodowa");

        private String type;

        Interface(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
}
