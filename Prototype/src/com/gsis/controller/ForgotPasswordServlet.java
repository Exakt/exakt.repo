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
 * Servlet implementation class ForgotPasswordServlet
 */
@WebServlet("/ForgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPasswordServlet() {
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
			String answer1 = request.getParameter("answer1");
			String answer2 = request.getParameter("answer2");
			
			if (member.getRegisteredMember(bpNumber)){
				if (answer1.equals(member.getAnswer1()) && answer2.equals(member.getAnswer2())){
					HttpSession session = request.getSession();
					session.setAttribute("member", member);
					member.updatePassword(bpNumber);
					response.sendRedirect("forgotPassword.jsp?error=okay"); // Successfull 
				}else{
					response.sendRedirect("forgotPassword.jsp?error=not"); // Not
				}
			}else{
				response.sendRedirect("forgotPassword.jsp?error=not"); // Not 
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
