package com.jcgfdev.deliveryapp.business.payloads.requests;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ProductTypeRequest {
    @NonNull
    private String details;
}
