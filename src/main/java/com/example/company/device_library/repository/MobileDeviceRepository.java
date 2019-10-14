package com.example.company.device_library.repository;

import com.example.company.device_library.model.MobileDevice;
import com.example.company.device_library.util.dtos.MobileDeviceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface MobileDeviceRepository extends JpaRepository<MobileDevice, Long> {
    Optional<MobileDevice> getMobileDeviceById(Long telephoneId);

    @Query("select m from MobileDevice m where m.isUse = false")
    Collection<MobileDevice> findFreeMobiles();

    @Query("select m from MobileDevice m where m.isUse = true")
    Collection<MobileDevice> findNotFreeMobiles();
}
