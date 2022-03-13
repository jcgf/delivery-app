package com.jcgfdev.deliveryapp.business.services.impl;

import com.jcgfdev.deliveryapp.business.entities.DeliveryWarehouse;
import com.jcgfdev.deliveryapp.business.entities.MaritimeLogistics;
import com.jcgfdev.deliveryapp.business.entities.ProductType;
import com.jcgfdev.deliveryapp.business.entities.dto.BillingGroundDTO;
import com.jcgfdev.deliveryapp.business.entities.dto.BillingMaritimeDTO;
import com.jcgfdev.deliveryapp.business.entities.dto.MaritimeLogisticsDTO;
import com.jcgfdev.deliveryapp.business.payloads.requests.BillingMaritimeRequest;
import com.jcgfdev.deliveryapp.business.payloads.requests.MaritimeLogisticsRequest;
import com.jcgfdev.deliveryapp.business.repositories.DeliveryWarehouseRepository;
import com.jcgfdev.deliveryapp.business.repositories.MaritimeLogisticsRepository;
import com.jcgfdev.deliveryapp.business.repositories.ProductTypeRepository;
import com.jcgfdev.deliveryapp.business.services.BillingMaritimeService;
import com.jcgfdev.deliveryapp.business.services.MaritimeLogisticsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaritimeLogisticsServiceImpl implements MaritimeLogisticsService {

    @Autowired
    private MaritimeLogisticsRepository maritimeLogisticsRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private DeliveryWarehouseRepository deliveryWarehouseRepository;

    @Autowired
    private BillingMaritimeService billingMaritimeService;

    private final ModelMapper modelMapper;

    public MaritimeLogisticsServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BillingMaritimeDTO> findAll() {
        return billingMaritimeService.findAll();
    }

    @Override
    public  BillingMaritimeDTO findById(Long id) {
        return billingMaritimeService.findById(id);
    }

    @Override
    public BillingMaritimeDTO save(MaritimeLogisticsRequest maritimeLogisticsRequest) {
        ProductType productType = productTypeRepository
                .findById(maritimeLogisticsRequest.getProductTypeId())
                .orElseThrow(() -> new IllegalStateException("productType don't exist"));
        DeliveryWarehouse deliveryWarehouse = deliveryWarehouseRepository
                .findById(maritimeLogisticsRequest.getDeliveryWarehouseId())
                .orElseThrow(() -> new IllegalStateException("deliveryWarehouse don't exist"));
        MaritimeLogistics groundLogistics = new MaritimeLogistics();
        groundLogistics.setProductType(productType);
        groundLogistics.setQuantity(maritimeLogisticsRequest.getQuantity());
        groundLogistics.setRegisteredAt(maritimeLogisticsRequest.getRegisteredAt());
        groundLogistics.setDeliveredAt(maritimeLogisticsRequest.getDeliveredAt());
        groundLogistics.setDeliveryWarehouse(deliveryWarehouse);
        groundLogistics.setFleetPlate(maritimeLogisticsRequest.getFleetPlate());
        groundLogistics.setGuideNumber(maritimeLogisticsRequest.getGuideNumber());
        MaritimeLogisticsDTO MaritimeLogisticsDTO = modelMapper.map(maritimeLogisticsRepository.save(groundLogistics), MaritimeLogisticsDTO.class);
        BillingMaritimeRequest billingMaritimeRequest = new BillingMaritimeRequest();
        billingMaritimeRequest.setMaritimeLogisticsId(MaritimeLogisticsDTO.getId());
        billingMaritimeRequest.setNormalPrice(maritimeLogisticsRequest.getNormalPrice());
        return modelMapper.map(billingMaritimeService.save(billingMaritimeRequest), BillingMaritimeDTO.class);
    }
}
