package com.example.company.device_library.controller;

import com.example.company.device_library.model.Computer;
import com.example.company.device_library.model.MobileDevice;
import com.example.company.device_library.service.ComputerService;
import com.example.company.device_library.service.DepartmentService;
import com.example.company.device_library.service.MobileDeviceService;
import com.example.company.device_library.service.UserService;
import com.example.company.device_library.util.dtos.UserDto;
import com.example.company.device_library.util.mappers.ComputerMapper;
import com.example.company.device_library.util.mappers.MobileDeviceMapper;
import com.example.company.device_library.util.mappers.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class UserController {

    private final UserService userService;
    private final MobileDeviceService mobileDeviceService;
    private final ComputerService computerService;
    private final DepartmentService departmentService;

    public UserController(UserService userService,
                          MobileDeviceService mobileDeviceService,
                          ComputerService computerService,
                          DepartmentService departmentService) {
        this.userService = userService;
        this.mobileDeviceService = mobileDeviceService;
        this.computerService = computerService;
        this.departmentService = departmentService;
    }

    @GetMapping("/user")
    public String userPage(Model model) {
        model.addAttribute("formName", "Dodawanie osoby");
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("computers", computerService.getAllComputers());
        model.addAttribute("telephones", mobileDeviceService.getAllMobileDevices());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "admin/user/user";
    }

    @PostMapping("/user")
    public String addNewUser(@Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/user/user";
        } else {
            userService.addUser(userDto);
            return "redirect:/admin/user/";
        }
    }

    @GetMapping("/user/{userId}/updateUser")
    public String getPageForUserUpdate(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("userDto", userService.getUserById(userId));
        return "admin/user/update-user";
    }

    @PutMapping("/user")
    public String updateUser(@ModelAttribute("userDto") UserDto userDto) {
        userService.updateUser(userDto);
        return "redirect:/admin/user";
    }

    @GetMapping("/user/{userId}/addDevice")
    public String getPageForAddDeviceOfUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("formName", "Dodawanie urządzenia użytkownikowi");
        model.addAttribute("userDto", userService.getUserById(userId));
        model.addAttribute("mobileDevices", mobileDeviceService.getAllMobileDevices());
        return "admin/user/add-device";
    }

    @PostMapping("/user/addDevice/{userId}")
    public String addDevice(@PathVariable("userId") Long userId, MobileDevice mobileDevice) {
        userService.reloadDevice(mobileDevice, userService.getUserById(userId));
        return "redirect:/admin/user";
    }

    @GetMapping("/user/{userId}/addComputer")
    public String getPageForAddComputerOfUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("formName", "Dodawanie komputera użytkownikowi");
        model.addAttribute("userDto", userService.getUserById(userId));
        model.addAttribute("computers", computerService.getAllComputers());
        return "admin/user/add-computer";
    }

    @PostMapping("/user/addComputer/{userId}")
    public String addComputer(@PathVariable("userId") Long userId, Computer computer) {
        userService.reloadComputer(computer, userService.getUserById(userId));
        return "redirect:/admin/user";
    }
}
