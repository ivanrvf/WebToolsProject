package com.spring.project.dao;



import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.spring.project.model.User;

public class LoginDAO extends DAO{
	
	public LoginDAO() {}
	
	Session session;
    Transaction tx;
    
	public User login(User user) {
		session = super.sf.openSession();
        tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eqOrIsNull("username", user.getUsername()));
        criteria.add(Restrictions.eqOrIsNull("password", user.getPassword()));
        User verifedUser = (User)criteria.uniqueResult();
        tx.commit();
        session.close();
		return verifedUser; 
	}
}
