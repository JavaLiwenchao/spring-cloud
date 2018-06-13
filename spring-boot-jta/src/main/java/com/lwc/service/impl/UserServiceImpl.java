package com.lwc.service.impl;

import com.lwc.dao.UserDao;
import com.lwc.domain.User;
import com.lwc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liwenchao on 2018-05-28.
 *
 * @author liwenchao
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void addUser(String userName, String password) {
        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        userDao.save(user);
    }

    @Override
    public List<User> findUserList() {
        List<User> list = userDao.findAll();
        return list;
    }

    @Override
    public User findUserById(Long id) {

        User user = userDao.findOne(id);
        return user;
    }

    @Override
    public User findUserByUsername(String username) {
        return userDao.findByUsername(username);
    }


    public void delUser(Long id) {
        userDao.delete(id);
    }
}
