package com.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.entities.User;
import com.entities.UserAddress;
import com.helper.FactoryBuilder;

public class UserDao {
	public static boolean setUser(User user,UserAddress address) {
		boolean flag = false;
		try {
			Session session = FactoryBuilder.getFactory().openSession();
			session.beginTransaction();
			int userId = (Integer) session.save(user);
			int addressId = (Integer) session.save(address);
			session.getTransaction().commit();
			session.close();
			if(userId > 0 && addressId >0) {
				flag = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public static User getUserCredential(String userName,String pass) {
		User user = null;
		try {
			String query = "from User where userEmail=:e and userPassword=:p";
			Session session = FactoryBuilder.getFactory().openSession();
			Query q = session.createQuery(query);
			q.setParameter("e", userName);
			q.setParameter("p", pass);
			user = (User)q.uniqueResult();
			session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(user != null) {
			return user;
		}
		else {
			return null;
		}
	}
	
	public static long getCount() {
		long cnt=0;
		try {
			String sql = "Select count(*) from User";
			Session session = FactoryBuilder.getFactory().openSession();
			Query query = session.createQuery(sql);
			cnt = (Long)query.list().get(0);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	
	
	public static boolean userUpdate(User user) {
		boolean flag = false;
		try {
			String sql1 = "update User SET firstName=:fName,"
					+ "lastName=:lName,userEmail=:email,birthDate=:bDate,"
					+ "userPassword=:pass,userPhone=:phone,userPro=:profile where userId=:id";
			String sql2 = "update UserAddress SET userAddressCity=:city,"
					+ "userAddressPinCode=:pinCode,userAddressState=:state,"
					+ "userAddressHouseNo=:house where userAddressId=:addId";
			Session session1 = FactoryBuilder.getFactory().openSession();
			session1.beginTransaction();
			Query query1 = session1.createQuery(sql1);
			query1.setParameter("fName", user.getFirstName());
			query1.setParameter("lName", user.getLastName());
			query1.setParameter("email", user.getUserEmail());
			query1.setParameter("bDate", user.getBirthDate());
			query1.setParameter("pass", user.getUserPassword());
			query1.setParameter("phone", user.getUserPhone());
			query1.setParameter("profile", user.getUserPro());
			query1.setParameter("id", user.getUserId());
			System.out.println(sql1);
			int userId = query1.executeUpdate();
			session1.getTransaction().commit();
			session1.close();
			
			Session session2 = FactoryBuilder.getFactory().openSession();
			session2.beginTransaction();
			Query query2 = session2.createQuery(sql2);
			query2.setParameter("city", user.getUserAddress().getUserAddressCity());
			query2.setParameter("pinCode", user.getUserAddress().getUserAddressPinCode());
			query2.setParameter("state", user.getUserAddress().getUserAddressState());
			query2.setParameter("house", user.getUserAddress().getUserAddressHouseNo());
			query2.setParameter("addId", user.getUserAddress().getUserAddressId());
			int addressId = query2.executeUpdate();
			session2.getTransaction().commit();
			session2.close();
			
			if(userId>0 && addressId > 0) {
				flag = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
}
