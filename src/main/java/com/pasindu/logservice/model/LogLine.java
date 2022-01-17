package com.pasindu.logservice.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
