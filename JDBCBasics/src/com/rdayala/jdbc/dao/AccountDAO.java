package com.rdayala.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDAO {

	public static void main(String[] args) {
		
		try(Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/mylocaldb", "root", "chotu123");
				Statement statement = myConn.createStatement();
				ResultSet rs = statement.executeQuery("select * from account");
				) {
			
			System.out.println(myConn);
			
//			int result = statement.executeUpdate("insert into account values(1, 'Dayala', 'Raghu', 10000)");			
//			System.out.println(result + " rows got inserted..");
			
//			int result = statement.executeUpdate("update account set bal = 20000 where accno = 1");
//			System.out.println(result + " rows got updated..");
			
//			int result = statement.executeUpdate("delete from account where accno = 1");
//			System.out.println(result + " rows got deleted..");
			
			while(rs.next()){
				// column index starts from 1
				System.out.print(rs.getString(2)+ " "); // lastname
				System.out.print(rs.getString(3)+ " "); // firstname
				System.out.println(rs.getInt(4)); // balance
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
