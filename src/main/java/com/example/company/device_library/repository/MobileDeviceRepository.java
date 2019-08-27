package com.example.company.device_library.repository;

import com.example.company.device_library.model.MobileDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MobileDeviceRepository extends JpaRepository<MobileDevice, Long> {
    Optional<MobileDevice> getMobileDeviceById(Long telephoneId);
}
