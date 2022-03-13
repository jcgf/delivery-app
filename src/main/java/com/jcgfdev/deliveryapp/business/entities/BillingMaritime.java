package com.jcgfdev.deliveryapp.business.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "billing_maritime")
public class BillingMaritime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "maritime_logistics", referencedColumnName = "id")
    private MaritimeLogistics maritimeLogistics;

    @Basic(optional = false)
    private Double normalPrice;

    @Basic(optional = false)
    private Double discount;

    @Basic(optional = false)
    private Double total;
}
