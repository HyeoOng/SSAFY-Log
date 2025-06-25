package com.ssafy.exam.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.exam.model.dao.SpecialtyDao;
import com.ssafy.exam.model.dto.Specialty;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {
    @Autowired
    private SpecialtyDao specialtyDao;

    @Override
    public boolean addSpecialty(Specialty specialty) {
        return specialtyDao.insertSpecialty(specialty) > 0;
    }

    @Override
    public Specialty getSpecialtyByCode(int code) {
        return specialtyDao.selectSpecialtyByCode(code);
    }

    @Override
    public boolean deleteSpecialty(int code) {
        return specialtyDao.deleteSpecialty(code) > 0;
    }

    @Override
    public List<Specialty> getAllSpecialties() {
        return specialtyDao.selectAll();
    }
}