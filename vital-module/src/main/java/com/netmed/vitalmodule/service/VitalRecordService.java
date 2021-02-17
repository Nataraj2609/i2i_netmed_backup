package com.netmed.vitalmodule.service;

import com.netmed.vitalmodule.dto.VitalRecordDto;

import java.util.List;

public interface VitalRecordService {

    VitalRecordDto createPatientVitalRecord(VitalRecordDto vitalRecordDto);

    VitalRecordDto getVitalInformation(long checkupId);

    VitalRecordDto updateVitalRecord(long checkupId, VitalRecordDto vitalRecordDto);

    void deletePatientVitalRecord(long checkupId);

    List<VitalRecordDto> getAllVitalRecords(int page, int limit, String orderBy);

    List<VitalRecordDto> searchPatientVitalRecords(String search, int page, int limit, String orderBy);
}
