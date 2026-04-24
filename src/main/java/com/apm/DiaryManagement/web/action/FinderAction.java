package com.apm.DiaryManagement.web.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMessages;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.ActionError;

import com.a.a.a.b.i;
import com.apm.Appointment.eu.bi.AppointmentDAO;
import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.Appointment.eu.entity.Appointment;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.FinderDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCFinderDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.web.form.NotAvailableSlotForm;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Emr.eu.entity.Emr;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryVendorDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bams;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.SittingFollowupDAO;
import com.apm.Master.eu.bi.UnitDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCSittingFollowupDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCUnitDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.SittingFollowup;
import com.apm.Master.eu.entity.Unit;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.bi.ChargesReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCChargesReportDAO;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.Tools.web.action.AllTemplateAction;
import com.apm.TreatmentEpisode.eu.bi.TreatmentEpisodeDAO;
import com.apm.TreatmentEpisode.eu.blogic.jdbc.JDBCTreatmentEpisode;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.common.web.utils.PopulateList;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import atg.taglib.json.util.JSONObject;

public class FinderAction extends BaseAction implements Preparable, ModelDriven<NotAvailableSlotForm>{
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	NotAvailableSlotForm notAvailableSlotForm = new NotAvailableSlotForm();
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	Pagination pagination = new Pagination(25, 1);
	public Pagination getPagination() {
		return pagination;
	}
	public String execute() throws Exception {
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			FinderDAO finderDAO = new JDBCFinderDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			
			//set selected clientid from session
			/*if(session.getAttribute("sessionselectedclientid")!=null){*/
			
			notAvailableSlotForm.setClientId(DateTimeUtils.isNull(notAvailableSlotForm.getClientId()));
				if(notAvailableSlotForm.getClientId().equals("")){
					/*String clientid = (String)session.getAttribute("sessionselectedclientid");
					notAvailableSlotForm.setClientId(clientid);*/
					/*Client client = clientDAO.getSelectedSessionClientDetails(clientid);
					notAvailableSlotForm.setClient(client.getClientName());*/
				}
				
			/*}*/
			
			//if(!notAvailableSlotForm.getClientId().equals("")){
				String fromDate =notAvailableSlotForm.getFromDate() ;
				String todate=notAvailableSlotForm.getToDate();
				String searchtext=DateTimeUtils.isNull(notAvailableSlotForm.getSearchText());
				String actiontype=request.getParameter("IpdpatientType");
				String fakclientid=request.getParameter("fake_clientid");
				String fkclientname=request.getParameter("clientname");
				String appf=DateTimeUtils.isNull(request.getParameter("aptf"));
				//String ottype="0";
				String ottype=DateTimeUtils.isNull(notAvailableSlotForm.getAppointmentTypeTest());
				String action=DateTimeUtils.isNull(request.getParameter("action"));
				
				if(ottype.equals("2")) {
					appf="2";
					actiontype="2";
				}
				
				
				if (fromDate == null) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					fromDate = dateFormat.format(cal.getTime());
					fromDate = DateTimeUtils.getCommencingDate1(fromDate);
				} else {

					if (fromDate.equals("")) {
						DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						Calendar cal = Calendar.getInstance();
						fromDate = dateFormat.format(cal.getTime());
						fromDate = DateTimeUtils.getCommencingDate1(fromDate);
					} else {
						fromDate = DateTimeUtils.getCommencingDate1(fromDate);
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
				
				
				if(notAvailableSlotForm.getAppointment()==null){
					notAvailableSlotForm.setAppointment(new Appointment());
				}
				
				if(action.equals("fake")) {
					 String com=(String) session.getAttribute("commencingdte");
					 fromDate=com;
				 }
				
				String practionerId=DateTimeUtils.isNull(notAvailableSlotForm.getDiaryUser());
				String payee=DateTimeUtils.isNull(notAvailableSlotForm.getWithpayment());
				String fkdepartment=DateTimeUtils.isNull(notAvailableSlotForm.getDisciplineName());
				
				/*
				 * if(!fkdepartment.equals("")) {
				 * 
				 * String deptdate=finderDAO.getdepmnDate(fkdepartment);
				 * if(!deptdate.equals("")) { deptdate=deptdate.split(" ")[0]; }
				 * fromDate=deptdate; fromDate=DateTimeUtils.getCommencingDate2(fromDate);
				 * fromDate=fromDate+","+"00:00:00"+" "+"PM"; String
				 * ftodate=DateTimeUtils.calnewdate(fromDate, 120);
				 * ftodate=DateTimeUtils.getCommencingDate(ftodate);
				 * fromDate=fromDate.split(",")[0];
				 * fromDate=DateTimeUtils.getCommencingDate(fromDate); //todate=ftodate; }
				 */
				
				
				{
					if(!DateTimeUtils.isNull(notAvailableSlotForm.getAppointment().getAppointmentTypeTime()).equals("")){
						if(DateTimeUtils.isNull(notAvailableSlotForm.getAppointment().getAppointmentTypeTime()).equals("1")){
							//past
							DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
							Calendar cal2 = Calendar.getInstance();
							cal2.add(Calendar.DATE, -7);
							fromDate = dateFormat2.format(cal2.getTime());
							todate=dateFormat2.format(new Date());
						}else{
							//future
							DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
							Calendar cal2 = Calendar.getInstance();
							cal2.add(Calendar.DATE, 7);
							 todate= dateFormat2.format(cal2.getTime());
							 fromDate=dateFormat2.format(new Date());
						}
					}
					
					// summaryReportForm.setFromDate(fromDate);
					
				}
			  if(appf!=null) {
				if(appf.equals("2")) {
					if(actiontype.equals("2")) {
						 String dropdwnotdate=finderDAO.getotDatebydropdown();
						 fromDate=dropdwnotdate;
						 fromDate=DateTimeUtils.getCommencingDate2(fromDate);
						 fromDate=fromDate+","+"00:00:00"+" "+"PM";
						 String ftodate=DateTimeUtils.calnewdate(fromDate, 120);
						 ftodate=DateTimeUtils.getCommencingDate(ftodate);
						 fromDate=DateTimeUtils.getCommencingDate1(fromDate);
						 todate=ftodate;
					}else {
						String otdate=finderDAO.getfOTdatebyClientid(fakclientid);
						fromDate=otdate;
						searchtext=fakclientid;
					}
					
				}
			  }
				
			 if(appf.equals("1")){
			  if(actiontype.equals("1")) {
				String fdate=finderDAO.getfakedatebyClientid(fakclientid);
				if(fdate!=null) {
					fdate=fdate.split(" ")[0];
				}
				
				if(fdate!=null) {
				  if(actiontype.equals("1")){
					 
					 fromDate=fdate;
					 fromDate=DateTimeUtils.getCommencingDate2(fromDate);
					 fromDate=fromDate+","+"00:00:00"+" "+"PM";
					 String ftodate=DateTimeUtils.calnewdate(fromDate, 120);
					 ftodate=DateTimeUtils.getCommencingDate(ftodate);
					 fromDate=fromDate.split(" ")[0];
					 fromDate=DateTimeUtils.getCommencingDate(fromDate);
					 searchtext=fakclientid;
					 todate=ftodate;
				}
			  }
				
			}
		 }
				String apptype="";
				if(DateTimeUtils.isNull(notAvailableSlotForm.getApmtType()).equals("1")){
					apptype="1";
				}else if (DateTimeUtils.isNull(request.getParameter("status")).equals("2")) {
					apptype="2";
				}
				else{
				apptype=DateTimeUtils.isNull(notAvailableSlotForm.getAppointmentTypeTest());
				}
				ArrayList<NotAvailableSlot>fakedepartmentlist = finderDAO.getconsultDepartmentList();
				notAvailableSlotForm.setFakeconsultdeptlist(fakedepartmentlist);
				
				ArrayList<NotAvailableSlot>finderList = finderDAO.getnewFinderList(notAvailableSlotForm.getClientId(),fromDate,todate,practionerId,payee,notAvailableSlotForm.getAppointment(),apptype,notAvailableSlotForm.getApmtType(),searchtext,appf,fkdepartment);
				notAvailableSlotForm.setFinderList(finderList);
				
				/*
				 * session.setAttribute("clientid", notAvailableSlotForm.getClientId());
				 * session.setAttribute("client", notAvailableSlotForm.getClient());
				 */
				
				Client client = clientDAO.getSelectedSessionClientDetails(notAvailableSlotForm.getClientId());
				notAvailableSlotForm.setClient(client.getClientName());
			//}
			notAvailableSlotForm.setAppointmentTypeTest(apptype);
			notAvailableSlotForm.setFromDate(DateTimeUtils.getCommencingDate1(fromDate));
			notAvailableSlotForm.setToDate(DateTimeUtils.getCommencingDate1(todate));
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return SUCCESS;
	}

	
	public String input() throws Exception {
		if(!verifyLogin(request)){
			return "login";
		}
		
		return execute();
	}
	
	


	public NotAvailableSlotForm getModel() {
		// TODO Auto-generated method stub
		return notAvailableSlotForm;
	}
	
	public String delete() throws Exception{
		
	/*	if(!verifyLogin(request)){
			return "login";
		}
		
		
		//int id = notAvailableSlotForm.getId();
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			
			int del = notAvailableSlotDAO.DeleteBlockedSlot(Integer.toString(notAvailableSlotForm.getId()));
			
			FinderDAO finderDAO = new JDBCFinderDAO(connection);
			
			String clientid = (String)session.getAttribute("clientid");
			String client = (String)session.getAttribute("client");
			notAvailableSlotForm.setClient(client);
			notAvailableSlotForm.setClientId(clientid);
			
			ArrayList<NotAvailableSlot>finderList = finderDAO.getFinderList(clientid);
			notAvailableSlotForm.setFinderList(finderList);
			
			addActionMessage("Appointment Deleted Successfully!!");*/
		
		
		if(!verifyLogin(request)){
			return "login";
		}
		String selectedid = Integer.toString(notAvailableSlotForm.getId());
		String cancelApmtNote = notAvailableSlotForm.getCancelApmtNote();
		Connection connection = null;
		//current date and time in dd/mm/yyyy
				String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
				String datetemp[] = currentDate.split(" ");
				String temp1[] = datetemp[0].split("-");
				String date = temp1[0] + "/" + temp1[1] + "/" + temp1[2];
				String time = (datetemp[2]+" "+datetemp[3]);
				
				

		try{
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			int status = notAvailableSlotDAO.getStatus(selectedid,"opd");
			if(status==0){
				int treatmeId = notAvailableSlotDAO.getSelecedTreatmentEpisodeId(Integer.parseInt(selectedid));
				int usedSession = notAvailableSlotDAO.getUsedSession(treatmeId,selectedid);
				ArrayList<NotAvailableSlot>usedSessionList = new ArrayList<NotAvailableSlot>();
				usedSessionList = notAvailableSlotDAO.getUsedSessionList(treatmeId,usedSession);
				
				
				for(NotAvailableSlot n:usedSessionList){
					int updateAllPrevious = notAvailableSlotDAO.updateAllPrevious(n.getId(),treatmeId);
				}
				int updatesession = notAvailableSlotDAO.updateSessions(treatmeId);
			}
			NotAvailableSlot n = notAvailableSlotDAO.getApmtDetailsForLog(Integer.parseInt(selectedid));
			
			String commencingTemp = n.getCommencing();
			
			String apmtstatus = "Cancelled";
			
			int logsave = notAvailableSlotDAO.saveCancelApmtInLog(Integer.parseInt(selectedid),DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),time,loginInfo.getUserId(),n.getClientId(),commencingTemp,n.getSTime(),apmtstatus,cancelApmtNote,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),n);
			
			  //send sms
		    ClientDAO clientDAO = new JDBCClientDAO(connection);
		    NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getApmtDetailsForLog(Integer.parseInt(selectedid));
		    UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		    Client clients = clientDAO.getClientDetails(notAvailableSlot.getClientId());
		    UserProfile userProfile  = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());
		   
