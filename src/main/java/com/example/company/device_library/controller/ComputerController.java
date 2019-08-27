package com.example.company.device_library.controller;

import com.example.company.device_library.service.*;
import com.example.company.device_library.util.dtos.ComputerDto;
import com.example.company.device_library.util.dtos.DeviceTypesDto;
import com.example.company.device_library.util.dtos.ManufacturerDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/admin")
public class ComputerController {

    private ComputerService computerService;
    private DeviceTypeService deviceTypeService;
    private ManufacturerService manufacturerService;
    private UserService userService;
    private PrinterService printerService;

    public ComputerController(ComputerService computerService, DeviceTypeService deviceTypeService,
                              ManufacturerService manufacturerService, UserService userService,
                              PrinterService printerService) {
        this.computerService = computerService;
        this.deviceTypeService = deviceTypeService;
        this.manufacturerService = manufacturerService;
        this.userService = userService;
        this.printerService = printerService;
    }

    @GetMapping("/computer")
    public String computerPage(Model model) {
        model.addAttribute("buttonValue", "Dodaj");
        model.addAttribute("computerDto", new ComputerDto());
        model.addAttribute("computers", computerService.getAllComputers());
        model.addAttribute("produces", manufacturerService.getAllManufacturers());
        model.addAttribute("models", deviceTypeService.getAllDeviceTypes());
        model.addAttribute("printers", printerService.getAllPrinters());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("formName", "Dodawanie komputera");
        return "admin/computer/computer";
    }

    @PostMapping("/computer")
    public String addNewComputerItem(@ModelAttribute("computerDto") ComputerDto computerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/computer/computer";
        } else {
            computerService.addComputer(computerDto);
            return "redirect:/admin/computer";
        }
    }

    @GetMapping("/computer/{computerId}/updateComputer")
    public String getPageForUpdateComputerItem(Model model, @PathVariable("computerId") Long computerId) {
        model.addAttribute("buttonValue", "Edytuj");
        model.addAttribute("computerDto", computerService.getComputerById(computerId));
        model.addAttribute("produces", manufacturerService.getAllManufacturers());
        model.addAttribute("models", deviceTypeService.getAllDeviceTypes());
        model.addAttribute("printers", printerService.getAllPrinters());
        model.addAttribute("users", userService.getAllUsers());
        return "admin/computer/computer";
    }

    @PutMapping("/computer")
    public String updateComputerItem(@ModelAttribute("computerDto") ComputerDto computerDto) {
        computerService.updateComputer(computerDto);
        return "redirect:/admin/computer";
    }
}
