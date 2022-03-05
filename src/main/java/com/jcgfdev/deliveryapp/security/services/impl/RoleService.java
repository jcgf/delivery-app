package com.jcgfdev.deliveryapp.security.services.impl;

import com.jcgfdev.deliveryapp.security.entities.Role;
import com.jcgfdev.deliveryapp.security.repositories.RoleRepository;
import com.jcgfdev.deliveryapp.security.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        Optional<Role> roles = roleRepository.findByName(role.getName());
        if (roles.isEmpty()) {
            return roleRepository.save(role);
        } else {
            return roles.get();
        }
    }
}
