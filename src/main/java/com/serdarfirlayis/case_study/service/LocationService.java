package com.serdarfirlayis.case_study.service;

import com.serdarfirlayis.case_study.entity.Location;
import com.serdarfirlayis.case_study.exception.LocationNotFoundException;
import com.serdarfirlayis.case_study.model.LocationDto;
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

    public LocationDto createLocation(LocationRequest request) {
        Location location = Location.builder()
                .name(request.getName())
                .country(request.getCountry())
                .city(request.getCity())
                .locationCode(request.getLocationCode())
                .build();

        Location savedLocation = locationRepository.save(location);
        return mapToDto(savedLocation);
    }

    public LocationResponse getAllLocations() {
        List<LocationDto> locations = locationRepository.findAll().stream()
                .map(this::mapToDto)
                .toList();
        return LocationResponse.builder()
                .locations(locations)
                .build();
    }

    public LocationDto getLocationById(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException("Location not found"));
        return mapToDto(location);
    }

    public Location getLocationEntityById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException("Location not found"));
    }

    public LocationDto updateLocation(Long id, LocationRequest request) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException("Location not found"));

        location.setName(request.getName());
        location.setCountry(request.getCountry());
        location.setCity(request.getCity());
        location.setLocationCode(request.getLocationCode());

        Location updatedLocation = locationRepository.save(location);
        return mapToDto(updatedLocation);
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

    private LocationDto mapToDto(Location location) {
        return LocationDto.builder()
                .id(location.getId())
                .name(location.getName())
                .country(location.getCountry())
                .city(location.getCity())
                .locationCode(location.getLocationCode())
                .build();
    }
}
