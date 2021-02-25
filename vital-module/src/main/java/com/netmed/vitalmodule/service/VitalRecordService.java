package com.netmed.vitalmodule.service;

import com.netmed.vitalmodule.dto.VitalRecordDto;

import java.util.List;

/**
 * VitalRecordService is used to perform operations on patient Vital records
 *
 * @author Nataraj
 * @created 16/02/2021
 */
public interface VitalRecordService {

    /**
     * createPatientVitalRecord Saves the Patient vital details
     *
     * @param vitalRecordDto
     * @return Response status with saved Patient vital record
     */
    VitalRecordDto createPatientVitalRecord(VitalRecordDto vitalRecordDto);

    /**
     * Get the Patient vital details based on id
     *
     * @param checkupId
     * @return Requested Patient vital Detail
     */
    VitalRecordDto getVitalInformation(long checkupId);

    /**
     * Update Service for updating the Patient Vital details for the id
     *
     * @param checkupId
     * @param vitalRecordDto
     * @return Updated Patient Detail
     */
    VitalRecordDto updateVitalRecord(long checkupId, VitalRecordDto vitalRecordDto);

    /**
     * Delete the Patient vital details for the id
     *
     * @param checkupId
     * @return No Content
     */
    void deletePatientVitalRecord(long checkupId);

    /**
     * Retrieves all the patient vital details matching the given condition
     *
     * @param orderBy
     * @param page
     * @param limit
     * @return Patient vital list
     */
    List<VitalRecordDto> getAllVitalRecords(int page, int limit, String orderBy);

    /**
     * Search all the vital details matching the given condition
     *
     * @param search
     * @param limit
     * @param page
     * @param orderBy
     * @return List of Patient Details
     */
    List<VitalRecordDto> searchPatientVitalRecords(String search, int page, int limit, String orderBy);
}
