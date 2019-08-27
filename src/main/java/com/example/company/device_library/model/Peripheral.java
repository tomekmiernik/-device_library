package com.example.company.device_library.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "peripherals")
public class Peripheral extends Device{

    @Enumerated(EnumType.STRING)
    @Column
    private Interface typeInterface;

    @Column
    private String namePeripheral;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "computer_id")
    private Computer computer;

    public enum Interface{
        WIRELESSLY("Bezprzewodowo"),
        WIRE("Przewodowo");

        private String type;

        Interface(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
}
