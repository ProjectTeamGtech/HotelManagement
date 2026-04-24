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
import com.apm.Master.web.form.NABHCatagoryMasterForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class NABHCatagoryMasterAction extends BaseAction implements ModelDriven<NABHCatagoryMasterForm>,Preparable{
	NABHCatagoryMasterForm nabhMasterForm = new NABHCatagoryMasterForm();
	String mastername;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession();
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	Pagination pagination=new Pagination(25,1);
   
	public NABHCatagoryMasterForm getModel() {
		return nabhMasterForm;
	}

	
	
	public String execute() throws Exception{
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			String searchText = nabhMasterForm.getSearchText();
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			int count = masterDAO.getTotalNABHCatagoryCount(searchText);
			pagination.setPreperties(count);
			
			ArrayList<Master> nabhcatagorylist = masterDAO.getNABHCatagoryList(searchText,pagination);
			nabhMasterForm.setNabhcatagorylist(nabhcatagorylist);
			mastername=request.getParameter("selectedid");
			pagination.setPage_records(nabhcatagorylist.size());
			nabhMasterForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			nabhMasterForm.setTotalRecords(String.valueOf(count));
			if(mastername!=null){
				
				 session.setAttribute("mastername", mastername);
				
			} else {
				
				mastername=(String)session.getAttribute("mastername");
			}
			nabhMasterForm.setMastername(mastername);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return "nabhcatagorymaster";
	}
	public String add(){
		return "addnabhcatagorymaster";	
	}
	
	public String save() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			String name = nabhMasterForm.getName();
			int result = masterDAO.addNABHCatagory(name);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "save";
	}

	public String delete(){
		Connection connection;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			String id = request.getParameter("id");
			int result = masterDAO.deleteNABHCatagory(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "deleted";
	}
	
	public String edit(){
		Connection connection;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			String id = request.getParameter("id");
			Master master = masterDAO.getNABHCatagory(id);
			nabhMasterForm.setId(""+master.getId());
			nabhMasterForm.setName(master.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "edit";
	}
	
	public String update() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			String id = nabhMasterForm.getId();
			String name = nabhMasterForm.getName();
			int res = masterDAO.updateNABHCatagory(id,name);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "update";
	}
	


	public void prepare() throws Exception {
		Connection connection = null;
		try { 
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			nabhMasterForm.setMasterlist(masterlist);
			mastername=(String)session.getAttribute("mastername");
			nabhMasterForm.setMastername(mastername);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	
}
