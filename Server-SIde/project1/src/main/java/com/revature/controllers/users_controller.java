package com.revature.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.revature.baseclasses.usersbaseclass;
import com.revature.daos.Users;

import com.revature.utilities.DriverManagerConnector;

public class users_controller implements Users{

	@Override
	public List<usersbaseclass> getUsers() {
		try(Connection connector = DriverManagerConnector.getConnection()){
			PreparedStatement statement = connector.prepareStatement(
					"SELECT * FROM getAllUsers()"
					);
			ResultSet results = statement.executeQuery();
			if(results.next()) {
				List<usersbaseclass> users = new ArrayList<>();
				do {
					users.add(new usersbaseclass(
							results.getInt("ers_users_id"),
							results.getString("ers_username"),
							results.getString("user_first_name"),
							results.getString("user_last_name"),
							results.getString("user_email"),
							results.getString("user_role")
							));
				}while(results.next());
				return users;
			}
		}catch(Exception error) {
			error.printStackTrace();
		}
		return null;
	}

	@Override
	public List<usersbaseclass> resetPassword(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean ticketStatus() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Boolean authenticateUser(String auth) {
		try(Connection connector = DriverManagerConnector.getConnection()){
			PreparedStatement statement = connector.prepareStatement(
					"SELECT * FROM getAllUsers()"
					);
			ResultSet results = statement.executeQuery();
			if(results.next()) {

				do {
					if(results.getString("ers_user_uuid") != null && results.getString("ers_user_uuid").equals(auth)) {
						return true;
					}

				}while(results.next());
			}
		}catch(Exception error) {
			error.printStackTrace();
			return false;
		}
		return false;
		
	}

	@Override
	public List<usersbaseclass> getUser(String email, String auth) {
		try(Connection connector = DriverManagerConnector.getConnection()){
			PreparedStatement statement = connector.prepareStatement(
					"SELECT * FROM getUserTickets(?,?)"
					);
			statement.setString(1, email);
			statement.setString(2, auth);
			ResultSet results = statement.executeQuery();
			List<usersbaseclass> user = new ArrayList<>();
			if(results.next()) {
				do {
					System.out.println(results.getString("submitted").getClass().getName());
					System.out.println(results.getString("submitted"));
					user.add(new usersbaseclass(
							results.getInt("userID"),
							results.getString("displayname"),
							results.getString("firstName"),
							results.getString("lastName"),
							results.getString("email"),
							results.getString("userRole"),
							results.getDouble("amount"),
							results.getString("submitted"),
							results.getString("resolved"),
							results.getString("description"),
							results.getString("status"),
							results.getString("reimbursement_type"),
							results.getInt("reimbursementID")
							
							));
					
				}while(results.next());
				System.out.println(user.toString());
				return user;
			}

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public List<usersbaseclass> getAllTickets() {
		try(Connection connector = DriverManagerConnector.getConnection()){
			PreparedStatement statement = connector.prepareStatement(
					"SELECT * FROM getAllTickets()");
			
			ResultSet results = statement.executeQuery();
			if(results.next()) {
				List<usersbaseclass> users = new ArrayList<>();
				do {
					users.add(new usersbaseclass(
							results.getInt("userID"),
							results.getString("displayname"),
							results.getString("firstName"),
							results.getString("lastName"),
							results.getString("email"),
							results.getString("userRole"),
							results.getDouble("amount"),
							results.getString("submitted"),
							results.getString("resolved"),
							results.getString("description"),
							results.getString("status"),
							results.getString("reimbursement_type"),
							results.getInt("reimbursementID")
							
							));
				}while(results.next());
				return users;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean changeStatus(String auth, int newstatus, int reimbursementID, int resolvedBy) {
		try(Connection connector = DriverManagerConnector.getConnection()){
			PreparedStatement statement = connector.prepareStatement(
					"SELECT  changeStatus(?,?,?,?);"
					);
			statement.setString(1, auth);
			statement.setInt(2, newstatus);
			statement.setInt(3, reimbursementID);
			statement.setInt(4, resolvedBy);
			statement.execute();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
