package com.example.company.device_library.repository;

import com.example.company.device_library.model.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {

    @Query("select c from Computer c where c.id=?1")
    Optional<Computer> getComputerById(Long computerId);

    @Query("select c from Computer c where c.isUse = false")
    Collection<Computer> findFreeComputers();

    @Query("select c from Computer c where c.isUse = true")
    Collection<Computer> findNotFreeComputers();
}
