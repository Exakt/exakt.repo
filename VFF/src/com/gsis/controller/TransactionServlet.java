package com.gsis.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gsis.bom.Transaction;

/**
 * Servlet implementation class TransactionServlet
 */
@WebServlet("/TransactionServlet")
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionServlet() {
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
			String stat = request.getParameter("stat");
			//String stat = "in-process";
			int page = 1;
	        int recordsPerPage = 5;
	        if(request.getParameter("page") != null)
	            page = Integer.parseInt(request.getParameter("page"));
	        
	        TransactionInformation transactionInformation = new TransactionInformation();
	        ArrayList<Transaction> transactionList = transactionInformation.getTransactions((page-1)*recordsPerPage,recordsPerPage, stat);
	       
	        int noOfRecords = transactionInformation.getNoOfRecords();
	        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
	        request.setAttribute("transactionList", transactionList);
	        request.setAttribute("noOfPages", noOfPages);
	        request.setAttribute("currentPage", page);
	        RequestDispatcher view = request.getRequestDispatcher("inprocess.jsp");
	        view.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
