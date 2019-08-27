package com.example.company.device_library.repository;

import com.example.company.device_library.model.Software;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SoftwareRepository extends JpaRepository<Software, Long> {
    Optional<Software> getSoftwareById(Long softwareId);
}
