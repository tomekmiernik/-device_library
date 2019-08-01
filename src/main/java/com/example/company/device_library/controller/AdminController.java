package com.example.company.device_library.controller;

import com.example.company.device_library.util.dtos.*;
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



    @GetMapping("/add-computer")
    public String getAddComputerFormPage(@ModelAttribute ComputerDto computerDto, Model model) {
        model.addAttribute("title", "Dodawanie komputera");
        model.addAttribute("formName", "dodawanie komputera");
        return "admin/add-computer";
    }

    @GetMapping("/add-consumable")
    public String getAddConsumableFormPage(@ModelAttribute ConsumableDto consumableDto, Model model) {
        model.addAttribute("title", "Dodawanie tuszu / toneru");
        model.addAttribute("formName", "dodawanie tuszu / toneru");
        return "admin/add-consumable";
    }

    @GetMapping("/add-department")
    public String getAddDepartmentFormPage(@ModelAttribute DepartmentDto departmentDto, Model model) {
        model.addAttribute("title", "Dodawanie działu");
        model.addAttribute("formName", "dodawanie działu");
        return "admin/add-department";
    }



    /*@GetMapping("/add-types")
    public String getAddTypesFormPage(@ModelAttribute DeviceTypesDto deviceTypesDto, Model model) {
        model.addAttribute("title", "Dodawanie modelu sprzętu");
        model.addAttribute("formName", "dodawanie modelu sprzętu");
        return "admin/add-type";
    }*/

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

    @GetMapping("/add-software")
    public String getAddSoftwareFormPage(@ModelAttribute SoftwareDto softwareDto, Model model) {
        model.addAttribute("title", "Dodawanie oprogramowania");
        model.addAttribute("formName", "dodawanie oprogramowania");
        return "admin/add-software";
    }

    @GetMapping("/add-telephone")
    public String getAddTelephoneFormPage(@ModelAttribute TelephoneDto telephoneDto, Model model){
        model.addAttribute("title", "Dodawanie telefonu");
        model.addAttribute("formName", "dodawanie telefonu");
        return "admin/add-telephone";
    }

}
