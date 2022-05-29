package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.entities.Category;
import com.helper.FactoryBuilder;

public class CategoryDao {
	
	public static boolean setCategory(Category cat) {
		boolean flag = false;
		try {
			Session session = FactoryBuilder.getFactory().openSession();
			session.beginTransaction();
			int id = (Integer)session.save(cat);
			System.out.println(id);
			session.getTransaction().commit();
			session.close();
			if(id>0) {
				flag = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public static List<Category> getCategory() {
		List<Category> list = null;
		try {
			Session session = FactoryBuilder.getFactory().openSession();
			String sql = "from Category";
			Query query =session.createQuery(sql);
			list = query.list();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static Category getCategory(int id) {
		Category category = null;
		try {
			Session session =FactoryBuilder.getFactory().openSession();
			String sql = "from Category where categoryId =:i";
			Query query = session.createQuery(sql);
			query.setParameter("i", id);
			category = (Category)query.uniqueResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return category;
	}
	
	public static long getCount() {
		long cnt = 0;
		try {
			String sql = "Select count(*) from Category";
			Session session = FactoryBuilder.getFactory().openSession();
			Query query = session.createQuery(sql);
			cnt = (Long)query.list().get(0);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
}
