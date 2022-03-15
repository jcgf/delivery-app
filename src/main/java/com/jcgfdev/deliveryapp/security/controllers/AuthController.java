package com.jcgfdev.deliveryapp.security.controllers;

import com.jcgfdev.deliveryapp.base.Constantes;
import com.jcgfdev.deliveryapp.base.dto.ResponseDTO;
import com.jcgfdev.deliveryapp.base.utils.ResponseDTOService;
import com.jcgfdev.deliveryapp.security.entities.User;
import com.jcgfdev.deliveryapp.security.payloads.requests.LoginRequest;
import com.jcgfdev.deliveryapp.security.payloads.requests.UserRequest;
import com.jcgfdev.deliveryapp.security.payloads.responses.UserLoginResponse;
import com.jcgfdev.deliveryapp.security.services.impl.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private ResponseDTOService responseDTOService;
    @Autowired
    private UserService userService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful login",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserLoginResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Failed to log in user",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @PostMapping("/loginUser")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseDTOService.response(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        } else {
            return responseDTOService.response(userService.loginUser(loginRequest), HttpStatus.OK);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Failed to create user",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserRequest userRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseDTOService.response(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        } else {
            return responseDTOService.response(userService.saveUser(userRequest), HttpStatus.CREATED);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User confirmed",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Failed to confirm user",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @PutMapping("/confirmToken")
    public ResponseEntity<?> confirmToken(@RequestParam("token") String token) {
        ResponseDTO<?> responseDTO;
        responseDTO = new ResponseDTO<>(Constantes.NO_ERROR, userService.confirmToken(token));
        return ResponseEntity.ok(responseDTO);
    }
}
