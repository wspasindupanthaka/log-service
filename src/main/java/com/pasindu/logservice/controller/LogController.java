package com.pasindu.logservice.controller;

import com.pasindu.logservice.model.LogLine;
import com.pasindu.logservice.model.ResponseBody;
import com.pasindu.logservice.service.LogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/log")
@RestController
public class LogController {

    @GetMapping("")
    public ResponseEntity<ResponseBody> helloWorld() {
        LogService logService = new LogService();
        logService.insertLog(new LogLine("Samsung", "Bixby", "pasindu")
                ,"End Point Eken");
        return ResponseEntity.ok(new ResponseBody("Hello World!"));
    }

}
