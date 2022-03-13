package com.jcgfdev.deliveryapp.business.repositories;

import com.jcgfdev.deliveryapp.business.entities.DeliveryWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryWarehouseRepository extends JpaRepository<DeliveryWarehouse, Long> {
    Optional<DeliveryWarehouse> findById(Long id);
}
