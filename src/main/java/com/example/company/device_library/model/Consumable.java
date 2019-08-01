package com.example.company.device_library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "consumables")
public class Consumable extends BasicEntityField{

    @Column
    @Enumerated(EnumType.STRING)
    private ConsumableType consumableType;

    @Column
    private String color;

    @Column
    private String consumableMark;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "printer_id")
    private Printer printer;

    public enum ConsumableType{
        INK("Tusz"),
        TONER("Toner");

        private String type;

        ConsumableType(String type){
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
}
