package com.rdayala.learnings.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AdditionServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {

		Integer num1 = Integer.parseInt(request.getParameter("number1"));
		Integer num2 = Integer.parseInt(request.getParameter("number2"));
		
		PrintWriter out = response.getWriter();
		out.println("The sum is : " + (num1+num2));
		
	}

}
