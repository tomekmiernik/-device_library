package com.example.company.device_library.controller;

import com.example.company.device_library.service.SoftwareService;
import com.example.company.device_library.util.dtos.SoftwareDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class SoftwareController {
    private SoftwareService softwareService;

    public SoftwareController(SoftwareService softwareService) {
        this.softwareService = softwareService;
    }

    @GetMapping("/software")
    public String softwarePage(Model model) {
        model.addAttribute("formName", "Dodawanie oprogramowania");
        model.addAttribute("softwareDto", new SoftwareDto());
        model.addAttribute("software", softwareService.getAllSoftwares());
        return "admin/software/software";
    }

    @PostMapping("/software")
    public String addNewSoftwareItem(@ModelAttribute("softwareDto") SoftwareDto softwareDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/software/software";
        } else {
            softwareService.addSoftware(softwareDto);
            return "redirect:/admin/software";
        }
    }

    @GetMapping("/software/{softwareId}/updateSoftware")
    public String getPageForUpdateSoftwareItem(Model model, @PathVariable("softwareId") Long softwareId) {
        model.addAttribute("softwareDto", softwareService.getSoftwareById(softwareId));
        return "admin/software/update-software";
    }

    @PutMapping("/software")
    public String updateSoftwareItem(@ModelAttribute("softwareDto") SoftwareDto softwareDto) {
        softwareService.updateSoftware(softwareDto);
        return "redirect:/admin/software";
    }
}
