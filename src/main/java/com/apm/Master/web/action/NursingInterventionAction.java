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

public class NursingInterventionAction extends BaseAction implements ModelDriven<MasterForm>,Preparable{
	MasterForm masterForm = new MasterForm();
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}



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
	public String execute() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			int count = masterDAO.getTotalNursingInterventionCount();
			
			pagination.setPreperties(count);
			String searchText = masterForm.getSearchText();
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			
			ArrayList<Master>nursinginterventionlist = masterDAO.getAllNursingIntervention(searchText, pagination);
			masterForm.setNursinginterventionlist(nursinginterventionlist);
			mastername=request.getParameter("selectedid");
			pagination.setPage_records(nursinginterventionlist.size());
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
		
		return super.execute();
	}
	public String add(){
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			
			ArrayList<Master>nursingplanninglist = masterDAO.getAllNursingPlanning(null, null);
			masterForm.setNursingplanninglist(nursingplanninglist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "add";	
	}
	public String save() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			Master master = new Master();
		    
		    master.setNursing_planning(masterForm.getNursing_planning());
		    //master.setIntervention_name(masterForm.getIntervention_name());
		    master.setNursing_intervention(masterForm.getNursing_intervention());
				int result = masterDAO.addNursingIntervention(master);

				
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "save";
	}
public String delete(){
	Connection connection= null;
	try {
		connection= Connection_provider.getconnection();
		MasterDAO masterDAO = new JDBCMasterDAO(connection);
		String id="";
		id= request.getParameter("id");
		int x= masterDAO.deleteNursingcareIntervationMaster(id);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "deletenursing";
}
public String edit(){
	Connection connection= null;
	try {
		connection= Connection_provider.getconnection();
		MasterDAO masterDAO = new JDBCMasterDAO(connection);
		String id="";
		id= request.getParameter("id");
		Master master= new Master();
		master= masterDAO.getNusringCareMasterInfo(id);
		ArrayList<Master>nursingplanninglist = masterDAO.getAllNursingPlanning(null, null);
		masterForm.setNursingplanninglist(nursingplanninglist);	
		masterForm.setNursing_planning(master.getPlanningid());
		masterForm.setNursing_intervention(master.getIntervention_name());
		masterForm.setId(master.getId());
		
	}catch (Exception e) {
		e.printStackTrace();
	}	
	
	return "editnursing";
}

public String update(){
	Connection connection= null;
	try {
		connection = Connection_provider.getconnection();
		MasterDAO masterDAO = new JDBCMasterDAO(connection);
int id= masterForm.getId();
String nursing_planning= masterForm.getNursing_planning();
String nursing_intervention= request.getParameter("nursing_intervention");

		
int x= masterDAO.updateNursingcareIntervationMaster(String.valueOf(id), nursing_planning, nursing_intervention);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "updatenursing";
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
