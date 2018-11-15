package com.revature.daos;

import java.sql.Timestamp;
import java.util.List;

import com.revature.baseclasses.usersbaseclass;
import com.revature.controllers.users_controller;

public interface Users {
	Users instance = new users_controller();
	Boolean  authenticateUser(String auth);
	List<usersbaseclass> getUsers();
	List<usersbaseclass> getUser(String email, String auth);
	Boolean changeStatus(String auth, int newstatus, int reimbursementID, int resolvedBy);
	List<usersbaseclass> getAllTickets();
}

