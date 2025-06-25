package com.ssafy.exam.model.dto;

import java.time.LocalDateTime;

public class Member {
    private String email;            // 사용자 이메일
    private String password;         // 사용자 비밀번호
    private String name;             // 사용자 이름
    private Role role;               // 사용자 역할
    private LocalDateTime createdAt; // 사용자 생성 시간
    private LocalDateTime updatedAt; // 사용자 정보 수정 시간

    // 역할을 구분하기 위한 enum 타입
    public static enum Role {
        ADMIN("admin"),
        STAFF("staff"),
        PATIENT("patient");

        private final String role;

        Role(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }

        public static Role fromString(String role) {
            for (Role r : Role.values()) {
                if (r.role.equalsIgnoreCase(role)) {
                    return r;
                }
            }
            throw new IllegalArgumentException("Unknown role: " + role);
        }
    }

    // 기본 생성자
    public Member() {
    	this.role = Role.PATIENT;
    }

    // 모든 필드를 포함한 생성자
    public Member(String email, String password, String name, String role, LocalDateTime createdAt, LocalDateTime updatedAt) {
    	System.out.println("생성자주입");
        setEmail(email);
        setPassword(password);
        setName(name);
        setRole(role);
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role == null || role.isEmpty()) {
            role = "patient";
        }
        this.role = Role.fromString(role);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

	@Override
	public String toString() {
		return "Member [email=" + email + ", password=" + password + ", name=" + name + ", role=" + role
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
}
