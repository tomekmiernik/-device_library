package com.example.company.device_library.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;


@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserApp extends BasicEntityField {

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String position;

    @Column
    private String department;

    @Column
    private String localization;

    @Column(unique = true)
    private String email;

    @Column
    @Size(min=6, max= 30)
    private String password;

    @Column
    private boolean active;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "computer_id")
    private Computer computer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mobileDevice_id")
    private MobileDevice mobileDevice;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

}
