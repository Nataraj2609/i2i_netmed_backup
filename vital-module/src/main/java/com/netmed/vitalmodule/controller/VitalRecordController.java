package com.netmed.vitalmodule.controller;

import com.netmed.vitalmodule.aop.LogExecutionTime;
import com.netmed.vitalmodule.dto.VitalRecordDto;
import com.netmed.vitalmodule.service.VitalRecordService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/netmed-vital-api/v1")
@RequiredArgsConstructor
public class VitalRecordController {

    private final VitalRecordService vitalRecordService;

    /**
     * Saves the Patient Vital details
     *
     * @param vitalRecordDto
     * @return Response status with saved patient record
     */
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Api Endpoint to create the patient vital details")
    @PostMapping
    @LogExecutionTime
    public VitalRecordDto createPatientVitalRecord(@Valid @RequestBody VitalRecordDto vitalRecordDto) {
        return vitalRecordService.createPatientVitalRecord(vitalRecordDto);
    }

    /**
     * Get the patient viatl details based on id
     *
     * @param checkupId
     * @return Requested patient vital Detail
     */
    @ApiOperation(value = "Api Endpoint to retrieve the Patient Vital details using Checkup Id")
    @GetMapping(path = "/{checkupId}")
    @LogExecutionTime
    public VitalRecordDto getVitalInformation(@PathVariable long checkupId) {
        return vitalRecordService.getVitalInformation(checkupId);
    }

    /**
     * Update the Patient vital details for the id
     *
     * @param checkupId
     * @param vitalRecordDto
     * @return Updated Patient vital Detail
     */
    @ApiOperation(value = "Api Endpoint to update the Patient vital details")
    @PutMapping(path = "/{checkupId}")
    @LogExecutionTime
    public VitalRecordDto updateVitalRecord(@Valid @RequestBody VitalRecordDto vitalRecordDto, @PathVariable long checkupId) {
        return vitalRecordService.updateVitalRecord(checkupId, vitalRecordDto);
    }

    /**
     * Delete the Patient vital details for the id
     *
     * @param checkupId
     * @return No Content status code
     */
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{checkupId}")
    @LogExecutionTime
    public void deletePatientVitalRecord(@PathVariable long checkupId) {
        vitalRecordService.deletePatientVitalRecord(checkupId);
    }

    /**
     * Retrieves all the Patient vital details matching the given condition
     *
     * @param limit
     * @param page
     * @param orderBy
     * @return List of Patient vital Details
     */
    @ApiOperation(value = "Api Endpoint to retrieve all the Patient's Vital details")
    @GetMapping("getAllVitalRecords")
    @LogExecutionTime
    public List<VitalRecordDto> getAllVitalRecords(@RequestParam(name = "page", defaultValue = "0") int page,
                                                   @RequestParam(name = "limit", defaultValue = "10") int limit,
                                                   @RequestParam(name = "orderBy", defaultValue = "asc") String orderBy) {
        return vitalRecordService.getAllVitalRecords(page, limit, orderBy);
    }

    /**
     * Search all the Patient details matching the given condition
     *
     * @param search
     * @param limit
     * @param page
     * @param orderBy
     * @return List of Patient vital Details
     */
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
