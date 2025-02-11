package com.serdarfirlayis.case_study.controller;

import com.serdarfirlayis.case_study.model.GenericResponse;
import com.serdarfirlayis.case_study.model.request.LocationRequest;
import com.serdarfirlayis.case_study.model.response.LocationResponse;
import com.serdarfirlayis.case_study.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    // TODO: @Valid annotation can be used to validate the request body
    @PostMapping
    public ResponseEntity<GenericResponse<LocationResponse>> createLocation(@RequestBody LocationRequest dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("custom-header", "custom-value")
                .body(GenericResponse.success(locationService.createLocation(dto),
                        "Location created successfully"));
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<LocationResponse>>> getAllLocations() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("custom-header", "custom-value")
                .body(GenericResponse.success(locationService.getAllLocations(),
                        "Locations retrieved successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<LocationResponse>> getLocationById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("custom-header", "custom-value")
                .body(GenericResponse.success(locationService.getLocationById(id),
                        "Location retrieved successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<LocationResponse>> updateLocation(@PathVariable Long id, @RequestBody LocationRequest dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("custom-header", "custom-value")
                .body(GenericResponse.success(locationService.updateLocation(id, dto),
                        "Location updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Void>> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("custom-header", "custom-value")
                .body(GenericResponse.success(null, "Location deleted successfully"));
    }
}
