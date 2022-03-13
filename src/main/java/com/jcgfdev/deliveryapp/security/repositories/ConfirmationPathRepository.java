package com.jcgfdev.deliveryapp.security.repositories;

import com.jcgfdev.deliveryapp.security.entities.ConfirmationPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationPathRepository extends JpaRepository<ConfirmationPath, Long> {
    Optional<ConfirmationPath> findById(Long id);
}
