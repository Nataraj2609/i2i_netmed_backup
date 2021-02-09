package com.netmed.usermodule.service;

import com.netmed.usermodule.dto.UserDto;

import java.util.List;

/**
 * UserService is used to perform user operations
 *
 * @author Nataraj
 * @created 04/02/2021
 */
public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUser(long userId);

    UserDto updateUser(UserDto userDto);

    void deleteUser(long userId);

    List<UserDto> getAllUsers(int page, int limit, String orderBy);

    List<UserDto> searchUser(String search, int page, int limit, String orderBy);

    List<UserDto> search(String search);
}
