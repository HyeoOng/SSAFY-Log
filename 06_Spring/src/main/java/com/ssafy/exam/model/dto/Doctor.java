package com.ssafy.exam.model.dto;

public class Doctor {
	private int doctorId;
	private String name;
	private int age;
	private int specialtyCode;
	private int experienceYears;
	private String specialtyName;

	// Getters and setters
	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSpecialtyCode() {
		return specialtyCode;
	}

	public void setSpecialtyCode(int specialtyCode) {
		this.specialtyCode = specialtyCode;
	}

	public int getExperienceYears() {
		return experienceYears;
	}

	public void setExperienceYears(int experienceYears) {
		this.experienceYears = experienceYears;
	}

	public String getSpecialtyName() {
		return specialtyName;
	}

	public void setSpecialtyName(String specialtyName) {
		this.specialtyName = specialtyName;
	}

	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", name=" + name + ", age=" + age + ", specialtyCode=" + specialtyCode
				+ ", experienceYears=" + experienceYears + ", specialtyName=" + specialtyName + "]";
	}
	
	
}
