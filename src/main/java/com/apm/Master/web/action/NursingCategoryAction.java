package com.apm.Master.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.PrescriptionMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCPrescriptionMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.web.form.MasterForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class NursingCategoryAction extends BaseAction implements ModelDriven<MasterForm> ,Preparable{

	
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
		// TODO Auto-generated method stub
		return masterForm;
	}

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			
			int count=masterDAO.getNursingCategoryCount();
			pagination.setPreperties(count);
			ArrayList<Master> nursingcategorylist=masterDAO.getAllNursingCategory(pagination);
			pagination.setPage_records(nursingcategorylist.size());
			masterForm.setTotalRecords(count);
			masterForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			masterForm.setNursingcategorylist(nursingcategorylist);
			mastername = request.getParameter("selectedid");
			if (mastername != null) {

				session.setAttribute("mastername", mastername);
			} else {
				mastername = (String) session.getAttribute("mastername");
			}
			masterForm.setMastername(mastername);
			session.setAttribute("pagination", pagination);
			
			
			
		} catch (Exception e) {

		    e.printStackTrace();
		}
		finally{
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

		
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;

		try {

			connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			for(Master master:masterForm.getNursing_category()) {
			
				int result=masterDAO.addNursingCategory(master);   
			}
						
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			connection.close();
		}

		return "save";
	}

public String edit() throws Exception{
	
	if (!verifyLogin(request)) {
		return "login";
	}
	
	Connection connection=null;
	
	try {
		
		connection=Connection_provider.getconnection();
		MasterDAO masterDAO=new JDBCMasterDAO(connection);
		String selectedid=request.getParameter("id");
		Master master=masterDAO.getNursingCategory(selectedid); 
		masterForm.setId(master.getId());
		masterForm.setName(master.getName());
		masterForm.setDescription(master.getDescription());
		
		
	} catch (Exception e) {

	   e.printStackTrace();
	}
	finally{
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
		
		int result=masterDAO.updateNursingCategory(master);
	} catch (Exception e) {

	   e.printStackTrace();
	}
	finally {
		connection.close();
	}
	
	return "save";
}


public String delete() throws Exception {
	
	Connection connection=null;
	try {
		
		connection=Connection_provider.getconnection();
		MasterDAO masterDAO=new JDBCMasterDAO(connection); 

		String id=request.getParameter("id");
		int result=masterDAO.deleteNursingCategory(id);
	} catch (Exception e) {

	   e.printStackTrace();
	}
	finally {
		connection.close();
	}

	return "save";
}



	public void prepare() throws Exception {

		Connection con = null;

		try {
			con = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(con);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			masterForm.setMasterlist(masterlist);
 
			mastername = (String) session.getAttribute("mastername");
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			con.close();
		}
		
	}
	
	
	
}
