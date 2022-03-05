package com.jcgfdev.deliveryapp.security.services.impl;

import com.jcgfdev.deliveryapp.security.entities.ConfirmationUser;
import com.jcgfdev.deliveryapp.security.repositories.ConfirmationTokenRepository;
import com.jcgfdev.deliveryapp.security.services.IConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmationTokenService implements IConfirmationTokenService {
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public void saveConfirmationToken(ConfirmationUser confirmationUser) {
        confirmationTokenRepository.save(confirmationUser);
    }

    @Override
    public Optional<ConfirmationUser> findTokenByToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    @Override
    public int setTokenConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
