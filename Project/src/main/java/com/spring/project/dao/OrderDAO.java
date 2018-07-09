package com.spring.project.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.spring.project.model.Item;
import com.spring.project.model.Order;
import com.spring.project.model.User;
import com.spring.project.model.product.Product;

public class OrderDAO extends DAO{

	Session session;
    Transaction tx;
    
	public Integer insert(Order model) {
		session = super.sf.openSession();
        tx = session.beginTransaction();
        Integer id = (Integer)session.save(model);
        tx.commit();
        session.close();
        return id;
	}
	
	public void update(Order model) {
		session = super.sf.openSession();
        tx = session.beginTransaction();
        session.update(model);
        tx.commit();
        session.close();
	}
	
	public List<Order> getList(){
		session = super.sf.openSession();
        tx = session.beginTransaction();
        Query query = session.createQuery("from Order");
        List<Order> modelList = query.list();
        return modelList;
	}
	
	public List<Order> get(int offset, int limit){
		session = super.sf.openSession();
        tx = session.beginTransaction();
        Criteria query = session.createCriteria(Order.class);
//        ("from Order limit :limit offset :offset");
//        query.setInteger("limit", limit);
//        query.setInteger("offset", offset);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        List<Order> modelList = query.list();
        return modelList;
	}
	
	public List<Order> getListByUser(User user){
		session = super.sf.openSession();
        tx = session.beginTransaction();
        Query query = session.createQuery("from Order where user_id = :userId");
        query.setInteger("userId", user.getId());
        List<Order> modelList = query.list();
        return modelList;
	}
	
	public Order getById(String id) {
		int modelId = Integer.parseInt(id);
		session = super.sf.openSession();
        tx = session.beginTransaction();
        Order returnModel = session.get(Order.class, modelId );
        tx.commit();
        session.close();
        return returnModel;
	}
	
	public void delete(Order model) {
		session = super.sf.openSession();
        tx = session.beginTransaction();
        session.delete(model);
        tx.commit();
        session.close();
	}
	
	public Order addToCart(Order order, Product product, int qty) {
		Set<Item> items = order.getItems();
		boolean flag = false;
		Iterator<Item> iterator = items.iterator();
		while(iterator.hasNext()) {
			Item item = iterator.next();
			if(item.getProduct().getName().equals(product.getName())) {
				int itemQty = item.getQty();
				item.setQty(itemQty += qty);
				items.add(item);
				flag = true;
				break;
			}
		}
		if(flag == false) {
			Item item = new Item();
			item.setProduct(product);
			item.setQty(qty);
			items.add(item);
		}
		return order;
	}
	
	public Order removeCart(Order order, Product product) {
		Set<Item> items = order.getItems();
		boolean flag = false;
		Iterator<Item> iterator = items.iterator();
		while(iterator.hasNext()) {
			Item item = iterator.next();
			if(item.getProduct().getName().equals(product.getName())) {
				items.remove(item);
				flag = true;
				break;
			}
		}
		return order;
	}
}
