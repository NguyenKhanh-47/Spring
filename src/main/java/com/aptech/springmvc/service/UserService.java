package com.aptech.springmvc.service;

import com.aptech.springmvc.entity.RegisteredUser;
import com.aptech.springmvc.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUserName(String userName);
    void save(RegisteredUser registeredUser);
}