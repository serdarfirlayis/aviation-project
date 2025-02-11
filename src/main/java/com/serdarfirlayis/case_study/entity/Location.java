package com.serdarfirlayis.case_study.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "locations")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, unique = true, length = 10)
    private String locationCode;
}
