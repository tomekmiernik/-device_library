package com.example.company.device_library.controller;

import com.example.company.device_library.service.ManufacturerService;
import com.example.company.device_library.util.dtos.ManufacturerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("/manufacturer")
    public String manufacturePage(Model model) {
        model.addAttribute("formName", "Dodawanie producenta sprzętu");
        model.addAttribute("manufacturerDto", new ManufacturerDto());
        getManufacturersForTableContent(model);
        return "admin/manufacturer/manufacturer";
    }

    @PostMapping("/manufacturer")
    public String addNewManufacturerItem(@Valid ManufacturerDto manufacturerDto,
                                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            getManufacturersForTableContent(model);
            return "admin/manufacturer/manufacturer";
        } else if(manufacturerService.addDeviceManufacturer(manufacturerDto)){
            return "redirect:/admin/manufacturer";
        }else {
            getManufacturersForTableContent(model);
            model.addAttribute("info", "Podana nazwa już istnieje");
            return "admin/manufacturer/manufacturer";
        }
    }

    @GetMapping("/manufacturer/{typeId}/updateManufacturer")
    public String getPageForUpdateManufacturerItem(Model model, @PathVariable("typeId") Long typeId) {
        model.addAttribute("formName", "Edycja nazwy producenta urządzenia");
        model.addAttribute("manufacturerDto", manufacturerService.getDeviceManufacturerById(typeId));
        return "admin/manufacturer/update-manufacturer";
    }

    @PutMapping("/manufacturer")
    public String updateManufacturerItem(@ModelAttribute("manufacturerDto") @Valid ManufacturerDto manufacturerDto,
                                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "admin/manufacturer/update-manufacturer";
        }else {
            manufacturerService.updateManufacturer(manufacturerDto);
            return "redirect:/admin/manufacturer";
        }
    }

    private void getManufacturersForTableContent(Model model) {
        model.addAttribute("manufacturers", manufacturerService.getAllManufacturers());
    }
}
