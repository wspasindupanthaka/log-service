package com.pasindu.logservice.service.impl;

import com.pasindu.logservice.model.LogLine;
import com.pasindu.logservice.service.LogService;
import com.pasindu.logservice.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    private Logger logger = LogManager.getLogger();

    public void insertLog(List<LogLine> logLines) {

        logLines.stream().forEach(logLine -> {
            MDC.put(Constants.ID, String.valueOf(logLine.getId()));
            MDC.put(Constants.CLASS_NAME, String.valueOf(logLine.getClassName()));
            MDC.put(Constants.DATE, String.valueOf(logLine.getDate()));
            MDC.put(Constants.MESSAGE, String.valueOf(logLine.getMessage()));
            MDC.put(Constants.LOG_LEVEL, String.valueOf(logLine.getLogLevel()));
            MDC.put(Constants.APPLICATION_NAME, String.valueOf(logLine.getApplicationName()));
            MDC.put(Constants.SERVICE_NAME, String.valueOf(logLine.getServiceName()));
            MDC.put(Constants.USER_NAME, String.valueOf(logLine.getUserName()));
            MDC.put(Constants.REQUEST_ID, String.valueOf(logLine.getRequestId()));

            logger.info(logLine.getMessage());
        });

    }

}
