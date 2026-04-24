package com.apm.Medical.Records.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.web.form.MasterForm;
import com.apm.Medical.Records.eu.bi.MedicalRecordDAO;
import com.apm.Medical.Records.eu.blogic.jdbc.JDBCMedicalRecordDAO;
import com.apm.Medical.Records.eu.entity.MedicalRecord;
import com.apm.Medical.Records.web.form.MedicalRecordForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class MedicalRecordAction extends BaseAction implements Preparable, ModelDriven<MedicalRecordForm>{
	
	MedicalRecordForm medicalRecordForm = new MedicalRecordForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);

	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	private Pagination pagination = new Pagination(10, 1);
	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	


	public String execute(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			String client = medicalRecordForm.getClient();
			String clientid = medicalRecordForm.getSearchClientId();
			
			MedicalRecordDAO medicalRecordDAO = new JDBCMedicalRecordDAO(connection);
			
			int totalCount = medicalRecordDAO.getTotalMedicakRecordsCount(medicalRecordForm.getSearchText(),medicalRecordForm.getSearchClientId());
			pagination.setPreperties(totalCount);
			
			ArrayList<MedicalRecord>medicalRecordList = medicalRecordDAO.getMedicalRecordList(pagination,medicalRecordForm.getSearchText(),medicalRecordForm.getSearchClientId());
			pagination.setPage_records(medicalRecordList.size());
			medicalRecordForm.setTotalRecords(totalCount);
			medicalRecordForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			medicalRecordForm.setMedicalRecordList(medicalRecordList);
			medicalRecordForm.setClient(client);
			medicalRecordForm.setSearchClientId(clientid);
			session.setAttribute("pagination", pagination);
			
			session.setAttribute("searchClientId", clientid);
			session.setAttribute("client", client);
			
		}
		catch (Exception e) {
			
		}
		
		
		return "allMedicalRecords";
	}
	public void setFormData(){
		Connection connection = null;
		int result = 0;
		try{
			connection = Connection_provider.getconnection();
			Master master = new Master();
			
			MedicalRecordDAO medicalRecordDAO = new JDBCMedicalRecordDAO(connection);
			pagination = (Pagination) session.getAttribute("pagination");
			int totalCount = medicalRecordDAO.getTotalMedicakRecordsCount(medicalRecordForm.getSearchText(),medicalRecordForm.getSearchClientId());
		pagination.setPreperties(totalCount);
		
		ArrayList<MedicalRecord>medicalRecordList = medicalRecordDAO.getMedicalRecordList(pagination,medicalRecordForm.getSearchText(),medicalRecordForm.getSearchClientId());
		pagination.setPage_records(medicalRecordList.size());
		medicalRecordForm.setTotalRecords(totalCount);
		medicalRecordForm.setPagerecords(Integer.toString(pagination.getPage_records()));
		medicalRecordForm.setMedicalRecordList(medicalRecordList);
		String clientId = (String) session.getAttribute("searchClientId");
		String client = (String) session.getAttribute("client");
		medicalRecordForm.setClient(client);
		medicalRecordForm.setSearchClientId(clientId);
		
		}
		catch(Exception e){
			
		}
	}
	
	public String input(){
		if(!verifyLogin(request)){
			return "login";
		}
		String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
		String temp[] = currentDate.split(" ");
		String temp1[] = temp[0].split("-");
		String date = temp1[0] + "/" + temp1[1] + "/" + temp1[2];
		medicalRecordForm.setDate(date);
		String clientId = medicalRecordForm.getClientId();
		String clientName = medicalRecordForm.getClientName();
		if(clientId.equals(null) || clientId.equals("")){
			clientId = (String) session.getAttribute("searchClientId");
			clientName = (String) session.getAttribute("client");
			
		}
		medicalRecordForm.setClientId(clientId);
		medicalRecordForm.setClientName(clientName);
		return INPUT;
		
	}
	
	public String save(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			MedicalRecordDAO medicalRecordDAO = new JDBCMedicalRecordDAO(connection);
			MedicalRecord medicalRecord = new MedicalRecord();
			medicalRecord.setClient(medicalRecordForm.getClientName());
			medicalRecord.setClientId(medicalRecordForm.getClientId());
			medicalRecord.setDate(medicalRecordForm.getDate());
			medicalRecord.setSubject(medicalRecordForm.getSubject());
			medicalRecord.setDescription(medicalRecordForm.getDescription());
			medicalRecord.setUserImageFileName(medicalRecordForm.getUserImageFileName());
			
			int id = medicalRecordDAO.saveMedicalRecord(medicalRecord);
			medicalRecordForm.setMessage("Record Added Sucessfully!!");

			addActionMessage("Record Added Sucessfully!!");
			//Upload
			String filePath = request.getRealPath("/liveData/medicalAttachments/");
		       
//			System.out.println("Server path:" + filePath);
			File fileToCreate = new File(filePath, id+"_"+medicalRecordForm.getUserImageFileName());
			FileUtils.copyFile(medicalRecordForm.getUserImage(), fileToCreate);
			setFormData();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "allMedicalRecords";
	}
	public String download() throws IOException{
		if(!verifyLogin(request)){
			return "login";
		}
		String userImageFileName = request.getParameter("userImageFileName");
		FileInputStream in = null;
		ServletOutputStream out = null;
		HttpServletRequest servletRequest=(HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
		 File file=new File(servletRequest.getRealPath("/liveData/medicalAttachments/"+userImageFileName+""));
		 if(file.exists()){
		//return an application file instead of html page
		//response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename="+userImageFileName+"");
	
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
		return null;
	}
	
	public String edit(){
		if(!verifyLogin(request)){
			return "login";
		}
		int id =Integer.parseInt(request.getParameter("id"));
		Connection connection = null;
		
		MedicalRecord medicalRecord = new MedicalRecord();
		try{
			
			connection = Connection_provider.getconnection();
			MedicalRecordDAO medicalRecordDAO = new JDBCMedicalRecordDAO(connection);
			medicalRecord = medicalRecordDAO.getMedicalDetails(id,medicalRecord);
			medicalRecordForm.setId(medicalRecord.getId());
			medicalRecordForm.setDate(medicalRecord.getDate());
			medicalRecordForm.setSubject(medicalRecord.getSubject());
			medicalRecordForm.setDescription(medicalRecord.getDescription());
			medicalRecordForm.setUserImageFileName(medicalRecord.getUserImageFileName());
			medicalRecordForm.setFilename(medicalRecord.getUserImageFileName());
			medicalRecordForm.setClientName(medicalRecord.getClient());
			medicalRecordForm.setClientId(medicalRecord.getClientId());
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return "editPage";
	}
	
	public String update(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			MedicalRecordDAO medicalRecordDAO = new JDBCMedicalRecordDAO(connection);
			int idSelected = medicalRecordForm.getId();
			MedicalRecord medicalRecord = new MedicalRecord();
			medicalRecord.setClient(medicalRecordForm.getClientName());
			medicalRecord.setClientId(medicalRecordForm.getClientId());
			medicalRecord.setDate(medicalRecordForm.getDate());
			medicalRecord.setSubject(medicalRecordForm.getSubject());
			medicalRecord.setDescription(medicalRecordForm.getDescription());
			if(medicalRecordForm.getUserImageFileName()==null){
				medicalRecord.setUserImageFileName(medicalRecordForm.getFilename());
			}else{
			medicalRecord.setUserImageFileName(medicalRecordForm.getUserImageFileName());
			}
			int result = medicalRecordDAO.updateMedicalRecord(medicalRecord,idSelected);
			medicalRecordForm.setMessage("Record Updated Sucessfully!!");

			addActionMessage("Record Updated Sucessfully!!");
			if(medicalRecordForm.getUserImageFileName()!=null && medicalRecordForm.getUserImage()!=null){
			//Upload
			String filePath = request.getRealPath("/liveData/medicalAttachments/");
		       
//			System.out.println("Server path:" + filePath);
			File fileToCreate = new File(filePath, idSelected+"_"+medicalRecordForm.getUserImageFileName());
			FileUtils.copyFile(medicalRecordForm.getUserImage(), fileToCreate);
			}
			setFormData();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "allMedicalRecords";
	}
	
	public String delete(){
		if(!verifyLogin(request)){
			return "login";
		}
		int result = 0;
		int id =Integer.parseInt(request.getParameter("id"));
		String userImageFileName = request.getParameter("userImageFileName");
		Connection connection = null;
		Master master = new Master();
		MedicalRecord medicalRecord = new MedicalRecord();
		try{
			
			connection = Connection_provider.getconnection();
			MedicalRecordDAO medicalRecordDAO = new JDBCMedicalRecordDAO(connection);
			
			result = medicalRecordDAO.deleteMedicalRecord(id,medicalRecord);	
			medicalRecordForm.setMessage("Medical Details Deleted Sucessfully!!");
			addActionMessage("Medical Details Deleted Sucessfully!!");
			String filePath = request.getRealPath("/liveData/medicalAttachments/");
			File file = new File(filePath+"/"+userImageFileName);
			file.delete();
			setFormData();
		}
		catch (Exception e) {
			
		}
		return "allMedicalRecords";
	}
	public MedicalRecordForm getModel() {
		// TODO Auto-generated method stub
		return medicalRecordForm;
	}


	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
