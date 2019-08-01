package com.example.company.device_library.repository;

import com.example.company.device_library.model.DeviceManufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<DeviceManufacturer, Long> {
}
