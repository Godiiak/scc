package com.currencyconverter.scc.repositories;

import com.currencyconverter.scc.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String name);
    boolean existsByUsername(String name);
    boolean existsByEmail(String email);
}
