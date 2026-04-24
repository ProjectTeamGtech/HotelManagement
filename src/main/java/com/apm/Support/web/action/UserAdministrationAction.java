package com.apm.Support.web.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Support.eu.bi.UserAdministartionDAO;
import com.apm.Support.eu.blogic.jdbc.JDBCUserAdministration;
import com.apm.Support.web.form.SupportForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class UserAdministrationAction extends BaseAction implements ModelDriven<SupportForm> {

	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse) ActionContext.getContext()
			.get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	SupportForm supportForm = new SupportForm();

	public SupportForm getModel() {
		return supportForm;
	}

	public String execute() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			UserAdministartionDAO userAdministartionDAO = new JDBCUserAdministration(connection);
			ArrayList<Clinic> cliniclist = new ArrayList<Clinic>();
			cliniclist = userAdministartionDAO.getAllClinic();
			supportForm.setCliniclist(cliniclist);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "allhospitals";
	}

	public String giveexpirydate() {
		String userid = request.getParameter("clinicid");
		String date = request.getParameter("value");
		String type=request.getParameter("type");
		date = DateTimeUtils.getCommencingDate1(date);
		Connection connection = null;
		try {
			connection = getAdminConnection();
			UserAdministartionDAO userAdministartionDAO = new JDBCUserAdministration(connection);
			int x = userAdministartionDAO.giveDeadLineToClient(userid, date,type);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");

		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	public String active_deactive_clinic() {

		String clinicid = request.getParameter("clinicid");
		String status = request.getParameter("status");

		Connection connection = null;
		try {
			connection = getAdminConnection();
			UserAdministartionDAO userAdministartionDAO = new JDBCUserAdministration(connection);
			Clinic clinic = new Clinic();
			clinic = userAdministartionDAO.getClinicDetailsFromMaster(clinicid);
			if (status != null) {
				int result = userAdministartionDAO.setClinicActiveDeactive(clinic, status);
				if(result!=0){
					result=userAdministartionDAO.setClinicActivDeactiveMaster(clinicid, status);
				}
			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	private Connection getAdminConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", ""+com.apm.main.common.constants.Constants.DB_USER+"",
					""+Constants.DB_PWD+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
}
