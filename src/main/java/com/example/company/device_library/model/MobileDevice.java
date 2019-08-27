package com.example.company.device_library.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "mobile_device")
public class MobileDevice extends Device {

    @Column
    private String phoneNumber;

    @Column
    private String imeiNumber;

    @Enumerated(EnumType.STRING)
    @Column
    private PhoneType phoneType;

    @OneToOne
    @JoinColumn(name = "user_id")
    @Transient
    private User user;

    public enum PhoneType{
        PHONE("Stacjonarny"),
        MOBILE_PHONE("Komórkowy"),
        TABLET("Tablet");

        private String type;

        PhoneType(String type){
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
}
