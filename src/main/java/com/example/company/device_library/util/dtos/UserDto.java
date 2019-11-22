package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.Computer;
import com.example.company.device_library.model.MobileDevice;
import com.example.company.device_library.model.Role;
import com.example.company.device_library.util.annotations.ValidEmail;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long userId;

    @NotEmpty(message = "To pole jest wymagane")
    @Size(min = 3, max = 16, message = "Polskie imiona to przynajmniej 3 znaki")
    private String firstName;

    @NotEmpty(message = "To pole jest wymagane")
    @Size(min = 3, max = 30, message = "Polskie nazwiska to przynajmniej 3 znaki")
    private String lastName;

    @NotEmpty(message = "To pole jest wymagane")
    private String position;

    @NotNull(message = "To pole jest wymagane")
    private String department;

    @NotNull(message = "To pole jest wymagane")
    private String localization;

    @Email(message = "Email jest niepoprawny")
    @NotEmpty(message = "To pole jest wymagane")
    private String email;

    private String password;
    private String confirmPassword;
    private boolean active;
    private Computer computer;
    private MobileDevice mobileDevice;
    private Collection<Role> roles;
}
