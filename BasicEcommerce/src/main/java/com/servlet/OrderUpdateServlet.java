package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.OrderDetailsDao;
import com.dao.ProductDao;
import com.entities.Product;
import com.entities.User;

@WebServlet("/statusUpdate")
public class OrderUpdateServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("userDetails");
		if(user == null) {
			req.setAttribute("messege", "You are not logged!");
			req.getRequestDispatcher("loginPage").forward(req, res);
		}

		String productArrays[] = req.getParameterValues("productArrays[]");
		String status = req.getParameter("status");
		Map<Integer, Integer> proValue = new LinkedHashMap<Integer, Integer>();
		
		for(String st:productArrays) {
			String tmp = "";
			int i=0;
			while(i<st.length()) {
				if(st.charAt(i) >= '0' && st.charAt(i) <= '9') {
					tmp += st.charAt(i);
				}
				else if(st.charAt(i) == '=') {
					break;
				}
				
				++i;
			}
			
			//after break statemnt execute 
			
			int productId = Integer.parseInt(tmp);
			int quntity = Integer.parseInt(st.substring(i+1,st.length()));
			proValue.put(productId, quntity);
			System.out.println(productId + " " + quntity);
		}
		
		
		
		for(Map.Entry<Integer,Integer> entry : proValue.entrySet()) {
			Product product = ProductDao.getProductById(entry.getKey());
			if(product == null) {
				out.print(entry.getKey() + " this product are not valids");
				return;
			}
			
			
			
			boolean flag = OrderDetailsDao.updateOrder(entry.getKey(),user.getUserId(),status);
			
			System.out.println(flag);
		}
		
		
		out.print("1");
	}
}
