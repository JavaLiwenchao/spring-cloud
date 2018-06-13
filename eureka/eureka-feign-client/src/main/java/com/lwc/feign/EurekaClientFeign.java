package com.lwc.feign;

import com.lwc.config.FeignConfig;
import com.lwc.feign.impl.HiHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by liwenchao on 2018-05-30.
 *
 * @author liwenchao
 */
@FeignClient(value = "eureka-client", configuration = FeignConfig.class, fallback = HiHystrix.class)
public interface EurekaClientFeign {

    @GetMapping("/hi")
    String syHiFromClientEureka(@RequestParam(value = "name") String name);
}
