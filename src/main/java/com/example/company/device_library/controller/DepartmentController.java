package com.example.company.device_library.controller;

import com.example.company.device_library.service.DepartmentService;
import com.example.company.device_library.util.dtos.DepartmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class DepartmentController {
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/department")
    public String departmentPage(Model model) {
        model.addAttribute("formName", "Dodawanie działu");
        model.addAttribute("departmentDto", new DepartmentDto());
        getDepartmentsForTableContent(model);
        return "admin/department/department";
    }

    @PostMapping("/department")
    public String addNewDepartmentItem(@Valid DepartmentDto departmentDto,
                                       BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            getDepartmentsForTableContent(model);
            return "admin/department/department";
        } else if (departmentService.addDepartment(departmentDto)) {
            return "redirect:/admin/department";
        } else {
            getDepartmentsForTableContent(model);
            model.addAttribute("info", "Wpis o takich oznaczeniach już istnieje");
            return "admin/department/department";
        }
    }

    @GetMapping("/department/{departmentId}/updateDepartment")
    public String getPageForUpdateDepartmentItem(Model model, @PathVariable("departmentId") Long departmentId) {
        model.addAttribute("formName", "Edycja informacji o dziale");
        model.addAttribute("departmentDto", departmentService.getDepartmentById(departmentId));
        return "admin/department/update-department";
    }

    @PutMapping("/department")
    public String updateDepartmentItem(@ModelAttribute("departmentDto") @Valid DepartmentDto departmentDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/department/update-department";
        } else {
            departmentService.updateDepartment(departmentDto);
            return "redirect:/admin/department";
        }
    }

    private void getDepartmentsForTableContent(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
    }
}
