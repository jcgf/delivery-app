package com.jcgfdev.deliveryapp.business.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "billing_ground")
public class BillingGround {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "ground_logistics", referencedColumnName = "id")
    private GroundLogistics groundLogistics;

    @Basic(optional = false)
    private Double normalPrice;

    @Basic(optional = false)
    private Double discount;

    @Basic(optional = false)
    private Double total;
}
