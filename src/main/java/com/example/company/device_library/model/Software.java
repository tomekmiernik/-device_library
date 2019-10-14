package com.example.company.device_library.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "software")
public class Software extends BasicEntityField {

    @Column
    private String softwareName;
    @Column
    private String licenseNumber;

    @ManyToMany(mappedBy = "softwareCollection")
    private Collection<Computer> computers;
}
