package com.sendingnextpage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/product")
public class ProductView extends HttpServlet{
	protected void service(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		req.getRequestDispatcher("productView.jsp").forward(req, res);
	}
}
