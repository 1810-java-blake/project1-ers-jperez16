package com.revature.controllers;
import com.revature.baseclasses.LogIn;
import com.revature.daos.login;
import com.revature.utilities.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.apache.tomcat.dbcp.dbcp2.cpdsadapter.DriverAdapterCPDS;
import com.revature.utilities.passwordHasher;

public class login_controller implements login{
	private passwordHasher hasher = passwordHasher.Hasher;

	public List<LogIn> getUser(String username, String password) {
		try(Connection connector = DriverManagerConnector.getConnection()){
			PreparedStatement statement = connector.prepareStatement(
					"SELECT * FROM ers_users WHERE LOWER(user_email) = LOWER(?) OR "
					+ " LOWER(ers_username) = LOWER(?)"
					);
			statement.setString(1, username);
			statement.setString(2, username);
			ResultSet results = statement.executeQuery();
			if(results.next()) {				
				List<LogIn> user = new ArrayList<>();
				
				do {
					if(results.getString("ers_password").equals(hasher.passHasher(password))) {
						user.add(new LogIn(
								results.getInt("ers_users_id"),
								results.getString("ers_username"),
								results.getString("user_email"),
								results.getString("user_first_name"),
								results.getString("user_last_name"),
								results.getInt("user_role_id")
								));
						return user;
					}
				}while(results.next());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Boolean save_sessiod(UUID uid, String username) {
		try(Connection connector = DriverManagerConnector.getConnection()){
			PreparedStatement statement = connector.prepareStatement(
					"UPDATE ers_users SET ers_user_uuid = ? WHERE user_email = ?"
					);
			statement.setString(1, uid.toString());
			statement.setString(2, username);
			statement.executeUpdate();
			
			
		}catch(Exception error) {
			error.printStackTrace();
		}
		return false;
	}

	@Override
	public List<LogIn> reauthenticate(String auth) {
		try(Connection connector = DriverManagerConnector.getConnection()){
			PreparedStatement statement = connector.prepareStatement(
					"SELECT * FROM ers_users WHERE ers_user_uuid = ?"
					);
			statement.setString(1, auth);
			ResultSet results = statement.executeQuery();
			if(results.next()) {
				List<LogIn> user = new ArrayList<>();
				do {
					user.add(new LogIn(
							results.getInt("ers_users_id"),
							results.getString("ers_username"),
							results.getString("user_email"),
//							results.getString("ers_password"),
							results.getString("user_first_name"),
							results.getString("user_last_name"),
							results.getInt("user_role_id")
							));
				}while(results.next());
			}
			
		}catch(Exception error) {
			
		}
		return null;
	}

}
