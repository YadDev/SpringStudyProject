package com.ridlr.service;

import com.ridlr.entity.LoginPojo;

public interface LoginService {
	
	public LoginPojo checkLogin(String userName, String userPassword);

}
