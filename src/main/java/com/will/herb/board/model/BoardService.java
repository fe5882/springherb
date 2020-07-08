package com.will.herb.board.model;

import java.sql.SQLException;
import java.util.List;

import com.will.herb.common.SearchVO;

public interface BoardService {
	public int insertBoard(BoardVO vo);
	
	public List<BoardVO> selectAll(SearchVO searchVo);
	
	public int selectTotalRecord(SearchVO searchVo);
	
	public int updateReadCount(int no);
	
	public BoardVO selectByNo(int no);
	
	public boolean checkPwd(int no, String pwd);
	
	public int updateBoard(BoardVO vo);
	
	public int deleteBoard(int no);
}
