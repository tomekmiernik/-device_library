package com.example.company.device_library.controller;

import com.example.company.device_library.model.Consumable;
import com.example.company.device_library.service.ConsumableService;
import com.example.company.device_library.service.DeviceTypeService;
import com.example.company.device_library.service.ManufacturerService;
import com.example.company.device_library.service.PrinterService;
import com.example.company.device_library.util.dtos.PrinterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/admin")
public class PrinterController {
    private PrinterService printerService;
    private ManufacturerService manufacturerService;
    private DeviceTypeService deviceTypeService;
    private ConsumableService consumableService;

    @Autowired
    public PrinterController(PrinterService printerService,
                             ManufacturerService manufacturerService,
                             DeviceTypeService deviceTypeService,
                             ConsumableService consumableService) {
        this.printerService = printerService;
        this.manufacturerService = manufacturerService;
        this.deviceTypeService = deviceTypeService;
        this.consumableService = consumableService;
    }

    @GetMapping("/printer")
    public String printerPage(Model model) {
        model.addAttribute("formName", "Dodawanie drukarki");
        model.addAttribute("printerDto", new PrinterDto());
        setCollectionsManufacturersAndDeviceTypes(model);
        getPrintersForTableContent(model);
        return "admin/printer/printer";
    }

    @PostMapping("/printer")
    public String addNewPrinterItem(@ModelAttribute("printerDto") @Valid PrinterDto printerDto,
                                    BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            setCollectionsManufacturersAndDeviceTypes(model);
            getPrintersForTableContent(model);
            return "admin/printer/printer";
        } else if(printerService.addPrinter(printerDto)){
            return "redirect:/admin/printer";
        }else {
            setCollectionsManufacturersAndDeviceTypes(model);
            getPrintersForTableContent(model);
            model.addAttribute("info", "Istnieje urządzenie o takim numerze seryjnym");
            return "admin/printer/printer";
        }
    }

    @GetMapping("/printer/{printerId}/updatePrinter")
    public String getPageForUpdatePrinterItem(Model model, @PathVariable("printerId") Long printerId) {
        model.addAttribute("formName", "Edycja drukarki");
        getPrinterDtoByHerId(printerId,model);
        setCollectionsManufacturersAndDeviceTypes(model);
        return "admin/printer/update-printer";
    }

    @PutMapping("/printer")
    public String updatePrinterItem(@ModelAttribute("printerDto") @Valid PrinterDto printerDto,
                                    BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            setCollectionsManufacturersAndDeviceTypes(model);
            return "admin/printer/update-printer";
        }
        printerService.updatePrinter(printerDto);
        return "redirect:/admin/printer";
    }

    @GetMapping("/printer/{printerId}/addConsumable")
    public String getPageForAddConsumableOfPrinter(@PathVariable("printerId") Long printerId, Model model) {
        model.addAttribute("formName", "Dodawanie materiałów eksploatacyjnych");
        getPrinterDtoByHerId(printerId, model);
        setCollectionConsumables(model);
        return "admin/printer/add-consumable";
    }

    @PutMapping("/printer/addConsumable/{printerId}")
    public String addConsumable(@PathVariable("printerId") Long printerId,
                                @RequestParam(value = "consumable", required = false) Collection<Long> consumableIds,
                                        Model model) {
        if(consumableIds == null || consumableIds.isEmpty()){
            setCollectionConsumables(model);
            getPrinterDtoByHerId(printerId, model);
            setDisplayWarningInformation(model);
            return "admin/printer/add-consumable";
        }
        printerService.getPrinterAndAddConsumableHer(consumableIds, printerService.getPrinterById(printerId));
        return "redirect:/admin/printer";
    }

    private void setCollectionsManufacturersAndDeviceTypes(Model model) {
        model.addAttribute("produces", manufacturerService.getAllManufacturers());
        model.addAttribute("models", deviceTypeService.getAllDeviceTypes());
    }

    private void getPrintersForTableContent(Model model) {
        model.addAttribute("printers", printerService.getAllPrinters());
    }

    private void getPrinterDtoByHerId(Long printerId, Model model) {
        model.addAttribute("printerDto", printerService.getPrinterById(printerId));
    }

    private void setCollectionConsumables(Model model) {
        model.addAttribute("consumables", consumableService.getAllConsumables());
    }

    private void setDisplayWarningInformation(Model model) {
        model.addAttribute("printerInfoWarning", "Dokonaj wybory");
    }
}
