package com.gsis.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gsis.bom.Member;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
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
		
		int bp = Integer.parseInt(request.getParameter("bp"));
		String firstName = request.getParameter("first");
		String lastName = request.getParameter("last");
		
		Member member = new Member();
		int flag = member.register(bp, firstName, lastName);
		
		System.out.println(flag);
		
		switch(flag){
			case Member.EXIST_ID	: 	response.sendRedirect("register.jsp?result="+Member.EXIST_ID);
										break;
			case Member.INVALID_EMAIL:	response.sendRedirect("register.jsp?result="+Member.INVALID_EMAIL);
										break;
			case Member.OK_ID		:	request.getSession().setAttribute("member", member);
										response.sendRedirect("confirmation.jsp");
										break;
			default					:	response.sendRedirect("register.jsp?result="+Member.INVALID_ID);
										break;
		}
	}
}
