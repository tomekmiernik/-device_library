package com.example.company.device_library.repository;

import com.example.company.device_library.model.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, Long> {
    Optional<Telephone> getTelephoneById(Long telephoneId);
}
