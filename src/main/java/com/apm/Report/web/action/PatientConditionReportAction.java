package com.apm.Report.web.action;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Diagnosis.eu.bi.DiagnosisDAO;
import com.apm.Diagnosis.eu.blogic.jdbc.JDBCDiagnosisDAO;
import com.apm.Diagnosis.eu.entity.Diagnosis;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.TreatmentTypeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCTreatmentTypeDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.Report.eu.bi.ChargesReportDAO;
import com.apm.Report.eu.bi.ClientReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCChargesReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCClientReportDAO;
import com.apm.Report.eu.entity.SummaryReport;
import com.apm.Report.web.form.ClientReportForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class PatientConditionReportAction extends BaseAction implements ModelDriven<ClientReportForm>,Preparable{

	ClientReportForm clientReportForm=new ClientReportForm();
	
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession(true);
	LoginInfo loginInfo=LoginHelper.getLoginInfo(request);
	Pagination pagination=new Pagination(25,1);
	
	
	
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public ClientReportForm getModel() {
		return clientReportForm;
	}
	
	public String execute() throws Exception {
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			ClientReportDAO clientReportDAO=new JDBCClientReportDAO(connection);
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			String condition1=clientReportForm.getCondition1();
			String condition2=clientReportForm.getCondition2();
			String condition3=clientReportForm.getCondition3();
			String type=clientReportForm.getType();
			String fromdate=clientReportForm.getFromDate();
			String todate=clientReportForm.getToDate();
			String city = clientReportForm.getCity();
			if(type!=null){
				  if(type.equals("")){
					  
					  type="all";
				  }
			} else {
				type="all";
			}
			
			if(city==null){
				city="0";
			}else if(city.equals("")){
				city="0";
			}
			
			if(fromdate!=null){
				
				if(fromdate.equals("")){
					
					fromdate=null;
				}
			} else {
				
				fromdate=null;
			}
			
			if(todate!=null){
				
				if(todate.equals("")){
					
					todate=null;
				}
			} else {
				
				todate=null;
			}
			
			if(condition1!=null){
				
				if(condition1.equals("0")){
					
					condition1=null;
				}
			}
			
			if(condition2!=null){
				
				if(condition2.equals("0")){
					
					condition2=null;
				}
			}
			
			if(condition3!=null){
				
				if(condition3.equals("0")){
					
					condition3=null;
				}
			}
			
			
			if(fromdate==null){
				
				Calendar calendar=Calendar.getInstance();
				calendar.add(Calendar.DATE, -7);
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
				fromdate=dateFormat.format(calendar.getTime());
			    
			} else {
				fromdate=DateTimeUtils.getCommencingDate1(fromdate);
			}
			
			if(todate==null){
				 String datefro=DateTimeUtils.getUKCurrentDataTime(loginInfo.getCountry());
			     todate=datefro.split(" ")[0];
			} else {
				todate=DateTimeUtils.getCommencingDate1(todate);
			}
			
			ArrayList<SummaryReport> ipdConditionList=new ArrayList<SummaryReport>();
			ArrayList<SummaryReport> opdConditionList=new ArrayList<SummaryReport>();
			int ipdcount=0;
			int opdcount=0;
			int totalsize=0;
			
			
			

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		      Calendar cal = Calendar.getInstance();
		      String date = dateFormat.format(cal.getTime());  
			
			int result = chargesReportDAO.saveMisReportLog("Patient Condition List",loginInfo.getUserId(),fromdate,todate,date,"execute");
			if(type.equals("IPD")) {
				
			    ipdcount=clientReportDAO.getIpdConditionCount(condition1,condition2,condition3,fromdate,todate,city);
				pagination.setPreperties(ipdcount);
				ipdConditionList=clientReportDAO.getIpdConditionList(condition1,condition2,condition3,fromdate,todate,pagination,city);
				totalsize=ipdConditionList.size();
				pagination.setPage_records(ipdConditionList.size());
				
			} else if(type.equals("OPD")) {
				 
				opdcount=clientReportDAO.getOpdConditionCount(condition1,condition2,condition3,fromdate,todate,city);
				pagination.setPreperties(opdcount);
				opdConditionList=clientReportDAO.getOpdConditionList(condition1,condition2,condition3,fromdate,todate,pagination,city);
				totalsize=opdConditionList.size();
				pagination.setPage_records(totalsize);
			}
			else {
				ipdcount=clientReportDAO.getIpdConditionCount(condition1,condition2,condition3,fromdate,todate,city);
				opdcount=clientReportDAO.getOpdConditionCount(condition1,condition2,condition3,fromdate,todate,city);
				pagination.setPreperties(ipdcount+opdcount);
				ipdConditionList=clientReportDAO.getIpdConditionList(condition1,condition2,condition3,fromdate,todate,pagination,city);
				opdConditionList=clientReportDAO.getOpdConditionList(condition1,condition2,condition3,fromdate,todate,pagination,city);
				totalsize=ipdConditionList.size()+opdConditionList.size();
				pagination.setPage_records(totalsize);
			}
			
			clientReportForm.setTotalRecords(totalsize);
			clientReportForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			clientReportForm.setIpdConditionList(ipdConditionList);
			clientReportForm.setOpdConditionList(opdConditionList);
			
			if(fromdate!=null){
				fromdate=DateTimeUtils.getCommencingDate1(fromdate);
			}
			if(todate!=null){
				todate=DateTimeUtils.getCommencingDate1(todate);
			}
			clientReportForm.setFromDate(fromdate);
			clientReportForm.setToDate(todate);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		return super.execute();
	}

	
	public void prepare() throws Exception {

		Connection connection=null;
		
		 try {
			connection=Connection_provider.getconnection();
			TreatmentTypeDAO treatmentTypeDAO=new JDBCTreatmentTypeDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<TreatmentType> conditionList=treatmentTypeDAO.getConditionList();
			clientReportForm.setConditionList(conditionList);
			ArrayList<Master> citylist = masterDAO.getAllCityList();
			clientReportForm.setCitylist(citylist);
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
	}

}
