package com.apm.Master.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.TreatmentTypeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCTreatmentTypeDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.Master.web.form.TreatmentTypeForm;
import com.apm.Registration.eu.entity.Location;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class TreatmentTypeAction extends BaseAction implements Preparable, ModelDriven<TreatmentTypeForm>{
	TreatmentTypeForm treatmentTypeForm = new TreatmentTypeForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	private Pagination pagination = new Pagination(20, 1);
	private String mastername;
	
	
	int practitionerID = loginInfo.getId();
	
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
			TreatmentTypeDAO treatmentTypeDAO = new JDBCTreatmentTypeDAO(connection);
			String searchText = treatmentTypeForm.getSearchText();
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			int totalCount = treatmentTypeDAO.getTotalTreatmentTypeCount(searchText);
			pagination.setPreperties(totalCount);
			ArrayList<TreatmentType> treatmentTypeList = treatmentTypeDAO.getTreatmentTypeList(pagination,searchText);
			pagination.setPage_records(treatmentTypeList.size());
			treatmentTypeForm.setTotalRecords(totalCount);
			treatmentTypeForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			treatmentTypeForm.setTreatmentTypeList(treatmentTypeList);
			session.setAttribute("pagination", pagination);
			mastername = request.getParameter("selectedid");
			if (mastername != null) {

				session.setAttribute("mastername", mastername);
			} else {
				mastername = (String) session.getAttribute("mastername");
			}
			treatmentTypeForm.setMastername(mastername);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "treatmentTypeList";		
	}
	
	private void setFormData() {
		Connection connection = null;
		int result = 0;
		try{
			connection = Connection_provider.getconnection();
			TreatmentType treatmentType = new TreatmentType();
			
			TreatmentTypeDAO treatmentTypeDAO = new JDBCTreatmentTypeDAO(connection);
			pagination = (Pagination) session.getAttribute("pagination");
			int totalCount = treatmentTypeDAO.getTotalTreatmentTypeCount(null);
			pagination.setPreperties(totalCount);		
			ArrayList<TreatmentType>treatmentTypeList = treatmentTypeDAO.getTreatmentTypeList(pagination,null);
			pagination.setPage_records(treatmentTypeList.size());
			treatmentTypeForm.setTotalRecords(totalCount);
			treatmentTypeForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			treatmentTypeForm.setTreatmentTypeList(treatmentTypeList);
			
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String add(){
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		//set admin emr
		try{
		if(loginInfo.getUserType()==2){
			//setAdminEmr();
			
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			
			ArrayList<DiaryManagement>userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
			
			treatmentTypeForm.setUserList(userList);
			
			if(!treatmentTypeForm.getDiaryUser().equals("")){
				session.setAttribute("selectedPractitioner", Integer.parseInt(treatmentTypeForm.getDiaryUser()));
				
				practitionerID = Integer.parseInt(treatmentTypeForm.getDiaryUser());
				session.setAttribute("practitionerID", practitionerID);
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "addTreatmentType";
	}
	
	public String save(){
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		int result = 0;
		try{
			connection = Connection_provider.getconnection();
			TreatmentType treatmentType = new TreatmentType();
			
			TreatmentTypeDAO treatmentTypeDAO = new JDBCTreatmentTypeDAO(connection);
			//treatmentType.setDiaryUser(treatmentTypeForm.getDiaryUser());
			treatmentType.setTreatmentName(treatmentTypeForm.getTreatmentName());
			treatmentType.setLocation(treatmentTypeForm.getLocation());
			//treatmentType.setTreatmentNotes(treatmentTypeForm.getTreatmentNotes());
			//treatmentType.setDateTime(treatmentTypeForm.getDateTime());		
			treatmentType.setIcd_code(treatmentTypeForm.getIcd_code());
			result = treatmentTypeDAO.saveTreatmentType(treatmentType);
			treatmentTypeForm.setMastername(mastername);
			treatmentTypeForm.setMessage("Condition Added Sucessfully!!");
			addActionMessage("Condition Added Sucessfully!!");
			setFormData();
		}
		catch (Exception e) {
			
		}
		return "treatmentTypeList";
	}

	public String edit(){
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		int id =Integer.parseInt(request.getParameter("selectedid"));		
		
		TreatmentType treatmentType = new TreatmentType();
		//set admin emr
		try{
			connection = Connection_provider.getconnection();
		if(loginInfo.getUserType()==2){
			//setAdminEmr();			
			
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			
			ArrayList<DiaryManagement>userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
			
			treatmentTypeForm.setUserList(userList);
			
			if(!treatmentTypeForm.getDiaryUser().equals("")){
				session.setAttribute("selectedPractitioner", Integer.parseInt(treatmentTypeForm.getDiaryUser()));
				
				practitionerID = Integer.parseInt(treatmentTypeForm.getDiaryUser());
				session.setAttribute("practitionerID", practitionerID);
			}
		}		
			
			
			TreatmentTypeDAO treatmentTypeDAO = new JDBCTreatmentTypeDAO(connection);
			
			treatmentType = treatmentTypeDAO.getTreatmentType(id,treatmentType);
			treatmentTypeForm.setId(treatmentType.getId());
			treatmentTypeForm.setTreatmentName(treatmentType.getTreatmentName());
			//treatmentTypeForm.setTreatmentNotes(treatmentType.getTreatmentNotes());
			treatmentTypeForm.setDateTime(treatmentType.getDateTime());
			treatmentTypeForm.setLocation(treatmentType.getLocation());
			treatmentTypeForm.setIcd_code(treatmentType.getIcd_code());
			//treatmentTypeForm.setDiaryUser(treatmentType.getDiaryUser());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "editTreatmentType";
	}
	
	public String update(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		int result = 0;
		try{
			int id = treatmentTypeForm.getId();
			connection = Connection_provider.getconnection();
			TreatmentType treatmentType = new TreatmentType();
			TreatmentTypeDAO treatmentTypeDAO = new JDBCTreatmentTypeDAO(connection);
			
			treatmentType.setId(id);
			treatmentType.setTreatmentName(treatmentTypeForm.getTreatmentName());
			treatmentType.setLocation(treatmentTypeForm.getLocation());
			//treatmentType.setTreatmentNotes(treatmentTypeForm.getTreatmentNotes());
			//treatmentType.setDiaryUser(treatmentTypeForm.getDiaryUser());
			//treatmentType.setDateTime(treatmentTypeForm.getDateTime());
			treatmentType.setIcd_code(treatmentTypeForm.getIcd_code());
			
		 	result = treatmentTypeDAO.updateTreatmentType(treatmentType,id);
		 	treatmentTypeForm.setMastername(mastername);	
			treatmentTypeForm.setMessage("Condition Updated Sucessfully!!");
			addActionMessage("Condition Updated Sucessfully!!");
			setFormData();
		}
		catch (Exception e) {
			
		}
		return "treatmentTypeList";
	}

	public String delete(){
		
		if(!verifyLogin(request)){
			return "login";
		}
		int result = 0;
		int id =Integer.parseInt(request.getParameter("selectedid"));
		Connection connection = null;
		TreatmentType treatmentType = new TreatmentType();
		
		try{
			
			connection = Connection_provider.getconnection();
			TreatmentTypeDAO treatmentTypeDAO = new JDBCTreatmentTypeDAO(connection);			
			
//			result = treatmentTypeDAO.deleteTreatmentType(id);	
			result=treatmentTypeDAO.updateDeleteDiagnosis(id);
			treatmentTypeForm.setMastername(mastername);
			treatmentTypeForm.setMessage("Condition Deleted Sucessfully!!");
			addActionMessage("Condition Deleted Sucessfully!!");
			setFormData();
		}
		catch (Exception e) {
			
		}
		
		return "treatmentTypeList";
	}
	
	public String back(){
		setFormData();
		treatmentTypeForm.setMastername(mastername);
		return "treatmentTypeList";
	}
	


	public TreatmentTypeForm getModel() {
		// TODO Auto-generated method stub
		return treatmentTypeForm;
	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			treatmentTypeForm.setMasterlist(masterlist);
			mastername=(String)session.getAttribute("mastername");
			
			
			ArrayList<Location>locationList = notAvailableSlotDAO.getLocationList(loginInfo.getId());
			treatmentTypeForm.setLocationList(locationList);
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

}
