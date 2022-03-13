package com.jcgfdev.deliveryapp.business.entities.dto;

import com.jcgfdev.deliveryapp.business.entities.MaritimeLogistics;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillingMaritimeDTO {
    private Long id;
    private MaritimeLogistics maritimeLogistics;
    private Double normalPrice;
    private Double discount;
    private Double total;
}
