package com.rdayala.user.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet(urlPatterns = "/addServlet", initParams = {
		@WebInitParam(name = "dbUrl", value = "jdbc:mysql://localhost/mylocaldb"),
		@WebInitParam(name = "dbUser", value = "root"), @WebInitParam(name = "dbPassword", value = "chotu123") })
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;

	// this gets called ONLY ONCE
	public void init(ServletConfig config) {
		try {
			System.out.println("init");
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		try {
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate("insert into user values('" + firstName + "', '" + lastName + "', '"
					+ email + "', '" + password + "')");
			PrintWriter out = response.getWriter();
			if (result > 0) {
				out.print("<h1>USER Created..</h1>");
			} else {
				out.print("<h1>Error Creating user.</h1>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void destroy() {
		try {
			System.out.println("destroy");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
