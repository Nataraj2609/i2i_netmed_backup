package com.netmed.patientmodule.exception;

/**
 * DuplicatePatientRecordFoundException is to throw Exception when requested patient detail is already found in Database while saving
 *
 * @author Nataraj
 * @created 11/02/2021
 */
public class DuplicatePatientRecordFoundException extends RuntimeException {
    public DuplicatePatientRecordFoundException() {
        super("Duplicate patient Record - A Record with same Patient name already Exists");
    }
}
