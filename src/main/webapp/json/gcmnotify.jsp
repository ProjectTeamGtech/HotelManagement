<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.apm.common.eu.blogic.jdbc.Connection_provider"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.google.android.gcm.server.Message"%>
<%@page import="com.google.android.gcm.server.MulticastResult"%>
<%@page import="com.google.android.gcm.server.Result"%>
<%@page import="com.google.android.gcm.server.Sender"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	final String GOOGLE_API_KEY = "AIzaSyAdE5zluOpzB-Sb3wjpYOdXyiUojj0UWuk";
	final String MESSAGE_KEY = "his";

	response.setContentType("application/json");
	String action = request.getParameter("action");
	if (action.equals("single")) {
		String regId = null;
		String data = request.getParameter("data");
		String userid = request.getParameter("userid");
		List<String> list = new ArrayList<String>();
		try {
			Connection con = Connection_provider.getconnection();
			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select regId from admin.gcm_user where userid='"
							+ userid + "'");
			while (rs.next()) {
				regId = rs.getString("regId");
				list.add(regId);
			}
			con.close();
			Sender sender = new Sender(GOOGLE_API_KEY);
			Message message = new Message.Builder().timeToLive(3)
					.collapseKey("1").delayWhileIdle(true)
					.addData(MESSAGE_KEY, data).build();
			MulticastResult multiResult = sender.send(message, list, 1);

			request.setAttribute("response", "data sent to " + userid
					+ "");

		} catch (Exception e) {
			e.printStackTrace();
		}

	} else if (action.equals("many")) {
		String data = request.getParameter("data");
		List<String> list = new ArrayList<String>();
		try {
			Connection con = Connection_provider.getconnection();
			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select regId from admin.gcm_user");
			while (rs.next()) {

				String regId = rs.getString("regId");
				list.add(regId);
			}

			con.close();

			Sender sender = new Sender(GOOGLE_API_KEY);
			Message message = new Message.Builder().timeToLive(30)
					.delayWhileIdle(true).addData(MESSAGE_KEY, data)
					.build();
			MulticastResult multiResult = sender.send(message, list, 1);

			request.setAttribute("response", "data sent to many");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
%>