package com.jcgfdev.deliveryapp.security.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponse {
    private String token;
    private String email;
    List<String> roles;
}
