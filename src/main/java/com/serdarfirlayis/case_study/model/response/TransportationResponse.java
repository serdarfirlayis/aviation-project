package com.serdarfirlayis.case_study.model.response;

import com.serdarfirlayis.case_study.model.TransportationType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransportationResponse {
    private Long id;
    private String originName;
    private String destinationName;
    private TransportationType type;
}
