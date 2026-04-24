package com.apm.AssesmentForms.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.Appointment.web.form.AppointmentTypeForm;
import com.apm.AssesmentForms.eu.bi.AssessmentFormDAO;
import com.apm.AssesmentForms.eu.bi.ImportImageForAssessmentDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCAssessmentFormDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCImportImageAssessmentDAO;
import com.apm.AssesmentForms.eu.entity.Assessment;
import com.apm.AssesmentForms.web.form.AssessmentForm;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Emr.eu.bi.ConsultationNoteDAO;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCConsultationNoteDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;

import com.apm.Mrd.eu.bi.MrdDAO;
import com.apm.Mrd.eu.blogic.JDBCMrdDAO;
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
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class AssessmentFormsAction extends BaseAction implements Preparable, ModelDriven<AssessmentForm>{
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
	public String execute() throws Exception {
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			
			//AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
			
			
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
			
		}
		finally{
			connection.close();
		}
		
		
		return "";
		
		
}
	
	
public String showtemplate(){
	
	if(!verifyLogin(request)){
		return "login";
	}
	
	String appointmentid = request.getParameter("appointId");
	if(appointmentid!=null){
		if(!appointmentid.equals("0")){
		session.removeAttribute("sessionadmissionid");
		}
	}
	
	if(appointmentid==null){
		session.removeAttribute("sessionadmissionid");
	}
	
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
		ArrayList<Assessment> templateNameList = new ArrayList<Assessment>();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		
		templateNameList = assessmentFormDAO.getTemplateList();
		
		if(!assessmentForm.getClientId().equals("")){
			
			if(assessmentForm.getClientId().equals("0")){
				String clientiid = (String)session.getAttribute("sessionselectedclientid");
				assessmentForm.setClientId(clientiid);
			}
			
			
			Client client = clientDAO.getSelectedSessionClientDetails(assessmentForm.getClientId());
			ArrayList<Assessment>clientAssesmentformList = assessmentFormDAO.getClientAssesementFormList(assessmentForm.getClientId(),client.getClientName());
			//clientAssesmentformList.addAll(templateNameList);
			//assessmentForm.setTemplateNameList(clientAssesmentformList);
			assessmentForm.setClientAssesmentformList(clientAssesmentformList);
			assessmentForm.setTemplateNameList(templateNameList);
			assessmentForm.setClient(client.getClientName());
			
			session.setAttribute("sessionselectedclientid", assessmentForm.getClientId());
		}else{
			assessmentForm.setTemplateNameList(templateNameList);
		}
		
		
		
		//branchForm.setTemplateNameList(templateNameList);
		//session.setAttribute("templateNameList", templateNameList);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
	
	
	
	return "showtemplate";
}
	
public String input(){
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
		/*int rowcount = assessmentFormDAO.getRowCount();
		
		session.setAttribute("assemmntrowcount", rowcount);*/
		
	}catch (Exception e) {
		// TODO: handle exception
	}
	
	return "createNewAssessmentForm";
}

//int valueId = 0;

/*public String addPreview(){	
	if(!verifyLogin(request)){
		return "login";
	}
	
	
	System.out.println(assessmentForm.getImageData());
	
	Connection connection = null;
	int valueId = 0;
	String valueId1 = "";
	String lableValue1 = "id";
	String typeValue = "";
	String sizeValue = "";
	try{
		connection = Connection_provider.getconnection();
		
		String labelValueData[] = assessmentForm.getLableValueData().split("#");
		
		String typeValueData[] = assessmentForm.getTypeValueData().split("#");
		
		String sizeValueData[] = assessmentForm.getSizeValueData().split("#");
		
		String templateName = assessmentForm.getHiddenTemplateName();
		
		String Pic = assessmentForm.getImageData();
		
		AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
		 valueId = assessmentFormDAO.saveTemplate(templateName,Pic);
		
		 //assessmentFormDAO.saveUpdateTemplateField(valueId,lableValue1,lableValue1,templateName,typeValue,sizeValue);
		 
		for(int i=0;i<labelValueData.length;i++){
			String lableValue = labelValueData[i];
			String fieldValue = lableValue.replace(" ", "_");
			String temp1 = fieldValue.replace("(", "_");
			String temp2 = temp1.replace(")", "_");
			String temp3 = temp2.replace("-", "_");
			String temp4 = temp3.replace("/", "_");
			String temp5 = temp4.replace("?", "_");
			String temp6 = temp5.replace(",", "_");
			String temp7 = temp6.replace("&", "_");
			String temp8 = temp7.replace("+", "_");
			String temp9 = temp8.replace(".", "_");
			
			typeValue = typeValueData[i];
			
			sizeValue = sizeValueData[i];
			
			assessmentFormDAO.saveUpdateTemplateField(valueId,temp9,lableValue,templateName,typeValue,sizeValue);
		}
		String typeValue1 = "",sizeValue1 = "";
		
		 assessmentFormDAO.saveUpdateTemplateField(valueId,lableValue1,lableValue1,templateName,typeValue1,sizeValue1);

		
		String lableValue = request.getParameter("lableValue");
		String fieldValue = lableValue.replace(" ", "_");
		String textName = request.getParameter("textName");
		valueId1 = (String) session.getAttribute("valueId2");
		if(valueId1 == null){
			valueId = 0;
		}else{
		valueId = Integer.parseInt((valueId1) );
		}
		String templateName = request.getParameter("templateName");
		String Pic = request.getParameter("imageData");
		
	//	String templateName = assessmentForm.getTemplateName();
				
		//AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
		ArrayList<String> fieldTextList = new ArrayList<String>();
		fieldTextList = assessmentFormDAO.getFieldTextList();
		
		//valueId = assessmentFormDAO.saveTemplate(templateName);
		
		if(valueId == 0){
			valueId = assessmentFormDAO.saveTemplate(templateName,Pic);
			int result = assessmentFormDAO.saveUpdateTemplateField(valueId,fieldValue,lableValue,templateName);

		}
		else{
		int result1 = assessmentFormDAO.saveUpdateTemplateField(valueId,fieldValue,lableValue,templateName);
		}
		
		ArrayList<Assessment> templateNameList = new ArrayList<Assessment>();
		templateNameList = assessmentFormDAO.getTemplateList();
		assessmentForm.setTemplateNameList(templateNameList);
		session.setAttribute("templateNameList", templateNameList);
		String valueId2 = Integer.toString(valueId);
		session.setAttribute("valueId2", valueId2);
		session.setAttribute("assesmentTemplateName", assessmentForm.getHiddenTemplateName());
		
		assessmentForm.setTemplateId(valueId2);
		String imageData = assessmentFormDAO.getImageDetails(valueId2);
		session.setAttribute("imageData", imageData);
		
		ArrayList<Assessment> fieldNameList = new ArrayList<Assessment>();
		fieldNameList = assessmentFormDAO.getFieldNameListById(valueId2);
		session.setAttribute("fieldNameList", fieldNameList);
		
		assessmentForm.setMessage("Field Added Sucessfully!!");
		addActionMessage("Field Added Sucessfully!!");
				
	}catch(Exception e){
		
	}
	return "addTemplateDetails";
		
	
}*/


