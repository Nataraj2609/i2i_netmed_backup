package com.netmed.patientmodule.repository;

import com.netmed.patientmodule.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PatientRepository is a JPA Repository to Persist Patient Entity with DB
 *
 * @author Nataraj
 * @created 04/02/2021
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>, CustomPatientRepository {

}
