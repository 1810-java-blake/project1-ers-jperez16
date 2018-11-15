package com.revature.servlets;
import com.revature.controllers.new_ticket_controller;
import com.revature.daos.Users;
import com.revature.daos.newticketdao;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.revature.baseclasses.usersbaseclass;
import com.revature.daos.Users;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class new_ticket_servlet
 */
public class new_ticket_servlet extends HttpServlet {
	private newticketdao newticket = new_ticket_controller.instance;
	private static final long serialVersionUID = 1L;
	private Users u = Users.instance;
	private ObjectNode node = JsonNodeFactory.instance.objectNode();
	private Logger log = Logger.getRootLogger();


       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public new_ticket_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Content-Type", "application/json");
		response.addHeader("MIME", "application/json");
		response.addHeader("Access-Control-Allow-Origin" , "*");
		response.addHeader("Access-Control-Allow-Credentials" , "true");
		
		// TODO Auto-generated method stub
		JSONObject encodedResponse = new JSONObject (request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
		log.info("User containing authentication " + encodedResponse.getString("auth") + " is attempting to enter a new ticket");
		ObjectMapper om = new ObjectMapper();
		newticket.insertTicket(
					Double.parseDouble(encodedResponse.getString("reimbursementAmount")),
					encodedResponse.getString("description"),
					encodedResponse.getInt("userID"),
					Integer.parseInt(encodedResponse.getString("reimbursementType"))
					
				);
		log.info("Returning new information for user containg authentication ");
		log.info(encodedResponse.getString("auth"));
		List<usersbaseclass> ubc = u.getUser(encodedResponse.getString("email"),encodedResponse.getString("auth"));
		node.put("resultsData", new ObjectMapper().readTree(om.writeValueAsString(ubc)));

		response.setStatus(200);
		response.getWriter().write(om.writeValueAsString(node));
		System.out.println(encodedResponse);
	}

}
