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

import org.json.JSONObject;

import com.dao.ProductDao;
import com.entities.Product;
import com.entities.User;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;


@WebServlet("/orderPayment")
public class OrderPayments extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
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
		if(productArrays == null) {
			out.print("You are not selected any product");
			return;
		}
		for(String i:productArrays) {
			System.out.println("product Details " + i);
		}
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
			
		double totalAmount = 0.00;
		for(Map.Entry<Integer,Integer> entry : proValue.entrySet()) {
			Product product = ProductDao.getProductById(entry.getKey());
			if(product == null) {
				out.print(entry.getKey() + " this product are not valids");
				return;
			}
			long totalProductAmount = product.getAfterDiscountPrice() * entry.getValue();
			totalAmount += totalProductAmount;
		}
			
		if(totalAmount > 0.00) {
			try {
				RazorpayClient razor = new RazorpayClient("rzp_test_DIhp8Aqjojcgzl", "P2IZZTysbx0Lor8o4IVt32Fc");
				JSONObject option = new JSONObject();
				option.put("amount", totalAmount*100);
				option.put("currency", "INR");
				option.put("receipt", "txn_3345");
				Order order = razor.Orders.create(option);
				System.out.println(order);
					
				out.print(order.toString());
					
			} catch (RazorpayException e) {
					
				e.printStackTrace();
			}
				
		}
		else {
			out.print("Please Select Some product to Create Orders");
		}
	}
}
