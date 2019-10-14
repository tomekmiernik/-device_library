package com.example.company.device_library.util.mappers;

import com.example.company.device_library.model.UserApp;
import com.example.company.device_library.util.dtos.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserApp, UserDto> {
    @Override
    public UserDto map(UserApp from) {
        return UserDto.builder()
                .userId(from.getId())
                .firstName(from.getFirstName())
                .lastName(from.getLastName())
                .email(from.getEmail())
                .position(from.getPosition())
                .active(from.isActive())
                .department(from.getDepartment())
                .computer(from.getComputer())
                .localization(from.getLocalization())
                .password(from.getPassword())
                .roles(from.getRoles())
                .mobileDevice(from.getMobileDevice())
                .build();
    }

    @Override
    public UserApp reverse(UserDto to) {
        UserApp userApp = new UserApp();
        userApp.setId(to.getUserId());
        userApp.setFirstName(to.getFirstName());
        userApp.setLastName(to.getLastName());
        userApp.setEmail(to.getEmail());
        userApp.setPosition(to.getPosition());
        userApp.setActive(to.isActive());
        userApp.setDepartment(to.getDepartment());
        userApp.setComputer(to.getComputer());
        userApp.setLocalization(to.getLocalization());
        userApp.setPassword(to.getPassword());
        userApp.setRoles(to.getRoles());
        userApp.setMobileDevice(to.getMobileDevice());
        return userApp;
    }
}
