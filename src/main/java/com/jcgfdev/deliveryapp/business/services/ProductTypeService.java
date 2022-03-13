package com.jcgfdev.deliveryapp.business.services;

import com.jcgfdev.deliveryapp.business.entities.dto.ProductTypeDTO;
import com.jcgfdev.deliveryapp.business.payloads.requests.ProductTypeRequest;

import java.util.List;
import java.util.Optional;

public interface ProductTypeService {
    List<ProductTypeDTO> findAll();

    ProductTypeDTO findById(Long id);

    ProductTypeDTO save(ProductTypeRequest productTypeRequest);
}
