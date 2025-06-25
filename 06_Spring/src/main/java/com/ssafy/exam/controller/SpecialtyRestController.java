package com.ssafy.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.exam.model.dto.Specialty;
import com.ssafy.exam.model.service.SpecialtyService;

@RestController
@RequestMapping("/api/specialty")
public class SpecialtyRestController {
    @Autowired
    private SpecialtyService specialtyService;

    @PostMapping
    public ResponseEntity<String> addSpecialty(@RequestBody Specialty specialty) {
        boolean isAdded = specialtyService.addSpecialty(specialty);
        if (isAdded) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Specialty added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add specialty");
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Specialty>> getAllSpecialties(){
        List<Specialty> specialties = specialtyService.getAllSpecialties();
         if (specialties != null && !specialties.isEmpty()) {
             return ResponseEntity.ok(specialties);
         } else {
             return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
         }
    }

    @GetMapping("/{code}")
    public ResponseEntity<Specialty> getSpecialty(@PathVariable int code) {
        Specialty specialty = specialtyService.getSpecialtyByCode(code);
        if (specialty != null) {
            return ResponseEntity.ok(specialty);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<String> deleteSpecialty(@PathVariable int code) {
        boolean isDeleted = specialtyService.deleteSpecialty(code);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Specialty deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete specialty");
        }
    }
}