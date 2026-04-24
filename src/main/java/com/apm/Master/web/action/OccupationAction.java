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
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class OccupationAction extends BaseAction implements Preparable, ModelDriven<MasterForm>{
	
	MasterForm masterForm = new MasterForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	private Pagination pagination = new Pagination(20, 1);
	public Pagination getPagination() {
		return pagination;
	}

	private String mastername;

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
public String execute() {
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			
			String searchText = masterForm.getSearchText();
			
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			
			int totalCount = masterDAO.getTotalOccupationCount(searchText);
			pagination.setPreperties(totalCount);
			
			ArrayList<Master>occupationList = masterDAO.getOccupationList(pagination,searchText);
			pagination.setPage_records(occupationList.size());
			masterForm.setTotalRecords(totalCount);
			masterForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			masterForm.setOccupationList(occupationList);
			session.setAttribute("pagination", pagination);
			ArrayList<Master> masterlist=masterDAO.getMasterList(loginInfo);
			masterForm.setMasterlist(masterlist);
			
			String selectedid = request.getParameter("selectedid");
			masterForm.setMastername(selectedid);
			mastername = request.getParameter("selectedid");
			if (mastername != null) {

				session.setAttribute("mastername", mastername);
			} else {
				mastername = (String) session.getAttribute("mastername");
			}
			masterForm.setMastername(mastername);
			
			
		}
		catch (Exception e) {
			
		}
		
		return "occupationPage";
		
	}
	
	public void setFormData(){
		Connection connection = null;
		int result = 0;
		try{
			connection = Connection_provider.getconnection();
			Master master = new Master();
			
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			pagination = (Pagination) session.getAttribute("pagination");
		int totalCount = masterDAO.getTotalOccupationCount(null);
		pagination.setPreperties(totalCount);
		
		ArrayList<Master>occupationList = masterDAO.getOccupationList(pagination,null);
		pagination.setPage_records(occupationList.size());
		masterForm.setTotalRecords(totalCount);
		masterForm.setPagerecords(Integer.toString(pagination.getPage_records()));
		masterForm.setOccupationList(occupationList);
		
		}
		catch(Exception e){
			
		}
	}
	
	public String add(){
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		return "addOccupationPage";
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
			master.setOccupation(masterForm.getOccupation());
		
			
			result = masterDAO.saveOccupation(master);
			masterForm.setMessage("Occupation Added Sucessfully!!");
			addActionMessage("Occupation Added Sucessfully!!");
			setFormData();
		}
		catch (Exception e) {
			
		}
		
		return "save";
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
			
			master = masterDAO.getOccupation(id,master);
			masterForm.setId(master.getId());
			masterForm.setOccupation(master.getOccupation());
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return "editOccupationPage";
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
			
			master.setOccupation(masterForm.getOccupation());
		
			result = masterDAO.updateOccupation(master,id);
			
			masterForm.setMessage("Occupation Updated Sucessfully!!");
			addActionMessage("Occupation Updated Sucessfully!!");
			setFormData();
		}
		catch (Exception e) {
			
		}
		
		return "save";
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
			
			
			result = masterDAO.deleteOccupation(id,master);	
			masterForm.setMessage("Occupation Deleted Sucessfully!!");
			addActionMessage("Occupation Deleted Sucessfully!!");
			setFormData();
		}
		catch (Exception e) {
			
		}
		
		return "save";
	}
	
	public String back(){
		setFormData();
		
		return "save";
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
			masterForm.setMasterlist(masterlist);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
}
