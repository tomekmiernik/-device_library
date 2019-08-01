package com.example.company.device_library.service;

import com.example.company.device_library.model.User;
import com.example.company.device_library.repository.UserRepository;
import com.example.company.device_library.util.dtos.UserDto;
import com.example.company.device_library.util.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Collection<UserDto> getUsersByLastName(String userLastName) {
        return userRepository.findUsersByLastName(userLastName)
                .stream()
                .map(userMapper::map)
                .collect(Collectors.toList());
    }

    public User addUser(UserDto userDto) {
        return userRepository.save(userMapper.reverse(userDto));
    }

    public UserDto getUserByUsernameAsEmail(String email) {
        return userRepository.findUserByUsernameAsEmail(email)
                .map(userMapper::map)
                .get();
    }

    public Collection<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::map)
                .collect(Collectors.toList());
    }

    public User getUserById(Long userId) {
        return userRepository.getOne(userId);
    }
}
