package com.example.company.device_library.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "softwares")
public class Software extends BasicEntityField{

    @Column
    private String softwareName;
    @Column
    private String licenseNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "computer_id")
    private Computer computer;
}
