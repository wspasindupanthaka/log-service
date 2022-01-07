package com.pasindu.logservice;

import com.pasindu.logservice.model.LogLine;
import com.pasindu.logservice.service.LogService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogServiceApplication.class, args);
        LogService logService = new LogService();
        logService.insertLog(new LogLine("Samsung", "Bixby", "pasindu"), "Parana Log Service Eka");
    }

}
