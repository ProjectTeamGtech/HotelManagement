package com.apm.Master.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.Master.eu.bi.TreatmentTypeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCTreatmentTypeDAO;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.Master.web.form.NotificationForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class NotificationAction extends BaseAction implements Preparable, ModelDriven<NotificationForm> {
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	private String mastername;
	
	NotificationForm notificationForm = new NotificationForm();
	
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}


	public NotificationForm getModel() {
		// TODO Auto-generated method stub
		return notificationForm;
	}


	public void prepare() throws Exception {
		Connection connection = null;
		try{
		
			connection = Connection_provider.getconnection();
			TreatmentTypeDAO treatmentTypeDAO = new JDBCTreatmentTypeDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
			
			ArrayList<TreatmentType> treatmentTypeList  =  treatmentTypeDAO.getConditionList();
			session.setAttribute("noticonditionlist", treatmentTypeList);
			
			ArrayList<Accounts>locationList = accountsDAO.getLocationList(loginInfo.getClinicid());
			session.setAttribute("notilocationlist", locationList);
			
			ArrayList<DiaryManagement>userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
			session.setAttribute("notiuserlist", userList);
			
			ArrayList<AppointmentType>apmtTypeList = appointmentTypeDAO.getAppointmentddList();
			session.setAttribute("notiapmtlist", apmtTypeList);
			
			
			mastername = request.getParameter("selectedid");
			if (mastername != null) {

				session.setAttribute("mastername", mastername);
			} else {
				mastername = (String) session.getAttribute("mastername");
			}
			notificationForm.setMastername(mastername);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	
		
		
	
		
	}
}
