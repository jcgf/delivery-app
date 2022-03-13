package com.jcgfdev.deliveryapp.business.entities.dto;

import com.jcgfdev.deliveryapp.business.entities.GroundLogistics;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillingGroundDTO {
    private Long id;
    private GroundLogistics groundLogistics;
    private Double normalPrice;
    private Double discount;
    private Double total;
}
