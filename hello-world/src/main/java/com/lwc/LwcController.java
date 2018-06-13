package com.lwc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liwenchao on 2018-05-26.
 *
 * @author liwenchao
 */
@RestController
@EnableConfigurationProperties({ConfigBean.class, User.class})
public class LwcController {

    @Autowired
    private ConfigBean configBean;

    @Autowired
    private User user;

    @RequestMapping(value = "/lwc", method = {RequestMethod.POST, RequestMethod.GET})
    public String lwc() {
        return configBean.toString();
    }

    @RequestMapping(value = "/user", method = {RequestMethod.POST, RequestMethod.GET})
    public String user() {
        return user.toString();
    }
}
