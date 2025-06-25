package com.ssafy.exam.model.service;

import com.ssafy.exam.model.dto.Member;

public interface MemberService {
    boolean addMember(Member member);
//    List<Member> getAllMembers();
//    Member getMemberByEmail(String email);
    boolean updateMember(String email, Member member);
    boolean deleteMember(String email);
    Member login(Member member);
}