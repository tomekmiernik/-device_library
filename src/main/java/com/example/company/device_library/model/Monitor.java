package com.example.company.device_library.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "monitors")
public class Monitor extends Device {

    @Column
    String inchValue;

    @Column
    MonitorType monitorType;

    @OneToOne
    @JoinColumn(name = "computer_id")
    private Computer computer;

    public enum MonitorType{
        CRT,
        LCD,
        LED,
        OLED
    }
}
