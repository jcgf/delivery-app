package com.jcgfdev.deliveryapp.business.services.impl;

import com.jcgfdev.deliveryapp.business.entities.DeliveryWarehouse;
import com.jcgfdev.deliveryapp.business.entities.GroundLogistics;
import com.jcgfdev.deliveryapp.business.entities.ProductType;
import com.jcgfdev.deliveryapp.business.entities.dto.BillingGroundDTO;
import com.jcgfdev.deliveryapp.business.entities.dto.GroundLogisticsDTO;
import com.jcgfdev.deliveryapp.business.payloads.requests.BillingGroundRequest;
import com.jcgfdev.deliveryapp.business.payloads.requests.GroundLogisticsRequest;
import com.jcgfdev.deliveryapp.business.repositories.DeliveryWarehouseRepository;
import com.jcgfdev.deliveryapp.business.repositories.GroundLogisticsRepository;
import com.jcgfdev.deliveryapp.business.repositories.ProductTypeRepository;
import com.jcgfdev.deliveryapp.business.services.BillingGroundService;
import com.jcgfdev.deliveryapp.business.services.GroundLogisticsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroundLogisticsServiceImpl implements GroundLogisticsService {
    @Autowired
    private GroundLogisticsRepository groundLogisticsRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private DeliveryWarehouseRepository deliveryWarehouseRepository;

    @Autowired
    private BillingGroundService billingGroundService;

    private final ModelMapper modelMapper;

    public GroundLogisticsServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List< BillingGroundDTO> findAll() {
        return billingGroundService.findAll();
    }

    @Override
    public  BillingGroundDTO findById(Long id) {
        return billingGroundService.findById(id);
    }

    @Override
    public BillingGroundDTO save(GroundLogisticsRequest groundLogisticsRequest) {
        ProductType productType = productTypeRepository
                .findById(groundLogisticsRequest.getProductTypeId())
                .orElseThrow(() -> new IllegalStateException("productType don't exist"));
        DeliveryWarehouse deliveryWarehouse = deliveryWarehouseRepository
                .findById(groundLogisticsRequest.getDeliveryWarehouseId())
                .orElseThrow(() -> new IllegalStateException("deliveryWarehouse don't exist"));
        GroundLogistics groundLogistics = new GroundLogistics();
        groundLogistics.setProductType(productType);
        groundLogistics.setQuantity(groundLogisticsRequest.getQuantity());
        groundLogistics.setRegisteredAt(groundLogisticsRequest.getRegisteredAt());
        groundLogistics.setDeliveredAt(groundLogisticsRequest.getDeliveredAt());
        groundLogistics.setDeliveryWarehouse(deliveryWarehouse);
        groundLogistics.setVehiclePlate(groundLogisticsRequest.getVehiclePlate());
        groundLogistics.setGuideNumber(groundLogisticsRequest.getGuideNumber());
        GroundLogisticsDTO groundLogisticsDTO = modelMapper.map(groundLogisticsRepository.save(groundLogistics), GroundLogisticsDTO.class);
        BillingGroundRequest billingGroundRequest = new BillingGroundRequest();
        billingGroundRequest.setGroundLogisticsId(groundLogisticsDTO.getId());
        billingGroundRequest.setNormalPrice(groundLogisticsRequest.getNormalPrice());
        return modelMapper.map(billingGroundService.save(billingGroundRequest), BillingGroundDTO.class);
    }
}
