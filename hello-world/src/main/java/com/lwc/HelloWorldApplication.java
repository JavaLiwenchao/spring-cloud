package com.lwc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
public class HelloWorldApplication {

    @Value("${my.name}")
    private String name;

    @Value("${my.age}")
    private int age;

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }

    @RequestMapping(value = "/hi", method = {RequestMethod.POST, RequestMethod.GET})
    public String hi() {
        return "hello, I'm " + name + ", " + age + "year old";
    }
}
