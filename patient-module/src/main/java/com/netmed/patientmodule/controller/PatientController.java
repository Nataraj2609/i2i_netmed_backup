package com.netmed.patientmodule.controller;

import com.netmed.patientmodule.aop.LogExecutionTime;
import com.netmed.patientmodule.dto.PatientDto;
import com.netmed.patientmodule.service.PatientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * PatientController contains Rest End points to perform patient record operations
 *
 * @author Nataraj
 * @created 04/02/2021
 */
@RestController
@RequestMapping("/netmed-patient-api/v1")
public class PatientController {

    @Autowired
    PatientService patientService;

    /**
     * Saves the Patient details
     *
     * @param patientDto
     * @return Response status with saved patient record
     */
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Api Endpoint to create the patient details")
    @PostMapping
    @LogExecutionTime
    public PatientDto createPatientRecord(@Valid @RequestBody PatientDto patientDto) {
        return patientService.createPatientRecord(patientDto);
    }

    /**
     * Get the patient details based on id
     *
     * @param patientId
     * @return Requested patient Detail
     */
    @ApiOperation(value = "Api Endpoint to get the patient details")
    @GetMapping(path = "/{patientId}")
    @LogExecutionTime
    public PatientDto getPatientRecords(@PathVariable long patientId) {
        return patientService.getPatientRecords(patientId);
    }

    /**
     * Update the Patient details for the id
     *
     * @param patientDto
     * @return Requested Patient Detail
     */
    @ApiOperation(value = "Api Endpoint to update the Patient details")
    @PutMapping(path = "/{patientId}")
    @LogExecutionTime
    public PatientDto updatePatientRecord(@Valid @RequestBody PatientDto patientDto, @PathVariable long patientId) {
        return patientService.updatePatientRecord(patientId, patientDto);
    }

    /**
     * Delete the Patient details for the id
     *
     * @param patientId
     * @return No Content
     */
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Api Endpoint to delete the Patient details")
    @DeleteMapping(path = "/{patientId}")
    @LogExecutionTime
    public void deletePatientRecord(@PathVariable long patientId) {
        patientService.deletePatientRecord(patientId);
    }

    /**
     * Retrieves all the Patient details matching the given condition
     *
     * @param limit
     * @param page
     * @param orderBy
     * @return List of Patient Details
     */
    @ApiOperation(value = "Api Endpoint to retrieve all the Patient details")
    @GetMapping("getallpatientrecords")
    @LogExecutionTime
    public List<PatientDto> getallpatientrecords(@RequestParam(name = "page", defaultValue = "0") int page,
                                                 @RequestParam(name = "limit", defaultValue = "10") int limit,
                                                 @RequestParam(name = "orderBy", defaultValue = "asc") String orderBy) {
        return patientService.getallpatientrecords(page, limit, orderBy);
    }

    /**
     * Search all the Patient details matching the given condition
     *
     * @param search
     * @param limit
     * @param page
     * @param orderBy
     * @return List of Patient Details
     */
    @ApiOperation(value = "Api Endpoint to search for the Patient record - Only using User name")
    @GetMapping("searchpatientrecords")
    @LogExecutionTime
    public List<PatientDto> searchpatientrecords(@RequestParam(name = "search") String search,
                                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                                 @RequestParam(name = "limit", defaultValue = "10") int limit,
                                                 @RequestParam(name = "orderBy", defaultValue = "asc") String orderBy) {
        return patientService.searchpatientrecords(search, page, limit, orderBy);
    }
}
