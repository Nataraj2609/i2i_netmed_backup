package com.netmed.vitalmodule.client;

import com.netmed.vitalmodule.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * UserClientProxy is an interface to make call to UserService microservice using Feign
 *
 * @author Nataraj
 * @created 15/02/2021
 */
@FeignClient(name = "user-module/netmed-user-api/v1")
public interface UserClientProxy {

    //For Create, Update
    @GetMapping(path = "/findUserId")
    long findUserIdByUserName(@RequestParam String userName);

    //For Get, GetAll, SearchAll
    @GetMapping(path = "/{userId}")
    UserDto getUser(@PathVariable long userId);
}
