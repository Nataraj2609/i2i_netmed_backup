package com.netmed.vitalmodule.repository;

/**
 * CustomVitalRecordRepository is a JPA Repository comprising Custom Queries
 *
 * @author Nataraj
 * @created 14/02/2021
 */
public interface CustomVitalRecordRepository {

    /**
     * existsByPatientId method is used to know whether patient exists or not
     *
     * @param patientId
     * @return boolean
     */
    boolean existsByPatientId(long patientId);
}
