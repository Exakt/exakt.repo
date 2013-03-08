package com.gsis.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(30);
		
		String username = "";
		String password = "";
		
		boolean result = false;
		
		/*
		Integer loginFailCount;
		
		if((loginFailCount = (Integer)session.getAttribute("loginFailCount")) == null){
			loginFailCount = 1;
		}else{
			if(loginFailCount != 3){
				loginFailCount++;
			}else if(loginFailCount == 3){
				
			}
		}
		
		session.setAttribute("loginFailCount", loginFailCount);
		*/
		
		Member member = null;
		
		try{
			username = request.getParameter("username");
			password = request.getParameter("password");
			
			ArrayList<String> list;
			
			if((list = (ArrayList)session.getAttribute("userAttemptList")) == null){
				list = new ArrayList<String>();
				session.setAttribute("userAttemptList", list);
			}
			
			list.add(username);
			
			Set<String> unique = new HashSet<String>(list);
			
			for(String key : unique){
				if(Collections.frequency(list, key) == 3){
					session.setAttribute("locked", "locked");
				}
			}
			
			member = new Member();
			result = member.login(username, password);
			
		}catch(NullPointerException e){
			username = "";
		}
		
		if(result){
			session.setAttribute("member", member);
			response.sendRedirect("home.jsp");
		}else{
			response.sendRedirect("index.jsp");
		}
	}
}
