package com.lwc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by liwenchao on 2018-05-29.
 *
 * @author liwenchao
 */
@RestController
public class RestTestController {

    @GetMapping("/testRest")
    public String testRest() {

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://www.baidu.com", String.class, "Content-Type: application/javascript;charset=utf-8");
    }
}
