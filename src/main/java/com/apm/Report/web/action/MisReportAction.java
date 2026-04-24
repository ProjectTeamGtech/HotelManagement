package com.apm.Report.web.action;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;

import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.web.action.EmbeddedImageEmailUtil;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Report.eu.bi.MisDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCMisDAO;
import com.apm.Report.eu.entity.MisReport;
import com.apm.Report.web.form.MisReportForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class MisReportAction extends BaseAction implements ModelDriven<MisReportForm> {
	
	MisReportForm misReportForm = new MisReportForm();
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	public String execute() throws Exception {
		
//		System.out.println("hello");
		
		/*String filePath = request.getRealPath("//liveData//invoiceData//misreport.xls");
		
		session.setAttribute("misreportfile", filePath);*/
		
		if(!misReportForm.getFromDate().equals("") && !misReportForm.getToDate().equals("")){
			
		
		
		//FileOutputStream fileOut = new FileOutputStream(filePath);
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet worksheet = workbook.createSheet("Mis Sheet");
		
		Row row = worksheet.createRow((short) 0);
	    Cell cell = row.createCell((short) 1);
	    cell.setCellValue("AQP Physiotherapy MIS Reports (From : "+misReportForm.getFromDate()+" To : "+misReportForm.getToDate()+")");
	    HSSFFont font1 = workbook.createFont();
		font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font1.setFontHeightInPoints((short)14);
		
		HSSFCellStyle cellStyle1 = workbook.createCellStyle();
		cellStyle1.setFont(font1);
		/*cellStyle1.setBorderBottom((short)2);
		cellStyle1.setBorderLeft((short)2);
		cellStyle1.setBorderRight((short)2);*/
		cell.setCellStyle(cellStyle1);

	  /*  worksheet.addMergedRegion(new CellRangeAddress(
	            1, //first row (0-based)
	            1, //last row  (0-based)
	            1, //first column (0-based)
	            1  //last column  (0-based)
	    ));*/
		
		//set width
	    
	    ArrayList<String>list = new ArrayList<String>();
		list.add("Provider Name");
		list.add("Invoice No");
		list.add("Treatment Location");
		list.add("NHS Number");
		list.add("Age");
		list.add("Patients Gender");
		list.add("Practice Code");
		list.add("Commissioner CCG");
		list.add("Sub CCD");
		list.add("If Other has been selected for Sub CCD, please give details");
		list.add("Source of Referral");
		list.add("Referral Received Date");
		list.add("Urgency");
		list.add("Assessment Date");
		list.add("Referral to Assessment (Days)");
		list.add("Practitioner Type");
		list.add("Appointment Date");
		list.add("Appt Time");
		list.add("Practitioner Surname");
		list.add("Treatment Outcome");
		list.add("Discharge Reason");
		list.add("Tariff");
		list.add("Proposed Charge ("+Constants.getCurrency(loginInfo)+")");
		
		misReportForm.setHeaderList(list);
		session.setAttribute("headerList", list);
		
		
		worksheet.setColumnWidth(1, 30*256);
		worksheet.setColumnWidth(2, 30*256);
		worksheet.setColumnWidth(3, 30*256);
		worksheet.setColumnWidth(4, 10*256);
		worksheet.setColumnWidth(5, 10*256);
		worksheet.setColumnWidth(6, 20*256);
		worksheet.setColumnWidth(7, 30*256);
		worksheet.setColumnWidth(8, 20*256);
		worksheet.setColumnWidth(9, 10*256);
		worksheet.setColumnWidth(10, 30*256);
		worksheet.setColumnWidth(11, 20*256);
		worksheet.setColumnWidth(12, 15*256);
		worksheet.setColumnWidth(13, 15*256);
		worksheet.setColumnWidth(14, 15*256);
		worksheet.setColumnWidth(15, 20*256);
		worksheet.setColumnWidth(16, 20*256);
		worksheet.setColumnWidth(17, 15*256);
		worksheet.setColumnWidth(18, 15*256);
		worksheet.setColumnWidth(19, 20*256);
		worksheet.setColumnWidth(20, 20*256);
		worksheet.setColumnWidth(21, 20*256);
		worksheet.setColumnWidth(22, 20*256);
		worksheet.setColumnWidth(23, 20*256);
		
	
		HSSFFont font= workbook.createFont();
		HSSFRow row1 = worksheet.createRow((short) 2);
		
		HSSFCell cellA1 = row1.createCell((short) 0);
		cellA1.setCellValue("Report 1");
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellA1.setCellStyle(cellStyle);
		
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints((short)14);
		HSSFCell cellB1 = row1.createCell((short) 1);
		cellB1.setCellValue("Monthly Activity Data");
		cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellB1.setCellStyle(cellStyle);
		
		
		
		
		//creating heading
		HSSFRow row2 = worksheet.createRow((short) 4);
		
		int i=1;
		HSSFFont font2= workbook.createFont();
		for(String s : list){
			HSSFCell cellA2 = row2.createCell((short) i);
			cellA2.setCellValue(s);
			
			font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font2.setFontHeightInPoints((short)10);
			HSSFCellStyle cellStyle2 = workbook.createCellStyle();
			cellStyle2.setFont(font2);
			/*cellStyle2.setBorderBottom((short)2);
			cellStyle2.setBorderLeft((short)2);
			cellStyle2.setBorderRight((short)2);*/
			cellA2.setCellStyle(cellStyle2);
			
			i++;
		}
		
		
		
		
		//creating heading details
		String fromDate = DateTimeUtils.getCommencingDate(misReportForm.getFromDate());
		String toDate = DateTimeUtils.getCommencingDate(misReportForm.getToDate());
		Connection connection = Connection_provider.getconnection();
		MisDAO misDAO = new JDBCMisDAO(connection);
		ArrayList<MisReport>mislist = misDAO.getMisRecord(fromDate,toDate,loginInfo);
		misReportForm.setMislist(mislist);
		
		int p = 4;
		int j = 0;
		for(j=0;j<mislist.size();j++){
			p++;
			row2 = worksheet.createRow((short) p);
			int k=1;
			 font2= workbook.createFont();
			for(int z=0;z<23;z++){
				MisReport misReport = mislist.get(j);
				HSSFCell cellA2 = row2.createCell((short) k);
				if(k==1){
					cellA2.setCellValue(misReport.getClinicName());
				}
				if(k==2){
					cellA2.setCellValue(misReport.getInvoiceno());
				}
				if(k==3){
					cellA2.setCellValue(misReport.getLocation());
				}
				if(k==4){
					cellA2.setCellValue(misReport.getNhsNumber());
				}
				if(k==5){
					cellA2.setCellValue(misReport.getAge());
				}
				if(k==6){
					cellA2.setCellValue(misReport.getGender());
				}
				if(k==7){
					cellA2.setCellValue(misReport.getPracticeCode());
				}
				if(k==8){
					cellA2.setCellValue(misReport.getCommissionerCCG());
				}
				if(k==9){
					cellA2.setCellValue(misReport.getSubCCD());
				}
				if(k==10){
					cellA2.setCellValue(misReport.getOtherSubCCD());
				}
				if(k==11){
					cellA2.setCellValue(misReport.getSourceofRefferal());
				}
				if(k==12){
					cellA2.setCellValue(misReport.getRefferalRecievedDate());
				}
				if(k==13){
					cellA2.setCellValue(misReport.getUrgency());
				}
				if(k==14){
					cellA2.setCellValue(misReport.getAssesmentDate());
				}
				if(k==15){
					cellA2.setCellValue(misReport.getReftoAssesmentDays());
				}
				if(k==16){
					cellA2.setCellValue(misReport.getPractitonerType());
				}
				if(k==17){
					cellA2.setCellValue(misReport.getApptDate());
				}
				if(k==18){
					cellA2.setCellValue(misReport.getApptTime());
				}
				if(k==19){
					cellA2.setCellValue(misReport.getPractitonerSurname());
				}
				if(k==20){
					cellA2.setCellValue(misReport.getTreatmentOutcome());
				}
				if(k==21){
					cellA2.setCellValue(misReport.getDischargeReason());
				}
				if(k==22){
					cellA2.setCellValue(misReport.getApptName());
				}
				if(k==23){
					cellA2.setCellValue(misReport.getApptCharge());
				}
				//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			/*	font2.setFontHeightInPoints((short)10);
				HSSFCellStyle cellStyle2 = workbook.createCellStyle();
				cellStyle2.setFont(font2);
				cellA2.setCellStyle(cellStyle2);*/
				
				k++;
			}
		}
		
		
		//Report 2
		//use list size in in place of p here
		p = p +2;
		row1 = worksheet.createRow((short) p);
		
		cellA1 = row1.createCell((short) 0);
		cellA1.setCellValue("Report 2");
		cellStyle = workbook.createCellStyle();
		cellA1.setCellStyle(cellStyle);
		
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints((short)14);
		cellB1 = row1.createCell((short) 1);
		cellB1.setCellValue("AQP Physiotherapy Quality Indicators - Monthly KPI Report");
		cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellB1.setCellStyle(cellStyle);
		
		
		p = p +2;
		//Report 2 Heading
			HSSFFont font3= workbook.createFont();
			row2 = worksheet.createRow((short) p);
		
		
			HSSFCell cellA2 = row2.createCell((short) 1);
			cellA2.setCellValue("Key Performance Indicator");
			
			font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font3.setFontHeightInPoints((short)10);
			HSSFCellStyle cellStyle2 = workbook.createCellStyle();
			cellStyle2.setFont(font3);
			cellA2.setCellStyle(cellStyle2);
			
			cellA2 = row2.createCell((short) 2);
			cellA2.setCellValue("Calculate Data for Given Month for Following");
			
			font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font3.setFontHeightInPoints((short)10);
			cellStyle2 = workbook.createCellStyle();
			cellStyle2.setFont(font3);
			cellA2.setCellStyle(cellStyle2);
			
			//Report 2 heading details
			p = p +1;
			row2 = worksheet.createRow((short) p);
			cellA2 = row2.createCell((short) 1);
			cellA2.setCellValue("Number of Urgent patients seen within 3 working days of receipt of referral");
			
			//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font2.setFontHeightInPoints((short)10);
			cellStyle2 = workbook.createCellStyle();
			cellStyle2.setFont(font2);
			cellA2.setCellStyle(cellStyle2);
			
			
			cellA2 = row2.createCell((short) 2);
			cellA2.setCellValue("22");
			
			//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font2.setFontHeightInPoints((short)10);
			cellStyle2 = workbook.createCellStyle();
			cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle2.setFont(font2);
			cellA2.setCellStyle(cellStyle2);
			
			p = p +1;
			row2 = worksheet.createRow((short) p);
			cellA2 = row2.createCell((short) 1);
			cellA2.setCellValue("Threshold 100%");
			
			//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font2.setFontHeightInPoints((short)10);
			cellStyle2 = workbook.createCellStyle();
			cellStyle2.setFont(font2);
			cellA2.setCellStyle(cellStyle2);
			
			
			cellA2 = row2.createCell((short) 2);
			cellA2.setCellValue("60%");
			
			//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font2.setFontHeightInPoints((short)10);
			cellStyle2 = workbook.createCellStyle();
			cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle2.setFont(font2);
			cellA2.setCellStyle(cellStyle2);
			
			p = p +1;
			row2 = worksheet.createRow((short) p);
			cellA2 = row2.createCell((short) 1);
			cellA2.setCellValue("Routine patients seen within 28 days of receipt of referral");
			
			//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font2.setFontHeightInPoints((short)10);
			cellStyle2 = workbook.createCellStyle();
			cellStyle2.setFont(font2);
			cellA2.setCellStyle(cellStyle2);
			
			
			cellA2 = row2.createCell((short) 2);
			cellA2.setCellValue("22");
			
			//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font2.setFontHeightInPoints((short)10);
			cellStyle2 = workbook.createCellStyle();
			cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle2.setFont(font2);
			cellA2.setCellStyle(cellStyle2);
			
			p = p +1;
			row2 = worksheet.createRow((short) p);
			cellA2 = row2.createCell((short) 1);
			cellA2.setCellValue("Threshold 100%");
			
			//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font2.setFontHeightInPoints((short)10);
			cellStyle2 = workbook.createCellStyle();
			cellStyle2.setFont(font2);
			cellA2.setCellStyle(cellStyle2);
			
			
			cellA2 = row2.createCell((short) 2);
			cellA2.setCellValue("60%");
			
			//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font2.setFontHeightInPoints((short)10);
			cellStyle2 = workbook.createCellStyle();
			cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle2.setFont(font2);
			cellA2.setCellStyle(cellStyle2);
			
			p = p +1;
			row2 = worksheet.createRow((short) p);
			cellA2 = row2.createCell((short) 1);
			cellA2.setCellValue("Number of DNAs");
			
			//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font2.setFontHeightInPoints((short)10);
			cellStyle2 = workbook.createCellStyle();
			cellStyle2.setFont(font2);
			cellA2.setCellStyle(cellStyle2);
			
			
			cellA2 = row2.createCell((short) 2);
			cellA2.setCellValue("22");
			
			//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font2.setFontHeightInPoints((short)10);
			cellStyle2 = workbook.createCellStyle();
			cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle2.setFont(font2);
			cellA2.setCellStyle(cellStyle2);
			
			p = p +1;
			row2 = worksheet.createRow((short) p);
			cellA2 = row2.createCell((short) 1);
			cellA2.setCellValue("Threshold less than 6% of total referrals");
			
			//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font2.setFontHeightInPoints((short)10);
			cellStyle2 = workbook.createCellStyle();
			cellStyle2.setFont(font2);
			cellA2.setCellStyle(cellStyle2);
			
			
			cellA2 = row2.createCell((short) 2);
			cellA2.setCellValue("60%");
			
			//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font2.setFontHeightInPoints((short)10);
			cellStyle2 = workbook.createCellStyle();
			cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle2.setFont(font2);
			cellA2.setCellStyle(cellStyle2);
			
			p = p +1;
			row2 = worksheet.createRow((short) p);
			cellA2 = row2.createCell((short) 1);
			cellA2.setCellValue("Discharge letters to be sent to the referrer within 5 working days");
			
			//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font2.setFontHeightInPoints((short)10);
			cellStyle2 = workbook.createCellStyle();
			cellStyle2.setFont(font2);
			cellA2.setCellStyle(cellStyle2);
			
			
			cellA2 = row2.createCell((short) 2);
			cellA2.setCellValue("60%");
			
			//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font2.setFontHeightInPoints((short)10);
			cellStyle2 = workbook.createCellStyle();
			cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle2.setFont(font2);
			cellA2.setCellStyle(cellStyle2);
			
			p = p +1;
			row2 = worksheet.createRow((short) p);
			cellA2 = row2.createCell((short) 1);
			cellA2.setCellValue("Threshold 100%");
			
			//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font2.setFontHeightInPoints((short)10);
			cellStyle2 = workbook.createCellStyle();
			cellStyle2.setFont(font2);
			cellA2.setCellStyle(cellStyle2);
			
			
			cellA2 = row2.createCell((short) 2);
			cellA2.setCellValue("60%");
			
			//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font2.setFontHeightInPoints((short)10);
			cellStyle2 = workbook.createCellStyle();
			cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle2.setFont(font2);
			cellA2.setCellStyle(cellStyle2);
			
			p = p +1;
			row2 = worksheet.createRow((short) p);
			cellA2 = row2.createCell((short) 1);
			cellA2.setCellValue("Patients to be offered information leaflets prior to and during their treatment");
			
			//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font2.setFontHeightInPoints((short)10);
			cellStyle2 = workbook.createCellStyle();
			cellStyle2.setFont(font2);
			cellA2.setCellStyle(cellStyle2);
			
			
			cellA2 = row2.createCell((short) 2);
			cellA2.setCellValue("60");
			
			//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font2.setFontHeightInPoints((short)10);
			cellStyle2 = workbook.createCellStyle();
			cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle2.setFont(font2);
			cellA2.setCellStyle(cellStyle2);
			
			p = p +1;
			row2 = worksheet.createRow((short) p);
			cellA2 = row2.createCell((short) 1);
			cellA2.setCellValue("Threshold 100%");
			
			//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font2.setFontHeightInPoints((short)10);
			cellStyle2 = workbook.createCellStyle();
			cellStyle2.setFont(font2);
			cellA2.setCellStyle(cellStyle2);
			
			
			cellA2 = row2.createCell((short) 2);
			cellA2.setCellValue("60%");
			
			//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			font2.setFontHeightInPoints((short)10);
			cellStyle2 = workbook.createCellStyle();
			cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle2.setFont(font2);
			cellA2.setCellStyle(cellStyle2);
			
			
			
			
			//Report 3
			p = p +2;
			row1 = worksheet.createRow((short) p);
			
			cellA1 = row1.createCell((short) 0);
			cellA1.setCellValue("Report 3");
			cellStyle = workbook.createCellStyle();
			cellA1.setCellStyle(cellStyle);
			
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setFontHeightInPoints((short)14);
			cellB1 = row1.createCell((short) 1);
			cellB1.setCellValue("Monthly Activity Summary");
			cellStyle = workbook.createCellStyle();
			cellStyle.setFont(font);
			cellB1.setCellStyle(cellStyle);
			
			
				p = p +2;
			//Report 2 Heading
				font3= workbook.createFont();
				row2 = worksheet.createRow((short) p);
			
			
				cellA2 = row2.createCell((short) 1);
				cellA2.setCellValue("Key Performance Indicator");
				
				font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font3.setFontHeightInPoints((short)10);
				cellStyle2 = workbook.createCellStyle();
				cellStyle2.setFont(font3);
				cellA2.setCellStyle(cellStyle2);
				
				cellA2 = row2.createCell((short) 2);
				cellA2.setCellValue("Calculate Data for Given Month for Following");
				
				font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font3.setFontHeightInPoints((short)10);
				cellStyle2 = workbook.createCellStyle();
				cellStyle2.setFont(font3);
				cellA2.setCellStyle(cellStyle2);
				
				//Report 2 heading details
				p = p +1;
				row2 = worksheet.createRow((short) p);
				cellA2 = row2.createCell((short) 1);
				cellA2.setCellValue("Number of Appointments for the month by:");
				
				//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font2.setFontHeightInPoints((short)10);
				cellStyle2 = workbook.createCellStyle();
				cellStyle2.setFont(font2);
				cellA2.setCellStyle(cellStyle2);
				
				
				cellA2 = row2.createCell((short) 2);
				cellA2.setCellValue("New Referrals");
				
				//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font2.setFontHeightInPoints((short)10);
				cellStyle2 = workbook.createCellStyle();
				cellStyle2.setFont(font2);
				cellA2.setCellStyle(cellStyle2);
				
				p = p +1;
				row2 = worksheet.createRow((short) p);
				cellA2 = row2.createCell((short) 2);
				cellA2.setCellValue("New Referrals Triaged");
				
				//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font2.setFontHeightInPoints((short)10);
				cellStyle2 = workbook.createCellStyle();
				cellStyle2.setFont(font2);
				cellA2.setCellStyle(cellStyle2);
				
				p = p +1;
				row2 = worksheet.createRow((short) p);
				cellA2 = row2.createCell((short) 2);
				cellA2.setCellValue("New Referrals Not Triaged within 3 working days");
				
				//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font2.setFontHeightInPoints((short)10);
				cellStyle2 = workbook.createCellStyle();
				cellStyle2.setFont(font2);
				cellA2.setCellStyle(cellStyle2);
				
				p = p +1;
				row2 = worksheet.createRow((short) p);
				cellA2 = row2.createCell((short) 2);
				cellA2.setCellValue("New Referrals Triaged and sent back to GP as inappropriate");
				
				//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font2.setFontHeightInPoints((short)10);
				cellStyle2 = workbook.createCellStyle();
				cellStyle2.setFont(font2);
				cellA2.setCellStyle(cellStyle2);
				
				p = p +1;
				row2 = worksheet.createRow((short) p);
				cellA2 = row2.createCell((short) 2);
				cellA2.setCellValue("Initial Assessments");
				
				//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font2.setFontHeightInPoints((short)10);
				cellStyle2 = workbook.createCellStyle();
				cellStyle2.setFont(font2);
				cellA2.setCellStyle(cellStyle2);
				
				p = p +1;
				row2 = worksheet.createRow((short) p);
				cellA2 = row2.createCell((short) 2);
				cellA2.setCellValue("Follow up attendances");
				
				//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font2.setFontHeightInPoints((short)10);
				cellStyle2 = workbook.createCellStyle();
				cellStyle2.setFont(font2);
				cellA2.setCellStyle(cellStyle2);
				
				p = p +1;
				row2 = worksheet.createRow((short) p);
				cellA2 = row2.createCell((short) 2);
				cellA2.setCellValue("Attended");
				
				//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font2.setFontHeightInPoints((short)10);
				cellStyle2 = workbook.createCellStyle();
				cellStyle2.setFont(font2);
				cellA2.setCellStyle(cellStyle2);
				
				p = p +1;
				row2 = worksheet.createRow((short) p);
				cellA2 = row2.createCell((short) 2);
				cellA2.setCellValue("DNA");
				
				//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font2.setFontHeightInPoints((short)10);
				cellStyle2 = workbook.createCellStyle();
				cellStyle2.setFont(font2);
				cellA2.setCellStyle(cellStyle2);
				
				p = p +1;
				row2 = worksheet.createRow((short) p);
				cellA2 = row2.createCell((short) 1);
				cellA2.setCellValue("Number of patients for the month by:");
				
				//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font2.setFontHeightInPoints((short)10);
				cellStyle2 = workbook.createCellStyle();
				cellStyle2.setFont(font2);
				cellA2.setCellStyle(cellStyle2);
				
				
				cellA2 = row2.createCell((short) 2);
				cellA2.setCellValue("Requiring an onward referral to secondary care");
				
				//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font2.setFontHeightInPoints((short)10);
				cellStyle2 = workbook.createCellStyle();
				cellStyle2.setFont(font2);
				cellA2.setCellStyle(cellStyle2);
				
				p = p +1;
				row2 = worksheet.createRow((short) p);
				cellA2 = row2.createCell((short) 2);
				cellA2.setCellValue("Routine");
				
				//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font2.setFontHeightInPoints((short)10);
				cellStyle2 = workbook.createCellStyle();
				cellStyle2.setFont(font2);
				cellA2.setCellStyle(cellStyle2);
				
				
				p = p +1;
				row2 = worksheet.createRow((short) p);
				cellA2 = row2.createCell((short) 2);
				cellA2.setCellValue("Urgent");
				
				//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font2.setFontHeightInPoints((short)10);
				cellStyle2 = workbook.createCellStyle();
				cellStyle2.setFont(font2);
				cellA2.setCellStyle(cellStyle2);
				
				
				p = p +1;
				row2 = worksheet.createRow((short) p);
				cellA2 = row2.createCell((short) 2);
				cellA2.setCellValue("Visited at home");
				
				//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font2.setFontHeightInPoints((short)10);
				cellStyle2 = workbook.createCellStyle();
				cellStyle2.setFont(font2);
				cellA2.setCellStyle(cellStyle2);
				
				p = p +1;
				row2 = worksheet.createRow((short) p);
				cellA2 = row2.createCell((short) 2);
				cellA2.setCellValue("Discharged from the Service");
				
				//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font2.setFontHeightInPoints((short)10);
				cellStyle2 = workbook.createCellStyle();
				cellStyle2.setFont(font2);
				cellA2.setCellStyle(cellStyle2);
				
				p = p +1;
				row2 = worksheet.createRow((short) p);
				cellA2 = row2.createCell((short) 1);
				cellA2.setCellValue("Number of Complaints of each type:");
				
				//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font2.setFontHeightInPoints((short)10);
				cellStyle2 = workbook.createCellStyle();
				cellStyle2.setFont(font2);
				cellA2.setCellStyle(cellStyle2);
				
				cellA2 = row2.createCell((short) 2);
				cellA2.setCellValue("Type of Complaint");
				
				//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font2.setFontHeightInPoints((short)10);
				cellStyle2 = workbook.createCellStyle();
				cellStyle2.setFont(font2);
				cellA2.setCellStyle(cellStyle2);
				
				p = p +1;
				row2 = worksheet.createRow((short) p);
				cellA2 = row2.createCell((short) 2);
				cellA2.setCellValue("No of Complaints");
				
				//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font2.setFontHeightInPoints((short)10);
				cellStyle2 = workbook.createCellStyle();
				cellStyle2.setFont(font2);
				cellA2.setCellStyle(cellStyle2);
				
				p = p +1;
				row2 = worksheet.createRow((short) p);
				cellA2 = row2.createCell((short) 1);
				cellA2.setCellValue("Number of Cancellations for the month by:");
				
				//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font2.setFontHeightInPoints((short)10);
				cellStyle2 = workbook.createCellStyle();
				cellStyle2.setFont(font2);
				cellA2.setCellStyle(cellStyle2);
				
				cellA2 = row2.createCell((short) 2);
				cellA2.setCellValue("Patient");
				
				//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font2.setFontHeightInPoints((short)10);
				cellStyle2 = workbook.createCellStyle();
				cellStyle2.setFont(font2);
				cellA2.setCellStyle(cellStyle2);
				
				p = p +1;
				row2 = worksheet.createRow((short) p);
				cellA2 = row2.createCell((short) 2);
				cellA2.setCellValue("Provider");
				
				//font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font2.setFontHeightInPoints((short)10);
				cellStyle2 = workbook.createCellStyle();
				cellStyle2.setFont(font2);
				cellA2.setCellStyle(cellStyle2);
				
				
				
				//Report 4
				p = p +2;
				row1 = worksheet.createRow((short) p);
				
				cellA1 = row1.createCell((short) 0);
				cellA1.setCellValue("Report 4");
				cellStyle = workbook.createCellStyle();
				cellA1.setCellStyle(cellStyle);
				
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font.setFontHeightInPoints((short)14);
				cellB1 = row1.createCell((short) 1);
				cellB1.setCellValue("Monthly Follow Up Report: Patients having more than 6 follow ups in a month");
				cellStyle = workbook.createCellStyle();
				cellStyle.setFont(font);
				cellB1.setCellStyle(cellStyle);
				
				
					p = p +2;
				//Report 4 Heading
					font3= workbook.createFont();
					row2 = worksheet.createRow((short) p);
				
				
					cellA2 = row2.createCell((short) 1);
					cellA2.setCellValue("Key Performance Indicator");
					
					font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
					font3.setFontHeightInPoints((short)10);
					cellStyle2 = workbook.createCellStyle();
					cellStyle2.setFont(font3);
					cellA2.setCellStyle(cellStyle2);
					
					cellA2 = row2.createCell((short) 2);
					cellA2.setCellValue("Calculate Data for Given Month for Following");
					
					font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
					font3.setFontHeightInPoints((short)10);
					cellStyle2 = workbook.createCellStyle();
					cellStyle2.setFont(font3);
					cellA2.setCellStyle(cellStyle2);
					
						p = p +2;
						//Report 4 Heading
						font3= workbook.createFont();
						row2 = worksheet.createRow((short) p);
					
					
						cellA2 = row2.createCell((short) 1);
						cellA2.setCellValue("Client Name");
						
						font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
						font3.setFontHeightInPoints((short)10);
						cellStyle2 = workbook.createCellStyle();
						cellStyle2.setFont(font3);
						cellA2.setCellStyle(cellStyle2);
						
						cellA2 = row2.createCell((short) 2);
						cellA2.setCellValue("NHS Number");
						
						font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
						font3.setFontHeightInPoints((short)10);
						cellStyle2 = workbook.createCellStyle();
						cellStyle2.setFont(font3);
						cellA2.setCellStyle(cellStyle2);
						
						cellA2 = row2.createCell((short) 3);
						cellA2.setCellValue("Number of Follow ups");
						
						font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
						font3.setFontHeightInPoints((short)10);
						cellStyle2 = workbook.createCellStyle();
						cellStyle2.setFont(font3);
						cellA2.setCellStyle(cellStyle2);
				
						
						//report 1 values
						ArrayList<NotAvailableSlot>threedayreflist = misDAO.getThreeDayRefList(fromDate,toDate);
						int count3 = 0;
						for(NotAvailableSlot notAvailableSlot : threedayreflist){
							String assesmentdate = DateTimeUtils.getCommencingDate2(notAvailableSlot.getCommencing());
							long reftoassesmentdays = DateTimeUtils.getDifferenceOfTwoDate(notAvailableSlot.getRefDate(), assesmentdate);
							if(reftoassesmentdays==3){
								count3++;
							}
						}
				
						int percentage3 = 0;
						if(threedayreflist.size()>0){
							percentage3 = (count3 * 100)/threedayreflist.size();
						}
						
						misReportForm.setCount3(Integer.toString(count3));
						misReportForm.setPer3(Integer.toString(percentage3));
				
						int count28=0;
						ArrayList<NotAvailableSlot>twentyeightreflist = misDAO.getTwentyEightRefList(fromDate,toDate);
						for(NotAvailableSlot notAvailableSlot : twentyeightreflist){
							String assesmentdate = DateTimeUtils.getCommencingDate2(notAvailableSlot.getCommencing());
							long reftoassesmentdays = DateTimeUtils.getDifferenceOfTwoDate(notAvailableSlot.getRefDate(), assesmentdate);
							if(reftoassesmentdays==28){
								count28++;
							}
						}
						
						int percentage28 = 0;
						if(twentyeightreflist.size()>0){
							percentage28 = (count28 * 100)/twentyeightreflist.size();
						}
						
						misReportForm.setCount28(Integer.toString(count28));
						misReportForm.setPer28(Integer.toString(percentage28));
						
						int dnacount = misDAO.getDnaCount(fromDate,toDate);
						misReportForm.setDnaCount(dnacount);
						misReportForm.setPerless6("N/A");
						
						
						int dschargeCount = 0;
						for(NotAvailableSlot notAvailableSlot : twentyeightreflist){
							if(notAvailableSlot.getDischargedate()!=null){
								String temp[] = notAvailableSlot.getDischargedate().split(" ");
								String dchargeDate = DateTimeUtils.getCommencingDate2(temp[0]);
								long deffdays = DateTimeUtils.getDifferenceOfTwoDate(notAvailableSlot.getRefDate(), dchargeDate);
								if(deffdays==5){
									dschargeCount++;
								}
							}
						}
						
						int percentage5 = 0;
						if(twentyeightreflist.size()>0){
							percentage5 = (dschargeCount * 100)/twentyeightreflist.size();
						}
						
						misReportForm.setDschargeCount(dschargeCount);
						misReportForm.setPer5(Integer.toString(percentage5));
						
						int letterCount = misDAO.getLetterCount(fromDate,toDate);
						int totalAppt = misDAO.getTotalAppt(fromDate,toDate);
						int letterPer = (letterCount *  100)/totalAppt;
						
						misReportForm.setLetterCount(letterCount);
						misReportForm.setLetterPer(Integer.toString(letterPer));
						
						
						//report 3 details
						int newReferalCount = misDAO.getNewRefralCount(fromDate,toDate);
						misReportForm.setNewReferalCount(newReferalCount);
						
						int newRefralTriggered = misDAO.getNewRefralTriggeredCount(fromDate,toDate);
						misReportForm.setNewRefralTriggered(newRefralTriggered);
						
						int notrigerdCount3 = 0;
						ArrayList<NotAvailableSlot>newRefNotTrigeredList = misDAO.getNewREfralNotTrigeredList(fromDate,toDate);
						for(NotAvailableSlot notAvailableSlot : newRefNotTrigeredList){
							if(notAvailableSlot.getRefDate()!=null){
								if(!notAvailableSlot.getRefDate().equals("")){
									String assesmentdate = DateTimeUtils.getCommencingDate2(notAvailableSlot.getCommencing());
									long nottrigredDays = DateTimeUtils.getDifferenceOfTwoDate(notAvailableSlot.getRefDate(), assesmentdate);
									if(nottrigredDays>3){
										notrigerdCount3++;
									}
								}
							}
							
						}
						
						misReportForm.setNotrigerdCount3(notrigerdCount3);
						
						int countInitialAssessment = misDAO.getCountInitialAssessment(fromDate,toDate);
						misReportForm.setCountInitialAssessment(countInitialAssessment);
						
						int countFollowupAttendence = misDAO.getCountFollowupAttendence(fromDate,toDate);
						misReportForm.setCountFollowupAttendence(countFollowupAttendence);
						
						int routineCount = misDAO.getRoutineCount(fromDate,toDate);
						misReportForm.setRoutineCount(routineCount);
						
						int misUrgentCount = misDAO.getUrgentCount(fromDate,toDate);
						misReportForm.setMisUrgentCount(misUrgentCount);
						
						int dischargeCount = misDAO.getDischargeCount(fromDate,toDate);
						misReportForm.setDischargeCount(dischargeCount);
						
						int countCanceldApmt = misDAO.getCountCancelledApmt(fromDate,toDate);
						misReportForm.setCountCanceldApmt(countCanceldApmt);
		
						
						//Report 4 Details
						ArrayList<NotAvailableSlot>greaterThanSixFollowupsList = misDAO.getGreaterThanSixFollowups(fromDate,toDate);
						misReportForm.setGreaterThanSixFollowupsList(greaterThanSixFollowupsList);
		
		/*workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();*/
		
		}
		return SUCCESS;
	}
	

	public String sendmail()throws Exception{
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
		//String file = request.getParameter("file");	
		String filename = (String)session.getAttribute("misreportfile");		
		
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
		}finally{
			connection.close();
		}
		return null;
	}

	public MisReportForm getModel() {
		// TODO Auto-generated method stub
		return misReportForm;
	}

}
