package com.lwc.dao;

import com.lwc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by liwenchao on 2018-05-28.
 *
 * @author liwenchao
 */
public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
