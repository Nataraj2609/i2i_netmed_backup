package com.netmed.patientmodule.serviceimpl;

import com.netmed.patientmodule.client.UserClientProxy;
import com.netmed.patientmodule.dto.PatientDto;
import com.netmed.patientmodule.exception.DuplicatePatientRecordFoundException;
import com.netmed.patientmodule.exception.PatientDetailsNotFoundException;
import com.netmed.patientmodule.exception.UserNotFoundException;
import com.netmed.patientmodule.model.Patient;
import com.netmed.patientmodule.repository.PatientRepository;
import com.netmed.patientmodule.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * PatientServiceImpl is a Implementation of PatientService
 *
 * @author Nataraj
 * @created 04/02/2021
 */
@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserClientProxy userClientProxy;

    /**
     * createPatientRecord Saves the Patient details
     *
     * @param patientDto
     * @return Response status with saved Patient record
     */
    @Override
    @CachePut(value = "patient")
    public PatientDto createPatientRecord(PatientDto patientDto) {
        Patient patient = modelMapper.map(patientDto, Patient.class);
        if (patientRepository.existsByUserId(patient.getUserId()))
            throw new DuplicatePatientRecordFoundException();
        try {
            long userId = userClientProxy.findUserIdByUserName(patientDto.getUserName());
            patient.setUserId(userId);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
        patientRepository.save(patient);
        return modelMapper.map(patient, PatientDto.class);
    }

    /**
     * Get the Patient details based on id
     *
     * @param patientId
     * @return Requested Patient Detail
     */
    @Override
    @Cacheable(value = "patient")
    public PatientDto getPatientRecords(long patientId) {
        Optional<Patient> patientEntity = patientRepository.findById(patientId);
        if (!patientEntity.isPresent())
            throw new PatientDetailsNotFoundException();
        PatientDto patientRecord = modelMapper.map(patientEntity.get(), PatientDto.class);
        return patientRecord;
    }

    /**
     * Update Service for updating the Patient details for the id
     *
     * @param patientId
     * @param patientDto
     * @return Updated Patient Detail
     */
    @Override
    @CachePut(value = "patient")
    public PatientDto updatePatientRecord(long patientId, PatientDto patientDto) {
        if (!patientRepository.existsById(patientId))
            throw new PatientDetailsNotFoundException();
        Patient oldPatientRecord = patientRepository.findById(patientId).get();
        Patient patient = modelMapper.map(patientDto, Patient.class);
        oldPatientRecord.setUserName(patient.getUserName());
        oldPatientRecord.setDob(patient.getDob());
        oldPatientRecord.setAge(patient.getAge());
        oldPatientRecord.setGender(patient.getGender());
        oldPatientRecord.setMobileNumber(patient.getMobileNumber());
        oldPatientRecord.setEmail(patient.getEmail());
        oldPatientRecord.setAddress(patient.getAddress());
        oldPatientRecord.setZipcode(patient.getZipcode());
        oldPatientRecord.setSubscriptionStartDate(patient.getSubscriptionStartDate());
        oldPatientRecord.setSubscriptionEndDate(patient.getSubscriptionEndDate());
        patientRepository.save(oldPatientRecord);
        return patientDto;
    }

    /**
     * Delete the Patient details for the id
     *
     * @param patientId
     * @return No Content
     */
    @Override
    @CacheEvict(value = "patient")
    public void deletePatientRecord(long patientId) {
        try {
            patientRepository.deleteById(patientId);
        } catch (Exception e) {
            throw new PatientDetailsNotFoundException();
        }
    }

    /**
     * Retrieves all the patient details matching the given condition
     *
     * @param orderBy
     * @param page
     * @param limit
     * @return Patient list
     */
    @Override
    @Cacheable(value = "patient")
    public List<PatientDto> getallpatientrecords(int page, int limit, String orderBy) {
        Sort.Direction sortDirection = orderBy.equals("des") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "userName"));
        List<Patient> patientList = patientRepository.findAll(pageable).toList();
        return patientList.stream().map(patient -> modelMapper.map(patient, PatientDto.class)).collect(Collectors.toList());
    }

    /**
     * Search all the user details matching the given condition
     *
     * @param search
     * @param limit
     * @param page
     * @param orderBy
     * @return List of Patient Details
     */
    @Override
    @Cacheable(value = "patient")
    public List<PatientDto> searchpatientrecords(String search, int page, int limit, String orderBy) {
        Sort.Direction sortDirection = orderBy.equals("des") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "userName"));
        List<Patient> patientList = patientRepository.findByUserName(search, pageable).toList();
        return patientList.stream().map(patient -> modelMapper.map(patient, PatientDto.class)).collect(Collectors.toList());
    }
}
