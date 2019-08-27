package com.example.company.device_library.service;

import com.example.company.device_library.model.Computer;
import com.example.company.device_library.model.Device;
import com.example.company.device_library.model.MobileDevice;
import com.example.company.device_library.model.User;
import com.example.company.device_library.repository.UserRepository;
import com.example.company.device_library.util.dtos.ComputerDto;
import com.example.company.device_library.util.dtos.MobileDeviceDto;
import com.example.company.device_library.util.dtos.UserDto;
import com.example.company.device_library.util.mappers.ComputerMapper;
import com.example.company.device_library.util.mappers.MobileDeviceMapper;
import com.example.company.device_library.util.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
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

    public UserDto getUserById(Long userId) {
        return userMapper.map(userRepository.getOne(userId));
    }

    public void updateUser(UserDto userDto) {
        userRepository.getUserById(userDto.getUserId())
                .ifPresent(u-> {
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

    public void reloadComputer(Computer computer, UserDto databaseUser) {
        ComputerDto computerDto = computerMapper.map(computer);
        computerDto.setComputerId(computer.getId());
        computerDto.setComputerAdName(computer.getComputerAdName());
        computerDto.setComputerType(computer.getComputerType());
        computerDto.setDeviceManufacturer(computer.getDeviceManufacturer());
        computerDto.setDeviceType(computer.getDeviceType());
        computerDto.setPeripheralCollection(computer.getPeripheralCollection());
        computerDto.setPrinter(computer.getPrinter());
        computerDto.setSerialNumber(computer.getSerialNumber());
        computerDto.setSoftwareCollection(computer.getSoftwareCollection());
        computerDto.setUser(userMapper.reverse(databaseUser));
        databaseUser.setComputer(computerMapper.reverse(computerDto));
        userRepository.save(userMapper.reverse(databaseUser));
    }

    public void reloadDevice(MobileDevice mobileDevice, UserDto databaseUser) {
        MobileDeviceDto mobileDeviceDto = mobileDeviceMapper.map(mobileDevice);
        mobileDeviceDto.setMobileDeviceId(mobileDevice.getId());
        mobileDeviceDto.setDeviceManufacturer(mobileDevice.getDeviceManufacturer());
        mobileDeviceDto.setDeviceType(mobileDevice.getDeviceType());
        mobileDeviceDto.setImeiNumber(mobileDevice.getImeiNumber());
        mobileDeviceDto.setPhoneNumber(mobileDevice.getPhoneNumber());
        mobileDeviceDto.setSerialNumber(mobileDevice.getSerialNumber());
        mobileDeviceDto.setUser(userMapper.reverse(databaseUser));
        databaseUser.setMobileDevice(mobileDeviceMapper.reverse(mobileDeviceDto));
        userRepository.save(userMapper.reverse(databaseUser));
    }
}
