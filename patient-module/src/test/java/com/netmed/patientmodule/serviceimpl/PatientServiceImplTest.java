package com.netmed.patientmodule.serviceimpl;

import com.netmed.patientmodule.client.UserClientProxy;
import com.netmed.patientmodule.dto.PatientDto;
import com.netmed.patientmodule.model.Patient;
import com.netmed.patientmodule.repository.PatientRepository;
import com.netmed.patientmodule.service.PatientService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * PatientServiceImplTest class is used to do unit testing for Business logic of Patient Micro Service Module
 *
 * @author Nataraj M
 * @CreatedOn 23 March 2021
 */
@SpringBootTest
class PatientServiceImplTest {

    /**
     * The Patient repository
     **/
    @MockBean
    private PatientRepository patientRepository;

    /**
     * The Model mapper
     **/
    @Autowired
    private ModelMapper modelMapper;

    /**
     * The Patient service
     **/
    @Autowired
    private PatientService patientService;

    /**
     * The User Client Proxy to do Feign call
     **/
    @MockBean
    private UserClientProxy userClientProxy;

    /**
     * createPatientRecordTest method is used to do unit testing for createPatientRecord() Business logic in service class
     */
    @Test
    void createPatientRecordTest() {
        Patient patient = new Patient(0, 2, "John Wick",
                LocalDate.parse("1995-09-26"), 25, 0, "9842482716",
                "nataraj2609@gmail.com", "219 Florida", 608602,
                LocalDate.parse("2020-09-26"), LocalDate.parse("2030-09-26"),
                "Doctor hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"),
                "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        when(userClientProxy.findUserIdByUserName(patient.getUserName())).thenReturn(Long.parseLong("2"));
        when(patientRepository.save(patient)).thenReturn(patient);
        PatientDto patientDto = modelMapper.map(patient, PatientDto.class);
        assertEquals(patientDto, patientService.createPatientRecord(patientDto));
    }

    /**
     * getPatientRecordsTest method is used to test business logic of getPatientRecords()
     **/
    @Test
    void getPatientRecordsTest() {
        long patientId = 111;
        Patient patient = new Patient(patientId, 2, "John Wick",
                LocalDate.parse("1995-09-26"), 25, 0, "9842482716",
                "nataraj2609@gmail.com", "219 Florida", 608602,
                LocalDate.parse("2020-09-26"), LocalDate.parse("2030-09-26"),
                "Doctor hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"),
                "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        when(patientRepository.findById(patientId)).thenReturn(java.util.Optional.of(patient));
        PatientDto patientDto = modelMapper.map(patient, PatientDto.class);
        assertEquals(patientDto, patientService.getPatientRecords(patientId));
    }

    /**
     * updatePatientRecordTest method is used to do unit testing for updatePatientRecord() Business logic in service class
     */
    @Test
    void updatePatientRecordTest() {
        long patientId = 111;
        Patient oldPatientRecord = new Patient(patientId, 2, "John Wick",
                LocalDate.parse("1995-09-26"), 25, 0, "9842482716",
                "nataraj2609@gmail.com", "219 Florida", 608602,
                LocalDate.parse("2020-09-26"), LocalDate.parse("2030-09-26"),
                "Doctor hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"),
                "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        when(patientRepository.existsById(patientId)).thenReturn(Boolean.TRUE);
        when(patientRepository.findById(patientId)).thenReturn(java.util.Optional.of(oldPatientRecord));
        Patient newPatientRecord = new Patient(patientId, 2, "John Wick",
                LocalDate.parse("1995-09-26"), 25, 0, "9842482716",
                "nataraj2609@gmail.com", "UPDATED ADDRESS", 608602,
                LocalDate.parse("2020-09-26"), LocalDate.parse("2030-09-26"),
                "Doctor hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"),
                "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        PatientDto patientDto = modelMapper.map(newPatientRecord, PatientDto.class);
        assertEquals(patientDto, patientService.updatePatientRecord(patientId, patientDto));
    }

    /**
     * deletePatientRecordTest method is used to do unit testing for deletePatientRecord() Business logic in service class
     */
    @Test
    void deletePatientRecordTest() {
        long patientId = 999;
        patientService.deletePatientRecord(patientId);
        verify(patientRepository, times(1)).deleteById(patientId);
    }

    /**
     * getAllPatientRecordsTest method is used to do unit testing for getAllPatientRecords() Business logic in service class
     */
    @Test
    void getAllPatientRecordsTest() {
        Patient patient1 = new Patient(1, 2, "John Wick",
                LocalDate.parse("1995-09-26"), 25, 0, "9842482716",
                "nataraj2609@gmail.com", "UPDATED ADDRESS", 608602,
                LocalDate.parse("2020-09-26"), LocalDate.parse("2030-09-26"),
                "Doctor hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"),
                "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        Patient patient2 = new Patient(1, 2, "John Wick",
                LocalDate.parse("1995-09-26"), 25, 0, "9842482716",
                "nataraj2609@gmail.com", "UPDATED ADDRESS", 608602,
                LocalDate.parse("2020-09-26"), LocalDate.parse("2030-09-26"),
                "Doctor hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"),
                "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        Patient patient3 = new Patient(1, 2, "John Wick",
                LocalDate.parse("1995-02-26"), 25, 0, "9842482716",
                "nataraj2609@gmail.com", "UPDATED ADDRESS", 608602,
                LocalDate.parse("2020-09-26"), LocalDate.parse("2030-09-26"),
                "Doctor hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"),
                "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        List<Patient> patientList = new ArrayList<>(Stream.of(patient1, patient2, patient3)
                .collect(Collectors.toList()));
        Page<Patient> patientPage = new PageImpl<>(patientList);

        Sort.Direction sortDirection = Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(0, 3, Sort.by(sortDirection, "userName"));
        when(patientRepository.findAll(pageable)).thenReturn(patientPage);

        List<PatientDto> patientDtoList = patientList.stream().map(patient -> modelMapper.map(patient, PatientDto.class)).collect(Collectors.toList());
        assertEquals(patientDtoList, patientService.getAllPatientRecords(0, 3, "des"));
    }
}