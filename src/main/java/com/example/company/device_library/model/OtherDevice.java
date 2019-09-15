package com.example.company.device_library.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "other_device")
public class OtherDevice extends Device {

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private OtherDeviceType otherDeviceType;

    public enum OtherDeviceType {
        MOBILE("Przenośne"),
        STATIONARY("Stacjonarne");

        private String type;
        OtherDeviceType(String type){
            this.type = type;
        }
        public String getType(){
            return type;
        }
    }
}
