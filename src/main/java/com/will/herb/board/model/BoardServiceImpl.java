package com.will.herb.board.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.will.herb.common.SearchVO;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardDAO dao;
	
	public int insertBoard(BoardVO vo){
		return dao.insertBoard(vo);
	}
	
	public List<BoardVO> selectAll(SearchVO searchVo){
		return dao.selectAll(searchVo);
	}

	@Override
	public int selectTotalRecord(SearchVO searchVo) {
		return dao.selectTotalRecord(searchVo);
	}
	
	public int updateReadCount(int no) {
		return dao.updateReadCount(no);
	}
	
	public BoardVO selectByNo(int no) {
		return dao.selectByNo(no);
	}
	
	public boolean checkPwd(int no, String pwd) {
		String dbPwd = dao.checkPwd(no);
		
		boolean bool = false;
		if(dbPwd.equals(pwd)) {
			bool = true;
		}
		return bool;
	}
	
	public int updateBoard(BoardVO vo) {
		return dao.updateBoard(vo);
	}
	
	public int deleteBoard(int no) {
		return dao.deleteBoard(no);
	}
	
	
	
	
	/*
	public List<BoardVO> selectAll(String condition, String keyword) throws SQLException{
		return dao.selectAll(condition, keyword);
	}
	
	public BoardVO selectByNo(int no) throws SQLException {
		return dao.selectByNo(no);
	}
	
	public int updateBoard(BoardVO vo) throws SQLException {
		return dao.updateBoard(vo);
	}
	
	public int updateReadCount(int no) throws SQLException {
		return dao.updateReadCount(no);
	}
	
	public int deleteBoard(int no) throws SQLException {
		return dao.deleteBoard(no);
	}
	
	public boolean checkPwd(int no, String pwd) throws SQLException {
		return dao.checkPwd(no, pwd);
	}
	
	public List<BoardVO> selectMainNotice(){
		return dao.selectMainNotice();
	}*/
}

