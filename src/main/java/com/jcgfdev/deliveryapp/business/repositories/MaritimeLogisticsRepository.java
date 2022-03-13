package com.jcgfdev.deliveryapp.business.repositories;

import com.jcgfdev.deliveryapp.business.entities.MaritimeLogistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaritimeLogisticsRepository extends JpaRepository<MaritimeLogistics, Long> {
    Optional<MaritimeLogistics> findById(Long id);
}
