package com.example.company.device_library.controller;

import com.example.company.device_library.model.Consumable;
import com.example.company.device_library.service.ConsumableService;
import com.example.company.device_library.service.DeviceTypeService;
import com.example.company.device_library.service.ManufacturerService;
import com.example.company.device_library.service.PrinterService;
import com.example.company.device_library.util.dtos.PrinterDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class PrinterController {
    private PrinterService printerService;
    private ManufacturerService manufacturerService;
    private DeviceTypeService deviceTypeService;
    private ConsumableService consumableService;

    public PrinterController(PrinterService printerService,
                             ManufacturerService manufacturerService,
                             DeviceTypeService deviceTypeService,
                             ConsumableService consumableService) {
        this.printerService = printerService;
        this.manufacturerService = manufacturerService;
        this.deviceTypeService = deviceTypeService;
        this.consumableService = consumableService;
    }

    @GetMapping("/printer")
    public String printerPage(Model model) {
        model.addAttribute("formName", "Dodawanie drukarki");
        model.addAttribute("produces", manufacturerService.getAllManufacturers());
        model.addAttribute("models", deviceTypeService.getAllDeviceTypes());
        model.addAttribute("printerDto", new PrinterDto());
        model.addAttribute("printers", printerService.getAllPrinters());
        return "admin/printer/printer";
    }

    @PostMapping("/printer")
    public String addNewPrinterItem(@ModelAttribute("printerDto") PrinterDto printerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/printer/printer";
        } else {
            printerService.addPrinter(printerDto);
            return "redirect:/admin/printer";
        }
    }

    @GetMapping("/printer/{printerId}/updatePrinter")
    public String getPageForUpdatePrinterItem(Model model, @PathVariable("printerId") Long printerId) {
        model.addAttribute("printerDto", printerService.getPrinterById(printerId));
        return "admin/printer/update-printer";
    }

    @PutMapping("/printer")
    public String updatePrinterItem(@ModelAttribute("printerDto") PrinterDto printerDto) {
        printerService.updatePrinter(printerDto);
        return "redirect:/admin/printer";
    }

    @GetMapping("/printer/{printerId}/addConsumable")
    public String getPageForAddConsumableOfPrinter(@PathVariable("printerId") Long printerId, Model model) {
        model.addAttribute("formName", "Dodawanie materiałów eksploatacyjnych");
        model.addAttribute("printerDto", printerService.getPrinterById(printerId));
        model.addAttribute("consumables", consumableService.getAllConsumables());
        return "admin/printer/add-consumable";
    }

    @PostMapping("/printer/addConsumable/{printerId}")
    public String addConsumable(@PathVariable("printerId") Long printerId, Consumable consumable) {
        printerService.getPrinterAndAddConsumableHer(consumable, printerService.getPrinterById(printerId));
        return "redirect:/admin/printer";
    }
}
