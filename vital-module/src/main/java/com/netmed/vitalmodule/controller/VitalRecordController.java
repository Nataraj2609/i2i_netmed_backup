package com.netmed.vitalmodule.controller;


import com.netmed.vitalmodule.aop.LogExecutionTime;
import com.netmed.vitalmodule.dto.VitalRecordDto;
import com.netmed.vitalmodule.service.VitalRecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * VitalRecordController contains Rest End points to perform operations on Real time Patient's Vital Records
 *
 * @author Nataraj
 * @created 04/02/2021
 */
@RestController
@RequestMapping("/netmed-vital-api")
public class VitalRecordController {

    @Autowired
    VitalRecordService vitalRecordService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public VitalRecordDto createPatientVitalRecord(@Valid @RequestBody VitalRecordDto vitalRecordDto) {
        return vitalRecordService.createPatientVitalRecord(vitalRecordDto);
    }


    @ApiOperation(value = "Api Endpoint to retrieve the Patient Vital details using Checkup Id")
    @GetMapping(path = "/{checkupId}")
    @LogExecutionTime
    public VitalRecordDto getVitalInformation(@PathVariable long checkupId) {
        return vitalRecordService.getVitalInformation(checkupId);
    }

    @PutMapping(path = "/{checkupId}")
    public VitalRecordDto updateVitalRecord(@Valid @RequestBody VitalRecordDto vitalRecordDto, @PathVariable long checkupId) {
        return vitalRecordService.updateVitalRecord(checkupId, vitalRecordDto);
    }


    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{checkupId}")
    public void deletePatientVitalRecord(@PathVariable long checkupId) {
        vitalRecordService.deletePatientVitalRecord(checkupId);
    }


    @ApiOperation(value = "Api Endpoint to retrieve all the Patient's Vital details")
    @GetMapping("getAllVitalRecords")
    @LogExecutionTime
    public List<VitalRecordDto> getAllVitalRecords(@RequestParam(name = "page", defaultValue = "0") int page,
                                                   @RequestParam(name = "limit", defaultValue = "10") int limit,
                                                   @RequestParam(name = "orderBy", defaultValue = "asc") String orderBy) {
        return vitalRecordService.getAllVitalRecords(page, limit, orderBy);
    }


    @ApiOperation(value = "Api Endpoint to search for the Patient Vital record - Only using User name")
    @GetMapping("searchPatientVitalRecords")
    @LogExecutionTime
    public List<VitalRecordDto> searchPatientVitalRecords(@RequestParam(name = "search") String search,
                                                          @RequestParam(name = "page", defaultValue = "0") int page,
                                                          @RequestParam(name = "limit", defaultValue = "10") int limit,
                                                          @RequestParam(name = "orderBy", defaultValue = "asc") String orderBy) {
        return vitalRecordService.searchPatientVitalRecords(search, page, limit, orderBy);
    }
}
