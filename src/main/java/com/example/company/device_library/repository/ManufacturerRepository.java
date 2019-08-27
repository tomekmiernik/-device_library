package com.example.company.device_library.repository;

import com.example.company.device_library.model.DeviceManufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<DeviceManufacturer, Long> {

    @Query("select dm from DeviceManufacturer dm where dm.manufacturerName=?1")
    Optional<DeviceManufacturer> getManufacturerByName(String typeName);

    Optional<DeviceManufacturer> getManufacturerById(Long manufacturerId);

}
