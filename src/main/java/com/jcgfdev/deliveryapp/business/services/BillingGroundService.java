package com.jcgfdev.deliveryapp.business.services;

import com.jcgfdev.deliveryapp.business.entities.dto.BillingGroundDTO;
import com.jcgfdev.deliveryapp.business.payloads.requests.BillingGroundRequest;

import java.util.List;

public interface BillingGroundService {
    List<BillingGroundDTO> findAll();

    BillingGroundDTO findById(Long id);

    BillingGroundDTO save(BillingGroundRequest groundRequest);
}
