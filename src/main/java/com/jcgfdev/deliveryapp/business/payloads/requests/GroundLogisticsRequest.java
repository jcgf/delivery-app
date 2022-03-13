package com.jcgfdev.deliveryapp.business.payloads.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
public class GroundLogisticsRequest {

    private Long productTypeId;

    private Integer quantity;

    private LocalDateTime registeredAt;

    private LocalDateTime deliveredAt;

    private Long deliveryWarehouseId;

    @NotBlank(message = "vehiclePlate cannot be empty")
    @Pattern(regexp = "[a-zA-Z]{3}[0-9]{3}", message = "vehiclePlate don't valid")
    private String vehiclePlate;

    @Pattern(regexp = "[0-9]{10}", message = "must contain numeric characters")
    private String guideNumber;

    private Double normalPrice;
}
