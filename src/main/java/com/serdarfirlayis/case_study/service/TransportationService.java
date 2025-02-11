package com.serdarfirlayis.case_study.service;

import com.serdarfirlayis.case_study.entity.Location;
import com.serdarfirlayis.case_study.entity.Transportation;
import com.serdarfirlayis.case_study.exception.TransportationNotFoundException;
import com.serdarfirlayis.case_study.model.request.TransportationRequest;
import com.serdarfirlayis.case_study.model.response.TransportationResponse;
import com.serdarfirlayis.case_study.repository.TransportationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransportationService {

    private final TransportationRepository transportationRepository;
    private final LocationService locationService;

    public TransportationResponse createTransportation(TransportationRequest request) {
        Location origin = locationService.getLocationEntityById(request.getOriginId());
        Location destination = locationService.getLocationEntityById(request.getDestinationId());

        Transportation transportation = Transportation.builder()
                .origin(origin)
                .destination(destination)
                .type(request.getType())
                .build();

        Transportation saved = transportationRepository.save(transportation);
        return mapToResponse(saved);
    }

    public List<TransportationResponse> getAllTransportations() {
        return transportationRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<Transportation> getAllTransportationsEntity() {
        return transportationRepository.findAll();
    }

    public TransportationResponse getTransportationById(Long id) {
        Transportation transportation = transportationRepository.findById(id)
                .orElseThrow(() -> new TransportationNotFoundException("Transportation not found"));
        return mapToResponse(transportation);
    }

    public TransportationResponse updateTransportation(Long id, TransportationRequest request) {
        Transportation transportation = transportationRepository.findById(id)
                .orElseThrow(() -> new TransportationNotFoundException("Transportation not found"));

        Location origin = locationService.getLocationEntityById(request.getOriginId());
        Location destination = locationService.getLocationEntityById((request.getDestinationId()));

        transportation.setOrigin(origin);
        transportation.setDestination(destination);
        transportation.setType(request.getType());

        Transportation updatedTransportation = transportationRepository.save(transportation);
        return mapToResponse(updatedTransportation);
    }

    public void deleteTransportation(Long id) {
        if (!transportationRepository.existsById(id)) {
            throw new TransportationNotFoundException("Transportation not found");
        }
        transportationRepository.deleteById(id);
    }

    private TransportationResponse mapToResponse(Transportation transportation) {
        return TransportationResponse.builder()
                .id(transportation.getId())
                .originName(transportation.getOrigin().getName())
                .destinationName(transportation.getDestination().getName())
                .type(transportation.getType())
                .build();
    }
}
