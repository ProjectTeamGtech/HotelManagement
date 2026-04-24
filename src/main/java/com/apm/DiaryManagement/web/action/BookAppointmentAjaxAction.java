
package com.apm.DiaryManagement.web.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.tiles.context.MapEntry;

import com.a.a.a.g.m.m;
import com.a.a.a.g.m.n;
import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.AdditionalDAO;
import com.apm.Accounts.eu.bi.ChargesAccountProcessingDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAdditionalDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCChargeAccountProcesDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.bi.AppointmentDAO;
import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.Diagnosis.eu.bi.DiagnosisDAO;
import com.apm.Diagnosis.eu.blogic.jdbc.JDBCDiagnosisDAO;
import com.apm.Diagnosis.eu.entity.Diagnosis;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.bi.FinderDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCCompleteAptmDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCFinderDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Dietary.eu.bi.DietaryDetailsDAO;
import com.apm.Dietary.eu.blogic.jdbc.JDBCDietaryDetailsDAO;
import com.apm.Dietary.eu.entity.DietaryDetails;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.bi.PrescriptionDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCPrescriptionDAO;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Inventory.eu.bi.IndentDAO;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.bi.ProcurementDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCIndentDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCProcurementDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Ipd.eu.entity.Ipd;
import com.apm.Log.eu.bi.AccountLogDAO;
import com.apm.Log.eu.blogic.jdbc.JDBCAccountLogDAO;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Master.eu.bi.InvestigationMasterDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.PackageMasterDAO;
import com.apm.Master.eu.bi.PrescriptionMasterDAO;
import com.apm.Master.eu.bi.SittingFollowupDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCInvestigationMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCPackageMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCPrescriptionMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCSittingFollowupDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.PackageMaster;
import com.apm.Master.eu.entity.SittingFollowup;
import com.apm.Pacs.eu.bi.PacsDAO;
import com.apm.Pacs.eu.blogic.JDBCPacsDAO;
import com.apm.Pacs.eu.entity.Pacs;
import com.apm.Pharmacy.eu.bi.PharmacyDAO;
import com.apm.Pharmacy.eu.blogic.jdbc.JDBCPharmacyDAO;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
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
import com.apm.common.utils.CommonOpdIpdReport;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;
import atg.taglib.json.util.JSONObject;


public class BookAppointmentAjaxAction extends BaseAction {

	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse) ActionContext.getContext()
			.get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	private static final Logger TestingLog = Logger.getLogger(BookAppointmentAjaxAction.class);
	public String rdd(){
		String val = request.getParameter("val");
		session.setAttribute("sessionrddval", val);
		return null;
	}
	
	
	public String media(){
		
		return "opdmedia";
	}
	
	public String vdyo(){
		
		try {
			
			response.sendRedirect("./VidyoClient-WebSDK/samples/VidyoConnector/js/VidyoConnector.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
		
	
	public String dsplay() {

		if (!verifyLogin(request)) {
			return "login";
		}

		session.setAttribute("openedb", "dsplay");

		return "dsplay";
	}


	public String opd() {

		if (!verifyLogin(request)) {
			return "login";
		}

		session.setAttribute("openedb", "opd");

		return "opd";
	}

	public String otdb() {

		if (!verifyLogin(request)) {
			return "login";
		}

		session.setAttribute("openedb", "otdb");
		session.removeAttribute("sessionrddval");
		return "ot";
	}
	
	public String updmveapmt(){
		String duserid = request.getParameter("duserid");
		String commencing = request.getParameter("commencing");
		String stime = request.getParameter("stime");
		String endtime = request.getParameter("endtime");
		String duration = request.getParameter("duration");
		String editAppointId = request.getParameter("editAppointId");
		String apmttype = request.getParameter("apmttype");
//		System.out.println("hello");
		
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			
			NotAvailableSlot n = new NotAvailableSlot();
			AdditionalDAO additionalDAO=new JDBCAdditionalDAO(connection);
			n.setDiaryUserId(Integer.parseInt(duserid));
			commencing = DateTimeUtils.getCommencingDate(commencing);
			n.setCommencing(commencing);
			n.setSTime(stime);
			n.setEndTime(endtime);
			n.setDuration(duration);
			n.setAppointmentid(Integer.parseInt(editAppointId));
			n.setApmtType(apmttype);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile up = userProfileDAO.getUserprofileDetails(Integer.parseInt(duserid));
			AppointmentType appointmentType=additionalDAO.getAppointmentTypeDetails(apmttype);
			n.setApmttypetext(appointmentType.getName());
			n.setCharge(Double.parseDouble(appointmentType.getCharges()));
			String dname = up.getInitial() + " " + up.getFirstname() + " " + up.getLastname();
			n.setDiaryUser(dname);
			n.setDept(up.getDiciplineName());
						NotAvailableSlot t = notAvailableSlotDAO.getMveDiaryUserDetails(duserid,commencing);
			n.setId(t.getId());
			
			NotAvailableSlot apmtlog= notAvailableSlotDAO.getApmtDetailsForLog(DateTimeUtils.convertToInteger(editAppointId));
			apmtlog.setCurrentDate(n.getCommencing());
			apmtlog.setId(DateTimeUtils.convertToInteger(editAppointId));
            n.setClientId(apmtlog.getClientId());
            n.setClientName(apmtlog.getClientName());
            int r=0;
            
            //this code is done for markhosp as per their requirement on 25/03/2025 
            
			NotAvailableSlot apmt=(NotAvailableSlot) session.getAttribute("previousapmtdata");
			if(loginInfo.isMarkhosp()) {
				
				String chargeinvoiceid=notAvailableSlotDAO.getInvoice_id(apmt);
				if((apmt.getCharge() > n.getCharge()) || (apmt.getCharge() < n.getCharge())) {
					double chargediff=apmt.getCharge()-n.getCharge();
					
				
				  
				  //int apminv=notAvailableSlotDAO.saveaApmInvoiceData(apmt,appointmentid);
				  apmt.setPayBy("0");
				  apmt.setCharge(chargediff);
				  //int apminvass=notAvailableSlotDAO.saveApmInvoiceAssesmentformark(apmt, apminv, appointmentid);
				  n.setInvoiceid(chargeinvoiceid);
				  int delete=notAvailableSlotDAO.deleteAppointment(apmt.getId());
				  n.setAddedBy(loginInfo.getUserId());
				  int appointmentid = notAvailableSlotDAO.saveAppointment1(n);
				  //r = notAvailableSlotDAO.updatemveappointment(n);
					
				
				}else {
					
					
					   r = notAvailableSlotDAO.updatemveappointment(n);
						
						if(r>0){
							notAvailableSlotDAO.addApmtChangeLog(apmtlog);
						}
						
						//update apm_invoice
						int u = notAvailableSlotDAO.updatemveapminvoice(editAppointId,n);
						
						Accounts a = notAvailableSlotDAO.getmveapmtchargeinfo(editAppointId);
						
						// update invoice assesment

						u = notAvailableSlotDAO.updatemveapminvoiceassesmnt(a.getId(),n);
						
						// update charges invoice
						u = notAvailableSlotDAO.updatemveapmtchargesinvoice(a.getInvoiceid(),n);
				}
				
			}else {
			
			
		
			
			   r = notAvailableSlotDAO.updatemveappointment(n);
			
			if(r>0){
				notAvailableSlotDAO.addApmtChangeLog(apmtlog);
			}
			
			//update apm_invoice
			int u = notAvailableSlotDAO.updatemveapminvoice(editAppointId,n);
			
			Accounts a = notAvailableSlotDAO.getmveapmtchargeinfo(editAppointId);
			
			// update invoice assesment

			u = notAvailableSlotDAO.updatemveapminvoiceassesmnt(a.getId(),n);
			
			// update charges invoice
			u = notAvailableSlotDAO.updatemveapmtchargesinvoice(a.getInvoiceid(),n);
		  }	
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public String mveapmt(){
		
		String duserid = request.getParameter("duserid");
		String commencing = request.getParameter("commencing");
		
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			   AppointmentTypeDAO appointmentTypeDAO=new JDBCAppointmentTypeDAO(connection);
			commencing = DateTimeUtils.getCommencingDate(commencing);
			
			 ArrayList<AppointmentType> appointmentTypeList=new ArrayList<AppointmentType>();
			
			NotAvailableSlot n = notAvailableSlotDAO.getMveDiaryUserDetails(duserid,commencing);
			
			String stime = n.getSTime();
			int slotid = n.getId();
			String duration = n.getDuration();
			boolean chkapmtexsist = notAvailableSlotDAO.chkmveapmtaxsist(duserid,commencing);
			if(chkapmtexsist){
				stime = notAvailableSlotDAO.getmveapmtendtime(duserid,commencing);
			}
			
			if(loginInfo.isSaimed()) {
			appointmentTypeList = appointmentTypeDAO.gettpAppointmentTypeList("", "Client",false,"0",loginInfo,duserid,0);

			}
			

			StringBuffer str1 = new StringBuffer();

			str1.append("<select onchange='setAppointmentTypeTimeAjax(this.value)' name='duration' id='apmtType12' class='form-control showToolTip chosen' >");
			str1.append("<option value='0'>Select Appointment Type</option>");
			for (AppointmentType appointmentType : appointmentTypeList) {
				str1.append("<option value='" + appointmentType.getId() + "'>"
						+ appointmentType.getName() + "</option>");
			}
			str1.append("</select>");
		
			String str = slotid + "~" + stime + "~" + duration+"~"+str1.toString();
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str.toString());

			
			
		}catch (Exception e) {
			// TODO: handle exception
	
			}

		
		return null;
	}
	public String newdisplay(){
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		
		
		String ndate = request.getParameter("ndate");
		String nduserid = request.getParameter("nduserid");
		String opdlogstatus = request.getParameter("opdstatus");
		String patientcategory=DateTimeUtils.isNull(request.getParameter("patientcategory"));
		
		String opendb=(String)session.getAttribute("openedb");
		loginInfo.setOpendb(opendb);
		if(!opdlogstatus.equals("")){
		if(opdlogstatus.equals("3")){
			session.setAttribute("orderapptt",opdlogstatus);
		}else {
			session.removeAttribute("orderapptt");
		}
		if(session.getAttribute("orderapptt")!=null){
			opdlogstatus=(String)session.getAttribute("orderapptt");
		}
		}
		if(loginInfo.isLmh()) {
		if(!patientcategory.equals("")){
				session.setAttribute("patientcategory",patientcategory);
			if(session.getAttribute("patientcategory")!=null || !session.getAttribute("patientcategory").equals("")){
				patientcategory=(String)session.getAttribute("patientcategory");
			}
			}
		}
		int t = 1;
		try{
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			//Opd dashboard display data
			if(!ndate.equals("")){
			ndate = DateTimeUtils.getCommencingDate(ndate);	
			}else{
				Date date = new Date();
				 ndate= new SimpleDateFormat("yyyy-MM-dd").format(date);
			}
			ArrayList<NotAvailableSlot>opdlist = notAvailableSlotDAO.getNewOpdList(ndate,nduserid,loginInfo,opdlogstatus,patientcategory);
			
			StringBuffer str = new StringBuffer();
			int i = 1;
			StringBuffer buffer=new StringBuffer();
			UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
			ChargesReportDAO chargesReportDAO=new JDBCChargesReportDAO(connection);
			int undonecount=notAvailableSlotDAO.getUndoneAppointment(nduserid,ndate);
			String opdicon="";
			if(loginInfo.isOpd_telemed()){
				for(NotAvailableSlot n : opdlist){
					UserProfile userProfile=profileDAO.getUserprofileDetails(n.getDiaryUserId());
					String descriptionname=profileDAO.getdescriptionName(userProfile.getDiciplineName());
					
					session.setAttribute("descriptionname", descriptionname);
					boolean checkattachment=notAvailableSlotDAO.checkAttachment(n.getId());
					opdicon="&nbsp;&nbsp;<a><i class='fa fa-user fa-2x' title='View Profile' style='color: black;' onclick=openSamll('puresevaprofileClient?id="+n.getClientId()+"')> </i></a>";
							/*+ "&nbsp;&nbsp;<a href='#' onclick='openCancelApmtPopupNEW("+n.getId()+")'><i class='fa fa-times fa-2x' aria-hidden='true' title='Cancel Appointment' style='color: black;'></i></a>";*/
					String color = "";
					String textcolor="";
					String remarkicon="";
					String paymentsts="Unpaid";
					String opdapptsts="Request";
					String vidicon="";
					String scanqr="";
					String cancelapmt="";
					String chaticon="";
					int popupstatus = 0;
					if(n.getStatus().equals("0")){
						color = "rgb(252, 186, 99)";
						popupstatus = 1;
						if(loginInfo.getUserType()==5){
							popupstatus = 5;
							paymentsts="Unpaid";
							opdapptsts="Request";
//							opdicon="&nbsp;&nbsp;<a href='#' onclick='openscanpopup("+n.getId()+")'><i class='fa fa-qrcode fa-2x' aria-hidden='true' style='color: black;' title='Payment'></i></a>"
//									+ "&nbsp;&nbsp;<a href='#' onclick='openCancelApmtPopupNEW("+n.getId()+")'><i class='fa fa-times fa-2x' aria-hidden='true' style='color: black;' title='Cancel Appointment'></i></a>";
							scanqr="<a href='#' onclick='openscanpopup("+n.getId()+")'><i class='fa fa-qrcode fa-2x' aria-hidden='true' style='color: black;' title='Payment'></i></a>";
							cancelapmt="<a href='#' onclick='openCancelApmtPopupNEW("+n.getId()+")'><i class='fa fa-times fa-2x' aria-hidden='true' style='color: black;' title='Cancel Appointment'></i></a>";
//									+ "&nbsp;&nbsp;<a><i class='fa fa-edit fa-2x' style='color: black;'  onclick=openEditClientPrintPopup("+n.getClientId()+") title='Edit Profile'> </i></a>";
							
							if(!n.getPending_remark().equals("") || !n.getDoctor_vid_reject_remark().equals("") ){
//							opdicon=opdicon+" "+remarkicon;
								remarkicon="&nbsp;&nbsp;<a href='#' onclick='openremarkPopup("+n.getClientId()+","+n.getId()+")'><i class='fa fa-commenting fa-2x' aria-hidden='true' style='color: black;' title='Remark'></i></a>";
							}
						}
					}
					if(n.isAppointmentCompleted()){
						color = "rgb(129, 171, 109)";
						popupstatus = 2;
						if(loginInfo.getUserType()==5){
							popupstatus = 0;
						}
					}
					if(!n.getInvoiceid().equals("0")){
						color = "rgb(204, 204, 204)";
						
						popupstatus = 3;
						if(loginInfo.getUserType()==5){
							popupstatus = 6;
							paymentsts="Received";
							opdapptsts="Confirm";
							opdicon="";
							cancelapmt="";
//							vidicon = "&nbsp;&nbsp;<a href='#' onclick='openvideoconfirm("+n.getClientId()+","+n.getId()+")'><i class='fa fa-video-camera fa-2x' aria-hidden='true' style='color: black;'title='Confirm Video Call'></i></a>";
//							opdicon="&nbsp;&nbsp;<a><i class='fa fa-video-camera fa-2x' style='color: black;' onclick=openPopup('vdyoEmr?clid="+n.getClientId()+"&condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"') title='Video Call'> </i></a>";
									/*+ "&nbsp;&nbsp;<a href='#' onclick='openremarkPopup("+n.getClientId()+","+n.getId()+")'><i class='fa fa-commenting fa-2x' aria-hidden='true' style='color: black;' title='Remark'></i></a>";*/
//									+ "&nbsp;&nbsp;<a><i class='fa fa-user fa-2x' style='color: black;' onclick=openSamll('puresevaprofileClient?id="+n.getClientId()+"') title='View Profile'> </i></a>"
//									+ "&nbsp;&nbsp;<a><i class='fa fa-edit fa-2x' style='color: black;'  onclick=openEditClientPrintPopup("+n.getClientId()+") title='Edit Profile'> </i></a>";
							
							if(!n.getPending_remark().equals("") || !n.getDoctor_vid_reject_remark().equals("") ){
//							opdicon=opdicon+" "+remarkicon;
								remarkicon="&nbsp;&nbsp;<a href='#' onclick='openremarkPopup("+n.getClientId()+","+n.getId()+")'><i class='fa fa-commenting fa-2x' aria-hidden='true' style='color: black;' title='Remark'></i></a>";
							}
						}
					}
					if(n.getOpdpmnt()>0){
						color = "rgb(204, 204, 204)";
						if(loginInfo.getUserType()!=5){
						opdicon="&nbsp;&nbsp;<a><i class='fa fa-user fa-2x' onclick=openSamll('puresevaprofileClient?id="+n.getClientId()+"') title='View Profile'> </i></a>";
								/*+ "&nbsp;&nbsp;<a href='#' onclick='openCancelApmtPopupNEW("+n.getId()+")'><i class='fa fa-times fa-2x' aria-hidden='true' style='color: black;' title='Cancel Appointment'></i></a>";*/
						}
						}
					if(n.getDrcompleted()==1){
						
						color = "rgb(92, 241, 213)";
						
						popupstatus = 3;
						if(loginInfo.getUserType()==5){
							popupstatus = 0;
						}
					}
					
					if(n.getArrivedStatus()==1){
						textcolor="color: green !important;";
					}
					 if(n.getArrivedStatus()==2){
						textcolor="color: #bb0202f2 !important;";
					}
					 if(n.isDna()){
						 color = "rgb(255,0,0)";
						 popupstatus = 4;
						 if(loginInfo.getUserType()==5){
								popupstatus = 0;
							}	 
					 }
					 if(n.getApmttypetext().equals("OPD FREE") && Integer.parseInt(n.getInvoiceid())>0){
						 color = "rgb(204, 204, 204)";
							
							popupstatus = 3;
							if(n.getDrcompleted()==1){
								
								color = "rgb(92, 241, 213)";
								
								popupstatus = 3;
							}
							if(loginInfo.getUserType()==5){
								popupstatus = 0;
							}
					 }
					 String attachicon="";
					 if(loginInfo.getUserType()==5){
						 attachicon="&nbsp;&nbsp;<a href='#' onclick='openpatientattachment("+n.getClientId()+","+n.getId()+")'><i class='fa fa-paperclip fa-2x' aria-hidden='true' style='color: black;'title='Attachment'></i></a>";	 
					 }else{
						 if(checkattachment){
							 attachicon="&nbsp;&nbsp;<a href='#' onclick='openpatientattachment("+n.getClientId()+","+n.getId()+")'><i class='fa fa-paperclip fa-2x' aria-hidden='true' style='color: black;'title='Attachment'></i></a>";
							 opdicon=opdicon+" "+attachicon; 
						 }	 
					 }
//					 opdicon=opdicon+" "+attachicon; 
					 String newicon="";
					 if(n.getMobstatusnew()==1){
						 if(loginInfo.getUserType()!=5){
							 if(!loginInfo.getJobTitle().equals("Practitioner")){
								 newicon = "&nbsp;&nbsp;<a href='#' onclick='openbookingconfirmchecklist("+n.getClientId()+","+n.getId()+")'><i class='fa fa-list fa-2x' aria-hidden='true' style='color: black;'title='Booking Confirm'></i></a>";
							 }
						 }
					 }
					 
					 
					   int duserid  = profileDAO.getDiaryUserId(n.getAddedBy());
						UserProfile userProfile2 = profileDAO.getUserprofileDetails(duserid);
					 
					 
					if(loginInfo.isAppreception()){
										 if(userProfile2.isOpd_user_vid_access()){
										 if (loginInfo.isOpd_user_vid_access()) {
											 if(loginInfo.isDr_opd_vid()){
												 if(n.getDoctor_vid_verify()==1){
													 vidicon="&nbsp;&nbsp;<a><i class='fa fa-video-camera fa-2x' style='color: green;' onclick=openPopup('vdyoEmr?condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"') title='Video Call'> </i></a>";
													 chaticon ="&nbsp;&nbsp;<a><i class='fa fa-comment fa-2x' style='color: green;' onclick=openPopup('chatqbEmr?condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"') title='Chat'> </i></a>";
													 	}
												 	else{
													 		 
													 		vidicon = "&nbsp;&nbsp;<a href='#' onclick='openvideoconfirm("+n.getClientId()+","+n.getId()+")'><i class='fa fa-video-camera fa-2x' aria-hidden='true' style='color: black;'title='Confirm Video Call'></i></a>";
													 		chaticon ="&nbsp;&nbsp;<a><i class='fa fa-comment fa-2x' style='color: green;' onclick=openPopup('chatqbEmr?condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"') title='Chat'> </i></a>";
													 	}
												 }else{
													 if(n.getDoctor_vid_verify()==1){
														 vidicon="&nbsp;&nbsp;<a><i class='fa fa-video-camera fa-2x' style='color: green;' onclick=openPopup('vdyoEmr?clid="+n.getClientId()+"&condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"') title='Video Call'> </i></a>";
														 chaticon ="&nbsp;&nbsp;<a><i class='fa fa-comment fa-2x' style='color: green;' onclick=openPopup('chatqbEmr?condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"') title='Chat'> </i></a>";
													}else{
													 vidicon = "&nbsp;&nbsp;<a href='#'><i class='fa fa-video-camera fa-2x' aria-hidden='true' style='color: black;'title='Video Completed'></i></a>";
														 }
													}
												
											}
										 }
									 } else if(loginInfo.getUserType()!=5){
										 if(n.getMobstatusnew()==1){
											 if(n.getReception_vid_verify()==1){

												 if(n.getDoctor_vid_verify()==1){
//												 vidicon="&nbsp;&nbsp;<a title='Video Call' href='vdyoEmr?clid="+n.getClientId()+"&condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"' target='blank'><span class='glyphicon glyphicon-facetime-video'></span></a>";
												 vidicon="&nbsp;&nbsp;<a><i class='fa fa-video-camera fa-2x' style='color: green;' onclick=openPopup('vdyoEmr?condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"') title='Video Call'> </i></a>";
												 chaticon ="&nbsp;&nbsp;<a><i class='fa fa-comment' style='color: green;' onclick=openPopup('chatqbEmr?condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"') title='Chat'> </i></a>";
												 	}else if(n.getDoctor_vid_reject_remark()==""){
												 		vidicon = "&nbsp;&nbsp;<a href='#' onclick='openvideoconfirm("+n.getClientId()+","+n.getId()+")'><i class='fa fa-video-camera fa-2x' aria-hidden='true' style='color: black;'title='Confirm Video Call'></i></a>";
												 		chaticon ="&nbsp;&nbsp;<a><i class='fa fa-comment fa-2x' style='color: green;' onclick=openPopup('chatqbEmr?condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"') title='Chat'> </i></a>";
												 	}else{
												 		vidicon="";
												 	}
												 }
											 }
								else{
									 if(userProfile2.isOpd_user_vid_access()){
									 if (loginInfo.isOpd_user_vid_access()) {
										 if(loginInfo.isDr_opd_vid()){
											 if(n.getDoctor_vid_verify()==1){
												 vidicon="&nbsp;&nbsp;<a><i class='fa fa-video-camera fa-2x' style='color: green;' onclick=openPopup('vdyoEmr?condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"') title='Video Call'> </i></a>";
												 chaticon ="&nbsp;&nbsp;<a><i class='fa fa-comment fa-2x' style='color: green;' onclick=openPopup('chatqbEmr?condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"') title='Chat'> </i></a>";
												 	}
											 	else{
												 		 
												 		vidicon = "&nbsp;&nbsp;<a href='#' onclick='openvideoconfirm("+n.getClientId()+","+n.getId()+")'><i class='fa fa-video-camera fa-2x' aria-hidden='true' style='color: black;'title='Confirm Video Call'></i></a>";
												 		chaticon ="&nbsp;&nbsp;<a><i class='fa fa-comment fa-2x' style='color: green;' onclick=openPopup('chatqbEmr?condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"') title='Chat'> </i></a>";
												 	}
											 }else{
												 if(n.getDoctor_vid_verify()==1){
													 vidicon="&nbsp;&nbsp;<a><i class='fa fa-video-camera fa-2x' style='color: green;' onclick=openPopup('vdyoEmr?clid="+n.getClientId()+"&condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"') title='Video Call'> </i></a>";
													 chaticon ="&nbsp;&nbsp;<a><i class='fa fa-comment fa-2x' style='color: green;' onclick=openPopup('chatqbEmr?condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"') title='Chat'> </i></a>";
												}else{
												 vidicon = "&nbsp;&nbsp;<a href='#'><i class='fa fa-video-camera fa-2x' aria-hidden='true' style='color: black;'title='Schedule Pending'></i></a>";
													 }
												}
											
										}
									 }
								 }
							 } else{
						 if(n.getMobstatusnew()==1){
							 if(n.getReception_vid_verify()==1){

								 if(n.getDoctor_vid_verify()==1){
									 opdapptsts="Dr.Confirm";
									 vidicon="&nbsp;&nbsp;<a><i class='fa fa-video-camera fa-2x' style='color: green;' onclick=openPopup('vdyoEmr?clid="+n.getClientId()+"&condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"') title='Video Call'> </i></a>";
									 chaticon ="&nbsp;&nbsp;<a><i class='fa fa-comment fa-2x' style='color: green;' onclick=openPopup('chatqbEmr?clid="+n.getClientId()+"&condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"') title='Chat'> </i></a>";
								 }
								 }
								 
								 }
					 }
					 if(n.isDna()){
						 //when DNA set then hide video icon
						 vidicon="";
					 }
					 
					 if(n.getDrcompleted()==1){
						//when appointment complted then hide video icon
						 if(userProfile2.isOpd_user_vid_access()){
							 vidicon = "&nbsp;&nbsp;<a href='#'><i class='fa fa-video-camera fa-2x' aria-hidden='true' style='color: brown; title='Wait for Confirmation'></i></a>";
							 chaticon ="&nbsp;&nbsp;<a><i class='fa fa-comment fa-2x' style='color: green;' onclick=openPopup('chatqbEmr?condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"') title='Chat'> </i></a>";
						 }else{
							 vidicon="";
						 }
					 }
					 if(!loginInfo.isOpd_chat()){
						 chaticon="";
					 }
					 vidicon=vidicon+" "+chaticon;
					 opdicon=opdicon+" "+newicon+ " "+vidicon; 
					 
					 if( loginInfo.getUserType()!=5){
						 if(!loginInfo.isOpd_video_icon_show()){
						 opdicon="";
						 }
					 }
					 if(loginInfo.getUserType()!=5){
						 if(loginInfo.isSaimed()){
							 str.append("<tr style='background-color:"+color+";' >");
							 str.append("<td class='notranslate' style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+i+"</td>");
							 str.append("<td class='notranslate' style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+n.getAbrivationid()+"</td>");//uhid
							if(loginInfo.isSjivh()) {
							 str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+n.getFullname()+"</td>");//patient name
	
							}else {
							 str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+n.getClientName()+"</td>");//patient name
							}
							 str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+n.getToken()+"</td>");
							 if(!loginInfo.isParihar()){
							 str.append("<td>"+opdicon+"</td>");//icon
							 }
							 str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+n.getAgegender()+"</td>");//age gender
							 if(!loginInfo.getOpendb().equals("otdb")) {
							 str.append("<td class='notranslate' style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+/*n.getId()*/n.getOpdAbbrId()+"</td>");//opdid
							 }
							 if(DateTimeUtils.isNull(n.getOpdbooktime()).equals("")){
									str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'></td>");	
								}else{
								str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+DateTimeUtils.getdatewithmonth(n.getOpdbookdate())+" "+n.getOpdbooktime().split(":")[0]+":"+n.getOpdbooktime().split(":")[1]+"</td>");
								}
							str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+DateTimeUtils.getdatewithmonth(DateTimeUtils.getCommencingDate1(n.getCommencing()))+" "+n.getsTime()+"</td>");//app date time
							str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+userProfile2.getFullname()+"</td>");//book by
							str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+n.getDuration()+"</td>");//duration
							str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+n.getApmttypetext()+"</td>");//appoint name
							str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+n.getPstatus()+"</td>");
							  }
						 if(!loginInfo.isSaimed()){
						    str.append("<tr style='background-color:"+color+";' >");
							str.append("<td class='notranslate' style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+i+"</td>");//srno
							str.append("<td class='notranslate' style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+/*n.getId()*/n.getOpdAbbrId()+"</td>");//opdid
							if(DateTimeUtils.isNull(n.getOpdbooktime()).equals("")){
								str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+DateTimeUtils.getCommencingDate1(n.getOpdbookdate())+"</td>");	
							}else{
							str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+DateTimeUtils.getdatewithmonth(n.getOpdbookdate())+" "+n.getOpdbooktime().split(":")[0]+":"+n.getOpdbooktime().split(":")[1]+"</td>");
							}
							str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+DateTimeUtils.getdatewithmonth(DateTimeUtils.getCommencingDate1(n.getCommencing()))+" "+n.getsTime()+"</td>");//app date time
							str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+n.getDuration()+"</td>");//duration
							str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+userProfile2.getFullname()+"</td>");//book by
							str.append("<td class='notranslate' style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+n.getAbrivationid()+"</td>");//uhid
//							str.append("<td style='text-align: left;'>"+n.getClientName()+" "+opdicon+"</td>");
							str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+n.getClientName()+"</td>");//patient name
							
							str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+n.getAgegender()+"</td>");//age gender
							str.append("<td>"+opdicon+"</td>");
							str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+n.getApmttypetext()+"</td>");//appoint name
						 }
						 
						
					    	str.append("<td>");
					    	if(descriptionname.equalsIgnoreCase("Ophthalmology")) {
					    		//for demo @rahul
						    	str.append("&nbsp;&nbsp;<a><i class='fa fa-keyboard-o fa-2x' onclick=openPopup('consultationNotesCommonnew?clientId="+n.getClientId()+"&apmtId="+n.getId()+"') title='CONSULTATION NOTES'> </i></a>");

					    	}else {
						    	str.append("&nbsp;&nbsp;<a><i class='fa fa-keyboard-o fa-2x' onclick=openPopup('consultationNotesCommonnew?clientId="+n.getClientId()+"&apmtId="+n.getId()+"') title='CONSULTATION NOTES'> </i></a>");
	
					    	}
					    	//str.append("&nbsp;&nbsp;<a><i class='fa fa-keyboard-o fa-2x' onclick=openPopup('consultationNotesCommonnew?clientId="+n.getClientId()+"&apmtId="+n.getId()+"') title='CONSULTATION NOTES'> </i></a>");
					     if(descriptionname.equalsIgnoreCase("Ophthalmology")) {
					    	str.append("&nbsp;&nbsp;<a><i class='fa fa-print fa-2x' onclick=openPopup('printophtomoConsultationFormCommonnew?clientId="+n.getClientId()+"&apmtId="+n.getId()+"') title='Print'> </i></a>");
					    }else {
					    	str.append("&nbsp;&nbsp;<a><i class='fa fa-print fa-2x' onclick=openPopup('printConsulatationFormCommonnew?clientId="+n.getClientId()+"&apmtId="+n.getId()+"') title='Print'> </i></a>");

					     }	 
					     //str.append("&nbsp;&nbsp;<a><i class='fa fa-history fa-2x' onclick=openPopup('historyConsulatationFormCommonnew?clientId="+n.getClientId()+"&apmtId="+n.getId()+"') title='History'> </i></a>");
			 		    	str.append("&nbsp;&nbsp;<a href='#' onclick='historyConsulatationForm("+n.getClientId()+","+n.getId()+")'><i class='fa fa-history fa-2x'title='History'></i></a>");
			 		    	//for ayushman
			 		    	str.append("&nbsp;&nbsp;<a href='#' onclick='showLetterHeadforayush("+n.getId()+","+n.getClientId()+")')'><i class='fa fa-print fa-2x'title='Print Letterhead'></i></a>");

			 		    	
					    	str.append("</td>");
					  
						/*str.append("<td style='text-align: left;'><a href='#'>Day_To_Day</a></td>");*/
						str.append("<input type = 'hidden' name='ncname' id='ncname"+i+"' value='"+n.getClientName()+"'>");
						str.append("<input type = 'hidden' name='nwhopay' id='nwhopay"+i+"' value='"+n.getWhopay()+"'>");
					 
					 
					 }else{
						 
						 	str.append("<tr style='background-color:"+color+";cursor:pointer;"+textcolor+";font-size:13px'>");
						 	str.append("<td style='text-align: left;'>"+i+"</td>");
							str.append("<td style='text-align: left;'>"+DateTimeUtils.getdatewithmonth(n.getOpdbookdate())+" "+n.getOpdbooktime().split(":")[0]+":"+n.getOpdbooktime().split(":")[1]+"</td>");
							str.append("<td style='text-align: left;'>"+DateTimeUtils.getdatewithmonth(DateTimeUtils.getCommencingDate1(n.getCommencing()))+" "+n.getsTime()+"</td>");
							str.append("<td style='text-align: center;' onclick='showdrprofile("+n.getDiaryUserId()+")'><i class='fa fa-user-md fa-2x' aria-hidden='true' style='color: crimson;'title='Doctor Profile'></i>&nbsp;&nbsp;"+userProfile.getFullname()+"</td>");
							str.append("<td>"+scanqr+"</td>");
							str.append("<td>"+vidicon+"</td>");
							str.append("<td>"+remarkicon+"</td>");
							str.append("<td>"+attachicon+"</td>");
							str.append("<td>"+cancelapmt+"</td>");
//									+ "<br>"+userProfile.getQualification()+"<br>"
//									+ ""+chargesReportDAO.getChargeDepartmentName(n.getDiaryUserId())+"<br>"
//											+ "Reg No. "+DateTimeUtils.isNull(userProfile.getRegisterno())+"<br>Phone No. "+userProfile.getMobile()+"<br>"+opdicon+"</td>");
							str.append("<td style='text-align: left;'>"+paymentsts+"</td>");
							
							str.append("<td style='text-align: left;'>"+opdapptsts+" </td>");
							/*str.append("<td style='text-align: left;'><a href='#'>Day_To_Day</a></td>");*/
							if(loginInfo.isSaimed()){
						    	str.append("<td>");
						    	str.append("&nbsp;&nbsp;<a><i class='fa fa-keyboard-o fa-2x' onclick=openPopup('consultationNotesCommonnew?clientId="+n.getClientId()+"&apmtId="+n.getId()+"') title='CONSULTATION NOTES'> </i></a>");
						    	str.append("&nbsp;&nbsp;<a><i class='fa fa-print fa-2x' onclick=openPopup('printConsulatationFormCommonnew?clientId="+n.getClientId()+"&apmtId="+n.getId()+"') title='Print'> </i></a>");
						    	str.append("</td>");
						    }
							
							str.append("<input type = 'hidden' name='ncname' id='ncname"+i+"' value='"+n.getClientName()+"'>");
							str.append("<input type = 'hidden' name='nwhopay' id='nwhopay"+i+"' value='"+n.getWhopay()+"'>");
					 }
					
					 if(loginInfo.isSaimed()) {
						  str.append("<td>");
						 if(loginInfo.isMarkhosp()) {
						 if(!loginInfo.getJobTitle().equals("Practitioner")) {
					       
				             str.append("&nbsp;&nbsp;<a><i class='fa fa-print fa-2x' onclick='openprintOpdInvoice("+n.getId()+")' title='Thermal Print'> </i></a>");
				             str.append("&nbsp;&nbsp;<a><i class='fa fa-print fa-2x' onclick='openprintOpdInvoice1("+n.getId()+")' title='Normal Print'> </i></a>");

					        
						 }
				       }else {
				    	   str.append("&nbsp;&nbsp;<a><i class='fa fa-print fa-2x' onclick='openprintOpdInvoice1("+n.getId()+")' title='Normal Print'> </i></a>");
				       }
						 
						 str.append("</td>");
					 }else {
					  str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='openprintOpdInvoice("+n.getId()+")'><i class='fa fa-print' aria-hidden='true'></i>"+"</td>");

					 }
					 i++;
					
					str.append("</tr>");
				}
			}else{
				for(NotAvailableSlot n : opdlist){
					UserProfile userProfile=profileDAO.getUserprofileDetails(n.getDiaryUserId());
                    String descriptionname=profileDAO.getdescriptionName(userProfile.getDiciplineName());
					
					session.setAttribute("descriptionname", descriptionname);

					String color = "";
					String textcolor="";
					String cancelapmt="";
					int popupstatus = 0;
					String paymentcolor="";
					String payvalue="";
					if(n.getBalance()>0) {
						paymentcolor="color: red !important;";
						payvalue=""+n.getBalance();
					}
					if(n.getBalance()==0 && n.getAdvance()>0 ) {
						paymentcolor="color: green !important;";
						payvalue=""+n.getAdvance();
					}
					if(n.getBalance()==0 && n.getAdvance()==0) {
						
						payvalue="0";
					}
					if(n.getStatus().equals("0")){
						color = "rgb(252, 186, 99)";
						popupstatus = 1;
					if(n.isAppointmentCompleted()){
						color = "rgb(129, 171, 109)";
						popupstatus = 2;
					}
					if(!n.getInvoiceid().equals("0")){
						color = "rgb(204, 204, 204)";
						
						popupstatus = 3;
					}
					if(n.getOpdpmnt()>0){
						color = "rgb(204, 204, 204)";
						}
					if(n.getDrcompleted()==1){
						
						color = "rgb(92, 241, 213)";
						
						popupstatus = 3;
					}
					
					if(n.getArrivedStatus()==1){
						textcolor="color: green !important;";
					}
					 if(n.getArrivedStatus()==2){
						textcolor="color: #bb0202f2 !important;";
					}
					 if(n.isDna()){
						 color = "rgb(255,0,0)";
						 popupstatus = 4;
					 }
					 if(n.getApmttypetext().equals("OPD FREE") && Integer.parseInt(n.getInvoiceid())>0){
						 color = "rgb(204, 204, 204)";
							
							popupstatus = 3;
							if(n.getDrcompleted()==1){
								
								color = "rgb(92, 241, 213)";
								
								popupstatus = 3;
							}
					 }
					 String newicon="";
					 
					 
					   int duserid  = profileDAO.getDiaryUserId(n.getAddedBy());
						UserProfile userProfile2 = profileDAO.getUserprofileDetails(duserid);
					 if(loginInfo.isLmh()){
						 popupstatus=3;
						 color = "rgb(204, 204, 204)";
					 }
					 
					 
				 		str.append("<tr style='background-color:"+color+";' >");
						str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+i+"</td>");
						String opdabrivationid= n.getOpdAbbrId();
						if(loginInfo.isLmh()){
							if(!DateTimeUtils.isNull(n.getOpdSequnce()).equals("")){
								opdabrivationid = n.getOpdSequnce();
							}
						}
						str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+opdabrivationid+"</td>");
						
						if(DateTimeUtils.isNull(n.getOpdbooktime()).equals("")){
							str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'></td>");	
						}else{
						str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+DateTimeUtils.getdatewithmonth(n.getOpdbookdate())+" "+n.getOpdbooktime().split(":")[0]+":"+n.getOpdbooktime().split(":")[1]+"</td>");
						}
						str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+DateTimeUtils.getdatewithmonth(DateTimeUtils.getCommencingDate1(n.getCommencing()))+" "+n.getsTime()+"</td>");
						str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+n.getDuration()+"</td>");
						str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+userProfile2.getFullname()+"</td>");
						str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+n.getAbrivationid()+"</td>");
//							str.append("<td style='text-align: left;'>"+n.getClientName()+" "+opdicon+"</td>");
						str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+n.getClientName()+"</td>");
						str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+n.getAgegender()+"</td>");
						str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+n.getApmttypetext()+"</td>");
						 
					    if(loginInfo.getClinicid1().equals("raiprachi")) {
						
						str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px'><a href='#' onclick=openPopup('patientlabelIpd?selectedid="+n.getClientId()+"')>Label</a></td>");  
						

					    
					    }
						
						if(!loginInfo.isLmh()){
						if(!n.getInvoiceid().equals("0")) {
						str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='openprintOpdInvoice("+n.getId()+")'><i class='fa fa-print' aria-hidden='true'></i>"+"</td>");
						}else {
							
							str.append("<td></td>");						}
						
						}
                        if(loginInfo.isLmh()){
					    	str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+n.getPatientcategory()+"</td>");
					    	str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+DateTimeUtils.isNull(n.getRefferedfrom())+"</td>");
					    	str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' ><input onclick='changedepartment("+n.getClientId()+","+n.getId()+")' type='button' value='Refer To ' class='btn btn-primary' style='margin-top: 6px;'></td>");
					    }
					    if(loginInfo.isSaimed() || loginInfo.isLmh()){
					    	str.append("<td style='width: 20%'>");
					    	str.append("&nbsp;&nbsp;<a><i class='fa fa-keyboard-o fa-2x' onclick=openPopup('consultationNotesCommonnew?clientId="+n.getClientId()+"&apmtId="+n.getId()+"') title='CONSULTATION NOTES'> </i></a>");
					    	str.append("&nbsp;&nbsp;<a><i class='fa fa-print fa-2x' onclick=openPopup('printConsulatationFormCommonnew?clientId="+n.getClientId()+"&apmtId="+n.getId()+"') title='Print'> </i></a>");
			 		    	str.append("&nbsp;&nbsp;<a href='#' onclick='historyConsulatationForm("+n.getClientId()+","+n.getId()+")'><i class='fa fa-history fa-2x'title='History'></i></a>");
			 		    	str.append("&nbsp;&nbsp;<a href='#' title='EMR' onclick='redircttoNewEmr("+n.getClientId()+","+nduserid+","+n.getId()+")'><img src='popicons/emr.png' class='setsizeimg' style='width:30px; '/>"
			 		    			+ "</i></a>");

					    	str.append("</td>");
					    	str.append("<td style='text-align: left;cursor:pointer;"+paymentcolor+";font-size:13px'>"+payvalue+"</td>");
					    }
						
					    if(loginInfo.getClinicid1().equals("raiprachi")) {
					    	str.append("<td>");
					    	str.append("&nbsp;&nbsp;<a><i class='fa fa-keyboard-o fa-2x' onclick=openPopup('consultationNotesCommonnew?clientId="+n.getClientId()+"&apmtId="+n.getId()+"') title='CONSULTATION NOTES'> </i></a>");
					    	str.append("&nbsp;&nbsp;<a class='hidden'><i class='fa fa-print fa-2x' onclick=openPopup('printConsulatationFormCommonnew?clientId="+n.getClientId()+"&apmtId="+n.getId()+"') title='Print'> </i></a>");
			 		    	str.append("&nbsp;&nbsp;<a href='#' onclick='historyConsulatationForm("+n.getClientId()+","+n.getId()+")'><i class='fa fa-history fa-2x'title='History'></i></a>");
					    	str.append("&nbsp;&nbsp;<a><i class='fa fa-print fa-2x' onclick=openPopup('printPriscriptionFormCommonnew?clientId="+n.getClientId()+"&apmtId="+n.getId()+"') title='Priscription Print'> </i></a>");

			 		    	str.append("</td>");
					    }

						/*str.append("<td style='text-align: left;'><a href='#'>Day_To_Day</a></td>");*/
					    str.append("<input type = 'hidden' name='ncname' id='ncname"+i+"' value='"+n.getClientName()+"'>");
					    str.append("<input type = 'hidden' name='nwhopay' id='nwhopay"+i+"' value='"+n.getWhopay()+"'>");
				    }
					
					i++;
					
					str.append("</tr>");
				}
			}
			
			NotAvailableSlot n = notAvailableSlotDAO.getNewOpdDiaryUserData(ndate,nduserid);
			String data=str.toString()+"~~~"+n.getId()+"~~~"+undonecount+"~~~"+opdlogstatus;
			
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(data);




			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
}
	public String newdmobisplay(){
		
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		
		
		String ndate = request.getParameter("ndate");
		String nduserid = request.getParameter("nduserid");
		String opdlogstatus = request.getParameter("opdstatus");
		if(!opdlogstatus.equals("")){
		if(opdlogstatus.equals("3")){
			session.setAttribute("orderapptt",opdlogstatus);
		}
		if(session.getAttribute("orderapptt")!=null){
			opdlogstatus=(String)session.getAttribute("orderapptt");
		}
		}
		int t = 1;
		try{
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			//Opd dashboard display data
			
			ndate = DateTimeUtils.getCommencingDate(ndate);	
			
			ArrayList<NotAvailableSlot>opdlist = notAvailableSlotDAO.getNewOpdList(ndate,nduserid,loginInfo,opdlogstatus,"");
			
			StringBuffer str = new StringBuffer();
			int i = 1;
			StringBuffer buffer=new StringBuffer();
			String opdicon="";
			UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
			ChargesReportDAO chargesReportDAO=new JDBCChargesReportDAO(connection);
			int undonecount=notAvailableSlotDAO.getUndoneAppointment(nduserid,ndate);
			for(NotAvailableSlot n : opdlist){
				UserProfile userProfile=profileDAO.getUserprofileDetails(n.getDiaryUserId());
				boolean checkattachment=notAvailableSlotDAO.checkAttachment(n.getId());
				opdicon="&nbsp;&nbsp;<a><i class='fa fa-user fa-2x' title='View Profile' style='color: black;' onclick=openSamll('puresevaprofileClient?id="+n.getClientId()+"')> </i></a>";
						/*+ "&nbsp;&nbsp;<a href='#' onclick='openCancelApmtPopupNEW("+n.getId()+")'><i class='fa fa-times fa-2x' aria-hidden='true' title='Cancel Appointment' style='color: black;'></i></a>";*/
				String color = "";
				String textcolor="";
				String remarkicon="";
				String paymentsts="Unpaid";
				String opdapptsts="Request";
				String vidicon="";
				String scanqr="";
				String cancelapmt="";
				int popupstatus = 0;
				if(n.getStatus().equals("0")){
					color = "rgb(252, 186, 99)";
					popupstatus = 1;
					if(loginInfo.getUserType()==5){
						popupstatus = 5;
						paymentsts="Unpaid";
						opdapptsts="Request";
//						opdicon="&nbsp;&nbsp;<a href='#' onclick='openscanpopup("+n.getId()+")'><i class='fa fa-qrcode fa-2x' aria-hidden='true' style='color: black;' title='Payment'></i></a>"
//								+ "&nbsp;&nbsp;<a href='#' onclick='openCancelApmtPopupNEW("+n.getId()+")'><i class='fa fa-times fa-2x' aria-hidden='true' style='color: black;' title='Cancel Appointment'></i></a>";
						scanqr="<a href='#' onclick='openscanpopup("+n.getId()+")'><i class='fa fa-qrcode fa-2x' aria-hidden='true' style='color: black;' title='Payment'></i></a>";
						cancelapmt="<a href='#' onclick='openCancelApmtPopupNEW("+n.getId()+")'><i class='fa fa-times fa-2x' aria-hidden='true' style='color: black;' title='Cancel Appointment'></i></a>";
//								+ "&nbsp;&nbsp;<a><i class='fa fa-edit fa-2x' style='color: black;'  onclick=openEditClientPrintPopup("+n.getClientId()+") title='Edit Profile'> </i></a>";
						
						if(!n.getPending_remark().equals("") || !n.getDoctor_vid_reject_remark().equals("") ){
//						opdicon=opdicon+" "+remarkicon;
							remarkicon="&nbsp;&nbsp;<a href='#' onclick='openremarkPopup("+n.getClientId()+","+n.getId()+")'><i class='fa fa-commenting fa-2x' aria-hidden='true' style='color: black;' title='Remark'></i></a>";
						}
					}
				}
				if(n.isAppointmentCompleted()){
					color = "rgb(129, 171, 109)";
					popupstatus = 2;
					if(loginInfo.getUserType()==5){
						popupstatus = 0;
					}
				}
				if(!n.getInvoiceid().equals("0")){
					color = "rgb(204, 204, 204)";
					
					popupstatus = 3;
					if(loginInfo.getUserType()==5){
						popupstatus = 6;
						paymentsts="Received";
						opdapptsts="Confirm";
						opdicon="";
						cancelapmt="";
//						vidicon = "&nbsp;&nbsp;<a href='#' onclick='openvideoconfirm("+n.getClientId()+","+n.getId()+")'><i class='fa fa-video-camera fa-2x' aria-hidden='true' style='color: black;'title='Confirm Video Call'></i></a>";
//						opdicon="&nbsp;&nbsp;<a><i class='fa fa-video-camera fa-2x' style='color: black;' onclick=openPopup('vdyoEmr?clid="+n.getClientId()+"&condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"') title='Video Call'> </i></a>";
								/*+ "&nbsp;&nbsp;<a href='#' onclick='openremarkPopup("+n.getClientId()+","+n.getId()+")'><i class='fa fa-commenting fa-2x' aria-hidden='true' style='color: black;' title='Remark'></i></a>";*/
//								+ "&nbsp;&nbsp;<a><i class='fa fa-user fa-2x' style='color: black;' onclick=openSamll('puresevaprofileClient?id="+n.getClientId()+"') title='View Profile'> </i></a>"
//								+ "&nbsp;&nbsp;<a><i class='fa fa-edit fa-2x' style='color: black;'  onclick=openEditClientPrintPopup("+n.getClientId()+") title='Edit Profile'> </i></a>";
						
						if(!n.getPending_remark().equals("") || !n.getDoctor_vid_reject_remark().equals("") ){
//						opdicon=opdicon+" "+remarkicon;
							remarkicon="&nbsp;&nbsp;<a href='#' onclick='openremarkPopup("+n.getClientId()+","+n.getId()+")'><i class='fa fa-commenting fa-2x' aria-hidden='true' style='color: black;' title='Remark'></i></a>";
						}
					}
				}
				if(n.getOpdpmnt()>0){
					color = "rgb(204, 204, 204)";
					if(loginInfo.getUserType()!=5){
					opdicon="&nbsp;&nbsp;<a><i class='fa fa-user fa-2x' onclick=openSamll('puresevaprofileClient?id="+n.getClientId()+"') title='View Profile'> </i></a>";
							/*+ "&nbsp;&nbsp;<a href='#' onclick='openCancelApmtPopupNEW("+n.getId()+")'><i class='fa fa-times fa-2x' aria-hidden='true' style='color: black;' title='Cancel Appointment'></i></a>";*/
					}
					}
				if(n.getDrcompleted()==1){
					
					color = "rgb(92, 241, 213)";
					
					popupstatus = 3;
					if(loginInfo.getUserType()==5){
						popupstatus = 0;
					}
				}
				
				if(n.getArrivedStatus()==1){
					textcolor="color: green !important;";
				}
				 if(n.getArrivedStatus()==2){
					textcolor="color: white !important;";
				}
				 if(n.isDna()){
					 color = "rgb(255,0,0)";
					 popupstatus = 4;
					 if(loginInfo.getUserType()==5){
							popupstatus = 0;
						}	 
				 }
				 if(n.getApmttypetext().equals("OPD FREE") && Integer.parseInt(n.getInvoiceid())>0){
					 color = "rgb(204, 204, 204)";
						
						popupstatus = 3;
						if(n.getDrcompleted()==1){
							
							color = "rgb(92, 241, 213)";
							
							popupstatus = 3;
						}
						if(loginInfo.getUserType()==5){
							popupstatus = 0;
						}
				 }
				 String attachicon="";
				 if(loginInfo.getUserType()==5){
					 attachicon="&nbsp;&nbsp;<a href='#' onclick='openpatientattachment("+n.getClientId()+","+n.getId()+")'><i class='fa fa-paperclip fa-2x' aria-hidden='true' style='color: black;'title='Attachment'></i></a>";	 
				 }else{
					 if(checkattachment){
						 attachicon="&nbsp;&nbsp;<a href='#' onclick='openpatientattachment("+n.getClientId()+","+n.getId()+")'><i class='fa fa-paperclip fa-2x' aria-hidden='true' style='color: black;'title='Attachment'></i></a>";
						 opdicon=opdicon+" "+attachicon; 
					 }	 
				 }
//				 opdicon=opdicon+" "+attachicon; 
				 String newicon="";
				 if(n.getMobstatusnew()==1){
					 if(loginInfo.getUserType()!=5){
						 if(!loginInfo.getJobTitle().equals("Practitioner")){
							 newicon = "&nbsp;&nbsp;<a href='#' onclick='openbookingconfirmchecklist("+n.getClientId()+","+n.getId()+")'><i class='fa fa-list fa-2x' aria-hidden='true' style='color: black;'title='Booking Confirm'></i></a>";
						 }
					 }
				 }
				 
				 
				   int duserid  = profileDAO.getDiaryUserId(n.getAddedBy());
					UserProfile userProfile2 = profileDAO.getUserprofileDetails(duserid);
				 
				 
				 if(loginInfo.getUserType()!=5){
						 if(n.getMobstatusnew()==1){
							 if(n.getReception_vid_verify()==1){

								 if(n.getDoctor_vid_verify()==1){
//								 vidicon="&nbsp;&nbsp;<a title='Video Call' href='vdyoEmr?clid="+n.getClientId()+"&condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"' target='blank'><span class='glyphicon glyphicon-facetime-video'></span></a>";
								 vidicon="&nbsp;&nbsp;<a><i class='fa fa-video-camera fa-2x' style='color: green;' onclick=openPopup('vdyoEmr?clid="+n.getClientId()+"&condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"') title='Video Call'> </i></a>";
								 	}else if(n.getDoctor_vid_reject_remark()==""){
								 		vidicon = "&nbsp;&nbsp;<a href='#' onclick='openvideoconfirm("+n.getClientId()+","+n.getId()+")'><i class='fa fa-video-camera fa-2x' aria-hidden='true' style='color: black;'title='Confirm Video Call'></i></a>";
								 	}else{
								 		vidicon="";
								 	}
								 }
							 }else{
								 if(userProfile2.isOpd_user_vid_access()){
								 if (loginInfo.isOpd_user_vid_access()) {
									 if(loginInfo.isDr_opd_vid()){
										 if(n.getDoctor_vid_verify()==1){
											 vidicon="&nbsp;&nbsp;<a><i class='fa fa-video-camera fa-2x' style='color: green;' onclick=openPopup('vdyoEmr?clid="+n.getClientId()+"&condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"') title='Video Call'> </i></a>";
											 	}
										 	else{
											 		 
											 		vidicon = "&nbsp;&nbsp;<a href='#' onclick='openvideoconfirm("+n.getClientId()+","+n.getId()+")'><i class='fa fa-video-camera fa-2x' aria-hidden='true' style='color: black;'title='Confirm Video Call'></i></a>";
											 	}
										 }else{
											 if(n.getDoctor_vid_verify()==1){
												 vidicon="&nbsp;&nbsp;<a><i class='fa fa-video-camera fa-2x' style='color: green;' onclick=openPopup('vdyoEmr?clid="+n.getClientId()+"&condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"') title='Video Call'> </i></a>";
											}else{
											 vidicon = "&nbsp;&nbsp;<a href='#'><i class='fa fa-video-camera fa-2x' aria-hidden='true' style='color: black;'title='Video Completed'></i></a>";
												 }
											}
										
									}
								 }
							 }
						 } else{
					 if(n.getMobstatusnew()==1){
						 if(n.getReception_vid_verify()==1){

							 if(n.getDoctor_vid_verify()==1){
								 opdapptsts="Dr.Confirm";
								 vidicon="&nbsp;&nbsp;<a><i class='fa fa-video-camera fa-2x' style='color: green;' onclick=openPopup('vdyoEmr?clid="+n.getClientId()+"&condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"') title='Video Call'> </i></a>";
							 }
							 }
							 
							 }
				 }
				 if(n.isDna()){
					 //when DNA set then hide video icon
					 vidicon="";
				 }
				 
				 if(n.getDrcompleted()==1){
					//when appointment complted then hide video icon
					 if(userProfile2.isOpd_user_vid_access()){
						 vidicon = "&nbsp;&nbsp;<a href='#'><i class='fa fa-video-camera fa-2x' aria-hidden='true' style='color: brown;'title='Wait for Confirmation'></i></a>";
					 }else{
						 vidicon="";
					 }
				 }
				 
				 opdicon=opdicon+" "+newicon+ " "+vidicon; 
				 
				 if( loginInfo.getUserType()!=5){
					 if(!loginInfo.isOpd_video_icon_show()){
					 opdicon="";
					 }
				 }
				 if(loginInfo.getUserType()!=5){
					 str.append("<tr style='background-color:"+color+";'>");
						str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+i+"</td>");
						str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+n.getId()+"</td>");
						if(DateTimeUtils.isNull(n.getOpdbooktime()).equals("")){
							str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'></td>");	
						}else{
						str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+DateTimeUtils.getdatewithmonth(n.getOpdbookdate())+" "+n.getOpdbooktime().split(":")[0]+":"+n.getOpdbooktime().split(":")[1]+"</td>");
						}
						str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+DateTimeUtils.getdatewithmonth(DateTimeUtils.getCommencingDate1(n.getCommencing()))+" "+n.getsTime()+"</td>");
						str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+n.getDuration()+"</td>");
						str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+userProfile2.getFullname()+"</td>");
						str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+n.getAbrivationid()+"</td>");
//						str.append("<td style='text-align: left;'>"+n.getClientName()+" "+opdicon+"</td>");
						str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+n.getClientName()+"</td>");
						str.append("<td>"+opdicon+"</td>");
						str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+n.getAgegender()+"</td>");
						
					
					str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+","+n.getDrcompleted()+","+n.getPatientisseen()+")'>"+n.getApmttypetext()+"</td>");
					
					/*str.append("<td style='text-align: left;'><a href='#'>Day_To_Day</a></td>");*/
					str.append("<input type = 'hidden' name='ncname' id='ncname"+i+"' value='"+n.getClientName()+"'>");
					str.append("<input type = 'hidden' name='nwhopay' id='nwhopay"+i+"' value='"+n.getWhopay()+"'>");
				 
				 
				 }else{
					 
					 	str.append("<tr style='background-color:"+color+";cursor:pointer;"+textcolor+";font-size:13px'>");
					 	str.append("<td style='text-align: left;'>"+i+"</td>");
						str.append("<td style='text-align: left;'>"+DateTimeUtils.getdatewithmonth(n.getOpdbookdate())+" "+n.getOpdbooktime().split(":")[0]+":"+n.getOpdbooktime().split(":")[1]+"</td>");
						str.append("<td style='text-align: left;'>"+DateTimeUtils.getdatewithmonth(DateTimeUtils.getCommencingDate1(n.getCommencing()))+" "+n.getsTime()+"</td>");
						str.append("<td style='text-align: center;' onclick='showdrprofile("+n.getDiaryUserId()+")'><i class='fa fa-user-md fa-2x' aria-hidden='true' style='color: crimson;'title='Doctor Profile'></i>&nbsp;&nbsp;"+userProfile.getFullname()+"</td>");
						str.append("<td>"+scanqr+"</td>");
						str.append("<td>"+vidicon+"</td>");
						str.append("<td>"+remarkicon+"</td>");
						str.append("<td>"+attachicon+"</td>");
						str.append("<td>"+cancelapmt+"</td>");
//								+ "<br>"+userProfile.getQualification()+"<br>"
//								+ ""+chargesReportDAO.getChargeDepartmentName(n.getDiaryUserId())+"<br>"
//										+ "Reg No. "+DateTimeUtils.isNull(userProfile.getRegisterno())+"<br>Phone No. "+userProfile.getMobile()+"<br>"+opdicon+"</td>");
						str.append("<td style='text-align: left;'>"+paymentsts+"</td>");
						
						str.append("<td style='text-align: left;'>"+opdapptsts+" </td>");
						/*str.append("<td style='text-align: left;'><a href='#'>Day_To_Day</a></td>");*/
						str.append("<input type = 'hidden' name='ncname' id='ncname"+i+"' value='"+n.getClientName()+"'>");
						str.append("<input type = 'hidden' name='nwhopay' id='nwhopay"+i+"' value='"+n.getWhopay()+"'>");
				 }
				
				i++;
				
				str.append("</tr>");
			}
			NotAvailableSlot n = notAvailableSlotDAO.getNewOpdDiaryUserData(ndate,nduserid);
			String data=str.toString()+"~~~"+n.getId()+"~~~"+undonecount+"~~~"+opdlogstatus;
			
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(data);




			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public String newopd(){
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		
		
		String ndate = request.getParameter("ndate");
		String nduserid = request.getParameter("nduserid");
		
	
		int t = 1;
		try{
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			
			
			ndate = DateTimeUtils.getCommencingDate(ndate);	
			
			
			
//			System.out.println(ndate);
			
			String res=checksetdiary(ndate,nduserid);
			
			NotAvailableSlot n = notAvailableSlotDAO.getNewOpdDiaryUserData(ndate,nduserid);
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("HH:mm");
			String currentTime=format.format(date);
			String mm=currentTime.split(":")[1];
			String strmm="";
			int rem=Integer.parseInt(mm)%5;
			if(rem==0){
				n.setsTime(currentTime);
				n.setSlotstime(currentTime);
			}else{
				int mmm=Integer.parseInt(mm);
				while(mmm%5!=0){
					mmm--;
				}
				strmm=""+mmm;
				if(mmm<=9){
					String singlemm=String.valueOf(mmm);
					singlemm="0"+singlemm;
					strmm=singlemm;
				}
				String updatetime=currentTime.split(":")[0]+":"+strmm;
				n.setsTime(updatetime);
				n.setSlotstime(updatetime);
			}
			
			// id,starttime,endtime,apmtduration,location,diaryuser,diaryuserid 
			String str = n.getId() + "~" + n.getsTime() + "~" + n.getEndTime() 
			+ "~" + n.getDuration() + "~" + n.getLocation() + "~" + n.getDiaryUser()
			+ "~" + n.getDiaryUserId() + "~" + n.getSlotstime() + "~" +ndate 
			+ "~" +  n.getDiaryUserId() + "~" +res;
			
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str);
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	public String saveAppoinment() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		int result = 0;

		String slotId = request.getParameter("slotId");
		String commencing = request.getParameter("commencing");
		/*
		 * String temp[] = commencing.split("/"); commencing =
		 * temp[2]+"-"+temp[1]+"-"+temp[0];
		 */
		String location = request.getParameter("location");
		String room = request.getParameter("room");
		String sTime = request.getParameter("sTime");
		
		//setting radio button value
		String rdstme[] = sTime.split(":");
		int rrr = Integer.parseInt(rdstme[0]) * 1;
		session.setAttribute("sessionrddval", ""+rrr+"");
		
		String endTime = request.getParameter("endTime");
		String apmtDuration = request.getParameter("apmtDuration");
		String diaryuserId = request.getParameter("diaryuserId");
		String client = request.getParameter("client");
		String dept = request.getParameter("dept");
		String apmtType = request.getParameter("apmtType");
		String notes = request.getParameter("notes");
		String diaryUser = request.getParameter("diaryUser");
		String clientId = request.getParameter("clientId");
		String treatmentEpisodeId = request.getParameter("treatmentEpisodeId");
		String condition = request.getParameter("condition");
		String whopay = request.getParameter("whopay");
		String stafflistid = request.getParameter("stafflistid");
		boolean isot = Boolean.parseBoolean(request.getParameter("isot"));
		String bookwithpayment = request.getParameter("bookwithpayment");

		// ot variables
		String otplaned = request.getParameter("otplaned");
		String otprocedureplaned1 = request.getParameter("otprocedureplaned");
        String procedureid=request.getParameter("procedureid");
		String otsurgeonname = request.getParameter("otsurgeonname");
		String otanesthesia = request.getParameter("otanesthesia");
		String otipdnos = request.getParameter("otipdnos");

		String psurcharge = request.getParameter("psurcharge");
		String panetcharge = request.getParameter("panetcharge");
		String anifees = request.getParameter("anifees");
		String sic = request.getParameter("sic");
		String assistaffcharge = request.getParameter("assistaffcharge");
		// take payment data
		String invcetype = request.getParameter("invcetype");
		String howpaid = request.getParameter("howpaid");
		String upino=request.getParameter("upino");
		String remark=request.getParameter("remark");
		String totalamount = request.getParameter("totalamount");
		String discount = request.getParameter("discount");
		String payAmount = request.getParameter("payAmount");
		String disctype = request.getParameter("disctype");
		String bnkname = request.getParameter("bnkname");
		// String paymentNote = document.getElementById('paymentNote').value;

		String status = null;
		int selectedid = Integer.parseInt(request.getParameter("selectedid"));
		String selectedot = request.getParameter("selectedot");
		
		String opdotcharge = request.getParameter("opdotcharge");
		String opdotregcharge = request.getParameter("opdotregcharge");
		String height = request.getParameter("height");
		String weight = request.getParameter("weight");
		String bmi = request.getParameter("bmi");
		String headcir = request.getParameter("headcir");
		String tempr = request.getParameter("tempr");
		String allergynotes=DateTimeUtils.isNull(request.getParameter("allergynotes"));
		String allergy=DateTimeUtils.isNull(request.getParameter("allergy"));
		int seqno = 1;
		if(assistaffcharge.equals("")){
			assistaffcharge="0";
     	}
     	
     	if(psurcharge.equals("")){
     		psurcharge="0";
     	}
     	if(panetcharge.equals("")){
     		panetcharge="0";
     	}
     	if(sic.equals("")){
     		sic="0";
     	}
     	if(assistaffcharge.equals("")){
     		assistaffcharge="0";
     	}
     	
     	if(anifees.equals("")){
     		anifees="0";
     	}
		/*
		 * int slotId1 = 0; int diaryuserId1 = 0;
		 * 
		 * if(selectedid == 0){ slotId1 = Integer.parseInt(slotId); diaryuserId1
		 * = Integer.parseInt(diaryuserId); }
		 */

		NotAvailableSlot beforeUpdateData = new NotAvailableSlot();

		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
			notAvailableSlot.setApmtSlotId(Integer.parseInt(slotId));
			notAvailableSlot.setDiaryUserId(Integer.parseInt(diaryuserId));
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());
			String dusername = userProfile.getInitial() + " " + userProfile.getFirstname() + " "
					+ userProfile.getLastname();
			notAvailableSlot.setDiaryUser(dusername);
			notAvailableSlot.setBnkname(bnkname);
			String physioipdopd=userProfileDAO.getPhysioIpdOpd(userProfile.getDiciplineName());
			if(physioipdopd.equals("1")) {
			  int update=userProfileDAO.updatephysioIpdOpd(clientId);
			}
			//   25 Jan 2018
			String otprocedureplaned = masterDAO.getMasterChargeType(otprocedureplaned1);

			//   set ipdno while booking ot appointment
			if (otprocedureplaned1 != null) {
				if (!otprocedureplaned1.equals("0")) {
					int lastipdid = ipdDAO.getLastIpdId(clientId);
					otipdnos = String.valueOf(lastipdid);
					if (otipdnos == null) {
						otipdnos = String.valueOf(lastipdid);
					} else if (otipdnos.equals("")) {
						otipdnos = String.valueOf(lastipdid);
					}
				}
			}
            if(dept.equals("")) {
            notAvailableSlot.setDept(userProfile.getDiciplineName());
            }else {
			notAvailableSlot.setDept(dept);
            }
			notAvailableSlot.setLocation(location);
			notAvailableSlot.setRoom(room);
			notAvailableSlot.setCommencing(commencing);
			notAvailableSlot.setSTime(sTime);
			notAvailableSlot.setEndTime(endTime);
			notAvailableSlot.setApmtDuration(apmtDuration);
			notAvailableSlot.setClient(client);
			notAvailableSlot.setApmtType(apmtType);
			notAvailableSlot.setNotes(notes);
			notAvailableSlot.setClientId(clientId);
			notAvailableSlot.setTreatmentEpisodeId(treatmentEpisodeId);
			notAvailableSlot.setAddedBy(loginInfo.getUserId());
			notAvailableSlot.setModifiedBy(loginInfo.getUserId());
			notAvailableSlot.setOt(isot);
			
			if(condition.equals("")) {
				notAvailableSlot.setCondition("1");
		    }else {
			    notAvailableSlot.setCondition(condition);
		    }
			notAvailableSlot.setPayBy(whopay);
			notAvailableSlot.setStafflistid(stafflistid);

			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);
			String zero = "0";
			int appointmentid = 0;
			// 2014-08-08
			// current date and time in dd/mm/yyyy
			String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
			String datetemp[] = currentDate.split(" ");
			String temp1[] = datetemp[0].split("-");
			String date = temp1[0] + "/" + temp1[1] + "/" + temp1[2];
			String commencingtemp[] = commencing.split(" ");
			String tempC[] = commencingtemp[0].split("-");
			String commencingTemp = commencing;
			String time = (datetemp[2] + " " + datetemp[3]);

			// ot set get
			notAvailableSlot.setOtplan(otplaned);
			notAvailableSlot.setProcedure(otprocedureplaned);
			notAvailableSlot.setSurgeon(otsurgeonname);
			notAvailableSlot.setAnesthesia(otanesthesia);
			notAvailableSlot.setIpdno(otipdnos);

			notAvailableSlot.setPsurcharge(psurcharge);
			notAvailableSlot.setPanetcharge(panetcharge);
			notAvailableSlot.setAnifees(anifees);
			notAvailableSlot.setSic(sic);
			notAvailableSlot.setAssistaffcharge(assistaffcharge);
			if (otplaned.equals("Planed")) {
				Bed bed = bedDao.getEditIpdData(otipdnos);
				notAvailableSlot.setWardid(bed.getWardid());
			}

			// update client bpayby

			ClientDAO clientDAO = new JDBCClientDAO(connection);
			Client client2 = clientDAO.getClientDetails(notAvailableSlot.getClientId());
			int updatewhopay = clientDAO.updateWhoPay(notAvailableSlot.getClientId(), notAvailableSlot.getPayBy());

			if (selectedid == 0) {

				if (!isot) {
					notAvailableSlot.setOtplan("0");
					notAvailableSlot.setProcedure("0");
					notAvailableSlot.setSurgeon("0");
					notAvailableSlot.setAnesthesia("0");
					notAvailableSlot.setIpdno("0");
					notAvailableSlot.setWardid("0");

					notAvailableSlot.setPsurcharge("0");
					notAvailableSlot.setPanetcharge("0");
					notAvailableSlot.setAnifees("0");
					notAvailableSlot.setSic("0");

				}

				int usedsession = diaryManagementDAO.getTempUsedSession(notAvailableSlot.getTreatmentEpisodeId());
				usedsession = usedsession + 1;

				if (treatmentEpisodeId.equals(zero)) {
					usedsession = 0;
				}
				notAvailableSlot.setUsedsession(Integer.toString(usedsession));

				boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(notAvailableSlot.getCommencing(),
						location, diaryuserId, notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
				if (checkEventExist) {
					checkEventExist = cheForWeekRepeatAfterEventExist(notAvailableSlot.getCommencing(), location,
							diaryuserId, notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
				}
				if (!checkEventExist) {

					// save abrivation seq no
					String cdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
					boolean checkifseq = notAvailableSlotDAO.checkifSequenceExist(commencing,
							notAvailableSlot.getDiaryUserId());
					String abrivationid = "";
					// String clinicabrivation =
					// clientDAO.getClinicAbrivation(loginInfo.getClinicid());
					String tempd[] = cdate.split("-");
					String y = tempd[0];
					String m = tempd[1];
					String d = tempd[2];
					SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");

				    Date now = new Date();

				    String opdbooktime = sdfTime.format(now);
					notAvailableSlot.setOpdbooktime(opdbooktime);
					// boolean ch =
					// notAvailableSlotDAO.checkEventAllreadyExist(notAvailableSlot.getCommencing(),location,
					// Integer.toString(notAvailableSlot.getDiaryUserId()),notAvailableSlot.getSTime(),notAvailableSlot.getEndTime());
					// if(!ch){
					if(loginInfo.getUserType()==5){
						notAvailableSlot.setMobstatus("1");
					}else{
						notAvailableSlot.setMobstatus("0");
					}
					String speciality=userProfileDAO.getSpeciality(diaryuserId);
					notAvailableSlot.setSpeciality(speciality);
					
					
					//changes for balgopal opd 22/08/2024 
					
					if(loginInfo.isBalgopal()) {
					int updtopd_count=0;
					int count1=appointmentTypeDAO.getavailableslotclientcount(clientId);
					int count=count1+1;
					long differ=0;
					String last_opd_date=appointmentTypeDAO.getLastopddatebyclientid(clientId);
					if(!last_opd_date.equals("")) {
					differ=DateTimeUtils.getDiffofTwoDates(last_opd_date, commencing);
					
					  String temp2[]=last_opd_date.split("-");
					  String month1=temp2[1];
					  String year1=temp2[0];
					  
					  String temp3[]=commencing.split("-");
					  String month2=temp3[1];
					  String year2=temp3[0];
				
					  if(!month1.equals(month2)) {
						  int month_days=DateTimeUtils.getlastmonthdate(Integer.parseInt(month1),Integer.parseInt(year1));
						  
						  if(month_days==28) {
							  differ=differ-3;
						  }else if(month_days==29) { 
							  differ=differ-2;
						  }else if(month_days==30) {
							  differ=differ-1;
						  }else if(month_days==31) {
							  differ=differ+1;
								 
						  }
					  }
					  }
					
					if(!last_opd_date.equals(commencing)) {
					 if(differ>7 ) {
						   updtopd_count=appointmentTypeDAO.updateOpd_count(1,clientId);
					  }else if(count==5){
				       	   updtopd_count=appointmentTypeDAO.updateOpd_count(1,clientId);
					  }else {
						   updtopd_count=appointmentTypeDAO.updateOpd_count(count,clientId);
					  }
					}
					}
					
					else if(loginInfo.isAyushman()) {
						int updtopd_count=0;
					    int count=appointmentTypeDAO.getavailableslotclientcount(clientId);
					
					      if(count==1) {
						     
					    	  updtopd_count=appointmentTypeDAO.updateOpd_count(0,clientId);
					    	  
					      }else {
					    	  
					    	  updtopd_count=appointmentTypeDAO.updateOpd_count(1,clientId);
						
					      }
					}
					
					appointmentid = notAvailableSlotDAO.saveAppointment(notAvailableSlot);
					//for reception to doctor direct call
					if(loginInfo.isWardhazp()){
					int res = clientDAO.updateBookingChecklist(""+appointmentid,"0","","0","1");
					}
					// }

					if (checkifseq) {
						seqno = notAvailableSlotDAO.getSqeunceNumber(commencing, notAvailableSlot.getDiaryUserId());
						seqno++;
						int r = notAvailableSlotDAO.InserCdateSeq(commencing, seqno, appointmentid,
								notAvailableSlot.getDiaryUserId());
						// SNH170609001
						/*
						 * int yr = Integer.parseInt(y)%1000; abrivationid =
						 * clinicabrivation + yr + m +d + "00" + seqno;
						 */
					} else {

						seqno = 1;
						int r = notAvailableSlotDAO.InserCdateSeq(commencing, seqno, appointmentid,
								notAvailableSlot.getDiaryUserId());
						// String seqno = clientDAO.getSqeunceNumber(cdate);
						/*
						 * int yr = Integer.parseInt(y)%1000; abrivationid =
						 * clinicabrivation + yr + m +d + "00" + seqno;
						 */
					}

					
					if (!client2.getCasualtyid().equals("0")) {

						int updc = bedDao.updateCasualtyid(Integer.parseInt(notAvailableSlot.getClientId()));
					}
					if (isot) {
						String temp[] = stafflistid.split(",");
						for (int i = 0; i < temp.length; i++) {
							if (i > 0) {
								int otid = notAvailableSlotDAO.saveParenrotData(notAvailableSlot.getCommencing(),
										selectedot, temp[i], appointmentid);
							}

							// block slot for ot
							stafflistid = stafflistid + "," + notAvailableSlot.getAnesthesia() + ","
									+ notAvailableSlot.getSurgeon();
							/*
							 * String temps[] = stafflistid.split(","); for(int
							 * b=0;b<temps.length;b++){ if(b>0){ String
							 * selectedpractid = temps[b];
							 * 
							 * UserProfile userProfile2 =
							 * userProfileDAO.getUserprofileDetails(Integer.
							 * parseInt(selectedpractid)); String fullname =
							 * userProfile2.getInitial() + " " +
							 * userProfile2.getFirstname() + " " +
							 * userProfile2.getLastname();
							 * notAvailableSlot.setDiaryUser(fullname); int
							 * apmtSlotid =
							 * notAvailableSlotDAO.getOtAppointmentSlotID(
							 * notAvailableSlot.getCommencing(),Integer.parseInt
							 * (selectedpractid),notAvailableSlot.getSTime(),
							 * notAvailableSlot.getEndTime(),notAvailableSlot.
							 * getLocation());
							 * notAvailableSlot.setApmtSlotId(apmtSlotid);
							 * checkEventExist =
							 * notAvailableSlotDAO.checkEventAllreadyExist(
							 * notAvailableSlot.getCommencing(),location,
							 * selectedpractid,notAvailableSlot.getSTime(),
							 * notAvailableSlot.getEndTime());
							 * if(!checkEventExist){
							 * notAvailableSlot.setStatus("1");
							 * notAvailableSlot.setDiaryUserId(Integer.parseInt(
							 * selectedpractid));
							 * notAvailableSlot.setBlockot(appointmentid);
							 * notAvailableSlot.setReasonforblock("OT Booked");
							 * int block = notAvailableSlotDAO.saveBlockSlot(
							 * notAvailableSlot, "opd"); } } }
							 */

						}
						TestingLog.debug("saveAppoinment()  728");
						// send sms to accountants
						double charge = notAvailableSlotDAO.getCharge(notAvailableSlot.getApmtType());
						ArrayList<Master> accUserList = notAvailableSlotDAO.getAccountUserList();
						for (Master mstr : accUserList) {
							UserProfile u = userProfileDAO.getUserprofileDetails(mstr.getId());
							String accountant = u.getInitial() + " " + u.getFirstname() + " " + u.getLastname();

							String otmsg = accountant + "it has been informed you that " + notAvailableSlot.getClient()
									+ " has OT " + notAvailableSlot.getProcedure() + " scheduled on date "
									+ notAvailableSlot.getCommencing() + " of " + Constants.getCurrency(loginInfo)
									+ DateTimeUtils.changeFormat(charge) + " kindly check payment";

							SendSms os = new SendSms();
							//os.send(otmsg, u.getMobile(), loginInfo, new EmailLetterLog());
						}
						SmsService s = new SmsService();
						if(loginInfo.isOt_patient_sms()){
							UserProfile userpf = userProfileDAO.getUserprofileDetails(Integer.parseInt(otsurgeonname));
						String patientot=notAvailableSlot.getClient()+" It has been informed you that OT " + notAvailableSlot.getProcedure() + " scheduled on date "+ DateTimeUtils.getCommencingDate1(notAvailableSlot.getCommencing())+"";
						String templateid="";
						s.sendSms(patientot, client2.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
						}
						if(loginInfo.isOt_surgeon_sms()){
						UserProfile userpf = userProfileDAO.getUserprofileDetails(Integer.parseInt(otsurgeonname));
						String drot=userpf.getFullname()+" It has been informed you that OT " + notAvailableSlot.getProcedure() + " scheduled on date "+ DateTimeUtils.getCommencingDate1(notAvailableSlot.getCommencing())+" for patient "+notAvailableSlot.getClient()+"";
						String templateid="";
						s.sendSms(drot, userpf.getMobile(), loginInfo, new EmailLetterLog(),templateid);
						}
						
						String procedure[] = procedureid.split(",");
						for(int j=0;j<procedure.length;j++) {
							
								int insertprocedure=notAvailableSlotDAO.insertProceduredata(appointmentid,procedure[j]);
							
						}
						
						
					}

					if (bookwithpayment.equals("1")) {
						saveOpdCharge(appointmentid, notAvailableSlot, invcetype, howpaid, totalamount, discount,
								payAmount, connection, disctype,opdotcharge,opdotregcharge,upino,remark);
					}
					status = "Booked";
					int logsave = notAvailableSlotDAO.saveApmtInLog(appointmentid, date, time, loginInfo.getUserId(),
							clientId, commencingTemp, sTime, status,
							DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
					// }

					/*
					 * else{ //book for ot String temp[] =
					 * stafflistid.split(","); int otid =
					 * notAvailableSlotDAO.saveParenrotData(notAvailableSlot.
					 * getCommencing(),selectedot); for(int
					 * i=0;i<temp.length;i++){ if(i>0){ String selectedpractid =
					 * temp[i];
					 * notAvailableSlot.setDiaryUserId(Integer.parseInt(
					 * selectedpractid)); UserProfile userProfile2 =
					 * userProfileDAO.getUserprofileDetails(Integer.parseInt(
					 * selectedpractid)); String fullname =
					 * userProfile2.getInitial() + " " +
					 * userProfile2.getFirstname() + " " +
					 * userProfile2.getLastname();
					 * notAvailableSlot.setDiaryUser(fullname); int apmtSlotid =
					 * notAvailableSlotDAO.getAppointmentSlotID(notAvailableSlot
					 * .getCommencing(),Integer.parseInt(selectedpractid),
					 * notAvailableSlot.getSTime(),notAvailableSlot.getEndTime()
					 * ,notAvailableSlot.getLocation());
					 * notAvailableSlot.setApmtSlotId(apmtSlotid);
					 * checkEventExist =
					 * notAvailableSlotDAO.checkEventAllreadyExist(
					 * notAvailableSlot.getCommencing(),location,
					 * selectedpractid,notAvailableSlot.getSTime(),
					 * notAvailableSlot.getEndTime()); if(!checkEventExist){
					 * 
					 * notAvailableSlot.setOtid(otid); appointmentid =
					 * notAvailableSlotDAO.saveAppointment(notAvailableSlot); }
					 * }
					 * 
					 * }
					 * 
					 * System.out.println("ot code"); }
					 */

				}

				// check apmtexist
				boolean isapmtexist = notAvailableSlotDAO.checkApmtExist(notAvailableSlot.getClientId());
				if (isapmtexist) {
					int sts = 1;
					int updp = notAvailableSlotDAO.updateNewPtsStatus(notAvailableSlot.getClientId(), sts);
				} else {
					int sts = 0;
					int updp = notAvailableSlotDAO.updateNewPtsStatus(notAvailableSlot.getClientId(), sts);
				}

				/*
				 * if(appointmentid!=0){
				 * 
				 * }
				 */
				int updatusessession = diaryManagementDAO.updateTreatmentSession(usedsession,
						notAvailableSlot.getTreatmentEpisodeId());

				// log.debug("*****************appointment2");
				session.setAttribute("appointmentid", appointmentid);

				/*
				 * Clinic clinic = new Clinic(); ClinicDAO clinicDAO = new
				 * JDBCClinicDAO(connection); clinic =
				 * clinicDAO.getCliniclistDetails(loginInfo.getId()); String
				 * logo = clinic.getUserImageFileName(); String filePath =
				 * request.getRealPath("/liveData/clinicLogo/"+logo+"/");
				 * session.setAttribute("logopath", filePath);
				 */
				/*
				 * String twitter =
				 * request.getRealPath("/img/Entypo_f309(0)_32.png/");
				 * session.setAttribute("twitter", twitter);
				 * 
				 * String fb =
				 * request.getRealPath("/img/Entypo_f30c(0)_32.png/");
				 * session.setAttribute("fb", fb);
				 * 
				 * String gml =
				 * request.getRealPath("/img/Entypo_f30f(0)_32.png/");
				 * session.setAttribute("gml", gml);
				 */

				/*
				 * DBScheduler dbScheduler = new DBScheduler();
				 * dbScheduler.callScheduler(connection,loginInfo,request,
				 * appointmentid);
				 */
				// result =
				// notAvailableSlotDAO.saveCharge(notAvailableSlot,apmtType,result);
			} else {
				int selectedTreatmentEpisodeId = notAvailableSlotDAO.getSelecedTreatmentEpisodeId(selectedid);

				if (selectedTreatmentEpisodeId > 0) {

					if (selectedTreatmentEpisodeId != Integer.parseInt(notAvailableSlot.getTreatmentEpisodeId())) {
						int usedsession = diaryManagementDAO
								.getTempUsedSession(notAvailableSlot.getTreatmentEpisodeId());
						usedsession = usedsession + 1;
						if (treatmentEpisodeId.equals(zero)) {
							usedsession = 0;
						}
						int updatusessession = diaryManagementDAO.updateTreatmentSession(usedsession,
								notAvailableSlot.getTreatmentEpisodeId());

						int prevTreatmentSession = notAvailableSlotDAO
								.getPreviousTreatmentUsedSession(selectedTreatmentEpisodeId);
						prevTreatmentSession = prevTreatmentSession - 1;
						int updatePreviousTreatmentEpisode = notAvailableSlotDAO
								.updatePreviousTreatmentEpisode(prevTreatmentSession, selectedTreatmentEpisodeId);

						notAvailableSlot.setUsedsession(Integer.toString(usedsession));

					}

				}

				int usedsession = diaryManagementDAO.getTempUsedSession(notAvailableSlot.getTreatmentEpisodeId());
				// usedsession = usedsession+1;
				if (treatmentEpisodeId.equals(zero)) {
					usedsession = 0;
				}
				notAvailableSlot.setUsedsession(Integer.toString(usedsession));
				beforeUpdateData = notAvailableSlotDAO.getApmtDetailsForLog(selectedid);
				int update = notAvailableSlotDAO.updateAppointment(notAvailableSlot, selectedid);
				// update oT data
				if (!notAvailableSlot.getOtplan().equals("0")) {
					int upot = notAvailableSlotDAO.updateOTdata(notAvailableSlot, selectedid);

					// delete asistant doctor
					int delasisdoctor = notAvailableSlotDAO.deleteAsistantDoctor(selectedid);

					String temp[] = stafflistid.split(",");
					for (int i = 0; i < temp.length; i++) {
						if (i > 0) {

							int otid = notAvailableSlotDAO.saveParenrotData(notAvailableSlot.getCommencing(),
									selectedot, temp[i], selectedid);
						}
					}

					// block slot for ot
					stafflistid = stafflistid + "," + notAvailableSlot.getAnesthesia() + ","
							+ notAvailableSlot.getSurgeon();
					int delblockot = notAvailableSlotDAO.deleteBlockOt(Integer.toString(selectedid));
					String temps[] = stafflistid.split(",");
					/*
					 * for(int b=0;b<temps.length;b++){ if(b>0){ String
					 * selectedpractid = temps[b];
					 * 
					 * UserProfile userProfile2 =
					 * userProfileDAO.getUserprofileDetails(Integer.parseInt(
					 * selectedpractid)); String fullname =
					 * userProfile2.getInitial() + " " +
					 * userProfile2.getFirstname() + " " +
					 * userProfile2.getLastname();
					 * notAvailableSlot.setDiaryUser(fullname); int apmtSlotid =
					 * notAvailableSlotDAO.getOtAppointmentSlotID(
					 * notAvailableSlot.getCommencing(),Integer.parseInt(
					 * selectedpractid),notAvailableSlot.getSTime(),
					 * notAvailableSlot.getEndTime(),notAvailableSlot.
					 * getLocation());
					 * notAvailableSlot.setApmtSlotId(apmtSlotid); boolean
					 * checkEventExist =
					 * notAvailableSlotDAO.checkEventAllreadyExist(
					 * notAvailableSlot.getCommencing(),location,
					 * selectedpractid,notAvailableSlot.getSTime(),
					 * notAvailableSlot.getEndTime()); if(!checkEventExist){
					 * notAvailableSlot.setStatus("1");
					 * notAvailableSlot.setDiaryUserId(Integer.parseInt(
					 * selectedpractid));
					 * notAvailableSlot.setBlockot(selectedid);
					 * notAvailableSlot.setReasonforblock("OT Booked"); int
					 * block =
					 * notAvailableSlotDAO.saveBlockSlot(notAvailableSlot,
					 * "opd"); } } }
					 */
					TestingLog.debug("saveAppoinment()  930");
					// send sms to accountants
					double charge = notAvailableSlotDAO.getCharge(notAvailableSlot.getApmtType());
					ArrayList<Master> accUserList = notAvailableSlotDAO.getAccountUserList();
					for (Master mstr : accUserList) {
						UserProfile u = userProfileDAO.getUserprofileDetails(mstr.getId());
						String accountant = u.getInitial() + " " + u.getFirstname() + " " + u.getLastname();

						String otmsg = accountant + "it has been informed you that " + notAvailableSlot.getClient()
								+ " has OT " + notAvailableSlot.getProcedure() + " scheduled on date "
								+ notAvailableSlot.getCommencing() + " of " + Constants.getCurrency(loginInfo)
								+ DateTimeUtils.changeFormat(charge) + " kindly check payment";

						SendSms os = new SendSms();
						os.send(otmsg, u.getMobile(), loginInfo, new EmailLetterLog());
					}
					SmsService s = new SmsService();
					if(loginInfo.isOt_patient_sms()){
						UserProfile userpf = userProfileDAO.getUserprofileDetails(Integer.parseInt(otsurgeonname));
					String patientot=notAvailableSlot.getClient()+" It has been informed you that OT " + notAvailableSlot.getProcedure() + " scheduled on date "+ DateTimeUtils.getCommencingDate1(notAvailableSlot.getCommencing())+"";
					String templateid="";
					s.sendSms(patientot, client2.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
					}
					if(loginInfo.isOt_surgeon_sms()){
					UserProfile userpf = userProfileDAO.getUserprofileDetails(Integer.parseInt(otsurgeonname));
					String drot=userpf.getFullname()+" It has been informed you that OT " + notAvailableSlot.getProcedure() + " scheduled on date "+ DateTimeUtils.getCommencingDate1(notAvailableSlot.getCommencing())+" for patient "+notAvailableSlot.getClient()+"";
					String templateid="";
					s.sendSms(drot, userpf.getMobile(), loginInfo, new EmailLetterLog(),templateid);
					}
				}

				appointmentid = selectedid;
				status = "Modified";
				int logsave = notAvailableSlotDAO.saveApmtInLog(appointmentid, date, time, loginInfo.getUserId(),
						clientId, commencingTemp, sTime, status,
						DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));

//				System.out.println("hello");
				CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
				int results = completeAptmDAO.deleteComplteApmt(loginInfo.getId());

				if (selectedid > 0) {
					// set all treatment episode sessions

					TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
					ArrayList<TreatmentEpisode> treatmentEpisodeList = treatmentEpisodeDAO
							.getTreatmentEpisodeList(clientId);
					for (TreatmentEpisode treatmentEpisode : treatmentEpisodeList) {

						ArrayList<NotAvailableSlot> dnaoffsetList = notAvailableSlotDAO
								.getDnaOffsetList(Integer.toString(treatmentEpisode.getId()));
						int i = 1;
						for (NotAvailableSlot nt : dnaoffsetList) {

							int updateusedsession = notAvailableSlotDAO.updateAppointmentUsedSession(nt.getId(), i);

							i++;

						}

						int updatetepisodeusedsession = notAvailableSlotDAO.updateupdateTpEpisodeusedsession(
								Integer.toString(treatmentEpisode.getId()), dnaoffsetList.size());
					}

				}

				// log.debug("*****************appointment3");
				session.setAttribute("appointmentid", selectedid);

				/*
				 * String filePath =
				 * request.getRealPath("/Design/images/logo.png/");
				 * session.setAttribute("logopath", filePath);
				 */
				/*
				 * DBScheduler dbScheduler = new DBScheduler();
				 * dbScheduler.callScheduler(connection,loginInfo,request,
				 * appointmentid);
				 */

			}

			// update client condition
			int updatecondition = notAvailableSlotDAO.updateClientCondition(notAvailableSlot.getClientId(), condition);

			boolean wholeweek = Boolean.parseBoolean(request.getParameter("wholeweek"));
			int weekNumber = Integer.parseInt(request.getParameter("weekNumber"));

			// repeat code

			if (weekNumber > 0) {
				boolean monday = Boolean.parseBoolean(request.getParameter("monday"));
				boolean tuesday = Boolean.parseBoolean(request.getParameter("tuesday"));
				boolean wednesday = Boolean.parseBoolean(request.getParameter("wednesday"));
				boolean thursday = Boolean.parseBoolean(request.getParameter("thursday"));
				boolean friday = Boolean.parseBoolean(request.getParameter("friday"));
				boolean saturday = Boolean.parseBoolean(request.getParameter("saturday"));
				boolean sunday = Boolean.parseBoolean(request.getParameter("sunday"));

				weekNumber = Integer.parseInt(request.getParameter("weekNumber"));

				String dt = notAvailableSlot.getCommencing();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(dt));

				for (int i = 0; i < weekNumber; i++) {

					for (int j = 0; j <= 6; j++) {

						c.add(Calendar.DATE, 1); // number of days to add
						dt = sdf.format(c.getTime()); // dt is now the new date

						notAvailableSlot.setCommencing(dt);
						commencingTemp = dt;

						String wcdate[] = dt.split("-");

						// set weekname
						int wyear = Integer.parseInt(wcdate[0]);
						int month = Integer.parseInt(wcdate[1]);
						int day = Integer.parseInt(wcdate[2]);

						String cweekName = DateTimeUtils.getWeekName(wyear, month, day);

//						System.out.println(dt);
//						System.out.println(cweekName);

						if (monday && cweekName.equals(Constants.MONDAY)) {

							// common for all
							int apmtSlotid = notAvailableSlotDAO.getAppointmentSlotID(notAvailableSlot.getCommencing(),
									notAvailableSlot.getDiaryUserId(), notAvailableSlot.getSTime(),
									notAvailableSlot.getEndTime(), notAvailableSlot.getLocation());
							if (apmtSlotid != 0) {
								notAvailableSlot.setApmtSlotId(apmtSlotid);
								boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(
										notAvailableSlot.getCommencing(), location, diaryuserId,
										notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
								if (checkEventExist) {
									checkEventExist = cheForWeekRepeatAfterEventExist(notAvailableSlot.getCommencing(),
											location, diaryuserId, notAvailableSlot.getSTime(),
											notAvailableSlot.getEndTime());
								}
								if (!checkEventExist) {
									int usedsession = diaryManagementDAO
											.getTempUsedSession(notAvailableSlot.getTreatmentEpisodeId());
									int sessions = diaryManagementDAO
											.getTempSessions(notAvailableSlot.getTreatmentEpisodeId());
									if (usedsession == sessions) {
										if (sessions > 0) {
											break;
										}
									}
									usedsession = usedsession + 1;
									if (treatmentEpisodeId.equals(zero)) {
										usedsession = 0;
									}
									notAvailableSlot.setUsedsession(Integer.toString(usedsession));
									appointmentid = notAvailableSlotDAO.saveAppointment(notAvailableSlot);
									int updatusessession = diaryManagementDAO.updateTreatmentSession(usedsession,
											notAvailableSlot.getTreatmentEpisodeId());
									// Log Save
									status = "Booked";
									int logsave = notAvailableSlotDAO.saveApmtInLog(appointmentid, date, time,
											loginInfo.getUserId(), clientId, commencingTemp, sTime, status,
											DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
								} else {
									break;
								}

							} else {
								// break;
							}

						} else if (tuesday && cweekName.equals(Constants.TUESDAY)) {
							// common for all
							int apmtSlotid = notAvailableSlotDAO.getAppointmentSlotID(notAvailableSlot.getCommencing(),
									notAvailableSlot.getDiaryUserId(), notAvailableSlot.getSTime(),
									notAvailableSlot.getEndTime(), notAvailableSlot.getLocation());
							if (apmtSlotid != 0) {
								notAvailableSlot.setApmtSlotId(apmtSlotid);
								boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(
										notAvailableSlot.getCommencing(), location, diaryuserId,
										notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
								if (checkEventExist) {
									checkEventExist = cheForWeekRepeatAfterEventExist(notAvailableSlot.getCommencing(),
											location, diaryuserId, notAvailableSlot.getSTime(),
											notAvailableSlot.getEndTime());
								}
								if (!checkEventExist) {
									int usedsession = diaryManagementDAO
											.getTempUsedSession(notAvailableSlot.getTreatmentEpisodeId());
									int sessions = diaryManagementDAO
											.getTempSessions(notAvailableSlot.getTreatmentEpisodeId());
									if (usedsession == sessions) {
										if (sessions > 0) {
											break;
										}
									}
									usedsession = usedsession + 1;
									if (treatmentEpisodeId.equals(zero)) {
										usedsession = 0;
									}
									notAvailableSlot.setUsedsession(Integer.toString(usedsession));
									appointmentid = notAvailableSlotDAO.saveAppointment(notAvailableSlot);
									int updatusessession = diaryManagementDAO.updateTreatmentSession(usedsession,
											notAvailableSlot.getTreatmentEpisodeId());
									// Log Save
									status = "Booked";
									int logsave = notAvailableSlotDAO.saveApmtInLog(appointmentid, date, time,
											loginInfo.getUserId(), clientId, commencingTemp, sTime, status,
											DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
								} else {
									break;
								}

							} else {
								// break;
							}
						} else if (wednesday && cweekName.equals(Constants.WEDNEDAY)) {
							// common for all
							int apmtSlotid = notAvailableSlotDAO.getAppointmentSlotID(notAvailableSlot.getCommencing(),
									notAvailableSlot.getDiaryUserId(), notAvailableSlot.getSTime(),
									notAvailableSlot.getEndTime(), notAvailableSlot.getLocation());
							if (apmtSlotid != 0) {
								notAvailableSlot.setApmtSlotId(apmtSlotid);
								boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(
										notAvailableSlot.getCommencing(), location, diaryuserId,
										notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
								if (checkEventExist) {
									checkEventExist = cheForWeekRepeatAfterEventExist(notAvailableSlot.getCommencing(),
											location, diaryuserId, notAvailableSlot.getSTime(),
											notAvailableSlot.getEndTime());
								}
								if (!checkEventExist) {
									int usedsession = diaryManagementDAO
											.getTempUsedSession(notAvailableSlot.getTreatmentEpisodeId());
									int sessions = diaryManagementDAO
											.getTempSessions(notAvailableSlot.getTreatmentEpisodeId());
									if (usedsession == sessions) {
										if (sessions > 0) {
											break;
										}
									}
									usedsession = usedsession + 1;
									if (treatmentEpisodeId.equals(zero)) {
										usedsession = 0;
									}
									notAvailableSlot.setUsedsession(Integer.toString(usedsession));
									appointmentid = notAvailableSlotDAO.saveAppointment(notAvailableSlot);
									int updatusessession = diaryManagementDAO.updateTreatmentSession(usedsession,
											notAvailableSlot.getTreatmentEpisodeId());
									// Log Save
									status = "Booked";
									int logsave = notAvailableSlotDAO.saveApmtInLog(appointmentid, date, time,
											loginInfo.getUserId(), clientId, commencingTemp, sTime, status,
											DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
								} else {
									break;
								}

							} else {
								// break;
							}
						} else if (thursday && cweekName.equals(Constants.THUSRDAY)) {
							// common for all
							int apmtSlotid = notAvailableSlotDAO.getAppointmentSlotID(notAvailableSlot.getCommencing(),
									notAvailableSlot.getDiaryUserId(), notAvailableSlot.getSTime(),
									notAvailableSlot.getEndTime(), notAvailableSlot.getLocation());
							if (apmtSlotid != 0) {
								notAvailableSlot.setApmtSlotId(apmtSlotid);
								boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(
										notAvailableSlot.getCommencing(), location, diaryuserId,
										notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
								if (checkEventExist) {
									checkEventExist = cheForWeekRepeatAfterEventExist(notAvailableSlot.getCommencing(),
											location, diaryuserId, notAvailableSlot.getSTime(),
											notAvailableSlot.getEndTime());
								}
								if (!checkEventExist) {
									int usedsession = diaryManagementDAO
											.getTempUsedSession(notAvailableSlot.getTreatmentEpisodeId());
									int sessions = diaryManagementDAO
											.getTempSessions(notAvailableSlot.getTreatmentEpisodeId());
									if (usedsession == sessions) {
										if (sessions > 0) {
											break;
										}
									}
									usedsession = usedsession + 1;
									if (treatmentEpisodeId.equals(zero)) {
										usedsession = 0;
									}
									notAvailableSlot.setUsedsession(Integer.toString(usedsession));
									appointmentid = notAvailableSlotDAO.saveAppointment(notAvailableSlot);
									int updatusessession = diaryManagementDAO.updateTreatmentSession(usedsession,
											notAvailableSlot.getTreatmentEpisodeId());
									// Log Save
									status = "Booked";
									int logsave = notAvailableSlotDAO.saveApmtInLog(appointmentid, date, time,
											loginInfo.getUserId(), clientId, commencingTemp, sTime, status,
											DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
								} else {
									break;
								}

							} else {
								// break;
							}
						} else if (friday && cweekName.equals(Constants.FRIDAY)) {
							// common for all
							int apmtSlotid = notAvailableSlotDAO.getAppointmentSlotID(notAvailableSlot.getCommencing(),
									notAvailableSlot.getDiaryUserId(), notAvailableSlot.getSTime(),
									notAvailableSlot.getEndTime(), notAvailableSlot.getLocation());
							if (apmtSlotid != 0) {
								notAvailableSlot.setApmtSlotId(apmtSlotid);
								boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(
										notAvailableSlot.getCommencing(), location, diaryuserId,
										notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
								if (checkEventExist) {
									checkEventExist = cheForWeekRepeatAfterEventExist(notAvailableSlot.getCommencing(),
											location, diaryuserId, notAvailableSlot.getSTime(),
											notAvailableSlot.getEndTime());
								}
								if (!checkEventExist) {
									int usedsession = diaryManagementDAO
											.getTempUsedSession(notAvailableSlot.getTreatmentEpisodeId());
									int sessions = diaryManagementDAO
											.getTempSessions(notAvailableSlot.getTreatmentEpisodeId());
									if (usedsession == sessions) {
										if (sessions > 0) {
											break;
										}
									}
									usedsession = usedsession + 1;
									if (treatmentEpisodeId.equals(zero)) {
										usedsession = 0;
									}
									notAvailableSlot.setUsedsession(Integer.toString(usedsession));
									appointmentid = notAvailableSlotDAO.saveAppointment(notAvailableSlot);
									int updatusessession = diaryManagementDAO.updateTreatmentSession(usedsession,
											notAvailableSlot.getTreatmentEpisodeId());
									// Log Save
									status = "Booked";
									int logsave = notAvailableSlotDAO.saveApmtInLog(appointmentid, date, time,
											loginInfo.getUserId(), clientId, commencingTemp, sTime, status,
											DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
								} else {
									break;
								}

							} else {
								// break;
							}
						} else if (saturday && cweekName.equals(Constants.SATURDAY)) {
							// common for all
							int apmtSlotid = notAvailableSlotDAO.getAppointmentSlotID(notAvailableSlot.getCommencing(),
									notAvailableSlot.getDiaryUserId(), notAvailableSlot.getSTime(),
									notAvailableSlot.getEndTime(), notAvailableSlot.getLocation());
							if (apmtSlotid != 0) {
								notAvailableSlot.setApmtSlotId(apmtSlotid);
								boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(
										notAvailableSlot.getCommencing(), location, diaryuserId,
										notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
								if (checkEventExist) {
									checkEventExist = cheForWeekRepeatAfterEventExist(notAvailableSlot.getCommencing(),
											location, diaryuserId, notAvailableSlot.getSTime(),
											notAvailableSlot.getEndTime());
								}
								if (!checkEventExist) {
									int usedsession = diaryManagementDAO
											.getTempUsedSession(notAvailableSlot.getTreatmentEpisodeId());
									int sessions = diaryManagementDAO
											.getTempSessions(notAvailableSlot.getTreatmentEpisodeId());
									if (usedsession == sessions) {
										if (sessions > 0) {
											break;
										}
									}
									usedsession = usedsession + 1;
									if (treatmentEpisodeId.equals(zero)) {
										usedsession = 0;
									}
									notAvailableSlot.setUsedsession(Integer.toString(usedsession));
									appointmentid = notAvailableSlotDAO.saveAppointment(notAvailableSlot);
									int updatusessession = diaryManagementDAO.updateTreatmentSession(usedsession,
											notAvailableSlot.getTreatmentEpisodeId());
									// Log Save
									status = "Booked";
									int logsave = notAvailableSlotDAO.saveApmtInLog(appointmentid, date, time,
											loginInfo.getUserId(), clientId, commencingTemp, sTime, status,
											DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
								} else {
									break;
								}

							} else {
								// break;
							}
						} else if (sunday && cweekName.equals(Constants.SUNDAY)) {
							// common for all
							int apmtSlotid = notAvailableSlotDAO.getAppointmentSlotID(notAvailableSlot.getCommencing(),
									notAvailableSlot.getDiaryUserId(), notAvailableSlot.getSTime(),
									notAvailableSlot.getEndTime(), notAvailableSlot.getLocation());
							if (apmtSlotid != 0) {
								notAvailableSlot.setApmtSlotId(apmtSlotid);
								boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(
										notAvailableSlot.getCommencing(), location, diaryuserId,
										notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
								if (checkEventExist) {
									checkEventExist = cheForWeekRepeatAfterEventExist(notAvailableSlot.getCommencing(),
											location, diaryuserId, notAvailableSlot.getSTime(),
											notAvailableSlot.getEndTime());
								}
								if (!checkEventExist) {
									int usedsession = diaryManagementDAO
											.getTempUsedSession(notAvailableSlot.getTreatmentEpisodeId());
									int sessions = diaryManagementDAO
											.getTempSessions(notAvailableSlot.getTreatmentEpisodeId());
									if (usedsession == sessions) {
										if (sessions > 0) {
											break;
										}
									}
									usedsession = usedsession + 1;
									if (treatmentEpisodeId.equals(zero)) {
										usedsession = 0;
									}
									notAvailableSlot.setUsedsession(Integer.toString(usedsession));
									appointmentid = notAvailableSlotDAO.saveAppointment(notAvailableSlot);
									int updatusessession = diaryManagementDAO.updateTreatmentSession(usedsession,
											notAvailableSlot.getTreatmentEpisodeId());
									// Log Save
									status = "Booked";
									int logsave = notAvailableSlotDAO.saveApmtInLog(appointmentid, date, time,
											loginInfo.getUserId(), clientId, commencingTemp, sTime, status,
											DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
								} else {
									break;
								}

							} else {
								// break;
							}
						} else if (!monday && !tuesday && !wednesday && !thursday && !friday && !saturday && !sunday) {
							// common for all
							int apmtSlotid = notAvailableSlotDAO.getAppointmentSlotID(notAvailableSlot.getCommencing(),
									notAvailableSlot.getDiaryUserId(), notAvailableSlot.getSTime(),
									notAvailableSlot.getEndTime(), notAvailableSlot.getLocation());
							if (apmtSlotid != 0) {
								notAvailableSlot.setApmtSlotId(apmtSlotid);
								boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(
										notAvailableSlot.getCommencing(), location, diaryuserId,
										notAvailableSlot.getSTime(), notAvailableSlot.getEndTime());
								if (checkEventExist) {
									checkEventExist = cheForWeekRepeatAfterEventExist(notAvailableSlot.getCommencing(),
											location, diaryuserId, notAvailableSlot.getSTime(),
											notAvailableSlot.getEndTime());
								}
								if (!checkEventExist) {
									int usedsession = diaryManagementDAO
											.getTempUsedSession(notAvailableSlot.getTreatmentEpisodeId());
									int sessions = diaryManagementDAO
											.getTempSessions(notAvailableSlot.getTreatmentEpisodeId());
									if (usedsession == sessions) {
										if (sessions > 0) {
											break;
										}
									}
									usedsession = usedsession + 1;
									if (treatmentEpisodeId.equals(zero)) {
										usedsession = 0;
									}
									notAvailableSlot.setUsedsession(Integer.toString(usedsession));
									appointmentid = notAvailableSlotDAO.saveAppointment(notAvailableSlot);
									int updatusessession = diaryManagementDAO.updateTreatmentSession(usedsession,
											notAvailableSlot.getTreatmentEpisodeId());
									// Log Save
									status = "Booked";
									int logsave = notAvailableSlotDAO.saveApmtInLog(appointmentid, date, time,
											loginInfo.getUserId(), clientId, commencingTemp, sTime, status,
											DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
								} else {
									break;
								}

							} else {
								// break;
							}
						}

					}
				}
			}

			session.setAttribute("connection", connection);

			notAvailableSlot = notAvailableSlotDAO.getAvailableSlotdata(appointmentid);

			String diaryuseridname = notAvailableSlotDAO.getDiaryUserIdName(notAvailableSlot.getDiaryUserId());

			String updatedAppointmentData = notAvailableSlot.getCommencing() + "/" + notAvailableSlot.getsTime() + "/"
					+ notAvailableSlot.getEndTime() + "/" + notAvailableSlot.getDiaryUser() + "/"
					+ notAvailableSlot.getClientName() + "/" + notAvailableSlot.getDiaryUserId() + "/"
					+ notAvailableSlot.getId() + "/" + notAvailableSlot.getClientId() + "/" + loginInfo.getClinicid()
					+ "/" + loginInfo.getUserType() + "/" + loginInfo.getClinicUserid() + "/" + diaryuseridname;
			TestingLog.debug("saveAppoinment()  728");
			// send sms
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			boolean isSMSActive = clinicDAO.isSmsActive(loginInfo.getId());
			if (isSMSActive) {
				TestingLog.debug("saveAppoinment()  728"+isSMSActive);
			}
			// autosms(Integer.toString(appointmentid), status
			// ,beforeUpdateData.getSTime() ,beforeUpdateData.getCommencing() );

			// autosms(Integer.toString(appointmentid), status
			// ,beforeUpdateData.getSTime()
			// ,beforeUpdateData.getCommencing(),seqno );
			
			Client client3=new Client();
			client3.setHeight(height);
			client3.setWeight(weight);
			client3.setBmi(bmi);
			client3.setHead_cir(headcir);
			client3.setTemprature(tempr);
			client3.setAllergy(allergy);
			if(loginInfo.isSimpliclinic()) {
				if(client3.getAllergy().equals("yes")) {
					client3.setAllergynotes(allergynotes);
				}else {
					client3.setAllergynotes("NO");
				}
			}else {
				client3.setAllergynotes("");
			}
			client3.setClientid(clientId);
			client3.setAppointmentid(String.valueOf(appointmentid));
			int res=clientDAO.saveBMIPatient(client3);
			
			//  27-05-2019
			int ageinmonth = DateTimeUtils.getmonthsfromdob(client2.getDob());
			if(ageinmonth<=60 && ageinmonth>=0){
				if(height!=null){
					if(!height.equals("") && !height.equals("0")){
						Client client4 = new Client();
						client4.setHeightdata(height);
						client4.setWeightdata(weight);
						client4.setBmidata(bmi);
						client4.setHeadcircumferncedata(headcir);
						client4.setClientid(clientId);
						client4.setMonth(""+ageinmonth);
						client4.setUserid(loginInfo.getUserId());
						String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						client4.setDate(datetime);
						int id = clientDAO.check_child_growth_data(clientId, ""+ageinmonth);
						int res1 = 0;
						if (id > 0) {
							client4.setId(id);
							// update
							res1 = clientDAO.updateChildGrowthData(client4, "height");
						} else {
							// insert
							res1 = clientDAO.saveChildGrowthData(client4, "height");
						}
					}
				}
				if(weight!=null){
					if(!weight.equals("") && !weight.equals("0")){
						Client client4 = new Client();
						client4.setHeightdata(height);
						client4.setWeightdata(weight);
						client4.setBmidata(bmi);
						client4.setHeadcircumferncedata(headcir);
						client4.setClientid(clientId);
						client4.setMonth(""+ageinmonth);
						client4.setUserid(loginInfo.getUserId());
						String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						client4.setDate(datetime);
						int id = clientDAO.check_child_growth_data(clientId, ""+ageinmonth);
						int res1 = 0;
						if (id > 0) {
							client4.setId(id);
							// update
							res1 = clientDAO.updateChildGrowthData(client4, "weight");
						} else {
							// insert
							res1 = clientDAO.saveChildGrowthData(client4, "weight");
						}
					}
				}
				if(bmi!=null){
					if(!bmi.equals("") && !bmi.equals("0")){
						Client client4 = new Client();
						client4.setHeightdata(height);
						client4.setWeightdata(weight);
						client4.setBmidata(bmi);
						client4.setHeadcircumferncedata(headcir);
						client4.setClientid(clientId);
						client4.setMonth(""+ageinmonth);
						client4.setUserid(loginInfo.getUserId());
						String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						client4.setDate(datetime);
						int id = clientDAO.check_child_growth_data(clientId, ""+ageinmonth);
						int res1 = 0;
						if (id > 0) {
							client4.setId(id);
							// update
							res1 = clientDAO.updateChildGrowthData(client4, "bmi");
						} else {
							// insert
							res1 = clientDAO.saveChildGrowthData(client4, "bmi");
						}
					}
				}
				if(headcir!=null){
					if(!headcir.equals("") && !headcir.equals("0")){
						Client client4 = new Client();
						client4.setHeightdata(height);
						client4.setWeightdata(weight);
						client4.setBmidata(bmi);
						client4.setHeadcircumferncedata(headcir);
						client4.setClientid(clientId);
						client4.setMonth(""+ageinmonth);
						client4.setUserid(loginInfo.getUserId());
						String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						client4.setDate(datetime);
						int id = clientDAO.check_child_growth_data(clientId, ""+ageinmonth);
						int res1 = 0;
						if (id > 0) {
							client4.setId(id);
							// update
							res1 = clientDAO.updateChildGrowthData(client4, "headcircumfernce");
						} else {
							// insert
							res1 = clientDAO.saveChildGrowthData(client4, "headcircumfernce");
						}
					}
				}

			}
			SlotAvailableAction slotAvailableAction=new SlotAvailableAction();
			if(loginInfo.isSjivh()) {
			
			slotAvailableAction.immunizationProcessVet(clientId,"",false);	
			slotAvailableAction.immunizationProcess(clientId,"",false);
			}else {
			if(client2.getAge()<=14) {
			
			slotAvailableAction.immunizationProcess(clientId,"",false);
			}
			}
			TestingLog.debug("saveAppoinment()  1548"+isSMSActive);
			// @ruchi check smscheck then send msg
			autosmsCheckSms(Integer.toString(appointmentid), status, beforeUpdateData.getSTime(),
					beforeUpdateData.getCommencing(), seqno);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + updatedAppointmentData + "");

		} catch (Exception e) {
			e.printStackTrace();
//			StringWriter sw = new StringWriter();
//	         PrintWriter pw = new PrintWriter(sw);
//	         e.printStackTrace(pw);
//			Tra.exceptionMail(sw.toString());
			// log.debug("***************"+e.getMessage());
			TestingLog.debug("saveAppoinment()  "+e.getMessage()+"");
		}

		finally {
			connection.close();
		}
		TestingLog.debug("saveAppoinment()  1566");
		return null;
	}

	public boolean cheForWeekRepeatAfterEventExist(String commencing, String location, String diaryuserId,
			String starttime, String endtime) throws Exception {
		boolean checkEventExist = true;
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			int coutnEsistingSlot = notAvailableSlotDAO.coutnEsistingSlot(commencing, location, diaryuserId, starttime,
					endtime, 0);

			if (coutnEsistingSlot == 1) {
				String existStartTime = notAvailableSlotDAO.getExistStartTime(commencing, location, diaryuserId,
						starttime, endtime, 0);

				String duration = DateTimeUtils.getDuration(starttime, endtime);

//				System.out.println(duration);

				String sumoftime = DateTimeUtils.getSumofTime(starttime, duration);

				if (sumoftime.equals(existStartTime)) {
					checkEventExist = false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}
		return checkEventExist;

	}

	private void saveOpdCharge(int appointmentid, NotAvailableSlot notAvailableSlot, String invcetype, String howpaid,
			String totalamount, String discounts, String payAmount, Connection connection, String disctype, String opdotcharge, String opdotregcharge, String upino, String remark)
			throws Exception {

		try {

			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			AccountLogDAO accountLogDAO = new JDBCAccountLogDAO(connection);
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			String location = notAvailableSlot.getLocation();
			String chargeType = "Submit";

			String temp[] = notAvailableSlot.getCommencing().split("-");
			String commencing = temp[2] + "-" + temp[1] + "-" + temp[0];
			double debit = Double.parseDouble(totalamount);
			if(discounts==null){
				discounts="0";
			}
			if(discounts.equals("")){
				discounts="0";
			}
			double discount = Double.parseDouble(discounts);

			ArrayList<Accounts> invoiceList = new ArrayList<Accounts>();
			int thirdPartyID = accountsDAO.getThirdPartyID(notAvailableSlot.getClientId());

			String payBuy = "0";
			if (notAvailableSlot.getPayBy().equals(Constants.PAY_BY_THIRD_PARTY)) {
				payBuy = "1";
			}

			String date = DateTimeUtils.getDateinSimpleFormate(new Date());
			String stemp[] = date.split(" ");

			String temps[] = stemp[0].split("-");
			date = temps[2] + "-" + temps[1] + "-" + temps[0];

			ClientDAO clientDAO = new JDBCClientDAO(connection);
			Client client = clientDAO.getClientDetails(notAvailableSlot.getClientId());
			String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();

			CompleteAppointment completeAppointment = new CompleteAppointment();
			completeAppointment.setClientId(notAvailableSlot.getClientId());
			completeAppointment.setPractitionerId(Integer.toString(notAvailableSlot.getDiaryUserId()));
			completeAppointment.setUser(clientname);
			completeAppointment.setInvoiceDate(date);
			completeAppointment.setChargeId(notAvailableSlot.getApmtType());
			completeAppointment.setChargeType("Charge");
			completeAppointment.setAppointmentid(Integer.toString(appointmentid));
			completeAppointment.setPractitionerName(notAvailableSlot.getDiaryUser());
			completeAppointment.setLocation(location);
			completeAppointment.setIpd("0");
			completeAppointment.setGpriscid("0");
			completeAppointment.setGinvstid("0");
			completeAppointment.setAdditionalcharge_id(null);
			completeAppointment.setWardid(null);
			completeAppointment.setPayBuy(payBuy);
			String apmtTYpeText = notAvailableSlotDAO.getAppointmentTypeText(notAvailableSlot.getApmtType());
			completeAppointment.setApmtType(apmtTYpeText);
			completeAppointment.setQuantity(1);
			completeAppointment.setCommencing(notAvailableSlot.getCommencing());
			completeAppointment.setMasterchargetype("Appointment Charge");

			// insert into apm_invoice
			int selfInvoice = completeAptmDAO.saveAmpmInvoice(completeAppointment, loginInfo.getId(),loginInfo.getUserId());

			// insert in apm_invoice_assessment
			//completeAppointment.setCharges(totalamount);
			completeAppointment.setCharges(opdotcharge);
			NotAvailableSlot not = notAvailableSlotDAO.getOTData(Integer.toString(appointmentid));
			if (!notAvailableSlot.getProcedure().equals("0")) {
				completeAppointment.setCharges(not.getChargeamout());
				completeAppointment.setMasterchargetype(notAvailableSlot.getProcedure());
			}
			int result = completeAptmDAO.saveInvoiceAssesment(completeAppointment, selfInvoice);

			// save ot more charges

			if (!notAvailableSlot.getProcedure().equals("0")) {

				//completeAppointment.setMasterchargetype(notAvailableSlot.getProcedure());
				completeAppointment.setMasterchargetype("IP / OP PROCEDURE");

				completeAppointment.setApmtType(Constants.SURGEON_CHARGE);
				completeAppointment.setCharges(notAvailableSlot.getPsurcharge());
				result = completeAptmDAO.saveInvoiceAssesment(completeAppointment, selfInvoice);

				completeAppointment.setApmtType(Constants.ANISTHESIA_CHARGE);
				completeAppointment.setCharges(notAvailableSlot.getPanetcharge());
				result = completeAptmDAO.saveInvoiceAssesment(completeAppointment, selfInvoice);

				completeAppointment.setApmtType(Constants.SIC_CHARGE);
				completeAppointment.setCharges(notAvailableSlot.getSic());
				result = completeAptmDAO.saveInvoiceAssesment(completeAppointment, selfInvoice);
				
				//  11 July 2018 assiting staff charges
				completeAppointment.setApmtType(Constants.ASSISTING_STAFF_CHARGE);
				if(notAvailableSlot.getAssistaffcharge()!=null){
					if(notAvailableSlot.getAssistaffcharge().equals("")){
						notAvailableSlot.setAssistaffcharge("0");
					}
				}else{
					notAvailableSlot.setAssistaffcharge("0");
				}
				completeAppointment.setCharges(notAvailableSlot.getAssistaffcharge());
				result = completeAptmDAO.saveInvoiceAssesment(completeAppointment, selfInvoice);
				
			}else{
				//  12 July 2018 /to set registration charge
				boolean flag = completeAptmDAO.isclientIdInApmt(notAvailableSlot.getClientId());
				boolean isfirsttimereg=completeAptmDAO.isOPDFirstCharge(notAvailableSlot.getClientId(), "");
				boolean issetchargenew= false;
				if(!flag&&isfirsttimereg){
					issetchargenew=true;
				}
				if(!flag||issetchargenew){
					if(opdotregcharge!=null){
						if(!opdotregcharge.equals("")){
							if(Double.parseDouble(opdotregcharge)>0){
								//double opdregcharge =  completeAptmDAO.getOpdRegCharge();
								completeAppointment.setApmtType("OPD Registration Charge");
								completeAppointment.setCharges(opdotregcharge);
								completeAppointment.setMasterchargetype("Registration Charge");
								result = completeAptmDAO.saveInvoiceAssesment(completeAppointment, selfInvoice);
							}
						}
					}
				}
			}

			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			// logdata
			String status = "Created";
			int log = accountLogDAO.saveAmpmInvoice(completeAppointment, selfInvoice, status,
					DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));

			// reset invoice
			int resetinv = accountsDAO.getMaxResetInv();
			int resetcreditinv = accountsDAO.getMaxResetCreditInv();
			int rinv = 0;
			if (resetinv > resetcreditinv) {
				rinv = resetinv + 1;
			} else {
				rinv = resetcreditinv + 1;
			}
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			int ipdid = 0;
			if (invcetype != null) {
				if (invcetype.equals("2")) {
					ipdid = ipdDAO.getLastIpdId(notAvailableSlot.getClientId());
				} else {
					ipdid = 0;
				}
			} else {
				ipdid = 0;
			}
			int invoiceid = accountsDAO.saveChargesInvoice(notAvailableSlot.getPayBy(),
					DateTimeUtils.getCommencingDate1(commencing), Integer.parseInt(notAvailableSlot.getClientId()),
					debit, discount, "", thirdPartyID, location,
					DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()), loginInfo.getId(),
					"0", invcetype, rinv, null, null, String.valueOf(notAvailableSlot.getDiaryUserId()), ipdid,0,"0");
			DateFormat dateFormat1 = new SimpleDateFormat("yyyy");
			Calendar cal1 = Calendar.getInstance();
			String a_year = dateFormat1.format(cal1.getTime());
			if(notAvailableSlot.isOt()) {
				int up=accountsDAO.updateOtid(invoiceid,appointmentid);
			}
			//lokeh for generating seqno
			int res1 = accountsDAO.getMaxOpdseqNo(a_year);
			res1 =  res1+1;
			int ress = accountsDAO.updateInvoiceSeqNo("1",res1,invoiceid,a_year);

		
			// posting opd invoice
			String podate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());

			int res = chargesAccountProcessingDAO.updateIclosed(Integer.toString(invoiceid), podate);
			if(loginInfo.isShow_unpost()!=true){
				res = chargesAccountProcessingDAO.updateIpost(Integer.toString(invoiceid), podate);
			}
			// ledger for credit invoice
			if (invoiceid > 0) {
				String itype = accountsDAO.getInvoiceTypeId(invoiceid);
				// String serviceid =
				// chargesAccountProcessingDAO.getLedgrServiceIds(itype);
				String serviceid = itype;
				String ledgerid = chargesAccountProcessingDAO.getledgerID(serviceid, "0", "0");

				double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
				lbal = lbal + debit;
				String credit = "" + debit + "";
				String ldebit = "0";
				String product = "xxxxx";
				String partyid = notAvailableSlot.getClientId();
				String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
						ledgerid, lcommencing, "" + invoiceid + "", 0,"0","0","0","0","0",0,0,"0");
				
				//second effect
				 lbal = 0;
				 credit = "0";
				 ldebit = "" + debit + "";
				 product = "xxxxx";
				 partyid = notAvailableSlot.getClientId();
				 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				 saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
						ledgerid, lcommencing, "" + invoiceid + "", 0,"0","0","0","0","0",0,0,"0");
			}
			// update discount
			String userid = loginInfo.getUserId();
			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int p = accountsDAO.updatePercentageAmount(Integer.toString(invoiceid), discounts, disctype, userid,
					datetime);

			double discdebit = Double.parseDouble(discounts);
			if (disctype.equals("0")) {
				discdebit = (debit * Double.parseDouble(discounts)) / 100;
			}

			// discount ledger
			if (discdebit > 0) {

				String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds("Discount");
				String ledgerid = chargesAccountProcessingDAO.getledgerID(serviceid, "0", "0");

				double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
				lbal = lbal + discdebit;
				String credit = "" + discdebit + "";
				String ldebit = "0";
				String product = "" + invoiceid + "";
				String partyid = notAvailableSlot.getClientId();
				String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
						ledgerid, lcommencing, "" + invoiceid + "", 0,"0","0","0","0","0",0,0,"0");
				
				//second effect
				 lbal = 0;
				 credit = "0";
				 ldebit = "" + discdebit + "";
				 product = "xxxxx";
				 partyid = notAvailableSlot.getClientId();
				 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				 saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
						ledgerid, lcommencing, "" + invoiceid + "", 0,"0","0","0","0","0",0,0,"0");

			}

			// save log
			int invoiceid1 = accountLogDAO.saveChargesInvoice(notAvailableSlot.getPayBy(),
					DateTimeUtils.getCommencingDate1(commencing), Integer.parseInt(notAvailableSlot.getClientId()),
					debit, discount, "", thirdPartyID, location, invoiceid,
					DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));

			String chargeInvoiceid = accountsDAO.getAppointmentInvoiceid(Integer.toString(appointmentid));
			int save = accountsDAO.saveChargesAssesment(Integer.parseInt(chargeInvoiceid), invoiceid);
			int update = accountsDAO.updateChargeType(chargeInvoiceid, "Submit");

			String chequeno = request.getParameter("chequeno");
			String bankname = request.getParameter("bankname");

			//update charge invoiced
			ArrayList<Master>chargeidList = accountsDAO.getInvoicedChargeidList(invoiceid);
			for(Master m : chargeidList){
				int upc = accountsDAO.updateChargeInvoideid(m.getId(),invoiceid);
			}
			
			// save payment
			if(!payAmount.equals("0")){
			
				Accounts accounts = accountsDAO.getInvoiceChargesDetails(""+invoiceid);
				
			int re = accountsDAO.saveChargesPayment(notAvailableSlot.getClientId(), invoiceid, payAmount, howpaid,
					thirdPartyID, "", DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()), 0,
					loginInfo.getUserId(), chequeno, bankname,accounts.getPredayornot(),accounts.getDate(),accounts.getItype(),upino,remark);
			
			int upstatus = accountsDAO.updateOpdPaymentStatus(appointmentid,invoiceid);
			
			//update invoice type payment autono
			int maxno = accountsDAO.getMaxInvoiceTypePaymentNo(re,invcetype);
			int u = accountsDAO.updateInvoicetypePaymentNo(re,maxno,invcetype);
			// Sms to Patient
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			Clinic clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			boolean isPaymentSms = clinicDAO.isPaymentSMSActive(loginInfo.getClinicid());
			if (loginInfo.isOpd_payamnt_sms()) {
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				String templateid = userProfileDAO.getSMSTemplateID("Payment");
				String msg = "Thanks " + clientname + " for payment of Rupees " + payAmount + " from- "
						+ clinic.getClinicName() + "";
				SmsService service = new SmsService();
				service.sendSms(msg, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);

			}

			int result1 = accountLogDAO.saveChargesPayment(notAvailableSlot.getClientId(), invoiceid, payAmount,
					howpaid, thirdPartyID, "", DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()), "");

			// ledger services
			ArrayList<Master> ledgerservicesList = accountsDAO.getLedgerServicesList(invoiceid);

			int l = 1;
			// for(Master master : ledgerservicesList){

			String itype = accountsDAO.getInvoiceTypeId(invoiceid1);
			// String serviceid =
			// chargesAccountProcessingDAO.getLedgrServiceIds(itype);
			String serviceid = itype;
			String ledgerid = chargesAccountProcessingDAO.getledgerID(serviceid, howpaid,
					notAvailableSlot.getBnkname());

			double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
			lbal = lbal + Double.parseDouble(payAmount);
			String credit = "0";
			String ldebit = payAmount;
			String product = "xxxxx";
			String partyid = notAvailableSlot.getClientId();
			String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal, ledgerid,
					lcommencing, "" + invoiceid + "", re,"0","0","0","0","0",0,0,"0");
			
			//second effect
			 lbal = 0;
			 credit = payAmount;
			 ldebit = "0";
			 product = "xxxxx";
			 partyid = notAvailableSlot.getClientId();
			 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			 saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
					ledgerid, lcommencing, "" + invoiceid + "", 0,"0","0","0","0","0",0,0,"0");
			 }else{
				 //if payamount not zero
				 int upstatus = accountsDAO.updateOpdPaymentStatus(appointmentid,invoiceid);
			 }

		} catch (Exception e) {
			e.printStackTrace();
//			StringWriter sw = new StringWriter();
//	         PrintWriter pw = new PrintWriter(sw);
//	         e.printStackTrace(pw);
//			Tra.exceptionMail(sw.toString());
		}

	}

	public void autosmsCheckSms(String appointmentid, String status, String starttime, String apmtdate, int seqNO)
			throws Exception {

		// String appointmentid = request.getParameter("appointmentid");

		Connection connection = null;

		try {
			TestingLog.debug("autosmsCheckSms()  1944");
			connection = Connection_provider.getconnection();
			// send sms
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			MasterDAO masterdao=new JDBCMasterDAO(connection);
			NotAvailableSlot notAvailableSlot = notAvailableSlotDAO
					.getApmtDetailsForLog(Integer.parseInt(appointmentid));
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			boolean isSMSActive = clinicDAO.isSmsActive(loginInfo.getId());
			Client client = clientDAO.getClientDetails(notAvailableSlot.getClientId());
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());

			String message = AllTemplateAction.getAppointmentSMSTextNew(notAvailableSlot.getClientId(),
					Integer.parseInt(appointmentid), connection, loginInfo, seqNO);

			String messageapmuser = AllTemplateAction.getAppointmentSMSTextToapmUser(notAvailableSlot.getClientId(),
					Integer.parseInt(appointmentid), connection, loginInfo, seqNO);

			EmrDAO emrDAO = new JDBCEmrDAO(connection);
			Clinic clinic = emrDAO.getsmsCheckConditionList(loginInfo.getClinicid());
			
		    Clinic clinic1 = clinicDAO.getWhatsappActive(loginInfo.getId());
			TestingLog.debug("autosmsCheckSms()  1966");
			String sms_type="";
			/*if(clinic1.getWsms()==1){
			WhatsAppService whatsapp=new WhatsAppService();
			whatsapp.sendMsg(client.getMobNo(), message);
			}*/
			
			if (status.equals("Modified")) {
				boolean rsult = false;
				if (!starttime.equals(notAvailableSlot.getSTime())) {
					rsult = true;
				}

				if (!apmtdate.equals(notAvailableSlot.getCommencing())) {
					rsult = true;
				}

				if (rsult) {
					message = AllTemplateAction.getAppointmentSMSTextNew(notAvailableSlot.getClientId(),
							Integer.parseInt(appointmentid), connection, loginInfo, seqNO);
					if (loginInfo.getCountry().equals("India")) {
						SmsService s = new SmsService();
						sms_type="Tokan";
						loginInfo.setSms_type(sms_type);
						loginInfo.setPatient_name(client.getFullname());
						if (clinic.isSmscheck()) {
							/*
							 * sms_type="Tokan"; loginInfo.setSms_type(sms_type);
							 * loginInfo.setPatient_name(client.getFullname());
							 */
							String templateid = userProfileDAO.getSMSTemplateID("Tokan SMS");
							s.sendSms(message, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
						}

						/*if (isSMSActive) {*/
							if (clinic.isSmscheckdoctor()) {
								String templateid = userProfileDAO.getSMSTemplateID("Appointment SMS DR");
								s.sendSms(messageapmuser, userProfile.getMobile(), loginInfo, new EmailLetterLog(),templateid);
							}
							WhatsAppService w=new WhatsAppService();
							if(clinic1.getWsms()==1){
							
							    NotAvailableSlot notAvailableSlot1 = notAvailableSlotDAO.getApmtDetailsForLog(Integer.parseInt(appointmentid));
							    String commencing = DateTimeUtils.getCommencingDate1(notAvailableSlot.getCommencing());
								String msg=client.getFullname()+" टोकन नम्बर "+seqNO+" आपने दिनांक "+commencing+" को डाॅ. "+notAvailableSlot.getDiaryUser()+", "+loginInfo.getClinicName()+" में अपाइंटमेंट बुक किया है।";
								String templateid = userProfileDAO.getSMSTemplateID("Tokan SMS");
								w.sendMsg(loginInfo, client.getMobNo(),msg);
							}
						/*}*/
					}
				}

			} else {
				if (loginInfo.getCountry().equals("India")) {
					SmsService s = new SmsService();
					sms_type="Tokan";
			      	loginInfo.setSms_type(sms_type);
			      	loginInfo.setPatient_name(client.getFullname());
					if (clinic.isSmscheck()) {
					
						String templateid = userProfileDAO.getSMSTemplateID("Tokan SMS");
						s.sendSms(message, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
						// s.sendSms(message, client.getMobNo(), loginInfo, new
						// EmailLetterLog());
					}
					/*if (isSMSActive) {*/
						if (clinic.isSmscheckdoctor()) {
							String templateid = userProfileDAO.getSMSTemplateID("Appointment SMS DR");
							s.sendSms(messageapmuser, userProfile.getMobile(), loginInfo, new EmailLetterLog(),templateid);
						}
					/*}*/
						WhatsAppService w=new WhatsAppService();
						if(clinic1.getWsms()==1){
							NotAvailableSlot notAvailableSlot1 = notAvailableSlotDAO.getApmtDetailsForLog(Integer.parseInt(appointmentid));
							String commencing = DateTimeUtils.getCommencingDate1(notAvailableSlot.getCommencing());
							String msg=client.getFullname()+" टोकन नम्बर "+seqNO+" आपने दिनांक "+commencing+" को डाॅ. "+notAvailableSlot.getDiaryUser()+", "+loginInfo.getClinicName()+" में अपाइंटमेंट बुक किया है।";
							String templateid = userProfileDAO.getSMSTemplateID("Tokan SMS");
						    boolean sms=  w.sendMsg(loginInfo,client.getMobNo(),msg);
						   if(!sms) {
						    String error="message Failed";
						    int smslog=masterdao.insertSmsdata(loginInfo,""+sms,"",client.getMobNo(),0,error);
						    }
						}
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
//			StringWriter sw = new StringWriter();
//	         PrintWriter pw = new PrintWriter(sw);
//	         e.printStackTrace(pw);
//			Tra.exceptionMail(sw.toString());
			TestingLog.debug("autosmschksms()  "+e.getMessage()+"");
		} finally {

			connection.close();
		}

	}

	public String getConditionPatient() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			String clientId = request.getParameter("clientId");
			String id = notAvailableSlotDAO.getConditionPatient(clientId);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + id + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String duration() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		String selectedid = request.getParameter("selectedid");
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			String durationStr = notAvailableSlotDAO.getDuration(selectedid);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + durationStr + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String diaryduration() throws SQLException {

		String slotid = request.getParameter("diaryslotid");
//		System.out.println(slotid);

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			String duration = notAvailableSlotDAO.getDiaryDuration(slotid);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + duration + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String eventExist() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			String commencing = request.getParameter("commencing");
			String location = request.getParameter("location");
			String diaryuserId = request.getParameter("diaryuserId");
			String starttime = request.getParameter("starttime");
			String endtime = request.getParameter("endtime");
			int editAppointId = Integer.parseInt(request.getParameter("editAppointId"));

			boolean checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(commencing, location, diaryuserId,
					starttime, endtime);
			if (editAppointId != 0) {
				NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getAvailableSlotdata(editAppointId);

				if (starttime.equals(notAvailableSlot.getsTime()) && endtime.equals(notAvailableSlot.getEndTime())) {
					checkEventExist = false;
				} else {

					checkEventExist = notAvailableSlotDAO.checkEventAllreadyExist(commencing, location, diaryuserId,
							starttime, endtime, editAppointId);
				}
			}

			if (checkEventExist) {
				int coutnEsistingSlot = notAvailableSlotDAO.coutnEsistingSlot(commencing, location, diaryuserId,
						starttime, endtime, editAppointId);

				if (coutnEsistingSlot == 1) {
					String existStartTime = notAvailableSlotDAO.getExistStartTime(commencing, location, diaryuserId,
							starttime, endtime, editAppointId);

					String duration = DateTimeUtils.getDuration(starttime, endtime);

//					System.out.println(duration);

					String sumoftime = DateTimeUtils.getSumofTime(starttime, duration);

					if (sumoftime.equals(existStartTime)) {
						checkEventExist = false;
					}

					// boolean checkDurationExist =
					// notAvailableSlotDAO.checkDurationExist(duration);

					/*
					 * if(checkDurationExist){ checkEventExist = false; }
					 */
				}
				if (editAppointId != 0) {
					if (coutnEsistingSlot == 1 || coutnEsistingSlot == 2) {
						String duration = DateTimeUtils.getDuration(starttime, endtime);

						String existStartTime = notAvailableSlotDAO.getEditExistStartTime(commencing, location,
								diaryuserId, starttime, endtime, editAppointId);

//						System.out.println(duration);

						String sumoftime = DateTimeUtils.getSumofTime(starttime, duration);

						if (sumoftime.equals(existStartTime)) {
							checkEventExist = false;
						}

					}
				}

			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + checkEventExist + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String setPendingAmountOfClient() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);

			String clientId = request.getParameter("clientId");

			/*
			 * double balanceTotal =
			 * notAvailableSlotDAO.getPendingBalanceTotal(clientId); String
			 * btotal = DateTimeUtils.changeFormat(balanceTotal);
			 */

			double debit = diaryManagementDAO.getClientDebitTotal(clientId);
			double payment = diaryManagementDAO.getClientPayment(clientId);

			double balance = debit - payment;

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + DateTimeUtils.changeFormat(balance) + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String loggedinuser() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin", ""+Constants.DB_USER+"", ""+Constants.DB_PWD+"");
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			String apmuserlist = notAvailableSlotDAO.getApmLoggedUserList(loginInfo.getDbName());
			session.setAttribute("apmuserlist", apmuserlist);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(apmuserlist);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String multiloc() throws SQLException {

		if (!verifyLogin(request)) {
			return "login";
		}

		// var url =
		// "multilocNotAvailableSlot?title="+title+"&endtime="+endtime+"&userid="+userid+"&commencing="+commencing+"
		// ";
		String title = request.getParameter("title");
		String temp[] = title.split(" ");
		String selectedStarttime = temp[0];

		String diaryuserid = request.getParameter("userid");
		String commencing = request.getParameter("commencing");
		String prevEndTime = request.getParameter("endtime");

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getInitilizedElementData(diaryuserid, commencing,
					selectedStarttime, prevEndTime);

			String data = notAvailableSlot.getApmtSlotId() + "/" + notAvailableSlot.getSTime() + "/"
					+ notAvailableSlot.getEndTime() + "/" + notAvailableSlot.getLocation() + "/"
					+ notAvailableSlot.getDisciplineid();

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + data + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String rewraped() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {

			String eventid = request.getParameter("id");

			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			String opendb = (String) session.getAttribute("openedb");
			ArrayList<NotAvailableSlot> wrapedLiest = diaryManagementDAO.getWrapedEventData(eventid, opendb);

			String str = "";

			for (NotAvailableSlot notAvailableSlot : wrapedLiest) {

				if (notAvailableSlot.getImagename() == null) {
					notAvailableSlot.setImagename("");
				}

				Client client = clientDAO.getPatientBMIData(notAvailableSlot.getClientId(),Integer.parseInt(eventid));

				str = notAvailableSlot.getId() + "#" + notAvailableSlot.getClientName() + "#"
						+ notAvailableSlot.getApmtType() + "#" + notAvailableSlot.getSTime() + "#"
						+ notAvailableSlot.getEndTime() + "#" + notAvailableSlot.getDuration() + "#"
						+ notAvailableSlot.getStatus() + "#" + notAvailableSlot.getNotes() + "#"
						+ notAvailableSlot.getArrivedStatus() + "#" + notAvailableSlot.isDna() + "#"
						+ notAvailableSlot.getClientId() + "#" + notAvailableSlot.getCommencing() + "#"
						+ notAvailableSlot.getDiaryUserId() + "#" + notAvailableSlot.getCharge() + "#"
						+ notAvailableSlot.getReasonforblock() + "#" + notAvailableSlot.getTreatmentEpisodeId() + "#"
						+ notAvailableSlot.getApmttypetext() + "#" + notAvailableSlot.getClientName() + "#"
						+ notAvailableSlot.getUsedsession() + "#" + notAvailableSlot.getTptypeid() + "#"
						+ notAvailableSlot.getTpnameid() + "#" + notAvailableSlot.getPractitionerEmail() + "#"
						+ notAvailableSlot.getClientEmail() + "#" + notAvailableSlot.getLocid() + "#"
						+ notAvailableSlot.getLocation() + "#" + notAvailableSlot.getCondition() + "#"
						+ notAvailableSlot.getWhopay() + "#" + notAvailableSlot.isAppointmentCompleted() + "#"
						+ notAvailableSlot.isChargeCompleted() + "#" + notAvailableSlot.getDiaryUser() + "#"
						+ notAvailableSlot.getApmtSlotId() + "#" + notAvailableSlot.getTpName() + "#"
						+ loginInfo.getEmail() + "#" + notAvailableSlot.getOtid() + "#"
						+ notAvailableSlot.getImagename() + "#" + client.getHeight() + "#" + client.getWeight() + "#"
						+ client.getBmi() + "#" + client.getPulse() + "#" + client.getSysbp() + "#" + client.getDiabp()
						+ "#" + notAvailableSlot.getOtplan() + "#" + notAvailableSlot.getProcedure() + "#"
						+ notAvailableSlot.getSurgeon() + "#" + notAvailableSlot.getAnesthesia() + "#"
						+ notAvailableSlot.getIpdno() + "#" + notAvailableSlot.getWardid() + "#"
						+ notAvailableSlot.getAsistantdoclist() + "#" + notAvailableSlot.getToken() + "#"
						+ client.getSugarfasting() + "#" + client.getPostmeal() + "#" + loginInfo.getUhid() + "#"
						+ notAvailableSlot.getUhid()+ "#" + client.getTemprature()+ "#" + client.getSpo()+ "#" + client.getHead_cir();

				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(str);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	// IPD

	public String getbellcolor() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		try {

			StringBuffer buffer = new StringBuffer();
			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);

			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			date = date.substring(0, 10);
			String nextday = date + " 23:59:59";
			ArrayList<Bed> doseremainders = ipdDAO.getAllDosageRemainders(date, nextday, loginInfo);
			// ipdForm.setDoseremainders(doseremainders);

			HashSet<String> colorcount = new HashSet<String>();
			for (Bed bed : doseremainders) {

				String rowid = "d" + "" + bed.getConditionname() + "" + bed.getId();
				buffer.append(bed.getId() + "-" + bed.getColor() + "-" + rowid + "/");
				if (bed.getColor().equals("Red")) {

					String data = bed.getId() + "/" + bed.getColor();
					colorcount.add(data);
				}

			}

			buffer.append(colorcount.size());

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	//   16 April 2018 diagnosis
	public String getdiagnosis() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			String search = request.getParameter("search");
			String selected = request.getParameter("selected");
			ArrayList<Client> ipdCondtitionList = ipdDAO.getAllDiagnosis(search);
			StringBuilder builder = new StringBuilder();
			for (Client client : ipdCondtitionList) {

				boolean flag = false;

				for (String s : selected.split(",")) {

					if (s == null) {
						continue;
					}

					int d = Integer.parseInt(s);
					if (d == 0) {
						continue;
					}
					if (d == client.getId()) {
						flag = true;
						break;
					}
				}

				if (flag) {

					builder.append("<tr>");
					builder.append("<td><input type='checkbox' checked='checked' onclick='setCheckedData(this)' value='"
							+ client.getId() + "'  /></td>");
					builder.append("<td>" + client.getTreatmentType() + "</td>");
					builder.append("</tr>");

				} else {

					builder.append("<tr>");
					builder.append("<td><input type='checkbox' onclick='setCheckedData(this)' value='" + client.getId()
							+ "'  /></td>");
					builder.append("<td>" + client.getTreatmentType() + "</td>");
					builder.append("</tr>");

				}

			}
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(builder.toString());
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	
	public String prodstock() throws Exception {
		String masterchargetype = request.getParameter("masterchargetype");
		String prodid = request.getParameter("prodid");

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);

			boolean checkInventoryProduct = ipdDAO.checkInventoryChargeType(masterchargetype);
			Product product = completeAptmDAO.getInventoryProductDetails(prodid);

			String str = "";
			if (!checkInventoryProduct) {
				int sumqty = ipdDAO.getSumOfProdQty(prodid);
				str = "1/" + product.getStock() + "/" + sumqty;
			} else {
				str = "0/0/0";
			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str + "");

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			connection.close();
		}

		return null;
	}

	
	public String savecharge() throws Exception {

		Double total = 0.00;
		String totalx = "";

		String user = request.getParameter("ipdclientname");
		String apmtType = request.getParameter("chargetype");
		String quantity = request.getParameter("quantity");

		String charge = request.getParameter("charge");

		String practitionerId = request.getParameter("ipdpractitionerid");
		String clientId = request.getParameter("ipdclientid");
		String practitionerName = request.getParameter("ipdpractitionername");
		String date = request.getParameter("date");

		String payBuy = request.getParameter("payby");
		String markAppointment = request.getParameter("markappointment");
		String apppointmentid = DateTimeUtils.numberCheck(request.getParameter("apmtid"));

		String masterchargetype = request.getParameter("masterchargetype");
		String mannualcharge = request.getParameter("mannualcharge");
		String packageid = request.getParameter("packageid");

		String visitingconsulatntdr = request.getParameter("visitingconsulatntdr");
		String isindisharecharge = request.getParameter("isindisharecharge");
		
		String taxtypids=request.getParameter("taxtypes");
		String chargedescription=request.getParameter("chargedescriptnew");
		String docid=request.getParameter("diaryUsersss");
		String sessioncount=request.getParameter("sessioncount");
		Connection connection = null;
		try {
			if (date == null) {
				date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			} else {
				date = DateTimeUtils.getCommencingDate1(date);
			}
			connection = Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ChargesAccountProcessingDAO chargesAccountProcessingDAO=new  JDBCChargeAccountProcesDAO(connection);
			int compulsary_consultant=chargesAccountProcessingDAO.isCompulasryConsultant(masterchargetype);
			if (masterchargetype != null) {
				if (masterchargetype.equals("IPD Visiting Charge") || masterchargetype.equals("Consultation Charge")||compulsary_consultant==1) {
					practitionerId = visitingconsulatntdr;
					//practitionerName = userProfileDAO.getReferalDrName(visitingconsulatntdr);
					//  16 May 2018
					practitionerName = userProfileDAO.getFullName(practitionerId);
				}
			}
			
			
			

			CompleteAppointment completeAppointment = new CompleteAppointment();

			//lokesh
			if(taxtypids!=null){
				for(String id:taxtypids.split(",")){
					if(id.equals("0")){
						continue;
					}
					String per[]= id.split("~");
					if(per[0].equals("1")){
						completeAppointment.setTax1(per[1]);
					}else if(per[0].equals("2")){
						completeAppointment.setTax2(per[1]);
					}else if(per[0].equals("3")){
						completeAppointment.setTax3(per[1]);
					}
				}
			}
			completeAppointment.setChargedescription(chargedescription);
			completeAppointment.setDocid(docid);
			completeAppointment.setSessionno(sessioncount);
			PackageMasterDAO packageMasterDAO = new JDBCPackageMasterDAO(connection);

			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);

			completeAppointment.setUser(user);
			completeAppointment.setIsindisharecharge(isindisharecharge);
			completeAppointment.setVisitingconsulatntdrid(visitingconsulatntdr);
			
			String appointmentTypeName = completeAptmDAO.getAppointmentTypeName(apmtType);
			completeAppointment.setApmtType(apmtType);
			completeAppointment.setCharges(charge);
			//
			completeAppointment.setStartTime("");
			completeAppointment.setDuration("");
			completeAppointment.setPractitionerId(practitionerId);
			completeAppointment.setPractitionerName(practitionerName);
			completeAppointment.setClientId(clientId);
			completeAppointment.setCommencing(date);
			completeAppointment.setPayBuy(payBuy);
			completeAppointment.setMarkAppointment(markAppointment);
			completeAppointment.setAppointmentid(apppointmentid);
			completeAppointment.setQuantity(Integer.parseInt(quantity));
			completeAppointment.setMasterchargetype(masterchargetype);
			completeAppointment.setManualcharge(mannualcharge);
			//
			completeAppointment.setAdditionalcharge_id(apmtType);
			completeAppointment.setManuallyadded("1");
			int ipdid=0;
			String appointmentTYpeText = notAvailableSlotDAO.getAppointmentTypeText(apmtType);
			if(session.getAttribute("sessionadmissionid")!=null){
			 ipdid = (Integer) session.getAttribute("sessionadmissionid");
			}
			if (ipdid != 0) {
				completeAppointment.setIpdid(ipdid);
			}
			String masterchargetype1=completeAptmDAO.getMasterChargeType(clientId);
			int mastercharrge=0;
		if(loginInfo.isLmh()) {  //this is for lmh same select masterchargetype	
			if(!masterchargetype1.equals("")) {
			    mastercharrge=completeAptmDAO.getmasterchargeExistOrNot(clientId,masterchargetype);
			}else {
				mastercharrge=1;
			}
		}else {
			mastercharrge=1;
		}
		
		if(mastercharrge==1) {	
			 if (packageid == null) {
				int result = completeAptmDAO.saveCharge(completeAppointment, apppointmentid, loginInfo.getId());
			} else if (packageid.equals("")) {
				int result = completeAptmDAO.saveCharge(completeAppointment, apppointmentid, loginInfo.getId());
			} else if (packageid.equals("0")) {
				int result = completeAptmDAO.saveCharge(completeAppointment, apppointmentid, loginInfo.getId());
			} else {
				ArrayList<PackageMaster> arrayList = packageMasterDAO.getPackageFromChild(Integer.parseInt(packageid));
				for (PackageMaster packageMaster : arrayList) {
					completeAppointment.setManualcharge(packageMaster.getChargename());
					completeAppointment.setCharges(packageMaster.getCal_amount());
					completeAppointment.setMasterchargetype(mannualcharge);
					completeAppointment.setTpkg(packageMaster.getId());
					int result = completeAptmDAO.saveCharge(completeAppointment, apppointmentid, loginInfo.getId());
				}
			}
		}	 

			ArrayList<CompleteAppointment> clientChargeListDetail = new ArrayList<CompleteAppointment>();

			clientChargeListDetail = completeAptmDAO.getPatientChrageDetails(clientId, date, apppointmentid,
					loginInfo.getId());

			for (CompleteAppointment completeAppointment2 : clientChargeListDetail) {
				total = completeAppointment2.getChargeTotal();
				totalx = completeAppointment2.getChargeTotalx();
			}
			// completeAppointmentForm.setChargeTotal(total);

			String textAjax = new String();

			textAjax = ("<input class = 'form-control' type = 'text' id = 'chargeTotal' name = 'chargeTotal' disabled = 'disabled' value = '"
					+ Constants.getCurrency(loginInfo) + totalx + " '>")+"~~"+mastercharrge+"~~"+loginInfo.isLmh();

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + textAjax.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String cashDesk() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		String id = request.getParameter("selectedUser");
		String date = request.getParameter("date");
		if (date == null) {
			date = "";
		}
		if (date.equals("")) {
			date = DateTimeUtils.getDateinSimpleFormate(new Date());
			String stemp[] = date.split(" ");

			String temp[] = stemp[0].split("-");
			date = temp[2] + "-" + temp[1] + "-" + temp[0];
		}

		String apmtSlotId = request.getParameter("apmtSlotId");
//		System.out.println(id);
		double total = 0;
		String totalx = "";
		Connection connection = null;

		try {
			CompleteAppointment completeAppointment = new CompleteAppointment();
			ArrayList<CompleteAppointment> clientChargeListDetail = new ArrayList<CompleteAppointment>();
			connection = Connection_provider.getconnection();
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			clientChargeListDetail = completeAptmDAO.getPatientChrageDetails(id, date, apmtSlotId, loginInfo.getId());
			// completeAppointmentForm.setClientChargeListDetail(clientChargeListDetail);

			// total = completeAppointment2.getChargeTotal();

			StringBuffer str = new StringBuffer();

			// Practitioner Mail
			/*
			 * str.
			 * append("<table  id = 'cashDesk' cellpadding='0' cellspacing='0' class='my-table' > "
			 * );
			 * 
			 * 
			 * 
			 * str.append("<tr>"); str.append("<th>Id</th> ");
			 * str.append("<th>PayBy</th> ");
			 * str.append("<th>Appointment Type</th> ");
			 * str.append("<th>Quantity</th> "); str.append("<th>Charge</th> ");
			 * str.append("<th>Delete</th> "); str.append("</tr>");
			 */
			int count = 0;
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			Client client = clientDAO.getClientDetails(id);
			AppointmentDAO appointmentDAO=new JDBCAppointmentDAO(connection);

			for (CompleteAppointment completeAppointment1 : clientChargeListDetail) {
                String docname=completeAptmDAO.getDocnamebyid(completeAppointment1.getDocid());
				str.append("<tr>");
				str.append("<td>"+DateTimeUtils.getCommencingDate1(completeAppointment1.getCommencing())+"</td>");
				// str.append("<td>"+completeAppointment1.getId()+"</td>");
				if (client.getWhopay().equals(Constants.PAY_BY_CLIENT)) {
					str.append("<td>Self</td>");
				} else {
					str.append("<td>Third party</td>");
				}
				str.append("<td>" + completeAppointment1.getMasterchargetype() + "</td>");
				str.append("<td id='invchargenameid" + count + "'>" + completeAppointment1.getApmtType() + "</td>");
				if(!loginInfo.isAyushman()) {
				str.append("<td id='docid" + count + "' hidden>" + docname + "</td>");
				}
				if(loginInfo.isPhysio_ipd()){ 
				str.append("<td style='text-align:center'>" + completeAppointment1.getSessionno() + "</td>");
				}else {
				str.append("<td style='text-align:center' class='hidden'>" + completeAppointment1.getSessionno() + "</td>");
				}

				str.append("<td style='text-align:center'>" + completeAppointment1.getQuantity() + "</td>");

				double taxcharge=Math.round((Double.parseDouble(completeAppointment1.getCharges())*Double.parseDouble(completeAppointment1.getTotaltax()))/100.0);
				double chargeamt=Double.parseDouble(completeAppointment1.getCharges())+taxcharge;
				if (completeAppointment1.getMasterchargetype().equals("INVESTIGATION")) {
					if (loginInfo.isEdit_invst_charge()) {
						str.append(
								"<td  style='text-align:right'><input  class='invunitchargecase' type='number' onchange='updatetptypetempcharge("
										+ completeAppointment1.getId() + ",this.id,this.value)' id='invchargeid" + count
										+ "' name='invchargeid" + count + "' value='" + /*completeAppointment1.getCharges()*/chargeamt
										+ "' /></td>");
					} else {
						int noneditamt = appointmentDAO.getNonEditAmtSts(completeAppointment1.getAdditionalcharge_id());
						if(noneditamt==1){
							str.append(
									"<td  style='text-align:right'><input  class='invunitchargecase' type='number' readonly='readonly' onchange='updatetptypetempcharge("
											+ completeAppointment1.getId() + ",this.id,this.value)' id='invchargeid" + count
											+ "' name='invchargeid" + count + "' value='" + /*completeAppointment1.getCharges()*/chargeamt
											+ "' /></td>");
						}else if (!loginInfo.isAdd_charge_amt_edit()) {
							str.append(
									"<td  style='text-align:right'><input  class='invunitchargecase' type='number' readonly='readonly' onchange='updatetptypetempcharge("
											+ completeAppointment1.getId() + ",this.id,this.value)' id='invchargeid" + count
											+ "' name='invchargeid" + count + "' value='" + /*completeAppointment1.getCharges()*/chargeamt
											+ "' /></td>");
						}else{
						str.append(
								"<td  style='text-align:right'><input  class='invunitchargecase' type='number' onchange='updatetptypetempcharge("
										+ completeAppointment1.getId() + ",this.id,this.value,"+completeAppointment1.getClientId()+")' id='invchargeid" + count
										+ "' name='invchargeid" + count + "' value='" + /*completeAppointment1.getCharges()*/chargeamt
										+ "' /></td>");
						}
						
					}

				} else {
					if (client.getWhopay().equals(Constants.PAY_BY_THIRD_PARTY)) {
						int noneditamt = appointmentDAO.getNonEditAmtSts(completeAppointment1.getAdditionalcharge_id());
						if(noneditamt==1){
						str.append(
								"<td  style='text-align:right'><input style='width:112%' class='invunitchargecase' type='number' readonly='readonly' onchange='updatetptypetempcharge("
										+ completeAppointment1.getId() + ",this.id,this.value)' id='invchargeid" + count
										+ "' name='invchargeid" + count + "' value='" + /*completeAppointment1.getCharges()*/chargeamt
										+ "' /></td>");
					}else if (!loginInfo.isAdd_charge_amt_edit()) {
						str.append(
								"<td  style='text-align:right'><input  class='invunitchargecase' type='number' readonly='readonly' onchange='updatetptypetempcharge("
										+ completeAppointment1.getId() + ",this.id,this.value)' id='invchargeid" + count
										+ "' name='invchargeid" + count + "' value='" + /*completeAppointment1.getCharges()*/chargeamt
										+ "' /></td>");
					}else{
						str.append(
								"<td  style='text-align:right'><input style='width:112%' class='invunitchargecase' type='number' min='0' onchange='updatetptypetempcharge("
										+ completeAppointment1.getId() + ",this.id,this.value)' id='invchargeid" + count
										+ "' name='invchargeid" + count + "' value='" + /*completeAppointment1.getCharges()*/chargeamt
										+ "' /></td>");
					}
						
					}	else{
						int noneditamt = appointmentDAO.getNonEditAmtSts(completeAppointment1.getAdditionalcharge_id());
						if(noneditamt==1){
							str.append(
									"<td  style='text-align:right'><input style='width:112%' class='invunitchargecase' type='number' readonly='readonly' onchange='updatetptypetempcharge("
											+ completeAppointment1.getId() + ",this.id,this.value)' id='invchargeid" + count
											+ "' name='invchargeid" + count + "' value='" + /*completeAppointment1.getCharges()*/chargeamt
											+ "' /></td>");
					}else if (!loginInfo.isAdd_charge_amt_edit()) {
						str.append(
								"<td  style='text-align:right'><input  class='invunitchargecase' type='number' readonly='readonly' onchange='updatetptypetempcharge("
										+ completeAppointment1.getId() + ",this.id,this.value)' id='invchargeid" + count
										+ "' name='invchargeid" + count + "' value='" + /*completeAppointment1.getCharges()*/chargeamt
										+ "' /></td>");
					}else{
						str.append(
								"<td  style='text-align:right'><input style='width:112%' class='invunitchargecase' type='number' min='0' onchange='updatetptypetempcharge("
										+ completeAppointment1.getId() + ",this.id,this.value,"+completeAppointment1.getClientId()+")' id='invchargeid" + count
										+ "' name='invchargeid" + count + "' value='" + /*completeAppointment1.getCharges()*/chargeamt
										+ "' /></td>");
					}
				}

				double charge = Double.parseDouble(completeAppointment1.getCharges())
						* completeAppointment1.getQuantity();
				
				str.append("<td style='text-align:right'>" + Constants.getCurrency(loginInfo)
						+ "<span id='invamtchargeid'>" + DateTimeUtils.changeFormat(charge) + "</span></td>");
				/*if (count == 0) {
					str.append("<td><img src='common/images/delete.gif'></img></td>");

				} else {*/
					str.append("<td onclick = 'confirmedDelete1(" + completeAppointment1.getId()
							+ ")'><img src='common/images/delete.gif'></img></td>");

				/*}*/

				str.append("</tr>");
				count = count + 1;
			}
			}
			// str.append("</table>");
			for (CompleteAppointment completeAppointment2 : clientChargeListDetail) {
				total = completeAppointment2.getChargeTotal();
				totalx = ""+(Double.parseDouble(completeAppointment2.getChargeTotalx())+completeAppointment2.getTotaltaxamt());
				
			}

			str.append("<tr style='background-color: #efefef;'>");
			str.append("<th colspan='5' style='font-size: 13px;font-weight: bold;'>Total</th> ");

			str.append("<th style='font-size: 13px;font-weight: bold;' colspan='5'>" + Constants.getCurrency(loginInfo)
					+ totalx + "</th> ");
			str.append("</tr>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str.toString() + "");

			String textAjax = new String();

			textAjax = ("<input class='form-control' type = 'hidden' id = 'hiddenTotal' name = 'hiddenTotal' value = '"
					+ Constants.getCurrency(loginInfo) + totalx + " '>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");

			response.getWriter().write("" + textAjax.toString() + "");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	// This method now called from IpdAction 17 April 2018  
	public String bed() throws Exception {
		if (!verifyLogin(request)) {

			return "login";
		}

		String wardid = request.getParameter("wid");
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			ArrayList<Bed> bedList = ipdDAO.getWardWiseBedList(wardid);

			StringBuffer str = new StringBuffer();
			str.append("<select id='bedid' name='bedid' class='form-control'>");
			str.append("<option value='0'>Select Bed</option>");

			for (Bed bed : bedList) {
				str.append("<option value='" + bed.getId() + "'>" + bed.getBedname() + "</option>");
			}

			str.append("</select>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String setalldiagnosis() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			DiagnosisDAO diagnosisDAO = new JDBCDiagnosisDAO(connection);
			StringBuffer buffer = new StringBuffer();
			String selected = request.getParameter("selected");

			int i = 0;
			for (String s : selected.split(",")) {

				int d = Integer.parseInt(s);
				if (d == 0) {
					continue;
				} else {

					Diagnosis diagnosis = diagnosisDAO.getDiagnosisName(s);
					buffer.append("<tr>");
					buffer.append("<td><input type='checkbox' checked='checked' onclick=reoveThisRow(this,'"
							+ diagnosis.getId() + "') value='" + diagnosis.getId()
							+ "' class='classD' /> <input type='hidden' value=" + diagnosis.getId()
							+ " name='conditions[" + i + "].conditionid'  /> </td>");
					buffer.append("<td>" + diagnosis.getName() + "</td>");
					buffer.append("<td class='hidden'><a onclick=reoveThisRow('" + diagnosis.getId()
							+ "')><i class='fa fa-trash-o'></i></a></td>");
					buffer.append("</tr>");
					i++;
				}

			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String saveinvestoutsource() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			StringBuffer buffer = new StringBuffer();
			String invid = request.getParameter("invid");
			String val = request.getParameter("val");
			String userid= loginInfo.getUserId();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());
			int res = investigationDAO.updateInvestigationOutsource(invid, val,userid,date);
			String invreq=investigationDAO.getInveReqId(Integer.parseInt(invid));
			Investigation investigation=investigationDAO.getInvestigationTyeID(invid);
			double charge=0;
			charge=investigationDAO.getoutsourceAmount(invid,val,investigation.getInvsttypeid());
			if(charge>0){
			int chargeup=investigationDAO.updateOutsourceChargeAmount(invreq,charge);
			}
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String savechildgrowthdata() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			String heightdata = request.getParameter("heightdata");
			String heightmonth = request.getParameter("heightmonth");
			String weightdata = request.getParameter("weightdata");
			String weightmonth = request.getParameter("weightmonth");
			String bmidata = request.getParameter("bmidata");
			String bmimonth = request.getParameter("bmimonth");
			String headcircumferncedata = request.getParameter("headcircumferncedata");
			String headcircumferncemonth = request.getParameter("headcircumferncemonth");
			String clientId = request.getParameter("clientId");
			String val = request.getParameter("val");
			String month = request.getParameter("month");
			String lengthdata = ""; //request.getParameter("lengthdata");
			String lengthmonth = ""; //request.getParameter("lengthmonth");
			Client client = new Client();
			client.setHeightdata(heightdata);
			client.setWeightdata(weightdata);
			client.setBmidata(bmidata);
			client.setHeadcircumferncedata(headcircumferncedata);
			client.setLength(lengthdata);
			client.setClientid(clientId);
			client.setMonth(month);
			client.setUserid(loginInfo.getUserId());
			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			client.setDate(datetime);
			int id = clientDAO.check_child_growth_data(clientId, month);
			int res = 0;
			if (id > 0) {
				client.setId(id);
				// update
				res = clientDAO.updateChildGrowthData(client, val);
			} else {
				// insert
				res = clientDAO.saveChildGrowthData(client, val);
			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	// from ipd action 21 april 2018
	public String otinfo() throws Exception {
		String clientid = request.getParameter("clientid");
		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);

			int lastipdid = ipdDAO.getLastIpdId(clientid);

			BedDao bedDao = new JDBCBedDao(connection);

			Bed bed = bedDao.getEditIpdData(Integer.toString(lastipdid));
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			Client client = clientDAO.getClientDetails(clientid);

			String age = DateTimeUtils.getAge(client.getDob());
			String agegender = age + "Years" + " / " + client.getGender();

			String wardname = ipdDAO.getIpdWardName(bed.getWardid());
			String bedname = ipdDAO.getIpdBedName(bed.getBedid());

			String result = agegender + "~" + lastipdid + "~" + wardname + "~" + bedname;

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + result + "");

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			connection.close();
		}

		return null;
	}

	public String setappointmentprocedure() throws Exception {
		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			String editAppointId = request.getParameter("editAppointId");
			NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getProcedureDepartment(editAppointId);
			ArrayList<Master> procedureList = notAvailableSlotDAO.getProcedureList(notAvailableSlot.getDept());

			StringBuffer str = new StringBuffer();

			str.append(
					"<select onchange='filterOtCharges(this.value)' name='otprocedureplaned' id='otprocedureplaned' class='form-control showToolTip chosen' >");
			str.append("<option value='0'>Select Procedure</option>");
			for (Master master : procedureList) {
				//   30-March-2018
				if (notAvailableSlot.getId() == master.getId()) {
					str.append("<option selected value='" + master.getId() + "'>" + master.getName() + "</option>");
				} else {
					str.append("<option value='" + master.getId() + "'>" + master.getName() + "</option>");
				}
			}
			str.append("</select>");

			str.append("~");

			ArrayList<Master> list = ipdDAO.getOtFilteredChargeList(notAvailableSlot.getProcedure(),
					notAvailableSlot.getClientId());

			str.append(
					"<select onchange='setAppointmentTypeTimeAjax(this.value)' name='duration' id='apmtType' class='form-control showToolTip chosen' >");
			str.append("<option value='0'>Select Charge</option>");
			for (Master master : list) {
				/*
				 * str.append("<option value='"+master.getId()+"'>"+master.
				 * getName()+"</option>");
				 */
				if (notAvailableSlot.getAppointmentTypeid() == master.getId()) {
					str.append(
							"<option selected value='" + master.getId() + "'>" + master.getChargetype() + "</option>");
				} else {
					str.append("<option value='" + master.getId() + "'>" + master.getChargetype() + "</option>");
				}
			}
			str.append("</select>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String chageotemplate() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			JDBCMasterDAO masterDAO = new JDBCMasterDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			String surgeonid = request.getParameter("srgeonid");
			String tempsrno = request.getParameter("tempsrno");
			StringBuffer stringBuffer = new StringBuffer();

			int specialityid = notAvailableSlotDAO.getSpeciSurgonFromRefernce(Integer.parseInt(surgeonid));
			Master master1 = masterDAO.getDisciplineData((String.valueOf(specialityid)));
			String surgical_template = masterDAO.getIpdTemplateId("OT Template");
			ArrayList<Master> otherTemplateList = masterDAO.getIpdTemplateBySpeciality(surgical_template,
					master1.getDiscipline());

			stringBuffer
					.append("<label for='exampleInputEmail1'>Operation Notes <a href='#' onclick='setOTemplateBySpeciality("
							+ tempsrno + "," + surgeonid + ")'><i class='fa fa-refresh'></i></a></label>");
			stringBuffer.append("<select name='template" + tempsrno + "' id='template" + tempsrno
					+ "' onchange='getNewOTtemplate(this.value," + tempsrno
					+ ")' class='form-control showToolTip chosen-select'>");
			stringBuffer.append("<option value='0'>Select Template</option>");
			for (Master master : otherTemplateList) {
				stringBuffer.append("<option value='" + master.getId() + "'>" + master.getName() + "</option>");
			}
			stringBuffer.append("</select>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(stringBuffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	//   26 April 2018 called from addcharge its from ipddashobardaction
	public String getajaxnursingdata() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			// cal.add(Calendar.DATE, -7);
			String currDate = dateFormat.format(cal.getTime());

			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			String ipdid = request.getParameter("ipdid");

			// for priscription

			ArrayList<Priscription> parentPriscList = ipdDAO.getParentPriscList(ipdid);

			for (Priscription priscription : parentPriscList) {
				String temp[] = priscription.getDate().split(" ");
				String mdicinestartdate = temp[0];

				long mdicinedays = DateTimeUtils.getDiffofTwoDates(mdicinestartdate, currDate);
				mdicinedays++;

				ArrayList<Priscription> clientPriscList = ipdDAO.getClientPriscList(priscription.getParentid());
				for (Priscription pr : clientPriscList) {
					boolean checkpriscexist = ipdDAO.checkPrescExist(mdicinedays, pr.getId());
					String dosecolumn = "";
					String doseqmark = "";

					String dosage = pr.getDosage();
					if (!checkpriscexist) {
						if (dosage != null) {
							String dosetemp[] = dosage.split("-");
							if (dosetemp.length < 3) {
								dosage = ipdDAO.getAlterNateDose(dosage);
								dosetemp = dosage.split("-");
							}
							int c = 0;
							for (int i = 1; i <= dosetemp.length; i++) {

								doseqmark = doseqmark + 0 + ",";
								dosecolumn = dosecolumn + "dos" + i + ",";
								c++;
							}
							dosecolumn = dosecolumn.substring(0, dosecolumn.length() - 1);
							doseqmark = doseqmark.substring(0, doseqmark.length() - 1);

							if (mdicinedays <= pr.getDays()) {
								int result = ipdDAO.savePrescReminder(dosecolumn, doseqmark, mdicinedays, pr.getId(),
										ipdid);
							}

						}

					}
				}
			}

			// for Nursing

			ArrayList<Master> nursingParentList = ipdDAO.getParentNursingList(ipdid);

			for (Master master : nursingParentList) {
				String temp[] = master.getDate().split(" ");
				String mdicinestartdate = temp[0];

				long mdicinedays = DateTimeUtils.getDiffofTwoDates(mdicinestartdate, currDate);
				mdicinedays++;

				ArrayList<Master> clientNursingList = ipdDAO.getClientNursingList(master.getParentid());
				for (Master pr : clientNursingList) {
					boolean checkpriscexist = ipdDAO.checkNursingExist(mdicinedays, pr.getId());
					String dosecolumn = "";
					String doseqmark = "";

					if (!checkpriscexist) {
						if (pr.getFrequency() != null) {
							String dosetemp[] = pr.getFrequency().split("-");
							int c = 0;
							for (int i = 1; i <= dosetemp.length; i++) {

								doseqmark = doseqmark + 0 + ",";
								dosecolumn = dosecolumn + "dos" + i + ",";
								c++;
							}
							dosecolumn = dosecolumn.substring(0, dosecolumn.length() - 1);
							doseqmark = doseqmark.substring(0, doseqmark.length() - 1);

							if (mdicinedays <= pr.getDays()) {
								int result = ipdDAO.saveNursingReminder(dosecolumn, doseqmark, mdicinedays, pr.getId(),
										ipdid);
							}

						}
					}
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	//   26 April 2018 called from addcharge its from ipddashobardaction
	public String getallpriscajax() throws Exception {

		Connection connection = null;

		try {
			String ipdid = request.getParameter("ipdid");
			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			ArrayList<Priscription> ipdPriscList = ipdDAO.getAJaxPriscription(ipdid);

			StringBuffer buffer = new StringBuffer();
			for (Priscription priscription : ipdPriscList) {

				if (priscription.getMdicinenametxt() != null) {

					buffer.append("<tr class='prstotip'>");
					buffer.append("<td>" + priscription.getMdicinenametxt() + "("+priscription.getCurrdays()+"/"+priscription.getTotaldays()+" days)</td>");
					buffer.append("<td>" + priscription.getDosage() + "</td>");
					buffer.append("<td>");

					if (priscription.getDosesize() == 3) {
						String priscdose = priscription.getDosage();
						String doselabletime = priscription.getDosegiventime();
						String[] dosetime= new String[4];
						if(doselabletime!=null){
							if(!doselabletime.equals("")){
								dosetime = doselabletime.split("-");
								if(dosetime[0]!=null){
									if(dosetime[0].equals("")){
										dosetime[0]="0";
									}
								}
								if(dosetime[1]!=null){
									if(dosetime[1].equals("")){
										dosetime[1]="0";
									}
								}
								
								if(dosetime[2]!=null){
									if(dosetime[2].equals("")){
										dosetime[2]="0";
									}
								}
							}else{
								dosetime[0]="0";
								dosetime[1]="0";
								dosetime[2]="0";
							}
						}else{
							dosetime[0]="0";
							dosetime[1]="0";
							dosetime[2]="0";
						}
						
						if (!priscription.getDosevalue1().equals("0")) {
							if (priscription.isDos1()) {
								buffer.append(
										"<input type='checkbox' name='dos1' id='dos1' checked='checked' onclick=toggleConfirmation("
												+ priscription.getId() + ",'dos1'," + priscription.isDos1() + ")>");
								if(doselabletime!=null){
									if(!doselabletime.equals("")){
										if(!dosetime[0].equals("0")){
											buffer.append(""+dosetime[0]+"("+priscription.getUserid()+")<br>");
										}else{
											buffer.append("("+priscription.getUserid()+")<br>");
										}
									}else{
										buffer.append("("+priscription.getUserid()+")<br>");
									}
								}else{
									buffer.append("("+priscription.getUserid()+")<br>");
								}
								
							} else {
								buffer.append("<input type='checkbox' name='dos1' id='dos1' onclick=toggleConfirmation("
										+ priscription.getId() + ",'dos1'," + priscription.isDos1() + ")>");
								if(doselabletime!=null){
									if(!doselabletime.equals("")){
										if(!dosetime[0].equals("0")){
											buffer.append(""+dosetime[0]+"<br>");
										}else{
											buffer.append("<br>");
										}
									}else{
										buffer.append("<br>");
									}
								}else{
									buffer.append("<br>");
								}
							}
						} else {
							buffer.append("<input type='checkbox' name='dos1' id='dos1' onclick=toggleConfirmation("
									+ priscription.getId() + ",'dos1'," + priscription.isDos1() + ") disabled='true'>");
							if(doselabletime!=null){
								if(!doselabletime.equals("")){
									if(!dosetime[0].equals("0")){
										buffer.append(""+dosetime[0]+"<br>");
									}else{
										buffer.append("<br>");
									}
								}else{
									buffer.append("<br>");
								}
							}else{
								buffer.append("<br>");
							}
						}
						if (!priscription.getDosevalue2().equals("0")) {
							if (priscription.isDos2()) {
								buffer.append(
										"<input type='checkbox' name='dos2' id='dos2' checked='checked' onclick=toggleConfirmation("
												+ priscription.getId() + ",'dos2'," + priscription.isDos2() + ")>");
								if(doselabletime!=null){
									if(!doselabletime.equals("")){
										if(!dosetime[1].equals("0")){
											buffer.append(""+dosetime[1]+"("+priscription.getUserid()+")<br>");
										}else{
											buffer.append("("+priscription.getUserid()+")<br>");
										}
									}else{
										buffer.append("("+priscription.getUserid()+")<br>");
									}
								}else{
									buffer.append("("+priscription.getUserid()+")<br>");
								}
							} else {
								buffer.append("<input type='checkbox' name='dos2' id='dos2' onclick=toggleConfirmation("
										+ priscription.getId() + ",'dos2'," + priscription.isDos2() + ")>");
								if(doselabletime!=null){
									if(!doselabletime.equals("")){
										if(!dosetime[1].equals("0")){
											buffer.append(""+dosetime[1]+"<br>");
										}else{
											buffer.append("<br>");
										}
									}else{
										buffer.append("<br>");
									}
								}else{
									buffer.append("<br>");
								}
							}
						} else {
							buffer.append("<input type='checkbox' name='dos2' id='dos2' onclick=toggleConfirmation("
									+ priscription.getId() + ",'dos2'," + priscription.isDos2() + ") disabled='true'>");
								if(doselabletime!=null){
									if(!doselabletime.equals("")){
										if(!dosetime[1].equals("0")){
											buffer.append(""+dosetime[1]+"<br>");
										}else{
											buffer.append("<br>");
										}
									}else{
										buffer.append("<br>");
									}
								}else{
									buffer.append("<br>");
								}
						}
						if (!priscription.getDosevalue3().equals("0")) {
							if (priscription.isDos3()) {
								buffer.append(
										"<input type='checkbox' name='dos3' id='dos3' checked='checked' onclick=toggleConfirmation("
												+ priscription.getId() + ",'dos3'," + priscription.isDos3() + ")>");
								if(doselabletime!=null){
									if(!doselabletime.equals("")){
										if(!dosetime[2].equals("0")){
											buffer.append(""+dosetime[2]+"("+priscription.getUserid()+")<br>");
										}else{
											buffer.append("("+priscription.getUserid()+")<br>");
										}
									}else{
										buffer.append("("+priscription.getUserid()+")<br>");
									}
								}else{
									buffer.append("("+priscription.getUserid()+")<br>");
								}
							} else {
								buffer.append("<input type='checkbox' name='dos3' id='dos3' onclick=toggleConfirmation("
										+ priscription.getId() + ",'dos3'," + priscription.isDos3() + ")>");
								if(doselabletime!=null){
									if(!doselabletime.equals("")){
										if(!dosetime[2].equals("0")){
											buffer.append(""+dosetime[2]+"<br>");
										}else{
											buffer.append("<br>");
										}
									}else{
										buffer.append("<br>");
									}
								}else{
									buffer.append("<br>");
								}
							}
						} else {
							buffer.append("<input type='checkbox' name='dos3' id='dos3' onclick=toggleConfirmation("
									+ priscription.getId() + ",'dos3'," + priscription.isDos3() + ") disabled='true'>");
								if(doselabletime!=null){
									if(!doselabletime.equals("")){
										if(!dosetime[2].equals("0")){
											buffer.append(""+dosetime[2]+"<br>");
										}else{
											buffer.append("<br>");
										}
									}else{
										buffer.append("<br>");
									}
								}else{
									buffer.append("<br>");
								}
						}

					}
					if (priscription.getDosesize() == 4) {

						if (!priscription.getDosevalue1().equals("0")) {
							if (priscription.isDos1()) {
								buffer.append(
										"<input type='checkbox' name='dos1' id='dos1' checked='checked' onclick=toggleConfirmation("
												+ priscription.getId() + ",'dos1'," + priscription.isDos1() + ")>");
								buffer.append("("+priscription.getUserid()+")<br>");
							} else {
								buffer.append(
										"<input type='checkbox' name='dos1' id='dos1' onclick='toggleConfirmation("
												+ priscription.getId() + ",'dos1'," + priscription.isDos1() + ")'>");
								buffer.append("<br>");
							}
						} else {
							buffer.append("<input type='checkbox' name='dos1' id='dos1' onclick=toggleConfirmation("
									+ priscription.getId() + ",'dos1'," + priscription.isDos1() + ") disabled='true'>");
							buffer.append("<br>");
						}
						if (!priscription.getDosevalue2().equals("0")) {
							if (priscription.isDos2()) {
								buffer.append(
										"<input type='checkbox' name='dos2' id='dos2' checked='checked' onclick=toggleConfirmation("
												+ priscription.getId() + ",'dos2'," + priscription.isDos2() + ")>");
								buffer.append("("+priscription.getUserid()+")<br>");
							} else {
								buffer.append("<input type='checkbox' name='dos2' id='dos2' onclick=toggleConfirmation("
										+ priscription.getId() + ",'dos2'," + priscription.isDos2() + ")>");
								buffer.append("<br>");
							}
						} else {
							buffer.append("<input type='checkbox' name='dos2' id='dos2' onclick=toggleConfirmation("
									+ priscription.getId() + ",'dos2'," + priscription.isDos2() + ") disabled='true'>");
							buffer.append("<br>");
						}
						if (!priscription.getDosevalue3().equals("0")) {
							if (priscription.isDos3()) {
								buffer.append(
										"<input type='checkbox' name='dos3' id='dos3' checked='checked' onclick=toggleConfirmation("
												+ priscription.getId() + ",'dos3'," + priscription.isDos3() + ")>");
								buffer.append("("+priscription.getUserid()+")<br>");
							} else {
								buffer.append("<input type='checkbox' name='dos3' id='dos3' onclick=toggleConfirmation("
										+ priscription.getId() + ",'dos3'," + priscription.isDos3() + ")>");
								buffer.append("<br>");
							}
						} else {
							buffer.append("<input type='checkbox' name='dos3' id='dos3' onclick=toggleConfirmation("
									+ priscription.getId() + ",'dos3'," + priscription.isDos3() + ") disabled='true'>");
							buffer.append("<br>");
						}
						if (!priscription.getDosevalue4().equals("0")) {
							if (priscription.isDos4()) {
								buffer.append(
										"<input type='checkbox' name='dos4' id='dos4' checked='checked' onclick=toggleConfirmation("
												+ priscription.getId() + ",'dos4'," + priscription.isDos4() + ")>");
								buffer.append("("+priscription.getUserid()+")<br>");
							} else {
								buffer.append("<input type='checkbox' name='dos4' id='dos4' onclick=toggleConfirmation("
										+ priscription.getId() + ",'dos4'," + priscription.isDos4() + ")>");
								buffer.append("<br>");
							}
						} else {
							buffer.append("<input type='checkbox' name='dos4' id='dos4' onclick=toggleConfirmation("
									+ priscription.getId() + ",'dos4'," + priscription.isDos4() + ") disabled='true'>");
							buffer.append("<br>");
						}
					}
					buffer.append("</td>");
					buffer.append("<td>" + priscription.getDosenotes() + "</td>");
					buffer.append("<td>" + priscription.getPrisctimename() + "</td>");
					buffer.append("<td>" + priscription.getPriscindivisualremark() + "</td>");
					buffer.append("<td><a href='#' onclick=deleteChildPriscIPD('" + priscription.getPrischildid()
							+ "','" + ipdid + "') ><i class='fa fa-times fa-2x text-danger' ></i></a></td>");
					buffer.append("</tr>");
				}
			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	//   26 April 2018 called from addcharge its from ipddashobardaction
	public String getallnursingajax() throws Exception {

		Connection connection = null;

		try {
			String ipdid = request.getParameter("ipdid");
			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			ArrayList<Master> ipdNursingList = ipdDAO.getAJaxNursing(ipdid);

			StringBuffer buffer = new StringBuffer();
			for (Master master : ipdNursingList) {

				if (master.getTaskname() != null) {

					buffer.append("<tr class='prstotip'>");
					buffer.append("<td>" + master.getTaskname() + "</td>");
					buffer.append("<td>" + master.getFrequency() + "</td>");
					buffer.append("<td>");
					if (master.getDosesize() == 3) {

						if (!master.getDosevalue1().equals("0")) {
							if (master.isDos1()) {
								buffer.append(
										"<input type='checkbox' name='dos1' id='dos1' checked='checked' onclick=togglenursingConfirm("
												+ master.getId() + ",'dos1'," + master.isDos1() + ")>");
							} else {
								buffer.append(
										"<input type='checkbox' name='dos1' id='dos1' onclick=togglenursingConfirm("
												+ master.getId() + ",'dos1'," + master.isDos1() + ")>");
							}
						} else {
							buffer.append("<input type='checkbox' name='dos1' id='dos1' onclick=togglenursingConfirm("
									+ master.getId() + ",'dos1'," + master.isDos1() + ") disabled='true'>");
						}
						if (!master.getDosevalue2().equals("0")) {
							if (master.isDos2()) {
								buffer.append(
										"<input type='checkbox' name='dos2' id='dos2' checked='checked' onclick=togglenursingConfirm("
												+ master.getId() + ",'dos2'," + master.isDos2() + ")>");
							} else {
								buffer.append(
										"<input type='checkbox' name='dos2' id='dos2' onclick=togglenursingConfirm("
												+ master.getId() + ",'dos2'," + master.isDos2() + ")>");
							}
						} else {
							buffer.append("<input type='checkbox' name='dos2' id='dos2' onclick=togglenursingConfirm("
									+ master.getId() + ",'dos2'," + master.isDos2() + ") disabled='true'>");
						}
						if (!master.getDosevalue3().equals("0")) {
							if (master.isDos3()) {
								buffer.append(
										"<input type='checkbox' name='dos3' id='dos3' checked='checked' onclick=togglenursingConfirm("
												+ master.getId() + ",'dos3'," + master.isDos3() + ")>");
							} else {
								buffer.append(
										"<input type='checkbox' name='dos3' id='dos3' onclick=togglenursingConfirm("
												+ master.getId() + ",'dos3'," + master.isDos3() + ")>");
							}
						} else {
							buffer.append("<input type='checkbox' name='dos3' id='dos3' onclick=togglenursingConfirm("
									+ master.getId() + ",'dos3'," + master.isDos3() + ") disabled='true'>");
						}

					}
					if (master.getDosesize() == 4) {

						if (!master.getDosevalue1().equals("0")) {
							if (master.isDos1()) {
								buffer.append(
										"<input type='checkbox' name='dos1' id='dos1' checked='checked' onclick=togglenursingConfirm("
												+ master.getId() + ",'dos1'," + master.isDos1() + ")>");
							} else {
								buffer.append(
										"<input type='checkbox' name='dos1' id='dos1' onclick='togglenursingConfirm("
												+ master.getId() + ",'dos1'," + master.isDos1() + ")'>");
							}
						} else {
							buffer.append("<input type='checkbox' name='dos1' id='dos1' onclick=togglenursingConfirm("
									+ master.getId() + ",'dos1'," + master.isDos1() + ") disabled='true'>");
						}
						if (!master.getDosevalue2().equals("0")) {
							if (master.isDos2()) {
								buffer.append(
										"<input type='checkbox' name='dos2' id='dos2' checked='checked' onclick=togglenursingConfirm("
												+ master.getId() + ",'dos2'," + master.isDos2() + ")>");
							} else {
								buffer.append(
										"<input type='checkbox' name='dos2' id='dos2' onclick=togglenursingConfirm("
												+ master.getId() + ",'dos2'," + master.isDos2() + ")>");
							}
						} else {
							buffer.append("<input type='checkbox' name='dos2' id='dos2' onclick=togglenursingConfirm("
									+ master.getId() + ",'dos2'," + master.isDos2() + ") disabled='true'>");
						}
						if (!master.getDosevalue3().equals("0")) {
							if (master.isDos3()) {
								buffer.append(
										"<input type='checkbox' name='dos3' id='dos3' checked='checked' onclick=togglenursingConfirm("
												+ master.getId() + ",'dos3'," + master.isDos3() + ")>");
							} else {
								buffer.append(
										"<input type='checkbox' name='dos3' id='dos3' onclick=togglenursingConfirm("
												+ master.getId() + ",'dos3'," + master.isDos3() + ")>");
							}
						} else {
							buffer.append("<input type='checkbox' name='dos3' id='dos3' onclick=togglenursingConfirm("
									+ master.getId() + ",'dos3'," + master.isDos3() + ") disabled='true'>");
						}
						if (!master.getDosevalue4().equals("0")) {
							if (master.isDos4()) {
								buffer.append(
										"<input type='checkbox' name='dos4' id='dos4' checked='checked' onclick=togglenursingConfirm("
												+ master.getId() + ",'dos4'," + master.isDos4() + ")>");
							} else {
								buffer.append(
										"<input type='checkbox' name='dos4' id='dos4' onclick=togglenursingConfirm("
												+ master.getId() + ",'dos4'," + master.isDos4() + ")>");
							}
						} else {
							buffer.append("<input type='checkbox' name='dos4' id='dos4' onclick=togglenursingConfirm("
									+ master.getId() + ",'dos4'," + master.isDos4() + ") disabled='true'>");
						}
					}
					buffer.append("</td>");
					// buffer.append("<td>"+master.getNotes()+"</td>");

					buffer.append("</tr>");
				}
			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	//   26 April 2018 called from addcharge its from ipddashobardaction
	public String getajaxperpatientinvestigation() throws Exception {
		String ipdid = request.getParameter("ipdid");

		//   03 oct 2017 investigation details in ipd dashborad
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);

			ArrayList<Investigation> invstigationList = investigationDAO.getIPDInvestList(ipdid);

			StringBuffer str = new StringBuffer();
			int i = 0;

			for (Investigation investigation : invstigationList) {

				str.append("<tr>");
				str.append("<td>" + (++i) + "</td>");
				str.append("<td>" + investigation.getInvsttype() + "</td>");
				str.append("<td>" + investigation.getDate() + "</td>");
				if (investigation.getInvstreporttype().equals("Numerical")) {
					str.append("<td><a href='#' onclick='printinvstigationrecord(" + investigation.getId()
							+ ",0)' title='Print Investigation' class='editpricon'><i class='fa fa-print'></i></a></td>");

				} else {
					str.append("<td><a href='#' onclick='printinvstigationrecord(" + investigation.getId()
							+ ",1)' title='Print Investigation' class='editpricon'><i class='fa fa-print'></i></a></td>");
				}

				if (investigation.getPacs() != 0) {
					str.append("<td><a href='#' onclick='viewpacsreport(" + investigation.getId()
							+ ")' title='Pacs Report' class='editpricon'><i class='fa fa-object-ungroup aria-hidden='true''></i></a></td>");
				} else {
					str.append("<td></td>");
				}

				str.append("</tr>");

			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str.toString() + "");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	//   26 April 2018 called from addcharge its from ipddashobardaction
	public String getAjaxPerpatientdietary() throws Exception {

		Connection connection = null;

		try {
			String ipdid = request.getParameter("ipdid");
			connection = Connection_provider.getconnection();
			StringBuffer buffer = new StringBuffer();

			DietaryDetailsDAO detailsDAO = new JDBCDietaryDetailsDAO(connection);
			// ArrayList<DietaryDetails> dietarylist =
			// detailsDAO.getAllIpdDietplan();
			DietaryDetails dietaryDetails = detailsDAO.getIpdPerPatientDiet(ipdid);
			//   10 nov 2017 dietary plan
			
			String mutliParents=detailsDAO.mutiParentIds(ipdid);
			if(!DateTimeUtils.isNull(mutliParents).equals("")){
				for(String parentId:mutliParents.split(",")){
					dietaryDetails.setParentid(parentId);
					buffer.append("<tr>");
					buffer.append("<td><span id='c" + dietaryDetails.getBedid() + "'><i class='fa fa-bell'></i></span> / "
							+ dietaryDetails.getWardname() + " <br> " + dietaryDetails.getClientname() + " <br>"
							+ dietaryDetails.getAge() + "</td>");
					for (int i = 1; i <= 9; i++) {
						ArrayList<DietaryDetails> arrayList = detailsDAO.getSingleDietplanList(dietaryDetails.getParentid(),
								"" + i);
						buffer.append("<td>");
						int x = 0;
						for (DietaryDetails dietaryDetails2 : arrayList) {
							if (x == 0) {
								if (dietaryDetails2.getExecuted().equals("0")) {
									buffer.append("<input type='checkbox' onclick=updateDietaryGivenStatus('"
											+ dietaryDetails.getParentid() + "','" + i + "',this.checked)>"
											+ dietaryDetails2.getSubcategory() + "<br>");
								} else {
									buffer.append("<input type='checkbox' checked='checked' onclick=updateDietaryGivenStatus('"
											+ dietaryDetails.getParentid() + "','" + i + "',this.checked)>"
											+ dietaryDetails2.getSubcategory() + "<br>");
								}

							} else {
								buffer.append("" + dietaryDetails2.getSubcategory() + "<br>");
							}
							x++;

						}
						buffer.append("</td>");
						/*
						 * if(i==0){ DietaryDetails details =
						 * detailsDAO.getSingleDietplan(dietaryDetails.getParentid(),
						 * "Breakfast"); if(details.getSubcategory()!=null){
						 * buffer.append("<td><center>"+details.getSubcategory()+
						 * "</center></td>"); }else{ buffer.append("<td></td>"); } }else
						 * if(i==1){ DietaryDetails details =
						 * detailsDAO.getSingleDietplan(dietaryDetails.getParentid()
						 * ,"Midmorning Snack"); if(details.getSubcategory()!=null){
						 * buffer.append("<td><center>"+details.getSubcategory()+
						 * "</center></td>"); }else{ buffer.append("<td></td>"); } }else
						 * if(i==2){ DietaryDetails details =
						 * detailsDAO.getSingleDietplan(dietaryDetails.getParentid(),
						 * "Lunch"); if(details.getSubcategory()!=null){
						 * buffer.append("<td><center>"+details.getSubcategory()+
						 * "</center></td>"); }else{ buffer.append("<td></td>"); } }else
						 * if(i==3){ DietaryDetails details =
						 * detailsDAO.getSingleDietplan(dietaryDetails.getParentid()
						 * ,"Midafternoon Snack"); if(details.getSubcategory()!=null){
						 * buffer.append("<td><center>"+details.getSubcategory()+
						 * "</center></td>"); }else{ buffer.append("<td></td>"); } }else
						 * if(i==4){ DietaryDetails details =
						 * detailsDAO.getSingleDietplan(dietaryDetails.getParentid()
						 * ,"Midevening Snack"); if(details.getSubcategory()!=null){
						 * buffer.append("<td><center>"+details.getSubcategory()+
						 * "</center></td>"); }else{ buffer.append("<td></td>"); } }else
						 * if(i==5){ DietaryDetails details =
						 * detailsDAO.getSingleDietplan(dietaryDetails.getParentid(),
						 * "Dinner"); if(details.getSubcategory()!=null){
						 * buffer.append("<td><center>"+details.getSubcategory()+
						 * "</center></td>"); }else{ buffer.append("<td></td>"); } }
						 */

					}
					buffer.append("</tr>");

				}
			}
			
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			connection.close();
		}
		return null;
	}

	public String deletechildprisc() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			String prischildid = request.getParameter("prischildid");
			StringBuffer buffer = new StringBuffer();
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String userid = loginInfo.getUserId();
			int result = ipdDAO.updatePriscChildFromIpd(prischildid, date, userid);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String saveimmurtizationdata() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			String clientId = request.getParameter("clientId");
			String colname = request.getParameter("colname");
			String val = request.getParameter("val");

			/*
			 * StringBuffer buffer=new StringBuffer(); String date =
			 * DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			 * String userid = loginInfo.getUserId();
			 */
			int result = notAvailableSlotDAO.updateimmurtizationchart(clientId, colname, val);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;

	}

	public String saveTempIPDData() throws Exception {
		Connection connection = null;
		IpdDAO ipddao = null;
		try {
			connection = Connection_provider.getconnection();
			StringBuffer strbuffer = new StringBuffer();

			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			String data = buffer.toString();
			JSONObject jsonObject = new JSONObject(data);
			String addmissionfor = jsonObject.getString("addmissionfor");
			String alergies = jsonObject.getString("alergies");
			String packagename = jsonObject.getString("packagename");
			String chiefcomplains = jsonObject.getString("chiefcomplains");
			String presentillness = jsonObject.getString("presentillness");
			String onexamination = jsonObject.getString("onexamination");
			String pasthistory = jsonObject.getString("pasthistory");
			String familyhist = jsonObject.getString("familyhist");
			String personalhist = jsonObject.getString("personalhist");
			String surgicalnotes = jsonObject.getString("surgicalnotes");
			String suggestedtrtment = jsonObject.getString("suggestedtrtment");
			String earlierinvest = jsonObject.getString("earlierinvest");
		
		String birthhist = jsonObject.getString("birthhist");
		String emmunizationhist = jsonObject.getString("emmunizationhist");
		String diethist = jsonObject.getString("diethist");
		String developmenthist = jsonObject.getString("developmenthist");
		
			String ipdid = jsonObject.getString("ipdid");
			String clientid = jsonObject.getString("clientid");
			String coloumn = "";

			ipddao = new JDBCIpdDAO(connection);
			boolean flag = false;
			flag = ipddao.checkOfTempData(ipdid, clientid, coloumn);
			if (flag) {
				int b = ipddao.updateTempIPDData(ipdid, clientid, addmissionfor, alergies, packagename, chiefcomplains,
						presentillness, onexamination, pasthistory, familyhist, personalhist, surgicalnotes,
						suggestedtrtment, earlierinvest,diethist, birthhist, developmenthist, emmunizationhist);

			} else {
				int a = ipddao.insertTempIPDData(ipdid, clientid, addmissionfor, alergies, packagename, chiefcomplains,
						presentillness, onexamination, pasthistory, familyhist, personalhist, surgicalnotes,
						suggestedtrtment, earlierinvest,diethist, birthhist, developmenthist, emmunizationhist);
			}

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("1");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getIPDTempData() throws Exception {
		try {

			if (!verifyLogin(request)) {
				return "login";
			}
			Connection connection = null;
			IpdDAO ipddao = null;
			Bed bed = new Bed();

			connection = Connection_provider.getconnection();
			StringBuffer strbuffer = new StringBuffer();

			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			String data = buffer.toString();
			JSONObject jsonObject = new JSONObject(data);

			String ipdid = jsonObject.getString("ipdid");
			String clientid = jsonObject.getString("clientid");

			ipddao = new JDBCIpdDAO(connection);

			bed = ipddao.getAllIPDData(ipdid, clientid);

			String addmissionfor = bed.getAddmissionfor();
			String alergies = bed.getAlergies();
			String packagename = bed.getPackagename();
			String chiefcomplains = bed.getChiefcomplains();
			String presentillness = bed.getPresentillness();

			String onexamination = bed.getOnexamination();
			String pasthistory = bed.getPasthistory();
			String familyhist = bed.getFamily_history();
			String personalhist = bed.getPersonalhist();
			String surgicalnotes = bed.getSurgicalnotes();
			String suggestedtrtment = bed.getSuggestedtrtment();
			String earlierinvest = bed.getEarlierinvest();
           String birthhist = bed.getBirthhist();
           String diethist = bed.getDiethist();
           String emmunizationhist = bed.getEmmunizationhist();
           String developmenthist = bed.getDevelopmenthist();
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("addmissionfor", addmissionfor);
			jsonobj.put("alergies", alergies);
			jsonobj.put("packagename", packagename);
			jsonobj.put("chiefcomplains", chiefcomplains);
			jsonobj.put("presentillness", presentillness);
			jsonobj.put("onexamination", onexamination);
			jsonobj.put("pasthistory", pasthistory);
			jsonobj.put("familyhist", familyhist);
			jsonobj.put("personalhist", personalhist);
			jsonobj.put("surgicalnotes", surgicalnotes);
			jsonobj.put("suggestedtrtment", suggestedtrtment);
			jsonobj.put("earlierinvest", earlierinvest);

			jsonobj.put("birthhist", birthhist);
			jsonobj.put("diethist", diethist);
			jsonobj.put("emmunizationhist", emmunizationhist);
			jsonobj.put("developmenthist", developmenthist);
			
			
			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}
	
	public String savediagnosisfastJSON()throws Exception{

		if (!verifyLogin(request)) {
			return "login";
		}
		try{ 
		Connection connection= Connection_provider.getconnection();
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);

		String condition = jsonObject.getString("condition");
		String icdcode= "0";
		DiagnosisDAO  diagnosisDAO =new JDBCDiagnosisDAO(connection);
		Diagnosis diagnosis=new Diagnosis();
		diagnosis.setName(condition);
		diagnosis.setIcdcode(icdcode);
		int checkid=diagnosisDAO.checkExixstDiagnosis(diagnosis.getName());
		int id=0;
		if (checkid>0) {
			id=0;
		}else{
		 id=diagnosisDAO.addDiagnosisName(diagnosis);
		}
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("condition", id);
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);

		
		
		}catch(Exception e){
			e.printStackTrace();
		}
		 
		 return null;
	 }
	
public String setAllDiagosisJSON(){

	if (!verifyLogin(request)) {
		return "login";
	}
	
	try{
		Connection connection= Connection_provider.getconnection();
		DiagnosisDAO diagnosisDAO= new JDBCDiagnosisDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		StringBuffer buffer= new StringBuffer();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);

		String selected = jsonObject.getString("selected");
		int i = 0;
		for (String s : selected.split(",")) {

			int d = Integer.parseInt(s);
			if (d == 0) {
				continue;
			} else {

				Diagnosis diagnosis = diagnosisDAO.getDiagnosisName(s);
				buffer.append("<tr>");
				buffer.append("<td><input type='checkbox' checked='checked' onclick=reoveThisRow(this,'"
						+ diagnosis.getId() + "') value='" + diagnosis.getId()
						+ "' class='classD' /> <input type='hidden' value=" + diagnosis.getId()
						+ " name='conditions[" + i + "].conditionid'  /> </td>");
				buffer.append("<td>" + diagnosis.getName() + "</td>");
				buffer.append("<td class='hidden'><a onclick=reoveThisRow('" + diagnosis.getId()
						+ "')><i class='fa fa-trash-o'></i></a></td>");
				buffer.append("</tr>");
				i++;
			}

		}
		String responsej= buffer.toString();
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("responsej", responsej);
		

		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);


	}catch(Exception e){
		e.printStackTrace();
	}
	
	return null;
}

public String getdiagnosisJSON()throws Exception{

	if (!verifyLogin(request)) {
		return "login";
	}
	try{
		Connection connection= Connection_provider.getconnection();
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		String selected= jsonObject.getString("selected");
		
		String search= jsonObject.getString("search");
		
		ArrayList<Client> ipdCondtitionList = ipdDAO.getAllDiagnosis(search);
		StringBuilder builder = new StringBuilder();
		for (Client client : ipdCondtitionList) {

			boolean flag = false;

			for (String s : selected.split(",")) {

				if (s == null) {
					continue;
				}

				int d = Integer.parseInt(s);
				if (d == 0) {
					continue;
				}
				if (d == client.getId()) {
					flag = true;
					break;
				}
			}
			if(loginInfo.isF_diagnosis_discharge()){
				if (flag) {

					builder.append("<tr>");
					builder.append("<td><input type='checkbox' checked='checked' onclick='setfreedischargedata(this)'  value='"
							+ client.getId() + "'  /></td>");
					builder.append("<td>" + client.getTreatmentType() + "</td>");
					builder.append("</tr>");

				} else {

					builder.append("<tr>");
					builder.append("<td><input type='checkbox' onclick='setfreedischargedata(this)'  value='" + client.getId()
							+ "'  /></td>");
					builder.append("<td>" + client.getTreatmentType() + "</td>");
					builder.append("</tr>");

			}
			}	else{
				
				if (flag) {

					builder.append("<tr>");
					builder.append("<td><input type='checkbox' checked='checked' onclick='setCheckedData(this)' value='"
							+ client.getId() + "'  /></td>");
					builder.append("<td>" + client.getTreatmentType() + "</td>");
					builder.append("</tr>");

				} else {

					builder.append("<tr>");
					builder.append("<td><input type='checkbox' onclick='setCheckedData(this)' value='" + client.getId()
							+ "'  /></td>");
					builder.append("<td>" + client.getTreatmentType() + "</td>");
					builder.append("</tr>");

			}
		}


		}
	String responsej=	builder.toString();
	JSONObject jsonobj = new JSONObject();
	jsonobj.put("responsej", responsej);
		

String response1 = jsonobj.toString();
response.setContentType("application/json");
response.setHeader("Cache-Control", "no-cache");
response.getWriter().write(response1);


		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return null;
}

public String getprodiagnosisJSON()throws Exception{

	if (!verifyLogin(request)) {
		return "login";
	}
	try{
		Connection connection= Connection_provider.getconnection();
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		String selected= jsonObject.getString("selected");
		
		String search= jsonObject.getString("search");
		
		ArrayList<Client> ipdCondtitionList = ipdDAO.getAllDiagnosis(search);
		StringBuilder builder = new StringBuilder();
		for (Client client : ipdCondtitionList) {

			boolean flag = false;

			for (String s : selected.split(",")) {

				if (s == null) {
					continue;
				}

				int d = Integer.parseInt(s);
				if (d == 0) {
					continue;
				}
				if (d == client.getId()) {
					flag = true;
					break;
				}
			}
			
				
				if (flag) {

					builder.append("<tr>");
					builder.append("<td><input type='checkbox' checked='checked' onclick='setCheckedData(this)' value='"
							+ client.getId() + "'  /></td>");
					builder.append("<td>" + client.getTreatmentType() + "</td>");
					builder.append("</tr>");

				} else {

					builder.append("<tr>");
					builder.append("<td><input type='checkbox' onclick='setCheckedData(this)' value='" + client.getId()
							+ "'  /></td>");
					builder.append("<td>" + client.getTreatmentType() + "</td>");
					builder.append("</tr>");

			}
		


		}
	String responsej=	builder.toString();
	JSONObject jsonobj = new JSONObject();
	jsonobj.put("responsej", responsej);
		

String response1 = jsonobj.toString();
response.setContentType("application/json");
response.setHeader("Cache-Control", "no-cache");
response.getWriter().write(response1);


		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return null;
}




public String setalldiagnosisemrJSON(){

	if (!verifyLogin(request)) {
		return "login";
	}
	Connection connection= null;
	try{
		connection= Connection_provider.getconnection();
		StringBuilder buffer1 = new StringBuilder();
		DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
		StringBuffer builder=new StringBuffer();
	
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		String selected= jsonObject.getString("selected");
		int i=0;
		for(String s:selected.split(",")){
			
			   int d=Integer.parseInt(s);
			   if(d==0){
				    continue;
			   } else {
				   	
				    Diagnosis diagnosis=diagnosisDAO.getDiagnosisName(s);
				    builder.append("<tr><td>");
					builder.append("<input class='concase' checked='checked'  type='checkbox' onclick='showopdcontxtoneditornew("+d+")'  id='chh"+d+" name='ch"+d+"' value='"+d+"' /> ");
					builder.append("<span id='ccck"+d+"' >"+diagnosis.getName()+"</span><br>");
					builder.append("</td></tr>");
				   i++;
			   }
			    
		}
		String responsej=	builder.toString();
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("responsej", responsej);
			
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);


	}catch(Exception e){
		e.printStackTrace();
	}
	
	
	return null;
}


public String searchdiagnosisemrJSON(){

	if (!verifyLogin(request)) {
		return "login";
	}
	Connection connection= null;
	try{
	connection= Connection_provider.getconnection();
	StringBuilder buffer1 = new StringBuilder();
	DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
	StringBuilder builder=new StringBuilder();

	BufferedReader reader = request.getReader();
	String line;
	while ((line = reader.readLine()) != null) {
		buffer1.append(line);
	}
	String data = buffer1.toString();
	JSONObject jsonObject = new JSONObject(data);
	
	String selected= jsonObject.getString("selected");
	String search= jsonObject.getString("search");
	IpdDAO ipdDAO = new JDBCIpdDAO(connection);
	ArrayList<Client> ipdCondtitionList = ipdDAO.getAllDiagnosis(search);
	
	for (Client client : ipdCondtitionList) {
		
		boolean flag=false;
		
		for(String s:selected.split(",")){

			     if(s==null){
			    	 continue;
			     }
			
			    int d=Integer.parseInt(s);
			    if(d==0){
			    	continue;
			    }
			    if(d==client.getId()){
			    	flag=true;
			    	break;
			    }
		}
		
	   
		if(flag){
			
			  builder.append("<tr><td>");
				builder.append("<input class='concase' checked='checked' type='checkbox' onclick='setCheckedData(this)'  id='chh"+client.getId()+" name='ch"+client.getId()+"' value='"+client.getId()+"' /> ");
				builder.append("<span id='ccck"+client.getId()+"' >"+client.getTreatmentType()+"</span><br>");
				builder.append("</td></tr>");
		} else {
			  builder.append("<tr><td>");
				builder.append("<input class='concase' type='checkbox' onclick='setCheckedData(this)'  id='chh"+client.getId()+" name='ch"+client.getId()+"' value='"+client.getId()+"' /> ");
				builder.append("<span id='ccck"+client.getId()+"' >"+client.getTreatmentType()+"</span><br>");
				builder.append("</td></tr>");
		}
		  
		
	}
	
	
	String responsej=	builder.toString();
	JSONObject jsonobj = new JSONObject();
	jsonobj.put("responsej", responsej);
		
	
	String response1 = jsonobj.toString();
	response.setContentType("application/json");
	response.setHeader("Cache-Control", "no-cache");
	response.getWriter().write(response1);

		
	}catch(Exception e){
e.printStackTrace();		
	}
	
	return null;
}


public String searchdiagnosiseditemrJSON(){

	if (!verifyLogin(request)) {
		return "login";
	}
	Connection connection= null;
	try{
		connection= Connection_provider.getconnection();
		StringBuilder buffer1 = new StringBuilder();
		DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);

		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		String selected= jsonObject.getString("selected");
		String search= jsonObject.getString("search");
		
		
		ArrayList<Client> ipdCondtitionList = ipdDAO.getAllDiagnosis(search);
		StringBuilder builder = new StringBuilder();
		for (Client client : ipdCondtitionList) {
			
			boolean flag=false;
			
			for(String s:selected.split(",")){

				     if(s==null){
				    	 continue;
				     }
				
				    int d=Integer.parseInt(s);
				    if(d==0){
				    	continue;
				    }
				    if(d==client.getId()){
				    	flag=true;
				    	break;
				    }
			}
			
		   
			if(flag){
				
				  builder.append("<tr><td>");
					builder.append("<input class='econcase' checked='checked' type='checkbox' onclick='setCheckedDataEdit(this)'  id='echh"+client.getId()+" name='ech"+client.getId()+"' value='"+client.getId()+"' /> ");
					builder.append("<span id='econtxt"+client.getId()+"' >"+client.getTreatmentType()+"</span><br>");
					builder.append("</td></tr>");
			} else {
				  builder.append("<tr><td>");
					builder.append("<input class='econcase' type='checkbox' onclick='setCheckedDataEdit(this)'  id='echh"+client.getId()+" name='ech"+client.getId()+"' value='"+client.getId()+"' /> ");
					builder.append("<span id='econtxt"+client.getId()+"' >"+client.getTreatmentType()+"</span><br>");
					builder.append("</td></tr>");
			}
			  
			
		}
		
		

		String responsej=	builder.toString();
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("responsej", responsej);
			
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);

		
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return null;
}

public String newmedicineJSON(){

	if (!verifyLogin(request)) {
		return "login";
	}
	Connection connection= null;
	try{
		connection =Connection_provider.getconnection();
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		InventoryProductDAO inventoryProductDAO= new JDBCInventoryProductDAO(connection);
		PrescriptionMasterDAO prescriptionMasterDAO= new JDBCPrescriptionMasterDAO(connection);
		
		
		String medicinename= jsonObject.getString("medicinename");
		String genericname= jsonObject.getString("genericname");
		String istemp = jsonObject.getString("istemp");
		String newcomponent = jsonObject.getString("newcomponent");
		String date = "";
		Product product= new Product();
		product.setProduct_name(medicinename);
		product.setGeneric_name(genericname);
		product.setComponent(newcomponent);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		date = dateFormat.format(cal.getTime());
		product.setDate(date);
		String loc = (String) session.getAttribute("location");
		if (loc == null) {
			loc = "0";
		}
		product.setLocation(loc);
		if (product.getGeneric_name() == null) {
			product.setGeneric_name("");
		} else if (product.getGeneric_name().equals("")) {
			product.setGeneric_name("");
		}
		
		product.setCategory_id("2");
		product.setSubcategory_id("1");
		
		if(istemp!=null){
			if(istemp.equals("")){
				istemp="0";
			}
		}else{
			istemp="0";
		}
		
		product.setUserid(loginInfo.getUserId());
		product.setTodate(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0]);
		
		/*int res = inventoryProductDAO.addNewProduct(product);*/
		//int result = inventoryProductDAO.addNewProductToVendor(loc, res);
		int res=0;
		int result=prescriptionMasterDAO.addToMedicineMaster(product,res,istemp);
		
		
		
		//add to medicine master
		
		ArrayList<Master>medicineList = masterDAO.getMedicineList();
		StringBuffer buffer =new StringBuffer();
		/*buffer.append("<select class='form-control chosen' id='mdicinename' name='mdicinename' onchange='getDoseNote(this.value)' > ");
		buffer.append("<option value='0'>Select Medicine</option>");
		if(istemp.equals("1")){
			Master master1 = masterDAO.getMedicineDetails(result);
			buffer.append("<option value='"+master1.getId()+"'> "+master1.getGenericname()+" </option>");
		}
		
		for(Master master:medicineList){
			
			buffer.append("<option value='"+master.getId()+"'> "+master.getGenericname()+" </option>");
		}
		buffer.append("</select>");
		
		buffer.append("<a href='#' type='button' class='btn btn-sm btn-primary' onclick=openhiddendiv('hiddendiv') style='margin-left: 5px;'><i  class='fa fa-plus'></i></a>");
		*/
		
		if(loginInfo.isAdd_medicine() || loginInfo.getUserType()==2){
			buffer.append("<a title='Add New Medicine' href='#' type='button' class='btn btn-sm btn-primary' onclick=openhiddendiv('hiddendiv') style='margin-right: 5px;'><i class='fa fa-pencil' aria-hidden='true'></i></a>");
		} else{
			buffer.append("<a title='Add New Medicine' href='#' type='button' class='btn btn-sm btn-primary'  style='margin-right: 5px;'><i class='fa fa-pencil' aria-hidden='true'></i></a>");
		} 
		buffer.append("<select class='form-control chosen' id='mdicinename' name='mdicinename' onchange='getDoseNote(this.value)' > ");
		buffer.append("<option value='0'>Select Medicine</option>");
		if(istemp.equals("1")){
			Master master1 = masterDAO.getMedicineDetails(result);
			buffer.append("<option value='"+master1.getId()+"'> "+master1.getGenericname()+" </option>");
		}
		for(Master master:medicineList){
			buffer.append("<option value='"+master.getId()+"'> "+master.getGenericname()+" </option>");
		}
		buffer.append("</select>");
		//buffer.append("<a href='#' type='button' class='btn btn-sm btn-primary' onclick=openhiddendiv('hiddendiv') style='margin-left: 5px;'><i  class='fa fa-plus'></i></a>");
		
		if(loginInfo.isAdd_medicine() || loginInfo.getUserType()==2){
			buffer.append("&nbsp;<div><a href='#'><i class='fa fa-plus' style='width:10px;' onclick=openBlankPopup('addPrescriptiondetails')></i></a></div>");
		} else{
			buffer.append("&nbsp;<div><a href='#'><i class='fa fa-plus' style='width:10px;' ></i></a></div>");
		} 

		String responsej=	buffer.toString();
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("responsej", responsej);
			
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);

		
		
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return null;
}
public String getvisitingconsultantlist() throws Exception {
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		StringBuffer str = new StringBuffer();
		/*ArrayList<UserProfile> visitingConsultDoctors=userProfileDAO.getVisitingPractitinerList();*/
		ArrayList<UserProfile> visitingConsultDoctors=userProfileDAO.getVisitingPractitinerListFromUser();
		str.append("<select name='consultantdr' id='consultantdr' class='form-control showToolTip chosen' >");
		str.append("<option value='0'>Select Consultant</option>");
		for (UserProfile userProfile : visitingConsultDoctors) {
			str.append("<option value='" + userProfile.getId() + "'>" + userProfile.getFullname() + "</option>");
		}
		str.append("</select>");

		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("" + str.toString() + "");

	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		connection.close();
	}
	return null;
}

//  16 May 2018
//To speed up
public String charge()throws Exception{
	Connection connection=null;
    try {
    	connection=Connection_provider.getconnection();
    	IpdDAO ipdDAO = new JDBCIpdDAO(connection);
    	ChargesAccountProcessingDAO chargesAccountProcessingDAO= new JDBCChargeAccountProcesDAO(connection);
    	
    	String chargetype = request.getParameter("chargetype");
    	
    	String ipdwhopay = request.getParameter("ipdwhopay");
    	String ipdtpid = request.getParameter("ipdtpid");
    	String ipdaddmissionid = request.getParameter("ipdaddmissionid");
    	String clientId = request.getParameter("clientId");
    	
    	if(ipdwhopay.equals("")){
    		ClientDAO clientDAO = new JDBCClientDAO(connection);
    		Client client = clientDAO.getClientDetails(clientId);
    		ipdwhopay = client.getWhopay();
    	}
    	
    	if(ipdwhopay.equals(Constants.PAY_BY_CLIENT)){
    		ipdtpid = "0";
    	}
    	
    	if(ipdaddmissionid==null){
    		
    		ipdaddmissionid="0";
    	}
    	if(ipdaddmissionid.equals("")){
    		ipdaddmissionid="0";
    	}
    	
    	BedDao bedDao=new JDBCBedDao(connection);
		Bed bed = bedDao.getEditIpdData(ipdaddmissionid);
		
		String wardid = bed.getWardid();
		
		if(wardid==null){
			wardid="0";
		}
		if(wardid.equals("")){
			wardid="0";
		}
		
		int compulsary_consultant=chargesAccountProcessingDAO.isCompulasryConsultant(chargetype);
    	
		ArrayList<Master>list = ipdDAO.getFilteredChargeList(chargetype,ipdtpid,wardid,clientId,loginInfo.isShow_wardname());
    	StringBuffer str = new StringBuffer();
   
    		str.append("<select onchange='setAdditionalChargeAjax1(this.value)' name='chargeTYpe' id='chargeTYpe' class='form-control showToolTip chosen' >");
    		str.append("<option value='0'>Select Charge</option>");
    		for(Master master : list){
    			str.append("<option value='"+master.getId()+"'>"+master.getName()+"</option>");
    		}
    		str.append("</select>");
    		
    		str.append("!@");
    		str.append(compulsary_consultant);
    		response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+""); 
    	
    }catch(Exception e){
 	   e.printStackTrace();
    }
    finally{
		connection.close();
	}
	return null;
}
//  16 May 2018
public String apmtcharge()throws Exception{
	
	   Connection connection=null;
     
     try {
     	
     	connection=Connection_provider.getconnection();
     	IpdDAO ipdDAO = new JDBCIpdDAO(connection);
     	String chargetype = request.getParameter("chargetype");
     	String tpid=request.getParameter("tpid");
     	String whopay = request.getParameter("whopay");
     	
     	if(whopay.equals(Constants.PAY_BY_CLIENT)){
     		tpid = "0";
     	}
     	
     	
     	ArrayList<Master>list = ipdDAO.getFilteredOpdChargeList(chargetype,tpid,whopay);
     	StringBuffer str = new StringBuffer();
     	
    
     		str.append("<select onchange='setAdditionalChargeAjax(this.value)' name='chargeTYpe' id='addChargeType' class='form-control showToolTip chosen' >");
     		str.append("<option value='0'>Select Charge</option>");
     		for(Master master : list){
     			str.append("<option value='"+master.getId()+"'>"+master.getName()+"</option>");
     		}
     		str.append("</select>");
     		
     		response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+str.toString()+""); 
     	
     }catch(Exception e){
  	   e.printStackTrace();
     }finally{
			connection.close();
		}
	
	return null;
}
public String saveTempIPDDischargeData() throws Exception {
	Connection connection = null;
	IpdDAO ipddao = null;
	try {
		connection = Connection_provider.getconnection();
		StringBuffer strbuffer = new StringBuffer();

		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);
		

		   	String history = jsonObject.getString("history");
		String surgicalnotes = jsonObject.getString("surgicalnotes");
		String hospitalcourse = jsonObject.getString("hospitalcourse");
		String treatmentgiven = jsonObject.getString("treatmentgiven");
		String investigation = jsonObject.getString("investigation");
		String findondis = jsonObject.getString("findondis");
		String dischargeadvice = jsonObject.getString("dischargeadvice");
	
	String birthhist = jsonObject.getString("birthhist");
	String emmunizationhist = jsonObject.getString("emmunizationhist");
	String diethist = jsonObject.getString("diethist");
	String developmenthist = jsonObject.getString("developmenthist");
	
		String ipdid = jsonObject.getString("ipdid");
		String clientid = jsonObject.getString("clientid");
		String coloumn = "";

		ipddao = new JDBCIpdDAO(connection);
		boolean flag = false;
		flag = ipddao.checkOfTempData(ipdid, clientid, coloumn);
		if (flag) {
			int b = ipddao.updateTempIPDDischargeData(ipdid, clientid, history, surgicalnotes, hospitalcourse, treatmentgiven, investigation, findondis, dischargeadvice, diethist, birthhist, developmenthist, emmunizationhist);
					

		} else {
			int a = ipddao.insertTempIPDDischargeData(ipdid, clientid, history, surgicalnotes, hospitalcourse, treatmentgiven, investigation, findondis, dischargeadvice, diethist, birthhist, developmenthist, emmunizationhist);
		}

		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("1");

	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}



public String getIPDTempDischargeData() throws Exception {
	try {

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		IpdDAO ipddao = null;
		Bed bed = new Bed();

		connection = Connection_provider.getconnection();
		StringBuffer strbuffer = new StringBuffer();

		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);

		String ipdid = jsonObject.getString("ipdid");
		String clientid = jsonObject.getString("clientid");

		ipddao = new JDBCIpdDAO(connection);

		bed = ipddao.getAllIPDDischargeData(ipdid, clientid);
		
		
       String birthhist = bed.getBirthhist();
       String diethist = bed.getDiethist();
       String emmunizationhist = bed.getEmmunizationhist();
       String developmenthist = bed.getDevelopmenthist();
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("surgicalnotes", bed.getSurgicalnotes());
		jsonobj.put("hospitalcourse",bed.getHospitalcourse());
		jsonobj.put("treatmentgiven", bed.getTreatmentgiven());
		jsonobj.put("investigation", bed.getInvestigation());
		jsonobj.put("findondis", bed.getFindondis());
		jsonobj.put("dischargeadvice", bed.getDischargeadvice());
	
		jsonobj.put("birthhist", birthhist);
		jsonobj.put("diethist", diethist);
		jsonobj.put("emmunizationhist", emmunizationhist);
		jsonobj.put("developmenthist", developmenthist);
		
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);

	} catch (Exception e) {
		e.printStackTrace();

	}

	return null;
}

//  21 May 2018 

public String getipdrmodata() throws Exception {
	Connection connection = null;
	try {

		if (!verifyLogin(request)) {
			return "login";
		}
		
		connection = Connection_provider.getconnection();
		IpdDAO ipddao = new JDBCIpdDAO(connection);
		
		StringBuffer strbuffer = new StringBuffer();
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);

		String ipdid = jsonObject.getString("ipdid");
		String clientid = jsonObject.getString("clientid");
		
		String addmissiondate = ipddao.getIPDAdmissionDate(ipdid);
		long day=0;
		if (!addmissiondate.equals("")) {
			String[] addate = addmissiondate.split(" ");
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			String toDate = dateFormat.format(cal.getTime());
			toDate = DateTimeUtils.getCommencingDate1(toDate);
			
			String date1[] = addate[0].split("-");
			String date= date1[2]+"/"+date1[1]+"/"+date1[0];
			
			Date d1=new SimpleDateFormat("dd/MM/yyyy").parse(date);
			Date d2= new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
			long diff=d2.getTime()- d1.getTime();
			long difference= (Math.abs((diff / (1000*60*60*24))));
			day = difference++;
		}
		
		ArrayList<Ipd> arrayList = ipddao.getAllRMONotes(ipdid,day);
		
		Ipd ipd = ipddao.getRmoNoteDay(ipdid,day);
		
		JSONObject jsonobj = new JSONObject();
		strbuffer.append("<tr>");
		strbuffer.append("<input type='hidden' id='rmocurdateid' value='"+ipd.getId()+"'>");
		strbuffer.append("<input type='hidden' id='rmocurday' value='"+day+"'>");
		strbuffer.append("<td>Day: "+day+"</td>");
		strbuffer.append("</tr>");
		strbuffer.append("<tr>");
		strbuffer.append("<td><textarea rows='6'  class='form-control' id='rmonewdaynote' placeholder='Day to Day Note' style='background-color: beige;'>"+ipd.getNotes()+"</textarea></td>");
		strbuffer.append("</tr>");
		
		StringBuffer buffer2 = new StringBuffer();
		for (Ipd ipd2 : arrayList) {
			buffer2.append("<tr>");
			buffer2.append("<td>Day: "+ipd2.getDay()+"</td>");
			buffer2.append("</tr>");
			buffer2.append("<tr>");
			buffer2.append("<td><p>"+ipd2.getNotes()+"</p></td>");
			buffer2.append("</tr>");
		}
		
		jsonobj.put("prenoteslist", buffer2.toString());
		jsonobj.put("curentdaynotes", strbuffer.toString());
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);

	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

//24 May 2018

public String savermonotes() throws Exception {
	Connection connection = null;
	try {

		if (!verifyLogin(request)) {
			return "login";
		}
		
		connection = Connection_provider.getconnection();
		IpdDAO ipddao = new JDBCIpdDAO(connection);
		
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);

		String ipdid = jsonObject.getString("ipdid");
		String clientid = jsonObject.getString("clientid");
		String rmonotes = jsonObject.getString("rmonotes");
		String rmocurdateid = jsonObject.getString("rmocurdateid");
		String rmocurday = jsonObject.getString("rmocurday");
		
		Ipd ipd = new Ipd();
		ipd.setIpdid(ipdid);
		ipd.setClientid(clientid);
		ipd.setNotes(rmonotes);
		ipd.setDay(rmocurday);
		ipd.setId(Integer.parseInt(rmocurdateid));
		int res =0;
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();
		String toDate = dateFormat.format(cal.getTime());
		toDate = DateTimeUtils.getCommencingDate1(toDate);
		ipd.setDate(toDate);
		if(ipd.getId()>0){
			res = ipddao.updateRMONotes(ipd);
		}else{
			res = ipddao.saveRMONotes(ipd);
		}
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("response1", res);
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);

	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

//28 May 2018

public String checkrefundpaidstatus() throws Exception{
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		AdditionalDAO additionalDAO = new JDBCAdditionalDAO(connection);
		String id = request.getParameter("id");
		String clientid = request.getParameter("clientid");
		
		int res = additionalDAO.checkrefundStatus(id,clientid);
		StringBuffer buffer =new StringBuffer();
		buffer.append(""+res+"");
		buffer.append("~");
		buffer.append(""+id+"");
		buffer.append("~");
		buffer.append(""+clientid+"");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+buffer.toString()+""); 
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

//  02 Jun 2018
public String getinvoicechargeslist() throws Exception {
	Connection connection = null;
	try {
		if (!verifyLogin(request)) {
			return "login";
		}
		connection = Connection_provider.getconnection();
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		Clinic clinic = new Clinic();
		ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
		clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
		
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);
	
		String billsummary = jsonObject.getString("billsummary");
		String invoiceid = jsonObject.getString("invoiceid");
		String totalAmountx = jsonObject.getString("totalAmountx");
		String seq = jsonObject.getString("seq");
		
		String filterbydate= seq;
		ArrayList<Master>masterAssessmentList = new ArrayList<Master>();
		if(billsummary.equals("1")){
			masterAssessmentList = accountsDAO.getBillMasterAssessmentList(Integer.parseInt(invoiceid),filterbydate);
		}else{
			masterAssessmentList = accountsDAO.getMasterAssessmentList(Integer.parseInt(invoiceid),filterbydate);
		}
		
		StringBuffer stringBuffer = new StringBuffer();
		
		for (Master master : masterAssessmentList) {
			if(master.getName().equals("Ipd Registration Charge")){
				master.setName("Admission Charge");
			}
			stringBuffer.append("<tr class='totalbor'>");
			stringBuffer.append("<td><b>"+master.getName()+"</b></td>");
			stringBuffer.append("<td></td>");
			stringBuffer.append("<td></td>");
			stringBuffer.append("<td></td>");
			stringBuffer.append("<td></td>");
			stringBuffer.append("<td></td>");
			stringBuffer.append("<td style='text-align:right;color: #5cb85c;'>Sub Total "+Constants.getCurrency(loginInfo)+""+master.getCharge()+"</td>");
			stringBuffer.append("</tr>");
			for (Accounts accounts : master.getAssesmentList()) {
				stringBuffer.append("<tr>");
				stringBuffer.append("<td>");
					if(clinic.getInvoice_date()!=null){
						if(clinic.getInvoice_date().equals("0")){
							stringBuffer.append(""+accounts.getCommencing()+"");
						}	
					}
				stringBuffer.append("</td>");
				stringBuffer.append("<td>"+accounts.getTpcode()+"</td> ");
				stringBuffer.append("<td class='padletab'>");
					if(accounts.isDna()){
						stringBuffer.append(""+accounts.getAppointmentType()+"<span style='color:red'>(DNA)</span>");
					}else{
						stringBuffer.append(""+accounts.getAppointmentType()+"");
					}
				stringBuffer.append("</td>");
				stringBuffer.append("<td class='text-center'>");
				if(accounts.getPkginvissid()!=null){
					if(accounts.getPkginvissid().equals("0")){
						stringBuffer.append(""+accounts.getQuantity()+"");
					}
				}
				stringBuffer.append("</td>");
				stringBuffer.append("<td class=''>");
				if(accounts.getPkginvissid()!=null){
					if(accounts.getPkginvissid().equals("0")){
						stringBuffer.append(""+Constants.getCurrency(loginInfo)+""+accounts.getUnitcharge()+"");
					}
				}
				stringBuffer.append("</td>");
				
				stringBuffer.append("<td class=''>");
				if(accounts.getPkginvissid()!=null){
					if(accounts.getPkginvissid().equals("0")){
						stringBuffer.append(""+Constants.getCurrency(loginInfo)+""+accounts.getChargedisc()+"");
					}
				}
				stringBuffer.append("</td>");
				//stringBuffer.append("<%-- <td>"+accounts.getAppointmentType()+"</td> --%>");
				
				stringBuffer.append("<td class='text-right hidden'>");
				if(accounts.getPkginvissid()!=null){
					if(accounts.getPkginvissid().equals("0")){
						stringBuffer.append(""+Constants.getCurrency(loginInfo)+""+accounts.getCharges()+"");
					}
				}
				stringBuffer.append("</td>");
				stringBuffer.append("<td class='text-right'>");
				if(accounts.getPkginvissid()!=null){
					if(accounts.getPkginvissid().equals("0")){
						stringBuffer.append(""+Constants.getCurrency(loginInfo)+""+accounts.getChargeTotal()+"");
					}
				}
				stringBuffer.append("</td>");
				stringBuffer.append("</tr>");
			}
			
			
		}
		stringBuffer.append("<tr class='settopbac'>");
		stringBuffer.append("<td>Total</td>");
		stringBuffer.append("<td></td>");
		stringBuffer.append("<td></td>");
		stringBuffer.append("<td></td>");
		stringBuffer.append("<td></td>");
		stringBuffer.append("<td></td>");
		stringBuffer.append("<td class='text-right'>"+Constants.getCurrency(loginInfo)+""+totalAmountx+"</td>");
		stringBuffer.append("</tr>");
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("response1", stringBuffer.toString());
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

//  11 June 2018
public String getoutsourcedetails() throws Exception {
	Connection connection = null;
	try {
		if (!verifyLogin(request)) {
			return "login";
		}
		connection = Connection_provider.getconnection();
		InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
		Clinic clinic = new Clinic();
		ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
		clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
		
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);
	
		String outsourceid = jsonObject.getString("outsourceid");
		String inparentid = jsonObject.getString("inparentid");
		
		Investigation investigation = investigationDAO.getInvestigationDetails(inparentid);
		String outsourcename = investigationDAO.getOutSourceName(outsourceid);
		StringBuffer buffer2 = new StringBuffer();
		StringBuffer buffer3 = new StringBuffer();
		StringBuffer buffer4 = new StringBuffer();
		StringBuffer buffer5 = new StringBuffer();
		StringBuffer buffer6 = new StringBuffer();
		
		buffer2.append("<label>OutSourced to :  </label>");
		buffer2.append("<label> "+outsourcename+"</label>");
		
		buffer3.append("<label>Sample Handover to :  </label>");
		if(investigation.getHandoverto()!=null){
			if(investigation.getHandoverto().equals("")){
				buffer5.append("<input type='hidden' id='oshandovertostatus' value='0'>");
				buffer3.append("<input type='text' id='oshandoverto' value='"+investigation.getHandoverto()+"' class='form-control' placeholder='Sample Handover To'>");
			}else{
				buffer5.append("<input type='hidden' id='oshandovertostatus' value='1'>");
				buffer3.append("<input type='text' readonly='readonly' value='"+investigation.getHandoverto()+"' class='form-control' id='oshandoverto' placeholder='Sample Handover To'>");
			}
		}else{
			buffer5.append("<input type='hidden' id='oshandovertostatus' value='0'>");
			buffer3.append("<input type='text' id='oshandoverto' class='form-control' placeholder='Sample Handover To'>");
		}
		
		buffer4.append("<label>Report Received</label>");
			if(investigation.getIsreturnOS()!=null){
				if(investigation.getIsreturnOS().equals("1")){
					buffer4.append("<select id='isreturnOS' disabled='disabled' class='form-control chosen'>");
					buffer4.append("<option value='0'>No</option> ");
					buffer4.append("<option value='1' selected='selected'>Yes</option>");
				}else{
					buffer4.append("<select id='isreturnOS'  class='form-control' >");
					buffer4.append("<option value='0'>No</option> ");
					buffer4.append("<option value='1' >Yes</option>");
				}
			}else{
				buffer4.append("<select id='isreturnOS' class='form-control chosen'>");
				buffer4.append("<option value='0'>No</option> ");
				buffer4.append("<option value='1'>Yes</option>");
			}
		buffer4.append("</select>");
		
		buffer5.append("<label>HandOver From:</label>");
		if(investigation.getIsreturnOS()!=null){
			if(investigation.getIsreturnOS().equals("1")){
				buffer5.append("<input type='hidden' id='oshandoverfromstatus' value='1'>");
				buffer5.append("<input type='text' readonly='readonly' value='"+investigation.getHandoverfrom()+"' class='form-control' id='oshandoverfrom' placeholder='Handover From'>");
			}else{
				if(investigation.getHandoverfrom()!=null){
						buffer5.append("<input type='hidden' id='oshandoverfromstatus' value='0'>");
						buffer5.append("<input type='text' id='oshandoverfrom' value='"+investigation.getHandoverfrom()+"' class='form-control' placeholder='Handover From'>");
				}else{
					buffer5.append("<input type='hidden' id='oshandoverfromstatus' value='0'>");
					buffer5.append("<input type='text' id='oshandoverfrom' class='form-control' placeholder='Handover From'>");
				}
			}
		}else{
			if(investigation.getHandoverfrom()!=null){
				buffer5.append("<input type='hidden' id='oshandoverfromstatus' value='0'>");
				buffer5.append("<input type='text' id='oshandoverfrom' class='form-control' placeholder='Handover From'>");
		}else{
			buffer5.append("<input type='hidden' id='oshandoverfromstatus' value='0'>");
			buffer5.append("<input type='text' id='oshandoverfrom' class='form-control' placeholder='Handover From'>");
		}
	}
		
		if(investigation.getIsreturnOS()!=null){
			if(investigation.getIsreturnOS().equals("1")){
				buffer6.append("<a href='#' class='btn btn-danger'>OK</a>");
			}else{
				buffer6.append("<a href='#' class='btn btn-danger' onclick='saveOutsourceDetails()'>Save</a>");
				
			}
		}else{
			buffer6.append("<a href='#' class='btn btn-danger' onclick='saveOutsourceDetails()'>Save</a>");
		}
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("outsourcediv", buffer2.toString());
		jsonobj.put("outsourcehandoverdiv", buffer3.toString());
		jsonobj.put("outsourceisretunddiv", buffer4.toString());
		jsonobj.put("outsourcereturnuserdiv", buffer5.toString());
		jsonobj.put("outsourcebtndiv", buffer6.toString());
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String updateinvestoutsource() throws Exception{
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
		StringBuffer buffer = new StringBuffer();
		String invid = request.getParameter("invid");
		String oshandoverto = request.getParameter("oshandoverto");
		String isreturnOS = request.getParameter("isreturnOS");
		String oshandoverfrom = request.getParameter("oshandoverfrom");
		String oshandovertostatus = request.getParameter("oshandovertostatus");
		String outexpdsate=DateTimeUtils.isNull(request.getParameter("outexpdsate"));
		if(isreturnOS==null){
			isreturnOS="1";
		}else if(isreturnOS.equals("")){
			isreturnOS="1";
		}else if(isreturnOS.equals("null")){
			isreturnOS="1";
		}
		
		if(!outexpdsate.equals("")){
			outexpdsate=DateTimeUtils.getCommencingDate1(outexpdsate);
		}
		
		String userid= loginInfo.getUserId();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		int res = investigationDAO.updateInvesOutsourceNew(invid,userid,date,oshandoverto,oshandoverfrom,isreturnOS,oshandovertostatus,outexpdsate);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(buffer.toString());
	} catch (Exception e) {

		e.printStackTrace();
	} finally {
		connection.close();
	}

	return null;
}


public String priscnurseJson() throws Exception{
	if (!verifyLogin(request)) {
		return "login";
	}
	Connection connection= null;
	try{
		connection =Connection_provider.getconnection();
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		EmrDAO emrDAO = new JDBCEmrDAO(connection);
		//MasterDAO masterDAO = new JDBCMasterDAO(connection);
		String parentid= jsonObject.getString("parentid");
		
		String date = "";
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		date = dateFormat.format(cal.getTime());
		
		//ArrayList<Master>medicineList = masterDAO.getMedicineList();
		StringBuffer buffer =new StringBuffer();
		
		//Priscription priscription = emrDAO.getPriscriptionParentData(Integer.parseInt(parentid));
		ArrayList<Priscription> arrayList= emrDAO.getPriscriptionChildData(parentid,0);
		int size = arrayList.size();
		if (size > 0){
			String totalid = arrayList.get(size - 1).getTotalid();
			String medid = arrayList.get(size - 1).getMedid();
			buffer.append("<input type='hidden' id='totalid' name='totalid' value='"+totalid+"'>");
			buffer.append("<input type='hidden' id='medid' name='medid' value='"+medid+"'>");
		}else{
			buffer.append("<input type='hidden' id='totalid' name='totalid' value='"+0+"'>");
			buffer.append("<input type='hidden' id='medid' name='medid' value='"+0+"'>");
		}
		int i=0;
		buffer.append("<input type='hidden' id='priscstatusparentid' name='parentid' value='"+parentid+"'>");
		for (Priscription priscription2 : arrayList) {
			buffer.append("<tr>");
			buffer.append("<input type='hidden' class='akashpriscrequest' value='"+priscription2.getId()+"'>");
			buffer.append("<input type='hidden' name='mdname"+priscription2.getId()+"' value='"+priscription2.getMdicinenametxt()+"'>");
			buffer.append("<input type='hidden' name='mdid"+priscription2.getId()+"' value='"+priscription2.getMdicinenameid()+"'>");
			buffer.append("<input type='hidden' name='childid"+priscription2.getId()+"' value='"+priscription2.getId()+"'>");
			buffer.append("<td><input type='checkbox' class='akashcase' value='"+priscription2.getId()+"' class='form-control' /></td>");
			buffer.append("<td>"+(++i)+"</td>");
			buffer.append("<td>"+priscription2.getMdicinenametxt()+"</td>");
			if(priscription2.getDosage()!=null){
				buffer.append("<td>"+priscription2.getDosage()+"</td>");
			}else{
				buffer.append("<td></td>");
			}
			buffer.append("<td>"+priscription2.getPriscdays()+"</td>");
			buffer.append("<td>"+priscription2.getPriscqty()+"</td>");
			buffer.append("<td><input type='number' tabindex="+i+" class='form-control' id='mdrequestqty"+priscription2.getId()+"' name='mdrequestqty"+priscription2.getId()+"'></td>");
			buffer.append("</tr>");
		}
		PrescriptionDAO prescriptionDAO = new JDBCPrescriptionDAO(connection);
		int locationid = prescriptionDAO.getPriscLastLocationId(Integer.parseInt(parentid));
		String responsej=	buffer.toString();
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("responsej", responsej);
		jsonobj.put("locationid_data", locationid);
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return null;
}


public String addnewpriscbynurse() throws Exception{
	if (!verifyLogin(request)) {
		return "login";
	}
	Connection connection= null;
	try{
		connection =Connection_provider.getconnection();
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		EmrDAO emrDAO = new JDBCEmrDAO(connection);
		String parentid= jsonObject.getString("parentid");
		String newmedicineid = jsonObject.getString("newmedicineid");
		String date = "";
		
		String med= emrDAO.getMedicicneName(newmedicineid);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		date = dateFormat.format(cal.getTime());
		
		StringBuffer buffer =new StringBuffer();
		Priscription priscription = emrDAO.getPriscriptionParentData(Integer.parseInt(parentid));
		priscription.setDate(date);
		priscription.setUserid(loginInfo.getUserId());
		priscription.setMdicinenameid(newmedicineid);
		priscription.setMdicinenametxt(med);
		priscription.setParentid(parentid);
		int res = emrDAO.saveNewMedBynurse(priscription);
		
		String responsej=	buffer.toString();
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("responsej", responsej);
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return null;
}


public String admissinpriscdata()throws Exception{
	 
	 Connection connection = null;
	 
	 try{
		 connection = Connection_provider.getconnection();
		 IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		 EmrDAO emrDAO = new JDBCEmrDAO(connection);
		 String sessionadmissionid = (String)session.getAttribute("sessionadmissionid");
		 
		 ArrayList<Priscription>dischargePriscList = ipdDAO.getAdmissionPrescList(sessionadmissionid);
		
		ArrayList<Master> dosageList = emrDAO.getDosageList();
		
		int size = dischargePriscList.size();
		String totalchildmedids ="0";
		if (size > 0) {
			totalchildmedids = dischargePriscList.get(size - 1).getTotalchildmedids();
		}
		
		int i = 0;
		StringBuffer str = new StringBuffer();
		for(Priscription priscription : dischargePriscList){
			str.append("<tr>");
			str.append("<td><input type='number' class='form-control' name='dicpriscmed"+priscription.getId()+"' value='"+priscription.getDispriscsrno()+"'></td>");
			str.append("<td>"+priscription.getMdicinenametxt()+"</td/>");
			//  05 June 2018
			str.append("<td>");
			str.append("<select id='discpriscdose"+priscription.getId()+"' name='discpriscdose"+priscription.getId()+"' class='form-control chosen-select'>");
			for (Master master : dosageList) {
				if(priscription.getPriscdose()!=null){
					if(master.getName()!=null){
						if(master.getName().equals(priscription.getPriscdose())){
							str.append("<option value='"+master.getName()+"' selected='selected'>"+master.getName()+"</option>");
						}else{
							str.append("<option value='"+master.getName()+"'>"+master.getName()+"</option>");
						}
					}else{
						str.append("<option value='"+master.getName()+"'>"+master.getName()+"</option>");
					}
				}else{
					str.append("<option value='"+master.getName()+"'>"+master.getName()+"</option>");
				}
			}
			str.append("</select>");
			str.append("</td>");
			//str.append("<td>"+priscription.getPriscdose()+"</td/>");
			/*str.append("<td>"+priscription.getPriscdays()+" "+priscription.getPriscdurationtype()+"</td/>");*/
			str.append("<td><input type='number' class='form-control' name='dicpriscdays"+priscription.getId()+"' value='"+priscription.getPriscdays()+"'></td>");
			str.append("<td>"+priscription.getDosenotes()+"</td/>");
			str.append("<td>"+priscription.getStrengthnew()+"</td/>");
			str.append("<td>"+priscription.getPriscindivisualremark()+"</td/>");
			str.append("<td><a onclick='removeMedicineDisc(this,"+priscription.getId()+")' ><i class='fa fa-trash'></i></a></td>");
			str.append("</tr>");
			i++;
		}
		str.append("<input type='hidden' name='totalchildmedids' value='"+totalchildmedids+"'>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+str.toString()+"");
		 
	 }catch (Exception e) {
		e.printStackTrace();
	}
	 finally{
			connection.close();
		}
	 return null;
}

public String discdata()throws Exception{
	 
	 Connection connection = null;
	 
	 try{
		 connection = Connection_provider.getconnection();
		 IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		 EmrDAO emrDAO = new JDBCEmrDAO(connection);
		 String sessionadmissionid = (String)session.getAttribute("sessionadmissionid");
		 String treatmentgivensatus=DateTimeUtils.isNull(request.getParameter("treatmentgivensatus"));
		 ArrayList<Priscription>dischargePriscList = new ArrayList<Priscription>();
		if(treatmentgivensatus.equals("1")){
			dischargePriscList=ipdDAO.getTreatmentGivenDischargePrescList(sessionadmissionid);
		}else {
			dischargePriscList=ipdDAO.getDischargePrescList(sessionadmissionid);
		}
		
		ArrayList<Master> dosageList = emrDAO.getDosageList();
		
		int size = dischargePriscList.size();
		String totalchildmedids ="0";
		if (size > 0) {
			totalchildmedids = dischargePriscList.get(size - 1).getTotalchildmedids();
		}
		
		int i = 0;
		StringBuffer str = new StringBuffer();
		for(Priscription priscription : dischargePriscList){
			str.append("<tr>");
			str.append("<td><input type='number' class='form-control' name='dicpriscmed"+priscription.getId()+"' value='"+priscription.getDispriscsrno()+"'></td>");
			str.append("<td>"+priscription.getMdicinenametxt()+"</td/>");
			//  05 June 2018
			str.append("<td>");
			str.append("<select id='discpriscdose"+priscription.getId()+"' name='discpriscdose"+priscription.getId()+"' class='form-control chosen-select'>");
			for (Master master : dosageList) {
				if(priscription.getPriscdose()!=null){
					if(master.getName()!=null){
						if(master.getName().equals(priscription.getPriscdose())){
							str.append("<option value='"+master.getName()+"' selected='selected'>"+master.getName()+"</option>");
						}else{
							str.append("<option value='"+master.getName()+"'>"+master.getName()+"</option>");
						}
					}else{
						str.append("<option value='"+master.getName()+"'>"+master.getName()+"</option>");
					}
				}else{
					str.append("<option value='"+master.getName()+"'>"+master.getName()+"</option>");
				}
			}
			str.append("</select>");
			str.append("</td>");
			//str.append("<td>"+priscription.getPriscdose()+"</td/>");
			/*str.append("<td>"+priscription.getPriscdays()+" "+priscription.getPriscdurationtype()+"</td/>");*/
			str.append("<td><input type='number' class='form-control' name='dicpriscdays"+priscription.getId()+"' value='"+priscription.getPriscdays()+"'></td>");
			
			str.append("<td>"+priscription.getDosenotes()+"</td/>");
			str.append("<td>"+priscription.getStrength()+"</td/>");
			if(!treatmentgivensatus.equals("1")){
				str.append("<td><a onclick='removeMedicineDisc(this,"+priscription.getId()+")' ><i class='fa fa-trash'></i></a></td>");
			}else{
				str.append("<td><a onclick='removeMedicineDisc1(this,"+priscription.getId()+")' ><i class='fa fa-trash'></i></a></td>");
			}
			
			str.append("</tr>");
			i++;
		}
		str.append("<input type='hidden' name='totalchildmedids' value='"+totalchildmedids+"'>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+str.toString()+"");
		 
	 }catch (Exception e) {
		e.printStackTrace();
	}
	 finally{
			connection.close();
		}
	 return null;
}

//  12 July 2018

public String chargeopdot() throws Exception{
	
	String apmtType = request.getParameter("apmtType");
	String clientId = request.getParameter("clientId");
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
		double charge = notAvailableSlotDAO.getCharge(apmtType);
		double opdregcharge =0;
		//  10 July 2018 /to set registration charge
		boolean isopd = completeAptmDAO.isclientIdInApmtBefore(clientId);
		boolean isipd=completeAptmDAO.isOPDFirstCharge(clientId, "");
		//new condition for opd reg charge
		/*boolean isundernewcond=false;
		if(todayspatient&&!flag){
			isundernewcond=true;
		}
		if(!flag||todayspatient){
			opdregcharge =  completeAptmDAO.getOpdRegCharge();
		}*/
		if(isopd){
			opdregcharge =  completeAptmDAO.getOpdRegCharge();
		}
		if(isipd){
			opdregcharge = 0;
		}
		
		String data = charge +"~"+opdregcharge+"~"+loginInfo.isOpd_tp_zero_invoice();
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+data+""); 
		
	}catch (Exception e) {
		// TODO: handle exception
	}finally{
		
		connection.close();
	}
	
	return null;
}

//  21 Julyn2018
public String getvendorstateforlongpo() throws Exception{
	  Connection connection= null;
		 try {
			String procurementid= request.getParameter("procurementid");
			connection= Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			int result= procurementDAO.getvendorstateforlongpo(procurementid);
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+result+"");

		} catch (Exception e) {
			e.printStackTrace();
		}
		 return null;
}

public String getstate() throws Exception{

	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(loginInfo.getUserId());
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(priscription.getState());
	} catch (Exception e) {

		e.printStackTrace();
	}
	finally {
		connection.close();
	}

	return null;
}

public String getclientweight() throws Exception{

	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		String clientid = request.getParameter("clientid");
		
		String weight = clientDAO.getClientWeight(clientid);
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+weight+"");
	} catch (Exception e) {

		e.printStackTrace();
	}
	finally {
		connection.close();
	}

	return null;
}

public String addtonursereturn() throws Exception{
    
    Connection connection=null;
    try {
     connection=Connection_provider.getconnection();
     InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
     PharmacyDAO pharmacyDAO= new JDBCPharmacyDAO(connection);
     String chargeid=request.getParameter("chargeid");
     String tcount=request.getParameter("count");
     String qty=request.getParameter("qty");
     Priscription priscription= pharmacyDAO.getMedicineChargesbyid(Integer.parseInt(chargeid),0);
     Product product=inventoryProductDAO.getProduct(priscription.getProduct_id());
     String medicine_shedule= inventoryProductDAO.getMedicineShedule(product.getCatalogueid());
     chargeid = DateTimeUtils.numberCheck(chargeid);
     int returnqty= pharmacyDAO.getReturnQtyofCharge(chargeid); 
     int returnqty0 = pharmacyDAO.getNurseReturnQty(Integer.parseInt(chargeid));
     returnqty = returnqty + returnqty0;
     if(product.getId()>0){
      
     String ex=product.getExpiry_date();
     String expiry=DateTimeUtils.converToMMMYYYYforExp(ex);
     int count=Integer.parseInt(tcount);
     String color="#777";
     if(medicine_shedule.equals("Regular")) {
         color="#777";
     } else if(medicine_shedule.equals("Narcotics")){
         color="#e05d6f";
     } else if(medicine_shedule.equals("H1")){
         color="#e69522";
     }else if(medicine_shedule.equals("High Risk Medicine")){
    	 color="#9381cc";
     }else if(medicine_shedule.equals("Vaccination")){
    	 color="#e0acdafc";
     }
     returnqty =priscription.getSaleqty()- returnqty; 
     int sr=count+1;
     StringBuffer buffer=new StringBuffer();
     buffer.append("<td>"+sr+"</td>");
     buffer.append("<td style=color:"+color+" '>"+product.getProduct_name()+" <input type='hidden' class='pclass' value='"+count+"' /> <input type='hidden' name='medicines["+count+"].id' id='id"+count+"'  value='"+chargeid+"' /> <input type='hidden' name='medicines["+count+"].product_id' id='product_id"+count+"' value='"+product.getId()+"' /> </td>");
     buffer.append("<td id='labelreq"+count+"'>"+priscription.getSaleqty()+"</td> <input type='hidden' name='medicines["+count+"].tempsale' value='"+returnqty+"' id='tempsale"+count+"' /> <input type='hidden' name='medicines["+count+"].productname' value='"+product.getProduct_name()+"' />");
    /* buffer.append("<td> "+product.getHsnno()+" </td>");*/
     /*buffer.append("<td>"+returnqty+"/"+product.getBatch_no()+"/"+expiry+" <input type='hidden' id='pur_price"+count+"' value='"+product.getPurchase_price()+"' /> <input type='hidden' value='"+product.getPack()+"' id='pack"+count+"'/> <input type='hidden' name='medicines["+count+"].billno' id='billno"+count+"' value='"+priscription.getBillno()+"' /> </td>");*/
     buffer.append("<input type='hidden' id='pur_price"+count+"' value='"+product.getPurchase_price()+"' /> <input type='hidden' value='"+product.getPack()+"' id='pack"+count+"'/> <input type='hidden' name='medicines["+count+"].billno' id='billno"+count+"' value='"+priscription.getBillno()+"' /> ");
     
     buffer.append("<td>"+returnqty+"</td>");
     buffer.append("<td>"+product.getBatch_no()+"</td>");
     buffer.append("<td>"+expiry+"</td>");
     buffer.append("<td><input type='number' name='medicines["+count+"].returnqty' onchange='validatePharmacyNurseBill()'  id='returnqty"+count+"' value='"+qty+"' class='form-control' style='height: 20px !important;background-color: rgba(255, 225, 225, 0.59);'></td>");
     if(loginInfo.isPurchase_edit_pharmacy()){
   	  	buffer.append("<td class='hidden'><input type='number' value='"+product.getSale_price()+"' name='medicines["+count+"].sale_price'  id='mrp"+count+"' class='form-control' style='height: 20px !important;text-align: right;background-color: rgba(255, 225, 225, 0.59);' readonly/> <input type='hidden' value='"+product.getSale_price()+"' id='sale_price"+count+"' /> </td>");
     }else{
   	  	buffer.append("<td class='hidden'><input type='number' value='"+product.getSale_price()+"' name='medicines["+count+"].sale_price'  id='mrp"+count+"' class='form-control' style='height: 20px !important;text-align: right;background-color: rgba(255, 225, 225, 0.59);' readonly/> <input type='hidden' value='"+product.getSale_price()+"' id='sale_price"+count+"' /> </td>");  
     }
     buffer.append("<td style='text-align: center;text-transform: uppercase;' class='hidden'>"+product.getShelf()+" <input type='hidden' name='medicines["+count+"].vat' id='vat"+count+"' value='"+product.getVat()+"'> </td>");
     buffer.append("<td  class='text-right hidden'>Rs.<label id='totalmrp"+count+"'>00.00</label> ");
     buffer.append("<input type='hidden' id='prodd"+count+"' value='"+product.getStock()+"'/>");
     buffer.append("<input type='hidden' id='discper"+count+"' value='"+priscription.getDiscount()+"'/>");
     buffer.append("</td>");
    double stock=Double.parseDouble(product.getStock());
     if(stock>100){
      buffer.append("<td class='text-center' id='tdbutton"+count+"'><a href='#' onclick=deleteRowtempNurse(this)><i class='fa fa-minus-circle' style='color:#c50404;font-size: 17px;padding-top: 2px;' aria-hidden='true'></i></a></td>");
     } else {
      buffer.append("<td class='text-center'><a href='#' onclick=deleteRowtempNurse(this)><i class='fa fa-minus-circle' style='color:#c50404;font-size: 17px;padding-top: 2px;' aria-hidden='true'></i></a></td><td class='text-center hidden'><a href='#' onclick='requestStock("+product.getId()+","+count+")' title='Request Stock'><i class='fa fa-cart-plus' aria-hidden='true' style='font-size: 17px;padding-top: 2px;'></i></a></td>");
     }
     buffer.append("<input type='hidden' id='totalrefundrs"+count+"' name='medicines["+count+"].totalrefundrs'  />");
     buffer.append("<input type='hidden' id='tmedicineid"+count+"' name='medicines["+count+"].mdicinenameid' value='"+product.getMedicinenameid()+"' />");
     
     response.setContentType("text/html");
     response.setHeader("Cache-Control", "no-cache");
     response.getWriter().write(""+buffer.toString()+""); 
     
     } else {
      response.setContentType("text/html");
      response.setHeader("Cache-Control", "no-cache");
      response.getWriter().write("0"); 
     }
     
    } catch (Exception e) {

     e.printStackTrace();
    }
    finally {
     connection.close();
    }
    
    return null;
   }
public String deletediscount(){
	Connection connection= null;
	String id= request.getParameter("id");
	String reason= request.getParameter("reason");
	String invid=request.getParameter("invid");
	String userid= loginInfo.getUserId();
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Calendar cal = Calendar.getInstance();
    String date = dateFormat.format(cal.getTime());  
	
	try {
		connection = Connection_provider.getconnection();
		AppointmentDAO appointmentDAO= new JDBCAppointmentDAO(connection);
		appointmentDAO.deletediscountfromdashboard(id, userid, date, reason,invid);
		
		 response.setContentType("text/html");
	      response.setHeader("Cache-Control", "no-cache");
	      response.getWriter().write("0"); 
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String truncate() throws Exception{
	
	Connection connection = null;
	try {

		connection = Connection_provider.getconnection();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		
		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);

		int result = completeAptmDAO.deleteComplteApmt(loginInfo.getId());
	}
	catch(Exception e){
		e.printStackTrace();
	}finally{
		
		connection.close();
	}
	return null;
}
public String getpriscmedicinelist(){

	if (!verifyLogin(request)) {
		return "login";
	}
	Connection connection= null;
	try{
		connection =Connection_provider.getconnection();
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		InventoryProductDAO inventoryProductDAO= new JDBCInventoryProductDAO(connection);
		PrescriptionMasterDAO prescriptionMasterDAO= new JDBCPrescriptionMasterDAO(connection);
		/*
		 * //diagnosis ArrayList<Diagnosis>diagnosisList=masterDAO.getDiagnosisList();
		 * 
		 * //add to medicine master
		 * 
		 * StringBuffer buffer2 =new StringBuffer(); buffer2.
		 * append("<select class='form-control chosen' id='diagnosisname' name='diagnosisname' onchange='getmedondiagnosis(this.value)' > "
		 * ); buffer2.append("<option value='0'>Select Diagnosis</option>");
		 * for(Diagnosis diagnosis:diagnosisList) {
		 * buffer2.append("<option value='"+diagnosis.getId()+"'> "+diagnosis.getName()
		 * +" </option>"); } buffer2.append("</select>");
		 */
		
		ArrayList<Master>medicineList = masterDAO.getMedicineList();
		StringBuffer buffer =new StringBuffer();
		if(loginInfo.isAdd_medicine() || loginInfo.getUserType()==2){
			buffer.append("<a title='Add New Medicine' href='#' type='button' class='' onclick=openhiddendiv('hiddendiv') style='margin-right: 5px;'><i class='fa fa-pencil fa-2x cstm-icon' aria-hidden='true'></i></a>");
		} else{
			buffer.append("<a title='Add New Medicine' href='#' type='button' class=''  style='margin-right: 5px;'><i class='fa fa-pencil fa-2x cstm-icon' aria-hidden='true'></i></a>");
		} 
		buffer.append("<select class='form-control chosen' id='mdicinename' name='mdicinename' onchange='getDoseNote(this.value)' > ");
		buffer.append("<option value='0'>Select Medicine</option>");
		for(Master master:medicineList){
			buffer.append("<option value='"+master.getId()+"'> "+master.getGenericname()+" </option>");
		}
		buffer.append("</select>");
		
		/*if(loginInfo.isAdd_medicine() || loginInfo.getUserType()==2){
			buffer.append("&nbsp;<div><a href='#'><i class='fa fa-plus' style='width:10px;' onclick=openBlankPopup('addPrescriptiondetails')></i></a></div>");
		} else{
			buffer.append("&nbsp;<div><a href='#'><i class='fa fa-plus' style='width:10px;' ></i></a></div>");
		} */
		
		
		String responsej=	buffer.toString();
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("responsej", responsej);
			
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);

		
		
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return null;
}


public String delete() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	String selectedid = request.getParameter("selectedid");
	String cancelApmtNote = request.getParameter("cancelApmtNote");
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
		ClinicDAO clinicDAO=new JDBCClinicDAO(connection);
		
		String opendb = (String)session.getAttribute("openedb");
		int status = notAvailableSlotDAO.getStatus(selectedid,opendb);
/*		if(status==0){
			int treatmeId = notAvailableSlotDAO.getSelecedTreatmentEpisodeId(Integer.parseInt(selectedid));
			int usedSession = notAvailableSlotDAO.getUsedSession(treatmeId,selectedid);
			ArrayList<NotAvailableSlot>usedSessionList = new ArrayList<NotAvailableSlot>();
			usedSessionList = notAvailableSlotDAO.getUsedSessionList(treatmeId,usedSession);
			
			
			for(NotAvailableSlot n:usedSessionList){
				int updateAllPrevious = notAvailableSlotDAO.updateAllPrevious(n.getId(),treatmeId);
			}
			int updatesession = notAvailableSlotDAO.updateSessions(treatmeId);
		}*/
		NotAvailableSlot n = notAvailableSlotDAO.getApmtDetailsForLog(Integer.parseInt(selectedid));
		
		String commencingTemp = n.getCommencing();
		
		String apmtstatus = "Cancelled";
		
		
		
		  //send sms
	    ClientDAO clientDAO = new JDBCClientDAO(connection);
	    NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getApmtDetailsForLog(Integer.parseInt(selectedid));
	    UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
	    Client client = clientDAO.getClientDetails(notAvailableSlot.getClientId());
	    UserProfile userProfile  = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());
	   
	   
		 
	    int otid = notAvailableSlotDAO.checkotAppointment(selectedid);
	    if(otid>0){
	    	int result = notAvailableSlotDAO.deleteOtApmt(otid);
	    }else{
	    	//if(opendb.equals("opd")){
	    		int logsave = notAvailableSlotDAO.saveCancelApmtInLog(Integer.parseInt(selectedid),DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),time,loginInfo.getUserId(),n.getClientId(),commencingTemp,n.getSTime(),apmtstatus,cancelApmtNote,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),n);
		    	
		    	int result = notAvailableSlotDAO.DeleteBlockedSlot(selectedid,opendb);
		    	 //String message = AllTemplateAction.getDeletedAppointmentSMSText(notAvailableSlot.getClientId(), Integer.parseInt(selectedid), connection, loginInfo);
		 	    String message = "Appointment has cancelled";
		 	    if(loginInfo.getCountry().equals("India")){
		 	    	SmsService s = new SmsService();
		 	    	if(loginInfo.isSms_cancel_appointment()){
		 	    	if(status==0){
		 	    		String templateid="";
		 	    	 s.sendSms(message, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
		 	    	boolean isSMSActive=clinicDAO.isSmsActive(loginInfo.getId());
		 	    	if(isSMSActive){
		 				s.sendSms(message, userProfile.getMobile(), loginInfo, new EmailLetterLog(),templateid);
		 			}
		 	    	  
		 	    	}
		 	    }
		 	    }
		 	    
		 	    String to = client.getEmail();
				String cc = "";
				String subject = "Appoitment cancelled";
				String notes = "Dear "+n.getClientName()+", your appointment sheduled with "+n.getDiaryUser()+" has been cancellled.";
				EmailLetterLog emailLetterLog=new EmailLetterLog();
				EmbeddedImageEmailUtil.sendMail(connection, loginInfo.getId(), to, cc, subject, notes,loginInfo,emailLetterLog);
	    	//}
	    	
	    }
		
		
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
	    response.getWriter().write("0"); 
		
	}catch(Exception e){
		//log.debug("sendsms"+e.getMessage());
		//e.printStackTrace();
	}finally{
		
		connection.close();
	}
	
	
	return null;

}

public String saveopddiagnosis(){
	try {
	Connection connection=Connection_provider.getconnection();	
	String opdid=request.getParameter("opdid");
	String diagnosisid=request.getParameter("diagnosisid");
	NotAvailableSlotDAO notAvailableSlotDAO= new JDBCNotAvailableSlotDAO(connection);
	int result=notAvailableSlotDAO.saveDiagnosisOpd(opdid, diagnosisid);
	response.setContentType("text/html");
    response.setHeader("Cache-Control", "no-cache");
    response.getWriter().write("0"); 
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}


public String getbmidata(){
	try {
		Connection connection=Connection_provider.getconnection();
		String clientid=request.getParameter("clientid");
		String appointmentid=request.getParameter("apmtid");
		ClientDAO clientDAO= new JDBCClientDAO(connection);
		Client client=clientDAO.getBMIData(clientid,appointmentid);
		NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
		NotAvailableSlot notAvailableSlot=notAvailableSlotDAO.getAvailableSlotdata(Integer.parseInt(appointmentid));
		Client client1=clientDAO.getClientDetails(clientid);
		DiaryManagementDAO diaryManagementDAO=new JDBCDiaryManagentDAO(connection);
		UserProfile userProfile=diaryManagementDAO.getUserProfile(notAvailableSlot.getDiaryUserId());
		String responnse="";
		String spliter="$$~$$";
		responnse=client.getHeight()+spliter+client.getWeight()+spliter+client.getBmi()+spliter+client.getPulse()+spliter+client.getSysbp();
		responnse=responnse+spliter+client.getDiabp()+spliter+client.getSugarfasting()+spliter+client.getPostmeal()+spliter+DateTimeUtils.isNull(client.getTemprature())+spliter+DateTimeUtils.isNull(client.getSpo())+spliter+DateTimeUtils.isNull(client.getHead_cir());
		responnse=responnse+spliter+client1.getFullname();
		responnse=responnse+spliter+notAvailableSlot.getsTime();
		responnse=responnse+spliter+notAvailableSlot.getDuration();
		responnse=responnse+spliter+client1.getWhopay();
		responnse=responnse+spliter+userProfile.getFullname();
		responnse=responnse+spliter+userProfile.getDiciplineName();
		responnse=responnse+spliter+notAvailableSlot.getApmttypetext();
		responnse=responnse+spliter+notAvailableSlot.getCommencing();
		responnse=responnse+spliter+notAvailableSlot.isDna();
		responnse=responnse+spliter+notAvailableSlot.getOpdpmnt();
		responnse=responnse+spliter+notAvailableSlot.getDiaryUserId();
		responnse=responnse+spliter+notAvailableSlot.getProcedure();
		responnse=responnse+spliter+DateTimeUtils.isNull(client.getLmpd());
		responnse=responnse+spliter+client1.getAbrivationid();
		response.setContentType("text/html");
	    response.setHeader("Cache-Control", "no-cache");
	    response.getWriter().write(""+responnse); 
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return null;
}

public String getchildgrowthdataofmonth() throws Exception {
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		
		String clientId = request.getParameter("clientId");
		String val = request.getParameter("val");
		String month = request.getParameter("month");
		Client client = clientDAO.getChildGrowthData(clientId,val,month);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+client.getCgddata()+"~~~"+val);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}
	return null;
}

public String savebmi() throws Exception {
	
	Connection connection=null; 
	try {
		connection=Connection_provider.getconnection();
//		/ var url="savebmiNotAvailableSlot?height="+height+"&weight="+weight+"&bmi="+bmi+"&pulse="+pulse+"&sysbp="+sysbp+"&diabp="+diabp+"";
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		String height=request.getParameter("height");
		String weight=request.getParameter("weight");
		String bmi=request.getParameter("bmi");
		String pulse=request.getParameter("pulse");
		String sysbp=request.getParameter("sysbp");
		String diabp=request.getParameter("diabp");
		String patientId=request.getParameter("patientId");		
		String sugarfasting=request.getParameter("sugarfasting");
		String postmeal=request.getParameter("postmeal");	
		String temprature=request.getParameter("temprature");
		String spo=request.getParameter("spo");
		String head_cir=request.getParameter("bsa");
		String appointmentid=request.getParameter("apmtid");
		String lmpd=request.getParameter("lmpd");
		String date=request.getParameter("date");
		Client client=new Client();
		client.setHeight(height);
		client.setWeight(weight);
		client.setBmi(bmi);
		client.setPulse(pulse);
		client.setSysbp(sysbp);
		client.setDiabp(diabp);
		client.setClientid(patientId);
		client.setSugarfasting(sugarfasting);
		client.setPostmeal(postmeal);
		client.setTemprature(temprature);
		client.setSpo(spo);
		client.setHead_cir(head_cir);
		client.setAppointmentid(appointmentid);
		client.setLmpd(lmpd);
		String datetime5 = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		String commencing=clientDAO.getappointmentdate(appointmentid);
		String ipdid=clientDAO.getIpdid(patientId);
		
		
		if(loginInfo.isBams1()) {
			if(appointmentid==null) {
				date = DateTimeUtils.getCommencingDate1(date);
				date=date+" "+datetime5.split(" ")[1];
				client.setDate(date);
			}else {
				commencing=commencing+" "+datetime5.split(" ")[1];
				client.setDate(commencing);
			}
			
		}else {
			client.setDate(datetime5);
		}
		
		//client.setDate(datetime5);
		client.setVital_fake_date(date);
		client.setIpdid(ipdid);
		int result=clientDAO.saveBMIPatient(client);
		
		Client client2 = clientDAO.getClientDetails(patientId);
		int ageinmonth = DateTimeUtils.getmonthsfromdob(client2.getDob());
		if(ageinmonth<=60 && ageinmonth>=0){
			if(height!=null){
				if(!height.equals("") && !height.equals("0")){
					Client client4 = new Client();
					client4.setHeightdata(height);
					client4.setWeightdata(weight);
					client4.setBmidata(bmi);
					client4.setHeadcircumferncedata(head_cir);
					client4.setClientid(patientId);
					client4.setMonth(""+ageinmonth);
					client4.setUserid(loginInfo.getUserId());
					String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					client4.setDate(datetime);
					int id = clientDAO.check_child_growth_data(patientId, ""+ageinmonth);
					int res1 = 0;
					if (id > 0) {
						client4.setId(id);
						// update
						res1 = clientDAO.updateChildGrowthData(client4, "height");
					} else {
						// insert
						res1 = clientDAO.saveChildGrowthData(client4, "height");
					}
				}
			}
			if(weight!=null){
				if(!weight.equals("") && !weight.equals("0")){
					Client client4 = new Client();
					client4.setHeightdata(height);
					client4.setWeightdata(weight);
					client4.setBmidata(bmi);
					client4.setHeadcircumferncedata(head_cir);
					client4.setClientid(patientId);
					client4.setMonth(""+ageinmonth);
					client4.setUserid(loginInfo.getUserId());
					String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					client4.setDate(datetime);
					int id = clientDAO.check_child_growth_data(patientId, ""+ageinmonth);
					int res1 = 0;
					if (id > 0) {
						client4.setId(id);
						// update
						res1 = clientDAO.updateChildGrowthData(client4, "weight");
					} else {
						// insert
						res1 = clientDAO.saveChildGrowthData(client4, "weight");
					}
				}
			}
			if(bmi!=null){
				if(!bmi.equals("") && !bmi.equals("0")){
					Client client4 = new Client();
					client4.setHeightdata(height);
					client4.setWeightdata(weight);
					client4.setBmidata(bmi);
					client4.setHeadcircumferncedata(head_cir);
					client4.setClientid(patientId);
					client4.setMonth(""+ageinmonth);
					client4.setUserid(loginInfo.getUserId());
					String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					client4.setDate(datetime);
					int id = clientDAO.check_child_growth_data(patientId, ""+ageinmonth);
					int res1 = 0;
					if (id > 0) {
						client4.setId(id);
						// update
						res1 = clientDAO.updateChildGrowthData(client4, "bmi");
					} else {
						// insert
						res1 = clientDAO.saveChildGrowthData(client4, "bmi");
					}
				}
			}
			if(head_cir!=null){
				if(!head_cir.equals("") && !head_cir.equals("0")){
					Client client4 = new Client();
					client4.setHeightdata(height);
					client4.setWeightdata(weight);
					client4.setBmidata(bmi);
					client4.setHeadcircumferncedata(head_cir);
					client4.setClientid(patientId);
					client4.setMonth(""+ageinmonth);
					client4.setUserid(loginInfo.getUserId());
					String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					client4.setDate(datetime);
					int id = clientDAO.check_child_growth_data(patientId, ""+ageinmonth);
					int res1 = 0;
					if (id > 0) {
						client4.setId(id);
						// update
						res1 = clientDAO.updateChildGrowthData(client4, "headcircumfernce");
					} else {
						// insert
						res1 = clientDAO.saveChildGrowthData(client4, "headcircumfernce");
					}
				}
			}
		}
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""); 
		
	} catch (Exception e) {

		e.printStackTrace();
	}finally{
		
		connection.close();
	}
	 
	 return null;
 }


public String priscrequest() throws Exception{
	if (!verifyLogin(request)) {
		return "login";
	}
	Connection connection= null;
	try{
		connection =Connection_provider.getconnection();
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		EmrDAO emrDAO = new JDBCEmrDAO(connection);
		//MasterDAO masterDAO = new JDBCMasterDAO(connection);
		String parentid= jsonObject.getString("parentid");
		
		String date = "";
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		date = dateFormat.format(cal.getTime());
		
		//ArrayList<Master>medicineList = masterDAO.getMedicineList();
		StringBuffer buffer =new StringBuffer();
		
		//Priscription priscription = emrDAO.getPriscriptionParentData(Integer.parseInt(parentid));
		ArrayList<Priscription> arrayList= emrDAO.getPriscriptionChildData(parentid,0);
		int size = arrayList.size();
		if (size > 0){
			String totalid = arrayList.get(size - 1).getTotalid();
			String medid = arrayList.get(size - 1).getMedid();
			buffer.append("<input type='hidden' id='totalid_request' name='totalid' value='"+totalid+"'>");
			buffer.append("<input type='hidden' id='medid_request' name='medid' value='"+medid+"'>");
		}else{
			buffer.append("<input type='hidden' id='totalid_request' name='totalid' value='"+0+"'>");
			buffer.append("<input type='hidden' id='medid_request' name='medid' value='"+0+"'>");
		}
		int i=0;
		buffer.append("<input type='hidden' id='priscstatusparentid_request' name='parentid' value='"+parentid+"'>");
		for (Priscription priscription2 : arrayList) {
			buffer.append("<tr>");
			buffer.append("<input type='hidden' class='akashpriscrequest_request' value='"+priscription2.getId()+"'>");
			buffer.append("<input type='hidden' name='mdname"+priscription2.getId()+"' value='"+priscription2.getMdicinenametxt()+"'>");
			buffer.append("<input type='hidden' name='mdid"+priscription2.getId()+"' value='"+priscription2.getMdicinenameid()+"'>");
			buffer.append("<input type='hidden' name='childid"+priscription2.getId()+"' value='"+priscription2.getId()+"'>");
			if(priscription2.getDeliver_statuss()==0){
				buffer.append("<td><input type='checkbox' class='akashcase_request' value='"+priscription2.getId()+"'  /></td>");
			}else{
				buffer.append("<td><input type='checkbox' disabled='disabled' checked='true' value='"+priscription2.getId()+"'  /></td>");
			}
			
			buffer.append("<td>"+(++i)+"</td>");
			buffer.append("<td>"+priscription2.getMdicinenametxt()+"</td>");
			if(priscription2.getDosage()!=null){
				buffer.append("<td>"+priscription2.getDosage()+"</td>");
			}else{
				buffer.append("<td></td>");
			}
			buffer.append("<td>"+priscription2.getPriscdays()+"</td>");
			buffer.append("<td>"+priscription2.getReq_qtys()+"</td>");
			/*buffer.append("<td><input type='number' class='form-control' id='mdrequestqty"+priscription2.getId()+"_request' name='mdrequestqty"+priscription2.getId()+"'></td>");*/
			buffer.append("</tr>");
		}
		
		String responsej=	buffer.toString();
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("responsej", responsej);
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return null;
}

public String priscrequestforreturn() throws Exception{
	if (!verifyLogin(request)) {
		return "login";
	}
	Connection connection= null;
	try{
		connection =Connection_provider.getconnection();
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		EmrDAO emrDAO = new JDBCEmrDAO(connection);
		PrescriptionDAO prescriptionDAO = new JDBCPrescriptionDAO(connection);
		//MasterDAO masterDAO = new JDBCMasterDAO(connection);
		String parentid= jsonObject.getString("parentid");
		
		String date = "";
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		date = dateFormat.format(cal.getTime());
		
		StringBuffer buffer =new StringBuffer();
		int isforreturn =1;
		ArrayList<Priscription> arrayList= emrDAO.getPriscriptionChildData(parentid,isforreturn);
		int size = arrayList.size();
		if (size > 0){
			String totalid = arrayList.get(size - 1).getTotalid();
			String medid = arrayList.get(size - 1).getMedid();
			buffer.append("<input type='hidden' id='totalid_return' name='totalid' value='"+totalid+"'>");
			buffer.append("<input type='hidden' id='medid_return' name='medid' value='"+medid+"'>");
		}else{
			buffer.append("<input type='hidden' id='totalid_return' name='totalid' value='"+0+"'>");
			buffer.append("<input type='hidden' id='medid_return' name='medid' value='"+0+"'>");
		}
		int i=0;
		buffer.append("<input type='hidden' id='priscstatusparentid_return' name='parentid' value='"+parentid+"'>");
		for (Priscription priscription2 : arrayList) {
			int returnqty =  prescriptionDAO.getReturnQtyAgainstPrisc(priscription2.getId());
			int reqqty = priscription2.getReq_qtys();
			int availableqty = reqqty - returnqty;
			buffer.append("<tr>");
			buffer.append("<input type='hidden' class='akashpriscrequest_return' value='"+priscription2.getId()+"'>");
			buffer.append("<input type='hidden' name='mdname"+priscription2.getId()+"' value='"+priscription2.getMdicinenametxt()+"'>");
			buffer.append("<input type='hidden' name='mdid"+priscription2.getId()+"' value='"+priscription2.getMdicinenameid()+"'>");
			buffer.append("<input type='hidden' name='childid"+priscription2.getId()+"' value='"+priscription2.getId()+"'>");
			buffer.append("<input type='hidden' id='availaberetrunqty"+priscription2.getId()+"' value='"+availableqty+"'>");
			buffer.append("<td><input type='checkbox' class='akashcase_return' value='"+priscription2.getId()+"'  /></td>");
			buffer.append("<td>"+(++i)+"</td>");
			buffer.append("<td>"+priscription2.getMdicinenametxt()+"</td>");
			if(priscription2.getDosage()!=null){
				buffer.append("<td>"+priscription2.getDosage()+"</td>");
			}else{
				buffer.append("<td></td>");
			}
			buffer.append("<td>"+priscription2.getPriscdays()+"</td>");
			buffer.append("<td>"+reqqty+"</td>");
			buffer.append("<td>"+availableqty+"</td>");
			buffer.append("<td><input type='number' class='form-control' id='mdrequestqty_return"+priscription2.getId()+"' name='mdrequestqty"+priscription2.getId()+"'></td>");
			buffer.append("</tr>");
		}
		
		String responsej=	buffer.toString();
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("responsej", responsej);
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return null;
}
public String getstdcharge() throws Exception {
	
	Connection connection=null;
     try {
      connection=Connection_provider.getconnection();
      ClientDAO clientDAO=new JDBCClientDAO(connection);
      AppointmentTypeDAO appointmentTypeDAO= new JDBCAppointmentTypeDAO(connection);
      String wardid= request.getParameter("wardid");
      String tpid= request.getParameter("tpid");
      String clientId= request.getParameter("clientId");
      String ipdid= request.getParameter("ipdid");
      String payee= request.getParameter("payee");
      
      if(payee==null){
    	  payee="Client";
      }
      if(payee.equals("")){
    	  payee="Client";
      }
      Client client=clientDAO.getClientDetails(clientId);
      String fullname= client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
      AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
      StringBuffer buffer=new StringBuffer();
      buffer.append(fullname);
      buffer.append("~");
  		if(payee.equals("Client") || payee.equals("Self")){
		 tpid="0";
  		}
  		tpid=DateTimeUtils.isNull(tpid);
  		
      ArrayList<Master> chargeList= appointmentTypeDAO.getStdOnoffChargeList(wardid, tpid,payee);
      IpdDAO ipdDAO = new JDBCIpdDAO(connection);
      ArrayList<String> wardlist=ipdDAO.getAllWardIdsOFPerson(ipdid);
      ArrayList<Master> otherwardList=new ArrayList<Master>();
      for(String ward:wardlist){
    	  if(!(wardid.equals(ward))){
    		  otherwardList.addAll(appointmentTypeDAO.getStdOnoffChargeListDiffWard(ward, tpid, payee, ipdid));
    	  }
      }
      
      
      chargeList.addAll(otherwardList);
      //@Rahul for autocharge charges not show on popup isiliye add kiye code
      String temptpid= ipdDAO.getFollowerTp(tpid); 
		if(temptpid!=null){
			
			if(!tpid.equals("0")){
				tpid=temptpid;  



			}
		}
		//End ho gya 
      for(Master master: chargeList){
    	  
    	 boolean isSelected= appointmentTypeDAO.isStdChargeSelected(ipdid, master.getId());
    	 boolean isexist=appointmentTypeDAO.isStdChargeExist(ipdid, master.getId());
    			 
    	 if(isexist||master.getTpid().equals(tpid)){
    		 
    	 }else{
    		 continue;
    	 }
    	 if(!(master.getTpid().equals("0")||master.getTpid().equals(""))){
    		 master.setPayby("(TP)");
    	 }
    	
    	 
    	 Accounts accounts= accountsDAO.showonofftime(master.getId(), ipdid);
    	 Accounts accounts2= new Accounts();
    	 if(accounts.getId()>0){
    		 accounts2 = accountsDAO.getStdChargeChildData(accounts.getId());
    	 }
    	
    	 String wardname="";
    	 String color="";
    	 if(master.isFromOldWard()){
    		 wardname="W :"+ipdDAO.getIpdWardName(master.getWardid());
    		 color="color:green;";
    	 }
    	
    	 String ondate=DateTimeUtils.isNull(accounts2.getOndatetime());
    	 /*if(ondate.contains(",")){
    		 for(String on:ondate.split(",")){
    			 ondate=on;
    		 }
    	 }*/
    	 
    	 String offdate=DateTimeUtils.isNull(accounts2.getOffdatetime());
    	 /*if(offdate.contains(",")){
    		 for(String off:offdate.split(",")){
    			 offdate=off;
    		 }
    	 }*/
    	  /*if(isSelected){
    		  buffer.append("<input type='checkbox' checked='checked'  class='scase' id='ch"+master.getId()+"' name='ch"+master.getId()+"' value='"+master.getId()+"'  />");
    	  } else {
    		  buffer.append("<input type='checkbox' class='scase' id='ch"+master.getId()+"' name='ch"+master.getId()+"' value='"+master.getId()+"'  />");
    	  }
    	  buffer.append(master.getName()+" <input type='button' class='btn updatebtn' value='Update' onclick='updateOnOffStd("+ipdid+","+master.getId()+")' /> <br>  ");
    	*/
    	buffer.append("<div class='form-group col-xs-12' style='"+color+"'>");
    	buffer.append("<label class='col-xs-4 control-label'>"+master.getName()+"   : "+DateTimeUtils.isNull(master.getPayby())+"  "+wardname+" "+"</label>");
    	buffer.append("<div class='col-xs-1 control-label'>");
    	buffer.append("<div class='onoffswitch dutch'>");
		if(isSelected){
    		  buffer.append("<input type='checkbox' checked='checked' id='ch"+master.getId()+"' onclick='updateOnOffStd("+ipdid+","+master.getId()+","+accounts2.getId()+",1,"+accounts.getId()+")' name='ch"+master.getId()+"' value='"+master.getId()+"' class='onoffswitch-checkbox scase'>");
    		  buffer.append("<label class='onoffswitch-label' for='ch"+master.getId()+"'>");
			  buffer.append("<span class='onoffswitch-inner'></span>");
			  buffer.append("<span class='onoffswitch-switch'></span>");
			  buffer.append("</label>");
		}else{
			  buffer.append("<input type='checkbox' id='ch"+master.getId()+"' onclick='updateOnOffStd("+ipdid+","+master.getId()+","+accounts2.getId()+",0,"+accounts.getId()+")' name='ch"+master.getId()+"' value='"+master.getId()+"' class='onoffswitch-checkbox scase'>");
    		  buffer.append("<label class='onoffswitch-label' for='ch"+master.getId()+"'>");
			  buffer.append("<span class='onoffswitch-inner'></span>");
			  buffer.append("<span class='onoffswitch-switch'></span>");
			  buffer.append("</label>");
		}
		
		buffer.append("</div>");
		buffer.append("</div>");
		boolean flag = true;
		if(ondate.equals("")){
			flag=false;
		}
		
		boolean flag1 = true;
		if(offdate.equals("")){
			flag1=false;
		}
		
		if(!flag){
			buffer.append("<span class='col-xs-3 control-label'>"+ondate+"</span>");
		}else{
			/*buffer.append("<span class='col-xs-3 control-label'>"+ondate+"</span>");*/
			if(!flag1){
				buffer.append("<span class='col-xs-3 control-label'>"+ondate+"<a href='#' style='color: #d9534f;' onclick='editdatetime("+ipdid+","+master.getId()+","+0+","+clientId+","+accounts2.getId()+")' > <i class='fa fa-edit' aria-hidden='true' style='color: #d9534f;'></i></a></span>");
			}else{
				 
				buffer.append("<span class='col-xs-3 control-label'>"+ondate+"<a href='#' style='color: #d9534f;' onclick='editdatetime("+ipdid+","+master.getId()+","+0+","+clientId+","+accounts2.getId()+")' > <i class='fa fa-edit' aria-hidden='true' style='color: #d9534f;'></i></a></span>");
			}
			
		}
		
		if(isSelected){
			buffer.append("<span class='col-xs-3 control-label'></span>");
		}else{
			if(!flag){
				buffer.append("<span class='col-xs-3 control-label'>"+offdate+"</span>");
			}else{
				
				/*buffer.append("<span class='col-xs-3 control-label'>"+offdate+"</span>");*/
				buffer.append("<span class='col-xs-3 control-label'>"+offdate+"<a href='#' style='color: #d9534f;' onclick='editdatetime("+ipdid+","+master.getId()+","+1+","+clientId+","+accounts2.getId()+")' > <i class='fa fa-edit' aria-hidden='true' style='color: #d9534f;'></i></a></span>");
			}
			
		}
		
		
		
		buffer.append("<input type='button' class='btn updatebtn col-xs-1' value='Info' style='margin-top: 0px;' onclick='getOnoffTime("+ipdid+","+master.getId()+")'/>  ");
		buffer.append("</div>");
		
		
		/*buffer.append(master.getName()+" <input type='button' class='btn updatebtn' value='Update' onclick='updateOnOffStd("+ipdid+","+master.getId()+")' /> <br>  ");*/
		
      }
      
    
      response.setContentType("text/html");
      response.setHeader("Cache-Control", "no-cache");
      response.getWriter().write(buffer.toString());
      
  } catch (Exception e) {
   e.printStackTrace();
  }finally{
		connection.close();
	}
     
     return null;
	
}

public String updatestdonoff()throws Exception{
	
	Connection connection=null;
	
	try {
		connection=Connection_provider.getconnection();
		
		AppointmentTypeDAO appointmentTypeDAO= new JDBCAppointmentTypeDAO(connection);
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		CompleteAptmDAO completeAptmDAO= new JDBCCompleteAptmDAO(connection);
		BedDao bedDao= new JDBCBedDao(connection);
		ClientDAO clientDAO= new JDBCClientDAO(connection);
		
		String chargeid= request.getParameter("chargeid");
		String ipdid= request.getParameter("ipdid");
		String status= request.getParameter("status");
		String stddate= request.getParameter("stddate");
		String starthour= request.getParameter("starthour");
		String startminute= request.getParameter("startminute");
		String enddate =request.getParameter("enddate");
		String endminute =request.getParameter("endminute");
		String endhour= request.getParameter("endhour");
		String childid =request.getParameter("childid");
		String previousstatus= request.getParameter("previousstatus");
		String parentid= request.getParameter("parentid");
		boolean stdbackdatechk = Boolean.parseBoolean(request.getParameter("stdbackdatechk"));
		
		if(stdbackdatechk){
			int r = appointmentTypeDAO.getStdChargeIdIdExists(ipdid,chargeid);
			if(r!=0){
				String curstatus = appointmentTypeDAO.getstddbcurstatus(r);
				if(curstatus.equals("1")){
					 response.setContentType("text/html");
				     response.setHeader("Cache-Control", "no-cache");
				     response.getWriter().write("0");
					return null;
				}
			}
		}
		/*String startTime = "";
		String endTime= "";
		String ondatetime= "";
		String offdatetime= "";
		if(!stdbackdatechk){
			startTime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
			endTime= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
			stddate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			stddate= DateTimeUtils.getCommencingDate1(stddate);
			enddate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			enddate= DateTimeUtils.getCommencingDate1(enddate);
			ondatetime= stddate+" "+startTime;
			offdatetime= enddate+" "+endTime;
		}else{
			startTime = starthour+":"+startminute+":"+"00";
			endTime= endhour+":"+endminute+":"+"00";
			ondatetime= stddate+" "+startTime;
			offdatetime= enddate+" "+endTime;
		}*/
		String startTime = starthour+":"+startminute+":"+"00";
		String endTime= endhour+":"+endminute+":"+"00";
		String ondatetime= stddate+" "+startTime;
		String offdatetime= enddate+" "+endTime;
		if(!stdbackdatechk){
			enddate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			endTime =DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
			enddate= DateTimeUtils.getCommencingDate1(enddate);
			offdatetime= enddate+" "+endTime;
		}
		
		if(stddate!=null){
			
			if(stddate.equals("")){
				 stddate= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			} else {
				stddate= DateTimeUtils.getCommencingDate1(stddate);
			}
		} else {
			stddate= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
		}
		
		if(enddate!=null){
			
			if(enddate.equals("")){
				enddate= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			} else {
				enddate= DateTimeUtils.getCommencingDate1(enddate);
			}
		} else {
			enddate= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
		}
		
		String nowDate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
		
		long diff= DateTimeUtils.getDifferenceOfTwoDateDBFormat(stddate, nowDate);
		int qty =(int) diff;
		if(qty<0){
			 qty=0;
		}
		
		if(qty==0){
			qty=1;
		} else {
			qty++;
		}
		
		String stdcharges="001";
		int invoiceid=0;
		
		String time= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
		
		String date1 = DateTimeUtils.getDateinSimpleFormate(new Date());
		String stemp[] = date1.split(" ");
		
		String temp[] = stemp[0].split("-");
		date1 = temp[2] + "-" + temp[1] + "-" + temp[0];
		int result=0;
		
		Bed bed= bedDao.getEditIpdData(ipdid);
		String clientid = bed.getClientid();
		Client client= clientDAO.getClientDetails(clientid);

		//creating invoice
			
			CompleteAppointment appointment=new CompleteAppointment();
			appointment.setClientId(clientid);
			appointment.setPractitionerId(bed.getPractitionerid());
			appointment.setChargeType("Charge");
			appointment.setLocation("1");
		    appointment.setAdditionalcharge_id(stdcharges);
		    appointment.setIpdid(Integer.parseInt(ipdid));
		    appointment.setInvoiceDate(date1);
		    appointment.setIpd("1");
		    appointment.setAppointmentid("0");
		    appointment.setWardid(bed.getWardid());
		    if(client.getWhopay()!=null){
		    	
		    	if(client.getWhopay().equals("Self") || client.getWhopay().equals("Client")){
		    	       
		    		appointment.setPolicyExcess("0");
		    		appointment.setPayBuy("0");
		    	} else {
		    		appointment.setPolicyExcess("1");
		    		appointment.setPayBuy("1");
		    	}
		    }
		    invoiceid= completeAptmDAO.getInvoiceforStandardCharges(ipdid,stdcharges);
		    if(invoiceid==0){
		    	invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId(),loginInfo.getUserId());
		    }
		    
		    String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
		    appointment.setUser(fullname);
		    appointment.setCommencing(date1);     
		    
		    Master master= appointmentTypeDAO.getMasterCharges(chargeid);
	    	   appointment.setApmtType(master.getName());
	    	   appointment.setCharges(master.getCharge());
	    	   appointment.setAdditionalcharge_id(chargeid);
	    	   appointment.setMasterchargetype(master.getMasterchargetype());
	    	   appointment.setStartTime(time);
	    	   
	    	   qty = DateTimeUtils.getDifferanceofDateWithTime(ondatetime, offdatetime, master.getChargehours());
	    	   if(qty==0){
	   			qty=1;
	    	   }
	    	   appointment.setQuantity(qty);
	    	   appointment.setBackDate(stddate);
	    	   appointment.setStdflag("1");
	    	   
	    	   result = appointmentTypeDAO.getStdChargeIdIdExists(ipdid,chargeid);
	    	   if(status.equals("0")){
	    		   if(result==0){
	    			   
		    	   } else {
		    		   if(!stdbackdatechk){
		    			   
		    			 /*  Accounts accounts = appointmentTypeDAO.getStdChargeDetails(result);
		    			   String temps[] = accounts.getOndatetime().split(",");
		    			   String lastdate = temps[temps.length-1];
		    			   String temp1[] = lastdate.split(" ");
		    			   diff= DateTimeUtils.getDifferenceOfTwoDateDBFormat(DateTimeUtils.getCommencingDate1(temp1[0]), enddate);
		    			   qty =(int) diff;
		    			   
		    				int assesmentid = appointmentTypeDAO.getstdAssesmentid(result);
		    				int dbqty = appointmentTypeDAO.getDbQuantity(assesmentid);
		    				 qty = qty + dbqty;
		    			     int upd = accountsDAO.updateChargeqty(Integer.toString(assesmentid),Integer.toString(qty));*/
		    			     
		    			     result= appointmentTypeDAO.updateStdCharge(result,"0","",offdatetime);
		    			   
		    		   }else{
		    			   String curstatus = appointmentTypeDAO.getstddbcurstatus(result);
		    			   if(curstatus.equals("0")){
		    				   Accounts accounts = appointmentTypeDAO.getStdChargeDetails(result);
		    				   offdatetime = accounts.getOffdatetime() + " , " + offdatetime;
		    				   ondatetime = accounts.getOndatetime() + " , " + ondatetime;
		    				   int upd = appointmentTypeDAO.updateStdChargeDateTime(result, ondatetime, offdatetime);
		    			   }
		    		   }
		    		  
		    	   }
	    		   
	    	   }else {
	    		   
	    		   int assesmentid= completeAptmDAO.getAssesmentIdforStdChargeIfExits1(ipdid,chargeid,invoiceid,master.getMasterchargetype());
		    	   if(assesmentid==0){
		    		   if(stdbackdatechk){
		    			     /*diff= DateTimeUtils.getDifferenceOfTwoDateDBFormat(stddate, enddate);
		    				 qty =(int) diff;*/
		    			     qty = DateTimeUtils.getDifferanceofDateWithTime(ondatetime, offdatetime, master.getChargehours());
		    				 appointment.setQuantity(qty);
		    				 
		    		   }
		    		   assesmentid=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
		    	   }
		    	   if(result==0){
		    		   if(!stdbackdatechk){
		    			   result= appointmentTypeDAO.saveStdCharge(ipdid,chargeid,assesmentid,"1",ondatetime,"");
		    		   }else{
		    			   result= appointmentTypeDAO.saveStdCharge(ipdid,chargeid,assesmentid,"0",ondatetime,offdatetime);
		    			   
		    			  /* diff= DateTimeUtils.getDifferenceOfTwoDateDBFormat(stddate, enddate);
		    				 qty =(int) diff;*/
		    			   qty = DateTimeUtils.getDifferanceofDateWithTime(ondatetime, offdatetime, master.getChargehours());
		    				 assesmentid = appointmentTypeDAO.getstdAssesmentid(result);
		    			     int upd = accountsDAO.updateChargeqty(Integer.toString(assesmentid),Integer.toString(qty));
		    		   }
		    		   
		    	   } else {
		    		   
		    		   if(stdbackdatechk){
		    			   String curstatus = appointmentTypeDAO.getstddbcurstatus(result);
		    			   if(curstatus.equals("0")){
		    				     /*diff= DateTimeUtils.getDifferenceOfTwoDateDBFormat(stddate, enddate);
			    				 qty =(int) diff;*/
		    				   qty = DateTimeUtils.getDifferanceofDateWithTime(ondatetime, offdatetime, master.getChargehours());
			    				 assesmentid = appointmentTypeDAO.getstdAssesmentid(result);
			    				 if(qty==0){
			    					 qty=1;
			    				 }
			    				 int dbqty = appointmentTypeDAO.getDbQuantity(assesmentid);
			    				 qty = qty + dbqty;
			    				 int upd = accountsDAO.updateChargeqty(Integer.toString(assesmentid),Integer.toString(qty));
		    			   }
		    			    
		    				 
		    		   }
		    		   if(!stdbackdatechk){
		    			   String curstatus = appointmentTypeDAO.getstddbcurstatus(result);
		    			   if(curstatus.equals("0")){
		    				   qty = DateTimeUtils.getDifferanceofDateWithTime(ondatetime, offdatetime, master.getChargehours());
			    			   int dbqty = appointmentTypeDAO.getDbQuantity(assesmentid);
			    			   if(qty==0){
			    					 qty=1;
			    				 }
			    				 qty = qty + dbqty;
			    			   int upd = accountsDAO.updateChargeqty(Integer.toString(assesmentid),Integer.toString(qty));
			    			   result= appointmentTypeDAO.updateStdCharge(result,"1",ondatetime,"");
		    			   }
		    		   }else{
		    			   String curstatus = appointmentTypeDAO.getstddbcurstatus(result);
		    			   if(curstatus.equals("0")){
		    				   Accounts accounts = appointmentTypeDAO.getStdChargeDetails(result);
		    				   offdatetime = accounts.getOffdatetime() + " , " + offdatetime;
		    				   ondatetime = accounts.getOndatetime() + " , " + ondatetime;
		    				   int upd = appointmentTypeDAO.updateStdChargeDateTime(result, ondatetime, offdatetime);
		    			   }
		    		   }
		    		   
		    	   }
	    		   
	    	   }
		
	    	 //akash 06-08-2019 below if else only
	    	Accounts accounts= accountsDAO.showonofftime(Integer.parseInt(chargeid), ipdid);
	   		if(previousstatus.equals("0")){
	   			int res = accountsDAO.insertStdChargeChild(chargeid,""+accounts.getId(),loginInfo.getUserId(),DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),ondatetime);
	   		}else{
	   			int res = accountsDAO.updateStdChargeChild(loginInfo.getUserId(),DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),childid,offdatetime);
	   		}
		
    	 response.setContentType("text/html");
	     response.setHeader("Cache-Control", "no-cache");
	     response.getWriter().write("1");
		
		
	} catch (Exception e) {

		e.printStackTrace();
	}finally{
		connection.close();
	}
	return null;
}

public String editchargedate() throws Exception {
	
	Connection connection=null;
     try {
      connection=Connection_provider.getconnection();
      ClientDAO clientDAO=new JDBCClientDAO(connection);
      AppointmentTypeDAO appointmentTypeDAO= new JDBCAppointmentTypeDAO(connection);
      String ipdid= request.getParameter("ipdid");
      String chargeid= request.getParameter("chargeid");
      String startend= request.getParameter("startend");
      String clientId= request.getParameter("clientid");
      String childid= request.getParameter("childid");
      Client client=clientDAO.getClientDetails(clientId);
      String fullname= client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
      AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
      StringBuffer buffer=new StringBuffer();
      buffer.append(fullname);
      buffer.append("~~");
      String chargename = appointmentTypeDAO.getChargeName(chargeid);
      buffer.append(""+chargename+"");
      Accounts accounts= accountsDAO.showonofftime(Integer.parseInt(chargeid), ipdid);
      String date ="";
      String hour ="00";
      String min ="00";
      if(startend.equals("0")){
    	  String ondate=DateTimeUtils.isNull(accounts.getOndatetime());
    	  if(ondate.contains(",")){
    		 for(String on:ondate.split(",")){
    			 ondate=on;
    		 }
    	  }
    	  if(!ondate.equals("")){
    		  String datetime[] = ondate.split(" ");
    		  if(datetime.length==3){
    			  date = datetime[1];
    			  hour = datetime[2].split(":")[0];
    			  min = datetime[2].split(":")[1];
    		  }else{
    			  date = datetime[0];
    			  hour = datetime[1].split(":")[0];
    			  min = datetime[1].split(":")[1];
    		  }
    	  }
      }else{
    	  String offdate=DateTimeUtils.isNull(accounts.getOffdatetime());
    	  if(offdate.contains(",")){
    		 for(String off:offdate.split(",")){
    			 offdate=off;
    		 }
    	  }
    	  if(!offdate.equals("")){
    		  String datetime[] = offdate.split(" ");
    		  if(datetime.length==3){
    			  date = datetime[1];
    			  hour = datetime[2].split(":")[0];
    			  min = datetime[2].split(":")[1];
    		  }else{
    			  date = datetime[0];
    			  hour = datetime[1].split(":")[0];
    			  min = datetime[1].split(":")[1];
    		  }
    	  }
      }
      buffer.append("~~");
      buffer.append(""+date+"");
      buffer.append("~~");
      buffer.append(""+hour+"");
      buffer.append("~~");
      buffer.append(""+min+"");
      buffer.append("~~");
      buffer.append(""+ipdid+"");
      buffer.append("~~");
      buffer.append(""+chargeid+"");
      buffer.append("~~");
      buffer.append(""+startend+"");
      buffer.append("~~");
      buffer.append(""+clientId+"");
      buffer.append("~~");
      buffer.append(""+childid+"");
      response.setContentType("text/html");
      response.setHeader("Cache-Control", "no-cache");
      response.getWriter().write(buffer.toString());
      
  } catch (Exception e) {
   e.printStackTrace();
  }finally{
		connection.close();
	}
     
     return null;
	
}

public String updatestdonofftimedate()throws Exception{
	try {
		Connection connection= Connection_provider.getconnection();
		String date=DateTimeUtils.isNull(request.getParameter("stddate"));
		String time=DateTimeUtils.isNull(request.getParameter("starthour"))+":"+DateTimeUtils.isNull(request.getParameter("startminute"))+":00";
		//dd-mm-yy hh:mm:ss
		String datetime=date+" "+time;
		String ipdid=request.getParameter("ipdid");
		String stdchargeId=request.getParameter("chargeid");
		String childId=request.getParameter("editstdxhildid");
		String clientId=request.getParameter("clientid");
		String startorend=DateTimeUtils.isNull(request.getParameter("editstdstartend"));
		
		AccountsDAO accountsDAO= new JDBCAccountsDAO(connection);
		Accounts accounts=accountsDAO.showonofftime(Integer.parseInt(stdchargeId), ipdid);
		Accounts childaccount=accountsDAO.getOnOffChargesChildDates(""+accounts.getId());
		String appenddate="";
		if(startorend.equals("0")){
			appenddate=childaccount.getOndatetime();
		}else{
			appenddate=childaccount.getOffdatetime();
		}		
		appenddate=DateTimeUtils.isNull(appenddate);
		String temp[]=appenddate.split(",");
		
		String correcttime="";
		temp[temp.length-1]=datetime;
		for (int i = 0; i < temp.length; i++) {
			if(i==0){
				correcttime=temp[i];
			}else{
				correcttime=correcttime+","+temp[i];
			}
			
		}
		if(appenddate.contains(",")){
			appenddate=appenddate+","+datetime;
		}else{
			appenddate=datetime;
		}
		int x=accountsDAO.updatestdOnFFDateTime(ipdid, stdchargeId, correcttime, startorend);
		x=accountsDAO.updateChildOnOffDatetime(datetime, childId, startorend);
		
		
		//Charge qty Logic 
		accounts=accountsDAO.showonofftime(Integer.parseInt(stdchargeId), ipdid);
		 childaccount=accountsDAO.getOnOffChargesChildDates(""+accounts.getId());
		int totalqty=0;
		AppointmentTypeDAO appointmentTypeDAO=new JDBCAppointmentTypeDAO(connection);
		 Master master= appointmentTypeDAO.getMasterCharges(stdchargeId);
		{
			String ontm[]=childaccount.getOndatetime().split(",");
			String offtm[]=childaccount.getOffdatetime().split(",");
			for (int i = 0; i < offtm.length; i++) {
				String ondatetime = DateTimeUtils.isNull(ontm[i]);
				String offdatetime= DateTimeUtils.isNull(offtm[i]);
				if(ondatetime.contains(":")&&offdatetime.contains(":")){
					  int qty = 1+DateTimeUtils.getDifferanceofDateWithTime(ondatetime, offdatetime, master.getChargehours());	
					  totalqty=totalqty+qty;
				}
			}
		}
		
//			System.out.println(totalqty);
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			int invoiceid = completeAptmDAO.getInvoiceforStandardCharges(ipdid, "001");
			int assesmentid = completeAptmDAO.getAssesmentIdforStdChargeIfExits1(ipdid, stdchargeId, invoiceid,
					master.getMasterchargetype());
			int upd = accountsDAO.updateChargeqty(Integer.toString(assesmentid), Integer.toString(totalqty));
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" );
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}


public String getonoffcharges(){
	  try {
			Connection connection= Connection_provider.getconnection();
	  String ipdid= request.getParameter("ipdid");
		String id =request.getParameter("id");
		 Accounts accounts=new Accounts();
		 AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
			accounts=accountsDAO.showonofftime(Integer.parseInt(id),ipdid);
			accounts.setOndatetime(DateTimeUtils.isNull(accounts.getOndatetime()));
			accounts.setOffdatetime(DateTimeUtils.isNull(accounts.getOffdatetime()));
			String str=accounts.getOndatetime()+"~"+accounts.getOffdatetime();
			
			
			ArrayList<Accounts> childList= accountsDAO.getOnOffChildList(""+accounts.getId());
			StringBuffer buffer=new StringBuffer();
			if(childList.size()>0){
				buffer.append("<table class='my-table lkclass' style='width:100%'>");
				buffer.append("<tr>");
				buffer.append("<th>On Date/Time</th>");
				buffer.append("<th>Off Date/Time</th>");
				buffer.append("<th>Delete</th>");
				buffer.append("</tr>");
			
			
			for(Accounts child:childList){
				buffer.append("<tr>");
				buffer.append("<td>"+child.getOndatetime()+"</td>");
			
				buffer.append("<td>"+child.getOffdatetime()+"</td>");
				if(child.getOffdatetime().equals("")){
					buffer.append("<td><a></a></td>");
				}else{
					buffer.append("<td><a onclick='delonnoffstd("+child.getChildid()+")'><i class='fa fa-trash'></a></td>");
				}
				
						
				
				buffer.append("</tr>");
			}
		
				buffer.append("</table>");
			}
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+buffer.toString()+"");
	  }catch (Exception e) {
		e.printStackTrace();
	}
	return null;
	  
}
public String editcharge() throws Exception{
	//var url = "editchargeStatement?ipdid="+ipdid+"&chargeid="+chargeid+"&assesmentid="+assesmentid+" ";
	StringBuilder buffer1 = new StringBuilder();
	BufferedReader reader = request.getReader();
	String line;
	while ((line = reader.readLine()) != null) {
		buffer1.append(line);
	}
	String data = buffer1.toString();
	JSONObject jsonObject = new JSONObject(data);
	//MasterDAO masterDAO = new JDBCMasterDAO(connection);
	String ipdid = jsonObject.getString("ipdid");
	String chargeid = jsonObject.getString("chargeid");
	String assesmentid = jsonObject.getString("assesmentid");
	String stdcharge =  jsonObject.getString("stdcharge");
	String newchargename=jsonObject.getString("chargename");
	String updchargeid=jsonObject.getString("updchargeid");
	String mastername=jsonObject.getString("mastername");
//	String ipdid = request.getParameter("ipdid");
//	String chargeid = request.getParameter("chargeid");
//	String assesmentid = request.getParameter("assesmentid");
//	String stdcharge =  request.getParameter("stdcharge");
//	String newchargename=request.getParameter("chargename");
//	String updchargeid=request.getParameter("updchargeid");
//	String mastername=request.getParameter("mastername");
	session.setAttribute("chargeipdid", ipdid);
	session.setAttribute("chargechargeid", chargeid);
	session.setAttribute("chargeassesmentid", assesmentid);
	session.setAttribute("stdcharge", stdcharge);
	session.setAttribute("updchargeid", updchargeid);
	
	Connection connection  = null;
	String id = request.getParameter("id");
	
	try{
		
		connection  = Connection_provider.getconnection();
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		IpdDAO ipdDAO=new JDBCIpdDAO(connection);
		String clientid=ipdDAO.getClientIDFromIPDID(ipdid);
		Accounts accounts = accountsDAO.getAssesmentDetails(assesmentid);
		ArrayList<Accounts> chargelist=accountsDAO.getchargelist(mastername);
		StringBuffer str = new StringBuffer();
//		str.append("<tr>");
//		str.append("<td>Qty : <input type='number' class='form-control'  name='qty"+accounts.getId()+"' id='qty"+accounts.getId()+"' value='"+accounts.getQuantity()+"'></td>");
//		str.append("</tr>");
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		Client client = clientDAO.getClientDetails(clientid);
		String tpid = client.getTypeName();
			 
			String temptpid= ipdDAO.getFollowerTp(tpid); 
			if(temptpid!=null){
				
				if(!temptpid.equals("0")){
					 tpid=temptpid;  
				}
			}
				ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
				ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(tpid);
		ArrayList<Client> apmtmentlist=clientDAO.getApmtNameList(mastername,thirdParty.getChargecolumnname(),tpid);	
		
		boolean isSTdCharge = accountsDAO.getIfSTdCharge(chargeid);
		
		if(isSTdCharge && stdcharge.equals("1")){
			String startEndTime= accountsDAO.getStdChargeStartEndTime(Integer.parseInt(assesmentid),ipdid); 
			session.setAttribute("startEndTime", startEndTime);
			
			
			if(startEndTime!=null){
				String tempc[] = startEndTime.split("~");
				String t1[] = tempc[0].split(",");
				String t2[] = tempc[1].split(",");
				String resultdatetime = "";
				for(int i=0;i<t2.length;i++){
					//resultdatetime = resultdatetime + t1[i] + " - " + t2[i] + " , "; 
					str.append("<tr>");
					str.append("<td>From Date : <input type='text' class='form-control' readonly='readonly' name='fdate"+i+"' id='fdate"+i+"' value='"+t1[i]+"' autocomplete='off'></td>");
					str.append("<td>ToDate : <input type='text' class='form-control' readonly='readonly' name='tdate"+i+"' id='tdate"+i+"' value='"+t2[i]+"'></td>");
					str.append("</tr>");
					str.append("<tr>");
					str.append("<td>Charge : <input type='text' class='form-control'  name='newchargename' id='newchargename' value='"+newchargename+"'></td>");
					str.append("</tr><br>");
					str.append("<tr>");
					
					str.append("<br><select id='apmttype' name='apmttype' class='form-control chosen'>");
					str.append("<option value='0'>Replace Existing Charge</option>");

					for (Client apmt : apmtmentlist) {
						str.append("<option value='" + apmt.getMasterid() + "'>" + apmt.getMastername() + "</option>");
					}

					str.append("</select>");
					str.append("</tr>");
					str.append("<br><br>");
					str.append("<tr>");
					str.append("<br><button type='button' class='btn btn-info' onclick=openPopup('debitAdditional?clientId="+clientid+"')>Add New Charge</button></td>");
					str.append("</tr><br>");
				}
				int curstatus = accountsDAO.getstdonoffcurstatus(Integer.parseInt(assesmentid),ipdid);
				if(curstatus==1){
					//resultdatetime = resultdatetime + " , " + t1[t1.length-1];
					int j = t1.length-1;
					str.append("<tr>");
					str.append("<td>Charge Date : <input type='text' class='form-control' readonly='readonly' name='fdate"+j+"' id='fdate"+j+"' value='"+t1[t1.length-1]+"' autocomplete='off'></td>");
					str.append("</tr>");
				}
				
			}
		}else{
			str.append("<tr>");
			str.append("<td>Charge Date : <input type='text' class='form-control' readonly='readonly' name='fdate' id='fdate' value='"+DateTimeUtils.getCommencingDate1(accounts.getCommencing())+"' autocomplete='off'></td>");
			if(loginInfo.getClinicUserid().equals("ngppadole"))
			{
			str.append("<td>Showing Date : <input type='text' class='form-control' name='sdate' id='sdate' value='"+DateTimeUtils.isNull(accounts.getShowdate())+"' autocomplete='off'></td>");
			}
			str.append("</tr><br>");
			str.append("<tr>");
			str.append("<td>Charge : <input type='text' class='form-control'  name='newchargename' id='newchargename' value='"+newchargename+"'></td>");
			str.append("</tr><br>");
			str.append("<tr>");
			
			str.append("<br><select id='apmttype' name='apmttype' class='form-control chosen'>");
			str.append("<option value='0'>Replace Existing Charge</option>");

			for (Client apmt : apmtmentlist) {
				str.append("<option value='" + apmt.getMasterid() + "'>" + apmt.getMastername() + "</option>");
			}

			str.append("</select>");
			str.append("</tr>");
			
			str.append("<br><br>");
			str.append("<tr>");
			str.append("<br><button type='button' class='btn btn-info' onclick=openPopup('debitAdditional?clientId="+clientid+"')>Add New Charge</button>");
			str.append("</tr><br>");
			/*String curdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String tempa[] = curdate.split(" ");
			
			str.append("<tr>");
			str.append("<td>FromDate : <input type='text' class='form-control'  name='tdate' id='tdate' value='"+tempa[0]+"'></td>");
			str.append("</tr>");*/
		}
		String ngppadole="";
		/*if(loginInfo.getClinicUserid().equals("ngppadole"))
		{
			ngppadole="1";
		}else{
			ngppadole="0";
		}*/
		
		JSONObject jsonobj = new JSONObject();
	
		jsonobj.put("chargedtailsbody", str.toString());
		jsonobj.put("ngppadole", ngppadole);
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
		
		
	}catch (Exception e) {
	e.printStackTrace();
	}finally{
		
		connection.close();
	}
	
	return null;
}
public String deleteinddiscount(){
	Connection connection= null;
	String id= request.getParameter("id");
	String reason= request.getParameter("reason");
	String userid= loginInfo.getUserId();
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Calendar cal = Calendar.getInstance();
    String date = dateFormat.format(cal.getTime());  
	
	try {
		connection = Connection_provider.getconnection();
		AppointmentDAO appointmentDAO= new JDBCAppointmentDAO(connection);
		int res=appointmentDAO.deletediscountindfromdashboard(id, userid, date, reason);
		CompleteAppointment appointment = appointmentDAO.getDeleteDiscountReqData(id);
		if(appointment.getInvoiced()>0){
			res = appointmentDAO.setUpdateDiscRequestStatusTo0(appointment.getInvoiced());
		}
		 response.setContentType("text/html");
	      response.setHeader("Cache-Control", "no-cache");
	      response.getWriter().write("0"); 
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}


public String delonnoffstd(){
	try {
		Connection connection= Connection_provider.getconnection();
		String childid=DateTimeUtils.isNull(request.getParameter("child"));
		
		AccountsDAO accountsDAO= new JDBCAccountsDAO(connection);
		Accounts accounts= accountsDAO.getOnOffChargesChildDateNEW(childid);
		Accounts parent= accountsDAO.getOnOffChargesParent(accounts.getParentId());
		int res=accountsDAO.delChildOnOffCharge(childid);
		AppointmentTypeDAO appointmentTypeDAO= new JDBCAppointmentTypeDAO(connection);
		Master master= appointmentTypeDAO.getMasterCharges(parent.getChargeid());
		if(res==1){
			Accounts childaccount=accountsDAO.getOnOffChargesChildDates(""+accounts.getParentId());
				int totalqty=0;
				
				{
					String ontm[]=childaccount.getOndatetime().split(",");
					String offtm[]=childaccount.getOffdatetime().split(",");
					for (int i = 0; i < offtm.length; i++) {
						String ondatetime = DateTimeUtils.isNull(ontm[i]);
						String offdatetime= DateTimeUtils.isNull(offtm[i]);
						if(ondatetime.contains(":")&&offdatetime.contains(":")){
							  int qty = 1+DateTimeUtils.getDifferanceofDateWithTime(ondatetime, offdatetime, master.getChargehours());	
							  totalqty=totalqty+qty;
						}
					}
				}
			  int upd = accountsDAO.updateChargeqty(parent.getAssesmentid(),Integer.toString(totalqty));
		}
		String rest=parent.getIpdid()+"-"+parent.getChargeid();
		 response.setContentType("text/html");
	      response.setHeader("Cache-Control", "no-cache");
	      response.getWriter().write(""+rest); 
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
public String getallnotoccupiedbeds(){
	Connection connection= null;
	String wardid=request.getParameter("wardid");
	try {
		connection= Connection_provider.getconnection();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		ArrayList<Bed> bedList = ipdDAO.getWardWiseBedList(wardid);

		StringBuffer str = new StringBuffer();
		str.append("<select  name='bedid' id='bedname' class='form-control chosen-select'>");
		str.append("<option value='0'>Select Bed</option>");

		for (Bed bed : bedList) {
			str.append("<option value='" + bed.getId() + "'>" + bed.getBedname() + "</option>");
		}

		str.append("</select>");
		str.append("&nbsp; &nbsp; &nbsp;<p><button class='btn btn-primary' onclick='savepatienttoipd()' >Save To IPD</button></p>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("" + str.toString() + "");

		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String vieemrwallclientprisc(){
	
	String clientId = request.getParameter("clientId");
	String prectionerid = request.getParameter("prectionerid");
	String conditionid = request.getParameter("conditionid");
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		EmrDAO emrDAO = new JDBCEmrDAO(connection);
		
		ArrayList<Priscription>parentPriscList = emrDAO.getParentPriscList(clientId,prectionerid,conditionid);
		
		StringBuffer str = new StringBuffer();
		str.append("<table class='table'>");
		str.append("<thead class='thead-dark'>");
		str.append("<tr>");
		str.append("<th class='emrinvestigationfont'>Sr.No</th><th class='emrinvestigationfont'>Date</th><th class='emrinvestigationfont'>Print</th>");
		str.append("</tr>");
		str.append("</thead>");
		str.append("<tbody>");
		int i=0;
		for(Priscription priscription : parentPriscList){
			str.append("<tr>");
			str.append("<td>"+(++i)+"</td>");			
			str.append("<td>"+priscription.getDate()+"</td>");
			str.append("<td><a href='#' onclick='printParentPrisc("+priscription.getId()+")' title='Print Prescription'><i class='fa fa-print emrinvestigationfontprint'></i></a></td>");
			str.append("</tr>");
			
		}
		str.append("</tbody>");
		str.append("</table>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+str.toString()+""); 
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return null;
}

public String viewemr(){
	
	String clientId = request.getParameter("clientId");
	String prectionerid = request.getParameter("prectionerid");
	String conditionid = request.getParameter("conditionid");
	
	Connection connection = null;
	try{
		
		
		connection = Connection_provider.getconnection();
		InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
		
		ArrayList<Investigation>invstigationList = investigationDAO.getParentPriscList(clientId,prectionerid,conditionid);
		
		StringBuffer str = new StringBuffer();
		
		   str.append("<table class='table'>");
		   str.append("<thead class='thead-dark'>");
		   str.append("<tr >");
		   str.append("<th class='emrinvestigationfont'>Sr.No</th><th class='emrinvestigationfont'>Investigation Type</th><th class='emrinvestigationfont'>Date</th><th class='emrinvestigationfont'>Print</th><th class='emrinvestigationfont'>Pacs Report</th>");
		   str.append("</tr>");
		   str.append("</thead>");
		   str.append("<tbody>");
		   int i=0;
		
		for(Investigation investigation : invstigationList){
			
			str.append("<tr>");
			str.append("<td>"+(++i)+"</td>");	
			str.append("<td>"+investigation.getInvsttype()+"</td>");	
			str.append("<td>"+investigation.getDate()+"</td>");
			if(investigation.getInvstreporttype().equals("Numerical")){
				str.append("<td><a href='#' onclick='printinvstigationrecord("+investigation.getId()+",0)' title='Print Investigation' class='editpricon'><i class='fa fa-print emrinvestigationfontprint'></i></a></td>");
				
			}else{
				str.append("<td><a href='#' onclick='printinvstigationrecord("+investigation.getId()+",1)' title='Print Investigation' class='editpricon'><i class='fa fa-print emrinvestigationfontprint'></i></a></td>");
			}
			
			if(investigation.getPacs()!=0){
				str.append("<td><a href='#' onclick='viewpacsreport("+investigation.getId()+")' title='Pacs Report' class='editpricon'><i class='fa fa-object-ungroup aria-hidden='true''></i></a></td>");
			}
			str.append("<td></td>"); 
			str.append("</tr>");
			
		}
		str.append("</tbody>");
		str.append("</table>");
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+str.toString()+""); 
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return null;
}

public String checkdisccancelorapplied() throws Exception{
	  Connection connection =null;
	  try {
		   connection = Connection_provider.getconnection();
		   AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
		   String discountid = request.getParameter("discountid");
		   String invoiceid = request.getParameter("invoiceid");
		   String isaproveordisc = request.getParameter("isaproveordisc");
		   boolean flag = appointmentDAO.checkDiscRequestDeleted(discountid);
		   int res =0;
		   if(!flag){
			   flag = appointmentDAO.checkInvoiceCreatedAgainstDiscReq(invoiceid);
			   if(flag){
				   res =2;
			   }
		   }else{
			   res=1;
		   }
		   response.setContentType("text/html");
		   response.setHeader("Cache-Control", "no-cache");
		   response.getWriter().write(""+res+"~~"+isaproveordisc+""); 
		  
	  } catch (Exception e) {
	  		e.printStackTrace();
	  }finally{
		  connection.close();
	  }
	  return null;
	 }
public String getappointmentdata(){
	 Connection connection =null;
	  try {
		   connection = Connection_provider.getconnection();
		   String editAppointId = request.getParameter("apmtid");
		   NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
		   NotAvailableSlot notAvailableSlot=notAvailableSlotDAO.getAvailableSlotdata(Integer.parseInt(editAppointId));
		   AppointmentTypeDAO appointmentTypeDAO=new JDBCAppointmentTypeDAO(connection);
		   ClientDAO clientDAO=new JDBCClientDAO(connection);
		   StringBuffer str=new StringBuffer();
		   String val="0";
		   str.append(""+notAvailableSlot.getsTime()+"~~"+notAvailableSlot.getEndTime()+"~~"+DateTimeUtils.changeDateFormattoPicker(notAvailableSlot.getCommencing())+"~~"+notAvailableSlot.getDiaryUserId()+"~~"+notAvailableSlot.getDuration()+"~~"+notAvailableSlot.getAppointmentid()+"~~"+notAvailableSlot.getOpdpmnt()+"~~"+notAvailableSlot.getChrgstatus());
		   ArrayList<Client> acc=clientDAO.getAllPatientByApmtid(Integer.parseInt(notAvailableSlot.getClientId()));
		   int size=acc.size();
		   
		   ArrayList<AppointmentType> appointmentTypeList = appointmentTypeDAO.gettpAppointmentTypeList(acc.get(size-1).getTypeName(), acc.get(size-1).getWhopay(),false,"0",loginInfo,""+notAvailableSlot.getDiaryUserId(),0);

			StringBuffer str1 = new StringBuffer();

			str1.append("<select onchange='setAppointmentTypeTimeAjax(this.value)' name='duration' id='apmtType12' class='form-control showToolTip chosen' >");
			str1.append("<option value='0'>Select Appointment Type</option>");
			for (AppointmentType appointmentType : appointmentTypeList) {
				str1.append("<option value='" + appointmentType.getId() + "'>"
						+ appointmentType.getName() + "</option>");
			}
			str1.append("</select>");
			
			if(loginInfo.isSaimed()) {
		    val="1";
			}
			
			session.setAttribute("previousapmtdata", notAvailableSlot);
		     response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+"~~"+str1.toString()+"~~"+val); 
	  }catch (Exception e) {
		e.printStackTrace();
	}
	return null;
	
}
public String alreadybooked(){
		 Connection connection =null;
		  try {
			  int res=0;
			   connection = Connection_provider.getconnection();
			   AppointmentDAO appointmentDAO=new JDBCAppointmentDAO(connection);
			   String bedid = request.getParameter("bedid");
			   boolean flag = appointmentDAO.checkalredyadmitted(bedid);
			   if(flag){
				   res=1;
			   }
			   response.setContentType("text/html");
			   response.setHeader("Cache-Control", "no-cache");
			   response.getWriter().write(""+res+""); 
			  
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return null;
	
}

public String removemedicine() throws Exception {
	
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		String id= request.getParameter("id");
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		int res= ipdDAO.removeMedicineDischarge(id); 
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("");
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}
	
	return null;
}
public String selfcharge()throws Exception{
	Connection connection=null;
    try {
    	connection=Connection_provider.getconnection();
    	IpdDAO ipdDAO = new JDBCIpdDAO(connection);
    	ChargesAccountProcessingDAO chargesAccountProcessingDAO= new JDBCChargeAccountProcesDAO(connection);
    	
    	String chargetype = request.getParameter("chargetype");
    	
    	String ipdwhopay = request.getParameter("ipdwhopay");
    	String ipdtpid = request.getParameter("ipdtpid");
    	String ipdaddmissionid = request.getParameter("ipdaddmissionid");
    	String clientId = request.getParameter("clientId");
    	
    	if(ipdwhopay.equals("")){
    		ClientDAO clientDAO = new JDBCClientDAO(connection);
    		Client client = clientDAO.getClientDetails(clientId);
    		ipdwhopay = client.getWhopay();
    	}
    	
    	if(ipdwhopay.equals(Constants.PAY_BY_CLIENT)){
    		ipdtpid = "0";
    	}
    	
    	if(ipdaddmissionid==null){
    		
    		ipdaddmissionid="0";
    	}
    	if(ipdaddmissionid.equals("")){
    		ipdaddmissionid="0";
    	}
    	
    	BedDao bedDao=new JDBCBedDao(connection);
		Bed bed = bedDao.getEditIpdData(ipdaddmissionid);
		
		String wardid = bed.getWardid();
		
		if(wardid==null){
			wardid="0";
		}
		if(wardid.equals("")){
			wardid="0";
		}
		
		int compulsary_consultant=chargesAccountProcessingDAO.isCompulasryConsultant(chargetype);
    	
    	ArrayList<Master>list = ipdDAO.getFilteredChargeList(chargetype,ipdtpid,wardid,clientId,loginInfo.isShow_wardname());
    	StringBuffer str = new StringBuffer();
   
    		str.append("<select  name='chargeTYpe' id='chargeTYpe' class='form-control showToolTip chosen' >");
    		str.append("<option value='0'>Select Charge</option>");
    		for(Master master : list){
    			str.append("<option value='"+master.getId()+"'>"+master.getName()+"</option>");
    		}
    		str.append("</select>");
    		
    		str.append("!@");
    		str.append(compulsary_consultant);
    		response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+""); 
    	
    }catch(Exception e){
 	   e.printStackTrace();
    }
    finally{
		connection.close();
	}
	return null;
}

public String getdisadvoice() throws Exception {
    
    Connection connection=null;
    try {
     connection=Connection_provider.getconnection();
     TreatmentEpisodeDAO treatmentEpisodeDAO=new JDBCTreatmentEpisode(connection);
     String treatmentid=request.getParameter("treatmentepisode");
     String advoice=treatmentEpisodeDAO.getDischargeAdvoice(treatmentid);
     
     response.setContentType("text/html");
     response.setCharacterEncoding("UTF-8");
     response.setHeader("Cache-Control", "no-cache");
     response.getWriter().write(""+advoice+"");
     
 } catch (Exception e) {
  e.printStackTrace();
 }finally{
		connection.close();
	}
    
    return null;
}

public String checksetdiary(String commencing,String diaryuserid){
	 String res="1";
	 Connection connection =null;
	  try {
		 
		   connection = Connection_provider.getconnection();
		   AppointmentDAO appointmentDAO=new JDBCAppointmentDAO(connection);
		  /* String commencing = request.getParameter("commencing");
		   String diaryuserid = request.getParameter("diaryuserid");*/
		   boolean flag = appointmentDAO.checksetdiary(commencing,diaryuserid);
		   if(flag){
			   res="0";
		   }
		  /* response.setContentType("text/html");
		   response.setHeader("Cache-Control", "no-cache");
		   response.getWriter().write(res); */
		  
} catch (Exception e) {
	e.printStackTrace();
}

return res;

}

public String checkpatientchargescreated() throws Exception {
	if (!verifyLogin(request)) {
		return "login";
	}

	Connection connection = null;
	String apmtid = request.getParameter("apmtid");
	try {
		connection = Connection_provider.getconnection();
		NotAvailableSlotDAO availableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		int chargecreated = availableSlotDAO.checkPatientChargesCreated(apmtid);

		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("" + chargecreated + "");

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}

	return null;
}

public String checkinvoice() throws Exception {
	
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		String ipdid = request.getParameter("ipdid");
		String clientid= request.getParameter("clientid");
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		
		boolean hasInvoice= ipdDAO.checkHasInvoiceCreated(ipdid,clientid);     
		String str="0";
		if(hasInvoice){
			str="1";
		}
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(str);
		
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	finally {
		connection.close();
	}
	
	return null;
	
}

public String cancelipd() throws Exception {
	
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		String ipdid= request.getParameter("ipdid");
		String clientid= request.getParameter("clientid");
		String cancelnotes= request.getParameter("cancelnotes");
		IpdDAO ipdDAO =new JDBCIpdDAO(connection);
		
		//delete invoice
		int res=ipdDAO.cancelInvoice(ipdid,clientid);
		
		//update bedid
		
		res=ipdDAO.cancelBedStatus(ipdid,cancelnotes,loginInfo.getUserId());
		
		
	} catch (Exception e) {

		e.printStackTrace();
	}	
	finally {
		connection.close();
	}
	return null;
}


public String getpatientshiftdata() throws Exception {
	
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		IpdDAO ipdDAO =new JDBCIpdDAO(connection);
		BedDao bedDao = new JDBCBedDao(connection);
		String ipdid= request.getParameter("ipdaddmissionid");
		Bed bed = bedDao.getEditIpdData(ipdid);
		int wardid = Integer.parseInt(bed.getWardid());
		int bedid = Integer.parseInt(bed.getBedid());
		ArrayList<Bed>bedlist = ipdDAO.geteditWardWiseBedList(bed.getWardid(),bed.getBedid());
		ArrayList<Bed> wardlist=bedDao.getAllWardList("0");
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("<select name='shiftpatient_wardid' onchange='getbedFromWardid(this.value)' class='form-control chosen' id='shiftpatient_wardid' >");
		buffer.append("<option value='0'>Select Ward</option>");
	    for(Bed bed1:wardlist) {
	    	if(bed1.getId()==wardid){
	    		buffer.append("<option value='"+bed1.getId()+"' selected='selected'>"+bed1.getWardname()+"</option>");
	    	}else{
	    		buffer.append("<option value='"+bed1.getId()+"'>"+bed1.getWardname()+"</option>");
	    	}
	    }
	    buffer.append("</select>");
	    
		buffer.append("~~~");
		
		buffer.append("<select name='shiftpatient_bedid'  class='form-control chosen' id='shiftpatient_bedid' >");
		buffer.append("<option value='0'>Select Bed</option>");
	    for(Bed bed1:bedlist) {
	    	if(bed1.getId()==bedid){
	    		buffer.append("<option value='"+bed1.getId()+"' selected='selected'>"+bed1.getBedname()+"</option>");
	    	}else{
	    		buffer.append("<option value='"+bed1.getId()+"'>"+bed1.getBedname()+"</option>");
	    	}
	    }
	    buffer.append("</select>");
	    buffer.append("~~~");
	    buffer.append(""+wardid+"");
	    buffer.append("~~~");
	    buffer.append(""+bedid+"");
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

public String getbedFromWardid(){
	Connection connection= null;
	
	try {
		String wardid=request.getParameter("wardid");
		String bedid=request.getParameter("bedid");
		connection= Connection_provider.getconnection();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		ArrayList<Bed>bedlist = ipdDAO.geteditWardWiseBedList(wardid,bedid);
		int bedidid = Integer.parseInt(bedid);
		StringBuffer buffer = new StringBuffer();
		buffer.append("<select name='shiftpatient_bedid'  class='form-control chosen' id='shiftpatient_bedid' >");
		buffer.append("<option value='0'>Select Bed</option>");
	    for(Bed bed1:bedlist) {
	    	if(bed1.getId()==bedidid){
	    		buffer.append("<option value='"+bed1.getId()+"' selected='selected'>"+bed1.getBedname()+"</option>");
	    	}else{
	    		buffer.append("<option value='"+bed1.getId()+"'>"+bed1.getBedname()+"</option>");
	    	}
	    }
	    buffer.append("</select>");
	   
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("" + buffer.toString() + "");

		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String savepatientshiftdata(){
	Connection connection= null;
	
	try {
		String wardid=request.getParameter("wardid");
		String bedid=request.getParameter("bedid");
		String ipdaddmissionid=request.getParameter("ipdaddmissionid");
		int id = Integer.parseInt(ipdaddmissionid);
		connection= Connection_provider.getconnection();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		BedDao bedDao = new JDBCBedDao(connection);
		ClientDAO clientDAO= new JDBCClientDAO(connection);
		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
		AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
		int res222 =0;
		String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		
		Bed bedchange = bedDao.getEditIpdData(ipdaddmissionid);
		
		String templ[] = bedchange.getAdmissiondate().split(" ");
		String ldate = DateTimeUtils.getCommencingDate1(templ[0]);
		String time[] = templ[1].split(":");
		String hh = time[0];
		String mm = time[1];
		
		String loglastmodified = templ[0] + " " + hh + ":" + mm + ":20" ;
		String cutofftime = bedDao.getHospitalCutoffTime(loginInfo.getClinicid());
        if(!cutofftime.equals("0")){
        	loglastmodified = templ[0] + " " + cutofftime + ":20" ;
        }
		int log = 0;
		
		String logcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
		if(!bedchange.getWardid().equals(wardid) && !bedchange.getBedid().equals(bedid)){
			session.setAttribute("ipddash_forreload", "1");
			Bed bed = bedDao.getEditIpdData(ipdaddmissionid);
			bed.setWardid(wardid);
			bed.setBedid(bedid);
			log = bedDao.saveBedChangeLog(bed, loglastmodified, id,logcommencing,date,0);
		}
		else if(!bedchange.getWardid().equals(wardid)){
			session.setAttribute("ipddash_forreload", "1");
			Bed bed = bedDao.getEditIpdData(ipdaddmissionid);
			bed.setWardid(wardid);
			bed.setBedid(bedid);
			log = bedDao.saveBedChangeLog(bed, loglastmodified, id,logcommencing,date,0);
		}
		else if(!bedchange.getBedid().equals(bedid)){
			session.setAttribute("ipddash_forreload", "1");
			Bed bed = bedDao.getEditIpdData(ipdaddmissionid);
			bed.setWardid(wardid);
			bed.setBedid(bedid);
			log = bedDao.saveBedChangeLog(bed, loglastmodified, id,logcommencing,date,0);
		}
		
		
		boolean flag=bedDao.getIsWardChange(id,wardid);
		Bed bed2=new Bed();
		bed2=bedDao.getRecentWardid(id,wardid);
		
		//update bed and ward
		int result1 = ipdDAO.updateWardBedInAdmission(id,wardid,bedid);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String today = dateFormat.format(cal.getTime()); 
		boolean datestatus=today.equals(bed2.getCommencing());
	
		//  22-06-2019 new code of auto charge
		if(flag){
			Client client=clientDAO.getClientDetails(bedchange.getClientid()); 
        	int res=bedDao.saveShiftBedLog(bedchange.getAdmissiondate(),id,bedchange.getWardid(),wardid);
        	
        	int wardids = completeAptmDAO.getMaxWardChargeID(id,today);
        	String wardid1 = ""+wardids;
        	if(wardid1.equals("0")){
        		wardid1 = wardid;
        	}
        	ArrayList<Master> chargeList=appointmentTypeDAO.getStandardChargeList(""+wardid1, client.getTypeName(), client.getWhopay(),loginInfo);
        	String stdcharges="0";
    		for(Master master:chargeList){
    			stdcharges=stdcharges+","+master.getId();
    		}
			int result=completeAptmDAO.updateWardCharges(id,stdcharges);
			int invoiceid=0;
			String date1 = bedchange.getAdmissiondate().split(" ")[0];
			if(chargeList.size()!=0){
				CompleteAppointment appointment=new CompleteAppointment();
    			appointment.setClientId(bedchange.getClientid());
    			appointment.setPractitionerId(bedchange.getPractitionerid());
    			appointment.setChargeType("Charge");
    			appointment.setLocation("1");
    		    appointment.setAdditionalcharge_id(stdcharges);
    		    appointment.setIpdid(id);
    		    appointment.setInvoiceDate(date1);
    		    appointment.setAppointmentid("0");
    		    appointment.setIpd("1");
    		    appointment.setWardid(""+wardid);
    		    if(client.getWhopay()!=null){
    		    	if(client.getWhopay().equals("Self") || client.getWhopay().equals("Client")){
    		    		appointment.setPolicyExcess("0");
    		    		appointment.setPayBuy("0");
    		    	} else {
    		    		appointment.setPolicyExcess("1");
    		    		appointment.setPayBuy("1");
    		    	}
    		    }
    		    String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
    		    appointment.setUser(fullname);
    		    appointment.setInvoiceDate(today);
     			appointment.setCommencing(today); 
      		    invoiceid=completeAptmDAO.saveStndCharge(appointment.getClientId(), String.valueOf(id), stdcharges);
      		    invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId(),loginInfo.getUserId());
      		    int del = appointmentTypeDAO.deletetodayBedShiftingcharge(id,today,loginInfo);
   				appointment.setInvoiceid(String.valueOf(invoiceid));
      		    for(Master master:chargeList){
      		       appointment.setCommencing(today); 
  		    	   appointment.setApmtType(master.getName());
  		    	   appointment.setCharges(master.getCharge());
  		    	   appointment.setAdditionalcharge_id(String.valueOf(master.getId()));
  		    	   //appointment.setMasterchargetype("Bed Charge");
  		    	   //  30-11-2019 kunal auto charges not applied
		    	   if(loginInfo.getIskunal()!=1){
		    		   appointment.setMasterchargetype("Bed Charge");
				   }else{
					   appointment.setMasterchargetype("Bed Charges");
				   }
  		    	   appointment.setQuantity(1);
  		    	   appointment.setStdflag("1");
  		    	   appointment.setBedid(bedid);
  		    	   res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
  		    	}
  		    	
            }
			int autosetchargelogid = appointmentTypeDAO.getautosetchargelogid(id,""+wardid1,bedid);
  		    int uw = appointmentTypeDAO.updateAutosetWardID(autosetchargelogid,""+wardid1,bedid);
  		  
  		    if(loginInfo.isBalgopal()){
  	 			ipdDAO.startOnOffChargesWardChangingProcess(""+id, wardid, loginInfo);
  	 		}
  	 		
		}
		session.setAttribute("ipddash_forreload", "1");
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("" + res222 + "");

		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
public String getappointmentdataforpay() {
	Connection connection=null;
	try {
		connection= Connection_provider.getconnection();
		String editAppointId = request.getParameter("apmtid");
		NotAvailableSlotDAO availableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
		NotAvailableSlot a=availableSlotDAO.getAvailableSlotDnadata(DateTimeUtils.convertToInteger(editAppointId));
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("" + a.getCharge() + "");
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	 
	 
	 
	return null;
	
}
public String getselecteddrlist() throws SQLException {
	Connection connection = null;
	String dept=request.getParameter("dept");
	try {
		connection = Connection_provider.getconnection();
		NotAvailableSlotDAO availableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
		ArrayList<DiaryManagement>userList=availableSlotDAO.getUserListwithdepartment(loginInfo.getId(),dept);

		StringBuffer str = new StringBuffer();
		str.append("<select id='diaryUsersss' name='diaryUser' class='form-control showToolTip chosen-select' onchange='showdisplaynewopd()'>");
		str.append("<option value='0'>Select Doctor</option>");

		for (DiaryManagement diaryManagement : userList) {
			str.append("<option value='" + diaryManagement.getId() + "'>" + diaryManagement.getDiaryUser() + "</option>");
		}

		str.append("</select>");

		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("" + str.toString() + "");

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}
	return null;
	
}
public String showallAvailabilityfordr() {
	if (!verifyLogin(request)) {
		return "login";
	}
	String date = request.getParameter("date");
	String diaryuserId = request.getParameter("diaryuserId");
	String location = request.getParameter("location");
	String starttime = request.getParameter("starttime");
	String endtime = request.getParameter("endtime");
	String diaryuser = request.getParameter("diaryuser");
	String slotId = request.getParameter("slotid");
	boolean pikdrop = Boolean.parseBoolean(request.getParameter("pikdrop"));
	
	session.setAttribute("sessionpikdrop", pikdrop);
	
	System.out.println(pikdrop);

	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();

		date = DateTimeUtils.getCommencingDate(date);
		StringBuffer buffer = new StringBuffer();
		String wcdate[] = date.split("-");

		// set weekname
		int wyear = Integer.parseInt(wcdate[0]);
		int month = Integer.parseInt(wcdate[1]);
		int day = Integer.parseInt(wcdate[2]);

		String cweekName = DateTimeUtils.getWeekName(wyear, month, day);
		ChargesReportDAO chargesReportDAO=new JDBCChargesReportDAO(connection);
		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
		int i = 0;

		NotAvailableSlot diarydata = new NotAvailableSlot();
		int diaryuserid = 0;
		String stime = "";
		String currentstime = "";
		Date date1 = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String today = dateFormat.format(cal.getTime()); 
		Date date2=new SimpleDateFormat("yyyy-mm-dd").parse(date);
		DateFormat format = new SimpleDateFormat("HH:mm");
		diarydata = notAvailableSlotDAO.getDiaryData(date, "" + diaryuserId + "", location);
		if(date.equals(today)){
			String currentTime=format.format(date1);
			String mm=currentTime.split(":")[1];
			String strmm="";
			int rem=Integer.parseInt(mm)%5;
			if(rem==0){
				currentstime=currentTime;
			}else{
				int mmm=Integer.parseInt(mm);
				while(mmm%5!=0){
					mmm--;
				}
				strmm=""+mmm;
				if(mmm<=9){
					String singlemm=String.valueOf(mmm);
					singlemm="0"+singlemm;
					strmm=singlemm;
				}
				currentstime=currentTime.split(":")[0]+":"+strmm;
				diarydata.setSTime(currentstime);
			}	
		}else{
			currentstime=diarydata.getSTime();
		}
		
			
			ArrayList<DiaryManagement> servicemanList = notAvailableSlotDAO.getUserList(DateTimeUtils.convertToInteger(diaryuserId));
			boolean isab = false;
			int blockapmtid =0;
			int count=6;
			String duration=diarydata.getDuration().split(":")[1];
			String totalduration=DateTimeUtils.getDuration(diarydata.getSTime(), diarydata.getEndTime());
			String hour=totalduration.split(":")[0];
			String minutes=totalduration.split(":")[1];
			count=((DateTimeUtils.convertToInteger(hour)*60)+DateTimeUtils.convertToInteger(minutes))/DateTimeUtils.convertToInteger(duration);
			String starttime1=currentstime;
			String endtime1="";
			String d="";
//			for(int k=1;k<=count;k++){
//				buffer.append("<tr>");
//				isab = notAvailableSlotDAO.checkallbooked(date, DateTimeUtils.convertToInteger(diaryuserId), starttime1);
//				blockapmtid = notAvailableSlotDAO.checkBlockslot(date, DateTimeUtils.convertToInteger(diaryuserId), starttime1);
//				endtime1=DateTimeUtils.getTotalofTwoTime(starttime1, diarydata.getDuration());
//				d = "" + starttime1+ " - " + endtime1 + "";
//				starttime=starttime1;
//				endtime=endtime1;
//				starttime1=DateTimeUtils.getTotalofTwoTime(starttime1, diarydata.getDuration());
//					diaryuserid =DateTimeUtils.convertToInteger(diaryuserId);
//					
//
//			
//			if (isab) {
//				if(blockapmtid>0){
//					NotAvailableSlot slot=notAvailableSlotDAO.getAvailableSlotdata(blockapmtid);
//					String blockduration=diarydata.getDuration().split(":")[1];
//					String blocktotalduration=DateTimeUtils.getDuration(slot.getsTime(), slot.getEndTime());
//					String blockhour=blocktotalduration.split(":")[0];
//					String blockminutes=blocktotalduration.split(":")[1];
//					int blockcount=((DateTimeUtils.convertToInteger(hour)*60)+DateTimeUtils.convertToInteger(minutes))/DateTimeUtils.convertToInteger(blockduration);
//					String blockendtime1="";
//					String blockstarttime1=slot.getsTime();
//					String blockstarttime="";
//					String blockendtime="";
//					for(int j=1;j<=blockcount;j++){
//						blockendtime1=DateTimeUtils.getTotalofTwoTime(blockstarttime1, diarydata.getDuration());
//						d = "" + blockstarttime1+ " - " + blockendtime1 + "";
//						blockstarttime=blockstarttime1;
//						blockendtime=blockendtime1;
//						blockstarttime1=DateTimeUtils.getTotalofTwoTime(blockstarttime1, diarydata.getDuration());
//						
//						buffer.append("<li id='li" + k + "' class='slot strike lifont ' style='cursor:default;background-color: #aba9a9 !important;'  data-slot='12:45PM'>" + d
//										+ " " + cweekName + "  ("+slot.getReasonforblock()+")"+"</li>");
//						j++;
//						if(slot.getEndTime().equals(blockendtime1)){
//							break;
//						}
//					}
//					starttime1=blockendtime1;
//				}else{
//				buffer.append(
//						"<li id='li" + k + "' class='slot strike lifont' style='cursor:default'  data-slot='12:45PM'>" + d
//								+ " " + cweekName + "</li>");
//				}
//			} else {
//
//				buffer.append("<li id='li" + k + "' class='slot lifont' onclick=setslottemp(this.id," + diaryuserId
//						+ ",'"+starttime+"','"+endtime+"','"+diarydata.getDuration()+"') data-slot='12:45PM'>" + d + " " + cweekName + "</li>");
//			}
//			
//			}
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm");
			SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("HH:mm");
			
			int ccunt=count/4;
			boolean flag=false;
			int n=0;
			for(int l=0;l<=ccunt;l++){
				buffer.append("<tr>");
				for(int m=1;m<=4;m++){
					n=m;
					isab = notAvailableSlotDAO.checkallbooked(date, DateTimeUtils.convertToInteger(diaryuserId), starttime1);
					blockapmtid = notAvailableSlotDAO.checkBlockslot(date, DateTimeUtils.convertToInteger(diaryuserId), starttime1);
					endtime1=DateTimeUtils.getTotalofTwoTime(starttime1, diarydata.getDuration());
					
					d = "" + starttime1+ " - " + endtime1 + "";
					starttime=starttime1;
					endtime=endtime1;
					starttime1=DateTimeUtils.getTotalofTwoTime(starttime1, diarydata.getDuration());
						diaryuserid =DateTimeUtils.convertToInteger(diaryuserId);
//					buffer.append("<td>"+d+"</td>");
						if(diarydata.getEndTime().equals(starttime)){
							flag=true;
						}else if(simpleDateFormat.parse(diarydata.getEndTime()).before(simpleDateFormat.parse(starttime))){
							flag=true;
						}
						if(flag){
							break;
						}
					if (isab) {
						if(blockapmtid>0){
							NotAvailableSlot slot=notAvailableSlotDAO.getAvailableSlotdata(blockapmtid);
							starttime1=slot.getEndTime();
							if(loginInfo.getUserType()!=5){
								d = "" + starttime+ " - " + starttime1 + "";
								buffer.append("<td class='tdbooked'>"+d+" (Blocked)"+ "  ("+slot.getReasonforblock()+")"+"</td>");
//								m++;
							}else{
							m--;
							}
//							break;
						}else{
						buffer.append("<td class='tdbooked'>"+d+"</td>");
						}
					} else {
		
//						buffer.append("<li id='li" + k + "' class='slot lifont' onclick=setslottemp(this.id," + diaryuserId
//								+ ",'"+starttime+"','"+endtime+"','"+diarydata.getDuration()+"') data-slot='12:45PM'>" + d + " " + cweekName + "</li>");
						buffer.append("<td class='tdavail' onclick=setslottemp(this.id," + diaryuserId+ ",'"+starttime+"','"+endtime+"','"+diarydata.getDuration()+"')>"
								+ ""+d+" </td>");
					
					}
				
				
				
				
				
				
				}
				buffer.append("</tr>");
			}
			String speciality=userProfileDAO.getSpeciality(diaryuserId);
			String appointtypeid="";
			boolean isfollowup=notAvailableSlotDAO.checkLastAppointment(diaryuserid,speciality,loginInfo.getPuresevaclientid());
			if(isfollowup){
				String lastappointdate=notAvailableSlotDAO.getlastappointmentdate(diaryuserid,speciality,loginInfo.getPuresevaclientid());
				long diff=DateTimeUtils.getDifferenceOfTwoDateDBFormat(lastappointdate, date);
				if(diff<=DateTimeUtils.convertToInteger(loginInfo.getOpdfollowup())){
					appointtypeid=notAvailableSlotDAO.getAppointmentTypeId(Constants.OPD_FOLLOWUP_CHARGES);
				}else{
					appointtypeid=notAvailableSlotDAO.getAppointmentTypeId(Constants.OPD_CONSULTATION_CHARGES);
				}
			}else{
				appointtypeid=notAvailableSlotDAO.getAppointmentTypeId(Constants.OPD_CONSULTATION_CHARGES);
			}
			String drnamewithqual=userProfileDAO.getdrnamewithqualification(diaryuserId);
			String specialityname=chargesReportDAO.getChargeDepartmentName(diaryuserid);
			String data=buffer.toString()+"~~"+appointtypeid+"~~"+drnamewithqual+"~~"+specialityname+"~~"+DateTimeUtils.getCommencingDate1(date);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + data + "");

	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String updateclientdata() {
	Connection connection = null;
	try{
		
		connection = Connection_provider.getconnection();
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		String email = request.getParameter("email");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String mob = request.getParameter("mob");
		String dob = request.getParameter("dob");
		String gender = request.getParameter("gender");
		String initial = request.getParameter("title");
		String clientid= request.getParameter("updteclientid");
		
		String town = request.getParameter("town");
		
		Client client2= new Client();
		client2.setRelation(DateTimeUtils.isNull(request.getParameter("relation")));
		client2.setRelativename(DateTimeUtils.isNull(request.getParameter("relativename")));
		client2.setRelativeno(DateTimeUtils.isNull(request.getParameter("relativecontact")));
		client2.setState(DateTimeUtils.isNull(request.getParameter("state")));
		client2.setCity(DateTimeUtils.isNull(request.getParameter("city")));
		client2.setAddress(DateTimeUtils.isNull(request.getParameter("address")));
		client2.setDocType(DateTimeUtils.isNull(request.getParameter("docType")));
		client2.setPincode(DateTimeUtils.isNull(request.getParameter("pin")));
		client2.setTown_village(town);
		int res = clientDAO.updatePureSevaClientData(clientid,email,fname,lname,mob,dob,gender,initial,client2);
		
		String sess=session.getId();
		String proimg=(String) session.getAttribute("propt"+sess);
		String docimg=(String) session.getAttribute("docpt"+sess);
		String relativeimg=(String) session.getAttribute("relativept"+sess);
		session.setAttribute("propt"+sess, null);
		session.setAttribute("docpt"+sess, null);
		session.setAttribute("relativept"+sess, null);
		
		if(!DateTimeUtils.isNull(docimg).equals("")){
			res = clientDAO.updateClientIdenttyDoc(docimg,clientid);
		}
		
		if(!DateTimeUtils.isNull(proimg).equals("")){
			res = clientDAO.updateClientProfleDoc(proimg,clientid);
		}
		
		if(!DateTimeUtils.isNull(relativeimg).equals("")){
			res = clientDAO.updateClientRelativeDoc(relativeimg,clientid);
		}
		
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().write(clientid); 
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
	
}
public String listofregpatient() {
	Connection connection= null;
	try {
		connection= Connection_provider.getconnection();
		String mobno=request.getParameter("mob");
		ClientDAO clientDAO =new JDBCClientDAO(connection);
		ArrayList<Client> clientlist=clientDAO.getAbravationIdOfAllUser(mobno);
		StringBuffer buffer=new StringBuffer();
		int i=0;
		for (Client client : clientlist) {
			buffer.append("<tr>");
			buffer.append("<td><a href='#' onclick='setoriguhid("+i+")'>"+client.getAbrivationid()+"</a></td>");
			buffer.append("<td><a href='#' onclick='setoriguhid("+i+")'>"+client.getFullname()+"</td>");
			buffer.append("<input type='hidden' id='selectabbr"+i+"' value='"+client.getAbrivationid()+"'>");
			buffer.append("</tr>");
		i++;
		}
		
	
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(buffer.toString()); 
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return null;
	
	
}


public String getchargeofbookedopd() throws Exception{
	
	String appmtid = request.getParameter("appmtid");
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
		NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getOTData(appmtid);
		
		//code to get doctorname for markhosp for move appointment 30/04/25
	    NotAvailableSlot notslot=notAvailableSlotDAO.getDoctorname(notAvailableSlot.getInvoiceid());
	
		double charge = Double.parseDouble(notAvailableSlot.getChargeamout());
		double opdregcharge =0;
		if(notAvailableSlot.getProcedure().equals("0")){
			boolean flag = completeAptmDAO.isclientIdInApmt(notAvailableSlot.getClientId());
			if(!flag){
				/*boolean flag2 = completeAptmDAO.isclientRegisterChargeAdded(notAvailableSlot.getClientId(),loginInfo.getId());
				if(!flag2){*/
					opdregcharge =  completeAptmDAO.getOpdRegCharge();
				/*}*/
			}
		}
		
		String data = charge +"~"+opdregcharge+"~"+loginInfo.isOpd_tp_zero_invoice()+"~"+loginInfo.getClinicid1()+"~"+notslot.getDoctorname()+"~"+notslot.getCharge()+"~"+notAvailableSlot.getInvoiceid();
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+data+""); 
		
	}catch (Exception e) {
		// TODO: handle exception
	}finally{
		
		connection.close();
	}
	
	return null;
}

public String getbookingcofirmdata(){
	 Connection connection =null;
	  try {
		   connection = Connection_provider.getconnection();
		   String editAppointId = request.getParameter("apmtid");
		   NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
		   NotAvailableSlot notAvailableSlot=notAvailableSlotDAO.getAvailableSlotdata(Integer.parseInt(editAppointId));
		   ClientDAO clientDAO=new JDBCClientDAO(connection);
		   int paymentdone =0;
		   if(notAvailableSlot.getOpdpmnt()>0){
			   paymentdone=1;
		   }
		   int registrationstatus = clientDAO.getPatientRegistrationStatus(notAvailableSlot.getClientId());
		   int videoconfiernce =0;
		   if(registrationstatus==1 && paymentdone==1){
			   videoconfiernce=1;
		   }
		   
		   String data =registrationstatus +"~~~~"+""+notAvailableSlot.getMed_doc_verify()+"~~~~"+""+paymentdone+"~~~~"+""+videoconfiernce+"~~~~"+""+notAvailableSlot.getReception_vid_verify()+"~~~~"+""+notAvailableSlot.getPending_verify()+"~~~~"+""+DateTimeUtils.isNull(notAvailableSlot.getPending_remark())+"";
		 
		   response.setContentType("text/html");
		   response.setHeader("Cache-Control", "no-cache");
		   response.getWriter().write(""+data+""); 
	  }catch (Exception e) {
		e.printStackTrace();
	}
	return null;
	
}

public String savebookingcofirmdata(){
	 Connection connection =null;
	  try {
		   connection = Connection_provider.getconnection();
		   
		   String editAppointId = request.getParameter("apmtid");
		   String pending = request.getParameter("pending");
		   String remark = request.getParameter("remark");
		   String registration = request.getParameter("registration");
		   String doc_confirm = request.getParameter("doc_confirm");
		   String vid_confirm = request.getParameter("vid_confirm");
		   String clientid = request.getParameter("clientid");
		   
		   ClientDAO clientDAO=new JDBCClientDAO(connection);
		   NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
		   
		   
		   int registrationstatus = clientDAO.getPatientRegistrationStatus(clientid);
		   if(registrationstatus==0){
			   int res = clientDAO.updateRegistrationConfirm(clientid,registration);
		   }
		   int res = clientDAO.updateBookingChecklist(editAppointId,pending,remark,doc_confirm,vid_confirm);
		   
		   if(DateTimeUtils.numberCheck(pending).equals("1")){
			   NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getAvailableSlotdata(DateTimeUtils.convertToInteger(editAppointId));
			   Client client = clientDAO.getClientDetails(clientid);
			   String to = client.getEmail();
			   String cc = "";
			   String subject = "OPD";
			   String notes = ""+notAvailableSlot.getClientName()+" your appointment has been pending for telemed conference call "
						+ "with "+notAvailableSlot.getDiaryUser()+" on date "+DateTimeUtils.getCommencingDate1(notAvailableSlot.getCommencing())+" in between "+notAvailableSlot.getsTime()+" and "+notAvailableSlot.getEndTime()+". Remark: ";
			   notes = notes+" "+ remark;
			   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			   Date date = new Date();
			   String d1 = dateFormat.format(date);
			   String[] temp = d1.split("\\s+");
			   String date1 = temp[0];
			   String time = temp[1];
				
			   String type = "Letter";
			   int appointmentid = 0,invoiceid = 0;
			   EmailLetterLog emailLetterLog = new EmailLetterLog();
			   emailLetterLog.setAppointmentid(appointmentid);
			   emailLetterLog.setInvoiceid(invoiceid);
			   emailLetterLog.setType(type);
			   EmbeddedImageEmailUtil.sendMail(connection, loginInfo.getId(), to, cc, subject, notes,loginInfo,emailLetterLog);
		   }
		   
		   response.setContentType("text/html");
		   response.setHeader("Cache-Control", "no-cache");
		   response.getWriter().write(""+res+""); 
	  }catch (Exception e) {
		e.printStackTrace();
	}
	return null;
	
}


public String updatevideoremark(){
	 Connection connection =null;
	  try {
		   connection = Connection_provider.getconnection();
		   String editAppointId = request.getParameter("id");
		   String remark = request.getParameter("notes");
		   String clientid=request.getParameter("clientid");
		   NotAvailableSlotDAO availableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
		   
		   int res = availableSlotDAO.setnotesandstatus(editAppointId,remark);
		   
		   response.setContentType("text/html");
		   response.setHeader("Cache-Control", "no-cache");
		   response.getWriter().write(""+res+""); 
	  }catch (Exception e) {
		e.printStackTrace();
	}
	return null;
	
}

private void sendMailToPatientVideoConferencing(Client client, String appointmntid) throws Exception {

	Connection connection = null;
	
	try{
		connection = Connection_provider.getconnection();
		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		UserProfileDAO userProfileDAO1 = new JDBCUserProfileDAO(connection);
		NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getAvailableSlotdata(DateTimeUtils.convertToInteger(appointmntid));
		
		String drid = ""+notAvailableSlot.getDiaryUserId();
		
		String speciality = userProfileDAO1.getSpeciality(drid);
		
		Connection connection1 = DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+loginInfo.getClinicUserid()+"",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection1);
		String linkaddress = userProfileDAO.getClinicLinkAddress(loginInfo.getClinicUserid());
		
		String to = client.getEmail();
		String cc = "";
		String subject = "OPD";
		String notes = ""+notAvailableSlot.getClientName()+" your appointment has been confirm for telemed conference call "
				+ "with "+notAvailableSlot.getDiaryUser()+" on date "+DateTimeUtils.getCommencingDate1(notAvailableSlot.getCommencing())+" in between "+notAvailableSlot.getsTime()+" and "+notAvailableSlot.getEndTime()+". Please click on button. ";
		notes = notes+" "+"https://yuvicare.com:8443/YUVICARETEST1/Pureseva?title=&firstname=&lastname=&email=&clinicid="+loginInfo.getClinicUserid()+"&mob=&date=&diaryuserid="+drid+"&gender=&dob=&uhid="+client.getAbrivationid()+"&dept="+speciality+"";
		//notes = notes+" "+"<a href='https://"+linkaddress+":8443/YUVICARE/Pureseva?title=&firstname=&lastname=&email=&clinicid="+loginInfo.getClinicUserid()+"&mob=&date=&diaryuserid="+drid+"&gender=&dob=&uhid="+client.getAbrivationid()+"&dept="+speciality+"'><button style='background-color: #008CBA;border: none;color: white;padding: 15px 32px;text-align: center;text-decoration: none;display: inline-block;font-size: 16px;'>Join Video Call</button></a>";
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String d1 = dateFormat.format(date);
		String[] temp = d1.split("\\s+");
		String date1 = temp[0];
		String time = temp[1];
		
		String type = "Letter";
		int appointmentid = 0,invoiceid = 0;
		EmailLetterLog emailLetterLog = new EmailLetterLog();
		emailLetterLog.setAppointmentid(appointmentid);
		emailLetterLog.setInvoiceid(invoiceid);
		emailLetterLog.setType(type);
		
		SmsService s = new SmsService();
		String message =""+notAvailableSlot.getClientName()+" your appointment has been confirm for telemed conference call "
				+ "with "+notAvailableSlot.getDiaryUser()+" on date "+DateTimeUtils.getCommencingDate1(notAvailableSlot.getCommencing())+" in between "+notAvailableSlot.getsTime()+" and "+notAvailableSlot.getEndTime()+". Please find link sent on your register email.";
		String templateid="";
		s.sendSms(message, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
		
		WhatsAppService whatsapp=new WhatsAppService();
		whatsapp.sendMsg(loginInfo,client.getMobNo(), notes);
		EmbeddedImageEmailUtil.sendMail(connection, loginInfo.getId(), to, cc, subject, notes,loginInfo,emailLetterLog);
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	

	
}
public String updatevideostatus(){
	 Connection connection =null;
	  try {
		   connection = Connection_provider.getconnection();
		   String editAppointId = request.getParameter("id");
		   String clientid=request.getParameter("clientid");
		   UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
		   NotAvailableSlotDAO availableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
		   ClientDAO clientDAO=new JDBCClientDAO(connection);
		   ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
		   
		   int res = availableSlotDAO.setvideostatus(editAppointId);
		   
		   int res1 = chargesAccountProcessingDAO.getMobStatusFromAppoitmentId(editAppointId, clientid);
		   if(res1==1){
			    Client client = clientDAO.getClientDetails(clientid);
				sendMailToPatientVideoConferencing(client,editAppointId);
		   }
		   if(loginInfo.isOpd_user_vid_access()){
			   NotAvailableSlot notAvailableSlot=availableSlotDAO.getAvailableSlotdata(Integer.parseInt(editAppointId));
			   int duserid  = userProfileDAO.getDiaryUserId(loginInfo.getUserId());
				UserProfile userProfile = userProfileDAO.getUserprofileDetails(duserid);
				Client client = clientDAO.getClientDetails(clientid);
				videoconfirmsmstouser(userProfile,client,editAppointId);
		   }
		   response.setContentType("text/html");
		   response.setHeader("Cache-Control", "no-cache");
		   response.getWriter().write(""+res+""); 
	  }catch (Exception e) {
		e.printStackTrace();
	}
	return null;
	
}


public String getremark(){
	Connection connection =null;
	try {
		   connection = Connection_provider.getconnection();
		   String editAppointId = request.getParameter("id");
		   NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
		   NotAvailableSlot notAvailableSlot=notAvailableSlotDAO.getAvailableSlotdata(Integer.parseInt(editAppointId));
		   ClientDAO clientDAO=new JDBCClientDAO(connection);
		   
		   String data =notAvailableSlot.getDoctor_vid_reject_remark() +"~~~~"+""+DateTimeUtils.isNull(notAvailableSlot.getPending_remark())+"";
		 
		   response.setContentType("text/html");
		   response.setHeader("Cache-Control", "no-cache");
		   response.getWriter().write(""+data+""); 
	}catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String saveopdcashdesknew(){
	Connection connection =null;
	try {
		   connection = Connection_provider.getconnection();
		   NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
		   
		   String editAppointId = request.getParameter("selectedid");
		   String invcetype = request.getParameter("invcetype");
		   String howpaid = request.getParameter("howpaid");
		   String totalamount = request.getParameter("totalamount");
		   String discount = request.getParameter("discount");
		   String payAmount = request.getParameter("payAmount");
		   String disctype = request.getParameter("disctype");
		   String bnkname = request.getParameter("bnkname");
		   String opdotcharge = request.getParameter("opdotcharge");
		   String opdotregcharge = request.getParameter("opdotregcharge");
		   
		   NotAvailableSlot notAvailableSlot=notAvailableSlotDAO.getAvailableSlotdata(Integer.parseInt(editAppointId));
		   notAvailableSlot.setBnkname(bnkname);
		   saveOpdCharge(Integer.parseInt(editAppointId), notAvailableSlot, invcetype, howpaid, totalamount, discount,
					payAmount, connection, disctype,opdotcharge,opdotregcharge,"","");
		   
		   if(loginInfo.isMarkhosp()) {
				if(!notAvailableSlot.getInvoiceid().equals("0")) {
					double newcharge=0;
					double debit=notAvailableSlotDAO.getDebitfromChargeinvoice(notAvailableSlot.getInvoiceid());
					if(debit>notAvailableSlot.getCharge()) {
					   newcharge=debit-notAvailableSlot.getCharge();
					}else {
						newcharge=0.0;
					}
					int updtcharge_invoice=notAvailableSlotDAO.updateChargeInvoicedebit(newcharge,notAvailableSlot.getInvoiceid());
					int updt_assessmnt=notAvailableSlotDAO.updateAssessmentCharge(newcharge,notAvailableSlot.getInvoiceid());
					int updt_payment=notAvailableSlotDAO.updateChargesPayment(newcharge,notAvailableSlot.getInvoiceid());
					if(debit>notAvailableSlot.getCharge()) {
					reset(notAvailableSlot.getClientId(),notAvailableSlot.getInvoiceid());
					refundRequest(notAvailableSlot.getClientId(),newcharge);
					}
				}
			}
		   String data="";
		   response.setContentType("text/html");
		   response.setHeader("Cache-Control", "no-cache");
		   response.getWriter().write(""+data+""); 
	}catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

private void refundRequest(String clientId, double newcharge) throws Exception{
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		AdditionalDAO additionalDAO = new JDBCAdditionalDAO(connection);
		/*
		 * String clientId = accountsForm.getClientId(); String clientname =
		 * accountsForm.getClient(); String type = accountsForm.getApmtType(); String
		 * date = accountsForm.getDate(); String location = accountsForm.getLocation();
		 * String creditDebitCharge = accountsForm.getCreditDebitCharge(); String
		 * manualinvoiceid = accountsForm.getManualinvoiceid(); String refundnote =
		 * accountsForm.getRefundnote();
		 */
		//session.setAttribute("manualinvoiceid", manualinvoiceid);
		//session.setAttribute("refundnote", refundnote);
		String clientname=additionalDAO.getClientfullname(clientId);
		String date="";
		if(date==null){
			date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
		} else {
			if(date.equals("")){
				date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			} else {
				date = DateTimeUtils.getCommencingDate1(date);
			}
		}
		
		date = DateTimeUtils.getCommencingDate1(date);

		
		String requestedate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		int parentid = additionalDAO.saveRequestRefund(clientId,clientname,"",date,"1","1","","",loginInfo.getUserId(),requestedate);
		int result = additionalDAO.saveRequestRefundChild(parentid,clientId,loginInfo.getId());
		
		int updt=additionalDAO.updateRefundChild(result,newcharge);
		String ids = additionalDAO.getRequestedRefundDeleteIDs(clientId,loginInfo.getId());
		
		if(ids!=null){
			int reess = additionalDAO.updateRefundParentIds(ids,parentid);
			for (String string : ids.split(",")) {
				if (string.equals("0")) {
					continue;
				}
				int rres = additionalDAO.updateRefundRequestDeleteInvoice(string);
			}
		}
		
		ArrayList<String>  arrayList = additionalDAO.getRefundRequestId(String.valueOf(parentid));
		for (String string : arrayList) {
			int rres = additionalDAO.updateRefundRequestDeleteInvoice(string);
		}
		
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		//for approve request
		ArrayList<UserProfile> arrayList1 = userProfileDAO.getRefundApproveUserList("0");
		for (UserProfile userProfile : arrayList1) {
			int updatetstatus = userProfileDAO.updateBlankNotificationids("noti_ref_appr_ids",userProfile.getUserid());
			int updatecount = userProfileDAO.updateNotificationCount("noti_ref_appr_count",userProfile.getUserid());
			int updateids = userProfileDAO.updateNotificationIdValue("noti_ref_appr_ids",userProfile.getUserid(),""+result);
		}
		
		String userid = loginInfo.getUserId();
		String date1 = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		int del=additionalDAO.approveRefundrequest(""+parentid,userid,date1,"");
		
		refundinvoice(parentid,clientId);
		savecash(parentid,clientId,newcharge);
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		
		connection.close();
	}
	
}


private void savecash(int parentid, String clientId,double newcharge) {
	Connection connection=null;
	try {
	
	connection = Connection_provider.getconnection();
	AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
	AccountLogDAO accountLogDAO = new JDBCAccountLogDAO(connection);
	ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
	IpdDAO ipdDAO = new JDBCIpdDAO(connection);
	String creditDebit = "1";
	//String creditId = accountsForm.getCreditChargeId();
	String date_time = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
	//String sessioncount=accountsForm.getSessioncount();
	if(creditDebit.equalsIgnoreCase("1")){
	AdditionalDAO additionalDAO = new JDBCAdditionalDAO(connection);
	
	

	
	String date = request.getParameter("invoiceDate");
	
	if(date==null){
		date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
	} else if(date.equals("")){
		date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
	} else {
		date = DateTimeUtils.getCommencingDate1(date);
		date = date +" "+DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
	}
	
	ClientDAO clientDAO = new JDBCClientDAO(connection);
	Client client = clientDAO.getClientDetails(clientId);
	String clientname = client.getTitle() + " " +client.getFirstName() + " " + client.getLastName();
	
	
	String type = "Pre_Payment";
	String creditnote = "";
	String charge = ""+newcharge;
	String payBuy = "Client";
	String paymode = "Cash"
	;
	double balance = additionalDAO.getCreditTotal(clientId);
	
	
		
		//reset invoice
		int resetinv = accountsDAO.getMaxResetInv();
		int resetcreditinv = accountsDAO.getMaxResetCreditInv();
		int rinv = 0;
		if(resetinv>resetcreditinv){
			rinv = resetinv + 1;
		}else{
			rinv = resetcreditinv + 1;
		}
		
		String manualinvoiceid = (String)session.getAttribute("manualinvoiceid");
		String refundnote = (String)session.getAttribute("refundnote");
		String refundrequestid = (String)session.getAttribute("refundrequestid");
		
		if(manualinvoiceid!=null){
			if(!manualinvoiceid.equals("")){
				int crinvoiceid = additionalDAO.saveDebitRecord(clientId, type, date, creditnote,payBuy,charge,paymode,balance,1,rinv,loginInfo.getUserId(),manualinvoiceid,refundnote,"","8",loginInfo,null);
				int save = additionalDAO.saveCreditAssessmentRecord(clientId, "", type, date, crinvoiceid,charge,1);
				
				additionalDAO.createSeqnogenProccessForAdvAndRef("8", ""+crinvoiceid, paymode,"");
				
				//Refund Ledger
				if(crinvoiceid>0){
					int maxno = additionalDAO.getMaxAdvno(crinvoiceid);
					String invoicetype = "Refund";
					int paymentids = 0;
					boolean checkceinvoieid=additionalDAO.checkcrinvoiceidexist(crinvoiceid);
					if(!checkceinvoieid) {
					  int u = additionalDAO.updateAdvMaxno(crinvoiceid,maxno,invoicetype,paymentids);
					}
					
					
					String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds("Refund");
					String ledgerid = chargesAccountProcessingDAO.getledgerID(serviceid,paymode,"0");
					
					double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
					lbal = lbal + Double.parseDouble(charge);
					String credit = charge;
					String ldebit = "0";
					String product = "Refund";
					String partyid = clientId;
					String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					int saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,ldebit,credit,lbal,ledgerid,lcommencing,manualinvoiceid,0,"0","0","0","0","0",0,0,"0");
					
					//second effect
					lbal = 0;
					 credit = "0";
					 ldebit = charge;
					 product = "Refund";
					 partyid = clientId;
					 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					 saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,ldebit,credit,lbal,ledgerid,lcommencing,manualinvoiceid,0,"0","0","0","0","0",0,0,"0");
					
				}
			
				if(refundrequestid!=null){
					if(!refundrequestid.equals("")){
						 String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						int res = additionalDAO.updateRefundRequest(refundrequestid,loginInfo.getUserId(),datetime);
						ArrayList<String>  arrayList = additionalDAO.getRefundRequestId(refundrequestid);
						for (String string : arrayList) {
							int rres = additionalDAO.updateRefundDeleteInvoice(string,datetime,loginInfo.getUserId());
						}
						int ress = additionalDAO.updateDebitRecordRefundId(crinvoiceid,refundrequestid);
						
						// reset appointment status
						//  01-08-2019 color change of OPD appointment and that not needed confirm from adarsh sir and dipanjay sir
						 //int appointmentid = accountsDAO.getPaymentAppoinetmentId(manualinvoiceid);
						 //int upstatus = accountsDAO.resetOpdStatus(appointmentid,0);

						
					}
				}
				session.removeAttribute("manualinvoiceid");
				session.removeAttribute("refundnote");
				session.removeAttribute("refundrequestid");
			}else{
				balance = balance - Double.parseDouble(charge);
				int crinvoiceid = additionalDAO.saveDebitRecord(clientId, type, date, creditnote,payBuy,charge,paymode,balance,1,rinv,loginInfo.getUserId(),manualinvoiceid,refundnote,"","8",loginInfo,null);
				int save = additionalDAO.saveCreditAssessmentRecord(clientId, "", type, date, crinvoiceid,charge,1);
				
				additionalDAO.createSeqnogenProccessForAdvAndRef("8", ""+crinvoiceid, paymode,"");
				//  03 Aug 2018 initial againest advance refund
				int maxno = additionalDAO.getMaxAdvno(crinvoiceid);
				String invoicetype = "Refund";
				int paymentids = 0;
				/*
				 * boolean invoiceid_exist=additionalDAO.InviceidPresentOrNot(crinvoiceid);
				 * 
				 * if(!invoiceid_exist) { //for ayushman int u
				 * =additionalDAO.updateAdvMaxno(crinvoiceid,maxno,invoicetype,paymentids); }
				 */
				 
				// commenting beacuse entry repeated in ayushman
				boolean checkceinvoieid=additionalDAO.checkcrinvoiceidexist(crinvoiceid);
				if(!checkceinvoieid) {
				   int u = additionalDAO.updateAdvMaxno(crinvoiceid,maxno,invoicetype,paymentids);
				}
				//  28 May 2018 Its not change refund from advance -> pay button
				if(refundrequestid!=null){
					if(!refundrequestid.equals("")){
						 String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						int res = additionalDAO.updateRefundRequest(refundrequestid,loginInfo.getUserId(),datetime);
						int ress = additionalDAO.updateDebitRecordRefundId(crinvoiceid,refundrequestid);
					}
				}
				session.removeAttribute("manualinvoiceid");
				session.removeAttribute("refundnote");
				session.removeAttribute("refundrequestid");
				
			}
			
		}else{
			balance = balance - Double.parseDouble(charge);
			int crinvoiceid = additionalDAO.saveDebitRecord(clientId, type, date, creditnote,payBuy,charge,paymode,balance,1,rinv,loginInfo.getUserId(),manualinvoiceid,refundnote,"","8",loginInfo,null);
			int save = additionalDAO.saveCreditAssessmentRecord(clientId, "", type, date, crinvoiceid,charge,1);
			
			additionalDAO.createSeqnogenProccessForAdvAndRef("8", ""+crinvoiceid, paymode,"");
			int maxno = additionalDAO.getMaxAdvno(crinvoiceid);
			String invoicetype = "Refund";
			int paymentids = 0;
			boolean checkceinvoieid=additionalDAO.checkcrinvoiceidexist(crinvoiceid);
			if(!checkceinvoieid) {
			  int u = additionalDAO.updateAdvMaxno(crinvoiceid,maxno,invoicetype,paymentids);
			}
			
			//  28 May 2018 Its not change refund from advance -> pay button
			if(refundrequestid!=null){
				if(!refundrequestid.equals("")){
					 String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					int res = additionalDAO.updateRefundRequest(refundrequestid,loginInfo.getUserId(),datetime);
					int ress = additionalDAO.updateDebitRecordRefundId(crinvoiceid,refundrequestid);
				}
			}
			session.removeAttribute("manualinvoiceid");
			session.removeAttribute("refundnote");
			session.removeAttribute("refundrequestid");
		}
		
		
	}
	CommonOpdIpdReport commonOpdIpdReport=new CommonOpdIpdReport();
	commonOpdIpdReport.patientTranssection(clientId,date_time);
	
	
	}catch (Exception e) {
		e.printStackTrace();
	}

	
}


private void refundinvoice(int parentid, String clientId)throws Exception {
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(
				connection);

		ClientDAO clientDAO = new JDBCClientDAO(connection);
		AdditionalDAO additionalDAO = new JDBCAdditionalDAO(connection);
	
		int result = completeAptmDAO.deleteComplteApmt(loginInfo.getId());
		session.removeAttribute("sessionadmissionid");
		String date= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
	
		
		CompleteAppointment completeAppointment = additionalDAO.getRefundRequestData(""+parentid);
		int res = additionalDAO.saveRefundTemp(""+parentid,loginInfo.getId());
		ArrayList<CompleteAppointment> clientChargeListDetail = new ArrayList<CompleteAppointment>();
		clientChargeListDetail = completeAptmDAO.getPatientChrageDetails(clientId,date,completeAppointment.getAppointmentid(),loginInfo.getId());
		
	
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		
		connection.close();
	}


}


private void reset(String clientId, String invoiced)throws Exception {
	
	Connection connection  = null;
	try{
		
		connection  = Connection_provider.getconnection();
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		
	    int invoiceid=Integer.parseInt(invoiced);
		session.setAttribute("sessionselectedclientid", clientId);
//		System.out.println(invoiceid);
		
		movepaymenttoadvance(clientId,invoiceid,connection);
		int rrr = accountsDAO.resetAppliedDiscount(invoiceid);
		
		//update opd status
		 int appointmentid = accountsDAO.getPaymentAppoinetmentId(""+invoiceid+"");
		 int upstatus = accountsDAO.resetOpdInvoiceStatus(appointmentid,invoiceid);

		 //update if charge discounted
		 ArrayList<Accounts>unitchargeList = accountsDAO.getUnitchargeList(invoiceid);
		 
//		
//		ArrayList<Accounts>chargeAssesmentList = accountsDAO.getchargeAssesmenyList(invoiceid);
//		
//		for(Accounts a : unitchargeList){
//			
//			int u = accountsDAO.updateDiscountedCharge(a.getUnitcharge(),a.getId());
//		}
		
		/*for(Accounts accounts : chargeAssesmentList){
			int update = accountsDAO.updateChargeType(Integer.toString(invoiceid), "Charge");
		}*/
//		if(loginInfo.getIskunal()==1){
			int res=accountsDAO.reverseCharges(invoiceid);
			int res1=accountsDAO.reveseAssesmentAmount(invoiceid);
			accountsDAO.updateInvoiceid(invoiceid);
//		}
		int delete = accountsDAO.deleteChargeAssesmentList(invoiceid);
		String userid = loginInfo.getUserId();
		String date ="";
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		date = dateFormat.format(cal.getTime());	
		//update status of invoice
		int result=accountsDAO.updateDeleteStatus(invoiceid,"",userid,date);
		
		//int del = accountsDAO.deleteChargeInvoice(invoiceid);
//		result=accountsDAO.deleteChargesPayment(invoiceid); 
		result=accountsDAO.updatechargePaymrnt(invoiceid);
		session.setAttribute("sessionselectedinvoice", String.valueOf(invoiceid));
		//accountsForm.setMessage("The invoice has been deleted Sucessfully!!");
		addActionMessage("The invoice has been deleted Sucessfully!!");
		session.setAttribute("sessionactiontype", request.getParameter("cancelinv"));
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		
		connection.close();
	}
	
}


private void movepaymenttoadvance(String clientId, int invoiceid, Connection connection) {
	try{
		
		ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
		ArrayList<Accounts>paymentList = chargesAccountProcessingDAO.getPaymentList(invoiceid);
		AdditionalDAO additionalDAO = new JDBCAdditionalDAO(connection);
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		 ClinicDAO clinicDAO=new JDBCClinicDAO(connection);
		 ClientDAO clientDAO = new JDBCClientDAO(connection);
		 double balance =0;
		for(Accounts a : paymentList){
			String date = a.getCommencing();
			
			Client client = clientDAO.getClientDetails(clientId);
			String clientname = client.getTitle() + " " +client.getFirstName() + " " + client.getLastName();
			
			String type = "Pre_Payment";
			String creditnote = "";
			String charge = a.getAmountx();
			String payBuy = "Client";
			String paymode = a.getPaymentmode();
			
			 balance = additionalDAO.getCreditTotal(clientId);
			
			
				
				//reset invoice
				creditnote= request.getParameter("paymentNote");
				int resetinv = accountsDAO.getMaxResetInv();
				int resetcreditinv = accountsDAO.getMaxResetCreditInv();
				int rinv = 0;
				if(resetinv>resetcreditinv){
					rinv = resetinv + 1;
				}else{
					rinv = resetcreditinv + 1;
				}
				String useridpay=additionalDAO.getpaymentUserId(invoiceid);
				balance = balance + Double.parseDouble(charge);
				int crinvoiceid = additionalDAO.saveCreditRecord(clientId, type, date, creditnote,payBuy,charge,paymode,balance,rinv,useridpay,invoiceid);
				int flg=accountsDAO.updatecreditaccountsts(crinvoiceid);
				
				additionalDAO.createSeqnogenProccessForAdvAndRef("0", ""+crinvoiceid, "","");
				//lokesh
				if(crinvoiceid>0){
				String practid = chargesAccountProcessingDAO.getInvoiceDoctorid(invoiceid);
					int pr= additionalDAO.setPractionerinCreditacc(crinvoiceid, practid);
				}
				
				CompleteAppointment appointment = new CompleteAppointment();
				appointment.setApmtType("payment converted to advance");
				appointment.setChargedescription(a.getAmountx());
				int result = additionalDAO.saveCreditAssessment(clientId, clientname, type, date, crinvoiceid,appointment);
				
				int save = additionalDAO.saveCreditAssessmentRecord(clientId, "", type, date, crinvoiceid,charge,1);
				
				int maxno = additionalDAO.getMaxAdvno(crinvoiceid);
				String invoicetype = "Advance";
				int paymentids = 0;
				int u = additionalDAO.updateAdvMaxno(crinvoiceid,maxno,invoicetype,paymentids);

				//Advance Ledger
				if(crinvoiceid>0){
					String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds("Advance & Refund");
					String ledgerid = chargesAccountProcessingDAO.getledgerID(serviceid,paymode,"0");
					
					double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
					lbal = lbal + Double.parseDouble(charge);
					String credit = "0";
					String ldebit = charge;
					String product = "Advance";
					String partyid = clientId;
					String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					int saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,ldebit,credit,lbal,ledgerid,lcommencing,""+crinvoiceid+"",0,"0","0","0","0","0",0,0,"0");
					
					//second effect
					lbal = 0;
					 credit = charge;
					 ldebit = "0";
					 product = "Advance";
					 partyid = clientId;
					 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					 saveledger = chargesAccountProcessingDAO.saveLedger(partyid,product,ldebit,credit,lbal,ledgerid,lcommencing,""+crinvoiceid+"",0,"0","0","0","0","0",0,0,"0");
					
				}
				
		}
//		    Clinic clinic=clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
//		    Client client = clientDAO.getClientDetails(clientId);
//			String clientname = client.getTitle() + " " +client.getFirstName() + " " + client.getLastName();
//		     String msg=""+clientname+", Your Invoice has been cancelled and paid amount Rs. "+balance+" has been move into Advance. From- "+clinic.getClinicName()+"";
//		     SmsService service= new SmsService();
//		     service.sendSms(msg, client.getMobNo(), loginInfo, new EmailLetterLog());
		
	}catch (Exception e) {
		// TODO: handle exception
	}
}


public String getdrinfo(){
	Connection connection =null;
	try {
		   connection = Connection_provider.getconnection();
		   String diaryuser = request.getParameter("id");
		   UserProfileDAO profileDAO= new JDBCUserProfileDAO(connection);
		   ChargesReportDAO chargesReportDAO=new JDBCChargesReportDAO(connection);
		   UserProfile userProfile=profileDAO.getUserprofileDetails(DateTimeUtils.convertToInteger(diaryuser));
		   
		   String data =userProfile.getFullname() +"~~~~"+DateTimeUtils.isNull(userProfile.getQualification())+
		   "~~~~"+DateTimeUtils.isNull(chargesReportDAO.getChargeDepartmentName(DateTimeUtils.convertToInteger(diaryuser)))+""
		   + "~~~~"+DateTimeUtils.isNull(userProfile.getRegisterno())+"~~~~"+DateTimeUtils.isNull(userProfile.getMobile())+"";
		 
		   response.setContentType("text/html");
		   response.setHeader("Cache-Control", "no-cache");
		   response.getWriter().write(""+data+""); 
	}catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String verifypatientreg(){
	 Connection connection =null;
	  try {
		   connection = Connection_provider.getconnection();
		   
		   String registration = request.getParameter("registration");
		   String clientid = request.getParameter("clientid");
		   
		   ClientDAO clientDAO=new JDBCClientDAO(connection);
		   int res =0;
		   int registrationstatus = clientDAO.getPatientRegistrationStatus(clientid);
		   if(registrationstatus==0){
			   res = clientDAO.updateRegistrationConfirm(clientid,registration);
		   }
		   
		   
		   response.setContentType("text/html");
		   response.setHeader("Cache-Control", "no-cache");
		   response.getWriter().write(""+res+""); 
	  }catch (Exception e) {
		e.printStackTrace();
	}
	return null;
	
}
public String newdisplaytoken() throws SQLException {

	if (!verifyLogin(request)) {
		return "login";
	}
	Connection connection = null;
	

	int t = 1;
	try{
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		String ndate = jsonObject.getString("ndate");
		String nduserid = jsonObject.getString("nduserid");
		String opdlogstatus = jsonObject.getString("opdstatus");
		opdlogstatus="4";
		connection = Connection_provider.getconnection();
		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		//Opd dashboard display data
		
		ndate = DateTimeUtils.getCommencingDate(ndate);	
		
		ArrayList<NotAvailableSlot>opdlist = notAvailableSlotDAO.getNewOpdList(ndate,nduserid,loginInfo,opdlogstatus,"");
		
		StringBuffer str = new StringBuffer();
		int i = 1;
		StringBuffer buffer=new StringBuffer();
		String opdicon="";
		UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
		ChargesReportDAO chargesReportDAO=new JDBCChargesReportDAO(connection);
		
		for(NotAvailableSlot n : opdlist){
			UserProfile userProfile=profileDAO.getUserprofileDetails(n.getDiaryUserId());
			boolean checkattachment=notAvailableSlotDAO.checkAttachment(n.getId());
			opdicon="&nbsp;&nbsp;<a><i class='fa fa-user fa-2x' title='View Profile' style='color: black;' onclick=openSamll('puresevaprofileClient?id="+n.getClientId()+"')> </i></a>";
					/*+ "&nbsp;&nbsp;<a href='#' onclick='openCancelApmtPopupNEW("+n.getId()+")'><i class='fa fa-times fa-2x' aria-hidden='true' title='Cancel Appointment' style='color: black;'></i></a>";*/
			String color = "";
			String textcolor="";
			String remarkicon="";
			String paymentsts="Unpaid";
			String opdapptsts="Booked";
			String vidicon="";
			String scanqr="";
			String cancelapmt="";
			int popupstatus = 0;
			if(n.getStatus().equals("0")){
				color = "rgb(252, 186, 99)";
				popupstatus = 1;
				if(loginInfo.getUserType()==5){
					popupstatus = 5;
					paymentsts="Unpaid";
					opdapptsts="Request";
//					opdicon="&nbsp;&nbsp;<a href='#' onclick='openscanpopup("+n.getId()+")'><i class='fa fa-qrcode fa-2x' aria-hidden='true' style='color: black;' title='Payment'></i></a>"
//							+ "&nbsp;&nbsp;<a href='#' onclick='openCancelApmtPopupNEW("+n.getId()+")'><i class='fa fa-times fa-2x' aria-hidden='true' style='color: black;' title='Cancel Appointment'></i></a>";
					scanqr="<a href='#' onclick='openscanpopup("+n.getId()+")'><i class='fa fa-qrcode fa-2x' aria-hidden='true' style='color: black;' title='Payment'></i></a>";
					cancelapmt="<a href='#' onclick='openCancelApmtPopupNEW("+n.getId()+")'><i class='fa fa-times fa-2x' aria-hidden='true' style='color: black;' title='Cancel Appointment'></i></a>";
//							+ "&nbsp;&nbsp;<a><i class='fa fa-edit fa-2x' style='color: black;'  onclick=openEditClientPrintPopup("+n.getClientId()+") title='Edit Profile'> </i></a>";
					
					if(!n.getPending_remark().equals("") || !n.getDoctor_vid_reject_remark().equals("") ){
//					opdicon=opdicon+" "+remarkicon;
						remarkicon="&nbsp;&nbsp;<a href='#' onclick='openremarkPopup("+n.getClientId()+","+n.getId()+")'><i class='fa fa-commenting fa-2x' aria-hidden='true' style='color: black;' title='Remark'></i></a>";
					}
				}
			}
			if(n.isAppointmentCompleted()){
				color = "rgb(129, 171, 109)";
				popupstatus = 2;
				if(loginInfo.getUserType()==5){
					popupstatus = 0;
				}
			}
			if(!n.getInvoiceid().equals("0")){
				color = "rgb(204, 204, 204)";
				
				popupstatus = 3;
				if(loginInfo.getUserType()==5){
					popupstatus = 6;
					paymentsts="Received";
					opdapptsts="Confirm";
					opdicon="";
					cancelapmt="";
//					vidicon = "&nbsp;&nbsp;<a href='#' onclick='openvideoconfirm("+n.getClientId()+","+n.getId()+")'><i class='fa fa-video-camera fa-2x' aria-hidden='true' style='color: black;'title='Confirm Video Call'></i></a>";
//					opdicon="&nbsp;&nbsp;<a><i class='fa fa-video-camera fa-2x' style='color: black;' onclick=openPopup('vdyoEmr?clid="+n.getClientId()+"&condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"') title='Video Call'> </i></a>";
							/*+ "&nbsp;&nbsp;<a href='#' onclick='openremarkPopup("+n.getClientId()+","+n.getId()+")'><i class='fa fa-commenting fa-2x' aria-hidden='true' style='color: black;' title='Remark'></i></a>";*/
//							+ "&nbsp;&nbsp;<a><i class='fa fa-user fa-2x' style='color: black;' onclick=openSamll('puresevaprofileClient?id="+n.getClientId()+"') title='View Profile'> </i></a>"
//							+ "&nbsp;&nbsp;<a><i class='fa fa-edit fa-2x' style='color: black;'  onclick=openEditClientPrintPopup("+n.getClientId()+") title='Edit Profile'> </i></a>";
					
					if(!n.getPending_remark().equals("") || !n.getDoctor_vid_reject_remark().equals("") ){
//					opdicon=opdicon+" "+remarkicon;
						remarkicon="&nbsp;&nbsp;<a href='#' onclick='openremarkPopup("+n.getClientId()+","+n.getId()+")'><i class='fa fa-commenting fa-2x' aria-hidden='true' style='color: black;' title='Remark'></i></a>";
					}
				}
			}
			if(n.getOpdpmnt()>0){
				color = "rgb(204, 204, 204)";
				if(loginInfo.getUserType()!=5){
				opdicon="&nbsp;&nbsp;<a><i class='fa fa-user fa-2x' onclick=openSamll('puresevaprofileClient?id="+n.getClientId()+"') title='View Profile'> </i></a>";
						/*+ "&nbsp;&nbsp;<a href='#' onclick='openCancelApmtPopupNEW("+n.getId()+")'><i class='fa fa-times fa-2x' aria-hidden='true' style='color: black;' title='Cancel Appointment'></i></a>";*/
				}
				}
			if(n.getDrcompleted()==1){
				
				color = "blue";
				
				popupstatus = 3;
				if(loginInfo.getUserType()==5){
					popupstatus = 0;
				}
			}
			
			if(n.getArrivedStatus()==1){
				textcolor="color: green !important;";
			}
			 if(n.getArrivedStatus()==2){
				//textcolor="color: white !important;";
				 color="green !important";
				 
			}
			 if(n.isDna()){
				 color = "rgb(255,0,0)";
				 popupstatus = 4;
				 if(loginInfo.getUserType()==5){
						popupstatus = 0;
					}	 
			 }
			 if(n.getApmttypetext().equals("OPD FREE") && Integer.parseInt(n.getInvoiceid())>0){
				 color = "rgb(204, 204, 204)";
					
					popupstatus = 3;
					if(n.getDrcompleted()==1){
						
						color = "rgb(92, 241, 213)";
						
						popupstatus = 3;
					}
					if(loginInfo.getUserType()==5){
						popupstatus = 0;
					}
			 }
			 String attachicon="";
			 if(loginInfo.getUserType()==5){
				 attachicon="&nbsp;&nbsp;<a href='#' onclick='openpatientattachment("+n.getClientId()+","+n.getId()+")'><i class='fa fa-paperclip fa-2x' aria-hidden='true' style='color: black;'title='Attachment'></i></a>";	 
			 }else{
				 if(checkattachment){
					 attachicon="&nbsp;&nbsp;<a href='#' onclick='openpatientattachment("+n.getClientId()+","+n.getId()+")'><i class='fa fa-paperclip fa-2x' aria-hidden='true' style='color: black;'title='Attachment'></i></a>";
					 opdicon=opdicon+" "+attachicon; 
				 }	 
			 }
//			 opdicon=opdicon+" "+attachicon; 
			 String newicon="";
			 if(n.getMobstatusnew()==1){
				 if(loginInfo.getUserType()!=5){
					 if(!loginInfo.getJobTitle().equals("Practitioner")){
						 newicon = "&nbsp;&nbsp;<a href='#' onclick='openbookingconfirmchecklist("+n.getClientId()+","+n.getId()+")'><i class='fa fa-list fa-2x' aria-hidden='true' style='color: black;'title='Booking Confirm'></i></a>";
					 }
				 }
			 }
			 
			 if(loginInfo.getUserType()!=5){
			 if(n.getMobstatusnew()==1){
				 if(n.getReception_vid_verify()==1){

					 if(n.getDoctor_vid_verify()==1){
//					 vidicon="&nbsp;&nbsp;<a title='Video Call' href='vdyoEmr?clid="+n.getClientId()+"&condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"' target='blank'><span class='glyphicon glyphicon-facetime-video'></span></a>";
						 opdapptsts="Dr Confirm";
					 vidicon="&nbsp;&nbsp;<a><i class='fa fa-video-camera fa-2x' style='color: green;' onclick=openPopup('vdyoEmr?clid="+n.getClientId()+"&condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"') title='Video Call'> </i></a>";
					 	}else if(n.getDoctor_vid_reject_remark()==""){
					 		 opdapptsts="";
					 		vidicon = "&nbsp;&nbsp;<a href='#' onclick='openvideoconfirm("+n.getClientId()+","+n.getId()+")'><i class='fa fa-video-camera fa-2x' aria-hidden='true' style='color: black;'title='Confirm Video Call'></i></a>";
					 	}else{
					 		vidicon="";
					 	}
					 }
				 }
			 }else if (loginInfo.isOpd_user_vid_access()) {
				 if(loginInfo.isDr_opd_vid()){
				 if(n.getDoctor_vid_verify()==1){
					 vidicon="&nbsp;&nbsp;<a><i class='fa fa-video-camera fa-2x' style='color: green;' onclick=openPopup('vdyoEmr?clid="+n.getClientId()+"&condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"') title='Video Call'> </i></a>";
					 	}
				 	else{
					 		 
					 		vidicon = "&nbsp;&nbsp;<a href='#' onclick='openvideoconfirm("+n.getClientId()+","+n.getId()+")'><i class='fa fa-video-camera fa-2x' aria-hidden='true' style='color: black;'title='Confirm Video Call'></i></a>";
					 	}
				 }else{
					 vidicon = "&nbsp;&nbsp;<a href='#'><i class='fa fa-video-camera fa-2x' aria-hidden='true' style='color: black;'title='Wait for Confirmation'></i></a>";
				 }
				
			}else{
				 if(n.getMobstatusnew()==1){
					 if(n.getReception_vid_verify()==1){

						 if(n.getDoctor_vid_verify()==1){
							 vidicon="&nbsp;&nbsp;<a><i class='fa fa-video-camera fa-2x' style='color: green;' onclick=openPopup('vdyoEmr?clid="+n.getClientId()+"&condid="+n.getCondition()+"&pid="+nduserid+"&appmtsids="+n.getId()+"') title='Video Call'> </i></a>";
						 }
						 }
						 
						 }
			 }
			 if(n.isDna()){
				 //  15-07-2020 when DNA set then hide video icon
				 vidicon="";
			 }
			 
			 if(n.getDrcompleted()==1){
				//  15-07-2020 when appointment complted then hide video icon
				 vidicon="";
			 }
			 
			 opdicon=opdicon+" "+newicon+ " "+vidicon; 
			 
			 if( loginInfo.getUserType()!=5){
				 if(!loginInfo.isOpd_video_icon_show()){
				 opdicon="";
				 }
			 }
			 if(loginInfo.getUserType()!=5){
				 str.append("<tr id='token"+i+"'style='background-color:"+color+";font-weight:bold;color:white;'>");
					str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+")'>"+i+"</td>");
					str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+")'>"+DateTimeUtils.getdatewithmonth(n.getOpdbookdate())+" "+n.getOpdbooktime().split(":")[0]+":"+n.getOpdbooktime().split(":")[1]+"</td>");
					str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+")'>"+DateTimeUtils.getdatewithmonth(DateTimeUtils.getCommencingDate1(n.getCommencing()))+" "+n.getsTime()+"</td>");
					str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+")'>"+n.getDuration()+"</td>");
					str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+")'>"+n.getAbrivationid()+"</td>");
//					str.append("<td style='text-align: left;'>"+n.getClientName()+" "+opdicon+"</td>");
					str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+")'>"+n.getClientName()+"</td>");
					str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+")'>"+n.getToken()+"</td>");
					str.append("<td>"+opdicon+"</td>");
					str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+")'>"+n.getAgegender()+"</td>");
					
				
				str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='shownewdto("+n.getId()+","+nduserid+","+n.getCondition()+","+n.getClientId()+","+i+","+popupstatus+","+n.getPbodytemplate()+","+n.getPbodyeditedtmplate()+")'>"+n.getApmttypetext()+"</td>");
				str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='setClientIsBeingSeentoken("+n.getId()+","+i+")'><i class='fa fa-history fa-2x'title='History'></i></td>");
				str.append("<td style='text-align: left;cursor:pointer;"+textcolor+";font-size:13px' onclick='withpaymentCompleteAppointmenttoken("+n.getId()+","+i+")'><img src='popicons/cbs.png' alt='...' class='img-responsive'></td>");

				/*str.append("<td style='text-align: left;'><a href='#'>Day_To_Day</a></td>");*/
				str.append("<input type = 'hidden' name='ncname' id='ncname"+i+"' value='"+n.getClientName()+"'>");
				str.append("<input type = 'hidden' name='nwhopay' id='nwhopay"+i+"' value='"+n.getWhopay()+"'>");
			 
			 
			 }else{
				 
				 	str.append("<tr style='background-color:"+color+";cursor:pointer;"+textcolor+";font-size:13px'>");
				 	str.append("<td style='text-align: left;'>"+i+"</td>");
					str.append("<td style='text-align: left;'>"+DateTimeUtils.getdatewithmonth(n.getOpdbookdate())+" "+n.getOpdbooktime().split(":")[0]+":"+n.getOpdbooktime().split(":")[1]+"</td>");
					str.append("<td style='text-align: left;'>"+DateTimeUtils.getdatewithmonth(DateTimeUtils.getCommencingDate1(n.getCommencing()))+" "+n.getsTime()+"</td>");
					str.append("<td style='text-align: center;' onclick='showdrprofile("+n.getDiaryUserId()+")'><i class='fa fa-user-md fa-2x' aria-hidden='true' style='color: crimson;'title='Doctor Profile'></i>&nbsp;&nbsp;"+userProfile.getFullname()+"</td>");
					str.append("<td>"+scanqr+"</td>");
					str.append("<td>"+vidicon+"</td>");
					str.append("<td>"+remarkicon+"</td>");
					str.append("<td>"+attachicon+"</td>");
					str.append("<td>"+cancelapmt+"</td>");
//							+ "<br>"+userProfile.getQualification()+"<br>"
//							+ ""+chargesReportDAO.getChargeDepartmentName(n.getDiaryUserId())+"<br>"
//									+ "Reg No. "+DateTimeUtils.isNull(userProfile.getRegisterno())+"<br>Phone No. "+userProfile.getMobile()+"<br>"+opdicon+"</td>");
					str.append("<td style='text-align: left;'>"+paymentsts+"</td>");
					
					str.append("<td style='text-align: left;'>"+opdapptsts+" </td>");
					/*str.append("<td style='text-align: left;'><a href='#'>Day_To_Day</a></td>");*/
					str.append("<input type = 'hidden' name='ncname' id='ncname"+i+"' value='"+n.getClientName()+"'>");
					str.append("<input type = 'hidden' name='nwhopay' id='nwhopay"+i+"' value='"+n.getWhopay()+"'>");
			 }
			
			i++;
			
			str.append("</tr>");
		}
		UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
		NotAvailableSlot n = notAvailableSlotDAO.getNewOpdDiaryUserData(ndate,nduserid);
		String drnamewithqual=userProfileDAO.getdrnamewithqualification(nduserid);
		JSONObject jsonobj = new JSONObject();
		
		jsonobj.put("opdtable", str.toString());
		jsonobj.put("slotid", n.getId());
		jsonobj.put("drname", drnamewithqual);
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
		
	}catch (Exception e) {
		// TODO: handle exception
	}finally{
		
		connection.close();
	}
	
	return null;
	
}

public String updateorsaveinvcharge() throws Exception{
	if (!verifyLogin(request)) {

		return "login";
	}
	Connection connection = null;
	try {
		
		connection = Connection_provider.getconnection();
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		String alternativetext=jsonObject.getString("alternativetext");
	    String wardid=jsonObject.getString("wardid");
	    String chargess=jsonObject.getString("chargess");
	    String id = jsonObject.getString("id");
	    String invtypename = jsonObject.getString("invtypename");
	    String paytype ="0";
	    InvestigationMasterDAO masterDAO = new JDBCInvestigationMasterDAO(connection);
	    int res = masterDAO.updateInvestigationTypeData(id,alternativetext,chargess);
	    int aptid = masterDAO.getInvChargeId(invtypename,wardid,paytype);
	    
	    if(aptid>0){
	    	AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
	    	String previouscharge = appointmentTypeDAO.getChargeAmountFromId(aptid);
	    	res = masterDAO.updateInvApmentTypeData(aptid,chargess);
	    	int log=0;
	    	log= appointmentTypeDAO.insertappointmentlog(loginInfo.getUserId(), chargess, ""+aptid,previouscharge);
	    }else{
	    	Master master = new Master();
	    	master.setName(invtypename);
	    	master.setCharge(chargess);
	    	res = masterDAO.addInvAppointType(master,Integer.parseInt(wardid));
	    }
	    
	    JSONObject jsonobj = new JSONObject();
	    jsonobj.put("ressuuult", "1");
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	    
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}
	return null;
}

public String gynicpriscdata()throws Exception{
	 
	 Connection connection = null;
	 
	 try{
		 connection = Connection_provider.getconnection();
		 IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		 EmrDAO emrDAO = new JDBCEmrDAO(connection);
		
		 String clientid = request.getParameter("clientid");
		 String lastappointmentid = request.getParameter("lastappointmentid");
		 ArrayList<Priscription>dischargePriscList = new ArrayList<Priscription>();
		 dischargePriscList=ipdDAO.getGynicPrescList(lastappointmentid);
		ArrayList<Master> dosageList = emrDAO.getDosageList();
		
		int size = dischargePriscList.size();
		String totalchildmedids ="0";
		if (size > 0) {
			totalchildmedids = dischargePriscList.get(size - 1).getTotalchildmedids();
		}
		
		int i = 0;
		StringBuffer str = new StringBuffer();
		for(Priscription priscription : dischargePriscList){
			str.append("<tr>");
			/*str.append("<td><input type='number' class='form-control' name='dicpriscmed"+priscription.getId()+"' value='"+priscription.getDispriscsrno()+"'></td>");*/
			str.append("<td>"+priscription.getDispriscsrno()+"</td/>");
			str.append("<td>"+priscription.getMdicinenametxt()+"</td/>");
			//  05 June 2018
			str.append("<td>"+priscription.getPriscdose()+"");
			/*str.append("<select id='discpriscdose"+priscription.getId()+"' name='discpriscdose"+priscription.getId()+"' class='form-control chosen-select'>");
			for (Master master : dosageList) {
				if(priscription.getPriscdose()!=null){
					if(master.getName()!=null){
						if(master.getName().equals(priscription.getPriscdose())){
							str.append("<option value='"+master.getName()+"' selected='selected'>"+master.getName()+"</option>");
						}else{
							str.append("<option value='"+master.getName()+"'>"+master.getName()+"</option>");
						}
					}else{
						str.append("<option value='"+master.getName()+"'>"+master.getName()+"</option>");
					}
				}else{
					str.append("<option value='"+master.getName()+"'>"+master.getName()+"</option>");
				}
			}
			str.append("</select>");*/
			str.append("</td>");
			/*str.append("<td><input type='number' class='form-control' name='dicpriscdays"+priscription.getId()+"' value='"+priscription.getPriscdays()+"'></td>");*/
			str.append("<td>"+priscription.getPriscdays()+"</td/>");
			str.append("<td>"+priscription.getDosenotes()+"</td/>");
			str.append("<td>"+priscription.getStrength()+"</td/>");
			str.append("<td><a onclick='deletegynicmed(this,"+priscription.getId()+")' ><i class='fa fa-trash'></i></a></td>");
			str.append("</tr>");
			i++;
		}
		str.append("<input type='hidden' name='totalchildmedids' value='"+totalchildmedids+"'>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+str.toString()+"");
		 
	 }catch (Exception e) {
		e.printStackTrace();
	}
	 finally{
			connection.close();
		}
	 return null;
}

public String getinvsgynicdata()throws Exception{
	 try{
		String str="";
		int i =0;
		ArrayList<Investigation>investigationList = (ArrayList<Investigation>)session.getAttribute("invstList");
		String last="";
		for(Investigation invs  : investigationList){
			if(i==0){
				if(!last.equals(invs.getInvsttype())){
					str = invs.getInvsttype();
					last = invs.getInvsttype();
				}
				
			}else{
				if(!last.equals(invs.getInvsttype())){
					str = str +","+invs.getInvsttype();
					last = invs.getInvsttype();
				}
			}
			i++;
		}
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+str+"");
	 }catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
public void videoconfirmsmstouser(UserProfile userProfile, Client client, String editAppointId)  {
	Connection connection = null;
	try {
		
		connection = Connection_provider.getconnection();
		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getAvailableSlotdata(DateTimeUtils.convertToInteger(editAppointId));
		SmsService s = new SmsService();
		String message =""+notAvailableSlot.getClientName()+" your appointment has been confirm for telemed conference call "
				+ "with "+notAvailableSlot.getDiaryUser()+" on date "+DateTimeUtils.getCommencingDate1(notAvailableSlot.getCommencing())+" in between "+notAvailableSlot.getsTime()+" and "+notAvailableSlot.getEndTime()+".";
		String templateid="";
		s.sendSms(message, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
    	
	} catch (Exception e) {
		// TODO: handle exception
	}
}

public String getpatientfordatalist() throws Exception {
	Connection connection = null;
	try {

		if (!verifyLogin(request)) {
			return "login";
		}
		
		connection = Connection_provider.getconnection();
		IpdDAO ipddao = new JDBCIpdDAO(connection);
		
		StringBuffer strbuffer = new StringBuffer();
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);

		String val = jsonObject.getString("val");
		String colmnname = jsonObject.getString("colmnname");
		
		StringBuffer str = new StringBuffer();
		
		ArrayList<String> arrayList = new ArrayList<String>();
		if(!DateTimeUtils.isNull(val).equals("")){
			arrayList = ipddao.getPatientNameDataList(val,colmnname);
		}
		for (String string : arrayList) {
			str.append("<option value="+string+">"+string+"</option>");
		}
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("datalistdata", str.toString());
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);

	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
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
		
		StringBuffer str = new StringBuffer();
		

		
		str.append("<table class='table table-bordered' > ");
		str.append("<thead>");
		str.append("<tr class='bg-info'>");
		str.append("<th>UHID</th> ");
		str.append("<th>Patient Name</th> ");
		str.append("<th>Pay By</th> ");
		str.append("<th>Age</th> "); 
		str.append("<th>Mobile No.</th> ");
		str.append("<th>Aadhar No</th> ");
		str.append("<th>Father Name</th> ");
		str.append("<th>Mother Name</th> ");
		str.append("</tr>");
		str.append("</thead>");
		
		str.append("<tbody>");
		for(Client client1:allPatientList){
			String name = client1.getTitle()+" "+client1.getFirstName()+" "+client1.getMiddlename()+" "+client1.getLastName(); 	
			String color = "";
			if(!client1.getCasualtyid().equals("0")){
				color = "#f5a0b4";
			}
			String firstName= client1.getFirstName();
			
			int payee=0;
			if(client1.getWhopay()!=null){
				
				if(client1.getWhopay().equals("Client")){
					 payee=0;
				} else {
					payee=1;
				}
				
			}
			
			String whopay="";
			if(client1.getWhopay()!=null){
				
					if(client1.getWhopay().equals(Constants.PAY_BY_THIRD_PARTY)){
						whopay = "Third Party";
					}else{
						whopay = "Client";
					}
				
			}
		str.append("<tr style='background-color:"+color+";cursor: pointer;' onclick = setPatientName('"+client1.getId()+"','"+client1.getTypeName()+"','"+payee+"') >");
		if(client1.getAbrivationid()==null){
			str.append("<td>"+client1.getId()+"</td>");
		}else{
			str.append("<td>"+client1.getAbrivationid()+"</td>");
		}
		
		str.append("<input type='hidden' id='firstnameid"+client1.getId()+"' value='"+client1.getFirstName()+"'>");
		
	//	String data=client1.getAddress()+"~"+client1.getDob()+"~"+client1.getEmergencyContName()+"~"+client1.getEmergencyContNo()+"~"+client1.getRelation();
		str.append("<td style='cursor: pointer;'; >"+name+"</td>");
		str.append("<td>"+whopay+"</td>");
		String age= DateTimeUtils.isNull(DateTimeUtils.getAge1(client1.getDob()));
		str.append("<td>"+age+"</td>");
		str.append("<td>"+client1.getMobNo()+"</td>");
		str.append("<td>"+DateTimeUtils.isNull(client1.getAdhno())+"</td>");
		
		str.append("<td>"+DateTimeUtils.isNull(client1.getFathername())+"</td>");
		str.append("<td>"+DateTimeUtils.isNull(client1.getMothername())+"</td>");
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

public String getspecialitydata(){
	 Connection connection =null;
	  try {
		  int res=0;
		   connection = Connection_provider.getconnection();
		   UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		   String val = request.getParameter("val");
		   UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(val));
		   response.setContentType("text/html");
		   response.setHeader("Cache-Control", "no-cache");
		   response.getWriter().write(""+userProfile.getSpecialization()+""); 
		  
} catch (Exception e) {
	e.printStackTrace();
}

return null;

}

public String openconsumedata(){
	if(!verifyLogin(request)){
		return "login";
	}
	
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		NotAvailableSlotDAO availableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String vaccinationid=jsonObject.getString("vaccinationid");
		
		String location = availableSlotDAO.getVaccinationLocation();
		
		NotAvailableSlot availableSlot = availableSlotDAO.getVaccinationdataFromId(vaccinationid);
		
		ArrayList<Product> allMedicieneList = new ArrayList<Product>();
		if(!DateTimeUtils.numberCheck(location).equals("0")){
			allMedicieneList =	inventoryProductDAO.getVaccinationProductList(location,availableSlot.getMastername());
		}
		StringBuffer buffer= new StringBuffer();
		buffer.append("<select class='form-control chosen' id='newmedicine' name='mdicinename' >");
		buffer.append("<option value='0'>Select Product</option>");
		for(Product product:allMedicieneList){
			buffer.append("<option value='"+product.getId()+"'>"+product.getGenericname()+"</option>");
		}
		buffer.append("</select>");
		
		jsonObject.put("datax",buffer.toString());
		jsonObject.put("clientid",availableSlot.getClientId());
		jsonObject.put("clientname",availableSlot.getClientName());
		jsonObject.put("vaccinationname",availableSlot.getMastername());
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+jsonObject.toString());
	
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String saveconsumedata(){
	if(!verifyLogin(request)){
		return "login";
	}
	
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		NotAvailableSlotDAO availableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		IndentDAO indentDAO = new JDBCIndentDAO(connection);
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String clientid=jsonObject.getString("clientid");
		String newmid=jsonObject.getString("newmid");
		String vaccinationid=jsonObject.getString("vaccinationid");
		
		String location = availableSlotDAO.getVaccinationLocation();
		
		NotAvailableSlot availableSlot = availableSlotDAO.getVaccinationdataFromId(vaccinationid);
		
		String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		int parentid = indentDAO.savePatientTranferlog("0",loginInfo.getUserId(),datetime,"0",""); 
		Product product = inventoryProductDAO.getProduct(newmid);
		
		String todate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		int prodid = product.getId();
		String qty = "1";
		String product_name = product.getProduct_name();
		String stock = product.getStock();
		String tqty = qty;
		String catalogueid = product.getCatalogueid();
		product.setClientid(clientid);
		
		product.setIssueproceid("0");
		product.setIssueuserid("0");
		product.setHisuserfilter("0");
		product.setParentid(String.valueOf(parentid));
		product.setHisdepartmentfilter("0");
		product.setTqty(tqty);
		product.setVaccinationid(Integer.parseInt(vaccinationid));
		product.setDateTime(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
		product.setUserid(loginInfo.getUserId());
		product.setDate(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0]);
		int result = indentDAO.transferIssueProduct(product,"",loginInfo.getUserId(),todate,"0");
		
		if(catalogueid!=null){
			if(!catalogueid.equals("")){
				Product product2 = inventoryProductDAO.getProductCatalogueDetails(catalogueid);
				String medlocation = product.getLocation();
				boolean flag = indentDAO.checkLocationInWarehouseid(medlocation);
				if(flag){
					int minorder = Integer.parseInt(product2.getMinorder());
					int stock1 = indentDAO.getStockByProdId(prodid);
					
					if(stock1<minorder){
						int reqqty= minorder-stock1;
						int res = inventoryProductDAO.addTempPoRequest(""+prodid, "0", reqqty, product_name, todate, medlocation, 0, catalogueid);
					}
				}
			}
		}
		
		if(result>0){
			int res = availableSlotDAO.updateConsumeStatus(vaccinationid,"1");
			res = availableSlotDAO.updateVacinationConsumptiondata(vaccinationid,parentid,prodid,qty);
		}
		
		jsonObject.put("clientid",availableSlot.getClientId());
		jsonObject.put("clientname",availableSlot.getClientName());
		jsonObject.put("vaccinationname",availableSlot.getMastername());
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+jsonObject.toString());
	
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String setchargesofconsumption(){
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		NotAvailableSlotDAO availableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		String vaccinationid = request.getParameter("vaccinationid");
		String clientid = request.getParameter("clientid");
		String cdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		String temp[] = cdate.split(" ");
		String curdate = temp[0];
		
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		Client client = clientDAO.getClientDetails(clientid);
		String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
	
		//NotAvailableSlot availableSlot = availableSlotDAO.getVaccinationdataFromId(vaccinationid);
		
		String consumeid = availableSlotDAO.getConsumeIdFromVaccinId(vaccinationid);
		
		Map<Integer, String> productMap = availableSlotDAO.getVaccineProductList(consumeid);  
		
		for(Map.Entry<Integer, String> entry : productMap.entrySet()){
			String payBuy = "0";
			if(client.getWhopay().equals(Constants.PAY_BY_THIRD_PARTY)){
				payBuy = "1";
			}
			
			Product product = inventoryProductDAO.getProduct(""+entry.getKey());
		
			CompleteAppointment completeAppointment = new CompleteAppointment();
		
			completeAppointment.setUser(clientname);
			completeAppointment.setApmtType("0");
			completeAppointment.setManualcharge(product.getProduct_name());
			completeAppointment.setCharges(product.getSale_price());
			completeAppointment.setClientId(clientid);
		
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());
			completeAppointment.setCommencing(date);
			completeAppointment.setPayBuy(payBuy);
			completeAppointment.setMarkAppointment("1");
			completeAppointment.setQuantity(DateTimeUtils.convertToInteger(entry.getValue()));
			completeAppointment.setMasterchargetype("VACCINATION CHARGES");
			completeAppointment.setProdid(0);
			completeAppointment.setAppointmentid("0");
			completeAppointment.setPractitionerId("0");
			completeAppointment.setPractitionerName("");
			completeAppointment.setGinvstid("0");
		
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			int result = completeAptmDAO.saveCharge(completeAppointment,"0",loginInfo.getId());
		}
		
		
	
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	
	return null;
}


public String updatevaccinationstatus(){
	 Connection connection =null;
	  try {
		   connection = Connection_provider.getconnection();
		   NotAvailableSlotDAO availableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		   String vaccinationid = request.getParameter("vacinationid");
		   int res = 0;
		   for(String vaccination : vaccinationid.split(",")){
			   if(vaccination.equals("0")){
				   continue;
			   }
			   res = availableSlotDAO.updateConsumeStatus(vaccination,"2");
		   }
		   
		  
		   response.setContentType("text/html");
		   response.setHeader("Cache-Control", "no-cache");
		   response.getWriter().write(""+res+""); 
		  
	} catch (Exception e) {
		e.printStackTrace();
	}

return null;

}

public String admission(){
	
	try{
		String admissionid = request.getParameter("admissionid");
		session.setAttribute("sessionadmissionid", admissionid);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return null;
}

public String getnursenotesajax() throws Exception{
    
	   if(!verifyLogin(request)) {
		     return "login";
		    }
	   Connection connection=null;
	   try {
			StringBuffer buffer = new StringBuffer();
			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			String ipdaddmissionid = request.getParameter("admissionid");
			ArrayList<Bed> arrayList = ipdDAO.getAllNurseNotes(ipdaddmissionid);
			  
			buffer.append("<ul class='chats'>");
			for (Bed bed : arrayList) {
				buffer.append("<li class=''>");
				buffer.append("<div class='media'>");
				buffer.append("<p class='media-heading'><a role='button' tabindex='0' class='name' style='color: #16a085;'>"+bed.getClientname()+"</a><br><span class='datetime'>"+bed.getDatetime()+"</span></p>");
				buffer.append("<span class='body' style='font-size: 10px;text-align: justify;'>"+bed.getNursenotes()+"</span>");
				buffer.append("</div>");
				buffer.append("</li>");
			}
			buffer.append("</ul>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+buffer.toString()+"");
		 } catch (Exception e) {

		    e.printStackTrace();
		 }   
		 finally {
		        connection.close();
		 }
  
 return null;
}

public String changeuserlanguage() throws Exception{
    
	   if(!verifyLogin(request)) {
		     return "login";
		    }
	   Connection connection=null;
	   try {
			StringBuffer buffer = new StringBuffer();
			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			String language = request.getParameter("language");
			int res = ipdDAO.updateUserLanaguage(language,loginInfo.getUserId());
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+res+"");
		 } catch (Exception e) {

		    e.printStackTrace();
		 }   
		 finally {
		        connection.close();
		 }

return null;
}

public String savediagnosisfast() throws Exception {
	
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		String condition= request.getParameter("condition");
		String icdcode= "0";
		DiagnosisDAO  diagnosisDAO =new JDBCDiagnosisDAO(connection);
		Diagnosis diagnosis=new Diagnosis();
		diagnosis.setName(condition);
		diagnosis.setIcdcode(icdcode);
		int r=diagnosisDAO.checkExixstDiagnosis(condition);
		if(r>0){
			r=1;
		}else{
			r=0;
		}
		int d=diagnosisDAO.addDiagnosisName(diagnosis);
		
		 response.setContentType("text/html");
	     response.setHeader("Cache-Control", "no-cache");
	     response.getWriter().write(""+d+"~!"+r+"");
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	finally {
		connection.close();
	}
	
	return null;
}

public String changedepartment() throws Exception {
	
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
		MasterDAO masterDAO=new JDBCMasterDAO(connection);
		String clientid= request.getParameter("clientid");
		String aptid= request.getParameter("aptid");
		ArrayList<Master> departmentList =  masterDAO.getDisciplineDataListWithChecked();;
		Date date = new Date();
		String commencing= new SimpleDateFormat("yyyy-MM-dd").format(date);
		ArrayList<Master> selecteddeptlist=notAvailableSlotDAO.getselecteddeptlist(commencing,clientid);
		StringBuffer str = new StringBuffer();
//		str.append("<select id='chdept' name='chdept' multiple='multiple' class='form-control chosen'>");
//		str.append("<option value='0'>Select Department</option>");
//		
//		for (Master master : departmentList) {
//			boolean flag=false;
//			for (Master master1 : selecteddeptlist) {
//			if(master1.getId()==master.getId()){
//				str.append("<option selected='true' disabled='disabled'  value='" + master.getId() + "'>" + master.getName() + "</option>");
//				flag=true;
//			}
//			
//		}
//			//if(!flag){
//			str.append("<option  value='" + master.getId() + "'>" + master.getName() + "</option>");
//			//}
//		}
		String dicipline = loginInfo.getDicipline();
		dicipline = DateTimeUtils.numberCheck(dicipline);
		int department = DateTimeUtils.convertToInteger(dicipline);
		for (Master master : departmentList) {
			if(master.getId() == department){
				str.append("<input type='checkbox' disabled style='margin-top: 5px;' value='" + master.getId() +"'>&emsp;"+master.getDiscipline()+"<br>");
			}else{
				str.append("<input type='checkbox' class='sscc' onchange='refersect();' style='margin-top: 5px;' value='" + master.getId() +"'>&emsp;"+master.getDiscipline()+"<br>");
			}
			
		}
		String listString = "";
		String selecteddepart="";
		str.append("</select>");
		for (Master s : selecteddeptlist)
		{
		    listString += s.getId() + ",";
		    selecteddepart += s.getName() + ">>";
		    
		}
		
		
		str.append("<input type='hidden' id='cllid' value='"+clientid+"'>");
		str.append("<input type='hidden' id='sellect' value='"+listString+"'>");
		str.append("<input type='hidden' id='appptid' value='"+aptid+"'>");
		str.append("@%$"+selecteddepart+"");
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


public String referdept() throws Exception {
	
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
		String dept= request.getParameter("dept");
		String clientid= request.getParameter("clientid");
		String aptid= request.getParameter("aptid");
		String sts= request.getParameter("sts");
		String referremark=request.getParameter("referremark");
		NotAvailableSlot notavailableslot=new NotAvailableSlot();
		if(sts.equals("1")){
			notavailableslot=notAvailableSlotDAO.getAvailableSlotdata(DateTimeUtils.convertToInteger(aptid));
			int newpatient = notAvailableSlotDAO.patientNewOrOld(clientid,dept);
			notavailableslot.setNewpatient(newpatient);
		}else{
			notavailableslot=notAvailableSlotDAO.getDepartmentAvailableSlotdata(DateTimeUtils.convertToInteger(aptid));
		}
		
		Date date = new Date();
		String commencing= new SimpleDateFormat("yyyy-MM-dd").format(date);
		
		if(notavailableslot.getPreDate()==1){
			commencing = notavailableslot.getCommencing();
		}
		StringBuffer str = new StringBuffer();
		ClientAction clientAction=new ClientAction();
		int up=notAvailableSlotDAO.settrefertodepartment(aptid,dept);
		List<String> myList = new ArrayList<String>(Arrays.asList(dept.split(",")));
		for (String string : myList) {
			String appointment=clientAction.saveappointmentwithDept(string, clientid,2,notavailableslot.getPreDate(),notavailableslot.getCommencing());
			int res=notAvailableSlotDAO.updateDeparttmentrefferdfrom(appointment,DateTimeUtils.isNull(notavailableslot.getDept()));
			int updaatepatienttype=notAvailableSlotDAO.updatePatientStatus(appointment,notavailableslot.getNewpatient());
			int fakestatus = notAvailableSlotDAO.getPatientFakeStatus(clientid);
			int updateFakeStatus = notAvailableSlotDAO.updateDepartmentFakeStatus(fakestatus,appointment);
		}
		
		int temp=notAvailableSlotDAO.updaterefferremarkforAllDept(notavailableslot,referremark,commencing);
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
public String getpacslist() throws Exception {
	Connection connection=null;
	try {
		String clientid=DateTimeUtils.isNull(request.getParameter("clientid"));
		String abrivationid=request.getParameter("abrivationid");
		connection=Connection_provider.getconnection();
		//connection=DriverManager.getConnection("jdbc:mysql://117.236.76.142:3306/vspm","manasyuvi","M@n@S1928YUVI#$@%");
		PacsDAO pacsDAO =new JDBCPacsDAO(connection);
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		Pacs pacs1=pacsDAO.getusernamebyuhid(abrivationid);
		session.setAttribute("user", pacs1.getFindinguser());
		//ArrayList<Pacs>pacsdataList = pacsDAO.getnewwebpacsList(connection,clientid,loginInfo.getPacsip(),abrivationid);
		ArrayList<Pacs>pacsdataList = pacsDAO.getwebpacsList(connection,"","","","","",clientid,loginInfo.getPacsip());
		StringBuffer str=new StringBuffer();
		int i=1;
		String name=clientDAO.getClientFullName(clientid);
		for (Pacs pacs : pacsdataList) {
			
			str.append("<tr>");
			str.append("<td style='text-align: center;'>"+i+"</td>");
			
//			str.append("<td style='text-align: center;'><a href='#' onclick='redirecttoip("+loginInfo.getPacsip()+","+pacs.getMultipacsid()+")' id='myAnchor"+pacs.getId()+"'>"+pacs.getFilename()+"  </a></td>");
			str.append("<td style='text-align: center;'><a href='#' onclick=openPacsPopup('http://"+loginInfo.getPacsip()+":8080/webpacs/pacsdata/"+pacs.getMultipacsid()+"/"+pacs.getFilename()+"')>"+pacs.getFilename()+"  </a></td>");
			//str.append("<td style='text-align: center;'>"+pacs.getStudydate()+"</td>");
			str.append("<td style='text-align: center;'>"+pacs.getAbrivationid()+"</td>");
			str.append("<td style='text-align: center;'>"+pacs.getRecievedon()+"</td>");
			str.append(" <td><a href='#' onclick=openPopup('printpagePacs?pacsid="+pacs.getDicid()+"')><i class='fa fa-print'></i></a></td>");
		
			//str.append("<td style='text-align: center;'>"+abrivationid+"</td>");
			
			str.append("</tr>");
			i++;
		}
		response.setContentType("text/html");
	    response.setHeader("Cache-Control", "no-cache");
	    response.getWriter().write(str.toString());
		
	}catch (Exception e) {
		e.printStackTrace();
	}finally {
		connection.close();
	}
	
	return null;
}


public String searchChargeByCode()throws Exception{
	Connection connection=null;
    try {
    	connection=Connection_provider.getconnection();
    	ChargesAccountProcessingDAO chargesAccountProcessingDAO= new JDBCChargeAccountProcesDAO(connection);
    	String searchByCode = request.getParameter("searchByCode");
    	String ipdtpid = request.getParameter("ipdtpid");
    	String ipdwhopay = request.getParameter("ipdwhopay");
    	//String ipdaddmissionid = request.getParameter("ipdaddmissionid");
    	String clientId = request.getParameter("clientId");
    	
    	if(DateTimeUtils.isNull(ipdwhopay).equals("")){
    		ClientDAO clientDAO = new JDBCClientDAO(connection);
    		Client client = clientDAO.getClientDetails(clientId);
    		ipdwhopay = client.getWhopay();
    	}
    	
    	if(ipdwhopay.equals(Constants.PAY_BY_CLIENT)){
    		ipdtpid = "0";
    	}
    	
    	/*if(ipdaddmissionid==null){
    		ipdaddmissionid="0";
    	}
    	if(ipdaddmissionid.equals("")){
    		ipdaddmissionid="0";
    	}
    	
    	BedDao bedDao=new JDBCBedDao(connection);
		Bed bed = bedDao.getWardIdFromIPDId(ipdaddmissionid);
		//getFilteredChargeList
		String wardid = bed.getWardid();
		
		if(wardid==null){
			wardid="0";
		}
		if(wardid.equals("")){
			wardid="0";
		}
    	*/
    	
    	Master master = chargesAccountProcessingDAO.searchChargeByCode(searchByCode,ipdtpid);
    	int found=0;
    	int masterChargeId =0;
    	if(master.getId()>0){
    		found = 1;
    		masterChargeId = chargesAccountProcessingDAO.getMasterChargeIdFromName(master.getChargetype());
    	}
    	
    	String str = found+"~~"+masterChargeId+"~~"+master.getId()+"~~"+master.getChargetype();
    	
    	response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+str+""); 
    	
    }catch(Exception e){
 	   e.printStackTrace();
    }
    finally{
		connection.close();
	}
	return null;
}
public String validateCodeDuplicate(){
	try {
		Connection connection=Connection_provider.getconnection();	
		String code=request.getParameter("code");
		String id=request.getParameter("id");
		AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
		
		int res = appointmentTypeDAO.validateCodeDuplicate(code,id);
		
		response.setContentType("text/html");
	    response.setHeader("Cache-Control", "no-cache");
	    response.getWriter().write(""+res+""); 
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String openpayertype(){
	try {
		Connection connection=Connection_provider.getconnection();	
		
		ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		ArrayList<ThirdParty> thirdPartyTypeList = thirdPartyDAO.getThirdPartyList();
		
		StringBuffer buffer = new StringBuffer();
		
		int res =0;
		for (ThirdParty thirdParty : thirdPartyTypeList) {
			buffer.append("<tr>");
			buffer.append("<td>"+(++res)+"<input type='hidden' id='patientTypeId_"+thirdParty.getId()+"' value='"+thirdParty.getPatientType()+"'></td>");
			if(DateTimeUtils.isNull(thirdParty.getPatientType()).equals("1")){
				buffer.append("<td>Third Party</td>");
			}else{
				buffer.append("<td>Self</td>");
			}
			buffer.append("<td>"+thirdParty.getName()+"<input type='hidden' id='payerName_"+thirdParty.getId()+"'  value='"+thirdParty.getName()+"'></td>");
			buffer.append("<td><a href='#' onclick='editPayerType("+thirdParty.getId()+")'><i class='fa fa-edit'></i></a></td>");
			buffer.append("</tr>");
		}
		
		response.setContentType("text/html");
	    response.setHeader("Cache-Control", "no-cache");
	    response.getWriter().write(""+buffer.toString()+""); 
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String validatepayertype()throws Exception{
	try {
		Connection connection = Connection_provider.getconnection();
		ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		String patientType = request.getParameter("patientType");
		String payerType = request.getParameter("payerType");
		
		String edit_Id = DateTimeUtils.numberCheck(request.getParameter("edit_Id"));
		
		
		int res = thirdPartyDAO.validatePayerType(patientType,payerType,edit_Id);
		
		if(res==0){
			if(edit_Id.equals("0")){
				ThirdParty thirdParty = new ThirdParty();
				thirdParty.setType(payerType);
				thirdParty.setDescription(payerType);
				thirdParty.setPatientType(patientType);
				int result = thirdPartyDAO.saveType(thirdParty);
			}else{
				ThirdParty thirdParty = new ThirdParty();
				thirdParty.setType(payerType);
				thirdParty.setDescription(payerType);
				thirdParty.setPatientType(patientType);
				thirdParty.setId(Integer.parseInt(edit_Id));
				int result = thirdPartyDAO.updatePayerType(thirdParty);
			}
			
		}
		
		response.setContentType("text/html");
	    response.setHeader("Cache-Control", "no-cache");
	    response.getWriter().write(""+res+""); 
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
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
		
		
		ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		ArrayList<ThirdParty> thirdPartyList=thirdPartyDAO.getMainTPListNew(patientType);
		
		StringBuffer dropDownAjax = new StringBuffer();
		dropDownAjax.append("<select id='tpid' name='tpid' onchange='resetChargeAndWard()' class='form-control showToolTip chosen-select' data-toggle='tooltip'>");
		dropDownAjax.append("<option value = '0'>Select Payer</option>");
		for(ThirdParty thirdParty : thirdPartyList){
				dropDownAjax.append("<option value = '"+thirdParty.getId()+"'>"+thirdParty.getCompanyName()+"</option>");
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

public String getPayerTypeListData() throws Exception{
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		String id = request.getParameter("id");
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		ArrayList<ThirdParty>ajaxTypeNameList = clientDAO.getPayerTypeList(id);
		StringBuffer dropDownAjax = new StringBuffer();
		//dropDownAjax.append("<label>Payer Type<span class='text-danger'>*</span></label><br>");
		dropDownAjax.append("<select id='type' name ='type'  onchange='setTPName(this.value)' class='form-control showToolTip chosen-select' data-toggle='tooltip'>");
		dropDownAjax.append("<option value = '0'>Select Payer Type</option>");
		if(ajaxTypeNameList.size()!=0){
			for(ThirdParty client : ajaxTypeNameList){
				dropDownAjax.append("<option value = '"+client.getId()+"'>"+client.getTypeName()+"</option>");
			}
					
		}
		dropDownAjax.append("</select>");
		dropDownAjax.append("<label  id = 'tpError' class='text-danger'></label>	");
		
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

public String setTypeNameClntDropDown() throws Exception{
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		 
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		String isforlmh = DateTimeUtils.numberCheck(request.getParameter("isforlmh"));
		
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		ArrayList<Client>ajaxTypeNameList =new ArrayList<Client>();
		if(loginInfo.isLmh()){
		   ajaxTypeNameList = clientDAO.getThirdTypeNameListSetupPayer(id);
		}else{
		   ajaxTypeNameList = clientDAO.getThirdTypeNameList(id);
		}
		 StringBuffer dropDownAjax = new StringBuffer();
			if(isforlmh.equals("1")){
				dropDownAjax.append("<select id='typeName' onchange='setRegistrationCharge(this.value)' name = 'typeName' class='form-control showToolTip chosen' data-toggle='tooltip'>");
				dropDownAjax.append("<option value = '0'>Select Payer </option>");
			}else{
				dropDownAjax.append("<select id='typeName' name = 'typeName' class='form-control showToolTip chosen' data-toggle='tooltip'>");
				dropDownAjax.append("<option value = '0'>Select Third Party </option>");
			}
			
				if(ajaxTypeNameList.size()!=0){
					for(Client client : ajaxTypeNameList){
						dropDownAjax.append("<option value = '"+client.getId()+"'>"+client.getThirdPartyCompanyName()+"</option>");
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

public String getRegistrationCharge() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) !=null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);
		String thirdPartyId = jsonObject.getString("thirdPartyId");
		String clientid = jsonObject.getString("id");
		
		int id = DateTimeUtils.convertToInteger(clientid);
		
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		Client client = new Client();
		if(id!=0){
			client = clientDAO.getPatient(id);
		}
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		String regchargeapplied ="1";
		String registrationcharge ="0";
		String netamount ="0";
		if(client.getChargeYear()!=year){
			String masterCharge ="Appointment Charge";
			String chargeName ="Registration Charge";
			NotAvailableSlot notAvailableSlot =clientDAO.getRegistrationChargeAmount(thirdPartyId,masterCharge,chargeName);
			registrationcharge = notAvailableSlot.getChargeamout();
		}
		String paymentType = clientDAO.getPayerPaymentType(thirdPartyId);
		int enrollcode=clientDAO.checkEnrollCode(thirdPartyId);
		int campArea=clientDAO.checkCampArea(thirdPartyId);
		JSONObject object = new JSONObject();
		object.put("regchargeapplied", regchargeapplied);
		object.put("registrationcharge", registrationcharge);
		object.put("netamount", netamount);
		object.put("enrollcode", enrollcode);
		object.put("paymentType", paymentType);
		object.put("campArea", campArea);
		
		String response1 = object.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String getPayerList() throws Exception{
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
		
		if(patientType.equals("0")){
			patientType = "";
		}else if(patientType.equals("Client")){
			patientType = "0";
		}else if(patientType.equals("Third Party")){
			patientType = "1";
		}
		
		ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		ArrayList<ThirdParty> thirdPartyList=thirdPartyDAO.getMainTPListNew(patientType);
		
		StringBuffer dropDownAjax = new StringBuffer();
		//old code commented beacuse vspm multiple payer search
		/*
		 * dropDownAjax.
		 * append("<select id='tpid' name='tpid' class='form-control showToolTip chosen-select' data-toggle='tooltip'>"
		 * ); dropDownAjax.append("<option value = '0'>Select Payer</option>");
		 * for(ThirdParty thirdParty : thirdPartyList){
		 * dropDownAjax.append("<option value = '"+thirdParty.getId()+"'>"+thirdParty.
		 * getCompanyName()+"</option>"); } dropDownAjax.append("</select>");
		 */
		
		//new code
        dropDownAjax.append("<button type='button' class='btn btn-default btn-sm dropdown-toggle' data-toggle='dropdown' style='width:100%;'>Select Payer <span class='caret'></span></button>");
		dropDownAjax.append("<ul class='dropdown-menu'>");
		for(ThirdParty thirdParty : thirdPartyList){
				//dropDownAjax.append("<option value = '"+thirdParty.getId()+"'><input type='checkbox' value='"+thirdParty.getCompanyName()+"'></option>");
		  dropDownAjax.append("<li><a href='#' data-value='option1' tabIndex='-1'><input type='checkbox' id='p"+thirdParty.getId()+"' class='pacss' value='"+thirdParty.getId()+"'/><span class='spandrop'>"+thirdParty.getCompanyName()+"</span></a></li>");
		  
		}
		dropDownAjax.append("</ul>");
//		dropDownAjax.append("</select>");
		
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

public String getPatientDeptCount()throws Exception{
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		MasterDAO masterDAO = new JDBCMasterDAO(connection);
		String date = request.getParameter("date");
		date = DateTimeUtils.getCommencingDate1(date);
		
		ArrayList<Master> deptWiseCountList = masterDAO.getDepartmentWisePatientCount(date,date,"",loginInfo);
		int finalNewPatientCount=0;
		int finalOldPatientCount=0;
		int finalTotalPatientCount=0;
		
		if(deptWiseCountList.size()>0){
			finalNewPatientCount = deptWiseCountList.get(deptWiseCountList.size()-1).getFinalNewPatientCount();
			finalOldPatientCount = deptWiseCountList.get(deptWiseCountList.size()-1).getFinalOldPatientCount();
			finalTotalPatientCount = deptWiseCountList.get(deptWiseCountList.size()-1).getFinalTotalPatientCount();
		}
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("<table class='table table-hover table-condensed table-bordered' style='border: black 1px solid;'>");
		buffer.append("<thead>");
		buffer.append("<tr>");
		buffer.append("<td>Department</td>");
		buffer.append("<td>New Patient</td>");
		buffer.append("<td>Old Patient</td>");
		buffer.append("<td>Total Count</td>");
		buffer.append("</tr>");
		buffer.append("</thead>");
		buffer.append("<tbody>");
		for (Master master : deptWiseCountList) {
			buffer.append("<tr>");
			buffer.append("<td>"+master.getDiscipline()+"</td>");
			buffer.append("<td>"+master.getNewPatientCount()+"</td>");
			buffer.append("<td>"+master.getOldPatientCound()+"</td>");
			buffer.append("<td>"+master.getTotalPatientCount()+"</td>");
			buffer.append("</tr>");
		}
		buffer.append("</tbody>");
		
		buffer.append("<thead>");
		buffer.append("<tr>");
		buffer.append("<td>Total Patient</td>");
		buffer.append("<td>"+finalNewPatientCount+"</td>");
		buffer.append("<td>"+finalOldPatientCount+"</td>");
		buffer.append("<td>"+finalTotalPatientCount+"</td>");
		buffer.append("</tr>");
		buffer.append("</thead>");
		buffer.append("</table>");
			
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+buffer.toString()+""); 
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String openmulticonsume(){
	if(!verifyLogin(request)){
		return "login";
	}
	
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		NotAvailableSlotDAO availableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		NotAvailableSlotDAO availableSlot = new JDBCNotAvailableSlotDAO(connection);
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String clientId = jsonObject.getString("clientId");
		
		String location = availableSlotDAO.getVaccinationLocation();
		
		Client client = clientDAO.getClientDetails(clientId);
		
		ArrayList<Product> allMedicieneList = new ArrayList<Product>();
		if(!DateTimeUtils.numberCheck(location).equals("0")){
			allMedicieneList =	inventoryProductDAO.getVaccinationProductList(location,"");
		}
		
		StringBuffer buffer= new StringBuffer();
		buffer.append("<option value='0'>Select Product</option>");
		for(Product product:allMedicieneList){
			buffer.append("<option value='"+product.getId()+"'>"+product.getGenericname()+"</option>");
		}
		
		int consumption_status =0;
		ArrayList<NotAvailableSlot> opdlist = availableSlot.vaccinationListOfPatient(client.getAbrivationid(),consumption_status);
		int count=0;
		StringBuffer buffer2 = new StringBuffer();
		for (NotAvailableSlot notAvailableSlot : opdlist) {
			buffer2.append("<tr>");
			buffer2.append("<td>"+(++count)+"</td>");
			buffer2.append("<td><input type='checkbox' class='multiclass' id='vaccinationId"+notAvailableSlot.getVaccinationid()+"' name='vaccinationId"+notAvailableSlot.getVaccinationid()+"' value='"+notAvailableSlot.getVaccinationid()+"'></td>");
			buffer2.append("<td>"+notAvailableSlot.getMastername()+"</td>");
			/*buffer2.append("<td>"+notAvailableSlot.getDuedate()+"</td>");*/
			buffer2.append("<td>"+notAvailableSlot.getGivendate()+"</td>");
			buffer2.append("<td>");
			buffer2.append("<select class='form-control chosen' onchange='setVaccineConsumeProduct("+notAvailableSlot.getVaccinationid()+")' id='newmedicine"+notAvailableSlot.getVaccinationid()+"' name='mdicinename"+notAvailableSlot.getVaccinationid()+"' >");
			buffer2.append(buffer.toString());
			buffer2.append("</select>");
			buffer2.append("</td>");
			buffer2.append("<td><input type='text' style='text-align: center;' class='form-control'  id='consumeQty"+notAvailableSlot.getVaccinationid()+"' name='consumeQty"+notAvailableSlot.getVaccinationid()+"'></td>");
			buffer2.append("</tr>");
		}
		
		jsonObject.put("datax",buffer2.toString());
		jsonObject.put("clientid",client.getId());
		jsonObject.put("clientname",client.getFullname());
		jsonObject.put("abrivationid",client.getAbrivationid());
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+jsonObject.toString());
	
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String savemultipleconsumedata(){
	if(!verifyLogin(request)){
		return "login";
	}
	
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		NotAvailableSlotDAO availableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		IndentDAO indentDAO = new JDBCIndentDAO(connection);
		
		String clientid=request.getParameter("multiconsume_clientid");
		String location = availableSlotDAO.getVaccinationLocation();
		
		String multipleVaccineIds = request.getParameter("multipleVaccineIds");
		
		for (String vaccinationid : multipleVaccineIds.split(",")) {
			if(vaccinationid.equals("0")){
				continue;
			}
			String newmid= request.getParameter("mdicinename"+vaccinationid);
			String qty = request.getParameter("consumeQty"+vaccinationid);
			
			NotAvailableSlot availableSlot = availableSlotDAO.getVaccinationdataFromId(vaccinationid);
			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int parentid = indentDAO.savePatientTranferlog("0",loginInfo.getUserId(),datetime,"0",""); 
			
			Product product = inventoryProductDAO.getProduct(newmid);
			
			String todate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int prodid = product.getId();
			String product_name = product.getProduct_name();
			String stock = product.getStock();
			String tqty = qty;
			String catalogueid = product.getCatalogueid();
			product.setClientid(clientid);
			
			product.setIssueproceid("0");
			product.setIssueuserid("0");
			product.setHisuserfilter("0");
			product.setParentid(String.valueOf(parentid));
			product.setHisdepartmentfilter("0");
			product.setTqty(tqty);
			product.setVaccinationid(Integer.parseInt(vaccinationid));
			product.setDateTime(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
			product.setUserid(loginInfo.getUserId());
			product.setDate(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0]);
			int result = indentDAO.transferIssueProduct(product,"",loginInfo.getUserId(),todate,"0");
			
			if(catalogueid!=null){
				if(!catalogueid.equals("")){
					Product product2 = inventoryProductDAO.getProductCatalogueDetails(catalogueid);
					String medlocation = product.getLocation();
					boolean flag = indentDAO.checkLocationInWarehouseid(medlocation);
					if(flag){
						int minorder = Integer.parseInt(product2.getMinorder());
						int stock1 = indentDAO.getStockByProdId(prodid);
						
						if(stock1<minorder){
							int reqqty= minorder-stock1;
							int res = inventoryProductDAO.addTempPoRequest(""+prodid, "0", reqqty, product_name, todate, medlocation, 0, catalogueid);
						}
					}
				}
			}
			
			if(result>0){
				int res = availableSlotDAO.updateConsumeStatus(vaccinationid,"1");
				res = availableSlotDAO.updateVacinationConsumptiondata(vaccinationid,parentid,prodid,qty);
			}
		}	
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "vaccinationdashmethod";
}

public String openmultivaccinecharge(){
	if(!verifyLogin(request)){
		return "login";
	}
	
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		NotAvailableSlotDAO availableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		NotAvailableSlotDAO availableSlot = new JDBCNotAvailableSlotDAO(connection);
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String clientId = jsonObject.getString("clientId");
		
		String location = availableSlotDAO.getVaccinationLocation();
		
		Client client = clientDAO.getClientDetails(clientId);
		
		int consumption_status =1;
		ArrayList<NotAvailableSlot> opdlist = availableSlot.vaccinationListOfPatient(client.getAbrivationid(),consumption_status);
		int count=0;
		StringBuffer buffer2 = new StringBuffer();
		for (NotAvailableSlot notAvailableSlot : opdlist) {
			String productName = inventoryProductDAO.getProductNameFromID(notAvailableSlot.getVac_productid());
			String qty = inventoryProductDAO.getConsumeProductQtySum(notAvailableSlot.getConsumeid());
			buffer2.append("<tr>");
			buffer2.append("<td>"+(++count)+"</td>");
			buffer2.append("<td><input type='checkbox' class='multiaddclass' id='add_vaccinationId"+notAvailableSlot.getVaccinationid()+"' name='add_vaccinationId"+notAvailableSlot.getVaccinationid()+"' value='"+notAvailableSlot.getVaccinationid()+"'></td>");
			buffer2.append("<td>"+notAvailableSlot.getMastername()+"</td>");
			buffer2.append("<td>"+notAvailableSlot.getGivendate()+"</td>");
			buffer2.append("<td>");
			buffer2.append(""+productName+"");
			buffer2.append("</td>");
			buffer2.append("<td>"+qty+"</td>");
			buffer2.append("</tr>");
		}
		
		jsonObject.put("datax",buffer2.toString());
		jsonObject.put("clientid",client.getId());
		jsonObject.put("clientname",client.getFullname());
		jsonObject.put("abrivationid",client.getAbrivationid());
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+jsonObject.toString());
	
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}


public String showReferPatientList() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	
	Connection connection = null;
	Client client = new Client();
	ArrayList<Client> allPatientList = new ArrayList<Client>();
	try{
		connection = Connection_provider.getconnection();
		
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		String pro_userid="";
		if(loginInfo.isSmallClinic() && loginInfo.isCommon_clinic_db()){
			pro_userid = loginInfo.getUserId();
		}
		
		String searchText = request.getParameter("searchText");
		if(!DateTimeUtils.isNull(searchText).equals("")){
			allPatientList = clientDAO.getClient(searchText,loginInfo.getId(),pro_userid);
		}else{
			allPatientList = clientDAO.getAllPatient(loginInfo.getId(),pro_userid);
		}
		
		
		StringBuffer str = new StringBuffer();
		
		
		
		str.append("<table class='table table-bordered' > ");
		str.append("<thead>");
		str.append("<tr class='bg-info'>");
		str.append("<th>UHID</th> ");
		str.append("<th>Patient Name</th> ");
		str.append("<th>Pay By</th> ");
		str.append("<th>Age</th> "); 
		str.append("<th>Mobile No.</th> ");
		str.append("<th>Aadhar No</th> ");
		str.append("<th>Father Name</th> ");
		str.append("<th>Mother Name</th> ");
		if(!loginInfo.isLmh()){
			str.append("<th>Edit</th> ");
		}
		str.append("</tr>");
		str.append("</thead>");
		
		str.append("<tbody>");
		for(Client client1:allPatientList){
			String name = client1.getTitle()+" "+client1.getFirstName()+" "+client1.getMiddlename()+" "+client1.getLastName(); 	
			String color = "";
			if(!client1.getCasualtyid().equals("0")){
				color = "#f5a0b4";
			}
			String firstName= client1.getFirstName();
			
			int payee=0;
			if(client1.getWhopay()!=null){
				
				if(client1.getWhopay().equals("Client")){
					 payee=0;
				} else {
					payee=1;
				}
				
			}
			
			String whopay="";
			if(client1.getWhopay()!=null){
				
					if(client1.getWhopay().equals(Constants.PAY_BY_THIRD_PARTY)){
						whopay = "Third Party";
					}else{
						whopay = "Client";
					}
				
			}
		str.append("<tr style='background-color:"+color+";'  >");
		if(client1.getAbrivationid()==null){
			str.append("<td style='cursor: pointer;' onclick = setReferPatientName("+client1.getId()+")>"+client1.getId()+"</td>");
		}else{
			str.append("<td style='cursor: pointer;' onclick = setReferPatientName("+client1.getId()+")>"+client1.getAbrivationid()+"</td>");
		}
		
		str.append("<input type='hidden' id='firstnameid"+client1.getId()+"' value='"+client1.getFirstName()+"'>");
		
	//	String data=client1.getAddress()+"~"+client1.getDob()+"~"+client1.getEmergencyContName()+"~"+client1.getEmergencyContNo()+"~"+client1.getRelation();
		str.append("<td style='cursor: pointer;' onclick = setReferPatientName("+client1.getId()+") >"+name+"</td>");
		str.append("<td>"+whopay+"</td>");
		String age= DateTimeUtils.isNull(DateTimeUtils.getAge1(client1.getDob()));
		str.append("<td>"+age+"</td>");
		str.append("<td>"+client1.getMobNo()+"</td>");
		str.append("<td>"+DateTimeUtils.isNull(client1.getAdhno())+"</td>");
		
		str.append("<td>"+DateTimeUtils.isNull(client1.getFathername())+"</td>");
		str.append("<td>"+DateTimeUtils.isNull(client1.getMothername())+"</td>");
		if(!loginInfo.isLmh()){
			str.append("<td style='text-align: center;'><a href='editClient?selectedid="+client1.getId()+"&prodasboard=1'><i class='fa fa-pencil'></i></a></td>");
		}
		
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

public String getreferepatientadata(){
	if(!verifyLogin(request)){
		return "login";
	}
	
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String clientId = jsonObject.getString("clientId");
		
		Client client = clientDAO.getClientDetails(clientId);
		
		jsonObject.put("clientid",client.getId());
		jsonObject.put("clientname",client.getFullname());
		jsonObject.put("abrivationid",client.getAbrivationid());
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+jsonObject.toString());
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
public String cancelreferpatient() throws Exception {
	if (!verifyLogin(request)) {
		return "login";
	}
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		FinderDAO finderDAO = new JDBCFinderDAO(connection);
		String parentid = request.getParameter("parentid");
		String delete_reason = request.getParameter("delete_reason");
		
		String userid = loginInfo.getUserId();
		String datetime= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		int res = finderDAO.cancelDeletePatient(parentid, delete_reason,userid,datetime);

		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("");
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}

	return null;
}

public String confirmreferpatient()throws Exception{
	if (!verifyLogin(request)) {
		return "login";
	}
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		FinderDAO finderDAO = new JDBCFinderDAO(connection);
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		String parentid = request.getParameter("parentid");
		
		String userid = loginInfo.getUserId();
		String datetime= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		int res = finderDAO.confirmReferPatient(parentid,userid,datetime);
        Client client=finderDAO.getRefereddata(parentid);
        Client client1 = clientDAO.referClientDetails(client.getClientid(),client.getSourceclientid());
        Client clinic=clientDAO.getClinicnamebyuserid(client.getRefclinic(),client.getSourceclientid());
        String message=client1.getFullname()+"(Mobile:" +client1.getMobNo()+") has been confirmed, referred by "+clinic.getFullname()+" on "+datetime;
        WhatsAppService whService=new WhatsAppService();
        whService.sendMsg(loginInfo,clinic.getMobNo(),message);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("");
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}
	return null;
}

public String updatereferpatientstatus() throws Exception {
	if (!verifyLogin(request)) {
		return "login";
	}
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		FinderDAO finderDAO = new JDBCFinderDAO(connection);
		String parentid = request.getParameter("parentid");
		String patientStatus = request.getParameter("patientStatus");
		
		String userid = loginInfo.getUserId();
		String datetime= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		int res = finderDAO.updateReferPatientStatus(parentid, patientStatus,userid,datetime);

		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("");
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}

	return null;
}

public String updatereferpatientpayment()throws Exception{
	Connection connection=null;
	try {
		connection = Connection_provider.getconnection();
		FinderDAO finderDAO = new JDBCFinderDAO(connection);
		
		String parentid= request.getParameter("parentid");
		String totalBillAmt = request.getParameter("totalBillAmt"); 
		String clinicShare = request.getParameter("clinicShare");
		String consultantShare= request.getParameter("consultantShare");
		String clinicShareAmt = request.getParameter("clinicShareAmt");
		String consultantShareAmt = request.getParameter("consultantShareAmt");
		String userid = loginInfo.getUserId();
		String datetime= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		int res = finderDAO.updateReferPatientPayment(parentid,totalBillAmt,clinicShare,consultantShare,clinicShareAmt,consultantShareAmt,userid,datetime);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("");
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String getclinicaccountdetails()throws Exception{
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
		
		String clinicUserid = request.getParameter("clinicUserId");
		String val = request.getParameter("val");
		String pro_userid="";
		if(loginInfo.isSmallClinic() && loginInfo.isCommon_clinic_db()){
			 pro_userid = loginInfo.getUserId();
		}else if(pro_userid.equals("") && !DateTimeUtils.isNull(val).equals("0")){
			pro_userid = DateTimeUtils.isNull(val);
		}
		Clinic clinicaccess=clinicDAO.getBankClinicDetails(clinicUserid,pro_userid);
		
		String data = DateTimeUtils.isNull(clinicaccess.getAccount_name())+"~~"+
					  DateTimeUtils.isNull(clinicaccess.getAccount_number())+"~~"+
					  DateTimeUtils.isNull(clinicaccess.getAccount_ifsc())+"~~"+
					  DateTimeUtils.isNull(clinicaccess.getAccount_upi())+"~~"+val;
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+data+"");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String saveclinicaccountdetails()throws Exception{
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
		String bankName =request.getParameter("bankName");
		String accountNo = request.getParameter("accountNo");
		String ifscCode = request.getParameter("ifscCode");
		String upiId = request.getParameter("upiId");
		
		String pro_userid="";
		if(loginInfo.isSmallClinic() && loginInfo.isCommon_clinic_db()){
			 pro_userid = loginInfo.getUserId();
		}
		Clinic clinicaccess=clinicDAO.getBankClinicDetails(loginInfo.getClinicUserid(),pro_userid);
		int res=0;
		if(clinicaccess.getId()>0){
			res = clinicDAO.updateClinicBankDetails(bankName,accountNo,ifscCode,upiId,loginInfo.getClinicUserid(),pro_userid);
		}else{
			res = clinicDAO.insertClinicBankDetails(bankName,accountNo,ifscCode,upiId,loginInfo.getClinicUserid(),pro_userid);
		}
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+res+"");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String checkvaccinationaccess()throws Exception{
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+loginInfo.getSuperadminid()+"");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String addcliniccommercial()throws Exception{
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
		StringBuffer buffer = new StringBuffer();
		BufferedReader bufferedReader =  request.getReader();
		String line;
		while ((line = bufferedReader.readLine())!=null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);
		String referHospitalId = jsonObject.getString("referHospitalId");
		
		String pro_userid="";
		if(loginInfo.isSmallClinic() && loginInfo.isCommon_clinic_db()){
			 pro_userid = loginInfo.getUserId();
		}else{
			pro_userid = loginInfo.getClinicUserid();
		}
		
		Clinic clinic = clinicDAO.getClinicCommercial(referHospitalId,pro_userid);
		boolean flag = clinicDAO.checkClinicCommercial(referHospitalId,pro_userid); 
		int res = 0;
		if(!flag){
			res = clinicDAO.saveClinicHospitalCommercial(referHospitalId,pro_userid,clinic);
		}
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("res", res);
		String val = jsonObject2.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+val+"");
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String showclinicalcomercial()throws Exception{
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
		StringBuffer buffer = new StringBuffer();
		BufferedReader bufferedReader = request.getReader();
		String line;
		while ((line = bufferedReader.readLine()) !=null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		String pro_userid="";
		if(loginInfo.isSmallClinic() && loginInfo.isCommon_clinic_db()){
			 pro_userid = loginInfo.getUserId();
		}else{
			pro_userid = loginInfo.getClinicUserid();
		}
		ArrayList<Clinic> arrayList = clinicDAO.getClinicTempComercial(pro_userid);
		StringBuffer buffer2 = new StringBuffer();
		int count=0;
		for (Clinic clinic : arrayList) {
			buffer2.append("<tr>");
			buffer2.append("<td>");
			buffer2.append(""+(++count)+"<input type='hidden' value='"+clinic.getHospital_id()+"' name='hosp_id"+clinic.getId()+"' id='hosp_id"+clinic.getId()+"'>");
			buffer2.append("</td>");
			buffer2.append("<td>");
			buffer2.append(""+clinic.getHospitalName()+" <input type='hidden' class='dclass' value='"+clinic.getId()+"'>");
			buffer2.append("</td>");
			buffer2.append("<td>");
			buffer2.append("<input type='number' class='form-control' value='"+clinic.getCommercial_charge()+"' name='hosp_commercial"+clinic.getId()+"' id='hosp_commercial"+clinic.getId()+"'>");
			buffer2.append("</td>");
			buffer2.append("</tr>");
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("comercialList", buffer2.toString());
		String val = jsonObject.toString();
		
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+val+"");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String saveclinichospital()throws Exception{
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
		String clinicHospTempIds = request.getParameter("clinicHospTempIds");
		for(String id : clinicHospTempIds.split(",")){
			if(id.equals("0")){
				continue;
			}
			String pro_userid="";
			if(loginInfo.isSmallClinic() && loginInfo.isCommon_clinic_db()){
				 pro_userid = loginInfo.getUserId();
			}else{
				pro_userid = loginInfo.getClinicUserid();
			}
			String hosp_id = request.getParameter("hosp_id"+id);
			String hosp_commercial = request.getParameter("hosp_commercial"+id);
			
			int result = clinicDAO.checkClinicHospitalCommerical(hosp_id,pro_userid);
			if(result==0){
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateTime = dateFormat.format(new Date());
				int res = clinicDAO.insertClinicHospitalCommercial(hosp_id,pro_userid,hosp_commercial,dateTime);
			}else{
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateTime = dateFormat.format(new Date());
				int res = clinicDAO.updateClinicHospitalCommercial(hosp_id,pro_userid,hosp_commercial,dateTime,result);
			}
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "clinichospitalpro";
}

public String getsittinglistdata()throws Exception{
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		SittingFollowupDAO sittingDAO = new JDBCSittingFollowupDAO(connection);
		StringBuffer buffer = new StringBuffer();
		BufferedReader bufferedReader = request.getReader();
		String line;
		while ((line = bufferedReader.readLine()) !=null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);
		String departmentId = jsonObject.getString("departmentId");
		ArrayList<SittingFollowup> sittingList = new ArrayList<>();
		sittingList=sittingDAO.getAllSittingList(departmentId);
		StringBuffer buffer1 = new StringBuffer();
		buffer1.append("<label>Sitting</label>");
		buffer1.append("<select name='sittingName' id='sittingName' onchange='setProcedureMasterList(this.value)' class='form-control chosen-select'>");
		buffer1.append(" <option value=''>Select Sitting</option>");
        for(SittingFollowup sittingFollowup:sittingList){
			buffer1.append("<option value='" + sittingFollowup.getId() + "'>" + sittingFollowup.getSittingFollowup() + "</option>");
		}
        buffer1.append("</select>");
        
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("sittingList", buffer1.toString());
        
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write(""+jsonObject2.toString()+"");
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}


public String getproceduremasterlist()throws Exception{
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		MasterDAO masterDAO = new JDBCMasterDAO(connection);
		StringBuffer buffer = new StringBuffer();
		BufferedReader bufferedReader = request.getReader();
		String line;
		while ((line = bufferedReader.readLine())!=null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);
		String sittingId = jsonObject.getString("sittingId");
		
		ArrayList<Master>proceduretypelist=masterDAO.getchargetypelist(sittingId);
		StringBuffer buffer1=new StringBuffer();
		buffer1.append("<label>Procedure Master</label>");
	    buffer1.append("<select name='proceduerMasterName' id='proceduerMasterName' onchange='setProcedureList(this.value)' class='form-control chosen-select'>");
	    buffer1.append("<option value=''>Select Procedure Master</option>");
	    for (Master master : proceduretypelist) {
			buffer1.append("<option value='" + master.getId() + "'>" + master.getName() + "</option>");
		}
		buffer1.append("</select>");
		
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("procedureMasterList", buffer1.toString());
		
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+jsonObject2.toString()+"");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String setprocedurelist()throws Exception{
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		AppointmentTypeDAO appointmentTypeDAO=new JDBCAppointmentTypeDAO(connection);
		StringBuffer buffer = new StringBuffer();
		BufferedReader bufferedReader = request.getReader();
		String line;
		while ((line = bufferedReader.readLine()) !=null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);
		String proMasterId = jsonObject.getString("proMasterId");
		
		ArrayList<AppointmentType> list =appointmentTypeDAO.getProcedureList(proMasterId);
		StringBuffer buffer1=new StringBuffer();
			
		buffer1.append("<label>Procedure Name</label>");
		buffer1.append("<select name='procedureName' id='procedureName'  class='form-control chosen-select'>");
		buffer1.append("<option value=''>Select Procedure</option>");
		for (AppointmentType appointmentType : list) {
			buffer1.append("<option value='" + appointmentType.getId() + "'>" + appointmentType.getName() + "</option>");
	    }
		buffer1.append("</select>");
		
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("procedureList", buffer1.toString());
		
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+jsonObject2.toString()+"");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String getuserdiscription()throws Exception{
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		StringBuffer buffer = new StringBuffer();
		BufferedReader bufferedReader = request.getReader();
		String line;
		while ((line = bufferedReader.readLine()) !=null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		int discription =0;
		if(DateTimeUtils.isNull(loginInfo.getJobTitle()).equals("Practitioner")){
			discription = userProfileDAO.getDiscriptionFromUserId(loginInfo.getUserId());
		}
		
		
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("discription", discription);
		
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+jsonObject2.toString()+"");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String getdiagnosisJSONCF()throws Exception{

	if (!verifyLogin(request)) {
		return "login";
	}
	try{
		Connection connection= Connection_provider.getconnection();
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		String selected= jsonObject.getString("selected");
		
		String search= jsonObject.getString("search");
		
		ArrayList<Client> ipdCondtitionList = ipdDAO.getAllDiagnosis(search);
		StringBuilder builder = new StringBuilder();
		for (Client client : ipdCondtitionList) {

			boolean flag = false;

			for (String s : selected.split(",")) {

				if (s == null) {
					continue;
				}

				int d = Integer.parseInt(s);
				if (d == 0) {
					continue;
				}
				if (d == client.getId()) {
					flag = true;
					break;
				}
			}
			if(loginInfo.isF_diagnosis_discharge()){
				if (flag) {

					builder.append("<tr>");
					builder.append("<td><input type='checkbox' checked='checked' onclick='setfreedischargedata(this)'  value='"
							+ client.getId() + "'  /></td>");
					builder.append("<td>" + client.getTreatmentType() + "</td>");
					builder.append("</tr>");

				} else {

					builder.append("<tr>");
					builder.append("<td><input type='checkbox' onclick='setfreedischargedata(this)'  value='" + client.getId()
							+ "'  /></td>");
					builder.append("<td>" + client.getTreatmentType() + "</td>");
					builder.append("</tr>");

				}
			}	else{
				
				if (flag) {

					builder.append("<tr>");
					builder.append("<td><input type='checkbox' checked='checked' onclick='setCheckedDataCF(this)' value='"
							+ client.getId() + "'  /></td>");
					builder.append("<td>" + client.getTreatmentType() + "</td>");
					builder.append("</tr>");

				} else {

					builder.append("<tr>");
					builder.append("<td><input type='checkbox' onclick='setCheckedDataCF(this)' value='" + client.getId()
							+ "'  /></td>");
					builder.append("<td>" + client.getTreatmentType() + "</td>");
					builder.append("</tr>");

			}
		}


		}
	String responsej=	builder.toString();
	JSONObject jsonobj = new JSONObject();
	jsonobj.put("responsej", responsej);
		

	String response1 = jsonobj.toString();
	response.setContentType("application/json");
	response.setHeader("Cache-Control", "no-cache");
	response.getWriter().write(response1);


		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return null;
}

public String setAllDiagosisJSONCF(){

	if (!verifyLogin(request)) {
		return "login";
	}
	
	try{
		Connection connection= Connection_provider.getconnection();
		DiagnosisDAO diagnosisDAO= new JDBCDiagnosisDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		StringBuffer buffer= new StringBuffer();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);

		String selected = jsonObject.getString("selected");
		int i = 0;
		for (String s : selected.split(",")) {

			int d = Integer.parseInt(s);
			if (d == 0) {
				continue;
			} else {

				Diagnosis diagnosis = diagnosisDAO.getDiagnosisName(s);
				buffer.append("<tr>");
				buffer.append("<td><input type='checkbox' checked='checked' onclick=reoveThisRowCF(this,'"
						+ diagnosis.getId() + "') value='" + diagnosis.getId()
						+ "' class='classD' /> <input type='hidden' value=" + diagnosis.getId()
						+ " name='conditions[" + i + "].conditionid'  /> </td>");
				buffer.append("<td>" + diagnosis.getName() + "</td>");
				buffer.append("<td class='hidden'><a onclick=reoveThisRowCF('" + diagnosis.getId()
						+ "')><i class='fa fa-trash-o'></i></a></td>");
				buffer.append("</tr>");
				i++;
			}

		}
		String responsej= buffer.toString();
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("responsej", responsej);
				
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);


	}catch(Exception e){
		e.printStackTrace();
	}
	
	return null;
}

public String consultpriscdata()throws Exception{
	 
	 Connection connection = null;
	 
	 try{
		 connection = Connection_provider.getconnection();
		 IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		 EmrDAO emrDAO = new JDBCEmrDAO(connection);
		 String apmtId=DateTimeUtils.isNull(request.getParameter("apmtId"));
		 MasterDAO masterDAO = new JDBCMasterDAO(connection);
		 ArrayList<Priscription>dischargePriscList = new ArrayList<Priscription>();
		 dischargePriscList=ipdDAO.getConsultFormPrescList(apmtId);
		
		ArrayList<Master> dosageList = emrDAO.getDosageList();
		
		int size = dischargePriscList.size();
		String totalchildmedids ="0";
		if (size > 0) {
			totalchildmedids = dischargePriscList.get(size - 1).getTotalchildmedids();
		}
		
		ArrayList<Master> dosagenoteList = masterDAO.getDosageNoteList();
		ArrayList<Priscription> medicinetimelist = emrDAO.getMedicineTimeList(); 
		ArrayList<Master> priscUnitList = masterDAO.getPriscUnitList();
		int i = 0;
		StringBuffer str = new StringBuffer();
		for(Priscription priscription : dischargePriscList){
			str.append("<tr>");
			str.append("<td><input onchange=changeCFSrNo(this.value,"+priscription.getId()+",'sqno') type='number' class='form-control' name='dicpriscmed"+priscription.getId()+"' value='"+priscription.getDispriscsrno()+"'></td>");
			str.append("<td>"+priscription.getMdicinenametxt()+"</td/>");
			//  05 June 2018
			str.append("<td>");
			str.append("<select onchange=changeCFSrNo(this.value,"+priscription.getId()+",'dose') id='discpriscdose"+priscription.getId()+"' name='discpriscdose"+priscription.getId()+"' class='form-control chosen-select'>");
			for (Master master : dosageList) {
				if(priscription.getPriscdose()!=null){
					if(master.getName()!=null){
						if(master.getName().equals(priscription.getPriscdose())){
							str.append("<option value='"+master.getName()+"' selected='selected'>"+master.getName()+"</option>");
						}else{
							str.append("<option value='"+master.getName()+"'>"+master.getName()+"</option>");
						}
					}else{
						str.append("<option value='"+master.getName()+"'>"+master.getName()+"</option>");
					}
				}else{
					str.append("<option value='"+master.getName()+"'>"+master.getName()+"</option>");
				}
			}
			str.append("</select>");
			str.append("</td>");
			//str.append("<td>"+priscription.getPriscdose()+"</td/>");
			/*str.append("<td>"+priscription.getPriscdays()+" "+priscription.getPriscdurationtype()+"</td/>");*/
			str.append("<td><input onchange=changeCFSrNo(this.value,"+priscription.getId()+",'days') type='number' class='form-control'  id='dicpriscdays"+priscription.getId()+"' name='dicpriscdays"+priscription.getId()+"' value='"+priscription.getPriscdays()+"'></td>");
			
			/*str.append("<td>"+priscription.getDosenotes()+"</td/>");*/
			str.append("<td>");
			str.append("<select onchange=changeCFSrNo(this.value,"+priscription.getId()+",'prisctimename') id='doseFN"+priscription.getId()+"' name='dosenotes"+priscription.getId()+"' class='form-control chosen-select'>");
			for (Priscription master : medicinetimelist) {
				if(priscription.getPrisctimename()!=null){
					if(master.getPriscriptiontime()!=null){
						if(master.getPriscriptiontime().equals(priscription.getPrisctimename())){
							str.append("<option value='"+master.getPriscriptiontime()+"' selected='selected'>"+master.getPriscriptiontime()+"</option>");
						}else{
							str.append("<option value='"+master.getPriscriptiontime()+"'>"+master.getPriscriptiontime()+"</option>");
						}
					}else{
						str.append("<option value='"+master.getPriscriptiontime()+"'>"+master.getPriscriptiontime()+"</option>");
					}
				}else{
					str.append("<option value='"+master.getPriscriptiontime()+"'>"+master.getPriscriptiontime()+"</option>");
				}
			}
			str.append("</select>");
			str.append("</td>");
			
			str.append("<td>");
			str.append("<input onchange=changeCFSrNo(this.value,"+priscription.getId()+",'unit') type='number' class='form-control' name='dicpriscunit"+priscription.getId()+"' value='"+priscription.getStrength()+"'><br>");
			str.append("<select onchange=changeCFSrNo(this.value,"+priscription.getId()+",'unitextension') id='dicpriscunitextenstion"+priscription.getId()+"' name='dicpriscunitextenstion"+priscription.getId()+"' class='form-control chosen-select'>");
			if(DateTimeUtils.numberCheck(priscription.getUnitextension()).equals("0")){
				str.append("<option value='0' selected='selected'>Select Type</option>");
			}else{
				str.append("<option value='0'>Select Type</option>");
			}
			for (Master master : priscUnitList) {
				if(priscription.getUnitextension()!=null){
					if(master.getName().equals(priscription.getUnitextension())){
						str.append("<option value='"+master.getName()+"' selected='selected'>"+master.getName()+"</option>");
					}else{
						str.append("<option value='"+master.getName()+"'>"+master.getName()+"</option>");
					}
				}else{
					str.append("<option value='"+master.getName()+"'>"+master.getName()+"</option>");
				}
			}
			str.append("</select>");
			str.append("</td>");
			str.append("<td>");
			str.append("<select onchange=changeCFSrNo(this.value,"+priscription.getId()+",'dosenotes') id='doseSR"+priscription.getId()+"' name='dosenotes"+priscription.getId()+"' class='form-control chosen-select'>");
			for (Master master : dosagenoteList) {
				if(priscription.getDosenotes()!=null){
					if(master.getName()!=null){
						if(master.getName().equals(priscription.getDosenotes())){
							str.append("<option value='"+master.getName()+"' selected='selected'>"+master.getName()+"</option>");
						}else{
							str.append("<option value='"+master.getName()+"'>"+master.getName()+"</option>");
						}
					}else{
						str.append("<option value='"+master.getName()+"'>"+master.getName()+"</option>");
					}
				}else{
					str.append("<option value='"+master.getName()+"'>"+master.getName()+"</option>");
				}
			}
			str.append("</select>");
			str.append("</td>");
			str.append("<td><input onchange=changeCFSrNo(this.value,"+priscription.getId()+",'dr_qty') type='number' class='form-control' id='dicpriscdrqty"+priscription.getId()+"' name='dicpriscdrqty"+priscription.getId()+"' value='"+priscription.getDr_qty()+"'></td>");
			str.append("<td><a onclick='removeMedicineDisc1(this,"+priscription.getId()+")' ><i class='fa fa-trash'></i></a></td>");
			
			str.append("</tr>");
			i++;
		}
		str.append("<input type='hidden' name='totalchildmedids' value='"+totalchildmedids+"'>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+str.toString()+"");
		 
	 }catch (Exception e) {
		e.printStackTrace();
	}
	 finally{
			connection.close();
		}
	 return null;
}

public String getRegistrationPhysioCharge() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) !=null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);
		String charges = jsonObject.getString("charges");
		int planid=jsonObject.getInt("planid");
		session.setAttribute("planid", planid);
		int activeplanid=jsonObject.getInt("activeplanid");
		session.setAttribute("activeplanid", activeplanid);
		String clientid = jsonObject.getString("id");
		String thirdPartyId=jsonObject.getString("thirdPartyId");
		int id = DateTimeUtils.convertToInteger(clientid);
		
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		Client client = new Client();
		if(id!=0){
			client = clientDAO.getPatient(id);
		}
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		Client client1=clientDAO.getNumberofPhysioDays(clientid);
	    Client client2=clientDAO.getPatientActiveplanDetails(""+activeplanid);
	  
		if((client1.getDay()!=0) && (client.getRegularpatient()==0) && (planid!=5))
		{
			charges="0";
		}
		String regchargeapplied ="1";
		String registrationcharge =charges;
		String netamount =charges;
		if(!thirdPartyId.equals("0") && client1.getDay()==0 || client.getRegularpatient()!=0 || planid==5){
			String masterCharge ="PHYSIOTHERAPY";
			String chargeName =client2.getPlan();
			NotAvailableSlot notAvailableSlot =clientDAO.getRegistrationChargeAmount(thirdPartyId,masterCharge,chargeName);
			registrationcharge = notAvailableSlot.getChargeamout();
			
		}
		
		JSONObject object = new JSONObject();
		object.put("regchargeapplied", regchargeapplied);
		object.put("registrationcharge", registrationcharge);
		object.put("netamount", netamount);
		
		
		String response1 = object.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String setregichargeajax() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) !=null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);
		String regdate = jsonObject.getString("regdate");
		
		String clientid = jsonObject.getString("id");
		
		int id = DateTimeUtils.convertToInteger(clientid);
		
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		Client client = new Client();
		if(id!=0){
			client = clientDAO.getPatient(id);
		}
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String regchargeapplied ="1";
		String registrationcharge ="";
		String netamount="";
		String masterCharge = "";
		String chargeName ="";
		    if(loginInfo.isMatrusevasang()){
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(DateTimeUtils.getCommencingDate1(regdate));
			String cdate = DateTimeUtils.getUKCurrentDataTime("India").split(" ")[0];
			Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(cdate);
		    long duration=date2.getTime()-date1.getTime();
		    long daydiff = duration / (1000 * 60 * 60 * 24);
		    int months=1;
//		    months=(int) (daydiff/30);
		    if(daydiff<=27){
		    	registrationcharge="0";
		    } else if(daydiff>27){
		    	registrationcharge=Integer.toString(months*100);
		    } 
		    
		    }
		
		if(client.getChargeYear()!=year){
			masterCharge ="Appointment Charge";
			chargeName ="Follow Up Charge";
		}
		
		JSONObject object = new JSONObject();
		object.put("regchargeapplied", regchargeapplied);
		object.put("registrationcharge", registrationcharge);
		object.put("netamount", netamount);
		
		
		String response1 = object.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		connection.close();
	}
	return null;
}

public String savenote() throws Exception{
	
	Connection connection = null;
	
	try {
	    connection=Connection_provider.getconnection();
	    IpdDAO ipdDAO = new JDBCIpdDAO(connection);
	    BedDao bedDao = new JDBCBedDao(connection);
	    StringBuffer buffer = new StringBuffer();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) !=null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);
		String note=jsonObject.getString("note");
		String ipdaddmissionid=jsonObject.getString("ipdaddmissionid");
		String actiontype=jsonObject.getString("actiontype");
		String reqid=jsonObject.getString("reqid");
		String ispunchkarma=jsonObject.getString("ispunchkarma");
		String procedureid=jsonObject.getString("procedureid");
		String opdid=jsonObject.getString("opdid");
		String ipdclientid=jsonObject.getString("ipdclientid");
		String opdclientid=jsonObject.getString("opdclientid");
		String date =jsonObject.getString("date");
		date=DateTimeUtils.getCommencingDate1(date);
		String userid=loginInfo.getUserId(); 
		String date_time =DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		Bed bed=new Bed();
		bed.setNotes(note);
		bed.setIpdid(ipdaddmissionid);
		bed.setUserid(userid); 
		bed.setDatetime(date);
		bed.setReqid(reqid);
		bed.setPunchkarma(ispunchkarma);
		bed.setProcedure(Integer.parseInt(procedureid));
		bed.setOpdid(opdid);
		
		
		if(ipdclientid.equals("0")) {
			bed.setClientid(opdclientid);
		}else {
			bed.setClientid(ipdclientid);
		}
		
	
		
		/*
		 * IpdDAO ipdDAO = new JDBCIpdDAO(connection); BedDao bedDao = new
		 * JDBCBedDao(connection);
		 * 
		 * String note=(request.getParameter("note")); String
		 * userid=loginInfo.getUserId(); String date_time =
		 * DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()); String
		 * ipdaddmissionid=request.getParameter("ipdaddmissionid");
		 * 
		 * Bed bed=new Bed();
		 * 
		 * bed.setNotes(note); bed.setUserid(userid); bed.setDatetime(date_time);
		 * bed.setIpdid(ipdaddmissionid);
		 */
		if(actiontype.equals("1")) {
		int update=bedDao.updateRequestnote(bed);	
		}else {
		int result=bedDao.saveNote(bed);
		}
		JSONObject object = new JSONObject();
		object.put("ipdaddmissionid", ipdaddmissionid);
		String response1 = object.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		connection.close();
	}
	return null;
}

/*
 * public String requestnotelist() throws Exception{
 * 
 * Connection connection = null; int i=1;
 * 
 * try {
 * 
 * String id=request.getParameter("ipdadmissionid"); connection =
 * Connection_provider.getconnection(); EmrDAO emrDAO=new
 * JDBCEmrDAO(connection); FinderDAO finderDAO=new JDBCFinderDAO(connection);
 * IpdDAO ipdDAO = new JDBCIpdDAO(connection);
 * 
 * 
 * ArrayList<Bed> requestnotelist = ipdDAO.getpatientRequestNote(id);
 * StringBuffer buffer=new StringBuffer();
 * 
 * for (Bed bed : requestnotelist) {
 * 
 * if(bed.getSeen().equals("1")) {
 * buffer.append("<tr style='background-color:red;'>");
 * buffer.append("<td>"+i+++"</td>");
 * buffer.append("<td>"+bed.getDatetime()+"</td>");
 * buffer.append("<td>"+bed.getUserid()+"</td>");
 * //buffer.append("<td>"+bed.getNotes()+"</td>"); buffer.
 * append("<td style='text-align: center;cursor:pointer;' onclick='seennote("
 * +bed.getId()+")'>"+bed.getNotes()+"</td>");
 * buffer.append("<td><a href='#' onclick='editSitting("+bed.getId()
 * +",1)'><i class='fa fa-edit'></i></a></td>");
 * buffer.append("<td><a href='#' onclick='deleterequestnote("+bed.getId()
 * +")'><i class='fa fa-trash-o'></i></a></td>"); //buffer.append("<td></td>");
 * buffer.append("<td></td>"); }
 * 
 * buffer.append("</tr>"); }else { buffer.append("<tr>");
 * buffer.append("<td>"+i+++"</td>");
 * buffer.append("<td>"+bed.getDatetime()+"</td>");
 * buffer.append("<td>"+bed.getUserid()+"</td>");
 * //buffer.append("<td>"+bed.getNotes()+"</td>"); buffer.
 * append("<td style='text-align: center;cursor:pointer;' onclick='seennote("
 * +bed.getId()+")'>"+bed.getNotes()+"</td>");
 * buffer.append("<td><a href='#' onclick='editSitting("+bed.getId()
 * +",1)'><i class='fa fa-edit'></i></a></td>");
 * buffer.append("<td><a href='#' onclick='deleterequestnote("+bed.getId()
 * +")'><i class='fa fa-trash-o'></i></a></td>"); buffer.append("</tr>"); }
 * 
 * 
 * } response.setContentType("text/html"); response.setHeader("Cache-Control",
 * "no-cache"); response.getWriter().write(buffer.toString());
 * 
 * 
 * } catch (Exception e) { // TODO: handle exception e.printStackTrace(); }
 * 
 * return null;
 * 
 * }
 */
public String deletenote() throws Exception{
	  
	Connection connection = null;
	
	try {
		
		connection=Connection_provider.getconnection();
		 IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		 StringBuffer buffer = new StringBuffer();
		 BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) !=null) {
				buffer.append(line);
			}
		 String data = buffer.toString();
	     JSONObject jsonObject = new JSONObject(data);
		 String id=jsonObject.getString("id");
		 String ipdaddmissionid=jsonObject.getString("ipdadmissionid");

		int result=ipdDAO.deletenote(id);
		
		JSONObject object = new JSONObject();
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

public String editnote() {
	Connection connection = null;
	try {
		 connection=Connection_provider.getconnection();
		 IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		 StringBuffer buffer = new StringBuffer();
		 BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) !=null) {
				buffer.append(line);
			}
		 String data = buffer.toString();
	     JSONObject jsonObject = new JSONObject(data);
		 String id=jsonObject.getString("id");
		 String actiontype=jsonObject.getString("actiontype");
		 String reqnote=ipdDAO.getRequestnotebyid(id);
		 String procedure=ipdDAO.getprocedurebyid(id);
		 JSONObject object = new JSONObject();
		 object.put("reqnote", reqnote);
		 object.put("actiontype", actiontype);
		 object.put("id", id);
		 object.put("procedure", procedure);
		 String response1 = object.toString();
		 response.setContentType("application/json");
		 response.setHeader("Cache-Control", "no-cache");
		 response.getWriter().write(response1);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String seennote() throws Exception{
	
	Connection connection = null;
	
	try {
		connection=Connection_provider.getconnection();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		StringBuffer buffer = new StringBuffer();
		 BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) !=null) {
				buffer.append(line);
			}
		 String data = buffer.toString();
	     JSONObject jsonObject = new JSONObject(data);
		 String id=jsonObject.getString("id");
		 String ipdaddmissionid=jsonObject.getString("ipdadmissionid");
		 String reqnote=ipdDAO.getRequestnotebyid(id);
		 int result=ipdDAO.updateisSeen(id);
		 JSONObject object = new JSONObject();
		 object.put("reqnote", reqnote);
		 object.put("ipdaddmissionid", ipdaddmissionid);
		 String response1 = object.toString();
		 response.setContentType("application/json");
		 response.setHeader("Cache-Control", "no-cache");
		 response.getWriter().write(response1);
		/*
		 * String id=request.getParameter("id");
		 * 
		 * String reqnote=ipdDAO.getRequestnotebyid(id); //upadte seen colomn int
		 * result=ipdDAO.updateisSeen(id);
		 * 
		 * response.setContentType("text/html"); response.setHeader("Cache-Control",
		 * "no-cache"); response.getWriter().write(""+reqnote+"");
		 */
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return null;
}


public String openmulticonsumedeworm(){
	if(!verifyLogin(request)){
		return "login";
	}
	
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		NotAvailableSlotDAO availableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		NotAvailableSlotDAO availableSlot = new JDBCNotAvailableSlotDAO(connection);
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String clientId = jsonObject.getString("clientId");
		
		String location = availableSlotDAO.getVaccinationLocation();
		
		Client client = clientDAO.getClientDetails(clientId);
		
		ArrayList<Product> allMedicieneList = new ArrayList<Product>();
		if(!DateTimeUtils.numberCheck(location).equals("0")){
			allMedicieneList =	inventoryProductDAO.getVaccinationProductList(location,"");
		}
		
		StringBuffer buffer= new StringBuffer();
		buffer.append("<option value='0'>Select Product</option>");
		for(Product product:allMedicieneList){
			buffer.append("<option value='"+product.getId()+"'>"+product.getGenericname()+"</option>");
		}
		
		int consumption_status =0;
		ArrayList<NotAvailableSlot> opdlist = availableSlot.vaccinationListOfPatientVeterinary(client.getAbrivationid(),consumption_status);
		int count=0;
		StringBuffer buffer2 = new StringBuffer();
		for (NotAvailableSlot notAvailableSlot : opdlist) {
			buffer2.append("<tr>");
			buffer2.append("<td>"+(++count)+"</td>");
			buffer2.append("<td><input type='checkbox' class='multiclass' id='vaccinationId"+notAvailableSlot.getVaccinationid()+"' name='vaccinationId"+notAvailableSlot.getVaccinationid()+"' value='"+notAvailableSlot.getVaccinationid()+"'></td>");
			buffer2.append("<td>"+notAvailableSlot.getMastername()+"</td>");
			/*buffer2.append("<td>"+notAvailableSlot.getDuedate()+"</td>");*/
			buffer2.append("<td>"+notAvailableSlot.getGivendate()+"</td>");
			buffer2.append("<td>");
			buffer2.append("<select class='form-control chosen' onchange='setVaccineConsumeProduct("+notAvailableSlot.getVaccinationid()+")' id='newmedicine"+notAvailableSlot.getVaccinationid()+"' name='mdicinename"+notAvailableSlot.getVaccinationid()+"' >");
			buffer2.append(buffer.toString());
			buffer2.append("</select>");
			buffer2.append("</td>");
			buffer2.append("<td><input type='text' style='text-align: center;' class='form-control'  id='consumeQty"+notAvailableSlot.getVaccinationid()+"' name='consumeQty"+notAvailableSlot.getVaccinationid()+"'></td>");
			buffer2.append("</tr>");
		}
		
		jsonObject.put("datax",buffer2.toString());
		jsonObject.put("clientid",client.getId());
		jsonObject.put("clientname",client.getFullname());
		jsonObject.put("abrivationid",client.getAbrivationid());
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+jsonObject.toString());
	
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String savemultipleconsumedataveterinary(){
	if(!verifyLogin(request)){
		return "login";
	}
	
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		NotAvailableSlotDAO availableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		IndentDAO indentDAO = new JDBCIndentDAO(connection);
		
		String clientid=request.getParameter("multiconsume_clientid");
		String location = availableSlotDAO.getVaccinationLocation();
		
		String multipleVaccineIds = request.getParameter("multipleVaccineIds");
		
		for (String vaccinationid : multipleVaccineIds.split(",")) {
			if(vaccinationid.equals("0")){
				continue;
			}
			String newmid= request.getParameter("mdicinename"+vaccinationid);
			String qty = request.getParameter("consumeQty"+vaccinationid);
			
			NotAvailableSlot availableSlot = availableSlotDAO.getVaccinationdataFromId(vaccinationid);
			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int parentid = indentDAO.savePatientTranferlog("0",loginInfo.getUserId(),datetime,"0",""); 
			
			Product product = inventoryProductDAO.getProduct(newmid);
			
			String todate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int prodid = product.getId();
			String product_name = product.getProduct_name();
			String stock = product.getStock();
			String tqty = qty;
			String catalogueid = product.getCatalogueid();
			product.setClientid(clientid);
			
			product.setIssueproceid("0");
			product.setIssueuserid("0");
			product.setHisuserfilter("0");
			product.setParentid(String.valueOf(parentid));
			product.setHisdepartmentfilter("0");
			product.setTqty(tqty);
			product.setVaccinationid(Integer.parseInt(vaccinationid));
			product.setDateTime(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
			product.setUserid(loginInfo.getUserId());
			product.setDate(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0]);
			int result = indentDAO.transferIssueProduct(product,"",loginInfo.getUserId(),todate,"0");
			
			if(catalogueid!=null){
				if(!catalogueid.equals("")){
					Product product2 = inventoryProductDAO.getProductCatalogueDetails(catalogueid);
					String medlocation = product.getLocation();
					boolean flag = indentDAO.checkLocationInWarehouseid(medlocation);
					if(flag){
						int minorder = Integer.parseInt(product2.getMinorder());
						int stock1 = indentDAO.getStockByProdId(prodid);
						
						if(stock1<minorder){
							int reqqty= minorder-stock1;
							int res = inventoryProductDAO.addTempPoRequest(""+prodid, "0", reqqty, product_name, todate, medlocation, 0, catalogueid);
						}
					}
				}
			}
			
			if(result>0){
				int res = availableSlotDAO.updateConsumeStatusVet(vaccinationid,"1");
				res = availableSlotDAO.updateVacinationConsumptiondataVet(vaccinationid,parentid,prodid,qty);
			}
		}	
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "medicinedashmethod";
}


public String openmultimedicinecharge(){
	if(!verifyLogin(request)){
		return "login";
	}
	
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		NotAvailableSlotDAO availableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		NotAvailableSlotDAO availableSlot = new JDBCNotAvailableSlotDAO(connection);
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		BufferedReader br=request.getReader();
		String line="";
		String inputjson="";
		if((line=br.readLine())!=null){
			inputjson=inputjson+line;
		}
		JSONObject jsonObject= new JSONObject(inputjson);
		String clientId = jsonObject.getString("clientId");
		
		String location = availableSlotDAO.getVaccinationLocation();
		
		Client client = clientDAO.getClientDetails(clientId);
		
		int consumption_status =1;
		ArrayList<NotAvailableSlot> opdlist = availableSlot.vaccinationListOfPatientVeterinary(client.getAbrivationid(),consumption_status);
		int count=0;
		StringBuffer buffer2 = new StringBuffer();
		for (NotAvailableSlot notAvailableSlot : opdlist) {
			String productName = inventoryProductDAO.getProductNameFromID(notAvailableSlot.getVac_productid());
			String qty = inventoryProductDAO.getConsumeProductQtySum(notAvailableSlot.getConsumeid());
			buffer2.append("<tr>");
			buffer2.append("<td>"+(++count)+"</td>");
			buffer2.append("<td><input type='checkbox' class='multiaddclass' id='add_vaccinationId"+notAvailableSlot.getVaccinationid()+"' name='add_vaccinationId"+notAvailableSlot.getVaccinationid()+"' value='"+notAvailableSlot.getVaccinationid()+"'></td>");
			buffer2.append("<td>"+notAvailableSlot.getMastername()+"</td>");
			buffer2.append("<td>"+notAvailableSlot.getGivendate()+"</td>");
			buffer2.append("<td>");
			buffer2.append(""+productName+"");
			buffer2.append("</td>");
			buffer2.append("<td>"+qty+"</td>");
			buffer2.append("</tr>");
		}
		
		jsonObject.put("datax",buffer2.toString());
		jsonObject.put("clientid",client.getId());
		jsonObject.put("clientname",client.getFullname());
		jsonObject.put("abrivationid",client.getAbrivationid());
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+jsonObject.toString());
	
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}


public String updatevaccinationstatusdeworm(){
	 Connection connection =null;
	  try {
		   connection = Connection_provider.getconnection();
		   NotAvailableSlotDAO availableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		   String vaccinationid = request.getParameter("vacinationid");
		   int res = 0;
		   for(String vaccination : vaccinationid.split(",")){
			   if(vaccination.equals("0")){
				   continue;
			   }
			   res = availableSlotDAO.updateConsumeStatusVet(vaccination,"2");
		   }
		   
		  
		   response.setContentType("text/html");
		   response.setHeader("Cache-Control", "no-cache");
		   response.getWriter().write(""+res+""); 
		  
	} catch (Exception e) {
		e.printStackTrace();
	}

return null;

}


public String setMedchargesofconsumption(){
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		NotAvailableSlotDAO availableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		String vaccinationid = request.getParameter("vaccinationid");
		String clientid = request.getParameter("clientid");
		String cdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		String temp[] = cdate.split(" ");
		String curdate = temp[0];
		
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		Client client = clientDAO.getClientDetails(clientid);
		String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
	
		//NotAvailableSlot availableSlot = availableSlotDAO.getVaccinationdataFromId(vaccinationid);
		
		String consumeid = availableSlotDAO.getConsumeIdFromVaccinIdVet(vaccinationid);
		
		Map<Integer, String> productMap = availableSlotDAO.getVaccineProductList(consumeid);  
		
		for(Map.Entry<Integer, String> entry : productMap.entrySet()){
			String payBuy = "0";
			if(client.getWhopay().equals(Constants.PAY_BY_THIRD_PARTY)){
				payBuy = "1";
			}
			
			Product product = inventoryProductDAO.getProduct(""+entry.getKey());
		
			CompleteAppointment completeAppointment = new CompleteAppointment();
		
			completeAppointment.setUser(clientname);
			completeAppointment.setApmtType("0");
			completeAppointment.setManualcharge(product.getProduct_name());
			completeAppointment.setCharges(product.getSale_price());
			completeAppointment.setClientId(clientid);
		
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());
			completeAppointment.setCommencing(date);
			completeAppointment.setPayBuy(payBuy);
			completeAppointment.setMarkAppointment("1");
			completeAppointment.setQuantity(DateTimeUtils.convertToInteger(entry.getValue()));
			completeAppointment.setMasterchargetype("VACCINATION CHARGES");
			completeAppointment.setProdid(0);
			completeAppointment.setAppointmentid("0");
			completeAppointment.setPractitionerId("0");
			completeAppointment.setPractitionerName("");
			completeAppointment.setGinvstid("0");
		
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			int result = completeAptmDAO.saveCharge(completeAppointment,"0",loginInfo.getId());
		}
		
		
	
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	
	return null;
}


public String searchChiefcomplainsJSONCF()throws Exception{

	if (!verifyLogin(request)) {
		return "login";
	}
	try{
		Connection connection= Connection_provider.getconnection();
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		
		String id= jsonObject.getString("id");
		String search= jsonObject.getString("search");

		String selected1= jsonObject.getString("selected1");
		
		ArrayList<Master> chiefComplainList = ipdDAO.getAllChiefComplains(search,id);
		StringBuilder builder = new StringBuilder();
		for (Master master : chiefComplainList) {
			boolean flag = false;
			
			 for (String s : selected1.split(",")) {
			 
			    if (s == null) { 
				 continue; 
				 }
			  
			  int d = Integer.parseInt(s); 
			  if (d == 0) { 
				  
				  continue; 
				  } if (d == master.getId()) {
					  flag = true;
					  break; 
					  }
				  }
			 
			
			if (flag) {

				builder.append("<tr>");
				builder.append("<td><input type='checkbox' checked='checked' onclick='setCheckedComplainsCF(this)'  value='"
						+ master.getId() + "'  /></td>");
				builder.append("<td>" + master.getChiefcomplains()+ "</td>");
				builder.append("</tr>");

			} else {
				builder.append("<tr>");
				builder.append("<td><input type='checkbox'  onclick='setCheckedComplainsCF(this)'  value='"
						+ master.getId() + "'  /></td>");
				builder.append("<td>" + master.getChiefcomplains()+ "</td>");
				builder.append("</tr>");

		   }
			
		}
	String responsej=	builder.toString();
	JSONObject jsonobj = new JSONObject();
	jsonobj.put("responsej", responsej);
	String response1 = jsonobj.toString();
	response.setContentType("application/json");
	response.setHeader("Cache-Control", "no-cache");
	response.getWriter().write(response1);


		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return null;
}




public String setAllChiefComplainsJSONCF(){

	if (!verifyLogin(request)) {
		return "login";
	}
	
	try{
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterdao= new JDBCMasterDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		StringBuffer buffer= new StringBuffer();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);

		String selected1 = jsonObject.getString("selected1");
		int i = 0;
		for (String s : selected1.split(",")) {

			int d = Integer.parseInt(s);
			if (d == 0) {
				continue;
			} else {

				Master master = masterdao.getChiefComplainsname(s);
				buffer.append("<tr>");
				buffer.append("<td><input type='checkbox' checked='checked' onclick=removechiefRowCF(this,'"
						+ master.getId() + "') value='" + master.getId()
						+ "' class='classC' /> <input type='hidden' value=" + master.getId()
						+ " name='chiefcomplain[" + i + "].id'  /> </td>");
				buffer.append("<td>" + master.getChiefcomplains() + "</td>");
				buffer.append("<td class='hidden'><a onclick=removechiefRowCF('" + master.getId()
						+ "')><i class='fa fa-trash-o'></i></a></td>");
				buffer.append("</tr>");
				i++;
			}

		}
		String responsej= buffer.toString();
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("responsej", responsej);
				
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);


	}catch(Exception e){
		e.printStackTrace();
	}
	
	return null;
}


public String saveChiefcomplainfastJSON()throws Exception{

	if (!verifyLogin(request)) {
		return "login";
	}
	try{ 
	Connection connection= Connection_provider.getconnection();
	StringBuilder buffer = new StringBuilder();
	BufferedReader reader = request.getReader();
	String line;
	while ((line = reader.readLine()) != null) {
		buffer.append(line);
	}
	String data = buffer.toString();
	JSONObject jsonObject = new JSONObject(data);

	String chiefcomplain = jsonObject.getString("chiefcomplain");
	String icdcode= "0";
	MasterDAO  masterDAO =new JDBCMasterDAO(connection);
	Master master=new Master();
	master.setChiefcomplains(chiefcomplain);
	master.setTemplateid(1);
	int checkid=masterDAO.checkExixstChiefComplains(master.getChiefcomplains(),1);
	int id=0;
	if (checkid>0) {
		id=0;
	}else{
	 id=masterDAO.addChiefComplainName(master);
	}
	JSONObject jsonobj = new JSONObject();
	jsonobj.put("chiefcomplain", id);
	
	String response1 = jsonobj.toString();
	response.setContentType("application/json");
	response.setHeader("Cache-Control", "no-cache");
	response.getWriter().write(response1);

	
	
	}catch(Exception e){
		e.printStackTrace();
	}
	 
	 return null;
 }





public String searchHistoryJSONCF()throws Exception{

	if (!verifyLogin(request)) {
		return "login";
	}
	try{
		Connection connection= Connection_provider.getconnection();
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		
		String id= jsonObject.getString("id");
		String search= jsonObject.getString("search");

		String selectedhist= jsonObject.getString("selectedhist");
		
		ArrayList<Master> historyList = ipdDAO.getAllHistoryList(search,id);
		StringBuilder builder = new StringBuilder();
		for (Master master : historyList) {
			boolean flag = false;
			
			 for (String s : selectedhist.split(",")) {
			 
			    if (s == null) { 
				 continue; 
				 }
			  
			  int d = Integer.parseInt(s); 
			  if (d == 0) { 
				  
				  continue; 
				  } if (d == master.getId()) {
					  flag = true;
					  break; 
					  }
				  }
			 
			
			if (flag) {

				builder.append("<tr>");
				builder.append("<td><input type='checkbox' checked='checked' onclick='setCheckedHistoryCF(this)'  value='"
						+ master.getHistoryid() + "'  /></td>");
				builder.append("<td>" + master.getPasthistory()+ "</td>");
				builder.append("</tr>");

			} else {
				builder.append("<tr>");
				builder.append("<td><input type='checkbox'  onclick='setCheckedHistoryCF(this)'  value='"
						+ master.getHistoryid() + "'  /></td>");
				builder.append("<td>" + master.getPasthistory()+ "</td>");
				builder.append("</tr>");

		   }
			
		}
	String responsej=	builder.toString();
	JSONObject jsonobj = new JSONObject();
	jsonobj.put("responsej", responsej);
	String response1 = jsonobj.toString();
	response.setContentType("application/json");
	response.setHeader("Cache-Control", "no-cache");
	response.getWriter().write(response1);


		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return null;
}


public String setAllHistoryJSONCF(){

	if (!verifyLogin(request)) {
		return "login";
	}
	
	try{
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterdao= new JDBCMasterDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		StringBuffer buffer= new StringBuffer();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);

		String selectedhist = jsonObject.getString("selectedhist");
		int i = 0;
		for (String s : selectedhist.split(",")) {

			int d = Integer.parseInt(s);
			if (d == 0) {
				continue;
			} else {

				Master master = masterdao.getHistoryname(s);
				buffer.append("<tr>");
				buffer.append("<td><input type='checkbox' checked='checked' onclick=removeHistoryRowCF(this,'"
						+ master.getHistoryid() + "') value='" +master.getHistoryid()
						+ "' class='classH' /> <input type='hidden' value=" + master.getHistoryid()
						+ " name='histories[" + i + "].id'  /> </td>");
				buffer.append("<td>" + master.getPasthistory() + "</td>");
				buffer.append("<td class='hidden'><a onclick=removeHistoryRowCF('" + master.getHistoryid()
						+ "')><i class='fa fa-trash-o'></i></a></td>");
				buffer.append("</tr>");
				i++;
			}

		}
		String responsej= buffer.toString();
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("responsej", responsej);
				
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);


	}catch(Exception e){
		e.printStackTrace();
	}
	
	return null;
}


public String saveHistoryfastJSON()throws Exception{

	if (!verifyLogin(request)) {
		return "login";
	}
	try{ 
	Connection connection= Connection_provider.getconnection();
	StringBuilder buffer = new StringBuilder();
	BufferedReader reader = request.getReader();
	String line;
	while ((line = reader.readLine()) != null) {
		buffer.append(line);
	}
	String data = buffer.toString();
	JSONObject jsonObject = new JSONObject(data);

	String pasthistory = jsonObject.getString("pasthistory");
	String icdcode= "0";
	MasterDAO  masterDAO =new JDBCMasterDAO(connection);
	Master master=new Master();
	master.setChiefcomplains(pasthistory);
	master.setTemplateid(3);
	int checkid=masterDAO.checkExixstChiefComplains(pasthistory,3);
	int id=0;
	if (checkid>0) {
		id=0;
	}else{
	 id=masterDAO.addChiefComplainName(master);
	}
	JSONObject jsonobj = new JSONObject();
	jsonobj.put("pasthistory", id);
	
	String response1 = jsonobj.toString();
	response.setContentType("application/json");
	response.setHeader("Cache-Control", "no-cache");
	response.getWriter().write(response1);

	
	
	}catch(Exception e){
		e.printStackTrace();
	}
	 
	 return null;
 }


public String searchInvestigationJSONCF()throws Exception{

	if (!verifyLogin(request)) {
		return "login";
	}
	try{
		Connection connection= Connection_provider.getconnection();
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		
	
		String search= jsonObject.getString("search");

		String selectedInvst= jsonObject.getString("selectedInvst");
		
		ArrayList<Master> investigationList = ipdDAO.getAllInvestigation(search);
		StringBuilder builder = new StringBuilder();
		for (Master master : investigationList) {
			boolean flag = false;
			
			 for (String s : selectedInvst.split(",")) {
			 
			    if (s == null) { 
				 continue; 
				 }
			  
			  int d = Integer.parseInt(s); 
			  if (d == 0) { 
				  
				  continue; 
				  } if (d == master.getId()) {
					  flag = true;
					  break; 
					  }
				  }
			 
			
			if (flag) {

				builder.append("<tr>");
				builder.append("<td><input type='checkbox' checked='checked' onclick='setCheckedInvestigationCF(this)'  value='"
						+ master.getId() + "'  /></td>");
				builder.append("<td>" + master.getInvestigation_name()+ "</td>");
				builder.append("</tr>");

			} else {
				builder.append("<tr>");
				builder.append("<td><input type='checkbox'  onclick='setCheckedInvestigationCF(this)'  value='"
						+ master.getId() + "'  /></td>");
				builder.append("<td>" + master.getInvestigation_name()+ "</td>");
				builder.append("</tr>");

		   }
			
		}
	String responsej=	builder.toString();
	JSONObject jsonobj = new JSONObject();
	jsonobj.put("responsej", responsej);
	String response1 = jsonobj.toString();
	response.setContentType("application/json");
	response.setHeader("Cache-Control", "no-cache");
	response.getWriter().write(response1);


		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return null;
}



public String setAllInvestigationJSONCF(){

	if (!verifyLogin(request)) {
		return "login";
	}
	
	try{
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterdao= new JDBCMasterDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		StringBuffer buffer= new StringBuffer();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);

		String selectedInvst = jsonObject.getString("selectedInvst");
		int i = 0;
		for (String s : selectedInvst.split(",")) {

			int d = Integer.parseInt(s);
			if (d == 0) {
				continue;
			} else {

				Master master = masterdao.getInvestigationName(s);
				buffer.append("<tr>");
				buffer.append("<td><input type='checkbox' checked='checked' onclick=removeInvestigationRowCF(this,'"
						+ master.getId() + "') value='" +master.getId()
						+ "' class='classI' /> <input type='hidden' value=" +master.getId()
						+ " name='investigations[" + i + "].id'  /> </td>");
				buffer.append("<td>" + master.getInvestigation_name() + "</td>");
				buffer.append("<td class='hidden'><a onclick=removeInvestigationRowCF('" + master.getId()
						+ "')><i class='fa fa-trash-o'></i></a></td>");
				buffer.append("</tr>");
				i++;
			}

		}
		String responsej= buffer.toString();
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("responsej", responsej);
				
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);


	}catch(Exception e){
		e.printStackTrace();
	}
	
	return null;
}



public String saveInvestigationfastJSON()throws Exception{

	if (!verifyLogin(request)) {
		return "login";
	}
	try{ 
	Connection connection= Connection_provider.getconnection();
	StringBuilder buffer = new StringBuilder();
	BufferedReader reader = request.getReader();
	String line;
	while ((line = reader.readLine()) != null) {
		buffer.append(line);
	}
	String data = buffer.toString();
	JSONObject jsonObject = new JSONObject(data);

	String investigation = jsonObject.getString("investigation");
	String icdcode= "0";
	MasterDAO  masterDAO =new JDBCMasterDAO(connection);
	Master master=new Master();
	master.setInvestigation_name(investigation);

	int checkid=masterDAO.checkExixstinvestigation(master.getInvestigation_name());
	int id=0;
	if (checkid>0) {
		id=0;
	}else{
	 id=masterDAO.addInvestigationName(master);
	}
	JSONObject jsonobj = new JSONObject();
	jsonobj.put("investigation", id);
	
	String response1 = jsonobj.toString();
	response.setContentType("application/json");
	response.setHeader("Cache-Control", "no-cache");
	response.getWriter().write(response1);

	
	
	}catch(Exception e){
		e.printStackTrace();
	}
	 
	 return null;
 }




public String searchNotesJSONCF()throws Exception{

	if (!verifyLogin(request)) {
		return "login";
	}
	try{
		Connection connection= Connection_provider.getconnection();
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		
		String id= jsonObject.getString("id");
		String search= jsonObject.getString("search");

		String selectednotes= jsonObject.getString("selectednotes");
		
		ArrayList<Master> notesList = ipdDAO.getAllNotesList(search,id);
		StringBuilder builder = new StringBuilder();
		for (Master master : notesList) {
			boolean flag = false;
			
			 for (String s : selectednotes.split(",")) {
			 
			    if (s == null) { 
				 continue; 
				 }
			  
			  int d = Integer.parseInt(s); 
			  if (d == 0) { 
				  
				  continue; 
				  } if (d == master.getId()) {
					  flag = true;
					  break; 
					  }
				  }
			 
			
			if (flag) {

				builder.append("<tr>");
				builder.append("<td><input type='checkbox' checked='checked' onclick='setCheckedNotesCF(this)'  value='"
						+ master.getId() + "'  /></td>");
				builder.append("<td>" + master.getNotes()+ "</td>");
				builder.append("</tr>");

			} else {
				builder.append("<tr>");
				builder.append("<td><input type='checkbox'  onclick='setCheckedNotesCF(this)'  value='"
						+ master.getId() + "'  /></td>");
				builder.append("<td>" + master.getNotes()+ "</td>");
				builder.append("</tr>");

		   }
			
		}
	String responsej=	builder.toString();
	JSONObject jsonobj = new JSONObject();
	jsonobj.put("responsej", responsej);
	String response1 = jsonobj.toString();
	response.setContentType("application/json");
	response.setHeader("Cache-Control", "no-cache");
	response.getWriter().write(response1);


		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return null;
}


public String setAllNotesJSONCF(){

	if (!verifyLogin(request)) {
		return "login";
	}
	
	try{
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterdao= new JDBCMasterDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		StringBuffer buffer= new StringBuffer();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);

		String selectednotes = jsonObject.getString("selectednotes");
		int i = 0;
		for (String s : selectednotes.split(",")) {

			int d = Integer.parseInt(s);
			if (d == 0) {
				continue;
			} else {

				Master master = masterdao.getNotesName(s);
				buffer.append("<tr>");
				buffer.append("<td><input type='checkbox' checked='checked' onclick=removeNotesRowCF(this,'"
						+ master.getId() + "') value='" +master.getId()
						+ "' class='classN' /> <input type='hidden' value=" + master.getId()
						+ " name='notes[" + i + "].id'  /> </td>");
				buffer.append("<td>" + master.getNotes() + "</td>");
				buffer.append("<td class='hidden'><a onclick=removeNotesRowCF('" + master.getId()
						+ "')><i class='fa fa-trash-o'></i></a></td>");
				buffer.append("</tr>");
				i++;
			}

		}
		String responsej= buffer.toString();
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("responsej", responsej);
				
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);


	}catch(Exception e){
		e.printStackTrace();
	}
	
	return null;
}


public String saveNotesfastJSON()throws Exception{

	if (!verifyLogin(request)) {
		return "login";
	}
	try{ 
	Connection connection= Connection_provider.getconnection();
	StringBuilder buffer = new StringBuilder();
	BufferedReader reader = request.getReader();
	String line;
	while ((line = reader.readLine()) != null) {
		buffer.append(line);
	}
	String data = buffer.toString();
	JSONObject jsonObject = new JSONObject(data);

	String newnotes = jsonObject.getString("newnotes");
	String icdcode= "0";
	MasterDAO  masterDAO =new JDBCMasterDAO(connection);
	Master master=new Master();
	master.setChiefcomplains(newnotes);
	master.setTemplateid(8);
	int checkid=masterDAO.checkExixstChiefComplains(newnotes,8);
	int id=0;
	if (checkid>0) {
		id=0;
	}else{
	 id=masterDAO.addChiefComplainName(master);
	}
	JSONObject jsonobj = new JSONObject();
	jsonobj.put("newnotes", id);
	
	String response1 = jsonobj.toString();
	response.setContentType("application/json");
	response.setHeader("Cache-Control", "no-cache");
	response.getWriter().write(response1);

	
	
	}catch(Exception e){
		e.printStackTrace();
	}
	 
	 return null;
 }



public String searchObservationJSONCF()throws Exception{

	if (!verifyLogin(request)) {
		return "login";
	}
	try{
		Connection connection= Connection_provider.getconnection();
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		
		String id= jsonObject.getString("id");
		String search= jsonObject.getString("search");

		String selectedobs= jsonObject.getString("selectedobs");
		
		ArrayList<Master> observList = ipdDAO.getAllNotesList(search,id);
		StringBuilder builder = new StringBuilder();
		for (Master master : observList) {
			boolean flag = false;
			
			 for (String s : selectedobs.split(",")) {
			 
			    if (s == null) { 
				 continue; 
				 }
			  
			  int d = Integer.parseInt(s); 
			  if (d == 0) { 
				  
				  continue; 
				  } if (d == master.getId()) {
					  flag = true;
					  break; 
					  }
				  }
			 
			
			if (flag) {

				builder.append("<tr>");
				builder.append("<td><input type='checkbox' checked='checked' onclick='setCheckedObservCF(this)'  value='"
						+ master.getId() + "'  /></td>");
				builder.append("<td>" + master.getNotes()+ "</td>");
				builder.append("</tr>");

			} else {
				builder.append("<tr>");
				builder.append("<td><input type='checkbox'  onclick='setCheckedObservCF(this)'  value='"
						+ master.getId() + "'  /></td>");
				builder.append("<td>" + master.getNotes()+ "</td>");
				builder.append("</tr>");

		   }
			
		}
	String responsej=	builder.toString();
	JSONObject jsonobj = new JSONObject();
	jsonobj.put("responsej", responsej);
	String response1 = jsonobj.toString();
	response.setContentType("application/json");
	response.setHeader("Cache-Control", "no-cache");
	response.getWriter().write(response1);


		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return null;
}



public String setAllObservJSONCF(){

	if (!verifyLogin(request)) {
		return "login";
	}
	
	try{
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterdao= new JDBCMasterDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		StringBuffer buffer= new StringBuffer();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);

		String selectedobs = jsonObject.getString("selectedobs");
		int i = 0;
		for (String s : selectedobs.split(",")) {

			int d = Integer.parseInt(s);
			if (d == 0) {
				continue;
			} else {

				Master master = masterdao.getNotesName(s);
				buffer.append("<tr>");
				buffer.append("<td><input type='checkbox' checked='checked' onclick=removeObserRowCF(this,'"
						+ master.getId() + "') value='" +master.getId()
						+ "' class='classO' /> <input type='hidden' value=" + master.getId()
						+ " name='observation[" + i + "].id'  /> </td>");
				buffer.append("<td>" + master.getNotes() + "</td>");
				buffer.append("<td class='hidden'><a onclick=removeObserRowCF('" + master.getId()
						+ "')><i class='fa fa-trash-o'></i></a></td>");
				buffer.append("</tr>");
				i++;
			}

		}
		String responsej= buffer.toString();
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("responsej", responsej);
				
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);


	}catch(Exception e){
		e.printStackTrace();
	}
	
	return null;
}




public String saveObservfastJSON()throws Exception{

	if (!verifyLogin(request)) {
		return "login";
	}
	try{ 
	Connection connection= Connection_provider.getconnection();
	StringBuilder buffer = new StringBuilder();
	BufferedReader reader = request.getReader();
	String line;
	while ((line = reader.readLine()) != null) {
		buffer.append(line);
	}
	String data = buffer.toString();
	JSONObject jsonObject = new JSONObject(data);

	String observation = jsonObject.getString("observation");
	String icdcode= "0";
	MasterDAO  masterDAO =new JDBCMasterDAO(connection);
	Master master=new Master();
	master.setChiefcomplains(observation);
	master.setTemplateid(17);
	int checkid=masterDAO.checkExixstChiefComplains(observation,17);
	int id=0;
	if (checkid>0) {
		id=0;
	}else{
	 id=masterDAO.addChiefComplainName(master);
	}
	JSONObject jsonobj = new JSONObject();
	jsonobj.put("observation", id);
	
	String response1 = jsonobj.toString();
	response.setContentType("application/json");
	response.setHeader("Cache-Control", "no-cache");
	response.getWriter().write(response1);

	
	
	}catch(Exception e){
		e.printStackTrace();
	}
	 
	 return null;
 }




public String searchChargesJSONCF()throws Exception{

	if (!verifyLogin(request)) {
		return "login";
	}
	try{
		Connection connection= Connection_provider.getconnection();
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
	
		String search= jsonObject.getString("search");

		String selectecharges= jsonObject.getString("selectecharges");
		
		ArrayList<Master> appointmentchargesList = ipdDAO.getAllChargesList(search);
		StringBuilder builder = new StringBuilder();
		for (Master master : appointmentchargesList) {
			boolean flag = false;
			
			 for (String s : selectecharges.split(",")) {
			 
			    if (s == null) { 
				 continue; 
				 }
			  
			  int d = Integer.parseInt(s); 
			  if (d == 0) { 
				  
				  continue; 
				  } if (d == master.getId()) {
					  flag = true;
					  break; 
					  }
				  }
			 
			
			if (flag) {

				builder.append("<tr>");
				builder.append("<td><input type='checkbox' checked='checked' onclick='setCheckedChargesCF(this)'  value='"
						+ master.getId() + "'  /></td>");
				builder.append("<td>" + master.getChargename()+ "</td>");
				builder.append("</tr>");

			} else {
				builder.append("<tr>");
				builder.append("<td><input type='checkbox'  onclick='setCheckedChargesCF(this)'  value='"
						+ master.getId() + "'  /></td>");
				builder.append("<td>" +master.getChargename()+ "</td>");
				builder.append("</tr>");

		   }
			
		}
	String responsej=	builder.toString();
	JSONObject jsonobj = new JSONObject();
	jsonobj.put("responsej", responsej);
	String response1 = jsonobj.toString();
	response.setContentType("application/json");
	response.setHeader("Cache-Control", "no-cache");
	response.getWriter().write(response1);


		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return null;
}




public String setAllChargesJSONCF(){

	if (!verifyLogin(request)) {
		return "login";
	}
	
	try{
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterdao= new JDBCMasterDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		StringBuffer buffer= new StringBuffer();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);

		String selectecharges = jsonObject.getString("selectecharges");
		int i = 0;
		for (String s : selectecharges.split(",")) {

			int d = Integer.parseInt(s);
			if (d == 0) {
				continue;
			} else {

				Master master = masterdao.getChragesName(s);
				buffer.append("<tr>");
				buffer.append("<td><input type='checkbox' checked='checked' onclick=removeChargesRowCF(this,'"
						+ master.getId() + "') value='" +master.getId()
						+ "' class='classCN' /> <input type='hidden' value=" + master.getId()
						+ " name='charges[" + i + "].id'  /> <input type='hidden' value='" + master.getChargename()+"' name='charges[" + i +"].chargename'  />"
										+ "<input type='hidden' value=" + master.getCharge()
										+ " name='charges[" + i +"].charge'  />"
										+ " </td>");
				buffer.append("<td>" + master.getChargename() + "</td>");
				buffer.append("<td class='hidden'><a onclick=removeChargesRowCF('" + master.getId()
						+ "')><i class='fa fa-trash-o'></i></a></td>");
				buffer.append("<input type='hidden' id='treatcharge' name='treatcharge' value='"+master.getCharge()+"'>");
				buffer.append("<input type='hidden' id='chargetype' name='chargetype' value='"+master.getChargetype()+"'>");
				buffer.append("</tr>");
				i++;
			}

		}
		String responsej= buffer.toString();
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("responsej", responsej);
				
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);


	}catch(Exception e){
		e.printStackTrace();
	}
	
	return null;
}



public String setToothJsonCF(){

	if (!verifyLogin(request)) {
		return "login";
	}
	
	try{
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterdao= new JDBCMasterDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		StringBuffer buffer= new StringBuffer();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);

		String selecttooth = jsonObject.getString("selecttooth");
		String newcharge = jsonObject.getString("newcharge");
		int i = 0;
		

				Master master = masterdao.getChragesName(newcharge);
				buffer.append("<tr>");
				buffer.append("<td><input type='checkbox' checked='checked' onclick=removeChargesRowCF(this,'"
						+ master.getId() + "') value='" +master.getId()
						+ "' class='classCN' /> <input type='hidden' value=" + master.getId()
						+ " name='charges[" + i + "].id'  /> <input type='hidden' value='" + master.getChargename()+"' name='charges[" + i +"].chargename'  />"
										+ "<input type='hidden' value=" + master.getCharge()
										+ " name='charges[" + i +"].charge'  />"
										+ " </td>");
				buffer.append("<td>" + master.getChargename() +" "+selecttooth+ "</td>");
				buffer.append("<td class='hidden'><a onclick=removeChargesRowCF('" + master.getId()
						+ "')><i class='fa fa-trash-o'></i></a></td>");
				buffer.append("<input type='hidden' id='treatcharge' name='treatcharge' value='"+master.getCharge()+"'>");
				buffer.append("<input type='hidden' id='chargetype' name='chargetype' value='"+master.getChargetype()+"'>");
				buffer.append("</tr>");
				i++;
		
		String responsej= buffer.toString();
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("responsej", responsej);
				
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);


	}catch(Exception e){
		e.printStackTrace();
	}
	
	return null;
}

public String saveApmtCharges() {
	try {
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterdao= new JDBCMasterDAO(connection);
		String chargename=request.getParameter("chargename");
		String charge=request.getParameter("charge");
		
		String toothnum=request.getParameter("toothnum");
		String temp[]=toothnum.split(",");
		int size = temp.length;
		
		double totalcharges=Double.parseDouble(charge)*size;
		//System.out.println("Size of the array: " + totalcharges);
		int result=masterdao.insertCharges(""+totalcharges,chargename);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("");
	} catch (Exception e) {
		e.printStackTrace();	}
	return null;
}

}