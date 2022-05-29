package com.dao;

import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;

import com.entities.OrderDetails;
import com.helper.FactoryBuilder;

public class OrderDetailsDao {
	public static boolean setOrders(OrderDetails orderDetails) {
		boolean  flag = false;
		try {
			Session session = FactoryBuilder.getFactory().openSession();
			session.beginTransaction();
			int id =  (Integer)session.save(orderDetails);
			if(id > 0) {
				flag = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static List<OrderDetails> getOrderByUser(int userId) {
		List<OrderDetails> orders = null;
		try {
			String sql = "from OrderDetails where userId =:id";
			Session session = FactoryBuilder.getFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("id", userId);
			orders = query.list();
			return orders;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static boolean updateOrder(int productId,int userId,String status) {
		boolean flag = false;
		try {
			String sql = "Update OrderDetails SET status =:stu where userId =:uId and productId =:pId";
			Session session = FactoryBuilder.getFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("stu", status);
			query.setParameter("uId", userId);
			query.setParameter("pId", productId);
			int id = query.executeUpdate();
			session.getTransaction().commit();
			session.close();
			if(id > 0) {
				flag = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
