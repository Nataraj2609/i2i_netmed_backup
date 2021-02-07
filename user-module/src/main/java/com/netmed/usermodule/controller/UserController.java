package com.netmed.usermodule.controller;

import com.netmed.usermodule.dto.UserDto;
import com.netmed.usermodule.model.User;
import com.netmed.usermodule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * UserController contains Rest End points to perform user operations
 *
 * @author Nataraj
 * @created 04/02/2021
 */
@RestController
@RequestMapping("/netmed-user-api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getallusers")
    public Page<User> getUsers(){
        return userService.getUsers();
    }
}
