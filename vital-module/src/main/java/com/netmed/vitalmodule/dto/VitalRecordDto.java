package com.netmed.vitalmodule.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * VitalRecordDto is a Dto for Vital Record Entity
 *
 * @author Nataraj
 * @created 15/02/2021
 */
@Data
public class VitalRecordDto implements Serializable, Comparable<VitalRecordDto> {

    /*Serializable is used because of JdkSerializationRedisSerializer used in CacheConfig - For Redis*/
    private static final long serialVersionUID = 6340298171379460246L;

    @NotBlank(message = "Patient Name Should not be null")
    private String patientName;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkupDate;

    @Range(max = 115, message = "Body Temperature should be less than 116 Degree F")
    @Range(min = 95, message = "Body Temperature should be higher than 94 Degree F")
    private float bodyTemperature;

    @Range(max = 100, message = "pulse Rate should be less than 101")
    @Range(min = 60, message = "pulse Rate should be higher than 59")
    private int pulseRate;

    @Range(max = 290, message = "weight should be less than 291 Kg")
    @Range(min = 30, message = "weight should be higher than 29 Kg")
    private int weight;

    @Range(max = 100, message = "spo2Level should be less than 101")
    @Range(min = 60, message = "spo2Level should be higher than 59")
    private int spo2Level;

    @Range(max = 300, message = "Blood Sugar Level should be less than 301 (3 Hours after eating)")
    @Range(min = 120, message = "Blood Sugar Level should be less than 119 (3 Hours after eating)")
    private int bloodSugarLevel;

    @NotBlank(message = "Examiner By Should not be null")
    private String examinerBy;

    @NotBlank(message = "Examiner Name Should not be null")
    private String examinerName;

    private String createdBy;

    private LocalDateTime createdDate;

    private String lastModifiedBy;

    private LocalDateTime lastModifiedDate;

    //Implemented Comparable Interface to Natural Sort(Only ascending - cannot be changed and hence went for comparator) Vital Record list during getallvitalrecords() => getAll Fetch
    @Override
    public int compareTo(VitalRecordDto secondVitalRecordDto) {
        return patientName.compareTo(secondVitalRecordDto.patientName);
    }

    /**
     * Comparator to sort VitalRecord list or array in order of Salary
     */
    public static Comparator<VitalRecordDto> PatientNameComparatorAscending = new Comparator<VitalRecordDto>() {
        @Override
        public int compare(VitalRecordDto o1, VitalRecordDto o2) {
            return o1.getPatientName().compareTo(o2.patientName);
        }
    };

    /**
     * Comparator to sort VitalRecord list or array in order of Salary
     */
    public static Comparator<VitalRecordDto> PatientNameComparatorDescending = new Comparator<VitalRecordDto>() {
        @Override
        public int compare(VitalRecordDto o1, VitalRecordDto o2) {
            return o2.getPatientName().compareTo(o1.patientName);
        }
    };
}
