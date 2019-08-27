package com.example.company.device_library.repository;

import com.example.company.device_library.controller.DeviceTypeController;
import com.example.company.device_library.model.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceTypeRepository extends JpaRepository<DeviceType, Long> {

    @Query("select dt from DeviceType dt where dt.typeName =?1")
    Optional<DeviceType> getDeviceTypeByName(String typeName);

    Optional<DeviceType> getDeviceTypeById(Long deviceTypeId);
}
