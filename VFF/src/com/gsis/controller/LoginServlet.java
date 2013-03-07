package com.gsis.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gsis.bom.Member;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(30);
		
		String username = "";
		String password = "";
		
		boolean result = false;
		
		Integer loginFailCount;
		
		if((loginFailCount = (Integer)session.getAttribute("loginFailCount")) == null){
			loginFailCount = 1;
		}else{
			if(loginFailCount != 3){
			}
		}
		
		System.out.println(loginFailCount);
		session.setAttribute("loginFailCount", loginFailCount);
		
		Member member = null;
		
		try{
			username = request.getParameter("username");
			password = request.getParameter("password");
			
			member = new Member();
			result = member.login(username, password);
			
		}catch(NullPointerException e){
			username = "";
			e.printStackTrace();
		}
		
		if(result){
			System.out.println(username + " is now online");
			session.setAttribute("member", member);
			response.sendRedirect("home.jsp");
		}else{
			response.sendRedirect("index.jsp");
		}
	}
}
