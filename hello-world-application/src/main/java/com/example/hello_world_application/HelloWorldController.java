package com.example.hello_world_application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping(path = "/")
    public String helloWorld() {
		// Implemented using String Templates
        return """
                { message: Hello World Java v3 }
               """;
    }
}
