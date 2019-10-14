package com.example.company.device_library.controller;

import com.example.company.device_library.service.*;
import com.example.company.device_library.util.dtos.MobileDeviceDto;
import com.example.company.device_library.util.dtos.SimCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/admin")
public class MobileDeviceController {

    private MobileDeviceService mobileDeviceService;
    private ManufacturerService manufacturerService;
    private DeviceTypeService deviceTypeService;
    private SimCardService simCardService;

    @Autowired
    public MobileDeviceController(MobileDeviceService mobileDeviceService,
                                  ManufacturerService manufacturerService,
                                  DeviceTypeService deviceTypeService,
                                  SimCardService simCardService) {
        this.mobileDeviceService = mobileDeviceService;
        this.manufacturerService = manufacturerService;
        this.deviceTypeService = deviceTypeService;
        this.simCardService = simCardService;
    }

    @GetMapping("/mobile")
    public String mobileDevicePage(Model model) {
        model.addAttribute("formName", "Dodawanie urządzenia mobilnego");
        model.addAttribute("mobileDeviceDto", new MobileDeviceDto());
        setCollectionsManufacturersAndDeviceTypes(model);
        getMobilesForTableContent(model);
        return "admin/mobile/mobile";
    }

    @PostMapping("/mobile")
    public String addNewMobileDeviceItem(@Valid MobileDeviceDto mobileDeviceDto,
                                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            setCollectionsManufacturersAndDeviceTypes(model);
            getMobilesForTableContent(model);
            return "admin/mobile/mobile";
        } else if(mobileDeviceService.addMobileDevice(mobileDeviceDto)){
            return "redirect:/admin/mobile";
        }else {
            setCollectionsManufacturersAndDeviceTypes(model);
            getMobilesForTableContent(model);
            model.addAttribute("info", "Numer seryjny lub numer IMEI istnieją w bazie");
            return "admin/mobile/mobile";
        }
    }

    @GetMapping("/mobile/{mobileDeviceId}/updateMobileDevice")
    public String getPageForUpdateTelephoneItem(Model model, @PathVariable("mobileDeviceId") Long mobileDeviceId) {
        model.addAttribute("formName", "Edycja informacji o urządzeniu mobilnym");
        getMobileDeviceDtoByHisId(mobileDeviceId, model);
        setCollectionsManufacturersAndDeviceTypes(model);
        return "admin/mobile/update-mobile";
    }

    @PutMapping("/mobile")
    public String updateMobileDeviceItem(@ModelAttribute("mobileDeviceDto") @Valid MobileDeviceDto mobileDeviceDto,
                                         BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            setCollectionsManufacturersAndDeviceTypes(model);
            return "admin/mobile/update-mobile";
        }else {
            mobileDeviceService.updateMobileDevice(mobileDeviceDto);
            return "redirect:/admin/mobile";
        }
    }

    @GetMapping("/mobile/{mobileDeviceId}/addSimCard")
    public String getPageForAddSimCardOfMobileDevice(@PathVariable("mobileDeviceId") Long mobileDeviceId, Model model){
        model.addAttribute("formName", "Przypisywanie karty SIM");
        getMobileDeviceDtoByHisId(mobileDeviceId, model);
        setCollectionFreeSimCards(model);
        return "admin/mobile/add-sim";
    }

    @PutMapping("/mobile/addSimCard/{mobileDeviceId}")
    public String addSimCard(@PathVariable("mobileDeviceId") Long mobileDeviceId,
                             @RequestParam(value = "simCard", required = false) Long simCardId,
                             Model model){
        if(simCardId == null){
            setCollectionFreeSimCards(model);
            getMobileDeviceDtoByHisId(mobileDeviceId,model);
            setDisplayWarningInformation(model);
            return "admin/mobile/add-sim";
        }
        mobileDeviceService.getMobileDeviceAndAddSimCardHim(simCardId, mobileDeviceService.getMobileDeviceById(mobileDeviceId));
        return "redirect:/admin/mobile";
    }

    @GetMapping("/mobile/sim")
    public String getSimCardsPage(Model model){
        Collection<SimCardDto> allNotFreeSimCards = simCardService.getAllNotFreeSimCards();
        if(allNotFreeSimCards.isEmpty()){
            model.addAttribute("mobileInfoWarning", "Lista nie zawiera elementów");
            return "admin/mobile/sim";
        }
        model.addAttribute("sims", allNotFreeSimCards);
        return "admin/mobile/sim";
    }

    @GetMapping("/mobile/{simId}/changeToFree")
    public String changeSimOnReadyForUse(@PathVariable("simId") Long simId){
        simCardService.changeSimOnReadyToUse(simId);
        return "redirect:/admin/mobile";
    }

    private void setCollectionsManufacturersAndDeviceTypes(Model model) {
        model.addAttribute("produces", manufacturerService.getAllManufacturers());
        model.addAttribute("models", deviceTypeService.getAllDeviceTypes());
    }

    private void getMobilesForTableContent(Model model) {
        model.addAttribute("mobiles", mobileDeviceService.getAllMobileDevices());
    }

    private void setCollectionFreeSimCards(Model model) {
        if(simCardService.getAllFreeSimCards().isEmpty()){
            model.addAttribute("mobileInfoWarning", "Brak wolnych numerów telfonów");
        }
        model.addAttribute("sims", simCardService.getAllFreeSimCards());
    }

    private void getMobileDeviceDtoByHisId(@PathVariable("mobileDeviceId") Long mobileDeviceId, Model model) {
        model.addAttribute("mobileDeviceDto", mobileDeviceService.getMobileDeviceById(mobileDeviceId));
    }

    private void setDisplayWarningInformation(Model model) {
        model.addAttribute("mobileInfoWarning", "Dokonaj wyboru");
    }
}
