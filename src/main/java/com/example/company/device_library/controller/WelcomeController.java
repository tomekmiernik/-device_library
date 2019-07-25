package com.example.company.device_library.controller;

import com.example.company.device_library.service.UserService;
import com.example.company.device_library.util.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

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

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute UserDto userDto){
        return "register";
    }
}
