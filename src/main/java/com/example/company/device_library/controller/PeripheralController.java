package com.example.company.device_library.controller;

import com.example.company.device_library.service.DeviceTypeService;
import com.example.company.device_library.service.ManufacturerService;
import com.example.company.device_library.service.PeripheralService;
import com.example.company.device_library.util.dtos.PeripheralDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class PeripheralController {
    private PeripheralService peripheralService;
    private ManufacturerService manufacturerService;
    private DeviceTypeService deviceTypeService;

    @Autowired
    public PeripheralController(PeripheralService peripheralService,
                                ManufacturerService manufacturerService,
                                DeviceTypeService deviceTypeService) {
        this.peripheralService = peripheralService;
        this.manufacturerService = manufacturerService;
        this.deviceTypeService = deviceTypeService;
    }

    @GetMapping("/peripheral")
    public String peripheralPage(Model model) {
        model.addAttribute("formName", "Dodawanie myszy / klawiatury");
        model.addAttribute("peripheralDto", new PeripheralDto());
        getPeripheralsForTableContent(model);
        setCollectionsManufacturersAndDeviceTypes(model);
        return "admin/peripheral/peripheral";
    }

    @PostMapping("/peripheral")
    public String addNewPeripheralItem(@ModelAttribute("peripheralDto") @Valid PeripheralDto peripheralDto,
                                       BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            setCollectionsManufacturersAndDeviceTypes(model);
            getPeripheralsForTableContent(model);
            return "admin/peripheral/peripheral";
        } else if(peripheralService.addPeripheral(peripheralDto)){
            return "redirect:/admin/peripheral";
        }else {
            setCollectionsManufacturersAndDeviceTypes(model);
            getPeripheralsForTableContent(model);
            model.addAttribute("info", "Istnieje urządzenie o takim numerze seryjnym");
            return "admin/peripheral/peripheral";
        }
    }

    @GetMapping("/peripheral/{peripheralId}/updatePeripheral")
    public String getPageForUpdatePeripheralItem(Model model, @PathVariable("peripheralId") Long peripheralId) {
        model.addAttribute("formName", "Edycja urządzenia");
        getPeripheralDtoByHisId(model, peripheralId);
        setCollectionsManufacturersAndDeviceTypes(model);
        return "admin/peripheral/update-peripheral";
    }

    @PutMapping("/peripheral")
    public String updatePeripheralItem(@ModelAttribute("peripheralDto") @Valid PeripheralDto peripheralDto,
                                       BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            setCollectionsManufacturersAndDeviceTypes(model);
            return "admin/peripheral/update-peripheral";
        }else {
            peripheralService.updatePeripheral(peripheralDto);
            return "redirect:/admin/peripheral";
        }
    }

    private void setCollectionsManufacturersAndDeviceTypes(Model model) {
        model.addAttribute("produces", manufacturerService.getAllManufacturers());
        model.addAttribute("models", deviceTypeService.getAllDeviceTypes());
    }

    private void getPeripheralsForTableContent(Model model) {
        model.addAttribute("peripherals", peripheralService.getAllPeripherals());
    }

    private void getPeripheralDtoByHisId(Model model,Long peripheralId) {
        model.addAttribute("peripheralDto", peripheralService.getPeripheralById(peripheralId));
    }
}
