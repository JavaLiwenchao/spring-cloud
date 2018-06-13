package com.lwc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liwenchao on 2018-06-06.
 *
 * @author liwenchao
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/hi")
    public String hi() {
        return "I'm 李卤味！";
    }
}
