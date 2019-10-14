package com.example.company.device_library.controller;

import com.example.company.device_library.service.DeviceTypeService;
import com.example.company.device_library.service.ManufacturerService;
import com.example.company.device_library.service.OtherDeviceService;
import com.example.company.device_library.util.dtos.OtherDeviceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class OtherDeviceController {
    private OtherDeviceService otherDeviceService;
    private ManufacturerService manufacturerService;
    private DeviceTypeService deviceTypeService;

    @Autowired
    public OtherDeviceController(OtherDeviceService otherDeviceService,
                                 ManufacturerService manufacturerService,
                                 DeviceTypeService deviceTypeService) {
        this.otherDeviceService = otherDeviceService;
        this.manufacturerService = manufacturerService;
        this.deviceTypeService = deviceTypeService;
    }


    @GetMapping("/other")
    public String otherDevicePage(Model model){
        model.addAttribute("formName", "Dodawanie urządzenia innego rodzaju");
        model.addAttribute("otherDeviceDto", new OtherDeviceDto());
        setCollectionsManufacturersAndDeviceTypes(model);
        getOtherDeviceForTableContent(model);
        return "admin/other/device";
    }

    @PostMapping("/other")
    public String addNewOtherDeviceItem(@ModelAttribute("otherDeviceDto") @Valid OtherDeviceDto otherDeviceDto,
                                        BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            setCollectionsManufacturersAndDeviceTypes(model);
            getOtherDeviceForTableContent(model);
            return "admin/other/device";
        }else if(otherDeviceService.addOtherDevice(otherDeviceDto)){
            return "redirect:/admin/other";
        }
        else {
            setCollectionsManufacturersAndDeviceTypes(model);
            getOtherDeviceForTableContent(model);
            model.addAttribute("info", "Istnieje urządzenie o takim numerze seryjnym");
            return "admin/other/device";
        }
    }

    @GetMapping("/other/{otherDeviceId}/updateDevice")
    public String getPageForUpdateOtherDeviceItem(Model model, @PathVariable("otherDeviceId") Long otherDeviceId){
        model.addAttribute("formName", "Edycja danych urządzenia");
        model.addAttribute("otherDeviceDto", otherDeviceService.getOtherDeviceById(otherDeviceId));
        setCollectionsManufacturersAndDeviceTypes(model);
        return "admin/other/update-device";
    }

    @PutMapping("/other")
    public String updateOtherDeviceItem(@ModelAttribute("otherDeviceDto") @Valid OtherDeviceDto otherDeviceDto,
                                        BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            setCollectionsManufacturersAndDeviceTypes(model);
            return "admin/other/update-device";
        }
        otherDeviceService.updateOtherDevice(otherDeviceDto);
        return "redirect:/admin/other";
    }


    private void setCollectionsManufacturersAndDeviceTypes(Model model) {
        model.addAttribute("produces", manufacturerService.getAllManufacturers());
        model.addAttribute("models", deviceTypeService.getAllDeviceTypes());
    }

    private void getOtherDeviceForTableContent(Model model) {
        model.addAttribute("otherDevices", otherDeviceService.getAllDevices());
    }
}
