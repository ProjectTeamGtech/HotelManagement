package com.apm.Tools.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.allcolor.yahp.converter.CYaHPConverter;
import org.allcolor.yahp.converter.IHtmlToPdfTransformer;
import org.apache.struts2.ServletActionContext;

import com.apm.InstantMessage.web.action.Email;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Tools.eu.bi.EmailTemplateDAO;
import com.apm.Tools.eu.blogic.jdbc.JDBCEmailTemplateDAO;
import com.apm.Tools.eu.entity.EmailTemplate;
import com.apm.Tools.web.form.EmailTemplateForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class EmailTemplateAction extends BaseAction implements Preparable, ModelDriven<EmailTemplateForm>{

	EmailTemplateForm emailTemplateForm = new EmailTemplateForm();
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

	public String execute() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			
			int totalCount = emailTemplateDAO.getTotalEmailTemplateCount();
			pagination.setPreperties(totalCount);
			
			ArrayList<EmailTemplate> emailTemplateList = emailTemplateDAO.getEmailTemplateList(pagination);
			
			pagination.setPage_records(emailTemplateList.size());
			emailTemplateForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			emailTemplateForm.setTotalRecords(totalCount);
			emailTemplateForm.setEmailTemplateList(emailTemplateList);
			session.setAttribute("emailTemplateList", emailTemplateList);
			session.setAttribute("pagination", pagination);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return "emailTemplateList";		
	}
	
	public String add(){
		if(!verifyLogin(request)){
			return "login";
		}
		return "addTemplatePage";
	}
	
	public String save() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		int result=0;
		try{
			connection = Connection_provider.getconnection();
			EmailTemplate emailTemplate = new EmailTemplate();
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			emailTemplate.setTemplateName(emailTemplateForm.getTemplateName());
			emailTemplate.setHeaderNote(request.getParameter("headerNote"));
			emailTemplate.setBody1Note(request.getParameter("body1Note"));
			String body2Note = request.getParameter("body2Note");
            String body2Note1 = body2Note.replaceAll("&nbsp;", " ");
            emailTemplate.setBody2Note(body2Note1.toString());
			emailTemplate.setFooterNote(request.getParameter("footerNote"));
			emailTemplate.setUserName(emailTemplateForm.getUserName());
			
			result = emailTemplateDAO.saveEmailTemplate(emailTemplate,loginInfo.getClinicid());
			
			emailTemplateForm.setMessage("Email Template Added Sucessfully!!");
			addActionMessage("Email Template Added Sucessfully!!");
			
			setFormDataofEmailTemplate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		if(loginInfo.isAfitech()) {
		  return "manageaff";
		}else {
		  return "emailTemplateList";
		}
	}
	
	public String preview() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		EmailTemplate emailTemplate = new EmailTemplate();
		try{
			connection = Connection_provider.getconnection();
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			
			//String selectedid = request.getParameter("selectedid");
			
			int selectedid = Integer.parseInt(request.getParameter("selectedid"));
			
			emailTemplate = emailTemplateDAO.getEmailTemplatePreview(selectedid);
			
			emailTemplateForm.setId(emailTemplate.getId());
			emailTemplateForm.setTemplateName(emailTemplate.getTemplateName());
			emailTemplateForm.setHeaderNote(emailTemplate.getHeaderNote());
			emailTemplateForm.setBody1Note(emailTemplate.getBody1Note());
			emailTemplateForm.setBody2Note(emailTemplate.getBody2Note());
			emailTemplateForm.setFooterNote(emailTemplate.getFooterNote());
			
			String templateName = emailTemplate.getTemplateName();
			String headerNote = emailTemplate.getHeaderNote();
			String body1Note = emailTemplate.getBody1Note();
			String body2Note = emailTemplate.getBody2Note();
			String footerNote = emailTemplate.getFooterNote();
			
			session.setAttribute("templateName",templateName);
			session.setAttribute("headerNote",headerNote);
			session.setAttribute("body1Note",body1Note);
			session.setAttribute("body2Note",body2Note);
			session.setAttribute("footerNote",footerNote);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return "previewEmailTemplatePage";
	}
	
	public String edit() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		int id = Integer.parseInt(request.getParameter("selectedid"));
		EmailTemplate emailTemplate = new EmailTemplate();
		try{
			connection = Connection_provider.getconnection();
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			emailTemplate = emailTemplateDAO.getEmailTemplate(id,emailTemplate);
			emailTemplateForm.setId(emailTemplate.getId());
			emailTemplateForm.setTemplateName(emailTemplate.getTemplateName());
			emailTemplateForm.setHeaderNote(emailTemplate.getHeaderNote());
			emailTemplateForm.setBody1Note(emailTemplate.getBody1Note());
			emailTemplateForm.setBody2Note(emailTemplate.getBody2Note());
			emailTemplateForm.setFooterNote(emailTemplate.getFooterNote());
			emailTemplateForm.setUserName(emailTemplate.getUserName());
			
			String templateName = emailTemplate.getTemplateName();
			String headerNote = emailTemplate .getHeaderNote();
			String body1Note = emailTemplate.getBody1Note();
			String body2Note = emailTemplate.getBody2Note();
			String footerNote = emailTemplate.getFooterNote();
			String userName = emailTemplate.getUserName();
			
			session.setAttribute("templateName",templateName);
			session.setAttribute("headerNote",headerNote);
			session.setAttribute("body1Note",body1Note);
			session.setAttribute("body2Note",body2Note);
			session.setAttribute("footerNote",footerNote);
			session.setAttribute("userName", userName);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return "editEmailTemplatePage";
	}
	
	public String update() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;		
		int result = 0;
		try{
			int id = emailTemplateForm.getId();
			connection = Connection_provider.getconnection();
			EmailTemplate emailTemplate = new EmailTemplate();
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			
			emailTemplate.setId(id);
			emailTemplate.setTemplateName(emailTemplateForm.getTemplateName());
			emailTemplate.setHeaderNote(request.getParameter("headerNote"));
			emailTemplate.setBody1Note(request.getParameter("body1Note"));
			String body2Note = request.getParameter("body2Note");
            String body2Note1 = body2Note.replaceAll("&nbsp;", " ");
            emailTemplate.setBody2Note(body2Note1.toString());
			emailTemplate.setFooterNote(request.getParameter("footerNote"));
			emailTemplate.setUserName(emailTemplateForm.getUserName());
			
			result = emailTemplateDAO.updateEmailTemplate(emailTemplate,id);
			
			emailTemplateForm.setMessage("Email Template Updated Sucessfully!!");
			addActionMessage("Email Template Updated Sucessfully!!");
			
			setFormDataofEmailTemplate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return "emailTemplateList";
	}
	
	public String delete() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		int result = 0;
		int id =Integer.parseInt(request.getParameter("selectedid"));
		Connection connection = null;
		EmailTemplate emailTemplate = new EmailTemplate();
		
		try{			
			connection = Connection_provider.getconnection();
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			
			result = emailTemplateDAO.deleteEmailTemplate(id,emailTemplate);	
			emailTemplateForm.setMessage("Email Template Deleted Sucessfully!!");
			addActionMessage("Email Template Deleted Sucessfully!!");
			
			setFormDataofEmailTemplate();
		}
		catch (Exception e) {
			
		}finally{
			connection.close();
		}
		return "emailTemplateList";
	}
	
	public String back() throws Exception{
		setFormDataofEmailTemplate();
		return "emailTemplateList";
	}
	
	
	public void setFormDataofEmailTemplate() throws Exception {
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			
			pagination = (Pagination) session.getAttribute("pagination");
			int totalCount = emailTemplateDAO.getTotalEmailTemplateCount();
			pagination.setPreperties(totalCount);
			
			ArrayList<EmailTemplate> emailTemplateList = emailTemplateDAO.getEmailTemplateList(pagination);
			
			pagination.setPage_records(emailTemplateList.size());
			emailTemplateForm.setTotalRecords(totalCount);
			emailTemplateForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			emailTemplateForm.setEmailTemplateList(emailTemplateList);
			session.setAttribute("pagination", pagination);
			session.setAttribute("emailTemplateList", emailTemplateList);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
	}
	
	public String checkEmailSend() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			String checkValue = clinicDAO.IsMailSend(loginInfo.getClinicid());
			
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
	    	EmailTemplate emailTemplate = new EmailTemplate();
	    	emailTemplate = emailTemplateDAO.getEmailConfigureDetails(loginInfo.getClinicid());
	    	emailTemplateForm.setEmailConfigureId(emailTemplate.getEmailConfigureId());
	    	emailTemplateForm.setEmailUserName(emailTemplate.getEmailUserName());
	    	emailTemplateForm.setAdDuration(emailTemplate.getAdDuration());
	    	//emailTemplateForm.setEmailPassword(emailTemplate.getEmailPassword());
	    	emailTemplateForm.setEmailHostName(emailTemplate.getEmailHostName());
	    	String pwd = emailTemplate.getEmailPassword();
	    	String pwd1 = "*";
	    	int count = pwd.length();
	    	for(int i=1; i<=count; i++){
	    		pwd1 = pwd1+"*";
	    		
	    	}
	    	emailTemplateForm.setEmailPassword(pwd1);
	    	//session.setAttribute("pwd1", pwd1);
	    	
			emailTemplateForm.setCheckMailSend(checkValue);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return "checkEmailSend";
	}
	
	public String createPDF() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		String templateData = request.getParameter("templateData").toString();
		String templateId = request.getParameter("templateId");
		
		String user = request.getParameter("user");
		int userId = Integer.parseInt(request.getParameter("id"));
		String  userName = request.getParameter("userName");
		
		String filePath = request.getRealPath("/liveData/template/");
