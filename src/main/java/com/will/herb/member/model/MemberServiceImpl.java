package com.will.herb.member.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAO dao;
	
	public int insertMember(MemberVO vo) {
		return dao.insertMember(vo);
	}

	@Override
	public int duplicateId(String userid) {
		int count = dao.selectDup(userid);
		int result = 0;
		
		if(dao.selectDup(userid) > 0) {
			result = MemberService.EXIST_ID;	//이미 해당아이디 존재
		}else {
			result = MemberService.NON_EXIST_ID;
		}
		
		return result;
	}

	@Override
	public int checkLogin(String userid, String pwd) {
		int result = 0;
		String dbPwd = dao.loginCheck(userid);
		if(dbPwd != null && !dbPwd.isEmpty()) {
			if(dbPwd.equals(pwd)) {
				result = MemberService.LOGIN_OK;
			}else {
				result = MemberService.PWD_DISAGREE;
			}
		
		}else {
			result = MemberService.ID_NONE;
		}
		
		
		return result;
	}

	@Override
	public MemberVO selectMember(String userid) {
		return dao.selectMember(userid);
	}

	
	
	
	
	
	
	 
	

	
	
}
