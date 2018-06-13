package com.lwc.feign.impl;

import com.lwc.feign.EurekaClientFeign;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by liwenchao on 2018-05-30.
 *
 * @author liwenchao
 */
@Component
public class HiHystrix implements EurekaClientFeign {

    @Override
    public String syHiFromClientEureka(@RequestParam(value = "name") String name) {
        return "Hi, " + name + ", sorry, error!";
    }
}
