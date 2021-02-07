package com.netmed.usermodule.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
/**
 * UserDto is a Dto for User Entity
 *
 * @author Nataraj
 * @created 04/02/2021
 */
@Data
public class UserDto {

    @NotBlank(message = "User Name should not be null")
    @Size(max = 15, message = "User Name should be less than 16 Characters")
    private String userName;

    @NotBlank(message = "Password should not be null")
    @Size(max = 15, message = "Password should be less than 16 Characters")
    @Size(min = 6, message = "Password should be greater than 5 Characters")
    private String password;

    private RoleDto roleDto;
}
