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
    private SimCardService simCardService;

    public MobileDeviceController(MobileDeviceService mobileDeviceService,
                                  ManufacturerService manufacturerService,
                                  DeviceTypeService deviceTypeService,
                                  SimCardService simCardService) {
        this.mobileDeviceService = mobileDeviceService;
        this.manufacturerService = manufacturerService;
        this.deviceTypeService = deviceTypeService;
        this.simCardService = simCardService;
    }

    @GetMapping("/mobile")
    public String mobileDevicePage(Model model) {
        model.addAttribute("formName", "Dodawanie urządzenia mobilnego");
        model.addAttribute("mobileDeviceDto", new MobileDeviceDto());
        setCollectionsManufacturersAndDeviceTypes(model);
        model.addAttribute("mobiles", mobileDeviceService.getAllMobileDevices());
        return "admin/mobile/mobile";
    }

    @PostMapping("/mobile")
    public String addNewMobileDeviceItem(@ModelAttribute("mobileDeviceDto") MobileDeviceDto mobileDeviceDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/mobile/mobile";
        } else {
            mobileDeviceService.addMobileDevice(mobileDeviceDto);
            return "redirect:/admin/mobile";
        }
    }

    @GetMapping("/mobile/{mobileDeviceId}/updateMobileDevice")
    public String getPageForUpdateTelephoneItem(Model model, @PathVariable("mobileDeviceId") Long mobileDeviceId) {
        model.addAttribute("formName", "Edycja informacji o urządzeniu mobilnym");
        model.addAttribute("mobileDeviceDto", mobileDeviceService.getMobileDeviceById(mobileDeviceId));
        setCollectionsManufacturersAndDeviceTypes(model);
        return "admin/mobile/update-mobile";
    }

    @PutMapping("/mobile")
    public String updateMobileDeviceItem(@ModelAttribute("mobileDeviceDto") MobileDeviceDto mobileDeviceDto) {
        mobileDeviceService.updateMobileDevice(mobileDeviceDto);
        return "redirect:/admin/mobile";
    }

    @GetMapping("/mobile/{mobileDeviceId}/addSimCard")
    public String getPageForAddSimCardOfMobileDevice(@PathVariable("mobileDeviceId") Long mobileDeviceId, Model model){
        model.addAttribute("formName", "Przypisywanie karty SIM");
        model.addAttribute("mobileDeviceDto", mobileDeviceService.getMobileDeviceById(mobileDeviceId));
        model.addAttribute("sims", simCardService.getAllCardSims());
        return "admin/mobile/add-sim";
    }

    @PostMapping("/mobile/addSimCard/{mobileDeviceId}")
    public String addSimCard(@PathVariable("mobileDeviceId") Long mobileDeviceId, SimCard simCard){
        mobileDeviceService.getComputerAndAddSimCardHim(simCard, mobileDeviceService.getMobileDeviceById(mobileDeviceId));
        return "redirect:/admin/mobile";
    }

    private void setCollectionsManufacturersAndDeviceTypes(Model model) {
        model.addAttribute("produces", manufacturerService.getAllManufacturers());
        model.addAttribute("models", deviceTypeService.getAllDeviceTypes());
    }
}
