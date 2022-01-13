package com.pasindu.logservice;

import com.pasindu.logservice.service.LogService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class LogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogServiceApplication.class, args);
    }

}
