package com.ridlr.dao;
import com.ridlr.entity.LoginPojo;

public interface LoginDao {

	public LoginPojo checkLogin(String userName, String userPassword);
}