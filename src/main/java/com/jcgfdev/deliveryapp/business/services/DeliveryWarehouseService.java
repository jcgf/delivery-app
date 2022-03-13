package com.jcgfdev.deliveryapp.business.services;

import com.jcgfdev.deliveryapp.business.entities.dto.DeliveryWarehouseDTO;
import com.jcgfdev.deliveryapp.business.payloads.requests.DeliveryWarehouseRequest;

import java.util.List;

public interface DeliveryWarehouseService {
    List<DeliveryWarehouseDTO> findAll();

    DeliveryWarehouseDTO findById(Long id);

    DeliveryWarehouseDTO save(DeliveryWarehouseRequest deliveryWarehouseRequest);
}
