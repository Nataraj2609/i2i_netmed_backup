package com.netmed.usermodule.serviceImpl;

import com.netmed.usermodule.dto.UserDto;
import com.netmed.usermodule.model.User;
import com.netmed.usermodule.repository.RoleRepository;
import com.netmed.usermodule.repository.UserRepository;
import com.netmed.usermodule.service.UserService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserServiceImpl is a Implementation of UserService
 *
 * @author Nataraj
 * @created 04/02/2021
 */
@Service
@Data
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;


    @Override
    public Page<User> getUsers() {
        Pageable pageable = PageRequest.of(0,20);
        Page<User> u= userRepository.findAll(pageable);
        System.out.println(u);
        return u;
    }
}