//		System.out.println(filePath);
		//String filename = "Invoice_"+clientid+"_"+invoiceid+".pdf";
		String filename = userName+"_"+userId+"_template"+templateId+".pdf";
		
		htmlToPdfFile(templateData, filePath, filename);	
		
		session.setAttribute("pdfFileName", filePath+"/"+filename);
//		System.out.println("pdf done");
		//downloadDoc(filename);
		emailTemplateForm.setMessage("PDF File Created Sucessfully!!");
		addActionMessage("PDF File Created Sucessfully!!");
		
		return null;
	}
	
	public void htmlToPdfFile(String htmlString, String filepath,
			String fileaName) throws Exception {
		
		

		CYaHPConverter converter = new CYaHPConverter();
		File fout = new File(filepath + "/" + fileaName);
		FileOutputStream out = new FileOutputStream(fout);
		Map properties = new HashMap();
		ArrayList headerFooterList = new ArrayList();

		properties.put(IHtmlToPdfTransformer.PDF_RENDERER_CLASS,
				IHtmlToPdfTransformer.FLYINGSAUCER_PDF_RENDERER);
		// properties.put(IHtmlToPdfTransformer.FOP_TTF_FONT_PATH, fontPath);
		converter.convertToPdf(htmlString, IHtmlToPdfTransformer.A4P,
				(java.util.List) headerFooterList, "file:///temp/",	out, properties);
		out.flush();
		out.close();
	}
	

	public String addEmailConfigure() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		return "addEmailConfigure";
	}
	
	public String saveEmailConfigure() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			EmailTemplate emailTemplate = new EmailTemplate();
			emailTemplate.setEmailUserName(emailTemplateForm.getEmailUserName());
			emailTemplate.setEmailPassword(emailTemplateForm.getEmailPassword());
			emailTemplate.setEmailConfirmPassword(emailTemplateForm.getEmailConfirmPassword());
			emailTemplate.setEmailHostName(emailTemplateForm.getEmailHostName());
			
			int result = emailTemplateDAO.SaveEmailConfugureInfo(emailTemplate,loginInfo.getId());
			
			formData();
			
			/*ClinicDAO clinicDAO = new JDBCClinicDAO(connection);			
			String CheckMailToSend = clinicDAO.IsMailSend(loginInfo.getId());
			emailTemplateForm.setCheckMailSend(CheckMailToSend);*/
			
			emailTemplateForm.setMessage("Data Added Sucessfully!!");
			addActionMessage("Data Added Sucessfully!!");
			
					
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return "checkEmailSend";
	}
	
	
	private void formData()throws Exception {
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			String checkValue = clinicDAO.IsMailSend(loginInfo.getId());
			
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
	    	EmailTemplate emailTemplate = new EmailTemplate();
	    	emailTemplate = emailTemplateDAO.getEmailConfigureDetails(loginInfo.getId());
	    	emailTemplateForm.setEmailConfigureId(emailTemplate.getEmailConfigureId());
	    	emailTemplateForm.setEmailUserName(emailTemplate.getEmailUserName());
	    	//emailTemplateForm.setEmailPassword(emailTemplate.getEmailPassword());
	    	emailTemplateForm.setEmailHostName(emailTemplate.getEmailHostName());	    	
			emailTemplateForm.setCheckMailSend(checkValue);
			
			loginInfo.setEmailConfigureId(emailTemplate.getEmailConfigureId());
			loginInfo.setEmailUserName(emailTemplate.getEmailUserName());
			loginInfo.setEmailPassword(emailTemplate.getEmailPassword());
			loginInfo.setEmailHostName(emailTemplate.getEmailHostName());
			
			String pwd = emailTemplate.getEmailPassword();
	    	String pwd1 = "*";
	    	int count = pwd.length();
	    	for(int i=1; i<=count; i++){
	    		pwd1 = pwd1+"*";
	    		
	    	}
	    	emailTemplateForm.setEmailPassword(pwd1);
			
			session.setAttribute("logininfo", loginInfo);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
	}

	public String editEmailConfigure()throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		String emailConfigureId = emailTemplateForm.getEmailConfigureId();
		int selectedId = Integer.parseInt(emailConfigureId);
