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

    @PostMapping("/loginProcess")
    public boolean connectionCheck(@PathVariable User user){
        Boolean login = userService.connectionCheck(user);
        return login;
    }

    @PostMapping("/registerProcess")
    public void creationCompte(@RequestBody User user){
        userService.createUser(user);
    }

    @GetMapping("/userList")
    public List<User> userList(){
        List<User> userList = userService.findUserList();
        return userList;
    }

    @GetMapping("/findUser/{username}")
    public User findUserByUsername(@PathVariable String username){
        User user = userService.findUserByUsername(username);
        return user;
    }
}
