package com.serdarfirlayis.case_study.model;

import com.serdarfirlayis.case_study.entity.Transportation;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TransportationGroup {
    private List<Transportation> transportations;
    private RouteDetail routeDetail;
}
