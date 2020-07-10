package com.will.herb.zipcode.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ZipcodeDAOMybatis implements ZipcodeDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	private String namespace = "config.mybatos.mapper.oracle.zipcode.";
	
	public List<ZipcodeVO> selectByDong(String dong){
		return sqlSessionTemplate.selectList(namespace+"searchByDong", dong);
	}
	
	
	
	/*
	public List<ZipcodeVO> selectByDong(String dong1) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ZipcodeVO vo = null;
		List<ZipcodeVO> list = new ArrayList<ZipcodeVO>();
		try {
			con = pool.getConnection();
			
			String sql = "select * from zipcode" + 
					" where dong like '%' ||? || '%' order by seq desc"; 
					
			ps = con.prepareStatement(sql);
			ps.setString(1, dong1);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String zipcode = rs.getString("zipcode");
				String sido = rs.getString("sido");
				String gugun = rs.getString("gugun");
				String dong = rs.getString("dong");
				String startbunji = rs.getString("startbunji");
				String endbunji = rs.getString("endbunji");
				int seq = rs.getInt("seq");
				
				vo = new ZipcodeVO(zipcode, sido, gugun, dong, startbunji, endbunji, seq);
				list.add(vo);
				
			}
			
			System.out.println("list.size() = " + list.size());
			return list;
			
			
		}finally {
			pool.dbClose(con, ps, rs);
			
		}
	}
	*/
}
