package com.example.company.device_library.controller;

import com.example.company.device_library.model.SimCard;
import com.example.company.device_library.service.*;
import com.example.company.device_library.util.dtos.MobileDeviceDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class MobileDeviceController {

    private MobileDeviceService mobileDeviceService;
    private ManufacturerService manufacturerService;
    private DeviceTypeService deviceTypeService;
    private UserService userService;
    private SimCardService simCardService;

    public MobileDeviceController(MobileDeviceService mobileDeviceService, ManufacturerService manufacturerService,
                                  DeviceTypeService deviceTypeService, UserService userService,
                                  SimCardService simCardService) {
        this.mobileDeviceService = mobileDeviceService;
        this.manufacturerService = manufacturerService;
        this.deviceTypeService = deviceTypeService;
        this.userService = userService;
        this.simCardService = simCardService;
    }

    @GetMapping("/telephone")
    public String telephonePage(Model model) {
        model.addAttribute("formName", "Dodawanie telefonu");
        model.addAttribute("produces", manufacturerService.getAllManufacturers());
        model.addAttribute("models", deviceTypeService.getAllDeviceTypes());
        model.addAttribute("telephones", mobileDeviceService.getAllMobileDevices());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("telephoneDto", new MobileDeviceDto());
        return "admin/telephone/telephone";
    }

    @PostMapping("/telephone")
    public String addNewTelephoneItem(@ModelAttribute("mobileDeviceDto") MobileDeviceDto mobileDeviceDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/telephone/telephone";
        } else {
            mobileDeviceService.addMobileDevice(mobileDeviceDto);
            return "redirect:/admin/telephone";
        }
    }

    @GetMapping("/telephone/{telephoneId}/updateTelephone")
    public String getPageForUpdateTelephoneItem(Model model, @PathVariable("telephoneId") Long telephoneId) {
        model.addAttribute("mobileDeviceDto", mobileDeviceService.getMobileDeviceById(telephoneId));
        model.addAttribute("produces", manufacturerService.getAllManufacturers());
        model.addAttribute("models", deviceTypeService.getAllDeviceTypes());
        return "admin/telephone/update-telephone";
    }

    @PutMapping("/telephone")
    public String updateTelephoneItem(@ModelAttribute("mobileDeviceDto") MobileDeviceDto mobileDeviceDto) {
        mobileDeviceService.updateMobileDevice(mobileDeviceDto);
        return "redirect:/admin/telephone";
    }

    @GetMapping("/telephone/{telephoneId}/addSimCard")
    public String getPageForAddSimCardOfTelephone(@PathVariable("telephoneId") Long telephoneId, Model model){
        model.addAttribute("formName", "Przypisywanie karty SIM");
        model.addAttribute("telephoneDto", mobileDeviceService.getMobileDeviceById(telephoneId));
        model.addAttribute("sims", simCardService.getAllCardSims());
        return "admin/telephone/add-sim";
    }

    @PostMapping("/telephone/addSimCard/{telephoneId}")
    public String addSimCard(@PathVariable("telephoneId") Long telephoneId, SimCard simCard){
        mobileDeviceService.getComputerAndAddSimCardHim(simCard, mobileDeviceService.getMobileDeviceById(telephoneId));
        return "redirect:/admin/telephone";
    }
}
