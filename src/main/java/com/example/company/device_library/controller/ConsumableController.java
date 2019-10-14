package com.example.company.device_library.controller;

import com.example.company.device_library.service.ConsumableService;
import com.example.company.device_library.service.PrinterService;
import com.example.company.device_library.util.dtos.ConsumableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class ConsumableController {
    private ConsumableService consumableService;

    @Autowired
    public ConsumableController(ConsumableService consumableService) {
        this.consumableService = consumableService;
    }

    @GetMapping("/consumable")
    public String consumablePage(Model model) {
        model.addAttribute("formName", "Dodawanie tuszu / toneru");
        model.addAttribute("consumableDto", new ConsumableDto());
        getConsumablesForTableContent(model);
        return "admin/consumable/consumable";
    }

    @PostMapping("/consumable")
    public String addNewConsumableItem(@ModelAttribute("consumableDto") @Valid ConsumableDto consumableDto,
                                       BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            getConsumablesForTableContent(model);
            return "admin/consumable/consumable";
        } else if(consumableService.addConsumable(consumableDto)){
            return "redirect:/admin/consumable";
        }else {
            getConsumablesForTableContent(model);
            model.addAttribute("info", "Istnieje pozycja o identycznym oznaczeniu");
            return "admin/consumable/consumable";
        }
    }

    @GetMapping("/consumable/{consumableId}/updateConsumable")
    public String getPageForUpdateConsumableItem(Model model, @PathVariable("consumableId") Long consumableId) {
        model.addAttribute("formName", "Edycja uzupełnienia drukarki");
        model.addAttribute("consumableDto", consumableService.getConsumableById(consumableId));
        return "admin/consumable/update-consumable";
    }

    @PutMapping("/consumable")
    public String updateConsumableItem(@ModelAttribute("consumableDto") @Valid ConsumableDto consumableDto,
                                       BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "admin/consumable/update-consumable";
        }
        consumableService.updateConsumable(consumableDto);
        return "redirect:/admin/consumable";
    }

    private void getConsumablesForTableContent(Model model) {
        model.addAttribute("consumables", consumableService.getAllConsumables());
    }
}
