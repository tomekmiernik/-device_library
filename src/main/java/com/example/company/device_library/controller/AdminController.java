package com.example.company.device_library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @GetMapping()
    public String getAdminToolsPage(Model model) {
        model.addAttribute("title", "Narzędzia administracyjne");
        model.addAttribute("pageName", "Narzędzia administracyjne");
        return "admin/admin-tools";
    }
}
