<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.apm.common.eu.blogic.jdbc.Connection_provider"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	Connection connection = null;
	String regid = request.getParameter("regId");
	String userid = request.getParameter("userid");

	if (regid != null && userid != null) {

		try {

			connection = Connection_provider.getconnection();
			Statement st = connection.createStatement();
			ResultSet rs = st
					.executeQuery("select * from admin.gcm_user where regId='"
							+ regid + "'");
			while (rs.next()) {
				String reg = rs.getString(2);
				PreparedStatement pre = connection
						.prepareStatement("delete from admin.gcm_user where regId=?");
				pre.setString(1, reg);
				pre.executeUpdate();
			}
			PreparedStatement ps = connection
					.prepareStatement("insert into admin.gcm_user values (?,?)");
			ps.setString(1, userid);
			ps.setString(2, regid);
			ps.executeUpdate();
			out.println("data inserted");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
	}
	out.println("ok..");
%>

