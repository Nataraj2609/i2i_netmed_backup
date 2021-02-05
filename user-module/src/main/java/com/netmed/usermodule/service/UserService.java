package com.netmed.usermodule.service;

import com.netmed.usermodule.model.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    public List<User> getUsers();

}
