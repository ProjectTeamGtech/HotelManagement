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

public class GodownAction extends BaseAction implements ModelDriven<MasterForm>,Preparable {

	
	MasterForm masterForm=new MasterForm();
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession(false);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	Pagination pagination=new Pagination(10,1);
	String mastername;
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public MasterForm getModel() {
		// TODO Auto-generated method stub
		return masterForm;
	}
 
	
	public String execute() throws Exception {
		
		Connection connection=null; 
		
		try {
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			int count=masterDAO.getAllGodownCount();
			pagination.setPreperties(count);
			masterForm.setTotalRecords(count);
			ArrayList<Master> godownList=masterDAO.getAllGodownlist(pagination);
			masterForm.setGodownList(godownList);
			pagination.setPage_records(godownList.size());
			masterForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			
			mastername = request.getParameter("selectedid");
			if (mastername != null) {
				session.setAttribute("mastername", mastername);
			} else {

				mastername = (String) session.getAttribute("mastername");
			}
			masterForm.setMastername(mastername);
			
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
			
		}

		
		return super.execute();
	}
	
	
	public String add(){
		
	  if(!verifyLogin(request)){
		  return "login";
	  }
	
	  return "add";	
	}
	
	
	public String save() throws Exception {
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			Master master=new Master();
			master.setName(masterForm.getName());
			master.setDescription(masterForm.getDescription());
			int result=masterDAO.addGodown(master);
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return "save";
	}
	
	public String edit()throws Exception {
		
		Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			String id=request.getParameter("selectedid");
			Master master=masterDAO.getGodown(id);
			masterForm.setName(master.getName());
			masterForm.setDescription(master.getDescription());
			masterForm.setId(master.getId());
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return "edit";
	}
	
	public String update() throws Exception {
		
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			Master master=new Master();
			master.setId(masterForm.getId());
			master.setName(masterForm.getName());
			master.setDescription(masterForm.getDescription());
			int res=masterDAO.updateGowdown(master);
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return "save";
	}
	
	
	public String delete()throws Exception {
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			String id=request.getParameter("selectedid");
			int result=masterDAO.deleteGodown(id);
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
		}
		
		return "save";
	}
	
	
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

		Connection con = null;

		try {
			con = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(con);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			masterForm.setMasterlist(masterlist);

			mastername = (String) session.getAttribute("mastername");
			masterForm.setMastername(mastername);
			

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			con.close();
		}
	}
	
	
	
}
