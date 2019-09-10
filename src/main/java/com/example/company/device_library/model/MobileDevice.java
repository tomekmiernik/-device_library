package com.example.company.device_library.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "mobile_device")
public class MobileDevice extends Device {

    @Column
    private String imeiNumber;

    @Enumerated(EnumType.STRING)
    @Column
    private PhoneType phoneType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "simCard_id")
    private SimCard simCard;

    public enum PhoneType {
        MODEM("Modem"),
        MOBILE_PHONE("Komórkowy"),
        TABLET("Tablet");

        private String type;

        PhoneType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
}
