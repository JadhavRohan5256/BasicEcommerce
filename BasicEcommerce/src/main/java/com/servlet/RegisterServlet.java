package com.servlet;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.hibernate.Session;

import com.dao.UserDao;
import com.entities.User;
import com.entities.UserAddress;
import com.helper.FactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
@WebServlet("/register")
@MultipartConfig
public class RegisterServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException {
		PrintWriter out = res.getWriter();
		
		/**
		 * Collecting all data from Server 
		 */
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String contactNo = req.getParameter("contactNo");
		String birthDate = req.getParameter("birthDate");
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		Part img = req.getPart("profileImage");
		String userAddressCity = req.getParameter("addressCity");
		String userAddressPin = req.getParameter("addressPin");
		String userAddressState = req.getParameter("addressState");
		String userAddressHouseNo = req.getParameter("addressHouse");
		/**
		 * removing all extra white spacess
		 */
		firstName.trim();
		lastName.trim();
		contactNo.trim();
		birthDate.trim();
		email.trim();
		pass.trim();
		userAddressCity.trim();
		userAddressPin.trim();
		userAddressState.trim();
		userAddressHouseNo.trim();
		 
		
		/**
		 * email validating 
		 */
		
//		User user1  = UserDao.userCredential(email, pass);
		/**
		 * Validating all data  
		 */
		
		String messege=null; 
		boolean con = firstName == null && lastName == null && birthDate == null &&
				      userAddressCity == null && userAddressState == null;
		if(con) {
			messege = "All Field are Required!";
		}
		else if(contactNo.length() != 10) {
			messege = "Number should be 10 digits";
		}
		else if(pass.length() <8) {
			messege = "password should be greater than 8 character";
		}
		else if(img.getSubmittedFileName() == null) {
			messege = "Select Your Profile Picture";
		}
//		else if(user1.getUserEmail() == email) {
//			messege = "Email already Exists";
//		}

		
		/**
		 * when some error occured then 
		 * sending to the registerUser.jsp file 
		 */
		if(messege != null) {
			out.println(messege);
			req.setAttribute("messeges", messege);
			req.getRequestDispatcher("registerUser.jsp").forward(req, res);
			return;	
		}
		
		
		/**
		 * Uploading file in folder 
		 */
		String path = req.getRealPath("UploadedImage")+File.separator+"Profile"+File.separator+img.getSubmittedFileName();
		System.out.println(path);
		FileOutputStream fo = new FileOutputStream(path);
		InputStream is = img.getInputStream();
		byte[] data = new byte[is.available()];
		is.read(data);
		fo.write(data);
		fo.close();
		is.close();
		
		
		/**
		 * creating user object
		 */
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUserPhone(contactNo);
		user.setBirthDate(birthDate);
		user.setUserEmail(email);
		user.setUserPassword(pass);
		user.setUserPro(img.getSubmittedFileName());
		UserAddress address = new UserAddress();
		address.setUserAddressCity(userAddressCity);
		address.setUserAddressPinCode(Long.parseLong(userAddressPin));
		address.setUserAddressState(userAddressState);
		address.setUserAddressHouseNo(userAddressHouseNo);
		user.setUserAddress(address);
		
	   /**
		*creating session to send data in database
	    */
		boolean flag = UserDao.setUser(user, address);
		if(flag) {
			HttpSession httpSession = req.getSession();
			httpSession.setAttribute("userDetails", user);
			req.getRequestDispatcher("index.jsp").forward(req, res);
		}
	}
}
