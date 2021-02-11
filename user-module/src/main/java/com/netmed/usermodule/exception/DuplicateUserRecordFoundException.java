package com.netmed.usermodule.exception;

/**
 * DuplicateUserRecordException is to throw Exception when requested user is already found in Database while saving
 *
 * @author Nataraj
 * @created 11/02/2021
 */
public class DuplicateUserRecordFoundException extends RuntimeException {
    public DuplicateUserRecordFoundException() {
        super("Duplicate User Record - A Record with same user name already Exists");
    }
}
