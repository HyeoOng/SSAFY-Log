package com.ssafy.exam.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.exam.model.dao.DoctorDao;
import com.ssafy.exam.model.dto.Doctor;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorDao doctorDao;

    @Override
    public boolean addDoctor(Doctor doctor) {
        return doctorDao.insertDoctor(doctor) > 0;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorDao.selectAllDoctors();
    }

    @Override
    public Doctor getDoctorById(int id) {
        return doctorDao.selectDoctorById(id);
    }

    @Override
    public boolean updateDoctor(int id, Doctor doctor) {
        doctor.setDoctorId(id);
        return doctorDao.updateDoctor(doctor) > 0;
    }

    @Override
    public boolean deleteDoctor(int id) {
        return doctorDao.deleteDoctor(id) > 0;
    }

	@Override
	public List<Doctor> getDoctorsBykwd(List<String> kwd) {
		return doctorDao.selectDoctorsByKwd(kwd);
	}
    
    
}