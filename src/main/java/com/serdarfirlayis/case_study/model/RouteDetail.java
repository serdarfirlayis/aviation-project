package com.serdarfirlayis.case_study.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RouteDetail {

    private List<String> stations;
    private List<TransportationType> transportTypes;
}
