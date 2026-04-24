package com.apm.Master.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Diagnosis.eu.bi.DiagnosisDAO;
import com.apm.Diagnosis.eu.blogic.jdbc.JDBCDiagnosisDAO;
import com.apm.Diagnosis.eu.entity.Diagnosis;
import com.apm.Diagnosis.web.form.DiagnosisForm;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class DiagnosisNameMasterAction extends BaseAction implements ModelDriven<DiagnosisForm>,Preparable {

	DiagnosisForm diagnosisForm=new DiagnosisForm();
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession(false);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	private String mastername;
	
	Pagination pagination=new Pagination(10, 1);
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	@Override
	public String execute() throws Exception {

		if(!verifyLogin(request)) {
			return "login";
		}
		
		Connection connection=null;
		
		try {
		
			connection=Connection_provider.getconnection();
			DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
			int total=diagnosisDAO.getTotalDiagnosisNameCount();
			pagination.setPreperties(total);
			diagnosisForm.setTotalRecords(total);
			
			ArrayList<Diagnosis> diagnosislist=diagnosisDAO.getAllDiagnosisNameList(pagination);
			pagination.setPage_records(diagnosislist.size());
			diagnosisForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			
			diagnosisForm.setDiagnosislist(diagnosislist);
			
			mastername=request.getParameter("selectedid");
			if(mastername!=null) {
				
				session.setAttribute("mastername", mastername);
				
			}
			else {
				
				mastername=(String)session.getAttribute("mastername");
			}
			diagnosisForm.setMastername(mastername);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		
		return super.execute();
	}
	
	
	public String edit() throws Exception {
		
		if(!verifyLogin(request)) {
			
			return "login";
		}
		
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
			String selectedid=request.getParameter("selectedid");
			Diagnosis diagnosis=diagnosisDAO.getDiagnosisName(selectedid);
			diagnosisForm.setId(diagnosis.getId());
			diagnosisForm.setName(diagnosis.getName());
			diagnosisForm.setDescription(diagnosis.getDescription());
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		   
		}
		finally {
			connection.close();
		}
		
		
		return "edit";
	}
	
	public String update() throws Exception {
		
		if(!verifyLogin(request)) {
			return "login";
		}
		
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
			
			Diagnosis diagnosis=new Diagnosis();
			diagnosis.setId(diagnosisForm.getId());
			diagnosis.setName(diagnosisForm.getName());
			
			int result=diagnosisDAO.updateDiagnosisName(diagnosis);
			
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
			
			String selectedid=request.getParameter("selectedid");
			connection=Connection_provider.getconnection();
			DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
			
			int result=diagnosisDAO.deleteDiagnosisName(selectedid);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
			
		}
		
		return "save";
	}
	
	
	
	
	
	
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
		Connection con = null;

		try {
			con = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(con);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			diagnosisForm.setMasterlist(masterlist);

			mastername = (String) session.getAttribute("mastername");
			diagnosisForm.setMastername(mastername);
			
		
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			con.close();
		}
		
		
	}

	public DiagnosisForm getModel() {
		// TODO Auto-generated method stub
		return diagnosisForm;
	}

	
	
	
}
