package com.revature.controllers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revature.daos.logoutdao;
import com.revature.utilities.*;
public class logout_controller implements logoutdao{
	

	@Override
	public Boolean userLogOut(String auth) {
		// no checks are being done here to verify that the auth is in the database
		// not sure if this is appropriate or not, but it does sound fine
		try(Connection connector = DriverManagerConnector.getConnection()){
			
			PreparedStatement statement = connector.prepareStatement(
						"SELECT logoutFunction(?)"
					);
			statement.setString(1, auth);
			statement.executeQuery();
			
			return true;
						
		}catch(Exception error) {
			error.printStackTrace();
		}
		return false;
	}
}
