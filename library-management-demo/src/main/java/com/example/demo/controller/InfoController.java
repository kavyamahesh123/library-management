package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @GetMapping("/api/info")
    public Map<String, String> getInfo() {
        return Map.of(
            "name", appName,
            "version", appVersion
        );
    }
}
