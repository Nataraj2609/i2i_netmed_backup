package com.netmed.patientmodule.exception;

/**
 * PatientDetailsNotFoundException is to throw Exception when requested patient record is not found in Database
 *
 * @author Nataraj
 * @created 11/02/2021
 */
public class PatientDetailsNotFoundException extends RuntimeException {
    public PatientDetailsNotFoundException() {
        super("Patient Record not found in the Database");
    }
}
