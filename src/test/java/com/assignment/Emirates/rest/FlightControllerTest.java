package com.assignment.Emirates.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeout;

@SpringBootTest
public class FlightControllerTest {

    @Autowired
    private FlightController flightController;

    @Test
    void getFlightInfoTest() {
        assertTimeout(ofMillis(850), () -> {
            var v1 = flightController.getFlightInfo("1/1/2021", "Emirates");
            StepVerifier.create(v1)
                    .expectNext("EE 1212","EE 1223", "EE 1245", "EE 1256","EE 1267")
                    .verifyComplete();
        });
    }


    @Test
    void getPriceTest() {
        assertTimeout(ofMillis(50), () -> {
            var v1 = flightController.price("", "");
            StepVerifier.create(v1)
                    .expectNext("10124")
                    .verifyComplete();
        });
    }
}
