package com.example.company.device_library.controller;

import com.example.company.device_library.service.SimCardService;
import com.example.company.device_library.util.dtos.SimCardDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class SimCardController {
    private SimCardService simCardService;

    public SimCardController(SimCardService simCardService) {
        this.simCardService = simCardService;
    }

    @GetMapping("/sim")
    public String getSimPage(Model model) {
        model.addAttribute("formName", "Dodawanie karty SIM");
        model.addAttribute("simCardDto", new SimCardDto());
        model.addAttribute("sims", simCardService.getAllCardSims());
        return "admin/sim/sim";
    }

    @PostMapping("/sim")
    public String addNewSimCardItem(@ModelAttribute("simCardDto") SimCardDto simCardDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/sim/sim";
        } else{
            simCardService.addSimCard(simCardDto);
            return "redirect:/admin/sim";
        }
    }

    @GetMapping("/sim/{simCardId}/updateSim")
    public String getPageForUpdateSimCardItem(Model model, @PathVariable("simCardId") Long simCardId){
        model.addAttribute("formName", "Edytowanie informacji o karcie SIM");
        model.addAttribute("simCardDto", simCardService.getSimCardById(simCardId));
        return "admin/sim/update-sim";
    }

    @PutMapping("/sim")
    public String updateSimCardItem(@ModelAttribute("simCardDto") SimCardDto simCardDto){
        simCardService.updateSimCard(simCardDto);
        return "redirect:/admin/sim";
    }
}
