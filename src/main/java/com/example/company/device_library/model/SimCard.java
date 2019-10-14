package com.example.company.device_library.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "sims")
public class SimCard extends BasicEntityField{
    @Column
    private String phoneNumber;

    @Column(unique = true)
    private String pinNumber;

    @Column(unique = true)
    private String pukNumber;

    @Column(unique = true)
    private String simCardNumber;

    @Column
    private Boolean isUse;

}
