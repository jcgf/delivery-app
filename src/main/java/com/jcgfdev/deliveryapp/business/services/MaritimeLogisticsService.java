package com.jcgfdev.deliveryapp.business.services;

import com.jcgfdev.deliveryapp.business.entities.dto.BillingMaritimeDTO;
import com.jcgfdev.deliveryapp.business.payloads.requests.MaritimeLogisticsRequest;

import java.util.List;

public interface MaritimeLogisticsService {
    List<BillingMaritimeDTO> findAll();

    BillingMaritimeDTO findById(Long id);

    BillingMaritimeDTO save(MaritimeLogisticsRequest maritimeLogisticsRequest);
}
