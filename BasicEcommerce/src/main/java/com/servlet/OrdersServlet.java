package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.OrderDetailsDao;
import com.dao.ProductDao;
import com.dao.UserDao;
import com.entities.OrderDetails;
import com.entities.Product;
import com.entities.User;

@WebServlet("/shippingAddress")
public class OrdersServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("userDetails");
		if(user == null) {
			req.setAttribute("messege", "You are not logged!");
			req.getRequestDispatcher("loginPage").forward(req, res);
		}
		
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		
		System.out.println(name + " " + email + " " + address);
		
		String productArrays[] = req.getParameterValues("productArrays[]");
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
		
		// after creating product map then checking product are present in database or not 
		List<OrderDetails> orderList = new ArrayList<OrderDetails>();
		for(Map.Entry<Integer,Integer> entry : proValue.entrySet()) {
			Product product = ProductDao.getProductById(entry.getKey());
			if(product == null) {
				out.print(entry.getKey() + " this product are not valids");
				return;
			}
			
			OrderDetails orderDetails  = new OrderDetails();
			
			orderDetails.setName(name);
			orderDetails.setEmail(email);
			orderDetails.setAddress(address);
			orderDetails.setProductId(product.getProductId());
			orderDetails.setOrderQuntity(entry.getValue());
			orderDetails.setUserId(user.getUserId());
			orderDetails.setStatus("created");
			
			boolean flag = OrderDetailsDao.setOrders(orderDetails);
			if(flag) {
				orderList.add(orderDetails);
			}
			System.out.println(flag);
		}
	
		
		
		//after creating listOfProduct then we want save query to save orderDetails
		
		
		
		out.print(1);
	}
}
