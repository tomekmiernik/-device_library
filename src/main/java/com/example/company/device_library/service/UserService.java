package com.example.company.device_library.service;

import com.example.company.device_library.model.Computer;
import com.example.company.device_library.model.MobileDevice;
import com.example.company.device_library.model.SimCard;
import com.example.company.device_library.model.User;
import com.example.company.device_library.repository.UserRepository;
import com.example.company.device_library.util.dtos.ComputerDto;
import com.example.company.device_library.util.dtos.MobileDeviceDto;
import com.example.company.device_library.util.dtos.UserDto;
import com.example.company.device_library.util.mappers.ComputerMapper;
import com.example.company.device_library.util.mappers.MobileDeviceMapper;
import com.example.company.device_library.util.mappers.UserMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private ComputerMapper computerMapper;
    private MobileDeviceMapper mobileDeviceMapper;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       ComputerMapper computerMapper,
                       MobileDeviceMapper mobileDeviceMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.computerMapper = computerMapper;
        this.mobileDeviceMapper = mobileDeviceMapper;
    }


    public Collection<UserDto> getUsersByLastName(String userLastName) {
        List<UserDto> collectUsers = userRepository.findUsersByLastName(userLastName)
                .stream()
                .map(userMapper::map)
                .collect(Collectors.toList());
        setDefaultCharsWhenUserHasNotMobileDevice(collectUsers);
        return collectUsers;
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
                .filter(User::isActive)
                .map(userMapper::map)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long userId) {
        return userMapper.map(userRepository.getOne(userId));
    }

    public void updateUser(UserDto userDto) {
        userRepository.getUserById(userDto.getUserId())
                .ifPresent(u -> {
                    u.setActive(userDto.isActive());
                    u.setEmail(userDto.getEmail());
                    u.setFirstName(userDto.getFirstName());
                    u.setLastName(userDto.getLastName());
                    u.setLocalization(userDto.getLocalization());
                    u.setPosition(userDto.getPosition());
                    u.setComputer(userDto.getComputer());
                    u.setDepartment(userDto.getDepartment());
                    u.setRoles(userDto.getRoles());
                    u.setMobileDevice(userDto.getMobileDevice());
                    userRepository.save(u);
                });
    }

    public void addComputer(Computer computer, UserDto databaseUser) {
        ComputerDto computerDto = computerMapper.map(computer);
        databaseUser.setComputer(computerMapper.reverse(computerDto));
        userRepository.save(userMapper.reverse(databaseUser));
    }

    public void addDevice(MobileDevice mobileDevice, UserDto databaseUser) {
        MobileDeviceDto mobileDeviceDto = mobileDeviceMapper.map(mobileDevice);
        databaseUser.setMobileDevice(mobileDeviceMapper.reverse(mobileDeviceDto));
        userRepository.save(userMapper.reverse(databaseUser));
    }

    public void getUsersFilerByPhoneNumber(Collection<UserDto> users) {
        setDefaultCharsWhenUserHasNotMobileDevice(users);
    }

    private void setDefaultCharsWhenUserHasNotMobileDevice(Collection<UserDto> users) {
        users.stream()
                .filter(u -> u.getMobileDevice() == null)
                .forEach(u -> {
                    u.setMobileDevice(new MobileDevice());
                    u.getMobileDevice().setSimCard(new SimCard());
                    u.getMobileDevice().getSimCard().setPhoneNumber("- - -");
                });
    }
}
