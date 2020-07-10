package com.will.herb.member.model;

public interface MemberDAO {
	public int insertMember(MemberVO vo);
	public int selectDup(String userid);
	public String loginCheck(String userid);
	public MemberVO selectMember(String userid);
}


