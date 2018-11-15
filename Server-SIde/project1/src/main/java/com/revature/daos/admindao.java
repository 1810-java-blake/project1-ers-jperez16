package com.revature.daos;
import com.revature.controllers.admin_controller;
public interface admindao {
	public admindao instance = new admin_controller();
	
	Boolean updateUser(String auth, int roleID, int userID);

}
