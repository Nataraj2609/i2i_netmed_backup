package com.netmed.vitalmodule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * UserDto is a Dto for User Entity
 *
 * @author Nataraj
 * @created 04/02/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    /*Serializable is used because of JdkSerializationRedisSerializer used in CacheConfig - For Redis*/
    private static final long serialVersionUID = 6340298171379460244L;

    @NotBlank(message = "User Name should not be null")
    @Size(max = 15, message = "User Name should be less than 16 Characters")
    private String userName;

    @NotBlank(message = "Password should not be null")
    @Size(max = 15, message = "Password should be less than 16 Characters")
    @Size(min = 6, message = "Password should be greater than 5 Characters")
    private String password;

    @NotBlank(message = "Role Name should not be null - (Doctor, Patient)")
    private String roleName;

    private String createdBy;

    private LocalDateTime createdDate;

    private String lastModifiedBy;

    private LocalDateTime lastModifiedDate;
}
