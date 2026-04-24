package com.apm.DiaryManagement.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.DiaryManagement.eu.bi.AppointmentDiaryReportDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCAppointmentDiaryReportDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.AppointmentDiaryReport;
import com.apm.DiaryManagement.web.form.AppointmentDiaryReportForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class AppointmentDiaryReportAction  extends BaseAction implements Preparable, ModelDriven<AppointmentDiaryReportForm> {

	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	AppointmentDiaryReportForm appointmentDiaryReportForm = new AppointmentDiaryReportForm();
	
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
		
		//show practice manager
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AppointmentDiaryReportDAO appointmentDiaryReportDAO = new JDBCAppointmentDiaryReportDAO(connection);
			
			ArrayList<AppointmentDiaryReport> diaryUserList = appointmentDiaryReportDAO.getDiaryUserList(loginInfo.getId());
			appointmentDiaryReportForm.setDiaryUserList(diaryUserList);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
	 
		return SUCCESS;
	}

public String show() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	Connection connection = null;
	/*String diaryUserId = request.getParameter("diaryUserId");
	String fromDate = request.getParameter("fromDate");
	String toDate = request.getParameter("toDate");*/
	
	
	String diaryUserId = appointmentDiaryReportForm.getDiaryUser();
	String fromDate = appointmentDiaryReportForm.getFromDate();
	String toDate = appointmentDiaryReportForm.getToDate();
	if(diaryUserId==null || diaryUserId == ""){
		diaryUserId = appointmentDiaryReportForm.getDiaryUser1();
		appointmentDiaryReportForm.setDiaryUser(diaryUserId);
	}
	if(fromDate==null || fromDate== ""){
		fromDate = appointmentDiaryReportForm.getFromDate1();
		appointmentDiaryReportForm.setFromDate(fromDate);
	}
	if(toDate==null || toDate == ""){
		toDate = appointmentDiaryReportForm.getToDate1();
		appointmentDiaryReportForm.setToDate(toDate);
	}
	appointmentDiaryReportForm.setDiaryUser1(diaryUserId);
	appointmentDiaryReportForm.setFromDate1(fromDate);
	appointmentDiaryReportForm.setToDate1(toDate);
	String tempFromDate[] = fromDate.split("/");
	fromDate = tempFromDate[2] + "-" + tempFromDate[1] + "-" + tempFromDate[0];
	
	String tempToDate[] = toDate.split("/");
	toDate = tempToDate[2] + "-" + tempToDate[1] + "-" + tempToDate[0];
	
	
	try{
		
		connection = Connection_provider.getconnection();
		AppointmentDiaryReportDAO appointmentDiaryReportDAO = new JDBCAppointmentDiaryReportDAO(connection);
		int totalCount = appointmentDiaryReportDAO.getTotalApmtReportCount(diaryUserId,fromDate,toDate);
		pagination.setPreperties(totalCount);
		ArrayList<AppointmentDiaryReport> diaryUserList = appointmentDiaryReportDAO.getDiaryUserList(loginInfo.getId());
		appointmentDiaryReportForm.setDiaryUserList(diaryUserList);
		
		ArrayList<AppointmentDiaryReport> apmtDiaryReportList = appointmentDiaryReportDAO.getDiaryReportList(diaryUserId,fromDate,toDate,pagination);
		pagination.setPage_records(apmtDiaryReportList.size());
		appointmentDiaryReportForm.setTotalRecords(totalCount);
		appointmentDiaryReportForm.setPagerecords(Integer.toString(pagination.getPage_records()));
		appointmentDiaryReportForm.setApmtDiaryReportList(apmtDiaryReportList);
		
	
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	finally{
		
		connection.close();
	}
	
	return SUCCESS;
}
	
public String reportPreview() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	String diaryUserId = request.getParameter("diaryUserId");
	String fromDate = request.getParameter("fromDate");
	String toDate = request.getParameter("toDate");
	
	String tempFromDate[] = fromDate.split("/");
	fromDate = tempFromDate[2] + "-" + tempFromDate[1] + "-" + tempFromDate[0];
	
	String tempToDate[] = toDate.split("/");
	toDate = tempToDate[2] + "-" + tempToDate[1] + "-" + tempToDate[0];
	
	Connection connection = null;
	try{
		
		connection = Connection_provider.getconnection();
		AppointmentDiaryReportDAO appointmentDiaryReportDAO = new JDBCAppointmentDiaryReportDAO(connection);
		ArrayList<AppointmentDiaryReport> apmtDiaryReportList = appointmentDiaryReportDAO.getDiaryReportList(diaryUserId,fromDate,toDate,pagination);
		appointmentDiaryReportForm.setApmtDiaryReportList(apmtDiaryReportList);
	
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	finally{
		
		connection.close();
	}
	return "reportPreview";
}

