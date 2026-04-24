package com.apm.Nabh.web.action;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.apm.DiaryManagement.eu.entity.Breadcrumbs;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Mis.eu.bi.MisChartDAO;
import com.apm.Mis.eu.blogic.jdbc.JDBCMisChartDAO;
import com.apm.Mis.web.form.MisChartForm;
import com.apm.Nabh.eu.bi.NabhDAO;
import com.apm.Nabh.eu.blogic.jdbc.JDBCNabhDAO;
import com.apm.Report.eu.bi.ClientReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCClientReportDAO;
import com.apm.Report.eu.entity.MisReport;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import atg.taglib.json.util.JSONObject;

public class NabhAction extends BaseAction implements ModelDriven<MisChartForm>,Preparable {
	MisChartForm misChartForm = new MisChartForm();
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession();
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	public MisChartForm getModel() {
		return misChartForm;
	}
	
	public String execute() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			ClientReportDAO clientReportDAO = new JDBCClientReportDAO(connection);
			MisChartDAO misChartDAO = new JDBCMisChartDAO(connection);
			NabhDAO nabhDAO = new JDBCNabhDAO(connection);
			ArrayList<MisReport> kpiarealist = new ArrayList<MisReport>();
			ArrayList<MisReport> kpilist = new ArrayList<MisReport>();
			ArrayList<MisReport>  accadmictrackerlist = new ArrayList<MisReport>();
			ArrayList<MisReport> subcategorylist = new ArrayList<MisReport>();
			ArrayList<MisReport> arealist = new ArrayList<MisReport>();
			ArrayList<MisReport> filesubmmissionlist = new ArrayList<MisReport>();
			ArrayList<MisReport> indicatorlist = new ArrayList<MisReport>();
			
			String action = (String)session.getAttribute("chklistaction");
			if(action==null){
				action = misChartForm.getAction();
			}else{
				session.removeAttribute("chklistaction");
			}
			
			if (action == null) {
				String sessionaction = (String)session.getAttribute("sessionaction");
				if(sessionaction!=null){
					action = sessionaction;
					session.removeAttribute("sessionaction");
				}else{
					action = "nabhdashboard";
				}
				
			}
			 String filePath = "";
			 filePath = request.getRealPath("/liveData/document/");
			
			if(action.equals("nabhdashboard")){
				ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
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
					if(breadcrumbs.getName().equals("NABH Dashboard")){
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
					breadcrumbs.setName("NABH Dashboard");
					breadcrumbs.setOn(true);
					breadcrumbs.setSqno(modulecount);
					breadcrumbs.setUrllink("Nabh?action="+action+"");
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
				}
				session.setAttribute("indentflowlist",indentflowlist);
			}else if(action.equals("kpinabh")){
				String year_filter = misChartForm.getYear_filter();
				String month_filter = misChartForm.getMonth_filter();
				String kpiarea_filter = misChartForm.getKpiarea_filter();
				if (year_filter == null) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy");
					Calendar cal = Calendar.getInstance(); 
					String todate = dateFormat.format(cal.getTime());
					
					DateFormat dateFormat1 = new SimpleDateFormat("MM");
					Calendar cal1 = Calendar.getInstance(); 
					String tomonth = dateFormat1.format(cal1.getTime());
					
					if(Integer.parseInt(tomonth)>=4){
						year_filter = todate+"-"+(Integer.parseInt(todate)+1);
					}else{
						year_filter = (Integer.parseInt(todate)-1)+"-"+todate;
					}
					//year_filter = "2017-2018";
				} else if (year_filter.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy");
					Calendar cal = Calendar.getInstance(); 
					String todate = dateFormat.format(cal.getTime());
					
					DateFormat dateFormat1 = new SimpleDateFormat("MM");
					Calendar cal1 = Calendar.getInstance(); 
					String tomonth = dateFormat1.format(cal1.getTime());
					
					if(Integer.parseInt(tomonth)>=4){
						year_filter = todate+"-"+(Integer.parseInt(todate)+1);
					}else{
						year_filter = (Integer.parseInt(todate)-1)+"-"+todate;
					}
				}

				if (month_filter == null) {
					month_filter = "01";
				} else if (month_filter.equals("")) {
					month_filter = "01";
				}
				

				if (kpiarea_filter != null) {
					if (kpiarea_filter.equals("")) {
						kpiarea_filter = "1";
					}else if(kpiarea_filter.equals("0")){
						kpiarea_filter = null;
					}
				}else{
					kpiarea_filter = "1";
				}
				
