package com.example.company.device_library.repository;

import com.example.company.device_library.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> getDepartmentById(Long departmentId);
}
