package com.serdarfirlayis.case_study.controller;

import com.serdarfirlayis.case_study.model.GenericResponse;
import com.serdarfirlayis.case_study.model.response.RouteResponse;
import com.serdarfirlayis.case_study.service.RouteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @Operation(summary = "Get routes", description = "Get routes between two locations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Routes created successfully",
                    content = @Content(schema = @Schema(implementation = GenericResponse.class)))
    })
    @GetMapping("/{originId}/{destinationId}")
    public ResponseEntity<GenericResponse<RouteResponse>> getRoutes(@PathVariable Long originId,
                                                                    @PathVariable Long destinationId) {
        RouteResponse routes = routeService.findRoutes(originId, destinationId);
        return ResponseEntity.ok(GenericResponse.success(routes, "Valid routes retrieved"));
    }
}
