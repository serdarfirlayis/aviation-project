package com.serdarfirlayis.case_study.model.response;

import com.serdarfirlayis.case_study.model.Route;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RouteResponse {
    private List<Route> routes;
}
