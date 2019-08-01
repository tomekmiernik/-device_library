package com.example.company.device_library.util.mappers;

import com.example.company.device_library.model.User;
import com.example.company.device_library.util.dtos.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserDto> {
    @Override
    public UserDto map(User from) {
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
                .telephone(from.getTelephone())
                .build();
    }

    @Override
    public User reverse(UserDto to) {
        User user = new User();
        user.setId(to.getUserId());
        user.setFirstName(to.getFirstName());
        user.setLastName(to.getLastName());
        user.setEmail(to.getEmail());
        user.setPosition(to.getPosition());
        user.setActive(to.isActive());
        user.setDepartment(to.getDepartment());
        user.setComputer(to.getComputer());
        user.setLocalization(to.getLocalization());
        user.setPassword(to.getPassword());
        user.setRoles(to.getRoles());
        user.setTelephone(to.getTelephone());
        return user;
    }
}
