package com.jcgfdev.deliveryapp.business.services;

import com.jcgfdev.deliveryapp.business.entities.dto.BillingGroundDTO;
import com.jcgfdev.deliveryapp.business.payloads.requests.GroundLogisticsRequest;

import java.util.List;

public interface GroundLogisticsService {
    List< BillingGroundDTO> findAll();

    BillingGroundDTO findById(Long id);

    BillingGroundDTO save(GroundLogisticsRequest groundLogisticsRequest);
}