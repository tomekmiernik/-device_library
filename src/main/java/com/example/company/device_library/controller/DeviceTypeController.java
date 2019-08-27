package com.example.company.device_library.controller;

import com.example.company.device_library.model.DeviceType;
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
    private ManufacturerService manufacturerService;

    public DeviceTypeController (DeviceTypeService deviceTypeService,
                                 ManufacturerService manufacturerService){
        this.deviceTypeService = deviceTypeService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("/type")
    public String deviceTypePage(Model model){
        model.addAttribute("typeDto", new DeviceTypesDto());
        model.addAttribute("produces", manufacturerService.getAllManufacturers());
        model.addAttribute("types", deviceTypeService.getAllDeviceTypes());
        model.addAttribute("formName", "Dodawanie modelu sprzętu");
        return "admin/type/type";
    }

    @PostMapping("/type")
    public String addNewTypesItem(@Valid @ModelAttribute("typeDto") DeviceTypesDto typeDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "admin/type/type";
        }else {
            deviceTypeService.addDeviceType(typeDto);
            return "redirect:/admin/type/";
        }
    }

    @GetMapping("/type/{typeId}/updateTypes")
    public String getPageForUpdateDeviceTypeItem(Model model, @PathVariable("typeId") Long typeId){
        model.addAttribute("deviceTypeDto", deviceTypeService.getDeviceTypeById(typeId));
        return "admin/type/update-types";
    }

    @PutMapping("/type")
    public String updateDeviceTypeItem(@ModelAttribute("typeDto") DeviceTypesDto typeDto){
        deviceTypeService.updateDeviceType(typeDto);
        return "redirect:/admin/type";
    }

}
