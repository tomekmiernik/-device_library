package com.example.company.device_library.util.mappers;

import com.example.company.device_library.model.Role;
import com.example.company.device_library.util.dtos.RoleDto;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements Mapper<Role, RoleDto>{
    @Override
    public RoleDto map(Role from) {
        return RoleDto.builder()
                .roleId(from.getId())
                .roleName(from.getRoleName())
                .userApps(from.getUserApps())
                .build();
    }

    @Override
    public Role reverse(RoleDto to) {
        Role role = new Role();
        role.setId(to.getRoleId());
        role.setRoleName(to.getRoleName());
        role.setUserApps(to.getUserApps());
        return role;
    }
}
