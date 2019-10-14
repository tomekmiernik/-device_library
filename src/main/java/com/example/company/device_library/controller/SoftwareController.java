package com.example.company.device_library.controller;

import com.example.company.device_library.service.SoftwareService;
import com.example.company.device_library.util.dtos.SoftwareDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class SoftwareController {
    private SoftwareService softwareService;

    @Autowired
    public SoftwareController(SoftwareService softwareService) {
        this.softwareService = softwareService;
    }

    @GetMapping("/software")
    public String softwarePage(Model model) {
        model.addAttribute("formName", "Dodawanie oprogramowania");
        model.addAttribute("softwareDto", new SoftwareDto());
        getSoftwareForTableContent(model);
        return "admin/software/software";
    }

    @PostMapping("/software")
    public String addNewSoftwareItem(@ModelAttribute("softwareDto") @Valid SoftwareDto softwareDto,
                                     BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            getSoftwareForTableContent(model);
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
    public String updateSoftwareItem(@ModelAttribute("softwareDto") @Valid SoftwareDto softwareDto,
                                     BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/software/update-software";
        }else {
            softwareService.updateSoftware(softwareDto);
            return "redirect:/admin/software";
        }
    }

    private void getSoftwareForTableContent(Model model) {
        model.addAttribute("software", softwareService.getAllSoftwares());
    }
}
