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
import com.apm.Master.web.form.NABHCatagoryMasterForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class NABHAreaMasterAction extends BaseAction implements ModelDriven<MasterForm>,Preparable{
	MasterForm masterForm = new MasterForm();
	String mastername;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession();
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	Pagination pagination=new Pagination(25,1);
   
	public MasterForm getModel() {
		return masterForm;
	}

	
	
	public String execute() throws Exception{
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			String searchText = masterForm.getSearchText();
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			int count = masterDAO.getTotalNABHAreaCount(searchText);
			pagination.setPreperties(count);
			
			ArrayList<Master> arealist = masterDAO.getNABHAreaList(searchText,pagination);
			masterForm.setArealist(arealist);
			mastername=request.getParameter("selectedid");
			pagination.setPage_records(arealist.size());
			masterForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			masterForm.setTotalRecords(count);
			if(mastername!=null){
				 session.setAttribute("mastername", mastername);
			} else {
				mastername=(String)session.getAttribute("mastername");
			}
			masterForm.setMastername(mastername);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return "nabharea";
	}
	public String add(){
		return "add";	
	}
	
	public String save() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			String name = masterForm.getName();
			String subcatagoryid = masterForm.getSubcatagoryid();
			int result = masterDAO.addNABHArea(name,subcatagoryid);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "save";
	}
	public String edit(){
		Connection connection;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			String id = request.getParameter("id");
			Master master = masterDAO.getNABHArea(id);
			masterForm.setId(master.getId());
			masterForm.setName(master.getName());
			masterForm.setSubcatagoryid(master.getSubcategory());
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
			int id = masterForm.getId();
			String name = masterForm.getName();
			String subcatagoryid = masterForm.getSubcatagoryid();
			int res = masterDAO.updateNABHArea(id,name,subcatagoryid);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "update";
	}
	
	public String delete(){
		Connection connection;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			String id = request.getParameter("id");
			int result = masterDAO.deleteNABHArea(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "deleted";
	}


	public void prepare() throws Exception {
		Connection connection = null;
		try { 
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			masterForm.setMasterlist(masterlist);
			mastername=(String)session.getAttribute("mastername");
			masterForm.setMastername(mastername);
			ArrayList<Master> nabhsubcatagorylist = masterDAO.getNABHSubCatagoryList(null,null);
			masterForm.setNabhsubcatagorylist(nabhsubcatagorylist);
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
