package com.serdarfirlayis.case_study.entity;

import com.serdarfirlayis.case_study.model.TransportationType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "transportations")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transportation extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "origin_location_id", nullable = false)
    private Location origin;

    @ManyToOne
    @JoinColumn(name = "destination_location_id", nullable = false)
    private Location destination;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransportationType type;
}
