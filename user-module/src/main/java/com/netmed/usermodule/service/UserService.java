package com.netmed.usermodule.service;

import com.netmed.usermodule.dto.UserDto;
import com.netmed.usermodule.model.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserService is used to perform user operations
 *
 * @author Nataraj
 * @created 04/02/2021
 */
public interface UserService {

    public Page<User> getUsers();

}
