package com.example.company.device_library.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role extends BasicEntityField {

    @Column
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Collection<UserApp> userApps;
}
