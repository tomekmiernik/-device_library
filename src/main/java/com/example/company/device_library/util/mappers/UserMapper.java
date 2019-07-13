package com.example.company.device_library.util.mappers;

import com.example.company.device_library.model.User;
import com.example.company.device_library.util.dtos.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserDto> {
    @Override
    public UserDto map(User from) {
        return UserDto.builder()
                .id(from.getId())
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
        user.setId(to.getId());
        user.setFirstName(to.getFirstName());
        user.setLastName(to.getLastName());
        user.setEmail(to.getEmail());
        user.setActive(to.isActive());
        user.setLocalization(to.getLocalization());
        user.setPosition(to.getPosition());
        return user;
    }
}
