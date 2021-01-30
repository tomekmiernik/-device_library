package com.example.company.device_library.controller;

import com.example.company.device_library.service.DeviceTypeService;
import com.example.company.device_library.service.ManufacturerService;
import com.example.company.device_library.service.TelephoneService;
import com.example.company.device_library.util.dtos.TelephoneDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TelephoneController {

    private TelephoneService telephoneService;
    private ManufacturerService manufacturerService;
    private DeviceTypeService deviceTypeService;

    @Autowired
    public TelephoneController(TelephoneService telephoneService,
                               ManufacturerService manufacturerService,
                               DeviceTypeService deviceTypeService) {
        this.telephoneService = telephoneService;
        this.manufacturerService = manufacturerService;
        this.deviceTypeService = deviceTypeService;
    }

    @GetMapping("/phone")
    public String getTelephonePage(Model model){
        model.addAttribute("formName", "Dodawanie telefonu stacjonarnego");
        model.addAttribute("telephoneDto", new TelephoneDto());
        setCollectionsManufacturersAndDeviceTypes(model);
        getPhonesForTableContent(model);
        return "admin/phone/phone";
    }

    @PostMapping("/phone")
    public String addNewTelephoneItem(@ModelAttribute("telephoneDto") @Valid TelephoneDto telephoneDto,
                                      BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            setCollectionsManufacturersAndDeviceTypes(model);
            getPhonesForTableContent(model);
            return "admin/phone/phone";
        }else if(telephoneService.addTelephone(telephoneDto)) {
            return "redirect:/admin/phone";
        }else {
            setCollectionsManufacturersAndDeviceTypes(model);
            getPhonesForTableContent(model);
            model.addAttribute("info", "Istnieje urządzenie o takim numerze seryjnym");
            return "admin/phone/phone";
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
    public String updateTelephoneItem(@ModelAttribute("telephoneDto") @Valid TelephoneDto telephoneDto,
                                      BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            setCollectionsManufacturersAndDeviceTypes(model);
            return "admin/phone/update-phone";
        }
        telephoneService.updateTelephone(telephoneDto);
        return "redirect:/admin/phone";
    }

    private void setCollectionsManufacturersAndDeviceTypes(Model model) {
        model.addAttribute("produces", manufacturerService.getAllManufacturers());
        model.addAttribute("models",deviceTypeService.getAllDeviceTypes());
    }


    private void getPhonesForTableContent(Model model) {
        model.addAttribute("phones", telephoneService.getAllTelephones());
    }
}
