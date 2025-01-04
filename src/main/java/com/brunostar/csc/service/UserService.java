package com.brunostar.csc.service;

import com.brunostar.csc.model.User;
import com.brunostar.csc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return user;
    }
}
