package com.example.company.device_library.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;


@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User extends BasicEntityField{

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String position;

    @Column
    private Department department;

    @Column
    private String localization;

    @Column
    private String email;

    @Column
    private String password;

    @Transient
    private String confirmPassword;

    @Column
    private boolean active;

    @OneToOne
    @JoinColumn(name = "computer_id")
    private Computer computer;

    @OneToOne
    @JoinColumn(name = "telephone_id")
    private Telephone telephone;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

}
