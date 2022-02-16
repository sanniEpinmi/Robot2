package com.robot.spring.mssql.controller;

import com.robot.spring.mssql.model.Location;
import com.robot.spring.mssql.model.RobotResponse;
import com.robot.spring.mssql.model.Survivor;
import com.robot.spring.mssql.repository.RobotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class RobotController {




    @Autowired
    RobotRepository robotRepository;


    @GetMapping("/survivors")
    public ResponseEntity<List<Survivor>> getAllSurvivor() {
        List<Survivor> fr= (List<Survivor>) robotRepository.findAll();
        if(fr.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        else{
            return ResponseEntity.ok(fr);
        }
    }


    @GetMapping("/infectedsurvivors")
    public ResponseEntity<List<Survivor>> getAllInfectedSurvivor() {
        final int num= 2;
        List<Survivor> fr= (List<Survivor>) robotRepository.findSurvivorByInfectionReportIsGreaterThan(num);
                //.filter(account1 -> account1.getAccountNumber().equals(fundsTransferDTO.getSourceAccountNumber()))
        //                                .findFirst()
        if(fr.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        else{
            return ResponseEntity.ok(fr);
        }
    }


    @GetMapping("/noninfectedsurvivors")
    public ResponseEntity<List<Survivor>> getAllnoninfectedSurvivor() {
       // List<Survivor> fr= (List<Survivor>) robotRepository.findAll();
        final int num= 3;
        List<Survivor> fr= (List<Survivor>) robotRepository.findSurvivorByInfectionReportIsLessThan(num);
      //  fr.stream().filter(f-> (!(f.getInfectionstatus().equalsIgnoreCase("INFECTED"))));

        if(fr.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        else{
            return ResponseEntity.ok(fr);
        }
    }


    @GetMapping("/noninfectedsurvivorsrate")
    public ResponseEntity<HashMap<String,Object>> noninfectedsurvivorsrate() {
        List<Survivor> allsurv= (List<Survivor>) robotRepository.findAll();
        final int num= 3;
        List<Survivor> fr= (List<Survivor>) robotRepository.findSurvivorByInfectionReportIsLessThan(num);

        int nonInfect  = fr.size();
double rate =  (new Double(nonInfect)) * (allsurv.size())/100 ;

        HashMap<String,Object> sh = new HashMap<>();
        sh.put("noninfectedrate" ,rate);
        sh.put("status" ,"non infected");
        if(fr.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        else{
            return ResponseEntity.ok(sh);
        }
    }


    @GetMapping("/infectedsurvivorsrate")
    public ResponseEntity<HashMap<String,Object>>infectedsurvivorsrate() {
        List<Survivor> allsurv= (List<Survivor>) robotRepository.findAll();
        final int num= 2;
        List<Survivor> fr= (List<Survivor>) robotRepository.findSurvivorByInfectionReportIsGreaterThan(num);

        int infect  = fr.size();
        double rate =  (new Double(infect)) * (allsurv.size())/100 ;

        HashMap<String,Object> sh = new HashMap<>();
        sh.put("infectedrate" ,rate);
        sh.put("status" ,"infected");
        if(fr.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        else{
            return ResponseEntity.ok(sh);
        }
    }




    @GetMapping("/survivor/{id}")
    public ResponseEntity<Survivor> getBookById(@PathVariable("id") long id) {
        Optional<Survivor> survData = robotRepository.findById(id);

        if (survData.isPresent()) {
            return new ResponseEntity<>(survData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/survivor/createpattern2")
    public ResponseEntity<Survivor> Survivorp2(@RequestBody Survivor survivor) {
        try {

           // THIS CAN BE COMMENTED OUT OR ALLOW USER TO PASS NO VALUE ON CREATION OF SURVIVOR AT THE BEGINING
        //survivor.setInfectionstatus("");
            Survivor _survivor = robotRepository
                    .save(survivor);


            return new ResponseEntity<>(_survivor, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/survivor/create")
    public ResponseEntity<Survivor> Survivor(@RequestBody Survivor survivor) {
        try {
            Survivor _survivor = robotRepository
                    .save(new Survivor( survivor.getName(),survivor.getPlace(),survivor.getResource(),survivor.getAge(),survivor.getGender(),survivor.getInfectionstatus(),survivor.getInfectionReport() ));


            return new ResponseEntity<>(_survivor, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



//TO REPORT A SURVIVOR,1 IS INFECETD,0 IS NONINFECTED
    @PutMapping("/survivorstatusreport/{id}")
    public ResponseEntity<Survivor> updateUser(@PathVariable(value = "id") Long id, @RequestBody Survivor userDetails) {
        Survivor user = robotRepository.findFirstById(id);
        if(null == user) {
            return ResponseEntity.notFound().build();
        }
        if(user.getInfectionReport()+ userDetails.getInfectionReport() >2 ){
userDetails.setInfectionstatus("INFECTED");
        }
        Survivor updatedSurvivor = robotRepository.save(userDetails);
        return ResponseEntity.ok(updatedSurvivor);
    }


//
//    @PutMapping("/survivor/update/{id}")
//    public ResponseEntity<Survivor> updateSurvivor(@PathVariable("id") long id, @RequestBody Survivor survivors) {
//        Optional<Survivor> robotData = robotRepository.findById(id);
//
//        if (robotData.isPresent()) {
//            Survivor survivor = robotData.get();
//            survivor.setAge(survivors.getAge());
////           survivor.setAmmunition(survivors.getAmmunition());
////            survivor.setFood(survivors.getFood());
//            survivor.setGender(survivors.getGender());
//            survivor.setInfectionReport(survivors.getInfectionReport()+ survivor.getInfectionReport());
////            survivor.setLatitude(survivors.getLatitude());
////            survivor.setWater(survivors.getWater());
////            survivor.setLocation(survivors.getLocation());
////            survivor.setMedication(survivors.getMedication());
////            survivor.setLongitude(survivors.getLongitude());
//            survivor.setName(survivors.getName());
//          //  survivor.setLatitude(survivors.getLatitude());
//            if(survivor.getInfectionReport()>2){
//                survivor.setInfectionstatus("INFECTED");
//            }
//           // survivor.setInfectionstatus();
//
//            return new ResponseEntity<>(robotRepository.save(survivor), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @DeleteMapping("/survivordeath/{id}")
    public ResponseEntity<HttpStatus> deleteSurvivor(@PathVariable("id") long id) {
        try {
            robotRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/killAllSurvivor/delete")
    public ResponseEntity<HttpStatus> deleteAllSurvivor() {
        try {
            robotRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }




}
