package com.jcgfdev.deliveryapp.business.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryWarehouseDTO {
    private Long id;
    private String name;
}
