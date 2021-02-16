package com.netmed.patientmodule.service;


import com.netmed.patientmodule.dto.PatientDto;

import java.util.List;

/**
 * PatientService is used to perform operations on patient records
 *
 * @author Nataraj
 * @created 04/02/2021
 */
public interface PatientService {

    PatientDto createPatientRecord(PatientDto patientDto);

    PatientDto getPatientRecords(long patientId);

    PatientDto updatePatientRecord(long patientId, PatientDto patientDto);

    void deletePatientRecord(long patientId);

    List<PatientDto> getallpatientrecords(int page, int limit, String orderBy);

    List<PatientDto> searchpatientrecords(String search, int page, int limit, String orderBy);
}
