package com.gsis.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gsis.bom.Member;

/**
 * Servlet implementation class CheckServlet
 */
@WebServlet("/CheckServlet")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckServlet() {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			Member member = new Member();

			String bpNumber = request.getParameter("bpNumber");
			String fname = request.getParameter("firstname");
			String lname = request.getParameter("lastname");
			String facility = request.getParameter("facility");
			
			
			if (facility.equals("registration")){
				if (member.checkMemberExist(bpNumber, fname, lname)){
					if (member.checkMemberRegistration(bpNumber)){						
						HttpSession session = request.getSession();
						session.setAttribute("member", member);
						member.setBpNumber(bpNumber);
						response.sendRedirect("registration.jsp?error=reg"); // Member already registered						
					}else{
						HttpSession session = request.getSession();
						session.setAttribute("member", member);
						member.setBpNumber(bpNumber);
						response.sendRedirect("registration.jsp?error=exist"); // Member exists
					}
				}else{
					HttpSession session = request.getSession();
					session.setAttribute("member", member);
					member.setBpNumber(bpNumber);
					member.setFirstName(fname);
					member.setLastName(lname);
					response.sendRedirect("registration.jsp?error=no"); // Member does not exist, wrong name or bp
				}
				
			}else if (facility.equals("forgotPassword")){
				
				if (member.checkMemberExistBP(bpNumber)){ // Member is registered
					if (member.getRegisteredMember(bpNumber)){
						HttpSession session = request.getSession();
						session.setAttribute("member", member);
						member.setBpNumber(bpNumber);
						response.sendRedirect("forgotPassword.jsp?error=exist");
					}
				}else{
					response.sendRedirect("forgotPassword.jsp?error=no"); //Member is not registered
				}
				
			}
			
			

		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
