package com.example.company.device_library.service;

import com.example.company.device_library.model.Department;
import com.example.company.device_library.repository.DepartmentRepository;
import com.example.company.device_library.util.dtos.DepartmentDto;
import com.example.company.device_library.util.mappers.DepartmentMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private DepartmentRepository departmentRepository;
    private DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentRepository departmentRepository,
                             DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    public Collection<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::map)
                .collect(Collectors.toList());
    }

    public Department addDepartment(DepartmentDto departmentDto) {
        return departmentRepository.save(departmentMapper.reverse(departmentDto));
    }

    public DepartmentDto getDepartmentById(Long departmentId) {
        return departmentMapper.map(departmentRepository.getOne(departmentId));
    }

    public void updateDepartment(DepartmentDto departmentDto) {
        departmentRepository.getDepartmentById(departmentDto.getDepartmentId())
                .ifPresent(d -> {
                    d.setDepartmentName(departmentDto.getDepartmentName());
                    d.setDepartmentShortName(departmentDto.getDepartmentShortName());
                    departmentRepository.save(d);
                });
    }
}
