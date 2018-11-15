package com.revature.controllers;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.daos.newticketdao;
import com.revature.utilities.DriverManagerConnector;
public class new_ticket_controller implements newticketdao{

	public Boolean insertTicket(double amount, String ticketDescription, int userRole,
			int reimbursement_type_ID) {
		// TODO Auto-generated method stub
		try(Connection connector = DriverManagerConnector.getConnection()){
			PreparedStatement statement = connector.prepareStatement(
					"INSERT INTO ers_reimbursment(reimb_amt,reimb_submitted,reimb_description,reimb_author,reimb_status_id,reimb_type_id) "
					+ "VALUES(?,LOCALTIMESTAMP,?,?,?,?)"
					);
			statement.setDouble(1, amount);
			statement.setString(2, ticketDescription);
			statement.setInt(3, userRole);
			statement.setInt(4, 1);
			statement.setInt(5, reimbursement_type_ID);
			statement.execute();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
