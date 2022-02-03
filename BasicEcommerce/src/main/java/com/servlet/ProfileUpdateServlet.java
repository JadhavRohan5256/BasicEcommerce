package com.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.dao.UserDao;
import com.entities.User;
import com.entities.UserAddress;

@WebServlet("/profileUpdate")
@MultipartConfig
public class ProfileUpdateServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException {
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String contactNo = req.getParameter("contactNo");
		String birthDate = req.getParameter("birthDate");
		String email = req.getParameter("email");
		String pass = req.getParameter("password");
		Part img = req.getPart("file");
		String userAddressCity = req.getParameter("city");
		String userAddressPin = req.getParameter("pinCode");
		String userAddressState = req.getParameter("state");
		String userAddressHouseNo = req.getParameter("house");
		
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();
		User userSession = (User) session.getAttribute("userDetails");
		User user = new User();
		user.setUserId(userSession.getUserId());
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setBirthDate(birthDate);
		user.setUserEmail(email);
		user.setUserPassword(pass);
		user.setUserPhone(contactNo);
		if(img.getSubmittedFileName().isEmpty()) {
			user.setUserPro(userSession.getUserPro());
		}
		else {
			user.setUserPro(img.getSubmittedFileName());
		}
	
		
		UserAddress address = new UserAddress();
		address.setUserAddressId(userSession.getUserAddress().getUserAddressId());
		address.setUserAddressCity(userAddressCity);
		address.setUserAddressPinCode(Long.parseLong(userAddressPin));
		address.setUserAddressState(userAddressState);
		address.setUserAddressHouseNo(userAddressHouseNo);
		address.setUser(user);
		
		user.setUserAddress(address);
		boolean flag = UserDao.userUpdate(user);
		if(flag) {
			// uploading profile file 
			if(!img.getSubmittedFileName().isEmpty()) {
				String path = req.getRealPath("UploadedImage")+File.separator+"Profile"+File.separator+img.getSubmittedFileName();
				System.out.println(path);
				FileOutputStream fo = new FileOutputStream(path);
				InputStream is = img.getInputStream();
				byte[] data = new byte[is.available()];
				is.read(data);
				fo.write(data);
				is.close();
				fo.close();
			}
			//when profile successfully updated then sending success messege in profileView.jsp
			req.setAttribute("messege", "1");
			req.getRequestDispatcher("profileView.jsp").forward(req, res);
		}
		else {
			// if some error occured then sending to the profileView.jsp
			req.setAttribute("messege", "Profile not updated due some resion");
			req.getRequestDispatcher("profileView.jsp").forward(req, res);
		}
		
	}

}
