package com.pasindu.logservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogLine {
    private String id;
    private String className;
    private String date;
    private String message;
    private String logLevel;
    private String applicationName;
    private String serviceName;
    private String userName;
    private String requestId;
}