/*		int selectedId = Integer.parseInt(request.getParameter("emailConfigureId"));*/
		
		EmailTemplate emailTemplate = new EmailTemplate();
		try{
			connection = Connection_provider.getconnection();
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			emailTemplate = emailTemplateDAO.getEditEmailConfigureDetails(selectedId);
			emailTemplateForm.setEmailConfigureId(emailTemplate.getEmailConfigureId());
	    	emailTemplateForm.setEmailUserName(emailTemplate.getEmailUserName());
	    	emailTemplateForm.setEmailPassword(emailTemplate.getEmailPassword());
	    	emailTemplateForm.setEmailHostName(emailTemplate.getEmailHostName());
	    	String emailpwd = emailTemplate.getEmailPassword();
	    	emailTemplateForm.setAdDuration(emailTemplate.getAdDuration());
	    	
	    	session.setAttribute("emailpwd", emailpwd);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return "editEmailConfigure";
	}
	
	public String updateEmailConfigure()throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			
			EmailTemplate emailTemplate = new EmailTemplate();
			emailTemplate.setEmailUserName(emailTemplateForm.getEmailUserName());
			emailTemplate.setEmailPassword(emailTemplateForm.getEmailPassword());
			emailTemplate.setEmailConfirmPassword(emailTemplateForm.getEmailConfirmPassword());
			emailTemplate.setEmailHostName(emailTemplateForm.getEmailHostName());
			emailTemplate.setAdDuration(emailTemplateForm.getAdDuration());
			
			int result = emailTemplateDAO.updateEmailConfugureInfo(emailTemplate,loginInfo.getClinicid());
			
			formData();
			
			emailTemplateForm.setMessage("Data Updated Sucessfully!!");
			addActionMessage("Data Updated Sucessfully!!");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return "checkEmailSend";
	}
	
	public String showEmailPassword()throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
		//	String emailConfigureId = emailTemplateForm.getEmailConfigureId();
			//int selectedId = Integer.parseInt(emailConfigureId);
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
		//	String showPwd = request.getParameter("showPwd");
			String emailPwd = emailTemplateDAO.showEmailConfigurePwd(loginInfo.getId());
			emailTemplateForm.setEmailPassword(emailPwd);
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+emailPwd+""); 
		
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			connection.close();
		}
			return null;
	}
	
	/*public String changePassword(){
		if(!verifyLogin(request)){
			return "login";
		}
		
	
		return "changeEmailPwd";
	}
	
	public String changePwdEmailConfigure(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			
			EmailTemplate emailTemplate = new EmailTemplate();
			//emailTemplate.setEmailUserName(emailTemplateForm.getEmailUserName());
			emailTemplate.setEmailPassword(emailTemplateForm.getEmailPassword());
			emailTemplate.setEmailConfirmPassword(emailTemplateForm.getEmailConfirmPassword());
			//emailTemplate.setEmailHostName(emailTemplateForm.getEmailHostName());
			
			int result = emailTemplateDAO.updatePasswordEmailConfugure(emailTemplate,loginInfo.getId());
			
			formData();
			
			emailTemplateForm.setMessage("Data Updated Sucessfully!!");
			addActionMessage("Data Updated Sucessfully!!");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "checkEmailSend";
	}
	*/


	public EmailTemplateForm getModel() {

		return emailTemplateForm;
	}

	public void prepare() throws Exception {		
		
	}
	
	public String loademailtemplate(){
		try {
			Connection connection= Connection_provider.getconnection();
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			EmailTemplate emailTemplate= new EmailTemplate();
			int selectedid = DateTimeUtils.convertToInteger(request.getParameter("selectedid"));
			
			emailTemplate = emailTemplateDAO.getEmailTemplatePreview(selectedid);
			String email=DateTimeUtils.isNull(emailTemplate.getHeaderNote());
			email=email+"<br>"+DateTimeUtils.isNull(emailTemplate.getBody1Note());
			email=email+"<br>"+DateTimeUtils.isNull(emailTemplate.getBody2Note());
			email=email+"<br>"+DateTimeUtils.isNull(emailTemplate.getFooterNote());
			
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(email); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
