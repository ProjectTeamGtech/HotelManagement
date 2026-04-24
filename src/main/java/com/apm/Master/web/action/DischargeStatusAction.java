package com.apm.Master.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Master.eu.bi.DeclarationDAO;
import com.apm.Master.eu.bi.DischargeStatusDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCDeclarationDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCDischargeStatus;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.web.form.MasterForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class DischargeStatusAction extends ActionSupport implements
		ModelDriven<MasterForm>,Preparable {

	MasterForm masterForm = new MasterForm();

	Pagination pagination=new Pagination(10,1);
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

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

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session=request.getSession(false);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
    private String mastername;
    
	
	public String execute() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			DischargeStatusDAO dao = new JDBCDischargeStatus(connection);
			int total=dao.getTotalCount();
			pagination.setPreperties(total);
			
			masterForm.setTotalRecords(total);
			
			ArrayList<Master> masters = dao.getAllDischargeStatus(pagination);
			pagination.setTotal_records(masters.size());
			masterForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
			masterForm.setDischargeStatusList(masters);
			mastername = request.getParameter("selectedid");
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

	public String delete() throws Exception {

		Connection connection = null;
		try {
			int id = Integer.parseInt(request.getParameter("selectedid"));
			connection = Connection_provider.getconnection();
			DischargeStatusDAO dao = new JDBCDischargeStatus(connection);
			dao.deleteDischargeStatus(id);
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
			DischargeStatusDAO dao = new JDBCDischargeStatus(connection);
			int res = dao.addDischargeStatus(master);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "save";
	}

	public String edit() throws Exception {

		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			int id = Integer.parseInt(request.getParameter("selectedid"));
			DischargeStatusDAO dao = new JDBCDischargeStatus(connection);
			Master master = dao.getMaster(id);
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
			master.setId(masterForm.getId());
			master.setName(masterForm.getName());
			master.setDescription(masterForm.getDescription());
			DischargeStatusDAO dao = new JDBCDischargeStatus(connection);
			dao.updateDischargeStatus(master);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "save";
	}

	
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;

		try {
			con = Connection_provider.getconnection();
			DischargeStatusDAO dao = new JDBCDischargeStatus(con);
			ArrayList<Master> masterlist = dao.getMasterList(loginInfo);
			masterForm.setMasterlist(masterlist);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
	}

}
