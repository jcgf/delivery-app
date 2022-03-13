package com.jcgfdev.deliveryapp.business.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "ground_logistics")
public class GroundLogistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_type", referencedColumnName = "id")
    private ProductType productType;

    @Basic(optional = false)
    private Integer quantity;

    @Basic(optional = false)
    private LocalDateTime registeredAt;

    @Basic(optional = false)
    private LocalDateTime deliveredAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "delivery_warehouse", referencedColumnName = "id")
    private DeliveryWarehouse deliveryWarehouse;

    @Basic(optional = false)
    private String vehiclePlate;

    @Basic(optional = false)
    private String guideNumber;
}
