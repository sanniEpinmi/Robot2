package com.robot.spring.mssql;
import com.robot.spring.mssql.model.RobotResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;

@Service
public class RobotService {
    private final String ROBOT_API = "https://robotstakeover20210903110417.azurewebsites.net/robotcpu";



    @Autowired
    private WebClient.Builder webClientBuilder;


    @Autowired
    WebClient webClient;

//    public Mono<RobotResponse> getRobotById(String id) {
//        return webClient.get()
//                .uri("/newrobotlist/{id}", id)
//                .retrieve()
//                .bodyToMono(RobotResponse.class);
//    }


    public Flux<RobotResponse> getRobots() {
        return webClient.get()
                .uri(ROBOT_API)
                .retrieve()
                .bodyToFlux(RobotResponse.class);
    }


    public RobotResponse[] getRobotsPattern2() {
        return webClientBuilder.build()
                .get()
                .uri(ROBOT_API)
                .retrieve()
                .bodyToMono(RobotResponse[].class)
                .block();
    }







}
