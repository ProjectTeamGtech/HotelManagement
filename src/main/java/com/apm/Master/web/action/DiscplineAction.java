package com.apm.Master.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Master.eu.bi.DischargeStatusDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCDischargeStatus;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.web.form.MasterForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class DiscplineAction extends BaseAction implements ModelDriven<MasterForm>,Preparable{
	
	MasterForm masterForm = new MasterForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	private Pagination pagination = new Pagination(20, 1);
	private String mastername;
	
	public String execute() throws Exception {
		
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
			
			int totalCount = masterDAO.getTotalDisciplineCount(searchText);
			pagination.setPreperties(totalCount);
			
			ArrayList<Master>disciplineList = masterDAO.getDisciplineList(pagination,searchText);
			pagination.setPage_records(disciplineList.size());
			masterForm.setTotalRecords(totalCount);
			masterForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			masterForm.setDisciplineList(disciplineList);
			session.setAttribute("pagination", pagination);
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
		
		return super.execute();
	}
	
	
	public String editsave() throws SQLException{
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		int result = 0;
		try{
			connection = Connection_provider.getconnection();
			Master master = new Master();
			
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			
			master.setDiscipline(masterForm.getDiscipline());
			master.setDescription(masterForm.getDescription());
			master.setId(masterForm.getId());
			master.setArea(masterForm.getArea());
			master.setFloor(masterForm.getFloor());
			master.setRoom_no(masterForm.getRoom_no());
			master.setShowdeptlist(DateTimeUtils.booleanCheck(masterForm.getShowdeptlist()));
			result = masterDAO.updateDiscipline(master);
			masterForm.setMessage("Discipline Added Sucessfully!!");
			addActionMessage("Discipline Added Sucessfully!!");
			setformdata();
			
			masterForm.setMessage("Discipline Updated Sucessfully!!");
			addActionMessage("Discipline Updated Sucessfully!!");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		
		return "editsave";
	}
	
	
	public String edit() throws SQLException{
		
		if(!verifyLogin(request)){
				return "login";
			
		}
			
		Connection connection = null;
		
		String selectedid = request.getParameter("selectedid");
		
		try{
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			
			Master master = masterDAO.getDisciplineData(selectedid);
			
			masterForm.setId(Integer.parseInt(selectedid));
			masterForm.setDiscipline(master.getDiscipline());
			masterForm.setDescription(master.getDescription());
			masterForm.setArea(master.getArea());
			masterForm.setFloor(master.getFloor());
			masterForm.setRoom_no(master.getRoom_no());
			masterForm.setShowdeptlist(master.getShowdeptlist());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		
		return "edit";
	}
	
	public String save() throws SQLException{
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		int result = 0;
		try{
			connection = Connection_provider.getconnection();
			Master master = new Master();
			
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			
			master.setDiscipline(masterForm.getDiscipline());
			master.setDescription(masterForm.getDescription());
			master.setArea(masterForm.getArea());
			master.setFloor(masterForm.getFloor());
			master.setRoom_no(masterForm.getRoom_no());
			master.setShowdeptlist(DateTimeUtils.booleanCheck(masterForm.getShowdeptlist()));
			result = masterDAO.saveDiscipline(master);
			masterForm.setMessage("Discipline Added Sucessfully!!");
			addActionMessage("Discipline Added Sucessfully!!");
			setformdata();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return "save";
	}
	
	public void setformdata(){
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			
			int totalCount = masterDAO.getTotalDisciplineCount(null);
			pagination.setPreperties(totalCount);
			
			ArrayList<Master>disciplineList = masterDAO.getDisciplineList(pagination,null);
			pagination.setPage_records(disciplineList.size());
			masterForm.setTotalRecords(totalCount);
			masterForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			masterForm.setDisciplineList(disciplineList);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public String delete(){
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		
		String selectedid = request.getParameter("selectedid");
		
		try{
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			
			int del = masterDAO.deleteDiscipline(selectedid);
			
			setformdata();
			
			masterForm.setMessage("Discipline Deleted Sucessfully!!");
			addActionMessage("Discipline Deleted Sucessfully!!");
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "delete";
	}
	

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


	
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;

		try {
			con = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(con);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			masterForm.setMasterlist(masterlist);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		
	}
	
	
	

}
