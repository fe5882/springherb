package com.will.herb.reboard.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.will.herb.common.SearchVO;

@Service
public class ReBoardServiceImpl implements ReBoardService{
	@Autowired
	private ReBoardDAO reBoardDao;
		
	public int insertReBoard(ReBoardVO vo){
		return reBoardDao.insertReBoard(vo);
	}
	
	public List<ReBoardVO> selectAll(SearchVO searchVo){
		return reBoardDao.selectAll(searchVo);
	}
	
	@Override
	public int selectTotalRecord(SearchVO searchVo) {
		return reBoardDao.selectTotalRecord(searchVo);
	}
	

	public int updateReadCount(int no){
		return reBoardDao.updateReadCount(no);
	}
	
	public ReBoardVO selectByNo(int no){
		return reBoardDao.selectByNo(no);
	}
	
	public int updateReBoard(ReBoardVO vo){
		return reBoardDao.updateReBoard(vo);
	}
		
	public boolean checkPwd(int no, String pwd){
		String dbPwd=reBoardDao.selectPwd(no);
		
		boolean bool=false;
		if(dbPwd.equals(pwd)) {
			bool=true;
		}
		
		return bool;
	}
	
	public void deleteReBoard(Map<String, String> map) {
		reBoardDao.deleteReBoard(map);
	}

	@Override
	public int updateDownCount(int no) {
		return reBoardDao.updateDownCount(no);
	}

	
	@Override
	@Transactional
	public int reply(ReBoardVO vo) {
		int cnt = reBoardDao.updateSortNo(vo);
		cnt = reBoardDao.reply(vo);
		
		
		return cnt;
	}
	
	
	
	/*
	public List<ReBoardVO> selectMainNotice(){
		return reBoardDao.selectMainNotice();
	}
	*/

	
}










