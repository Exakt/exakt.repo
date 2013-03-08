package com.gsis.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.captcha.Captcha;

import com.gsis.bom.Member;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationServlet() {
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
			HttpSession session = request.getSession();
			Member member = (Member) session.getAttribute("member");
			
			String bp = request.getParameter("bp");
			String q1 = request.getParameter("question1");
			String a1 = request.getParameter("answer1");
			String q2 = request.getParameter("question2");
			String a2 = request.getParameter("answer2");

			Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);

			request.setCharacterEncoding("UTF-8");

			String answer = request.getParameter("answer");


			if (captcha.isCorrect(answer)) { // Correct captcha
				if (member.getMember(bp)){
					member.register(member.getBpNumber(), member.getFirstName(), member.getLastName(), member.getEmail(), member.getAddress(), q1, a1, q2, a2);
					response.sendRedirect("home.jsp");
				}
			} else { // Incorrect captcha
				response.sendRedirect("registration.jsp?error="+member.getBpNumber());
			}

		}catch(Exception e){
			e.printStackTrace();
		}

	}
}