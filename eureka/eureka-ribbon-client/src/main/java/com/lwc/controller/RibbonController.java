package com.lwc.controller;

import com.lwc.feign.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liwenchao on 2018-05-29.
 *
 * @author liwenchao
 */
@RestController
public class RibbonController {

    @Autowired
    private RibbonService ribbonService;

    @GetMapping("/hi")
    public String hi(@RequestParam(required = false, defaultValue = "liwenchao") String name) {
        return ribbonService.hi(name);
    }

    @GetMapping("/testRibbon")
    public String testRibbon() {
        return ribbonService.testRibbon();
    }
}
