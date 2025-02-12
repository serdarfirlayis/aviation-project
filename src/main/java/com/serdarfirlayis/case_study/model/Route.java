package com.serdarfirlayis.case_study.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Route {
    private List<TransportationGroup> transportationGroups;
    private String routeName;
}