		    String message = AllTemplateAction.getDeletedAppointmentSMSText(notAvailableSlot.getClientId(), Integer.parseInt(selectedid), connection, loginInfo);
		    String templateid="";
		    if(loginInfo.getCountry().equals("India")){
		    	SmsService s = new SmsService();
		    	s.sendSms(message, clients.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
		    	s.sendSms(message, userProfile.getMobile(), loginInfo, new EmailLetterLog(),templateid);
		    }
			 
			 
			int result = notAvailableSlotDAO.DeleteBlockedSlot(selectedid,"opd");
			
			notAvailableSlotForm.setMessage("Appointment Deleted Successfully!!");
			addActionMessage("Appointment Deleted Successfully!!");
			
			clientDAO = new JDBCClientDAO(connection);
			
			Client client = clientDAO.getSelectedSessionClientDetails(notAvailableSlotForm.getClientId());
			notAvailableSlotForm.setClient(client.getClientName());
			
			boolean isapmtexist = notAvailableSlotDAO.checkApmtExist(n.getClientId());
			if(isapmtexist){
				int sts = 1;
				int updp = notAvailableSlotDAO.updateNewPtsStatus(n.getClientId(),sts);
			}else{
				int sts = 0;
				int updp = notAvailableSlotDAO.updateNewPtsStatus(n.getClientId(),sts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
			
		return execute();
	}


	
	public String deletenew() throws Exception{
		
		
			
			if(!verifyLogin(request)){
				return "login";
			}
			String selectedid = DateTimeUtils.isNull(request.getParameter("apmtId"));
			String cancelApmtNote =  DateTimeUtils.isNull(request.getParameter("cancelnote"));
			Connection connection = null;
			//current date and time in dd/mm/yyyy
					String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
					String datetemp[] = currentDate.split(" ");
					String temp1[] = datetemp[0].split("-");
					String date = temp1[0] + "/" + temp1[1] + "/" + temp1[2];
					String time = (datetemp[2]+" "+datetemp[3]);
					
					

			try{
				connection = Connection_provider.getconnection();
				NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
				int status = notAvailableSlotDAO.getStatus(selectedid,"opd");
				if(status==0){
					int treatmeId = notAvailableSlotDAO.getSelecedTreatmentEpisodeId(Integer.parseInt(selectedid));
					int usedSession = notAvailableSlotDAO.getUsedSession(treatmeId,selectedid);
					ArrayList<NotAvailableSlot>usedSessionList = new ArrayList<NotAvailableSlot>();
					usedSessionList = notAvailableSlotDAO.getUsedSessionList(treatmeId,usedSession);
					
					
					for(NotAvailableSlot n:usedSessionList){
						int updateAllPrevious = notAvailableSlotDAO.updateAllPrevious(n.getId(),treatmeId);
					}
					int updatesession = notAvailableSlotDAO.updateSessions(treatmeId);
				}
				NotAvailableSlot n = notAvailableSlotDAO.getApmtDetailsForLog(Integer.parseInt(selectedid));
				
				String commencingTemp = n.getCommencing();
				
				String apmtstatus = "Cancelled";
				
				int logsave = notAvailableSlotDAO.saveCancelApmtInLog(Integer.parseInt(selectedid),DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),time,loginInfo.getUserId(),n.getClientId(),commencingTemp,n.getSTime(),apmtstatus,cancelApmtNote,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),n);
				
				  //send sms
			    ClientDAO clientDAO = new JDBCClientDAO(connection);
			    NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getApmtDetailsForLog(Integer.parseInt(selectedid));
			    UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			    Client clients = clientDAO.getClientDetails(notAvailableSlot.getClientId());
			    UserProfile userProfile  = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());
			   
			    String message = AllTemplateAction.getDeletedAppointmentSMSText(notAvailableSlot.getClientId(), Integer.parseInt(selectedid), connection, loginInfo);
			    String templateid="";
			    if(loginInfo.getCountry().equals("India")){
			    	SmsService s = new SmsService();
			    	s.sendSms(message, clients.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
			    	s.sendSms(message, userProfile.getMobile(), loginInfo, new EmailLetterLog(),templateid);
			    }
				 
				 
				int result = notAvailableSlotDAO.DeleteBlockedSlot(selectedid,"opd");
				
				
				clientDAO = new JDBCClientDAO(connection);
				
				Client client = clientDAO.getSelectedSessionClientDetails(notAvailableSlotForm.getClientId());
				notAvailableSlotForm.setClient(client.getClientName());
				
				boolean isapmtexist = notAvailableSlotDAO.checkApmtExist(n.getClientId());
				if(isapmtexist){
					int sts = 1;
					int updp = notAvailableSlotDAO.updateNewPtsStatus(n.getClientId(),sts);
				}else{
					int sts = 0;
					int updp = notAvailableSlotDAO.updateNewPtsStatus(n.getClientId(),sts);
				}
				
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("");
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				
				connection.close();
			}
			
			return null;
		}
	
	public void prepare() throws Exception {
		Connection connection = null;
		
		ArrayList<Client> thirdPartyTypeList = new ArrayList<Client>();
		ArrayList<Client> thirdPartyTypeNameList = new ArrayList<Client>();
		ArrayList<Client> clientOccupationList = new ArrayList<Client>();
		ArrayList<Client> refrenceList = new ArrayList<Client>();
		ArrayList<String> initialList = new ArrayList<String>();
		ArrayList<Client> sourceOfIntroList = new ArrayList<Client>();
		
		ArrayList<Client> condtitionList = new ArrayList<Client>();
		ArrayList<Client> gpList = new ArrayList<Client>();
		ArrayList<Client> surgeryList = new ArrayList<Client>();
		try{
			
			connection = Connection_provider.getconnection();
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			
			gpList = clientDAO.getGpList();
			notAvailableSlotForm.setGpList(gpList);
			condtitionList = clientDAO.getTreatmentTypeList();
			notAvailableSlotForm.setCondtitionList(condtitionList);
			
			
			thirdPartyTypeList = clientDAO.getThirdPartyType();
			notAvailableSlotForm.setThirdPartyTypeList(thirdPartyTypeList);
			
			thirdPartyTypeNameList = clientDAO.getThirdPartyTypeName();
			notAvailableSlotForm.setThirdPartyTypeNameList(thirdPartyTypeNameList);
			
			clientOccupationList = clientDAO.getOccupationList();
			
			Client client = new Client();
			client.getOther();
			clientOccupationList.add(client);
			notAvailableSlotForm.setClientOccupationList(clientOccupationList);
			
			surgeryList = clientDAO.getSurgeryList();
			Client client3 = new Client();
			client3.getOther();
			surgeryList.add(client3);
			notAvailableSlotForm.setSurgeryList(surgeryList);

			
			refrenceList = clientDAO.getReferenceList();
			Client client2 = new Client();
			client2.getOther();
			refrenceList.add(client2);
			notAvailableSlotForm.setRefrenceList(refrenceList);
			
			initialList = clientDAO.getInitialList();
			notAvailableSlotForm.setInitialList(initialList);
			
			sourceOfIntroList = clientDAO.getSourceOfIntroList();
			notAvailableSlotForm.setSourceOfIntroList(sourceOfIntroList);
			
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			
			ArrayList<Location>locationList = notAvailableSlotDAO.getLocationList(loginInfo.getId());
			notAvailableSlotForm.setLocationList(locationList);
			
			ArrayList<AppointmentType>appointmentTypeList = notAvailableSlotDAO.getAppointmentTypeList();
			notAvailableSlotForm.setAppointmentTypeList(appointmentTypeList);
			
			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
			ArrayList<TreatmentEpisode> sourceOfReferralList = treatmentEpisodeDAO.getSourceOfReferralList();
			notAvailableSlotForm.setSourceOfReferralList(sourceOfReferralList);
			
			notAvailableSlotForm.setCountry("United Kingdom");
			
			
			notAvailableSlotForm.setCountryList(PopulateList.countryList());
			
			
			notAvailableSlotForm.setStartTimeList(PopulateList.startTimeList());
			notAvailableSlotForm.setEndTimeList(PopulateList.endTimeList());
			notAvailableSlotForm.setApmtDurationList(PopulateList.apmtDurationList());
			notAvailableSlotForm.setApmtBlockDurationList(PopulateList.apmBlocktDurationList());
			
			ArrayList<DiaryManagement>userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
			notAvailableSlotForm.setUserList(userList);
			
			ArrayList<String>weekNameList = new ArrayList<String>();
			weekNameList.add("Monday");
			weekNameList.add("Tuesday");
			weekNameList.add("Wednesday");
			weekNameList.add("Thursday");
			weekNameList.add("Friday");
			weekNameList.add("Saturday");
			weekNameList.add("Sunday");
			
			notAvailableSlotForm.setWeekNameList(weekNameList);
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			connection.close();
		}
		
		

		
	}
	public String sendfollowsms() throws Exception{
		Connection connection = null;
		try {
			String clientid=request.getParameter("clientid");
			String message=request.getParameter("msg");
			connection = Connection_provider.getconnection();
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			Client client=clientDAO.getClientDetails(clientid);
			SmsService s = new SmsService();
			String templateid="";
	    	s.sendSms(message, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
	    	response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("0");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	public String vaccinationdashboard() throws Exception{ 
	if(!verifyLogin(request)){
		return "login";
	}
	
	
	Connection connection = null;
	try{
		
		connection = Connection_provider.getconnection();
		String fromDate = notAvailableSlotForm.getFromDate();
		String toDate = notAvailableSlotForm.getToDate();	
		String nduserid=DateTimeUtils.isNull(notAvailableSlotForm.getDiaryUser());
		NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
		if (fromDate == null) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			fromDate = dateFormat.format(cal.getTime());
			fromDate = DateTimeUtils.getCommencingDate1(fromDate);
		} else {

			if (fromDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromDate = dateFormat.format(cal.getTime());
				fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			} else {
				fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			}
		}

		if (toDate == null) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			toDate = dateFormat.format(cal.getTime());
			toDate = DateTimeUtils.getCommencingDate1(toDate);
		} else {
			if (toDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				toDate = dateFormat.format(cal.getTime());
				toDate = DateTimeUtils.getCommencingDate1(toDate);
			} else {
				toDate = DateTimeUtils.getCommencingDate1(toDate);
			}
		}
		
		String uhid=request.getParameter("uhid");
		String serachText="";
	    if(uhid !=null ) {
	     serachText=uhid;
	    }else {
		 serachText = notAvailableSlotForm.getSearchText();
	    }
		ArrayList<NotAvailableSlot>opdlist =new ArrayList<NotAvailableSlot>();
		serachText = DateTimeUtils.isNull(serachText);
		/*if(loginInfo.isSjivh()) {
		opdlist = notAvailableSlotDAO.vaccinationListOfPatientsvet(fromDate,toDate,notAvailableSlotForm.getVaccineType(),notAvailableSlotForm.getVaccineMasterId(),notAvailableSlotForm.getClientId(),notAvailableSlotForm.getStatus(),serachText);
	
		}else {*/
		opdlist = notAvailableSlotDAO.vaccinationListOfPatients(fromDate,toDate,notAvailableSlotForm.getVaccineType(),notAvailableSlotForm.getVaccineMasterId(),notAvailableSlotForm.getClientId(),notAvailableSlotForm.getStatus(),serachText);
		/* } */
		notAvailableSlotForm.setVaccinationlist(opdlist);
		notAvailableSlotForm.setClientId("");
		
		MasterDAO masterDAO = new JDBCMasterDAO(connection);
		/*ClientDAO clientDAO= new JDBCClientDAO(connection);
		notAvailableSlotForm.setCondtitionList(clientDAO.getAllPatient());*/
		notAvailableSlotForm.setVaccineMasterList(masterDAO.getallvaccinations());
		notAvailableSlotForm.setFromDate( DateTimeUtils.getCommencingDate1(fromDate));
		notAvailableSlotForm.setToDate(DateTimeUtils.getCommencingDate1(toDate));
		
		AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
		ArrayList<AppointmentType>additionalChargeList = appointmentDAO.getAdditionalChaergeTypeList("");
		notAvailableSlotForm.setAdditionalChargeList(additionalChargeList);
		
		ArrayList<Master>masterChageTypeList = appointmentDAO.getmasterChageTypeList(loginInfo);
		notAvailableSlotForm.setMasterChageTypeList(masterChageTypeList);
		notAvailableSlotForm.setMasterchargetype("Additional Charge");
	}catch (Exception e) {
		e.printStackTrace();
	}
	return "vaccinationdashboard";
	
	}
	
	
	
	//code for veterinary hospital medicine
	
	public String dewormingdashboard() throws Exception{ 
	if(!verifyLogin(request)){
		return "login";
	}
	
	
	Connection connection = null;
	try{
		
		connection = Connection_provider.getconnection();
		String fromDate = notAvailableSlotForm.getFromDate();
		String toDate = notAvailableSlotForm.getToDate();	
		String nduserid=DateTimeUtils.isNull(notAvailableSlotForm.getDiaryUser());
		NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
		if (fromDate == null) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			fromDate = dateFormat.format(cal.getTime());
			fromDate = DateTimeUtils.getCommencingDate1(fromDate);
		} else {

			if (fromDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromDate = dateFormat.format(cal.getTime());
				fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			} else {
				fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			}
		}

		if (toDate == null) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			toDate = dateFormat.format(cal.getTime());
			toDate = DateTimeUtils.getCommencingDate1(toDate);
		} else {
			if (toDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				toDate = dateFormat.format(cal.getTime());
				toDate = DateTimeUtils.getCommencingDate1(toDate);
			} else {
				toDate = DateTimeUtils.getCommencingDate1(toDate);
			}
		}
		
		String uhid=request.getParameter("uhid");
		String serachText="";
	    if(uhid !=null ) {
	     serachText=uhid;
	    }else {
		 serachText = notAvailableSlotForm.getSearchText();
	    }
		ArrayList<NotAvailableSlot>medicinelistvet =new ArrayList<NotAvailableSlot>();
		serachText = DateTimeUtils.isNull(serachText);
		if(loginInfo.isSjivh()) {
			medicinelistvet = notAvailableSlotDAO.vaccinationListOfPatientsvet(fromDate,toDate,notAvailableSlotForm.getVaccineType(),notAvailableSlotForm.getVaccineMasterId(),notAvailableSlotForm.getClientId(),notAvailableSlotForm.getStatus(),serachText);
	     }
		notAvailableSlotForm.setMedicinelistvet(medicinelistvet);
		notAvailableSlotForm.setClientId("");
		
		MasterDAO masterDAO = new JDBCMasterDAO(connection);
		/*ClientDAO clientDAO= new JDBCClientDAO(connection);
		notAvailableSlotForm.setCondtitionList(clientDAO.getAllPatient());*/
		notAvailableSlotForm.setVaccineMasterList(masterDAO.getallveterinarymedicine());
		notAvailableSlotForm.setFromDate( DateTimeUtils.getCommencingDate1(fromDate));
		notAvailableSlotForm.setToDate(DateTimeUtils.getCommencingDate1(toDate));
		
		AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
		ArrayList<AppointmentType>additionalChargeList = appointmentDAO.getAdditionalChaergeTypeList("");
		notAvailableSlotForm.setAdditionalChargeList(additionalChargeList);
		
		ArrayList<Master>masterChageTypeList = appointmentDAO.getmasterChageTypeList(loginInfo);
		notAvailableSlotForm.setMasterChageTypeList(masterChageTypeList);
		notAvailableSlotForm.setMasterchargetype("Additional Charge");
	}catch (Exception e) {
		e.printStackTrace();
	}
	return "dewormingdashboard";
	
	}
	
	
	public String departmentopd() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		
		try{
			
			connection = Connection_provider.getconnection();
			FinderDAO finderDAO = new JDBCFinderDAO(connection);
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			SittingFollowupDAO sittingDAO=new JDBCSittingFollowupDAO(connection);
			AppointmentTypeDAO appointmentTypeDAO=new JDBCAppointmentTypeDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
			//String specialized=profileDAO.getSpeciality(loginInfo.getUserId());
			
			ArrayList<Master> disciplineList = new ArrayList<Master>();
			ArrayList<DiaryManagement>userList=new ArrayList<DiaryManagement>();
			
			String department=DateTimeUtils.isNull(notAvailableSlotForm.getDept());
			String date = DateTimeUtils.isNull(notAvailableSlotForm.getFromDate()) ;
			String category=DateTimeUtils.numberCheck(notAvailableSlotForm.getPatientcategory());
			String orderby=DateTimeUtils.isNull(notAvailableSlotForm.getOrderby());
			String primarydoc=DateTimeUtils.isNull(notAvailableSlotForm.getDiaryUser());
			/*String dicipline = loginInfo.getDicipline();
			dicipline = DateTimeUtils.numberCheck(dicipline);
			ArrayList<Master> referDepartList = masterDAO.getDisciplineWithChecked(dicipline);
			notAvailableSlotForm.setReferDepartList(referDepartList);*/
			
			if ( loginInfo.getSuperadminid()==1 || loginInfo.getUserType()==2 || !loginInfo.getShow_dept_opd_list().equals("0") ) {
				disciplineList = masterDAO.getDisciplineDataListWithChecked();
			}else if(loginInfo.getShow_dept_opd_list().equals("0")) {
				UserProfile userProfile=userProfileDAO.getUserprofileDetails(loginInfo.getId());
				department=userProfile.getDiciplineName();
				notAvailableSlotForm.setDept(department);		
				disciplineList = masterDAO.getDisciplineDataListWithChecked();
			}
			if(orderby.equals("")) {
				orderby="desc";
			}
			
			   if(!department.equals("")){
					userList=notAvailableSlotDAO.getUserListwithdepartment(loginInfo.getId(),department);
				}else {
					userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
				}
				notAvailableSlotForm.setUserList(userList);
				notAvailableSlotForm.setDisciplineList(disciplineList);
				
				if (date == null) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					date = dateFormat.format(cal.getTime());
					date = DateTimeUtils.getCommencingDate1(date);
				}else {

					if (date.equals("")) {
						DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						Calendar cal = Calendar.getInstance();
						date = dateFormat.format(cal.getTime());
						date = DateTimeUtils.getCommencingDate1(date);
					} else {
						date = DateTimeUtils.getCommencingDate1(date);
					}
			   }
                //for vspm backdate sitting @rahul
				String sittingbackdate=notAvailableSlotForm.getFromDate();
				if (sittingbackdate == null) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					sittingbackdate = dateFormat.format(cal.getTime());
					sittingbackdate = DateTimeUtils.getCommencingDate1(sittingbackdate);
				} else {

					if (sittingbackdate.equals("")) {
						DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						Calendar cal = Calendar.getInstance();
						sittingbackdate = dateFormat.format(cal.getTime());
						sittingbackdate = DateTimeUtils.getCommencingDate1(sittingbackdate);
					} else {
						sittingbackdate = DateTimeUtils.getCommencingDate1(sittingbackdate);
					}
				}
				/*ArrayList<Master> deptWiseCountList = masterDAO.getDepartmentWisePatientCount(date);
				int finalNewPatientCount=0;
				int finalOldPatientCount=0;
				int finalTotalPatientCount=0;
				
				if(deptWiseCountList.size()>0){
					finalNewPatientCount = deptWiseCountList.get(deptWiseCountList.size()-1).getFinalNewPatientCount();
					finalOldPatientCount = deptWiseCountList.get(deptWiseCountList.size()-1).getFinalOldPatientCount();
					finalTotalPatientCount = deptWiseCountList.get(deptWiseCountList.size()-1).getFinalTotalPatientCount();
				}
				notAvailableSlotForm.setDeptWiseCountList(deptWiseCountList);
				notAvailableSlotForm.setFinalNewPatientCount(finalNewPatientCount);
				notAvailableSlotForm.setFinalOldPatientCount(finalOldPatientCount);
				notAvailableSlotForm.setFinalTotalPatientCount(finalTotalPatientCount);*/
				
				int count = finderDAO.getcountOfDepartmentOPD(department,date,category,orderby);
				pagination.setPreperties(count);
				ArrayList<NotAvailableSlot>departmentOPDList = finderDAO.departmentOPDList(department,date,pagination,category,orderby,loginInfo,primarydoc);
				pagination.setTotal_records(departmentOPDList.size());
				notAvailableSlotForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
				notAvailableSlotForm.setTotalRecords(count);
				
				notAvailableSlotForm.setDepartmentOPDList(departmentOPDList);
				notAvailableSlotForm.setSelectdepartment(department);
				notAvailableSlotForm.setFromDate(DateTimeUtils.getCommencingDate1(date));
				notAvailableSlotForm.setPatientcategory(category);
				notAvailableSlotForm.setOrderby(orderby);
			    notAvailableSlotForm.setSittingbackdate(DateTimeUtils.getCommencingDate1(sittingbackdate));
				
				/*ArrayList<NotAvailableSlot> additionalChargeList = finderDAO.getAdditionalChargeTypeList();
				notAvailableSlotForm.setAdditionalChargeListNew(additionalChargeList);*/
				
				ArrayList<NotAvailableSlot> tpNameList = finderDAO.gettpNameListAll();
				notAvailableSlotForm.setTpNameList(tpNameList);
				
				ArrayList<SittingFollowup>sittinglist=sittingDAO.getsittinglist();
				notAvailableSlotForm.setSittinglist(sittinglist);
				
				ArrayList<Master>procedurelist=masterDAO.getprocedurelist();
				notAvailableSlotForm.setProcedurelist(procedurelist);
				
	    }catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return "departmentopd";
	}
	
	public String showconfirm() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
			ClientAction client=new ClientAction();
			String dept= request.getParameter("chngdept");
			String clientid= request.getParameter("clientid");
			String aptid= request.getParameter("aptid");
			NotAvailableSlot notavailableslot=notAvailableSlotDAO.getDepartmentAvailableSlotdata(DateTimeUtils.convertToInteger(aptid));
			String newappointmentid=client.saveappointmentwithDept(dept, clientid,2,notavailableslot.getPreDate(),notavailableslot.getCommencing());
			int res=notAvailableSlotDAO.updateDeparttmentrefferdfrom(newappointmentid,notavailableslot.getDept());
			int res1=notAvailableSlotDAO.updateclientDepartment(dept,clientid);
			 response.setContentType("text/html");
		     response.setHeader("Cache-Control", "no-cache");
		     response.getWriter().write("");
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return null;
	}
	
	
	
	
	
