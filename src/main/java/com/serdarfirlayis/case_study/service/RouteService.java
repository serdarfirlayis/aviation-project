package com.serdarfirlayis.case_study.service;

import com.serdarfirlayis.case_study.entity.Transportation;
import com.serdarfirlayis.case_study.model.*;
import com.serdarfirlayis.case_study.model.response.RouteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final TransportationService transportationService;

    public RouteResponse findRoutes(Long originId, Long destinationId) {
        List<Transportation> allTransportations = transportationService.getAllTransportationsEntity();
        List<List<Transportation>> validRoutes = new ArrayList<>();

        for (Transportation t : allTransportations) {
            if (t.getOrigin().getId().equals(originId)) {
                List<Transportation> currentRoute = new ArrayList<>();
                currentRoute.add(t);
                findValidRoutes(t, destinationId, currentRoute, validRoutes, allTransportations);
            }
        }

        return RouteResponse.builder()
                .routes(convertToRouteList(validRoutes))
                .build();
    }

    private void findValidRoutes(
            Transportation current,
            Long destinationId,
            List<Transportation> currentRoute,
            List<List<Transportation>> validRoutes,
            List<Transportation> allTransportations
    ) {
        if (currentRoute.size() > 3) return;

        if (current.getDestination().getId().equals(destinationId)) {
            if (isValidRoute(currentRoute)) {
                validRoutes.add(new ArrayList<>(currentRoute));
            }
            return;
        }

        for (Transportation next : allTransportations) {
            if (current.getDestination().getId().equals(next.getOrigin().getId()) &&
                    !currentRoute.contains(next)) {
                currentRoute.add(next);
                findValidRoutes(next, destinationId, currentRoute, validRoutes, allTransportations);
                currentRoute.removeLast();
            }
        }
    }

    private boolean isValidRoute(List<Transportation> route) {
        if (route.isEmpty()) return false;

        boolean hasFlight = false;
        int flightCount = 0;
        int beforeFlightCount = 0;
        int afterFlightCount = 0;

        for (Transportation t : route) {
            if (t.getType() == TransportationType.FLIGHT) {
                hasFlight = true;
                flightCount++;
            } else {
                if (!hasFlight) beforeFlightCount++;
                else afterFlightCount++;
            }
        }

        return hasFlight &&
                flightCount == 1 &&
                beforeFlightCount <= 1 &&
                afterFlightCount <= 1;
    }

    private List<Route> convertToRouteList(List<List<Transportation>> validRoutes) {
        Map<String, List<TransportationGroup>> routeMap = new HashMap<>();

        for (List<Transportation> transportations : validRoutes) {
            List<String> stations = new ArrayList<>();
            List<TransportationType> transportTypes = new ArrayList<>();
            String viaAirport = "";

            stations.add(transportations.getFirst().getOrigin().getName());

            for (Transportation t : transportations) {
                transportTypes.add(t.getType());
                stations.add(t.getDestination().getName());

                if (t.getType() == TransportationType.FLIGHT) {
                    viaAirport = t.getOrigin().getName();
                }
            }

            String routeName = "Via " + viaAirport;

            RouteDetail routeDetail = RouteDetail.builder()
                    .stations(stations)
                    .transportTypes(transportTypes)
                    .build();

            TransportationGroup group = TransportationGroup.builder()
                    .transportations(transportations)
                    .routeDetail(routeDetail)
                    .build();

            routeMap.computeIfAbsent(routeName, k -> new ArrayList<>()).add(group);
        }

        return routeMap.entrySet().stream()
                .map(entry -> Route.builder()
                        .routeName(entry.getKey())
                        .transportationGroups(entry.getValue())
                        .build())
                .collect(Collectors.toList());
    }
}
