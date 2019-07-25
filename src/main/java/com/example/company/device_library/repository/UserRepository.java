package com.example.company.device_library.repository;

import com.example.company.device_library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.lastName = ?1")
    Collection<User> findUsersByLastName(String userLastName);

    @Query("select u from User u where u.email = ?1")
    Optional<User> findUserByUsernameAsEmail(String email);

    Optional<User> findUserById(long l);

}
