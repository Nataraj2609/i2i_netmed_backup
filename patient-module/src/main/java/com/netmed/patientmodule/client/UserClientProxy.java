package com.netmed.patientmodule.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * UserClientProxy is an interface to make call to UserService microservice using Feign
 *
 * @author Nataraj
 * @created 15/02/2021
 */
@FeignClient(name = "user-module/netmed-user-api")
public interface UserClientProxy {

    @GetMapping(path = "/findUserId")
    long findUserIdByUserName(@RequestParam String userName);
}
