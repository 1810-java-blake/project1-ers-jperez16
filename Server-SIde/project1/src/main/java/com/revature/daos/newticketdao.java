package com.revature.daos;
import com.revature.controllers.new_ticket_controller;
public interface newticketdao {
	public newticketdao instance = new new_ticket_controller();
	Boolean insertTicket(
			int amount,
			String ticketDescription,
			int userRole,
			int reimbursement_type_ID
			);

}
