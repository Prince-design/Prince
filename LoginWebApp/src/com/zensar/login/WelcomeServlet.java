package com.zensar.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class WelcomeServlet
 */
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		//session.setMaxInactiveInterval(5);
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		
		String logoutUrlEnc = response.encodeURL("logout");
		out.println("<p> <a href ='"+logoutUrlEnc +"'>Logout</a></p>");
		
		out.println("<p> Dear " +username+ " welcome to online shopping home! </p>");
		
		out.println("<p> Your session timeout period is "+ session.getMaxInactiveInterval()+" seconds</p>");
		out.println("<p> <a href ='searchInfo'>Search</a></p>");
		out.println("<p> <a href ='searchAma'>Go to amazon</a></p>");
		String productUrlEnc = response.encodeURL("Product");
		out.println("<p>To select products<a href ='"+ productUrlEnc +"'>Click here</a></p>");
		
	
		session.setAttribute("uname", username);
		System.out.println("Session id in welcomeServlet :  "+session.getId());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
