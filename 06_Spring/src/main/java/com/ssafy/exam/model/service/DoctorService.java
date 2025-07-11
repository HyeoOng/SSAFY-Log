package com.ssafy.exam.model.service;

import java.util.List;

import com.ssafy.exam.model.dto.Doctor;

public interface DoctorService {
    boolean addDoctor(Doctor doctor);
    List<Doctor> getAllDoctors();
    Doctor getDoctorById(int id);
    boolean updateDoctor(int id, Doctor doctor);
    boolean deleteDoctor(int id);
    List<Doctor> getDoctorsBykwd(List<String> kwd);
}