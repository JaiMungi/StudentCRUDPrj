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
		try {

	        Class.forName("com.mysql.cj.jdbc.Driver");

	        String host = System.getenv("MYSQLHOST");
	        String port = System.getenv("MYSQLPORT");
	        String database = System.getenv("MYSQLDATABASE");
	       // String user = System.getenv("MYSQLUSER");
	        String user = "root";
	        //String password = System.getenv("MYSQLPASSWORD");
	        String password = "ZUIRNkkqxvOpsrENDQHsMOLaMpgiOFfz";
//	        String url = "jdbc:mysql://" + host + ":" + port + "/" + database
//	                + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	        String url = "mysql://root:ZUIRNkkqxvOpsrENDQHsMOLaMpgiOFfz@ballast.proxy.rlwy.net:24024/railway";
	        System.out.println("===== NEW DBUTIL =====");
	        System.out.println(url);
	        System.out.println(user);
	        
	        return DriverManager.getConnection(url, user, password);

	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}
