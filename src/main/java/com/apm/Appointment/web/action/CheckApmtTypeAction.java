package com.apm.Appointment.web.action;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.apm.Appointment.eu.bi.AppointmentDAO;
import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.web.form.UserProfileForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;

public class CheckApmtTypeAction extends BaseAction implements ServletRequestAware,ServletResponseAware{

/** Http Servlet Request Object */
	private HttpServletRequest request;

/** Http Servlet Response Object */
	private HttpServletResponse response;


	UserProfileForm userProfileForm = new UserProfileForm();
	private Pagination pagination = new Pagination(5, 1);


	public String execute() throws SQLException{
		Connection connection = null;
		try{
		
		if(!verifyLogin(request)){
			return "login";
		}
		
			connection = Connection_provider.getconnection();
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
			
			
			String apmtType = (String)request.getParameter("name");
			
			// check if user with given user id already exist
			boolean apmtNameExist = appointmentTypeDAO.isApmtTypeExist(apmtType);
			
			// if user id already exist then set response 'false'
			if(apmtNameExist){
				response.getWriter().write("false");
			}else{	// else set response 'true'
				response.getWriter().write("true");
			}
			
						
		
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
