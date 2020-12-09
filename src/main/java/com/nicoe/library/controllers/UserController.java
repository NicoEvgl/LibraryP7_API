package com.nicoe.library.controllers;

import com.nicoe.library.Services.UserService;
import com.nicoe.library.model.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    static Logger logger = LogManager.getLogger(UserController.class);


    /**
     * Process to login
     * @param user user logged in
     * @return loggedIn
     */
    @PostMapping("/login")
    public boolean connectionCheck(@PathVariable User user){
        logger.debug(user.toString());
        Boolean loggedIn = userService.checkUser(user);
        return loggedIn;
    }

    /**
     * Process to create user account
     * @param user user
     */
    @PostMapping("/account-creation")
    public void accountCreation(@RequestBody User user){
        logger.debug("UserController account-creation");
        userService.createUser(user);
    }

    /**
     * Find User List
     * @return userList
     */
    @GetMapping("/user-list")
    public List<User> userList(){
        List<User> userList = userService.findUserList();
        return userList;
    }

    /**
     * Find user by pseudo
     * @param pseudo String pseudo
     * @return user
     */
    @GetMapping("/find-user/{pseudo}")
    public User findUser(@PathVariable String pseudo){
        User user = userService.findUserByPseudo(pseudo);
        return user;
    }
}
