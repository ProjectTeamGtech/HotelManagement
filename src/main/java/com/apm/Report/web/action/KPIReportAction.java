package com.apm.Report.web.action;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Master.eu.bi.CityMasterDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCCityMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.CityMaster;
import com.apm.Master.eu.entity.Master;
import com.apm.Report.eu.bi.ClientReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCClientReportDAO;
import com.apm.Report.eu.entity.MisReport;
import com.apm.Report.eu.entity.Report;
import com.apm.Report.web.form.ReportForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class KPIReportAction extends BaseAction implements ModelDriven<ReportForm> ,Preparable {

	
	
	ReportForm reportForm= new ReportForm();
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	public ReportForm getModel() {
		return reportForm;
	}
	
	
	public String execute() throws Exception {

		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			ClientReportDAO clientReportDAO= new JDBCClientReportDAO(connection);
			String fromdate =reportForm.getFromDate();
			String todate= reportForm.getToDate();
			if(fromdate!=null){
				
				if(fromdate.equals("")){
					fromdate= DateTimeUtils.getCommencingDate1(fromdate);
				} else {
					SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar=Calendar.getInstance();
					calendar.add(Calendar.DATE, -30);
					fromdate = dateFormat.format(calendar.getTime());
				}
				
			} else {
				SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar=Calendar.getInstance();
				calendar.add(Calendar.DATE, -30);
				fromdate = dateFormat.format(calendar.getTime());
			}
			if(todate!=null){
				
				if(todate.equals("")){
					todate= DateTimeUtils.getCommencingDate1(todate);
				} else {
					SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar=Calendar.getInstance();
					todate = dateFormat.format(calendar.getTime());
				}
				
			} else {
				SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar=Calendar.getInstance();
				todate = dateFormat.format(calendar.getTime());
			}
			
			
			double ipdInitialAssesmentPer= clientReportDAO.getIpdInitialAssesmentPer();
			reportForm.setIpdInitialAssesmentPer(DateTimeUtils.changeFormat(ipdInitialAssesmentPer));
			
			double emergencyAssesmentPer =clientReportDAO.getCasualityAssesmentPer(fromdate,todate);
			reportForm.setEmergencyAssesmentPer(DateTimeUtils.changeFormat(emergencyAssesmentPer));
			
			double nursingPlanDocument = clientReportDAO.getNursingCarePlanDocument(fromdate,todate);
			reportForm.setNursingPlanDocument(DateTimeUtils.changeFormat(nursingPlanDocument));
			
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return super.execute();
	}
	public String kpidashboard() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ClientReportDAO clientReportDAO = new JDBCClientReportDAO(connection);
			/*ArrayList<MisReport> arealist = clientReportDAO.getKPIAreaList();
			reportForm.setArealist(arealist);*/
			String areaid = reportForm.getAreaid();
			String catagoryid = reportForm.getNabhcatalistid();
			String subcatagorylist = reportForm.getNabhsubcatalistid();
			if(areaid==null){
				areaid ="0";
			}if(areaid.equals("")){
				areaid = "0";
			}
			if(catagoryid==null){
				catagoryid ="0";
			}if(catagoryid.equals("")){
				catagoryid = "0";
			}
			if(subcatagorylist==null){
				subcatagorylist ="0";
			}if(subcatagorylist.equals("")){
				subcatagorylist = "0";
			}
			ArrayList<Report> indicatorlist = clientReportDAO.getKPIIndicatorList(areaid,catagoryid,subcatagorylist);
			reportForm.setIndicatorlist(indicatorlist);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return "kpidashboard";
	}
	
	public String addindicatortokpi()throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ClientReportDAO clientReportDAO = new JDBCClientReportDAO(connection);
			ArrayList<String>  arrayList = clientReportDAO.getKPIIndicatorId();
			String id = request.getParameter("allindicatorid");
			for (String s : id.split(",")) {
				if (s.equals("0")) {
					continue;
				}
				Report report = clientReportDAO.getMasterIndicator(s);
				int res1 = clientReportDAO.getIsKPIAlreadyExist(report);
				if(res1==0){
					int res = clientReportDAO.saveKPIData(report);
				}
				int result = clientReportDAO.updateIndicatorMasterData(s,"1");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			connection.close();
		}
		return "addindicatortokpi";
	}
	
	public String updateindicatormaster()throws Exception{
		 Connection connection = null;
			try {
				connection = Connection_provider.getconnection();
				ClientReportDAO clientReportDAO = new JDBCClientReportDAO(connection);
				StringBuilder builder = new StringBuilder();
				String s = request.getParameter("val");
				String indicator = request.getParameter("indicator");
				int result = clientReportDAO.updateIndicatorMasterData(s,"0");
				builder.append("<label class='checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select'><input id='checki"+s+"' type='checkbox' value='"+s+"' class='akash'><i></i>"+indicator+"</label>");
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+builder.toString()+""); 
		  } catch (Exception e) {
			  e.printStackTrace();
		  }finally{
			  connection.close();
		  }
		  return null;
	}

	public void prepare() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ClientReportDAO clientReportDAO = new JDBCClientReportDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<MisReport> arealist = clientReportDAO.getKPIAreaList();
			ArrayList<Master> nabhcatagorylist = masterDAO.getNABHCatagoryList(null, null);
			ArrayList<Master> nabhsubcatagorylist = masterDAO.getNABHSubCatagoryList(null,null);
			reportForm.setNabhcatagorylist(nabhcatagorylist);
			reportForm.setNabhsubcatagorylist(nabhsubcatagorylist);
			reportForm.setArealist(arealist);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}
	}
	
	public String addIndicator(){
		return "addIndicator";	
	}
	
	public String saveIndicator() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			ClientReportDAO clientReportDAO = new JDBCClientReportDAO(connection);
			String areaid = reportForm.getAreaid();
			String indicator = reportForm.getIndicator();
			String formula = reportForm.getFormula();
			String formula_desc = reportForm.getFormula_desc();
			String no_of_input = reportForm.getNo_of_input();
			int result = clientReportDAO.saveIndicator(areaid,indicator,formula,formula_desc,no_of_input);
			//int result = cityMasterDAO.addcityDB(cityMaster);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "saveIndicator";
	}
public String getnabhsubcatagory()throws Exception{
	Connection connection=null;
	  try {
		  connection=Connection_provider.getconnection();
		  MasterDAO masterDAO = new JDBCMasterDAO(connection);
		  String id = request.getParameter("id");
		  ArrayList<Master> list = masterDAO.getNabhSubCatList(id);
		  StringBuffer buffer=new StringBuffer();
		  buffer.append("<select id='nabhsubcatalistid' name='nabhsubcatalistid' class='form-control chosen-select' >");
		  buffer.append("<option value='0'>Select Sub Catagory</option>");
		  for(Master product:list){
			  buffer.append("<option value='"+product.getId()+"'>"+product.getName()+"</option>");
		  }
		  
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
	
	
}
