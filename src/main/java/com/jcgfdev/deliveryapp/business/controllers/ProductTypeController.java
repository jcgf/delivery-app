package com.jcgfdev.deliveryapp.business.controllers;

import com.jcgfdev.deliveryapp.base.Constantes;
import com.jcgfdev.deliveryapp.base.dto.ResponseDTO;
import com.jcgfdev.deliveryapp.base.utils.ResponseDTOService;
import com.jcgfdev.deliveryapp.business.entities.dto.ProductTypeDTO;
import com.jcgfdev.deliveryapp.business.payloads.requests.ProductTypeRequest;
import com.jcgfdev.deliveryapp.business.services.ProductTypeService;
import com.jcgfdev.deliveryapp.security.payloads.responses.UserLoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/productType")
public class ProductTypeController {
    @Autowired
    private ResponseDTOService responseDTOService;
    @Autowired
    private ProductTypeService productTypeService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "data found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductTypeDTO.class))}),
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "productType list null",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @GetMapping("/")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<?> findAll() {
        List<ProductTypeDTO> productTypeDTOList = productTypeService.findAll();
        return responseDTOService.response(productTypeDTOList, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "data found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductTypeDTO.class))}),
            @ApiResponse(responseCode = "404", description = "productType not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @GetMapping("/search")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<?> findById(@RequestParam Long id) {
        ProductTypeDTO productTypeDTO = productTypeService.findById(id);
        return responseDTOService.response(productTypeDTO, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "productType created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductTypeDTO.class))}),
            @ApiResponse(responseCode = "400", description = "failed to create productType",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @PostMapping("/save")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<?> saveProductType(@Valid @RequestBody ProductTypeRequest productTypeRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseDTOService.response(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        } else {
            ProductTypeDTO productTypeDTO = productTypeService.save(productTypeRequest);
            return responseDTOService.response(productTypeDTO, HttpStatus.CREATED);
        }
    }
}
