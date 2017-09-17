package com.rdayala.user.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateUserServlet
 */

public class ReadUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;

	// this gets called ONLY ONCE
	public void init(ServletConfig config) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(config.getInitParameter("dbUrl"),
					config.getInitParameter("dbUser"), config.getInitParameter("dbPassword"));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from user");
			PrintWriter out = response.getWriter();
			
			out.print("<table>");			
			out.print("<tr>");			
				out.print("<th>");
					out.print("First Name");
				out.print("</th>");			
				out.print("<th>");
					out.print("Last Name");
				out.print("</th>");
				out.print("<th>");
					out.print("Email");
				out.print("</th>");
			out.print("</tr>");
			
			while(resultSet.next()){
				out.print("<tr>");
					out.print("<td>");
						out.print(resultSet.getString(1));
					out.print("</td>");
					out.print("<td>");
						out.print(resultSet.getString(2));
					out.print("</td>");
					out.print("<td>");				
						out.print(resultSet.getString(3));
					out.print("</td>");
				out.print("</tr>");
			}
			
			out.print("</table>");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
