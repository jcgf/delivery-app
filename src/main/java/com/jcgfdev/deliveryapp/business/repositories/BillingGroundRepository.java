package com.jcgfdev.deliveryapp.business.repositories;

import com.jcgfdev.deliveryapp.business.entities.BillingGround;
import com.jcgfdev.deliveryapp.business.entities.GroundLogistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillingGroundRepository extends JpaRepository<BillingGround,Long> {
    Optional<BillingGround> findById(Long id);

    Optional<BillingGround> findByGroundLogistics(GroundLogistics groundLogistics);
}
