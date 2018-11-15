package com.revature.utilities;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

public class DriverManagerConnector {
	private Logger log = Logger.getRootLogger();

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Map<String,String> loadConfig() {
		try {
			Yaml yml = new Yaml();
			//this needs to be improved, there must be a way to read the file from a specific location making the file location trip
			InputStream input = new FileInputStream(new File("../../../project1/server.yml"));
			return (Map<String,String>) yml.load(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(System.getProperty("user.dir"));
			e.printStackTrace();
		}
		return null;
	}
	
	public static Connection getConnection() throws SQLException{
		Map<String,String> data = loadConfig();	
		String dataSource = "jdbc:postgresql://"+data.get("db_url")+ ":" + data.get("db_port").toString() + "/"+data.get("db_name")+"?currentSchema="+data.get("db_schema");
		return DriverManager.getConnection(dataSource,data.get("db_username"),data.get("db_password"));
	}
	
	//relics
	//String url = System.getenv("db_url");
//	String port = System.getenv("db_port");
//	String dbname = System.getenv("db_name");
//	String schema = System.getenv("db_schema");
//	String username = System.getenv("db_username");
//	String password = System.getenv("db_password");
////	String dataSource = "jdbc:postgresql://"+url+ ":"+ port+"/"+dbname+"?currentSchema="+schema;
}
