package com.apm.AssesmentForms.web.action.Predefine;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.AssesmentForms.eu.bi.ImportImageForAssessmentDAO;
import com.apm.AssesmentForms.eu.bi.ListAssessmentDAO;
import com.apm.AssesmentForms.eu.bi.Predefine.AssessmentTemplateDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCImportImageAssessmentDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCListAssessmentFormDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.Predefine.JDBCAssessmentTemplateDAO;
import com.apm.AssesmentForms.eu.entity.Assessment;
import com.apm.AssesmentForms.eu.entity.Predefine.AssessmentTemplate;
import com.apm.AssesmentForms.eu.entity.Predefine.GeneralHealth;
import com.apm.AssesmentForms.web.form.Predefine.AssessmentTemplateForm;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.Emr.eu.bi.ConsultationNoteDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCConsultationNoteDAO;
import com.apm.Registration.eu.entity.Location;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class AssessmentTemplateAction extends BaseAction implements Preparable, ModelDriven<AssessmentTemplateForm> {
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	AssessmentTemplateForm assessmentTemplateForm = new AssessmentTemplateForm();
	
	public String templateForms(){
		if(!verifyLogin(request)){
			return "login";
		}
		
		return "assessmentTemplate";
	}
	
	public String save() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AssessmentTemplateDAO assessmentTemplateDAO = new JDBCAssessmentTemplateDAO(connection);			
			AssessmentTemplate assessmentTemplate = new AssessmentTemplate();
			String clientId = assessmentTemplateForm.getClient();
			String practId = assessmentTemplateForm.getDiaryUser();
			String conditionId = assessmentTemplateForm.getCondition();
			
			String name = "Physiotherpy Assesment form";
			int formId = assessmentTemplateDAO.savePhysioAssessmentFormName(name); 
			
			int formname = assessmentTemplateDAO.savePhysioClientFormName(name,formId,clientId,practId,conditionId,assessmentTemplateForm.getImageData()); 
			
			assessmentTemplate.setId(assessmentTemplateForm.getId());
			assessmentTemplate.setClient(assessmentTemplateForm.getClient());
			assessmentTemplate.setClientId(assessmentTemplateForm.getClient());
			assessmentTemplate.setDiaryUserId(assessmentTemplateForm.getDiaryUser());
			assessmentTemplate.setConditionId(assessmentTemplateForm.getCondition());
			assessmentTemplate.setExamDate(assessmentTemplateForm.getExamDate());
			assessmentTemplate.setSubjectiveHistory(assessmentTemplateForm.getSubjectiveHistory());
			assessmentTemplate.setOutcomeMeasurement(assessmentTemplateForm.getOutcomeMeasurement());
			assessmentTemplate.setPrimaryDiagnosis(assessmentTemplateForm.getPrimaryDiagnosis());
			assessmentTemplate.setSecondaryDiagnosys(assessmentTemplateForm.getSecondaryDiagnosys());
			assessmentTemplate.setAssessmentDate(assessmentTemplateForm.getAssessmentDate());
			assessmentTemplate.setTreatmentDate(assessmentTemplateForm.getTreatmentDate());
			assessmentTemplate.setTreatmentDate(assessmentTemplateForm.getTreatmentDate());
			assessmentTemplate.setTreatmentUsed(assessmentTemplateForm.getTreatmentUsed());
			assessmentTemplate.setDischargeStatus(assessmentTemplateForm.getDischargeStatus());
			assessmentTemplate.setPrimaryDiagnosisDetails(assessmentTemplateForm.getPrimaryDiagnosisDetails());
			assessmentTemplate.setSecondaryDiagnosysDetails(assessmentTemplateForm.getSecondaryDiagnosysDetails());
			
			assessmentTemplate.setGeneralHealthData(assessmentTemplateForm.getGeneralHealthData());
			assessmentTemplate.setNightPain(assessmentTemplateForm.getNightPain());
			assessmentTemplate.setFamilyHistory(assessmentTemplateForm.getFamilyHistory());
			assessmentTemplate.setPsychoSocial(assessmentTemplateForm.getPsychoSocial());
			assessmentTemplate.setSurgery(assessmentTemplateForm.getSurgery());
			assessmentTemplate.setAccident(assessmentTemplateForm.getAccident());
			assessmentTemplate.setBicepRight(assessmentTemplateForm.getBicepRight());
			assessmentTemplate.setBicepLeft(assessmentTemplateForm.getBicepLeft());
			assessmentTemplate.setTricepRight(assessmentTemplateForm.getTricepRight());
			assessmentTemplate.setTricepLeft(assessmentTemplateForm.getTricepLeft());
			assessmentTemplate.setBrachioradialisRight(assessmentTemplateForm.getBrachioradialisRight());
			assessmentTemplate.setBrachioradialisLeft(assessmentTemplateForm.getBrachioradialisLeft());
			assessmentTemplate.setUlnt1Right(assessmentTemplateForm.getUlnt1Right());
			assessmentTemplate.setUlnt1Left(assessmentTemplateForm.getUlnt1Left());
			assessmentTemplate.setUlnt2Right(assessmentTemplateForm.getUlnt2Right());
			assessmentTemplate.setUlnt2Left(assessmentTemplateForm.getUlnt2Left());
			assessmentTemplate.setUlnt3Right(assessmentTemplateForm.getUlnt3Right());
			assessmentTemplate.setUlnt3Left(assessmentTemplateForm.getUlnt3Left());
			assessmentTemplate.setJrActiveMoment(assessmentTemplateForm.getJrActiveMoment());
			assessmentTemplate.setJrPassiveMoment(assessmentTemplateForm.getJrPassiveMoment());
			assessmentTemplate.setMyotomes(assessmentTemplateForm.getMyotomes());
			assessmentTemplate.setDermatomes(assessmentTemplateForm.getDermatomes());
			assessmentTemplate.setSensoryLossChanges(assessmentTemplateForm.getSensoryLossChanges());
			assessmentTemplate.setKneeRight(assessmentTemplateForm.getKneeRight());
			assessmentTemplate.setKneeLeft(assessmentTemplateForm.getKneeLeft());
			assessmentTemplate.setAnkleRight(assessmentTemplateForm.getAnkleRight());
			assessmentTemplate.setAnkleLeft(assessmentTemplateForm.getAnkleLeft());
			assessmentTemplate.setBabinskiRight(assessmentTemplateForm.getBabinskiRight());
			assessmentTemplate.setBabinskiLeft(assessmentTemplateForm.getBabinskiLeft());
			assessmentTemplate.setSlumpRight(assessmentTemplateForm.getSlumpRight());
			assessmentTemplate.setSlumpLeft(assessmentTemplateForm.getSlumpLeft());
			assessmentTemplate.setDorsiflexionRom1(assessmentTemplateForm.getDorsiflexionRom1());
			assessmentTemplate.setDorsiflexionPain1(assessmentTemplateForm.getDorsiflexionPain1());
			assessmentTemplate.setDorsiflexionRom2(assessmentTemplateForm.getDorsiflexionRom2());
			assessmentTemplate.setDorsiflexionPain2(assessmentTemplateForm.getDorsiflexionPain2());
			assessmentTemplate.setMedicalRotaionRom1(assessmentTemplateForm.getMedicalRotaionRom1());
			assessmentTemplate.setMedicalRotaionPain1(assessmentTemplateForm.getMedicalRotaionPain1());
			assessmentTemplate.setMedicalRotaionRom2(assessmentTemplateForm.getMedicalRotaionRom2());
			assessmentTemplate.setMedicalRotaionPain2(assessmentTemplateForm.getMedicalRotaionPain2());
			assessmentTemplate.setNeckFlexionRom1(assessmentTemplateForm.getNeckFlexionRom1());
			assessmentTemplate.setNeckFlexionPain1(assessmentTemplateForm.getNeckFlexionPain1());
			assessmentTemplate.setNeckFlexionRom2(assessmentTemplateForm.getNeckFlexionRom2());
			assessmentTemplate.setNeckFlexionPain2(assessmentTemplateForm.getNeckFlexionPain2());
			assessmentTemplate.setPassiveKneeBendRom1(assessmentTemplateForm.getPassiveKneeBendRom1());
			assessmentTemplate.setPassiveKneeBendPain1(assessmentTemplateForm.getPassiveKneeBendPain1());
			assessmentTemplate.setPassiveKneeBendRom2(assessmentTemplateForm.getPassiveKneeBendRom2());
			assessmentTemplate.setPassiveKneeBendPain2(assessmentTemplateForm.getPassiveKneeBendPain2());
			assessmentTemplate.setDizzyy(assessmentTemplateForm.getDizzyy());
			assessmentTemplate.setDizzyn(assessmentTemplateForm.getDizzyn());
			assessmentTemplate.setDoubleVisiony(assessmentTemplateForm.getDoubleVisiony());
			assessmentTemplate.setDoubleVisionn(assessmentTemplateForm.getDoubleVisionn());
			assessmentTemplate.setDysarthriay(assessmentTemplateForm.getDysarthriay());
			assessmentTemplate.setDysarthrian(assessmentTemplateForm.getDysarthrian());
			assessmentTemplate.setDysphagiay(assessmentTemplateForm.getDysphagiay());
			assessmentTemplate.setDysphagian(assessmentTemplateForm.getDysphagian());
			assessmentTemplate.setDropAttacky(assessmentTemplateForm.getDropAttacky());
			assessmentTemplate.setDropAttackn(assessmentTemplateForm.getDropAttackn());
			assessmentTemplate.setNystagmusy(assessmentTemplateForm.getNystagmusy());
			assessmentTemplate.setNystagmusn(assessmentTemplateForm.getNystagmusn());
			assessmentTemplate.setNauseay(assessmentTemplateForm.getNauseay());
			assessmentTemplate.setNausean(assessmentTemplateForm.getNausean());
			assessmentTemplate.setNumbnessy(assessmentTemplateForm.getNumbnessy());
			assessmentTemplate.setNumbnessn(assessmentTemplateForm.getNumbnessn());
			assessmentTemplate.setMedication(assessmentTemplateForm.getMedication());
			assessmentTemplate.setInvestigation(assessmentTemplateForm.getInvestigation());
			assessmentTemplate.setGeneralHealth(assessmentTemplateForm.getGeneralHealth());
			
			assessmentTemplate.setImageData(assessmentTemplateForm.getImageData());
			
			int physioTemplateId = assessmentTemplateDAO.saveTemplateDetails(assessmentTemplate);
			
			assessmentTemplate.setDiabetesyn(assessmentTemplateForm.getDiabetesyn());
			assessmentTemplate.setDiabetesDetail(assessmentTemplateForm.getDiabetesDetail());
			assessmentTemplate.setEpilepsyyn(assessmentTemplateForm.getEpilepsyyn());
			assessmentTemplate.setEpilepsyDetail(assessmentTemplateForm.getEpilepsyDetail());
			assessmentTemplate.setHeartConditionyn(assessmentTemplateForm.getHeartConditionyn());
			assessmentTemplate.setHeartConditionDetail(assessmentTemplateForm.getHeartConditionDetail());
			assessmentTemplate.setLungDisorderyn(assessmentTemplateForm.getLungDisorderyn());
			assessmentTemplate.setLungDisorderDetail(assessmentTemplateForm.getLungDisorderDetail());
			assessmentTemplate.setProstateyn(assessmentTemplateForm.getProstateyn());
			assessmentTemplate.setProstateDetail(assessmentTemplateForm.getProstateDetail());
			assessmentTemplate.setCarcinomayn(assessmentTemplateForm.getCarcinomayn());
			assessmentTemplate.setCarcinomaDetail(assessmentTemplateForm.getCarcinomaDetail());
			assessmentTemplate.setRayn(assessmentTemplateForm.getRayn());
			assessmentTemplate.setRaDetail(assessmentTemplateForm.getRaDetail());
			assessmentTemplate.setOsteopaeniayn(assessmentTemplateForm.getOsteopaeniayn());
			assessmentTemplate.setOsteopaeniaDetail(assessmentTemplateForm.getOsteopaeniaDetail());
			assessmentTemplate.setWtStableyn(assessmentTemplateForm.getWtStableyn());
			assessmentTemplate.setWtStableDetail(assessmentTemplateForm.getWtStableDetail());
			assessmentTemplate.setBladderBowelyn(assessmentTemplateForm.getBladderBowelyn());
			assessmentTemplate.setBladderBowelDetail(assessmentTemplateForm.getBladderBowelDetail());
			assessmentTemplate.setOtheryn(assessmentTemplateForm.getOtheryn());
			assessmentTemplate.setOtherDetail(assessmentTemplateForm.getOtherDetail());			
			
			int result = assessmentTemplateDAO.saveGeneralHealthDetails(physioTemplateId,assessmentTemplate);
			
			/*for(GeneralHealth generalHealth : assessmentTemplateForm.getGeneralHealth()){
				assessmentTemplate = new AssessmentTemplate();
				
				assessmentTemplate.setHealth(generalHealth.getHealth());
				assessmentTemplate.setYorn(generalHealth.getYorn());
				assessmentTemplate.setGdetails(generalHealth.getGdetails());
				
			}*/
			
			for(GeneralHealth painArea : assessmentTemplateForm.getPainArea()){
				assessmentTemplate = new AssessmentTemplate();
				assessmentTemplate.setDetail(painArea.getDetail());
				assessmentTemplate.setVas(painArea.getVas());
				
				int result1 = assessmentTemplateDAO.savePainAreaDetails(physioTemplateId,assessmentTemplate);
			}
			
			session.setAttribute("clientId", clientId);
			session.setAttribute("diaryUserId", practId);
			session.setAttribute("conditionId", conditionId);
			session.setAttribute("templateId", Integer.toString(formId));
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return "assesmentsavedsuccess";
	}
	
	public String getDetails() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			int selectedId = Integer.parseInt(request.getParameter("id"));
			AssessmentTemplateDAO assessmentTemplateDAO = new JDBCAssessmentTemplateDAO(connection);
			int physioId = assessmentTemplateDAO.getDetailsById(selectedId);
			AssessmentTemplate assessmentTemplate = new AssessmentTemplate();
			assessmentTemplate = assessmentTemplateDAO.getPhysioTemplateDetails(physioId);
			
			assessmentTemplateForm.setId(assessmentTemplate.getId());
			assessmentTemplateForm.setClient(assessmentTemplate.getClient());
			assessmentTemplateForm.setClientId(assessmentTemplate.getClientId());
			assessmentTemplateForm.setDiaryUserId(assessmentTemplate.getDiaryUserId());
			assessmentTemplateForm.setConditionId(assessmentTemplate.getConditionId());
			assessmentTemplateForm.setExamDate(assessmentTemplate.getExamDate());
			assessmentTemplateForm.setSubjectiveHistory(assessmentTemplate.getSubjectiveHistory());
			assessmentTemplateForm.setOutcomeMeasurement(assessmentTemplate.getOutcomeMeasurement());
			assessmentTemplateForm.setPrimaryDiagnosis(assessmentTemplate.getPrimaryDiagnosis());
			assessmentTemplateForm.setSecondaryDiagnosys(assessmentTemplate.getSecondaryDiagnosys());
			assessmentTemplateForm.setAssessmentDate(assessmentTemplate.getAssessmentDate());
			assessmentTemplateForm.setTreatmentDate(assessmentTemplate.getTreatmentDate());
			assessmentTemplateForm.setTreatmentDate(assessmentTemplate.getTreatmentDate());
			assessmentTemplateForm.setTreatmentUsed(assessmentTemplate.getTreatmentUsed());
			assessmentTemplateForm.setDischargeStatus(assessmentTemplate.getDischargeStatus());
			assessmentTemplateForm.setPrimaryDiagnosisDetails(assessmentTemplate.getPrimaryDiagnosisDetails());
			assessmentTemplateForm.setSecondaryDiagnosysDetails(assessmentTemplate.getSecondaryDiagnosysDetails());
			
			assessmentTemplateForm.setGeneralHealthData(assessmentTemplate.getGeneralHealthData());
			assessmentTemplateForm.setNightPain(assessmentTemplate.getNightPain());
			assessmentTemplateForm.setFamilyHistory(assessmentTemplate.getFamilyHistory());
			assessmentTemplateForm.setPsychoSocial(assessmentTemplate.getPsychoSocial());
			assessmentTemplateForm.setSurgery(assessmentTemplate.getSurgery());
			assessmentTemplateForm.setAccident(assessmentTemplate.getAccident());
			assessmentTemplateForm.setBicepRight(assessmentTemplate.getBicepRight());
			assessmentTemplateForm.setBicepLeft(assessmentTemplate.getBicepLeft());
			assessmentTemplateForm.setTricepRight(assessmentTemplate.getTricepRight());
			assessmentTemplateForm.setTricepLeft(assessmentTemplate.getTricepLeft());
			assessmentTemplateForm.setBrachioradialisRight(assessmentTemplate.getBrachioradialisRight());
			assessmentTemplateForm.setBrachioradialisLeft(assessmentTemplate.getBrachioradialisLeft());
			assessmentTemplateForm.setUlnt1Right(assessmentTemplate.getUlnt1Right());
			assessmentTemplateForm.setUlnt1Left(assessmentTemplate.getUlnt1Left());
			assessmentTemplateForm.setUlnt2Right(assessmentTemplate.getUlnt2Right());
			assessmentTemplateForm.setUlnt2Left(assessmentTemplate.getUlnt2Left());
			assessmentTemplateForm.setUlnt3Right(assessmentTemplate.getUlnt3Right());
			assessmentTemplateForm.setUlnt3Left(assessmentTemplate.getUlnt3Left());
			assessmentTemplateForm.setJrActiveMoment(assessmentTemplate.getJrActiveMoment());
			assessmentTemplateForm.setJrPassiveMoment(assessmentTemplate.getJrPassiveMoment());
			assessmentTemplateForm.setMyotomes(assessmentTemplate.getMyotomes());
			assessmentTemplateForm.setDermatomes(assessmentTemplate.getDermatomes());
			assessmentTemplateForm.setSensoryLossChanges(assessmentTemplate.getSensoryLossChanges());
			assessmentTemplateForm.setKneeRight(assessmentTemplate.getKneeRight());
			assessmentTemplateForm.setKneeLeft(assessmentTemplate.getKneeLeft());
			assessmentTemplateForm.setAnkleRight(assessmentTemplate.getAnkleRight());
			assessmentTemplateForm.setAnkleLeft(assessmentTemplate.getAnkleLeft());
			assessmentTemplateForm.setBabinskiRight(assessmentTemplate.getBabinskiRight());
			assessmentTemplateForm.setBabinskiLeft(assessmentTemplate.getBabinskiLeft());
			assessmentTemplateForm.setSlumpRight(assessmentTemplate.getSlumpRight());
			assessmentTemplateForm.setSlumpLeft(assessmentTemplate.getSlumpLeft());
			assessmentTemplateForm.setDorsiflexionRom1(assessmentTemplate.getDorsiflexionRom1());
			assessmentTemplateForm.setDorsiflexionPain1(assessmentTemplate.getDorsiflexionPain1());
			assessmentTemplateForm.setDorsiflexionRom2(assessmentTemplate.getDorsiflexionRom2());
			assessmentTemplateForm.setDorsiflexionPain2(assessmentTemplate.getDorsiflexionPain2());
			assessmentTemplateForm.setMedicalRotaionRom1(assessmentTemplate.getMedicalRotaionRom1());
			assessmentTemplateForm.setMedicalRotaionPain1(assessmentTemplate.getMedicalRotaionPain1());
			assessmentTemplateForm.setMedicalRotaionRom2(assessmentTemplate.getMedicalRotaionRom2());
			assessmentTemplateForm.setMedicalRotaionPain2(assessmentTemplate.getMedicalRotaionPain2());
			assessmentTemplateForm.setNeckFlexionRom1(assessmentTemplate.getNeckFlexionRom1());
			assessmentTemplateForm.setNeckFlexionPain1(assessmentTemplate.getNeckFlexionPain1());
			assessmentTemplateForm.setNeckFlexionRom2(assessmentTemplate.getNeckFlexionRom2());
			assessmentTemplateForm.setNeckFlexionPain2(assessmentTemplate.getNeckFlexionPain2());
			assessmentTemplateForm.setPassiveKneeBendRom1(assessmentTemplate.getPassiveKneeBendRom1());
			assessmentTemplateForm.setPassiveKneeBendPain1(assessmentTemplate.getPassiveKneeBendPain1());
			assessmentTemplateForm.setPassiveKneeBendRom2(assessmentTemplate.getPassiveKneeBendRom2());
			assessmentTemplateForm.setPassiveKneeBendPain2(assessmentTemplate.getPassiveKneeBendPain2());
			assessmentTemplateForm.setDizzyy(assessmentTemplate.getDizzyy());
			assessmentTemplateForm.setDizzyn(assessmentTemplate.getDizzyn());
			assessmentTemplateForm.setDoubleVisiony(assessmentTemplate.getDoubleVisiony());
			assessmentTemplateForm.setDoubleVisionn(assessmentTemplate.getDoubleVisionn());
			assessmentTemplateForm.setDysarthriay(assessmentTemplate.getDysarthriay());
			assessmentTemplateForm.setDysarthrian(assessmentTemplate.getDysarthrian());
			assessmentTemplateForm.setDysphagiay(assessmentTemplate.getDysphagiay());
			assessmentTemplateForm.setDysphagian(assessmentTemplate.getDysphagian());
			assessmentTemplateForm.setDropAttacky(assessmentTemplate.getDropAttacky());
			assessmentTemplateForm.setDropAttackn(assessmentTemplate.getDropAttackn());
			assessmentTemplateForm.setNystagmusy(assessmentTemplate.getNystagmusy());
			assessmentTemplateForm.setNystagmusn(assessmentTemplate.getNystagmusn());
			assessmentTemplateForm.setNauseay(assessmentTemplate.getNauseay());
			assessmentTemplateForm.setNausean(assessmentTemplate.getNausean());
			assessmentTemplateForm.setNumbnessy(assessmentTemplate.getNumbnessy());
			assessmentTemplateForm.setNumbnessn(assessmentTemplate.getNumbnessn());
			
			assessmentTemplateForm.setMedication(assessmentTemplate.getMedication());
			assessmentTemplateForm.setInvestigation(assessmentTemplate.getInvestigation());
			assessmentTemplateForm.setGeneralHealth(assessmentTemplate.getGeneralHealth());
			
			int clientId = Integer.parseInt(assessmentTemplate.getClientId());
			int diaryUserId = Integer.parseInt(assessmentTemplate.getDiaryUserId());
			
			ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
			ArrayList<Client>conditionList = consultationNoteDAO.getConditionList(clientId);
			assessmentTemplateForm.setConditionList(conditionList);
			
			ArrayList<Client>clientList = consultationNoteDAO.getClientList(diaryUserId);
			assessmentTemplateForm.setClientList(clientList);
			 
			assessmentTemplateForm.setClient(Integer.toString(clientId));
			assessmentTemplateForm.setDiaryUser(assessmentTemplate.getDiaryUserId());
			assessmentTemplateForm.setCondition(assessmentTemplate.getConditionId());
			
			String imageData = assessmentTemplate.getImageData();
			session.setAttribute("imageData", imageData);
			
			assessmentTemplate = assessmentTemplateDAO.getGeneralHealthDetails(selectedId);
			
			assessmentTemplateForm.setDiabetesyn(assessmentTemplate.getDiabetesyn());
			assessmentTemplateForm.setDiabetesDetail(assessmentTemplate.getDiabetesDetail());
			assessmentTemplateForm.setEpilepsyyn(assessmentTemplate.getEpilepsyyn());
			assessmentTemplateForm.setEpilepsyDetail(assessmentTemplate.getEpilepsyDetail());
			assessmentTemplateForm.setHeartConditionyn(assessmentTemplate.getHeartConditionyn());
			assessmentTemplateForm.setHeartConditionDetail(assessmentTemplate.getHeartConditionDetail());
			assessmentTemplateForm.setLungDisorderyn(assessmentTemplate.getLungDisorderyn());
			assessmentTemplateForm.setLungDisorderDetail(assessmentTemplate.getLungDisorderDetail());
			assessmentTemplateForm.setProstateyn(assessmentTemplate.getProstateyn());
			assessmentTemplateForm.setProstateDetail(assessmentTemplate.getProstateDetail());
			assessmentTemplateForm.setCarcinomayn(assessmentTemplate.getCarcinomayn());
			assessmentTemplateForm.setCarcinomaDetail(assessmentTemplate.getCarcinomaDetail());
			assessmentTemplateForm.setRayn(assessmentTemplate.getRayn());
			assessmentTemplateForm.setRaDetail(assessmentTemplate.getRaDetail());
			assessmentTemplateForm.setOsteopaeniayn(assessmentTemplate.getOsteopaeniayn());
			assessmentTemplateForm.setOsteopaeniaDetail(assessmentTemplate.getOsteopaeniaDetail());
			assessmentTemplateForm.setWtStableyn(assessmentTemplate.getWtStableyn());
			assessmentTemplateForm.setWtStableDetail(assessmentTemplate.getWtStableDetail());
			assessmentTemplateForm.setBladderBowelyn(assessmentTemplate.getBladderBowelyn());
			assessmentTemplateForm.setBladderBowelDetail(assessmentTemplate.getBladderBowelDetail());
			assessmentTemplateForm.setOtheryn(assessmentTemplate.getOtheryn());
			assessmentTemplateForm.setOtherDetail(assessmentTemplate.getOtherDetail());		
			
			assessmentTemplateForm.setActionType("2");
			
			
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return "editPhysioTemplate";
	}
	
	public String edit() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			int selectedId = Integer.parseInt(request.getParameter("id"));
			AssessmentTemplateDAO assessmentTemplateDAO = new JDBCAssessmentTemplateDAO(connection);	
			AssessmentTemplate assessmentTemplate = new AssessmentTemplate();
			assessmentTemplate = assessmentTemplateDAO.getPhysioTemplateDetails(selectedId);
			
			assessmentTemplateForm.setId(assessmentTemplate.getId());
			assessmentTemplateForm.setClient(assessmentTemplate.getClient());
			assessmentTemplateForm.setClientId(assessmentTemplate.getClientId());
			assessmentTemplateForm.setDiaryUserId(assessmentTemplate.getDiaryUserId());
			assessmentTemplateForm.setConditionId(assessmentTemplate.getConditionId());
			assessmentTemplateForm.setExamDate(assessmentTemplate.getExamDate());
			assessmentTemplateForm.setSubjectiveHistory(assessmentTemplate.getSubjectiveHistory());
			assessmentTemplateForm.setOutcomeMeasurement(assessmentTemplate.getOutcomeMeasurement());
			assessmentTemplateForm.setPrimaryDiagnosis(assessmentTemplate.getPrimaryDiagnosis());
			assessmentTemplateForm.setSecondaryDiagnosys(assessmentTemplate.getSecondaryDiagnosys());
			assessmentTemplateForm.setAssessmentDate(assessmentTemplate.getAssessmentDate());
			assessmentTemplateForm.setTreatmentDate(assessmentTemplate.getTreatmentDate());
			assessmentTemplateForm.setTreatmentDate(assessmentTemplate.getTreatmentDate());
			assessmentTemplateForm.setTreatmentUsed(assessmentTemplate.getTreatmentUsed());
			assessmentTemplateForm.setDischargeStatus(assessmentTemplate.getDischargeStatus());
			assessmentTemplateForm.setPrimaryDiagnosisDetails(assessmentTemplate.getPrimaryDiagnosisDetails());
			assessmentTemplateForm.setSecondaryDiagnosysDetails(assessmentTemplate.getSecondaryDiagnosysDetails());
			
			assessmentTemplateForm.setGeneralHealthData(assessmentTemplate.getGeneralHealthData());
			assessmentTemplateForm.setNightPain(assessmentTemplate.getNightPain());
			assessmentTemplateForm.setFamilyHistory(assessmentTemplate.getFamilyHistory());
			assessmentTemplateForm.setPsychoSocial(assessmentTemplate.getPsychoSocial());
			assessmentTemplateForm.setSurgery(assessmentTemplate.getSurgery());
			assessmentTemplateForm.setAccident(assessmentTemplate.getAccident());
			assessmentTemplateForm.setBicepRight(assessmentTemplate.getBicepRight());
			assessmentTemplateForm.setBicepLeft(assessmentTemplate.getBicepLeft());
			assessmentTemplateForm.setTricepRight(assessmentTemplate.getTricepRight());
			assessmentTemplateForm.setTricepLeft(assessmentTemplate.getTricepLeft());
			assessmentTemplateForm.setBrachioradialisRight(assessmentTemplate.getBrachioradialisRight());
			assessmentTemplateForm.setBrachioradialisLeft(assessmentTemplate.getBrachioradialisLeft());
			assessmentTemplateForm.setUlnt1Right(assessmentTemplate.getUlnt1Right());
			assessmentTemplateForm.setUlnt1Left(assessmentTemplate.getUlnt1Left());
			assessmentTemplateForm.setUlnt2Right(assessmentTemplate.getUlnt2Right());
			assessmentTemplateForm.setUlnt2Left(assessmentTemplate.getUlnt2Left());
			assessmentTemplateForm.setUlnt3Right(assessmentTemplate.getUlnt3Right());
			assessmentTemplateForm.setUlnt3Left(assessmentTemplate.getUlnt3Left());
			assessmentTemplateForm.setJrActiveMoment(assessmentTemplate.getJrActiveMoment());
			assessmentTemplateForm.setJrPassiveMoment(assessmentTemplate.getJrPassiveMoment());
			assessmentTemplateForm.setMyotomes(assessmentTemplate.getMyotomes());
			assessmentTemplateForm.setDermatomes(assessmentTemplate.getDermatomes());
			assessmentTemplateForm.setSensoryLossChanges(assessmentTemplate.getSensoryLossChanges());
			assessmentTemplateForm.setKneeRight(assessmentTemplate.getKneeRight());
			assessmentTemplateForm.setKneeLeft(assessmentTemplate.getKneeLeft());
			assessmentTemplateForm.setAnkleRight(assessmentTemplate.getAnkleRight());
			assessmentTemplateForm.setAnkleLeft(assessmentTemplate.getAnkleLeft());
			assessmentTemplateForm.setBabinskiRight(assessmentTemplate.getBabinskiRight());
			assessmentTemplateForm.setBabinskiLeft(assessmentTemplate.getBabinskiLeft());
			assessmentTemplateForm.setSlumpRight(assessmentTemplate.getSlumpRight());
			assessmentTemplateForm.setSlumpLeft(assessmentTemplate.getSlumpLeft());
			assessmentTemplateForm.setDorsiflexionRom1(assessmentTemplate.getDorsiflexionRom1());
			assessmentTemplateForm.setDorsiflexionPain1(assessmentTemplate.getDorsiflexionPain1());
			assessmentTemplateForm.setDorsiflexionRom2(assessmentTemplate.getDorsiflexionRom2());
			assessmentTemplateForm.setDorsiflexionPain2(assessmentTemplate.getDorsiflexionPain2());
			assessmentTemplateForm.setMedicalRotaionRom1(assessmentTemplate.getMedicalRotaionRom1());
			assessmentTemplateForm.setMedicalRotaionPain1(assessmentTemplate.getMedicalRotaionPain1());
			assessmentTemplateForm.setMedicalRotaionRom2(assessmentTemplate.getMedicalRotaionRom2());
			assessmentTemplateForm.setMedicalRotaionPain2(assessmentTemplate.getMedicalRotaionPain2());
			assessmentTemplateForm.setNeckFlexionRom1(assessmentTemplate.getNeckFlexionRom1());
			assessmentTemplateForm.setNeckFlexionPain1(assessmentTemplate.getNeckFlexionPain1());
			assessmentTemplateForm.setNeckFlexionRom2(assessmentTemplate.getNeckFlexionRom2());
			assessmentTemplateForm.setNeckFlexionPain2(assessmentTemplate.getNeckFlexionPain2());
			assessmentTemplateForm.setPassiveKneeBendRom1(assessmentTemplate.getPassiveKneeBendRom1());
			assessmentTemplateForm.setPassiveKneeBendPain1(assessmentTemplate.getPassiveKneeBendPain1());
			assessmentTemplateForm.setPassiveKneeBendRom2(assessmentTemplate.getPassiveKneeBendRom2());
			assessmentTemplateForm.setPassiveKneeBendPain2(assessmentTemplate.getPassiveKneeBendPain2());
			assessmentTemplateForm.setDizzyy(assessmentTemplate.getDizzyy());
			assessmentTemplateForm.setDizzyn(assessmentTemplate.getDizzyn());
			assessmentTemplateForm.setDoubleVisiony(assessmentTemplate.getDoubleVisiony());
			assessmentTemplateForm.setDoubleVisionn(assessmentTemplate.getDoubleVisionn());
			assessmentTemplateForm.setDysarthriay(assessmentTemplate.getDysarthriay());
			assessmentTemplateForm.setDysarthrian(assessmentTemplate.getDysarthrian());
			assessmentTemplateForm.setDysphagiay(assessmentTemplate.getDysphagiay());
			assessmentTemplateForm.setDysphagian(assessmentTemplate.getDysphagian());
			assessmentTemplateForm.setDropAttacky(assessmentTemplate.getDropAttacky());
			assessmentTemplateForm.setDropAttackn(assessmentTemplate.getDropAttackn());
			assessmentTemplateForm.setNystagmusy(assessmentTemplate.getNystagmusy());
			assessmentTemplateForm.setNystagmusn(assessmentTemplate.getNystagmusn());
			assessmentTemplateForm.setNauseay(assessmentTemplate.getNauseay());
			assessmentTemplateForm.setNausean(assessmentTemplate.getNausean());
			assessmentTemplateForm.setNumbnessy(assessmentTemplate.getNumbnessy());
			assessmentTemplateForm.setNumbnessn(assessmentTemplate.getNumbnessn());
			
			assessmentTemplateForm.setMedication(assessmentTemplate.getMedication());
			assessmentTemplateForm.setInvestigation(assessmentTemplate.getInvestigation());
			assessmentTemplateForm.setGeneralHealth(assessmentTemplate.getGeneralHealth());
			
			int clientId = Integer.parseInt(assessmentTemplate.getClientId());
			int diaryUserId = Integer.parseInt(assessmentTemplate.getDiaryUserId());
			
			ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
			ArrayList<Client>conditionList = consultationNoteDAO.getConditionList(clientId);
			assessmentTemplateForm.setConditionList(conditionList);
			
			ArrayList<Client>clientList = consultationNoteDAO.getClientList(diaryUserId);
			assessmentTemplateForm.setClientList(clientList);
			 
			assessmentTemplateForm.setClient(Integer.toString(clientId));
			assessmentTemplateForm.setDiaryUser(assessmentTemplate.getDiaryUserId());
			assessmentTemplateForm.setCondition(assessmentTemplate.getConditionId());
			
			String imageData = assessmentTemplate.getImageData();
			session.setAttribute("imageData", imageData);
			
			assessmentTemplate = assessmentTemplateDAO.getGeneralHealthDetails(selectedId);
			
			assessmentTemplateForm.setDiabetesyn(assessmentTemplate.getDiabetesyn());
			assessmentTemplateForm.setDiabetesDetail(assessmentTemplate.getDiabetesDetail());
			assessmentTemplateForm.setEpilepsyyn(assessmentTemplate.getEpilepsyyn());
			assessmentTemplateForm.setEpilepsyDetail(assessmentTemplate.getEpilepsyDetail());
			assessmentTemplateForm.setHeartConditionyn(assessmentTemplate.getHeartConditionyn());
			assessmentTemplateForm.setHeartConditionDetail(assessmentTemplate.getHeartConditionDetail());
			assessmentTemplateForm.setLungDisorderyn(assessmentTemplate.getLungDisorderyn());
			assessmentTemplateForm.setLungDisorderDetail(assessmentTemplate.getLungDisorderDetail());
			assessmentTemplateForm.setProstateyn(assessmentTemplate.getProstateyn());
			assessmentTemplateForm.setProstateDetail(assessmentTemplate.getProstateDetail());
			assessmentTemplateForm.setCarcinomayn(assessmentTemplate.getCarcinomayn());
			assessmentTemplateForm.setCarcinomaDetail(assessmentTemplate.getCarcinomaDetail());
			assessmentTemplateForm.setRayn(assessmentTemplate.getRayn());
			assessmentTemplateForm.setRaDetail(assessmentTemplate.getRaDetail());
			assessmentTemplateForm.setOsteopaeniayn(assessmentTemplate.getOsteopaeniayn());
			assessmentTemplateForm.setOsteopaeniaDetail(assessmentTemplate.getOsteopaeniaDetail());
			assessmentTemplateForm.setWtStableyn(assessmentTemplate.getWtStableyn());
			assessmentTemplateForm.setWtStableDetail(assessmentTemplate.getWtStableDetail());
			assessmentTemplateForm.setBladderBowelyn(assessmentTemplate.getBladderBowelyn());
			assessmentTemplateForm.setBladderBowelDetail(assessmentTemplate.getBladderBowelDetail());
			assessmentTemplateForm.setOtheryn(assessmentTemplate.getOtheryn());
			assessmentTemplateForm.setOtherDetail(assessmentTemplate.getOtherDetail());		
			
			assessmentTemplateForm.setActionType("1");
			
			
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return "editPhysioTemplate";
	}
	
	public String update() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AssessmentTemplateDAO assessmentTemplateDAO = new JDBCAssessmentTemplateDAO(connection);			
			AssessmentTemplate assessmentTemplate = new AssessmentTemplate();
			
			int physioTemplateId = assessmentTemplateForm.getId();
			
			assessmentTemplate.setId(assessmentTemplateForm.getId());
			assessmentTemplate.setClient(assessmentTemplateForm.getClient());
			assessmentTemplate.setClientId(assessmentTemplateForm.getClient());
			assessmentTemplate.setDiaryUserId(assessmentTemplateForm.getDiaryUser());
			assessmentTemplate.setConditionId(assessmentTemplateForm.getCondition());
			assessmentTemplate.setExamDate(assessmentTemplateForm.getExamDate());
			assessmentTemplate.setSubjectiveHistory(assessmentTemplateForm.getSubjectiveHistory());
			assessmentTemplate.setOutcomeMeasurement(assessmentTemplateForm.getOutcomeMeasurement());
			assessmentTemplate.setPrimaryDiagnosis(assessmentTemplateForm.getPrimaryDiagnosis());
			assessmentTemplate.setSecondaryDiagnosys(assessmentTemplateForm.getSecondaryDiagnosys());
			assessmentTemplate.setAssessmentDate(assessmentTemplateForm.getAssessmentDate());
			assessmentTemplate.setTreatmentDate(assessmentTemplateForm.getTreatmentDate());
			assessmentTemplate.setTreatmentDate(assessmentTemplateForm.getTreatmentDate());
			assessmentTemplate.setTreatmentUsed(assessmentTemplateForm.getTreatmentUsed());
			assessmentTemplate.setDischargeStatus(assessmentTemplateForm.getDischargeStatus());
			assessmentTemplate.setPrimaryDiagnosisDetails(assessmentTemplateForm.getPrimaryDiagnosisDetails());
			assessmentTemplate.setSecondaryDiagnosysDetails(assessmentTemplateForm.getSecondaryDiagnosysDetails());
			
			assessmentTemplate.setMedication(assessmentTemplateForm.getMedication());
			assessmentTemplate.setInvestigation(assessmentTemplateForm.getInvestigation());
			assessmentTemplate.setGeneralHealth(assessmentTemplateForm.getGeneralHealth());
			assessmentTemplate.setNightPain(assessmentTemplateForm.getNightPain());
			assessmentTemplate.setFamilyHistory(assessmentTemplateForm.getFamilyHistory());
			assessmentTemplate.setPsychoSocial(assessmentTemplateForm.getPsychoSocial());
			assessmentTemplate.setSurgery(assessmentTemplateForm.getSurgery());
			assessmentTemplate.setAccident(assessmentTemplateForm.getAccident());
			assessmentTemplate.setBicepRight(assessmentTemplateForm.getBicepRight());
			assessmentTemplate.setBicepLeft(assessmentTemplateForm.getBicepLeft());
			assessmentTemplate.setTricepRight(assessmentTemplateForm.getTricepRight());
			assessmentTemplate.setTricepLeft(assessmentTemplateForm.getTricepLeft());
			assessmentTemplate.setBrachioradialisRight(assessmentTemplateForm.getBrachioradialisRight());
			assessmentTemplate.setBrachioradialisLeft(assessmentTemplateForm.getBrachioradialisLeft());
			assessmentTemplate.setUlnt1Right(assessmentTemplateForm.getUlnt1Right());
			assessmentTemplate.setUlnt1Left(assessmentTemplateForm.getUlnt1Left());
			assessmentTemplate.setUlnt2Right(assessmentTemplateForm.getUlnt2Right());
			assessmentTemplate.setUlnt2Left(assessmentTemplateForm.getUlnt2Left());
			assessmentTemplate.setUlnt3Right(assessmentTemplateForm.getUlnt3Right());
			assessmentTemplate.setUlnt3Left(assessmentTemplateForm.getUlnt3Left());
			assessmentTemplate.setJrActiveMoment(assessmentTemplateForm.getJrActiveMoment());
			assessmentTemplate.setJrPassiveMoment(assessmentTemplateForm.getJrPassiveMoment());
			assessmentTemplate.setMyotomes(assessmentTemplateForm.getMyotomes());
			assessmentTemplate.setDermatomes(assessmentTemplateForm.getDermatomes());
			assessmentTemplate.setSensoryLossChanges(assessmentTemplateForm.getSensoryLossChanges());
			assessmentTemplate.setKneeRight(assessmentTemplateForm.getKneeRight());
			assessmentTemplate.setKneeLeft(assessmentTemplateForm.getKneeLeft());
			assessmentTemplate.setAnkleRight(assessmentTemplateForm.getAnkleRight());
			assessmentTemplate.setAnkleLeft(assessmentTemplateForm.getAnkleLeft());
			assessmentTemplate.setBabinskiRight(assessmentTemplateForm.getBabinskiRight());
			assessmentTemplate.setBabinskiLeft(assessmentTemplateForm.getBabinskiLeft());
			assessmentTemplate.setSlumpRight(assessmentTemplateForm.getSlumpRight());
			assessmentTemplate.setSlumpLeft(assessmentTemplateForm.getSlumpLeft());
			assessmentTemplate.setDorsiflexionRom1(assessmentTemplateForm.getDorsiflexionRom1());
			assessmentTemplate.setDorsiflexionPain1(assessmentTemplateForm.getDorsiflexionPain1());
			assessmentTemplate.setDorsiflexionRom2(assessmentTemplateForm.getDorsiflexionRom2());
			assessmentTemplate.setDorsiflexionPain2(assessmentTemplateForm.getDorsiflexionPain2());
			assessmentTemplate.setMedicalRotaionRom1(assessmentTemplateForm.getMedicalRotaionRom1());
			assessmentTemplate.setMedicalRotaionPain1(assessmentTemplateForm.getMedicalRotaionPain1());
			assessmentTemplate.setMedicalRotaionRom2(assessmentTemplateForm.getMedicalRotaionRom2());
			assessmentTemplate.setMedicalRotaionPain2(assessmentTemplateForm.getMedicalRotaionPain2());
			assessmentTemplate.setNeckFlexionRom1(assessmentTemplateForm.getNeckFlexionRom1());
			assessmentTemplate.setNeckFlexionPain1(assessmentTemplateForm.getNeckFlexionPain1());
			assessmentTemplate.setNeckFlexionRom2(assessmentTemplateForm.getNeckFlexionRom2());
			assessmentTemplate.setNeckFlexionPain2(assessmentTemplateForm.getNeckFlexionPain2());
			assessmentTemplate.setPassiveKneeBendRom1(assessmentTemplateForm.getPassiveKneeBendRom1());
			assessmentTemplate.setPassiveKneeBendPain1(assessmentTemplateForm.getPassiveKneeBendPain1());
			assessmentTemplate.setPassiveKneeBendRom2(assessmentTemplateForm.getPassiveKneeBendRom2());
			assessmentTemplate.setPassiveKneeBendPain2(assessmentTemplateForm.getPassiveKneeBendPain2());
			assessmentTemplate.setDizzyy(assessmentTemplateForm.getDizzyy());
			assessmentTemplate.setDizzyn(assessmentTemplateForm.getDizzyn());
			assessmentTemplate.setDoubleVisiony(assessmentTemplateForm.getDoubleVisiony());
			assessmentTemplate.setDoubleVisionn(assessmentTemplateForm.getDoubleVisionn());
			assessmentTemplate.setDysarthriay(assessmentTemplateForm.getDysarthriay());
			assessmentTemplate.setDysarthrian(assessmentTemplateForm.getDysarthrian());
			assessmentTemplate.setDysphagiay(assessmentTemplateForm.getDysphagiay());
			assessmentTemplate.setDysphagian(assessmentTemplateForm.getDysphagian());
			assessmentTemplate.setDropAttacky(assessmentTemplateForm.getDropAttacky());
			assessmentTemplate.setDropAttackn(assessmentTemplateForm.getDropAttackn());
			assessmentTemplate.setNystagmusy(assessmentTemplateForm.getNystagmusy());
			assessmentTemplate.setNystagmusn(assessmentTemplateForm.getNystagmusn());
			assessmentTemplate.setNauseay(assessmentTemplateForm.getNauseay());
			assessmentTemplate.setNausean(assessmentTemplateForm.getNausean());
			assessmentTemplate.setNumbnessy(assessmentTemplateForm.getNumbnessy());
			assessmentTemplate.setNumbnessn(assessmentTemplateForm.getNumbnessn());
			
			assessmentTemplate.setImageData(assessmentTemplateForm.getImageData());
			
			
			
			int result = assessmentTemplateDAO.updateTemplateDetails(physioTemplateId,assessmentTemplate);
			
			assessmentTemplate.setDiabetesyn(assessmentTemplateForm.getDiabetesyn());
			assessmentTemplate.setDiabetesDetail(assessmentTemplateForm.getDiabetesDetail());
			assessmentTemplate.setEpilepsyyn(assessmentTemplateForm.getEpilepsyyn());
			assessmentTemplate.setEpilepsyDetail(assessmentTemplateForm.getEpilepsyDetail());
			assessmentTemplate.setHeartConditionyn(assessmentTemplateForm.getHeartConditionyn());
			assessmentTemplate.setHeartConditionDetail(assessmentTemplateForm.getHeartConditionDetail());
			assessmentTemplate.setLungDisorderyn(assessmentTemplateForm.getLungDisorderyn());
			assessmentTemplate.setLungDisorderDetail(assessmentTemplateForm.getLungDisorderDetail());
			assessmentTemplate.setProstateyn(assessmentTemplateForm.getProstateyn());
			assessmentTemplate.setProstateDetail(assessmentTemplateForm.getProstateDetail());
			assessmentTemplate.setCarcinomayn(assessmentTemplateForm.getCarcinomayn());
			assessmentTemplate.setCarcinomaDetail(assessmentTemplateForm.getCarcinomaDetail());
			assessmentTemplate.setRayn(assessmentTemplateForm.getRayn());
			assessmentTemplate.setRaDetail(assessmentTemplateForm.getRaDetail());
			assessmentTemplate.setOsteopaeniayn(assessmentTemplateForm.getOsteopaeniayn());
			assessmentTemplate.setOsteopaeniaDetail(assessmentTemplateForm.getOsteopaeniaDetail());
			assessmentTemplate.setWtStableyn(assessmentTemplateForm.getWtStableyn());
			assessmentTemplate.setWtStableDetail(assessmentTemplateForm.getWtStableDetail());
			assessmentTemplate.setBladderBowelyn(assessmentTemplateForm.getBladderBowelyn());
			assessmentTemplate.setBladderBowelDetail(assessmentTemplateForm.getBladderBowelDetail());
			assessmentTemplate.setOtheryn(assessmentTemplateForm.getOtheryn());
			assessmentTemplate.setOtherDetail(assessmentTemplateForm.getOtherDetail());			
			
			int result1 = assessmentTemplateDAO.updateGeneralHealthDetails(physioTemplateId,assessmentTemplate);
			
			/*for(GeneralHealth generalHealth : assessmentTemplateForm.getGeneralHealth()){
				assessmentTemplate = new AssessmentTemplate();
				
				assessmentTemplate.setHealth(generalHealth.getHealth());
				assessmentTemplate.setYorn(generalHealth.getYorn());
				assessmentTemplate.setGdetails(generalHealth.getGdetails());
				
			}*/
			
			/*for(GeneralHealth generalHealth : assessmentTemplateForm.getGeneralHealth()){
				assessmentTemplate = new AssessmentTemplate();
				assessmentTemplate.setDetail(generalHealth.getDetail());
				assessmentTemplate.setVas(generalHealth.getVas());
				
				int result1 = assessmentTemplateDAO.savePainAreaDetails(physioTemplateId,assessmentTemplate);
			}
			*/
			
		/*	String clientId = (String) session.getAttribute("clientId");
			ArrayList<AssessmentTemplate> physioTemplateList = new ArrayList<AssessmentTemplate>();
			physioTemplateList = assessmentTemplateDAO.getPhysioTemplateList(clientId);
			session.setAttribute("physioTemplateList", physioTemplateList);*/
					
			String clientId = assessmentTemplateForm.getClient();
			String practId = assessmentTemplateForm.getDiaryUser();
			String conditionId = assessmentTemplateForm.getCondition();
			
			setPysioTemplateForm(clientId,practId,conditionId);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return "assesmentsavedsuccess";
	}
	
	private void setPysioTemplateForm(String clientId,String practId,String conditionId) throws Exception {

		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AssessmentTemplateDAO assessmentTemplateDAO = new JDBCAssessmentTemplateDAO(connection);
		
			ArrayList<AssessmentTemplate> physioTemplateList = new ArrayList<AssessmentTemplate>();
			physioTemplateList = assessmentTemplateDAO.getPhysioTemplateList(clientId, practId, conditionId);			
			String templateId = assessmentTemplateDAO.getTemplateId(clientId,practId,conditionId);
			session.setAttribute("templateId", templateId);
			session.setAttribute("clientId", clientId);
			session.setAttribute("diaryUserId", practId);
			session.setAttribute("conditionId", conditionId);
			session.setAttribute("physioTemplateList", physioTemplateList);
			
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
	}

	public String delete() throws Exception{

		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			int selectedId = Integer.parseInt(request.getParameter("id"));
			AssessmentTemplateDAO assessmentTemplateDAO = new JDBCAssessmentTemplateDAO(connection);			
			
			AssessmentTemplate assessmentTemplate = new AssessmentTemplate();
			assessmentTemplate = assessmentTemplateDAO.getPhysioTemplateDetails(selectedId);
			
			int result3 = assessmentTemplateDAO.deleteClientAssessment(selectedId);			
			int result = assessmentTemplateDAO.deletePhysioTemplate(selectedId);
			int result1 = assessmentTemplateDAO.deleteGeneralHealthTemplate(selectedId);
			int result2 = assessmentTemplateDAO.deletePainAreaTemplate(selectedId);
			
			String clientId = assessmentTemplate.getClientId();
			String practId = assessmentTemplate.getDiaryUserId();
			String conditionId = assessmentTemplate.getConditionId();
			/*ArrayList<AssessmentTemplate> physioTemplateList = new ArrayList<AssessmentTemplate>();
			physioTemplateList = assessmentTemplateDAO.getPhysioTemplateList(clientId);
			session.setAttribute("physioTemplateList", physioTemplateList);*/
			
			setPysioTemplateForm(clientId,practId,conditionId);
					
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return "assesmentsavedsuccess";
	}
	
	
	
	public String updateImage() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			String id = request.getParameter("id");
			int selectedId = Integer.parseInt(id);
			String actionType = request.getParameter("actionType");
			ListAssessmentDAO listAssessmentDAO = new JDBCListAssessmentFormDAO(connection);
			int result = listAssessmentDAO.updateImageTemplate(selectedId,assessmentTemplateForm.getImageData());
			AssessmentTemplateDAO assessmentTemplateDAO = new JDBCAssessmentTemplateDAO(connection);
			int result1 = assessmentTemplateDAO.updateImageTemplate(selectedId,assessmentTemplateForm.getImageData());
			
			AssessmentTemplate assessmentTemplate = new AssessmentTemplate();
			assessmentTemplate = assessmentTemplateDAO.getPhysioTemplateDetails(selectedId);
			
			assessmentTemplateForm.setId(assessmentTemplate.getId());
			assessmentTemplateForm.setClient(assessmentTemplate.getClient());
			assessmentTemplateForm.setClientId(assessmentTemplate.getClientId());
			assessmentTemplateForm.setDiaryUserId(assessmentTemplate.getDiaryUserId());
			assessmentTemplateForm.setConditionId(assessmentTemplate.getConditionId());
			assessmentTemplateForm.setExamDate(assessmentTemplate.getExamDate());
			assessmentTemplateForm.setSubjectiveHistory(assessmentTemplate.getSubjectiveHistory());
			assessmentTemplateForm.setOutcomeMeasurement(assessmentTemplate.getOutcomeMeasurement());
			assessmentTemplateForm.setPrimaryDiagnosis(assessmentTemplate.getPrimaryDiagnosis());
			assessmentTemplateForm.setSecondaryDiagnosys(assessmentTemplate.getSecondaryDiagnosys());
			assessmentTemplateForm.setAssessmentDate(assessmentTemplate.getAssessmentDate());
			assessmentTemplateForm.setTreatmentDate(assessmentTemplate.getTreatmentDate());
			assessmentTemplateForm.setTreatmentDate(assessmentTemplate.getTreatmentDate());
			assessmentTemplateForm.setTreatmentUsed(assessmentTemplate.getTreatmentUsed());
			assessmentTemplateForm.setDischargeStatus(assessmentTemplate.getDischargeStatus());
			assessmentTemplateForm.setPrimaryDiagnosisDetails(assessmentTemplate.getPrimaryDiagnosisDetails());
			assessmentTemplateForm.setSecondaryDiagnosysDetails(assessmentTemplate.getSecondaryDiagnosysDetails());
			
			assessmentTemplateForm.setGeneralHealthData(assessmentTemplate.getGeneralHealthData());
			assessmentTemplateForm.setNightPain(assessmentTemplate.getNightPain());
			assessmentTemplateForm.setFamilyHistory(assessmentTemplate.getFamilyHistory());
			assessmentTemplateForm.setPsychoSocial(assessmentTemplate.getPsychoSocial());
			assessmentTemplateForm.setSurgery(assessmentTemplate.getSurgery());
			assessmentTemplateForm.setAccident(assessmentTemplate.getAccident());
			assessmentTemplateForm.setBicepRight(assessmentTemplate.getBicepRight());
			assessmentTemplateForm.setBicepLeft(assessmentTemplate.getBicepLeft());
			assessmentTemplateForm.setTricepRight(assessmentTemplate.getTricepRight());
			assessmentTemplateForm.setTricepLeft(assessmentTemplate.getTricepLeft());
			assessmentTemplateForm.setBrachioradialisRight(assessmentTemplate.getBrachioradialisRight());
			assessmentTemplateForm.setBrachioradialisLeft(assessmentTemplate.getBrachioradialisLeft());
			assessmentTemplateForm.setUlnt1Right(assessmentTemplate.getUlnt1Right());
			assessmentTemplateForm.setUlnt1Left(assessmentTemplate.getUlnt1Left());
			assessmentTemplateForm.setUlnt2Right(assessmentTemplate.getUlnt2Right());
			assessmentTemplateForm.setUlnt2Left(assessmentTemplate.getUlnt2Left());
			assessmentTemplateForm.setUlnt3Right(assessmentTemplate.getUlnt3Right());
			assessmentTemplateForm.setUlnt3Left(assessmentTemplate.getUlnt3Left());
			assessmentTemplateForm.setJrActiveMoment(assessmentTemplate.getJrActiveMoment());
			assessmentTemplateForm.setJrPassiveMoment(assessmentTemplate.getJrPassiveMoment());
			assessmentTemplateForm.setMyotomes(assessmentTemplate.getMyotomes());
			assessmentTemplateForm.setDermatomes(assessmentTemplate.getDermatomes());
			assessmentTemplateForm.setSensoryLossChanges(assessmentTemplate.getSensoryLossChanges());
			assessmentTemplateForm.setKneeRight(assessmentTemplate.getKneeRight());
			assessmentTemplateForm.setKneeLeft(assessmentTemplate.getKneeLeft());
			assessmentTemplateForm.setAnkleRight(assessmentTemplate.getAnkleRight());
			assessmentTemplateForm.setAnkleLeft(assessmentTemplate.getAnkleLeft());
			assessmentTemplateForm.setBabinskiRight(assessmentTemplate.getBabinskiRight());
			assessmentTemplateForm.setBabinskiLeft(assessmentTemplate.getBabinskiLeft());
			assessmentTemplateForm.setSlumpRight(assessmentTemplate.getSlumpRight());
			assessmentTemplateForm.setSlumpLeft(assessmentTemplate.getSlumpLeft());
			assessmentTemplateForm.setDorsiflexionRom1(assessmentTemplate.getDorsiflexionRom1());
			assessmentTemplateForm.setDorsiflexionPain1(assessmentTemplate.getDorsiflexionPain1());
			assessmentTemplateForm.setDorsiflexionRom2(assessmentTemplate.getDorsiflexionRom2());
			assessmentTemplateForm.setDorsiflexionPain2(assessmentTemplate.getDorsiflexionPain2());
			assessmentTemplateForm.setMedicalRotaionRom1(assessmentTemplate.getMedicalRotaionRom1());
			assessmentTemplateForm.setMedicalRotaionPain1(assessmentTemplate.getMedicalRotaionPain1());
			assessmentTemplateForm.setMedicalRotaionRom2(assessmentTemplate.getMedicalRotaionRom2());
			assessmentTemplateForm.setMedicalRotaionPain2(assessmentTemplate.getMedicalRotaionPain2());
			assessmentTemplateForm.setNeckFlexionRom1(assessmentTemplate.getNeckFlexionRom1());
			assessmentTemplateForm.setNeckFlexionPain1(assessmentTemplate.getNeckFlexionPain1());
			assessmentTemplateForm.setNeckFlexionRom2(assessmentTemplate.getNeckFlexionRom2());
			assessmentTemplateForm.setNeckFlexionPain2(assessmentTemplate.getNeckFlexionPain2());
			assessmentTemplateForm.setPassiveKneeBendRom1(assessmentTemplate.getPassiveKneeBendRom1());
			assessmentTemplateForm.setPassiveKneeBendPain1(assessmentTemplate.getPassiveKneeBendPain1());
			assessmentTemplateForm.setPassiveKneeBendRom2(assessmentTemplate.getPassiveKneeBendRom2());
			assessmentTemplateForm.setPassiveKneeBendPain2(assessmentTemplate.getPassiveKneeBendPain2());
			assessmentTemplateForm.setDizzyy(assessmentTemplate.getDizzyy());
			assessmentTemplateForm.setDizzyn(assessmentTemplate.getDizzyn());
			assessmentTemplateForm.setDoubleVisiony(assessmentTemplate.getDoubleVisiony());
			assessmentTemplateForm.setDoubleVisionn(assessmentTemplate.getDoubleVisionn());
			assessmentTemplateForm.setDysarthriay(assessmentTemplate.getDysarthriay());
			assessmentTemplateForm.setDysarthrian(assessmentTemplate.getDysarthrian());
			assessmentTemplateForm.setDysphagiay(assessmentTemplate.getDysphagiay());
			assessmentTemplateForm.setDysphagian(assessmentTemplate.getDysphagian());
			assessmentTemplateForm.setDropAttacky(assessmentTemplate.getDropAttacky());
			assessmentTemplateForm.setDropAttackn(assessmentTemplate.getDropAttackn());
			assessmentTemplateForm.setNystagmusy(assessmentTemplate.getNystagmusy());
			assessmentTemplateForm.setNystagmusn(assessmentTemplate.getNystagmusn());
			assessmentTemplateForm.setNauseay(assessmentTemplate.getNauseay());
			assessmentTemplateForm.setNausean(assessmentTemplate.getNausean());
			assessmentTemplateForm.setNumbnessy(assessmentTemplate.getNumbnessy());
			assessmentTemplateForm.setNumbnessn(assessmentTemplate.getNumbnessn());
			
			assessmentTemplateForm.setMedication(assessmentTemplate.getMedication());
			assessmentTemplateForm.setInvestigation(assessmentTemplate.getInvestigation());
			assessmentTemplateForm.setGeneralHealth(assessmentTemplate.getGeneralHealth());
			
			int clientId = Integer.parseInt(assessmentTemplate.getClientId());
			int diaryUserId = Integer.parseInt(assessmentTemplate.getDiaryUserId());
			
			ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
			ArrayList<Client>conditionList = consultationNoteDAO.getConditionList(clientId);
			assessmentTemplateForm.setConditionList(conditionList);
			
			ArrayList<Client>clientList = consultationNoteDAO.getClientList(diaryUserId);
			assessmentTemplateForm.setClientList(clientList);
			 
			assessmentTemplateForm.setClient(Integer.toString(clientId));
			assessmentTemplateForm.setDiaryUser(assessmentTemplate.getDiaryUserId());
			assessmentTemplateForm.setCondition(assessmentTemplate.getConditionId());
			
			String imageData = assessmentTemplate.getImageData();
			session.setAttribute("imageData", imageData);
			
			assessmentTemplate = assessmentTemplateDAO.getGeneralHealthDetails(selectedId);
			
			assessmentTemplateForm.setDiabetesyn(assessmentTemplate.getDiabetesyn());
			assessmentTemplateForm.setDiabetesDetail(assessmentTemplate.getDiabetesDetail());
			assessmentTemplateForm.setEpilepsyyn(assessmentTemplate.getEpilepsyyn());
			assessmentTemplateForm.setEpilepsyDetail(assessmentTemplate.getEpilepsyDetail());
			assessmentTemplateForm.setHeartConditionyn(assessmentTemplate.getHeartConditionyn());
			assessmentTemplateForm.setHeartConditionDetail(assessmentTemplate.getHeartConditionDetail());
			assessmentTemplateForm.setLungDisorderyn(assessmentTemplate.getLungDisorderyn());
			assessmentTemplateForm.setLungDisorderDetail(assessmentTemplate.getLungDisorderDetail());
			assessmentTemplateForm.setProstateyn(assessmentTemplate.getProstateyn());
			assessmentTemplateForm.setProstateDetail(assessmentTemplate.getProstateDetail());
			assessmentTemplateForm.setCarcinomayn(assessmentTemplate.getCarcinomayn());
			assessmentTemplateForm.setCarcinomaDetail(assessmentTemplate.getCarcinomaDetail());
			assessmentTemplateForm.setRayn(assessmentTemplate.getRayn());
			assessmentTemplateForm.setRaDetail(assessmentTemplate.getRaDetail());
			assessmentTemplateForm.setOsteopaeniayn(assessmentTemplate.getOsteopaeniayn());
			assessmentTemplateForm.setOsteopaeniaDetail(assessmentTemplate.getOsteopaeniaDetail());
			assessmentTemplateForm.setWtStableyn(assessmentTemplate.getWtStableyn());
			assessmentTemplateForm.setWtStableDetail(assessmentTemplate.getWtStableDetail());
			assessmentTemplateForm.setBladderBowelyn(assessmentTemplate.getBladderBowelyn());
			assessmentTemplateForm.setBladderBowelDetail(assessmentTemplate.getBladderBowelDetail());
			assessmentTemplateForm.setOtheryn(assessmentTemplate.getOtheryn());
			assessmentTemplateForm.setOtherDetail(assessmentTemplate.getOtherDetail());		
			
			assessmentTemplateForm.setActionType(actionType);
			
			
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return "editPhysioTemplate";
		
	
	}
	
	public AssessmentTemplateForm getModel() {
		// TODO Auto-generated method stub
		return assessmentTemplateForm;
	}

	public void prepare() throws Exception {
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ImportImageForAssessmentDAO imageForAssessmentDAO = new JDBCImportImageAssessmentDAO(connection);
			ArrayList<Assessment> importImageList = imageForAssessmentDAO.getImportImageList(); 
			assessmentTemplateForm.setImportImageList(importImageList);
			
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);			
			ArrayList<DiaryManagement>userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
			assessmentTemplateForm.setUserList(userList);
			
			 ArrayList<Client>clientList = new ArrayList<Client>();
			 ArrayList<Client>conditionList = new ArrayList<Client>();
			 assessmentTemplateForm.setClientList(clientList);
			 assessmentTemplateForm.setConditionList(conditionList);
		
			}catch (Exception e) {
			
			}
		finally{
			connection.close();
		}
	}

}
