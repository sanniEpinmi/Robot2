package com.robot.spring.mssql.controller;

import com.robot.spring.mssql.model.Location;
import com.robot.spring.mssql.repository.LocationRepository;
import com.robot.spring.mssql.repository.RobotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LocationController {

    @Autowired
    LocationRepository locationRepository;




    @PutMapping("/survivorlocation/{id}")
    public ResponseEntity<Location> updateUser(@PathVariable(value = "id") Long id,  @RequestBody Location userDetails) {
        Location user = locationRepository.findFirstById(id);
        if(null == user) {
            return ResponseEntity.notFound().build();
        }
        Location updatedUser = locationRepository.save(userDetails);
        return ResponseEntity.ok(updatedUser);
    }


}
