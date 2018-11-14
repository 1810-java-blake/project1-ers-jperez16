package com.revature.daos;
import com.revature.controllers.logout_controller;;
public interface logoutdao {
	logoutdao instance = new logout_controller();
	Boolean userLogOut(String auth);

}
