package com.example.company.device_library.repository;

import com.example.company.device_library.model.SimCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SimCardRepository extends JpaRepository<SimCard, Long> {
    @Query("select s from SimCard s where s.id=?1")
    Optional<SimCard> getSimCardById(Long simCardId);
}
