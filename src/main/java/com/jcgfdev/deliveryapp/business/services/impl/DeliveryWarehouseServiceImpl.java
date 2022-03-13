package com.jcgfdev.deliveryapp.business.services.impl;

import com.jcgfdev.deliveryapp.business.entities.DeliveryWarehouse;
import com.jcgfdev.deliveryapp.business.entities.dto.DeliveryWarehouseDTO;
import com.jcgfdev.deliveryapp.business.payloads.requests.DeliveryWarehouseRequest;
import com.jcgfdev.deliveryapp.business.repositories.DeliveryWarehouseRepository;
import com.jcgfdev.deliveryapp.business.services.DeliveryWarehouseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeliveryWarehouseServiceImpl implements DeliveryWarehouseService {

    @Autowired
    private DeliveryWarehouseRepository deliveryWarehouseRepository;

    private final ModelMapper modelMapper;

    public DeliveryWarehouseServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DeliveryWarehouseDTO> findAll() {
        return deliveryWarehouseRepository.findAll()
                .stream()
                .map(deliveryWarehouse ->
                        modelMapper
                                .map(deliveryWarehouse, DeliveryWarehouseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DeliveryWarehouseDTO findById(Long id) {
        Optional<DeliveryWarehouse> deliveryWarehouseOptional = deliveryWarehouseRepository.findById(id);
        return deliveryWarehouseOptional
                .map(deliveryWarehouse -> modelMapper
                        .map(deliveryWarehouse, DeliveryWarehouseDTO.class))
                .orElseThrow(() -> new IllegalStateException("Not found: deliveryWarehouse is not found"));
    }

    @Override
    public DeliveryWarehouseDTO save(DeliveryWarehouseRequest deliveryWarehouseRequest) {
        DeliveryWarehouse deliveryWarehouse = new DeliveryWarehouse();
        deliveryWarehouse.setName(deliveryWarehouseRequest.getName());
        return modelMapper.map(deliveryWarehouseRepository.save(deliveryWarehouse), DeliveryWarehouseDTO.class);
    }
}
