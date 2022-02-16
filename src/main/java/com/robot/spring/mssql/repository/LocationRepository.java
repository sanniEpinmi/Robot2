package com.robot.spring.mssql.repository;

import com.robot.spring.mssql.model.Location;
import com.robot.spring.mssql.model.Survivor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Locale;

public interface LocationRepository extends JpaRepository<Location, Long> {

    Location findFirstById(Long id);
}