package com.apm.Master.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Master.eu.bi.DischargeOutcomeDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCDischargeOutcomeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.web.form.MasterForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class JobTitleAction extends BaseAction implements Preparable, ModelDriven<MasterForm>{
	
	MasterForm masterForm = new MasterForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	private String mastername;
	private Pagination pagination = new Pagination(10, 1);
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public String execute(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			
			
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			
			int totalCount = masterDAO.getTotalJobTitleCount();
			pagination.setPreperties(totalCount);
			
			ArrayList<Master>jobTitleList = masterDAO.getJobTitleList(pagination);
			pagination.setPage_records(jobTitleList.size());
			masterForm.setTotalRecords(totalCount);
			masterForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			masterForm.setJobTitleList(jobTitleList);
			mastername = request.getParameter("selectedid");
			if (mastername != null) {

				session.setAttribute("mastername", mastername);
			} else {
				mastername = (String) session.getAttribute("mastername");
			}
			masterForm.setMastername(mastername);
			session.setAttribute("pagination", pagination);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "jobTitlePage";
		
	}
	
	public String back(){
		setFormDataofJobTitle();
		masterForm.setMastername(mastername);
		return "jobTitlePage";
	}
	
	public String add(){
		if(!verifyLogin(request)){
			return "login";
		}
		return "addJobTitlePage";
	}
	public void setFormDataofJobTitle(){
		Connection connection = null;
		int result = 0;
		try{
			connection = Connection_provider.getconnection();
			Master master = new Master();
			
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			pagination = (Pagination) session.getAttribute("pagination");
		int totalCount = masterDAO.getTotalJobTitleCount();
		pagination.setPreperties(totalCount);
		
		ArrayList<Master>jobTitleList = masterDAO.getJobTitleList(pagination);
		pagination.setPage_records(jobTitleList.size());
		masterForm.setTotalRecords(totalCount);
		masterForm.setPagerecords(Integer.toString(pagination.getPage_records()));
		masterForm.setJobTitleList(jobTitleList);
		}
		catch(Exception e){
			
		}
	}
	
	public String save(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		int result = 0;
		try{
			connection = Connection_provider.getconnection();
			Master master = new Master();
			
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			
			master.setJobTitle(masterForm.getJobTitle());
			master.setJobtitlegroup_id(masterForm.getJobtitlegroup_id());
			
			result = masterDAO.saveJobTitle(master);
			masterForm.setMastername(mastername);
			masterForm.setMessage("Job Title Added Sucessfully!!");
			addActionMessage("Job Title Added Sucessfully!!");
			setFormDataofJobTitle();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "jobTitlePage";
		
	}
	public String edit(){
		if(!verifyLogin(request)){
			return "login";
		}
		int id =Integer.parseInt(request.getParameter("selectedid"));
		Connection connection = null;
		
		Master master = new Master();
		try{
			
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			
			master = masterDAO.getJobTitle(id,master);
			masterForm.setId(master.getId());
			masterForm.setJobTitle(master.getJobTitle());
			masterForm.setJobtitlegroup_id(master.getJobtitlegroup_id());
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return "editJobTitlePage";
	}
	
	public String update(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		int result = 0;
		try{
			int id = masterForm.getId();
			connection = Connection_provider.getconnection();
			Master master = new Master();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			
			master.setJobTitle(masterForm.getJobTitle());
			master.setJobtitlegroup_id(masterForm.getJobtitlegroup_id());
			masterForm.setMastername(mastername);
			
			result = masterDAO.updateJobTitle(master,id);
			masterForm.setMessage("Job Title Updated Sucessfully!!");
			addActionMessage("Job Title Updated Sucessfully!!");
			setFormDataofJobTitle();
		}
		catch (Exception e) {
			
		}
		return "jobTitlePage";
	}
	
	public String delete(){
		if(!verifyLogin(request)){
			return "login";
		}
		int result = 0;
		int id =Integer.parseInt(request.getParameter("selectedid"));
		Connection connection = null;
		Master master = new Master();
		
		try{
			
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			
			
 			result = masterDAO.deleteJobTitle(id,master);
 			masterForm.setMastername(mastername);
			masterForm.setMessage("Job Title Deleted Sucessfully!!");
			addActionMessage("Job Title Deleted Sucessfully!!");
			setFormDataofJobTitle();
		}
		catch (Exception e) {
			
		}
		return "jobTitlePage";
	}
	

	public MasterForm getModel() {
		// TODO Auto-generated method stub
		return masterForm;
	}


	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			
			ArrayList<Master> jobtitlegropulist=masterDAO.getJobgroupList();
			masterForm.setJobtitlegropulist(jobtitlegropulist);
			
			masterForm.setMasterlist(masterlist);
			mastername=(String)session.getAttribute("mastername");
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

}
