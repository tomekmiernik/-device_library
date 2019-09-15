package com.example.company.device_library.controller;

import com.example.company.device_library.service.DeviceTypeService;
import com.example.company.device_library.service.ManufacturerService;
import com.example.company.device_library.service.TelephoneService;
import com.example.company.device_library.util.dtos.TelephoneDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class TelephoneController {

    private TelephoneService telephoneService;
    private ManufacturerService manufacturerService;
    private DeviceTypeService deviceTypeService;

    public TelephoneController(TelephoneService telephoneService,
                               ManufacturerService manufacturerService,
                               DeviceTypeService deviceTypeService) {
        this.telephoneService = telephoneService;
        this.manufacturerService = manufacturerService;
        this.deviceTypeService = deviceTypeService;
    }

    @GetMapping("/phone")
    public String getTelephonePage(Model model){
        model.addAttribute("formName", "Dodawnaie telefonu stacjonarnego");
        model.addAttribute("telephoneDto", new TelephoneDto());
        setCollectionsManufacturersAndDeviceTypes(model);
        model.addAttribute("phones", telephoneService.getAllTelephones());
        return "admin/phone/phone";
    }

    @PostMapping("/phone")
    public String addNewTelephoneItem(@ModelAttribute("telephoneDot") TelephoneDto telephoneDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "admin/phone/phone";
        }else {
            telephoneService.addTelephone(telephoneDto);
            return "redirect:/admin/phone";
        }
    }

    @GetMapping("/phone/{phoneId}/updatePhone")
    public String getPageForUpdateTelephoneItem(Model model, @PathVariable("phoneId") Long phoneId){
        model.addAttribute("formName", "Edytowanie informacji o telefonie");
        setCollectionsManufacturersAndDeviceTypes(model);
        model.addAttribute("telephoneDto", telephoneService.getTelephoneById(phoneId));
        return "admin/phone/update-phone";
    }

    @PutMapping("/phone")
    public String updateTelephoneItem(@ModelAttribute("telephoneDto") TelephoneDto telephoneDto){
        telephoneService.updateTelephone(telephoneDto);
        return "redirect:/admin/phone";
    }

    private void setCollectionsManufacturersAndDeviceTypes(Model model) {
        model.addAttribute("produces", manufacturerService.getAllManufacturers());
        model.addAttribute("models",deviceTypeService.getAllDeviceTypes());
    }

}
