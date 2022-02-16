package com.robot.spring.mssql.controller;

import com.robot.spring.mssql.RobotService;
import com.robot.spring.mssql.model.RobotResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@RestController
public class RobotListController {


@Autowired
RobotService robotService;

//THIS CAN BE PUT IN CONFIG
    private final String POST_API = "https://robotstakeover20210903110417.azurewebsites.net/robotcpu";



    @Autowired
    private WebClient.Builder webClientBuilder;


    @Autowired
    WebClient webClient;



    @GetMapping("/existingrobotspatternlanding")
    public Flux<RobotResponse> getRobotsLanding() {
       String a ="Landing";
        return Flux.fromStream(robotService.getRobots().toStream().filter(f -> f.getCategory().equalsIgnoreCase("Landing")));
    }

    @GetMapping("/existingrobotspatternflying")
    public Flux<RobotResponse> getRobotflying() {
        String a ="Flying";
        return Flux.fromStream(robotService.getRobots().toStream().filter(f -> f.getCategory().equalsIgnoreCase("Flying")));
    }
///filter(a -> a.isRegistered() && name.equals(a.getRegistration().getName()));
//}

    @GetMapping("/getrobotusingwebclient/all")
    public RobotResponse[] getAllRobot() {

        return robotService.getRobotsPattern2();
    }


}

