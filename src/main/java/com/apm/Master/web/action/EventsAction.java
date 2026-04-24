package com.apm.Master.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.web.form.MisDashboardForm;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.common.web.utils.PopulateList;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class EventsAction extends BaseAction implements ModelDriven<MisDashboardForm>,Preparable{

    MisDashboardForm dashboardForm=new MisDashboardForm();
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

	public MisDashboardForm getModel() {
		// TODO Auto-generated method stub
		return dashboardForm;
	}


	
	public String execute() throws Exception {
	
		Connection connection=null;
		try {
	
			connection=Connection_provider.getconnection();
			DiaryManagementDAO diaryManagementDAO=new JDBCDiaryManagentDAO(connection);
			int count=diaryManagementDAO.getTotalEventsCount();
			pagination.setPreperties(count);
			dashboardForm.setTotalRecords(count);
			ArrayList<DiaryManagement> eventList=diaryManagementDAO.getAllEventList(pagination);
			pagination.setPage_records(eventList.size());
			dashboardForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			dashboardForm.setEventList(eventList); 
			
			mastername = request.getParameter("selectedid");
			if (mastername != null) {

				session.setAttribute("mastername", mastername);
			} else {
				mastername = (String) session.getAttribute("mastername");
			}
			dashboardForm.setMastername(mastername);
			
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
	
	public String save()throws Exception {
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			DiaryManagementDAO diaryManagementDAO=new JDBCDiaryManagentDAO(connection);
		    DiaryManagement diaryManagement=new DiaryManagement();
		    diaryManagement.setEventname(dashboardForm.getEventname());
		    diaryManagement.setDescription(dashboardForm.getDescription());
		    diaryManagement.setTime(dashboardForm.getTime());
		    diaryManagement.setFromdate(dashboardForm.getFromdate());
		    diaryManagement.setTodate(dashboardForm.getTodate());
		    diaryManagement.setPlace(dashboardForm.getPlace());
		    diaryManagement.setJobtitle(dashboardForm.getJobtitle());
			
		    int result=diaryManagementDAO.saveEvent(diaryManagement);
		   
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		
		return "save";
	}
	
	
	public String edit() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			DiaryManagementDAO diaryManagementDAO=new JDBCDiaryManagentDAO(connection);
			String selectedid=request.getParameter("selectedid");
			DiaryManagement diaryManagement=diaryManagementDAO.getEvent(selectedid);
			ArrayList<DiaryManagement> jobtitleList=diaryManagementDAO.getAllJobTitleList();
			if(diaryManagement.getJobtitle()!=null){
				
				String titles[]=diaryManagement.getJobtitle().split(",");
	           	for(String str:titles){
	           		if(str.equals("0")){
	           			continue;
	           		} else {
	           			int index=Integer.parseInt(str);
	           			int oid=index-1;
	           			if(oid<jobtitleList.size()){
	           				jobtitleList.get(oid).setChecked(true);
	           			}
	           			
	           		}
	           	}
			}
			dashboardForm.setJobtitleList(jobtitleList);
			dashboardForm.setEventname(diaryManagement.getEventname());
			dashboardForm.setDescription(diaryManagement.getDescription());
			
			String timdata[]=diaryManagement.getTime().split(" ");
			
			dashboardForm.setTime1(timdata[0]);
			dashboardForm.setAmpm(timdata[1]);
			
			dashboardForm.setFromdate(diaryManagement.getFromdate());
			dashboardForm.setTodate(diaryManagement.getTodate());
			dashboardForm.setPlace(diaryManagement.getPlace());
			dashboardForm.setId(diaryManagement.getId());
			
			
		} catch (Exception e) {
			
			  e.printStackTrace();
		}		
		return "edit";
	}
	
    public String update()throws Exception {
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			DiaryManagementDAO diaryManagementDAO=new JDBCDiaryManagentDAO(connection);
		    DiaryManagement diaryManagement=new DiaryManagement();
		    diaryManagement.setEventname(dashboardForm.getEventname());
		    diaryManagement.setDescription(dashboardForm.getDescription());
		    diaryManagement.setTime(dashboardForm.getTime());
		    diaryManagement.setFromdate(dashboardForm.getFromdate());
		    diaryManagement.setTodate(dashboardForm.getTodate());
		    diaryManagement.setPlace(dashboardForm.getPlace());
		    diaryManagement.setJobtitle(dashboardForm.getJobtitle());
		    diaryManagement.setId(dashboardForm.getId());
			
		    int result=diaryManagementDAO.updateEvent(diaryManagement);
		   
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		
		return "save";
	}
	
	
	public String delete()throws Exception {
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			String id=request.getParameter("selectedid");
			DiaryManagementDAO diaryManagementDAO=new JDBCDiaryManagentDAO(connection);
			int result=diaryManagementDAO.deleteEvent(id);
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally{
			connection.close();
		}
	
		return "save";
	}
	
	
	
	
	
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			DiaryManagementDAO diaryManagementDAO=new JDBCDiaryManagentDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			dashboardForm.setMasterlist(masterlist);
			ArrayList<String> timelist=PopulateList.getSImpleTimeList();
			dashboardForm.setTimelist(timelist);
			
			ArrayList<DiaryManagement> jobtitleList=diaryManagementDAO.getAllJobTitleList();
			dashboardForm.setJobtitleList(jobtitleList); 
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}	
	}

	
	
	
}
