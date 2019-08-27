package com.example.company.device_library.controller;

import com.example.company.device_library.service.*;
import com.example.company.device_library.util.dtos.KitDto;
import com.example.company.device_library.util.dtos.UserDto;
import com.example.company.device_library.util.mappers.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class KitController {
    private KitService kitService;
    private UserService userService;
    private MobileDeviceService mobileDeviceService;
    private ComputerService computerService;
    private MonitorService monitorService;
    private PrinterService printerService;
    private UserMapper userMapper;

    public KitController(KitService kitService, UserService userService,
                         MobileDeviceService mobileDeviceService, ComputerService computerService,
                         MonitorService monitorService, PrinterService printerService,
                         UserMapper userMapper) {
        this.kitService = kitService;
        this.userService = userService;
        this.mobileDeviceService = mobileDeviceService;
        this.computerService = computerService;
        this.monitorService = monitorService;
        this.printerService = printerService;
        this.userMapper = userMapper;
    }

    @GetMapping("/kit")
    public String kitPage(Model model, UserDto userDto, KitDto kitDto) {
        model.addAttribute("kits", kitService.getAllKits());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("userDto", userDto);
        model.addAttribute("kitDto", kitDto);
        addDefaultModels(model);
        return "admin/kit/kit";
    }

    private void addDefaultModels(Model model) {
        model.addAttribute("phones", mobileDeviceService.getAllMobileDevices());
        model.addAttribute("comps", computerService.getAllComputers());
        model.addAttribute("monitors", monitorService.getAllMonitors());
        model.addAttribute("prints", printerService.getAllPrinters());
        model.addAttribute("formName", "Tworzenie zestawu komputerowego");
    }

    @GetMapping("kit/{kitId}/updateKit")
    public String getPageForUpdateKitItem(Model model, @PathVariable("kitId") Long kitId) {
        model.addAttribute("kitDto", kitService.getKitById(kitId));
        return "admin/kit/update-kit";
    }

    @PutMapping("/kit")
    public String updateKitItem(@ModelAttribute("kitDto") KitDto kitDto) {
        kitService.updateKit(kitDto);
        return "redirect:/admin/kit";
    }

    @GetMapping("/kit/{userId}")
    public String getPageForMakeKitOfUser(@PathVariable("userId") Long userId,
                                          Model model) {
        addDefaultModels(model);
        UserDto userDto = userService.getUserById(userId);
        model.addAttribute("userDto", userDto);
        model.addAttribute("kitDto", new KitDto());
        model.addAttribute("firstName", userDto.getFirstName());
        model.addAttribute("lastName", userDto.getLastName());
        return "admin/kit/kit";
    }

    @PostMapping("/kit/{userId}")
    public String addNewKitItem(@PathVariable("userId") Long userId,
                                @ModelAttribute("kitDto") KitDto kitDto,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/admin/kit/kit";
        } else {
            UserDto userDto = userService.getUserById(userId);
           /* kitDto.setUserId(userMapper.reverse(userDto));*/
            model.addAttribute("userDto", userDto);
            kitService.addKit(kitDto);
            return "redirect:/admin/kit";
        }
    }
}
