package com.jcgfdev.deliveryapp.business.services;

import com.jcgfdev.deliveryapp.business.entities.dto.BillingMaritimeDTO;
import com.jcgfdev.deliveryapp.business.payloads.requests.BillingMaritimeRequest;

import java.util.List;

public interface BillingMaritimeService {
    List<BillingMaritimeDTO> findAll();

    BillingMaritimeDTO findById(Long id);

    BillingMaritimeDTO save(BillingMaritimeRequest groundRequest);
}
