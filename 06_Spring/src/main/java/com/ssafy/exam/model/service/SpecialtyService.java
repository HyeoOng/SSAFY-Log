package com.ssafy.exam.model.service;

import java.util.List;

import com.ssafy.exam.model.dto.Specialty;

public interface SpecialtyService {
    boolean addSpecialty(Specialty specialty);
    Specialty getSpecialtyByCode(int code);
    boolean deleteSpecialty(int code);
    List<Specialty> getAllSpecialties();
}