public String walkInAndPreBooked() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	Connection connection = null;
try{
		
		connection = Connection_provider.getconnection();
		AppointmentDiaryReportDAO appointmentDiaryReportDAO = new JDBCAppointmentDiaryReportDAO(connection);
		ArrayList<AppointmentDiaryReport> diaryUserList = appointmentDiaryReportDAO.getDiaryUserList(loginInfo.getId());
		appointmentDiaryReportForm.setDiaryUserList(diaryUserList);
		
		ArrayList<AppointmentDiaryReport>deptList = appointmentDiaryReportDAO.getDepartmentList();
		appointmentDiaryReportForm.setDeptList(deptList);
		
		
		
		ArrayList<AppointmentDiaryReport>allWalkInPreBookedList = appointmentDiaryReportDAO.getAllWalkInPreBookedList();
		appointmentDiaryReportForm.setAllWalkInPreBookedList(allWalkInPreBookedList);
		
		
		
		
		
	}
	catch (Exception e) {
		e.printStackTrace();
	}finally{
		
		connection.close();
	}
	
	
	return "walkInAndPreBooked";
	
}

public String showWalkInAndPreBooked() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
	AppointmentDiaryReport appointmentDiaryReport = new AppointmentDiaryReport();
	ArrayList<AppointmentDiaryReport>allWalkInPreBookedList = new ArrayList<AppointmentDiaryReport>();
	AppointmentDiaryReportDAO appointmentDiaryReportDAO = new JDBCAppointmentDiaryReportDAO(connection);
	String date = appointmentDiaryReportForm.getDate();
	String tempDate[] = date.split("/");
	date = tempDate[2] + "-" + tempDate[1] + "-" + tempDate[0];
	
	String diaryUserId = appointmentDiaryReportForm.getDiaryUser();
	String dept = appointmentDiaryReportForm.getDept();
	
	
	
	for(String str : appointmentDiaryReportForm.getStatus()){
		
		if(str.equals(Constants.ARRIVED)){
			appointmentDiaryReport.setArrived(true);
		}else if(str.equals(Constants.BEINGSEEN)){
			appointmentDiaryReport.setBeingSeen(true);
		}else if(str.equals(Constants.COMPLETED)){
			appointmentDiaryReport.setCompleted(true);
		}else if(str.equals(Constants.DNA)){
			appointmentDiaryReport.setDoNotAttend(true);
		}
		
		}
	
	boolean arrived = appointmentDiaryReport.isArrived();
	boolean beingSeen = appointmentDiaryReport.isBeingSeen();
	boolean completed = appointmentDiaryReport.isCompleted();
	boolean dna = appointmentDiaryReport.isDoNotAttend();
	
	
		
		
			allWalkInPreBookedList = appointmentDiaryReportDAO.getAllBookedList(date,diaryUserId,dept,arrived,beingSeen,completed,dna);
			appointmentDiaryReportForm.setAllWalkInPreBookedList(allWalkInPreBookedList);
			
			ArrayList<AppointmentDiaryReport> diaryUserList = appointmentDiaryReportDAO.getDiaryUserList(loginInfo.getId());
			appointmentDiaryReportForm.setDiaryUserList(diaryUserList);
			
			ArrayList<AppointmentDiaryReport>deptList = appointmentDiaryReportDAO.getDepartmentList();
			appointmentDiaryReportForm.setDeptList(deptList);
			
			appointmentDiaryReportForm.setArrived(arrived);
			appointmentDiaryReportForm.setBeingSeen(beingSeen);
			appointmentDiaryReportForm.setCompleted(completed);
			appointmentDiaryReportForm.setDoNotAttend(dna);
		
		
		
	}
	catch (Exception e) {
		// TODO: handle exception
	}
	
	finally{
		connection.close();
	}
	
	return "walkInAndPreBooked";
	
}
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	public AppointmentDiaryReportForm getModel() {
		// TODO Auto-generated method stub
		return appointmentDiaryReportForm;
	}
	
}