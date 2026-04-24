package com.apm.Master.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Master.eu.bi.DischargeOutcomeDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCDischargeOutcomeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.web.form.MasterForm;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class OtherTemplateAction extends BaseAction implements
		ModelDriven<MasterForm>, Preparable {

	MasterForm masterForm = new MasterForm();
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session=request.getSession(false);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
    private String mastername;
    Pagination pagination=new Pagination(25,1);
	
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub

		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			int count=masterDAO.getOtherTemplateCount();
			pagination.setPreperties(count);
			String searchText = masterForm.getSearchText();
			
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			
			ArrayList<Master> otherTemplateList = masterDAO
					.getAllOtherTemplateList(searchText,pagination);
			masterForm.setOtherTemplateList(otherTemplateList);
			mastername = request.getParameter("selectedid");
			pagination.setPage_records(otherTemplateList.size());
			masterForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			masterForm.setTotalRecords(count);
			if (mastername != null) {

				session.setAttribute("mastername", mastername);
			} else {
				mastername = (String) session.getAttribute("mastername");
			}
			masterForm.setMastername(mastername);
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return SUCCESS;
	}

	public String add() {

		if (!verifyLogin(request)) {
			return "login";
		}
		try {
		Connection connection = Connection_provider.getconnection();
		MasterDAO masterDAO = new JDBCMasterDAO(connection);
		String payroll=DateTimeUtils.isNull(request.getParameter("action"));
		if(payroll.equals("1")) {
			ArrayList<Master>payrolltemplateList=masterDAO.getPayrollTemplateList();
			masterForm.setPayrolltemplatelist(payrolltemplateList);
		  }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "add";

	}

	public String save() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		String payroll=request.getParameter("action");
		Connection connection = null;
		try {
			
			connection=Connection_provider.getconnection();
            Master master=new Master();
       	   
            master.setTitle(masterForm.getTitle());
            master.setOther_template_text(masterForm.getOther_template_text());
            master.setDiscipline_id(masterForm.getDiscipline_id());
            master.setPayroll(payroll);
            MasterDAO masterDAO=new JDBCMasterDAO(connection);
            masterDAO.addOtherTemplate(master);         
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
        if(payroll.equals("1")) {
         return "move";	
        }else {
		return "save";
        }
	}

	
	public String edit() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
	 	
		Connection connection=null;
		
        try {
			
        	connection=Connection_provider.getconnection();
        	String selectedid=request.getParameter("selectedid");
        	MasterDAO masterDAO=new JDBCMasterDAO(connection);
        	Master master=masterDAO.getOtherTemplate(selectedid);
        	masterForm.setId(master.getId());
        	masterForm.setOther_template_text(master.getOther_template_text());
        	masterForm.setTitle(master.getTitle());
        	masterForm.setDiscipline_id(master.getDiscipline_id());
        	
		} catch (Exception e) {

		  e.printStackTrace();
		}		
        finally {
        	connection.close();
        }
		return "edit";
	}
	
	public String update() throws Exception{
		
		Connection connection=null;
		String payroll=DateTimeUtils.isNull(request.getParameter("action"));
		try {
		
			connection=Connection_provider.getconnection();
		    Master master=new Master();
		    master.setId(masterForm.getId());
			master.setTitle(masterForm.getTitle());
			master.setOther_template_text(masterForm.getOther_template_text());
			master.setDiscipline_id(masterForm.getDiscipline_id());
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			int result=masterDAO.updateOtherTemplate(master);			
			
		} catch (Exception e) {

		  e.printStackTrace();
		}
		finally{
			
			connection.close();
		}
	   if(payroll.equals("1")) {
		return "move";
	   }else {
	     return "save";
	   }
	} 
	
	public String delete() throws Exception {
		
		Connection connection=null;
		String payroll=DateTimeUtils.isNull(request.getParameter("action"));
		try {
			connection=Connection_provider.getconnection();
			String selectedid=request.getParameter("selectedid");
			MasterDAO dao=new JDBCMasterDAO(connection);
			int i=dao.deleteOtherTemplate(selectedid);
			
			
		} catch (Exception e) {

		  e.printStackTrace();
		}
	    finally{
	    	connection.close();
	    }
		if(payroll.equals("1")) {
			return "move";
		}else {
		return "save";
		}
	}
	
