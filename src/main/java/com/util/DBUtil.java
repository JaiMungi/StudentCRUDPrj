package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	
//    private static final String URL =
//            "jdbc:mysql://localhost:3306/studentdb";
	
//	private static final String USER = "root";
//	private static final String PASSWORD = "ZUIRNkkqxvOpsrENDQHsMOLaMpgiOFfz"; // <-- change to your MySQL password
	
	static Connection con;
	public static Connection getConnection()
	{
		String host = System.getenv("MYSQLHOST");
		String port = System.getenv("MYSQLPORT");
		String database = System.getenv("MYSQLDATABASE");
		String user = System.getenv("MYSQLUSER");
		String password = System.getenv("MYSQLPASSWORD");
//		String url = "jdbc:mysql://" + host + ":" + port + "/" + database
//		           + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
		String url = "jdbc:mysql://mysql.railway.internal:3306/railway";
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,password);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
}
