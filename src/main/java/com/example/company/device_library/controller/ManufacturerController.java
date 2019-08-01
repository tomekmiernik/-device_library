package com.example.company.device_library.controller;

import com.example.company.device_library.service.ManufacturerService;
import com.example.company.device_library.util.dtos.ManufacturerDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("/manufacturer")
    public String manufacturePage(Model model) {
        model.addAttribute("manufacturerDto", new ManufacturerDto());
        model.addAttribute("manufacturers", manufacturerService.getAllManufacturers());
        model.addAttribute("formName", "Dodawanie producenta sprzętu");
        return "admin/manufacturer/manufacturer";
    }

    @PostMapping("/manufacturer")
    public String addNewManufacturerItem(@ModelAttribute("manufacturerDto") ManufacturerDto manufacturerDto,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/manufacturer/manufacturer";
        } else {
            manufacturerService.addDeviceManufacturer(manufacturerDto);
            return "redirect:/admin/manufacturer/";
        }
    }
}
