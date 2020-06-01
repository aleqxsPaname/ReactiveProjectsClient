package com.alexisdemo.reactiveClient;

import com.alexisdemo.reactiveClient.resource.Vol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;


@SpringBootApplication
public class ReactiveClientApplication {


    public static WebClient getApiAirFrance() {
        return WebClient.create("http://localhost:8080/airfrance");
    }

    public static WebClient getApiQuatarAirWays() {
        return WebClient.create("http://localhost:8080/quatarairways");
    }

    public static void main(String[] args){
        SpringApplication.run(ReactiveClientApplication.class, args);
        getFlightsFromAirFrance();
        getFlightsFromQuatarAirWays();

    }


    public static void getFlightsFromAirFrance(){
        System.out.println("Start reactive method");

        getApiAirFrance().get()
                .uri(uriBuilder -> uriBuilder.path("/vols/paris").build())
                .exchange()
                .flatMapMany(f -> f.bodyToFlux(Vol.class))
                .subscribe(vol-> displayVol(vol, "AirFrance"));
                // the app still goes on, no blocking...
    }

    public static void getFlightsFromQuatarAirWays(){
        System.out.println("Start reactive method");

        Flux<Vol> firstVolReturnByQuatarAPI = getApiQuatarAirWays().get()
                .uri(uriBuilder -> uriBuilder.path("/vols/paris").build())
                .exchange()
                .flatMapMany(f -> f.bodyToFlux(Vol.class));

        firstVolReturnByQuatarAPI.subscribe(vol-> displayVol(vol, "QuatarAirWays"));
        // the app still goes on, no blocking...
    }

    public static void displayVol(Vol vol, String companyName){
        System.out.println("vol operated by : " + companyName +" -> " + vol.toString() );

    }

}
