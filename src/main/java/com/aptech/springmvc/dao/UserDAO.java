package com.aptech.springmvc.dao;

import com.aptech.springmvc.entity.User;

public interface UserDAO {
    User findByUserName(String username);
    void save(User user);
}
