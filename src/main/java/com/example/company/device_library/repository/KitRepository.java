package com.example.company.device_library.repository;

import com.example.company.device_library.model.Kit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KitRepository extends JpaRepository<Kit, Long> {

    Optional<Kit> findKitById(Long kitId);
}
