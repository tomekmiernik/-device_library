package com.example.company.device_library.controller;

import com.example.company.device_library.service.*;
import com.example.company.device_library.util.dtos.ComputerDto;
import com.example.company.device_library.util.dtos.PeripheralDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/admin")
public class ComputerController {

    private ComputerService computerService;
    private DeviceTypeService deviceTypeService;
    private ManufacturerService manufacturerService;
    private PrinterService printerService;
    private MonitorService monitorService;
    private PeripheralService peripheralService;
    private SoftwareService softwareService;

    @Autowired
    public ComputerController(ComputerService computerService,
                              DeviceTypeService deviceTypeService,
                              ManufacturerService manufacturerService,
                              PrinterService printerService,
                              MonitorService monitorService,
                              PeripheralService peripheralService,
                              SoftwareService softwareService) {
        this.computerService = computerService;
        this.deviceTypeService = deviceTypeService;
        this.manufacturerService = manufacturerService;
        this.printerService = printerService;
        this.monitorService = monitorService;
        this.peripheralService = peripheralService;
        this.softwareService = softwareService;
    }

    @GetMapping("/computer")
    public String computerPage(Model model) {
        model.addAttribute("formName", "Dodawanie komputera");
        model.addAttribute("computerDto", new ComputerDto());
        getComputersForTableContent(model);
        setCollectionsManufacturersAndDeviceTypes(model);
        return "admin/computer/computer";
    }

