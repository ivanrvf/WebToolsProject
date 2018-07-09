package com.spring.project.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.spring.project.model.Model;

public class ModelDAO extends DAO{

	public ModelDAO() {}
	
	Session session;
    Transaction tx;
    
	public Integer insert(Model model) {
		session = super.sf.openSession();
        tx = session.beginTransaction();
        Integer id = (Integer)session.save(model);
        tx.commit();
        session.close();
        return id;
	}
	
	public void update(Model model) {
		session = super.sf.openSession();
        tx = session.beginTransaction();
        session.update(model);
        tx.commit();
        session.close();
	}
	
	public Model get(Model model) {
		session = super.sf.openSession();
        tx = session.beginTransaction();
        Model returnModel = session.get(Model.class, model.getId() );
        tx.commit();
        session.close();
        return returnModel;
	}
	
	public List<Model> getList(){
		session = super.sf.openSession();
        tx = session.beginTransaction();
        Query query = session.createQuery("from Model");
        List<Model> modelList = query.list();
        return modelList;
	}
	
	public Model getById(String id) {
		int modelId = Integer.parseInt(id);
		session = super.sf.openSession();
        tx = session.beginTransaction();
        Model returnModel = session.get(Model.class, modelId );
        tx.commit();
        session.close();
        return returnModel;
	}
	
	public void delete(Model model) {
		session = super.sf.openSession();
        tx = session.beginTransaction();
        session.delete(model);
        tx.commit();
        session.close();
	}
}
