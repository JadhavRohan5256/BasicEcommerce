package com.sendingnextpage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/adminPannel")
public class AdminPannel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException {
		// request  sending to the admin.jsp file 
		req.getRequestDispatcher("admin.jsp").forward(req, res);
	}
}