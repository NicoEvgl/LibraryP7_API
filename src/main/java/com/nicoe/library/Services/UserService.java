package com.nicoe.library.Services;

import com.nicoe.library.model.entities.User;

import java.util.List;

public interface UserService {
    void createUser(User user);
    List<User> findUserList();
    User findUserByPseudo(String pseudo);
    Boolean checkUser(User user);
    User findByUserId(Integer userId);
}
