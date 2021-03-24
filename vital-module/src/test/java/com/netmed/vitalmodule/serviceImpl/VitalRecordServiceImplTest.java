package com.netmed.vitalmodule.serviceImpl;

import com.netmed.vitalmodule.client.UserClientProxy;
import com.netmed.vitalmodule.dto.UserDto;
import com.netmed.vitalmodule.dto.VitalRecordDto;
import com.netmed.vitalmodule.model.VitalRecord;
import com.netmed.vitalmodule.repository.VitalRecordRepository;
import com.netmed.vitalmodule.service.VitalRecordService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class VitalRecordServiceImplTest {

    @MockBean
    private VitalRecordRepository vitalRecordRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VitalRecordService vitalRecordService;

    @MockBean
    private UserClientProxy userClientProxy;

    @Test
    void createPatientVitalRecord() {
        VitalRecord vitalRecord = new VitalRecord(999, 2, LocalDate.parse("1995-02-26"), 102.0f, 93, 80, 98, 140, 1, "Hari Prasath", LocalDateTime.parse("2021-03-19T16:17:30.016413500"),
                "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        VitalRecordDto vitalRecordDto = modelMapper.map(vitalRecord, VitalRecordDto.class);
        vitalRecordDto.setPatientName("John Wick");
        when(vitalRecordRepository.existsByPatientId(2)).thenReturn(false);
        when(vitalRecordRepository.save(vitalRecord)).thenReturn(vitalRecord);
        when(userClientProxy.findUserIdByUserName("John Wick")).thenReturn(Long.valueOf(2));
        assertEquals(vitalRecordDto, vitalRecordService.createPatientVitalRecord(vitalRecordDto));
    }

    @Test
    void getVitalInformation() {
        long checkupId = 999;
        VitalRecord vitalRecord = new VitalRecord(checkupId, 2, LocalDate.parse("1995-02-26"), 102.0f, 93, 80, 98, 140, 1, "Hari Prasath", LocalDateTime.parse("2021-03-19T16:17:30.016413500"),
                "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        when(vitalRecordRepository.existsById(checkupId)).thenReturn(true);
        when(vitalRecordRepository.findById(checkupId)).thenReturn(java.util.Optional.of(vitalRecord));
        UserDto patientRecordDto = new UserDto("John Wick", "Flipkart", "Patient", "Doctor Nat", LocalDateTime.parse("2021-03-19T16:17:30.016413500"), "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        UserDto doctorRecordDto = new UserDto("Dr Hari Prasath", "Flipkart", "Doctor", "Doctor Nat", LocalDateTime.parse("2021-03-19T16:17:30.016413500"), "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        when(userClientProxy.getUser(1)).thenReturn(doctorRecordDto);
        when(userClientProxy.getUser(2)).thenReturn(patientRecordDto);
        VitalRecordDto vitalRecordDto = modelMapper.map(vitalRecord, VitalRecordDto.class);
        vitalRecordDto.setPatientName(patientRecordDto.getUserName());
        vitalRecordDto.setExaminerBy(patientRecordDto.getRoleName());
        vitalRecordDto.setExaminerName(patientRecordDto.getUserName());
        assertEquals(vitalRecordDto, vitalRecordService.getVitalInformation(checkupId));
    }

    @Test
    void updateVitalRecord() {
        VitalRecord vitalRecord = new VitalRecord(999, 2, LocalDate.parse("1995-02-26"), 102.0f, 93, 80, 98, 140, 1, "Hari Prasath", LocalDateTime.parse("2021-03-19T16:17:30.016413500"),
                "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        VitalRecordDto vitalRecordDto = modelMapper.map(vitalRecord, VitalRecordDto.class);
        vitalRecordDto.setPatientName("John Wick");
        when(vitalRecordRepository.existsById(Long.valueOf(999))).thenReturn(true);
        when(vitalRecordRepository.findById(Long.valueOf(999))).thenReturn(java.util.Optional.of(vitalRecord));
        when(vitalRecordRepository.save(vitalRecord)).thenReturn(vitalRecord);
        when(userClientProxy.findUserIdByUserName("John Wick")).thenReturn(Long.valueOf(2));
        assertEquals(vitalRecordDto, vitalRecordService.updateVitalRecord(999, vitalRecordDto));
    }

    @Test
    void deletePatientVitalRecord() {
        long checkupId = 999;
        vitalRecordService.deletePatientVitalRecord(checkupId);
        verify(vitalRecordRepository, times(1)).deleteById(checkupId);
    }

    @Test
    void getAllVitalRecords() {
        VitalRecord vitalRecord1 = new VitalRecord(999, 2, LocalDate.parse("1995-02-26"), 102.0f, 93, 80, 98, 140, 2, "Hari Prasath", LocalDateTime.parse("2021-03-19T16:17:30.016413500"),
                "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        VitalRecord vitalRecord2 = new VitalRecord(998, 2, LocalDate.parse("1995-02-26"), 102.0f, 93, 80, 98, 140, 2, "Hari Prasath", LocalDateTime.parse("2021-03-19T16:17:30.016413500"),
                "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        VitalRecord vitalRecord3 = new VitalRecord(997, 2, LocalDate.parse("1995-02-26"), 102.0f, 93, 80, 98, 140, 2, "Hari Prasath", LocalDateTime.parse("2021-03-19T16:17:30.016413500"),
                "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        List<VitalRecord> vitalRecordList = new ArrayList<>(Stream.of(vitalRecord1, vitalRecord2, vitalRecord3)
                .collect(Collectors.toList()));
        Page<VitalRecord> vitalRecordPage = new PageImpl<>(vitalRecordList);

        Pageable pageable = PageRequest.of(0, 3);
        when(vitalRecordRepository.findAll(pageable)).thenReturn(vitalRecordPage);
        UserDto patientRecordDto = new UserDto("John Wick", "Flipkart", "Patient", "Doctor Nat", LocalDateTime.parse("2021-03-19T16:17:30.016413500"), "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        when(userClientProxy.getUser(Long.valueOf(2))).thenReturn(patientRecordDto);

        List<VitalRecordDto> vitalRecordDtoList = vitalRecordList.stream().map(vitalRecord -> modelMapper.map(vitalRecord, VitalRecordDto.class)).collect(Collectors.toList());
        vitalRecordDtoList = vitalRecordDtoList.stream().map(vitalRecordDto -> {
            vitalRecordDto.setPatientName("John Wick");
            vitalRecordDto.setExaminerName("John Wick");
            vitalRecordDto.setExaminerBy("Patient");
            return vitalRecordDto;
        }).collect(Collectors.toList());
        assertEquals(vitalRecordDtoList, vitalRecordService.getAllVitalRecords(0, 3, "des"));
    }
}