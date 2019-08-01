package com.example.company.device_library.controller;

import com.example.company.device_library.model.DeviceType;
import com.example.company.device_library.service.DeviceTypeService;
import com.example.company.device_library.util.dtos.DeviceTypesDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class DeviceTypeController {
    private DeviceTypeService deviceTypeService;

    public DeviceTypeController (DeviceTypeService deviceTypeService){
        this.deviceTypeService = deviceTypeService;
    }

    @GetMapping("/type")
    public String deviceTypePage(Model model){
        model.addAttribute("typeDto", new DeviceTypesDto());
        model.addAttribute("types", deviceTypeService.getAllDeviceTypes());
        model.addAttribute("formName", "Dodawanie modelu sprzętu");
        return "admin/type/type";
    }

    @PostMapping("/type")
    public String addNewTypesItem(@ModelAttribute("typeDto") DeviceTypesDto typeDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "admin/type/type";
        }else {
            deviceTypeService.addDeviceType(typeDto);
            return "redirect:/admin/type/";
        }
    }
}
