package com.example.company.device_library.repository;

import com.example.company.device_library.model.Peripheral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeripheralRepository extends JpaRepository<Peripheral, Long> {

    Optional<Peripheral> getPeripheralById(Long peripheralId);

    @Query("select p from Peripheral p where p.isUse = false")
    List<Peripheral> findFreePeripheral();

    @Query("select p from Peripheral p where p.isUse = true")
    List<Peripheral> findNotFreePeripheral();
}
