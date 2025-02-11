package com.serdarfirlayis.case_study.service;

import com.serdarfirlayis.case_study.entity.Transportation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.serdarfirlayis.case_study.model.TransportationType;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final TransportationService transportationService;

    public List<List<Transportation>> findRoutes(Long originId, Long destinationId) {
        List<Transportation> allTransportations = transportationService.getAllTransportationsEntity();
        List<List<Transportation>> validRoutes = new ArrayList<>();

        for (Transportation t : allTransportations) {
            if (t.getOrigin().getId().equals(originId)) {
                List<Transportation> currentRoute = new ArrayList<>();
                currentRoute.add(t);
                findValidRoutes(t, destinationId, currentRoute, validRoutes, allTransportations);
            }
        }

        return validRoutes;
    }

    private void findValidRoutes(
        Transportation current,
        Long destinationId,
        List<Transportation> currentRoute,
        List<List<Transportation>> validRoutes,
        List<Transportation> allTransportations
    ) {
        // Rota uzunluğu 3'ü geçerse dur
        if (currentRoute.size() > 3) return;

        // Eğer şu anki taşıma hedefe ulaştıysa, rotayı kaydet ve dur
        if (current.getDestination().getId().equals(destinationId)) {
            if (isValidRoute(currentRoute)) {
                validRoutes.add(new ArrayList<>(currentRoute));
            }
            return;
        }

        // Bağlı taşımaları bul ve devam et
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
        int flightCount = 0, beforeFlightCount = 0, afterFlightCount = 0;

        for (Transportation t : route) {
            if (t.getType() == TransportationType.FLIGHT) {
                hasFlight = true;
                flightCount++;
            } else {
                if (!hasFlight) beforeFlightCount++;
                else afterFlightCount++;
            }
        }

        // Kuralları kontrol et
        return hasFlight &&
               flightCount == 1 &&
               beforeFlightCount <= 1 &&
               afterFlightCount <= 1;
    }
}
