package com.ridlr.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ridlr.dao.LoginDao;
import com.ridlr.entity.LoginPojo;

@Repository
public class LoginDaoImpl implements LoginDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null; 
	
//	@Override
	public LoginPojo checkLogin(String userName, String userPassword) {
		System.out.println("LoginDaoImplements.checkLogin()");
		session = sessionFactory.openSession();
		//boolean userFound = false;
		
		String SQL_QUERY = " from LoginPojo as o where o.userName=? and o.password=?";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter(0, userName);
		query.setParameter(1, userPassword);
		LoginPojo loginData = (LoginPojo) query.uniqueResult();
		// List list = query.list();

		// if (loginData != null) {
		// userFound= true;
		// }

		session.close();
		return loginData;
	}

}
