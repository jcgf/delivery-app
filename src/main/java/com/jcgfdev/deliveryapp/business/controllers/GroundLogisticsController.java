package com.jcgfdev.deliveryapp.business.controllers;

import com.jcgfdev.deliveryapp.base.utils.ResponseDTOService;
import com.jcgfdev.deliveryapp.business.entities.dto.BillingGroundDTO;
import com.jcgfdev.deliveryapp.business.entities.dto.GroundLogisticsDTO;
import com.jcgfdev.deliveryapp.business.payloads.requests.GroundLogisticsRequest;
import com.jcgfdev.deliveryapp.business.services.GroundLogisticsService;
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
@RequestMapping("/groundLogistics")
public class GroundLogisticsController {
    @Autowired
    private ResponseDTOService responseDTOService;
    @Autowired
    private GroundLogisticsService groundLogisticsService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "data found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GroundLogisticsDTO.class))}),
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "groundLogistics list null",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @GetMapping("/")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<?> findAll() {
        List<BillingGroundDTO> billingGroundDTOList = groundLogisticsService.findAll();
        return responseDTOService.response(billingGroundDTOList, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "data found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GroundLogisticsDTO.class))}),
            @ApiResponse(responseCode = "404", description = "groundLogistics not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @GetMapping("/search")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<?> findById(@RequestParam Long id) {
        BillingGroundDTO billingGroundDTO = groundLogisticsService.findById(id);
        return responseDTOService.response(billingGroundDTO, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "groundLogistic created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GroundLogisticsDTO.class))}),
            @ApiResponse(responseCode = "400", description = "failed to create groundLogistic",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @PostMapping("/save")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<?> saveGroundLogistic(@Valid @RequestBody GroundLogisticsRequest groundLogisticsRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseDTOService.response(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        } else {
            BillingGroundDTO billingGroundDTO = groundLogisticsService.save(groundLogisticsRequest);
            return responseDTOService.response(billingGroundDTO, HttpStatus.CREATED);
        }
    }
}
