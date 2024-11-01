package com.ssafy.exam.controller;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import com.ssafy.exam.model.dto.AiDoctor;

@RestController
@RequestMapping("/api/ai")
public class AIDoctorController {
	
	private final OpenAiChatModel openAiChatModel;
	
	public AIDoctorController(OpenAiChatModel openAiChatModel) {
		super();
		this.openAiChatModel = openAiChatModel;
	}

	@PostMapping
	public ResponseEntity<?> aiDoctor(@RequestBody AiDoctor doctor) {
		String result = openAiChatModel.call(doctor.getQuestionWithP());
		if("X".equals(result)) 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("증상을 제대로 다시 입력해주세요.");
		String[] response = result.split("\n", 2);
		doctor.setResult(response[0].trim());
		doctor.setSpecialties(Arrays.asList(response[1].trim().split("#")));
		return ResponseEntity.ok(doctor);
	}

}
