package com.apm.Emr.web.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.eclipse.jetty.util.ajax.JSON;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Api.MobileApiAction;
import com.apm.Appointment.eu.bi.AppointmentDAO;
import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.AssesmentForms.eu.bi.AssessmentFormDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCAssessmentFormDAO;
import com.apm.AssesmentForms.web.action.Template;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCCompleteAptmDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Breadcrumbs;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.DiaryManagement.web.action.EmbeddedImageEmailUtil;
import com.apm.DiaryManagement.web.action.SmsService;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.bi.PrescriptionDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCPrescriptionDAO;
import com.apm.Emr.eu.entity.Cbc;
import com.apm.Emr.eu.entity.Emr;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Emr.eu.entity.InvstTemplate;
import com.apm.Emr.eu.entity.RatingComparator;
//import com.apm.Emr.eu.entity.Report;
import com.apm.Emr.web.form.EmrForm;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryVendorDAO;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Log.eu.bi.AccountLogDAO;
import com.apm.Log.eu.blogic.jdbc.JDBCAccountLogDAO;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Master.eu.bi.InvestigationMasterDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.PackageMasterDAO;
import com.apm.Master.eu.bi.TreatmentTypeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCInvestigationMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCPackageMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCTreatmentTypeDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.PackageMaster;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.Tools.eu.bi.SMSTemplateDAO;
import com.apm.Tools.eu.blogic.jdbc.JDBCSMSTemplateDao;
import com.apm.Tools.eu.entity.EmailTemplate;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Age;
import com.apm.common.utils.AgeCalculator;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.common.web.utils.PopulateList;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.sun.javaws.exceptions.ExitException;

