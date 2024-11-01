package com.ssafy.exam.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.exam.model.dto.Doctor;
import com.ssafy.exam.model.service.DoctorService;

@RestController
@RequestMapping("/api/doctor")
public class DoctorRestController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<String> addDoctor(@RequestBody Doctor doctor) {
        boolean isAdded = doctorService.addDoctor(doctor);
        if (isAdded) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Doctor added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add doctor");
        }
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        if (doctors != null && !doctors.isEmpty()) {
            return ResponseEntity.ok(doctors);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable int id) {
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor != null) {
            return ResponseEntity.ok(doctor);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDoctor(@PathVariable int id, @RequestBody Doctor doctor) {
        boolean isUpdated = doctorService.updateDoctor(id, doctor);
        if (isUpdated) {
            return ResponseEntity.ok("Doctor updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update doctor");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable int id) {
        boolean isDeleted = doctorService.deleteDoctor(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Doctor deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete doctor");
        }
    }
    @PostMapping("/ai")
    public ResponseEntity<List<Doctor>> getDoctorsByKwd(@RequestBody HashMap<String, Object> map) {
    	List<Doctor> doctors = doctorService.getDoctorsBykwd((List)map.get("kwd"));
        if (doctors != null && !doctors.isEmpty()) {
            return ResponseEntity.ok(doctors);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}