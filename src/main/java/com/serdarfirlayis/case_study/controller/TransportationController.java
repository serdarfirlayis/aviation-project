package com.serdarfirlayis.case_study.controller;

import com.serdarfirlayis.case_study.model.GenericResponse;
import com.serdarfirlayis.case_study.model.request.TransportationRequest;
import com.serdarfirlayis.case_study.model.response.TransportationResponse;
import com.serdarfirlayis.case_study.service.TransportationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transportations")
@RequiredArgsConstructor
public class TransportationController {

    private final TransportationService transportationService;

    @Operation(summary = "Create a transportation", description = "Create a transportation with the given request body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transportation created successfully",
                    content = @Content(schema = @Schema(implementation = GenericResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = GenericResponse.class)))
    })
    @PostMapping
    public ResponseEntity<GenericResponse<TransportationResponse>> createTransportation(@RequestBody TransportationRequest dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("custom-header", "custom-value")
                .body(GenericResponse.success(transportationService.createTransportation(dto),
                        "Transportation created successfully"));
    }

    @Operation(summary = "Get all transportations", description = "Get all transportations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transportations retrieved successfully",
                    content = @Content(schema = @Schema(implementation = GenericResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = GenericResponse.class)))
    })
    @GetMapping
    public ResponseEntity<GenericResponse<List<TransportationResponse>>> getAllTransportations() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("custom-header", "custom-value")
                .body(GenericResponse.success(transportationService.getAllTransportations(),
                        "Transportations retrieved successfully"));
    }

    @Operation(summary = "Get a transportation by id", description = "Get a transportation by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transportation retrieved successfully",
                    content = @Content(schema = @Schema(implementation = GenericResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = GenericResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<TransportationResponse>> getTransportationById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("custom-header", "custom-value")
                .body(GenericResponse.success(transportationService.getTransportationById(id),
                        "Transportation retrieved successfully"));
    }

    @Operation(summary = "Update a transportation", description = "Update a transportation with the given request body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transportation updated successfully",
                    content = @Content(schema = @Schema(implementation = GenericResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = GenericResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<TransportationResponse>> updateTransportation(@PathVariable Long id, @RequestBody TransportationRequest dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("custom-header", "custom-value")
                .body(GenericResponse.success(transportationService.updateTransportation(id, dto),
                        "Transportation updated successfully"));
    }

    @Operation(summary = "Delete a transportation", description = "Delete a transportation by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transportation deleted successfully",
                    content = @Content(schema = @Schema(implementation = GenericResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = GenericResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Void>> deleteTransportation(@PathVariable Long id) {
        transportationService.deleteTransportation(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("custom-header", "custom-value")
                .body(GenericResponse.success(null, "Transportation deleted successfully"));
    }
}
