package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	
 private static final String URL =
		        "jdbc:mysql://ballast.proxy.rlwy.net:24024/railway?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

		    private static final String USER = "root";

		    private static final String PASSWORD = "ZUIRNkkqxvOpsrENDQHsMOLaMpgiOFfz\n";   // Railway password

		    public static Connection getConnection() {

		        Connection con = null;

		        try {

		            Class.forName("com.mysql.cj.jdbc.Driver");

		            con = DriverManager.getConnection(URL, USER, PASSWORD);

		            System.out.println("Database Connected Successfully");

		        } catch (Exception e) {
		            e.printStackTrace();
		        }

		        return con;
		    }
}
