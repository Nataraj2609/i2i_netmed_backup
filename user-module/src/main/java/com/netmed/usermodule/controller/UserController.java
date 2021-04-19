package com.netmed.usermodule.controller;

import com.netmed.usermodule.aop.LogExecutionTime;
import com.netmed.usermodule.dto.UserDto;
import com.netmed.usermodule.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


/**
 * UserController contains Rest End points to perform user operations
 *
 * @author Nataraj
 * @created 04/02/2021
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/netmed-user-api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Saves the user details
     *
     * @param userDto
     * @return Response status with saved user record
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Api Endpoint to create the User details")
    @LogExecutionTime
    public UserDto createUser(@Valid @RequestBody UserDto userDto) throws IOException {
        return userService.createUser(userDto);
    }

    /**
     * Get the user details based on id
     *
     * @param userId
     * @return Requested User Detail
     */
    @GetMapping(path = "/{userId}")
    @ApiOperation(value = "Api Endpoint to get the User details")
    @LogExecutionTime
    public UserDto getUser(@PathVariable long userId) {
        return userService.getUser(userId);
    }

    /**
     * Update the user details for the id
     *
     * @param userDto
     * @return Requested User Detail
     */
    @PutMapping
    @ApiOperation(value = "Api Endpoint to update the User details - Password & Role can be only updated")
    @LogExecutionTime
    public UserDto updateUser(@Valid @RequestBody UserDto userDto) throws IOException {
        return userService.updateUser(userDto);
    }

    /**
     * Delete the user details for the id
     *
     * @param userId
     * @return No Content
     */
    @DeleteMapping(path = "/{userId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Api Endpoint to delete the User details")
    @LogExecutionTime
    public void deleteUser(@PathVariable long userId) throws IOException {
        userService.deleteUser(userId);
    }

    /**
     * Retrieves all the user details matching the given condition
     *
     * @param limit
     * @param page
     * @param orderBy
     * @return List of User Details
     */
    @GetMapping("getAll")
    @ApiOperation(value = "Api Endpoint to retrieve all the User details")
    @LogExecutionTime
    public List<UserDto> getAllUsers(@RequestParam(name = "page", defaultValue = "0") int page,
                                     @RequestParam(name = "limit", defaultValue = "10") int limit,
                                     @RequestParam(name = "orderBy", defaultValue = "asc") String orderBy) {
        return userService.getAllUsers(page, limit, orderBy);
    }

    /**
     * Search all the user details matching the given condition
     *
     * @param search
     * @param limit
     * @param page
     * @param orderBy
     * @return List of User Details
     */
    @GetMapping("searchUser")
    @ApiOperation(value = "Api Endpoint to search for the User - Only using User name")
    @LogExecutionTime
    public List<UserDto> searchUser(@RequestParam(name = "search") String search,
                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "limit", defaultValue = "10") int limit,
                                    @RequestParam(name = "orderBy", defaultValue = "asc") String orderBy) {
        return userService.searchUser(search, page, limit, orderBy);
    }

    /**
     * Search all the user details matching the given condition using QUERY (Learning Purpose)
     *
     * @param search
     * @return List of User Details
     */
    @GetMapping("search")
    @ApiOperation(value = "Api Endpoint to search for the User - Only using User name - using Like")
    @LogExecutionTime
    public List<UserDto> search(@RequestParam(name = "search") String search) {
        return userService.search(search);
    }

    /**
     * Get the User Id for the need of Patient Module Microservice(Consumed by Feign client call directly)
     *
     * @param userName
     * @return Requested User id
     */
    @GetMapping(path = "/findUserId")
    @ApiOperation(value = "Api Endpoint to get the User Id for the need of Patient Module Microservice(Consumed by Feign client call directly, No need to show in Frontend.")
    @LogExecutionTime
    public Long getUserIdByUserName(@RequestParam String userName) {
        return userService.getUserIdByUserName(userName);
    }

    /**
     * Search all the details matching the given condition using ElasticSearch Elite Api
     *
     * @param query
     * @return List of User Details
     */
    @GetMapping("doElasticSearch")
    @ApiOperation(value = "Api Endpoint to search for the details matching the given condition using ElasticSearch Elite Api")
    @LogExecutionTime
    public List<UserDto> elasticSearch(@RequestParam(name = "q") String query) throws IOException {
        return userService.doElasticSearch(query);
    }
}
