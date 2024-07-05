package com.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class HibernateCriteriaExample {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx= session.beginTransaction();
		
		//Get all records
		Criteria criteria = session.createCriteria(products.class);
		List<products> proList=criteria.list();
		for(products pro: proList) {
			System.out.println(pro.getId()+" "+pro.getName()+" "+pro.getQuantity()+" "+pro.getPrice());
		}
		
		//select all records of product table whose price is greater than 500/-
		criteria = session.createCriteria(products.class).add(Restrictions.gt("price", new Float(500.00)));
         List<products> exppro=criteria.list();
         for(products pro:exppro) {
		System.out.println(pro.getName());
         }
         //select all records of product table whose qty is greater than 10 pcs;
         criteria = session.createCriteria(products.class).add(Restrictions.gt("quantity", new Integer(10)));
         List<products> qtypro=criteria.list();
         for(products pro:qtypro) {
        	 System.out.println(pro.getName());
         }
         
         //select no of products available
         criteria = session.createCriteria(products.class).setProjection(Projections.rowCount());
         Long productCount= (Long)criteria.uniqueResult();
         System.out.println("Totalproduct: "+productCount);
         
         
         
         //select highest and lowest amount product
       proList = session.createCriteria(products.class).addOrder(Order.desc("price")).setFirstResult(0).setMaxResults(1).list();
          for(products pro1:proList) {
        	 System.out.println("highest price product:"+pro1.getName());
             }
        proList = session.createCriteria(products.class).addOrder(Order.asc("price")).setFirstResult(0).setMaxResults(1).list();
         for(products pro1:proList) {
        	 System.out.println("lowest price product:"+pro1.getName());
         }
          
          //select sum of all available products price
		Criteria d =session.createCriteria(products.class);
		proList=d.setProjection(Projections.sum("price")).list();
		System.out.println("Sum of Price="+proList.get(0));
		
		
		tx.commit();
		sessionFactory.close();
		
		
	}
  }