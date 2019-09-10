package com.example.company.device_library.controller;

import com.example.company.device_library.model.Monitor;
import com.example.company.device_library.model.Peripheral;
import com.example.company.device_library.model.Printer;
import com.example.company.device_library.model.Software;
import com.example.company.device_library.service.*;
import com.example.company.device_library.util.dtos.ComputerDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class ComputerController {

    private ComputerService computerService;
    private DeviceTypeService deviceTypeService;
    private ManufacturerService manufacturerService;
    private UserService userService;
    private PrinterService printerService;
    private MonitorService monitorService;
    private PeripheralService peripheralService;
    private SoftwareService softwareService;

    public ComputerController(ComputerService computerService, DeviceTypeService deviceTypeService,
                              ManufacturerService manufacturerService, UserService userService,
                              PrinterService printerService,
                              MonitorService monitorService,
                              PeripheralService peripheralService,
                              SoftwareService softwareService) {
        this.computerService = computerService;
        this.deviceTypeService = deviceTypeService;
        this.manufacturerService = manufacturerService;
        this.userService = userService;
        this.printerService = printerService;
        this.monitorService = monitorService;
        this.peripheralService = peripheralService;
        this.softwareService = softwareService;
    }

    @GetMapping("/computer")
    public String computerPage(Model model) {
        model.addAttribute("buttonValue", "Dodaj");
        model.addAttribute("computerDto", new ComputerDto());
        model.addAttribute("computers", computerService.getAllComputers());
        model.addAttribute("produces", manufacturerService.getAllManufacturers());
        model.addAttribute("models", deviceTypeService.getAllDeviceTypes());
        model.addAttribute("printers", printerService.getAllPrinters());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("formName", "Dodawanie komputera");
        return "admin/computer/computer";
    }

    @PostMapping("/computer")
    public String addNewComputerItem(@ModelAttribute("computerDto") ComputerDto computerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/computer/computer";
        } else {
            computerService.addComputer(computerDto);
            return "redirect:/admin/computer";
        }
    }

    @GetMapping("/computer/{computerId}/updateComputer")
    public String getPageForUpdateComputerItem(Model model, @PathVariable("computerId") Long computerId) {
        model.addAttribute("buttonValue", "Edytuj");
        model.addAttribute("computerDto", computerService.getComputerById(computerId));
        model.addAttribute("produces", manufacturerService.getAllManufacturers());
        model.addAttribute("models", deviceTypeService.getAllDeviceTypes());
        model.addAttribute("printers", printerService.getAllPrinters());
        model.addAttribute("users", userService.getAllUsers());
        return "admin/computer/computer";
    }

    @PutMapping("/computer")
    public String updateComputerItem(@ModelAttribute("computerDto") ComputerDto computerDto) {
        computerService.updateComputer(computerDto);
        return "redirect:/admin/computer";
    }

    @GetMapping("/computer/{computerId}/addMonitor")
    public String getPageForAddMonitorOfComputer(@PathVariable("computerId") Long computerId, Model model) {
        model.addAttribute("formName", "Dodawanie monitora do stacji roboczej");
        model.addAttribute("computerDto", computerService.getComputerById(computerId));
        model.addAttribute("monitors", monitorService.getAllMonitors());
        return "admin/computer/add-monitor";
    }

    @PostMapping("/computer/addMonitor/{computerId}")
    public String addMonitor(@PathVariable("computerId") Long computerId, Monitor monitor) {
        computerService.getComputerAndAddMonitorHim(monitor, computerService.getComputerById(computerId));
        return "redirect:/admin/computer";
    }

    @GetMapping("/computer/{computerId}/addPrinter")
    public String getPageForAddPrinterOfComputer(@PathVariable("computerId") Long computerId, Model model) {
        model.addAttribute("formName", "Dodawanie drukarki do stacji roboczej");
        model.addAttribute("computerDto", computerService.getComputerById(computerId));
        model.addAttribute("printers", printerService.getAllPrinters());
        return "admin/computer/add-printer";
    }

    @PostMapping("/computer/addPrinter/{computerId}")
    public String addPrinter(@PathVariable("computerId") Long computerId, Printer printer) {
        computerService.getComputerAndAddPrinterHim(printer, computerService.getComputerById(computerId));
        return "redirect:/admin/computer";
    }

    @GetMapping("/computer/{computerId}/addPeripheral")
    public String getPageForAddPeripheralOfComputer(@PathVariable("computerId") Long computerId, Model model) {
        model.addAttribute("formName", "Dodawanie myszy / klawiatury do stacji roboczej");
        model.addAttribute("computerDto", computerService.getComputerById(computerId));
        model.addAttribute("peripherals", peripheralService.getAllPeripherals());
        return "admin/computer/add-peripheral";
    }

    @PostMapping("/computer/addPeripheral/{computerId}")
    public String addPeripheral(@PathVariable("computerId") Long computerId,
                                Peripheral peripheral) {
        computerService.getComputerAndAddPeripheralHim(peripheral, computerService.getComputerById(computerId));
        return "redirect:/admin/computer";
    }

    @GetMapping("/computer/{computerId}/addSoftware")
    public String getPageForAddSoftwareOfComputer(@PathVariable("computerId") Long computerId, Model model) {
        model.addAttribute("formName", "Dodawanie używanego oprogramowania");
        model.addAttribute("computerDto", computerService.getComputerById(computerId));
        model.addAttribute("softwares", softwareService.getAllSoftwares());
        return "admin/computer/add-software";
    }

    @PostMapping("/computer/addSoftware/{computerId}")
    public String addSoftware(@PathVariable("computerId") Long computerId, Software software) {
        computerService.getComputerAndAddSoftwareHim(software, computerService.getComputerById(computerId));
        return "redirect:/admin/computer";
    }
}
