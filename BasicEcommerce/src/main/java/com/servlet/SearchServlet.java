package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/searchPage")
public class SearchServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		String search = req.getParameter("search");
		if(!search.isEmpty()) {
			req.getRequestDispatcher("index.jsp").forward(req, res);
		}
	}
}
