package com.example.company.device_library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role extends BasicEntityField {

    @Column
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
