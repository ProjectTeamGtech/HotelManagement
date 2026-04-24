package com.apm.Master.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.SpecializationDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCSpecializationDAO;
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

public class SpecializationAction extends BaseAction implements Preparable,ModelDriven<MasterForm>{
	
	MasterForm masterForm = new MasterForm(); 
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	private String mastername;
	
	
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
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
			SpecializationDAO specializationDAO = new JDBCSpecializationDAO(connection);
			int totalCount = specializationDAO.getTotalSpecializationCount();
			pagination.setPreperties(totalCount);
			
			ArrayList<Master> specializationList = specializationDAO.getSpecializationList(pagination);
			pagination.setPage_records(specializationList.size());
			masterForm.setTotalRecords(totalCount);
			masterForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			masterForm.setSpecializationList(specializationList);
			session.setAttribute("specializationList", specializationList);
			session.setAttribute("pagination", pagination);
			mastername = request.getParameter("selectedid");
			if (mastername != null) {

				session.setAttribute("mastername", mastername);
			} else {
				mastername = (String) session.getAttribute("mastername");
			}
			masterForm.setMastername(mastername);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "specializationList";
	}
	
	public String back(){
		setSpecializeFormData();
		masterForm.setMastername(mastername);
		return "specializationList";
	}
	
	public String add(){
		if(!verifyLogin(request)){
			return "login";
		}
		return "addSpecializationPage";
	}
	
	public String save(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		int result = 0;
		
		try{
			connection = Connection_provider.getconnection();
			SpecializationDAO specializationDAO = new JDBCSpecializationDAO(connection);
			Master master = new Master();
			master.setSpecialization(masterForm.getSpecialization());
			master.setDescription(masterForm.getDescription());
			
			result = specializationDAO.saveSpecialization(master);
			
			masterForm.setMessage("Specialization Added Sucessfully!!");
			addActionMessage("Specialization Added Sucessfully!!");
        			
			setSpecializeFormData();
			masterForm.setMastername(mastername);	
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "specializationList";
	}
	
	public String edit(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		int selectedId = Integer.parseInt(request.getParameter("selectedid"));
		try{
			connection = Connection_provider.getconnection();
			SpecializationDAO specializationDAO = new JDBCSpecializationDAO(connection);
			Master master = new Master();			
			
			master = specializationDAO.getSpecializationData(selectedId,master);
			masterForm.setId(master.getId());
			masterForm.setSpecialization(master.getSpecialization());
			masterForm.setDescription(master.getDescription());
			masterForm.setDatetime(master.getDatetime());
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "editSpecializationPage";
	}
	
	public String update(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		int id = Integer.parseInt(request.getParameter("id"));
		try{
			connection = Connection_provider.getconnection();
			SpecializationDAO specializationDAO = new JDBCSpecializationDAO(connection);
			Master master = new Master();
			master.setSpecialization(masterForm.getSpecialization());
			master.setDescription(masterForm.getDescription());
			//master.setDatetime(masterForm.getDatetime());
			
			int result = specializationDAO.updateSpecialization(id,master); 
			
			masterForm.setMessage("Specialization Updated Sucessfully!!");
			addActionMessage("Specialization Updated Sucessfully!!");
			masterForm.setMastername(mastername);	
			setSpecializeFormData();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "specializationList";
	}
	
	public String delete(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		int id = Integer.parseInt(request.getParameter("selectedid"));
		try{
			connection = Connection_provider.getconnection();
			SpecializationDAO specializationDAO = new JDBCSpecializationDAO(connection);
			
			int result = specializationDAO.deleteSpecialization(id);
			
			masterForm.setMessage("Specialization Deleted Sucessfully!!");
			addActionMessage("Specialization Deleted Sucessfully!!");
			
			setSpecializeFormData();
			masterForm.setMastername(mastername);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "specializationList";
	}
	public void setSpecializeFormData() {
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			SpecializationDAO specializationDAO = new JDBCSpecializationDAO(connection);
			pagination = (Pagination) session.getAttribute("pagination");
			int totalCount = specializationDAO.getTotalSpecializationCount();
			pagination.setPreperties(totalCount);
			
			ArrayList<Master> specializationList = specializationDAO.getSpecializationList(pagination);
			pagination.setPage_records(specializationList.size());
			masterForm.setTotalRecords(totalCount);
			masterForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			masterForm.setSpecializationList(specializationList);
			session.setAttribute("specializationList", specializationList);
			session.setAttribute("pagination", pagination);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
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
			mastername = (String) session.getAttribute("mastername");
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

}
