package com.netmed.patientmodule.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * ErrorDetails holds the exception information
 *
 * @author Nataraj
 * @created 11/02/2021
 */
@Data
@AllArgsConstructor
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
}
