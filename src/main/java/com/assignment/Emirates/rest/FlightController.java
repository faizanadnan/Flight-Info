package com.assignment.Emirates.rest;


import com.assignment.Emirates.service.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @GetMapping(value = "flight", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getFlightInfo(@RequestParam(value = "date") String date, @RequestParam(value = "name") String name) {
        return flightService.fetchFlight(date, name);
    }


    @GetMapping(value = "price", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<String> price(@RequestParam(value = "flightNumber") String flightNumber,
                              @RequestParam(value = "date") String date) {
        Hooks.onOperatorDebug();
        return flightService.fetchPrice(flightNumber, date);
    }

}
