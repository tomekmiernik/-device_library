package com.example.company.device_library.controller;

import com.example.company.device_library.service.DeviceTypeService;
import com.example.company.device_library.service.ManufacturerService;
import com.example.company.device_library.util.dtos.DeviceTypesDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class DeviceTypeController {
    private DeviceTypeService deviceTypeService;

    public DeviceTypeController(DeviceTypeService deviceTypeService) {
        this.deviceTypeService = deviceTypeService;
    }

    @GetMapping("/type")
    public String deviceTypePage(Model model) {
        model.addAttribute("formName", "Dodawanie modelu sprzętu");
        model.addAttribute("typeDto", new DeviceTypesDto());
        model.addAttribute("types", deviceTypeService.getAllDeviceTypes());
        return "admin/type/type";
    }

    @PostMapping("/type")
    public String addNewTypesItem(@Valid @ModelAttribute("typeDto") DeviceTypesDto typeDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/type/type";
        } else {
            deviceTypeService.addDeviceType(typeDto);
            return "redirect:/admin/type/";
        }
    }

    @GetMapping("/type/{typeId}/updateType")
    public String getPageForUpdateDeviceTypeItem(Model model, @PathVariable("typeId") Long typeId) {
        model.addAttribute("formName", "Edycja modelu urządzenia");
        model.addAttribute("deviceTypeDto", deviceTypeService.getDeviceTypeById(typeId));
        return "admin/type/update-type";
    }

    @PutMapping("/type")
    public String updateDeviceTypeItem(@ModelAttribute("typeDto") DeviceTypesDto typeDto) {
        deviceTypeService.updateDeviceType(typeDto);
        return "redirect:/admin/type";
    }

}
