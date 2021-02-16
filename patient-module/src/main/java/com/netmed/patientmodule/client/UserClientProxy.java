package com.netmed.patientmodule.client;

import com.netmed.patientmodule.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * UserClientProxy is an interface to make call to UserService microservice
 *
 * @author Nataraj
 * @created 15/02/2021
 */
@FeignClient(name = "user-module/netmed-user-api")
public interface UserClientProxy {

    //NOT used yet
    @GetMapping(path = "/{userId}")
    UserDto getUser(@PathVariable long userId);

    @GetMapping(path = "/findUserId")
    long findUserIdByUserName(@RequestParam String userName);
}
