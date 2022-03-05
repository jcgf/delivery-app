package com.jcgfdev.deliveryapp.security.entities;

import com.jcgfdev.deliveryapp.security.roles.Roles;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Roles name;
}
