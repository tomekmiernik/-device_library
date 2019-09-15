package com.example.company.device_library.repository;

import com.example.company.device_library.model.OtherDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface OtherDeviceRepository extends JpaRepository<OtherDevice, Long> {

    Optional<OtherDevice> getOtherDeviceById(Long otherDeviceId);
}
