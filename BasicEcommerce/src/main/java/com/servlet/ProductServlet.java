package com.servlet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.dao.CategoryDao;
import com.dao.ProductDao;
import com.entities.Category;
import com.entities.Product;
import com.entities.ProductPhotos;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
@WebServlet("/addproduct")
@MultipartConfig
public class ProductServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
		String productName = req.getParameter("proName");
		
		int price = -1;
		if(!req.getParameter("price").isEmpty()) 
		price = Integer.parseInt(req.getParameter("price"));
		
		byte discount = -1;
		if(!req.getParameter("discount").isEmpty()) 
	    discount = Byte.parseByte(req.getParameter("discount"));
		
		int quntity = -1;
		if(!req.getParameter("proQuntity").isEmpty()) 
		quntity = Integer.parseInt(req.getParameter("proQuntity"));
		
		int categoryId = -1;
		if(req.getParameter("category") != null) {
			categoryId = Integer.parseInt(req.getParameter("category"));
		}
		String desc = req.getParameter("proDesc");
		
		
		List<Part> partList = new ArrayList<Part>();
		if(req.getPart("proImg1") != null) {
			Part img = req.getPart("proImg1");
			partList.add(img);
		}
		if(req.getPart("proImg2") != null) {
			Part img = req.getPart("proImg2");
			partList.add(img);
		}
		if(req.getPart("proImg3") != null) {
			Part img = req.getPart("proImg3");
			partList.add(img);
		}
		
//		boolean flag = true;
//		for(Part part:partList) {
//			if(!part.getSubmittedFileName().isEmpty()) {
//				flag = false;
//				break;
//			}
//		}
		int totalImg = partList.size();
		
		String messege = "";
		if(price == -1 || discount == -1 || quntity == -1 ) {
			messege = "All fields are required";
		}
		else if(productName.isEmpty()) {
			messege = "Product name should not be empty";
		}
		else if(price < 0) {
			messege = "Price should not be negative";
		}
		else if(discount <0 || discount >100) {
			messege = "discount range between 0 to 100 !";
		}
		else if(quntity < 0) {
			messege = "Quntity should be greater than 0";
		}
		else if(categoryId == -1 ) {
			messege = "Please select Category of product";
		}
		else if(desc.isEmpty()) {
			messege = "Description should not empty";
		}
		else if(totalImg<=0) {
			messege ="uploade atleast one image";
		}
		
		
		
		if(!messege.isEmpty()) {
			req.setAttribute("messege", messege);
			req.getRequestDispatcher("adminPannel").forward(req, res);
			return;
		}else {
			Product pro = new Product();
			pro.setProductTitle(productName);
			pro.setProductPrice(price);
			pro.setProductDiscount(discount);
			pro.setProductDesc(desc);
			pro.setProductQuantity(quntity);
			Category cat = CategoryDao.getCategory(categoryId);
			pro.setCategory(cat);
			
			List<ProductPhotos> photoList = new ArrayList<ProductPhotos>();
			for(Part part:partList) {
				ProductPhotos img = new ProductPhotos();
				img.setProductPhotosName(part.getSubmittedFileName());
				img.setProduct(pro);
				photoList.add(img);
			}
			
			
			boolean tmp = ProductDao.setProduct(pro, photoList);
			String file = "";
			for(Part part:partList) {
				String path = req.getRealPath("UploadedImage")+File.separator+"ProductImg"+File.separator+part.getSubmittedFileName();
				file = path;
				System.out.println(path);
				FileOutputStream fs = new FileOutputStream(path);
				InputStream is = part.getInputStream();
				byte[] data = new byte[is.available()];
				is.read(data);
				fs.write(data);
				fs.close();
				is.close();
			}
			System.out.println(file);
			if(tmp) {
				req.setAttribute("messege", "1");
				req.getRequestDispatcher("adminPannel").forward(req, res);
			}
			else {
				req.setAttribute("messege", "Data Not submitted");
				req.getRequestDispatcher("adminPannel").forward(req, res);
			}
			
			System.out.println(pro);
			
			
		}
		
	}
}