public String opdconfirm() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
			FinderDAO finderDAO=new JDBCFinderDAO(connection);
			ClientAction client=new ClientAction();
			String diaryuserid= request.getParameter("diaryuser");
			String clientid= request.getParameter("clientid");
			String aptid= request.getParameter("aptid");
			if(DateTimeUtils.isNull(clientid).equals("")){
				clientid=finderDAO.getclientidbyODMRid(aptid);
			}
			String seconady=request.getParameter("secondary");
			String temp[]=seconady.split(","); //for new table secondary_dr 09/05/2023
			
			NotAvailableSlot notavailableslot=notAvailableSlotDAO.getDepartmentAvailableSlotdata(DateTimeUtils.convertToInteger(aptid));
			String newappointmentid=client.savedirectappointment(diaryuserid, clientid,notavailableslot.getDept(),notavailableslot.getPreDate(),notavailableslot.getCommencing(),notavailableslot.getReferredfromdept(),notavailableslot.getId());
			
			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
			
			String dateTime ="";
			if(notavailableslot.getPreDate()==1){
				DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				String time = dateFormat.format(cal.getTime());
				dateTime = notavailableslot.getCommencing() +" "+time;
			}else{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				dateTime = dateFormat.format(cal.getTime());
			}
			
			if(!seconady.equals("") && seconady!=null) {
				finderDAO.insertinsideopd_secondary_dr(aptid,newappointmentid,seconady,dateTime,clientid);
				//for new table secondary_dr 09/05/2023
				for(String dr:temp){
					finderDAO.insertinnew_secondary_dr(aptid,newappointmentid,dr,dateTime,clientid);
				}
			}
			String diaryusername=userProfileDAO.getUserFullNameFromId(diaryuserid);
			int upddept=finderDAO.updatePatientDepartment(clientid,notavailableslot.getDept());
			int res=notAvailableSlotDAO.updatediaryuser(aptid,diaryuserid,diaryusername);
			 response.setContentType("text/html");
		     response.setHeader("Cache-Control", "no-cache");
		     response.getWriter().write("");
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return null;
	}

		public String getmodifyappointment() throws SQLException {
			
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
			String clientid= request.getParameter("clientid");
			String aptid= request.getParameter("aptid");
			ArrayList<Master> departmentList = notAvailableSlotDAO.getotDepartmenrList();
			StringBuffer str = new StringBuffer();
			NotAvailableSlot notAvailableSlot=notAvailableSlotDAO.getDepartmentAvailableSlotdata(DateTimeUtils.convertToInteger(aptid));
			int department=DateTimeUtils.convertToInteger(notAvailableSlot.getDept());
			
			str.append("<select id='modifyslect' name='modifyslect'  class='form-control chosen'>");
			str.append("<option value='0'>Select Department</option>");
		
			for (Master master : departmentList) {
				if(master.getId()==department){
					str.append("<option selected='true'  value='" + master.getId() + "'>" + master.getName() + "</option>");
				}else {
				
					str.append("<option  value='" + master.getId() + "'>" + master.getName() + "</option>");
				}
			}
			String reqdatetime=notAvailableSlot.getRequestDateTime();
			str.append("<input type='hidden' id='modifyappoclientid' value='"+clientid+"'>");
			str.append("<input type='hidden' id='modifyappoid' value='"+aptid+"'>");
			str.append("#@"+notAvailableSlot.getClientName()+"#@"+DateTimeUtils.getdatewithmonth(DateTimeUtils.getCommencingDate1(reqdatetime.split(" ")[0]))+" "+reqdatetime.split(" ")[1]+"");
			 response.setContentType("text/html");
		     response.setHeader("Cache-Control", "no-cache");
		     response.getWriter().write(str.toString());
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return null;
}
		
		
		
		public String updatemodifyappointment() throws SQLException {
			
			Connection connection=null;
			try {
				connection=Connection_provider.getconnection();
				FinderDAO finderDAO=new JDBCFinderDAO(connection);
				NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
				String clientid= request.getParameter("clientid");
				String aptid= request.getParameter("aptid");
				String department=request.getParameter("department");
				int res=finderDAO.updateModifyAppointment(clientid,aptid,department);
				 response.setContentType("text/html");
			     response.setHeader("Cache-Control", "no-cache");
			     response.getWriter().write("0");
				
			} catch (Exception e) {
			
				e.printStackTrace();
			}
			finally {
				connection.close();
			}
			
			return null;
	}		
		
		public String lmhopdpreview() throws SQLException {

			
			if(!verifyLogin(request)){
				return "login";
			}
			
			
			Connection connection = null;
			try{
				
				connection = Connection_provider.getconnection();
				FinderDAO finderDAO = new JDBCFinderDAO(connection);
				MasterDAO masterDAO=new JDBCMasterDAO(connection);
				NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
				ArrayList<Master> disciplineList =  new ArrayList<Master>();
				ClientDAO clientDAO=new JDBCClientDAO(connection);
				UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
				String department=DateTimeUtils.isNull(notAvailableSlotForm.getDept());
				//String department=DateTimeUtils.isNull(request.getParameter("referto"));
				if ( loginInfo.getSuperadminid()==1 || loginInfo.getUserType()==2 || !loginInfo.getShow_dept_opd_list().equals("0") ) {
					disciplineList = masterDAO.getDisciplineDataListWithChecked();
					
				}else if(loginInfo.getShow_dept_opd_list().equals("0")) {
					UserProfile userProfile=userProfileDAO.getUserprofileDetails(loginInfo.getId());
					department=userProfile.getDiciplineName();
					notAvailableSlotForm.setDept(department);		
					disciplineList = masterDAO.getDisciplineDataListWithChecked();
				}
				notAvailableSlotForm.setDisciplineList(disciplineList);
				
				ArrayList<DiaryManagement>userList=new ArrayList<DiaryManagement>();
				
				if(!department.equals("")){
					userList=notAvailableSlotDAO.getUserListwithdepartment(loginInfo.getId(),department);
				}else {
					userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
				}
				
				ArrayList<DiaryManagement> secondaryuserList=userProfileDAO.getAllUserListOfSecondary(department);
				ArrayList<Client> thirdPartyTypeNameList = clientDAO.getThirdPartyTypeName();
				notAvailableSlotForm.setThirdPartyTypeNameList(thirdPartyTypeNameList);
				notAvailableSlotForm.setUserList(userList);
				notAvailableSlotForm.setSecondaryuserList(secondaryuserList);
				String category=DateTimeUtils.isNull(notAvailableSlotForm.getPatientcategory());
				String fromDate=notAvailableSlotForm.getFromDate();
				String toDate=notAvailableSlotForm.getToDate();
				String patienttype=DateTimeUtils.isNull(notAvailableSlotForm.getPatienttype());
				String primarydoc=DateTimeUtils.isNull(notAvailableSlotForm.getDiaryUser());
				String secondarydoc=DateTimeUtils.isNull(notAvailableSlotForm.getSecondarydoc());
				String referto=DateTimeUtils.isNull(notAvailableSlotForm.getReferto());
				String orderby=DateTimeUtils.isNull(notAvailableSlotForm.getOrderby());
				String referfrom=DateTimeUtils.isNull(notAvailableSlotForm.getReferfrom());
				if(orderby.equals("")) {
					orderby="desc";
				}
				if (fromDate == null) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					fromDate = dateFormat.format(cal.getTime());
					fromDate = DateTimeUtils.getCommencingDate1(fromDate);
				} else {

					if (fromDate.equals("")) {
						DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						Calendar cal = Calendar.getInstance();
						fromDate = dateFormat.format(cal.getTime());
						fromDate = DateTimeUtils.getCommencingDate1(fromDate);
					} else {
						fromDate = DateTimeUtils.getCommencingDate1(fromDate);
					}
				}

				if (toDate == null) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					toDate = dateFormat.format(cal.getTime());
					toDate = DateTimeUtils.getCommencingDate1(toDate);
				} else {
					if (toDate.equals("")) {
						DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						Calendar cal = Calendar.getInstance();
						toDate = dateFormat.format(cal.getTime());
						toDate = DateTimeUtils.getCommencingDate1(toDate);
					} else {
						toDate = DateTimeUtils.getCommencingDate1(toDate);
					}
				}
				
				
				String searchText = notAvailableSlotForm.getSearchText();
				
				if(searchText!=null){
					if(searchText.equals("")){
						searchText=null;
					}
				}
				int count=0;
			    count=finderDAO.getcountOfDepartmentOPDPreview(patienttype,department,fromDate,toDate,category,secondarydoc,primarydoc,referto,orderby,referfrom);
				//int count=0;
				pagination.setPreperties(count);
				ArrayList<NotAvailableSlot>departmentOPDPreviewList = finderDAO.departmentOPDPreviewList(patienttype,department,fromDate,toDate,pagination,category,secondarydoc,primarydoc,referto,orderby,searchText,loginInfo,referfrom);
				
				   String secondary_dr2="";
				   if(departmentOPDPreviewList.size()!=0) {
				    NotAvailableSlot notAvailableSlot=departmentOPDPreviewList.get(departmentOPDPreviewList.size()-1);
				    String secondary_dr=notAvailableSlot.getOpdappoinmentid();
				    String temp[] = secondary_dr.split(",");
					for (String string : temp) {
						if(!string.equals(" ")){
	 						    String data=string;
							 if(secondary_dr2.equals("")){
								 secondary_dr2=data;
							 }else{
								 secondary_dr2=secondary_dr2+", "+data;
							 }
						}
					}
					
				   }
				   
				ArrayList<NotAvailableSlot> secondarydoctorlist=finderDAO.getSecondaryData(secondary_dr2);
				ArrayList<NotAvailableSlot>seconddrlist=new ArrayList<NotAvailableSlot>();
				int i=0;
				for (NotAvailableSlot n1 : departmentOPDPreviewList) {
					NotAvailableSlot n2=secondarydoctorlist.get(i);

					n1.setSecondarydoc(n2.getSecondarydoc());
					
					i++;
					seconddrlist.add(n1);
					
					
				}
				if(loginInfo.isAmravati()) {
				String odmarcount=finderDAO.getOdmrCount(fromDate,toDate);
				notAvailableSlotForm.setOdmrcount(odmarcount);
				ArrayList<NotAvailableSlot>deptcountList=finderDAO.deptwisecount(fromDate,toDate,referto);
				notAvailableSlotForm.setDeptwisegenderlist(deptcountList);
				}
				pagination.setTotal_records(departmentOPDPreviewList.size());
				notAvailableSlotForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
				notAvailableSlotForm.setTotalRecords(count);
				
				notAvailableSlotForm.setDepartmentOPDPreviewList(seconddrlist);
				notAvailableSlotForm.setSelectdepartment(department);
				notAvailableSlotForm.setFromDate(DateTimeUtils.getCommencingDate1(fromDate));
				notAvailableSlotForm.setToDate(DateTimeUtils.getCommencingDate1(toDate));
				notAvailableSlotForm.setPatientcategory(category);
				notAvailableSlotForm.setPatienttype(patienttype);
				notAvailableSlotForm.setOrderby(orderby);
				Clinic clinic = new Clinic();
				ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
				clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				notAvailableSlotForm.setClinicName(clinic.getClinicName());
				notAvailableSlotForm.setClinicOwner(clinic.getClinicOwner());
				notAvailableSlotForm.setOwner_qualification(clinic.getOwner_qualification());
				notAvailableSlotForm.setLandLine(clinic.getLandLine());
				notAvailableSlotForm.setClinicemail(clinic.getEmail());
				notAvailableSlotForm.setWebsiteUrl(clinic.getWebsiteUrl());

				notAvailableSlotForm.setClinicLogo(clinic.getUserImageFileName());
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
//			finally{
//				
//				connection.close();
//			}
			
			return "lmhopdpreview";
		
		}
		
		public String checkdiaryexist() throws SQLException {
			
			Connection connection=null;
			try {
				connection=Connection_provider.getconnection();
				BookAppointmentAjaxAction ajaxAction=new BookAppointmentAjaxAction();
				String ndiaryuser= request.getParameter("nuserid");
				String date= request.getParameter("date");
				String department=request.getParameter("dept");
				
				
				date = DateTimeUtils.getCommencingDate1(date);
				UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
				String res=ajaxAction.checksetdiary(date,ndiaryuser);
				ArrayList<DiaryManagement>secondaryuserList=new ArrayList<DiaryManagement>();
				String doctorname=userProfileDAO.getFullName(ndiaryuser);
				secondaryuserList=userProfileDAO.getUserListOfSecondary(department);
				StringBuffer str = new StringBuffer();
				for (DiaryManagement diaryManagement: secondaryuserList) {
//					str.append("<option value='" + diaryManagement.getId() + "'>" + diaryManagement.getDiaryUser() + "</option>");
					str.append("<div class=''>");
					str.append("<label>");
					str.append("<input type='checkbox' style='margin: 1px;' class='allchk' id='secondary_doc' onchange='allids()' value="+diaryManagement.getId()+">"+diaryManagement.getDiaryUser());
					str.append("</label>");
					str.append("</div>");
				}
				
				res= res+"@#"+str.toString()+"@#"+doctorname;
				response.setContentType("text/html");
			     response.setHeader("Cache-Control", "no-cache");
			     response.getWriter().write(res);
				
			} catch (Exception e) {
			
				e.printStackTrace();
			}
			finally {
				connection.close();
			}
			
			return null;
	}	
		
		
		
		
		