public String generateDeclaration() {
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		MasterDAO dao=new JDBCMasterDAO(connection);
		ArrayList<Master>declarationList=dao.getAllpayrolldeclarationList();
		String cdate = DateTimeUtils.getUKCurrentDataTime("India").split(" ")[0];
		String empid=request.getParameter("empid");
	
		masterForm.setDeclarationlist(declarationList);
		String declarationTitle = dao.getpayrollTitleOfDeclaration(loginInfo.getId());
		masterForm.setDeclarationtitle(declarationTitle);
		masterForm.setEmpid(empid);
		Master master=dao.getUserdatabyid(empid);
        String declarationNotes = dao.getpayrollDeclaration(loginInfo.getId());
        declarationNotes= declarationNotes.replace("[Employees Full Name]", master.getFullname());
        declarationNotes= declarationNotes.replace("[Job Title]", master.getJobtitle());
        declarationNotes= declarationNotes.replace("[Company Name]", master.getHosp_name());
        declarationNotes= declarationNotes.replace("[Date]","Date :"+ cdate);
		session.setAttribute("declarationNotes", declarationNotes);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "showdeclaration";
}
	
public String printpayroll() {
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		MasterDAO dao=new JDBCMasterDAO(connection);
		String empid=request.getParameter("empid");
		String declarationNotes=request.getParameter("declarationNotes");
		String declarationTitle=request.getParameter("declarationTitle12");
	
		int insert=dao.saveAppointmentdocs(empid,declarationNotes,declarationTitle);
		Master master=dao.getpayrollPrint(empid);
		session.setAttribute("declarationNotes", master.getOther_template_text());
		
		masterForm.setTitle(master.getTitle());
		session.setAttribute("declarationTile",master.getTitle());
		
		ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		Clinic clinic = new Clinic();
		clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
		ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
		masterForm.setClinicName(clinic.getClinicName());
		masterForm.setClinicOwner(clinic.getClinicOwner());
		masterForm.setOwner_qualification(clinic.getOwner_qualification());
		masterForm.setLocationAdressList(locationAdressList);
		masterForm.setAddress(clinic.getAddress());
		masterForm.setLandLine(clinic.getLandLine());
		masterForm.setClinicemail(clinic.getEmail());
		masterForm.setWebsiteUrl(clinic.getWebsiteUrl());
		masterForm.setClinicLogo(clinic.getUserImageFileName());
	} catch (Exception e) {
	 e.printStackTrace();
	}
	return "printpayroll";
}
	public void prepare() throws Exception {

		Connection con = null;

		try {
			con = Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(con);
			DischargeOutcomeDAO outcomeDAO = new JDBCDischargeOutcomeDAO(con);
			ArrayList<Master> masterlist = outcomeDAO.getMasterList(loginInfo);
			masterForm.setMasterlist(masterlist);
			
			
			ArrayList<Master> disciplineList=masterDAO.getDisciplineDataList();
			masterForm.setDisciplineList(disciplineList);
			mastername = (String) session.getAttribute("mastername");
			masterForm.setMastername(mastername);
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			con.close();
		}

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

	
	  public String getApmtDeclaration() { 
		  
		 
			  Connection connection=null;
				try {
					connection=Connection_provider.getconnection();
					MasterDAO dao=new JDBCMasterDAO(connection);
					ArrayList<Master>declarationList=dao.getAllpayrolldeclarationList();
					
				
					masterForm.setDeclarationlist(declarationList);
					String empid=dao.getEmpidbyloginid(loginInfo.getId());
					Master master=dao.getpayrollPrint(empid);
					session.setAttribute("declarationNotes", master.getOther_template_text());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "printpayroll";
	 
		  
	  }
	 
	
}
