package com.example.company.device_library.controller;

import com.example.company.device_library.service.DeviceTypeService;
import com.example.company.device_library.service.ManufacturerService;
import com.example.company.device_library.util.dtos.DeviceTypesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class DeviceTypeController {
    private DeviceTypeService deviceTypeService;

    @Autowired
    public DeviceTypeController(DeviceTypeService deviceTypeService) {
        this.deviceTypeService = deviceTypeService;
    }

    @GetMapping("/type")
    public String deviceTypePage(Model model) {
        model.addAttribute("formName", "Dodawanie modelu urządzenia");
        model.addAttribute("deviceTypesDto", new DeviceTypesDto());
        getDeviceTypesForTableContent(model);
        return "admin/type/type";
    }

    @PostMapping("/type")
    public String addNewTypesItem(@Valid DeviceTypesDto deviceTypesDto,
                                  BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            getDeviceTypesForTableContent(model);
            return "admin/type/type";
        } else if(deviceTypeService.addDeviceType(deviceTypesDto)) {
            return "redirect:/admin/type/";
        }else {
            getDeviceTypesForTableContent(model);
            model.addAttribute("info", "Podana nazwa już istnieje");
            return "admin/type/type";
        }
    }

    @GetMapping("/type/{typeId}/updateType")
    public String getPageForUpdateDeviceTypeItem(Model model, @PathVariable("typeId") Long typeId) {
        model.addAttribute("formName", "Edycja nazwy modelu urządzenia");
        model.addAttribute("deviceTypesDto", deviceTypeService.getDeviceTypeById(typeId));
        return "admin/type/update-type";
    }

    @PutMapping("/type")
    public String updateDeviceTypeItem(@ModelAttribute("deviceTypesDto") @Valid DeviceTypesDto deviceTypesDto,
                                       BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "admin/type/update-type";
        }else {
            deviceTypeService.updateDeviceType(deviceTypesDto);
            return "redirect:/admin/type";
        }
    }

    private void getDeviceTypesForTableContent(Model model) {
        model.addAttribute("types", deviceTypeService.getAllDeviceTypes());
    }
}
