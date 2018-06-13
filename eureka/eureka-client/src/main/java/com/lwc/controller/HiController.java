package com.lwc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liwenchao on 2018-05-29.
 *
 * @author liwenchao
 */
@RestController
public class HiController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/hi")
    public String hi(String name) {
        return "hi, I'm " + name + ", port: " + port;
    }
}