public class InvestigationAction extends BaseAction implements Preparable, ModelDriven<EmrForm>{
	
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
    Pagination pagination=new Pagination(15, 1);
	

	
	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}



	EmrForm emrForm = new EmrForm();
	
	public String execute() throws Exception {
		if (!verifyLogin(request)) {

			return "login";
		}

		Connection connection = null;
		try {
			
			
			session.removeAttribute("sessionadmissionid");
			//session.removeAttribute("invstypelist");

		/*	
			ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			
			if(indentflowlisttemp!=null){
				if(indentflowlisttemp.size()==2){
					indentflowlisttemp.remove(1);
				}
			}
			boolean isavilablemodule= false;
			int modulecount =0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if(breadcrumbs.getName().equals("Investigation Dashboard")){
					isavilablemodule =true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				}else{
					indentflowlist.add(breadcrumbs);
				}
			}
			
			
			
			if(!isavilablemodule){
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Investigation Dashboard");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("Investigation");
				breadcrumbs.setIscurrent(true);
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);*/
			
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			InvestigationMasterDAO investigationMasterDAO = new JDBCInvestigationMasterDAO(connection);
			String invsttype=emrForm.getInvsttype();
			String fromdate=emrForm.getFromdate();
			String todate=emrForm.getTodate();
			String filter_status = emrForm.getFilter_status();
			String filter_ward = emrForm.getFilter_ward();
			String outsource = emrForm.getOutsource();
			String actiontype=request.getParameter("IpdpatientType");
			String fakclientid=request.getParameter("fake_clientid");
			String fuhid=request.getParameter("uhid");
			
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> invst_temp_list = masterDAO.getIpdTemplateListNamesList("Investigations");
			emrForm.setInvst_templt_list(invst_temp_list);
			
			if(actiontype==null){
				actiontype="0";
			}else if(actiontype.equals("")){
				actiontype="0";
			}
			
			if(filter_status==null){
				filter_status="0";
			}else if(filter_status.equals("")){
				filter_status="0";
			}
			
			if(filter_ward==null){
				filter_ward="0";
			}else if(filter_ward.equals("")){
				filter_ward="0";
			}
			
		/*	if(emrForm.getSearchText()==null){
				
				fromdate = (String)session.getAttribute("fromdate1");
				todate = (String)session.getAttribute("todate1");
				String searchtext = (String)session.getAttribute("searchtext1");
				emrForm.setSearchText(searchtext);
				
			}
			*/
			
			if(fromdate!=null){
				
				if(!fromdate.equals("")){
				  fromdate=DateTimeUtils.getCommencingDate1(fromdate);
				}else {
					fromdate=null;
				}
			} else {
				/*fromdate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				String str[]=fromdate.split(" ");
				fromdate=str[0];*/
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				//cal.add(Calendar.DATE, -1);
				fromdate = dateFormat.format(cal.getTime());			
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			}
			
			
			if(todate!=null){
				if(!todate.equals("")){
				  todate=DateTimeUtils.getCommencingDate1(todate);
				}else {
					todate=null;
				}
			} else {
				
				todate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				String str[]=todate.split(" ");
				todate=str[0];
				
			}
		
			
			
			
			if(invsttype!=null){
				
				if(invsttype.equals("0")|| invsttype.equals("")){
					invsttype=null;
				}
			}
			String jobtitle = emrForm.getJobtitle();
			if(jobtitle!=null){
				if(jobtitle.equals("0")){
					jobtitle=null;
				}
			}
			String invstsecid = emrForm.getInvstsecid();
			if(invstsecid!=null ){
				if(invstsecid.equals("0")|| invstsecid.equals("")){
					invstsecid=null;
				}
			}
			String unewuhid=request.getParameter("uhid");
			if(unewuhid!=null){
				if(!unewuhid.equals("")){
				emrForm.setSearchText(unewuhid);
				emrForm.setIsindivisual("1");
				String c=investigationDAO.getClientdtails(unewuhid);
				String e[]=c.split("~");
				emrForm.setIndivisualclientid(DateTimeUtils.isNull(e[0]));
				emrForm.setIndivisualclientname(DateTimeUtils.isNull(e[1]));
				
				emrForm.setDoctorname(e[2]);
				emrForm.setIndgender(e[3]);
				emrForm.setIndage(e[4]);
				session.removeAttribute("invstList");
				session.setAttribute("invstList", null);
				
				session.removeAttribute("invstypelist");
				session.setAttribute("invstypelist", null);
				}else{
					emrForm.setIsindivisual("2");
					emrForm.setIndivisualclientid("");
					emrForm.setIndivisualclientname("");
					
				}
				
				}else{
				emrForm.setIsindivisual("2");
				emrForm.setIndivisualclientid("");
				emrForm.setIndivisualclientname("");
				emrForm.setDoctorname("");
				emrForm.setIndgender("");
				emrForm.setIndage("");
			}
			
			String searchText = emrForm.getSearchText();
			
			if(searchText!=null){
				if(searchText.equals("")){
					searchText =null;
				}
			}
			
			if(outsource==null){
				outsource="0";
			}else if(outsource.equals("")){
				outsource ="0";
			}
			String isdeleted= emrForm.getIsdeleted();
			if(isdeleted==null){
				isdeleted="";
			}
			
			if(actiontype.equals("1")) {
				String fdate=investigationDAO.getfakedatebyClientid(fakclientid);
				 fdate=DateTimeUtils.isNull(fdate);
				  if(!fdate.equals("")) {
					  fdate=fdate.split(" ")[0];
				   }
				  actiontype=DateTimeUtils.isNull(actiontype);
				  if(!fdate.equals("")) {
				    if(actiontype.equals("1")){
					  fromdate=fdate;
					  searchText=unewuhid;
				}
			  }
				
			}
			
		    int count=investigationDAO.getAllInvestigationCount(searchText,jobtitle,invsttype,fromdate,todate,filter_status,filter_ward,loginInfo,invstsecid,outsource,isdeleted);
			pagination.setPreperties(count);
			
			ArrayList<Investigation> investigationList = investigationDAO.getViewInvestigationList(pagination,searchText,jobtitle,invsttype,fromdate,todate,loginInfo,filter_status,filter_ward,invstsecid,outsource,isdeleted);
			pagination.setPage_records(investigationList.size());
			
			if(loginInfo.isInvest_order()){
				
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(sdf.parse(fromdate));
				cal.add(Calendar.DATE, -1); 
				String newTodate = sdf.format(cal.getTime());
				cal.add(Calendar.DATE, -2);
				String newFromdate = sdf.format(cal.getTime());
//				System.out.println(newFromdate+"--------------"+newTodate);
				 int countextra=investigationDAO.getAllInvestigationCount(searchText,jobtitle,invsttype,newFromdate,newTodate,"1",filter_ward,loginInfo,invstsecid,outsource,"0");
					pagination.setPreperties(count+countextra);
					
					ArrayList<Investigation> investigationListnew = investigationDAO.getViewInvestigationList(pagination,searchText,jobtitle,invsttype,newFromdate,newTodate,loginInfo,"1",filter_ward,invstsecid,outsource,"0");
					ArrayList<Investigation> allInvestigations=new ArrayList<Investigation>();
					allInvestigations.addAll(investigationListnew);
					allInvestigations.addAll(investigationList);
					
					investigationList=allInvestigations;
					
					pagination.setPage_records(investigationList.size());
					
						
			}
			
			emrForm.setTotalRecords(count);
			emrForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			emrForm.setInvestigationList(investigationList);
					
			//emrForm.setPriscriptionlist(priscriptionlist);

			if(fromdate!=null){
				
				fromdate=DateTimeUtils.getCommencingDate1(fromdate);
			}
			else {
				
				fromdate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				String str[]=fromdate.split(" ");
				fromdate=DateTimeUtils.getCommencingDate1(str[0]);
				
			}
			if(todate!=null){
				todate=DateTimeUtils.getCommencingDate1(todate);
			}else {
				todate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				String str[]=todate.split(" ");
				todate=DateTimeUtils.getCommencingDate1(str[0]);		
			}
			
			ArrayList<Master> allSectionList = investigationMasterDAO.getAllSectionList();
			emrForm.setAllSectionList(allSectionList);
			emrForm.setInvstsecid(invstsecid);
			emrForm.setFromdate(fromdate);
			emrForm.setTodate(todate);
			emrForm.setInvsttype(invsttype);
			emrForm.setFilter_status(filter_status);
			emrForm.setFilter_ward(filter_ward);
			emrForm.setSearchText(searchText);
			
			String investigation_approve = String.valueOf(loginInfo.isInvestigation_approve());
			if(investigation_approve.equals("true")){
				investigation_approve ="1";
			}else{
				investigation_approve ="0";
			}
			/*if(loginInfo.getJobTitle().equals("Admin")){
				investigation_approve ="1";
			}*/
			
			emrForm.setInvestigation_approve(investigation_approve);
			
			ArrayList<Investigation> outsourcelist = investigationDAO.getOutSourceList();
			Investigation outSourceExtra= new Investigation();
			outSourceExtra.setName("All Outsource");
			outSourceExtra.setId(10000);
			outsourcelist.add(0, outSourceExtra);
			emrForm.setOutsourcelist(outsourcelist);
			if(loginInfo.getJobTitle().equals("Pathlab")){
				
				return "pathlab";
			}
			
			ArrayList<Investigation> thirdPartyTypeNameList = new ArrayList<Investigation>();
			thirdPartyTypeNameList=investigationDAO.getallThirdpartynamelist();
			emrForm.setThirdPartyTypeNameList(thirdPartyTypeNameList);
			
			ArrayList<Investigation> wardnamelist = new ArrayList<Investigation>();
			wardnamelist=investigationDAO.getallwardnamelist();
			emrForm.setWardnamelist(wardnamelist);
			
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return SUCCESS;
	}
	
	
	public String elabs(){
		
		return "elabs";
	}
	
	public String packageinvtype(){
		
	String sectionid = request.getParameter("id");
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			
			ArrayList<Master>invTypeList = investigationDAO.getPackageInvTypeList(sectionid);
			
			StringBuffer str = new StringBuffer();
			str.append("<select onchange='showNameData(this.value)' name='invsttype' id='invsttype' class='form-control chosen-select'>");
			str.append("<option value='0'>Investigation Type</option>");
			for(Master master : invTypeList){
				str.append("<option value='"+master.getId()+"'>"+master.getName()+"</option>");
			}
			str.append("</select>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str.toString()); 
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	public String invtype(){
		String sectionid = request.getParameter("sectionid");
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			
			ArrayList<Master>invTypeList = investigationDAO.getInvTypeList(sectionid);
			
			StringBuffer str = new StringBuffer();
			str.append("<select onchange='showNameData(this.value)' name='invsttype' id='invsttype' class='form-control chosen-select'>");
			str.append("<option value='0'>Investigation Type</option>");
			for(Master master : invTypeList){
				str.append("<option value='"+master.getId()+"'>"+master.getName()+"</option>");
			}
			str.append("</select>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str.toString()); 
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return null;
	}
	
	public String graph(){
		String templateid = emrForm.getSelectedtemplateid();
		String invsttype = emrForm.getSelectedinvsttype();
//		System.out.println(templateid);
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			ArrayList<Investigation>graphInvstNameList = investigationDAO.getInvstNameList(templateid);
			
			session.setAttribute("graphInvstNameList", graphInvstNameList);
			session.setAttribute("invstgraphclientid", emrForm.getSelectedclientid());
			
			
			if(emrForm.getSelectedclientid()!=null){
				
				Client client=clientDAO.getClientDetails(emrForm.getSelectedclientid());
				String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
				emrForm.setClientname(fullname);
			}
			
			
			String fromDate = emrForm.getFromDate();
			String toDate = emrForm.getToDate();	
			if(fromDate.equals("")){
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -7); 
				fromDate = dateFormat.format(cal.getTime());
				emrForm.setFromDate(fromDate);
			}
			if(toDate.equals("")){
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				//cal.add(Calendar.DATE, -7); 
				toDate = dateFormat.format(cal.getTime());
				emrForm.setToDate(toDate);
			}
			
			
			if(!fromDate.equals("")){
				String temp[]= fromDate.split("/");
				fromDate = temp[2]+"-"+temp[1]+"-"+temp[0];
			}
			if(!toDate.equals("")){
				String temp1[]= toDate.split("/");
				toDate = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
			}
			
			session.setAttribute("invstgraphfromdate", fromDate);
			session.setAttribute("invstgraphtodate",toDate);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return "graph";
	}
	
	public String payment(){
		
		Connection connection = null;
		try {
			connection=Connection_provider.getconnection();
			EmrDAO emrDAO=new JDBCEmrDAO(connection);
			InvestigationDAO investigationDAO=new JDBCInvestigationDAO(connection);
			
			String fromDate = emrForm.getFromDate();
			String toDate = emrForm.getToDate();	
			
			if(fromDate.equals("")){
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -7); 
				fromDate = dateFormat.format(cal.getTime());
				emrForm.setFromDate(fromDate);
			}
			if(toDate.equals("")){
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				//cal.add(Calendar.DATE, -7); 
				toDate = dateFormat.format(cal.getTime());
				emrForm.setToDate(toDate);
			}
			
			
			if(!fromDate.equals("")){
				String temp[]= fromDate.split("/");
				fromDate = temp[2]+"-"+temp[1]+"-"+temp[0];
			}
			if(!toDate.equals("")){
				String temp1[]= toDate.split("/");
				toDate = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
			}
			
			
			ArrayList<Client>clientList = investigationDAO.getPaymentClientList(emrForm.getSearchText(),fromDate,toDate,loginInfo);
			emrForm.setClientList(clientList);
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return "payment";
	}
	
	
	
	public String createcharge() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			InvestigationDAO investigationDAO=new JDBCInvestigationDAO(connection);
			CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection); 
			AppointmentTypeDAO appointmentTypeDAO=new JDBCAppointmentTypeDAO(connection);
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
			
			String chargeIds=request.getParameter("invnameId");
			String charges=request.getParameter("charges");
			String clientid=request.getParameter("patientid");
			String practitionerId=request.getParameter("doctorid");
			String invsparentid=request.getParameter("invsparentid");
			int ipdid=investigationDAO.getIpdIdFromInvestigation(invsparentid);
			
			Client client=clientDAO.getClientDetails(clientid);
			String clientName=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
			String date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			String practitionerName=userProfileDAO.getFullName(practitionerId);
			
			String ipd="0";
			if(ipdid>0){
				 ipd="1";
			}
			
			CompleteAppointment completeAppointment=new CompleteAppointment();
			completeAppointment.setClientId(clientid);
			completeAppointment.setPractitionerId(practitionerId);
			completeAppointment.setUser(clientName);
			completeAppointment.setInvoiceDate(date);
			completeAppointment.setChargeType("Charge");
			completeAppointment.setAppointmentid("0");
			completeAppointment.setPractitionerName(practitionerName);
			completeAppointment.setLocation("1");
			completeAppointment.setIpd(ipd);
			completeAppointment.setGpriscid("0");
			completeAppointment.setGinvstid(invsparentid);
			completeAppointment.setAdditionalcharge_id("0");
			completeAppointment.setWardid("0");
			completeAppointment.setIpdid(ipdid);
			completeAppointment.setCommencing(date);
			
			String tpid="0";
			if(client.getWhopay()!=null){
				
				if(client.getWhopay().equals("Client")){
					completeAppointment.setPayBuy("0");
				} else {
					completeAppointment.setPayBuy("1");
					tpid=client.getTypeName();
				}
			}
			
			int invoiceid = completeAptmDAO.saveAmpmInvoice(completeAppointment,loginInfo.getId(),loginInfo.getUserId());
			
			double debit=0;
			String[] strcharges=charges.split(",");
			
			int i=-1;
			for(String str:chargeIds.split(",")){
				i++;
				if(str.equals("0")){
					continue;
				}
				debit=debit+Double.parseDouble(strcharges[i]);
				completeAppointment.setCharges(strcharges[i]);
				completeAppointment.setMasterchargetype("Investigation");
				completeAppointment.setAdditionalcharge_id(str);
				String investigationName=appointmentTypeDAO.getChargeName(str);
				completeAppointment.setApmtType(investigationName);
				completeAppointment.setQuantity(1);
				int result=completeAptmDAO.saveInvoiceAssesment(completeAppointment, invoiceid);
				
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return null;
	}
	
	
	
	
	
	
	public String createinvoice() throws Exception {
		
		Connection connection=null; 
		try {
			connection=Connection_provider.getconnection();
			CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection);
			AppointmentTypeDAO appointmentTypeDAO=new JDBCAppointmentTypeDAO(connection);
			AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
			AccountLogDAO accountLogDAO=new JDBCAccountLogDAO(connection);
			InvestigationDAO investigationDAO=new JDBCInvestigationDAO(connection);
			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			
			String chargeIds=request.getParameter("invnameId");
			String charges=request.getParameter("charges");
			String clientid=request.getParameter("patientid");
			String practitionerId=request.getParameter("doctorid");
			String invsparentid=request.getParameter("invsparentid");
			int ipdid=investigationDAO.getIpdIdFromInvestigation(invsparentid);
			
			int tempinvoiceid= investigationDAO.getifInvoiceCreated(invsparentid);
			
			
			
			CompleteAppointment completeAppointment=new CompleteAppointment();
			completeAppointment.setClientId(clientid);
			completeAppointment.setPractitionerId(practitionerId);
			completeAppointment.setIpdid(ipdid);
			String invoiceDate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			
			Client client=clientDAO.getClientDetails(clientid);
			String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
			String practitionerName=userProfileDAO.getFullName(practitionerId);
			
			String tpid="0";
			if(client.getWhopay()!=null){
				
				if(client.getWhopay().equals("Client")){
					completeAppointment.setPayBuy("0");
				} else {
					completeAppointment.setPayBuy("1");
					tpid=client.getTypeName();
				}
			}
			
			completeAppointment.setClient(fullname);
			completeAppointment.setUser(practitionerName);
			completeAppointment.setChargeType("Submit");
			completeAppointment.setAppointmentid("0");
			completeAppointment.setInvoiceDate(invoiceDate);
			completeAppointment.setCommencing(invoiceDate);
			int invoiceid=0;
			if(tempinvoiceid>0){
			   
				 invoiceid=tempinvoiceid;
			} else {
				 invoiceid=completeAptmDAO.saveAmpmInvoice(completeAppointment,loginInfo.getId(),loginInfo.getUserId());
			}
			
			
			
			double debit=0;
			String[] strcharges=charges.split(",");
			
			int i=-1;
			for(String str:chargeIds.split(",")){
				i++;
				if(str.equals("0")){
					continue;
				}
				debit=debit+Double.parseDouble(strcharges[i]);
				completeAppointment.setCharges(strcharges[i]);
				completeAppointment.setMasterchargetype("Investigation");
				completeAppointment.setAdditionalcharge_id(str);
				String investigationName=appointmentTypeDAO.getChargeName(str);
				completeAppointment.setApmtType(investigationName);
				completeAppointment.setQuantity(1);
				if(tempinvoiceid==0){
				  int result=completeAptmDAO.saveInvoiceAssesment(completeAppointment, invoiceid);
				}
			}
			
			String payby="";
			if(completeAppointment.getPayBuy().equals("0")){
				payby="Client";
			} else {
				payby="Third Party";
			}
			double discount=0; 
			String payAmount=String.valueOf(debit);
			//  Now Invoice
			
			int chargeInvoiceid = accountsDAO.saveChargesInvoice(payby,completeAppointment.getCommencing(),Integer.parseInt(completeAppointment.getClientId()),debit,discount,"",Integer.parseInt(tpid),"0",DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),loginInfo.getId(),"0","Submit",0,"","",practitionerId,0,0,"0");
			if(chargeInvoiceid>0){
				
				   UserProfile userProfile=userProfileDAO.getUserprofileDetails(Integer.parseInt(practitionerId));
				   int d=investigationDAO.updateInvsInvoiceId(invsparentid,chargeInvoiceid,userProfile.getUserid());
			}
			
			
			
			//log data			
			int result = accountLogDAO.saveChargesInvoice(payby,completeAppointment.getCommencing(),Integer.parseInt(completeAppointment.getClientId()),debit,discount,"0",Integer.parseInt(tpid),"0",chargeInvoiceid,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
			
			
			  int save = accountsDAO.saveChargesAssesment(invoiceid,chargeInvoiceid);
			  int update = accountsDAO.updateChargeType(String.valueOf(chargeInvoiceid),"Submit");
			  
			  Accounts accounts = accountsDAO.getInvoiceChargesDetails(""+invoiceid);
			  //save payment
			 int re = accountsDAO.saveChargesPayment(completeAppointment.getClientId(),invoiceid,payAmount,"Cash",Integer.parseInt(tpid),"",DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),0,loginInfo.getUserId(),"","",accounts.getPredayornot(),accounts.getDate(),accounts.getItype(),"","");
			  
			 int result1 = accountLogDAO.saveChargesPayment(completeAppointment.getClientId(),invoiceid,payAmount,"Cash",Integer.parseInt(tpid),"",DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),"");
			
			  
			  
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			
			connection.close();
		}
		
		
		return null;
	}
	
	
	
	
	public String template() throws Exception{
		
		Connection connection = null;
		try {
			connection=Connection_provider.getconnection();
			InvestigationDAO investigationDAO=new JDBCInvestigationDAO(connection);
			
			String clientId= request.getParameter("clientId");
			ArrayList<InvstTemplate>templateList = investigationDAO.getTemplateList();
			emrForm.setTemplateList(templateList);
			
			//invstList
			session.removeAttribute("invstList");
			
			
			String fromDate = emrForm.getFromDate();
			String toDate = emrForm.getToDate();	
			if(fromDate.equals("")){
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -90); 
				fromDate = dateFormat.format(cal.getTime());
				emrForm.setFromDate(fromDate);
			}
			if(toDate.equals("")){
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				//cal.add(Calendar.DATE, -7); 
				toDate = dateFormat.format(cal.getTime());
				emrForm.setToDate(toDate);
			}
			
			
			if(!fromDate.equals("")){
				String temp[]= fromDate.split("/");
				fromDate = temp[2]+"-"+temp[1]+"-"+temp[0];
			}
			if(!toDate.equals("")){
				String temp1[]= toDate.split("/");
				toDate = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
			}
			
			session.setAttribute("invstgraphfromdate", fromDate);
			session.setAttribute("invstgraphtodate",toDate);
			
			
			if(clientId!=null){
				 
				  if(!clientId.equals("")){
					    
					    ClientDAO clientDAO=new JDBCClientDAO(connection); 
					    Client client=clientDAO.getClientDetails(clientId);
					    String fullname= client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
					    emrForm.setClient(fullname);
					    emrForm.setClientId(clientId);
					    
					    emrForm.setInvestigationList(investigationDAO.getNumericInvestigationListOfPatient(fromDate, toDate, clientId));
					    
				  }
				 
			}
			
			
			
		}catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return "template";
	}
	
	
	public void createPackImage() throws Exception{
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			EmrDAO emrDAO=new JDBCEmrDAO(connection);
			InvestigationDAO investigationDAO=new JDBCInvestigationDAO(connection);
			ArrayList<Emr> dicomList= emrDAO.getDicomImageData();
			int i=0;
			for(Emr dicom : dicomList){
			    InputStream in =dicom.getDicomimageData();
	           		   
			    String filepath = request.getRealPath("/liveData/document/"+dicom.getInvstid()+"_dimage.png");
			    File fc = new File(filepath);
			    OutputStream f = new FileOutputStream(new File(filepath));
			    i++;
			    int c = 0;
			    //if(!fc.exists()){
			     while ((c = in.read()) > -1) {
			      f.write(c);
			     }
			    //}
			    f.close();
			    in.close();
			    
			    boolean isimgexist=emrDAO.ispackImageExists(dicom.getImgid());
			    if(!isimgexist){
			    	
			    	
			    	Investigation investigation=investigationDAO.getEditInvestigation(String.valueOf(dicom.getInvstid()));
			    	
                    String fileName=""+dicom.getInvstid()+"_dimage.png";
			    	dicom.setFileName(fileName);
			    	int pratitionerid=Integer.parseInt(investigation.getPrectionerid());
			    	dicom.setPractitionerId(pratitionerid);
			    	dicom.setCondition_id(investigation.getConditionid());
			    	dicom.setDoctType("Investigation");
			        int result = emrDAO.savePatientDocument(dicom,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),String.valueOf(dicom.getInvstid()),loginInfo.getUserId());  
			    }
			 }
				
		} catch (Exception e) {

		    e.printStackTrace();
		}
		finally{
			connection.close();
		}
	
	}
		
	
	
	public String outocharge(){
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			String ipdclientid = request.getParameter("ipdclientid");
			PackageMasterDAO packageMasterDAO = new JDBCPackageMasterDAO(connection);
			String cdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String temp[] = cdate.split(" ");
			
			String curdate = temp[0];
			String ipdinvreq = request.getParameter("ipdinvreq");
			ArrayList<String>requestedInvstgationList = investigationDAO.getRequestedInvestigationList(curdate,ipdclientid,ipdinvreq);
			
//			System.out.println(ipdinvreq);
			
		for(String selectedid : requestedInvstgationList){
			
			//String selectedid = request.getParameter("selectedid");
			
			
			//clientid,invsttypeid
			Investigation invparent = investigationDAO.getInvestigationTyeID(selectedid);
			
			//invstname,charges
			Investigation invmaster = investigationDAO.getInvstTypeCharges(invparent.getInvsttypeid());
			
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			Client client = clientDAO.getClientDetails(invparent.getClientId());
			String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
			
			String payBuy = "0";
			if(client.getWhopay().equals(Constants.PAY_BY_THIRD_PARTY)){
				payBuy = "1";
			}
			
			CompleteAppointment completeAppointment = new CompleteAppointment();
			
			completeAppointment.setUser(clientname);
			completeAppointment.setApmtType("0");
			completeAppointment.setManualcharge(invmaster.getInvsttype());
			completeAppointment.setCharges(invmaster.getCharge());
			completeAppointment.setClientId(invparent.getClientId());
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());
			completeAppointment.setCommencing(date);
			completeAppointment.setPayBuy(payBuy);
			completeAppointment.setMarkAppointment("1");
			completeAppointment.setQuantity(1);
			completeAppointment.setMasterchargetype("INVESTIGATION");
			
			completeAppointment.setProdid(0);
			completeAppointment.setAppointmentid("0");
			completeAppointment.setPractitionerId(invparent.getPractitionerName());
			completeAppointment.setPractitionerName("");
			completeAppointment.setGinvstid(selectedid);
			
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			
			/*if(Double.parseDouble(invmaster.getCharge())>0){
				int result = completeAptmDAO.saveCharge(completeAppointment,"0");
			}else{
				ArrayList<String>invstClientChargeList = investigationDAO.getinvstClientChargeList(selectedid);
				for(String str : invstClientChargeList){
					Investigation clinve = investigationDAO.getClientInvstChargeDetails(str);
					completeAppointment.setManualcharge(clinve.getInvstname());
					completeAppointment.setCharges(clinve.getCharge());
					int result = completeAptmDAO.saveCharge(completeAppointment,"0");
				}
			}*/
			
			int pkg = investigationDAO.getAppliedPkgID(selectedid);
			completeAppointment.setPkgid(pkg);
			
			if(pkg!=0){
				PackageMaster packageMaster = packageMasterDAO.getPerticularPackage(""+pkg);
				completeAppointment.setManualcharge(packageMaster.getName());
				completeAppointment.setCharges(packageMaster.getAmount());
				
				ArrayList<String>invstClientChargeList = investigationDAO.getinvstClientChargeList(selectedid);
				int roundcharge = investigationDAO.checkRoundCharge(invparent.getInvsttypeid());
				if(roundcharge==1){
					invstClientChargeList = new ArrayList<String>();
					String chargename = investigationDAO.getMasterChargeName(invparent.getInvsttypeid()); 
					invstClientChargeList.add(chargename);
				}
				for(String str : invstClientChargeList){
					AppointmentType appointmentType = investigationDAO.getInvestigationPaybyCharge(client.getWhopay(),str,client.getTypeName(),client.getId(),loginInfo);
					if(appointmentType.getCharges()==null){
						appointmentType.setCharges("0");
					}
					completeAppointment.setAdditionalcharge_id(Integer.toString(appointmentType.getId()));
					break;
				}
				
				
				
				int result = completeAptmDAO.saveCharge(completeAppointment,"0",loginInfo.getId());
				break;
			}else{
				ArrayList<String>invstClientChargeList = investigationDAO.getinvstClientChargeList(selectedid);
				int roundcharge = investigationDAO.checkRoundCharge(invparent.getInvsttypeid());
				if(roundcharge==1){
					invstClientChargeList = new ArrayList<String>();
					String chargename = investigationDAO.getMasterChargeName(invparent.getInvsttypeid()); 
					invstClientChargeList.add(chargename);
				}
				for(String str : invstClientChargeList){
					//Investigation clinve = investigationDAO.getClientInvstChargeDetails(str);
					completeAppointment.setManualcharge(str);
					
					AppointmentType appointmentType = investigationDAO.getInvestigationPaybyCharge(client.getWhopay(),str,client.getTypeName(),client.getId(),loginInfo);
					if(appointmentType.getCharges()==null){
						appointmentType.setCharges("0");
					}
					completeAppointment.setCharges(appointmentType.getCharges());
					
					
					if(pkg!=0){
						String pkgcharge = investigationDAO.getPkgCharge(pkg,str);
						completeAppointment.setCharges(pkgcharge);
					}
//					System.out.println(selectedid);
					
					completeAppointment.setAdditionalcharge_id(Integer.toString(appointmentType.getId()));
					int result = completeAptmDAO.saveCharge(completeAppointment,"0",loginInfo.getId());
				}
			}
			
			
			
		}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	 
	
	public String autostandardcharge(){
		Connection connection = null;
		try{
			String clientid = request.getParameter("clientid");
			String ipdid=request.getParameter("ipdid");
			String standardcharges=request.getParameter("standardids");
			String checkStandardChargeExist=request.getParameter("checkStandardChargeExist");
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			String completApmtIds="0";
			
			//int res=completeAptmDAO.truncateApmcompAppointment();
			
			
			for(String stid:standardcharges.split(",")) {

				
			    if(stid.equals("0")){
			    	continue;
			    }
				
				Master standardChargeMaster=masterDAO.getStandardCharge(stid);
			    
				/*//clientid,invsttypeid
				Investigation invparent = investigationDAO.getInvestigationTyeID(selectedid);
			
				//invstname,charges
				Investigation invmaster = investigationDAO.getInvstTypeCharges(invparent.getInvsttypeid());*/
			
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				Client client = clientDAO.getClientDetails(clientid);
				String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
			
				String payBuy = "0";
				if(client.getWhopay().equals(Constants.PAY_BY_THIRD_PARTY)){
					payBuy = "1";
				}
			
				CompleteAppointment completeAppointment = new CompleteAppointment();
			
				completeAppointment.setUser(clientname);
				completeAppointment.setApmtType("0");
				completeAppointment.setManualcharge(standardChargeMaster.getName());
				completeAppointment.setCharges(standardChargeMaster.getCharge());
				completeAppointment.setClientId(clientid);
			
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				String date = dateFormat.format(cal.getTime());
				completeAppointment.setCommencing(date);
				completeAppointment.setPayBuy(payBuy);
				completeAppointment.setMarkAppointment("1");
				completeAppointment.setQuantity(1);
				completeAppointment.setMasterchargetype("Standard Charge");
				completeAppointment.setProdid(0);
				completeAppointment.setAppointmentid("0");
				completeAppointment.setPractitionerId("0");
				completeAppointment.setIpdid(Integer.parseInt(ipdid));
				completeAppointment.setPractitionerName("");
			    completeAppointment.setAdditionalcharge_id(stid);
			
			    int result=0;
			    if(checkStandardChargeExist.equals("true")) {
			    	result=completeAptmDAO.updateStndAccessmentCharge(ipdid,standardcharges);  
			    }
			    else {
			         result = completeAptmDAO.saveCharge(completeAppointment,"0",loginInfo.getId());
			    }
			   
		   }
			
			//int stdResId=completeAptmDAO.getIfAlradyExistsStdId(ipdid,standardcharges);
		    
		    if(!checkStandardChargeExist.equals("true")) {
		    	int res=completeAptmDAO.saveStndCharge(clientid,ipdid,standardcharges);
			
				String str=standardcharges+"~"+ipdid+"~"+clientid;
		    	response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(str); 
		    }
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	

	
	public String showgroup(){
		Connection connection = null;
		try{
			String selectedid = request.getParameter("selectedid");
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			
			ArrayList<Master>groupList = investigationDAO.getGroupList(selectedid);
			
			StringBuffer str = new StringBuffer();
			str.append("<select onchange='showNameData(this.value)' class='form-control' name='invstgroup' id='invstgroup'>");
			str.append(" <option value='0' >Select Group</option>");
			
			for(Master master : groupList){
				str.append(" <option value='"+master.getId()+"' >"+master.getName()+"</option>");
			}
			
			str.append("</select>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str+""); 
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public String showname(){
		Connection connection = null;
		try{
			String selectedid = request.getParameter("selectedid");
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			InvestigationMasterDAO investigationMasterDAO = new JDBCInvestigationMasterDAO(connection);
			
			Master master1 = investigationMasterDAO.getInvestigationType(selectedid);
			
			ArrayList<Master>nameist = investigationDAO.getNameList(selectedid);
			
			StringBuffer str = new StringBuffer();
			
			/*str.append("<select  onchange='showspecimen(this.value)' class='form-control' name='invstname' id='invstname'>");
			str.append(" <option value='0' >Select Name</option>");
			
			for(Master master : nameist){
				str.append(" <option value='"+master.getId()+"' >"+master.getName()+"</option>");
			}*/
			str.append("<input type='hidden' name='rpttype' id='rpttype' value='"+master1.getReport_type()+"'>");
			str.append("<li><input type='checkbox' id='selecctall' />Select All</li>");
			for(Master master : nameist){
				str.append("<li><input class='case'  value='"+master.getId()+"' type='checkbox' name='ch"+master.getId()+"' id='ch"+master.getId()+"'> "+master.getName()+"</li>");
			}
           				
			str.append("</select>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str+""); 
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public String showspecimen(){
		
		Connection connection = null;
		try{
			String selectedid = request.getParameter("selectedid");
			session.setAttribute("selectedinvestigationnameid", selectedid);
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			
			String selectedinvestigationnameid = (String)session.getAttribute("selectedinvestigationnameid");
			
			Investigation investigation = investigationDAO.getInveStigationDetails(selectedinvestigationnameid);
			
			StringBuffer str = new StringBuffer();
			str.append("<input type='hidden' name='normvalue' id='normvalue' value='"+investigation.getNormvalue()+"'>");
			str.append("<select   class='form-control' name='specimen' id='specimen' value='"+investigation.getNormvalue()+"'>");
			str.append(" <option value='"+investigation.getSpecimen()+"' >"+investigation.getSpecimen()+"</option>");
			str.append("</select>");
			
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str+"");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public String showreport(){
		Connection connection = null;
		try{
			String selectedid = request.getParameter("selectedid");
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			
			String selectedinvestigationnameid = (String)session.getAttribute("selectedinvestigationnameid");
			
			Investigation investigation = investigationDAO.getInveStigationDetails(selectedinvestigationnameid);
			
			
			//ArrayList<Master>reportList = investigationDAO.geReportList(selectedid);
			
			StringBuffer str = new StringBuffer();
			str.append("<select   class='form-control' name='invstreporttype' id='invstreporttype'>");
			str.append(" <option value='"+investigation.getInvstreporttype()+"' >"+investigation.getInvstreporttype()+"</option>");
			str.append("</select>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str+""); 
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String showunit(){
		
		Connection connection = null;
		try{
			String selectedid = request.getParameter("selectedid");
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			
			//ArrayList<Master>unitList = investigationDAO.getUnitList(selectedid);
			
			String selectedinvestigationnameid = (String)session.getAttribute("selectedinvestigationnameid");
			
			Investigation investigation = investigationDAO.getInveStigationDetails(selectedinvestigationnameid);
			
			StringBuffer str = new StringBuffer();
			str.append("<select  class='form-control' name='invstUnit' id='invstUnit'>");
			str.append(" <option value='"+investigation.getInvstUnit()+"' >"+investigation.getInvstUnit()+"</option>");
			str.append("</select>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str+""); 
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String addtemp(){
		
		Investigation invtypeobj =new Investigation();
		String invstcode = request.getParameter("invstcode");
		String invsttype = request.getParameter("invsttype");
		String invstgroup = request.getParameter("invstgroup");
		String invstname = request.getParameter("invstname");
		String specimen = request.getParameter("specimen");
		String invstreporttype = request.getParameter("invstreporttype");
		String invstUnit = request.getParameter("invstUnit");
		String normvalue = request.getParameter("normvalue");
		
		String invsttypeid=request.getParameter("invsttypeid");
		
		String clientId = request.getParameter("clientId");
		String prectionerid = request.getParameter("prectionerid");
		String conditionid = request.getParameter("conditionid");
		
		String isFromCF = request.getParameter("isFromCF");
		
	//	Investigation investigation = new Investigation();
		
		ArrayList<Investigation>invstypelist = new ArrayList<Investigation>();
		ArrayList<String>invstExistypelist = new ArrayList<String>();
		if(session.getAttribute("invstypelist")!=null){
			invstypelist = (ArrayList<Investigation>) session.getAttribute("invstypelist");
			for(Investigation itype : invstypelist){
				invstExistypelist.add(itype.getInvsttype());
			}
		}
		
		invtypeobj.setInvsttype(invsttype);
		invtypeobj.setInvsttypeid(invsttypeid);
		if(invstExistypelist.size()!=0){
			if(invstExistypelist.contains(invsttype)==false){
				invstypelist.add(invtypeobj);
			}
		}else{
			invstypelist.add(invtypeobj);
		}
		
		

		 Object[] st = invstypelist.toArray();
	      for (Object s : st) {
	        if (invstypelist.indexOf(s) != invstypelist.lastIndexOf(s)) {
	        	invstypelist.remove(invstypelist.lastIndexOf(s));
	         }
	      }

		session.setAttribute("invstypelist", invstypelist);
		
	
		
		ArrayList<Investigation>invstList = new ArrayList<Investigation>();
		if(session.getAttribute("invstList")!=null){
			invstList = (ArrayList<Investigation>)session.getAttribute("invstList");
		}
		
		
		
		Connection connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			ArrayList<Investigation>invstnameList = investigationDAO.getInvestigationNameList(invstname);
			
			ArrayList<String>invstexisistingList = new ArrayList<String>();
			if(session.getAttribute("invstList")!=null){
				ArrayList<Investigation>investigationList = (ArrayList<Investigation>)session.getAttribute("invstList");
				
				for(Investigation inv : investigationList){
					invstexisistingList.add(inv.getInvstname()+"~"+inv.getInvsttypeid());
				}
			}
			
			int i = 0;
			for(Investigation investigation : invstnameList){
				investigation.setInvstcode(invstcode);
				investigation.setInvsttype(invsttype);
				investigation.setInvstgroup(invstgroup);
				investigation.setInvstreporttype(invstreporttype);
				investigation.setInvsttypeid(invsttypeid);
				
				investigation.setClientId(clientId);
				investigation.setPrectionerid(prectionerid);
				investigation.setConditionid(conditionid);
				investigation.setIndx(i);
				
				if(session.getAttribute("invstList")!=null){
					ArrayList<Investigation>investigationList = (ArrayList<Investigation>)session.getAttribute("invstList");
					boolean invr = invstexisistingList.contains(investigation.getInvstname()+"~"+investigation.getInvsttypeid());
					if(invr==false){
						invstList.add(investigation);
					}
				}else{
					invstList.add(investigation);
				}
				i++;
			}
			
			session.setAttribute("invstList", invstList);
			
			getInvestigationData(DateTimeUtils.numberCheck(isFromCF));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
	
	public void getInvestigationData(String isFromCF){
		try{
			
	StringBuffer str = new StringBuffer();
			ArrayList<Investigation>list = new ArrayList<Investigation>();
			ArrayList<Investigation>investigationList = (ArrayList<Investigation>)session.getAttribute("invstList");
			int i = 0;
			for(Investigation investigation  : investigationList){
				investigation.setIndx(i);
				list.add(investigation);
				i++;
			}
			Comparator<Investigation> cmp = Collections.reverseOrder(new RatingComparator());
			String investigationtype="";
			  
	        Collections.sort(list, cmp);  
			
			 i = 0;
			 if(loginInfo.isMarkhosp()) {
				 for(Investigation investigation  : list){
						str.append("<tr>");
						/*str.append("<td>"+priscription.getDate()+"</td/>");*/
						
						if(!investigationtype.equals(investigation.getInvsttype())) {
						 str.append("<td>"+investigation.getInvsttype()+"</td/>");
						
				         
						/*str.append("<td>"+investigation.getInvstgroup()+"</td/>");*/
					//	str.append("<td>"+investigation.getInvstname()+"</td/>");
						//str.append("<td>"+investigation.getSpecimen()+"</td/>");
						//str.append("<td>"+investigation.getInvstreporttype()+"</td> <td>"+investigation.getInvstUnit()+"</td/>");
						//str.append("<td>"+investigation.getInvstUnit()+"</td/>");
					/*
					 * if(DateTimeUtils.numberCheck(isFromCF).equals("1")){
					 * str.append("<td><input type='text' class='form-control' value='"
					 * +DateTimeUtils.isNull(investigation.getValue_cf())
					 * +"' onchange='changeInvCFVal("+i+",this.value)'></td>"); }
					 */
						/*str.append("<td><i onclick='showedit("+i+")' class='fa fa-edit' ></i></td/>");*/
						str.append("<td><i onclick='deleteinvstdata("+i+")' style='cursor: pointer;color: #d9534f;' class='fa fa-trash-o' ></i></td/>");
						}
						str.append("</tr>");
						
						investigationtype=investigation.getInvsttype();
						i++;
					}
		}else {
			for(Investigation investigation  : list){
				str.append("<tr>");
				/*str.append("<td>"+priscription.getDate()+"</td/>");*/
				
				str.append("<td>"+investigation.getInvsttype()+"</td/>");
				/*str.append("<td>"+investigation.getInvstgroup()+"</td/>");*/
				str.append("<td>"+investigation.getInvstname()+"</td/>");
				//str.append("<td>"+investigation.getSpecimen()+"</td/>");
				//str.append("<td>"+investigation.getInvstreporttype()+"</td> <td>"+investigation.getInvstUnit()+"</td/>");
				//str.append("<td>"+investigation.getInvstUnit()+"</td/>");
				if(DateTimeUtils.numberCheck(isFromCF).equals("1")){
					str.append("<td><input type='text' class='form-control' value='"+DateTimeUtils.isNull(investigation.getValue_cf())+"' onchange='changeInvCFVal("+i+",this.value)'></td>");
				}
				/*str.append("<td><i onclick='showedit("+i+")' class='fa fa-edit' ></i></td/>");*/
				str.append("<td><i onclick='deleteinvstdata("+i+")' style='cursor: pointer;color: #d9534f;' class='fa fa-trash-o' ></i></td/>");
				str.append("</tr>");
				
				i++;
			}
	     }
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+""); 
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public String tempsave(){
		
		String prepay = request.getParameter("prepay");
		String postpay = request.getParameter("postpay");
		String otherpay = request.getParameter("otherpay");
		String invstautoid = request.getParameter("invstautoid");
		
		String advoice = request.getParameter("advoice");
		String english = request.getParameter("english");
		String regional  = request.getParameter("regional");
		String hindi = request.getParameter("hindi");
		String invstreporttype = request.getParameter("invstreporttype");
		String jobtitle = request.getParameter("jobtitle");
		
		String clientId = request.getParameter("clientId");
		String prectionerid = request.getParameter("prectionerid");
		String conditionid = request.getParameter("conditionid");
		
		Investigation investigation = new Investigation();
		
		
		investigation.setPrepay(prepay);
		investigation.setPostpay(postpay);
		investigation.setOtherpay(otherpay);
		
		investigation.setEnglish(english);
		investigation.setRegional(regional);
		investigation.setHindi(hindi);
		investigation.setAdvoice(advoice);
		investigation.setInvstreporttype(invstreporttype);
		investigation.setJobtitle(jobtitle);
		
		
		Connection connection = null;
		try{
			
			
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			
			String oldpractid = prectionerid;
			//check if doctor placed with machine
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(oldpractid));
			
			if(userProfile.getJobgroup()!=null){
				if(userProfile.getJobgroup().equals("4")){
				prectionerid = userProfile.getDoctor();
				}
			}
		
			investigation.setClientId(clientId);
			investigation.setPrectionerid(prectionerid);
			investigation.setConditionid(conditionid);
			
			String sessionadmissionid = (String)session.getAttribute("sessionadmissionid");
			ArrayList<Investigation>invstypelist = (ArrayList<Investigation>) session.getAttribute("invstypelist");
			ArrayList<Investigation>invstList = (ArrayList<Investigation>)session.getAttribute("invstList");
			
			String templatetext = request.getParameter("templatetext");
			int tmplateid = investigationDAO.saveInvTemplateName(templatetext,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
			
			for(Investigation invs : invstypelist){
		
				int saveparent =  investigationDAO.saveTemplateParentInvestigation(investigation,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),sessionadmissionid,invs.getInvsttypeid(),tmplateid);
				session.setAttribute("invstsaveparent", saveparent);
				
				for(Investigation investigation2 : invstList){
					if(invs.getInvsttype().equals(investigation2.getInvsttype())){
						int update = investigationDAO.updateReportType(saveparent,investigation2.getInvstreporttype());
						int result  = investigationDAO.saveTemplateInvestigation(investigation2,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),saveparent,clientId,prectionerid,conditionid);
					}
					
					
				}
			}
			
			
			StringBuffer str = new StringBuffer();
			
			ArrayList<Investigation>investigationList = (ArrayList<Investigation>)session.getAttribute("invstList");
			
			int i = 0;
			for(Investigation invs  : investigationList){
				str.append("<tr>");
				/*str.append("<td>"+priscription.getDate()+"</td/>");*/
				
				str.append("<td>"+invs.getInvsttype()+"</td/>");
				/*str.append("<td>"+invs.getInvstgroup()+"</td/>");*/
				str.append("<td>"+invs.getInvstname()+"</td/>");
				str.append("<td>"+invs.getSpecimen()+"</td/>");
				str.append("<td>"+invs.getInvstreporttype()+"</td/>");
				str.append("<td>"+invs.getInvstUnit()+"</td/>");
				//str.append("<td></td>");
				
				
				i++;
			}
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+""); 
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return null;
		
		
	}
	
	
	public String insert(){
		String action =request.getParameter("action");
		String prepay = request.getParameter("prepay");
		String postpay = request.getParameter("postpay");
		String otherpay = request.getParameter("otherpay");
		String invstautoid = request.getParameter("invstautoid");
		
		String advoice = request.getParameter("advoice");
		String english = request.getParameter("english");
		String regional  = request.getParameter("regional");
		String hindi = request.getParameter("hindi");
		String invstreporttype = request.getParameter("invstreporttype");
		String jobtitle = request.getParameter("jobtitle");
		String saveemr=request.getParameter("saveemr");
		String clientId = request.getParameter("clientId");
		String prectionerid = request.getParameter("prectionerid");
		String conditionid = request.getParameter("conditionid");
		String invpkg = request.getParameter("invpkg");
		String isFromCF = request.getParameter("isFromCF");
		String invstbackdate = request.getParameter("invstbackdate");
		session.setAttribute("actprn", action);
		Investigation investigation = new Investigation();
		
		investigation.setInvpkg(invpkg);
		investigation.setPrepay(prepay);
		investigation.setPostpay(postpay);
		investigation.setOtherpay(otherpay);
		
		investigation.setEnglish(english);
		investigation.setRegional(regional);
		investigation.setHindi(hindi);
		investigation.setAdvoice(advoice);
		investigation.setInvstreporttype(invstreporttype);
		investigation.setJobtitle(jobtitle);
		investigation.setUserid(Integer.toString(loginInfo.getId()));
		investigation.setUseridnew(loginInfo.getUserId());
		
		String lastmodidate="";
		String time=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
		
		//for bams backdate
		if(loginInfo.isBams1()) {
			if(invstbackdate==null) {
				lastmodidate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());

			}else {
				
			lastmodidate=DateTimeUtils.getCommencingDate1(invstbackdate);
			lastmodidate=lastmodidate+" "+time;
			}
		}else {
			lastmodidate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		}
		Connection connection = null;
		int invrequest = 0;
		try{
			
			
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			IpdDAO ipdDAO= new JDBCIpdDAO(connection);
			if(prectionerid.equals("0")){
				     //get from ipd id not discharge or get from opd 
					String str[]=  ipdDAO.getFromIpdOrOpd(clientId); 
				    prectionerid=str[0];
				    conditionid= str[1];
			}
			
			String oldpractid = prectionerid;
			//check if doctor placed with machine
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(oldpractid));
			
			if(userProfile.getJobgroup()!=null){
				if(userProfile.getJobgroup().equals("4")){
				prectionerid = userProfile.getDoctor();
				}
			}
		
			investigation.setClientId(clientId);
			investigation.setPrectionerid(prectionerid);
			investigation.setConditionid(conditionid);
			
			String sessionadmissionid = (String)session.getAttribute("sessionadmissionid");
			ArrayList<Investigation>invstypelist = (ArrayList<Investigation>) session.getAttribute("invstypelist");
			ArrayList<Investigation>invstList = (ArrayList<Investigation>)session.getAttribute("invstList");
			
			
			String commencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			 invrequest = investigationDAO.saveInvRequest(commencing);
			 String saveid ="0";
			 //lokesh
			 if(invstList.size()==0){
				 return execute();
			 }
			for(Investigation invs : invstypelist){
				jobtitle = investigationDAO.getInvsJobtitle(invs.getInvsttypeid());
				
				investigation.setJobtitle(jobtitle);
				invs.setPrectionerid(prectionerid);
				investigation.setUpdatedby(investigation.getUserid());
				int saveparent =  investigationDAO.saveParentInvestigation(investigation,lastmodidate,sessionadmissionid,invs.getInvsttypeid(),invrequest,loginInfo);
				session.setAttribute("invstsaveparent", saveparent);
				if(action==null){
					action="0";
				}
				if(action.equals("1")){
					Investigation investigation2=new Investigation();
					investigation2=investigationDAO.getparentdata(saveparent);
					session.setAttribute("invwriteup", investigation2.getReporttype());
					session.setAttribute("invsectionid", investigation2.getSectionid());
					session.setAttribute("invlastmodi", investigation2.getDate());
					session.setAttribute("invprintm", "r");
					session.setAttribute("invrequ", investigation2.getInvreq());
				}
				saveid = saveid +","+saveparent;
				for(Investigation investigation2 : invstList){
					
					investigation2.setPrectionerid(prectionerid);
					if(invs.getInvsttype().equals(investigation2.getInvsttype())){
					/*	int update = investigationDAO.updateReportType(saveparent,investigation2.getInvstreporttype());*/
						int result  = investigationDAO.saveInvestigation(investigation2,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),saveparent,clientId,prectionerid,conditionid);
					}
					
					
				}
			}
			
			
			StringBuffer str = new StringBuffer();
			
			ArrayList<Investigation>investigationList = (ArrayList<Investigation>)session.getAttribute("invstList");
			
			//  11 April hide previous code that is in else part and added if part code 
			int i = 0;
			if(!saveid.equals("0")){
				ArrayList<Investigation> compressedinvestigation = investigationDAO.getSavedInvestigationList(saveid);
				for(Investigation invs  : compressedinvestigation){
					str.append("<tr>");
					/*str.append("<td style='text-align:left !important;'>"+invs.getInvsttype()+"<b>["+DateTimeUtils.getCommencingDate1(commencing.split(" ")[0])+" "+commencing.split(" ")[1]+"]</b></td/>");*/
					str.append("<td style='text-align:left !important;'>"+invs.getInvsttype()+"</td/>");
					//str.append("<td class='hidden'>"+invs.getInvstname()+" </td/>");
					str.append("</tr>");
					i++;
				}
			}else{
				for(Investigation invs  : investigationList){
					str.append("<tr>");
					/*str.append("<td>"+priscription.getDate()+"</td/>");*/
					 str.append("<td>"+invs.getInvsttype()+"</td/>");
					/*str.append("<td>"+invs.getInvstgroup()+"</td/>");*/
					//str.append("<td class='displaynonesrno' align='left'>"+invs.getInvstname()+"</td/>");
					/*str.append("<td>"+invs.getSpecimen()+"</td/>");
					str.append("<td>"+invs.getInvstreporttype()+"</td/>");
					str.append("<td>"+invs.getInvstUnit()+"</td/>");*/
					//str.append("<td></td>");
					i++;
				}
			}

			
			//truncate temp charges
			
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			int result = completeAptmDAO.deleteComplteApmt(loginInfo.getId());
			
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			String doctorname = "";
			
			
			Investigation invchargeinfo = investigationDAO.getInvChargeInfo(invrequest);
			String reqdate = investigationDAO.getInvereqDate(invrequest);
			if(invchargeinfo.getPrectionerid()!=null){
				UserProfile uprofile = userProfileDAO.getUserprofileDetails(Integer.parseInt(invchargeinfo.getPrectionerid()));
				doctorname = uprofile.getInitial() + " " + uprofile.getFirstname() + " " + uprofile.getLastname();
			}
			
			
			
			
			if(DateTimeUtils.isNull(saveemr).equals("1")){
				
				StringBuffer hist=new StringBuffer();
					hist.append("<br><table  cellspacing='0' width='100%'>");
					hist.append("<thead>");
					hist.append("<tr class='tableback'>");
					hist.append("<th>Investigation</th>");
					hist.append("</tr>");
					hist.append(" </thead>");
					hist.append("<tbody >");
					hist.append(str);
					hist.append("</tbody>");
					hist.append("</table><br>");
				EmrDAO emrDAO=new JDBCEmrDAO(connection);
				Emr emr=new Emr();
				emr.setPatientId(DateTimeUtils.convertToInteger(clientId));
				String appid=request.getParameter("appid");
				emr.setAppointmentid(DateTimeUtils.convertToInteger(appid));
				emr.setPractitionerId(DateTimeUtils.convertToInteger(invchargeinfo.getPrectionerid()));
				emr.setCondition_id(conditionid);
				emr.setFinaldiagnosis("");
				emr.setConsNote(hist.toString());
				emr.setEmripdid("0");
//				int res=emrDAO.savePatientConsultationNote(emr, DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
				int res1=emrDAO.saveEntryToEMR(emr, DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),"emr_investigation");
			}else if(DateTimeUtils.isNull(saveemr).equals("2")){
				
				StringBuffer hist=new StringBuffer();
				hist.append("<br><table  cellspacing='0' width='100%'>");
				hist.append("<thead>");
				hist.append("<tr class='tableback'>");
				hist.append("<th>Investigation</th>");
				hist.append("</tr>");
				hist.append(" </thead>");
				hist.append("<tbody >");
				hist.append(str);
				hist.append("</tbody>");
				hist.append("</table><br>");
			EmrDAO emrDAO=new JDBCEmrDAO(connection);
			Emr emr=new Emr();
			emr.setPatientId(DateTimeUtils.convertToInteger(clientId));
			String appid=request.getParameter("appid");
			emr.setAppointmentid(0);
			emr.setEmripdid(appid);
			emr.setPractitionerId(DateTimeUtils.convertToInteger(invchargeinfo.getPrectionerid()));
			emr.setCondition_id(conditionid);
			emr.setFinaldiagnosis("");
			emr.setConsNote(hist.toString());
			
//			int res=emrDAO.savePatientConsultationNote(emr, DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
			int res1=emrDAO.saveEntryToEMR(emr, DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),"emr_investigation");
			}
			
			
			
			
			Client client = clientDAO.getClientDetails(invchargeinfo.getClientId());
			String cname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
			
			 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		      Date birthDate = sdf.parse(client.getDob()); //Yeh !! It's my date of birth :-)
		      
		      Age age = AgeCalculator.calculateAge(birthDate);
		      //My age is
//		      System.out.println("####"+age);
		      
		      //get agetype
		      String strr= age.toString();
				
				String temp[] = strr.split(",");
//				System.out.println(temp);
				int year = Integer.parseInt(temp[0].split(" ")[0]);
				String m = temp[1].split(" ")[1];
				int month = Integer.parseInt(temp[1].split(" ")[1]);
				int days = Integer.parseInt(temp[2].split(" ")[1]);
				
				String aage = "";
				if(year==0 && month==0){
					aage = days + " D";
				}
				else if(year==0){
					aage = month + days +" M";
				}
				else{
					aage = year + " Y";
				}
			
			
		      String age1 = aage.split(" ")[0];
		      String agetype = aage.split(" ")[1];
		      
		      String wid = "0";
		      String wardname = "OPD";
			  AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
				int bedid = assessmentFormDAO.getIpdBedno(clientId);
				
				if(bedid!=0){
					BedDao bedDao=new JDBCBedDao(connection);
					
					 String ipdid = assessmentFormDAO.getAdmissionid(clientId);
					 Bed bed = bedDao.getEditIpdData(ipdid);
					 wid = bed.getWardid();
					 wardname=ipdDAO.getIpdWardName(bed.getWardid());
					 
					 String bedname=ipdDAO.getIpdBedName(bed.getBedid());
					 
					 wardname = "Ward: " + wardname+ " / " + bedname;
				}
		      
				String whopay = client.getWhopay();
				
				if(whopay.equals(Constants.PAY_BY_THIRD_PARTY)){
					String tpid = client.getTypeName();
					
					
					if(!ipdDAO.checkifMainTp(tpid)){
						 
						String temptpid= ipdDAO.getFollowerTp(tpid); 
						if(temptpid!=null){
							
							if(!temptpid.equals("0")){
								 tpid=temptpid;  
							}
							
							ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
							ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(tpid);
							
							whopay = whopay + " {"+thirdParty.getCompanyName()+"} ";
							
						}
						
					}	
				}
		     
				
				
			String data = invchargeinfo.getId() + "~" + invchargeinfo.getClientId() + "~" +
			invchargeinfo.getPrectionerid() + "~"  + invrequest + "~" + cname + "~" + doctorname + "~" + client.getWhopay() + 
			"~" + invchargeinfo.getInvsttypeid() + "~" + invchargeinfo.getIpdid() + 
			"~" + client.getAbrivationid()  + "~"  + loginInfo.getClinicUserid() + "~"  + age1 + "~" + 
			client.getGender() + "~" + reqdate + "~" + agetype + "~" + whopay + "~" + wardname
			+ "~" + invchargeinfo.getDepartment(); 
			
			
			
			//get investigation charges
			String tresult = "";
			String ttt[] = invchargeinfo.getMachineInvList().split(",");
			for(int t=0;t<ttt.length;t++){
				String chargename = investigationDAO.getMasterChargeName(ttt[t]); 
				AppointmentType appointmentType = investigationDAO.getInvestigationPaybyCharge(whopay,chargename,client.getTypeName(),client.getId(),loginInfo);
				if(appointmentType.getCharges()==null){
					appointmentType.setCharges("0");
				}
				tresult = tresult + appointmentType.getCharges() + ",";
			}
			
			
			
			str.append("<input type='hidden' name='invchargeinfo' id='invchargeinfo' value='"+data+"'>");
			str.append("<input type='hidden' name='machineinvlist' id='machineinvlist' value='"+invchargeinfo.getMachineInvList()+"'>");
			str.append("<input type='hidden' name='mchinereqidlist' id='mchinereqidlist' value='"+invchargeinfo.getMchinereqidlist()+"'>");
			
			str.append("<input type='hidden' name='mchininvcharges' id='mchininvcharges' value='"+tresult+"'>");
			
			if(DateTimeUtils.numberCheck(isFromCF).equals("1")){
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+saveid+""); 
			}else{
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+str.toString()+""); 
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
	public String elabresend(){
		
		Connection connection = null;
		try{
			
			int invrequest = Integer.parseInt(request.getParameter("invrequest"));
			String clientId = request.getParameter("clientId");
			String doctorname = "";
			
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			
			StringBuffer str = new StringBuffer();
			
			Investigation invchargeinfo = investigationDAO.getInvChargeInfo(invrequest);
			String reqdate = investigationDAO.getInvereqDate(invrequest);
			if(invchargeinfo.getPrectionerid()!=null){
				UserProfile uprofile = userProfileDAO.getUserprofileDetails(Integer.parseInt(invchargeinfo.getPrectionerid()));
				doctorname = uprofile.getInitial() + " " + uprofile.getFirstname() + " " + uprofile.getLastname();
			}
			
			Client client = clientDAO.getClientDetails(invchargeinfo.getClientId());
			String cname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
			
			 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		      Date birthDate = sdf.parse(client.getDob()); //Yeh !! It's my date of birth :-)
		      
		      Age age = AgeCalculator.calculateAge(birthDate);
		      //My age is
//		      System.out.println("####"+age);
		      
		      //get agetype
		      String strr= age.toString();
				
				String temp[] = strr.split(",");
//				System.out.println(temp);
				int year = Integer.parseInt(temp[0].split(" ")[0]);
				String m = temp[1].split(" ")[1];
				int month = Integer.parseInt(temp[1].split(" ")[1]);
				int days = Integer.parseInt(temp[2].split(" ")[1]);
				
				String aage = "";
				if(year==0 && month==0){
					aage = days + " D";
				}
				else if(year==0){
					aage = month + days +" M";
				}
				else{
					aage = year + " Y";
				}
			
			
		      String age1 = aage.split(" ")[0];
		      String agetype = aage.split(" ")[1];
		      
		      String wid = "0";
		      String wardname = "OPD";
			  AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
				int bedid = assessmentFormDAO.getIpdBedno(clientId);
				
				if(bedid!=0){
					BedDao bedDao=new JDBCBedDao(connection);
					
					 String ipdid = assessmentFormDAO.getAdmissionid(clientId);
					 Bed bed = bedDao.getEditIpdData(ipdid);
					 wid = bed.getWardid();
					 wardname=ipdDAO.getIpdWardName(bed.getWardid());
					 
					 String bedname=ipdDAO.getIpdBedName(bed.getBedid());
					 
					 wardname = "Ward: " + wardname+ " / " + bedname;
				}
		      
				String whopay = client.getWhopay();
				
				if(whopay.equals(Constants.PAY_BY_THIRD_PARTY)){
					String tpid = client.getTypeName();
					
					
					if(!ipdDAO.checkifMainTp(tpid)){
						 
						String temptpid= ipdDAO.getFollowerTp(tpid); 
						if(temptpid!=null){
							
							if(!temptpid.equals("0")){
								 tpid=temptpid;  
							}
							
							ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
							ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(tpid);
							
							whopay = whopay + " {"+thirdParty.getCompanyName()+"} ";
							
						}
						
					}	
				}
		     
				
				
			String data = invchargeinfo.getId() + "~" + invchargeinfo.getClientId() + "~" +
			invchargeinfo.getPrectionerid() + "~"  + invrequest + "~" + cname + "~" + doctorname + "~" + client.getWhopay() + 
			"~" + invchargeinfo.getInvsttypeid() + "~" + invchargeinfo.getIpdid() + 
			"~" + client.getAbrivationid()  + "~"  + loginInfo.getClinicUserid() + "~"  + age1 + "~" + 
			client.getGender() + "~" + reqdate + "~" + agetype + "~" + whopay + "~" + wardname
			+ "~" + invchargeinfo.getDepartment(); 
			
			
			
			//get investigation charges
			String tresult = "";
			String ttt[] = invchargeinfo.getMachineInvList().split(",");
			for(int t=0;t<ttt.length;t++){
				String chargename = investigationDAO.getMasterChargeName(ttt[t]); 
				AppointmentType appointmentType = investigationDAO.getInvestigationPaybyCharge(whopay,chargename,client.getTypeName(),client.getId(),loginInfo);
				if(appointmentType.getCharges()==null){
					appointmentType.setCharges("0");
				}
				tresult = tresult + appointmentType.getCharges() + ",";
			}
			
			
			
			str.append("<input type='hidden' name='invchargeinfo' id='invchargeinfo' value='"+data+"'>");
			str.append("<input type='hidden' name='machineinvlist' id='machineinvlist' value='"+invchargeinfo.getMachineInvList()+"'>");
			str.append("<input type='hidden' name='mchinereqidlist' id='mchinereqidlist' value='"+invchargeinfo.getMchinereqidlist()+"'>");
			
			str.append("<input type='hidden' name='mchininvcharges' id='mchininvcharges' value='"+tresult+"'>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+""); 
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public String view(){
		
			String clientId = request.getParameter("clientId");
			String prectionerid = request.getParameter("prectionerid");
			String conditionid = request.getParameter("conditionid");
			
			Connection connection = null;
			try{
				
				
				connection = Connection_provider.getconnection();
				InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
				UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
				if(prectionerid!=null){
					if(!prectionerid.equals("")){
						 
						UserProfile userProfile=userProfileDAO.getUserprofileDetails(Integer.parseInt(prectionerid));
					}
					
				}
				
				
				ArrayList<Investigation>invstigationList = investigationDAO.getParentPriscList(clientId,prectionerid,conditionid);
				
				StringBuffer str = new StringBuffer();
				
				for(Investigation investigation : invstigationList){
					str.append("<div class='row'>");
					//<a href='#' onclick='editparentinvstc("+investigation.getId()+")' title='Edit Prescription' class='editpricon'><i class='fa fa-edit'></i></a>
					str.append("<span><p class='pdatpree'>"+investigation.getDate()+"  ");
					str.append("<a href='#' onclick='delparentinvst("+investigation.getId()+")' title='Delete Investigation' class='editpricon'><i class='fa fa-trash-o'></i></a>");
					
					if(investigation.getInvstreporttype().equals("Numerical")){
						str.append("<a href='#' onclick='printinvstigationrecord("+investigation.getId()+",0)' title='Print Investigation' class='editpricon'><i class='fa fa-print'></i></a></p> </span>");
						
					}else{
						str.append("<a href='#' onclick='printinvstigationrecord("+investigation.getId()+",1)' title='Print Investigation' class='editpricon'><i class='fa fa-print'></i></a></p> </span>");
					}
					str.append("</div>");
					
				}
				
			
				
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
	
	


	public String edit(){
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			String selectedid = request.getParameter("selectedid");
			
			Investigation investigation = investigationDAO.getEditInvestigation(selectedid);
			
			
			
			String parentdata = investigation.getPrepay() + "~" + investigation.getPostpay() + "~" + investigation.getOtherpay()  + "~"
					+  investigation.getEnglish() + "~" + investigation.getRegional() + "~" 
					+ investigation.getHindi() + "~" + investigation.getAdvoice();
			
			
			ArrayList<Investigation>selectedInvstList = investigationDAO.getSelectedInvestigationData(selectedid);
			session.setAttribute("invstList", selectedInvstList);
			ArrayList<String>invstypelist = new ArrayList<String>();
			
			StringBuffer str = new StringBuffer();
			
			int i = 0;
			str.append("<input type='hidden' name='parentinvsteditdataid' id='parentinvsteditdataid' value='"+parentdata+"'>");
			
			for(Investigation investigation2 : selectedInvstList){
				if(i==0){
					String editinvstype = investigation2.getInvsttype();
					invstypelist.add(editinvstype);
					session.setAttribute("invstypelist", invstypelist);
				}
				str.append("<tr>");
				/*str.append("<td>"+priscription.getDate()+"</td/>");*/
				
				str.append("<td>"+investigation2.getInvsttype()+"</td/>");
				/*str.append("<td>"+investigation2.getInvstgroup()+"</td/>");*/
				str.append("<td>"+investigation2.getInvstname()+"</td/>");
				str.append("<td>"+investigation2.getSpecimen()+"</td/>");
				str.append("<td>"+investigation2.getInvstreporttype()+" "+investigation2.getInvstUnit()+"</td/>");
			//	str.append("<td>"+investigation2.getInvstUnit()+"</td/>");
								
				str.append("<td><i onclick='delparentinvst("+i+")' class='fa fa-trash-o' ></i></td/>");
				str.append("</tr>");
				
				i++;
			}
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+"");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;

		
		
	}
	
	public String deleteparent(){
		
		String selectedid = request.getParameter("selectedid");
		Connection connection = null;
		try{
			
			
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			
			int del = investigationDAO.deleteInvestigationData(selectedid);
			int de = investigationDAO.deleteParentData(selectedid);
			
		/*	EmrDAO emrDAO = new JDBCEmrDAO(connection);
			
			int del = emrDAO.deleteParentPrisc(selectedid);
			int dl = emrDAO.deleteParet(selectedid);
					
					viewallclientprisc();*/
			
			//view();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
    public String delete() throws Exception{
		
		String selectedid = request.getParameter("selectedid");
		String delete_reason = request.getParameter("delete_reason");
		Connection connection = null;
		try{
			
			emrForm.setSearchText(emrForm.getSearchText());
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			//int del = investigationDAO.deleteInvestigationData(selectedid);
			//int de = investigationDAO.deleteParentData(selectedid);
			String userid = loginInfo.getUserId();
			int res = investigationDAO.cancelInvestigation(selectedid,userid);
			
			if(res>0){
				if(loginInfo.isDeleted_invst_charge()){
					ipdDAO.startInvestigationChargeCancelProcess(null, selectedid,loginInfo);
				}
				
			}
			String loc= (String) session.getAttribute("location");
				if(loc==null){
					loc="0";
				}
			res = investigationDAO.saveUpDeleteInvestigation(selectedid,delete_reason,userid,loc);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return execute();
	}
	
	
	public String delinvst(){
		Connection connection = null;
		try{
			
			String clientId = request.getParameter("clientId");
			String prectionerid = request.getParameter("prectionerid");
			String conditionid = request.getParameter("conditionid");
			
			String selectedid = request.getParameter("selectedid");
			String isFromCF= request.getParameter("isFromCF");
			
			ArrayList<Investigation>invstList = (ArrayList<Investigation>)session.getAttribute("invstList");
			if(invstList.size()>0){
				selectedid=String.valueOf(Math.abs(invstList.size()-(Integer.parseInt(selectedid)+1)));
			}
			invstList.remove(Integer.parseInt(selectedid));
			
			getInvestigationData(DateTimeUtils.numberCheck(isFromCF));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String rmove(){
		
		session.removeAttribute("priscList");
		session.removeAttribute("invstList");
		session.removeAttribute("invstypelist");
		session.removeAttribute("oteqlist");
		return null;
	}
	
	
	public String results(){
		
		
		
		/*String cbcarray = request.getParameter("cbcarray");
		System.out.println(cbcarray);
		String rc = cbcarray.replace("{", "");
		rc = rc.replace("}", "");
		
		String cbctemp[] = rc.split(",");*/
		
		Connection connection = null;
		String action = request.getParameter("action");
		try{
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	


	public String rportedit(){
		Connection connection = null;
		String action = request.getParameter("action");
		try{
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			String selectedid = request.getParameter("selectedid");
			String cbcid = request.getParameter("cbcid");
			
			Investigation investigation = investigationDAO.getEditInvestigation(selectedid);
			String invstype="";
			
			
			
			String parentdata = investigation.getPrepay() + "~" + investigation.getPostpay() + "~" + investigation.getOtherpay()  + "~"
					+  investigation.getEnglish() + "~" + investigation.getRegional() + "~" 
					+ investigation.getHindi() + "~" + investigation.getAdvoice()+"~"+investigation.getRemark();
			
				
			ArrayList<Investigation>selectedInvstList = investigationDAO.getSelectedInvestigationData(selectedid);
			session.setAttribute("editinvstList", selectedInvstList);
			session.setAttribute("editinvstparentid", selectedid);
			
			StringBuffer str = new StringBuffer();
			String invstigationtype1="";
			int i = 0;
			int r = 1;
			int upratioid = 0;
			Cbc cbc = new Cbc();
			if(!cbcid.equals("0")){
				 cbc =  investigationDAO.getCbcReportData(cbcid);
			}
			
			str.append("<input type='hidden' name='parentinvsteditdataid' id='parentinvsteditdataid' value='"+parentdata+"'>");
			
			for(Investigation investigation2 : selectedInvstList){
				str.append("<tr>");
				/*str.append("<td>"+priscription.getDate()+"</td/>");*/
				
				if(investigation2.getObsvalue().equals("0")){
					investigation2.setObsvalue("");
				}
				
				if(!cbcid.equals("0")){
					if(investigation2.getInvstname().equals(Constants.TOTAL_LEUCOCYTE_COUNT)){
						double tlc = Double.parseDouble(cbc.getWbc()) * 1000;
						int x = (int)tlc;
						investigation2.setObsvalue(Integer.toString(x));
					}
					else if(investigation2.getInvstname().equals(Constants.LYMPHOCYTES)){
						investigation2.setObsvalue(cbc.getPerlym());
					}
					
					else if(investigation2.getInvstname().equals(Constants.MONOCYTES)){
						investigation2.setObsvalue(cbc.getPermon());
					}
					else if(investigation2.getInvstname().equals(Constants.NEUTROPHILS)){
						investigation2.setObsvalue(cbc.getPergra());
					}
					else if(investigation2.getInvstname().equals(Constants.RBC)){
						investigation2.setObsvalue(cbc.getRbc());
					}
					else if(investigation2.getInvstname().equals(Constants.HAEMOGLOBIN)){
						investigation2.setObsvalue(cbc.getHgb());
					}
					else if(investigation2.getInvstname().equals(Constants.HCT)){
						investigation2.setObsvalue(cbc.getHcp());
					}
					else if(investigation2.getInvstname().equals(Constants.MCV)){
						investigation2.setObsvalue(cbc.getMcv());
					}
					else if(investigation2.getInvstname().equals(Constants.MCH)){
						investigation2.setObsvalue(cbc.getMch());
					}
					else if(investigation2.getInvstname().equals(Constants.MCHC)){
						investigation2.setObsvalue(cbc.getMchc());
					}
					else if(investigation2.getInvstname().equals(Constants.RDW)){
						investigation2.setObsvalue(cbc.getRdw());
					}
					else if(investigation2.getInvstname().equals(Constants.PLATELET_COUNT)){
						String temp[] = cbc.getTlt().split(" ");
						cbc.setTlt(temp[0]);
						double pc = Double.parseDouble(cbc.getTlt()) / 100;
						if(temp.length>1){
							investigation2.setObsvalue(Double.toString(pc) + " " + temp[2]);
						}else{
							investigation2.setObsvalue(Double.toString(pc));
						}
						
					}
					else if(investigation2.getInvstname().equals(Constants.PCT)){
						//double pc = Double.parseDouble(cbc.getTlt()) / 100;
						investigation2.setObsvalue(cbc.getPct());
					}
					else if(investigation2.getInvstname().equals(Constants.MPV)){
						investigation2.setObsvalue(cbc.getMpv());
					}
					else if(investigation2.getInvstname().equals(Constants.PDW)){
						investigation2.setObsvalue(cbc.getPdw());
					}
				}
				String onchngefunction = "";
				
				if(investigation2.getInvsttype().equals("URINE PC  RATIO")){
					if(r==1){
						upratioid = investigation2.getId()+2;
					}
					onchngefunction = "showupr("+r+",this.id,this.value,"+upratioid+")";
					r++;
				}
				
				if(investigation2.getInvsttype().equals("Iron Studies")){
					if(r==1){
						upratioid = investigation2.getId();
					}
					onchngefunction = "showirondata("+r+",this.id,this.value,"+upratioid+")";
					r++;
				}
				
				if(investigation2.getInvsttype().equals("URINE PROTEIN CREATININE RATIO    ")){
					if(r==1){
						upratioid = investigation2.getId();
					}
					onchngefunction = "showupcrndata("+r+",this.id,this.value,"+upratioid+")";
					r++;
				}
				
				if(investigation2.getInvsttype().equals("Urine Protein Creatinine Ratio")){
					if(r==1){
						upratioid = investigation2.getId();
					}
					onchngefunction = "showupcrndata("+r+",this.id,this.value,"+upratioid+")";
					r++;
				}
				if(investigation2.getInvsttype().equals("CALCIUM:Creatinine Ratio")){
					if(r==1){
						upratioid = investigation2.getId();
					}
					onchngefunction = "showcalcration("+r+",this.id,this.value,"+upratioid+")";
					r++;
				}
				
			
				if(investigation2.getInvsttype().equals("LIPID  PROFILE")){
					if(r==1){
						upratioid = investigation2.getId();
					}
					onchngefunction = "showlipidprofile("+r+",this.id,this.value,"+upratioid+")";
					r++;
				}
				
				if(investigation2.getInvsttype().equals("LIPID PROFILE  ~ TOTAL CHOL, TRIGLY, HDL, LDL, VLDL")){
					if(r==1){
						upratioid = investigation2.getId();
					}
					onchngefunction = "showsnhlipidprofile("+r+",this.id,this.value,"+upratioid+")";
					r++;
				}
				
				if(investigation2.getInvsttype().equals("TOTAL PROTEIN  ~  TOT PROTEIN, ALBUMIN, GLOBULIN, A/G RATIO")){
					if(r==1){
						upratioid = investigation2.getId();
					}
					onchngefunction = "showtotalprotein("+r+",this.id,this.value,"+upratioid+")";
					r++;
				}
				
				if(investigation2.getInvsttype().equals("TOTAL PROTEIN")){
					if(r==1){
						upratioid = investigation2.getId();
					}
					onchngefunction = "showtotalprotein("+r+",this.id,this.value,"+upratioid+")";
					r++;
				}
				if(investigation2.getInvsttype().equals("LIVER FUNCTION TEST LFT ")){
					if(r==1){
						upratioid = investigation2.getId();
					}
					onchngefunction = "showliverfunctionlft("+r+",this.id,this.value,"+upratioid+")";
					r++;
				}
				
				
				if(investigation2.getInvsttype().equals("TOTAL BILIRUBIN ~ Total Bilirubin, Direct Bilirubin, Indirect Bilirubin ")){
					if(r==1){
						upratioid = investigation2.getId();
					}
					onchngefunction = "showsnhliverfunction("+r+",this.id,this.value,"+upratioid+")";
					r++;
				}
				
				if(investigation2.getInvsttype().equals("LIVER FUNCTION  TEST ")){
					if(r==1){
						upratioid = investigation2.getId();
					}
					onchngefunction = "showliverfunction("+r+",this.id,this.value,"+upratioid+")";
					r++;
				}
				
				if(investigation2.getInvsttype().equals("PROTHROMBIN TIME (PT/INR) ~ TEST, CONTROL, INR")){
					if(r==1){
						upratioid = investigation2.getId();
					}
					onchngefunction = "showsnhprothombin("+r+",this.id,this.value,"+upratioid+")";
					r++;
				}
				
				if(investigation2.getInvsttype().equals("PROTHROMBIN  TIME")){
					if(r==1){
						upratioid = investigation2.getId();
					}
					onchngefunction = "showprothombin("+r+",this.id,this.value,"+upratioid+")";
					r++;
				}
				
				if(investigation2.getInvsttype().equals("CALCIUM:CREATININE RATIO")){
					if(r==1){
						upratioid = investigation2.getId();
					}
					onchngefunction = "showcalciumcreatin("+r+",this.id,this.value,"+upratioid+")";
					r++;
				}
				
				//lokesh for kunal hspital
				if(investigation2.getInvsttype().equals("Bilirubin")){
					if(r==1){
						upratioid = investigation2.getId();
					}
					onchngefunction = "showkunalbilburin("+r+",this.id,this.value,"+upratioid+")";
					r++;
				}
				
				if(investigation2.getInvsttype().equals("Total Protein")){
					if(r==1){
						upratioid = investigation2.getId();
					}
					onchngefunction = "showkunaltotalprotien("+r+",this.id,this.value,"+upratioid+")";
					r++;
				}
				
				
				if(investigation2.getInvsttype().equals("Liver Function Test ( LFT )")){
					if(r==1){
						upratioid = investigation2.getId();
					}
					onchngefunction = "showkunallft("+r+",this.id,this.value,"+upratioid+")";
					r++;
				}
				
				if(investigation2.getInvsttype().equals("Extended Lipid Profile")){
					if(r==1){
						upratioid = investigation2.getId();
					}
					onchngefunction = "showsnhlipidprofile("+r+",this.id,this.value,"+upratioid+")";
					r++;
				}
				
				if(investigation2.getInvsttype().equals("Prothrombin Time (PT)")){
					if(r==1){
						upratioid = investigation2.getId();
					}
					onchngefunction = "calprothombinpt("+r+",this.id,this.value,"+upratioid+")";
					r++;
				}
				
				
				String value="";
				str.append("<td>"+investigation2.getInvsttype()+"</td>");
				str.append("<td>"+investigation2.getInvstname()+"</td>");
				if(action.equals("1")){
					
					if (investigation2.getInvsttype().equals("Urine Examination")) {
						
						   if(investigation2.getInvstname().equals("Nature")){
							   
							    if(investigation2.getObsvalue()!=null){
							    	
							    	 if(!investigation2.getObsvalue().equals("")){
							    		 
							    		 value= investigation2.getObsvalue();
							    	 } else {
							    		 value="random";
							    	 }
							    } else {
							    	value="random";
							    }
							   
							    
						   }
						   else if(investigation2.getInvstname().equals("Appearance")){
							    
							   if(investigation2.getObsvalue()!=null){
							    	
							    	 if(!investigation2.getObsvalue().equals("")){
							    		 
							    		 value= investigation2.getObsvalue();
							    	 } else {
							    		 value="clear";
							    	 }
							    } else {
							    	 value="clear";
							    }
							   
							   
						   }
						   else if(investigation2.getInvstname().equals("Quantity")){
							   
							   if(investigation2.getObsvalue()!=null){
							    	
							    	 if(!investigation2.getObsvalue().equals("")){
							    		 
							    		 value= investigation2.getObsvalue();
							    	 } else {
							    		 value="20 ML";
							    	 }
							    } else {
							    	value="20 ML";
							    }
							    
						   }
						   else if(investigation2.getInvstname().equals("Colour")){
							     
							   if(investigation2.getObsvalue()!=null){
							    	
							    	 if(!investigation2.getObsvalue().equals("")){
							    		 
							    		 value= investigation2.getObsvalue();
							    	 } else {
							    		 value="p.yellow";
							    	 }
							    } else {
							    	value="p.yellow";
							    }
							    
						   }
						   else if(investigation2.getInvstname().equals("RBC")){
							     
							   if(investigation2.getObsvalue()!=null){
							    	
							    	 if(!investigation2.getObsvalue().equals("")){
							    		 
							    		 value= investigation2.getObsvalue();
							    	 } else {
							    		 value="0-1/hpf";
							    	 }
							    } else {
							    	value="0-1/hpf";
							    }
							   
							    
						   }
						   else if(investigation2.getInvstname().equals("Pus Cells")){
							   
							   if(investigation2.getObsvalue()!=null){
							    	
							    	 if(!investigation2.getObsvalue().equals("")){
							    		 
							    		 value= investigation2.getObsvalue();
							    	 } else {
							    		 value="0-1/hpf";
							    	 }
							    } else {
							    	value="0-1/hpf";
							    }
							     
							    
						   }
						   else if(investigation2.getInvstname().equals("Epithelial Cells")){
							     
							   if(investigation2.getObsvalue()!=null){
							    	
							    	 if(!investigation2.getObsvalue().equals("")){
							    		 
							    		 value= investigation2.getObsvalue();
							    	 } else {
							    		 value="0-1/hpf";
							    	 }
							    } else {
							    	value="0-1/hpf";
							    }
							   
						   }
						   else if(investigation2.getInvstname().equals("Sugar")){
							     
							   if(investigation2.getObsvalue()!=null){
							    	
							    	 if(!investigation2.getObsvalue().equals("")){
							    		 
							    		 value= investigation2.getObsvalue();
							    	 } else {
							    		 value="nil";
							    	 }
							    } else {
							    	value="nil";
							    }
							    
						   }
						   else if(investigation2.getInvstname().equals("Casts")){
							     
							   if(investigation2.getObsvalue()!=null){
							    	
							    	 if(!investigation2.getObsvalue().equals("")){
							    		 
							    		 value= investigation2.getObsvalue();
							    	 } else {
							    		 value="nil";
							    	 }
							    } else {
							    	value="nil";
							    }
						   }
						   else if(investigation2.getInvstname().equals("Albumin")){
							      
							   if(investigation2.getObsvalue()!=null){
							    	
							    	 if(!investigation2.getObsvalue().equals("")){
							    		 
							    		 value= investigation2.getObsvalue();
							    	 } else {
							    		 value="nil";
							    	 }
							    } else {
							    	value="nil";
							    } 
							   
						   }
						   else if(investigation2.getInvstname().equals("Crystals")){
							     
							   if(investigation2.getObsvalue()!=null){
							    	
							    	 if(!investigation2.getObsvalue().equals("")){
							    		 
							    		 value= investigation2.getObsvalue();
							    	 } else {
							    		 value="nil";
							    	 }
							    } else {
							    	value="nil";
							    } 
						   }
						   else if(investigation2.getInvstname().equals("Amorphous Material")){
							     
							   if(investigation2.getObsvalue()!=null){
							    	
							    	 if(!investigation2.getObsvalue().equals("")){
							    		 
							    		 value= investigation2.getObsvalue();
							    	 } else {
							    		 value="nil";
							    	 }
							    } else {
							    	value="nil";
							    } 
						   }
						   else {
							   value=investigation2.getObsvalue();
						   }
						   
					}
					else if(investigation2.getInvsttype().startsWith("Viral Markers")){
						
						if(investigation2.getInvstname().equals("HIV")){
						     
							  if(investigation2.getObsvalue()!=null){
							    	
							    	 if(!investigation2.getObsvalue().equals("")){
							    		 
							    		 value= investigation2.getObsvalue();
							    	 } else {
							    		 value="NON-REACTIVE";
							    	 }
							    } else {
							    	value="NON-REACTIVE";
							    } 
							
						    
						} else if(investigation2.getInvstname().equals("HBSAG")){
							
							if(investigation2.getObsvalue()!=null){
						    	
						    	 if(!investigation2.getObsvalue().equals("")){
						    		 
						    		 value= investigation2.getObsvalue();
						    	 } else {
						    		 value="NEGATIVE";
						    	 }
						    } else {
						    	value="NEGATIVE";
						    } 
							
							
						}
						else if(investigation2.getInvstname().equals("HCV")){
							
							if(investigation2.getObsvalue()!=null){
						    	
						    	 if(!investigation2.getObsvalue().equals("")){
						    		 
						    		 value= investigation2.getObsvalue();
						    	 } else {
						    		 value="NEGATIVE";
						    	 }
						    } else {
						    	value="NEGATIVE";
						    } 
						} else {
							value=investigation2.getObsvalue();
						}
					}
					else if(investigation2.getInvsttype().equals("Serology")){
						
						if(investigation2.getInvstname().equals("HIV I AND II")){
						     
							if(investigation2.getObsvalue()!=null){
						    	
						    	 if(!investigation2.getObsvalue().equals("")){
						    		 
						    		 value= investigation2.getObsvalue();
						    	 } else {
						    		 value="NON-REACTIVE";
						    	 }
						    } else {
						    	value="NON-REACTIVE";
						    } 
						} else if(investigation2.getInvstname().equals("AUSTRALIA")){
							if(investigation2.getObsvalue()!=null){
						    	
						    	 if(!investigation2.getObsvalue().equals("")){
						    		 
						    		 value= investigation2.getObsvalue();
						    	 } else {
						    		 value="NEGATIVE";
						    	 }
						    } else {
						    	value="NEGATIVE";
						    } 
						} else if(investigation2.getInvstname().equals("HCV")){
							if(investigation2.getObsvalue()!=null){
						    	
						    	 if(!investigation2.getObsvalue().equals("")){
						    		 
						    		 value= investigation2.getObsvalue();
						    	 } else {
						    		 value="NEGATIVE";
						    	 }
						    } else {
						    	value="NEGATIVE";
						    } 
						} else {
							value=investigation2.getObsvalue();
						}
					}else {
						value=investigation2.getObsvalue();
					}
					if(loginInfo.isKalmegha()) {
						   str.append("<td><input onchange='' style='line-height: 0px;background-color: rgba(255, 127, 80, 0.18);' value='"+value+"' type='text' name='obs"+investigation2.getId()+"' id='obs"+investigation2.getId()+"' class='form-control'></td/>");
					   }else if(loginInfo.isParihar()) {
						   if(investigation2.getInvstname().equals("HBSAG (Australia antigen)")) {
							   if(investigation2.getObsvalue()!=null) {
								   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='obs"+investigation2.getId()+"' id='obs"+investigation2.getId()+"' class='form-control'>"
										    + "<option value='"+investigation2.getObsvalue()+"'>"+investigation2.getObsvalue()+"</option>"
										    + "<option value='Positive'>Positive</option>"
											+ "<option value='Negative'>Negative</option>"
									        + "</select></td>");
							   }else {
						   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='obs"+investigation2.getId()+"' id='obs"+investigation2.getId()+"' class='form-control'>"
								    + "<option value='Positive'>Positive</option>"
									+ "<option value='Negative'>Negative</option>"
							        + "</select></td>");
							   }
						   }else if(investigation2.getInvstname().equals("HIV I and II")) {
							   if(investigation2.getObsvalue()!=null) {
								   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='obs"+investigation2.getId()+"' id='obs"+investigation2.getId()+"' class='form-control'>"
										    + "<option value='"+investigation2.getObsvalue()+"'>"+investigation2.getObsvalue()+"</option>"
										    + "<option value='Reactive'>Reactive</option>"
											+ "<option value='Non-Reactive'>Non-Reactive</option>"
									        + "</select></td>"); 
							   }else {
							   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='obs"+investigation2.getId()+"' id='obs"+investigation2.getId()+"' class='form-control'>"
									    + "<option value='Reactive'>Reactive</option>"
										+ "<option value='Non-Reactive'>Non-Reactive</option>"
								        + "</select></td>");
							   }
						   }
						   else if(investigation2.getInvstname().equals("TO")) {
							   if(investigation2.getFinding1()!=null) {
								   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find1"+investigation2.getId()+"' id='find1"+investigation2.getId()+"' class='form-control'>"
										    + "<option value='"+investigation2.getFinding1()+"'>"+investigation2.getFinding1()+"</option>"
										    + "<option value='-ve'>-</option>"
											+ "<option value='+ve'>+</option>"
									        + "</select></td>");
									
							   }else {
							   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find1"+investigation2.getId()+"' id='find1"+investigation2.getId()+"' class='form-control'>"
									    + "<option value='-ve'>-</option>"
										+ "<option value='+ve'>+</option>"
								        + "</select></td>");
							   }
							   if(investigation2.getFinding2()!=null) {
								   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find2"+investigation2.getId()+"' id='find2"+investigation2.getId()+"' class='form-control'>"
										   
                                           + "<option value='"+investigation2.getFinding2()+"'>"+investigation2.getFinding2()+"</option>"
                                           + "<option value='-ve'>-</option>"
											+ "<option value='+ve'>+</option>"
									        + "</select></td>");
										
								   }else {
							   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find2"+investigation2.getId()+"' id='find2"+investigation2.getId()+"' class='form-control'>"
									    + "<option value='-ve'>-</option>"
										+ "<option value='+ve'>+</option>"
								        + "</select></td>");
								   }
							   if(investigation2.getFinding3()!=null) {
								   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find3"+investigation2.getId()+"' id='find3"+investigation2.getId()+"' class='form-control'>"
										   + "<option value='"+investigation2.getFinding3()+"'>"+investigation2.getFinding3()+"</option>"
										   + "<option value='-ve'>-</option>"
											+ "<option value='+ve'>+</option>"
									        + "</select></td>");
										
								   }else {
							   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find3"+investigation2.getId()+"' id='find3"+investigation2.getId()+"' class='form-control'>"
									    + "<option value='-ve'>-</option>"
										+ "<option value='+ve'>+</option>"
								        + "</select></td>");
						   } if(investigation2.getFinding4()!=null) {
							   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find4"+investigation2.getId()+"' id='find4"+investigation2.getId()+"' class='form-control'>"
									    + "<option value='"+investigation2.getFinding4()+"'>"+investigation2.getFinding4()+"</option>"
                                        + "<option value='-ve'>-</option>"
										+ "<option value='+ve'>+</option>"
								        + "</select></td>");
									
						   }else {
							   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find4"+investigation2.getId()+"' id='find4"+investigation2.getId()+"' class='form-control'>"
									    + "<option value='-ve'>-</option>"
										+ "<option value='+ve'>+</option>"
								        + "</select></td>");
					     }
							   }else if(investigation2.getInvstname().equals("TH")) {
								   if(investigation2.getFinding1()!=null) {
									   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find1"+investigation2.getId()+"' id='find1"+investigation2.getId()+"' class='form-control'>"
											   + "<option value='"+investigation2.getFinding1()+"'>"+investigation2.getFinding1()+"</option>"
											    + "<option value='-ve'>-</option>"
												+ "<option value='+ve'>+</option>"
										        + "</select></td>");
											
									   }else {
								   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find1"+investigation2.getId()+"' id='find1"+investigation2.getId()+"' class='form-control'>"
										    + "<option value='-ve'>-</option>"
											+ "<option value='+ve'>+</option>"
									        + "</select></td>");
									   } if(investigation2.getFinding2()!=null) {
										   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find2"+investigation2.getId()+"' id='find2"+investigation2.getId()+"' class='form-control'>"
												   + "<option value='"+investigation2.getFinding2()+"'>"+investigation2.getFinding2()+"</option>" 
												   + "<option value='-ve'>-</option>"
													+ "<option value='+ve'>+</option>"
											        + "</select></td>");
												
									   }else {
								   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find2"+investigation2.getId()+"' id='find2"+investigation2.getId()+"' class='form-control'>"
										    + "<option value='-ve'>-</option>"
											+ "<option value='+ve'>+</option>"
									        + "</select></td>");
									   } if(investigation2.getFinding3()!=null) {
										   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find3"+investigation2.getId()+"' id='find3"+investigation2.getId()+"' class='form-control'>"
												    + "<option value='"+investigation2.getFinding3()+"'>"+investigation2.getFinding3()+"</option>"
												    + "<option value='-ve'>-</option>"
													+ "<option value='+ve'>+</option>"
											        + "</select></td>");
												
									   }else {
								   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find3"+investigation2.getId()+"' id='find3"+investigation2.getId()+"' class='form-control'>"
										    + "<option value='-ve'>-</option>"
											+ "<option value='+ve'>+</option>"
									        + "</select></td>");
									   } if(investigation2.getFinding4()!=null) {
										   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find4"+investigation2.getId()+"' id='find4"+investigation2.getId()+"' class='form-control'>"
												    + "<option value='"+investigation2.getFinding4()+"'>"+investigation2.getFinding4()+"</option>"
												    + "<option value='-ve'>-</option>"
													+ "<option value='+ve'>+</option>"
											        + "</select></td>");
												
									   }else {
								   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find4"+investigation2.getId()+"' id='find4"+investigation2.getId()+"' class='form-control'>"
										    + "<option value='-ve'>-</option>"
											+ "<option value='+ve'>+</option>"
									        + "</select></td>");
									   }
								   }else if(investigation2.getInvstname().equals("AH")) {
									   if(investigation2.getFinding1()!=null) {
										   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find1"+investigation2.getId()+"' id='find1"+investigation2.getId()+"' class='form-control'>"
												   + "<option value='"+investigation2.getFinding1()+"'>"+investigation2.getFinding1()+"</option>" 
												   + "<option value='-ve'>-</option>"
												   + "<option value='+ve'>+</option>"
											       + "</select></td>");
												
										   }else {
									   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find1"+investigation2.getId()+"' id='find1"+investigation2.getId()+"' class='form-control'>"
											    + "<option value='-ve'>-</option>"
												+ "<option value='+ve'>+</option>"
										        + "</select></td>");
										   } if(investigation2.getFinding2()!=null) {
											   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find2"+investigation2.getId()+"' id='find2"+investigation2.getId()+"' class='form-control'>"
													   + "<option value='"+investigation2.getFinding2()+"'>"+investigation2.getFinding2()+"</option>"
													    + "<option value='-ve'>-</option>"
														+ "<option value='+ve'>+</option>"
												        + "</select></td>");

													
										   }else {
									   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find2"+investigation2.getId()+"' id='find2"+investigation2.getId()+"' class='form-control'>"
											    + "<option value='-ve'>-</option>"
												+ "<option value='+ve'>+</option>"
										        + "</select></td>");
										   } if(investigation2.getFinding3()!=null) {
											   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find3"+investigation2.getId()+"' id='find3"+investigation2.getId()+"' class='form-control'>"
													    + "<option value='"+investigation2.getFinding3()+"'>"+investigation2.getFinding3()+"</option>"
													    + "<option value='-ve'>-</option>"
														+ "<option value='+ve'>+</option>"
												        + "</select></td>");
													
										   }else {
									   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find3"+investigation2.getId()+"' id='find3"+investigation2.getId()+"' class='form-control'>"
											    + "<option value='-ve'>-</option>"
												+ "<option value='+ve'>+</option>"
										        + "</select></td>");
										   } if(investigation2.getFinding4()!=null) {
											   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find4"+investigation2.getId()+"' id='find4"+investigation2.getId()+"' class='form-control'>"
													    + "<option value='"+investigation2.getFinding4()+"'>"+investigation2.getFinding4()+"</option>"
													    + "<option value='-ve'>-</option>"
														+ "<option value='+ve'>+</option>"
												        + "</select></td>");
													
										   }else {
									   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find4"+investigation2.getId()+"' id='find4"+investigation2.getId()+"' class='form-control'>"
											    + "<option value='-ve'>-</option>"
												+ "<option value='+ve'>+</option>"
										        + "</select></td>");
								      }
									   }else if(investigation2.getInvstname().equals("BH")) {
										   if(investigation2.getFinding1()!=null) {
											   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find1"+investigation2.getId()+"' id='find1"+investigation2.getId()+"' class='form-control'>"
													   + "<option value='"+investigation2.getFinding1()+"'>"+investigation2.getFinding1()+"</option>"
													    + "<option value='-ve'>-</option>"
														+ "<option value='+ve'>+</option>"
												        + "</select></td>");
													
											   }else {
										   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find1"+investigation2.getId()+"' id='find1"+investigation2.getId()+"' class='form-control'>"
												   + "<option value='-ve'>-</option>"
													+ "<option value='+ve'>+</option>"
											        + "</select></td>");
											   } if(investigation2.getFinding2()!=null) {
												   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find2"+investigation2.getId()+"' id='find2"+investigation2.getId()+"' class='form-control'>"
														    + "<option value='"+investigation2.getFinding2()+"'>"+investigation2.getFinding2()+"</option>"
														    + "<option value='-ve'>-</option>"
															+ "<option value='+ve'>+</option>"
													        + "</select></td>");
														
											   }else {
										   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find2"+investigation2.getId()+"' id='find2"+investigation2.getId()+"' class='form-control'>"
												   + "<option value='-ve'>-</option>"
													+ "<option value='+ve'>+</option>"
											        + "</select></td>");
											   } if(investigation2.getFinding3()!=null) {
												   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find3"+investigation2.getId()+"' id='find3"+investigation2.getId()+"' class='form-control'>"
														    + "<option value='"+investigation2.getFinding3()+"'>"+investigation2.getFinding3()+"</option>"
														    + "<option value='-ve'>-</option>"
															+ "<option value='+ve'>+</option>"
													        + "</select></td>");
														
											   }else {
										   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find3"+investigation2.getId()+"' id='find3"+investigation2.getId()+"' class='form-control'>"
												    + "<option value='-ve'>-</option>"
													+ "<option value='+ve'>+</option>"
											        + "</select></td>");
											   } if(investigation2.getFinding4()!=null) {
												   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find4"+investigation2.getId()+"' id='find4"+investigation2.getId()+"' class='form-control'>"
														    + "<option value='"+investigation2.getFinding4()+"'>"+investigation2.getFinding4()+"</option>"
														    + "<option value='-ve'>-</option>"
															+ "<option value='+ve'>+</option>"
													        + "</select></td>");
														
											   }else {
										   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find4"+investigation2.getId()+"' id='find4"+investigation2.getId()+"' class='form-control'>"
												   + "<option value='-ve'>-</option>"
													+ "<option value='+ve'>+</option>"
											        + "</select></td>");
											   }
										   }else if(investigation2.getInvstname().equals("INTERPRETATION")) {
											   if(investigation2.getFinding1()!=null) {
												   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find1"+investigation2.getId()+"' id='find1"+investigation2.getId()+"' class='form-control'>"
														    + "<option value='"+investigation2.getFinding1()+"'>"+investigation2.getFinding1()+"</option>"
														    + "<option value='Negative'>Negative</option>"
														    + "<option value='Positive'>Positive</option>"
													        + "</select></td>");
											        
														
												   }else {
											   str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='find1"+investigation2.getId()+"' id='find1"+investigation2.getId()+"' class='form-control'>"
													    + "<option value='Negative'>Negative</option>"
														+ "<option value='Positive'>Positive</option>"
												        + "</select></td>");
										        }
											  
											   }else {
											   str.append("<td><input onchange='"+onchngefunction+"' style='line-height: 0px;background-color: rgba(255, 127, 80, 0.18);' value='"+value+"' type='text' name='obs"+investigation2.getId()+"' id='obs"+investigation2.getId()+"' class='form-control'></td/>");

										   }
					   }else {
						   str.append("<td><input onchange='"+onchngefunction+"' style='line-height: 0px;background-color: rgba(255, 127, 80, 0.18);' value='"+value+"' type='text' name='obs"+investigation2.getId()+"' id='obs"+investigation2.getId()+"' class='form-control'></td/>");
						}				
				}else{
					str.append("<td>"+investigation2.getObsvalue()+"</td>");
				}
				
				str.append("<td></td>");
				str.append("<td>"+investigation2.getNormvalue()+" "+investigation2.getInvstUnit()+"</td/>");
				//str.append("<td>"+investigation2.getInvstUnit()+"</td/>");
				str.append("</tr>");
				
				i++;
				
				 invstype=investigation2.getInvsttype();
				invstigationtype1=invstype;
			}
			
		str.append("~");
		String defremark=investigationDAO.getDefaultRemark(investigation.getInvsttypeid());
		
		str.append("<div class='hidden'></div>");
		str.append("lhg-kits");
		str.append(""+investigation.getInvreq());
			if(invstigationtype1!=null){
			if(false){
				str.append("hello-how-are-you/ok");
				str.append("hello-how-are-you/"+investigation.getRemark());
			}else if(defremark!=null){
				str.append("hello-how-are-you/ok");
				if(investigation.getRemark()==null){
					investigation.setRemark("");
				}
				if(!investigation.getRemark().equals("")){
					defremark=investigation.getRemark();
				}
				str.append("hello-how-are-you/"+" "+defremark);
			}else{
				str.append("hello-how-are-you/bad");
				str.append("hello-how-are-you/"+investigation.getRemark());
			}
		}
			str.append("<div class='hidden'>");
			str.append("~"+invstype+"");
			str.append("~"+loginInfo.isParihar());
		    str.append("</div>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+"");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String updaterport() throws Exception{
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			ClientDAO clientDAO= new JDBCClientDAO(connection);  
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			String editinvstparentid ="0";
			String approved =request.getParameter("approved");
			ArrayList<Investigation>selectedInvstList = (ArrayList<Investigation>)session.getAttribute("editinvstList");
			session.setAttribute("selectedInvstList", selectedInvstList);
			String userid = String.valueOf(loginInfo.getId());
			String invsttyperept="";
		
			for(Investigation investigation : selectedInvstList){
				String obsvalue = request.getParameter("obs"+investigation.getId());
				String findings=request.getParameter("f"+investigation.getId());
				String finding1=request.getParameter("find1"+investigation.getId());
				String finding2=request.getParameter("find2"+investigation.getId());
				String finding3=request.getParameter("find3"+investigation.getId());
				String finding4=request.getParameter("find4"+investigation.getId());
				emrForm.setInvsttype(investigation.getInvsttype());
//				System.out.println(""+findings);
				if(obsvalue==null){
					obsvalue="";
				}
				int update = investigationDAO.updateReportData(investigation.getId(),obsvalue,finding1,finding2,finding3,finding4);
				editinvstparentid = (String) session.getAttribute("editinvstparentid");
				int duserid  = loginInfo.getId();
				invsttyperept=investigation.getInvstreporttype();
				int upstatus = investigationDAO.UpdateUpStatus(editinvstparentid,duserid,approved);
			}
			
			Investigation inv=investigationDAO.getEditInvestigation(editinvstparentid);
			invsttyperept=inv.getReporttype();
			String date =DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String remark= request.getParameter("comment");
			if(invsttyperept!=null){
				if(invsttyperept.equals("Writeup")||invsttyperept.equals("Text")){
					remark=request.getParameter("commentwriteupappr");
				}
			}
			int res= investigationDAO.updateDateOFInvestigation(editinvstparentid,approved,date,userid);
			int rs= investigationDAO.updateRemark(editinvstparentid,remark);
			
			
			Investigation investigation= investigationDAO.getEditInvestigation(editinvstparentid);
			Client client= clientDAO.getClientDetails(investigation.getClientId());
			if (emrForm.getInvestigationtempname() != null) {
				if (!emrForm.getInvestigationtempname().equals("")) {
					String invenstigations = masterDAO.getIpdTemplateId("Investigations");
					int res5 = ipdDAO.saveIPDTemplate(emrForm.getInvestigationtempname(), invenstigations,
							emrForm.getDepartment(), 
							new String(DateTimeUtils.isNull(emrForm.getComment()).getBytes("iso-8859-1"), "UTF-8"));
				}
			}
			//@ jitu 
			//push notification
		//	MobileApiAction.sendpushnotification("single", "2~Your Investigation Report is ready to view!",client.getAbrivationid());
			
			//  02 MAY 2018 sms send critical value
			if(loginInfo.isCriticalvaluesms()){
				if(approved!=null){
			
				if(approved.equals("1")){
					//New SMS send code   15 June 2018
					ArrayList<Investigation> invescriticlvaluelist = investigationDAO.getInvestiCriticalValueNew(editinvstparentid);
					int size = invescriticlvaluelist.size();
					if (size > 0) {
						String mobile = invescriticlvaluelist.get(size - 1).getMobile();
						
						if(mobile!=null){
							if(!mobile.equals("") || (!mobile.equals("0"))){
								String msg ="Alert of Mr."+client.getFullname()+", ";
								for (Investigation investigation2 : invescriticlvaluelist) {
									msg =msg+""+investigation2.getInvstname()+": "+investigation2.getObsvalue()+", ";
								}
								SmsService smsService=new SmsService();
								String templateid="";
				            	smsService.sendSms(msg, mobile, loginInfo, new EmailLetterLog(),templateid);
							}
						}
						
					}
				}
			}}
			
			
			emrForm.setMessage("Record Updated Successfully!!");
			addActionMessage("Record Updated Successfully!!");
			emrForm.setInvsttype("0");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return execute();
	}
	
	
	
	public String editwriteup(){
		
		Connection connection = null;
		String action = request.getParameter("action");
		try{
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			String selectedid = request.getParameter("selectedid");
			session.setAttribute("emrinvstid", selectedid);
			
			Investigation investigation = investigationDAO.getEditInvestigation(selectedid);
			
	
			String parentdata = investigation.getPrepay() + "~" + investigation.getPostpay() + "~" + investigation.getOtherpay()  + "~"
					+  investigation.getEnglish() + "~" + investigation.getRegional() + "~" 
					+ investigation.getHindi() + "~" + investigation.getAdvoice();
			
			
			ArrayList<Investigation>selectedInvstList = investigationDAO.getSelectedInvestigationData(selectedid);
			/*
			 * String find=investigationDAO.getFindingsbyid(selectedid);
			 * session.setAttribute("find", find);
			 */
			
			session.setAttribute("editinvstList", selectedInvstList);
			session.setAttribute("editinvstparentid", selectedid);
			
			StringBuffer str = new StringBuffer();
			
			int i = 0;
			str.append("<input type='hidden' name='parentinvsteditdataid' id='parentinvsteditdataid' value='"+parentdata+"'>");
		/*	if(investigation.getReporttype().equalsIgnoreCase("Text")){
				str.append("<input type='hidden'  id='rpttype' value='text'>");
			} else {
				str.append("<input type='hidden'  id='rpttype' value='"+investigation.getReporttype()+"'>");
			}*/
			
			str.append("<input type='hidden'  id='rpttype' value='"+investigation.getReporttype()+"'>");
			
			StringBuffer writeupid = new StringBuffer();
			String textdata="";
			String findingtxt = "";
			
			for(Investigation investigation2 : selectedInvstList){
				findingtxt = investigation2.getFindings();
				writeupid.append(investigation2.getId() + ",");
				str.append("<tr>");
				/*str.append("<td>"+priscription.getDate()+"</td/>");*/
				
				if(investigation2.getObsvalue()!=null){
					if(investigation2.getObsvalue().equals("0")){
						investigation2.setObsvalue("");
					}
					
				}
				
				if(investigation2.getFindings()==null){
					investigation2.setFindings("");
				}
				
				if(investigation2.getBiorefrange()==null){
					investigation2.setBiorefrange("");
				}
				if(investigation2.getMethods()==null){
					investigation2.setMethods("");
				}
				String value=investigation2.getFindings();
				textdata= textdata+value;
				if(action.equals("1")){
					str.append("<td>"+investigation2.getInvsttype()+"</td/>");
					str.append("<td>"+investigation2.getInvstname()+"</td/>");
					
					if(investigation2.getInvsttype().equals("Serology")){
						
						if(investigation2.getInvstname().equals("HIV I AND II")){
						     
						    value="NON-REACTIVE";
						} else if(investigation2.getInvstname().startsWith("AUSTRALIA")){
							value="NEGATIVE";
						} else if(investigation2.getInvstname().equals("HCV")){
							value="NEGATIVE";
						} else {
							value=investigation2.getFindings();
						}
					}
					
					if(investigation.getReporttype().equalsIgnoreCase("Text")){
						 str.append("<td>-</td>");
						 str.append("<td>-</td>");
					} else {
					    
						if(investigation2.getInvstname().equals("BLOOD GROUP")) {
							
						//str.append("<td><input style='line-height: 0px' value='"+value+"' type='text' name='f"+investigation2.getId()+"' id='f"+investigation2.getId()+"' class='form-control'></td/>");
							if(loginInfo.isParihar()) {
						if(investigation2.getFindings()!=null) {
							str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='f"+investigation2.getId()+"' id='f"+investigation2.getId()+"' class='form-control'>"
									+ "<option value='"+investigation2.getFindings()+"'>"+investigation2.getFindings()+"</option>"
									+ "<option value='0'>Select Blood Group</option>"
									+ "<option value='A+ve'>A+ve</option>"
									+ "<option value='B+ve'>B+ve</option>"
									+ "<option value='O+ve'>O+ve</option>"
									+ "<option value='AB+ve'>AB+ve</option>"
									+ "<option value='A-ve'>A-ve</option>"
									+ "<option value='B-ve'>B-ve</option>"
									+ "<option value='O-ve'>O-ve</option>"
									+ "<option value='AB-ve'>AB-ve</option>"
									+ "</select></td/>");
						}else {
						str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='f"+investigation2.getId()+"' id='f"+investigation2.getId()+"' class='form-control'>"
								+ "<option value='0'>Select Blood Group</option>"
								+ "<option value='A+ve'>A+ve</option>"
								+ "<option value='B+ve'>B+ve</option>"
								+ "<option value='O+ve'>O+ve</option>"
								+ "<option value='AB+ve'>AB+ve</option>"
								+ "<option value='A-ve'>A-ve</option>"
								+ "<option value='B-ve'>B-ve</option>"
								+ "<option value='O-ve'>O-ve</option>"
								+ "<option value='AB-ve'>AB-ve</option>"
								+ "</select></td/>");
						}
						}else {
							str.append("<td><input style='line-height: 0px' value='"+value+"' type='text' name='f"+investigation2.getId()+"' id='f"+investigation2.getId()+"' class='form-control'></td/>");
						}
						}else if(investigation2.getInvstname().equals("Smear for Malarial Parasite (PS for MP)")) {
							
							if(investigation2.getFindings()!=null) {
							str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='f"+investigation2.getId()+"' id='f"+investigation2.getId()+"' class='form-control'>"
									    + "<option value='"+investigation2.getFindings()+"'>"+investigation2.getFindings()+"</option>"
									    + "<option value='Negative'>Negative</option>"
										+ "<option value='Positive(p.vivax)'>Positive(p.vivax)</option>"
										+ "<option value='Positive(p.falciparam)'>Positive(p.falciparam)</option>"
										+ "</select></td/>");	
							}else {
							str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='f"+investigation2.getId()+"' id='f"+investigation2.getId()+"' class='form-control'>"
								    + "<option value='Negative'>Negative</option>"
									+ "<option value='Positive(p.vivax)'>Positive(p.vivax)</option>"
									+ "<option value='Positive(p.falciparam)'>Positive(p.falciparam)</option>"
									+ "</select></td/>");
							}
						}else if(investigation2.getInvstname().equals("SICKLING TEST (SOLUBILITY)")) {
							if(investigation2.getFindings()!=null) {
								str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='f"+investigation2.getId()+"' id='f"+investigation2.getId()+"' class='form-control'>"
										+ "<option value='"+investigation2.getFindings()+"'>"+investigation2.getFindings()+"</option>"
										+ "<option value='Positive'>Positive</option>"
										+ "<option value='Negative'>Negative</option>"
								        + "</select></td/>");
							}else {
							str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='f"+investigation2.getId()+"' id='f"+investigation2.getId()+"' class='form-control'>"
								    + "<option value='Positive'>Positive</option>"
									+ "<option value='Negative'>Negative</option>"
							        + "</select></td/>");
							}
						}
						else if(investigation2.getInvstname().equals("HCV")) {
							if(loginInfo.isParihar()) {
							if(investigation2.getFindings()!=null) {
								str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='f"+investigation2.getId()+"' id='f"+investigation2.getId()+"' class='form-control'>"
										+ "<option value='"+investigation2.getFindings()+"'>"+investigation2.getFindings()+"</option>"
										+ "<option value='Reactive'>Reactive</option>"
										+ "<option value='Non-Reactive'>Non-Reactive</option>"
								        + "</select></td/>");
							}else {
							str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='f"+investigation2.getId()+"' id='f"+investigation2.getId()+"' class='form-control'>"
								    + "<option value='Reactive'>Reactive</option>"
									+ "<option value='Non-Reactive'>Non-Reactive</option>"
							        + "</select></td/>");
							}
						}else {
							str.append("<td><input style='line-height: 0px' value='"+value+"' type='text' name='f"+investigation2.getId()+"' id='f"+investigation2.getId()+"' class='form-control'>");
						}
						}else if(investigation2.getInvstname().equals("VDRL")) {
							if(investigation2.getFindings()!=null) {
							str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='f"+investigation2.getId()+"' id='f"+investigation2.getId()+"' class='form-control'>"
									    + "<option value='"+investigation2.getFindings()+"'>"+investigation2.getFindings()+"</option>"    
									    + "<option value='Reactive'>Reactive</option>"
										+ "<option value='Non-Reactive'>Non-Reactive</option>"
								        + "</select></td/>");
							}else {
							str.append("<td><select style='line-height: 0px' value='"+value+"' type='text' name='f"+investigation2.getId()+"' id='f"+investigation2.getId()+"' class='form-control'>"
								    + "<option value='Reactive'>Reactive</option>"
									+ "<option value='Non-Reactive'>Non-Reactive</option>"
							        + "</select></td/>");
							}
						}else {
							str.append("<td><input style='line-height: 0px' value='"+value+"' type='text' name='f"+investigation2.getId()+"' id='f"+investigation2.getId()+"' class='form-control'></td/>");

						}


						str.append("<td style='display:none'><input style='line-height: 0px' value='"+investigation2.getBiorefrange()+"' type='text' name='b"+investigation2.getId()+"' id='b"+investigation2.getId()+"' class='form-control'></td/>");
						str.append("<td><input style='line-height: 0px' value='"+investigation2.getMethods()+"' type='text' name='m"+investigation2.getId()+"' id='m"+investigation2.getId()+"' class='form-control'></td/>");
					}
					str.append("</tr>");
				}else{
					
					if(investigation.getReporttype().equalsIgnoreCase("Text")){
						str.append("<td>"+investigation2.getInvsttype()+"</td/>");
						str.append("<td>"+investigation2.getInvstname()+"</td/>");
						 str.append("<td>-</td>");
						 str.append("<td>-</td>");
						 str.append("<td>-</td>");
						
					} else {
							str.append("<td>"+investigation2.getInvsttype()+"</td/>");
							str.append("<td>"+investigation2.getInvstname()+"</td/>");
							str.append("<td>"+investigation2.getFindings()+"</td/>");
							str.append("<td>"+investigation2.getBiorefrange()+"</td/>");
							str.append("<td>"+investigation2.getMethods()+"</td/>");
					}
					str.append("</tr>");
				}
				
				
				i++;
			}
			
			String wertpid = "";
			if(writeupid.length()>0){
				 wertpid = writeupid.substring(0,writeupid.length()-1);
			}
			
			str.append("<input type='hidden' name='writeupid' id='writeupid' value='"+wertpid+"'> ");
			str.append("<input type='hidden' name='writeupidsize' id='writeupidsize' value='"+selectedInvstList.size()+"'> ");
			str.append("#");
			if(investigation.getReporttype().equalsIgnoreCase("Text")){
				
				String sectionid=investigationDAO.getInvstigationSectionId(Integer.parseInt(selectedid));
				ArrayList<Master> templateList= investigationDAO.getAllInvestigationTemplateList(sectionid);
				if(templateList.size()!=0){
					
					str.append("<b>Select Template</b>");
					str.append("<select id='invstemplate' onchange='getinvstemplate(this.value)' class='form-control chosen-select'>");
					 str.append("<option value='0'>Select Template</option>"); 
					 for(Master master:templateList){
						 str.append("<option value='"+master.getId()+"'>"+master.getName()+"</option>");
					 }
					
					str.append("</select>");
				
					
				}
				str.append("#");
				str.append(textdata);
			} else {
				str.append("0");
			}
			
			String defremark=investigationDAO.getDefaultRemark(investigation.getInvsttypeid());
		
			str.append("");
			
			
			if (defremark != null) {

				if (investigation.getRemark() == null) {
					investigation.setRemark("");
				}
				if (!investigation.getRemark().equals("")) {
					defremark = investigation.getRemark();
				}
			}
			str.append("XXXthisisremarkforwriteupXXX" + defremark);
			str.append("XXXthisisremarkforwriteupXXX" + investigation.getInvreq());
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+"");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	//var url = "savefindingsInvestigation?finding="+finding+"&biorefrange="+biorefrange+"&method="+method+"&selectedid="+selectedid+" ";
		public String savefindings() throws Exception{
			String finding = request.getParameter("finding");
			String biorefrange = request.getParameter("biorefrange");
			String method = request.getParameter("method");
			String selectedid = request.getParameter("selectedid");
			String remarkwriteup=request.getParameter("commentwriteup");
			String temp[] = emrForm.getHdnselectedid().split(",");
			
			Connection connection = null;
			try{
				connection = Connection_provider.getconnection();
				InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
				MasterDAO masterDAO = new JDBCMasterDAO(connection);
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				for(int i=0;i<temp.length;i++){
					
					 finding = request.getParameter("f"+temp[i]);
					 biorefrange = request.getParameter("b"+temp[i]);
					 method = request.getParameter("m"+temp[i]);
					 selectedid = temp[i];
					
					int update = investigationDAO.updateWriteupData(Integer.parseInt(selectedid),finding,biorefrange,method);
				}
				
				if (emrForm.getInvestigationtempname() != null) {
					if (!emrForm.getInvestigationtempname().equals("")) {
						String invenstigations = masterDAO.getIpdTemplateId("Investigations");
						int res5 = ipdDAO.saveIPDTemplate(emrForm.getInvestigationtempname(), invenstigations,
								emrForm.getDepartment(), 
								new String(DateTimeUtils.isNull(emrForm.getCommentwriteup()).getBytes("iso-8859-1"), "UTF-8"));
					}
				}
				String editinvstparentid = (String) session.getAttribute("editinvstparentid");
				int rs= investigationDAO.updateRemark(editinvstparentid,remarkwriteup);
				
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				int duserid  = loginInfo.getId();
				int upstatus = investigationDAO.UpdateUpStatus(editinvstparentid,duserid,"0");
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				String datetime = dateFormat.format(cal.getTime());
				investigationDAO.updateDateOFInvestigation(editinvstparentid, "0", datetime, "");
				
				
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			return execute();
		}
		
		public String savetextfindings() throws Exception{
			String finding = request.getParameter("finding");
			String selectedid = request.getParameter("selectedid");
			
			session.setAttribute("fromdate1", emrForm.getFromdate());
			session.setAttribute("todate1", emrForm.getTodate());
			session.setAttribute("searchtext1", emrForm.getSearchText());
			
			
			
			Connection connection = null;
			try{
				connection = Connection_provider.getconnection();
				InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
				
				int update = investigationDAO.updateWriteupData(Integer.parseInt(selectedid),finding,"","");
				String editinvstparentid = (String) session.getAttribute("editinvstparentid");
				String remark=emrForm.getImpresssiontext();
				int res=investigationDAO.updateRemark(editinvstparentid, remark);
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				int duserid  = loginInfo.getId();
				int upstatus = investigationDAO.UpdateUpStatus(editinvstparentid,duserid,"0");
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				String datetime = dateFormat.format(cal.getTime());
				investigationDAO.updateDateOFInvestigation(editinvstparentid, "0", datetime, "");
			}catch(Exception e){
				e.printStackTrace();
			}
			finally {
				connection.close();
			}
			
			return execute();
		}
		
		
		public String upinvfile(){
			
			String selectedid = request.getParameter("selectedid");
			session.setAttribute("emrinvstid", selectedid);
			return null;
		}
		
		
		public String savewriteup() throws Exception{
			
			Connection connection = null;
			try{
				connection = Connection_provider.getconnection();
				InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
				EmrDAO emrDAO = new JDBCEmrDAO(connection);
				ClientDAO clientDAO= new JDBCClientDAO(connection);
				
				String invstid = (String) session.getAttribute("emrinvstid");
				Investigation investigation = investigationDAO.getEditInvestigation(invstid);
				String approved= emrForm.getApproved1();
				
				if(approved!=null){
					if(approved.equals("1")){
						int duserid  = loginInfo.getId();
						int upstatus = investigationDAO.UpdateUpStatus(invstid,duserid,"1");
						Client client= clientDAO.getClientDetails(investigation.getClientId());
						
						//@ jitu 
						//push notification
					  //	MobileApiAction.sendpushnotification("single", "2~Your Investigation Report is ready to view!",client.getAbrivationid());
						
					}
				}else{
					approved="";
				}
			
				
				
				
				   String invsttype  = investigationDAO.getPrintInvsttype(invstid);
				
				 	Emr emr = new Emr();
					
					String patientid = investigation.getClientId();
					String practionerId = investigation.getPrectionerid();
					String condition = investigation.getConditionid();
					
					
					emr.setPatientId(Integer.parseInt(patientid));
					emr.setPractitionerId(Integer.parseInt(practionerId));
					emr.setCondition_id(condition);
					emr.setDoctType("Investigation");
					
					String date =DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					String userid = String.valueOf(loginInfo.getId());
					int res= investigationDAO.updateDateOFInvestigation(invstid,approved,date,userid);
					
				ArrayList<String> attachfile = new ArrayList<>();
				
				 // copy the uploaded files into pre-configured location
		        for (int i = 0; i < emrForm.getFileUpload().length; i++) {
		            File uploadedFile = emrForm.getFileUpload()[i];
		            String fileName = emrForm.getFileUploadFileName()[i];
		            fileName = loginInfo.getClinicUserid() + "_" +invstid + "_" + fileName;
		            
		            //fileName = invsttype + "_" + invstid + "_" + fileName;
		            String filePath = request.getRealPath("/liveData/document/");
		            
		            /*String workingDirectory = System.getProperty("user.dir");
					String absoluteFilePath = workingDirectory + File.separator + loginInfo.getClinicUserid() + File.separator +  fileName;*/
		            
					
//		            System.out.println("Server path:" + filePath);
		            try {
		            	File fileToCreate = new File(filePath, fileName);
		            	FileUtils.copyFile(uploadedFile, fileToCreate);
		            	
		            	emr.setFileName(fileName);
		            	String attachFileName="";
		            	attachFileName = filePath + "/" + fileName;
		            	attachfile.add(attachFileName);
		            	int result = emrDAO.savePatientDocument(emr,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()),invstid,loginInfo.getUserId()); 
		            	
		            } catch (IOException ex) {
//		                System.out.println("Could not copy file " + fileName);
		                ex.printStackTrace();
		            }
		        }
		        if(loginInfo.isAustralia()){
		        	UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			        Client client = clientDAO.getClientDetails(patientid);
			        UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(practionerId));
			        //Send Mail
			        int loginId = loginInfo.getId();
					String to = client.getEmail();
			        
					String cc = "";
					cc = DateTimeUtils.isNull(userProfile.getEmail());
					/*cc = DateTimeUtils.isNull(loginInfo.getTelemedPharmacyMail());
					if(cc.equals("")){
						cc = DateTimeUtils.isNull(userProfile.getEmail());
					}else{
						if(!DateTimeUtils.isNull(userProfile.getEmail()).equals("")){
							cc = cc +","+DateTimeUtils.isNull(userProfile.getEmail());
						}
					}*/
					String subject = "Investigation";
					String notes = "Dear "+client.getFullname()+",<br>";
					notes = notes+"PFA.";
					int procurementid1 = 0;
					String type = "";
					// set deliver status
					String status = "Email";
					EmailLetterLog emailLetterLog = new EmailLetterLog();
					emailLetterLog.setType(status);

					EmbeddedImageEmailUtil.sendMailMultipleAttachment(connection, loginId, to, cc, subject, notes, attachfile, loginInfo,
							emailLetterLog, "0");
		        }
		        

		        
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return execute();
		}
		
	
	public String attachment(){
		Connection connection = null;
		String selectedid = request.getParameter("id");
		
		try{
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			String invsttype = investigationDAO.getPrintInvsttype(selectedid);
			String attachmentfile = investigationDAO.getAttchmentFile(selectedid,invsttype);
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+attachmentfile+""); 
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	
	
public String print(){
		
	if (!verifyLogin(request)) {

		return "login";
	}
		Connection connection = null;
		String selectedid = request.getParameter("selectedid");
		if(selectedid!=null){
			if(selectedid.equals("") || selectedid.equals("0")){
				int parentid=(Integer) session.getAttribute("invstsaveparent");
				selectedid=String.valueOf(parentid);
				session.removeAttribute("invstsaveparent");
			}	
			
		}else {
			int parentid=(Integer) session.getAttribute("invstsaveparent");
			selectedid=String.valueOf(parentid);
			session.removeAttribute("invstsaveparent");
		}
		
		String rpt = request.getParameter("rpt");
		if(rpt!=null){
			if(rpt.equals("") || rpt.equals("0")){
				rpt=(String) session.getAttribute("invwriteup");
				session.removeAttribute("invwriteup");
			}
		}else{
			rpt=(String) session.getAttribute("invwriteup");
			session.removeAttribute("invwriteup");
		}
		String sectionid = request.getParameter("sectionid");
		if(sectionid!=null){
			if(sectionid.equals("") || sectionid.equals("0")){
				sectionid=(String) session.getAttribute("invsectionid");
				session.removeAttribute("invsectionid");
			}
		}else{
			sectionid=(String) session.getAttribute("invsectionid");
			session.removeAttribute("invsectionid");
		}
		
		String fromdate= request.getParameter("fromdate");
		String todate= request.getParameter("todate");
		String lastday="";
		if(fromdate!=null){
			if(fromdate.equals("") || fromdate.equals("0")){
				lastday=(String) session.getAttribute("invlastmodi");
				session.removeAttribute("invlastmodi");
				fromdate=lastday.split(" ")[0];
				todate=fromdate;
			}
		}else{
			lastday=(String) session.getAttribute("invlastmodi");
			session.removeAttribute("invlastmodi");
			if(lastday!=null){
			fromdate=lastday.split(" ")[0];
			todate=fromdate;
			}
		}
		try{
			
			
			
			/*ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule= false;
			int modulecount =0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if(breadcrumbs.getName().equals("Investigation Print")){
					isavilablemodule =true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				}else{
					indentflowlist.add(breadcrumbs);
				}
			}
			if(!isavilablemodule){
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Investigation Print");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("");
				breadcrumbs.setIscurrent(true);
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);*/
			
			
			
			
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			
			
			
			ArrayList<Emr>invstdocList = investigationDAO.getInvstDocList(selectedid);
			emrForm.setDocList(invstdocList);
			
			
			String invsttype = investigationDAO.getPrintInvsttype(selectedid);
			emrForm.setInvsttype(invsttype);
			UserProfileDAO userProfileDAO1= new JDBCUserProfileDAO(connection);
			Investigation investigation = investigationDAO.getEditInvestigation(selectedid);
			
			emrForm.setJobtitle(investigation.getJobtitle());
			
			emrForm.setRequested_uerid(userProfileDAO1.getUserFullNameFromId(""+userProfileDAO1.getDiaryUserId(investigation.getRequested_userid())));
			
			
			emrForm.setInvesttemp(selectedid);
			ArrayList<Investigation>selectedInvstList = investigationDAO.getPrintSelectedInvestigationData(selectedid);
			emrForm.setSelectedInvstList(selectedInvstList);
			
			emrForm.setReportType(rpt);
			
			if(investigation.getIpdid()==null){
				emrForm.setIpdid("0");
				emrForm.setWardname("");
				emrForm.setBedname("");
			} else {
				BedDao bedDao = new JDBCBedDao(connection);  
				IpdDAO ipdDAO=new JDBCIpdDAO(connection);
				String ipdid= investigation.getIpdid();
				Bed bed =bedDao.getEditIpdData(ipdid);
				String ward= ipdDAO.getIpdWardName(bed.getWardid());
				String bedname= ipdDAO.getIpdBedName(bed.getBedid());
				emrForm.setWardname(ward);
				emrForm.setBedname(bedname);
				emrForm.setIpdid(ipdid);
				emrForm.setDaycare(bedDao.isDayCare(ipdid));
				
			}
			
			
			//set client data
			String clientid = investigation.getClientId();
			ClientDAO clientDAO  = new JDBCClientDAO(connection);
			Client client = clientDAO.getClientDetails(clientid);
			if(client.getTypeName()==null){
				client.setTypeName("0");
			}
			if(client.getTypeName().equals("")){
				client.setTypeName("0");
			}
			emrForm.setTpid(client.getTypeName());
			if(client.getTpDetails()!=null){
				emrForm.setThirdPartyCompanyName(client.getTpDetails().getCompanyName());	
			}
			
			if(client.getType()==null){
				client.setType("0");
			}
			emrForm.setTptype(client.getType());
			String payby = "";
			if(client.getWhopay().equals(Constants.PAY_BY_THIRD_PARTY)){
				payby = "Third Party";
			}else{
				payby = "Client";
			}


			String fullname = client.getTitle() + " " + client.getFirstName() + " "+client.getMiddlename()+" " + client.getLastName();
			String agegender="";
			String dob = client.getDob();
			String age = DateTimeUtils.getAge(client.getDob());
			if(Integer.parseInt(age)<2){
				if(Integer.parseInt(age)<1){
					String monthdays= DateTimeUtils.getMonthandDays(client.getDob());
					agegender=monthdays+" / "+client.getGender();
				}else{
					String monthdays= DateTimeUtils.getMonthandDays(client.getDob());
					agegender= age + " Years" + " "+monthdays+" / "+client.getGender();
				}
			} else {
				agegender = age + " / " + client.getGender();	
			}
			agegender = DateTimeUtils.getAge1(client.getDob())+" / "+client.getGender();
			//String ageandgender = DateTimeUtils.getAge(client.getDob());

			//String ageandgender = DateTimeUtils.getAge(client.getDob());
			emrForm.setFullname(fullname);
			emrForm.setAgeandgender(agegender);
			emrForm.setClientId(clientid);
			emrForm.setGender(client.getGender());
			emrForm.setDateTime(investigation.getDate());
			emrForm.setId(Integer.parseInt(selectedid));
			emrForm.setAbrivationid(client.getAbrivationid());
			emrForm.setUpdate_date(investigation.getUpdate_date());
			emrForm.setComplete_date(investigation.getComplete_date());
			emrForm.setCollect_date(investigation.getCollect_date());
			emrForm.setRemark(investigation.getRemark());
			if(loginInfo.getInvestigation_newaccess().equals("1")){
				String reporton= emrForm.getUpdate_date();
				if(reporton!=null){
					if(!reporton.equals("")){
						String temp[]=reporton.split(" ");
						emrForm.setUpdate_date(temp[0]);
					}
				}
			}
			
			//show admin for all user other than practitioner
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(investigation.getPrectionerid()));
			
			
			String practfullname = userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname();
			emrForm.setPractitionerName(practfullname);
			emrForm.setDepartment(userProfile.getSpecialization());
			emrForm.setPractDepartment(userProfile.getSpecialization());
			if(investigation.getPrectionerid().equals("0")){
				practfullname = clientDAO.getGPname(client.getGpid());
			}else{
				if(investigation.getUserid()!=null){
					userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(investigation.getUserid()));
					practfullname = userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname();
				}
			}
			
			emrForm.setDiaryUser(practfullname);
			String quali = userProfile.getQualification();
			emrForm.setQualification(userProfile.getQualification());
			emrForm.setSpecialization(userProfile.getSpecialization());
			
			int duserid  = Integer.parseInt(investigation.getUpdatedby());
			userProfile = userProfileDAO.getUserprofileDetails(duserid);
			practfullname = userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname();
			emrForm.setPathLabuser(practfullname);
			String quali1 = userProfile.getQualification();
			if(quali==null){
				emrForm.setQualification(quali1);
			}
			
			
			//letter head
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			//ArrayList<Clinic> locationAdressList = accountsDAO.getLetterHeadDetails(loginInfo.getClinicid());
			
			 ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
				
			    clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			    emrForm.setClinicName(clinic.getClinicName());
				emrForm.setClinicOwner(clinic.getClinicOwner());
				emrForm.setOwner_qualification(clinic.getOwner_qualification());
				emrForm.setLocationAdressList(locationAdressList);
				emrForm.setAddress(clinic.getAddress());
				emrForm.setLandLine(clinic.getLandLine());
				emrForm.setClinicemail(clinic.getEmail());
				emrForm.setWebsiteUrl(clinic.getWebsiteUrl());
				emrForm.setClinicLogo(clinic.getUserImageFileName());
				emrForm.setUserid(loginInfo.getUserId());
			    emrForm.setMobile(clinic.getMobileNo());
			    
			if(loginInfo.getJobTitle().equals("Pathlab") || loginInfo.getJobTitle().equals("Radiologist")){
				clinic = clinicDAO.getLettHeadDetails(loginInfo.getUserId());
				Clinic clinic1 = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				clinic.setOwner_qualification(clinic1.getOwner_qualification());
				clinic.setClinicOwner(clinic1.getClinicOwner());
			} else {
				clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			}
			
			if(sectionid==null){
				sectionid="0";
			}
			if(!sectionid.equals("0")){
				String invreq = request.getParameter("invreq");
				
				if(invreq!=null){
					if(invreq.equals("") || invreq.equals("0")){
						invreq=(String) session.getAttribute("invrequ");
						session.removeAttribute("invrequ");
					}
				}else{
					invreq=(String) session.getAttribute("invrequ");
					session.removeAttribute("invrequ");
				}
			    String curdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			    ArrayList<Master>masterInvstTypeList =new ArrayList<Master>();
			    masterInvstTypeList=		investigationDAO.getMasterInvstTypeList(sectionid,clientid,fromdate,todate,investigation.getReporttype(),selectedid,invreq);
			    
			    emrForm.setMasterInvstTypeList(masterInvstTypeList);
			    
			    session.setAttribute("masterInvstTypeList", masterInvstTypeList);
			    
			    String sectionName = investigationDAO.getInvSectionName(sectionid);
			    emrForm.setSectionName(sectionName);
			    
			    emrForm.setInvreq(invreq);
			    Master master = investigationDAO.getInvSectionDetails(sectionid);
			    emrForm.setLabname(master.getLabname());
			    emrForm.setCreport(master.getCreport());
			    //akash 04 dec 2017 
			    if(investigation.getStatus().equals("1")){
			    		if(investigation.getApproved_userid()!=null){
			    			if(!investigation.getApproved_userid().equals("")){
			    				duserid  = Integer.parseInt(investigation.getApproved_userid());
								userProfile = userProfileDAO.getUserprofileDetails(duserid);
								practfullname = userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname();
								emrForm.setPathLabuser(practfullname);
								 quali1 = userProfile.getQualification();
								if(quali==null){
									emrForm.setQualification(quali1);
								}
								emrForm.setQualification(quali1);
								emrForm.setSpecialization(userProfile.getJobtitle());
			    			}
			    		}
			    }else{
			    	if(master.getPractitionerid()!=null){
			    		if(!master.getPractitionerid().equals("")){
			    			duserid  = Integer.parseInt(master.getPractitionerid());
							userProfile = userProfileDAO.getUserprofileDetails(duserid);
							practfullname = userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname();
							emrForm.setPathLabuser(practfullname);
							 quali1 = userProfile.getQualification();
							if(quali==null){
								emrForm.setQualification(quali1);
							}
							emrForm.setQualification(quali1);
							emrForm.setSpecialization(userProfile.getJobtitle());
							emrForm.setLabuserid(userProfile.getUserid());
			    		}
			    	}
			    	 	
			    }
			    
			   
				emrForm.setApprove_status(investigation.getStatus());
				
				String printbr = investigationDAO.getInvstPrintbr(investigation.getInvsttypeid());
				emrForm.setPrintbr(printbr);
				//set req doctor details
				duserid  = Integer.parseInt(investigation.getPrectionerid());
				if(duserid==0){
					duserid = 1;
				}
				userProfile = userProfileDAO.getUserprofileDetails(duserid);
				practfullname = userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname();
				emrForm.setReqspecialization(userProfile.getSpecialization());
				if(investigation.getPrectionerid().equals("0")){
					practfullname = clientDAO.getGPname(client.getGpid());
					String dname = investigationDAO.getGpDepartment(client.getGpid());
					emrForm.setReqspecialization(dname);
					
				}
				
				
				emrForm.setReqdiaryUser(practfullname);
				 quali = userProfile.getQualification();
				emrForm.setReqqualification(userProfile.getQualification());
				
				Investigation investigation1=investigationDAO.getSignUser();
				
			if(!loginInfo.isLmh()) {	
				if(investigation1.isSignuser_access() && investigation.getReporttype().equals("Numerical") || investigation.getReporttype().equals("Hybreed")) {
					investigation.setSignUser(investigation1.getPractitionerName());
				}
			}	
			    
			if(loginInfo.isLmh()) {
				//for @vspm sign according to approve user 
				investigation.setSignUser(investigation.getUpdatedby());
			 }
				if(!DateTimeUtils.isNull(investigation.getSignUser()).equals("")){
					userProfile = userProfileDAO.getUserprofileDetails(DateTimeUtils.convertToInteger(investigation.getSignUser()));//I have asked to change this parameter
					//userProfile = userProfileDAO.getUserprofileDetails(duserid);
					practfullname = userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname();
					//emrForm.setReqspecialization(userProfile.getSpecialization());
					emrForm.setPathLabuser(practfullname);
					emrForm.setQualification(userProfile.getQualification());
					emrForm.setJobtitle(userProfile.getJobtitle());
					emrForm.setRegino(userProfile.getRegisterno());
					MasterDAO masterDAO= new JDBCMasterDAO(connection);
					UserProfile uf=masterDAO.userSignature(investigation.getSignUser());
					emrForm.setUserImageFileName(uf.getFilename());
				}
				
				
				String paction = request.getParameter("paction");
				if(paction!=null){
					if(paction.equals("") || paction.equals("0")){
						paction=(String) session.getAttribute("invprintm");
						session.removeAttribute("invprintm");
					}
				}else{
					paction=(String) session.getAttribute("invprintm");
					session.removeAttribute("invprintm");
				}
				String actprn="0";
				String rptact=request.getParameter("actprn");
				if(rptact==null){
					actprn=(String)session.getAttribute("actprn");
				}else{
					actprn=rptact;
				}
				
				ArrayList<UserProfile> userlist=new ArrayList<UserProfile>();
				if(loginInfo.isLmh()) {
					 //for vspm sign according to approve user 
					 userlist= investigationDAO.userList1(investigation.getUpdatedby());
					 emrForm.setDepartment(investigation.getUpdatedby());
				}else {
					 userlist= investigationDAO.userList(investigation.getJobtitle());
				}
				
				//ArrayList<UserProfile> userlist= investigationDAO.userList(investigation.getJobtitle());  //commented because vspm requirement sign as per approve person
				emrForm.setAllInvsUserList(userlist);
				ArrayList<Master>departmentInvList =new ArrayList<Master>();
				if(paction!=null){
				if(paction.equals("r")){
					if(actprn.equals("1")){
						String deptid=investigationDAO.getdeptinvid(invreq);
						for(String str:deptid.split(",")){
							
						
						departmentInvList = investigationDAO.getDepartmentinvListPrint(invreq,str);
						
						}
						int size=departmentInvList.size();
						if(size>0){
							boolean bghsts=(departmentInvList.get(size-1).isBghhead());
							emrForm.setBghsts(bghsts);
						}
					}else{
						departmentInvList = investigationDAO.getDepartmentinvList(invreq);	
					}
					
					
					emrForm.setDepartmentInvList(departmentInvList);
					emrForm.setInvreq(invreq);
					
					return "printinvrequest";
				}
				}
				System.out.println(emrForm.getDepartment());
				System.out.println(emrForm.getPractDepartment());
			    return "printinvsection";
			 }
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "printinvestigation";
	}
	

