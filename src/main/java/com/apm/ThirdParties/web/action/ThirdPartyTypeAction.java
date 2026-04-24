package com.apm.ThirdParties.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.ThirdParties.web.form.ThirdPartyForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class ThirdPartyTypeAction extends BaseAction implements Preparable,
		ModelDriven<ThirdPartyForm> {

	HttpServletRequest request = (HttpServletRequest) ActionContext
			.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse) ActionContext
			.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);

	private String mastername;
	
	ThirdPartyForm thirdPartyForm = new ThirdPartyForm();
	private Pagination pagination = new Pagination(10, 1);

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);

	public String execute() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
			int totalCount = thirdPartyDAO.getThirdPartyTypeCount();
			pagination.setPreperties(totalCount);
			ArrayList<ThirdParty> thirdPartyTypeList = thirdPartyDAO
					.getThirdPartyList(pagination);

			pagination.setPage_records(thirdPartyTypeList.size());
			thirdPartyForm.setTotalRecords(totalCount);
			thirdPartyForm.setPagerecords(Integer.toString(pagination
					.getPage_records()));
			thirdPartyForm.setThirdPartyTypeList(thirdPartyTypeList);
			session.setAttribute("pagination", pagination);
			mastername = request.getParameter("selectedid");
			if (mastername != null) {

				session.setAttribute("mastername", mastername);
			} else {
				mastername = (String) session.getAttribute("mastername");
			}
			thirdPartyForm.setMastername(mastername);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}
		return "typeList";

	}

	public void setFormDataOfTypeList() {
		ThirdParty thirdParty = new ThirdParty();
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
			pagination = (Pagination) session.getAttribute("pagination");
			int totalCount = thirdPartyDAO.getThirdPartyTypeCount();
			pagination.setPreperties(totalCount);
			ArrayList<ThirdParty> thirdPartyTypeList = thirdPartyDAO
					.getThirdPartyList(pagination);
			pagination.setPage_records(thirdPartyTypeList.size());
			thirdPartyForm.setTotalRecords(totalCount);
			thirdPartyForm.setPagerecords(Integer.toString(pagination
					.getPage_records()));
			thirdPartyForm.setThirdPartyTypeList(thirdPartyTypeList);
		} catch (Exception e) {
		}
	}

	public String back() {
		setFormDataOfTypeList();
		thirdPartyForm.setMastername(mastername);
		return "typeList";
		
	}

	public String add() {
		return "addType";
	}

	public String save() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		ThirdParty thirdParty = new ThirdParty();
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);

			thirdParty.setType(thirdPartyForm.getType());
			thirdParty.setDescription(thirdPartyForm.getDescription());
			thirdParty.setPatientType(thirdPartyForm.getPatientType());
			int result = thirdPartyDAO.saveType(thirdParty);
			thirdPartyForm.setMessage("Third Party Type Added Sucessfully!!");
			addActionMessage("Third Party Type Added Sucessfully!!");
			setFormDataOfTypeList();
			thirdPartyForm.setMastername(mastername);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}
		return "typeList";
	}

	public String edit() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		int id = Integer.parseInt(request.getParameter("selectedid"));
		ThirdParty thirdParty = new ThirdParty();
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);

			thirdParty = thirdPartyDAO.getTypeDetail(id);
			thirdPartyForm.setType(thirdParty.getType());
			thirdPartyForm.setId(thirdParty.getId());
			thirdPartyForm.setDescription(thirdParty.getDescription());
			thirdPartyForm.setPatientType(thirdParty.getPatientType());
		} catch (Exception e) {

		} finally {

			connection.close();
		}
		return "editTypePage";
	}

	public String update() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		ThirdParty thirdParty = new ThirdParty();
		Connection connection = null;
		int id = thirdPartyForm.getId();
		try {
			connection = Connection_provider.getconnection();
			ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);

			thirdParty.setType(thirdPartyForm.getType());
			thirdParty.setDescription(thirdPartyForm.getDescription());
			thirdParty.setPatientType(thirdPartyForm.getPatientType());
			int result = thirdPartyDAO.updateType(id, thirdParty);
			thirdPartyForm.setMessage("Third Party Type Updated Sucessfully!!");
			addActionMessage("Third Party Type Updated Sucessfully!!");
			setFormDataOfTypeList();

			thirdPartyForm.setMastername(mastername);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}
		return "typeList";
	}

	public String delete() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		ThirdParty thirdParty = new ThirdParty();
		Connection connection = null;
		int id = Integer.parseInt(request.getParameter("selectedid"));
		try {
			connection = Connection_provider.getconnection();
			ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);

			int result = thirdPartyDAO.deleteType(id);
			thirdPartyForm.setMessage("Third Party Type Deleted Sucessfully!!");
			addActionMessage("Third Party Type Deleted Sucessfully!!");
			setFormDataOfTypeList();

			
			thirdPartyForm.setMastername(mastername);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}
		return "typeList";
	}

	public ThirdPartyForm getModel() {
		// TODO Auto-generated method stub
		return thirdPartyForm;
	}

	public void prepare() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
			ArrayList<ThirdParty> thirdPartyTypeList = thirdPartyDAO
					.getThirdPartyList();
			thirdPartyForm.setThirdPartyTypeList(thirdPartyTypeList);
			thirdPartyForm.setCountry("United Kingdom");
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			thirdPartyForm.setMasterlist(masterlist);

			mastername=(String)session.getAttribute("mastername");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

	}
}
