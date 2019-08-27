package com.example.company.device_library.repository;

import com.example.company.device_library.model.Consumable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsumableRepository extends JpaRepository<Consumable, Long> {

    Optional<Consumable> getConsumableById(Long consumableId);
}
