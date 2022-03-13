package com.jcgfdev.deliveryapp.business.payloads.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillingGroundRequest {
    private Long groundLogisticsId;
    private Double normalPrice;
}
