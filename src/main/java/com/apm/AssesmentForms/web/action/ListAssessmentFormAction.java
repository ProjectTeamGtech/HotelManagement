package com.apm.AssesmentForms.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.AssesmentForms.eu.bi.AssessmentFormDAO;
import com.apm.AssesmentForms.eu.bi.ImportImageForAssessmentDAO;
import com.apm.AssesmentForms.eu.bi.ListAssessmentDAO;
import com.apm.AssesmentForms.eu.bi.Predefine.AssessmentTemplateDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCAssessmentFormDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCAssessmentMasterDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCImportImageAssessmentDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCListAssessmentFormDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.Predefine.JDBCAssessmentTemplateDAO;
import com.apm.AssesmentForms.eu.entity.Assessment;
import com.apm.AssesmentForms.eu.entity.Predefine.AssessmentTemplate;
import com.apm.AssesmentForms.web.form.AssessmentForm;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Emr.eu.bi.ConsultationNoteDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCConsultationNoteDAO;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Tools.web.action.AllTemplateAction;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class ListAssessmentFormAction extends BaseAction implements Preparable, ModelDriven<AssessmentForm>{

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
	
	
	public String input() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		/*Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			String clientId1 = (String) session.getAttribute("clientId");
			String templateId = (String) session.getAttribute("templateId");
			String diaryUserId = (String) session.getAttribute("diaryUserId");
			String conditionId = (String) session.getAttribute("conditionId");
			
			if(clientId1 !=null && templateId !=null && diaryUserId!=null && conditionId!=null)
			{
				int clientId = Integer.parseInt(clientId1);					
			
				//ListAssessmentDAO listAssessmentDAO = new JDBCListAssessmentFormDAO(connection);
				//String clientName = listAssessmentDAO.getClientFullName(clientId);
				//assessmentForm.setClientName(clientName);
			
				//setDropDownOfTemplateRedirect(clientId);
			
				//AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
				//String templateName = assessmentFormDAO.getTemplateName(templateId);
			
				ArrayList<Assessment> templateNameList = new ArrayList<Assessment>();
				templateNameList = listAssessmentDAO.getTemplateList(clientId1);	
					assessmentForm.setTemplateNameList(templateNameList);
			
					showTemplateListOfClient(clientId,templateId,clientName,templateName);
				//assessmentForm.setTemplateName(templateName);
			}else
			{				
				AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
				ArrayList<Assessment> templateNameList = new ArrayList<Assessment>();
				templateNameList = assessmentFormDAO.getTemplateList();
				assessmentForm.setTemplateNameList(templateNameList);
				
				
			}
			
			//session.removeAttribute("physioTemplateList");
			//session.removeAttribute("assessmentFormsList");
			//session.removeAttribute("columnList");
		
		}catch (Exception e) {
			e.printStackTrace();
		}*/
		
			session.removeAttribute("physioTemplateList");
			session.removeAttribute("assessmentFormsList");
			session.removeAttribute("columnList");
			
		return "viewListPage";
	}
	
	public String setListDetails() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			String clientId1 = (String) session.getAttribute("clientId");
			String templateId = (String) session.getAttribute("templateId");
			String diaryUserId = (String) session.getAttribute("diaryUserId");
			String conditionId = (String) session.getAttribute("conditionId");
			
			int clientId = Integer.parseInt(clientId1);		
			
			ListAssessmentDAO listAssessmentDAO = new JDBCListAssessmentFormDAO(connection);
			String clientName = listAssessmentDAO.getClientFullName(clientId);
			
			AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
			String templateName = assessmentFormDAO.getTemplateName(templateId);
			
			ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
			ArrayList<Client>clientList = consultationNoteDAO.getClientList(Integer.parseInt(diaryUserId));
			assessmentForm.setClientList(clientList);
			
			ArrayList<Client>conditionList = consultationNoteDAO.getConditionList(clientId);
			assessmentForm.setConditionList(conditionList);
			
			ArrayList<Assessment>templateNameList = listAssessmentDAO.getTemplateList(clientId1,diaryUserId,conditionId);			
			assessmentForm.setTemplateNameList(templateNameList);
			
			showTemplateListOfClient(clientId,templateId,clientName,templateName,diaryUserId,conditionId);
			
			assessmentForm.setClientName(clientId1);
			assessmentForm.setDiaryUser(diaryUserId);
			assessmentForm.setCondition(conditionId);
			assessmentForm.setTemplateName(templateId);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return "viewListPage";
	}
	
	private void showTemplateListOfClient(int clientId, String tempalteId,String clientname,String tempname,String diaryUserId,String conditionId) throws Exception {
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ListAssessmentDAO listAssessmentDAO = new JDBCListAssessmentFormDAO(connection);
			AssessmentTemplateDAO assessmentTemplateDAO = new JDBCAssessmentTemplateDAO(connection);
			
			if(tempname.equalsIgnoreCase("Physiotherpy Assesment form")){
				ArrayList<AssessmentTemplate> physioTemplateList = new ArrayList<AssessmentTemplate>();
				physioTemplateList = assessmentTemplateDAO.getPhysioTemplateList(Integer.toString(clientId),diaryUserId,conditionId);
				session.setAttribute("clientId", clientId);
				session.setAttribute("physioTemplateList", physioTemplateList);
				session.removeAttribute("assessmentFormsList");
				session.removeAttribute("columnList");
			}
			else {
				ArrayList<String> assessmentFormsList = listAssessmentDAO.getAssessmentFormList(Integer.toString(clientId),clientname,tempalteId,diaryUserId,conditionId); 
				ArrayList<Assessment> columnList = listAssessmentDAO.getColumnList(tempalteId);
				session.setAttribute("columnList", columnList);
				int cols = listAssessmentDAO.getColumnLength(tempalteId);
				int rows =0;
				if( assessmentFormsList.size()!=0){
					rows = assessmentFormsList.size()/cols;
			}
			if(cols !=0){
				session.setAttribute("rows", Integer.toString(rows));
				session.setAttribute("cols", Integer.toString(cols));
				
			}
				assessmentForm.setColumnSize(cols);
				assessmentForm.setAssessmentFormsList(assessmentFormsList);
				session.setAttribute("assessmentFormsList", assessmentFormsList);
				
				session.removeAttribute("physioTemplateList");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
	}

/*	private void setDropDownOfTemplateRedirect(int clientId) {
	
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ListAssessmentDAO listAssessmentDAO = new JDBCListAssessmentFormDAO(connection);
			ArrayList<Assessment> templateNameList = new ArrayList<Assessment>();
			
			//templateNameList = listAssessmentDAO.getTemplateList(Integer.toString(clientId));
			assessmentForm.setTemplateNameList(templateNameList);
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	
		
	}*/

	public String execute() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			
			//AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
			ListAssessmentDAO listAssessmentDAO = new JDBCListAssessmentFormDAO(connection);
			String clientId = assessmentForm.getClientName();
			String practId = assessmentForm.getDiaryUser();
			String conditionId = assessmentForm.getCondition();
			String clientname = assessmentForm.getClientName();
			String tempalteId = assessmentForm.getTemplateName();
			
			if(session.getAttribute("sessionselectedclientid")!=null && assessmentForm.getClientName().equals("")){
				clientId = (String)session.getAttribute("sessionselectedclientid");
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				
				NotAvailableSlot notAvailableSlot = clientDAO.getLastAppointmentdetails(clientId);
				practId = Integer.toString(notAvailableSlot.getDiaryUserId());
				conditionId = notAvailableSlot.getCondition();
				
				
			}
			
			
			AssessmentTemplateDAO assessmentTemplateDAO = new JDBCAssessmentTemplateDAO(connection);
			String tempname = assessmentTemplateDAO.getTemplateName(tempalteId);
			
			if(tempname.equalsIgnoreCase("Physiotherpy Assesment form")){
				ArrayList<AssessmentTemplate> physioTemplateList = new ArrayList<AssessmentTemplate>();
				physioTemplateList = assessmentTemplateDAO.getPhysioTemplateList(clientId,practId,conditionId);
				session.setAttribute("clientId", clientId);
				session.setAttribute("physioTemplateList", physioTemplateList);
				session.removeAttribute("assessmentFormsList");
				session.removeAttribute("columnList");
				
				ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
				ArrayList<Client>clientList = consultationNoteDAO.getClientList(Integer.parseInt(practId));
				assessmentForm.setClientList(clientList);
				
				ArrayList<Client>conditionList = consultationNoteDAO.getConditionList(Integer.parseInt(clientId));
				assessmentForm.setConditionList(conditionList);
				
				ArrayList<Assessment>templateNameList = listAssessmentDAO.getTemplateList(clientId,practId,conditionId);			
				assessmentForm.setTemplateNameList(templateNameList);
			}
			else {
			ArrayList<String> assessmentFormsList = listAssessmentDAO.getAssessmentFormList(clientId,clientname,tempalteId,practId,conditionId); 
			ArrayList<Assessment> columnList = listAssessmentDAO.getColumnList(tempalteId);
			session.setAttribute("columnList", columnList);
			int cols = listAssessmentDAO.getColumnLength(tempalteId);
			int rows =0;
			if( assessmentFormsList.size()!=0){
				rows = assessmentFormsList.size()/cols;
			}
			if(cols !=0){
			session.setAttribute("rows", Integer.toString(rows));
			session.setAttribute("cols", Integer.toString(cols));
			
			}
			assessmentForm.setColumnSize(cols);
			assessmentForm.setAssessmentFormsList(assessmentFormsList);
			
			ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
			ArrayList<Client>clientList = consultationNoteDAO.getClientList(Integer.parseInt(practId));
			assessmentForm.setClientList(clientList);
			
			ArrayList<Client>conditionList = consultationNoteDAO.getConditionList(Integer.parseInt(clientId));
			assessmentForm.setConditionList(conditionList);
			
			ArrayList<Assessment>templateNameList = listAssessmentDAO.getTemplateList(clientId,practId,conditionId);			
			assessmentForm.setTemplateNameList(templateNameList);
			 
			assessmentForm.setClientName(clientId);
			assessmentForm.setCondition(conditionId);
			assessmentForm.setTemplateName(tempalteId);
			assessmentForm.setDiaryUser(practId);
			//assessmentForm.setTemplateId(tempalteId);
			session.setAttribute("assessmentFormsList", assessmentFormsList);
			
			session.removeAttribute("physioTemplateList");
			}
			/*int totalCount = appointmentTypeDAO.getTotalApmtTypeCount();
			pagination.setPreperties(totalCount);
			
			ArrayList<AppointmentType>appointmentTypeList = appointmentTypeDAO.getAppointmentTypeList(pagination);
			pagination.setPage_records(appointmentTypeList.size());
			appointmentTypeForm.setTotalRecords(totalCount);
			appointmentTypeForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			appointmentTypeForm.setAppointmentTypeList(appointmentTypeList);
			session.setAttribute("pagination", pagination);*/
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		
		
		return "viewListPage";
	}
	
	public String dtr(){
		
		return "dtr";
	}
	
	public String opdbpdy() throws Exception{
		session.removeAttribute("imageData");
		try {
			return edit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return edit();
	}

	public String edit() throws Exception{
		String id = request.getParameter("id");
		session.setAttribute("clientassesmentfieldid", id);
		String mrdid = request.getParameter("mrdid");
		 String formtype = request.getParameter("formtype");
		if(!verifyLogin(request)){
			return "login";
		}
		
		session.removeAttribute("sessionassement");
		
		
		Connection connection = null;
		try{
			String columnStr ="";
			String actionType = request.getParameter("actionType");
			connection = Connection_provider.getconnection();
			ListAssessmentDAO listAssessmentDAO = new JDBCListAssessmentFormDAO(connection);
			ClientDAO clientDAO= new JDBCClientDAO(connection); 
			AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
			String tempId = listAssessmentDAO.getTemplateId(id);
			
			
			
			//check repeat
			boolean isRepeat = assessmentFormDAO.checkIsRepeatTemplate(tempId);
			session.setAttribute("isRepeat", isRepeat);
			assessmentForm.setRepeat(isRepeat);
			
			String action = request.getParameter("action");
			//ArrayList<Assessment> columnList = listAssessmentDAO.getColumnList(tempId);
		
			Assessment assessment2 = new Assessment();
			formtype=DateTimeUtils.numberCheck(formtype);
			if(!formtype.equals("0")){
				assessment2 = assessmentFormDAO.getCombineFormEditDetails(id);
			}else{
				assessment2 = listAssessmentDAO.getClientDetailsData(tempId,id);
			}
			
			tempId = assessment2.getTemplateId();
					
					
			int clientId =  Integer.parseInt(assessment2.getClientId());
			
			session.setAttribute("session_clientId", clientId);
			assessmentForm.setMrd_clientid(""+clientId);
			String diaryUserId = assessment2.getDiaryUserId();
			String conditionId = assessment2.getConditionId();
			String templateName = assessment2.getTemplateName();
			
			String oldpractid = diaryUserId;
			//check if doctor placed with machine
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(diaryUserId));
			
			if(userProfile.getJobgroup().equals("4")){
				diaryUserId = userProfile.getDoctor();
			}
			
			
			
			// set if patient is from ipd
			String ipdid = assessment2.getIpdid();
			BedDao bedDao = new JDBCBedDao(connection);
			IpdDAO  ipdDAO = new JDBCIpdDAO(connection);
			Bed bed = bedDao.getEditIpdData(ipdid);
			
			if(ipdid==null || ipdid.equals("0")){
				assessmentForm.setIpdid("0");
			}else{
				assessmentForm.setIpdid(ipdid);
			}
		
			
			
			String wardname=ipdDAO.getIpdWardName(bed.getWardid());
			assessmentForm.setWardname(wardname);
			String bedname = ipdDAO.getIpdBedName(bed.getBedid());
			assessmentForm.setBedname(bedname);

    		String numcount=ipdDAO.getNumofAdmissionCount(Integer.toString(clientId));
    		assessmentForm.setNum_admission(numcount);
			
			
			//get template details
			Template template = assessmentFormDAO.getTemplateDetails(tempId);
			template.setClientid(Integer.toString(clientId));
			template.setDiaryuserid(diaryUserId);
			template.setConditionid(conditionId);
			template.setTemplateid(tempId);
			
			assessmentForm.setMrdid(mrdid);
			session.setAttribute("assmnttemplatedetails", template);
			
			String clientName = listAssessmentDAO.getClientFullName(clientId);
			
			String diaryUser = listAssessmentDAO.getDiaryUserName(diaryUserId);
			
			String condition = listAssessmentDAO.getConditionName(conditionId);
			
			
			assessmentForm.setDoctorname(diaryUser);
			assessmentForm.setActionType(actionType);
	     	assessmentForm.setClientFullName(clientName);
	     	
	     	if(formtype.equals("0")){
	     		setAssesmentForm(id, tempId, connection);
	     	}
	     	
	     	
	     	ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
			ArrayList<Client>conditionList = consultationNoteDAO.getConditionList(clientId);
			assessmentForm.setConditionList(conditionList);
			
			 ArrayList<Client>clientList = consultationNoteDAO.getClientListEmr(Integer.parseInt(diaryUserId),Integer.toString(clientId),oldpractid,"0");
			assessmentForm.setClientList(clientList);
			
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			Clinic locationdirection = new Clinic();
	     	//locationdirection = clinicDAO.getLocationDetails(Integer.parseInt(locationID));
	     	
			AllTemplateAction allTemplateAction = new AllTemplateAction();
			Clinic clinic = new Clinic();
	     	int clinicID = loginInfo.getClinicid();
	     
	     	clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
	     	String headerData = allTemplateAction.header(clinic.getClinicName(), clinic.getClinicOwner(), clinic.getOwner_qualification(), locationdirection.getAddress(), 
					clinic.getEmail(),locationdirection.getContactNo(), clinic.getWebsiteUrl(),loginInfo.getClinicid(),connection);
	     	session.setAttribute("headerData", headerData);
		
	     
	     	
	     	
	     	Client client1= new Client();
	     	ClientDAO clientDAO2= new JDBCClientDAO(connection);
	     	client1= clientDAO2.getClientDetails(String.valueOf(clientId));
	     	assessmentForm.setAbrivationid(client1.getAbrivationid());
	     	assessmentForm.setAgegender(client1.getAge1()+"/"+client1.getGender());
	     	assessmentForm.setContact(client1.getMobNo());
	     	
	     	//get clinic address header
	    	
			
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			//String address = accountsDAO.getLocationAddress(locationid,loginInfo.getId());
			
			 
			  assessmentForm.setClinicName(clinic.getClinicName());
			  assessmentForm.setClinicOwner(clinic.getClinicOwner());
			  assessmentForm.setOwner_qualification(clinic.getOwner_qualification());
			assessmentForm.setLocationAdressList(locationAdressList);
			  assessmentForm.setAddress(clinic.getAddress());
			  assessmentForm.setLandLine(clinic.getLandLine());
			  assessmentForm.setClinicemail(clinic.getEmail());
			  assessmentForm.setWebsiteUrl(clinic.getWebsiteUrl());
			  assessmentForm.setClinicLogo(clinic.getUserImageFileName());

	     	
		     	if(action!=null && formtype.equals("0")){
		     	 	if(action.equals("print")){
			     		assessmentForm.setDiaryUser(diaryUser);
			     		assessmentForm.setClientName(clientName);
			     		assessmentForm.setCondition(condition);
			     		
			     		return "printAssementPageOfClient";
			     	}
		     	 	if(action.equals("dtr") && formtype.equals("0")){
		     	 		assessmentForm.setDiaryUser(diaryUser);
			     		assessmentForm.setClientName(clientName);
			     		assessmentForm.setCondition(condition);
		     	 		return "dtr";
		     	 	}
		     	}
		    
		     	assessmentForm.setFormtype(formtype);
		     if(!formtype.equals("0")){
					ArrayList<Master>leftNameList = assessmentFormDAO.getLeftNameList(tempId);
					ArrayList<Master>topNameList = assessmentFormDAO.getTopNameList(tempId);
					
					session.setAttribute("leftNameList", leftNameList);
					session.setAttribute("topNameList", topNameList);
					session.setAttribute("assementtemplateId", tempId);
					
					Assessment assessment = assessmentFormDAO.getCombineFormEditDetails(id);
					session.setAttribute("combineformclientid", assessment.getClientId());
					session.setAttribute("combineformdoctorid", assessment.getDiaryUserId());
					session.setAttribute("combineformconditionid", assessment.getConditionId());
					
					assessmentForm.setDiaryUser(assessment.getDiaryUserId());
		     		assessmentForm.setClientName(assessment.getClientId());
		     		assessmentForm.setCondition(assessment.getConditionId());
		     		
		     		String imageData = assessment.getImageData();
		     		session.setAttribute("imageData", imageData);
		     		
		     		assessmentForm.setTemplateName(assessment.getTemplateName());
		     		
		     		if(action!=null){
		     			if(action.equals("print")){
			     			return "printcombineformdetails";
			     		}
		     		}
		     		
					
					return "editcombineformdetails";
				}
		    	 
			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return "editAssementPageOfClient";
	}
	
	
	public void setAssesmentForm(String id,String tempId,Connection connection){
		String columnStr = "";
		ListAssessmentDAO listAssessmentDAO = new JDBCListAssessmentFormDAO(connection);
		ArrayList<Assessment> columnList =  listAssessmentDAO.getColumnFieldList(tempId);
		for(Assessment a : columnList){
			 columnStr = a.getColumnstr();
		}
		ArrayList<String> assessmentFormsList = listAssessmentDAO.getAssessmentFormClientDetails(id,columnStr,tempId);
			Assessment assessment = new Assessment();
			
			AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
			
			String imageData = listAssessmentDAO.getImageDetails(id);
			session.setAttribute("imageData", imageData);
			
			ArrayList<Assessment> fieldNameList = new ArrayList<Assessment>();
			fieldNameList = listAssessmentDAO.getFieldNameListById(tempId,id);
			
			//int clientId = listAssessmentDAO.getClientNameData(tempId,id);
			Assessment assessment2 = listAssessmentDAO.getClientDetailsData(tempId,id);
			int clientId =  Integer.parseInt(assessment2.getClientId());
			String diaryUserId = assessment2.getDiaryUserId();
			String conditionId = assessment2.getConditionId();
			String templateName = assessment2.getTemplateName();
			
			
			String oldpractid = assessment2.getOldpractid();
			//check if doctor placed with machine
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(oldpractid));
			
			if(!oldpractid.equals("0")){
				if(userProfile.getJobgroup().equals("4")){
					diaryUserId = userProfile.getDoctor();
				}
			}
			
			
			
			
			assessmentForm.setClientId(Integer.toString(clientId));
			assessmentForm.setDiaryUserId(diaryUserId);
			assessmentForm.setConditionId(conditionId);
			assessmentForm.setTemplateName(templateName);
			
			
			
			 
			assessmentForm.setClientName(assessment2.getClientId());
			assessmentForm.setDiaryUser(diaryUserId);
			assessmentForm.setCondition(conditionId);
			
			//assessmentForm.setFieldNameList(fieldNameList);
			session.setAttribute("fieldNameList", fieldNameList);
			session.setAttribute("assessmentFormsList", assessmentFormsList);
			session.setAttribute("columnStr", columnStr);

			}
		
	
	public String updatecombineformimg() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		String columnStr ="";
		try{
			connection = Connection_provider.getconnection();
			ListAssessmentDAO listAssessmentDAO = new JDBCListAssessmentFormDAO(connection);
			String id = request.getParameter("id");
			
			String combineformclientid = (String)session.getAttribute("combineformclientid");
			String combineformdoctorid = (String)session.getAttribute("combineformdoctorid");
			String combineformconditionid = (String)session.getAttribute("combineformconditionid");
			
			 String assementtemplateId = (String)session.getAttribute("assementtemplateId");
			 
			 int result = listAssessmentDAO.updateCombineImageTemplate(id,assessmentForm.getImageData());
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			connection.close();
		}
		
		return edit();
	}
	
	public String updateImageTemplate() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		String columnStr ="";
		try{
			connection = Connection_provider.getconnection();
			String id = request.getParameter("id");
			int id1 = Integer.parseInt(id);
			String actionType = request.getParameter("actionType");
			ListAssessmentDAO listAssessmentDAO = new JDBCListAssessmentFormDAO(connection);
			int result = listAssessmentDAO.updateImageTemplate(id1,assessmentForm.getImageData());

			String tempId = listAssessmentDAO.getTemplateId(id);
			//ArrayList<Assessment> columnList = listAssessmentDAO.getColumnList(tempId);
			ArrayList<Assessment> columnList =  listAssessmentDAO.getColumnFieldList(tempId);
			for(Assessment a : columnList){
				columnStr = a.getColumnstr();
			}
			ArrayList<String> assessmentFormsList = listAssessmentDAO.getAssessmentFormClientDetails(id,columnStr,tempId);
				Assessment assessment = new Assessment();
				
				AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
				
				String imageData = listAssessmentDAO.getImageDetails(id);
				session.setAttribute("imageData", imageData);
				
				ArrayList<Assessment> fieldNameList = new ArrayList<Assessment>();
				fieldNameList = listAssessmentDAO.getFieldNameListById(tempId,id);
				
				//int clientId = listAssessmentDAO.getClientNameData(tempId,id);
				Assessment assessment2 = listAssessmentDAO.getClientDetailsData(tempId,id);
				int clientId =  Integer.parseInt(assessment2.getClientId());
				String diaryUserId = assessment2.getDiaryUserId();
				String conditionId = assessment2.getConditionId();
				String templateName = assessment2.getTemplateName();
				
				String clientName = listAssessmentDAO.getClientFullName(clientId);
				
				String diaryUser = listAssessmentDAO.getDiaryUserName(diaryUserId);
				
				String condition = listAssessmentDAO.getConditionName(conditionId);
				
				assessmentForm.setClientId(Integer.toString(clientId));
				assessmentForm.setDiaryUserId(diaryUserId);
				assessmentForm.setConditionId(conditionId);
				assessmentForm.setTemplateName(templateName);
				assessmentForm.setClientFullName(clientName);
				
				
				ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
				ArrayList<Client>conditionList = consultationNoteDAO.getConditionList(clientId);
				assessmentForm.setConditionList(conditionList);
				
				ArrayList<Client>clientList = consultationNoteDAO.getClientList(Integer.parseInt(diaryUserId));
				assessmentForm.setClientList(clientList);
				 
				assessmentForm.setClientName(assessment2.getClientId());
				assessmentForm.setDiaryUser(diaryUserId);
				assessmentForm.setCondition(conditionId);
				assessmentForm.setActionType(actionType);
				
				//assessmentForm.setFieldNameList(fieldNameList);
				session.setAttribute("fieldNameList", fieldNameList);
				session.setAttribute("assessmentFormsList", assessmentFormsList);
				session.setAttribute("columnStr", columnStr);

				ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
				Clinic locationdirection = new Clinic();
		     	//locationdirection = clinicDAO.getLocationDetails(Integer.parseInt(locationID));
		     	
				AllTemplateAction allTemplateAction = new AllTemplateAction();
				Clinic clinic = new Clinic();
		     	int clinicID = loginInfo.getClinicid();
		     
		     	clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
		     	String headerData = allTemplateAction.header(clinic.getClinicName(), clinic.getClinicOwner(), clinic.getOwner_qualification(), locationdirection.getAddress(), 
						clinic.getEmail(),locationdirection.getContactNo(), clinic.getWebsiteUrl(),loginInfo.getClinicid(),connection);
		     	session.setAttribute("headerData", headerData);
		     	
		     	
		     	//get clinic address header
		    	
				clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
				
				ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
				//String address = accountsDAO.getLocationAddress(locationid,loginInfo.getId());
				
				assessmentForm.setClinicName(clinic.getClinicName());
				assessmentForm.setClinicOwner(clinic.getClinicOwner());
				assessmentForm.setOwner_qualification(clinic.getOwner_qualification());
				assessmentForm.setLandLine(clinic.getLandLine());
				assessmentForm.setWebsiteUrl(clinic.getWebsiteUrl());
				assessmentForm.setClinicemail(clinic.getEmail());
				assessmentForm.setLocationAdressList(locationAdressList);
				
				
			
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return "editAssementPageOfClient";
	}
	
	public String updateTemplate() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			int id = Integer.parseInt(request.getParameter("id"));
			String clientId = request.getParameter("clientName");
			String diaryUserId = request.getParameter("diaryUser");
			String conditionId = request.getParameter("condition");
			ListAssessmentDAO listAssessmentDAO = new JDBCListAssessmentFormDAO(connection);
			
			int result1 = listAssessmentDAO.updateAssessmentClientNameImage(id,clientId,diaryUserId,conditionId);
			
			ArrayList<Assessment> fieldNameList = (ArrayList<Assessment>) session.getAttribute("fieldNameList");
			for(Assessment assessment : fieldNameList){
				
				String lableName = assessment.getFiledname();
			
			
			/*	String fieldValue = lableName.replace(" ", "_");
				String temp1 = fieldValue.replace("(", "_");
				String temp2 = temp1.replace(")", "_");
				String temp3 = temp2.replace("-", "_");
				String temp4 = temp3.replace("/", "_");
				String temp5 = temp4.replace("?", "_");
				String temp6 = temp5.replace(",", "_");
				String temp7 = temp6.replace("&", "_");
				String temp8 = temp7.replace("+", "_");
				String temp9 = temp8.replace(".", "_");
				String temp10 = temp9.replace("'", "_");
				*/
				
				String temp10 = DateTimeUtils.removeAllSpecialChar(lableName);
				temp10 = temp10.replace(" ", "");
				
				String textName = request.getParameter(lableName);
				
				String note = "";
				if(assessment.getType().equals("5")){
					note = request.getParameter("note_"+lableName);
					textName = textName + ":" + note;
				}
				
				if(assessment.getType().equals("6")){
					note = request.getParameter("note_"+lableName);
					note = request.getParameter("note_"+lableName);
					textName = textName + ":" + note;
				}
				
				if(assessmentForm.isRepeat()==false){
					int result = listAssessmentDAO.updateAssessmentFormClient(id,temp10,lableName,textName,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
				}else{
					AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
					Template template = (Template)session.getAttribute("assmnttemplatedetails");
					String clientassesmentfieldid = (String)session.getAttribute("clientassesmentfieldid");
					ArrayList<Assessment>dataList = assessmentFormDAO.getRepeatFormData(template,assessment.getFiledname(),clientassesmentfieldid);
					int r=0;
					for(Assessment data : dataList){
						String strtxt = request.getParameter(assessment.getFiledname() + r);
						int updte = assessmentFormDAO.updateRepeatFormData(strtxt,data.getId(),temp10,clientId,diaryUserId,conditionId);
					}
				}
				
			}
			
			String templateId = listAssessmentDAO.getTemplateId(id);
			
			session.setAttribute("clientId", clientId);
			session.setAttribute("diaryUserId", diaryUserId);
			session.setAttribute("conditionId", conditionId);
			session.setAttribute("templateId", templateId);
			
			assessmentForm.setMessage("Field Updated Sucessfully!!");
			addActionMessage("Field Updated Sucessfully!!");
			
			//assessmentForm.setClientName("");
			
		}catch(Exception e){
			
		}finally{
			
			connection.close();
		}
		
		return "updateListPage";
	}
	
	public String delete() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			int id = Integer.parseInt(request.getParameter("id"));
			String clientId = assessmentForm.getClientName();
			String diaryUserId = assessmentForm.getDiaryUser();
			String conditionId = assessmentForm.getCondition();
			String templateId = assessmentForm.getTemplateName();
			
			ListAssessmentDAO listAssessmentDAO = new JDBCListAssessmentFormDAO(connection);
			int result = listAssessmentDAO.deleteAssessmentClient(id);
			
			session.setAttribute("clientId", clientId);
			session.setAttribute("diaryUserId", diaryUserId);
			session.setAttribute("conditionId", conditionId);
			session.setAttribute("templateId", templateId);
			
			assessmentForm.setMessage("Field Deleted Sucessfully!!");
			addActionMessage("Field Deleted Sucessfully!!");
			
		}catch(Exception e){
			
		}finally{
			
			connection.close();
		}
		
		return "updateListPage";
	}
	public String showList() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		Client client = new Client();
		ArrayList<Client> allPatientList = new ArrayList<Client>();
		try{
			connection = Connection_provider.getconnection();
			
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			allPatientList = clientDAO.getAllPatient(loginInfo.getId(),"");
			
			//clientForm.setAllPatientList(allPatientList);
			
			
			StringBuffer str = new StringBuffer();
			

			
			str.append("<table class='table table-hover table-condensed table-bordered table-striped ' > ");
			str.append("<thead>");
			str.append("<tr class='bg-info'>");
			str.append("<th>Id</th> ");

			str.append("<th>Name</th> ");
			str.append("<th>Old Client Id</th> ");
			str.append("<th>Mobile</th> ");
			str.append("<th>Email</th> ");
			str.append("<th>PostCode</th> ");
			str.append("<th>Dob</th> ");
			str.append("<th>LastModified</th> ");

			str.append("</tr>");
			str.append("</thead>");
			
			str.append("<tbody>");
			for(Client client1:allPatientList){
				String name = client1.getTitle()+" "+client1.getFirstName()+" "+client1.getLastName(); 		
			str.append("<tr>");
			str.append("<td>0000"+client1.getId()+"</td>");

			String firstName= client1.getFirstName();
			str.append("<td style='cursor: pointer;'; onclick = setPatientNameAssessment('"+firstName+"','"+client1.getId()+"','"+client1.getType()+"','"+client1.getTypeName()+"')>"+name+"</td>");
			if(client1.getOldclientId()==null){
				client1.setOldclientId("");
			}
			str.append("<td>"+client1.getOldclientId()+"</td>");
			str.append("<td>"+client1.getMobNo()+"</td>");
			str.append("<td>"+client1.getEmail()+"</td>");
			str.append("<td>"+client1.getPostCode()+"</td>");
			str.append("<td>"+client1.getDob()+"</td>");
			if(client1.getLastModified()==null){
				client1.setLastModified("");
			}
			str.append("<td>"+client1.getLastModified()+"</td>");

			str.append("</tr>");
			}
			str.append("</tbody>");
			str.append("</table>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+str.toString()+""); 
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			connection.close();
		}
		
		return null;
		
	}
	
	public String setDropDownOfTemplate() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		String conditionId = request.getParameter("conditionId");
		String clientId = request.getParameter("clientId");
		String practId = request.getParameter("practId");
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ListAssessmentDAO listAssessmentDAO = new JDBCListAssessmentFormDAO(connection);
			ArrayList<Assessment> templateNameList = new ArrayList<Assessment>();
			//assessmentForm.setTemplateNameList(templateNameList);
			
			templateNameList = listAssessmentDAO.getTemplateList(clientId,practId,conditionId);			
			
			StringBuffer dropDownAjax = new StringBuffer();
			dropDownAjax.append("<select id='templateName1' name = 'templateName' class='form-control showToolTip chosen' data-toggle='tooltip'>");
			dropDownAjax.append("<option value = '0'>Select Template </option>");
				if(templateNameList.size()!=0){
					for(Assessment assessment : templateNameList){
						dropDownAjax.append("<option value = '"+assessment.getTemplateId()+"'>"+assessment.getTemplateName()+"</option>");
					}
					
				}
			dropDownAjax.append("</select>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+dropDownAjax.toString()+""); 
			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return null;
	}
	
	public String redirect() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		String columnStr ="";
		try{
			connection = Connection_provider.getconnection();
			ListAssessmentDAO listAssessmentDAO = new JDBCListAssessmentFormDAO(connection);
			
			/*String practitionerID1 = request.getParameter("practitionerId");
			int practitionerID = Integer.parseInt(practitionerID1);*/
			String clientId1 = request.getParameter("clientId");
			//int apmtId = Integer.parseInt(request.getParameter("appointId"));	
			
			if(session.getAttribute("sessionselectedclientid")!=null && clientId1==null){
				clientId1 = (String)session.getAttribute("sessionselectedclientid");
				ClientDAO clientDAO = new JDBCClientDAO(connection);
			}
			
			String clientName = listAssessmentDAO.getClientFullName(Integer.parseInt(clientId1));
			assessmentForm.setClientFullName(clientName);
			
			String id = listAssessmentDAO.getAssessmentClientId(clientId1);
			
			if(id.equals("")){
				
				return "noassesmentformmsg";
			}
			
			assessmentForm.setId(Integer.parseInt(id));
			
			String tempId = listAssessmentDAO.getTemplateId(id);
			
			setAssesmentForm(id, tempId, connection);
			
			//ArrayList<Assessment> columnList = listAssessmentDAO.getColumnList(tempId);
			/*ArrayList<Assessment> columnList =  listAssessmentDAO.getColumnFieldList(tempId);
			for(Assessment a : columnList){
				columnStr = a.getColumnstr();
			}
			ArrayList<String> assessmentFormsList = listAssessmentDAO.getAssessmentFormClientDetails(id,columnStr,tempId);
				
				String imageData = listAssessmentDAO.getImageDetails(id);
				session.setAttribute("imageData", imageData);
				
				ArrayList<Assessment> fieldNameList = new ArrayList<Assessment>();
				fieldNameList = listAssessmentDAO.getFieldNameListById(tempId,id);
				
				int clientId = Integer.parseInt(clientId1);
				String clientName = listAssessmentDAO.getClientFullName(clientId);
				assessmentForm.setClientFullName(clientName);
				
				assessmentForm.setClientId(Integer.toString(clientId));
				assessmentForm.setClientName(clientName);
				
				//assessmentForm.setFieldNameList(fieldNameList);
				session.setAttribute("fieldNameList", fieldNameList);
				session.setAttribute("assessmentFormsList", assessmentFormsList);
				session.setAttribute("columnStr", columnStr);

				ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
				Clinic locationdirection = new Clinic();
		     	//locationdirection = clinicDAO.getLocationDetails(Integer.parseInt(locationID));
		     	
				AllTemplateAction allTemplateAction = new AllTemplateAction();
				Clinic clinic = new Clinic();
		     	int clinicID = loginInfo.getId();
		     
		     	clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
		     	String headerData = allTemplateAction.header(clinic.getClinicName(), clinic.getClinicOwner(), clinic.getOwner_qualification(), locationdirection.getAddress(), 
						clinic.getEmail(),locationdirection.getContactNo(), clinic.getWebsiteUrl(),loginInfo.getClinicid(),connection);
		     	session.setAttribute("headerData", headerData);
		     	
		     	
		     	//set value
		     	ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
				 ArrayList<Client>clientList = consultationNoteDAO.getClientList(practitionerID);
				 assessmentForm.setClientList(clientList);
				
				 
				 ArrayList<Client>conditionList = consultationNoteDAO.getConditionList(clientId);
				 assessmentForm.setConditionList(conditionList);
				 
				 	assessmentForm.setClientName(Integer.toString(clientId));
					assessmentForm.setDiaryUser(Integer.toString(practitionerID));
					
					int conditionid = consultationNoteDAO.getconditionID(apmtId);
					assessmentForm.setCondition(Integer.toString(conditionid));
				
					
					
					//get clinic address header
			    	
					clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
					AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
					
					ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
					//String address = accountsDAO.getLocationAddress(locationid,loginInfo.getId());
					
					assessmentForm.setClinicName(clinic.getClinicName());
					assessmentForm.setClinicOwner(clinic.getClinicOwner());
					assessmentForm.setOwner_qualification(clinic.getOwner_qualification());
					assessmentForm.setLandLine(clinic.getLandLine());
					assessmentForm.setWebsiteUrl(clinic.getWebsiteUrl());
					assessmentForm.setClinicemail(clinic.getEmail());
					assessmentForm.setLocationAdressList(locationAdressList);*/
					
		     	
			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return "editAssementPageOfClient";
	}
	
	
	public AssessmentForm getModel() {
		// TODO Auto-generated method stub
		return assessmentForm;
	}

	public void prepare() throws Exception {
		Connection connection = null;
		try{
		connection = Connection_provider.getconnection();
		AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
		ArrayList<Assessment> templateNameList = new ArrayList<Assessment>();
		templateNameList = assessmentFormDAO.getTemplateList();
		assessmentForm.setTemplateNameList(templateNameList);
		
		ImportImageForAssessmentDAO imageForAssessmentDAO = new JDBCImportImageAssessmentDAO(connection);
		ArrayList<Assessment> importImageList = imageForAssessmentDAO.getImportImageList(); 
		assessmentForm.setImportImageList(importImageList);
		
		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);			
		ArrayList<DiaryManagement>userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
		assessmentForm.setUserList(userList);
		
		 ArrayList<Client>clientList = new ArrayList<Client>();
		 ArrayList<Client>conditionList = new ArrayList<Client>();
		 assessmentForm.setClientList(clientList);
		 assessmentForm.setConditionList(conditionList);
		 
		 ArrayList<Master>customdataList = assessmentFormDAO.getCustomDataList();
		 assessmentForm.setCustomdataList(customdataList);
		 session.setAttribute("customdataList", customdataList);
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
	}
}