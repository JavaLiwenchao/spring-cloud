package com.lwc.service;

import com.lwc.feign.EurekaClientFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liwenchao on 2018-05-30.
 *
 * @author liwenchao
 */
@Service
public class HiService {

    @Autowired
    private EurekaClientFeign eurekaClientFeign;

    public String sayHi(String name) {
        return eurekaClientFeign.syHiFromClientEureka(name);
    }
}
