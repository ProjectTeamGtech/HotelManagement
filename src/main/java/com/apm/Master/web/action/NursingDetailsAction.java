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

public class NursingDetailsAction extends BaseAction implements ModelDriven<MasterForm>, Preparable{

	MasterForm masterForm=new MasterForm();
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession(false);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	Pagination pagination=new Pagination(20,1);
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

	public String execute() throws Exception {
	    
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			
			String searchText = masterForm.getSearchText();
			
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			
			int count=masterDAO.getTotalNursingDetailsCount(searchText);
			pagination.setPreperties(count);
			ArrayList<Master> nursingdetails=masterDAO.getAllNursingDetails(pagination,searchText);
			masterForm.setNursingdetails(nursingdetails);
			pagination.setPage_records(nursingdetails.size());
		    masterForm.setTotalRecords(count); 	
            masterForm.setPagerecords(String.valueOf(pagination.getPage_records()));
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
     	     
		return super.execute();
	}
	
	public String add(){
		
		if(!verifyLogin(request)){
			return "login";
		}
		return "add";
	}
	
	
	
	public String addnewrow() throws Exception {

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> nursingcategorylist =masterDAO.getAllNursingCategory(null);
			int rowcount = Integer.parseInt(request.getParameter("rowcount"));
			int index = rowcount;
			index--;
			StringBuffer buffer = new StringBuffer();
			buffer.append("<tr>");
			buffer.append("<td><input type='checkbox' name='chkbox[]' title='Delete row' title='delete row' /></td>");
			buffer.append("<td>" + (rowcount) + "</td>");
			buffer.append("<td>");
			buffer.append("<select name='nursing_details["
					+ index
					+ "].nursingtype_id' id='nursingtype_id' class='form-control showToolTip chosen' title = 'Select Nursing Category'>");
			buffer.append("<option value='0'>Select Nursing Category</option>");
			for (Master master : nursingcategorylist) {
				buffer.append("<option value='" + master.getId() + "'>"
						+ master.getName() + "</option>");
			}
			buffer.append("</select>");
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append("<input type='text' name='nursing_details["
					+ index
					+ "].taskname' id='taskname' placeholder='enter taskname' class='form-control showToolTip filedname' data-toggle='tooltip' />");
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append("<input type='text' name='nursing_details["
					+ index
					+ "].frequency' id='frequency' placeholder='enter frequency' class='form-control showToolTip filedname' data-toggle='tooltip' />");
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append("<input type='text' name='nursing_details["
					+ index
					+ "].notes' id='notes' placeholder='enter notes' class='form-control showToolTip filedname' data-toggle='tooltip' />");
			buffer.append("</td>");
			buffer.append("</tr>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}

		finally {

			connection.close();
		}

		return null;

	}
	
	
	public String save() throws Exception {
		
		if(!verifyLogin(request)){
		   return "login";	
		}
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			
			for(Master master:masterForm.getNursing_details()){
				
				int result=masterDAO.addNursingDetails(master); 
				
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
	   
		return "save";
	}
	
	
	public String edit() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			String selectedid=request.getParameter("selectedid");
			Master master=masterDAO.getNursingDetails(selectedid);
			masterForm.setId(master.getId());
			masterForm.setNursingtype_id(master.getNursingtype_id());
			masterForm.setTaskname(master.getTaskname());
			masterForm.setFrequency(master.getFrequency());
			masterForm.setNotes(master.getFrequency()); 
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return "edit";
	}
	
	public String update() throws Exception {
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			
			Master master=new Master();
			master.setId(masterForm.getId());
			master.setNursingtype_id(masterForm.getNursingtype_id());
			master.setTaskname(masterForm.getTaskname());
			master.setFrequency(masterForm.getFrequency());
			master.setNotes(masterForm.getNotes());
			
			int result=masterDAO.updateNursingDetails(master);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}

	   	return "save";
	}
	
	public String delete() throws Exception {
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection); 
			String id=request.getParameter("selectedid");
			int result=masterDAO.deleteNursingDetails(id);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally{
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
 
			ArrayList<Master> nursingcategorylist=masterDAO.getAllNursingCategory(null);
			masterForm.setNursingcategorylist(nursingcategorylist);
			
			mastername = (String) session.getAttribute("mastername");
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			con.close();
		}
	}

	
	
}
