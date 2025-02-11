package com.serdarfirlayis.case_study.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationDto {
    private Long id;
    private String name;
    private String country;
    private String city;
    private String locationCode;
}
