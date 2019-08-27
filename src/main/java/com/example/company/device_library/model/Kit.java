package com.example.company.device_library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "kits")
public class Kit extends BasicEntityField{

    @Column
    /*@OneToOne(mappedBy = "user_id")*/
    private Long userId;

    @Column
   /* @OneToMany(mappedBy = "telephone_id")*/
    private Long mobileDeviceId;

    @Column
    /*@OneToMany(mappedBy = "computer_id")*/
    private Long computerId;

    @Column
    /*@OneToMany(mappedBy = "monitor_id")*/
    private Long monitorId;

    @Column
   /* @OneToMany(mappedBy = "printer_id")*/
    private Long printerId;

}