public String pkglist(){
	
	Connection connection=null;
	try {
		
		connection=Connection_provider.getconnection();
		InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
		ArrayList<Master>pkgsList = investigationDAO.getInvPaksLists();
		
		 StringBuffer str  = new StringBuffer();
		   
		   str.append("<select onchange='addpackageoncreatecharge(this.value)' class='form-control' name='ipdpackage' id='ipdpackage'>");
		   str.append("<option value='0' >Select Package</option>");
		   for(Master m : pkgsList){
			   str.append("<option value='"+m.getId()+"' >"+m.getName()+"</option>");
		   }
		   str.append("");
		   str.append("</select>");
		   
		   response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+""); 
		
	}catch (Exception e) {
		// TODO: handle exception
	}
	
	return null;
}



	public String collect() throws Exception{ 
		
		Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			String id= request.getParameter("selectedid");
			InvestigationDAO investigationDAO=new JDBCInvestigationDAO(connection);
			String date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int res=investigationDAO.updateCollectDateandStatus(id,date);
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
	
	
	public EmrForm getModel() {
		// TODO Auto-generated method stub
		return emrForm;
	}


	public void prepare() throws Exception {
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			EmrDAO emrDAO = new  JDBCEmrDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			
			String date = DateTimeUtils.getDashboardTodayDate(loginInfo.getTimeZone());
			emrForm.setPriscdate(date);
			emrForm.setPriscdateandtime(DateTimeUtils.getPriscDatetime(loginInfo.getTimeZone()));
			
			ArrayList<Master>mdicinecategoryList = emrDAO.getmedicineCategoryList();
			emrForm.setMdicinecategoryList(mdicinecategoryList);
			
			ArrayList<Master>dosageList = emrDAO.getDosageList();
			emrForm.setDosageList(dosageList);
			
			ArrayList<Master>mdicneTypeList = emrDAO.getMedicineTypeList();
			emrForm.setMdicneTypeList(mdicneTypeList);
			
			
			ArrayList<Client> condtitionList = clientDAO.getTreatmentTypeList();
			emrForm.setTreatmentTypeList(condtitionList);

			
			
			ArrayList<Master>invsTypeList = emrDAO.getInvesigationTypeList();
			emrForm.setInvsTypeList(invsTypeList);
			
			ArrayList<Master>invstReportTypeList = emrDAO.getInvstReportTypeList();
			emrForm.setInvstReportTypeList(invstReportTypeList);
			
			ArrayList<Master>invstUnitList = emrDAO.getInvstUnitList();
			emrForm.setInvstUnitList(invstUnitList);
			
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			/*
			 * ArrayList<Master> invst_temp_list =
			 * masterDAO.getIpdTemplateListNamesList("Investigations");
			 * emrForm.setInvst_templt_list(invst_temp_list);
			 */
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			
			/*clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			emrForm.setClinicName(clinic.getClinicName());
			emrForm.setClinicOwner(clinic.getClinicOwner());
			emrForm.setOwner_qualification(clinic.getOwner_qualification());
		//	emrForm.setLocationAdressList(locationAdressList);
			//accountsForm.setAddress(clinic.getAddress());
			emrForm.setLandLine(clinic.getLandLine());
			emrForm.setClinicemail(clinic.getEmail());
			emrForm.setWebsiteUrl(clinic.getWebsiteUrl());*/
			
			emrForm.setLocationAdressList(locationAdressList);
			//emrForm.setClinicLogo(clinic.getUserImageFileName());
			
			SMSTemplateDAO smsTemplateDAO = new JDBCSMSTemplateDao(connection);
			ArrayList<EmailTemplate>smsTemplateList = smsTemplateDAO.getAllSMSTemplates();
			emrForm.setSmsTemplateList(smsTemplateList);
			
			ArrayList<Master>cbcIdList = investigationDAO.getCbcIdList();
			emrForm.setCbcIdList(cbcIdList);
		
			//set state and city list
			InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
			ArrayList<Master> statelist= vendorDAO.getAllStateList();
			emrForm.setStatelist(statelist);
			ArrayList<Master> citylist= vendorDAO.getAllCityList();
			emrForm.setCitylist(citylist);
			
			
			// for third party 
			
			ThirdPartyDAO  thirdPartyDAO = new JDBCThirdPartyDAO(connection);
			ArrayList<ThirdParty>thirdPartyTypeList = thirdPartyDAO.getThirdPartyList();
			emrForm.setThirdPartyTypeList(thirdPartyTypeList);
			
			
			//
			ArrayList<String> initialList = new ArrayList<String>();
			initialList = clientDAO.getInitialList();
			emrForm.setInitialList(initialList);
			
			emrForm.setCountryList(PopulateList.countryList());
			
			ArrayList<Client> refrenceList = clientDAO.getReferenceList();
			emrForm.setRefrenceList(refrenceList);
			
			
			emrForm.setCondtitionList(condtitionList);
			
			ArrayList<Client>surgeryList = clientDAO.getSurgeryList();
			emrForm.setSurgeryList(surgeryList);
			
			ArrayList<String> jobTitleList = userProfileDAO.getJobTitleList();
			emrForm.setJobTitleList(jobTitleList);
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(loginInfo.getId());
			//emrForm.setJobtitle(userProfile.getJobtitle());
			
			AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
			ArrayList<AppointmentType>additionalChargeList = appointmentDAO.getAdditionalChaergeTypeList("");
			emrForm.setAdditionalChargeList(additionalChargeList);
			
			ArrayList<Master>masterChageTypeList = appointmentDAO.getmasterChageTypeList(loginInfo);
			emrForm.setMasterChageTypeList(masterChageTypeList);
			emrForm.setMasterchargetype("Additional Charge");
			
			ArrayList<Location>locationList = notAvailableSlotDAO.getLocationList(loginInfo.getId());
			emrForm.setLocationList(locationList);
			String regLocationID = emrDAO.getRegisteredLocationId();
			emrForm.setLocationid(regLocationID);
			
			//investigation section master
			ArrayList<Master>invSectionList = investigationDAO.getInvestigationSectionList();
			emrForm.setInvSectionList(invSectionList);
			
			ArrayList<Master>disciplineList =  masterDAO.getDisciplineDataList();
			emrForm.setDisciplineList(disciplineList);
			
			//investigation pkg list
			ArrayList<Master>pkgsList = investigationDAO.getInvPaksLists();
			emrForm.setPkgsList(pkgsList);
			
			
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			emrForm.setClinicName(clinic.getClinicName());
			emrForm.setClinicOwner(clinic.getClinicOwner());
			emrForm.setOwner_qualification(clinic.getOwner_qualification());
			emrForm.setLocationAdressList(locationAdressList);
			emrForm.setAddress(clinic.getAddress());
			emrForm.setLandLine(clinic.getLandLine());
			emrForm.setClinicemail(clinic.getEmail());
			emrForm.setWebsiteUrl(clinic.getWebsiteUrl());
			emrForm.setClinicLogo(clinic.getUserImageFileName());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	}


	public String saveinvestigationNewPatient() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
			Client client = new Client();
			connection = Connection_provider.getconnection();
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			client.setTitle(emrForm.getTitle());
			client.setFirstName(emrForm.getFirstName());
			client.setLastName(emrForm.getLastName());
			client.setMiddlename(emrForm.getMiddleName());
			client.setDob(emrForm.getDob());
			client.setGender(emrForm.getGender());
			/*client.setAddress(emrForm.getAddress());*/
			client.setAddress1(emrForm.getAddress1());
			client.setTown(emrForm.getTown());
			client.setEmail(emrForm.getEmail());
			client.setMobNo(emrForm.getMobNo().trim());
			client.setWhopay("Client");
			client.setDiaryUser(emrForm.getDoctorname());
			String age= request.getParameter("age");
			String state = request.getParameter("state");
			client.setCounty(state);
			client.setCountry("India");
			String dob = emrForm.getDob();
			
			//  15 june 2018
			client.setFullname(client.getFirstName()+" "+client.getLastName());
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			
			 clientDAO=new JDBCClientDAO(connection);
			//save abrivation seq no
			String cdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			boolean checkifseq = clientDAO.checkifSequenceExist(cdate);
			String abrivationid = "";
			String clinicabrivation = clientDAO.getClinicAbrivation(loginInfo.getClinicid());
			String tempd[] = cdate.split("-");
			String y = tempd[0];
			String m = tempd[1];
			String d = tempd[2];
			String newseq="";
			if(checkifseq){
				int seqno = clientDAO.getSqeunceNumber(cdate);
				seqno++;
				int r = clientDAO.InserCdateSeq(cdate,seqno);
				//SNH170609001
				int yr = Integer.parseInt(y)%1000;
				 if(String.valueOf(seqno).length()==1)
				{
					 newseq="00"+seqno;
				}else if(String.valueOf(seqno).length()==2){
					 newseq="0"+seqno;
				}else{
					newseq=""+seqno;
				}
				abrivationid = clinicabrivation + yr + m +d + newseq;
			}else{
				
				int seqno = 1;
				int r = clientDAO.InserCdateSeq(cdate,seqno);
				//String seqno = clientDAO.getSqeunceNumber(cdate);
				int yr = Integer.parseInt(y)%1000;
				 if(String.valueOf(seqno).length()==1)
				{
					 newseq="00"+seqno;
				}else if(String.valueOf(seqno).length()==2){
					 newseq="0"+seqno;
				}else{
					newseq=""+seqno;
				}
				abrivationid = clinicabrivation + yr + m +d + newseq;
			}
			client.setAbrivationid(abrivationid);
			
			
			if(age!=null){
				
				if(age.equals("")){
				
					age="0";
				}
				
			} else {
				age="0";
			}

			Calendar calendar=Calendar.getInstance();
			int year=calendar.get(Calendar.YEAR);
			int ageee=Integer.parseInt(age);
			
			int dobyear=year-ageee;
			
			SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
			String date=dateFormat.format(calendar.getTime());
			String splitdate[]=date.split("/");
			String dodd=splitdate[0]+"/"+splitdate[1]+"/"+dobyear;
			client.setAge(ageee);
			
			//client.setDob(dodd);
			client.setDob(dob);
			
			int gpid=investigationDAO.insertGPDetails(emrForm.getDoctorname(),emrForm.getDiciplineName());
			client.setGpname(String.valueOf(gpid));
			if(emrForm.getMaritalsts().equals("0")){
				client.setMaritalsts("");
			}else{
			client.setMaritalsts(emrForm.getMaritalsts());
			}
			client.setDiaryUser(emrForm.getDoctorname());
			client.setDepartment(emrForm.getDiciplineName());
			int result = clientDAO.saveNewPatient(client,loginInfo.getId());
			if(loginInfo.isAyushman()) {
			  int practid=clientDAO.savePractioner(client);
			  result=clientDAO.updatePractid(gpid,practid);
			}
			emrForm.setMessage("Client Registered Suceessfully");
			addActionMessage("Client Registered Suceessfully");
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return "save";
		
	}
	
	
	public String saveeqclient() throws Exception{
		  if(!verifyLogin(request)){
		   return "login";
		  }
		  Connection connection = null;
		  try{
		   
		   connection=DriverManager.getConnection(""+Constants.DB_HOST+":3306/eq",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		   Client client = new Client();
		   ClientDAO clientDAO = new JDBCClientDAO(connection);
		   client.setTitle(emrForm.getTitle());
		   client.setFirstName(emrForm.getFirstName());
		   client.setLastName(emrForm.getLastName());
		   client.setMiddlename(emrForm.getMiddleName());
		   client.setDob(emrForm.getDob());
		   client.setGender(emrForm.getGender());
		   client.setAddress(emrForm.getAddress());
		   
		   client.setEmail(emrForm.getEmail());
		   client.setMobNo(emrForm.getMobNo().trim());

		   client.setDiaryUser(emrForm.getDoctorname());
		   InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
		   
		   int gpid=investigationDAO.insertGPDetails(emrForm.getDoctorname(),"");
		   client.setGpname(String.valueOf(gpid));
		   
		   client.setTreatmentType(emrForm.getTreatmentType());
		 
		   int result = clientDAO.saveNewPatient(client,loginInfo.getId());
		   emrForm.setMessage("Client Registered Suceessfully");
		   addActionMessage("Client Registered Suceessfully");
		   
		   
		   String clientId=String.valueOf(result);
		   String diaryUserId="1";
		   String conditionId=emrForm.getTreatmentType();
		   
		   session.setAttribute("clientId", clientId);
		   session.setAttribute("diaryUserId", diaryUserId);
		   session.setAttribute("conditionId", conditionId);
		   
		  }catch (Exception e) {
		   e.printStackTrace();
		  }
		  finally{
		   connection.close();
		  }
		  return "emrlink";
		  
		 }
	
	
	public String showclient() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		
		session.removeAttribute("invstypelist");
		session.removeAttribute("invstList");


		
		Connection connection = null;
		Client client = new Client();
		ArrayList<Client> allPatientList = new ArrayList<Client>();
		try{
			connection = Connection_provider.getconnection();
			
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			allPatientList = clientDAO.getAllPatient(loginInfo.getId(),"");
			
			
			
	//		clientForm.setAllPatientList(allPatientList);
			
			
			StringBuffer str = new StringBuffer();
			

			
			str.append("<table class='table table-hover table-condensed table-bordered table-striped ' > ");
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
				String name = client1.getTitle()+"/"+client1.getFirstName()+"/"+client1.getLastName(); 	
				String name1=client1.getTitle()+" "+client1.getFirstName()+" "+client1.getLastName(); 	
				
				String gpname=clientDAO.getGPname(client1.getGpid());
				String temo[] = gpname.split(" ");
				String doctorname = "";
				for(int i=0;i<temo.length;i++){
					doctorname += temo[i] + "/";
				}
				
//				System.out.println(doctorname);
				
			str.append("<tr>");
			str.append("<td>"+client1.getAbrivationid()+"</td>");

			str.append("<td style='cursor: pointer;'; onclick = setPatientName('"+client1.getId()+"','"+client1.getType()+"','"+client1.getAge()+"','"+client1.getGender()+"','"+doctorname+"')>"+name1+"</td>");
			if(client1.getOldclientId()==null){
				client1.setOldclientId("");
			}
			str.append("<input type='hidden' id='firstnameid"+client1.getId()+"' value='"+name+"'>");
			String whopay="";
			if(client1.getWhopay()!=null){
				
					if(client1.getWhopay().equals(Constants.PAY_BY_THIRD_PARTY)){
						whopay = "Third Party";
					}else{
						whopay = "Client";
					}
				
			}
			str.append("<td>"+whopay+"</td>");
			String age= DateTimeUtils.isNull(DateTimeUtils.getAge1(client1.getDob()));
			str.append("<td>"+age+"</td>");
			str.append("<td>"+DateTimeUtils.isNull(client1.getMobNo())+"</td>");
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
	
	
	public String cstatus() throws Exception{
	
     Connection connection=null;		
		
	 try {
		connection=Connection_provider.getconnection();
		InvestigationDAO investigationDAO=new JDBCInvestigationDAO(connection);
		String status=request.getParameter("status");
		String id=request.getParameter("id");
		int result=investigationDAO.updateApproveStatus(status,id);
		
		
	} catch (Exception e) {

	   e.printStackTrace();
	}	
	finally {
		connection.close();
	}
		
	  
	  return "save";	
	}
	
	
	
	
	public String searchParticularPatient() throws SQLException{
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
			
		//	clientForm.setAllPatientList(allPatientList);
        StringBuffer str = new StringBuffer();
			

			
			str.append("<table class='table table-hover table-condensed table-bordered table-striped ' > ");
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
				String name = client1.getTitle()+"/"+client1.getFirstName()+"/"+client1.getLastName(); 	
				String name1=client1.getTitle()+" "+client1.getFirstName()+" "+client1.getLastName(); 	
				
				String gpname=clientDAO.getGPname(client1.getGpid());
				String temo[] = gpname.split(" ");
				String doctorname = "";
				for(int i=0;i<temo.length;i++){
					doctorname += temo[i] + "/";
				}
				
//				System.out.println(doctorname);
				
			str.append("<tr>");
			str.append("<td>"+client1.getAbrivationid()+"</td>");

			str.append("<td style='cursor: pointer;'; onclick = setPatientName('"+client1.getId()+"','"+client1.getType()+"','"+client1.getAge()+"','"+client1.getGender()+"','"+doctorname+"')>"+name1+"</td>");
			str.append("<input type='hidden' id='firstnameid"+client1.getId()+"' value='"+name+"'>");
			String whopay="";
			if(client1.getWhopay()!=null){
				
					if(client1.getWhopay().equals(Constants.PAY_BY_THIRD_PARTY)){
						whopay = "Third Party";
					}else{
						whopay = "Client";
					}
				
			}
			str.append("<td>"+whopay+"</td>");
			String age= DateTimeUtils.isNull(DateTimeUtils.getAge1(client1.getDob()));
			str.append("<td>"+age+"</td>");
			str.append("<td>"+client1.getMobNo()+"</td>");
			str.append("<td>"+client1.getAdhno()+"</td>");
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
	
	
	public String getinvstemplate() throws Exception {
		
		Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			InvestigationDAO investigationDAO=new JDBCInvestigationDAO(connection);
			String id= request.getParameter("id");
			String data= investigationDAO.getInvsTemplateData(id);
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+data+""); 
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
		return null;
	}

	
	public String savenewinvs() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			InvestigationMasterDAO investigationMasterDAO= new JDBCInvestigationMasterDAO(connection);
			String invstypeid= request.getParameter("invstypeid");
			String newinvsname= request.getParameter("newinvsname");
			Master master= new Master();
			master.setId(Integer.parseInt(invstypeid));
			master.setName(newinvsname);
			master.setNormal_value("0");
			master.setCharge("0");
			int res= investigationMasterDAO.addInvestigationName(master);
			
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
	 public String graphreport(){
		  String templateid = emrForm.getSelectedtemplateid();
		  String invsttype = emrForm.getSelectedinvsttype();
		  String tempname= emrForm.getInvesttemp();
		  emrForm.setInvesttemp(tempname);
		  
//		  System.out.println(templateid);
		  if(!verifyLogin(request)){
				return "login";
			}
		  
		  Connection connection = null;
		  try{
		   connection = Connection_provider.getconnection();
		   InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
		   ClientDAO clientDAO=new JDBCClientDAO(connection);
		   ArrayList<Investigation>graphInvstNameList = investigationDAO.getInvstNameList(templateid);
		   
		   session.setAttribute("graphInvstNameList", graphInvstNameList);
		   session.setAttribute("invstgraphclientid", emrForm.getSelectedclientid());
		   
		   
		   if(emrForm.getSelectedclientid()!=null){
		    
		    Client client=clientDAO.getClientDetails(emrForm.getSelectedclientid());
		    String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
		    emrForm.setClientname(fullname);
		   }
		   
		   
		   String fromDate = emrForm.getFromDate();
		   String toDate = emrForm.getToDate(); 
		   if(fromDate.equals("")){
		    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		    Calendar cal = Calendar.getInstance();
		    cal.add(Calendar.DATE, -7); 
		    fromDate = dateFormat.format(cal.getTime());
		    emrForm.setFromDate(fromDate);
		   }
		   if(toDate.equals("")){
		    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		    Calendar cal = Calendar.getInstance();
		    //cal.add(Calendar.DATE, -7); 
		    toDate = dateFormat.format(cal.getTime());
		    emrForm.setToDate(toDate);
		   }
		   
		   
		   if(!fromDate.equals("")){
		    String temp[]= fromDate.split("/");
		    fromDate = temp[2]+"-"+temp[1]+"-"+temp[0];
		   }
		   if(!toDate.equals("")){
		    String temp1[]= toDate.split("/");
		    toDate = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
		   }
		   
		   session.setAttribute("invstgraphfromdate", fromDate);
		   session.setAttribute("invstgraphtodate",toDate);
		   
		   
		  }catch (Exception e) {
		   e.printStackTrace();
		  }
		  
		  
		  
		  return "graphreport";
		 }
	
public String investigationtypewisereport(){
	Connection connection= null;
	String fromdate="";
	String todate="";
	String test=null;
	if("".equals(test)){
		test="";
	}
	fromdate= emrForm.getFromdate();
	todate= emrForm.getTodate();
	if(fromdate==null){
		fromdate="";
	}
	if(todate==null){
		todate="";
	}
	if(fromdate.equals("")){
		 DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		    Calendar cal = Calendar.getInstance(); 
		    fromdate = dateFormat.format(cal.getTime());
	}
	if(todate.equals("")){
		 DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		    Calendar cal = Calendar.getInstance(); 
		    todate = dateFormat.format(cal.getTime());
	}
	try{
		connection= Connection_provider.getconnection();
		InvestigationDAO investigationDAO= new JDBCInvestigationDAO(connection);
		ArrayList<Investigation> invtypelist= new ArrayList<Investigation>();
		invtypelist= investigationDAO.getallinvsttypewisecountrpt(fromdate, todate);
		emrForm.setInvestigationList(invtypelist);
		emrForm.setFromdate(fromdate);
		emrForm.setTodate(todate);
		
		//letter head
		Clinic clinic = new Clinic();
		ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		//ArrayList<Clinic> locationAdressList = accountsDAO.getLetterHeadDetails(loginInfo.getClinicid());
		
		 ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			
		    clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
		    emrForm.setClinicName(clinic.getClinicName());
			emrForm.setClinicOwner(clinic.getClinicOwner());
			emrForm.setOwner_qualification(clinic.getOwner_qualification());
			emrForm.setLocationAdressList(locationAdressList);
			emrForm.setAddress(clinic.getAddress());
			emrForm.setLandLine(clinic.getLandLine());
			emrForm.setClinicemail(clinic.getEmail());
			emrForm.setWebsiteUrl(clinic.getWebsiteUrl());
			emrForm.setClinicLogo(clinic.getUserImageFileName());
			emrForm.setUserid(loginInfo.getUserId());
		 
	}catch (Exception e) {
		e.printStackTrace();
	}
	return "investigationtypewisereport";
}

public String invstincome(){
	Connection connection= null;
	try {
		connection= Connection_provider.getconnection();
		InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return "invstincome";
}
public String investigationtypewisereportnew() {
	  if(!verifyLogin(request)){
			return "login";
		}
	  Clinic clinic = new Clinic();
	  Connection connection;
	try {
		connection = Connection_provider.getconnection();
	
		ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		
		 ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			
		    clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
		    emrForm.setClinicName(clinic.getClinicName());
			emrForm.setClinicOwner(clinic.getClinicOwner());
			emrForm.setOwner_qualification(clinic.getOwner_qualification());
			emrForm.setLocationAdressList(locationAdressList);
			emrForm.setAddress(clinic.getAddress());
			emrForm.setLandLine(clinic.getLandLine());
			emrForm.setClinicemail(clinic.getEmail());
			emrForm.setWebsiteUrl(clinic.getWebsiteUrl());
			emrForm.setClinicLogo(clinic.getUserImageFileName());
			emrForm.setUserid(loginInfo.getUserId());
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return "investigationtypewisereportnew";
}

public String thirdpartysettings(){
	Connection connection= null;
	
	try {
		connection= Connection_provider.getconnection();
		InvestigationDAO investigationDAO= new JDBCInvestigationDAO(connection);
		ArrayList<ThirdParty> tplist= new ArrayList<ThirdParty>();
		tplist=investigationDAO.getTpListofInvestigation();
		emrForm.setTplist(tplist);		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "thirdpartysettings";
}

public String gettpinvsttype(){
	Connection connection= null;
	String tpid= request.getParameter("tpid");
	try {
		connection= Connection_provider.getconnection();
		InvestigationDAO investigationDAO= new JDBCInvestigationDAO(connection);
		String response1=investigationDAO.getInvestTypelistByTpid(tpid);
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1); 
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String savetoinvst(){
	Connection connection= null;
	emrForm.getThirdPartyCompanyName();
	emrForm.getInvsttype();
	String tpinvstid=request.getParameter("tpinvstname");
	if(tpinvstid==null){
		tpinvstid="";
	}
	if(tpinvstid.equals("")){
		tpinvstid= request.getParameter("nametext");
	}
	try {
		connection= Connection_provider.getconnection();
		InvestigationDAO investigationDAO= new JDBCInvestigationDAO(connection);
		String shortname=investigationDAO.getShortnameofTP(emrForm.getThirdPartyCompanyName());
		int col=investigationDAO.createColofInvst(shortname);
		int create=investigationDAO.updateTPInvsetigationName(tpinvstid, emrForm.getInvsttype(), shortname); 
	} catch (Exception e) {
		e.printStackTrace();
	}
	return  "savetoinvst";
}

public String gettemplateTypelist(){
	Connection connection= null;
	try {
		connection= Connection_provider.getconnection();
		String templateid=request.getParameter("id");
		String clientid= request.getParameter("clientid");
		String fromdate= request.getParameter("admndate");
		 DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		 Calendar cal = Calendar.getInstance();
		 String todate = dateFormat.format(cal.getTime());
		todate= DateTimeUtils.getCommencingDate1(todate);
		InvestigationDAO investigationDAO= new JDBCInvestigationDAO(connection);
		ArrayList<Investigation>graphInvstNameList = investigationDAO.getInvstNameList(templateid);
		int i=1,counttr=0;
		StringBuffer buffer= new StringBuffer();
		
		buffer.append("<table class='my-table xlstable' style='width: 100%'>");
		for(Investigation investigation : graphInvstNameList){
			String name= investigation.getInvstname();
		
			String graphdatelist = investigationDAO.getGraphDateList(clientid,investigation.getInvstname(),fromdate,todate);
			String graphValueList = investigationDAO.getGraphValueList(clientid,investigation.getInvstname(),fromdate,todate);
			String invstTypeName = investigationDAO.getGraphinvstTypeName(clientid,investigation.getInvstname(),fromdate,todate);
			String date[]= graphdatelist.split(",");
			String values[]= graphValueList.split(",");
			int x= date.length;
			//buffer.append(x);
			if(i==1){
			
			/*	buffer.append("<tr><div><h3>"+invstTypeName+"</h3><div></tr>");*/
			buffer.append("<tr bgcolor='#283747' style='color: white'><td >"+invstTypeName+"</td> ");
			
			for(int c=0;c<x;c++){
				date[c]= date[c].replace("'", "");
			buffer.append("<td>"+date[c]+"</td> ");
			}
			buffer.append("</tr>");
		}
			++i;
			buffer.append("<tr><td> "+name+"</td>");
			for(int c=0;c<x;c++){
				values[c]= values[c].replace("'", "");
			buffer.append("<td><strong>"+values[c]+"</strong></td> ");
			}
			
		}
		buffer.append("</table>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+buffer.toString()); 
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}


public String getinvesrevenueNcount(){
	try {
		Connection connection= Connection_provider.getconnection();
		String fromdate="",todate="";
		fromdate= emrForm.getFromdate();
		todate= emrForm.getTodate();
		if(fromdate==null||todate==null){
			fromdate="";
			todate="";
		}
		if(fromdate.equals("")){
			 DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			    Calendar cal = Calendar.getInstance(); 
			    fromdate = dateFormat.format(cal.getTime());
		}
		if(todate.equals("")){
			 DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			    Calendar cal = Calendar.getInstance(); 
			    todate = dateFormat.format(cal.getTime());
		}
			
		InvestigationDAO investigationDAO= new JDBCInvestigationDAO(connection);
		ArrayList<Investigation> list=new ArrayList<Investigation>();
		
		list= investigationDAO.getinvestrevenuenCountReport(fromdate, todate);
		emrForm.setInvestigationList(list);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "getinvesrevenueNcount";
}

public String updateinvstkunal(){
	try {
		Connection connection= Connection_provider.getconnection();
		String id= request.getParameter("id");
		String date= request.getParameter("date");
		String time=request.getParameter("time");
		InvestigationDAO investigationDAO= new JDBCInvestigationDAO(connection);
		Investigation investigation= new Investigation();
		investigation= investigationDAO.getEditInvestigation(id);
		 String datetime= DateTimeUtils.getCommencingDate1(date)+" "+time+":00";
		 int x= investigationDAO.updateInvstDate(investigation.getInvreq(), Integer.parseInt(investigation.getInvsttype()), datetime);
		 String chrgename=investigationDAO.getMasterChargeName(investigation.getInvsttype());
		 int y= investigationDAO.updateapmtAsssInvstDate(chrgename, investigation.getInvreq(), DateTimeUtils.getCommencingDate1(date));
		 response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}


public String multiinvsthandle(){
	String clientid=request.getParameter("clientid");
	String type=request.getParameter("databy");
	String ipdid="";
	try {
		Connection connection= Connection_provider.getconnection();
		InvestigationDAO investigationDAO= new JDBCInvestigationDAO(connection);
		ClientDAO clientDAO= new JDBCClientDAO(connection);
		ArrayList<Investigation> investigations= new ArrayList<Investigation>();
		
		if(type!=null){
			if(type.equals("ipd")){
				IpdDAO ipdDAO= new JDBCIpdDAO(connection);
				ipdid=ipdDAO.getLAstIpdIdByClient(clientid);
				if(ipdid.equals("0")){
					ipdid="";
				}
			}
		}
		investigations=investigationDAO.getAllInvestigationRepoerTypeWise(clientid,ipdid);
		session.setAttribute("multiinvestigationlist", investigations);
		Client client= new Client();
		client= clientDAO.getClientDetails(clientid);
		emrForm.setClientname(client.getTitle()+" "+client.getFullname());
		emrForm.setAgegender(client.getAge1()+" / "+client.getGender());
		emrForm.setClientId(clientid);
		emrForm.setAddr(type);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "multiinvsthandle";
}


public String savemultiinvstlist(){
	try {
		String remark="";
		Connection connection=Connection_provider.getconnection();
		InvestigationDAO investigationDAO= new JDBCInvestigationDAO(connection);
		
		for(Investigation investigation:emrForm.getMultiinvstigation()){
			if(investigation==null){
				continue;
			}
			int investigationnameid=investigation.getId();
			String reporttype= investigation.getReporttype();
			String obsval= investigation.getObsvalue();
			if(investigation.getRemark()!=null){
				remark=investigation.getRemark();
			}
			String approve=request.getParameter("approve");
			String parentid=investigation.getInvsttypeid();
			if(reporttype.equals("Numerical")||reporttype.equals("Hybreed")){
				int update = investigationDAO.updateReportData(investigationnameid,obsval,"","","","");
				int duserid  = loginInfo.getId();
				int upstatus = investigationDAO.UpdateUpStatus(parentid,duserid,approve);
				String date =DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				int res= investigationDAO.updateDateOFInvestigation(parentid,"",date,loginInfo.getUserId());
				
				}else if(reporttype.equals("Writeup")){
					int update = investigationDAO.updateWriteupData(investigationnameid,investigation.getFindings(),investigation.getBiorefrange(),investigation.getMethods());
					int duserid  = loginInfo.getId();
					int upstatus = investigationDAO.UpdateUpStatus(parentid,duserid,approve);
					String date =DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					int res= investigationDAO.updateDateOFInvestigation(parentid,"",date,loginInfo.getUserId());
				}else if(reporttype.equals("Text")){
					int update = investigationDAO.updateWriteupData(investigationnameid,investigation.getFindings(),investigation.getBiorefrange(),investigation.getMethods());
					int duserid  = loginInfo.getId();
					int upstatus = investigationDAO.UpdateUpStatus(parentid,duserid,approve);
					String date =DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					int res= investigationDAO.updateDateOFInvestigation(parentid,"",date,loginInfo.getUserId());
				}
			if(DateTimeUtils.isNull(approve).equals("1")){
				investigationDAO.updateCompStatusAndDate(parentid, DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
			}
		}
		for(Investigation investigation:emrForm.getRemarklist()){
			int rs= investigationDAO.updateRemark(""+investigation.getId(),investigation.getRemark());
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "savemultiinvstlist";
}

public String printmultiinvestlist(){
	try {
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "printmultiinvestlist";
}

public String packageinvtypechk(){
	
	String sectionid = request.getParameter("id");
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			
			ArrayList<Master>invTypeList = investigationDAO.getPackageInvTypeList(sectionid);
			
			StringBuffer str = new StringBuffer();
			String ids="";
			for(Master master : invTypeList){
				str.append("<input type='checkbox' disabled value="+master.getId()+" name='cdks' class='sshubh' onchange='selectedinv()' checked>");
				str.append("<label for='cdks'>"+master.getName()+"</label><br>");
				if(ids.equals("")){
					ids=""+master.getId();
				}else{
					ids=ids+","+master.getId();
				}
			}
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str.toString()+"#$"+ids); 
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}


public String showname1(){
	Connection connection = null;
	try{
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		atg.taglib.json.util.JSONObject jsonObject=new atg.taglib.json.util.JSONObject(data);
		String selectedid = jsonObject.getString("selectedid");
		connection = Connection_provider.getconnection();
		InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
		InvestigationMasterDAO investigationMasterDAO = new JDBCInvestigationMasterDAO(connection);
		String sttr[]=selectedid.split(",");
		StringBuffer str = new StringBuffer();
		if(sttr.length>1){
			for (String string : sttr) {
				Master master1 = investigationMasterDAO.getInvestigationType(string);
				
				ArrayList<Master>nameist = investigationDAO.getNameList(string);
				
				
				/*str.append("<select  onchange='showspecimen(this.value)' class='form-control' name='invstname' id='invstname'>");
				str.append(" <option value='0' >Select Name</option>");
				
				for(Master master : nameist){
					str.append(" <option value='"+master.getId()+"' >"+master.getName()+"</option>");
				}*/
				str.append("<input type='hidden' name='rpttype' id='rpttype' value='"+master1.getReport_type()+"'>");
				str.append("<li><input type='checkbox' id='selecctall' />Select All</li>");
				for(Master master : nameist){
					str.append("<li><input class='case'  value='"+master.getId()+"' type='checkbox' name='ch"+master.getId()+"' id='ch"+master.getId()+"'> "+master.getName()+"</li>");
				}
		       				
				str.append("</select>");
				
			}
			str.append("<input type='hidden' name='invsttype' id='invsttype' value='"+selectedid+"'>");
		}else{
			Master master1 = investigationMasterDAO.getInvestigationType(selectedid);
			
			ArrayList<Master>nameist = investigationDAO.getNameList(selectedid);
			
			
			/*str.append("<select  onchange='showspecimen(this.value)' class='form-control' name='invstname' id='invstname'>");
			str.append(" <option value='0' >Select Name</option>");
			
			for(Master master : nameist){
				str.append(" <option value='"+master.getId()+"' >"+master.getName()+"</option>");
			}*/
			str.append("<input type='hidden' name='rpttype' id='rpttype' value='"+master1.getReport_type()+"'>");
			str.append("<li><input type='checkbox' id='selecctall' />Select All</li>");
			for(Master master : nameist){
				str.append("<li><input class='case'  value='"+master.getId()+"' type='checkbox' name='ch"+master.getId()+"' id='ch"+master.getId()+"'> "+master.getName()+"</li>");
			}
			str.append("</select>");
			str.append("<input type='hidden' name='invsttype' id='invsttype' value='"+selectedid+"'>");
		}
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("obdata", str.toString());
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1); 
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return null;
}


public String addpkgtemp(){
	
	
	String invstcode = request.getParameter("invstcode");
	String invsttype = request.getParameter("invsttype");
	String invstgroup = request.getParameter("invstgroup");
	String invstname = request.getParameter("invstname");
	String specimen = request.getParameter("specimen");
	String invstreporttype = request.getParameter("invstreporttype");
	String invstUnit = request.getParameter("invstUnit");
	String normvalue = request.getParameter("normvalue");
	
	String invsttypeid=request.getParameter("invsttypeid");
	
	String clientId = request.getParameter("clientId");
	String prectionerid = request.getParameter("prectionerid");
	String conditionid = request.getParameter("conditionid");
	
	String isFromCF =request.getParameter("isFromCF");
	
//	Investigation investigation = new Investigation();
	
	String sttr[]=invsttypeid.split(",");
	if(sttr.length>1){
		ArrayList<Investigation>invstList = new ArrayList<Investigation>();
		ArrayList<Investigation>list = new ArrayList<Investigation>();
		for (String string : sttr) {
Connection connection = null;
Investigation invtypeobj =new Investigation();
			try{
				connection = Connection_provider.getconnection();
				InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			invsttypeid=string;
			invsttype=investigationDAO.getMasterChargeName(invsttypeid);
			ArrayList<Investigation>invstypelist = new ArrayList<Investigation>();
			
			ArrayList<String>invstExistypelist = new ArrayList<String>();
			if(session.getAttribute("invstypelist")!=null){
				invstypelist = (ArrayList<Investigation>) session.getAttribute("invstypelist");
				for(Investigation itype : invstypelist){
					invstExistypelist.add(itype.getInvsttype());
				}
			}
			
			invtypeobj.setInvsttype(invsttype);
			invtypeobj.setInvsttypeid(invsttypeid);
			if(invstExistypelist.size()!=0){
				if(invstExistypelist.contains(invsttype)==false){
					invstypelist.add(invtypeobj);
				}
			}else{
				invstypelist.add(invtypeobj);
			}
			
			
			
//			 Object[] st = invstypelist.toArray();
//		      for (Object s : st) {
//		        if (invstypelist.indexOf(s) != invstypelist.lastIndexOf(s)) {
//		        	invstypelist.remove(invstypelist.lastIndexOf(s));
//		         }
//		      }
		      list.addAll(invstypelist);
//			invstList=null;
			if(session.getAttribute("invstList")!=null){
				invstList = (ArrayList<Investigation>)session.getAttribute("invstList");
			}
			
			
			
			
			ArrayList<Master> nmList=investigationDAO.getNameList(string);
			Set< Integer> defaultIds=new HashSet<>();
			Set< Integer> seletdIds=new HashSet<>();
			for(Master m:nmList){
				defaultIds.add(m.getId());
			}
			
			for(String id:invstname.split(",")){
				seletdIds.add(DateTimeUtils.convertToInteger(id));
			}
			
			defaultIds.retainAll(seletdIds);
			String newNameIds="0";
			for(Integer s:defaultIds){
				newNameIds=newNameIds+","+s;
			}
			
				ArrayList<Investigation>invstnameList = investigationDAO.getInvestigationNameList(newNameIds);
				
				ArrayList<String>invstexisistingList = new ArrayList<String>();
				if(session.getAttribute("invstList")!=null){
					ArrayList<Investigation>investigationList = (ArrayList<Investigation>)session.getAttribute("invstList");
					
					for(Investigation inv : investigationList){
						invstexisistingList.add(inv.getInvstname()+"~"+inv.getInvsttypeid());
					}
				}
				
				int i = 0;
				for(Investigation investigation : invstnameList){
					investigation.setInvstcode(invstcode);
					investigation.setInvsttype(invsttype);
					investigation.setInvstgroup(invstgroup);
					investigation.setInvstreporttype(invstreporttype);
					investigation.setInvsttypeid(invsttypeid);
					
					investigation.setClientId(clientId);
					investigation.setPrectionerid(prectionerid);
					investigation.setConditionid(conditionid);
					investigation.setIndx(i);
					
					if(session.getAttribute("invstList")!=null){
						ArrayList<Investigation>investigationList = (ArrayList<Investigation>)session.getAttribute("invstList");
						boolean invr = invstexisistingList.contains(investigation.getInvstname()+"~"+investigation.getInvsttypeid());
						if(invr==false){
							invstList.add(investigation);
						}
					}else{
						invstList.add(investigation);
					}
					i++;
				}
				
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		session.setAttribute("invstypelist", list);
		session.setAttribute("invstList", invstList);
		
		getInvestigationData(DateTimeUtils.numberCheck(isFromCF));
	}else{
		
Connection connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			Investigation invtypeobj =new Investigation();
	//	Investigation investigation = new Investigation();
			invsttype=investigationDAO.getMasterChargeName(invsttypeid);
		ArrayList<Investigation>invstypelist = new ArrayList<Investigation>();
		ArrayList<String>invstExistypelist = new ArrayList<String>();
		if(session.getAttribute("invstypelist")!=null){
			invstypelist = (ArrayList<Investigation>) session.getAttribute("invstypelist");
			for(Investigation itype : invstypelist){
				invstExistypelist.add(itype.getInvsttype());
			}
		}
		
		invtypeobj.setInvsttype(invsttype);
		invtypeobj.setInvsttypeid(invsttypeid);
		if(invstExistypelist.size()!=0){
			if(invstExistypelist.contains(invsttype)==false){
				invstypelist.add(invtypeobj);
			}
		}else{
			invstypelist.add(invtypeobj);
		}
		
		

		 Object[] st = invstypelist.toArray();
	      for (Object s : st) {
	        if (invstypelist.indexOf(s) != invstypelist.lastIndexOf(s)) {
	        	invstypelist.remove(invstypelist.lastIndexOf(s));
	         }
	      }

		session.setAttribute("invstypelist", invstypelist);
		
	
		
		ArrayList<Investigation>invstList = new ArrayList<Investigation>();
		if(session.getAttribute("invstList")!=null){
			invstList = (ArrayList<Investigation>)session.getAttribute("invstList");
		}
		
		
		
			ArrayList<Investigation>invstnameList = investigationDAO.getInvestigationNameList(invstname);
			
			ArrayList<String>invstexisistingList = new ArrayList<String>();
			if(session.getAttribute("invstList")!=null){
				ArrayList<Investigation>investigationList = (ArrayList<Investigation>)session.getAttribute("invstList");
				
				for(Investigation inv : investigationList){
					invstexisistingList.add(inv.getInvstname()+"~"+inv.getInvsttypeid());
				}
			}
			
			int i = 0;
			for(Investigation investigation : invstnameList){
				investigation.setInvstcode(invstcode);
				investigation.setInvsttype(invsttype);
				investigation.setInvstgroup(invstgroup);
				investigation.setInvstreporttype(invstreporttype);
				investigation.setInvsttypeid(invsttypeid);
				
				investigation.setClientId(clientId);
				investigation.setPrectionerid(prectionerid);
				investigation.setConditionid(conditionid);
				investigation.setIndx(i);
				
				if(session.getAttribute("invstList")!=null){
					ArrayList<Investigation>investigationList = (ArrayList<Investigation>)session.getAttribute("invstList");
					boolean invr = invstexisistingList.contains(investigation.getInvstname()+"~"+investigation.getInvsttypeid());
					if(invr==false){
						invstList.add(investigation);
					}
				}else{
					invstList.add(investigation);
				}
				i++;
			}
			
			session.setAttribute("invstList", invstList);
			
			getInvestigationData(DateTimeUtils.numberCheck(isFromCF));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	
	return null;
}

public String changeInvCFval(){
	Connection connection = null;
	try{
		String val = request.getParameter("val");
		String selectedid = request.getParameter("selectedid");
		String isFromCF= request.getParameter("isFromCF");
		
		ArrayList<Investigation>invstList = (ArrayList<Investigation>)session.getAttribute("invstList");
		if(invstList.size()>0){
			selectedid=String.valueOf(Math.abs(invstList.size()-(Integer.parseInt(selectedid)+1)));
		}
		invstList.get(DateTimeUtils.convertToInteger(selectedid)).setValue_cf(val);
		session.setAttribute("invstList",invstList);
		getInvestigationData(DateTimeUtils.numberCheck(isFromCF));
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return null;
}

public String templateinv() {
	Connection connection=null;
	try {
		connection = Connection_provider.getconnection();
		InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
		String selectedid=request.getParameter("selectedid");
		String fromdate=request.getParameter("fromdate");
		String todate=request.getParameter("todate");
		String approved=request.getParameter("approved");
		Investigation find=investigationDAO.getFindingsbyid(selectedid);
		String sectionid=investigationDAO.getInvstigationSectionId(Integer.parseInt(selectedid));
		ArrayList<Master> templateList= investigationDAO.getAllInvestigationTemplateList(sectionid);
		emrForm.setInvsSectionList(templateList);
		emrForm.setTemplate_text(find.getFindings());
		emrForm.setId(find.getId());
		emrForm.setFromdate(fromdate);
		emrForm.setTodate(todate);
		emrForm.setSelectedid(Integer.parseInt(selectedid));
		emrForm.setApprove_status(approved);
		System.out.println("hello");
	} catch (Exception e) {
		e.printStackTrace();
		}
	return "edittemplt";
}


public String savefind() throws Exception {
	
	  String finding = request.getParameter("template_text");
	  String biorefrange =request.getParameter("biorefrange"); 
	  String method = request.getParameter("method");
	  String id = request.getParameter("id"); 
	  String selectedid = request.getParameter("selectedid"); 
	  String remarkwriteup=request.getParameter("commentwriteup");
	  String todate=request.getParameter("todate");
	  String approved=request.getParameter("approved");
	  if(approved.equals("")) {
		  approved="0";
	  }
	  todate=todate.split(" ")[0];
	  todate=DateTimeUtils.getCommencingDate1(todate);
	  String fromdate=request.getParameter("fromdate");
	  fromdate=DateTimeUtils.getCommencingDate1(fromdate);
	Connection connection = null;
	try{
		
		 connection = Connection_provider.getconnection(); InvestigationDAO
		 investigationDAO = new JDBCInvestigationDAO(connection);
		 
		 
		 int update = investigationDAO.updateWriteupData(Integer.parseInt(id),finding,biorefrange,method);
		
		 int rs= investigationDAO.updateRemark(selectedid,remarkwriteup);
		  
		  UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection); 
		  int duserid = loginInfo.getId(); 
		  int upstatus =investigationDAO.UpdateUpStatus(selectedid,duserid,approved);
		  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar  cal = Calendar.getInstance();
		String datetime = dateFormat.format(cal.getTime());
	    investigationDAO.updateDateOFInvestigation(selectedid, "0", datetime, "");
		
		emrForm.setFromdate(fromdate);
		emrForm.setTodate(todate);
		emrForm.setApprove_status(approved);
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
	return execute();
}
}


