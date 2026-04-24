package com.apm.Master.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
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

public class IpdTemplateAction extends BaseAction implements ModelDriven<MasterForm>,Preparable{

	MasterForm masterForm=new MasterForm();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpSession session=request.getSession();
	Pagination pagination=new Pagination(25,1);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	private String mastername;
	
	
	
	
	public MasterForm getModel() {
		
		return masterForm;
		
	}
	
	public String execute() throws Exception {

		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
		   	MasterDAO masterDAO=new JDBCMasterDAO(connection);
		   	
		   	String SearchText=masterForm.getSearchTextIPD();
		   	if(SearchText !=null)
		   	{
		   		if(SearchText.equals(""))
		   		{
		   			SearchText=null;
		   		}
		   	}
		   	
		   	int count=masterDAO.getIpdTemplateCount();
		   	
		   	pagination.setPreperties(count);
		   	masterForm.setTotalRecords(count);
		   	ArrayList<Master> ipd_templateList=masterDAO.getIpdTemplateList(pagination,SearchText);
		   	masterForm.setIpd_templateList(ipd_templateList);
		   	
		   	pagination.setPage_records(ipd_templateList.size());
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
			if(masterForm.getShowall()==null){
				masterForm.setShowall("0");
				
			}
		if(masterForm.getShowall().equals("on")){
			masterForm.setShowall("1");
		}
		if(masterForm.getShowall().equals("off")){
			masterForm.setShowall("0");
		}
			Master master=new Master();
			master.setTemplate_nameid(masterForm.getTemplate_nameid());
			master.setTitle(masterForm.getTitle());
			master.setTemplate_text(masterForm.getTemplate_text());
			master.setDepartment(masterForm.getDepartment());
			master.setShowall(masterForm.getShowall());
			int result=masterDAO.saveIpdTemplate(master);
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return "save";
	}
	
	
	public String edit() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			String id=request.getParameter("selectedid");
			Master master= masterDAO.getIpdTemplate(id);
			masterForm.setTemplate_nameid(master.getTemplate_nameid());
			masterForm.setTitle(master.getTitle());
			masterForm.setText(master.getText());
			masterForm.setId(master.getId());
			masterForm.setDepartment(master.getSpeciality());
			if(master.getShowall().equals("1")){
			masterForm.setShowall("1");
			}
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
			if(masterForm.getShowall()==null){
				masterForm.setShowall("0");
				
			}
		if(masterForm.getShowall().equals("on")){
			masterForm.setShowall("1");
		}
		if(masterForm.getShowall().equals("off")){
			masterForm.setShowall("0");
		}
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
		    Master master=new Master();
		    master.setTemplate_nameid(masterForm.getTemplate_nameid());
		    master.setTitle(masterForm.getTitle());
		    master.setText(masterForm.getText());
		    master.setId(masterForm.getId());
		    master.setSpeciality(masterForm.getDepartment());
		    master.setShowall(masterForm.getShowall());
		    int result=masterDAO.updateIpdTemplate(master);
		    
			
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
			String id=request.getParameter("selectedid");
			int result=masterDAO.deleteIpdTemplate(id);
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
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			masterForm.setMasterlist(masterlist);
			
			ArrayList<Master> ipd_template_names=masterDAO.getIpdTemplateNameList();
			masterForm.setIpd_template_names(ipd_template_names);
			mastername = (String) session.getAttribute("mastername");
			masterForm.setMastername(mastername);

			ArrayList<String> departmentList= diaryManagementDAO.getDepartmentList();
			masterForm.setDepartmentList(departmentList);
			
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
