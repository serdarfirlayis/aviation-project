package com.serdarfirlayis.case_study.model;

import com.serdarfirlayis.case_study.entity.Transportation;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Route {
    private List<Transportation> transportations;
    private String routeName;
    private RouteDetail routeDetail;
}
