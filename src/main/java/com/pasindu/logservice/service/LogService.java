package com.pasindu.logservice.service;

import com.pasindu.logservice.model.LogLine;

import java.util.List;

public interface LogService {

    void insertLog(List<LogLine> logLines);

}
