package com.jcgfdev.deliveryapp.security.repositories;


import com.jcgfdev.deliveryapp.security.entities.Role;
import com.jcgfdev.deliveryapp.security.roles.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles role);
}
