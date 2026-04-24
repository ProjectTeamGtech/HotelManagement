package com.apm.DiaryManagement.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.collection.SynchronizedCollection;
import org.apache.struts2.ServletActionContext;

import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.bi.FinderDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCCompleteAptmDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCFinderDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.web.form.NotAvailableSlotForm;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryVendorDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
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
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class AdjustPatientData  extends BaseAction implements ModelDriven<NotAvailableSlotForm>
	{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	NotAvailableSlotForm notAvailableSlotForm = new NotAvailableSlotForm();
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);

	private Pagination pagination = new Pagination(5, 1);
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	
	
		@Override
		public NotAvailableSlotForm getModel() {
			// TODO Auto-generated method stub
			return notAvailableSlotForm;
		}
		

	public String execute() throws Exception {
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			FinderDAO finderDAO=new JDBCFinderDAO(connection);
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
			ArrayList<Master> disciplineList = masterDAO.getDisciplineDataListWithChecked();
			notAvailableSlotForm.setDisciplineList(disciplineList);
			
			ArrayList<DiaryManagement>userList= new ArrayList<>();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			if(loginInfo.getShow_dept_opd_list().equals("0")){
				UserProfile userProfile=userProfileDAO.getUserprofileDetails(loginInfo.getId());
				String department=userProfile.getDiciplineName();
				notAvailableSlotForm.setDept(department);
				notAvailableSlotForm.setPatienttype("1");
			}else{
				notAvailableSlotForm.setPatienttype("0");
			}
			if(loginInfo.isKalmegha()){
				userList=notAvailableSlotDAO.getUserListwithdepartment(loginInfo.getId(),null);
			}
			notAvailableSlotForm.setUserList(userList);
			int patientcount=DateTimeUtils.convertToInteger(request.getParameter("count"));
			String type=DateTimeUtils.isNull(notAvailableSlotForm.getPatienttype());
			
			String patientAgeType = DateTimeUtils.isNull(notAvailableSlotForm.getPatientAgeType());
			
			ArrayList<Client> fakepatientlist=null;
			
			String fromDate = notAvailableSlotForm.getFromDate();
			String toDate= notAvailableSlotForm.getToDate();
			String drId = notAvailableSlotForm.getDrId();
			
			String currentDate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			String nextDate = currentDate;
			if(type.equals("")){
				type="0";
			}
			if(type.equals("1")){
				String date ="";
				if(DateTimeUtils.isNull(notAvailableSlotForm.getCurrentdate()).equals("")){
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					date = dateFormat.format(cal.getTime());
					currentDate = DateTimeUtils.getCommencingDate1(date);
				}else{
					currentDate=DateTimeUtils.getCommencingDate1(notAvailableSlotForm.getCurrentdate());
				}
				//int preDeptId = finderDAO.getPreDeptId(notAvailableSlotForm.getDept(),date);
				nextDate = currentDate;
				fakepatientlist=finderDAO.getOldDepartmentList(patientcount,type,notAvailableSlotForm.getDept(),date,0,currentDate);
				
			}else{
				if(loginInfo.isKalmegha()){
					if(DateTimeUtils.isNull(fromDate).equals("")){
						DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						Calendar cal = Calendar.getInstance();
						fromDate = dateFormat.format(cal.getTime());
						fromDate = DateTimeUtils.getCommencingDate1(fromDate);
					}else{
						fromDate = DateTimeUtils.getCommencingDate1(fromDate);
					}
					if(DateTimeUtils.isNull(toDate).equals("")){
						DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						Calendar cal = Calendar.getInstance();
						toDate = dateFormat.format(cal.getTime());
						toDate = DateTimeUtils.getCommencingDate1(toDate);
					}else{
						toDate = DateTimeUtils.getCommencingDate1(toDate);
					}
					currentDate = fromDate;
					nextDate = toDate;
				}
				drId = DateTimeUtils.numberCheck(drId);
				fakepatientlist=finderDAO.getFakePatientData(patientcount,type,loginInfo.getLoginsessionid(),patientAgeType,loginInfo.isKalmegha(),fromDate,drId,nextDate,0);
			}
			notAvailableSlotForm.setFakepatientlist(fakepatientlist);
			if(fakepatientlist.size()>0){
				notAvailableSlotForm.setSelectedids(fakepatientlist.get(fakepatientlist.size()-1).getSelectedids());
			}
			InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
			ArrayList<Master> stateList= vendorDAO.getAllStateList();
			ArrayList<Master> cityList= vendorDAO.getAllCityList();
			
			notAvailableSlotForm.setStatelist(stateList);
			notAvailableSlotForm.setCitylist(cityList);
			ArrayList<String> initialList = clientDAO.getInitialList();
			notAvailableSlotForm.setInitialList(initialList);
			notAvailableSlotForm.setDept(notAvailableSlotForm.getDept());
			notAvailableSlotForm.setPatientAgeType(patientAgeType);
			
			ArrayList<Master> deptWiseCountList = masterDAO.getDepartmentWisePatientCount(currentDate,nextDate,"",loginInfo);
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
			notAvailableSlotForm.setFinalTotalPatientCount(finalTotalPatientCount);
			
			notAvailableSlotForm.setCurrentDate(DateTimeUtils.getCommencingDate1(currentDate));
			notAvailableSlotForm.setCurrentdate(DateTimeUtils.getCommencingDate1(currentDate));
			notAvailableSlotForm.setFromDate(DateTimeUtils.getCommencingDate1(currentDate));
			notAvailableSlotForm.setToDate(DateTimeUtils.getCommencingDate1(nextDate));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String movepatient() {
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			String ids=notAvailableSlotForm.getSelectedids();
			String tmp[] = null;
			if(ids.length()>0){
				tmp=ids.split(",");
			}
			FinderDAO finderDAO=new JDBCFinderDAO(connection);
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			
			for (String id : tmp) {
				Client client=new Client();
				client.setDob(request.getParameter("dob"+id));
				/*client.setTitle(request.getParameter("title"+id));*/
				/*client.setFullname(request.getParameter("fullname"+id));*/
				client.setFirstName(request.getParameter("firstName"+id));
				client.setLastName(request.getParameter("lastName"+id));
				client.setAddress(request.getParameter("address"+id));
				client.setMobNo(request.getParameter("mobNo"+id));
				client.setTitle(request.getParameter("title"+id));
				client.setState(request.getParameter("county"+id));
				client.setCity(request.getParameter("city"+id));
				client.setGender(request.getParameter("gender"+id));
				client.setClientId(id);
				client.setApmtStatus("1");
				client.setType(notAvailableSlotForm.getPatienttype());
				
				
				int updatestatus=finderDAO.updatePatientTypeAndDob(client);
				
			}
			
			ArrayList<Client> fakepatientlist=finderDAO.getMoveFakePatientData(ids);
			RegistrationAction registration = new RegistrationAction();
			ThirdPartyDAO thirdPartyDAO=new JDBCThirdPartyDAO(connection);
			String department=notAvailableSlotForm.getDept();
			String invoicetype="10";
			String paymode="Cash";
			String paidamount="0";
			String discountamt="0";
//			double grandtotal=DateTimeUtils.convertToDouble(paidamount)+DateTimeUtils.convertToDouble(discountamt);
			String disctype="0";
			String opdregchargeamount=paidamount;
			String consultationcharge="0";
			String othercharge="0";
			double grandtotal=DateTimeUtils.convertToDouble(opdregchargeamount)+DateTimeUtils.convertToDouble(consultationcharge)+DateTimeUtils.convertToDouble(othercharge);
			int newpatient =1;
			String currentDate = notAvailableSlotForm.getCurrentDate();
			
			if(DateTimeUtils.isNull(currentDate).equals("")){
				currentDate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			}
			currentDate = DateTimeUtils.getCommencingDate1(currentDate);
			for (Client client : fakepatientlist) {
				
				if(loginInfo.isKalmegha()){
					currentDate = client.getApmtDate();
					department = client.getDrnameId();
				}
				
				String abrivation=finderDAO.generateAbrivationId(loginInfo,currentDate);
				
				String lastmodified = currentDate +" "+ DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
				
				int previousDate = 0;
				if(!currentDate.equals(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0])){
					previousDate =1;
				}
				client.setRegDate(lastmodified);
				client.setLastModified(lastmodified);
				client.setLastModifiedDate(lastmodified);
				
				//client.setLastModified(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
				
				client.setAbrivationid(abrivation);
				client.setFakestatus("1");
				if(client.getMiddlename()==null){
					client.setMiddlename("");
				}
				int activeplanid=0;
				String actplan="";
				if(loginInfo.isPhysio()){
					//activeplanid=(int) session.getAttribute("activeplanid");
					//actplan=Integer.toString(activeplanid);
				}
				
				ThirdParty thirdParty=thirdPartyDAO.getThirdPartyDetails(""+loginInfo.getPatient_category());
				client.setTypeName(""+loginInfo.getPatient_category());
				client.setType(""+thirdParty.getThirdPartyId());
				client.setPatientcategory(clientDAO.getThirdPartyCompanyName(client.getTypeName()));
				client.setFromdate("");
				client.setPhysioipd("0");
				int res=clientDAO.savePatient(client, client.getId(),loginInfo);
				int updatestatus=finderDAO.updatePatientTransferSts(client.getId(),previousDate);
				
				String appointment=registration.saveappointmentwithDept(department,""+res,newpatient,newpatient,
						DateTimeUtils.convertToDouble(opdregchargeamount),previousDate,currentDate,activeplanid,1);
				int appointmentid=DateTimeUtils.convertToInteger(appointment);
				int up=finderDAO.updatefakestatus(appointment,previousDate,0);
				registration.cashdeskdepartmentdirect(appointmentid, invoicetype, paymode, ""+grandtotal, discountamt, paidamount, connection, disctype, opdregchargeamount, consultationcharge, othercharge,"");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "movepatient";
	}
	public String referdoctorclinical() {
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			ArrayList<Master> disciplineList = masterDAO.getDisciplineDataListWithChecked();
			notAvailableSlotForm.setDisciplineList(disciplineList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "referdoctorclinical";
	}
	public String referdepartmentclinical() {
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			FinderDAO finderDAO= new JDBCFinderDAO(connection);
			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
			/*ArrayList<Master> disciplineList = masterDAO.getDisciplineDataList();*/
			ArrayList<Master> disciplineList = new ArrayList<>();
			
			String dicipline = loginInfo.getDicipline();
			dicipline = DateTimeUtils.numberCheck(dicipline);
			disciplineList = masterDAO.getDisciplineWithChecked(dicipline);
			notAvailableSlotForm.setDisciplineList(disciplineList);
			
			UserProfile userProfile=userProfileDAO.getUserprofileDetails(loginInfo.getId());
			String department=userProfile.getDiciplineName();
			ArrayList<DiaryManagement>userList=notAvailableSlotDAO.getUserListwithdepartment(loginInfo.getId(),department);
			notAvailableSlotForm.setUserList(userList);
			
			String currentDate =  notAvailableSlotForm.getFromDate();
			
			if(DateTimeUtils.isNull(currentDate).equals("")){
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				currentDate = dateFormat.format(cal.getTime());
				notAvailableSlotForm.setDate(currentDate);
			}
			notAvailableSlotForm.setFromDate(currentDate);
			currentDate = DateTimeUtils.getCommencingDate1(currentDate);
//			date="2021-11-09";
			notAvailableSlotForm.setUserdepartment(userProfileDAO.getSpeciality(""+loginInfo.getId()));
			
			ArrayList<Master> deptWiseCountList = masterDAO.getDepartmentWisePatientCount(currentDate,currentDate,"",loginInfo);
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
			notAvailableSlotForm.setFinalTotalPatientCount(finalTotalPatientCount);
			
			
			
			ArrayList<NotAvailableSlot> departmentOPDList=finderDAO.fakedepartmentOPDList(department, currentDate, null, "", "desc");
			notAvailableSlotForm.setDepartmentOPDList(departmentOPDList);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "referdepartmentclinical";
		
	}
	
	public String opdconfirmfake() throws SQLException {

		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
			FinderDAO finderDAO=new JDBCFinderDAO(connection);
			RegistrationAction registration = new RegistrationAction();
			ClientAction client=new ClientAction();
			String diaryuserid= request.getParameter("diaryuser");
			String selectedids= request.getParameter("aptid");
			String apmidarray[] = null;
			if(!DateTimeUtils.isNull(selectedids).equals("")){
				apmidarray=selectedids.split(",");
			}
			String seconady=request.getParameter("secondary");
			String temp[]=seconady.split(","); //for new table secondary_dr
			for (String aptid : apmidarray) {
				
			String clientid=finderDAO.getclientidbyODMRid(aptid);
			NotAvailableSlot notavailableslot=notAvailableSlotDAO.getDepartmentAvailableSlotdata(DateTimeUtils.convertToInteger(aptid));
			String newappointmentid=client.savedirectappointment(diaryuserid, clientid,notavailableslot.getDept(),notavailableslot.getPreDate(),notavailableslot.getCommencing(),notavailableslot.getReferredfromdept(),notavailableslot.getId());
			
			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
			
			
			String dateTime = "";
			if(notavailableslot.getPreDate()==1){
				DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				String time = dateFormat.format(cal.getTime());
				dateTime = notavailableslot.getCommencing()+" "+time;
			}else{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				dateTime = dateFormat.format(cal.getTime());
			}
			
			if(!seconady.equals("") && seconady!=null) {
				finderDAO.insertinsideopd_secondary_dr(aptid,newappointmentid,seconady,dateTime,clientid);
				//save secondary dr into new secondary table
				for(String dr:temp){
					finderDAO.insertinnew_secondary_dr(aptid,newappointmentid,dr,dateTime,clientid);
				}
			}
			String diaryusername=userProfileDAO.getUserFullNameFromId(diaryuserid);
			int upddept=finderDAO.updatePatientDepartment(clientid,notavailableslot.getDept());
			int res=notAvailableSlotDAO.updatediaryuser(aptid,diaryuserid,diaryusername);
			}
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
	
	public String referdept() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
			String dept= request.getParameter("dept");
			String sts= request.getParameter("sts");
			String referremark=request.getParameter("referremark");
			NotAvailableSlot notavailableslot=new NotAvailableSlot();
			FinderDAO finderDAO=new JDBCFinderDAO(connection);
			String selectedids= request.getParameter("aptid");
			String apmidarray[] = null;
			if(!DateTimeUtils.isNull(selectedids).equals("")){
				apmidarray=selectedids.split(",");
			}
			for (String aptid : apmidarray) {
				
			String clientid=finderDAO.getclientidbyODMRid(aptid);
			
			notavailableslot=notAvailableSlotDAO.getDepartmentAvailableSlotdata(DateTimeUtils.convertToInteger(aptid));
			StringBuffer str = new StringBuffer();
			ClientAction clientAction=new ClientAction();
			RegistrationAction action=new RegistrationAction();
			int up=notAvailableSlotDAO.settrefertodepartment(aptid,dept);
				String appointment=clientAction.saveappointmentwithDept(dept, clientid,2,notavailableslot.getPreDate(),notavailableslot.getCommencing());
				int res=notAvailableSlotDAO.updateDeparttmentrefferdfrom(appointment,DateTimeUtils.isNull(notavailableslot.getDept()));
				int temp=finderDAO.updatedepartmentfaketransfer(appointment);
				int upt=finderDAO.updatefakestatus(appointment,notavailableslot.getPreDate(),0);
				int updaatepatienttype=notAvailableSlotDAO.updatePatientStatus(appointment,notavailableslot.getNewpatient());
				//	int temp=notAvailableSlotDAO.updaterefferremark(appointment,referremark);
			}
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
	
public String oldreferdept() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
			String dept= request.getParameter("dept");
			String sts= request.getParameter("sts");
			String referremark=request.getParameter("referremark");
			
			String patienttype = request.getParameter("patienttype");
			String currentDate = request.getParameter("currentDate");
			
			patienttype = DateTimeUtils.isNull(patienttype);
			
			NotAvailableSlot notavailableslot=new NotAvailableSlot();
			FinderDAO finderDAO=new JDBCFinderDAO(connection);
			String selectedids= request.getParameter("aptid");
			String apmidarray[] = null;
			if(!DateTimeUtils.isNull(selectedids).equals("")){
				apmidarray=selectedids.split(",");
			}
			//String currentDate = notAvailableSlotForm.getCurrentDate();
			
			if(DateTimeUtils.isNull(currentDate).equals("")){
				currentDate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			}
			currentDate = DateTimeUtils.getCommencingDate1(currentDate);
			
			int previousDate = 0;
			if(!currentDate.equals(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0])){
				previousDate =1;
			}
			
			for (String aptid : apmidarray) {
				
				int preDeptId = DateTimeUtils.convertToInteger(aptid);
				
				String clientid=finderDAO.getclientidbyODMRid(aptid);
				RegistrationAction action=new RegistrationAction();
				notavailableslot=notAvailableSlotDAO.getDepartmentAvailableSlotdata(DateTimeUtils.convertToInteger(aptid));
				
				int up=notAvailableSlotDAO.settrefertodepartment(aptid,dept);
				int newpatient =2;
				
				String department=dept;
				String invoicetype="10";
				String paymode="Cash";
				String paidamount="0";
				String discountamt="0";
				double grandtotal=DateTimeUtils.convertToDouble(paidamount)+DateTimeUtils.convertToDouble(discountamt);
				String disctype="0";
				String opdregchargeamount=paidamount;
				String consultationcharge="0";
				String othercharge="0";
				double opdregcharge = DateTimeUtils.convertToDouble(opdregchargeamount);
				/*if(opdregcharge>0){
					int year = Calendar.getInstance().get(Calendar.YEAR);
					int res = clientDAO.updateRegChargeApplied(year,DateTimeUtils.convertToInteger(clientid));
				}*/
				
				int activeplanid=0;
				String actplan="";
				if(loginInfo.isPhysio()){
					//activeplanid=(int) session.getAttribute("activeplanid");
					//actplan=Integer.toString(activeplanid);
				}
				String appointment=action.saveappointmentwithDept(dept, clientid,1,newpatient,opdregcharge,previousDate,currentDate,activeplanid,1);
				int appointmentid=DateTimeUtils.convertToInteger(appointment);
				action.cashdeskdepartmentdirect(appointmentid, invoicetype, paymode, ""+grandtotal, discountamt, paidamount, connection, disctype, opdregchargeamount, consultationcharge, othercharge,"");
				//int res=notAvailableSlotDAO.updateDeparttmentrefferdfrom(appointment,DateTimeUtils.isNull(notavailableslot.getDept()));
				int temp=finderDAO.updatedepartmentfaketransfer(appointment);
				int upt=finderDAO.updatefakestatus(appointment,previousDate,preDeptId);
				
				//update followup used
				finderDAO.updateDepartmentFollowUpDone(preDeptId);
				//	int temp=notAvailableSlotDAO.updaterefferremark(appointment,referremark);
			}
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

/*public String getfullname()throws Exception {
	
	try {
		Connection connection=Connection_provider.getconnection();
		
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		String fname= request.getParameter("fullname");
		String id = request.getParameter("globalid");

	   int updateFullName = clientDAO.updateFullName(fname , id);
	   Client client = new Client();
	   String str = client.getFullname();
		   
		  
		   
		   response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(fname);  
		

	   
		
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return null;
}*/
public String changefollowupdate() throws Exception {
	
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		FinderDAO finderDAO = new JDBCFinderDAO(connection);
		String ids= request.getParameter("ids");
		String followUpDate= request.getParameter("followUpDate");
		
		for (String aptid : ids.split(",")) {
			if(aptid.equals("0")){
				continue;
			}
			finderDAO.updateDepartmentfollowupdate(aptid, DateTimeUtils.getCommencingDate1(followUpDate));
		}
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
public String referdepartmentcount() throws Exception{
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		FinderDAO finderDAO = new JDBCFinderDAO(connection);
		connection=Connection_provider.getconnection();
		UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
		MasterDAO masterDAO=new JDBCMasterDAO(connection);
		NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
		/*ArrayList<Master> disciplineList = masterDAO.getDisciplineDataList();*/
		ArrayList<Master> disciplineList = new ArrayList<>();
		
		String dicipline = loginInfo.getDicipline();
		dicipline = DateTimeUtils.numberCheck(dicipline);
		disciplineList = masterDAO.getDisciplineWithChecked(dicipline);
		notAvailableSlotForm.setDisciplineList(disciplineList);
		
		UserProfile userProfile=userProfileDAO.getUserprofileDetails(loginInfo.getId());
		String department=userProfile.getDiciplineName();
		ArrayList<DiaryManagement>userList=notAvailableSlotDAO.getUserListwithdepartment(loginInfo.getId(),department);
		notAvailableSlotForm.setUserList(userList);
		
		String fromDate =  notAvailableSlotForm.getFromDate();
		String toDate=notAvailableSlotForm.getToDate();
		
		
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

		//currentDate = DateTimeUtils.getCommencingDate1(currentDate);
//		date="2021-11-09";
		notAvailableSlotForm.setUserdepartment(userProfileDAO.getSpeciality(""+loginInfo.getId()));
		
		//ArrayList<Master> deptWiseCountList = masterDAO.getDepartmentWisePatientCount(currentDate,currentDate);
		int finalNewPatientCount=0;
		int finalOldPatientCount=0;
		int finalTotalPatientCount=0;
		//for amravati
		ArrayList<Master> maindeptcountlist=masterDAO.getReferDepartmentwisePatientCount(fromDate,toDate,dicipline,loginInfo);
		notAvailableSlotForm.setFromDate(DateTimeUtils.getCommencingDate1(fromDate));
		notAvailableSlotForm.setToDate(DateTimeUtils.getCommencingDate1(toDate));
		/*
		 * if(deptWiseCountList.size()>0){ finalNewPatientCount =
		 * deptWiseCountList.get(deptWiseCountList.size()-1).getFinalNewPatientCount();
		 * finalOldPatientCount =
		 * deptWiseCountList.get(deptWiseCountList.size()-1).getFinalOldPatientCount();
		 * finalTotalPatientCount =
		 * deptWiseCountList.get(deptWiseCountList.size()-1).getFinalTotalPatientCount()
		 * ; }
		 */
		notAvailableSlotForm.setDeptWiseCountList(maindeptcountlist);
		notAvailableSlotForm.setFinalNewPatientCount(finalNewPatientCount);
		notAvailableSlotForm.setFinalOldPatientCount(finalOldPatientCount);
		notAvailableSlotForm.setFinalTotalPatientCount(finalTotalPatientCount);
		
		
		
		/*
		 * ArrayList<NotAvailableSlot>
		 * departmentOPDList=finderDAO.fakedepartmentOPDList(department, currentDate,
		 * null, "", "desc");
		 * notAvailableSlotForm.setDepartmentOPDList(departmentOPDList);
		 */
		
		
	} catch (Exception e) {
		e.printStackTrace();	
	}

    return "referdepartmentcount";
}


 public String deptwisecount() {
	 Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			String fromDate = notAvailableSlotForm.getFromDate();
			String toDate = DateTimeUtils.isNull(notAvailableSlotForm.getToDate());
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			ArrayList<Master> disciplineList =  new ArrayList<Master>();
			if(fromDate.equals("")){
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				//cal.add(Calendar.DATE, -7); 
				fromDate = dateFormat.format(cal.getTime());
				notAvailableSlotForm.setFromDate(fromDate);
			}
			if(toDate.equals("")){
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				//cal.add(Calendar.DATE, -7); 
				toDate = dateFormat.format(cal.getTime());
				notAvailableSlotForm.setToDate(toDate);
			}
			
			if(!fromDate.equals("")){
				String temp[]= fromDate.split("/");
				fromDate = temp[2]+"-"+temp[1]+"-"+temp[0];
			}
			if(!toDate.equals("")){
				String temp1[]= toDate.split("/");
				toDate = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
			}
			String deptid="";
			if ( loginInfo.getSuperadminid()==1 || loginInfo.getJobTitle().equals("Admin") ) {
				deptid ="";
			}else {
				deptid = masterDAO.getDiscriptionidbyuserid(loginInfo.getUserId());
			}
			//String deptid = masterDAO.getDiscriptionidbyuserid(loginInfo.getUserId());  //commented beacuse it showing departwise showing
			
			
			ArrayList<Master> deptWiseCountList = masterDAO.getDepartmentWisePatientCount2(fromDate,toDate,loginInfo,DateTimeUtils.isNull(deptid));
		
			
			String deptname=masterDAO.getdeptnameByDeptId(deptid);
			/*
			 * notAvailableSlotForm.setDiciplineName(deptname); int
			 * arvnew=masterDAO.getArvpatientcount(deptid,fromDate,toDate,"1","ARV Charity"
			 * ); int
			 * arvold=masterDAO.getArvpatientcount(deptid,fromDate,toDate,"2","ARV Charity"
			 * ); int
			 * newpatient=masterDAO.getArvpatientcount(deptid,fromDate,toDate,"1",""); int
			 * oldpatient=masterDAO.getArvpatientcount(deptid,fromDate,toDate,"2","");
			 * 
			 * int total=arvnew+arvold+newpatient+oldpatient;
			 * 
			 * notAvailableSlotForm.setArvnew(arvnew);
			 * notAvailableSlotForm.setArvold(arvold);
			 * notAvailableSlotForm.setNewpatient(newpatient);
			 * notAvailableSlotForm.setOldpatient(oldpatient);
			 * notAvailableSlotForm.setTotalcount(total);
			 */
			notAvailableSlotForm.setDeptWiseCountList(deptWiseCountList);
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
	return "deptwisecount";
  }
}