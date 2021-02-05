package com.netmed.usermodule.controller;

import com.netmed.usermodule.model.User;
import com.netmed.usermodule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/netmed-user-api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String getHello(){
        return "Hello World";
    }

    @GetMapping("/getall")
    public List<User> getUsers(){
        return userService.getUsers();
    }
}
