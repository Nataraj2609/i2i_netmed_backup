package com.netmed.vitalmodule.exception;

/**
 * DuplicatePatientRecordFoundException is to throw Exception when requested patient detail is already found in Database while saving
 *
 * @author Nataraj
 * @created 11/02/2021
 */
public class DuplicateVitalRecordFoundException extends RuntimeException {
    public DuplicateVitalRecordFoundException() {
        super("Duplicate patient's Vital Record - A Vital Record for same Patient already Exists");
    }
}
