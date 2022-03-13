package com.jcgfdev.deliveryapp.business.services.impl;

import com.jcgfdev.deliveryapp.business.entities.BillingMaritime;
import com.jcgfdev.deliveryapp.business.entities.MaritimeLogistics;
import com.jcgfdev.deliveryapp.business.entities.dto.BillingMaritimeDTO;
import com.jcgfdev.deliveryapp.business.payloads.requests.BillingMaritimeRequest;
import com.jcgfdev.deliveryapp.business.repositories.BillingMaritimeRepository;
import com.jcgfdev.deliveryapp.business.repositories.MaritimeLogisticsRepository;
import com.jcgfdev.deliveryapp.business.services.BillingMaritimeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillingMaritimeServiceImpl implements BillingMaritimeService {
    @Autowired
    private BillingMaritimeRepository billingMaritimeRepository;

    @Autowired
    private MaritimeLogisticsRepository maritimeLogisticsRepository;

    private final ModelMapper modelMapper;

    public BillingMaritimeServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BillingMaritimeDTO> findAll() {
        return billingMaritimeRepository.findAll()
                .stream()
                .map(billingMaritime -> modelMapper
                        .map(billingMaritime, BillingMaritimeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BillingMaritimeDTO findById(Long id) {
        Optional<BillingMaritime> billingMaritimeOptional = billingMaritimeRepository.findById(id);
        return billingMaritimeOptional
                .map(billingMaritime -> modelMapper
                        .map(billingMaritime, BillingMaritimeDTO.class))
                .orElseThrow(() -> new IllegalStateException("Not found: billingMaritime not found"));
    }

    @Override
    public BillingMaritimeDTO save(BillingMaritimeRequest billingMaritimeRequest) {
        MaritimeLogistics maritimeLogistics = maritimeLogisticsRepository
                .findById(billingMaritimeRequest.getMaritimeLogisticsId())
                .orElseThrow(() -> new IllegalStateException("maritimeLogistics don't found"));
        BillingMaritime billingMaritime = new BillingMaritime();
        billingMaritime.setMaritimeLogistics(maritimeLogistics);
        billingMaritime.setNormalPrice(billingMaritimeRequest.getNormalPrice());
        if (maritimeLogistics.getQuantity() > 10) {
            billingMaritime.setDiscount(billingMaritimeRequest.getNormalPrice() * 0.03);
        } else {
            billingMaritime.setDiscount(0.0);
        }
        billingMaritime.setTotal(billingMaritimeRequest.getNormalPrice() - billingMaritime.getDiscount());
        return modelMapper.map(billingMaritimeRepository.save(billingMaritime), BillingMaritimeDTO.class);
    }
}
