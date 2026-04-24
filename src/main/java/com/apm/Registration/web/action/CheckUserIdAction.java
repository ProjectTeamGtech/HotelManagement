package com.apm.Registration.web.action;



import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.web.action.BaseAction;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.common.utils.Pagination;
import com.apm.Registration.web.form.ClinicRegistrationForm;



public class CheckUserIdAction extends BaseAction 
			implements ServletRequestAware,ServletResponseAware{

	/** Http Servlet Request Object */
    private HttpServletRequest request;
    
    /** Http Servlet Response Object */
    private HttpServletResponse response;
    
    ClinicRegistrationForm clinicListForm = new ClinicRegistrationForm();
	private Pagination pagination = new Pagination(5, 1);
	
   
	public String execute() throws SQLException{
		
		Connection connection = null;
		try{
			
			if(!verifyLogin(request)){
				return "login";
			}
		
			connection = Connection_provider.getconnection();
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			// Get user id entered by user from request parameters
			String userid = (String)request.getParameter("userId");
		
			// check if user with given user id already exist
		/*	boolean userIdExist = clinicDAO.isUserExist(userid);
			
			// if user id already exist then set response 'false'
			if(userIdExist){
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
    
    public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}


}
