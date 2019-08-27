package com.example.company.device_library.controller;

import com.example.company.device_library.service.DeviceTypeService;
import com.example.company.device_library.service.ManufacturerService;
import com.example.company.device_library.service.PeripheralService;
import com.example.company.device_library.util.dtos.PeripheralDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class PeripheralController {
    private PeripheralService peripheralService;
    private ManufacturerService manufacturerService;
    private DeviceTypeService deviceTypeService;

    public PeripheralController(PeripheralService peripheralService,
                                ManufacturerService manufacturerService,
                                DeviceTypeService deviceTypeService) {
        this.peripheralService = peripheralService;
        this.manufacturerService = manufacturerService;
        this.deviceTypeService = deviceTypeService;
    }

    @GetMapping("/peripheral")
    public String peripheralPage(Model model) {
        model.addAttribute("formName", "Dodawanie myszy / klawiatury");
        model.addAttribute("peripheralDto", new PeripheralDto());
        model.addAttribute("peripherals", peripheralService.getAllPeripherals());
        model.addAttribute("produces", manufacturerService.getAllManufacturers());
        model.addAttribute("models", deviceTypeService.getAllDeviceTypes());
        return "admin/peripheral/peripheral";
    }

    @PostMapping("/peripheral")
    public String addNewPeripheralItem(@ModelAttribute("peripheralDto") PeripheralDto peripheralDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/peripheral/peripheral";
        } else {
            peripheralService.addPeripheral(peripheralDto);
            return "redirect:/admin/peripheral";
        }
    }

    @GetMapping("/peripheral/{peripheralId}/updatePeripheral")
    public String getPageForUpdatePeripheralItem(Model model, @PathVariable("peripheralId") Long peripheralId) {
        model.addAttribute("peripheralDto", peripheralService.getPeripheralById(peripheralId));
        return "admin/peripheral/update-peripheral";
    }

    @PutMapping("/peripheral")
    public String updatePeripheralItem(@ModelAttribute("peripheralDto") PeripheralDto peripheralDto) {
        peripheralService.updatePeripheral(peripheralDto);
        return "redirect:/admin/peripheral";
    }
}
