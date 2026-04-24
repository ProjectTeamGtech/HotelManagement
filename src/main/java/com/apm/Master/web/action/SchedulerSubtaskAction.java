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

public class SchedulerSubtaskAction extends BaseAction implements ModelDriven<MasterForm>,Preparable{

	
	MasterForm masterForm = new  MasterForm();
	String mastername;
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession();
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
    Pagination pagination=new Pagination(25,1);
	
	
	
    
	public MasterForm getModel() {
		// TODO Auto-generated method stub
		return masterForm;
	}

	
	public String execute() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			int count = masterDAO.getTotalSchedulerSubtaskCount();
			pagination.setPreperties(count);
			String searchText = masterForm.getSearchText();
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			
			ArrayList<Master>schedulersubtasklist = masterDAO.getAllSchedulerSubtask(searchText, pagination);
			masterForm.setSchedulersubtasklist(schedulersubtasklist);
			mastername=request.getParameter("selectedid");
			pagination.setPage_records(schedulersubtasklist.size());
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
				
				ArrayList<Master>schedulerlist = masterDAO.getAllSchedulerTask(null, null);
				masterForm.setSchedulerlist(schedulerlist);
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
				/*Master master = new Master();*/
				
				
				for (Master master : masterForm.getSubtask_name()) {
					
					int result = masterDAO.addSchedulerSubtask(master);

				}
			/*	master.setSubtask(masterForm.getSubtask());
				master.setTaskname(masterForm.getTaskname());
				int result = masterDAO.addSchedulerSubtask(master);*/
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
				Master master2 = masterDAO.getSchedulerSubtaskinfo(master);
				masterForm.setId(master2.getId());
				masterForm.setParentid(master2.getParentid());
				masterForm.setSubtask(master2.getSubtask());
				
				ArrayList<Master>schedulerlist = masterDAO.getAllSchedulerTask(null, null);
				masterForm.setSchedulerlist(schedulerlist);
				
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
				master.setParentid(masterForm.getParentid());
				master.setSubtask(masterForm.getSubtask());
				
				int result =masterDAO.updateSchedulerSubtask(master);
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
				int result = masterDAO.deleteSchedulerSubtask(master);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "save";
		}
		
		public String addnewrow() throws Exception{
			Connection connection = null;
			try {
				connection = Connection_provider.getconnection();
				MasterDAO masterDAO = new JDBCMasterDAO(connection);
				
				ArrayList<Master>schedulerlist = masterDAO.getAllSchedulerTask(null, null);
				int rowcount = Integer.parseInt(request.getParameter("rowcount"));
				int index = rowcount;
				index--;
				StringBuffer buffer = new StringBuffer();
				buffer.append("<tr>");
				
				
				buffer.append("<td>" + (rowcount) + "</td>");
				
				buffer.append("<td>");
				buffer.append("<select name='subtask_name["
						+ index
						+ "].taskname' id='schedulerlist' class='form-control showToolTip chosen' title = 'Select Task Type'>");
				buffer.append("<option value='0'>Select Task Type</option>");
				for (Master master : schedulerlist) {
					buffer.append("<option value='" + master.getId() + "'>"
							+ master.getTaskname() + "</option>");
				}
				buffer.append("</select>");
				buffer.append("</td>");
				buffer.append("<td>");
				buffer.append("<input type='text' name='subtask_name["
						+ index
						+ "].subtask' id='name' placeholder='enter sub-task name' class='form-control showToolTip filedname' data-toggle='tooltip' />");
				buffer.append("</td>");
				buffer.append("<td>");
				
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(buffer.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
			
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
