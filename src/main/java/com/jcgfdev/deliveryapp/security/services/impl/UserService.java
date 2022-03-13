package com.jcgfdev.deliveryapp.security.services.impl;

import com.jcgfdev.deliveryapp.security.configs.JwtUtils;
import com.jcgfdev.deliveryapp.security.entities.ConfirmationPath;
import com.jcgfdev.deliveryapp.security.entities.ConfirmationUser;
import com.jcgfdev.deliveryapp.security.entities.Role;
import com.jcgfdev.deliveryapp.security.entities.User;
import com.jcgfdev.deliveryapp.security.models.UserDetailsModel;
import com.jcgfdev.deliveryapp.security.payloads.requests.LoginRequest;
import com.jcgfdev.deliveryapp.security.payloads.requests.UserRequest;
import com.jcgfdev.deliveryapp.security.payloads.responses.MessageResponse;
import com.jcgfdev.deliveryapp.security.payloads.responses.UserLoginResponse;
import com.jcgfdev.deliveryapp.security.repositories.RoleRepository;
import com.jcgfdev.deliveryapp.security.repositories.UserRepository;
import com.jcgfdev.deliveryapp.security.roles.Roles;
import com.jcgfdev.deliveryapp.security.services.IConfirmationPathService;
import com.jcgfdev.deliveryapp.security.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private BuildEmailService buildEmailService;
    @Autowired
    private IConfirmationPathService confirmationPathService;

    @Override
    public UserLoginResponse loginUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsModel userDetails = (UserDetailsModel) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        userLoginResponse.setToken(jwt);
        userLoginResponse.setEmail(userDetails.getEmail());
        userLoginResponse.setRoles(roles);
        return userLoginResponse;
    }

    @Override
    public User saveUser(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new IllegalStateException("Error: Email is already in use!");
        } else {
            // Create new user's account
            User user = new User(userRequest.getFirstName(),
                    userRequest.getLastName(),
                    userRequest.getEmail(),
                    encoder.encode(userRequest.getPassword()));
            Set<String> strRoles = userRequest.getRole();
            Set<Role> roles = new HashSet<>();
            if (strRoles == null) {
                Role userRole = roleRepository.findByName(Roles.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            Role adminRole = roleRepository.findByName(Roles.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(adminRole);
                            break;
                        default:
                            Role userRole = roleRepository.findByName(Roles.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(userRole);
                    }
                });
            }
            user.setRoles(roles);
            userRepository.save(user);
            String token = UUID.randomUUID().toString();
            ConfirmationUser confirmationUser = new ConfirmationUser();
            confirmationUser.setToken(token);
            confirmationUser.setCreatedAt(LocalDateTime.now());
            confirmationUser.setExpiresAt(LocalDateTime.now().plusMinutes(15));
            confirmationUser.setUser(user);
            confirmationTokenService.saveConfirmationToken(confirmationUser);
            ConfirmationPath confirmationPath = confirmationPathService.findById(1);
            String link = confirmationPath.getPath() + token;
            emailService.send(userRequest.getEmail(), buildEmailService.buildEmail(userRequest.getFirstName(), link));
            return user;
        }
    }

    @Override
    public String confirmToken(String token) {
        ConfirmationUser confirmationUser = confirmationTokenService
                .findTokenByToken(token).orElseThrow(() -> new IllegalStateException("token not found"));
        if (confirmationUser.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }
        LocalDateTime expiredAt = confirmationUser.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }
        int confirmedToken = confirmationTokenService.setTokenConfirmedAt(token);
        enableUserByEmail(confirmationUser.getUser().getEmail());
        if (confirmedToken == 1) {
            return confirmationUser.getUser().getEmail() + " has been confirmed";
        } else {
            return confirmationUser.getUser().getEmail() + " don't confirmed";
        }
    }

    @Override
    public void enableUserByEmail(String email) {
        userRepository.enableAppUser(email);
    }
}
