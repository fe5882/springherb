package com.will.herb.member.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOMybatis implements MemberDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	private String namespace = "config.mybatos.mapper.oracle.member.";
	
	public int insertMember(MemberVO vo){
		return sqlSessionTemplate.insert(namespace+"insertMember", vo);
	}
	
	public int selectDup(String userid){
		return sqlSessionTemplate.selectOne(namespace+"selectDup", userid);
	}
	
	
	
	public String loginCheck(String userid) {
		return sqlSessionTemplate.selectOne(namespace+"checkPwd", userid);	
	}
	
	
	public MemberVO selectMember(String userid) {
		return sqlSessionTemplate.selectOne(namespace+"selectMember", userid);
	}
	/*
	
	
	
	
	}
	
	public int editMember(MemberVO vo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = pool.getConnection();
			String sql = "update member" + 
					" set email = ?, hp = ?, zipcode = ?, address = ?, addressdetail = ?" + 
					" where userid = ?";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, vo.getEmail());
			ps.setString(2, vo.getHp());
			ps.setString(3, vo.getZipcode());
			ps.setString(4, vo.getAddress());
			ps.setString(5, vo.getAddressDetail());
			ps.setString(6, vo.getUserid());
			
			int cnt = ps.executeUpdate();
			System.out.println("업데이트 완료 cnt = " + cnt + ", 매개변수 vo = " + vo);
			
			return cnt;
			
		}finally {
			pool.dbClose(con, ps);
			
		}
		
		
	}
	
	public int updateOutdate(String userid) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = pool.getConnection();
			String sql = "update member" + 
					" set outdate = sysdate" + 
					" where userid = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			
			int cnt = ps.executeUpdate();
			System.out.println("cnt = " + cnt + ", 매개변수 userid = " + userid);
			return cnt;
		}finally {
			pool.dbClose(con, ps);
			
		}
	}
	
	*/
}




















