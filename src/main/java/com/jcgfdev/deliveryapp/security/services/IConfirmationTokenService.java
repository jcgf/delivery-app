package com.jcgfdev.deliveryapp.security.services;


import com.jcgfdev.deliveryapp.security.entities.ConfirmationUser;

import java.util.Optional;

public interface IConfirmationTokenService {
    void saveConfirmationToken(ConfirmationUser confirmationUser);

    Optional<ConfirmationUser> findTokenByToken(String token);

    int setTokenConfirmedAt(String token);
}
