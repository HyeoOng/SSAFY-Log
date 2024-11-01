package com.ssafy.exam.model.dao;

import java.util.Map;
import com.ssafy.exam.model.dto.Member;

public interface MemberDao {
    int insertMember(Member member);
    Member login(Member member);
    Member selectMemberByEmail(String email);
    int updateMember(Member member);
    int deleteMember(String email);
    int insertMemberSalt(Map<String, String> info);
	String seletMemberSaltByEmail(String email);
}
