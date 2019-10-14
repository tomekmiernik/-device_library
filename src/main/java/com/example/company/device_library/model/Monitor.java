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
    @Enumerated(EnumType.STRING)
    MonitorType monitorType;

    @Column
    private Boolean isUse;

    public enum MonitorType {
        CRT,
        LCD,
        LED,
        OLED
    }
}
