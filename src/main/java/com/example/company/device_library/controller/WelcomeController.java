package com.example.company.device_library.controller;

import com.example.company.device_library.service.UserService;
import com.example.company.device_library.util.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WelcomeController {

    private final UserService userService;

    @Autowired
    public WelcomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getMainPage(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "welcome";
    }

    @GetMapping("/details/{userId}")
    public String getPageForDetailsDataOfUser(@PathVariable("userId") Long userId, Model model){
        UserDto databaseUser = userService.getUserById(userId);
        if(databaseUser != null){
            model.addAttribute("userInfo", "Szczegółowe informacje o użytkowniku "
                    +databaseUser.getFirstName()+" "+databaseUser.getLastName()+" pełniącym stanowisko "
                    + databaseUser.getPosition()+" pracującym w dziale "+ databaseUser.getDepartment());
        }
        if((databaseUser != null ? databaseUser.getComputer() : null) != null ){
            model.addAttribute("computerManufacturer", databaseUser.getComputer().getDeviceManufacturer());
            model.addAttribute("computerType", databaseUser.getComputer().getDeviceType());
            model.addAttribute("computerAdName", databaseUser.getComputer().getComputerAdName());
            model.addAttribute("computerType", databaseUser.getComputer().getComputerType());
            model.addAttribute("computerSerialNumber", databaseUser.getComputer().getSerialNumber());
        }
        if((databaseUser != null ? databaseUser.getComputer() != null ? databaseUser.getComputer().getMonitor() : null :null) != null){
            model.addAttribute("monitorManufacturer", databaseUser.getComputer().getMonitor().getDeviceManufacturer());
            model.addAttribute("monitorType", databaseUser.getComputer().getMonitor().getDeviceType());
            model.addAttribute("monitorSerialNumber", databaseUser.getComputer().getMonitor().getSerialNumber());
        }
        if ((databaseUser != null ? databaseUser.getComputer() != null ? databaseUser.getComputer().getPrinter() : null :null) !=null){
            model.addAttribute("printerManufacturer", databaseUser.getComputer().getPrinter().getDeviceManufacturer());
            model.addAttribute("printerDeviceType",databaseUser.getComputer().getPrinter().getDeviceType());
            model.addAttribute("printerSerialNumber", databaseUser.getComputer().getPrinter().getSerialNumber());
        }
        if((databaseUser != null ? databaseUser.getMobileDevice() : null) != null){
            model.addAttribute("deviceManufacturer", databaseUser.getMobileDevice().getDeviceManufacturer());
            model.addAttribute("deviceTypes", databaseUser.getMobileDevice().getDeviceType());
            model.addAttribute("devicePhoneNumber", databaseUser.getMobileDevice().getPhoneNumber());
            model.addAttribute("deviceImeiNumber", databaseUser.getMobileDevice().getImeiNumber());
            model.addAttribute("deviceSerialNumber", databaseUser.getMobileDevice().getSerialNumber());
        }
        return "/details";

    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute UserDto userDto){
        return "register";
    }
}
