package com.netmed.vitalmodule.repository;

import com.netmed.vitalmodule.model.VitalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * VitalRecordRepository is a JPA Repository to Persist Patient Vital Entity with DB
 *
 * @author Nataraj
 * @created 14/02/2021
 */
@Repository
public interface VitalRecordRepository extends JpaRepository<VitalRecord, Long>, CustomVitalRecordRepository {

}
