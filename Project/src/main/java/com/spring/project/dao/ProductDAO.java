package com.spring.project.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.spring.project.model.Model;
import com.spring.project.model.Order;
import com.spring.project.model.product.Product;

public class ProductDAO extends DAO{
	
	Session session;
    Transaction tx;
    
	public List<Product> getList(){
		session = super.sf.openSession();
        tx = session.beginTransaction();
        Query query = session.createQuery("from Product");
        List<Product> modelList = query.list();
        return modelList;
	}
	
	public Product getById(String id) {
		int productId = Integer.parseInt(id);
		session = super.sf.openSession();
        tx = session.beginTransaction();
        Product returnModel = session.get(Product.class, productId );
        tx.commit();
        session.close();
        return returnModel;
	}
}
