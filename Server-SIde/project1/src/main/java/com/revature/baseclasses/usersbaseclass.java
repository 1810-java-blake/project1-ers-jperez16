package com.revature.baseclasses;
import java.sql.Timestamp;

import com.revature.daos.Users;


/*
 * reimbursement:
 * reiumburse ID <this should go to to reimbusement status> *
 * amount
 * submitted
 * resolved
 * description
 * receipt 
 * resolver
 * status id
 * type id
 * 
 * user id <<
 * username << 
 * user password << 
 * first name << 
 * last name <<
 * email <<
 * user role id <goes to user role name which is varchar 20>
 * 
 * reimbursement status id *
 * reimbursement status
 * 
 * reimbursement type id *
 * reimbursement type <<
 * 
 * user role ID
 * user role
 * */


public class usersbaseclass{
	private int userID;
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private String submitted;
	private String resolved;
	private int reimbursementID;
	private double reimbursementAmount;
	
	private String userRole;
	private String reimbursementStatus;
	private String reimbursementType;
	private String description;
	
	
	
	// initial constructor to get users and display them in the admin menu
	public usersbaseclass(int userID, String username, String firstName, String lastName,String email,String userRole ) {
		this.userID = userID;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRole = userRole;
		
	}

	
	public usersbaseclass(
			int userID, 
			String username, 
			String firstName, 
			String lastName,
			String email,
			String userRole, 
			double amount, 
			String submitted, 
			String resolved,
			String description,String status,String reimbursment_type, int reimburseID) {
		this.userID = userID;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRole = userRole;
		this.reimbursementAmount = amount;
		this.description = description;
		this.reimbursementStatus = status;
		this.reimbursementType = reimbursment_type;
		this.reimbursementID = reimburseID;
		this.submitted = submitted;
		this.resolved = resolved;
		
	}


	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


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


	public String getUserRole() {
		return userRole;
	}


	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}


	public String getReimbursementStatus() {
		return reimbursementStatus;
	}


	public void setReimbursementStatus(String reimbursementStatus) {
		this.reimbursementStatus = reimbursementStatus;
	}


	public String getReimbursementType() {
		return reimbursementType;
	}


	public void setReimbursementType(String reimbursementType) {
		this.reimbursementType = reimbursementType;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(reimbursementAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + reimbursementID;
		result = prime * result + ((reimbursementStatus == null) ? 0 : reimbursementStatus.hashCode());
		result = prime * result + ((reimbursementType == null) ? 0 : reimbursementType.hashCode());
		result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
		result = prime * result + userID;
		result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		usersbaseclass other = (usersbaseclass) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (Double.doubleToLongBits(reimbursementAmount) != Double.doubleToLongBits(other.reimbursementAmount))
			return false;
		if (reimbursementID != other.reimbursementID)
			return false;
		if (reimbursementStatus == null) {
			if (other.reimbursementStatus != null)
				return false;
		} else if (!reimbursementStatus.equals(other.reimbursementStatus))
			return false;
		if (reimbursementType == null) {
			if (other.reimbursementType != null)
				return false;
		} else if (!reimbursementType.equals(other.reimbursementType))
			return false;
		if (resolved == null) {
			if (other.resolved != null)
				return false;
		} else if (!resolved.equals(other.resolved))
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		if (userID != other.userID)
			return false;
		if (userRole == null) {
			if (other.userRole != null)
				return false;
		} else if (!userRole.equals(other.userRole))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "usersbaseclass [userID=" + userID + ", username=" + username + ", email=" + email + ", firstName="
				+ firstName +  ", lastName=" + lastName + ", submitted=" + submitted
				+ ", resolved=" + resolved + ", reimbursementID=" + reimbursementID + ", reimbursementAmount="
				+ reimbursementAmount + ", userRole=" + userRole + ", reimbursementStatus=" + reimbursementStatus
				+ ", reimbursementType=" + reimbursementType + ", description=" + description + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
