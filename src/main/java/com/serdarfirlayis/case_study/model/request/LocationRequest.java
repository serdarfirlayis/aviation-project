package com.serdarfirlayis.case_study.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationRequest {
    private String name;
    private String country;
    private String city;
    private String locationCode;
}
