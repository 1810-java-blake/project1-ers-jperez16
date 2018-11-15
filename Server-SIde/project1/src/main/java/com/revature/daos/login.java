package com.revature.daos;
import java.util.List;
import java.util.UUID;

import com.revature.baseclasses.LogIn;
import com.revature.controllers.login_controller;
public interface login {
	login instance = new login_controller();
	List<LogIn> getUser(String username, String password);
	Boolean save_sessiod(UUID uid, String username);
	List<LogIn> reauthenticate(String auth);

}
