package com.jcgfdev.deliveryapp.security.services;


import com.jcgfdev.deliveryapp.security.entities.User;
import com.jcgfdev.deliveryapp.security.payloads.requests.LoginRequest;
import com.jcgfdev.deliveryapp.security.payloads.requests.UserRequest;
import com.jcgfdev.deliveryapp.security.payloads.responses.UserLoginResponse;

public interface IUserService {
    UserLoginResponse loginUser(LoginRequest loginRequest);

    User saveUser(UserRequest userRequest);

    String confirmToken(String token);

    void enableUserByEmail(String email);
}
