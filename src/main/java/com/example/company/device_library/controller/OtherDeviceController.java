package com.example.company.device_library.controller;

import com.example.company.device_library.service.DeviceTypeService;
import com.example.company.device_library.service.ManufacturerService;
import com.example.company.device_library.service.OtherDeviceService;
import com.example.company.device_library.util.dtos.OtherDeviceDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class OtherDeviceController {
    private OtherDeviceService otherDeviceService;
    private ManufacturerService manufacturerService;
    private DeviceTypeService deviceTypeService;

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
        model.addAttribute("otherDevices", otherDeviceService.getAllDevices());
        return "admin/other/device";
    }

    @PostMapping("/other")
    public String addNewOtherDeviceItem(@ModelAttribute("otherDeviceDto") OtherDeviceDto otherDeviceDto,
                                        BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "admin/other/device";
        }else{
            otherDeviceService.addOtherDevice(otherDeviceDto);
            return "redirect:/admin/other";
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
    public String updateOtherDeviceItem(@ModelAttribute("otherDeviceDto") OtherDeviceDto otherDeviceDto){
        otherDeviceService.updateOtherDevice(otherDeviceDto);
        return "redirect:/admin/other";
    }


    private void setCollectionsManufacturersAndDeviceTypes(Model model) {
        model.addAttribute("produces", manufacturerService.getAllManufacturers());
        model.addAttribute("models", deviceTypeService.getAllDeviceTypes());
    }


}
