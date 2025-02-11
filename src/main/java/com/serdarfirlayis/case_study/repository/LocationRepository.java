package com.serdarfirlayis.case_study.repository;

import com.serdarfirlayis.case_study.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
