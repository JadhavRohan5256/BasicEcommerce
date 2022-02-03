package com.servlet;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.dao.UserDao;
import com.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
@WebServlet("/home")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		/**
		 * Collecting all data from the Servlet
		 */
		String email = req.getParameter("email");
		String password = req.getParameter("pass");
		String messege = null;
		User user = null;
		
		/**
		 * Collected all data validating 
		 */
		if(email == null && password == null) {
			messege = "Please Enter your Email and Password!";
		}else {
			user  = UserDao.getUserCredential(email, password);
			if(user == null) {
				messege = "Incorrect Credentials!";
			}
		}
		
		/**
		 * Checking if Some Error occured
		 * then sending to loginDetails.jsp
		 */
		if(messege != null) {
			req.setAttribute("messeges", messege);
			req.getRequestDispatcher("loginDetails.jsp").forward(req,res);
		}
		else if(user.getUserType() == 1) {
			/**
			 * if User become Admin then sending to admin.jsp
			 */
			HttpSession httpSession = req.getSession();
			httpSession.setAttribute("userDetails", user);
			req.getRequestDispatcher("admin.jsp").forward(req,res);
			return;
		}
		else{
			HttpSession httpSession =  req.getSession();
			httpSession.setAttribute("userDetails", user);
			req.getRequestDispatcher("index.jsp").forward(req, res);
		}
	}
}
