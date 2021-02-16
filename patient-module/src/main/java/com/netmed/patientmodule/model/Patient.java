package com.netmed.patientmodule.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Patient is a JPA Entity
 *
 * @author Nataraj
 * @created 04/02/2021
 */
@Entity
@Table(name = "netmed_patient_mst")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "patient_id")
    private long patientId;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    private int gender;

    @Column(name = "mobile")
    private String mobileNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "zipcode")
    private int zipcode;

    @Column(name = "subscription_start_date")
    private LocalDate subscriptionStartDate;

    @Column(name = "subscription_end_date")
    private LocalDate subscriptionEndDate;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
