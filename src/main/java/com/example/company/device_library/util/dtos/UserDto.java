package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.Computer;
import com.example.company.device_library.model.Department;
import com.example.company.device_library.model.Role;
import com.example.company.device_library.model.Telephone;
import lombok.*;

import java.util.Collection;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String position;
    private Department department;
    private String localization;
    private String email;
    private String password;
    private boolean active;
    private Computer computer;
    private Telephone telephone;
    private Set<Role> roles;
}