public String addPreview(){	
	if(!verifyLogin(request)){
		return "login";
	}
	
	
//	System.out.println(assessmentForm.getImageData());
	
	Connection connection = null;
	int valueId = 0;
	int id = 0;
	String lableValue1 = "id";
	String typeValue = "";
	String sizeValue = "";
	String repeatValue = "";
	try{
		connection = Connection_provider.getconnection();
		
		String labelValueData[] = assessmentForm.getLableValueData().split("#");
		
		String typeValueData[] = assessmentForm.getTypeValueData().split("#");
		
		String sizeValueData[] = assessmentForm.getSizeValueData().split("#");
		
		String repeatValueData[] = assessmentForm.getRepeatValueData().split("#");
		
		String templateName = assessmentForm.getHiddenTemplateName();		
		
		String Pic = assessmentForm.getImageData();
		
		String formtype = assessmentForm.getFormtype();
		String rowsno = assessmentForm.getRowsno();
		String hdrcolumn = assessmentForm.getHdrcolumn();
		
		
		AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
		
		boolean result = assessmentFormDAO.IsTemplateNameExist(templateName);
		
		if(assessmentForm.getTempaction().equals("edit")){
			valueId = assessmentFormDAO.updateTemplate(templateName,Pic,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),assessmentForm.isIncludeImage(),assessmentForm.getTemplateNote(),assessmentForm.getSpecialization(),assessmentForm.getLayout(),formtype,rowsno,hdrcolumn);
			int del = assessmentFormDAO.delteTemplateField(valueId);
		}
		else{		
		 valueId = assessmentFormDAO.saveTemplate(templateName,Pic,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),assessmentForm.isIncludeImage(),assessmentForm.getTemplateNote(),assessmentForm.getSpecialization(),assessmentForm.getLayout(),formtype,rowsno,hdrcolumn);
		}
		
		//save hdr column value
		String hdrtemp[] = hdrcolumn.split(",");
		for(int h=0;h<hdrtemp.length;h++){
			String dbcname =  DateTimeUtils.removeAllSpecialChar(hdrtemp[h]) ;
			dbcname = dbcname.replace(" ", "");
			int r = assessmentFormDAO.sveHdrColumnData(hdrtemp[h],valueId,dbcname);
		}
		
		if(assessmentForm.getFormtype().equals("2")){
			
			int crowsno = Integer.parseInt(assessmentForm.getRowsno());
			labelValueData = new String[crowsno];
			int t = 1;
			for(int i=0;i<crowsno;i++){
				labelValueData[i] = ""+t+"";
				t++;
			}
		}
		
		
		
		 //assessmentFormDAO.saveUpdateTemplateField(valueId,lableValue1,lableValue1,templateName,typeValue,sizeValue);
	//	if(result == false){ 
		for(int i=0;i<labelValueData.length;i++){
			String lableValue = labelValueData[i];
			
			/*String fieldValue = lableValue.replace(" ", "_");
			String temp1 = fieldValue.replace("(", "_");
			String temp2 = temp1.replace(")", "_");
			String temp3 = temp2.replace("-", "_");
			String temp4 = temp3.replace("/", "_");
			String temp5 = temp4.replace("?", "_");
			String temp6 = temp5.replace(",", "_");
			String temp7 = temp6.replace("&", "_");
			String temp8 = temp7.replace("+", "_");
			String temp9 = temp8.replace(".", "_");
			String temp10 = temp9.replace("'", "_");*/
			
			
			String temp10 = lableValue;
			if(!assessmentForm.getFormtype().equals("2")){
				 temp10 =  DateTimeUtils.removeAllSpecialChar(lableValue) ;
					temp10 = temp10.replace(" ", "");
					
					
					typeValue = typeValueData[i];
					
					sizeValue = sizeValueData[i];
					repeatValue = repeatValueData[i];
					if(sizeValue.equals("select")){
						sizeValue = "0";
					}
					
					if(!repeatValue.equals("0")){
						int addcoluimn = assessmentFormDAO.alterNewColumn(temp10);
					}
			}
			
			
			
				
				
				assessmentFormDAO.saveUpdateTemplateField(valueId,temp10,lableValue,templateName,typeValue,sizeValue,repeatValue);
			}
		//}
		
		
		ArrayList<Assessment> templateNameList = new ArrayList<Assessment>();
		templateNameList = assessmentFormDAO.getTemplateList();
		assessmentForm.setTemplateNameList(templateNameList);
		session.setAttribute("templateNameList", templateNameList);
		String valueId2 = Integer.toString(valueId);
		session.setAttribute("valueId2", valueId2);
		session.setAttribute("assesmentTemplateName", assessmentForm.getHiddenTemplateName());
		
		assessmentForm.setTemplateId(valueId2);
		String imageData = assessmentFormDAO.getImageDetails(valueId2);
		session.setAttribute("imageData", imageData);
		
		ArrayList<Assessment> fieldNameList = new ArrayList<Assessment>();
		fieldNameList = assessmentFormDAO.getFieldNameListById(valueId2);
		session.setAttribute("fieldNameList", fieldNameList);
		
		
	  	
     	//get clinic address header
		ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
		Clinic clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
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
		
		
		assessmentForm.setMessage("New template added successfully!! ");
		addActionMessage("New template added successfully!!");
		
		
				
	}catch(Exception e){
		e.printStackTrace();
	}
	//return "templateadded";
	
	return showtemplate();
		
	
}

	public String updateImageDetails(){
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		int valueId = 0;
		String valueId1 = "";
		try{
			connection = Connection_provider.getconnection();
			AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
			valueId1 = (String) session.getAttribute("valueId2");
			if(valueId1 == null){
				valueId = 0;
			}else{
			valueId = Integer.parseInt((valueId1) );
			}
			String templateName = request.getParameter("templateName");
			String imageData = request.getParameter("imageData");
			
			int result = assessmentFormDAO.insertImageData(valueId,imageData);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
			
		return null;
	}

	public String addTemplateDetails(){
		if(!verifyLogin(request)){
			return "login";
		}
		
		session.removeAttribute("sessionassement");
		session.removeAttribute("fieldNameList");
		
		String apmtid = assessmentForm.getBodyapmtid();
		if(apmtid==null){
			apmtid = "0";
		}
		
		session.setAttribute("pbodyapmtid", apmtid);
		
		String templateId = request.getParameter("templateId");
		
	
		AllTemplateAction allTemplateAction = new AllTemplateAction();
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
			
			String imageData = assessmentFormDAO.getImageDetails(templateId);
			session.setAttribute("imageData", imageData);
			
			ArrayList<Assessment> fieldNameList = new ArrayList<Assessment>();
			fieldNameList = assessmentFormDAO.getFieldNameListById(templateId);
			//assessmentForm.setFieldNameList(fieldNameList);
			session.setAttribute("fieldNameList", fieldNameList);
			
			//check repeat
			boolean isRepeat = assessmentFormDAO.checkIsRepeatTemplate(templateId);
			session.setAttribute("isRepeat", isRepeat);
			assessmentForm.setRepeat(isRepeat);
			
			//get template details
			Template template = assessmentFormDAO.getTemplateDetails(templateId);
			session.setAttribute("assmnttemplatedetails", template);
			
			
			String templateName = assessmentFormDAO.getTemplateName(templateId);
			session.setAttribute("assesmentTemplateName", templateName);
			assessmentForm.setTemplateName(templateName);
			
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			Clinic locationdirection = new Clinic();
	     	//locationdirection = clinicDAO.getLocationDetails(Integer.parseInt(locationID));
	     	
			Clinic clinic = new Clinic();
	     	int clinicID = loginInfo.getClinicid();
	     
	   /*  	clinic = clinicDAO.getCliniclistDetails(loginInfo.getId());
	     	String headerData = allTemplateAction.header(clinic.getClinicName(), clinic.getClinicOwner(), clinic.getOwner_qualification(), locationdirection.getAddress(), 
					clinic.getEmail(),locationdirection.getContactNo(), clinic.getWebsiteUrl());
	     	session.setAttribute("headerData", headerData);*/
	     	
	     	
	     	//clinic header address
			clinic = clinicDAO.getCliniclistDetails(clinicID);
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
	     	
	    	//set selected clientid from session
			if(session.getAttribute("sessionselectedclientid")!=null){
				String clientId = (String)session.getAttribute("sessionselectedclientid");
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				
				NotAvailableSlot notAvailableSlot = clientDAO.getLastAppointmentdetails(clientId);
				String practionerId = Integer.toString(notAvailableSlot.getDiaryUserId());
				
				String condition = notAvailableSlot.getCondition();
				
				String oldpractid = practionerId;
				assessmentForm.setOldpractid(oldpractid);
				//check if doctor placed with machine
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(practionerId));
				
				if(userProfile.getJobgroup()==null){
					userProfile.setJobgroup("");
				}
				if(userProfile.getJobgroup().equals("4")){
					practionerId = userProfile.getDoctor();
				}
				
				if(practionerId==null){
					practionerId="0";
				}
				
				//get ipd details
				int bedid = assessmentFormDAO.getIpdBedno(clientId);
				if(bedid!=0){
					IpdDAO ipdDAO = new JDBCIpdDAO(connection);
					BedDao bedDao=new JDBCBedDao(connection);
					
					String admissionid = assessmentFormDAO.getAdmissionid(clientId);
					//String sessionadmissionid = (String)session.getAttribute("sessionadmissionid");
					
					Bed bed = bedDao.getEditIpdData(admissionid);
					practionerId = bed.getPractitionerid();
					userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(practionerId));
					condition = userProfile.getDiciplineName();
					notAvailableSlot.setId(0);
					
				}
				
			
				ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
				 ArrayList<Client>clientList = consultationNoteDAO.getClientListEmr(Integer.parseInt(practionerId),clientId,oldpractid,Integer.toString(notAvailableSlot.getId()));
				 assessmentForm.setClientList(clientList);
				
				 
				 
				 ArrayList<Client>conditionList = consultationNoteDAO.getConditionList(Integer.parseInt(clientId));
				 assessmentForm.setConditionList(conditionList);
				 
				 	assessmentForm.setClientName(clientId);
					assessmentForm.setDiaryUser(practionerId);
					assessmentForm.setCondition(condition);
					
					session.setAttribute("combineformclientid", clientId);
					session.setAttribute("combineformdoctorid", practionerId);
					session.setAttribute("combineformconditionid", condition);
					
					
					
				}
			
			String formtype = request.getParameter("formtype");
			session.setAttribute("assmntformtype", formtype);
			if(!formtype.equals("0")){
				ArrayList<Master>leftNameList = assessmentFormDAO.getLeftNameList(templateId);
				ArrayList<Master>topNameList = assessmentFormDAO.getTopNameList(templateId);
				
				session.setAttribute("leftNameList", leftNameList);
				session.setAttribute("topNameList", topNameList);
				session.setAttribute("assementtemplateId", templateId);
				
				
				
				return "addcombineformdetails";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "addTemplateDetails";
	}
	
	
	public String savecombineimg(){
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
			
			String templateId = assessmentForm.getTemplateId();
			ArrayList<Master>leftNameList = assessmentFormDAO.getLeftNameList(templateId);
			ArrayList<Master>topNameList = assessmentFormDAO.getTopNameList(templateId);
			
			session.setAttribute("leftNameList", leftNameList);
			session.setAttribute("topNameList", topNameList);
			session.setAttribute("assementtemplateId", templateId);
			
			
		
		
		
		session.setAttribute("imageData", assessmentForm.getImageData());
		
		
		
		ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
		Clinic locationdirection = new Clinic();
     	//locationdirection = clinicDAO.getLocationDetails(Integer.parseInt(locationID));
     	
		Clinic clinic = new Clinic();
     	int clinicID = loginInfo.getClinicid();
     
   /*  	clinic = clinicDAO.getCliniclistDetails(loginInfo.getId());
     	String headerData = allTemplateAction.header(clinic.getClinicName(), clinic.getClinicOwner(), clinic.getOwner_qualification(), locationdirection.getAddress(), 
				clinic.getEmail(),locationdirection.getContactNo(), clinic.getWebsiteUrl());
     	session.setAttribute("headerData", headerData);*/
     	
     	
     	//clinic header address
		clinic = clinicDAO.getCliniclistDetails(clinicID);
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
		
		
		String combineformclientid = (String)session.getAttribute("combineformclientid");
		String combineformdoctorid = (String)session.getAttribute("combineformdoctorid");
		String combineformconditionid = (String)session.getAttribute("combineformconditionid");
		 String assementtemplateId = (String)session.getAttribute("assementtemplateId");
		 

		 	assessmentForm.setClientName(combineformclientid);
			assessmentForm.setDiaryUser(combineformdoctorid);
			assessmentForm.setCondition(combineformconditionid);
			assessmentForm.setTemplateId(assementtemplateId);
			
			
			String oldpractid = combineformdoctorid;
			//check if doctor placed with machine
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(combineformdoctorid));
			
			if(userProfile.getJobgroup().equals("4")){
				combineformdoctorid = userProfile.getDoctor();
			}
			
			
			ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
			ArrayList<Client>conditionList = consultationNoteDAO.getConditionList(Integer.parseInt(combineformclientid));
			assessmentForm.setConditionList(conditionList);
			
			 ArrayList<Client>clientList = consultationNoteDAO.getClientListEmr(Integer.parseInt(combineformdoctorid),combineformclientid,oldpractid,"0");
			assessmentForm.setClientList(clientList);
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	
		
		return "addcombineformdetails";
	}
	public String saveImageTemplate(){
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			String templateId = request.getParameter("templateId");
			AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
			//int result = assessmentFormDAO.updateImageTemplate(templateId,assessmentForm.getImageData());
			
		//	String imageData = assessmentFormDAO.getImageDetails(templateId);
			session.setAttribute("imageData", assessmentForm.getImageData());
			
			ArrayList<Assessment> fieldNameList = new ArrayList<Assessment>();
			fieldNameList = assessmentFormDAO.getFieldNameListById(templateId);
			//assessmentForm.setFieldNameList(fieldNameList);
			session.setAttribute("fieldNameList", fieldNameList);
			
			String templateName = assessmentFormDAO.getTemplateName(templateId);
			session.setAttribute("assesmentTemplateName", templateName);
			
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			Clinic locationdirection = new Clinic();
	     	//locationdirection = clinicDAO.getLocationDetails(Integer.parseInt(locationID));
	     	
			Clinic clinic = new Clinic();
	     	int clinicID = loginInfo.getId();
	     
	     	clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
	     	AllTemplateAction allTemplateAction = new AllTemplateAction();
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
			
			String clientname = assessmentForm.getClientName();
			ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
			//ArrayList<Client>clientList = consultationNoteDAO.getClientList(Integer.parseInt(assessmentForm.getDiaryUser()));
			ArrayList<Client>clientList = consultationNoteDAO.getpbodyclient(clientname);
			assessmentForm.setClientList(clientList);
			assessmentForm.setClientName(clientname);
		
			ArrayList<Client>conditionList = consultationNoteDAO.getConditionList(Integer.parseInt(assessmentForm.getClientName()));
			assessmentForm.setConditionList(conditionList);
			//assessmentForm.setCondition(ass);
	     	
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "addTemplateDetails";
	}
	
	
	public String combinesave(){
		
		if(!verifyLogin(request)){
			return "login";
		}
		int fieldId = 0;
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			String templateId = (String)session.getAttribute("assementtemplateId");
			String diaryUserId = request.getParameter("diaryUser");
			String clientId = request.getParameter("clientName");
			String conditionId = request.getParameter("condition");
			
			AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
			String sessionadmissionid = (String)session.getAttribute("sessionadmissionid");
			
			ArrayList<Master>leftNameList = (ArrayList<Master>)session.getAttribute("leftNameList");
			ArrayList<Master>topNameList = (ArrayList<Master>)session.getAttribute("topNameList");
			
			int i = 1;
			for(Master student : leftNameList){
				for(Master subject : topNameList){
					
					
					String reqid = "n0min" + i + "-" + subject.getId() + "-" + student.getId(); 
					String textName = request.getParameter(reqid);
					
					String subjectname = subject.getDisplayname();
					boolean checkcolumnexist = assessmentFormDAO.checkColumnExist(subjectname);
					
					if(!checkcolumnexist){
						int r = assessmentFormDAO.createColumnName(subjectname);
					}
					
					boolean checktermexist = assessmentFormDAO.checkTermExist(student.getId(),templateId,clientId,diaryUserId,conditionId,student.getId());
					
					int saveid = 0;
					if(!checktermexist){
						saveid = assessmentFormDAO.saveIdOfCombineTemplate(templateId,clientId,assessmentForm.getImageData(),diaryUserId,conditionId,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),sessionadmissionid,assessmentForm.getOldpractid(),null,student.getId());
						int result = assessmentFormDAO.saveUpdateCombineTemplateDetails(saveid,subjectname,textName,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
					}else{
						saveid = assessmentFormDAO.getCombineDataid(student.getId(),templateId,clientId,diaryUserId,conditionId,student.getId());
						int result = assessmentFormDAO.saveUpdateCombineTemplateDetails(saveid,subjectname,textName,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
					}
					
					i++;
				}
			}
			
			session.setAttribute("sessionselectedclientid", clientId);
			session.setAttribute("diaryUserId", diaryUserId);
			session.setAttribute("conditionId", conditionId);
			session.setAttribute("templateId", templateId);
			
			
//			System.out.println(conditionId);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return "assesmentsavedsuccess";
	}
	
	
	
	public String opdbody(){
		
		//pbodytmplate+'&formtype=0&apmtid='+editAppointId+'&patientId='+patientId+'&diaryuserId='+diaryuserId+'&editcondition_id
		String pbodytmplate = request.getParameter("pbodytmplate");
		String apmtid = request.getParameter("apmtid");
		String patientId = request.getParameter("patientId");
		String diaryuserId = request.getParameter("diaryuserId");
		String editcondition_id = request.getParameter("editcondition_id");
		
		session.setAttribute("sessionselectedclientid", patientId);
		session.setAttribute("bodyapmtid", apmtid);
		session.setAttribute("bodytemplate", pbodytmplate);
		
		assessmentForm.setBodytemplate(pbodytmplate);
assessmentForm.setBodyapmtid(apmtid);
		
		
		return addTemplateDetails();
	}
	
	public String saveTemplate(){
		if(!verifyLogin(request)){
			return "login";
		}
		int fieldId = 0;
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			String templateId = request.getParameter("templateId");
			String diaryUserId = request.getParameter("diaryUser");
			String clientId = request.getParameter("clientName");
			String conditionId = request.getParameter("condition");
			
	
			
			
			
			ArrayList<Assessment> fieldNameList = (ArrayList<Assessment>) session.getAttribute("fieldNameList");
			int r = 0;
			for(Assessment assessment : fieldNameList){
				
				String lableName = assessment.getFiledname();
				
				/*String fieldValue = lableName.replace(" ", "_");
				String temp1 = fieldValue.replace("(", "_");
				String temp2 = temp1.replace(")", "_");
				String temp3 = temp2.replace("-", "_");
				String temp4 = temp3.replace("/", "_");
				String temp5 = temp4.replace("?", "_");
				String temp6 = temp5.replace(",", "_");
				String temp7 = temp6.replace("&", "_");
				String temp8 = temp7.replace("+", "_");
				String temp9 = temp8.replace(".", "_");
				String temp10 = temp9.replace("'", "_");*/
				
				String temp10 = DateTimeUtils.removeAllSpecialChar(lableName);
				temp10 = temp10.replace(" ", "");
				
				String pbodyapmtid = (String)session.getAttribute("pbodyapmtid");
				
				
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

				AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
				String sessionadmissionid = (String)session.getAttribute("sessionadmissionid");
				
				
				if(!assessmentForm.isRepeat()){
					if(fieldId == 0){
						fieldId = assessmentFormDAO.saveIdOfTemplate(templateId,clientId,assessmentForm.getImageData(),diaryUserId,conditionId,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),sessionadmissionid,assessmentForm.getOldpractid(),null,pbodyapmtid);
						int result = assessmentFormDAO.saveUpdateTemplateDetails(fieldId,temp10,lableName,textName,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));

					}
					else{
					
						int result1 = assessmentFormDAO.saveUpdateTemplateDetails(fieldId,temp10,lableName,textName,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
					
					}
				}else{
					
					if(fieldId==0){
						fieldId = assessmentFormDAO.saveIdOfTemplate(templateId,clientId,assessmentForm.getImageData(),diaryUserId,conditionId,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),sessionadmissionid,assessmentForm.getOldpractid(),null,pbodyapmtid);
					}
					
					for(int i=0;i<Integer.parseInt(assessment.getRptvalue());i++){
						String strtxt  = request.getParameter(assessment.getFiledname()+i);
						int save = assessmentFormDAO.saveRepeatFormData(strtxt,temp10,templateId,clientId,diaryUserId,conditionId,fieldId);
//						System.out.println(strtxt);
					}
					
					
				}
				r++;
				
			}
			
		/*	String lableName = request.getParameter("lableName");
			String fieldValue = lableName.replace(" ", "_");
			String textName = request.getParameter("textName");
			int fieldId = Integer.parseInt(request.getParameter("fieldId"));
			String templateId = request.getParameter("templateId");
			
			AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
			
			if(fieldId == 0){
				//fieldId = assessmentFormDAO.saveTemplate(templateName);
				int result = assessmentFormDAO.saveUpdateTemplateDetails(fieldId,fieldValue,lableName,textName,templateId);

			}
			else{
			int result1 = assessmentFormDAO.saveUpdateTemplateDetails(fieldId,fieldValue,lableName,textName,templateId);
			}
		
			*/
			
			session.setAttribute("sessionselectedclientid", clientId);
			session.setAttribute("diaryUserId", diaryUserId);
			session.setAttribute("conditionId", conditionId);
			session.setAttribute("templateId", templateId);
			
			
			assessmentForm.setMessage("Field Added Sucessfully!!");
			addActionMessage("Field Added Sucessfully!!");
					
		}catch(Exception e){
			e.printStackTrace();
		}
		/*try{
		   
		    String img64 = assessmentForm.getImageData();
            img64 = img64.substring("data:image/png;base64,".length());   
        byte[] decodedBytes = DatatypeConverter.parseBase64Binary(img64 );
        BufferedImage bfi = ImageIO.read(new ByteArrayInputStream(decodedBytes));    
        File outputfile = new File(request.getRealPath("/liveData/document/"+fieldId+".png"));
        ImageIO.write(bfi , "png", outputfile);
        bfi.flush();

		    }catch(Exception e){System.out.println("In canvas"+e.getMessage());}
		*/
		
		return "assesmentsavedsuccess";
	}
	
	public String checkTemplateNameExist(){
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			String templateName = request.getParameter("tempname");
			AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
			boolean result = assessmentFormDAO.IsTemplateNameExist(templateName);
			
			if(result){
				response.getWriter().write("false");
			}else{	
				response.getWriter().write("true");
			}
			
			
		}catch(Exception e){
			
		}
		return null;
	}
	
	public String modify(){
		if(!verifyLogin(request)){
			return "login";
		}
		
		return "modifyNewAssessmentForm";
	}
	
	public String availableSelectedList(){
		if(!verifyLogin(request)){
			return "login";
		}
		int counts = 1;
		int size = 0;
		StringBuffer arrayString = new StringBuffer();
		StringBuffer sizeString = new StringBuffer();
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			String tempListNameId = request.getParameter("tempListNameId");
			AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
			
			ArrayList<Assessment> availableList = new ArrayList<Assessment>();
			availableList = assessmentFormDAO.getAvailableFieldList(tempListNameId);
			//assessmentForm.setAvailableList(availableList);
			//assessmentForm.setFieldList(availableList);
			
			String listOption1 = "";
			String list1 = "";
			StringBuffer str2 = new StringBuffer();
			for(Assessment assessment2 : availableList){
				listOption1 += "<option value="+assessment2.getId()+">"+assessment2.getFiledname()+"</option>";
			}			
			list1 = "<select size='20' multiple='multiple' id='list1' title='Select Fields' class='form-control showToolTip'>"+listOption1+"</select>";
			str2.append(list1);
			
			ArrayList<Assessment> selectedList = new ArrayList<Assessment>();
			selectedList = assessmentFormDAO.getSelectedFieldList(tempListNameId);
			//assessmentForm.setSelectedList(selectedList);
			String listOption = "";
			String list = "";
			StringBuffer str1 = new StringBuffer();
			for(Assessment assessment1 : selectedList){
				listOption += "<option value="+assessment1.getId()+">"+assessment1.getFiledname()+"</option>";
			}			
			list = "<select size='20' multiple='multiple' id='list2' title='Select Fields' class='form-control showToolTip'>"+listOption+"</select>";
			str1.append(list);
			
			String options = "";
			String rptOption = "";
			StringBuffer str = new StringBuffer();
		/*	for(Assessment assessment2 : selectedList){
				String sizeValue = assessment2.getSizeValue();
			    options = "<option value=\"select\">Select Size</option><option selected='selected' value='"+sizeValue+"'>"+sizeValue+"</option>";
	         
			for(int j=10;j<=500;j=j+10) {
	            options +="<option value=\""+j+"\">"+j+"</option>";
			}
	          }*/
			//str.append("<thead><tr><th>Feild Name</th><th>Type</th><th>Size</th></tr></thead><tbody>");
			for(Assessment assessment : selectedList){
				
				String sizeValue = assessment.getSizeValue();
			    options = "<option value=\"select\">Select Size</option><option selected='selected' value='"+sizeValue+"'>"+sizeValue+"</option>";
	         
			    for(int j=10;j<=500;j=j+10) {
			    	options +="<option value=\""+j+"\">"+j+"</option>";
			    }
			    
			    
			    //repeat drop down
			    String rptValue = assessment.getRptvalue();
			    rptOption = "<option value'0'>Select Repeat</option><option selected='selected' value='"+rptValue+"'>"+rptValue+"</option>";
	         
			    for(int j=0;j<=10;j++) {
			    	rptOption +="<option value='"+j+"'>"+j+"</option>";
			    }
				
				str.append("<tr>");
				str.append("<td>"+assessment.getFiledname()+"</td>");
				int typevalue = Integer.parseInt(assessment.getType());
				if(typevalue == 1){
					str.append("<td><select  class='form-control showToolTip type'  id='type' name = 'assessmentform[" + counts + "].type'><option value='"+assessment.getTypeValue()+"'>"+assessment.getTypeValue()+"</option></select></td>");
					str.append("<td><input type='hidden' class='1-100 form-control showToolTip size' id='size" + size + "' name = 'assessmentform[" + counts + "].size' /></td>");
					str.append("<td><input type='hidden' class='1-100 form-control showToolTip size' id='rptopt" + size + "' name = 'assessmentform[" + counts + "].size' /></td>");
				}
				else{
				if(typevalue == 2){
					str.append("<td><select  class='form-control showToolTip type'  id='type"+counts+"' name = 'assessmentform[" + counts + "].type'><option value='"+assessment.getTypeValue()+"'>"+assessment.getTypeValue()+"</option><option value='Multi Line Text'>Multi Line Text</option><option value='Y/N Dropdown'>Y/N Dropdown</option><option value='Custom Dropdown'>Custom Dropdown</option></select></td>");
					str.append("<td><select class='1-100 form-control showToolTip size' id='size" + size + "' name = 'assessmentform[" + counts + "].size'>"+options+"</select></td>");				
					str.append("<td><select class='1-100 form-control showToolTip size' id='rptopt" + size + "' name = 'assessmentform[" + counts + "].size'>"+rptOption+"</select></td></tr>");
					}
				else if(typevalue == 3){
					str.append("<td><select  class='form-control showToolTip type'  id='type"+counts+"'  name = 'assessmentform[" + counts + "].type'><option value='"+assessment.getTypeValue()+"'>"+assessment.getTypeValue()+"</option><option value='Single Line Text'>Single Line Text</option><option value='Y/N Dropdown'>Y/N Dropdown</option><option value='Custom Dropdown'>Custom Dropdown</option></select></td>");
					str.append("<td><select class='1-100 form-control showToolTip size' id='size" + size + "' name = 'assessmentform[" + counts + "].size'>"+options+"</select></td>");					
					str.append("<td><select class='1-100 form-control showToolTip size' id='rptopt" + size + "' name = 'assessmentform[" + counts + "].size'>"+rptOption+"</select></td></tr>");
					}
				else if(typevalue == 5){
					str.append("<td><select  class='form-control showToolTip type'  id='type"+counts+"' name = 'assessmentform[" + counts + "].type'><option value='"+assessment.getTypeValue()+"'>"+assessment.getTypeValue()+"</option><option value='Single Line Text'>Single Line Text</option><option value='Multi Line Text'>Multi Line Text</option><option value='Custom Dropdown'>Custom Dropdown</option></select></td>");
					str.append("<td><select class='1-100 form-control showToolTip size' id='size" + size + "' name = 'assessmentform[" + counts + "].size'>"+options+"</select></td>");					
					str.append("<td><select class='1-100 form-control showToolTip size' id='rptopt" + size + "' name = 'assessmentform[" + counts + "].size'>"+rptOption+"</select></td></tr>");
					}
				else if(typevalue == 6){
					str.append("<td><select  class='form-control showToolTip type'  id='type"+counts+"' name = 'assessmentform[" + counts + "].type'><option value='"+assessment.getTypeValue()+"'>"+assessment.getTypeValue()+"</option><option value='Single Line Text'>Single Line Text</option><option value='Multi Line Text'>Multi Line Text</option><option value='Y/N Dropdown'>Y/N Dropdown</option></select></td>");
					str.append("<td><select class='1-100 form-control showToolTip size' id='size" + size + "' name = 'assessmentform[" + counts + "].size'>"+options+"</select></td>");
					str.append("<td><select class='1-100 form-control showToolTip size' id='rptopt" + size + "' name = 'assessmentform[" + counts + "].size'>"+rptOption+"</select></td></tr>");
					}
				}
				size++;
				counts++;
				
				arrayString.append(assessment.getTypeValue() + ",");
				sizeString.append(sizeValue + ",");
			}
			StringBuffer str3 = new StringBuffer();
			str3.append("<tr><th>Feild Name</th><th>Type</th><th>Size</th><th>Repeat</th></tr>");
			
			String imageData = assessmentFormDAO.getImageDetails(tempListNameId);
			session.setAttribute("imageData", imageData);
			
		
				if(arrayString.length()>0){
					String strs = arrayString.substring(0,arrayString.length()-1);
					str.append("<input type='hidden' name='arrstring' id='arrstring' value='"+strs+"'>");
				}
				
				if(sizeString.length()>0){
					String zz = sizeString.substring(0,sizeString.length()-1);
					str.append("<input type='hidden' name='sizeStringdata' id='sizeStringdata' value='"+zz+"'>");
				}
			
			
			
				//get template details
				Template template = assessmentFormDAO.getTemplateDetails(tempListNameId);
				String filters = template.getLayout() + "~" + template.getSpecialization() + "~" + template.isIncludeImage() + "~" + template.getTemplateNote();
				//str.append("<input type='hidden' name='filterhdn' id='filterhdn' value='"+filters+"'> ");
				str3.append("<input type='hidden' name='filterhdn' id='filterhdn' value='"+filters+"'>");
			
			//str.append("</tbody>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");			
			response.getWriter().write(""+str.toString()+"#"+str1.toString()+"#"+str2.toString()+"#"+str3.toString()+"" + "#" + imageData); 

		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String setClients(){
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			 
			
			int practId = Integer.parseInt(request.getParameter("practId"));
			
			
			ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
			 ArrayList<Client>clientList = consultationNoteDAO.getClientList(practId);
			

			 StringBuffer dropDownAjax = new StringBuffer();
				dropDownAjax.append("<select id='clientnameAssessment' name = 'clientName' class='form-control showToolTip chosen' data-toggle='tooltip' onchange = 'setConditionAssessment(this.value)'>");
					dropDownAjax.append("<option value = '0'>Select Client </option>");
					if(clientList.size()!=0){
						for(Client client : clientList){
							dropDownAjax.append("<option value = '"+client.getId()+"'>"+client.getClientName()+"</option>");
						}
						
					}
				dropDownAjax.append("</select>");
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				
				response.getWriter().write(""+dropDownAjax.toString()+""); 
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
public String setClientCondition(){
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			 
			
			int clientId = Integer.parseInt(request.getParameter("clientId"));
			
			
			ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
			 ArrayList<Client>conditionList = consultationNoteDAO.getConditionList(clientId);
			

			 StringBuffer dropDownAjax = new StringBuffer();
				dropDownAjax.append("<select id='conditionAssessment' name = 'condition' class='form-control showToolTip chosen' data-toggle='tooltip'>");
					dropDownAjax.append("<option value = '0'>Select Speciality </option>");
					if(conditionList.size()!=0){
						for(Client client : conditionList){
							dropDownAjax.append("<option value = '"+client.getId()+"'>"+client.getTreatmentType()+"</option>");
						}
						
					}
				dropDownAjax.append("</select>");
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				
				response.getWriter().write(""+dropDownAjax.toString()+""); 
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
public String searchPatient() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	String searchClient = request.getParameter("searchText");
	Connection connection = null;
	ArrayList<Client> allPatientList = new ArrayList<Client>();
	try{
		connection = Connection_provider.getconnection();
		
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		allPatientList = clientDAO.getClient(searchClient,loginInfo.getId(),"");
		
		//clientForm.setAllPatientList(allPatientList);
		StringBuffer str = new StringBuffer();
			
		str.append("<table class='table table-hover table-condensed table-bordered table-striped ' > ");
		str.append("<thead>");
		str.append("<tr class='bg-info'>");
		str.append("<th>Id</th> ");

		str.append("<th>Name</th> ");
		str.append("<th>Old Id</th> ");
		str.append("<th>Mobile</th> ");
		str.append("<th>Email</th> ");
		str.append("<th>Post Code</th> ");
		str.append("<th>DOB</th> ");
		str.append("<th>LastModified</th> ");
		
		str.append("</tr>");
		str.append("</thead>");
		for(Client client1:allPatientList){
		str.append("<tr>");
		String name = client1.getTitle()+" "+client1.getFirstName()+" "+client1.getLastName(); 	
		String firstName= client1.getFirstName();
		str.append("<td>0000"+client1.getId()+"</td>");

		String whopay = "";
		if(client1.getWhopay().equals(Constants.PAY_BY_THIRD_PARTY)){
			whopay = "tp";
		}else{
			whopay = "Client";
		}

		String fullname = client1.getTitle() + "/" + client1.getFirstName() + "/" + client1.getLastName();

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


public String deltemplate(){
	
	if(!verifyLogin(request)){
		return "login";
	}
	
	Connection connection = null;
	
	try{
		connection = Connection_provider.getconnection();
		AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
	
		String selectedid = request.getParameter("selectedid");
		
		int del = assessmentFormDAO.deleteClientTemplateFieldData(selectedid);
		int res = assessmentFormDAO.deleteTemplate(selectedid);
		int res2 = assessmentFormDAO.deleteTemplateFieldByTempid(selectedid);
		
		assessmentForm.setMessage("Template deleted successfully!! ");
		addActionMessage("Template deleted successfully!!");
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
	
	return showtemplate();
}


public String set(){
	
	//var url = "setAssessmentForm?id="+id+"&value="+value+" ";
	try{
		String id = request.getParameter("kid");
		String value = request.getParameter("value");
		HashMap<String, String>map = new HashMap<String, String>();
		if(session.getAttribute("sessionassement")!=null){
			map = (HashMap<String, String>)session.getAttribute("sessionassement");
		}
		map.put(id, value);
		
		
		
		session.setAttribute("sessionassement", map);
		
	/*	HashMap<String, String>map1 = (HashMap<String, String>)session.getAttribute("sessionassement");
		
		for ( Map.Entry<String, String> entry : map1.entrySet()) {
		    String key = entry.getKey();
		    String val = entry.getValue();
		    
		    System.out.println(key);
		    System.out.println(val);
		    // do something with key and/or tab
		}*/
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return null;
}

public String delassesment(){
	if(!verifyLogin(request)){
		return "login";
	}
	
	Connection connection = null;
	
	try{
		
		connection = Connection_provider.getconnection();
		EmrDAO emrDAO = new JDBCEmrDAO(connection);
		String id = request.getParameter("selectedid");
	
		
		int del = emrDAO.deleteAssesmentForm(id);
		
	
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	 return showtemplate();
			
	
}


	public AssessmentForm getModel() {
		// TODO Auto-generated method stub
		return assessmentForm;
	}


	public void prepare() throws Exception {
		ArrayList<Assessment> fieldList = new ArrayList<Assessment>();
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
			fieldList = assessmentFormDAO.getFieldList();
			assessmentForm.setFieldList(fieldList);
			
			ArrayList<Assessment> templateNameList = new ArrayList<Assessment>();
			templateNameList = assessmentFormDAO.getTemplateList();
			assessmentForm.setTemplateNameList(templateNameList);
			
			ArrayList<Assessment> headingFieldList = new ArrayList<Assessment>();
			headingFieldList = assessmentFormDAO.getHeadingFieldList();
			assessmentForm.setHeadingFieldList(headingFieldList);			
			
			ArrayList<Assessment> templateFieldList = new ArrayList<Assessment>();
			templateFieldList = assessmentFormDAO.getTemplateFieldList();
			assessmentForm.setTemplateFieldList(templateFieldList);
			
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
			 
			/* ArrayList<Master>specializationList = assessmentFormDAO.getSpecializationList();
			assessmentForm.setSpecializationList(specializationList);*/
			
			
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master>disciplineList =  masterDAO.getDisciplineDataList();
			assessmentForm.setSpecializationList(disciplineList);
			
		}	catch (Exception e) {
				
			}
			finally{
				connection.close();
			}
			
	}
	
	public String addMrdTemplateDetails(){
		if(!verifyLogin(request)){
			return "login";
		}
		
		session.removeAttribute("sessionassement");
		session.removeAttribute("fieldNameList");
		
		String templateId = request.getParameter("templateId");
		String mrd_clientid  = request.getParameter("clientid");
		String mrd_ipdid = request.getParameter("ipdid");
		String mrdid = request.getParameter("mrdid");
		AllTemplateAction allTemplateAction = new AllTemplateAction();
		
		String clientId= request.getParameter("clientId");
	       
	       if(clientId!=null) {
	        session.setAttribute("sessionselectedclientid", clientId);
	       }
	       if(mrd_clientid!=null) {
		        session.setAttribute("sessionselectedclientid", mrd_clientid);
		   }
		
		Connection connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
			MrdDAO mrdDAO = new JDBCMrdDAO(connection);
			
			String imageData = assessmentFormDAO.getImageDetails(templateId);
			session.setAttribute("imageData", imageData);
			
			ArrayList<Assessment> fieldNameList = new ArrayList<Assessment>();
			fieldNameList = assessmentFormDAO.getFieldNameListById(templateId);
			
			//assessmentForm.setFieldNameList(fieldNameList);
			session.setAttribute("fieldNameList", fieldNameList);
			
			//check repeat
			boolean isRepeat = assessmentFormDAO.checkIsRepeatTemplate(templateId);
			session.setAttribute("isRepeat", isRepeat);
			assessmentForm.setRepeat(isRepeat);
			
			//get template details
			Template template = assessmentFormDAO.getTemplateDetails(templateId);
			session.setAttribute("assmnttemplatedetails", template);
			
			
			String templateName = assessmentFormDAO.getTemplateName(templateId);
			session.setAttribute("assesmentTemplateName", templateName);
			assessmentForm.setTemplateName(templateName);
			
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			Clinic locationdirection = new Clinic();
	     	//locationdirection = clinicDAO.getLocationDetails(Integer.parseInt(locationID));
	     	
			Clinic clinic = new Clinic();
	     	int clinicID = loginInfo.getClinicid();
	     	
	     	//clinic header address
			clinic = clinicDAO.getCliniclistDetails(clinicID);
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
	     	
			/*if(mrd_clientid!=null){
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				NotAvailableSlot notAvailableSlot = clientDAO.getLastAppointmentdetails(mrd_clientid);
				
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				BedDao bedDao=new JDBCBedDao(connection);
				
				//String sessionadmissionid = (String)session.getAttribute("sessionadmissionid");
				
				//Bed bed = bedDao.getEditIpdData(sessionadmissionid);
				Bed bed = bedDao.getEditIpdData(mrd_ipdid);
				String practionerId = bed.getPractitionerid();
				String condition = bed.getSpeciality();
				String oldpractid = practionerId;
				assessmentForm.setOldpractid(oldpractid);
				
				 ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
				 ArrayList<Client> clientList = consultationNoteDAO.getClientListEmr(Integer.parseInt(practionerId),mrd_clientid,oldpractid,Integer.toString(notAvailableSlot.getId()));
				 assessmentForm.setClientList(clientList);
				 
				 ArrayList<Client> conditionList = consultationNoteDAO.getConditionList(Integer.parseInt(mrd_clientid));
				 assessmentForm.setConditionList(conditionList);
				 assessmentForm.setIpdid(mrd_ipdid);
				 assessmentForm.setClientId(mrd_clientid);
				 assessmentForm.setClientName(mrd_clientid);
				 assessmentForm.setDiaryUser(practionerId);
				 assessmentForm.setCondition(condition);
				 assessmentForm.setMrd_c_id(mrd_id);
			}*/
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			//NotAvailableSlot notAvailableSlot = clientDAO.getLastAppointmentdetails(mrd_clientid);
			com.apm.Mrd.eu.entity.Mrd mrd = mrdDAO.getPatientForEdit(mrdid);
			String practionerId = mrd.getPractitionerid();
			String condition = mrd.getSpeciality();
			String oldpractid = practionerId;
			assessmentForm.setOldpractid(oldpractid);
			
			//ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
			//ArrayList<Client> clientList = consultationNoteDAO.getClientListEmr(Integer.parseInt(practionerId),mrd_clientid,oldpractid,Integer.toString(notAvailableSlot.getId()));
			
			ArrayList<Client> clientList = new ArrayList<Client>();
			Client client = clientDAO.getPatient(Integer.parseInt(mrd.getClientid()));
			String name1=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
			client.setClientName(name1);
			clientList.add(client);
			assessmentForm.setClientList(clientList);
			
			ArrayList<Client> conditionList = new ArrayList<Client>();
			//ListAssessmentDAO listAssessmentDAO = new JDBCListAssessmentFormDAO(connection);
			//String condition1 = listAssessmentDAO.getConditionName(condition);
			String conditionid = mrdDAO.getSpecialityId(condition);
			Client client2 = new Client();
			client2.setId(Integer.parseInt(conditionid));
			client2.setTreatmentType(condition);
			conditionList.add(client2);
			assessmentForm.setConditionList(conditionList);
			
			//ArrayList<Client> conditionList = consultationNoteDAO.getConditionList(Integer.parseInt(mrd_clientid));
			//assessmentForm.setConditionList(conditionList);
			
			
			assessmentForm.setIpdid(mrd_ipdid);
			assessmentForm.setClientId(mrd_clientid);
			assessmentForm.setClientName(mrd_clientid);
			assessmentForm.setDiaryUser(practionerId);
			assessmentForm.setCondition(condition);
			assessmentForm.setMrdid(mrdid);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "addMrdTemplateDetails";
	}
	
	
	public String saveMrdTemplate(){
		if(!verifyLogin(request)){
			return "login";
		}
		int fieldId = 0;
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			String templateId = request.getParameter("templateId");
			String diaryUserId = request.getParameter("diaryUser");
			String clientId = request.getParameter("clientName");
			String conditionId = request.getParameter("condition");
			String mrd_clientid = request.getParameter("clientId");
			String mrd_ipdid = request.getParameter("ipdid");
			String mrdid = request.getParameter("mrdid");
			ArrayList<Assessment> fieldNameList = (ArrayList<Assessment>) session.getAttribute("fieldNameList");
			
			int r = 0;
			MrdDAO mrdDAO = new JDBCMrdDAO(connection);
			/*int mrdid;
			if(mrd_c_id!=null){
				if(mrd_c_id.equals("0")){
					mrdid = mrdDAO.setMrdCheckList(mrd_clientid,mrd_ipdid,templateId);
				}else{
					mrdid=Integer.parseInt(mrd_c_id);
					int result = mrdDAO.updateMrdChecklist(templateId,""+mrdid);
				}
			}else{
				mrdid = mrdDAO.setMrdCheckList(mrd_clientid,mrd_ipdid,templateId);
			}*/
			//int mrdid = Integer.parseInt(mrd_c_id);
			int result = mrdDAO.updateMrdChecklist(templateId,mrdid);
			
			for(Assessment assessment : fieldNameList){
				String lableName = assessment.getFiledname();
				
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

				
				AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
				//String sessionadmissionid = (String)session.getAttribute("sessionadmissionid");
				String sessionadmissionid = mrd_ipdid;
				
				
				if(!assessmentForm.isRepeat()){
					if(fieldId == 0){
						fieldId = assessmentFormDAO.saveIdOfTemplate(templateId,clientId,assessmentForm.getImageData(),diaryUserId,conditionId,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),sessionadmissionid,assessmentForm.getOldpractid(),mrdid,"0");
						int result1 = assessmentFormDAO.saveUpdateTemplateDetails(fieldId,temp10,lableName,textName,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
					}
					else{
						int result1 = assessmentFormDAO.saveUpdateTemplateDetails(fieldId,temp10,lableName,textName,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
					}
				}else{
					
					if(fieldId==0){
						fieldId = assessmentFormDAO.saveIdOfTemplate(templateId,clientId,assessmentForm.getImageData(),diaryUserId,conditionId,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),sessionadmissionid,assessmentForm.getOldpractid(),mrdid,"0");
					}
					
					for(int i=0;i<Integer.parseInt(assessment.getRptvalue());i++){
						String strtxt  = request.getParameter(assessment.getFiledname()+i);
						int save = assessmentFormDAO.saveRepeatFormData(strtxt,temp10,templateId,clientId,diaryUserId,conditionId,fieldId);
//						System.out.println(strtxt);
					}
				}
				r++;
				
			}
					
			session.setAttribute("sessionselectedclientid", clientId);
			session.setAttribute("diaryUserId", diaryUserId);
			session.setAttribute("conditionId", conditionId);
			session.setAttribute("templateId", templateId);
			
			
			assessmentForm.setMessage("Field Added Sucessfully!!");
			addActionMessage("Field Added Sucessfully!!");
					
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return "assesmentsavedsuccessForMrd";
	}

	
	
	

	
}
