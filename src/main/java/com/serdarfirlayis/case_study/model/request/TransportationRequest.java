package com.serdarfirlayis.case_study.model.request;

import com.serdarfirlayis.case_study.model.TransportationType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransportationRequest {
    private Long originId;
    private Long destinationId;
    private TransportationType type;
}
