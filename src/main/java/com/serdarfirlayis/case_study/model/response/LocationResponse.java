package com.serdarfirlayis.case_study.model.response;

import com.serdarfirlayis.case_study.model.LocationDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LocationResponse {
    private List<LocationDto> locations;
}
