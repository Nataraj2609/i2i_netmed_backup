package com.netmed.patientmodule.serviceimpl;

import com.netmed.patientmodule.client.UserClientProxy;
import com.netmed.patientmodule.config.RabbitMqConfig;
import com.netmed.patientmodule.dto.PatientDto;
import com.netmed.patientmodule.dto.UserDto;
import com.netmed.patientmodule.exception.DuplicatePatientRecordFoundException;
import com.netmed.patientmodule.exception.PatientDetailsNotFoundException;
import com.netmed.patientmodule.exception.UserNotFoundException;
import com.netmed.patientmodule.model.Patient;
import com.netmed.patientmodule.repository.PatientRepository;
import com.netmed.patientmodule.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    private final ModelMapper modelMapper;

    private final UserClientProxy userClientProxy;

    private final RabbitTemplate rabbitTemplate;

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

    @Override
    @Cacheable(value = "patient")
    public PatientDto getPatientRecords(long patientId) {
        Optional<Patient> patientEntity = patientRepository.findById(patientId);
        if (!patientEntity.isPresent())
            throw new PatientDetailsNotFoundException();
        PatientDto patientRecord = modelMapper.map(patientEntity.get(), PatientDto.class);
        return patientRecord;
    }

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

    @Override
    @CacheEvict(value = "patient")
    public void deletePatientRecord(long patientId) {
        try {
            patientRepository.deleteById(patientId);
        } catch (Exception e) {
            throw new PatientDetailsNotFoundException();
        }
    }

    @Override
    @Cacheable(value = "patient")
    public List<PatientDto> getAllPatientRecords(int page, int limit, String orderBy) {
        Sort.Direction sortDirection = orderBy.equals("des") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "userName"));
        List<Patient> patientList = patientRepository.findAll(pageable).toList();
        return patientList.stream().map(patient -> modelMapper.map(patient, PatientDto.class)).collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "patient")
    public List<PatientDto> searchPatientRecords(String search, int page, int limit, String orderBy) {
        Sort.Direction sortDirection = orderBy.equals("des") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "userName"));
        List<Patient> patientList = patientRepository.findByUserName(search, pageable).toList();
        return patientList.stream().map(patient -> modelMapper.map(patient, PatientDto.class)).collect(Collectors.toList());
    }

    /**
     * Listener function to listen for RabbitMq Messages
     *
     * @param userDto
     */
    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME)
    public void messageListener(UserDto userDto) {
        System.out.println("RabbitMq ON");
        System.out.println();
        System.out.println("NEW USER: " + userDto.getUserName() + " has been created by "
                + userDto.getCreatedBy());
        System.out.println("Please book an appointment to Collect Patient and Vital Details");
        System.out.println();
        System.out.println("RabbitMq OFF");
    }
}