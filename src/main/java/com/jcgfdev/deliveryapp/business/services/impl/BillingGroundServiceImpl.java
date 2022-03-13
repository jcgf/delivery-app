package com.jcgfdev.deliveryapp.business.services.impl;

import com.jcgfdev.deliveryapp.business.entities.BillingGround;
import com.jcgfdev.deliveryapp.business.entities.GroundLogistics;
import com.jcgfdev.deliveryapp.business.entities.dto.BillingGroundDTO;
import com.jcgfdev.deliveryapp.business.payloads.requests.BillingGroundRequest;
import com.jcgfdev.deliveryapp.business.repositories.BillingGroundRepository;
import com.jcgfdev.deliveryapp.business.repositories.GroundLogisticsRepository;
import com.jcgfdev.deliveryapp.business.services.BillingGroundService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillingGroundServiceImpl implements BillingGroundService {
    @Autowired
    private BillingGroundRepository billingGroundRepository;

    @Autowired
    private GroundLogisticsRepository groundLogisticsRepository;

    private final ModelMapper modelMapper;

    public BillingGroundServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BillingGroundDTO> findAll() {
        return billingGroundRepository.findAll()
                .stream()
                .map(billingGround -> modelMapper
                        .map(billingGround, BillingGroundDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BillingGroundDTO findById(Long id) {
        Optional<BillingGround> billingGroundOptional = billingGroundRepository.findById(id);
        return billingGroundOptional
                .map(billingGround -> modelMapper
                        .map(billingGround, BillingGroundDTO.class))
                .orElseThrow(() -> new IllegalStateException("Not found: billingGround not found"));
    }

    @Override
    public BillingGroundDTO save(BillingGroundRequest billingGroundRequest) {
        GroundLogistics groundLogistics = groundLogisticsRepository
                .findById(billingGroundRequest.getGroundLogisticsId())
                .orElseThrow(() -> new IllegalStateException("groundLogistics don't found"));
        BillingGround billingGround = new BillingGround();
        billingGround.setGroundLogistics(groundLogistics);
        billingGround.setNormalPrice(billingGroundRequest.getNormalPrice());
        if (groundLogistics.getQuantity() > 10) {
            billingGround.setDiscount(billingGroundRequest.getNormalPrice() * 0.05);
        } else {
            billingGround.setDiscount(0.0);
        }
        billingGround.setTotal(billingGroundRequest.getNormalPrice() - billingGround.getDiscount());
        return modelMapper.map(billingGroundRepository.save(billingGround), BillingGroundDTO.class);
    }
}
