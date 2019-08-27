package com.example.company.device_library.util.mappers;

import com.example.company.device_library.model.Department;
import com.example.company.device_library.util.dtos.DepartmentDto;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper implements Mapper<Department, DepartmentDto> {
    @Override
    public DepartmentDto map(Department from) {
        return DepartmentDto.builder()
                .departmentId(from.getId())
                .departmentName(from.getDepartmentName())
                .departmentShortName(from.getDepartmentShortName())
                .build();
    }

    @Override
    public Department reverse(DepartmentDto to) {
        Department department = new Department();
        department.setId(to.getDepartmentId());
        department.setDepartmentName(to.getDepartmentName());
        department.setDepartmentShortName(to.getDepartmentShortName());
        return department;
    }
}
