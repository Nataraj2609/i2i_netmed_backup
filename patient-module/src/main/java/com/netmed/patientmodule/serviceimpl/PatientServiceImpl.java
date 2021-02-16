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
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public PatientDto createPatientRecord(PatientDto patientDto) {
        Patient patient = modelMapper.map(patientDto, Patient.class);
        if (patientRepository.existsByUserId(patient.getUserId()))
            throw new DuplicatePatientRecordFoundException();
        try{
            long userId = userClientProxy.findUserIdByUserName(patientDto.getUserName());
            patient.setUserId(userId);
        }catch (Exception e){
            throw new UserNotFoundException();
        }
        patientRepository.save(patient);
        return modelMapper.map(patient, PatientDto.class);
    }

    @Override
    public PatientDto getPatientRecords(long patientId) {
        Optional<Patient> patientEntity = patientRepository.findById(patientId);
        if (!patientEntity.isPresent())
            throw new PatientDetailsNotFoundException();
        PatientDto patientRecord = modelMapper.map(patientEntity.get(), PatientDto.class);
        return patientRecord;
    }

    @Override
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

    @Override
    public void deletePatientRecord(long patientId) {
        try {
            patientRepository.deleteById(patientId);
        } catch (Exception e) {
            throw new PatientDetailsNotFoundException();
        }
    }


}
