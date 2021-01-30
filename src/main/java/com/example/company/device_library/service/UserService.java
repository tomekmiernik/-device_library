package com.example.company.device_library.service;

import com.example.company.device_library.model.*;
import com.example.company.device_library.repository.ComputerRepository;
import com.example.company.device_library.repository.MobileDeviceRepository;
import com.example.company.device_library.repository.RoleRepository;
import com.example.company.device_library.repository.UserRepository;
import com.example.company.device_library.util.dtos.UserDto;
import com.example.company.device_library.util.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    private ComputerRepository computerRepository;
    private MobileDeviceRepository mobileDeviceRepository;
    private RoleRepository roleRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository,
                       ComputerRepository computerRepository,
                       MobileDeviceRepository mobileDeviceRepository,
                       RoleRepository roleRepository,
                       UserMapper userMapper,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.computerRepository = computerRepository;
        this.mobileDeviceRepository = mobileDeviceRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public Collection<UserDto> getUsersByLastName(String userLastName) {
        List<UserDto> collectUsers = userRepository.findUsersByLastName(userLastName)
                .stream()
                .map(userMapper::map)
                .collect(Collectors.toList());
        setDefaultCharsWhenUserHasNotMobileDevice(collectUsers);
        return collectUsers;
    }

    public boolean addUser(UserDto userDto) {
        boolean checked = checkDataBeforeSave(userDto);
        if (checked) {
            try {
                userDto.setRoles(setDefaultUserRole());
                userRepository.save(userMapper.reverse(userDto));
            } catch (DataIntegrityViolationException dive) {
                checked = false;
            }
        }
        return checked;
    }

    public UserDto getUserByUsernameAsEmail(String email) {
        return userRepository.findUserByUsernameAsEmail(email)
                .map(userMapper::map)
                .get();
    }

    public Collection<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .filter(UserApp::isActive)
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

    public void getUserAndAddMobileDeviceHim(Long mobileId, UserDto databaseUser) {
        MobileDevice databaseDevice = mobileDeviceRepository.getOne(mobileId);
        databaseDevice.setIsUse(Boolean.TRUE);
        databaseUser.setMobileDevice(databaseDevice);
        userRepository.save(userMapper.reverse(databaseUser));
    }

    public void getUserAndAddComputerHim(Long computerId, UserDto databaseUser) {
        Computer databaseComputer = computerRepository.getOne(computerId);
        databaseComputer.setIsUse(Boolean.TRUE);
        databaseUser.setComputer(databaseComputer);
        userRepository.save(userMapper.reverse(databaseUser));
    }

    public void getUsersFilerByPhoneNumber(Collection<UserDto> users) {
        setDefaultCharsWhenUserHasNotMobileDevice(users);
    }


    public boolean checkPasswordValue(String pass, String confPass) {
        return pass.equals(confPass);
    }

    public void resetPassword(Long userId, String pass) {
        userRepository.getUserById(userId)
                .ifPresent(u -> {
                    u.setPassword(passwordEncoder.encode(pass));
                    userRepository.save(u);
                });
    }

    private Collection<Role> setDefaultUserRole() {
        Collection<Role> roles = new ArrayList<>();
        Role defaultRoleUser = roleRepository.findByRoleName("USER");
        roles.add(defaultRoleUser);
        return roles;
    }

    private boolean checkDataBeforeSave(UserDto userDto) {
        return userRepository.findAll()
                .stream()
                .noneMatch(u -> u.getEmail()
                        .matches(userDto.getEmail()));
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
