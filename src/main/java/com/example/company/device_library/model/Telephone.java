package com.example.company.device_library.model;

import lombok.*;

import javax.persistence.*;

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

    @Column
    @Enumerated(EnumType.STRING)
    private PhoneType phoneType;

    public enum PhoneType{
        WIRELESSLY("Bezprzewodowy"),
        WIRE("Przewodowy"),
        VOIP("VoIP");

        private String type;
        PhoneType(String type){
            this.type = type;
        }

        public String getType(){
            return type;
        }

    }
}
