package com.example.company.device_library.service;

import com.example.company.device_library.model.Role;
import com.example.company.device_library.repository.RoleRepository;
import com.example.company.device_library.util.dtos.RoleDto;
import com.example.company.device_library.util.mappers.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RoleService {
    private RoleRepository roleRepository;
    private RoleMapper roleMapper;

    @Autowired
    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
        initialization();
    }

    private void initialization() {
        Role adminRole = new Role();
        adminRole.setRoleName("ADMIN");
        adminRole.setUserApps(new ArrayList<>());
        if (checkDataBeforeSave(roleMapper.map(adminRole))) {
            roleRepository.save(adminRole);
        }

        Role userRole = new Role();
        userRole.setRoleName("USER");
        userRole.setUserApps(new ArrayList<>());
        if (checkDataBeforeSave(roleMapper.map(userRole))) {
            roleRepository.save(userRole);
        }
    }

    public boolean addRole(RoleDto roleDto) {
        boolean checked = checkDataBeforeSave(roleDto);
        if (checked) {
            try {
                roleRepository.save(roleMapper.reverse(roleDto));
            } catch (DataIntegrityViolationException dive) {
                checked = false;
            }
        }
        return checked;
    }

    private boolean checkDataBeforeSave(RoleDto roleDto) {
        return roleRepository.findAll()
                .stream()
                .noneMatch(r -> r.getRoleName().matches(roleDto.getRoleName()));
    }
}
