package com.will.herb.board.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.will.herb.common.SearchVO;


@Repository
public class BoardDAOMybatis implements BoardDAO{
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private String namespace = "config.mybatos.mapper.oracle.board.";
	
	public int insertBoard(BoardVO vo) {
		int cnt = sqlSession.insert(namespace+"insertBoard", vo);
		return cnt;
			
		
	}
	
	public List<BoardVO> selectAll(SearchVO searchVo){
		/*String sql = "select * from board";
		if(keyword != null && !keyword.isEmpty()) {	//검색의 경우
			sql += " where " + condition + " like '%'|| ? ||'%'";
		}
		sql += " order by no desc";
		ps = con.prepareStatement(sql);
		
		if(keyword != null && !keyword.isEmpty()) {	//검색의 경우
			ps.setString(1, keyword);
		}
		rs = ps.executeQuery();*/
		
		
		return sqlSession.selectList(namespace+"selectAll", searchVo);
	}

	@Override
	public int selectTotalRecord(SearchVO searchVo) {
		return sqlSession.selectOne(namespace+"selectTotalRecord", searchVo);
	}
	
	
	public int updateReadCount(int no){
		return sqlSession.update(namespace+"updateReadCount", no);
	}
	
	
	public BoardVO selectByNo(int no) {
		return sqlSession.selectOne(namespace+"selectByNo", no);
	}
	
	
	public String checkPwd(int no) {
		return sqlSession.selectOne(namespace+"checkPwd", no);
	}
	
	public int updateBoard(BoardVO vo) {
		return sqlSession.update(namespace+"editBoard", vo);
	}
	
	public int deleteBoard(int no) {
		return sqlSession.delete(namespace+"deleteBoard", no);
	}
	
	/*
	
	
	
	
	
	
	
	
	public int updateBoard(BoardVO vo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = pool.getConnection();
			
			String sql = "update board" + 
					" set name = ?, title = ?, email = ?, content = ?" + 
					" where no = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getTitle());
			ps.setString(3, vo.getEmail());
			ps.setString(4, vo.getContent());
			ps.setInt(5, vo.getNo());
			
			
			int cnt = ps.executeUpdate();
			System.out.println("글수정결과, cnt = " + cnt + ", 매개변수 vo = " + vo);
			
			return cnt;
		}finally {
			pool.dbClose(con, ps);
			
		}
	}
	
	
	
	public int deleteBoard(int no) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		int cnt = 0;
		try {
			con = pool.getConnection();
			
			String sql = "delete from board where no = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			
			cnt = ps.executeUpdate();
			
			System.out.println("삭제결과 cnt = " + cnt + ", 매개변수 no = " + no);
			return cnt;
			
		}finally {
			pool.dbClose(con, ps);
			
		}
	}
	
	public List<BoardVO> selectMainNotice() throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		
		try {
			con = pool.getConnection();
			String sql = "select * from (select no, title from board order by regdate desc)" + 
					" where rownum <= 6";
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				
				//vo.setNo(rs.getInt("no"));
				//vo.setTitle(rs.getString("title"));
				
				int no = rs.getInt("no");
				String title = rs.getString("title");
				
				vo.setNo(no);
				vo.setTitle(title);
				
				list.add(vo);
				
			}
			System.out.println("list.size() = " + list.size());
			return list;
		}finally {
			pool.dbClose(con, ps, rs);
			
		}
	}

	public boolean checkPwd(int no, String pwd) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			//1,2
			con=pool.getConnection();
			
			//3
			String sql="select pwd from board where no=?";
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, no);
			
			//4
			rs=ps.executeQuery();
			boolean result=false;
			if(rs.next()) {
				String dbPwd=rs.getString(1);
				
				if(dbPwd.equals(pwd)) {
					result=true;  //비밀번호 일치
				}
			}
			
			System.out.println("비밀번호 체크 결과, result="+result
				+", 매개변수 no="+no+", pwd="+pwd);
			
			return result;
		}finally {
			pool.dbClose(con, ps, rs);
		}
	}*/
}
