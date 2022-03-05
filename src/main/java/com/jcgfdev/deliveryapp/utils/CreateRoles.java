package com.jcgfdev.deliveryapp.utils;

import com.jcgfdev.deliveryapp.security.entities.Role;
import com.jcgfdev.deliveryapp.security.roles.Roles;
import com.jcgfdev.deliveryapp.security.services.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateRoles implements CommandLineRunner {
    @Autowired
    private RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        Role rolAdmin = new Role(Roles.ROLE_ADMIN);
        Role rolUser = new Role(Roles.ROLE_USER);
        roleService.saveRole(rolAdmin);
        roleService.saveRole(rolUser);
    }
}
