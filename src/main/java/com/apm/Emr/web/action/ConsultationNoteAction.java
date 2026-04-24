package com.apm.Emr.web.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.ChargesAccountProcessingDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCChargeAccountProcesDAO;
import com.apm.Appointment.eu.bi.AppointmentDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentDAO;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.AssesmentForms.eu.bi.AssessmentFormDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCAssessmentFormDAO;
import com.apm.AssesmentForms.eu.entity.Assessment;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Emr.eu.bi.ConsultationNoteDAO;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCConsultationNoteDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Emr.web.form.EmrForm;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Discharge;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.bi.ChargesReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCChargesReportDAO;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.common.web.utils.PopulateList;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.apm.Emr.eu.entity.Emr;

public class ConsultationNoteAction extends BaseAction implements  Preparable, ModelDriven<EmrForm>{
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	EmrForm emrForm = new EmrForm();
	public String execute(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
			ArrayList<Emr> consultationNoteList = new ArrayList<Emr>();
			emrForm.setConsultationList(consultationNoteList);
			session.setAttribute("consultationList", consultationNoteList);
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return "consultationNoteSucess";
	}
	
	
	public String setClientsajax(){
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			 
			
			int practId = Integer.parseInt(request.getParameter("practId"));
			
			
			ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
			 ArrayList<Client>clientList = consultationNoteDAO.getClientList(practId);
			

			 StringBuffer dropDownAjax = new StringBuffer();
				dropDownAjax.append("<select id='clientname' name = 'clientname' class='form-control showToolTip chosen' data-toggle='tooltip' onchange = 'setConditionajax(this.value)'>");
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

	public String setClients(){
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			 
			
			int practId = Integer.parseInt(request.getParameter("practId"));
			
			
			ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
			 ArrayList<Client>clientList = consultationNoteDAO.getClientList(practId);
			

			 StringBuffer dropDownAjax = new StringBuffer();
				dropDownAjax.append("<select id='clientname' name = 'clientname' class='form-control showToolTip chosen' data-toggle='tooltip' onchange = 'setCondition(this.value)'>");
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
	
	
	public String setClientConditionajax(){
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			 
			
			int clientId = Integer.parseInt(request.getParameter("clientId"));
			
			
			ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
			 ArrayList<Client>conditionList = consultationNoteDAO.getConditionList(clientId);
			

			 StringBuffer dropDownAjax = new StringBuffer();
				dropDownAjax.append("<select onchange='getPatientRecord()' id='condition'  name = 'condition' class='form-control showToolTip chosen' data-toggle='tooltip'>");
					dropDownAjax.append("<option value = '0'>Select Condtition </option>");
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
	
	public String setClientCondition(){
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			 
			
			int clientId = Integer.parseInt(request.getParameter("clientId"));
			
			
			ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
			 ArrayList<Client>conditionList = consultationNoteDAO.getConditionList(clientId);
			

			 StringBuffer dropDownAjax = new StringBuffer();
				dropDownAjax.append("<select id='condition'  name = 'condition' class='form-control showToolTip chosen' data-toggle='tooltip'>");
					dropDownAjax.append("<option value = '0'>Select Condtition </option>");
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
	
	public String showAll(){
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			String diaryUser = request.getParameter("practId");
			String clientname = request.getParameter("clientId");
			String condition = request.getParameter("conditionId");
			
		//	ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
		//	ArrayList<Emr> consultationNoteList = consultationNoteDAO.getConsultationNoteList(diaryUser,clientname,condition);
			setConsFormData(diaryUser,clientname,condition);

			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String redirect(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);

			String diaryUser = request.getParameter("practitionerId");
			String clientname = request.getParameter("clientId");
			int apmtId = Integer.parseInt(request.getParameter("appointId"));
			String condition = consultationNoteDAO.getCondtion(apmtId);
			 ArrayList<Client>clientList = consultationNoteDAO.getClientList(Integer.parseInt(diaryUser));

			 ArrayList<Client>conditionList = consultationNoteDAO.getConditionList(Integer.parseInt(clientname));

			 emrForm.setClientList(clientList);
			 emrForm.setConditionList(conditionList);
			 
			emrForm.setDiaryUser(diaryUser);
			emrForm.setClientname(clientname);
			emrForm.setCondition(condition);
		//	ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
		//	ArrayList<Emr> consultationNoteList = consultationNoteDAO.getConsultationNoteList(diaryUser,clientname,clientname);
			//setConsFormData(diaryUser,clientname,condition);
			ArrayList<Emr> consultationNoteList = consultationNoteDAO.getConsultationNoteList(diaryUser,clientname,condition);
			emrForm.setConsultationList(consultationNoteList);
			session.setAttribute("consultationList", consultationNoteList);

			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return "consultationNoteSucess";
	}
	public String save(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			String practId = request.getParameter("practId");
			String clientId = request.getParameter("clientId");
			String conditionId = request.getParameter("conditionId");
			String consNote = request.getParameter("consNote");
			ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
			int result = consultationNoteDAO.saveNote(practId,clientId,conditionId,consNote);
		
			setConsFormData(practId,clientId,conditionId);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String edit(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			connection=DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+loginInfo.getClinicUserid()+"?useUnicode=true&characterEncoding=UTF-8",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			String id = request.getParameter("id");
			
			ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
			String note = consultationNoteDAO.getNote(id);
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+note+""); 
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	public String update(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			String id = request.getParameter("id");
			String practId = request.getParameter("practId");
			String clientId = request.getParameter("clientId");
			String conditionId = request.getParameter("conditionId");
			String consNote = request.getParameter("consNote");
			ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
			int result = consultationNoteDAO.updateNote(practId,clientId,conditionId,consNote,id);
			setConsFormData(practId,clientId,conditionId);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public String delete(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			String id = request.getParameter("id");
			String practId = request.getParameter("practId");
			String clientId = request.getParameter("clientId");
			String conditionId = request.getParameter("conditionId");
			ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
			int result = consultationNoteDAO.deleteNote(id);
			setConsFormData(practId,clientId,conditionId);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	private void setConsFormData(String practId, String clientId, String conditionId) {
		Connection connection = null;
		
		try{ 
			
			connection = Connection_provider.getconnection();
			
			StringBuffer str = new StringBuffer();
			
			
			str.append("<table style='margin-left: 20px;'>");
			/*str.append("<thead>");
			str.append("<tr>");
			str.append("<th>History</th>");
			
			str.append("</tr>");
			str.append("</thead>");*/
			ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
			ArrayList<Emr> consultationNoteList = consultationNoteDAO.getConsultationNoteList(practId,clientId,conditionId);
			
			for(Emr emr2 : consultationNoteList){
				
				str.append("<tr>");
				str.append("<td style='width: 20%;'>"+emr2.getLastModified()+"</td>");	
				str.append("<td style='width: 20%;'>"+emr2.getPractitionerName()+"</td>");

				
				str.append("</tr>");
				
				str.append("<tr>");
				str.append("<td style='width: 20%;'>Client given massage.</td>");	
				str.append("<td style='width: 100%;'>"+emr2.getDescription()+"</td>"); 
				//str.append("<td style='width: 20%;'></td>");
				str.append("<td><a href ='#' onclick = 'editConsultationNote("+emr2.getId()+")' title = 'Edit' style = 'padding-right: 20px;'> <i class='fa fa-edit'></i> </a></td>");
				str.append("<td><a href ='#' onclick = 'deleteConsultationNote("+emr2.getId()+")' title = 'Delete'> <i class='fa fa-trash-o'></i> </a></td>");
				
				str.append("</tr>");
				str.append("<tr>");
				str.append("<td colspan = '5'><hr size='1' width='100%' style = 'margin-top: 10px;margin-bottom: 10px;border: 0;border-top: 1px solid #60CFD3;;'/></td>");
				str.append("</tr>");
			}
			
			str.append("</table>");			
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+""); 
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public EmrForm getModel() {
		// TODO Auto-generated method stub
		return emrForm;
	}
	

	public void prepare() throws Exception {
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			
			ArrayList<DiaryManagement>userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
			emrForm.setUserList(userList);
			ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);

			 ArrayList<Client>clientList = new ArrayList<Client>();
			 ArrayList<Client>conditionList = new ArrayList<Client>();
			 emrForm.setClientList(clientList);
			 emrForm.setConditionList(conditionList);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public String getPatientRecord() {

		if (!verifyLogin(request)) {
			return "login";
		}
		String clientId = emrForm.getClientname();
		session.removeAttribute("sessionselectedclientid");
		session.removeAttribute("consultationNoteList");
		emrForm.setDischargeOutcomeList(new ArrayList<Discharge>());
		emrForm.setDischargeStatusList(new ArrayList<Discharge>());
		emrForm.setHourList(PopulateList.hourList());
		emrForm.setMinuteList(PopulateList.getMinuteList());
		emrForm.setPkgsList(new ArrayList<Master>());
		emrForm.setInvSectionList(new ArrayList<Master>());
		emrForm.setInvsTypeList(new ArrayList<Master>());
		emrForm.setInvstUnitList(new ArrayList<Master>());
		emrForm.setMedicalRecordsTypeList(new ArrayList<Emr>());
		emrForm.setMedicalRecordsTypeList(new ArrayList<Emr>());
		emrForm.setImageDataList(new ArrayList<Assessment>());
		emrForm.setVideoList(new ArrayList<Emr>());
		emrForm.setParentPriscList(new ArrayList<Priscription>());
		emrForm.setMedicalRecordTypeList(new ArrayList<Emr>());
		emrForm.setSpecializationTemplateList(new ArrayList<Master>());
		emrForm.setMasterChageTypeList(new ArrayList<Master>());
		emrForm.setAdditionalChargeList(new ArrayList<AppointmentType>());
		emrForm.setDisciplineList(new ArrayList<Master>());
		emrForm.setProcedurelist(new ArrayList<Master>());
		
		if(!clientId.equals("")){
		if (!emrForm.getApmtId().equals("0")) {
			session.removeAttribute("sessionadmissionid");

		} else {
			session.removeAttribute("sessionemrapmtid");
		}

		if (!emrForm.getApmtId().equals("")) {
			session.setAttribute("sessionemrapmtid", emrForm.getApmtId());
		}
		String fromdate = emrForm.getFromDate();
		String todate = emrForm.getToDate();
		// if(fromdate.equals("")|| todate.equals("")){
		// fromdate=request.getParameter("fromDate");
		// todate=request.getParameter("toDate");
		// }
		if (fromdate == null) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			fromdate = dateFormat.format(cal.getTime());
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
		} else {
			if (fromdate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			} else {
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			}
		}
		if (todate == null) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			todate = dateFormat.format(cal.getTime());
			todate = DateTimeUtils.getCommencingDate1(todate);
		} else {
			if (todate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				todate = dateFormat.format(cal.getTime());
				todate = DateTimeUtils.getCommencingDate1(todate);
			} else {
				todate = DateTimeUtils.getCommencingDate1(todate);
			}
		}

		if (emrForm.getCaldate().equals("")) {
			String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
			String temp[] = currentDate.split(" ");

			String temp1[] = temp[0].split("-");
			String date = temp1[0] + "/" + temp1[1] + "/" + temp1[2];
			emrForm.setCommencing(date);
		} else {
			emrForm.setCommencing(emrForm.getCaldate());
		}

		String action = request.getParameter("action");
		// System.out.println(action);
		
		String practionerId = emrForm.getDiaryUser();
		String condition = emrForm.getCondition();
		if (practionerId != null) {
			if (practionerId.equals("undefined")) {
				practionerId = null;
			}
		}
		if (condition != null) {
			if (condition.equals("undefined")) {
				condition = null;
			}
		}
		if (emrForm.getClientname().equals("")) {
			/*
			 * Id = (String)session.getAttribute("clientId"); practionerId =
			 * (String)session.getAttribute("diaryUserId"); condition =
			 * (String)session.getAttribute("conditionId");
			 */
			if (clientId == null) {
				clientId = "";
			}
			emrForm.setApmtId("");
		}

		if (!emrForm.getConditionsApmt().equals("")) {
			condition = emrForm.getConditionsApmt();
			emrForm.setConditionsApmt("");
		}

		try {
			Connection connection = null;
			Client client = new Client();
			
			connection = Connection_provider.getconnection();
			ConsultationNoteDAO consultationNoteDAO = new JDBCConsultationNoteDAO(connection);
			ArrayList<Master> odmrprocedurelist=consultationNoteDAO.getodmrprocedureonly();
			emrForm.setMasterChageTypeList(odmrprocedurelist);
			AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
			ArrayList<AppointmentType>additionalChargeList = appointmentDAO.getAdditionalChaergeTypeList("");
			emrForm.setAdditionalChargeList(additionalChargeList);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			AccountsDAO accountsDAO=new JDBCAccountsDAO(connection); 
			EmrDAO emrDAO = new JDBCEmrDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			InvestigationDAO investigationDAO=new JDBCInvestigationDAO(connection);
			ArrayList<Discharge> dischargeOutcomeList = emrDAO.getDischrageOutcomeList();
			emrForm.setDischargeOutcomeList(dischargeOutcomeList);
			ArrayList<Discharge> dischargeStatusList = emrDAO.getDischrageStatusList();
			emrForm.setDischargeStatusList(dischargeStatusList);
			ArrayList<Master> pkgsList = investigationDAO.getInvPaksLists();
			emrForm.setPkgsList(new ArrayList<Master>());
			ArrayList<Master> invSectionList = investigationDAO.getInvestigationSectionList();
			emrForm.setInvSectionList(invSectionList);
			ArrayList<Master> invsTypeList = emrDAO.getInvesigationTypeList();
			emrForm.setInvsTypeList(invsTypeList);
			ArrayList<Master> invstUnitList = emrDAO.getInvstUnitList();
			emrForm.setInvstUnitList(invstUnitList);
			ArrayList<Master> specializationTemplateList = masterDAO.getMasterSpecializationList();
			emrForm.setSpecializationTemplateList(specializationTemplateList);
			ArrayList<Master> disciplineList  = masterDAO.getDisciplineDataListWithChecked();
			emrForm.setDisciplineList(disciplineList);
			ArrayList<Master>procedurelist=masterDAO.getprocedurelist();
			emrForm.setProcedurelist(procedurelist);
			
			
			String uhid=(String)session.getAttribute("appclientid");
			
			if(uhid!=null){
				int cid=clientDAO.getPureSevaClientid(uhid);
				clientId=""+cid;
				practionerId=clientDAO.getIpdPractionerIdonly(clientId);
				if(DateTimeUtils.numberCheck(practionerId).equals("0")){
					practionerId=clientDAO.getOpdPractionerId(clientId);
				}
			}else{
				session.removeAttribute("appclientid");
			}
			int checkconfidential = emrDAO.checkConfidentialEmr(clientId, practionerId, condition);
			emrForm.setCheckconfidential(checkconfidential);
			/*
			 * if(!clientId.equals("")){ client =
			 * clientDAO.getPatient(Integer.parseInt(clientId)); StringBuffer
			 * str = new StringBuffer();
			 * 
			 * 
			 * emrForm.setClient(client.getTitle()+" " +
			 * client.getFirstName()+" "+client.getLastName());
			 * emrForm.setClientData(client.getDob()+", "+client.getAddress()
			 * +"  "+client.getSecondLineaddress()+ " "+client.getTown()+
			 * " "+client.getPostCode()+" , "+client.getMobNo());
			 * emrForm.setClientname(clientId); }
			 */
			if (practionerId == null && clientId != null) {
				NotAvailableSlot notAvailableSlot = clientDAO.getLastAppointmentdetails(clientId);
				practionerId = Integer.toString(notAvailableSlot.getDiaryUserId());
				condition = notAvailableSlot.getCondition();
			}
			if(practionerId.equals("") && !clientId.equals("")){
				practionerId=clientDAO.getIpdPractionerId(clientId);
				if(DateTimeUtils.numberCheck(practionerId).equals("0")){
					practionerId=clientDAO.getOpdPractionerId(clientId);
				}
			}
			// set selected clientid from session
			if (session.getAttribute("sessionselectedclientid") != null && clientId.equals("")) {
				clientId = (String) session.getAttribute("sessionselectedclientid");
				NotAvailableSlot notAvailableSlot=new NotAvailableSlot();
				if(!clientId.equals("")) {
				notAvailableSlot = clientDAO.getLastAppointmentdetails(clientId);
				}
				if(DateTimeUtils.convertToInteger(notAvailableSlot.getIpdno())>0){
					BedDao bedDao= new JDBCBedDao(connection);
					Bed bed=bedDao.getEditIpdData(notAvailableSlot.getIpdno());
					practionerId = (bed.getPractitionerid());
					condition = bed.getConditionid();

				}else{
					practionerId = Integer.toString(notAvailableSlot.getDiaryUserId());
					condition = notAvailableSlot.getCondition();

				}
				
				if (session.getAttribute("docpractionerid") != null) {
					practionerId = (String) session.getAttribute("docpractionerid");
					condition = (String) session.getAttribute("doccondition");
				}

				if (session.getAttribute("diaryUserId") != null) {
					practionerId = (String) session.getAttribute("diaryUserId");
					condition = (String) session.getAttribute("conditionId");
				}

				if (condition != null) {

					if (condition.equals("")) {
						condition = null;
					}
				}

				if (condition == null) {

					IpdDAO ipdDAO = new JDBCIpdDAO(connection);
					BedDao bedDao = new JDBCBedDao(connection);
					if(!clientId.equals("")) {
					String ipdid = ipdDAO.getIpdId(clientId);

					Bed bed = bedDao.getEditIpdData(ipdid);
					condition = bed.getConditionid();
					practionerId = bed.getPractitionerid();
					}
				}
				if(!clientId.equals("")) {
				Client client1 = clientDAO.getSelectedSessionClientDetails(clientId);
				emrForm.setClient(client1.getClientName());
				}
				/*
				 * client = clientDAO.getPatient(Integer.parseInt(clientId));
				 * StringBuffer str = new StringBuffer();
				 * 
				 * emrForm.setClient(client.getTitle()+" " +
				 * client.getFirstName()+" "+client.getLastName());
				 * emrForm.setClientData(client.getDob()+", "+client.getAddress(
				 * )+"  "+client.getSecondLineaddress()+", "+client.getTown()
				 * +", "+client.getPostCode()+" , "+client.getMobNo());
				 * emrForm.setClientname(clientId);
				 */
			}

			emrForm.setDiaryUser(practionerId);
			// common condition
			UserProfile up=new UserProfile();
			if(practionerId!=null || !practionerId.equals("") ) {
			up = userProfileDAO.getUserprofileDetails(DateTimeUtils.convertToInteger(practionerId));
			condition = up.getDiciplineName();
			}
			if (emrForm.getApmtId().equals("")) {
				String apmtid = emrDAO.getLastAppointmentid(clientId, practionerId, condition);
				emrForm.setApmtId(apmtid);
			}

			String oldpractid = practionerId;
			// check if doctor placed with machine

			UserProfile userProfile = userProfileDAO.getUserprofileDetails(DateTimeUtils.convertToInteger(practionerId));

			if (DateTimeUtils.isNull(userProfile.getJobgroup()).equals("4")) {
				practionerId = userProfile.getDoctor();
				// common condition
				up = userProfileDAO.getUserprofileDetails(DateTimeUtils.convertToInteger(practionerId));
				condition = up.getDiciplineName();
			}
			if(!clientId.equals("")) {
			client = clientDAO.getPatient(Integer.parseInt(clientId));

			// for assesment form list @ jitu
			ArrayList<Assessment> assesmenttemplateNameList = assessmentFormDAO.getTemplateList();
			emrForm.setAssesmenttemplateNameList(assesmenttemplateNameList);

			StringBuffer str = new StringBuffer();
			emrForm.setAbrivationid(client.getAbrivationid());
			emrForm.setAgeandgender(client.getAge1()+" / "+client.getGender());
			emrForm.setClient(DateTimeUtils.getPatientFullname(client));
			emrForm.setClientData(client.getAddress() + "  " + client.getSecondLineaddress());

			if (client.getMobNo() == null) {
				client.setMobNo("");
			}
			emrForm.setMobNo(client.getMobNo());
			emrForm.setTown(client.getTown());
			emrForm.setCounty(client.getCounty());
			emrForm.setCountry(client.getCountry());
			emrForm.setPostcode(client.getPostCode());
//			if (client.getMobNo().equals("")) {
//				emrForm.setClientData(client.getAddress() + "  "
//						+ client.getSecondLineaddress() + ", " + client.getTown() + ", "+", "+ client.getCounty() + ", "+client.getCountry()+ " " + client.getPostCode() + " ");
//			}
			emrForm.setClientname(clientId);
			}
			
			ArrayList<Emr> consultationNoteList=new ArrayList<Emr>();
			if(!(practionerId.equals("")||clientId.equals(""))) {
			consultationNoteList = consultationNoteDAO.getConsultationNoteListwithDate(practionerId,
					clientId, condition, fromdate, todate,"",emrForm.getApmtId(),loginInfo);
			}
			emrForm.setConsultationList(consultationNoteList);
			session.setAttribute("consultationNoteList", consultationNoteList);
			String emrClientName = /*client.getTitle() + " " + client.getFirstName() + " " + client.getLastName()*/DateTimeUtils.getPatientFullname(client);
			session.setAttribute("emrClientName", emrClientName);
			emrForm.setDiaryUser(practionerId);
			ArrayList<Client> clientList = new ArrayList<Client>();
			if(DateTimeUtils.isNull(practionerId).equals("") || !practionerId.equals("0")) {
			clientList = consultationNoteDAO.getClientListEmr(Integer.parseInt(practionerId),
					clientId, oldpractid, emrForm.getApmtId());
			}
			emrForm.setClientList(clientList);
			ArrayList<Client> conditionList = new ArrayList<Client>();
			if(!clientId.equals("")) {
			conditionList = consultationNoteDAO.getConditionList(Integer.parseInt(clientId));
			}
			emrForm.setConditionList(conditionList);
			emrForm.setCondition(up.getDiciplineName());

			ArrayList<TreatmentEpisode> treatmentEpisodeList = new ArrayList<TreatmentEpisode>();
			if(!(practionerId.equals("0") && clientId.equals(""))){
			treatmentEpisodeList = emrDAO.getTreatmentEpisodeList(clientId, practionerId, condition);
			}
			emrForm.setTreatmentEpisodeList(treatmentEpisodeList);

			String treatmentEpisodeid = consultationNoteDAO.getTreatmentEpisodeid(emrForm.getApmtId());
			if (emrForm.getApmtId().equals("0")) {
				String sessionadmissionid = (String) session.getAttribute("sessionadmissionid");
				BedDao bedDao = new JDBCBedDao(connection);
				Bed bed = bedDao.getEditIpdData(sessionadmissionid);
				treatmentEpisodeid = bed.getTreatmentepisodeid();
			}
			emrForm.setTreatmentEpisodeid(treatmentEpisodeid);

			/*
			 * for(TreatmentEpisode t : treatmentEpisodeList){
			 * for(NotAvailableSlot n : t.getAppointmnetList()) {
			 * if(t.getTreatmentApmtCount() == 1 && n.getApmtCount() == 1){
			 * emrForm.setApmtId(Integer.toString(n.getId())); } } }
			 */

			// Documentation
			ArrayList<Emr> docList = emrDAO.getDocList(Integer.parseInt(clientId), Integer.parseInt(practionerId),
					loginInfo.getId(), Integer.parseInt(condition), emrForm.getFilterdoctType());
			emrForm.setDocList(docList);

			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			ArrayList<Bed> addmissionList = new ArrayList<Bed>();
			ArrayList<Bed> dischargeList = new ArrayList<Bed>();
			ArrayList<Bed> gynicFormList = new ArrayList<Bed>();
			ArrayList<Assessment> assessmentFormsList = new ArrayList<Assessment>();
			ArrayList<NotAvailableSlot> optionformlist = new ArrayList<NotAvailableSlot>();
			
			ArrayList<Client> declarationformlist = new ArrayList<Client>();
			//   14 mar 2018 added ot notes list in docs
			ArrayList<NotAvailableSlot> otformlist = new ArrayList<NotAvailableSlot>();
			if (emrForm.getFilterdoctType().equals("0")) {
				addmissionList = emrDAO.getIpdAdmissionList(clientId);
				dischargeList = emrDAO.getDischargeList(clientId);
				assessmentFormsList = emrDAO.getAssessmentList(clientId, practionerId, condition);
				optionformlist = notAvailableSlotDAO.getAllOptionFormList(clientId);
				if (loginInfo.isVermanh()) {
					gynicFormList = emrDAO.getNewGynicFormList(clientId);
				} else {
					gynicFormList = emrDAO.getGynicFormList(clientId);
				}

				declarationformlist = clientDAO.getDeclarationDataList(clientId);
				otformlist = notAvailableSlotDAO.getOTNotesFormList(clientId);
			} else {
				if (emrForm.getFilterdoctType().equals("Admission Form")) {
					addmissionList = emrDAO.getIpdAdmissionList(clientId);
					dischargeList = new ArrayList<Bed>();
					assessmentFormsList = new ArrayList<Assessment>();
					optionformlist = new ArrayList<NotAvailableSlot>();
					declarationformlist = new ArrayList<Client>();
					otformlist = new ArrayList<NotAvailableSlot>();
				}
				if (emrForm.getFilterdoctType().equals("Discharge Form")) {
					dischargeList = emrDAO.getDischargeList(clientId);
					addmissionList = new ArrayList<Bed>();
					assessmentFormsList = new ArrayList<Assessment>();
					optionformlist = new ArrayList<NotAvailableSlot>();
					declarationformlist = new ArrayList<Client>();
					otformlist = new ArrayList<NotAvailableSlot>();
				}
				if (emrForm.getFilterdoctType().equals("Assessment Report")) {
					assessmentFormsList = emrDAO.getAssessmentList(clientId, practionerId, condition);
					dischargeList = new ArrayList<Bed>();
					addmissionList = new ArrayList<Bed>();
					optionformlist = new ArrayList<NotAvailableSlot>();
					declarationformlist = new ArrayList<Client>();
					otformlist = new ArrayList<NotAvailableSlot>();
				}
				if (emrForm.getFilterdoctType().equals("Optional Form")) {
					assessmentFormsList = new ArrayList<Assessment>();
					dischargeList = new ArrayList<Bed>();
					addmissionList = new ArrayList<Bed>();
					optionformlist = notAvailableSlotDAO.getAllOptionFormList(clientId);
					declarationformlist = new ArrayList<Client>();
					otformlist = new ArrayList<NotAvailableSlot>();
				}
				if (emrForm.getFilterdoctType().equals("Declaration Form")) {
					assessmentFormsList = new ArrayList<Assessment>();
					dischargeList = new ArrayList<Bed>();
					addmissionList = new ArrayList<Bed>();
					optionformlist = new ArrayList<NotAvailableSlot>();
					declarationformlist = clientDAO.getDeclarationDataList(clientId);
					otformlist = new ArrayList<NotAvailableSlot>();

				}
				if (emrForm.getFilterdoctType().equals("OT Form")) {
					assessmentFormsList = new ArrayList<Assessment>();
					dischargeList = new ArrayList<Bed>();
					addmissionList = new ArrayList<Bed>();
					optionformlist = new ArrayList<NotAvailableSlot>();
					declarationformlist = new ArrayList<Client>();
					otformlist = notAvailableSlotDAO.getOTNotesFormList(clientId);

				}

			}

			emrForm.setAddmissionList(addmissionList);
			emrForm.setIpdsdischargeList(dischargeList);
			emrForm.setAssessmentFormsList(assessmentFormsList);
			emrForm.setOptionformlist(optionformlist);
			emrForm.setGynicFormList(gynicFormList);
			emrForm.setDeclarationformlist(declarationformlist);
			emrForm.setOtformlist(otformlist);
			// createPackImage();
			String ipdid=emrForm.getEmripdid();
			String finaldiagnosis="";
			if(DateTimeUtils.isNull(ipdid).equals("")){
				ipdid=accountsDAO.getIpdidofClient(clientId);
				if(ipdid.equals("0")){
					ipdid="";
				}
			}
			IpdDAO ipdDAO=new JDBCIpdDAO(connection);
			int fdipdid=ipdDAO.getLastIpdId(clientId);
			String id=accountsDAO.getFdID(fdipdid);
			if(id.equals("")){
				id=ipdDAO.getConditionidofipdid(ipdid);
				finaldiagnosis=accountsDAO.getIpdFinalDiagnosis(id);
			}else{
				finaldiagnosis=accountsDAO.getIpdFinalDiagnosis(id); 
			}
			
			emrForm.setFinaldiagnosis(finaldiagnosis);
			emrForm.setEmripdid(ipdid);
			ArrayList<Emr> medicalRecordsTypeList = emrDAO.getMedicalRecordTypeList(Integer.parseInt(clientId),
					Integer.parseInt(practionerId), loginInfo.getId(), condition);
			emrForm.setMedicalRecordsTypeList(medicalRecordsTypeList);

			ArrayList<Emr> medicalRecordTypeList = emrDAO.getMedicalRecordTypeList();
			emrForm.setMedicalRecordTypeList(medicalRecordTypeList);
			
			ArrayList<Assessment> imageDataList = emrDAO.getImageDataList(clientId, practionerId, condition);
			emrForm.setImageDataList(imageDataList);

			ArrayList<Emr> videoList = emrDAO.getVideoList(clientId, practionerId, condition);
			emrForm.setVideoList(videoList);

			// get repeat prescription list

			ArrayList<Priscription> parentPriscList = emrDAO.getParentPriscList(clientId, practionerId, condition);
			emrForm.setParentPriscList(parentPriscList);

			userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(practionerId));
			emrForm.setEmrClientName(emrClientName);
			String emrDoctorName = userProfile.getInitial() + " " + userProfile.getFirstname() + " "
					+ userProfile.getLastname();
			emrForm.setEmrDoctorName(emrDoctorName);
			String emrConditionName = emrDAO.getEmrConditionName(condition);
			emrForm.setEmrConditionName(emrConditionName);
			ChargesReportDAO chargesReportDAO=new JDBCChargesReportDAO(connection);
			emrForm.setDiciplineName(chargesReportDAO.getDepartmentName(DateTimeUtils.convertToInteger(userProfile.getDiciplineName())));
			// set opd condiiton list
			if (session.getAttribute("sessionemrapmtid") != null) {
				String apmtid = (String) session.getAttribute("sessionemrapmtid");
				emrForm.setApmtId(apmtid);
				String opdcondstr = emrDAO.getopdconditionList(apmtid, emrForm.getClientname());
				emrForm.setOpdchkcondition(opdcondstr);
				emrForm.setOdconditionstr(opdcondstr);
			}

			//   12 jan 2018
			if (practionerId == null) {
				ArrayList<Master> otherTemplateList = masterDAO.getEmrTemplateList(null);
				emrForm.setOtherTemplateList(otherTemplateList);
			} else {
				ArrayList<Master> otherTemplateList = masterDAO.getEmrTemplateList(userProfile.getDiciplineName());
				emrForm.setOtherTemplateList(otherTemplateList);
			}

			emrForm.setFromDate(DateTimeUtils.getCommencingDate1(fromdate));
			emrForm.setToDate(DateTimeUtils.getCommencingDate1(todate));
			//   11 April 2018
			ArrayList<Priscription> medicinetimelist = emrDAO.getMedicineTimeList();
			emrForm.setMedicinetimelist(medicinetimelist);
			ArrayList<Emr> opdemrloglist=emrDAO.getOpdEmrLogList(clientId);
//			ArrayList<Emr> ipdemrloglist=emrDAO.getIpdEmrLogList(clientId);
			emrForm.setOpdemrloglist(opdemrloglist);
//			emrForm.setIpdemrloglist(ipdemrloglist);
			
			//  05/10/2020 Showing Investigation and Priscription list 
			ArrayList<Client> vitallist=clientDAO.getVitalList(fromdate,todate,clientId);
			
			ArrayList<Emr> consultationNoteListInv = consultationNoteDAO.getConsultationNoteListwithDateInv(practionerId,
					clientId, condition, fromdate, todate,"");
			ArrayList<Emr> consultationNoteListPrisc = consultationNoteDAO.getConsultationNoteListwithDatePrisc(practionerId,
					clientId, condition, fromdate, todate,"");
			emrForm.setConsultationNoteListInv(consultationNoteListInv);
			emrForm.setConsultationNoteListPrisc(consultationNoteListPrisc);
			emrForm.setVitallist(vitallist);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		session.setAttribute("sessionselectedclientid", clientId);

		return "emrDetailsPage";
	}

}