    @PostMapping("/computer")
    public String addNewComputerItem(@ModelAttribute("computerDto") @Valid ComputerDto computerDto,
                                     BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            setCollectionsManufacturersAndDeviceTypes(model);
            getComputersForTableContent(model);
            return "admin/computer/computer";
        } else if(computerService.addComputer(computerDto)){
            return "redirect:/admin/computer";
        }else {
            setCollectionsManufacturersAndDeviceTypes(model);
            getComputersForTableContent(model);
            model.addAttribute("info", "Numer seryjny istnieje w bazie");
            return "admin/computer/computer";
        }
    }

    @GetMapping("/computer/{computerId}/updateComputer")
    public String getPageForUpdateComputerItem(Model model, @PathVariable("computerId") Long computerId) {
        model.addAttribute("formName", "Edycja komputera");
        getComputerDtoByHisId(computerId, model);
        setCollectionsManufacturersAndDeviceTypes(model);
        return "admin/computer/update-computer";
    }

    @PutMapping("/computer")
    public String updateComputerItem(@ModelAttribute("computerDto") @Valid ComputerDto computerDto,
                                     BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            setCollectionsManufacturersAndDeviceTypes(model);
            return "admin/computer/update-computer";
        } else {
            computerService.updateComputer(computerDto);
            return "redirect:/admin/computer";
        }
    }

    @GetMapping("/computer/{computerId}/addMonitor")
    public String getPageForAddMonitorOfComputer(@PathVariable("computerId") Long computerId, Model model) {
        model.addAttribute("formName", "Dodawanie monitora do stacji roboczej");
        getComputerDtoByHisId(computerId, model);
        setCollectionMonitors(model);
        return "admin/computer/add-monitor";
    }

    @PutMapping("/computer/addMonitor/{computerId}")
    public String addMonitor(@PathVariable("computerId") Long computerId,
                             @RequestParam(value = "monitor", required = false) Long monitorId,
                             Model model) {
        if (monitorId == null) {
            setCollectionMonitors(model);
            getComputerDtoByHisId(computerId, model);
            setDisplayWarningInformation(model);
            return "admin/computer/add-monitor";
        } else {
            computerService.getComputerAndAddMonitorHim(monitorId, computerService.getComputerById(computerId));
            return "redirect:/admin/computer";
        }
    }

    @GetMapping("/computer/{computerId}/addPrinter")
    public String getPageForAddPrinterOfComputer(@PathVariable("computerId") Long computerId, Model model) {
        model.addAttribute("formName", "Dodawanie drukarki do stacji roboczej");
        getComputerDtoByHisId(computerId, model);
        setCollectionPrinters(model);
        return "admin/computer/add-printer";
    }

    @PutMapping("/computer/addPrinter/{computerId}")
    public String addPrinter(@PathVariable("computerId") Long computerId,
                             @RequestParam(value = "printer", required = false) Long printerId,
                             Model model) {
        if (printerId == null) {
            setCollectionPrinters(model);
            getComputerDtoByHisId(computerId, model);
            setDisplayWarningInformation(model);
            return "admin/computer/add-printer";
        } else {
            computerService.getComputerAndAddPrinterHim(printerId, computerService.getComputerById(computerId));
            return "redirect:/admin/computer";
        }
    }

    @GetMapping("/computer/{computerId}/addPeripheral")
    public String getPageForAddPeripheralOfComputer(@PathVariable("computerId") Long computerId, Model model) {
        model.addAttribute("formName", "Dodawanie myszy / klawiatury do stacji roboczej");
        getComputerDtoByHisId(computerId, model);
        setCollectionFreePeripherals(model);
        return "admin/computer/add-peripheral";
    }

    @PutMapping("/computer/addPeripheral/{computerId}")
    public String addPeripheral(@PathVariable("computerId") Long computerId,
                                @RequestParam(value = "peripheral", required = false) Collection<Long> peripheralListId,
                                Model model) {
        if (peripheralListId == null) {
            setCollectionFreePeripherals(model);
            getComputerDtoByHisId(computerId, model);
            setDisplayWarningInformation(model);
            return "admin/computer/add-peripheral";
        } else {
            computerService.getComputerAndAddPeripheralHim(peripheralListId, computerService.getComputerById(computerId));
            return "redirect:/admin/computer";
        }
    }

    @GetMapping("/computer/{computerId}/addSoftware")
    public String getPageForAddSoftwareOfComputer(@PathVariable("computerId") Long computerId, Model model) {
        model.addAttribute("formName", "Wybierz zainstalowane programy");
        getComputerDtoByHisId(computerId, model);
        setCollectionSoftware(model);
        return "admin/computer/add-software";
    }

    @PutMapping("/computer/addSoftware/{computerId}")
    public String addSoftware(@PathVariable("computerId") Long computerId,
                              @RequestParam(value = "software", required = false) Collection<Long> softwareListId,
                              Model model) {
        if (softwareListId == null) {
            setCollectionSoftware(model);
            getComputerDtoByHisId(computerId, model);
            setDisplayWarningInformation(model);
            return "admin/computer/add-software";
        } else {
            computerService.getComputerAndAddSoftwareHim(softwareListId, computerService.getComputerById(computerId));
            return "redirect:/admin/computer";
        }
    }

    @GetMapping("/computer/kit")
    public String kitsPage(Model model){
        Collection<PeripheralDto> notFreePeripherals = peripheralService.getNotFreePeripherals();
        if(notFreePeripherals == null || notFreePeripherals.isEmpty()){
            model.addAttribute("computerInfoWarning", "Lista nie zawiera wolnych elementów");
            return "admin/computer/kit";
        }
        model.addAttribute("peripherals", notFreePeripherals);
        return "admin/computer/kit";
    }

    @GetMapping("/computer/{peripheralId}/changeToFree")
    public String changePeripheralOnReadyForUse(@PathVariable("peripheralId") Long peripheralId){
        peripheralService.changePeripheralOnReadyToUse(peripheralId);
        return "redirect:/admin/computer";
    }

    private void setCollectionsManufacturersAndDeviceTypes(Model model) {
        model.addAttribute("produces", manufacturerService.getAllManufacturers());
        model.addAttribute("models", deviceTypeService.getAllDeviceTypes());
    }

    private void getComputersForTableContent(Model model) {
        model.addAttribute("computers", computerService.getAllComputers());
    }

    private void setCollectionMonitors(Model model) {
        model.addAttribute("monitors", monitorService.getAllMonitors());
    }

    private void getComputerDtoByHisId(Long computerId, Model model) {
        model.addAttribute("computerDto", computerService.getComputerById(computerId));
    }

    private void setCollectionPrinters(Model model) {
        model.addAttribute("printers", printerService.getAllPrinters());
    }

    private void setDisplayWarningInformation(Model model) {
        model.addAttribute("computerInfoWarning", "Dokonaj wyboru");
    }

    private void setCollectionFreePeripherals(Model model) {
        if (peripheralService.getAllFreePeripherals().isEmpty()) {
            model.addAttribute("computerInfoWarning", "Brak wolnych urządzeń");
        }
        model.addAttribute("peripherals", peripheralService.getAllFreePeripherals());
    }

    private void setCollectionSoftware(Model model) {
        model.addAttribute("software", softwareService.getAllSoftwares());
    }
}
