package com.will.herb.reboard.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.will.herb.common.SearchVO;

@Repository
public class ReBoardDAOMybatis implements ReBoardDAO{
	@Autowired
	private SqlSessionTemplate sqlSession;
		
	private String namespace="config.mybatis.mapper.oracle.reBoard.";
	
	public int insertReBoard(ReBoardVO vo){
		int cnt=sqlSession.insert(namespace+"insertReBoard", vo);
		return cnt;
	}
	
	
	public List<ReBoardVO> selectAll(SearchVO searchVo){
//		String sql="select * from reBoard";
//		if(keyword!=null && !keyword.isEmpty()) { //검색의 경우
//			sql+=" where "+ condition +" like '%' || ? || '%'";
//		}		
//		sql+=" order by no desc";
		
		return sqlSession.selectList(namespace+"selectAll", searchVo);
	}


	@Override
	public int selectTotalRecord(SearchVO searchVo) {
		return sqlSession.selectOne(namespace+"selectTotalRecord", 
			searchVo);
	}
	
	public int updateReadCount(int no){
		return sqlSession.update(namespace+"updateReadCount", no);		
	}
	
	public ReBoardVO selectByNo(int no){
		return sqlSession.selectOne(namespace+"selectByNo",no);
	}
	
	public String selectPwd(int no){
		return sqlSession.selectOne(namespace+"selectPwd",no);
	}
		
	public int updateReBoard(ReBoardVO vo) {
		return sqlSession.update(namespace+"updateReBoard", vo);		
	}
	
	public void deleteReBoard(Map<String, String> map){
		sqlSession.delete(namespace+"deleteReBoard", map);		
	}


	@Override
	public int updateDownCount(int no) {
		return sqlSession.update(namespace+"updateDownCount", no);
	}


	@Override
	public int updateSortNo(ReBoardVO vo) {
		return sqlSession.update(namespace+"updateSortNo", vo);
	}


	@Override
	public int reply(ReBoardVO vo) {
		return sqlSession.insert(namespace+"reply", vo);
	}
	
	
	
	

	/*
	public List<ReBoardVO> selectMainNotice() throws SQLException {
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		List<ReBoardVO> list = new ArrayList<ReBoardVO>();
		try {
			//1,2
			con=pool.getConnection();
			
			//3
			String sql="select A.*" + 
					"		from" + 
					"		(" + 
					"		    select no, title" + 
					"		    from reBoard order by no desc" + 
					"		)A" + 
					"		where rownum<=6";
			
			ps=con.prepareStatement(sql);
						
			rs=ps.executeQuery();
			while(rs.next()) {
				ReBoardVO vo = new ReBoardVO();
				
				vo.setNo(rs.getInt("no"));
				vo.setTitle(rs.getString("title"));
				
				list.add(vo);
			}
			
			System.out.println("메인 공지사항 조회 결과 list.size="
					+list.size());
			
			return list;
		}finally {
			pool.dbClose(con, ps, rs);
		}		
	}
		
	
	
	
	*/
}





