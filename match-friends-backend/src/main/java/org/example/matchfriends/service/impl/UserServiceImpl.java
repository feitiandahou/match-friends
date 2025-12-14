package org.example.matchfriends.service.impl;

import org.example.matchfriends.entity.User;
import org.example.matchfriends.mapper.UserMapper;
import org.example.matchfriends.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper mapper;

    @Override
    public long userRegister(String userName ,String userAccount, String userPassword) {
        User user = new User();
        user.setUserAccount(userAccount);
        user.setPassword(userPassword);
        user.setUsername(userName);
        int result = mapper.insert(user);
        if(result < 1) return -1;
        return user.getId();
    }
}
