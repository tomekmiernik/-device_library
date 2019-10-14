package com.example.company.device_library.controller;

import com.example.company.device_library.service.ComputerService;
import com.example.company.device_library.service.DeviceTypeService;
import com.example.company.device_library.service.ManufacturerService;
import com.example.company.device_library.service.MonitorService;
import com.example.company.device_library.util.dtos.MonitorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class MonitorController {
    private MonitorService monitorService;
    private ManufacturerService manufacturerService;
    private DeviceTypeService deviceTypeService;

    @Autowired
    public MonitorController(MonitorService monitorService,
                             ManufacturerService manufacturerService,
                             DeviceTypeService deviceTypeService) {
        this.monitorService = monitorService;
        this.manufacturerService = manufacturerService;
        this.deviceTypeService = deviceTypeService;
    }

    @GetMapping("/monitor")
    public String monitorPage(Model model) {
        model.addAttribute("formName", "Dodawanie monitora");
        model.addAttribute("monitorDto", new MonitorDto());
        setCollectionsManufacturersAndDeviceTypes(model);
        getMonitorsForTableContent(model);
        return "admin/monitor/monitor";
    }

    @PostMapping("/monitor")
    public String addNewMonitorItem(@ModelAttribute("monitorDto") @Valid MonitorDto monitorDto,
                                    BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            setCollectionsManufacturersAndDeviceTypes(model);
            getMonitorsForTableContent(model);
            return "admin/monitor/monitor";
        } else if(monitorService.addMonitor(monitorDto)){
            return "redirect:/admin/monitor";
        }else {
            setCollectionsManufacturersAndDeviceTypes(model);
            getMonitorsForTableContent(model);
            model.addAttribute("info", "Istnieje urządzenie o takim numerze seryjnym");
            return "admin/monitor/monitor";
        }
    }

    @GetMapping("/monitor/{monitorId}/updateMonitor")
    public String getPageForUpdateMonitorItem(Model model, @PathVariable("monitorId") Long monitorId) {
        model.addAttribute("formName", "Edycja monitora");
        getMonitorDtoByHisId(model, monitorId);
        setCollectionsManufacturersAndDeviceTypes(model);
        return "admin/monitor/update-monitor";
    }

    @PutMapping("/monitor")
    public String updateMonitorItem(@ModelAttribute("monitorDto") @Valid MonitorDto monitorDto,
                                    BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            setCollectionsManufacturersAndDeviceTypes(model);
            return "admin/monitor/update-monitor";
        }
        monitorService.updateMonitor(monitorDto);
        return "redirect:/admin/monitor";
    }

    private void setCollectionsManufacturersAndDeviceTypes(Model model) {
        model.addAttribute("produces", manufacturerService.getAllManufacturers());
        model.addAttribute("models", deviceTypeService.getAllDeviceTypes());
    }

    private void getMonitorsForTableContent(Model model) {
        model.addAttribute("monitors", monitorService.getAllMonitors());
    }

    private void getMonitorDtoByHisId(Model model, @PathVariable("monitorId") Long monitorId) {
        model.addAttribute("monitorDto", monitorService.getMonitorById(monitorId));
    }
}
