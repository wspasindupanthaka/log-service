package com.pasindu.logservice.controller;

import com.pasindu.logservice.model.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/log")
@RestController
public class LogController {

    @GetMapping("")
    public ResponseEntity<ResponseBody> helloWorld() {
        return ResponseEntity.ok(new ResponseBody("Hello World!"));
    }

}
