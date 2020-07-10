package com.will.herb.zipcode.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ZipcodeServiceImpl implements ZipcodeService{
	@Autowired
	private ZipcodeDAO dao;
	
	
	public List<ZipcodeVO> selectByDong(String dong){
		return dao.selectByDong(dong);
	}
	
	
}
