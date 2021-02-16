package com.netmed.patientmodule.controller;

import com.netmed.patientmodule.dto.PatientDto;
import com.netmed.patientmodule.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * PatientController contains Rest End points to perform patient record operations
 *
 * @author Nataraj
 * @created 04/02/2021
 */
@RestController
@RequestMapping("/netmed-patient-api")
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping
    public PatientDto createPatientRecord(@Valid @RequestBody PatientDto patientDto){
        return patientService.createPatientRecord(patientDto);
    }

    @GetMapping(path = "/{patientId}")
    public PatientDto getPatientRecords(@PathVariable long patientId) {
        return patientService.getPatientRecords(patientId);
    }

    @PutMapping(path = "/{patientId}")
    public PatientDto updatePatientRecord(@Valid @RequestBody PatientDto patientDto, @PathVariable long patientId){
        return patientService.updatePatientRecord(patientId, patientDto);
    }

    @DeleteMapping(path = "/{patientId}")
    public void deletePatientRecord(@PathVariable long patientId){
         patientService.deletePatientRecord(patientId);
    }


}
