package com.ridlr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ridlr.dao.LoginDao;
import com.ridlr.entity.LoginPojo;
import com.ridlr.service.LoginService;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;
	
	@Override
	public LoginPojo checkLogin(String userName, String userPassword) {
		// TODO Auto-generated method stub
		return loginDao.checkLogin(userName, userPassword);
	}
	
	

}
