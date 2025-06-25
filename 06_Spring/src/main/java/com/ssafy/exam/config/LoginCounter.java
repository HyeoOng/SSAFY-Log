package com.ssafy.exam.config;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope("application")
public class LoginCounter {
    private final Map<String, Integer> loginCounts = new HashMap<>();

    // 로그인 횟수 증가
    public void incrementLoginCount(String email) {
        loginCounts.put(email, loginCounts.getOrDefault(email, 0) + 1);
    }

    // 특정 사용자 로그인 횟수 조회
    public int getLoginCount(String email) {
        return loginCounts.getOrDefault(email, 0);
    }
    
    public void resetLoginCount(String email) {
    	loginCounts.remove(email);
	}

    // 모든 사용자 로그인 횟수 조회
    public Map<String, Integer> getAllLoginCounts() {
        return new HashMap<>(loginCounts);
    }
}
