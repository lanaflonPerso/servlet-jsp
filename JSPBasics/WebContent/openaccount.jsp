<%@ page import="java.sql.*" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%!
	Connection conn;
	PreparedStatement stmt;

	public void jspInit() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/mylocaldb", "root", "chotu123");
			stmt = conn.prepareStatement("insert into account values(?, ?, ?, ?)");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void jspDestroy() {
		try {
			stmt.close();
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}%>
	
<%

	int accno = Integer.parseInt(request.getParameter("accno"));
	String lastname = request.getParameter("lastname");
	String firstname = request.getParameter("firstname");
	int bal = Integer.parseInt(request.getParameter("bal"));
	
	stmt.setInt(1, accno);
	stmt.setString(2, lastname);
	stmt.setString(3, firstname);
	stmt.setInt(4, bal);
	
	int result = stmt.executeUpdate();
%>

<%@include file="openaccount.html" %>