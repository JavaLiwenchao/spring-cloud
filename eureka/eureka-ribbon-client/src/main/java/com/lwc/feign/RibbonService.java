package com.lwc.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by liwenchao on 2018-05-29.
 *
 * @author liwenchao
 */
@Service
public class RibbonService {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String hi(String name) {
        /*ServiceInstance instance = loadBalancerClient.choose("eureka-client");
        int port = instance.getPort();
        String serviceId = instance.getServiceId();*/
        String result = restTemplate.getForObject("http://eureka-client/hi?name=" + name, String.class);
        return result;
    }

    public String hiError(String name) {
        return "hi," + name + ", sorry,error!";
    }

    public String testRibbon() {
        ServiceInstance instance = loadBalancerClient.choose("stores");
        return instance.getHost();
    }
}
