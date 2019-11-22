package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.UserApp;
import lombok.*;

import java.util.Collection;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private Long roleId;

    private String roleName;

    private Collection<UserApp> userApps;
}
