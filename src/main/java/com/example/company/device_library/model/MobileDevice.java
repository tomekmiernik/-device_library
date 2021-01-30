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

    @Column(unique = true)
    private String imeiNumber;

    @Enumerated(EnumType.STRING)
    @Column
    private PhoneType phoneType;

    @Column
    private Boolean isUse;

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
