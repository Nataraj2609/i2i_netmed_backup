package com.netmed.patientmodule.repository;

import com.netmed.patientmodule.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * PatientRepository is a JPA Repository to Persist Patient Entity with DB
 *
 * @author Nataraj
 * @created 04/02/2021
 */
public interface CustomPatientRepository {

    /**
     * existsByUserId used to know occurrence of User Record
     *
     * @param userId
     * @return
     */
    boolean existsByUserId(long userId);

    /**
     * findByUserName fetches User with User name
     *
     * @param search
     * @param pageable
     * @return
     */
    Page<Patient> findByUserName(String search, Pageable pageable);
}
