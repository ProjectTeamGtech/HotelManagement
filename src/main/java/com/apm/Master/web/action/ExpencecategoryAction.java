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
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class ExpencecategoryAction extends BaseAction implements ModelDriven<MasterForm> ,Preparable{

	MasterForm masterForm=new MasterForm();
	
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	
	HttpSession session=request.getSession(false);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	Pagination pagination=new Pagination(10,1);
		
	private String mastername;
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public MasterForm getModel() {
		return masterForm;
	}

	@Override
	public String execute() throws Exception {

		
		if(!verifyLogin(request)) {
			return "login";
		}
		
		
		Connection connection=null;
		try {
			
			 mastername=request.getParameter("selectedid");
			
			 if(mastername!=null) {
				 
				 session.setAttribute("mastername", mastername);
			 }
			 else {
				 
				 mastername=(String)session.getAttribute("mastername");
			 }
			
			masterForm.setMastername(mastername);
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			int total=masterDAO.getTotalExpenceCategoryCount();
			
			pagination.setPreperties(total); 
			masterForm.setTotalRecords(total);
			
			ArrayList<Master> expececategorylist=masterDAO.getExpenceCategoryList(pagination);
			masterForm.setExpececategorylist(expececategorylist);
			pagination.setPage_records(expececategorylist.size());
			masterForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			
            
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		
		return SUCCESS;
	}
	
	
	public String add() throws Exception {
		
		if(!verifyLogin(request)) {
			return "login";
		}
		return "add";
	}
	
	
	public String save() throws Exception {
		
		if(!verifyLogin(request)) {
			
			return "login";
		}
		
		Connection connection=null;
		
		try {
		
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			
			Master master=new Master();
			master.setName(masterForm.getName());
			master.setDescription(masterForm.getDescription());
			
			int result=masterDAO.addExpenceCategory(master);
		} catch (Exception e) {
          e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		
		return "save";
	}

	
	public String edit() throws Exception {
		
		
		if(!verifyLogin(request)) {
			return "login";
		}
		
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			String selectedid=request.getParameter("selectedid");
			
			MasterDAO dao=new JDBCMasterDAO(connection);
			Master master=dao.getExpenceCategory(selectedid);
			
			masterForm.setId(master.getId());
			masterForm.setName(master.getName());
			masterForm.setDescription(master.getDescription());
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
		   connection.close();
		}
		return "edit";
	}
	
	
	public String update() throws Exception{
		
		if(!verifyLogin(request)) {
			return "login";
		}
		
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			
			Master master=new Master();
			master.setId(masterForm.getId());
			master.setName(masterForm.getName());
			master.setDescription(masterForm.getDescription());
			
			int result=masterDAO.updateExpenceCategory(master);
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		
		return "save";
	}
	
	
	public String delete() throws Exception{
		
		if(!verifyLogin(request)) {
			return "login";
		}
		
		Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			String selectedid=request.getParameter("selectedid");
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			int result=masterDAO.deleteExpenceCategory(selectedid);
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return "save";
	}
	
	
	public void prepare() throws Exception {

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
