package com.pasindu.logservice.service;

import com.pasindu.logservice.model.LogLine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.slf4j.MDC;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private Logger logger = LogManager.getLogger();

    public void insertLog(LogLine logLine) {
        MDC.put("applicationName", logLine.getApplicationName());
        MDC.put("serviceName", logLine.getServiceName());
        MDC.put("userName", logLine.getUserName());

logger.info("Sample Log Sample Log Sample Log Sample Log Sample Log");
    }

    public static void main(String[] args) {
        LogService logService = new LogService();
        logService.insertLog(new LogLine("Samsung","Bixby","pasindu"));
    }

}
