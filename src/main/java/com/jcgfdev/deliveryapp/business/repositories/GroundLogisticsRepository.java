package com.jcgfdev.deliveryapp.business.repositories;

import com.jcgfdev.deliveryapp.business.entities.GroundLogistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroundLogisticsRepository extends JpaRepository<GroundLogistics, Long> {
    Optional<GroundLogistics> findById(Long id);
}
