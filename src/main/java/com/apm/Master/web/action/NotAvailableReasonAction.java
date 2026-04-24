package com.apm.Master.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.web.form.MasterForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class NotAvailableReasonAction extends ActionSupport implements Preparable,
		ModelDriven<MasterForm> {

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
    HttpSession session=request.getSession(false);
    LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	private String mastername;
	Pagination pagination=new Pagination(10,1);
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	MasterForm masterForm = new MasterForm();

	public MasterForm getMasterForm() {
		return masterForm;
	}

	public void setMasterForm(MasterForm masterForm) {
		this.masterForm = masterForm;
	}

	
	public MasterForm getModel() {
		// TODO Auto-generated method stub
		return masterForm;
	}

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			int count=masterDAO.getTotalapmEventsCount();
			pagination.setPreperties(count);
			ArrayList<Master> notAvailableReasonList = masterDAO
					.getNotAvaiableReasonList(pagination);
			masterForm.setNotAvailableReasonList(notAvailableReasonList);
			mastername = request.getParameter("selectedid");
			pagination.setPage_records(notAvailableReasonList.size());
			masterForm.setPagerecords(String.valueOf(count));
			if (mastername != null) {

				session.setAttribute("mastername", mastername);
			} else {
				mastername = (String) session.getAttribute("mastername");
			}
			masterForm.setMastername(mastername);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return SUCCESS;
	}

	public String add() {

		return "add";
	}

	public String edit() throws Exception {

		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			int id = Integer.parseInt(request.getParameter("selectedid"));
			Master master = new Master();
			MasterDAO dao = new JDBCMasterDAO(connection);
			master = dao.getNotAvailableReason(id, master);
			masterForm.setId(master.getId());
			masterForm.setName(master.getName());
			masterForm.setDescription(master.getDescription());
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "edit";
	}

	public String update() throws Exception {

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			Master master = new Master();
			master.setName(masterForm.getName());
			master.setDescription(masterForm.getDescription());
			master.setId(masterForm.getId());
			connection = Connection_provider.getconnection();
			MasterDAO dao = new JDBCMasterDAO(connection);
			int i = dao.updateNotAvilableReason(master);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "save";
	}

	public String save() throws Exception {

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			Master master = new Master();
			master.setName(masterForm.getName());
			master.setDescription(masterForm.getDescription());
			MasterDAO dao = new JDBCMasterDAO(connection);
			dao.addNotAvailableReason(master);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "save";
	}

	public String delete() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			int id = Integer.parseInt(request.getParameter("selectedid"));
			MasterDAO dao = new JDBCMasterDAO(connection);
			dao.deleteNotAvilableReason(id);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "save";
	}

	
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			masterForm.setMasterlist(masterlist);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}	
	}
}
