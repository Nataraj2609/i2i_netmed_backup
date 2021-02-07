package com.netmed.usermodule.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * RoleDto is a Dto for Role Entity
 *
 * @author Nataraj
 * @created 04/02/2021
 */
@Data
public class RoleDto {

    @NotBlank(message = "Role Name should not be null - (Doctor, Patient)")
    private String roleName;

}
