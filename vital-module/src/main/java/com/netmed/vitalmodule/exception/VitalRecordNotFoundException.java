package com.netmed.vitalmodule.exception;

/**
 * PatientDetailsNotFoundException is to throw Exception when requested patient record is not found in Database
 *
 * @author Nataraj
 * @created 11/02/2021
 */
public class VitalRecordNotFoundException extends RuntimeException {
    public VitalRecordNotFoundException() {
        super("Patient Vital Record not found in the Database");
    }
}
