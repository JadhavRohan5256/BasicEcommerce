package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.entities.Category;
import com.entities.Product;
import com.entities.ProductPhotos;
import com.helper.FactoryBuilder;

public class ProductDao {
	public static boolean setProduct(Product pro,List<ProductPhotos> photosList) {
		boolean flag = false;
		try {
			Session session = FactoryBuilder.getFactory().openSession();
			session.beginTransaction();
			int productId = (Integer) session.save(pro);
			int photosId = 0;
			for(ProductPhotos list:photosList) {
				photosId = (Integer) session.save(list);
			}
			
			if(productId > 0 && photosId > 0) {
				flag = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	
	
	
	public static List<Product> getProduct() {
		List<Product> productList = null;
		try {
			String sql = "from Product";
			Session session = FactoryBuilder.getFactory().openSession();
			Query query = session.createQuery(sql);
			productList = query.list();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return productList;
	}
	
	public static List<Product> getProductByCategoryName(String name) {
		List<Product> list = null;
		try {
			String sql = "from Product as p where p.category.categoryTitle =:n";
			Session session = FactoryBuilder.getFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("n", name);
			list = query.list();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static Product getProductByName(String name) {
		Product pro = null;
		try {
			String sql = "from Product where productTitle=:n";
			Session session = FactoryBuilder.getFactory().openSession();
			Query q = session.createQuery(sql);
			q.setParameter("n", name);
			pro = (Product) q.uniqueResult();
			session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return pro;
	}
	
	
	public static List<Product> getProductBySearch(String search) {
		List<Product> pro= null;
		try {
			String sql = "from Product where category.categoryTitle LIKE'%"+search+"%' OR productTitle LIKE'%"+search+"%' OR productPrice LIKE'%"+search+"%'";
			Session session = FactoryBuilder.getFactory().openSession();
			Query q = session.createQuery(sql);
			pro = q.list();
			session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return pro; 
	}
	
	public static long getCount() {
		long cnt =0;
		try {
			String sql = "Select count(*) from Product";
			Session session = FactoryBuilder.getFactory().openSession();
			Query query = session.createQuery(sql);
			cnt = (Long)query.list().get(0);
			session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
}
