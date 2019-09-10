package com.example.company.device_library.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "telephones")
public class Telephone extends Device {

    @Column
    private String phoneNumber;

    @Column
    private String internalNumber;
}
