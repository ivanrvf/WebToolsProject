package com.spring.project.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class DAO {
	Configuration config = new Configuration();
	SessionFactory sf = config.configure("hibernate.cfg.xml").buildSessionFactory();
}
