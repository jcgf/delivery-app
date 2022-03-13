package com.jcgfdev.deliveryapp.business.payloads.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillingMaritimeRequest {
    private Long maritimeLogisticsId;
    private Double normalPrice;
}
