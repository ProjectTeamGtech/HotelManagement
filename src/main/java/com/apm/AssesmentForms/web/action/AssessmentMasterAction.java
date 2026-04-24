package com.apm.AssesmentForms.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.AssesmentForms.eu.bi.AssessmentFormDAO;
import com.apm.AssesmentForms.eu.bi.AssessmentMasterDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCAssessmentFormDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCAssessmentMasterDAO;
import com.apm.AssesmentForms.eu.entity.Assessment;
import com.apm.AssesmentForms.web.form.AssessmentForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class AssessmentMasterAction extends BaseAction implements ModelDriven<AssessmentForm>{

	AssessmentForm assessmentForm = new AssessmentForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	private Pagination pagination = new Pagination(10, 1);
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	public String execute() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AssessmentMasterDAO assessmentMasterDAO = new JDBCAssessmentMasterDAO(connection);
			
			int totalCount = assessmentMasterDAO.getTotalAssessmentCount();
			pagination.setPreperties(totalCount);
			
			ArrayList<Assessment> assessmentList = assessmentMasterDAO.getAssessmentFieldList(pagination);
			pagination.setPage_records(assessmentList.size());
			assessmentForm.setTotalRecords(totalCount);
			assessmentForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			assessmentForm.setAssessmentList(assessmentList);
			session.setAttribute("pagination", pagination);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return SUCCESS;
	}
	
	public String add(){
		if(!verifyLogin(request)){
			return "login";
		}
		
		return "addAssessment";
	}
	
	public String save() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AssessmentMasterDAO assessmentMasterDAO = new JDBCAssessmentMasterDAO(connection);
			
			for(Assessment assessment1 : assessmentForm.getAssessment()){			
			
			
			Assessment assessment =  new Assessment();
			String field = assessment1.getFiledname();
			
	/*		String temp = field.replace(" ", "_");
			String temp1 = temp.replace("(", "_");
			String temp2 = temp1.replace(")", "_");
			String temp3 = temp2.replace("-", "_");
			String temp4 = temp3.replace("/", "_");
			String temp5 = temp4.replace("?", "_");
			String temp6 = temp5.replace(",", "_");
			String temp7 = temp6.replace("&", "_");
			String temp8 = temp7.replace("+", "_");
			String temp9 = temp8.replace(".", "_");
			String temp10 = temp9.replace("'", "_");*/
			
			String temp10 = DateTimeUtils.removeAllSpecialChar(field);
			temp10 = temp10.replace(" ", "");
			
			assessment.setFiledname(field);
			assessment.setClinicId(loginInfo.getClinicid());	
			
			int result1 = assessmentMasterDAO.updateAssessmentTemplate(temp10);
			
			int result = assessmentMasterDAO.saveAssessmentField(assessment,temp10);
			}
			assessmentForm.setMessage("Field Added Sucessfully!!");
			addActionMessage("Field Added Sucessfully!!");
			
			setFormData();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return SUCCESS;
	}
	private void setFormData() throws Exception{
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AssessmentMasterDAO assessmentMasterDAO = new JDBCAssessmentMasterDAO(connection);
			pagination = (Pagination) session.getAttribute("pagination");
			int totalCount = assessmentMasterDAO.getTotalAssessmentCount();
			pagination.setPreperties(totalCount);
			
			ArrayList<Assessment> assessmentList = assessmentMasterDAO.getAssessmentFieldList(pagination);
			pagination.setPage_records(assessmentList.size());
			assessmentForm.setTotalRecords(totalCount);
			assessmentForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			assessmentForm.setAssessmentList(assessmentList);
			
		}catch(Exception e){
			
		}finally{
			
			connection.close();
		}
	}

	
	public String edit() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		int selectedid = Integer.parseInt(request.getParameter("selectedid"));
		
		try{
			connection = Connection_provider.getconnection();
			AssessmentMasterDAO assessmentMasterDAO = new JDBCAssessmentMasterDAO(connection);
			Assessment assessment = new Assessment();
			assessment = assessmentMasterDAO.getAssessmentFieldDetails(selectedid);
			assessmentForm.setId(assessment.getId());
			assessmentForm.setFiledname(assessment.getFiledname());
			assessmentForm.setClinicId(assessment.getClinicId());
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return "editAssessment";
	}
	
	public String update() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
	//	int selectedId = Integer.parseInt(request.getParameter("id"));
		int selectedid = assessmentForm.getId();
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AssessmentMasterDAO assessmentMasterDAO = new JDBCAssessmentMasterDAO(connection);
			Assessment assessment =  new Assessment();
			assessment.setFiledname(assessmentForm.getFiledname());
			assessment.setClinicId(loginInfo.getId());						
			String updatedFieldName = assessmentForm.getFiledname();
			String existingFieldName = assessmentMasterDAO.getExistingFieldName(selectedid);
			
			String temp = DateTimeUtils.removeAllSpecialChar(existingFieldName);
			String temp1 = DateTimeUtils.removeAllSpecialChar(updatedFieldName);
			
			temp = temp.replace(" ", "");
			temp1 = temp1.replace(" ", "");
			
			int result = assessmentMasterDAO.updateAssessmentField(temp1,assessment,selectedid);			
			
			int result1 = assessmentMasterDAO.updateAssessmentTemplateColumn(temp1,temp);
			
			int r2 = assessmentMasterDAO.updatdateTemplateAssesmentFields(temp,temp1,updatedFieldName);
			
			assessmentForm.setMessage("Field Modified Sucessfully!!");
			addActionMessage("Field Modified Sucessfully!!");
			
			setFormData();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return SUCCESS;
	}
	
	public String delete() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		int selectedid = Integer.parseInt(request.getParameter("selectedid"));
		
		try{
			connection = Connection_provider.getconnection();
			AssessmentMasterDAO assessmentMasterDAO = new JDBCAssessmentMasterDAO(connection);
			
			String existingFieldName = assessmentMasterDAO.getExistingFieldName(selectedid);
			String temp = existingFieldName.replace(" ", "_");
			
			int result = assessmentMasterDAO.deleteAssessmentField(selectedid);
			
			int result1 = assessmentMasterDAO.deleteAssessmentTemplateColumn(temp);

			assessmentForm.setMessage("Field Removed Sucessfully!!");
			addActionMessage("Field Removed Sucessfully!!");
			
			setFormData();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return SUCCESS;
	}
	
	public String subHeadingList() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AssessmentMasterDAO assessmentMasterDAO = new JDBCAssessmentMasterDAO(connection);
			
			int totalCount = assessmentMasterDAO.getTotalSubHeadingCount();
			pagination.setPreperties(totalCount);
			
			ArrayList<Assessment> subHeadingList = assessmentMasterDAO.getAssessmentSubHeadingList(pagination);
			pagination.setPage_records(subHeadingList.size());
			assessmentForm.setTotalRecords(totalCount);
			assessmentForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			assessmentForm.setSubHeadingList(subHeadingList);
			session.setAttribute("pagination", pagination);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return "headingList";
	}
	
	public String addSubHeading(){
		if(!verifyLogin(request)){
			return "login";
		}
		
		return "addsubHeadingAssessment";
	}
	
	public String saveSubHeading() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AssessmentMasterDAO assessmentMasterDAO = new JDBCAssessmentMasterDAO(connection);
			
			for(Assessment assessment1 : assessmentForm.getSubhead()){			
			
			
			Assessment assessment =  new Assessment();
			String subheading = assessment1.getHeadingName();
			
				
			assessment.setHeadingName(subheading);
			assessment.setClinicId(loginInfo.getClinicid());	
			
			int result = assessmentMasterDAO.saveAssessmentSubHeading(assessment);
			}
			assessmentForm.setMessage("Field Added Sucessfully!!");
			addActionMessage("Field Added Sucessfully!!");
			
			setSubHeadingFormData();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return "headingList";
	}
	
	private void setSubHeadingFormData() throws Exception {
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AssessmentMasterDAO assessmentMasterDAO = new JDBCAssessmentMasterDAO(connection);
			pagination = (Pagination) session.getAttribute("pagination");
			int totalCount = assessmentMasterDAO.getTotalSubHeadingCount();
			pagination.setPreperties(totalCount);
			
			ArrayList<Assessment> subHeadingList = assessmentMasterDAO.getAssessmentSubHeadingList(pagination);
			pagination.setPage_records(subHeadingList.size());
			assessmentForm.setTotalRecords(totalCount);
			assessmentForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			assessmentForm.setSubHeadingList(subHeadingList);
			
		}catch(Exception e){
			
		}finally{
			
			connection.close();
		}
	}
	
	public String editSubHeading() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		int selectedid = Integer.parseInt(request.getParameter("selectedid"));
		
		try{
			connection = Connection_provider.getconnection();
			AssessmentMasterDAO assessmentMasterDAO = new JDBCAssessmentMasterDAO(connection);
			Assessment assessment = new Assessment();
			assessment = assessmentMasterDAO.getAssessmentSubHeadingDetails(selectedid);
			assessmentForm.setId(assessment.getId());
			assessmentForm.setHeadingName(assessment.getHeadingName());
			assessmentForm.setClinicId(assessment.getClinicId());
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return "editSubHeadingAssessment";
	}
	
	public String updateSubHeading() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
	//	int selectedId = Integer.parseInt(request.getParameter("id"));
		int selectedid = assessmentForm.getId();
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AssessmentMasterDAO assessmentMasterDAO = new JDBCAssessmentMasterDAO(connection);
			Assessment assessment =  new Assessment();
			assessment.setHeadingName(assessmentForm.getHeadingName());
			assessment.setClinicId(loginInfo.getClinicid());						
					
			int result = assessmentMasterDAO.updateAssessmentField(assessment,selectedid);			
			
			assessmentForm.setMessage("Field Modified Sucessfully!!");
			addActionMessage("Field Modified Sucessfully!!");
			
			setSubHeadingFormData();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return "headingList";
	}
	
	public String deleteSubHeading() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		int selectedid = Integer.parseInt(request.getParameter("selectedid"));
		
		try{
			connection = Connection_provider.getconnection();
			AssessmentMasterDAO assessmentMasterDAO = new JDBCAssessmentMasterDAO(connection);			
			
			int result = assessmentMasterDAO.deleteAssessmentSubHeading(selectedid);			
			
			assessmentForm.setMessage("Field Removed Sucessfully!!");
			addActionMessage("Field Removed Sucessfully!!");
			
			setSubHeadingFormData();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return "headingList";
	}

	public AssessmentForm getModel() {
		// TODO Auto-generated method stub
		return assessmentForm;
	}

}
