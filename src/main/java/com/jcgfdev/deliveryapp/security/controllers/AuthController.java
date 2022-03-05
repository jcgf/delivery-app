package com.jcgfdev.deliveryapp.security.controllers;

import com.jcgfdev.deliveryapp.base.Constantes;
import com.jcgfdev.deliveryapp.base.dto.ResponseDTO;
import com.jcgfdev.deliveryapp.security.entities.User;
import com.jcgfdev.deliveryapp.security.payloads.requests.LoginRequest;
import com.jcgfdev.deliveryapp.security.payloads.requests.UserRequest;
import com.jcgfdev.deliveryapp.security.services.impl.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/loginUser")
    public ResponseEntity<ResponseDTO<?>> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        ResponseDTO<?> responseDTO;
        responseDTO = new ResponseDTO<>(Constantes.NO_ERROR, userService.loginUser(loginRequest));
        return ResponseEntity.ok(responseDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario creado exitosamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "500", description = "Error al crear usuario",
                    content = @Content)})
    @PostMapping("/saveUser")
    public ResponseEntity<ResponseDTO<?>> saveUser(@Valid @RequestBody UserRequest userRequest) {
        ResponseDTO<?> responseDTO;
        responseDTO = new ResponseDTO<>(Constantes.NO_ERROR, userService.saveUser(userRequest));
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/confirmToken")
    public ResponseEntity<ResponseDTO<?>> confirmToken(@RequestParam("token") String token) {
        ResponseDTO<?> responseDTO;
        responseDTO = new ResponseDTO<>(Constantes.NO_ERROR, userService.confirmToken(token));
        return ResponseEntity.ok(responseDTO);
    }
}
