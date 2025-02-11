package com.serdarfirlayis.case_study.service;

import com.serdarfirlayis.case_study.entity.Location;
import com.serdarfirlayis.case_study.exception.LocationNotFoundException;
import com.serdarfirlayis.case_study.model.request.LocationRequest;
import com.serdarfirlayis.case_study.model.response.LocationResponse;
import com.serdarfirlayis.case_study.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationResponse createLocation(LocationRequest request) {
        Location location = Location.builder()
                .name(request.getName())
                .country(request.getCountry())
                .city(request.getCity())
                .locationCode(request.getLocationCode())
                .build();

        Location savedLocation = locationRepository.save(location);
        return mapToResponse(savedLocation);
    }

    public List<LocationResponse> getAllLocations() {
        return locationRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public LocationResponse getLocationById(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException("Location not found"));
        return mapToResponse(location);
    }

    public Location getLocationEntityById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException("Location not found"));
    }

    public LocationResponse updateLocation(Long id, LocationRequest request) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException("Location not found"));

        location.setName(request.getName());
        location.setCountry(request.getCountry());
        location.setCity(request.getCity());
        location.setLocationCode(request.getLocationCode());

        Location updatedLocation = locationRepository.save(location);
        return mapToResponse(updatedLocation);
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

    private LocationResponse mapToResponse(Location location) {
        return LocationResponse.builder()
                .id(location.getId())
                .name(location.getName())
                .country(location.getCountry())
                .city(location.getCity())
                .locationCode(location.getLocationCode())
                .build();
    }
}
