package com.jcgfdev.deliveryapp.business.services.impl;

import com.jcgfdev.deliveryapp.business.entities.ProductType;
import com.jcgfdev.deliveryapp.business.entities.dto.ProductTypeDTO;
import com.jcgfdev.deliveryapp.business.payloads.requests.ProductTypeRequest;
import com.jcgfdev.deliveryapp.business.repositories.ProductTypeRepository;
import com.jcgfdev.deliveryapp.business.services.ProductTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeRepository productTypeRepository;

    private final ModelMapper modelMapper;

    public ProductTypeServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductTypeDTO> findAll() {
        return productTypeRepository.findAll()
                .stream()
                .map(productType ->
                        modelMapper
                                .map(productType, ProductTypeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductTypeDTO findById(Long id) {
        Optional<ProductType> optionalProductType = productTypeRepository.findById(id);
        return optionalProductType
                .map(productType -> modelMapper
                        .map(productType, ProductTypeDTO.class))
                .orElseThrow(() -> new IllegalStateException("Not found: productType is not found."));
    }

    @Override
    public ProductTypeDTO save(ProductTypeRequest productTypeRequest) {
        ProductType productType = new ProductType();
        productType.setDetails(productTypeRequest.getDetails());
        return modelMapper.map(productTypeRepository.save(productType), ProductTypeDTO.class);
    }
}