				kpiarealist = clientReportDAO.getKPIAreaList("1");
				misChartForm.setKpiarealist(kpiarealist);
				misChartForm.setIsKPI("1");
				kpilist = misChartDAO.getAllKPIList(kpiarea_filter, year_filter, month_filter);
				misChartForm.setKpiarea_filter(kpiarea_filter);
				misChartForm.setYear_filter(year_filter);
				misChartForm.setMonth_filter(month_filter);
				ArrayList<MisReport> graphicalkpilist = misChartDAO.getAllGrahicalKPIList(kpiarea_filter, year_filter, month_filter);
				session.setAttribute("graphicalkpilist", graphicalkpilist);
				misChartForm.setKpiarealist(kpiarealist);
				misChartForm.setKpilist(kpilist);
				ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
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
					if(breadcrumbs.getName().equals("Quality Indicators")){
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
					breadcrumbs.setName("Quality Indicators");
					breadcrumbs.setOn(true);
					breadcrumbs.setSqno(modulecount);
					breadcrumbs.setUrllink("Nabh?action="+action+"");
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
				}
				session.setAttribute("indentflowlist",indentflowlist);
			}else if(action.equals("accreditationtracker")){
				
				String subcategoryid = (String)session.getAttribute("chklistsubcategoryid");
				if(subcategoryid==null){
					subcategoryid = misChartForm.getSubcategoryid();
				}else{
					session.removeAttribute("chklistsubcategoryid");
				}
				
				String areaid = (String)session.getAttribute("chklistareaid");
				if(areaid==null){
					areaid = misChartForm.getAreaid();
				}else{
					session.removeAttribute("chklistaction");
				}
				
				if(subcategoryid!=null){
					if(subcategoryid.equals("")){
						subcategoryid = nabhDAO.getTopNabhSubcategory();
						areaid = null;
					}else{
						if(areaid!=null){
							if(areaid.equals("") || areaid.equals("0")){
								areaid=null;
							}
						}
					}
				}else{
					subcategoryid = nabhDAO.getTopNabhSubcategory();
					areaid = null;
				}
				if(areaid==null){
					areaid = nabhDAO.getTopAreaFromSubcategoryId(subcategoryid);
				}
				subcategorylist = misChartDAO.getNABHSubCatList("2");
				arealist = nabhDAO.getNABHAreaList(subcategoryid);
				indicatorlist =  nabhDAO.getNABHIndicatorList(areaid);
				accadmictrackerlist = nabhDAO.getAccadmicTrackerList(subcategoryid,areaid);
				misChartForm.setAccadmictrackerlist(accadmictrackerlist);
				misChartForm.setSubcategorylist(subcategorylist);
				misChartForm.setArealist(arealist);
				misChartForm.setSubcategoryid(subcategoryid);
				misChartForm.setAreaid(areaid);
				misChartForm.setIndicatorlist(indicatorlist);
				ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
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
					if(breadcrumbs.getName().equals("Compliance Tracker")){
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
					breadcrumbs.setName("Compliance Tracker");
					breadcrumbs.setOn(true);
					breadcrumbs.setSqno(modulecount);
					breadcrumbs.setUrllink("Nabh?action="+action+"");
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
				}
				session.setAttribute("indentflowlist",indentflowlist);
			}else if(action.equals("Committee")){
				
			}else if(action.equals("Meetings")){
				
			}else if(action.equals("Units")){
				
			}else if(action.equals("Tasks")){
				
			}else if(action.equals("AllResourceLiberary")){
				
			}else if(action.equals("PostersandVisuals")){
				ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
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
					if(breadcrumbs.getName().equals("Posters and Visuals")){
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
					breadcrumbs.setName("Posters and Visuals");
					breadcrumbs.setOn(true);
					breadcrumbs.setSqno(modulecount);
					breadcrumbs.setUrllink("Nabh?action="+action+"");
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
				}
				session.setAttribute("indentflowlist",indentflowlist);
				filesubmmissionlist = nabhDAO.getFileSubmissionList(action,filePath,0,"");
			}else if(action.equals("filesubmission")){
				filesubmmissionlist = nabhDAO.getFileSubmissionList(action,filePath,0,"");
				ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
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
					if(breadcrumbs.getName().equals("File Submission")){
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
					breadcrumbs.setName("File Submission");
					breadcrumbs.setOn(true);
					breadcrumbs.setSqno(modulecount);
					breadcrumbs.setUrllink("Nabh?action="+action+"");
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
				}
				session.setAttribute("indentflowlist",indentflowlist);
			}else if(action.equals("resource_forms")){
				ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
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
					if(breadcrumbs.getName().equals("Forms and SOPS")){
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
					breadcrumbs.setName("Forms and SOPS");
					breadcrumbs.setOn(true);
					breadcrumbs.setSqno(modulecount);
					breadcrumbs.setUrllink("Nabh?action="+action+"");
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
				}
				session.setAttribute("indentflowlist",indentflowlist);
				filesubmmissionlist = nabhDAO.getFileSubmissionList(action,filePath,0,"");
			}else if(action.equals("resource_qi_tools")){
				ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
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
					if(breadcrumbs.getName().equals("QI Tools")){
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
					breadcrumbs.setName("QI Tools");
					breadcrumbs.setOn(true);
					breadcrumbs.setSqno(modulecount);
					breadcrumbs.setUrllink("Nabh?action="+action+"");
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
				}
				session.setAttribute("indentflowlist",indentflowlist);
				filesubmmissionlist = nabhDAO.getFileSubmissionList(action,filePath,0,"");
			}else if(action.equals("resource_training_material")){
				ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
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
					if(breadcrumbs.getName().equals("Training Materials")){
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
					breadcrumbs.setName("Training Materials");
					breadcrumbs.setOn(true);
					breadcrumbs.setSqno(modulecount);
					breadcrumbs.setUrllink("Nabh?action="+action+"");
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
				}
				session.setAttribute("indentflowlist",indentflowlist);
				filesubmmissionlist = nabhDAO.getFileSubmissionList(action,filePath,0,"");
			}else if(action.equals("resource_video")){
				ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
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
					if(breadcrumbs.getName().equals("Videos")){
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
					breadcrumbs.setName("Videos");
					breadcrumbs.setOn(true);
					breadcrumbs.setSqno(modulecount);
					breadcrumbs.setUrllink("Nabh?action="+action+"");
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
				}
				session.setAttribute("indentflowlist",indentflowlist);
				filesubmmissionlist = nabhDAO.getFileSubmissionList(action,filePath,0,"");
			}
			
			misChartForm.setAction(action);
			misChartForm.setFilesubmmissionlist(filesubmmissionlist);
			
			if(action.equals("kpinabh")){
				return "kpinabh";
			}else if(action.equals("accreditationtracker")){
				return "accreditationtracker";
			}else if(action.equals("Committee")){
				return "committee";
			}else if(action.equals("Meetings")){
				return "meetings";
			}else if(action.equals("Units")){
				return "units";
			}else if(action.equals("Tasks")){
				return "tasks";
			}else if(action.equals("AllResourceLiberary")){
				return "allResourceLiberary";
			}else if(action.equals("PostersandVisuals")){
				return "postersandVisuals";
			}else if(action.equals("filesubmission")){
				return "filesubmission";
			}else if(action.equals("roadmapandscope")){
				ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
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
					if(breadcrumbs.getName().equals("Roadmap and Scope")){
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
					breadcrumbs.setName("Roadmap and Scope");
					breadcrumbs.setOn(true);
					breadcrumbs.setSqno(modulecount);
					breadcrumbs.setUrllink("Nabh?action="+action+"");
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
				}
				session.setAttribute("indentflowlist",indentflowlist);
			    return "roadmapandscope";
			}else if(action.equals("resource_forms")){
				return "filesubmission";
			}else if(action.equals("resource_qi_tools")){
				return "filesubmission";
			}else if(action.equals("resource_training_material")){
				return "filesubmission";
			}else if(action.equals("resource_video")){
				return "filesubmission";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return SUCCESS;
	}
	
	public String kpi() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			String action = misChartForm.getAction();
			if (action == null) {
				action = "nabhdashboard";
			}

			misChartForm.setAction(action);
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return "kpinabh";
	}
	
	
	public String savekpiresult() throws Exception {
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			MisChartDAO misChartDAO = new JDBCMisChartDAO(connection);
			NabhDAO nabhDAO = new JDBCNabhDAO(connection);
			String kpiid = request.getParameter("kpiid");
			String input1 = request.getParameter("val1");
			String input2 = request.getParameter("val2");
			String input3 = request.getParameter("val3");
			String input4 = request.getParameter("val4");
			String input5 = request.getParameter("val5");
			String indicatorid = request.getParameter("indicatorid");
			String final_result = request.getParameter("result");
			String kpi_year = request.getParameter("kpi_year");
			String kpi_month = request.getParameter("kpi_month");
			String kpi_dataid = request.getParameter("kpi_dataid");
			String target = request.getParameter("target");
			
			String srno = request.getParameter("srno");
			
			if(input1.equals("undefined")){
				input1 = null;
			}else if(input2.equals("undefined")){
				input2 = null;
			}else if(input3.equals("undefined")){
				input3 = null;
			}else if(input4.equals("undefined")){
				input4 = null;
			}else if(input5.equals("undefined")){
				input5 = null;
			}
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String todate = dateFormat.format(cal.getTime());   
			
			String userid = loginInfo.getUserId();
			
			kpi_dataid = nabhDAO.checkKPIStatus(kpiid,kpi_year,kpi_month); 
			
			if(kpi_dataid.equals("0")){
				MisReport misReport = misChartDAO.getKPIMasterData(kpiid);
				misReport.setUserid(userid);
				misReport.setDatetime(todate);
				int result = misChartDAO.insertNewKPIRecord(input1,input2,input3,input4,input5,final_result,indicatorid,misReport,kpi_year,kpi_month,target,null);
			}else{
				int result = misChartDAO.updateKPIData(input1,input2,input3,input4,input5,final_result,kpiid,todate,userid,kpi_dataid,target,null);
			}
			
			MisReport misrept = nabhDAO.getGrahicalKPI(kpi_year,indicatorid);
			
			StringBuffer buffer = new StringBuffer();
			/*buffer.append("<script>");
				buffer.append("Highcharts.chart('indicator"+srno+"', {");
				buffer.append("chart: { ");
				buffer.append("type: 'line'");
				buffer.append("},");
				buffer.append(" title: {");
				buffer.append(" text: '"+misrept.getKpi_year()+"'");
				buffer.append(" },");
				buffer.append(" xAxis: {");
				buffer.append("categories: ['Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec', 'Jan', 'Feb', 'Mar', ] ");
				buffer.append(" },");
				buffer.append(" yAxis: {");
				buffer.append(" title: {");
				buffer.append(" text: ''");
				buffer.append(" }");
				buffer.append(" },");
				buffer.append("  plotOptions: {");
				buffer.append(" line: {");
				buffer.append("dataLabels: {");
				buffer.append("  enabled: true");
				buffer.append(" },");
				buffer.append(" enableMouseTracking: true");
				buffer.append("}");
				buffer.append("},");
				buffer.append("series: [{");
				buffer.append(" name: 'Result',");
				buffer.append("data: ["+misrept.getMonth4()+","+misrept.getMonth5()+", "+misrept.getMonth6()+","+misrept.getMonth7()+", "+misrept.getMonth8()+", "+misrept.getMonth9()+", "+misrept.getMonth10()+", "+misrept.getMonth11()+", "+misrept.getMonth12()+", "+misrept.getMonth1()+", "+misrept.getMonth2()+","+misrept.getMonth3()+"]");
				buffer.append(" },{");
				buffer.append(" name: 'Target',");
				buffer.append("data: ["+misrept.getMonth4_target()+","+misrept.getMonth5_target()+", "+misrept.getMonth6_target()+", "+misrept.getMonth7_target()+", "+misrept.getMonth8_target()+", "+misrept.getMonth9_target()+", "+misrept.getMonth10_target()+", "+misrept.getMonth11_target()+", "+misrept.getMonth12_target()+", "+misrept.getMonth1_target()+", "+misrept.getMonth2_target()+", "+misrept.getMonth3_target()+"]");
				buffer.append(" }]");
				buffer.append("});");
			buffer.append("</script>");*/
			buffer.append(""+misrept.getMonth4()+","+misrept.getMonth5()+","+misrept.getMonth6()+","+misrept.getMonth7()+","+misrept.getMonth8()+","+misrept.getMonth9()+","+misrept.getMonth10()+","+misrept.getMonth11()+","+misrept.getMonth12()+","+misrept.getMonth1()+","+misrept.getMonth2()+","+misrept.getMonth3()+"");
			buffer.append("~~~");
			buffer.append(""+misrept.getMonth4_target()+","+misrept.getMonth5_target()+","+misrept.getMonth6_target()+","+misrept.getMonth7_target()+","+misrept.getMonth8_target()+","+misrept.getMonth9_target()+","+misrept.getMonth10_target()+","+misrept.getMonth11_target()+","+misrept.getMonth12_target()+","+misrept.getMonth1_target()+","+misrept.getMonth2_target()+","+misrept.getMonth3_target()+"");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+buffer.toString()+""); 	
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			connection.close();
		}
		return null;
	}
	
	
	public String getkpigraphdata() throws Exception {
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			NabhDAO nabhDAO = new JDBCNabhDAO(connection);
			String kpiid = request.getParameter("kpiid");
			String kpi_year = request.getParameter("kpi_year");
			String indicatorid = request.getParameter("indicatorid");
			String srno = request.getParameter("srno");
			
			MisReport misrept = nabhDAO.getGrahicalKPI(kpi_year,indicatorid);
			StringBuffer buffer = new StringBuffer();
			buffer.append(""+srno+"");
			buffer.append("~~~");
			buffer.append(""+kpiid+"");
			buffer.append("~~~");
			buffer.append(""+kpi_year+"");
			buffer.append("~~~");
			buffer.append(""+misrept.getMonth4()+","+misrept.getMonth5()+","+misrept.getMonth6()+","+misrept.getMonth7()+","+misrept.getMonth8()+","+misrept.getMonth9()+","+misrept.getMonth10()+","+misrept.getMonth11()+","+misrept.getMonth12()+","+misrept.getMonth1()+","+misrept.getMonth2()+","+misrept.getMonth3()+"");
			buffer.append("~~~");
			buffer.append(""+misrept.getMonth4_target()+","+misrept.getMonth5_target()+","+misrept.getMonth6_target()+","+misrept.getMonth7_target()+","+misrept.getMonth8_target()+","+misrept.getMonth9_target()+","+misrept.getMonth10_target()+","+misrept.getMonth11_target()+","+misrept.getMonth12_target()+","+misrept.getMonth1_target()+","+misrept.getMonth2_target()+","+misrept.getMonth3_target()+"");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+buffer.toString()+""); 	
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			connection.close();
		}
		return null;
	}
	
	public void prepare() throws Exception {
		
	}
	
	public String changearealist() throws Exception {
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			NabhDAO nabhDAO = new JDBCNabhDAO(connection);
			String subcategoryid = request.getParameter("subcategoryid");
			ArrayList<MisReport> arealist = nabhDAO.getNABHAreaList(subcategoryid);
			StringBuffer buffer1 = new StringBuffer();
			buffer1.append("<select class='form-control'  name='areaid' id='filter_areaid' >");  
			buffer1.append("<option value='0'>Select KPI Area</option>");
			for (MisReport misReport : arealist) {
				 	 buffer1.append("<option value='"+misReport.getAreaid()+"'>"+misReport.getArea()+"</option>");
			}
			buffer1.append("</select>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+buffer1.toString()+""); 	
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			connection.close();
		}
		return null;
	}
	
	public String savefilesubmission() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			NabhDAO nabhDAO = new JDBCNabhDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String toDate = dateFormat.format(cal.getTime());
			String filesubmission_category = misChartForm.getFilesubmission_category();
			String filesubremark = misChartForm.getFilesubremark();
			File uploadedFile = misChartForm.getSubuploadfiles();
			String filecontenttype = misChartForm.getSubuploadfilesContentType();
			String action = misChartForm.getAction();
	        String fileName ="";
	        /*if(uploadedFile.getName().length()>10){
	        	fileName=loginInfo.getClinicUserid()+"_"+toDate+"_"+uploadedFile.getName().substring(5);
	        }else{
	        	fileName=loginInfo.getClinicUserid()+"_"+toDate+"_"+uploadedFile.getName();
	        }*/
	        fileName=loginInfo.getClinicUserid()+"_"+toDate+"_"+misChartForm.getSubuploadfilesFileName();
	        String filePath = "";
	        filePath = request.getRealPath("/liveData/document/");
	        try {
	        	/*File fileToCreate = new File(filePath, fileName);*/
	        	File fileToCreate = new File(filePath, fileName);
	            FileUtils.copyFile(uploadedFile, fileToCreate);
	            String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
	            String userid = loginInfo.getUserId();
	            int res = nabhDAO.saveNabhFileSubmissionData(datetime,userid,filesubmission_category,filesubremark,fileName,filecontenttype,action,0,"","");
	            session.setAttribute("sessionaction", action);
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				connection.close();
			}
		return "redirecttoexecute";
	}
	public String downloadDoc() throws IOException{
		if(!verifyLogin(request)){
			return "login";
		}
		String fileName = "";
		
		FileInputStream in = null;
		ServletOutputStream out = null;
		HttpServletRequest servletRequest=(HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
		
		String id = request.getParameter("id");
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			NabhDAO nabhDAO = new JDBCNabhDAO(connection);
			MisReport misReport = nabhDAO.getFileSubmissionData(id);
			fileName = misReport.getFilename();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		String filepath = request.getRealPath("/liveData/document/"+fileName);
		
		 String workingDirectory = System.getProperty("user.dir");
		 //String absoluteFilePath = servletRequest.getRealPath("/liveData/document/");
		
		 File file=new File(filepath);
		 if(file.exists()){
		//return an application file instead of html page
		//response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename="+fileName+"");
	
        	 try {
				in = new FileInputStream(file);
				 out = response.getOutputStream();
	        	 
		        	byte[] outputByte = new byte[4096];
		        	//copy binary contect to output stream
		        	while(in.read(outputByte, 0, 4096) != -1)
		        	{
		        		out.write(outputByte, 0, 4096);
		        	}
		        	
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        
		 }
        	
        	in.close();
        	out.flush();
        	out.close();
        	
        	//setDocumentAjaxData(patientid,pid);
		return null;
	}

	public String deleteuploadedfile() throws Exception {
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			NabhDAO nabhDAO = new JDBCNabhDAO(connection);
			String id = request.getParameter("id");
			MisReport misReport = nabhDAO.getFileSubmissionData(id);
			String fileName = misReport.getFilename();
			String filepath = request.getRealPath("/liveData/document/"+fileName);
			File file=new File(filepath);
			if(file.delete()){
				String todate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
	            int res = nabhDAO.deleteFileUploaded(id,loginInfo.getUserId(),todate);
	        }else{
	        	String todate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
	            int res = nabhDAO.deleteFileUploaded(id,loginInfo.getUserId(),todate);
	        }
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""); 	
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			connection.close();
		}
		return null;
	}
	
	public String savechecklistdata() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			NabhDAO nabhDAO = new JDBCNabhDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String toDate = dateFormat.format(cal.getTime());
			
			int chcklistid = misChartForm.getChcklistid();
			int chkliststatus = misChartForm.getChkliststatus();
			String action = misChartForm.getAction();
			String isKPI = misChartForm.getIsKPI();
			String subcategoryid = misChartForm.getSubcategoryid();
			String areaid = misChartForm.getAreaid();
			String filesubremark = misChartForm.getFilesubremark();
			if(chkliststatus==0){
				try {
					String fileName ="";
					String subuploadfilesContentType = misChartForm.getSubuploadfilesContentType();
					String subuploadfilesFileName = misChartForm.getSubuploadfilesFileName();
					File subuploadfiles = misChartForm.getSubuploadfiles();
					if(subuploadfiles!=null){
						if(subuploadfiles.getName().length()>10){
				        	fileName=loginInfo.getClinicUserid()+"_"+toDate+"_"+subuploadfiles.getName().substring(5);
				        }else{
				        	fileName=loginInfo.getClinicUserid()+"_"+toDate+"_"+subuploadfiles.getName();
				        }
				        fileName=loginInfo.getClinicUserid()+"_"+toDate+"_"+subuploadfilesFileName;
				        String filePath = "";
				        filePath = request.getRealPath("/liveData/document/");
			        	File fileToCreate = new File(filePath, fileName);
			        	FileUtils.copyFile(subuploadfiles, fileToCreate);
					}
		        	
		        	String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		            String userid = loginInfo.getUserId();
		            int res = nabhDAO.updateNabhChecklistData(datetime,userid,chcklistid,filesubremark,fileName,subuploadfilesContentType,action,1);
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
			}else if(chkliststatus==1){
				 String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		         String userid = loginInfo.getUserId();
		         int res = nabhDAO.updateNabhCompleteChecklistData(datetime,userid,chcklistid,filesubremark);
			}
			
			session.setAttribute("chklistaction", action);
			session.setAttribute("chklistisKPI", isKPI);
			session.setAttribute("chklistsubcategoryid", subcategoryid);
			session.setAttribute("chklistareaid", areaid);
			
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				connection.close();
			}
		return "redirecttoexecute";
	}
	
	public String downloaddocchcklist() throws IOException{
		if(!verifyLogin(request)){
			return "login";
		}
		String fileName = "";
		
		FileInputStream in = null;
		ServletOutputStream out = null;
		HttpServletRequest servletRequest=(HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
		
		String id = request.getParameter("id");
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			NabhDAO nabhDAO = new JDBCNabhDAO(connection);
			MisReport misReport = nabhDAO.getChkListData(id);
			fileName = misReport.getFilename();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		String filepath = request.getRealPath("/liveData/document/"+fileName);
		
		 String workingDirectory = System.getProperty("user.dir");
		 //String absoluteFilePath = servletRequest.getRealPath("/liveData/document/");
		
		 File file=new File(filepath);
		 if(file.exists()){
		//return an application file instead of html page
		//response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename="+fileName+"");
	
        	 try {
				in = new FileInputStream(file);
				 out = response.getOutputStream();
	        	 
		        	byte[] outputByte = new byte[4096];
		        	//copy binary contect to output stream
		        	while(in.read(outputByte, 0, 4096) != -1)
		        	{
		        		out.write(outputByte, 0, 4096);
		        	}
		        	
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        
		 }
        	
        	in.close();
        	out.flush();
        	out.close();
        	
        	//setDocumentAjaxData(patientid,pid);
		return null;
	}
	
	public String addchangearealist() throws Exception {
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			NabhDAO nabhDAO = new JDBCNabhDAO(connection);
			String subcategoryid = request.getParameter("subcategoryid");
			ArrayList<MisReport> arealist = nabhDAO.getNABHAreaList(subcategoryid);
			StringBuffer buffer1 = new StringBuffer();
			buffer1.append("<select class='form-control' onchange='addchangeindicator(this.value)'  name='areaid' id='addareaid' >");  
			buffer1.append("<option value='0'>Select KPI Area</option>");
			for (MisReport misReport : arealist) {
				 	 buffer1.append("<option value='"+misReport.getAreaid()+"'>"+misReport.getArea()+"</option>");
			}
			buffer1.append("</select>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+buffer1.toString()+""); 	
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			connection.close();
		}
		return null;
	}
	
	public String addchangeindicatorlist() throws Exception {
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			NabhDAO nabhDAO = new JDBCNabhDAO(connection);
			String areaid = request.getParameter("areaid");
			ArrayList<MisReport> indicatorlist = nabhDAO.getNABHIndicatorList(areaid);
			StringBuffer buffer1 = new StringBuffer();
			buffer1.append("<select class='form-control'   name='indicator' id='addindicator' >");  
			buffer1.append("<option value='0'>Select Indicator</option>");
			for (MisReport misReport : indicatorlist) {
				 	 buffer1.append("<option value='"+misReport.getId()+"'>"+misReport.getIndicator()+"</option>");
			}
			buffer1.append("</select>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+buffer1.toString()+""); 	
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			connection.close();
		}
		return null;
	}
	
	public String savenewchecklist() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			NabhDAO nabhDAO = new JDBCNabhDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String toDate = dateFormat.format(cal.getTime());
			String areaid = misChartForm.getAreaid();
	        String indicator = misChartForm.getIndicator();
	        String remark = misChartForm.getRemark();
	        String userid = loginInfo.getUserId();
	        int res = nabhDAO.saveNabhChecklist(areaid,indicator,remark,userid,toDate);
	        session.setAttribute("sessionaction", "accreditationtracker");
	       
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				connection.close();
			}
		return "redirecttoexecute";
	}
	
	public String hisvideotutorial() throws Exception{

		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			NabhDAO nabhDAO =new JDBCNabhDAO(connection);
			String action = (String)session.getAttribute("videotutorial_action");
			if(action==null){
				action = misChartForm.getAction();
			}else{
				session.removeAttribute("videotutorial_action");
			}
			if (action == null) {
				String sessionaction = (String)session.getAttribute("videotutorialactionnew");
				if(sessionaction!=null){
					action = sessionaction;
					session.removeAttribute("videotutorialactionnew");
				}else{
					action = "Video_Training_Dash";
				}
			}
			String querysearch = misChartForm.getQuerysearch();
			querysearch = DateTimeUtils.isNull(querysearch);
			misChartForm.setQuerysearch(querysearch);
			String filePath = "";
			filePath = request.getRealPath("/liveData/document/");
			misChartForm.setAction(action);
			ArrayList<MisReport> filesubmmissionlist = new ArrayList<MisReport>();
			filesubmmissionlist = nabhDAO.getVideoTutorialFileSubmissionList(action,filePath,1,querysearch);
			misChartForm.setFilesubmmissionlist(filesubmmissionlist);
			
			ArrayList<MisReport> submoduleList = new ArrayList<MisReport>();
			submoduleList = nabhDAO.getSubmoduleList(action);
			misChartForm.setSubmoduleList(submoduleList);
			
			ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
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
				if(breadcrumbs.getName().equals("Video Tutorial")){
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
				breadcrumbs.setName("Video Tutorial");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("Nabh?action="+action+"");
				breadcrumbs.setIscurrent(true);
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return "hisvideotutorial";
	}
	
	public String savevideotutorial() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			NabhDAO nabhDAO = new JDBCNabhDAO(connection);
			
			String filesubmission_category = misChartForm.getFilesubmission_category();
			String filesubremark = misChartForm.getFilesubremark();
			File uploadedFile = misChartForm.getSubuploadfiles();
			String filecontenttype = misChartForm.getSubuploadfilesContentType();
			String action = misChartForm.getAction();
			String fileName ="";
			String filesubsubmodule = misChartForm.getFilesubsubmodule();
			String filesubserachkey = misChartForm.getFilesubserachkey();
	        fileName=misChartForm.getSubuploadfilesFileName();
	        String filePath = "";
	        filePath = request.getRealPath("/liveData/document/");
	        String doctitle = misChartForm.getFilesubtitle();
	        String docfeature = misChartForm.getFilesubfeatures();
	        try {
	        	/*File fileToCreate = new File(filePath, fileName);*/
	        	File fileToCreate = new File(filePath, fileName);
	            FileUtils.copyFile(uploadedFile, fileToCreate);
	            String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
	            String userid = loginInfo.getUserId();
	            int res = nabhDAO.saveVideoTFileSubmissionData(datetime,userid,filesubmission_category,filesubremark,fileName,filecontenttype,action,1,filesubsubmodule,filesubserachkey,doctitle,docfeature);
	            session.setAttribute("videotutorialactionnew", action);
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				connection.close();
			}
		return "redirecttovideotutorial";
	}
	
	public String savesubmodulename() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NabhDAO nabhDAO = new JDBCNabhDAO(connection);
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			String data = buffer.toString();
			JSONObject jsonObject = new JSONObject(data);
			
			String submodule = jsonObject.getString("submodule");
			String action = jsonObject.getString("action");
			
			StringBuffer builder = new StringBuffer(); 
			int res = nabhDAO.checkAlreadySavedSubModule(submodule,action);
			if(res==0){
				int result = nabhDAO.saveSubmoduleData(submodule,action);
				ArrayList<MisReport> submoduleList = new ArrayList<MisReport>();
				submoduleList = nabhDAO.getSubmoduleList(action);
				builder.append("<select id='filesubsubmodule' class='form-control'  name='filesubsubmodule' >");  
				builder.append("<option value='0'>Select Sub Module</option>");
				for(MisReport misReport:submoduleList){
					 builder.append("<option value='"+misReport.getId()+"'>"+misReport.getName()+"</option>");
				}
				builder.append("</select>");
			}
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("selecttaglist", builder.toString());
			jsonobj.put("alreadysaved", res);
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
	
	public String deletevideotutorialuploadedfile() throws Exception {
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			NabhDAO nabhDAO = new JDBCNabhDAO(connection);
			String id = request.getParameter("id");
			MisReport misReport = nabhDAO.getVideoTutorialData(id);
			String fileName = misReport.getFilename();
			String filepath = request.getRealPath("/liveData/document/"+fileName);
			File file=new File(filepath);
			if(file.delete()){
				String todate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
	            int res = nabhDAO.deleteVideoTFileUploaded(id,loginInfo.getUserId(),todate);
	        }else{
	        	String todate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
	            int res = nabhDAO.deleteVideoTFileUploaded(id,loginInfo.getUserId(),todate);
	        }
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""); 	
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			connection.close();
		}
		return null;
	}


}
