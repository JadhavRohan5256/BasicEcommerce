package com.sendingnextpage;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
@WebServlet("/registerPage")
public class RegisterPage extends HttpServlet{
	private static final long serialVersionUID = 1L;
	/**
	 * if client send registerPage request then servlet sending to 
	 * registerUser.jsp file.
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
		req.getRequestDispatcher("registerUser.jsp").forward(req, res);
	}

}
