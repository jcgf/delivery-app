package com.jcgfdev.deliveryapp.security.services.impl;

import com.jcgfdev.deliveryapp.security.entities.ConfirmationPath;
import com.jcgfdev.deliveryapp.security.repositories.ConfirmationPathRepository;
import com.jcgfdev.deliveryapp.security.services.IConfirmationPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationPathService implements IConfirmationPathService {

    @Autowired
    private ConfirmationPathRepository confirmationPathRepository;

    @Override
    public ConfirmationPath findById(Integer id) {
        return confirmationPathRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException("don't found: confirmationPath don't exist"));
    }
}
