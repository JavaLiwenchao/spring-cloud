package com.lwc.service;

import com.lwc.domain.User;

import java.util.List;

/**
 * Created by liwenchao on 2018-05-28.
 *
 * @author liwenchao
 */
public interface UserService {

    void addUser(String userName, String password);

    List<User> findUserList();

    User findUserById(Long id);

    User findUserByUsername(String username);

    void delUser(Long id);
}