public String showremarklist() throws SQLException {
			
			Connection connection=null;
			try {
				connection=Connection_provider.getconnection();
				NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
				ChargesReportDAO chargesReportDAO=new JDBCChargesReportDAO(connection);
				
				String id= request.getParameter("id");
				NotAvailableSlot notAvailableSlot=notAvailableSlotDAO.getDepartmentAvailableSlotdata(DateTimeUtils.convertToInteger(id));
				Date date = new Date();
				String commencing= new SimpleDateFormat("yyyy-MM-dd").format(date);
				ArrayList<NotAvailableSlot> remarklist=notAvailableSlotDAO.getRemarklist(commencing,notAvailableSlot.getClientId());
				
				StringBuffer remarkbuffer=new StringBuffer();
				
				for (NotAvailableSlot notAvailableSlot2 : remarklist) {
					remarkbuffer.append("&emsp;&emsp;&emsp;<label>"+notAvailableSlot2.getDepartmentname()+"</label> :- <span>"+notAvailableSlot2.getRefferremark()+"</span><br>");	
				}
				
				List<String> myList = new ArrayList<String>(Arrays.asList(notAvailableSlot.getRefertodept().split(",")));
				StringBuffer buffer2=new StringBuffer();
				for (String string : myList) {
					String dept=chargesReportDAO.getDepartmentName(DateTimeUtils.convertToInteger(string));
					buffer2.append("<span>"+dept+"</span> | ");
				}
				
				String departmentto=buffer2.toString();
				String departmentfrom=chargesReportDAO.getDepartmentName(DateTimeUtils.convertToInteger(notAvailableSlot.getReferredfromdept()));
				
				StringBuffer buffer=new StringBuffer();
				buffer.append(remarkbuffer.toString()+"@#"+notAvailableSlot.getClientName()+"@#"+departmentto+"@#"+departmentfrom);
				response.setContentType("text/html");
			     response.setHeader("Cache-Control", "no-cache");
			     response.getWriter().write(buffer.toString());
				
			} catch (Exception e) {
			
				e.printStackTrace();
			}
			finally {
				connection.close();
			}
			
			return null;
	}

public String collegeadmission() {
	Connection connection=null;
	try {
	connection=Connection_provider.getconnection();
	Clinic clinic = new Clinic();
	ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
	clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
	notAvailableSlotForm.setClinicName(clinic.getClinicName());
	notAvailableSlotForm.setClinicOwner(clinic.getClinicOwner());
	notAvailableSlotForm.setOwner_qualification(clinic.getOwner_qualification());
	notAvailableSlotForm.setLandLine(clinic.getLandLine());
	notAvailableSlotForm.setClinicemail(clinic.getEmail());
	notAvailableSlotForm.setWebsiteUrl(clinic.getWebsiteUrl());

	notAvailableSlotForm.setClinicLogo(clinic.getUserImageFileName());
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "collegeadmission";
}
public String savecollegeadmission() {
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		FinderDAO finderDAO=new JDBCFinderDAO(connection);
		int res=finderDAO.saveDetails(notAvailableSlotForm);
	}catch (Exception e) {
		e.printStackTrace();
	}
	return "savecollegeadmission";

}
public String listadmission() {
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		FinderDAO finderDAO=new JDBCFinderDAO(connection);
		ArrayList<NotAvailableSlot> list=finderDAO.getadmissionlist();
		notAvailableSlotForm.setList(list);
	}catch (Exception e) {
		e.printStackTrace();
	}
	return "listadmission";
}
public String viewcollegeadmission() {
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		FinderDAO finderDAO=new JDBCFinderDAO(connection);
		String id=DateTimeUtils.isNull(request.getParameter("id"));
		NotAvailableSlot notAvailableSlot=finderDAO.viewcollegeadmission(id);
		notAvailableSlotForm.setNotAvailableSlot(notAvailableSlot);
		notAvailableSlotForm.setEducation(notAvailableSlot.getEducation());
	}catch (Exception e) {
		e.printStackTrace();
	}
	return "viewcollegeadmission";
	
}

