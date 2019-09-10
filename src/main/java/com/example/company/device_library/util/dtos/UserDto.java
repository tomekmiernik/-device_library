package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.Computer;
import com.example.company.device_library.model.MobileDevice;
import com.example.company.device_library.model.Role;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String position;
    private String department;
    private String localization;
    private String email;
    private String password;
    private boolean active;
    private Computer computer;
    private MobileDevice mobileDevice;
    private Set<Role> roles;
}
