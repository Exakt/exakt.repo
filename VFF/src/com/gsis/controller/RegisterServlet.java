package com.gsis.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gsis.bom.MemberBean;

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
		
		MemberBean member = new MemberBean();
		int flag = member.register(bp, firstName, lastName);
		
		switch(flag){
			case MemberBean.EXIST_ID	: 	response.sendRedirect("register.jsp?result="+MemberBean.EXIST_ID);
										break;
			case MemberBean.INVALID_EMAIL:	response.sendRedirect("register.jsp?result="+MemberBean.INVALID_EMAIL);
										break;
			case MemberBean.OK_ID		:	request.getSession().setAttribute("member", member);
										response.sendRedirect("register.jsp");
										break;
			default					:	response.sendRedirect("register.jsp?result="+MemberBean.INVALID_ID);
										break;
		}
	}
}
