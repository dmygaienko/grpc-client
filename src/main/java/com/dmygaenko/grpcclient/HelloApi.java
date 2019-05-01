package com.dmygaenko.grpcclient;

import com.dmygaenko.grpcapi.HelloResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hello")
@RestController
@AllArgsConstructor
@Slf4j
public class HelloApi {

    private final HelloClient helloClient;

    @GetMapping
    public String hello(@RequestParam String firstName, @RequestParam String lastName) {
        log.info("Hello Api is called with firstName - {}, lastName - {}", firstName, lastName);
        return helloClient.hello(firstName, lastName);
    }
}
