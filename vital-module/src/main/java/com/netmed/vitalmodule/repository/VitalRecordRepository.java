package com.netmed.vitalmodule.repository;

import com.netmed.vitalmodule.model.VitalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VitalRecordRepository extends JpaRepository<VitalRecord, Long> {

    boolean existsByPatientId(long patientId);

}
