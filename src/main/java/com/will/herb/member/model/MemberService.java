package com.will.herb.member.model;

public interface MemberService {
	//아이디 중복관련 상수
	public static final int EXIST_ID = 1;
	public static final int NON_EXIST_ID = 2;
	
	//로그인 관련 상수
	int LOGIN_OK = 1;
	int PWD_DISAGREE = 2;
	int ID_NONE = 3;
	
	
	
	public int insertMember(MemberVO vo);
	int duplicateId(String userid);
	
	
	public int checkLogin(String userid, String pwd);
	
	public MemberVO selectMember(String userid);
}
