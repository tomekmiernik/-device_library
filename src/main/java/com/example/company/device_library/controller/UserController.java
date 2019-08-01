package com.example.company.device_library.controller;

import com.example.company.device_library.model.User;
import com.example.company.device_library.repository.UserRepository;
import com.example.company.device_library.service.UserService;
import com.example.company.device_library.util.dtos.UserDto;
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

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/add-user")
    public String getAddUserFormPage(@ModelAttribute UserDto userDto, Model model) {
        model.addAttribute("title", "Dodawanie osoby");
        model.addAttribute("formName", "dodawanie osoby");
        return "admin/add-user";
    }

    @PostMapping("/add-user")
    public String addNewUser(@Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/add-user";
        } else {
            User saveNewUser = this.userService.addUser(userDto);
            return "redirect:/user/" + saveNewUser.getId();
        }
    }

    @GetMapping("/user/{userId}")
    public String showUserDetails(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("user", userService.getUserById(userId));
        return "admin/add-user";
    }
}
