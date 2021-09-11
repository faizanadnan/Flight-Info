package com.assignment.Emirates.service;

import com.assignment.Emirates.PriceEngine;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@Slf4j
@AllArgsConstructor
public class FlightService {


    @Cacheable(value = "flightNumber", key = "#date+#name")
    public Flux<String> fetchFlight(String date, String name) {
        Mono<String> map1 = Mono.just("EE 1212").delayElement(Duration.ofMillis(500));
        Mono<String> map2 = Mono.just("EE 1223").delayElement(Duration.ofMillis(500));
        Mono<String> map3 = Mono.just("EE 1245").delayElement(Duration.ofMillis(500));
        Mono<String> map4 = Mono.just("EE 1256").delayElement(Duration.ofMillis(500));
        Mono<String> map5 = Mono.just("EE 1267").delayElement(Duration.ofMillis(500));
        return map1.mergeWith(map2)
                .mergeWith(map3)
                .mergeWith(map4)
                .mergeWith(map5)
                .doOnComplete(()-> log.debug("fetching flight number for"+ name+ "airport and date"+ date));
    }

    public Mono<String> fetchPrice(String flightNumber, String date) {
        PriceEngine priceEngine = new PriceEngine();

        return priceEngine.getPrice()
                .doOnSuccess(value -> log.debug(value + "price returned for"+ flightNumber +" and of date" + date))
                .doOnError(i-> log.debug("failed while fetching price"));
    }
}
