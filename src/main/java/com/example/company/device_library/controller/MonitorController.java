package com.example.company.device_library.controller;

import com.example.company.device_library.service.ComputerService;
import com.example.company.device_library.service.DeviceTypeService;
import com.example.company.device_library.service.ManufacturerService;
import com.example.company.device_library.service.MonitorService;
import com.example.company.device_library.util.dtos.MonitorDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class MonitorController {
    private MonitorService monitorService;
    private ManufacturerService manufacturerService;
    private DeviceTypeService deviceTypeService;
    private ComputerService computerService;

    public MonitorController(MonitorService monitorService, ManufacturerService manufacturerService,
                             DeviceTypeService deviceTypeService, ComputerService computerService) {
        this.monitorService = monitorService;
        this.manufacturerService = manufacturerService;
        this.deviceTypeService = deviceTypeService;
        this.computerService = computerService;
    }

    @GetMapping("/monitor")
    public String monitorPage(Model model) {
        model.addAttribute("monitorDto", new MonitorDto());
        model.addAttribute("formName", "Dodawanie monitora");
        model.addAttribute("produces", manufacturerService.getAllManufacturers());
        model.addAttribute("models", deviceTypeService.getAllDeviceTypes());
        model.addAttribute("computers", computerService.getAllComputers());
        model.addAttribute("monitors", monitorService.getAllMonitors());
        return "admin/monitor/monitor";
    }

    @PostMapping("/monitor")
    public String addNewMonitorItem(@ModelAttribute("monitorDto") MonitorDto monitorDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/monitor/monitor";
        } else {
            monitorService.addMonitor(monitorDto);
            return "redirect:/admin/monitor";
        }
    }

    @GetMapping("/monitor/{monitorId}/updateMonitor")
    public String getPageForUpdateMonitorItem(Model model, @PathVariable("monitorId") Long monitorId) {
        model.addAttribute("monitorDto", monitorService.getMonitorById(monitorId));
        return "admin/monitor/update-monitor";
    }

    @PutMapping("/monitor")
    public String updateMonitorItem(@ModelAttribute("monitorDto") MonitorDto monitorDto) {
        monitorService.updateMonitor(monitorDto);
        return "redirect:/admin/monitor";
    }
}
