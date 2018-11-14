package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.revature.utilities.*;
import com.revature.daos.Users;
import com.revature.baseclasses.usersbaseclass;

import org.json.JSONObject;

public class users_servlet  extends HttpServlet{
	private UUIDHelper uuidHelper  = UUIDHelper.uuidHelper;
	private ObjectMapper om = new ObjectMapper();
	private Users users = Users.instance;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.service(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Content-Type", "application/json");
		resp.addHeader("MIME", "application/json");
		resp.addHeader("Access-Control-Allow-Origin" , "*");
		resp.addHeader("Access-Control-Allow-Credentials" , "true");
		if(!uuidHelper.uuidChecker(req.getParameter("auth"))) {
			ObjectNode node = JsonNodeFactory.instance.objectNode();
			node.put("success", 1);
			node.put("error", "bad request");
			resp.getWriter().write(om.writeValueAsString(node));
		}
		else {
			if(!users.authenticateUser(req.getParameter("auth"))) {
				ObjectNode node = JsonNodeFactory.instance.objectNode();
				node.put("success", 1);
				node.put("error", "you are not allowed to view this page");
				resp.getWriter().write(om.writeValueAsString(node));
			}
			else {
				System.out.println("sending all users");
				ObjectNode node = JsonNodeFactory.instance.objectNode();
				List<usersbaseclass> allUsers = users.getUsers();
				node.put("success", "0");
				node.put("results", new ObjectMapper().readTree(om.writeValueAsString(allUsers)));
				resp.getWriter().write(om.writeValueAsString(node));
				
				
				
			}
		}

	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req);
		try {
			resp.addHeader("Content-Type", "application/json");
			resp.addHeader("MIME", "application/json");
			resp.addHeader("Access-Control-Allow-Origin" , "*");
			resp.addHeader("Access-Control-Allow-Credentials" , "true");
			resp.addHeader("Access-Control-Allow-Methods","POST,PUT");
			JSONObject encodedResponse = new JSONObject (req.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
			users.changeStatus(
					encodedResponse.getString("auth"),
					encodedResponse.getInt("newStatus"),
					Integer.parseInt(encodedResponse.getString("reimb_id")),
					encodedResponse.getInt("resolvedBy")
					);
			System.out.println(encodedResponse);
			resp.setStatus(200);

		}catch(Exception error) {
			resp.setStatus(404);
		}

	}
	
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Content-Type", "application/json");
		resp.addHeader("MIME", "application/json");
		resp.addHeader("Access-Control-Allow-Origin" , "*");
		resp.addHeader("Access-Control-Allow-Credentials" , "true");
		resp.addHeader("Access-Control-Allow-Methods","POST,PUT");
		super.doOptions(req, resp);
	}
	

}
