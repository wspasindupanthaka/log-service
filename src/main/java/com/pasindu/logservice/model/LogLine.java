package com.pasindu.logservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogLine {

    private String applicationName;
    private String serviceName;
    private String userName;

}
