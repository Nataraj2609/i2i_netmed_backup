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

    /**
     * createUser Saves the user details
     *
     * @param userDto
     * @return Response status with saved user record
     */
    UserDto createUser(UserDto userDto);

    /**
     * Get the user details based on id
     *
     * @param userId
     * @return Requested User Detail
     */
    UserDto getUser(long userId);

    /**
     * Update Service for updating the user details for the id
     *
     * @param userDto
     * @return Requested User Detail
     */
    UserDto updateUser(UserDto userDto);

    /**
     * Delete the user details for the id
     *
     * @param userId
     * @return No Content
     */
    void deleteUser(long userId);

    /**
     * Retrieves all the user details matching the given condition
     *
     * @param orderBy
     * @param page
     * @param limit
     * @return user list
     */
    List<UserDto> getAllUsers(int page, int limit, String orderBy);

    /**
     * Search all the user details matching the given condition
     *
     * @param search
     * @param limit
     * @param page
     * @param orderBy
     * @return List of User Details
     */
    List<UserDto> searchUser(String search, int page, int limit, String orderBy);

    /**
     * Search all the user details matching the given condition (Native Query)
     *
     * @param search
     * @return List of User Details
     */
    List<UserDto> search(String search);

    /**
     * getUserIdByUserName service is for Feign call invoked by Vital module
     *
     * @param userName
     * @return Long - UserId
     */
    Long getUserIdByUserName(String userName);

    List<UserDto> doElasticSearch(String query);
}
