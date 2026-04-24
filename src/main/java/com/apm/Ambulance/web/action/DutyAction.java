package com.apm.Ambulance.web.action;

import java.sql.Connection;
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

import com.apm.Ambulance.eu.bi.DutyDAO;
import com.apm.Ambulance.eu.blogic.jdbc.JDBCDutyDAO;
import com.apm.Ambulance.eu.entity.Duty;
import com.apm.Ambulance.web.form.DutyForm;
import com.apm.DiaryManagement.web.form.NotAvailableSlotForm;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.SittingFollowupDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCSittingFollowupDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.SittingFollowup;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class DutyAction extends BaseAction implements ModelDriven<DutyForm>,Preparable {

	    DutyForm dutyform=new DutyForm();
	
	    HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpSession session=request.getSession();
		Pagination pagination=new Pagination(15, 1);

		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		
	
	@Override
	public String execute() throws Exception {
		Connection con=null;
		try {
			con=Connection_provider.getconnection();
			DutyDAO dutydao=new  JDBCDutyDAO(con);
			int count=dutydao.getTotalDutylist();
			pagination.setPreperties(count);
			String searchText=dutyform.getSearchText();
			String driverid=loginInfo.getUserId();
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			if(driverid!=null){
				if(driverid.equals("")){
					driverid=null;
				}
			}
			
			ArrayList<Duty>dutylist=dutydao.getDutylist(searchText,pagination,driverid);
			dutyform.setDutylist(dutylist);
			MasterDAO masterDAO = new JDBCMasterDAO(con);
			
			
			pagination.setPage_records(dutylist.size());
			dutyform.setPagerecords(String.valueOf(pagination.getPage_records()));
			dutyform.setTotalRecords(String.valueOf(count));
		} catch (Exception e) {
			e.printStackTrace();
		}
			return "duty";
		}
	
	
	/*
	public String display() throws Exception{
		
		Connection con=null;*/
	/*	try {
			con=Connection_provider.getconnection();
			DutyDAO dutydao=new  JDBCDutyDAO(con);
			int count=dutydao.getTotalDutylist();
			pagination.setPreperties(count);
			String searchText=dutyform.getSearchText();
			
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			
			
			ArrayList<Duty>dutylist=dutydao.getDutylist(searchText,pagination);
			dutyform.setDutylist(dutylist);
			pagination.setPage_records(dutylist.size());
			dutyform.setPagerecords(String.valueOf(pagination.getPage_records()));
			dutyform.setTotalRecords(String.valueOf(count));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		return "duty";
	}

*/
	public String add(){
		
		return "addduty";
	}
	
	public String save() throws Exception{
		Connection con=null;
		String currdate=request.getParameter("currdate");
		String driverid=loginInfo.getUserId();
		String firstname=loginInfo.getFirstName();
		String lastname=loginInfo.getLastName();
		try {
			con=Connection_provider.getconnection();
		    DutyDAO dutydao=new JDBCDutyDAO(con);
		    Duty duty=new Duty();
		    duty.setId(dutyform.getId());
		    duty.setPatient(dutyform.getPatient());
		    duty.setCity(dutyform.getCity());
		    duty.setPickdrop(dutyform.getPickdrop());
		    duty.setHosp(dutyform.getHosp());
		   duty.setCurrentdate(currdate);
		   duty.setDriverid(driverid);
		   duty.setFirstname(firstname);
		   duty.setLastname(lastname);
		    int result=dutydao.addDutyDB(duty);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			con.close();
		}
		return "saveduty";
	}


	@Override
	public DutyForm getModel() {
		// TODO Auto-generated method stub
		return dutyform;
	}

	public String edit()
	{
		Connection con=null;
		try {
			String selectedid=request.getParameter("selectedid");
			con=Connection_provider.getconnection();
			DutyDAO dutydao=new JDBCDutyDAO(con);
			Duty duty=dutydao.getdutyAllocation(selectedid);
			dutyform.setId(duty.getId());
			dutyform.setPatient(duty.getPatient());
			dutyform.setCity(duty.getCity());
			dutyform.setPickdrop(duty.getPickdrop());
			dutyform.setHosp(duty.getHosp());
			dutyform.setCurrentdate(duty.getCurrentdate());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editduty";
	}

	public String update() throws Exception{
		
		Connection con=null;
		String currdate=request.getParameter("currdate");
		try {
			con=Connection_provider.getconnection();
		    DutyDAO dutydao=new JDBCDutyDAO(con);
		    Duty duty=new Duty();
		    duty.setId(dutyform.getId());
		    duty.setPatient(dutyform.getPatient());
		    duty.setCity(dutyform.getCity());
		    duty.setPickdrop(dutyform.getPickdrop());
		    duty.setHosp(dutyform.getHosp());
		   duty.setCurrentdate(currdate);
		    int result=dutydao.updateDutyDB(duty);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			con.close();
		}
		return "updateduty";
	}

	public String delete(){
		
		try {
			String selectedid=request.getParameter("selectedid");
			Connection con=Connection_provider.getconnection();
			DutyDAO dutydao=new JDBCDutyDAO(con);
			int result=dutydao.deleteDuty(selectedid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "deleteduty";
	}

	public String ambulancereport(){
		
		Connection con=null;
		try {
			
			con=Connection_provider.getconnection();
			DutyDAO dutydao=new JDBCDutyDAO(con);
			String fromDate=dutyform.getFromDate();
			String toDate=dutyform.getToDate();
			if(DateTimeUtils.isNull(fromDate).equals("")){
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				fromDate = dateFormat.format(new Date());
			}else{
				fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			}
			if(DateTimeUtils.isNull(fromDate).equals("")){
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				fromDate = dateFormat.format(new Date());
			}else{
				fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			}
			
			String searchText = dutyform.getSearchText();
			
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			/*Duty duty=new Duty();
			duty.setFirstname(dutyform.getFirstname());
			duty.setLastname(dutyform.getLastname());*/
			ArrayList<Duty>ambreportlist=dutydao.getambulancereportlist(fromDate,toDate,searchText);
			dutyform.setFromDate(DateTimeUtils.getCommencingDate1(fromDate));
			dutyform.setToDate(DateTimeUtils.getCommencingDate1(toDate));
			dutyform.setSearchText(searchText);
			dutyform.setAmbreportlist(ambreportlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ambreport";
	}
	
	
public String punchkarmareport(){
		
		Connection con=null;
		try {
			
			con=Connection_provider.getconnection();
			DutyDAO dutydao=new JDBCDutyDAO(con);
			String fromDate=dutyform.getFromDate();
			String toDate=dutyform.getToDate();
			if(DateTimeUtils.isNull(fromDate).equals("")){
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				fromDate = dateFormat.format(new Date());
			}else{
				fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			}
			if(DateTimeUtils.isNull(toDate).equals("")){
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				toDate = dateFormat.format(new Date());
			}else{
				toDate = DateTimeUtils.getCommencingDate1(toDate);
			}
			String searchText = dutyform.getSearchText();
			
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			/*Duty duty=new Duty();
			duty.setFirstname(dutyform.getFirstname());
			duty.setLastname(dutyform.getLastname());*/
			ArrayList<Duty>punchkarmarptlist=dutydao.getPunchkarmaReport(fromDate,toDate,searchText);
			dutyform.setFromDate(DateTimeUtils.getCommencingDate1(fromDate));
			dutyform.setToDate(DateTimeUtils.getCommencingDate1(toDate));
			dutyform.setSearchText(searchText);
			dutyform.setPunchkarmarpt(punchkarmarptlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "punchkarmarpt";
	}
	
    public String addpunchkarma(){
	if(!verifyLogin(request)){
		return "login";
	}
	Connection con=null;
	
	try {
	con=Connection_provider.getconnection();
	MasterDAO masterDAO = new JDBCMasterDAO(con);
	ArrayList<Master> disciplineList = masterDAO.getDisciplineDataList();
	dutyform.setDisciplineList(disciplineList);
	String fromDate=dutyform.getFromDate();
	
	if(DateTimeUtils.isNull(fromDate).equals("")){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		fromDate = dateFormat.format(new Date());
	}else{
		fromDate = DateTimeUtils.getCommencingDate1(fromDate);
	}

	dutyform.setFromDate(DateTimeUtils.getCommencingDate1(fromDate));
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return "add";
  }
	public String savePunchKarmaNote() throws Exception{
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			Duty duty=new Duty();
			String parentid=dutyform.getParentid();
			String date =dutyform.getFromDate();
			
			
			duty.setTemplate_text(dutyform.getTemplate_text());
			duty.setDate_time(DateTimeUtils.getCommencingDate1(date));
			duty.setParentid(parentid);
			DutyDAO dutydao=new JDBCDutyDAO(connection);
		
			int result=dutydao.savePunchkarmanote(duty);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "savepunch";
	}
	
	public String editpunchkarmanote() throws Exception{
		
		try {
			
			String selectedid=request.getParameter("id");
			Connection con=Connection_provider.getconnection();
			SittingFollowupDAO sdao=new JDBCSittingFollowupDAO(con);
			MasterDAO masterDAO=new JDBCMasterDAO(con);
			DutyDAO dutydao=new JDBCDutyDAO(con);
			
			Duty duty=new Duty();
			
			duty=dutydao.geteditPunchkarmanote(selectedid);
			
			dutyform.setId(duty.getId());
			dutyform.setFromDate(duty.getDate_time());
			dutyform.setTemplate_text(duty.getTemplate_text());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "editpunchnote";
		
		
	}
	
	public String updatePunchKarmaNote() throws Exception{
		

		 try {
			
			Duty duty=new Duty();
			
			duty.setId(dutyform.getId());
			duty.setDate_time(dutyform.getFromDate());
			duty.setTemplate_text(dutyform.getTemplate_text());
			
			Connection con=Connection_provider.getconnection();
			DutyDAO dutydao=new JDBCDutyDAO(con);
			
			int result=dutydao.updatePunchkarmaNote(duty);
			 
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "updatepunchkarma";
		
	}
	
	public String deletepunchkarmanote() throws Exception{
		
		try {
			
			String selectedid=request.getParameter("id");
			Connection con=Connection_provider.getconnection();
			DutyDAO dutydao=new JDBCDutyDAO(con);
			
			int result=dutydao.deletepunchkarmaData(selectedid);
			
		   } catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "deletepunchkarma";
		
	}
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
