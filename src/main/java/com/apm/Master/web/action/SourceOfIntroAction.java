package com.apm.Master.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.SourceOfIntroDAO;
import com.apm.Master.eu.bi.TreatmentTypeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCSourceOfIntroDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCTreatmentTypeDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.SourceOfIntro;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.Master.web.form.SourceOfIntroForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.action.LoginAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class SourceOfIntroAction extends BaseAction implements Preparable, ModelDriven<SourceOfIntroForm>{

	SourceOfIntroForm sourceOfIntroForm = new SourceOfIntroForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	private String mastername;
	
	private static final Logger log = Logger.getLogger(SourceOfIntroAction.class);
	
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
			SourceOfIntroDAO sourceOfIntroDAO = new JDBCSourceOfIntroDAO(connection);
			int totalCount = sourceOfIntroDAO.getTotalSourceOfIntroCount();
			pagination.setPreperties(totalCount);
			ArrayList<SourceOfIntro> sourceOfIntroList = sourceOfIntroDAO.getsourceOfIntroList(pagination);
			pagination.setPage_records(sourceOfIntroList.size());
			sourceOfIntroForm.setTotalRecords(totalCount);
			sourceOfIntroForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			sourceOfIntroForm.setSourceOfIntroList(sourceOfIntroList);
			mastername = request.getParameter("selectedid");
			if (mastername != null) {

				session.setAttribute("mastername", mastername);
			} else {
				mastername = (String) session.getAttribute("mastername");
			}
			sourceOfIntroForm.setMastername(mastername);
			session.setAttribute("pagination", pagination);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "sourceOfIntroList";		
	}
	
	private void setFormData() {
		Connection connection = null;
		int result = 0;
		try{
			connection = Connection_provider.getconnection();
			SourceOfIntro sourceOfIntro = new SourceOfIntro();
			
			SourceOfIntroDAO sourceOfIntroDAO = new JDBCSourceOfIntroDAO(connection);
			pagination = (Pagination) session.getAttribute("pagination");
			int totalCount = sourceOfIntroDAO.getTotalSourceOfIntroCount();
			pagination.setPreperties(totalCount);
			ArrayList<SourceOfIntro> sourceOfIntroList = sourceOfIntroDAO.getsourceOfIntroList(pagination);
			pagination.setPage_records(sourceOfIntroList.size());
			sourceOfIntroForm.setTotalRecords(totalCount);
			sourceOfIntroForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			sourceOfIntroForm.setSourceOfIntroList(sourceOfIntroList);
		
		}
		catch(Exception e){
			e.printStackTrace();
			
		    log.debug("#error######"+e.getMessage());
		}
	}
	
	public String add(){
		return "addSourceOfIntro";
	}
	
	public String save(){
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		int result = 0;
		try{
			connection = Connection_provider.getconnection();
			SourceOfIntro sourceOfIntro = new SourceOfIntro();
			
			SourceOfIntroDAO sourceOfIntroDAO = new JDBCSourceOfIntroDAO(connection);
			sourceOfIntro.setSourceName(sourceOfIntroForm.getSourceName());
			sourceOfIntro.setDescription(sourceOfIntroForm.getDescription());
			
			result = sourceOfIntroDAO.saveSourceOfIntro(sourceOfIntro);
			sourceOfIntroForm.setMessage("SourceOfIntro Added Sucessfully!!");
			addActionMessage("SourceOfIntro Added Sucessfully!!");
			setFormData();
			sourceOfIntroForm.setMastername(mastername);
		}
		catch (Exception e) {
			e.printStackTrace();
			log.debug("#error######"+e.getMessage());
		}
		return "sourceOfIntroList";
	}
	
	public String edit(){
		if(!verifyLogin(request)){
			return "login";
		}
		int id =Integer.parseInt(request.getParameter("selectedid"));
		Connection connection = null;
		
		SourceOfIntro sourceOfIntro = new SourceOfIntro();
		try{
			
			connection = Connection_provider.getconnection();
			SourceOfIntroDAO sourceOfIntroDAO = new JDBCSourceOfIntroDAO(connection);
			
			sourceOfIntro = sourceOfIntroDAO.getSourceOfIntro(id,sourceOfIntro);
			sourceOfIntroForm.setId(sourceOfIntro.getId());
			sourceOfIntroForm.setSourceName(sourceOfIntro.getSourceName());
			sourceOfIntroForm.setDescription(sourceOfIntro.getDescription());
			sourceOfIntroForm.setDateTime(sourceOfIntro.getDateTime());
			sourceOfIntroForm.setMastername(mastername);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("#error######"+e.getMessage());
		}
		return "editSourceOfIntro";
	}
	
	public String update(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		int result = 0;
		try{
			int id = sourceOfIntroForm.getId();
			connection = Connection_provider.getconnection();
			SourceOfIntro sourceOfIntro = new SourceOfIntro();
			SourceOfIntroDAO sourceOfIntroDAO = new JDBCSourceOfIntroDAO(connection);
			
			sourceOfIntro.setId(id);
			sourceOfIntro.setSourceName(sourceOfIntroForm.getSourceName());
			sourceOfIntro.setDescription(sourceOfIntroForm.getDescription());
		
			result = sourceOfIntroDAO.updateSourceOfIntro(sourceOfIntro,id);
			
			sourceOfIntroForm.setMessage("SourceOfIntro Updated Sucessfully!!");
			addActionMessage("SourceOfIntro Updated Sucessfully!!");
			setFormData();
			sourceOfIntroForm.setMastername(mastername);
		}
		catch (Exception e) {
			 e.printStackTrace();
			 log.debug("#error######"+e.getMessage());
		}
		return "sourceOfIntroList";
	}
	
	public String delete(){
		
		if(!verifyLogin(request)){
			return "login";
		}
		int result = 0;
		int id =Integer.parseInt(request.getParameter("selectedid"));
		Connection connection = null;
		SourceOfIntro sourceOfIntro = new SourceOfIntro();
		
		try{			
			connection = Connection_provider.getconnection();
			SourceOfIntroDAO sourceOfIntroDAO = new JDBCSourceOfIntroDAO(connection);		
			
			result = sourceOfIntroDAO.deleteSourceOfIntro(id);	
			sourceOfIntroForm.setMessage("SourceOfIntro Deleted Sucessfully!!");
			addActionMessage("SourceOfIntro Deleted Sucessfully!!");
			setFormData();
			 
			sourceOfIntroForm.setMastername(mastername);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return "sourceOfIntroList";
	}
	
	public String back(){
		setFormData();
		sourceOfIntroForm.setMastername(mastername);
		return "sourceOfIntroList";
	}

	public SourceOfIntroForm getModel() {
		// TODO Auto-generated method stub
		return sourceOfIntroForm;
	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			sourceOfIntroForm.setMasterlist(masterlist);
			mastername=(String)session.getAttribute("mastername");
			
			
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
}
