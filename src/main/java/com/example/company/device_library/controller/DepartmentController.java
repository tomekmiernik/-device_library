package com.example.company.device_library.controller;

import com.example.company.device_library.service.DepartmentService;
import com.example.company.device_library.util.dtos.DepartmentDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class DepartmentController {
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/department")
    public String departmentPage(Model model) {
        model.addAttribute("departmentDto", new DepartmentDto());
        model.addAttribute("formName", "Dodawanie działu");
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "admin/department/department";
    }

    @PostMapping("/department")
    public String addNewDepartmentItem(@ModelAttribute("departmentDto") DepartmentDto departmentDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/department/department";
        } else {
            departmentService.addDepartment(departmentDto);
            return "redirect:/admin/department";
        }
    }

    @GetMapping("/department/{departmentId}/updateDepartment")
    public String getPageForUpdateDepartmentItem(Model model, @PathVariable("departmentId") Long departmentId) {
        model.addAttribute("departmentDto", departmentService.getDepartmentById(departmentId));
        return "admin/department/update-department";
    }

    @PutMapping("/department")
    public String updateDepartmentItem(@ModelAttribute("departmentDto") DepartmentDto departmentDto) {
        departmentService.updateDepartment(departmentDto);
        return "redirect:/admin/department";
    }
}
