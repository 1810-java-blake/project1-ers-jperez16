package com.revature.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverManagerConnector {
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException{
		String url = System.getenv("db_url");
		String port = System.getenv("db_port");
		String dbname = System.getenv("db_name");
		String schema = System.getenv("db_schema");
		String username = System.getenv("db_username");
		String password = System.getenv("db_password");
		String dataSource = "jdbc:postgresql://"+url+ ":"+ port+"/"+dbname+"?currentSchema="+schema;
		return DriverManager.getConnection(dataSource,username,password);
	}
}
