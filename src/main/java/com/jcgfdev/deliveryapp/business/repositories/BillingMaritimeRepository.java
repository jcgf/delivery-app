package com.jcgfdev.deliveryapp.business.repositories;

import com.jcgfdev.deliveryapp.business.entities.BillingMaritime;
import com.jcgfdev.deliveryapp.business.entities.MaritimeLogistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillingMaritimeRepository extends JpaRepository<BillingMaritime, Long> {
    Optional<BillingMaritime> findById(Long id);

    Optional<BillingMaritime> findByMaritimeLogistics(MaritimeLogistics maritimeLogistics);
}
