package com.apm.Master.web.action;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.web.form.MasterForm;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class ReferenceAction extends BaseAction implements Preparable,
		ModelDriven<MasterForm> {

	MasterForm masterForm = new MasterForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext
			.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse) ActionContext
			.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	private String mastername;
	
	private Pagination pagination = new Pagination(15, 1);

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public String execute() {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();

			MasterDAO masterDAO = new JDBCMasterDAO(connection);

			int totalCount = masterDAO.getTotalReferenceCount();
			pagination.setPreperties(totalCount);

			ArrayList<Master> referenceList = masterDAO
					.getReferenceList(pagination);
			pagination.setPage_records(referenceList.size());
			masterForm.setTotalRecords(totalCount);
			masterForm.setPagerecords(Integer.toString(pagination
					.getPage_records()));
			masterForm.setReferenceList(referenceList);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			masterForm.setMasterlist(masterlist);
			mastername = request.getParameter("selectedid");
			if (mastername != null) {

				session.setAttribute("mastername", mastername);
			} else {
				mastername = (String) session.getAttribute("mastername");
			}
			masterForm.setMastername(mastername);
			session.setAttribute("pagination", pagination);
		} catch (Exception e) {

		}
		return "referencePage";
	}

	public void setFormDataOfReference() {
		Connection connection = null;
		int result = 0;
		try {
			connection = Connection_provider.getconnection();
			Master master = new Master();
			pagination = (Pagination) session.getAttribute("pagination");
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			int totalCount = masterDAO.getTotalReferenceCount();
			pagination.setPreperties(totalCount);

			ArrayList<Master> referenceList = masterDAO
					.getReferenceList(pagination);
			pagination.setPage_records(referenceList.size());
			masterForm.setTotalRecords(totalCount);
			masterForm.setPagerecords(Integer.toString(pagination
					.getPage_records()));
			masterForm.setReferenceList(referenceList);
		} catch (Exception e) {

		}
	}
	
	
	

	public String back() {
		setFormDataOfReference();
		return "save";
	}

	public String add() {
		if (!verifyLogin(request)) {
			return "login";
		}
		return "addReferencePage";
	}

	public String save() {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		int result = 0;
		try {
			connection = Connection_provider.getconnection();
			Master master = new Master();

			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			master.setReference(masterForm.getReference());
			

			result = masterDAO.saveReference(master);
			masterForm.setMessage("Reference Added Sucessfully!!");
			addActionMessage("Reference Added Sucessfully!!");
			setFormDataOfReference();

		} catch (Exception e) {

		}
		return "save";
	}

	public String edit() {
		if (!verifyLogin(request)) {
			return "login";
		}
		int id = Integer.parseInt(request.getParameter("selectedid"));
		Connection connection = null;

		Master master = new Master();
		try {

			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);

			master = masterDAO.getReference(id, master);
			masterForm.setId(master.getId());
			masterForm.setReference(master.getReference());
			masterForm.setDatetime(master.getDatetime());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "editReferencePage";

	}

	public String update() {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		int result = 0;
		try {
			int id = masterForm.getId();
			connection = Connection_provider.getconnection();
			Master master = new Master();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			master.setReference(masterForm.getReference());
			result = masterDAO.updateReference(master, id);
			masterForm.setMessage("Reference Updated Sucessfully!!");
			addActionMessage("Reference Updated Sucessfully!!");

			setFormDataOfReference();
		} catch (Exception e) {

		}
		return "save";
	}

	public String delete() {
		if (!verifyLogin(request)) {
			return "login";
		}
		int result = 0;
		int id = Integer.parseInt(request.getParameter("selectedid"));
		Connection connection = null;
		Master master = new Master();

		try {

			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);

			result = masterDAO.deleteReference(id, master);
			masterForm.setMessage("Reference Deleted Sucessfully!!");
			addActionMessage("Reference Deleted Sucessfully!!");

			setFormDataOfReference();
		} catch (Exception e) {

		}
		return "save";
	}

	public MasterForm getModel() {
		// TODO Auto-generated method stub
		return masterForm;
	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			ArrayList<Master>disciplineList =  masterDAO.getDisciplineDataList();
			masterForm.setDisciplineList(disciplineList);
			masterForm.setMasterlist(masterlist);
			ArrayList<UserProfile> practionerlist=userProfileDAO.getVisitingPractitiner();
			masterForm.setPractionerlist(practionerlist);
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

}
