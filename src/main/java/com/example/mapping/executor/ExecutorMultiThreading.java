package com.example.mapping.executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorMultiThreading {
//    ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Bean
    public ExecutorService executorService(){
        return Executors.newFixedThreadPool(2);
    }
}
