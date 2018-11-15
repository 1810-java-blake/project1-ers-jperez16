package com.revature.servlets;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.daos.logoutdao;

import org.json.JSONObject;

/**
 * Servlet implementation class logout_servlet
 */
public class logout_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private logoutdao logout = logoutdao.instance;
	private Logger log = Logger.getRootLogger();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setStatus(404);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Content-Type", "application/json");
		resp.addHeader("MIME", "application/json");
		resp.addHeader("Access-Control-Allow-Origin" , "http://localhost:3000");
		resp.addHeader("Access-Control-Allow-Credentials" , "true");
		JSONObject encodedResponse = new JSONObject (req.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
		log.info("Logging out user containing authentication: " + encodedResponse.getString("auth"));
		try {
			logout.userLogOut(encodedResponse.getString("auth"));
			resp.setStatus(200);
		}
		catch(Exception error) {
			
		}


	}

}
