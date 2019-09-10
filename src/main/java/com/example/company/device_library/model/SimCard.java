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

    @Column
    private String pinNumber;

    @Column
    private String pukNumber;

    @Column
    private String simCardNumber;

}
