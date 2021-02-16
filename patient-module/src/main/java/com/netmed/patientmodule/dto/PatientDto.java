package com.netmed.patientmodule.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * PatientDto is a Dto for Patient Entity
 *
 * @author Nataraj
 * @created 15/02/2021
 */
@Data
public class PatientDto implements Serializable {

    /*Serializable is used because of JdkSerializationRedisSerializer used in CacheConfig - For Redis*/
    private static final long serialVersionUID = 6340298171379460245L;

    @NotBlank(message = "User Name should not be null")
    private String userName;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;

    @Range(max = 120, message = "Age should be less than 120")
    @Range(min = 1, message = "Age should be greater than 1")
    private int age;

    @Range(max = 2, message = "Gender should be 0(Male), 1(Female) or 2(Other)")
    @Range(min = 0, message = "Gender should be 0(Male), 1(Female) or 2(Other)")
    private int gender;

    @NotBlank(message = "Mobile Number should not be null")
    @Pattern(regexp = "[\\d]{10}", message = "Mobile Number should be 10 Numbers")
    private String mobileNumber;

    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Address should not be Null")
    private String address;

    @NotNull(message = "Zipcode should not be null")
    private int zipcode;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate subscriptionStartDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate subscriptionEndDate;

    private String createdBy;

    private LocalDateTime createdDate;

    private String lastModifiedBy;

    private LocalDateTime lastModifiedDate;
}
