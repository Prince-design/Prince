package com.zensar.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.zensar.daos.UserDaoImpl;
import com.zensar.daos.UserDaov;
import com.zensar.entities.User;
import com.zensar.exceptions.ServiceException;
import com.zensar.services.UserService;
import com.zensar.services.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	//Call business layer component
	private UserService userService;
	
	public void setUserService(UserService userService) {
	this.userService = userService;
}



	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
		ServletContext context =config.getServletContext();
		String driverClassName = context.getInitParameter("jdbcDriver");
		String dbUrl = context.getInitParameter("jdbcUrl");
		String dbUsername = context.getInitParameter("dbUser");
		String dbPassword = context.getInitParameter("dbPassword");
		
		
		//load jdbc driver class
		try {
			Class.forName(driverClassName);
			
			context.log("JDBC Driver is loaded succesfully");
			Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			context.log("Database connection is established!");
			
			//create userdaov object
			UserDaov userDao = new UserDaoImpl();
			((UserDaoImpl) userDao).setConnection(con);
			
			 //create userService object
			UserService userService = new UserServiceImpl();
			((UserServiceImpl) userService).setUserDao(userDao);
			
			//set user service to servlet
			setUserService(userService);
			context.log("User Service is set in LoginServlet");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User clientUser = new User();
		clientUser.setUsername(username);
		clientUser.setPassword(password);
		
		PrintWriter out = response.getWriter();
		try {
			if(userService.validateUser(clientUser)) 
			{
				//out.println("<p> Dear " +username + "Welcome to online shopping </p>");
				RequestDispatcher rd = request.getRequestDispatcher("welcome");
				rd.forward(request, response);
				
			}
			else
			{
				
				out.println("<p> Invalid username or password!!! </p>");
				RequestDispatcher rd = request.getRequestDispatcher("Login.html");
				rd.include(request, response);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
