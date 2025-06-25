package com.ssafy.exam.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.exam.model.dao.MemberDao;
import com.ssafy.exam.model.dto.Member;
import com.ssafy.exam.util.OpenCrypt;

@Service
public class MemberServiceImpl implements MemberService{
	private final MemberDao memberDao;
	
	@Autowired
	public MemberServiceImpl(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Transactional
	@Override
	public boolean addMember(Member member) {
		String salt = OpenCrypt.encryptPw(member);
		Map<String, String> info = new HashMap<>();
		info.put("email", member.getEmail());
		info.put("salt", salt);
        try {
        	if (memberDao.insertMember(member)==1 && memberDao.insertMemberSalt(info)==1) return true;
		} catch (Exception e) {
			System.out.println("memberDao - addMember에러");
		}
        return false;
	}

	@Override
	public Member login(Member member) {
		String salt = memberDao.seletMemberSaltByEmail(member.getEmail());
		if(salt !=null) {
			String hashedPassword = OpenCrypt.byteArrayToHex(OpenCrypt.getSHA256(member.getPassword(), salt));
			member.setPassword(hashedPassword);
			member = memberDao.login(member);
			if(member !=null) {
				member.setPassword("");
				return member;
			}else {
				return null;
			}
		}
		return null;
	}

	@Override
	public boolean updateMember(String email, Member member) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteMember(String email) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
