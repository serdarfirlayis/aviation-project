package com.serdarfirlayis.case_study.controller;

import com.serdarfirlayis.case_study.model.GenericResponse;
import com.serdarfirlayis.case_study.model.LocationDto;
import com.serdarfirlayis.case_study.model.request.LocationRequest;
import com.serdarfirlayis.case_study.model.response.LocationResponse;
import com.serdarfirlayis.case_study.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @Operation(summary = "Create a location", description = "Create a location with the given request body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Location created successfully",
                    content = @Content(schema = @Schema(implementation = LocationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = GenericResponse.class)))
    })
    @PostMapping
    public ResponseEntity<GenericResponse<LocationDto>> createLocation(@Valid @RequestBody LocationRequest dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("custom-header", "custom-value")
                .body(GenericResponse.success(locationService.createLocation(dto),
                        "Location created successfully"));
    }

    @Operation(summary = "Get all locations", description = "Get all locations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Locations retrieved successfully",
                    content = @Content(schema = @Schema(implementation = GenericResponse.class)))
    })
    @GetMapping
    public ResponseEntity<GenericResponse<LocationResponse>> getAllLocations() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("custom-header", "custom-value")
                .body(GenericResponse.success(locationService.getAllLocations(),
                        "Locations retrieved successfully"));
    }

    @Operation(summary = "Get a location by id", description = "Get a location by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location retrieved successfully",
                    content = @Content(schema = @Schema(implementation = LocationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = GenericResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<LocationDto>> getLocationById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("custom-header", "custom-value")
                .body(GenericResponse.success(locationService.getLocationById(id),
                        "Location retrieved successfully"));
    }

    @Operation(summary = "Update a location", description = "Update a location with the given request body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location updated successfully",
                    content = @Content(schema = @Schema(implementation = LocationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = GenericResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<LocationDto>> updateLocation(@PathVariable Long id, @Valid @RequestBody LocationRequest dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("custom-header", "custom-value")
                .body(GenericResponse.success(locationService.updateLocation(id, dto),
                        "Location updated successfully"));
    }

    @Operation(summary = "Delete a location", description = "Delete a location by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location deleted successfully",
                    content = @Content(schema = @Schema(implementation = GenericResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Void>> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("custom-header", "custom-value")
                .body(GenericResponse.success(null, "Location deleted successfully"));
    }
}
