package com.example.company.device_library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "telephones")
public class Telephone extends Device {

    @Column
    private String phoneNumber;

    @Column
    private String imeiNumber;

    @Enumerated(EnumType.STRING)
    @Column
    private PhoneType phoneType;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public enum PhoneType{
        PHONE("Stacjonarny"),
        MOBILE_PHONE("Komórkowy");

        private String type;

        PhoneType(String type){
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
}
