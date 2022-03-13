package com.jcgfdev.deliveryapp.security.services;

import com.jcgfdev.deliveryapp.security.entities.ConfirmationPath;

public interface IConfirmationPathService {
    ConfirmationPath findById(Integer id);
}
