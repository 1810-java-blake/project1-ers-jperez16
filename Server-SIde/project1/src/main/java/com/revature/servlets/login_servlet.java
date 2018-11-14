package com.revature.servlets;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.revature.baseclasses.LogIn;
import com.revature.daos.login;
import com.revature.baseclasses.usersbaseclass;
import com.revature.daos.Users;

public class login_servlet extends HttpServlet{
	private ObjectNode node = JsonNodeFactory.instance.objectNode();
	private ObjectMapper t = new ObjectMapper();
	private ObjectMapper error = new ObjectMapper();
	private login logging = login.instance;
	private Users u = Users.instance;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setStatus(400);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		resp.addHeader("Content-Type", "application/json");
		resp.addHeader("MIME", "application/json");
		resp.addHeader("Access-Control-Allow-Origin" , "*");
		resp.addHeader("Access-Control-Allow-Credentials" , "true");
		JSONObject encodedResponse = new JSONObject (req.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
		
		LocalDate localtime = java.time.LocalDate.now();
		System.out.println(localtime);
		if(encodedResponse.has("data")) {
			try {
				ObjectMapper om = new ObjectMapper();
				JSONObject decodedResponse = new JSONObject(new String(Base64.getDecoder().decode(encodedResponse.getString("data"))));
				System.out.println(decodedResponse);
				List<LogIn> users = logging.getUser(
						decodedResponse.getString("username"), 
						decodedResponse.getString("password")
						);
				if(users == null) {
					System.out.println("users was null");
					node.removeAll();
				
					node.put("success", 1);
					node.put("error", "Wrong Password!");
					resp.getWriter().write(error.writeValueAsString(node));
					
				}
				else {
					UUID uid = UUID.randomUUID();
					node.removeAll();
					node.put("success", "0");
					node.put("auth",uid.toString());
					node.put("results", new ObjectMapper().readTree(om.writeValueAsString(users)));
					logging.save_sessiod(uid, users.get(0).getEmail());
					System.out.println(users.get(0).getRoleID());
					if(users.get(0).getRoleID() == 2) {
						List<usersbaseclass> ubc = u.getUser(users.get(0).getEmail(), uid.toString());
						node.put("resultsData", new ObjectMapper().readTree(om.writeValueAsString(ubc)));
					}
					if(users.get(0).getRoleID() == 3) {
						List<usersbaseclass> ubc = u.getAllTickets();
						node.put("resultsData", new ObjectMapper().readTree(om.writeValueAsString(ubc)));
					}

					
					
					
					System.out.println(om.writeValueAsString(node));
					resp.getWriter().write(om.writeValueAsString(node));
					
				}
				

			}catch (Exception e){
				e.printStackTrace();
				node.removeAll();
				node.put("success","300");
				node.put("error", "invalid format");
				resp.getWriter().write(error.writeValueAsString(node));
			}

		}
		else {
			node.removeAll();
			node.put("success","500");
			node.put("error", "invalid format");
			resp.getWriter().write(error.writeValueAsString(node));
			
		}
				
	}
}
