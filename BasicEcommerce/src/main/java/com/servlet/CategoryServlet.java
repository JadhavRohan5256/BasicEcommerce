package com.servlet;
import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import javax.servlet.annotation.WebServlet;

import com.dao.CategoryDao;
import com.entities.Category;
import com.helper.FactoryBuilder;

import javax.servlet.ServletException;
@WebServlet("/addcategory")
public class CategoryServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		String title = req.getParameter("title");
		String desc = req.getParameter("desc");
		String messege = "";
		if(title.isEmpty()) {
			messege = "Title should not be empty";
		}
		else if(desc.length() <100 || desc.length() > 10000) {
			messege = "description should be minimum 100 and maximum 10000 characters";
		}
		
			Random random = new Random();
			Category category = new Category();
			category.setCategoryId(random.nextInt());
			category.setCategoryTitle(title);
			category.setCategoryDesc(desc);
			category.setCategoryTotalQuntity(0);
			
			/**
			 * Sending Category object to the DataBase
			 */
	
			if(messege.isEmpty()) {
				Session session = FactoryBuilder.getFactory().openSession();
				session.beginTransaction();
				int id = (Integer)session.save(category);
				if(id>0) {
					messege = "1";
				}
				else {
					messege = "Data not Inserted!";
				}
				session.getTransaction().commit();
				session.close();
			}
			
			/**
			 * Checking data Inserted successfuly
			 */

		req.setAttribute("messege",messege);
		req.getRequestDispatcher("adminPannel").forward(req, res);
		
		
	}
}
