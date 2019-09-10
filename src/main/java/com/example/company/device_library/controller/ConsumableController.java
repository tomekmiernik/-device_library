package com.example.company.device_library.controller;

import com.example.company.device_library.service.ConsumableService;
import com.example.company.device_library.service.PrinterService;
import com.example.company.device_library.util.dtos.ConsumableDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class ConsumableController {
    private ConsumableService consumableService;
    private PrinterService printerService;

    public ConsumableController(ConsumableService consumableService,
                                PrinterService printerService) {
        this.consumableService = consumableService;
        this.printerService = printerService;
    }

    @GetMapping("/consumable")
    public String consumablePage(Model model) {
        model.addAttribute("formName", "Dodawanie tuszu / toneru");
        model.addAttribute("consumableDto", new ConsumableDto());
        model.addAttribute("consumables", consumableService.getAllConsumables());
        model.addAttribute("printers", printerService.getAllPrinters());
        return "admin/consumable/consumable";
    }

    @PostMapping("/consumable")
    public String addNewConsumableItem(@ModelAttribute("consumableDto") ConsumableDto consumableDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/consumable/consumable";
        } else {
            consumableService.addConsumable(consumableDto);
            return "redirect:/admin/consumable";
        }
    }

    @GetMapping("/consumable/{consumableId}/updateConsumable")
    public String getPageForUpdateConsumableItem(Model model, @PathVariable("consumableId") Long consumableId) {
        model.addAttribute("consumableDto", consumableService.getConsumableById(consumableId));
        return "admin/consumable/update-consumable";
    }

    @PutMapping("/consumable")
    public String updateConsumableItem(@ModelAttribute("consumableDto") ConsumableDto consumableDto) {
        consumableService.updateConsumable(consumableDto);
        return "redirect:/admin/consumable";
    }
}
