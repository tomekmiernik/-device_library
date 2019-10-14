package com.example.company.device_library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "consumables")
public class Consumable extends BasicEntityField {

    @Column
    @Enumerated(EnumType.STRING)
    private ConsumableType consumableType;

    @Column
    @Enumerated(EnumType.STRING)
    private Color color;

    @Column(unique = true)
    private String consumableMark;

    @ManyToOne
    @JoinColumn(name = "printer_id")
    private Printer printer;

    public enum ConsumableType {
        INK("Tusz"),
        TONER("Toner");

        private String type;

        ConsumableType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public enum Color{
        CYAN("Niebieski"),
        MAGENTA("Czerwony"),
        YELLOW("Żółty"),
        BLACK("Czarny");

        private String color;

        Color(String color){this.color = color;}

        public String getColor(){return color; }
    }
}
