package com.netmed.vitalmodule.serviceImpl;

import com.netmed.vitalmodule.client.UserClientProxy;
import com.netmed.vitalmodule.dto.UserDto;
import com.netmed.vitalmodule.dto.VitalRecordDto;
import com.netmed.vitalmodule.exception.DuplicateVitalRecordFoundException;
import com.netmed.vitalmodule.exception.UserNotFoundException;
import com.netmed.vitalmodule.exception.VitalRecordNotFoundException;
import com.netmed.vitalmodule.model.VitalRecord;
import com.netmed.vitalmodule.repository.VitalRecordRepository;
import com.netmed.vitalmodule.service.VitalRecordService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * VitalRecordServiceImpl is a Implementation of VitalRecordService
 *
 * @author Nataraj
 * @created 12/02/2021
 */
@Service
@RequiredArgsConstructor
public class VitalRecordServiceImpl implements VitalRecordService {

    private final VitalRecordRepository vitalRecordRepository;

    private final ModelMapper modelMapper;

    private final UserClientProxy userClientProxy;

    @Override
    @CachePut("vital-record")
    public VitalRecordDto createPatientVitalRecord(VitalRecordDto vitalRecordDto) {
        VitalRecord vitalRecord = modelMapper.map(vitalRecordDto, VitalRecord.class);
        long userId = 0, examinerId = 0;
        try {
            userId = userClientProxy.findUserIdByUserName(vitalRecordDto.getPatientName());
            examinerId = userClientProxy.findUserIdByUserName(vitalRecordDto.getExaminerName());
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
        if (vitalRecordRepository.existsByPatientId(userId))
            throw new DuplicateVitalRecordFoundException();
        vitalRecord.setPatientId(userId);
        vitalRecord.setExaminerId(examinerId);
        vitalRecordRepository.save(vitalRecord);
        return vitalRecordDto;
    }

    @Override
    @Cacheable("vital-record")
    public VitalRecordDto getVitalInformation(long checkupId) {
        if (!vitalRecordRepository.existsById(checkupId))
            throw new VitalRecordNotFoundException();
        VitalRecord vitalRecord = vitalRecordRepository.findById(checkupId).get();
        VitalRecordDto vitalRecordDto = modelMapper.map(vitalRecord, VitalRecordDto.class);
        try {
            UserDto patientUserDto = userClientProxy.getUser(vitalRecord.getPatientId());
            vitalRecordDto.setPatientName(patientUserDto.getUserName());
            UserDto examinerUserDto = userClientProxy.getUser(vitalRecord.getExaminerId());
            vitalRecordDto.setExaminerBy(examinerUserDto.getRoleName());
            vitalRecordDto.setExaminerName(examinerUserDto.getUserName());
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
        return vitalRecordDto;
    }

    @Override
    @CachePut("vital-record")
    public VitalRecordDto updateVitalRecord(long checkupId, VitalRecordDto vitalRecordDto) {
        if (!vitalRecordRepository.existsById(checkupId))
            throw new VitalRecordNotFoundException();
        VitalRecord oldVitalRecord = vitalRecordRepository.findById(checkupId).get();
        oldVitalRecord.setBodyTemperature(vitalRecordDto.getBodyTemperature());
        oldVitalRecord.setPulseRate(vitalRecordDto.getPulseRate());
        oldVitalRecord.setWeight(vitalRecordDto.getWeight());
        oldVitalRecord.setSpo2Level(vitalRecordDto.getSpo2Level());
        oldVitalRecord.setBloodSugarLevel(vitalRecordDto.getBloodSugarLevel());
        long userId = 0, examinerId = 0;
        try {
            userId = userClientProxy.findUserIdByUserName(vitalRecordDto.getPatientName());
            examinerId = userClientProxy.findUserIdByUserName(vitalRecordDto.getExaminerName());
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
        oldVitalRecord.setPatientId(userId);
        oldVitalRecord.setExaminerId(examinerId);
        vitalRecordRepository.save(oldVitalRecord);
        return vitalRecordDto;
    }

    @Override
    @CacheEvict("vital-record")
    public void deletePatientVitalRecord(long checkupId) {
        try {
            vitalRecordRepository.deleteById(checkupId);
        } catch (Exception e) {
            throw new VitalRecordNotFoundException();
        }
    }

    @Override
    @Cacheable("vital-record")
    public List<VitalRecordDto> getAllVitalRecords(int page, int limit, String orderBy) {
        Pageable pageable = PageRequest.of(page, limit);
        List<VitalRecord> vitalRecordList = vitalRecordRepository.findAll(pageable).toList();
        List<VitalRecordDto> vitalRecordDtoList = vitalRecordList.stream().map(vitalRecord ->
                modelMapper.map(vitalRecord, VitalRecordDto.class)).collect(Collectors.toList());

        vitalRecordList.stream().forEach(vitalRecord -> {
            try {
                UserDto patientUserDto = userClientProxy.getUser(vitalRecord.getPatientId());
                int index = vitalRecordList.indexOf(vitalRecord);

                vitalRecordDtoList.get(index).setPatientName(patientUserDto.getUserName());
                UserDto examinerUserDto = userClientProxy.getUser(vitalRecord.getExaminerId());
                vitalRecordDtoList.get(index).setExaminerBy(examinerUserDto.getRoleName());
                vitalRecordDtoList.get(index).setExaminerName(examinerUserDto.getUserName());
            } catch (Exception e) {
                throw new UserNotFoundException();
            }
        });

        if (orderBy.equals("des")) {
            Collections.sort(vitalRecordDtoList, VitalRecordDto.PatientNameComparatorDescending);
        } else {
            Collections.sort(vitalRecordDtoList, VitalRecordDto.PatientNameComparatorAscending);
        }
        return vitalRecordDtoList;
    }

    @Override
    @Cacheable("vital-record")
    public List<VitalRecordDto> searchPatientVitalRecords(String search, int page, int limit, String orderBy) {
        Pageable pageable = PageRequest.of(page, limit);
        List<VitalRecord> vitalRecordList = vitalRecordRepository.findAll(pageable).toList();
        List<VitalRecordDto> vitalRecordDtoList = vitalRecordList.stream().map(vitalRecord ->
                modelMapper.map(vitalRecord, VitalRecordDto.class)).collect(Collectors.toList());

        vitalRecordList.stream().forEach(vitalRecord -> {
            try {
                UserDto patientUserDto = userClientProxy.getUser(vitalRecord.getPatientId());
                int index = vitalRecordList.indexOf(vitalRecord);

                vitalRecordDtoList.get(index).setPatientName(patientUserDto.getUserName());
                UserDto examinerUserDto = userClientProxy.getUser(vitalRecord.getExaminerId());
                vitalRecordDtoList.get(index).setExaminerBy(examinerUserDto.getRoleName());
                vitalRecordDtoList.get(index).setExaminerName(examinerUserDto.getUserName());
            } catch (Exception e) {
                throw new UserNotFoundException();
            }
        });

        if (orderBy.equals("des")) {
            return vitalRecordDtoList.stream().filter(vitalRecordDto -> vitalRecordDto.getPatientName().startsWith(search))
                    .sorted(VitalRecordDto.PatientNameComparatorDescending).collect(Collectors.toList());
        } else {
            return vitalRecordDtoList.stream().filter(vitalRecordDto -> vitalRecordDto.getPatientName().startsWith(search))
                    .sorted(VitalRecordDto.PatientNameComparatorAscending).collect(Collectors.toList());
        }
    }
}