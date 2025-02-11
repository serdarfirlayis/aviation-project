package com.serdarfirlayis.case_study.controller;

import com.serdarfirlayis.case_study.model.GenericResponse;
import com.serdarfirlayis.case_study.model.request.TransportationRequest;
import com.serdarfirlayis.case_study.model.response.TransportationResponse;
import com.serdarfirlayis.case_study.service.TransportationService;
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

    @PostMapping
    public ResponseEntity<GenericResponse<TransportationResponse>> createTransportation(@RequestBody TransportationRequest dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("custom-header", "custom-value")
                .body(GenericResponse.success(transportationService.createTransportation(dto),
                        "Transportation created successfully"));
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<TransportationResponse>>> getAllTransportations() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("custom-header", "custom-value")
                .body(GenericResponse.success(transportationService.getAllTransportations(),
                        "Transportations retrieved successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<TransportationResponse>> getTransportationById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("custom-header", "custom-value")
                .body(GenericResponse.success(transportationService.getTransportationById(id),
                        "Transportation retrieved successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<TransportationResponse>> updateTransportation(@PathVariable Long id, @RequestBody TransportationRequest dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("custom-header", "custom-value")
                .body(GenericResponse.success(transportationService.updateTransportation(id, dto),
                        "Transportation updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Void>> deleteTransportation(@PathVariable Long id) {
        transportationService.deleteTransportation(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("custom-header", "custom-value")
                .body(GenericResponse.success(null, "Transportation deleted successfully"));
    }
}
