package com.revature.daos;
import java.util.List;
import java.util.UUID;

import com.revature.baseclasses.LogIn;
import com.revature.controllers.login_controller;
public interface login {
	login instance = new login_controller();
	List<LogIn> getUser(String username, String password);
//	List<LogIn> getAllUsers();
	Boolean save_sessiod(UUID uid, String username);
	List<LogIn> reauthenticate(String auth);
	// this method will most likely not be implemented since i do not 
	// have a mail server
	//LogIn resetUser(String username);

}
