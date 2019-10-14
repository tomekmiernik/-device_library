package com.example.company.device_library.repository;

import com.example.company.device_library.model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserApp, Long> {

    @Query("select u from UserApp u where u.lastName = ?1")
    Collection<UserApp> findUsersByLastName(String userLastName);

    @Query("select u from UserApp u where u.email = ?1")
    Optional<UserApp> findUserByUsernameAsEmail(String email);

    Optional<UserApp> getUserById(Long userId);
}
