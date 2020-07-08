package com.will.herb.board.model;

import java.util.List;

import com.will.herb.common.SearchVO;

public interface BoardDAO {
	public int insertBoard(BoardVO vo);
	
	public List<BoardVO> selectAll(SearchVO searchVo);
	
	public int selectTotalRecord(SearchVO searchVo);
	
	public int updateReadCount(int no);
	
	public BoardVO selectByNo(int no);
	
	public String checkPwd(int no);
	
	public int updateBoard(BoardVO vo);
	
	public int deleteBoard(int no);
}
