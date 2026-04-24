package com.apm.Appointment.web.action;



import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.apm.Appointment.eu.bi.BranchDAO;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.web.action.BaseAction;
import com.apm.Appointment.eu.blogic.jdbc.JDBCBranchDAO;



public class CheckUserIdAction extends BaseAction implements ServletRequestAware,ServletResponseAware{

	/** Http Servlet Request Object */
    private HttpServletRequest request;
    
    /** Http Servlet Response Object */
    private HttpServletResponse response;
    
   
	
   
	public String execute() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
		
			 connection = Connection_provider.getconnection();
			
		   // BranchDAO branchDAO = new JDBCBranchDAO(connection);
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			// Get user id entered by user from request parameters
			String userid = (String)request.getParameter("userId");
		
			// check if user with given user id already exist
			//boolean userIdExist = clinicDAO.isUserExist(userid);
			
			// if user id already exist then set response 'false'
			/*if(userIdExist){
				response.getWriter().write("false");
			}else{	// else set response 'true'
				response.getWriter().write("true");
			}*/

		}catch (Exception be) {
			be.printStackTrace();
		}
		
		finally{
			connection.close();
		}
		return null;
	}
	
    public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	    
    }

    public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	    
    }

}
