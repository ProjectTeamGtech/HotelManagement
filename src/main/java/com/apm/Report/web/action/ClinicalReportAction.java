package com.apm.Report.web.action;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.allcolor.yahp.converter.CYaHPConverter;
import org.allcolor.yahp.converter.IHtmlToPdfTransformer;
import org.apache.struts2.ServletActionContext;

import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.web.action.EmbeddedImageEmailUtil;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Registration.web.form.ClinicRegistrationForm;
import com.apm.Report.eu.bi.ChargesReportDAO;
import com.apm.Report.eu.bi.ClinicalReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCChargesReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCClinicalReportDAO;
import com.apm.Report.eu.entity.SummaryReport;
import com.apm.Report.web.form.ChargesReportForm;
import com.apm.Report.web.form.ClinicalReportForm;
import com.apm.TreatmentEpisode.eu.bi.TreatmentEpisodeDAO;
import com.apm.TreatmentEpisode.eu.blogic.jdbc.JDBCTreatmentEpisode;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class ClinicalReportAction extends BaseAction implements Preparable, ModelDriven<ClinicalReportForm> {
	ClinicalReportForm clinicalReportForm = new ClinicalReportForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	public String treatmentEpisodeList() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		String fromDate = clinicalReportForm.getFromDate();
		String toDate = clinicalReportForm.getToDate();
		
		if(clinicalReportForm.getOrderby().equals("")){
			clinicalReportForm.setOrderby("lastmodified");
		}
		
		if(fromDate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -7); 
			fromDate = dateFormat.format(cal.getTime());
			clinicalReportForm.setFromDate(fromDate);
		}
		if(toDate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			//cal.add(Calendar.DATE, -7); 
			toDate = dateFormat.format(cal.getTime());
			clinicalReportForm.setToDate(toDate);
		}
		
		session.setAttribute("fromDate", fromDate);
		session.setAttribute("toDate", toDate);
		
		if(!fromDate.equals("")){
			String temp[]= fromDate.split("/");
			fromDate = temp[2]+"-"+temp[1]+"-"+temp[0];
		}
		if(!toDate.equals("")){
			String temp1[]= toDate.split("/");
			toDate = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
		}
		Connection connection = null;
	try{
		
		String  practitionerId = clinicalReportForm.getDiaryUser();
		connection = Connection_provider.getconnection();
		
		
		ArrayList<TreatmentEpisode>treatmentEpisodeList = new ArrayList<TreatmentEpisode>();
		ClinicalReportDAO clinicalReportDAO = new JDBCClinicalReportDAO(connection);
		ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      Calendar cal = Calendar.getInstance();
	      String date = dateFormat.format(cal.getTime());  
		
		int result = chargesReportDAO.saveMisReportLog("Treatment Episode List",loginInfo.getUserId(),fromDate,toDate,date,"treatmentEpisodeList");
		
		
		treatmentEpisodeList = clinicalReportDAO.getTreatmentEpisodeList(practitionerId,fromDate,toDate,clinicalReportForm.getOrderby(),clinicalReportForm.getOrder());
		
		session.setAttribute("treatmentEpisodeList", treatmentEpisodeList);
		
		clinicalReportForm.setTreatmentEpisodeList(treatmentEpisodeList);
	}
	catch(Exception e){
		e.printStackTrace();
	}finally {
		connection.close();
	}
		return "treatmentEpisodeList";
	}
	
	public String treatmentEpisodePreview() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		String fromDate = clinicalReportForm.getFromDate();
		String toDate = clinicalReportForm.getToDate();
		
		session.setAttribute("fromDate", fromDate);
		session.setAttribute("toDate", toDate);
		
		if(!fromDate.equals("")){
			String temp[]= fromDate.split("/");
			fromDate = temp[2]+"-"+temp[1]+"-"+temp[0];
		}
		if(!toDate.equals("")){
			String temp1[]= toDate.split("/");
			toDate = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
		}
		Connection connection = null;
	try{
	
		String  practitionerId = request.getParameter("diaryUser");
		connection = Connection_provider.getconnection();
		ArrayList<TreatmentEpisode>treatmentEpisodeList = new ArrayList<TreatmentEpisode>();
		ClinicalReportDAO clinicalReportDAO = new JDBCClinicalReportDAO(connection);
		treatmentEpisodeList = (ArrayList<TreatmentEpisode>)session.getAttribute("treatmentEpisodeList");
			
		StringBuffer str = new StringBuffer();
		
		String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
		String temp[] = currentDate.split(" ");
		String temptime[] = currentDate.split("at");
		String temp1[] = temp[0].split("-");
		String date = temp1[0] + "-" + temp1[1] + "-" + temp1[2];
		String time = temptime[1];
		
		str.append("<div style='font-size: 15px; font-weight: bold;'> Treatment Episode List </div>");
		str.append("<div style='font-size: 10px; font-weight: bold;'> Date: "+date+" </div>");
		str.append("<div style='font-size: 10px; font-weight: bold;'> Time: "+time+" </div>");
		str.append("<br>");
		
		
		str.append("<table cellpadding='0' cellspacing='0' style='width: 100%;font-size: 10px;'>");
		
		str.append("<thead>");
		str.append("<tr>");
			
		str.append("<th>Sr.No.</th> ");
		str.append("<th>Client No</th> ");
		str.append("<th>Client Name</th> ");
		str.append("<th>Practitioner Name</th> ");
		str.append("<th>Treatment Episode Name</th> ");
		str.append("<th>Pay By</th> ");
		str.append("<th>Sessions</th> ");
		str.append("<th>Start Date</th> ");
		str.append("<th>Invoicee</th> ");
		str.append("<th>Used Session</th> ");
				
		str.append("</tr>");
		str.append("</thead>");
		str.append("<tbody>");
		int srno = 1;
		for(TreatmentEpisode treatmentEpisode:treatmentEpisodeList){
		str.append("<tr class= 'bg-info'>");
		str.append("<td>"+srno+"</td>");
		str.append("<td>"+treatmentEpisode.getClientId()+"</td>");
		str.append("<td>"+treatmentEpisode.getClientName()+"</td>");
		str.append("<td>"+treatmentEpisode.getDiaryUser()+"</td>");
		str.append("<td>"+treatmentEpisode.getTreatmentEpisodeName()+"</td>");
		str.append("<td>"+treatmentEpisode.getPayby()+"</td>");
		str.append("<td>"+treatmentEpisode.getSessions()+"</td>");
		str.append("<td>"+treatmentEpisode.getTreatmentStartDate()+"</td>");
		str.append("<td>"+treatmentEpisode.getInvoicee()+"</td>");
		str.append("<td>"+treatmentEpisode.getUsedSession()+"</td>");
		
		str.append("</tr>");	
		srno++;
		
		}
		
		str.append("</table><br><br>");			
		
		String filePath = request.getRealPath("/liveData/TreatmentEpisodeReport/");
//		System.out.println(filePath);
		String filename = "TreatmentEpisode_"+date+".pdf";
		//String filename ="commission.pdf";
		
		htmlToPdfFile(str.toString(), filePath, filename);	
		
		session.setAttribute("treatmentEpisodeFileName", filePath+"/"+filename);
		
//		System.out.println("PDF Created..");
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+filename+""); 
		
		
	}catch(Exception e){
		e.printStackTrace();
	}finally {
		connection.close();
	}
	
		return null;
	}
	
	
	public void htmlToPdfFile(String htmlString, String filepath,
			String fileaName) throws Exception {	

		CYaHPConverter converter = new CYaHPConverter();
		File fout = new File(filepath + "/" + fileaName);
		FileOutputStream out = new FileOutputStream(fout);
		Map properties = new HashMap();
		ArrayList headerFooterList = new ArrayList();

		properties.put(IHtmlToPdfTransformer.PDF_RENDERER_CLASS,IHtmlToPdfTransformer.FLYINGSAUCER_PDF_RENDERER);
		// properties.put(IHtmlToPdfTransformer.FOP_TTF_FONT_PATH, fontPath);
		converter.convertToPdf(htmlString, IHtmlToPdfTransformer.A4L,(java.util.List) headerFooterList, "file:///temp/", out, properties);
		out.flush();
		out.close();
	}
	
	public String sendEmailTreatmentEpisode() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
		connection = Connection_provider.getconnection();
		
		String to = request.getParameter("to");
		String cc = request.getParameter("cc");
		String subject = request.getParameter("subject");
		String notes = request.getParameter("emailBody");
		String file = request.getParameter("file");	
		
		String filename = (String)session.getAttribute("treatmentEpisodeFileName");		
		
		//int result = emailTemplateDAO.saveEmailLogDetails(to, cc, subject, notes, date1,time);
		
		String type = "Email";
		int appointmentid = 0;
		int invoiceid = 0;
		EmailLetterLog emailLetterLog = new EmailLetterLog();
		emailLetterLog.setAppointmentid(appointmentid);
		emailLetterLog.setInvoiceid(invoiceid);
		emailLetterLog.setType(type);
		
		EmbeddedImageEmailUtil.sendMailAttachment(connection,loginInfo.getId(),to, cc, subject, notes,filename,loginInfo,emailLetterLog,"0");
		
		
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return null;
	}
	
	public String DtrReportForm(){
		
		return "dtrreportform";
		
	}
	
	
	public ClinicalReportForm getModel() {
		
		return clinicalReportForm;
	}


	public void prepare() throws Exception {
		Connection connection = null;
		connection = Connection_provider.getconnection();
		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);		
		ArrayList<DiaryManagement>userList = notAvailableSlotDAO.getUserList(loginInfo.getId());		
		clinicalReportForm.setUserList(userList); 
		
		
	}

}
