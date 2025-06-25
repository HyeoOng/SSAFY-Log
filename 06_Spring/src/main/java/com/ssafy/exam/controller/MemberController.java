package com.ssafy.exam.controller;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.exam.config.LoginCounter;
import com.ssafy.exam.model.dto.Member;
import com.ssafy.exam.model.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/member")
public class MemberController {
	private final LoginCounter loginCounter;
	private final MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService,LoginCounter loginCounter) {
		this.memberService = memberService;
		this.loginCounter = loginCounter;
	}
	
	@PostMapping("/join")
	public ResponseEntity<?> join(@RequestBody Member member){
		if(memberService.addMember(member)) {
			return ResponseEntity.ok(Collections.singletonMap("email", member.getEmail()));
		}
		return ResponseEntity.status(HttpStatusCode.valueOf(500)).body("Member join fail");
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Member member, HttpServletRequest req){
		int count = loginCounter.getLoginCount(member.getEmail());
		if(count >=5) {
			return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("로그인 시도 횟수를 초과했습니다. 비밀번호 변경 후 로그인해주세요");
		}
		// 횟수제한 통과시
		loginCounter.incrementLoginCount(member.getEmail());
		member = memberService.login(member);
		if(member != null) {
			HttpSession session = req.getSession();
			session.setAttribute("loginMember", member);
			loginCounter.resetLoginCount(member.getEmail());
			Map<String,String> returnMap = new HashMap<>();
			returnMap.put("email", member.getEmail());
			returnMap.put("name", member.getName());
			return ResponseEntity.ok(returnMap);
		}
		return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(String.format("로그인에 실패했습니다.(%d회 실패)\n로그인 5회 실패 시 해당 ID는 잠김상태로 변경됩니다.", count+1));
	}
	
	@PostMapping("/logout")
	public void logout(HttpServletRequest req){
		HttpSession session = req.getSession(false);
		if(session!=null) {
			session.invalidate();
		}
	}
}
