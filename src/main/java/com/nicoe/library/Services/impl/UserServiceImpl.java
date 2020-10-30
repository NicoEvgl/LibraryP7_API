package com.nicoe.library.Services.impl;

import com.nicoe.library.Services.UserService;
import com.nicoe.library.model.dao.UserDao;
import com.nicoe.library.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public void createUser(User user) {
        userDao.save(user);
    }

    @Override
    public List<User> findUserList() {
        List<User> userList = userDao.findAll();
        return userList;
    }

    @Override
    public User findUserByUsername(String username) {
        User user = userDao.findByUsername(username);
        return user;
    }

    @Override
    public Boolean connectionCheck(User user) {
        User registeredUser = userDao.findByUsername(user.getUsername());
        if (registeredUser.getPassword().equals(user.getPassword())) {
            boolean connectionCheck = true;
        }
        boolean connectionCheck = false;
        return connectionCheck;
    }
}
