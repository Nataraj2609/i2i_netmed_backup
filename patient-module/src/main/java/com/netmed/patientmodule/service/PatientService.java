package com.netmed.patientmodule.service;


import com.netmed.patientmodule.dto.PatientDto;
import com.netmed.patientmodule.dto.UserDto;

import java.util.List;

/**
 * PatientService is used to perform operations on patient records
 *
 * @author Nataraj
 * @created 04/02/2021
 */
public interface PatientService {

    /**
     * createPatientRecord Saves the Patient details
     *
     * @param patientDto
     * @return Response status with saved Patient record
     */
    PatientDto createPatientRecord(PatientDto patientDto);

    /**
     * Get the Patient details based on id
     *
     * @param patientId
     * @return Requested Patient Detail
     */
    PatientDto getPatientRecords(long patientId);

    /**
     * Update Service for updating the Patient details for the id
     *
     * @param patientId
     * @param patientDto
     * @return Updated Patient Detail
     */
    PatientDto updatePatientRecord(long patientId, PatientDto patientDto);

    /**
     * Delete the Patient details for the id
     *
     * @param patientId
     * @return No Content
     */
    void deletePatientRecord(long patientId);

    /**
     * Retrieves all the patient details matching the given condition
     *
     * @param orderBy
     * @param page
     * @param limit
     * @return Patient list
     */
    List<PatientDto> getAllPatientRecords(int page, int limit, String orderBy);

    /**
     * Search all the user details matching the given condition
     *
     * @param search
     * @param limit
     * @param page
     * @param orderBy
     * @return List of Patient Details
     */
    List<PatientDto> searchPatientRecords(String search, int page, int limit, String orderBy);
}
