package com.lwc.controller;

import com.lwc.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liwenchao on 2018-05-30.
 *
 * @author liwenchao
 */
@RestController
public class HiController {

    @Autowired
    private HiService hiService;

    @GetMapping("/hi")
    public String sayHi(@RequestParam(defaultValue = "liwenchao", required = false) String name) {
        return hiService.sayHi(name);
    }
}
