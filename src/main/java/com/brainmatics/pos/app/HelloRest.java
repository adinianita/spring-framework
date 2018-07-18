package com.brainmatics.pos.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloRest {

    @GetMapping
    public String say(){
        return "Hello";
    }

}


