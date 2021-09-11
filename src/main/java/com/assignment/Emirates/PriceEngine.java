package com.assignment.Emirates;

import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class PriceEngine {


    public Mono<String> getPrice() {
        Mono.just("mocking 3gb object").map(value-> mock3gbObject());
        return Mono.just("10124");
    }

    public String mock3gbObject() {
        Runnable runnable = () -> {
            ArrayList<byte[]> list = new ArrayList<>(1610611911);
            for (int i = 0; i < 1610611911; i++) {
                list.add(new byte[i * 2]);
            }
        };
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(runnable);
        return "dummyData";
    }

}

