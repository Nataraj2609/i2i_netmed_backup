package com.netmed.vitalmodule.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * VitalRecord is a JPA Entity
 *
 * @author Nataraj
 * @created 16/02/2021
 */
@Entity
@Table(name = "netmed_vital_mst")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VitalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "checkup_id")
    private long checkupId;

    @Column(name = "patient_id")
    private long patientId;

    @Column(name = "checkup_date")
    private LocalDate checkupDate;

    @Column(name = "body_temperature")
    private float bodyTemperature;

    @Column(name = "pulse_rate")
    private int pulseRate;

    @Column(name = "weight")
    private int weight;

    @Column(name = "spo2")
    private int spo2Level;

    @Column(name = "blood_sugar_level")
    private int bloodSugarLevel;

    @Column(name = "examiner_id")
    private long examinerId;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
