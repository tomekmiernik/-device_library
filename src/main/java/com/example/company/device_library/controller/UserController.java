package com.example.company.device_library.controller;

import com.example.company.device_library.service.ComputerService;
import com.example.company.device_library.service.DepartmentService;
import com.example.company.device_library.service.MobileDeviceService;
import com.example.company.device_library.service.UserService;
import com.example.company.device_library.util.dtos.ComputerDto;
import com.example.company.device_library.util.dtos.MobileDeviceDto;
import com.example.company.device_library.util.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/admin")
public class UserController {
    private final UserService userService;

    private final MobileDeviceService mobileDeviceService;
    private final ComputerService computerService;
    private final DepartmentService departmentService;

    @Autowired
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
        getUsersForTableContent(model);
        getDepartmentsForSelectContent(model);
        return "admin/user/user";

    }

    @PostMapping("/user")
    public String addNewUser(@ModelAttribute("userDto") @Valid UserDto userDto,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            getDepartmentsForSelectContent(model);
            getUsersForTableContent(model);
            return "admin/user/user";
        } else if (userService.addUser(userDto)) {
            return "redirect:/admin/user/";
        } else {
            getUsersForTableContent(model);
            getDepartmentsForSelectContent(model);
            model.addAttribute("info", "Istniej użytkownik o podanym adresie e-mail");
            return "admin/user/user";
        }
    }

    @GetMapping("/user/{userId}/updateUser")
    public String getPageForUserUpdate(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("formName", "Edytowanie danych osoby");
        getUserDtoByHisId(userId, model);
        getDepartmentsForSelectContent(model);
        return "admin/user/update-user";
    }

    @PutMapping("/user")
    public String updateUser(@ModelAttribute("userDto") @Valid UserDto userDto,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            getDepartmentsForSelectContent(model);
            return "admin/user/update-user";
        } else {
            userService.updateUser(userDto);
            return "redirect:/admin/user";
        }
    }

    @GetMapping("/user/{userId}/resetPassword")
    public String getFormToResetPassword(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("formName", "Resetowanie hasła");
        getUserDtoByHisId(userId, model);
        return "admin/user/reset-password";
    }

    @PutMapping("/user/resetPassword")
    public String resetPasswordUserItem(@RequestParam("password") String password,
                                        @RequestParam("confirmPassword") String confirmPassword, UserDto userDto, Model model) {
        if (userService.checkPasswordValue(password, confirmPassword)) {
            userService.resetPassword(userDto.getUserId(), password);
            return "redirect:/admin/user";
        } else {
            model.addAttribute("info", "Hasła nie są takie same");
            return "admin/user/reset-password";
        }
    }

    private void getDepartmentsForSelectContent(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
    }

    @GetMapping("/user/{userId}/addDevice")
    public String getPageForAddDeviceOfUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("formName", "Dodawanie urządzenia użytkownikowi");
        getUserDtoByHisId(userId, model);
        setCollectionFreeMobiles(model);
        return "admin/user/add-device";
    }

    @PutMapping("/user/addDevice/{userId}")
    public String addDevice(@PathVariable("userId") Long userId,
                            @RequestParam(value = "mobileDevice", required = false) Long mobileId,
                            Model model) {
        if (mobileId == null) {
            setCollectionFreeMobiles(model);
            getUserDtoByHisId(userId, model);
            setDisplayWarningInformation(model);
            return "admin/user/add-device";
        } else {
            userService.getUserAndAddMobileDeviceHim(mobileId, userService.getUserById(userId));
            return "redirect:/admin/user";
        }
    }

    @GetMapping("/user/mobiles")
    public String getMobilesPage(Model model) {
        Collection<MobileDeviceDto> allNotFreeMobiles = mobileDeviceService.getAllNotFreeMobiles();
        if (allNotFreeMobiles.isEmpty()) {
            model.addAttribute("userInfoWarning", "Lista nie zawiera żadnych elemenótw");
            return "admin/user/mobiles";
        }
        model.addAttribute("mobiles", allNotFreeMobiles);
        return "admin/user/mobiles";
    }

    @GetMapping("/user/{mobileId}/changeMobileToFree")
    public String changeMobileOnReadyForUse(@PathVariable("mobileId") Long mobileId) {
        mobileDeviceService.changeMobileOnReadyToUse(mobileId);
        return "redirect:/admin/user";
    }


    @GetMapping("/user/{userId}/addComputer")
    public String getPageForAddComputerOfUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("formName", "Dodawanie komputera użytkownikowi");
        getUserDtoByHisId(userId, model);
        setCollectionFreeComputers(model);
        return "admin/user/add-computer";
    }

    @PutMapping("/user/addComputer/{userId}")
    public String addComputer(@PathVariable("userId") Long userId,
                              @RequestParam(value = "computer", required = false) Long computerId,
                              Model model) {
        if (computerId == null) {
            setCollectionFreeComputers(model);
            getUserDtoByHisId(userId, model);
            setDisplayWarningInformation(model);
            return "admin/user/add-computer";
        } else {
            userService.getUserAndAddComputerHim(computerId, userService.getUserById(userId));
            return "redirect:/admin/user";
        }
    }

    @GetMapping("/user/comps")
    public String getCompsPage(Model model) {
        Collection<ComputerDto> allNotFreeComputers = computerService.getAllNotFreeComputers();
        if (allNotFreeComputers.isEmpty()) {
            model.addAttribute("userInfoWarning", "Lista nie zawiera żadnych elemenótw");
            return "admin/user/comps";
        }
        model.addAttribute("computers", allNotFreeComputers);
        return "admin/user/comps";
    }

    @GetMapping("/user/{computerId}/changeCompToFree")
    public String changeComputerOnReadyForUse(@PathVariable("computerId") Long computerId) {
        computerService.changeComputerOnReadyToUse(computerId);
        return "redirect:/admin/user";
    }

    private void getUsersForTableContent(Model model) {
        model.addAttribute("users", userService.getAllUsers());
    }

    private void setCollectionFreeMobiles(Model model) {
        if (mobileDeviceService.getAllFreeMobiles().isEmpty()) {
            model.addAttribute("userInfoWarning", "Brak wolnych urządzeń");
        }
        model.addAttribute("mobiles", mobileDeviceService.getAllFreeMobiles());
    }

    private void getUserDtoByHisId(Long userId, Model model) {
        model.addAttribute("userDto", userService.getUserById(userId));
    }

    private void setCollectionFreeComputers(Model model) {
        if (computerService.getAllFreeComputers().isEmpty()) {
            model.addAttribute("userInfoWarning", "Brak wolnych urządzeń");
        }
        model.addAttribute("computers", computerService.getAllFreeComputers());
    }

    private void setDisplayWarningInformation(Model model) {
        model.addAttribute("userInfoWarning", "Dokonaj wyboru");
    }
}
