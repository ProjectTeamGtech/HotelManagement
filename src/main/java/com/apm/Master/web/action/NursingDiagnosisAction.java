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
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class NursingDiagnosisAction extends BaseAction implements ModelDriven<MasterForm>,Preparable{
MasterForm masterForm = new MasterForm();
String mastername;

HttpServletRequest request = ServletActionContext.getRequest();
HttpServletResponse response = ServletActionContext.getResponse();
HttpSession session = request.getSession();
LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
Pagination pagination = new Pagination(25, 1);

public MasterForm getModel() {
	// TODO Auto-generated method stub
	return masterForm;
}

public Pagination getPagination() {
	return pagination;
}

public void setPagination(Pagination pagination) {
	this.pagination = pagination;
}

public String execute() throws Exception{
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		MasterDAO masterDAO = new JDBCMasterDAO(connection);
		int count = masterDAO.getTotalNursingDiagnosisCount();
		
		pagination.setPreperties(count);
		String searchText = masterForm.getSearchText();
		if(searchText!=null){
			if(searchText.equals("")){
				searchText=null;
			}
		}
		
		ArrayList<Master>nursingdiagnosislist = masterDAO.getAllNursingDiagnosis(searchText, pagination);
		masterForm.setNursingdiagnosislist(nursingdiagnosislist);
		mastername=request.getParameter("selectedid");
		pagination.setPage_records(nursingdiagnosislist.size());
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
	
	return "nursingdiagnosis";
	
}

public String add(){
	
	return "add";	
}

public String save() throws Exception{
	Connection connection=null;
	try {
		connection = Connection_provider.getconnection();
		MasterDAO masterDAO = new JDBCMasterDAO(connection);
		Master master = new Master();
		master.setNursing_diagnosis(masterForm.getNursing_diagnosis());
		
		int result = masterDAO.addNursingDiagnosis(master);
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
		Master master = new Master();
		master.setId(masterForm.getId());
		Master master2 = masterDAO.getNursingDiagnosis(master);
		masterForm.setId(master2.getId());
		masterForm.setNursing_diagnosis(master2.getNursing_diagnosis());
		
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
		Master master = new Master();
		master.setId(masterForm.getId());
		master.setNursing_diagnosis(masterForm.getNursing_diagnosis());
		
		int result =masterDAO.updateNursingDiagnosis(master);
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
		Master master = new Master();
		master.setId(masterForm.getId());
	    int i = master.getId();
		int result = masterDAO.deleteNursingDiagnosis(master);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "save";
}
public void prepare() throws Exception {
	Connection connection = null;
	
	try { 
		connection = Connection_provider.getconnection();
		MasterDAO masterDAO = new JDBCMasterDAO(connection);
		ArrayList<String> jobTitleList = new ArrayList<String>();
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		jobTitleList = userProfileDAO.getJobTitleList();
		
		masterForm.setNewjobTitleList(jobTitleList);
		
		ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
		masterForm.setMasterlist(masterlist);

		mastername=(String)session.getAttribute("mastername");
		
		masterForm.setMastername(mastername);
	} catch (Exception e) {

		e.printStackTrace();
	} finally {
		connection.close();
	}	
}
}