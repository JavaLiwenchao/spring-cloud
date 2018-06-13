package com.lwc.beans;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.DiscoveryManager;
import com.netflix.discovery.EurekaClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * Created by liwenchao on 2018-05-29.
 *
 * @author liwenchao
 */
@Component
public class DisposableBean {

    @PreDestroy
    public void destory() {

        DiscoveryManager.getInstance().shutdownComponent();
        System.out.println("我被销毁了、、、、、我是用的@PreDestory的方式、、、、、、");
        System.out.println("我被销毁了、、、、、我是用的@PreDestory的方式、、、、、、");
    }
}
