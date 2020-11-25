package com.nicoe.library.controllers;

import com.nicoe.library.Services.UserService;
import com.nicoe.library.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public boolean connectionCheck(@PathVariable User user){
        Boolean loggedIn = userService.checkUser(user);
        return loggedIn;
    }

    @PostMapping("/account-creation")
    public void accountCreation(@RequestBody User user){
        userService.createUser(user);
    }

    @GetMapping("/user-list")
    public List<User> userList(){
        List<User> userList = userService.findUserList();
        return userList;
    }

    @GetMapping("/find-user/{pseudo}")
    public User findUser(@PathVariable String pseudo){
        User user = userService.findUserByPseudo(pseudo);
        return user;
    }
}
