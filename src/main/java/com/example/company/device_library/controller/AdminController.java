package com.example.company.device_library.controller;

import com.example.company.device_library.util.dtos.PeripheralDto;
import com.example.company.device_library.util.dtos.PrinterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    public AdminController() {
    }

    @GetMapping()
    public String getAdminToolsPage(Model model) {
        model.addAttribute("title", "Narzędzia administracyjne");
        model.addAttribute("pageName", "Narzędzia administracyjne");
        return "admin/admin-tools";
    }


    @GetMapping("/add-peripheral")
    public String getAddPeripheralFormPage(@ModelAttribute PeripheralDto peripheralDto, Model model) {
        model.addAttribute("title", "Dodawanie myszy / klawiatury");
        model.addAttribute("formName", "dodawanie myszy / klawiatury");
        return "admin/add-peripheral";
    }

    @GetMapping("/add-printer")
    public String getAddPrinterFormPage(@ModelAttribute PrinterDto printerDto, Model model) {
        model.addAttribute("title", "Dodawanie drukarki");
        model.addAttribute("formName", "dodawanie drukarki");
        return "admin/add-printer";
    }


}
