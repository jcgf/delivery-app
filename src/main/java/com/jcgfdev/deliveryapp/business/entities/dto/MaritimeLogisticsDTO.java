package com.jcgfdev.deliveryapp.business.entities.dto;

import com.jcgfdev.deliveryapp.business.entities.DeliveryWarehouse;
import com.jcgfdev.deliveryapp.business.entities.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaritimeLogisticsDTO {
    private Long id;
    private ProductType productType;
    private Integer quantity;
    private LocalDateTime registeredAt;
    private LocalDateTime deliveredAt;
    private DeliveryWarehouse deliveryWarehouse;
    private String fleetPlate;
    private String guideNumber;
}
