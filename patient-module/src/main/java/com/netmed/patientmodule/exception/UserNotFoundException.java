package com.netmed.patientmodule.exception;

/**
 * UserNotFoundException is to throw Exception when requested user is not found in Database
 *
 * @author Nataraj
 * @created 11/02/2021
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User Record not found in the Database");
    }
}
