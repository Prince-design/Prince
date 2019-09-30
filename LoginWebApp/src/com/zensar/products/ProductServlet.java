package com.zensar.products;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		if(session!=null) {
			
		
		String username = (String) session.getAttribute("uname");
	
		
		
		String logoutUrlEnc = response.encodeURL("logout");
		out.println("<p> <a href ='"+logoutUrlEnc +"'>Logout</a></p>");
		
		
		out.println("Dear " +username+ " view and purchase products");
		System.out.println("Session Id in product servlet: "+session.getId());
		}
	else {
		out.println("<p><h2>session expired </h2></p>");
		out.println("<p> <a href ='Login.html' please login again </p>");
	}}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
