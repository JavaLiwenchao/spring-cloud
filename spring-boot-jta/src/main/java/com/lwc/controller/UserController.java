package com.lwc.controller;

import com.lwc.domain.User;
import com.lwc.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by liwenchao on 2018-05-28.
 *
 * @author liwenchao
 */
@Api(value = "User-API",description = "这是用户接口详细信息的描述")
@RestController
@RequestMapping(value = "/userController", method = {RequestMethod.POST, RequestMethod.GET})
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "添加用户", notes = "添加用户")
    @RequestMapping(value = "/addUser", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "密码")})
    public void addUser(String userName, String password) {
        userService.addUser(userName, password);
    }

    @RequestMapping(value = "/findUserList", method = {RequestMethod.POST, RequestMethod.GET})
    public List<User> findUserList() {
        return userService.findUserList();
    }

    @RequestMapping(value = "/findUserById", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiResponse(code = 200, message = "返回用户信息")
    public User findUserById(Long id) {
        return userService.findUserById(id);
    }

    @RequestMapping(value = "/findUserByUsername", method = {RequestMethod.POST, RequestMethod.GET})
    public User findUserByUsername(String username) {
        return userService.findUserByUsername(username);
    }

    @RequestMapping(value = "/delUser", method = {RequestMethod.POST, RequestMethod.GET})
    public void delUser(Long id) {
        userService.delUser(id);
    }
}
