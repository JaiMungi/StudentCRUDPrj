package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
//    private static final String URL =
//            "jdbc:mysql://localhost:3306/studentdb";
    private static final String URL =
            "String url=\"jdbc:mysql://HOST:3306/studentdb\";";
    private static final String USER = "root";
    private static final String PASSWORD = "root"; // <-- change to your MySQL password

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found. Add mysql-connector-j jar to WEB-INF/lib.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
