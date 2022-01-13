package com.pasindu.logservice.service.transformer;

import com.pasindu.logservice.model.LogAvroModel;
import com.pasindu.logservice.model.LogLine;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LogAvroModelToLogLineTransformer {
    public List<LogLine> getLogLines(List<LogAvroModel> logAvroModels) {
        return logAvroModels.stream()
                .map(logAvroModel ->
                        LogLine
                                .builder()
                                .id(String.valueOf(logAvroModel.getId()))
                                .applicationName(String.valueOf(logAvroModel.getApplicationName()))
                                .className(String.valueOf(logAvroModel.getClassName()))
                                .logLevel(String.valueOf(logAvroModel.getLogLevel()))
                                .date(String.valueOf(logAvroModel.getDate()))
                                .message(String.valueOf(logAvroModel.getMessage()))
                                .requestId(String.valueOf(logAvroModel.getRequestId()))
                                .serviceName(String.valueOf(logAvroModel.getServiceName()))
                                .userName(String.valueOf(logAvroModel.getUserName()))
                                .build()
                ).collect(Collectors.toList());
    }
}
