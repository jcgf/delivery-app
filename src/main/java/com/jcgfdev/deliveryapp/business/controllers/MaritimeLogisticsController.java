package com.jcgfdev.deliveryapp.business.controllers;

import com.jcgfdev.deliveryapp.base.utils.ResponseDTOService;
import com.jcgfdev.deliveryapp.business.entities.dto.BillingMaritimeDTO;
import com.jcgfdev.deliveryapp.business.entities.dto.MaritimeLogisticsDTO;
import com.jcgfdev.deliveryapp.business.payloads.requests.MaritimeLogisticsRequest;
import com.jcgfdev.deliveryapp.business.services.MaritimeLogisticsService;
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
@RequestMapping("/maritimeLogistics")
public class MaritimeLogisticsController {
    @Autowired
    private ResponseDTOService responseDTOService;
    @Autowired
    private MaritimeLogisticsService maritimeLogisticsService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "data found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MaritimeLogisticsDTO.class))}),
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "maritimeLogistics list null",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @GetMapping("/")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<?> findAll() {
        List<BillingMaritimeDTO> billingMaritimeDTOList = maritimeLogisticsService.findAll();
        return responseDTOService.response(billingMaritimeDTOList, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "data found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MaritimeLogisticsDTO.class))}),
            @ApiResponse(responseCode = "404", description = "maritimeLogistics not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @GetMapping("/search")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<?> findById(@RequestParam Long id) {
        BillingMaritimeDTO billingMaritimeDTO = maritimeLogisticsService.findById(id);
        return responseDTOService.response(billingMaritimeDTO, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "maritimeLogistic created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MaritimeLogisticsDTO.class))}),
            @ApiResponse(responseCode = "400", description = "failed to create maritimeLogistic",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @PostMapping("/save")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<?> saveMaritimeLogistic(@Valid @RequestBody MaritimeLogisticsRequest maritimeLogisticsRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseDTOService.response(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        } else {
            BillingMaritimeDTO billingMaritimeDTO = maritimeLogisticsService.save(maritimeLogisticsRequest);
            return responseDTOService.response(billingMaritimeDTO, HttpStatus.CREATED);
        }
    }
}
