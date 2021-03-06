package com.nicoe.library.Services;

import com.nicoe.library.model.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void createUser(User user);
    List<User> findUserList();
    User findUserByPseudo(String pseudo);
    Boolean checkUser(User user);
    User findByUserId(Integer userId);
}
