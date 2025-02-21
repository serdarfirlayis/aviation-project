package com.serdarfirlayis.case_study.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationRequest {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Country cannot be blank")
    private String country;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "Location code cannot be blank")
    @Size(max = 10, message = "Location code must be at most 10 characters")
    private String locationCode;
}
