package com.apm.Master.web.action;

import java.sql.Connection;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Emr.web.form.EmrForm;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.PrescriptionMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCPrescriptionMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class PrescriptionCategoryAction extends BaseAction implements
		ModelDriven<EmrForm>, Preparable {

	EmrForm emrForm = new EmrForm();

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession(false);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	Pagination pagination = new Pagination(20, 1);
	String mastername;

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	@Override
	public String execute() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			PrescriptionMasterDAO masterDAO = new JDBCPrescriptionMasterDAO(
					connection);
			
			String searchText = emrForm.getSearchText();
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			
			int totalcount = masterDAO.getTotalPrescriptionCategoryCount(searchText);
			pagination.setPreperties(totalcount);

			ArrayList<Priscription> prescriptioncategorylist = masterDAO
					.getPrescriptionCategoryList(pagination,searchText);
			pagination.setPage_records(prescriptioncategorylist.size());
			emrForm.setTotalRecords(totalcount);
			emrForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			emrForm.setPrescriptioncategorylist(prescriptioncategorylist);
			mastername = request.getParameter("selectedid");
			if (mastername != null) {

				session.setAttribute("mastername", mastername);
			} else {
				mastername = (String) session.getAttribute("mastername");
			}
			emrForm.setMastername(mastername);
			session.setAttribute("pagination", pagination);

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
		return "add";
	}

	public String save() throws Exception {

		
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;

		try {

			connection=Connection_provider.getconnection();
            PrescriptionMasterDAO masterDAO=new JDBCPrescriptionMasterDAO(connection);
			for(Priscription priscription:emrForm.getPrescription_category()) {
			
				Priscription priscription2=new Priscription();
				String name=priscription.getName();
				String description=priscription.getDescription();
				priscription2.setName(name);
				priscription2.setDescription(description);
				int result=masterDAO.addPrescriptionCategory(priscription);
                
			}
						
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			connection.close();
		}

		return "save";
	}

	public String edit() throws Exception{
		
		if (!verifyLogin(request)) {
			return "login";
		}
		
		
		Connection connection=null;
		
		
		try {
			
			connection=Connection_provider.getconnection();
			PrescriptionMasterDAO prescriptionMasterDAO=new JDBCPrescriptionMasterDAO(connection);
			String selectedid=request.getParameter("selectedid");
			Priscription priscription=prescriptionMasterDAO.getPrescriptionCategory(selectedid); 
			emrForm.setId(priscription.getId());
			emrForm.setName(priscription.getName());
			emrForm.setDescription(priscription.getDescription());
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return "edit";
	}
	
	
	public String update() throws Exception {
		
		if (!verifyLogin(request)) {
			return "login";
		}
		
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			PrescriptionMasterDAO prescriptionMasterDAO=new JDBCPrescriptionMasterDAO(connection);
			Priscription priscription=new Priscription();
			priscription.setId(emrForm.getId());
			priscription.setName(emrForm.getName());
			priscription.setDescription(emrForm.getDescription());
			
			int result=prescriptionMasterDAO.updatePrescriptionCategory(priscription);
			
		} catch (Exception e) {

		  e.printStackTrace(); 
		}
		finally {
			connection.close();
		}
	   
		return "save";
	}
	
	
	public String delete() throws Exception {
		
		
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			
			PrescriptionMasterDAO prescriptionMasterDAO=new JDBCPrescriptionMasterDAO(connection); 
		    String selectedid=request.getParameter("selectedid"); 
		    int result=prescriptionMasterDAO.deletePrescriptionCategory(selectedid);
		   
		} catch (Exception e) {

		  e.printStackTrace();
		}
	
		return "save";
	}
	
	
	public String back() {
		return "save";
	}

	
	public void prepare() throws Exception {

		Connection con = null;

		try {
			con = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(con);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			emrForm.setMasterlist(masterlist);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			con.close();
		}

	}

	
	public EmrForm getModel() {
		// TODO Auto-generated method stub
		return emrForm;
	}

}
