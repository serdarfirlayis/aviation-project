package com.serdarfirlayis.case_study.controller;

import com.serdarfirlayis.case_study.entity.Transportation;
import com.serdarfirlayis.case_study.model.GenericResponse;
import com.serdarfirlayis.case_study.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @GetMapping("/{originId}/{destinationId}")
    public ResponseEntity<GenericResponse<List<List<Transportation>>>> getRoutes(
            @PathVariable Long originId,
            @PathVariable Long destinationId) {

        List<List<Transportation>> routes = routeService.findRoutes(originId, destinationId);

        return ResponseEntity.ok(GenericResponse.success(routes, "Valid routes retrieved"));
    }
}
