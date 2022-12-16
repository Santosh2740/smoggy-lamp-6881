package com.bus.utility;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
public class DBUtil {
	public static Connection provideConnection() {
		Connection connect = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost:3306/reservationofbus";
		
		try {
			connect =  DriverManager.getConnection(url, "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return connect;
	}

}