public String getProcedureList(){
	try {
		Connection connection=Connection_provider.getconnection();
		FinderDAO finderDAO=new JDBCFinderDAO(connection);
		String deptid = request.getParameter("deptid");
		String clientid = request.getParameter("clientid");
		/*String departmentname = request.getParameter("departmentname");*/
		
		ArrayList<NotAvailableSlot> additionalChargeList = finderDAO.getAdditionalChargeTypeList(deptid);
	/*	ArrayList<NotAvailableSlot> getProcedureList = finderDAO.getProcedureList(deptid , clientid);*/
		/*notAvailableSlotForm.setAdditionalChargeListNew(additionalChargeList);*/
		StringBuffer str = new StringBuffer();
		str.append("<label>Procedure</label>");
		str.append("<select id='procedure' name='procedure'  class='form-control chosen'>");
		str.append("<option value='0'>Select Procedure</option>");
		for (NotAvailableSlot notAvailableSlot : additionalChargeList) {
			
			str.append("<option  value='" + notAvailableSlot.getId() + "'>" + notAvailableSlot.getName() + "</option>");
		}
		
		 response.setContentType("text/html");
	     response.setHeader("Cache-Control", "no-cache");
	     response.getWriter().write(str.toString());
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String addfollowup(){
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		FinderDAO finderDAO=new JDBCFinderDAO(connection);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String givenDate = dateFormat.format(cal.getTime());
		
		NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
		String clientid = notAvailableSlot.getClientId();
		String deptid = String.valueOf(notAvailableSlot.getId());
		notAvailableSlot.setFollowupDate(notAvailableSlotForm.getFollowupDate());
		notAvailableSlot.setProcedure(notAvailableSlotForm.getProcedure());
		notAvailableSlot.setFollowupReason(notAvailableSlotForm.getFollowupReason());
		
		int res = finderDAO.saveFollowUpDetails(notAvailableSlot , clientid , deptid, givenDate);
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	return "departmentopd";
}

public String getMainTpList() throws Exception{
	
	Connection connection = null;
	
	try {
		
		if (!verifyLogin(request)) {
			return "login";
		}
		
		connection = Connection_provider.getconnection();
		
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);
	
		String patientType = jsonObject.getString("patientType");
		
		
		FinderDAO finderDAO=new JDBCFinderDAO(connection);
		ArrayList<NotAvailableSlot> thirdPartyList=finderDAO.gettpNameList(patientType);
		
		StringBuffer dropDownAjax = new StringBuffer();
		dropDownAjax.append("<select id='tpid' name='tpid' onchange='resetChargeAndWard()' class='form-control showToolTip chosen-select' data-toggle='tooltip'>");
		dropDownAjax.append("<option value = '0'>Select Payer</option>");
		for(NotAvailableSlot thirdParty : thirdPartyList){
				dropDownAjax.append("<option value = '"+thirdParty.getTpName()+"'>"+thirdParty.getTpName()+"</option>");
		}
		
		dropDownAjax.append("</select>");
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("tpList", dropDownAjax);
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return null;
}



public String getsittingdepartment() throws Exception{                          
	
	Connection connection=null;
	
	try {
		
		connection=Connection_provider.getconnection();
		String id=request.getParameter("id");
		
		SittingFollowupDAO sittingFollowupDAO=new JDBCSittingFollowupDAO(connection);
		
		ArrayList<SittingFollowup>list=sittingFollowupDAO.getAllSittingList(id);
		StringBuffer buffer=new StringBuffer();
		
		buffer.append("<label>Select Sitting</label>");
		buffer.append("<select name='sitting_id' id='sitting_id' onchange='setproceduremaster(this.value)' class='form-control chosen-select'>");
		buffer.append(" <option value='0'>Select Sitting</option>");
		
		for(SittingFollowup sittingFollowup:list){
			buffer.append("<option value='" + sittingFollowup.getId() + "'>" + sittingFollowup.getSittingFollowup() + "</option>");
		}
		buffer.append("</select>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(buffer.toString());
		
		} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	return null;
}


public String savesitting() throws Exception{
	
	
	try {
		
		Connection connection=Connection_provider.getconnection();
		FinderDAO finderDAO=new JDBCFinderDAO(connection);
		
	    //String patientnae=request.getParameter("patientnm");
		String deptopdid=request.getParameter("deptopdid");
		String clientid=request.getParameter("clientid");
		String department=request.getParameter("department");
		String sitting=request.getParameter("sitting");
		String date=request.getParameter("date");
	    //String followup = request.getParameter("followup");
	    String remark=request.getParameter("remark");
		String proceduremasterlist=request.getParameter("procedure");
	    String sittingnum=request.getParameter("sittingnum");
		String procedurelist=request.getParameter("procedureid");
		String diagnosis=request.getParameter("diagnosis");
		String userid=loginInfo.getUserId();
		String date_time = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		String sittinbckdate=DateTimeUtils.getCommencingDate1(request.getParameter("sittinbckdate"));
		
	    NotAvailableSlot notAvailableSlot=new NotAvailableSlot();
	   
		notAvailableSlot.setClientId(clientid);
		notAvailableSlot.setDepartment(department);
		notAvailableSlot.setSitting(sitting);
		notAvailableSlot.setDate(date);
	   // notAvailableSlot.setSittingFollowup(Boolean.parseBoolean(followup));
	    notAvailableSlot.setRemark(remark);
	    notAvailableSlot.setAll_procedure(proceduremasterlist);
	    notAvailableSlot.setProcedure_list(procedurelist);
	    notAvailableSlot.setUser_id(userid);
	    if(loginInfo.isLmh()) {
	    	notAvailableSlot.setDate_time(sittinbckdate+" "+date_time.split(" ")[1]);
	    }else {
	    	notAvailableSlot.setDate_time(date_time);
	    }
	    //notAvailableSlot.setDate_time(date_time);
	    notAvailableSlot.setDeptOpdId(Integer.parseInt(deptopdid));
	    notAvailableSlot.setSittingnum(sittingnum);
	    notAvailableSlot.setDiagnosisarea(diagnosis);
	    int result=finderDAO.getsittingData(notAvailableSlot);
	    
	    result=finderDAO.saveDepartmentfollowupdate(deptopdid,DateTimeUtils.getCommencingDate1(date));
	    
	    result= finderDAO.updateDepartmentfollowupdate(deptopdid,DateTimeUtils.getCommencingDate1(date));
	    
	    NotAvailableSlot noAvailableSlot1=finderDAO.getClientnamebyId(clientid);
	    String deptname=finderDAO.getdepartementname(department);
	    SmsService sms=new SmsService();
	    String templateid="1207173693656019160";
	    String message=noAvailableSlot1.getClientName()+" आपने दिनांक "+date+" को "+deptname+" में अपाइंटमेंट बुक किया है RDDCRC, Nagpur VISHWA TECHNOLOGIES PRIVATE LIMITED";
	    sms.sendSms(message, noAvailableSlot1.getMobno(), loginInfo, new EmailLetterLog(),templateid);
	    response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+result+"");

		} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		
		}
	
	return null;
	
}


public String editsitting() throws Exception{
	
Connection connection = null;
	
	try {
		
		String selectedid=request.getParameter("id");
		connection = Connection_provider.getconnection();
    	FinderDAO finderDAO=new JDBCFinderDAO(connection);
    	MasterDAO masterDAO=new JDBCMasterDAO(connection);
    
        
  
        NotAvailableSlot notAvailableSlot =finderDAO.geteditsitting(selectedid);
        
        ArrayList<Master>dlist=masterDAO.editDisciplineDataList(notAvailableSlot.getDepartment());
       
        //Method Resuse get sitting master list from departmentid -jdbc
        //jsp-code java
        //stringbuffer seperate
        
        //Method Resuse get procedure master list from sitting -jdbc
        //jsp-code java
        //stringbuffer seperate
        
        //Method Resuse get procedure list from procedure master -jdbc
        //jsp-code java
        //stringbuffer seperate
        
        
        StringBuffer buffer3=new StringBuffer();
		
		buffer3.append("<label>Select depatment</label>");
		buffer3.append("<select name='department' id='edit_dept' onchange='setDepartmentsitting(this.value)' class='form-control chosen-select'>");
		
        
		for(Master master:dlist){
			buffer3.append("<option value='" + master.getId() + "' selected='selected'>" + master.getDiscipline() + "</option>");
		}
        
		buffer3.append("</select>");
        
        
        SittingFollowupDAO sittingFollowupDAO=new JDBCSittingFollowupDAO(connection);
		
		ArrayList<SittingFollowup>list=sittingFollowupDAO.getAllSittingList(notAvailableSlot.getDepartment());
		StringBuffer buffer=new StringBuffer();
		
		buffer.append("<label>Select Sitting</label>");
		buffer.append("<select name='sitting_id' id='edit_sittings' onchange='setproceduremaster(this.value)' class='form-control chosen-select'>");
		//buffer.append(" <option value='0'>Select Sitting</option>");
        
		for(SittingFollowup sittingFollowup:list){
			if(sittingFollowup.getId().equals(notAvailableSlot.getSitting())) {
				buffer.append("<option value='" + sittingFollowup.getId() + "' selected='selected'>" + sittingFollowup.getSittingFollowup() + "</option>");
			}else {
				buffer.append("<option value='" + sittingFollowup.getId() + "'>" + sittingFollowup.getSittingFollowup() + "</option>");
			}
			
		}
        
		buffer.append("</select>");
		
		
		ArrayList<Master>proceduretypelist=masterDAO.getchargetypelist(notAvailableSlot.getSitting());
		StringBuffer buffer1=new StringBuffer();
		
		buffer1.append("<label>Select Proceduremasterlist</label>");
	    buffer1.append("<select name='master_id' id='edit_master_id' onchange='setProcedurelist(this.value)' class='form-control chosen-select'>");
		//buffer1.append(" <option value='0'>Select Proceduremasterlist</option>");
		 
		 for (Master master : proceduretypelist) {
			    if(master.getId()==DateTimeUtils.convertToInteger(notAvailableSlot.getAll_procedure())) {
			    	 buffer1.append("<option value='" + master.getId() + "' selected='selected'>" + master.getName() + "</option>");
			    }else {
			    	 buffer1.append("<option value='" + master.getId() + "'>" + master.getName() + "</option>");
			    	
			    }
			 }
		 
		buffer1.append("</select>");
		    
		    
		AppointmentTypeDAO appointmentTypeDAO=new JDBCAppointmentTypeDAO(connection);
	    ArrayList<AppointmentType>plist=appointmentTypeDAO.getProcedureList(notAvailableSlot.getAll_procedure());
	    StringBuffer buffer2=new StringBuffer();
				
		buffer2.append("<label>Select Procedurelist</label>");
		buffer2.append("<select name='procedure_id' id='edit_procedure'  class='form-control chosen-select'>");
		//buffer2.append(" <option value='0'>Select procedurelist</option>");
				
				
		for (AppointmentType appointmentType : plist) {
				if(appointmentType.getId()==DateTimeUtils.convertToInteger(notAvailableSlot.getProcedure_list())) {
					    buffer2.append("<option value='" + appointmentType.getId() + "' selected='selected'>" + appointmentType.getName() + "</option>");

					}else{
						buffer2.append("<option value='" + appointmentType.getId() +"'>" + appointmentType.getName() + "</option>");
					}
				}
			   
		buffer2.append("</select>");
		
	    String data=notAvailableSlot.getId()+"~~"+buffer3.toString()+"~~"+buffer.toString()+"~~"+notAvailableSlot.getRemark()+"~~"+
				                    notAvailableSlot.getFollowupDate()+"~~"+buffer1.toString()+"~~"+ buffer2.toString()+"~~"+notAvailableSlot.getSittingnum()+"~~"+DateTimeUtils.getCommencingDate1(notAvailableSlot.getDate_time().split(" ")[0]);     
    	
    	
    	response.setContentType("text/html"); 
		response.setHeader("Cache-Control", "no-cache"); 
		response.getWriter().write(""+data+"");
		 
 	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	return null;
}


public String updatesitting() throws Exception{
	
   Connection connection = null;
	
	
    try {
    	
		connection=Connection_provider.getconnection();
		
		
		String id=request.getParameter("id");
		String department=request.getParameter("department");
		String sitting=request.getParameter("sitting");
		String folloupdate=request.getParameter("date");
		String proceduremster=request.getParameter("proceduremaster");
		String procedure=request.getParameter("procedureid");
		String remark=request.getParameter("remark");
		String sittingnum=request.getParameter("sittingnum");
		String sittingdate=DateTimeUtils.getCommencingDate1(request.getParameter("sittingdate"));
		String date_time = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		
		NotAvailableSlot notAvailableSlot=new NotAvailableSlot();
		FinderDAO finderDAO=new JDBCFinderDAO(connection);
		
		notAvailableSlot.setId(Integer.parseInt(id));
		notAvailableSlot.setDepartment(department);
		notAvailableSlot.setSitting(sitting);
		notAvailableSlot.setFollowupDate(folloupdate);
		notAvailableSlot.setAll_procedure(proceduremster);
		notAvailableSlot.setProcedure_list(procedure);
		notAvailableSlot.setRemark(remark);
		notAvailableSlot.setSittingnum(sittingnum);
		if(loginInfo.isLmh()) {
	    	notAvailableSlot.setDate_time(sittingdate+" "+date_time.split(" ")[1]);
	    }else {
	    	notAvailableSlot.setDate_time(date_time);
	    }
		String deptopdid=finderDAO.Departmentopdid(id);	
		int result=finderDAO.getupdatesitting(notAvailableSlot);
		notAvailableSlot.setDeptOpdId(Integer.parseInt(deptopdid));
		result=finderDAO.updatedata(notAvailableSlot);
		
		response.setContentType("text/html"); 
		response.setHeader("Cache-Control", "no-cache"); 
		response.getWriter().write(""+result+"");
		
    }catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return null;
}

public String deletesitting() throws Exception{
	
  try {
		  
		String selectedid=request.getParameter("id");
		Connection con=Connection_provider.getconnection();
		FinderDAO finderDAO=new JDBCFinderDAO(con);	
		
		String deptopdid=finderDAO.Departmentopdid(selectedid);
		finderDAO.deletedata(deptopdid);
		int result=finderDAO.deletesittingdata(selectedid);
		
	    response.setContentType("text/html"); 
	    response.setHeader("Cache-Control", "no-cache"); 
	    response.getWriter().write(""+result+"");
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	return null;
}

public String getproceduremasterlist() throws Exception {
	
	Connection connection=null;
	
	try {
		
		connection=Connection_provider.getconnection();
		String id=request.getParameter("id");
		
		AppointmentTypeDAO appointmentTypeDAO=new JDBCAppointmentTypeDAO(connection);
	    String name=appointmentTypeDAO.getnameFromId(id);
	    ArrayList<AppointmentType>list=appointmentTypeDAO.getProcedureList(id);
	    StringBuffer buffer=new StringBuffer();
		
		buffer.append("<label>Select Procedure</label>");
		buffer.append("<select name='procedure_id' id='procedure_id'  class='form-control chosen-select'>");
		buffer.append(" <option value='0'>Select procedure</option>");
		
		for (AppointmentType appointmentType : list) {
			    buffer.append("<option value='" + appointmentType.getId() + "'>" + appointmentType.getName() + "</option>");
                }
	   
	    buffer.append("</select>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(buffer.toString());
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	return null;
}

public String dailyregistrationrpt() throws Exception{
	
	 if(!verifyLogin(request)){
		 return "login";
	     }
	   
	   Connection connection = null;
	try {
		
		connection = Connection_provider.getconnection();
		FinderDAO finderDAO = new JDBCFinderDAO(connection);
		MasterDAO masterDAO=new JDBCMasterDAO(connection);
		NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
		ArrayList<Master> disciplineList =  new ArrayList<Master>();
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
		String department=DateTimeUtils.isNull(notAvailableSlotForm.getDept());
		if ( loginInfo.getSuperadminid()==1 || loginInfo.getUserType()==2 || !loginInfo.getShow_dept_opd_list().equals("0") ) {
			disciplineList = masterDAO.getDisciplineDataListWithChecked();
			
		}else if(loginInfo.getShow_dept_opd_list().equals("0")) {
			UserProfile userProfile=userProfileDAO.getUserprofileDetails(loginInfo.getId());
			department=userProfile.getDiciplineName();
			notAvailableSlotForm.setDept(department);		
			disciplineList = masterDAO.getDisciplineDataListWithChecked();
		}
		notAvailableSlotForm.setDisciplineList(disciplineList);
		
		ArrayList<DiaryManagement>userList=new ArrayList<DiaryManagement>();
		
		if(!department.equals("")){
			userList=notAvailableSlotDAO.getUserListwithdepartment(loginInfo.getId(),department);
		}else {
			userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
		}
		
		//ArrayList<DiaryManagement> secondaryuserList=userProfileDAO.getAllUserListOfSecondary(department);
		ArrayList<DiaryManagement> secondaryuserList= new ArrayList<>();
		ArrayList<Client> thirdPartyTypeNameList = clientDAO.getThirdPartyTypeName();
		notAvailableSlotForm.setThirdPartyTypeNameList(thirdPartyTypeNameList);
		notAvailableSlotForm.setUserList(userList);
		notAvailableSlotForm.setSecondaryuserList(secondaryuserList);
		String category=DateTimeUtils.isNull(notAvailableSlotForm.getPatientcategory());
		String fromDate=notAvailableSlotForm.getFromDate();
		String toDate=notAvailableSlotForm.getToDate();
		String patienttype=DateTimeUtils.isNull(notAvailableSlotForm.getPatienttype());
		String primarydoc=DateTimeUtils.isNull(notAvailableSlotForm.getDiaryUser());
		String secondarydoc=DateTimeUtils.isNull(notAvailableSlotForm.getSecondarydoc());
		String referto=DateTimeUtils.isNull(notAvailableSlotForm.getReferto());
		String orderby=DateTimeUtils.isNull(notAvailableSlotForm.getOrderby());
		if(orderby.equals("")) {
			orderby="desc";
		}
		if (fromDate == null) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			fromDate = dateFormat.format(cal.getTime());
			fromDate = DateTimeUtils.getCommencingDate1(fromDate);
		} else {

			if (fromDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromDate = dateFormat.format(cal.getTime());
				fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			} else {
				fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			}
		}

		if (toDate == null) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			toDate = dateFormat.format(cal.getTime());
			toDate = DateTimeUtils.getCommencingDate1(toDate);
		} else {
			if (toDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				toDate = dateFormat.format(cal.getTime());
				toDate = DateTimeUtils.getCommencingDate1(toDate);
			} else {
				toDate = DateTimeUtils.getCommencingDate1(toDate);
			}
		}
		
		
		String searchText = notAvailableSlotForm.getSearchText();
		
		if(searchText!=null){
			if(searchText.equals("")){
				searchText=null;
			}
		}
		ArrayList<Master> deptWiseCountList = masterDAO.getDepartmentWisePatientCount1(fromDate,toDate);
		int finalTotalPatientCount=0;
		int TotalPatientCount=0;
		if(deptWiseCountList.size()>0){
			
			finalTotalPatientCount = deptWiseCountList.get(deptWiseCountList.size()-1).getFinalTotalPatientCount();
			TotalPatientCount = deptWiseCountList.get(deptWiseCountList.size()-1).getFinalNewPatientCount();

		}
		notAvailableSlotForm.setDeptWiseCountList(deptWiseCountList);
		notAvailableSlotForm.setFinalNewPatientCount(TotalPatientCount);
		
		notAvailableSlotForm.setFinalTotalPatientCount(finalTotalPatientCount);
		
		//int count=finderDAO.getcountOfDailyregistrationreport(patienttype,department,fromDate,toDate,category,secondarydoc,primarydoc,referto,orderby);
		//pagination.setPreperties(count);
		//ArrayList<NotAvailableSlot>dailyRegistrationreport = finderDAO.dailyRegistrationreport(patienttype,department,fromDate,toDate,pagination,category,secondarydoc,primarydoc,referto,orderby,searchText);
		
		ArrayList<NotAvailableSlot> dailyRegistrationreport = new ArrayList<NotAvailableSlot>();
		
	if(loginInfo.isLmh() && !loginInfo.isMatrusevasang() && !loginInfo.isPhysio() && !loginInfo.isAmravati()){	
		if(fromDate.equals(toDate)){
			dailyRegistrationreport = finderDAO.dailyRegistrationReport1(patienttype, department, fromDate, toDate, category, secondarydoc, primarydoc, referto, orderby, searchText) ;
		}else{
			
			/*String d1=DateTimeUtils.getCommencingDate(fromDate);
			String d2=DateTimeUtils.getCommencingDate(toDate);*/
			
			long dnumber=DateTimeUtils.getDiffofTwoDates(fromDate, toDate);
			dailyRegistrationreport = finderDAO.dailyRegistrationReport2(patienttype, department, fromDate, toDate, category, secondarydoc, primarydoc, referto, orderby, searchText,dnumber) ;
		
			
			String testDate = "29-Apr-2010,13:00:14 PM";
			DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");
			Date date = formatter.parse(testDate);
			System.out.println(date);
			
			//calculating new date
			    /*DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				Date date=new Date("fromDate");
				cal.add(Calendar.DATE, +1); 
				fromDate = dateFormat.format(cal.getTime());*/
		}
	
	}else{
		dailyRegistrationreport = finderDAO.dailyRegistrationReport1(patienttype, department, fromDate, toDate, category, secondarydoc, primarydoc, referto, orderby, searchText) ;

		}
		//pagination.setTotal_records(dailyRegistrationreport.size());
		//notAvailableSlotForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
		//notAvailableSlotForm.setTotalRecords(count);
		
		//notAvailableSlotForm.setDepartmentOPDPreviewList(dailyRegistrationreport);
	    notAvailableSlotForm.setDailyrepot(dailyRegistrationreport);
		notAvailableSlotForm.setSelectdepartment(department);
		notAvailableSlotForm.setFromDate(DateTimeUtils.getCommencingDate1(fromDate));
		notAvailableSlotForm.setToDate(DateTimeUtils.getCommencingDate1(toDate));
		notAvailableSlotForm.setPatientcategory(category);
		notAvailableSlotForm.setPatienttype(patienttype);
		notAvailableSlotForm.setOrderby(orderby);
		Clinic clinic = new Clinic();
		ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
		clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
		notAvailableSlotForm.setClinicName(clinic.getClinicName());
		notAvailableSlotForm.setClinicOwner(clinic.getClinicOwner());
		notAvailableSlotForm.setOwner_qualification(clinic.getOwner_qualification());
		notAvailableSlotForm.setLandLine(clinic.getLandLine());
		notAvailableSlotForm.setClinicemail(clinic.getEmail());
		notAvailableSlotForm.setWebsiteUrl(clinic.getWebsiteUrl());

		notAvailableSlotForm.setClinicLogo(clinic.getUserImageFileName());
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	return "dailyregistration";
}


public String referedpatientreport() throws Exception{
	
	Connection connection = null;
	
	try {
		 
		connection = Connection_provider.getconnection();
		FinderDAO finderDAO = new JDBCFinderDAO(connection);
		
		String fromDate=notAvailableSlotForm.getFromDate();
		String toDate=notAvailableSlotForm.getToDate();
		
		
		//Clinic clinic = clinicDAO.getClinicDetails(loginInfo.getClinicUserid());
		//String clinicid=clinic.getIsclinic();
		String sclinicid=loginInfo.getClinicUserid();
		boolean smallClinic = loginInfo.isSmallClinic();
		
		if (fromDate == null) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			fromDate = dateFormat.format(cal.getTime());
			fromDate = DateTimeUtils.getCommencingDate1(fromDate);
		} else {

			if (fromDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromDate = dateFormat.format(cal.getTime());
				fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			} else {
				fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			}
		}
		
		if (toDate == null) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			toDate = dateFormat.format(cal.getTime());
			toDate = DateTimeUtils.getCommencingDate1(toDate);
		} else {
			if (toDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				toDate = dateFormat.format(cal.getTime());
				toDate = DateTimeUtils.getCommencingDate1(toDate);
			} else {
				toDate = DateTimeUtils.getCommencingDate1(toDate);
			}
		}
		
        String searchText = notAvailableSlotForm.getSearchText();
		
		if(searchText!=null){
			if(searchText.equals("")){
				searchText=null;
			}
		}
		boolean isHospital = true;
		if(!smallClinic){
			isHospital = finderDAO.checkLoginByUserOrHosp(loginInfo.getUserId());
		}
		String pro_userid="";
		if(loginInfo.isSmallClinic() && loginInfo.isCommon_clinic_db()){
			pro_userid = loginInfo.getUserId();
		}
		ArrayList<NotAvailableSlot>referedpatientlist=finderDAO.getreferedpatientlist(fromDate,toDate,searchText,
				smallClinic,sclinicid,isHospital,loginInfo.getUserId(),pro_userid,loginInfo.getSuperadminid());
		
		
		notAvailableSlotForm.setFromDate(DateTimeUtils.getCommencingDate1(fromDate));
		notAvailableSlotForm.setToDate(DateTimeUtils.getCommencingDate1(toDate));
		
		notAvailableSlotForm.setReferedpatientlist(referedpatientlist);
		
		MasterDAO masterDAO = new JDBCMasterDAO(connection);
		String pro_userid_id = pro_userid;
		if(pro_userid_id.equals("")){
			pro_userid_id = loginInfo.getClinicUserid();
		}
		ArrayList<Master> referhospitallist = masterDAO.getallhospitallist(pro_userid_id,loginInfo.getUserId());
		notAvailableSlotForm.setReferhospitallist(referhospitallist);
		setPatientRegisterData();
		
		ArrayList<Master> patientStatusList = finderDAO.getPatientStatusList();
		notAvailableSlotForm.setPatientStatusList(patientStatusList);
		
		if(loginInfo.isSmallClinic()){
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			
			Clinic clinicaccess=clinicDAO.getBankClinicDetails(loginInfo.getClinicUserid(),pro_userid);
			//if(!DateTimeUtils.isNull(clinicaccess.getAccount_name()).equals("") || !DateTimeUtils.isNull(clinicaccess.getAccount_upi()).equals("")){
			if(clinicaccess.getId()>0){	
				notAvailableSlotForm.setIsAccnotPresent(1);
			}
			int res = clinicDAO.deleteClinicHospital(pro_userid_id);
		}
		ArrayList<Master> offerList = new ArrayList<>();
		if(smallClinic){
			//clinic =1
			offerList = finderDAO.getOffersList(1);
		}else{
			offerList = finderDAO.getOffersList(2);
		}
		int offerCount = offerList.size();
		notAvailableSlotForm.setOfferCount(offerCount);
		notAvailableSlotForm.setOfferList(offerList);
		notAvailableSlotForm.setIsFromProDashboard(1);
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "referedpatientrpt";
	
}

private void setPatientRegisterData() {

	Connection connection = null;
	
	ArrayList<Client> thirdPartyTypeList = new ArrayList<Client>();
	//ArrayList<Client> thirdPartyTypeNameList = new ArrayList<Client>();
	ArrayList<Client> clientOccupationList = new ArrayList<Client>();
	ArrayList<Client> refrenceList = new ArrayList<Client>();
	ArrayList<String> initialList = new ArrayList<String>();
	ArrayList<Client> sourceOfIntroList = new ArrayList<Client>();
	ArrayList<Client> gpList = new ArrayList<Client>();
	ArrayList<Client> condtitionList = new ArrayList<Client>();

	try{
		
		connection = Connection_provider.getconnection();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		gpList = clientDAO.getGpList();
		notAvailableSlotForm.setGpList(gpList);

		condtitionList = clientDAO.getEmrTreatmentTypeList();
		notAvailableSlotForm.setCondtitionList(condtitionList);
		
		ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
		notAvailableSlotForm.setUserList(userList);

		thirdPartyTypeList = clientDAO.getThirdPartyType();
		notAvailableSlotForm.setThirdPartyTypeList(thirdPartyTypeList);
		
		//thirdPartyTypeNameList = clientDAO.getThirdPartyTypeName();
		//mrdForm.setThirdPartyTypeNameList(thirdPartyTypeNameList);
		
		clientOccupationList = clientDAO.getOccupationList();
		notAvailableSlotForm.setHourList(PopulateList.hourList());
		notAvailableSlotForm.setMinuteList(PopulateList.getMinuteList());
		
		
		notAvailableSlotForm.setClientOccupationList(clientOccupationList);
		
		refrenceList = clientDAO.getReferenceList();
		
		notAvailableSlotForm.setRefrenceList(refrenceList);
		
		initialList = clientDAO.getInitialList();
		notAvailableSlotForm.setInitialList(initialList);
		
		sourceOfIntroList = clientDAO.getSourceOfIntroList();
		notAvailableSlotForm.setSourceOfIntroList(sourceOfIntroList);
		
		
		//set surgery list
		ArrayList<Client>surgeryList = clientDAO.getSurgeryList();
		notAvailableSlotForm.setSurgeryList(surgeryList);
		
		ArrayList<ThirdParty> tpnameList = new ArrayList<ThirdParty>();
		ThirdPartyDAO  thirdPartyDAO = new JDBCThirdPartyDAO(connection);

		tpnameList = thirdPartyDAO.getTPNameList();
		notAvailableSlotForm.setTpnameList(tpnameList);
		notAvailableSlotForm.setApmtDurationList(PopulateList.apmtDurationList());
		TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);

		ArrayList<TreatmentEpisode> sourceOfReferralList = treatmentEpisodeDAO.getSourceOfReferralList();
		notAvailableSlotForm.setSourceOfReferralList(sourceOfReferralList);
		
		//set declearation list
		/*ArrayList<Master>declerationTitleList = clientDAO.getDeclerationTitleList();
		notAvailableSlotForm.setDeclerationTitleList(declerationTitleList);
		String selecteddecid = clientDAO.getSelectedDecId();
		notAvailableSlotForm.setDectitle(selecteddecid);*/
		
		//set state and city list
		InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
		ArrayList<Master> stateList= vendorDAO.getAllStateList();
		ArrayList<Master> cityList= vendorDAO.getAllCityList();
		notAvailableSlotForm.setStatelist(stateList);
		notAvailableSlotForm.setCitylist(cityList);
		
	
		notAvailableSlotForm.setCountryList(PopulateList.countryList());
		notAvailableSlotForm.setCountry("India");
		
	
		ArrayList<Client> diagnosisList=clientDAO.getEmrTreatmentTypeList();
		notAvailableSlotForm.setDiagnosisList(diagnosisList);
	
	
	
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally{
		
	}

	
}
public String masterlist() throws Exception{
	
	Connection connection=null;
	
	try {
		
		connection=Connection_provider.getconnection();
		String id=request.getParameter("id");
		
		MasterDAO masterDAO=new JDBCMasterDAO(connection);
		ArrayList<Master>proceduretypelist=masterDAO.getchargetypelist(id);

		StringBuffer buffer=new StringBuffer();
		
		buffer.append("<label>Select Procedure Master</label>");
	    buffer.append("<select name='master_id' id='master_id' onchange='setProcedurelist(this.value)' class='form-control chosen-select'>");
		buffer.append(" <option value='0'>Select Procedure Master</option>");
		 
		 for (Master master : proceduretypelist) {
			     buffer.append("<option value='" + master.getId() + "'>" + master.getName() + "</option>");
			 }
		 
		buffer.append("</select>");
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(buffer.toString());
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return null;
    }

public String deptOpdlist() throws Exception{
	 
	 Connection connection=null;
	 
	 try {
		 
		    connection = Connection_provider.getconnection();
	    	FinderDAO finderDAO=new JDBCFinderDAO(connection);
	    	SittingFollowupDAO sittingFollowupDAO=new JDBCSittingFollowupDAO(connection);
	    	AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(
					connection);
		 
		    String selecteddept_id=request.getParameter("selecteddept");
		    String client_id=request.getParameter("clientid");
		    
		    MasterDAO masterDAO=new JDBCMasterDAO(connection);
		    ArrayList<Master>dlist=masterDAO.getDepartmenDataList(selecteddept_id); 
		    
		   int sittingcount=finderDAO.gettotalSittingcount(selecteddept_id,client_id);
		   sittingcount=sittingcount+1;
			StringBuffer buffer=new StringBuffer();
			
			buffer.append("<label>Select depatment</label>");
			buffer.append("<select name='dept' id='dept' onchange='setDepartmentsitting(this.value)' class='form-control chosen-select'>");
			
	        
			for(Master master:dlist){
				buffer.append("<option value='" + master.getId() + "' selected='selected'>" + master.getDiscipline() + "</option>");
			}
	        
			buffer.append("</select>");
			
			StringBuffer buffer1=new StringBuffer();
			if(!loginInfo.isBalgopal()) {
			
			ArrayList<SittingFollowup>list=sittingFollowupDAO.getAllSittingList(selecteddept_id);
			//StringBuffer buffer1=new StringBuffer();
			
			buffer1.append("<label>Select Sitting</label>");
			buffer1.append("<select name='sitting_id' id='sitting_id' onchange='setproceduremaster(this.value)' class='form-control chosen-select'>");
			buffer1.append(" <option value='0'>Select Sitting</option>");
	        
			for(SittingFollowup sittingFollowup:list){
				buffer1.append("<option value='" + sittingFollowup.getId() + "'>" + sittingFollowup.getSittingFollowup() + "</option>");
			}
	        
			buffer1.append("</select>");
			
			}else {
				ChargesReportDAO chargesReportDAO=new JDBCChargesReportDAO(connection);
				String departmentname=chargesReportDAO.getDepartmentName(Integer.parseInt(selecteddept_id));
				ArrayList<AppointmentType>list=appointmentTypeDAO.getAllSessionChargeList(departmentname,client_id);
				//StringBuffer buffer1=new StringBuffer();
				
				buffer1.append("<label>Select Session</label>");
				buffer1.append("<select name='sitting_id' id='sitting_id' onchange='setproceduremaster(this.value)' class='form-control chosen-select'>");
				buffer1.append(" <option value='0'>Select Session</option>");
		        
				for(AppointmentType sittingFollowup:list){
					buffer1.append("<option value='" + sittingFollowup.getId() + "' selected='selected'>" + sittingFollowup.getName() + "</option>");
				}
		        
				buffer1.append("</select>");
				
				
				
			}
			
			//buffer.append("<input type='text' name='sittingno' id='sitting_num'  class='form-control' value='"+sittingcount+"'>");
			String data=buffer.toString()+"~~"+buffer1.toString()+"~~"+sittingcount;
		    
		    
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+data+"");
			
		 
		 
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	 
	 return null;
}


public String consultCount() throws Exception{
	
	try {
		
		
		String clientid=request.getParameter("clientid");
		Connection con=Connection_provider.getconnection();
		
		FinderDAO finderDAO=new JDBCFinderDAO(con);
		NotAvailableSlot notAvailableSlot=new NotAvailableSlot();
		
	    int invcount=finderDAO.getinvestigationCount(clientid);
	    int ipdcount=finderDAO.getipdCount(clientid);
	    int precount=finderDAO.getprescriptionCount(clientid);
		int pharcount=finderDAO.getpharmacyCount(clientid);
		int opdcount=finderDAO.getopdCount(clientid);
		int otcount=finderDAO.getotCount(clientid);
		int priscount=finderDAO.getprescriptionCount(clientid);
		String data=""+invcount+"~~"+""+ipdcount+"~~"+""+precount+"~~"+""+pharcount+"~~"+""+opdcount+"~~"+""+otcount+"~~"+priscount;
		
		//String data=Integer.toString(invcount)+"~~"+Integer.toString(invcount);
		
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+data+"");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return null;
	
  }


public String deleteconsultfake()throws Exception{
	
	try {
		
         String clientid=request.getParameter("fake_clientid");
	     Connection con=Connection_provider.getconnection();
	     
	     FinderDAO finderDAO=new JDBCFinderDAO(con);
	     
	     int result=finderDAO.getdeleteData(clientid);
	     
	     response.setContentType("text/html");
		 response.setHeader("Cache-Control", "no-cache");
		 response.getWriter().write(""+result+"");
	     
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	return null;
	
}

public String patienthistory()throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	
	
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		FinderDAO finderDAO = new JDBCFinderDAO(connection);
		String searchText=notAvailableSlotForm.getSearchText();
		if(searchText!=null){
			if(searchText.equals("")){
				searchText="";
			}
		}
		ArrayList<NotAvailableSlot> patienthistoryList=finderDAO.getPatientHistory(searchText);
		notAvailableSlotForm.setPatienthistorylist(patienthistoryList);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return "patienthistory";
}


public String notecont() throws Exception{
	
	Connection connection = null;
     try {
			/*
			 * String ipdaddmissionid=request.getParameter("ipdaddmissionid"); Connection
			 * con=Connection_provider.getconnection();
			 * 
			 * FinderDAO finderDAO=new JDBCFinderDAO(con); NotAvailableSlot
			 * notAvailableSlot=new NotAvailableSlot();
			 * 
			 * int notecount=finderDAO.getnoteCount(ipdaddmissionid);
			 * 
			 * response.setContentType("text/html"); response.setHeader("Cache-Control",
			 * "no-cache"); response.getWriter().write(""+notecount+"");
			 */
		
    	 connection=Connection_provider.getconnection();
 		 IpdDAO ipdDAO = new JDBCIpdDAO(connection);
 		 FinderDAO finderDAO=new JDBCFinderDAO(connection);
 		 StringBuffer buffer = new StringBuffer();
 		 BufferedReader reader = request.getReader();
 			String line;
 			while ((line = reader.readLine()) !=null) {
 				buffer.append(line);
 			}
 		 String data = buffer.toString();
 	     JSONObject jsonObject = new JSONObject(data);
 	     String ipdaddmissionid=jsonObject.getString("ipdadmissionid");
 		 int notecount=finderDAO.getnoteCount(ipdaddmissionid);
 		 JSONObject object = new JSONObject();
 		 object.put("notecount", notecount);
 		 object.put("ipdaddmissionid", ipdaddmissionid);
 		 String response1 = object.toString();
 		 response.setContentType("application/json");
 		 response.setHeader("Cache-Control", "no-cache");
 		 response.getWriter().write(response1);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return null;

	
}

public String savecolorcode() throws Exception{
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		FinderDAO finderDAO=new JDBCFinderDAO(connection);
		String id = request.getParameter("data");
		String color = request.getParameter("color");
		String date_time = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		int result=0;
		
		for (String s : id.split(",")) {
			if (s.equals("0")) {
				continue;
			}
			NotAvailableSlot notAvailableSlot=finderDAO.getClientId(s);
			notAvailableSlot.setDate_time(date_time);
			notAvailableSlot.setColor(color);
			result=finderDAO.saveColorCode(notAvailableSlot);
			
		}
		
		 response.setContentType("text/html");
		 response.setHeader("Cache-Control", "no-cache");
		 response.getWriter().write(""+result+"");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return null;
}
public String physioipd() throws Exception{
	
	if(!verifyLogin(request)){
		return "login";
	}
	
	Connection connection = null;
	
	try{
		
		connection = Connection_provider.getconnection();
		FinderDAO finderDAO = new JDBCFinderDAO(connection);
		MasterDAO masterDAO=new JDBCMasterDAO(connection);
		SittingFollowupDAO sittingDAO=new JDBCSittingFollowupDAO(connection);
		AppointmentTypeDAO appointmentTypeDAO=new JDBCAppointmentTypeDAO(connection);
		NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
		UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
		//String specialized=profileDAO.getSpeciality(loginInfo.getUserId());
		
		ArrayList<Master> disciplineList = new ArrayList<Master>();
		ArrayList<DiaryManagement>userList=new ArrayList<DiaryManagement>();
		
		String department=DateTimeUtils.isNull(notAvailableSlotForm.getDept());
		String date = DateTimeUtils.isNull(notAvailableSlotForm.getFromDate()) ;
		String category=DateTimeUtils.numberCheck(notAvailableSlotForm.getPatientcategory());
		String orderby=DateTimeUtils.isNull(notAvailableSlotForm.getOrderby());
		
		/*String dicipline = loginInfo.getDicipline();
		dicipline = DateTimeUtils.numberCheck(dicipline);
		ArrayList<Master> referDepartList = masterDAO.getDisciplineWithChecked(dicipline);
		notAvailableSlotForm.setReferDepartList(referDepartList);*/
		
	if(!loginInfo.isBalgopal()) {	
		if ( loginInfo.getSuperadminid()==1 || loginInfo.getUserType()==2 || !loginInfo.getShow_dept_opd_list().equals("0") ) {
			disciplineList = masterDAO.getDisciplineDataListWithChecked();
		}else if(loginInfo.getShow_dept_opd_list().equals("0")) {
			UserProfile userProfile=userProfileDAO.getUserprofileDetails(loginInfo.getId());
			department=userProfile.getDiciplineName();
			notAvailableSlotForm.setDept(department);		
			disciplineList = masterDAO.getDisciplineDataListWithChecked();
		}
	}else {
		if ( loginInfo.getSuperadminid()==1 || loginInfo.getUserType()==2 || !loginInfo.getShow_dept_opd_list().equals("0") || loginInfo.getJobTitle().equals("PHYSIO RECEPTION")) {
			disciplineList = masterDAO.getDisciplineDataListWithChecked();
		}else if(loginInfo.getShow_dept_opd_list().equals("0")) {
			UserProfile userProfile=userProfileDAO.getUserprofileDetails(loginInfo.getId());
			department=userProfile.getDiciplineName();
			notAvailableSlotForm.setDept(department);		
			disciplineList = masterDAO.getDisciplineDataListWithChecked();
		}
		
		
	}
		if(orderby.equals("")) {
			orderby="desc";
		}
		
		   if(!department.equals("")){
				userList=notAvailableSlotDAO.getUserListwithdepartment(loginInfo.getId(),department);
			}else {
				userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
			}
			notAvailableSlotForm.setUserList(userList);
			notAvailableSlotForm.setDisciplineList(disciplineList);
			String searchtext=notAvailableSlotForm.getSearchText();


			if (date == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				date = dateFormat.format(cal.getTime());
				date = DateTimeUtils.getCommencingDate1(date);
			}else {

				if (date.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					date = dateFormat.format(cal.getTime());
					date = DateTimeUtils.getCommencingDate1(date);
				} else {
					date = DateTimeUtils.getCommencingDate1(date);
				}
		   }

			/*ArrayList<Master> deptWiseCountList = masterDAO.getDepartmentWisePatientCount(date);
			int finalNewPatientCount=0;
			int finalOldPatientCount=0;
			int finalTotalPatientCount=0;
			
			if(deptWiseCountList.size()>0){
				finalNewPatientCount = deptWiseCountList.get(deptWiseCountList.size()-1).getFinalNewPatientCount();
				finalOldPatientCount = deptWiseCountList.get(deptWiseCountList.size()-1).getFinalOldPatientCount();
				finalTotalPatientCount = deptWiseCountList.get(deptWiseCountList.size()-1).getFinalTotalPatientCount();
			}
			notAvailableSlotForm.setDeptWiseCountList(deptWiseCountList);
			notAvailableSlotForm.setFinalNewPatientCount(finalNewPatientCount);
			notAvailableSlotForm.setFinalOldPatientCount(finalOldPatientCount);
			notAvailableSlotForm.setFinalTotalPatientCount(finalTotalPatientCount);*/
			
			int count = finderDAO.getcountOfDepartmentOPD(department,date,category,orderby);
			pagination.setPreperties(count);
			ArrayList<NotAvailableSlot>physioIPDList = finderDAO.PhysioIPDList(department,date,pagination,category,orderby,loginInfo,searchtext);
			pagination.setTotal_records(physioIPDList.size());
			notAvailableSlotForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
			notAvailableSlotForm.setTotalRecords(count);
			
			notAvailableSlotForm.setDepartmentOPDList(physioIPDList);
			notAvailableSlotForm.setSelectdepartment(department);
			notAvailableSlotForm.setFromDate(DateTimeUtils.getCommencingDate1(date));
			notAvailableSlotForm.setPatientcategory(category);
			notAvailableSlotForm.setOrderby(orderby);
		
			
			/*ArrayList<NotAvailableSlot> additionalChargeList = finderDAO.getAdditionalChargeTypeList();
			notAvailableSlotForm.setAdditionalChargeListNew(additionalChargeList);*/
			
			ArrayList<NotAvailableSlot> tpNameList = finderDAO.gettpNameListAll();
			notAvailableSlotForm.setTpNameList(tpNameList);
			
			ArrayList<SittingFollowup>sittinglist=sittingDAO.getsittinglist();
			notAvailableSlotForm.setSittinglist(sittinglist);
			
			ArrayList<Master>procedurelist=masterDAO.getprocedurelist();
			notAvailableSlotForm.setProcedurelist(procedurelist);
			
			ArrayList<Master> physioIpdHospitalList = masterDAO.getPhysioIpdhospitalList();
			notAvailableSlotForm.setPhysioipdhospitallist(physioIpdHospitalList);
			
    }catch(Exception e){
		e.printStackTrace();
	}finally{
		
		connection.close();
	}
	
	return "physioipd";
}


public String saveipdsitting()throws Exception{
  Connection connection=null;
try {
	 connection=Connection_provider.getconnection();
	 FinderDAO finderDAO=new JDBCFinderDAO(connection);
	 
	    String deptopdid=request.getParameter("deptopdid");
	    String clientid=request.getParameter("clientid");
		String department=request.getParameter("department");
		String sitting=request.getParameter("sitting");
		String date=request.getParameter("date");
	    //String followup = request.getParameter("followup");
	    String remark=request.getParameter("remark");
		String proceduremasterlist=request.getParameter("procedure");
	    String sittingnum=request.getParameter("sittingnum");
		String procedurelist=request.getParameter("procedureid");
		String diagnosis=request.getParameter("diagnosis");
		String bed_ward=request.getParameter("bed_ward");
		String Hosp_name=request.getParameter("Hosp_name");
		String userid=loginInfo.getUserId();
		String date_time = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		
		String invoiceid=finderDAO.getChargesInvoiceid(clientid);
	    NotAvailableSlot notAvailableSlot=new NotAvailableSlot();
	   
		notAvailableSlot.setClientId(clientid);
		notAvailableSlot.setDepartment(department);
		notAvailableSlot.setSitting(sitting);
		notAvailableSlot.setDate(date);
	   // notAvailableSlot.setSittingFollowup(Boolean.parseBoolean(followup));
	    notAvailableSlot.setRemark(remark);
	    notAvailableSlot.setAll_procedure(proceduremasterlist);
	    notAvailableSlot.setProcedure_list(procedurelist);
	    notAvailableSlot.setUser_id(userid);
	    notAvailableSlot.setDate_time(date_time);
	    notAvailableSlot.setDeptOpdId(Integer.parseInt(deptopdid));
	    notAvailableSlot.setSittingnum(sittingnum);
	    notAvailableSlot.setDiagnosisarea(diagnosis);
	    notAvailableSlot.setBed_ward(bed_ward);
	    notAvailableSlot.setHosp_name(Hosp_name);
	    notAvailableSlot.setInvoiceid(invoiceid);
	    int result=finderDAO.saveIpdsittingdata(notAvailableSlot);
	    
	
} catch (Exception e) {
	e.printStackTrace();
}finally {
	connection.close();
}
return null;
}

public String physioipdpreview() throws Exception {
	Connection connection=null;
	try {
		
		connection = Connection_provider.getconnection();
		FinderDAO finderDAO = new JDBCFinderDAO(connection);
		MasterDAO masterDAO=new JDBCMasterDAO(connection);
		NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
		ArrayList<Master> disciplineList =  new ArrayList<Master>();
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
		String department=DateTimeUtils.isNull(notAvailableSlotForm.getDept());
		//String department=DateTimeUtils.isNull(request.getParameter("referto"));
		/*
		 * if ( loginInfo.getSuperadminid()==1 || loginInfo.getUserType()==2 ||
		 * !loginInfo.getShow_dept_opd_list().equals("0") ) { disciplineList =
		 * masterDAO.getDisciplineDataListWithChecked();
		 * 
		 * }else if(loginInfo.getShow_dept_opd_list().equals("0")) { UserProfile
		 * userProfile=userProfileDAO.getUserprofileDetails(loginInfo.getId());
		 * department=userProfile.getDiciplineName();
		 * notAvailableSlotForm.setDept(department); disciplineList =
		 * masterDAO.getDisciplineDataListWithChecked(); }
		 */
		
		if(!loginInfo.isBalgopal()) {	
			if ( loginInfo.getSuperadminid()==1 || loginInfo.getUserType()==2 || !loginInfo.getShow_dept_opd_list().equals("0") ) {
				disciplineList = masterDAO.getDisciplineDataListWithChecked();
			}else if(loginInfo.getShow_dept_opd_list().equals("0")) {
				UserProfile userProfile=userProfileDAO.getUserprofileDetails(loginInfo.getId());
				department=userProfile.getDiciplineName();
				notAvailableSlotForm.setDept(department);		
				disciplineList = masterDAO.getDisciplineDataListWithChecked();
			}
		}else {
			if ( loginInfo.getSuperadminid()==1 || loginInfo.getUserType()==2 || !loginInfo.getShow_dept_opd_list().equals("0") || loginInfo.getJobTitle().equals("PHYSIO RECEPTION")) {
				disciplineList = masterDAO.getDisciplineDataListWithChecked();
			}else if(loginInfo.getShow_dept_opd_list().equals("0")) {
				UserProfile userProfile=userProfileDAO.getUserprofileDetails(loginInfo.getId());
				department=userProfile.getDiciplineName();
				notAvailableSlotForm.setDept(department);		
				disciplineList = masterDAO.getDisciplineDataListWithChecked();
			}
			
			
		}
		notAvailableSlotForm.setDisciplineList(disciplineList);
		
		ArrayList<DiaryManagement>userList=new ArrayList<DiaryManagement>();
		
		if(!department.equals("")){
			userList=notAvailableSlotDAO.getUserListwithdepartment(loginInfo.getId(),department);
		}else {
			userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
		}
		
		ArrayList<DiaryManagement> secondaryuserList=userProfileDAO.getAllUserListOfSecondary(department);
		ArrayList<Client> thirdPartyTypeNameList = clientDAO.getThirdPartyTypeName();
		notAvailableSlotForm.setThirdPartyTypeNameList(thirdPartyTypeNameList);
		notAvailableSlotForm.setUserList(userList);
		notAvailableSlotForm.setSecondaryuserList(secondaryuserList);
		String category=DateTimeUtils.isNull(notAvailableSlotForm.getPatientcategory());
		String fromDate=notAvailableSlotForm.getFromDate();
		String toDate=notAvailableSlotForm.getToDate();
		String patienttype=DateTimeUtils.isNull(notAvailableSlotForm.getPatienttype());
		String primarydoc=DateTimeUtils.isNull(notAvailableSlotForm.getDiaryUser());
		String secondarydoc=DateTimeUtils.isNull(notAvailableSlotForm.getSecondarydoc());
		String referto=DateTimeUtils.isNull(notAvailableSlotForm.getReferto());
		String orderby=DateTimeUtils.isNull(notAvailableSlotForm.getOrderby());
		if(orderby.equals("")) {
			orderby="desc";
		}
		if (fromDate == null) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			fromDate = dateFormat.format(cal.getTime());
			fromDate = DateTimeUtils.getCommencingDate1(fromDate);
		} else {

			if (fromDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromDate = dateFormat.format(cal.getTime());
				fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			} else {
				fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			}
		}

		if (toDate == null) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			toDate = dateFormat.format(cal.getTime());
			toDate = DateTimeUtils.getCommencingDate1(toDate);
		} else {
			if (toDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				toDate = dateFormat.format(cal.getTime());
				toDate = DateTimeUtils.getCommencingDate1(toDate);
			} else {
				toDate = DateTimeUtils.getCommencingDate1(toDate);
			}
		}
		
		
		String searchText = notAvailableSlotForm.getSearchText();
		
		if(searchText!=null){
			if(searchText.equals("")){
				searchText=null;
			}
		}
		int count=0;
	    count=finderDAO.getcountOfDepartmentIPDPreview(patienttype,department,fromDate,toDate,category,secondarydoc,primarydoc,referto,orderby);
		//int count=0;
		pagination.setPreperties(count);
		ArrayList<NotAvailableSlot>departmentIPDPreviewList=new ArrayList<NotAvailableSlot>();
		ArrayList<NotAvailableSlot> mainArrayList =new ArrayList<>();
		mainArrayList=finderDAO.getdepartmentIpdList(patienttype,department,fromDate,toDate,pagination,category,secondarydoc,primarydoc,referto,orderby,searchText,loginInfo);
			 //departmentIPDPreviewList = finderDAO.departmentIPDPreviewList(patienttype,department,fromDate,toDate,pagination,category,secondarydoc,primarydoc,referto,orderby,searchText,loginInfo);
			 //notAvailableSlotForm.setDepartmentIPDPreviewList(departmentIPDPreviewList);
			
	
		
		//ArrayList<NotAvailableSlot>departmentIPDPreviewList = finderDAO.departmentIPDPreviewList(patienttype,department,fromDate,toDate,pagination,category,secondarydoc,primarydoc,referto,orderby,searchText,loginInfo);
		
		/*
		 * String secondary_dr2=""; if(departmentIPDPreviewList.size()!=0) {
		 * NotAvailableSlot
		 * notAvailableSlot=departmentIPPreviewList.get(departmentOPDPreviewList.size()-
		 * 1); String secondary_dr=notAvailableSlot.getOpdappoinmentid(); String temp[]
		 * = secondary_dr.split(","); for (String string : temp) {
		 * if(!string.equals(" ")){ String data=string; if(secondary_dr2.equals("")){
		 * secondary_dr2=data; }else{ secondary_dr2=secondary_dr2+", "+data; } } }
		 * 
		 * }
		 */
		   
	//	ArrayList<NotAvailableSlot> secondarydoctorlist=finderDAO.getSecondaryData(secondary_dr2);
		ArrayList<NotAvailableSlot>seconddrlist=new ArrayList<NotAvailableSlot>();
		int i=0;
		/*
		 * for (NotAvailableSlot n1 : departmentOPDPreviewList) { NotAvailableSlot
		 * n2=secondarydoctorlist.get(i);
		 * 
		 * n1.setSecondarydoc(n2.getSecondarydoc());
		 * 
		 * i++; seconddrlist.add(n1);
		 * 
		 * 
		 * }
		 */
		//pagination.setTotal_records(departmentOPDPreviewList.size());
		notAvailableSlotForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
		notAvailableSlotForm.setTotalRecords(count);
		
		//notAvailableSlotForm.setDepartmentIPDPreviewList(departmentIPDPreviewList);
		notAvailableSlotForm.setDepartmentIPDList(mainArrayList);
		notAvailableSlotForm.setSelectdepartment(department);
		notAvailableSlotForm.setFromDate(DateTimeUtils.getCommencingDate1(fromDate));
		notAvailableSlotForm.setToDate(DateTimeUtils.getCommencingDate1(toDate));
		notAvailableSlotForm.setPatientcategory(category);
		notAvailableSlotForm.setPatienttype(patienttype);
		notAvailableSlotForm.setOrderby(orderby);
		Clinic clinic = new Clinic();
		ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
		clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
		notAvailableSlotForm.setClinicName(clinic.getClinicName());
		notAvailableSlotForm.setClinicOwner(clinic.getClinicOwner());
		notAvailableSlotForm.setOwner_qualification(clinic.getOwner_qualification());
		notAvailableSlotForm.setLandLine(clinic.getLandLine());
		notAvailableSlotForm.setClinicemail(clinic.getEmail());
		notAvailableSlotForm.setWebsiteUrl(clinic.getWebsiteUrl());

		notAvailableSlotForm.setClinicLogo(clinic.getUserImageFileName());
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		connection.close();
	}
	return "physiopreview";
}



public String updateVaccdatevet() {
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		FinderDAO finderDAO = new JDBCFinderDAO(connection);
		
		String duedate=request.getParameter("duedate");
		String id=request.getParameter("dueid");
		//notAvailableSlotForm.setDatecolor(id);
		duedate=DateTimeUtils.getCommencingDate1(duedate);
		String mastername=request.getParameter("mastername");
		String clientid=request.getParameter("clientid");
		String action=request.getParameter("action");
		int update=finderDAO.updateVaccduedateforvet(duedate,mastername,clientid,action);
		
		  response.setContentType("text/html");
		  response.setHeader("Cache-Control", "no-cache"); 
		  response.getWriter().write(""+id+"");
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	System.out.println("hello");
	return null;
}

public String insertFollowup() {
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		FinderDAO finderDAO = new JDBCFinderDAO(connection);
		String clientId=request.getParameter("clientId");
		String mastername=request.getParameter("mastername");
		String duedate=request.getParameter("duedate");
		String action=request.getParameter("action");
		NotAvailableSlot notAvailableSlot=finderDAO.getPetmedcininedataFollowup(clientId,mastername,action);
		notAvailableSlot.setDuedate(DateTimeUtils.getCommencingDate1(duedate));
		int id=finderDAO.insertMedicinefollowup(notAvailableSlot,action);
		int update=finderDAO.updateFollowupid(notAvailableSlot,action,id);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
   return null;
}

}

