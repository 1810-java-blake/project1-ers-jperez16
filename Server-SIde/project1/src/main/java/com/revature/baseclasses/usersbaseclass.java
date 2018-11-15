package com.revature.baseclasses;
import java.sql.Timestamp;

import com.revature.daos.Users;


public class usersbaseclass{
	private String firstName;
	private String lastName;
	private String email;
	private int userID;
	private String userRole;
	private int reimbursementID;
	private double reimbursementAmount;
	private String submitted;
	private String resolved;
	private String description;
	private String ticketStatus;
	private String reimbursementType;
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}


	public String getUserRole() {
		return userRole;
	}


	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}


	public int getReimbursementID() {
		return reimbursementID;
	}


	public void setReimbursementID(int reimbursementID) {
		this.reimbursementID = reimbursementID;
	}


	public double getReimbursementAmount() {
		return reimbursementAmount;
	}


	public void setReimbursementAmount(double reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}


	public String getSubmitted() {
		return submitted;
	}


	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}


	public String getResolved() {
		return resolved;
	}


	public void setResolved(String resolved) {
		this.resolved = resolved;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getTicketStatus() {
		return ticketStatus;
	}


	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}


	public String getReimbursementType() {
		return reimbursementType;
	}


	public void setReimbursementType(String reimbursementType) {
		this.reimbursementType = reimbursementType;
	}


	public String getResolvedByFirstName() {
		return resolvedByFirstName;
	}


	public void setResolvedByFirstName(String resolvedByFirstName) {
		this.resolvedByFirstName = resolvedByFirstName;
	}


	public String getResolvedByLastName() {
		return resolvedByLastName;
	}


	public void setResolvedByLastName(String resolvedByLastName) {
		this.resolvedByLastName = resolvedByLastName;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}



	private String resolvedByFirstName;
	private String resolvedByLastName;
	private String userName;
	


	public usersbaseclass(int userID, String userName, String firstName, String lastName, String email, String userRole) {
		this.userID = userID;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRole = userRole;
	}
	
	
	public usersbaseclass(String firstName, String lastName, String email, int userID, String userRole,
			int reimbursementID, double reimbursementAmount, String submitted, String resolved, String description,
			String ticketStatus, String reimbursementType, String resolvedByFirstName, String resolvedByLastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userID = userID;
		this.userRole = userRole;
		this.reimbursementID = reimbursementID;
		this.reimbursementAmount = reimbursementAmount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.ticketStatus = ticketStatus;
		this.reimbursementType = reimbursementType;
		this.resolvedByFirstName = resolvedByFirstName;
		this.resolvedByLastName = resolvedByLastName;
	}

	
	
	@Override
	public String toString() {
		return "usersbaseclass [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", userID="
				+ userID + ", userRole=" + userRole + ", reimbursementID=" + reimbursementID + ", reimbursementAmount="
				+ reimbursementAmount + ", submitted=" + submitted + ", resolved=" + resolved + ", description="
				+ description + ", ticketStatus=" + ticketStatus + ", reimbursementType=" + reimbursementType
				+ ", resolvedByFirstName=" + resolvedByFirstName + ", resolvedByLastName=" + resolvedByLastName + "]";
	}

	
}
