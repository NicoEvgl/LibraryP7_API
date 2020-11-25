package com.nicoe.library.Services.impl;

import com.nicoe.library.Services.UserService;
import com.nicoe.library.model.dao.UserDao;
import com.nicoe.library.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public User findUserByPseudo(String pseudo) {
        User user = userDao.findByPseudo(pseudo);
        return user;
    }

    @Override
    public Boolean checkUser(User user) {
        User userInBdd = userDao.findByPseudo(user.getPseudo());
        if (userInBdd.getPassword().equals(user.getPassword())) {
            boolean userChecked = true;
        }
        boolean userChecked = false;
        return false;
    }

    @Override
    public User findByUserId(Integer userId) {
        User user = findById(userId);
        return user;
    }

    public User findById(Integer userId) {
        Optional<User> user = userDao.findById(userId);
        return user.isPresent() ? user.get() :null ;
    }
}
