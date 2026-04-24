package com.apm.Tools.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.apm.Tools.eu.bi.EmailTemplateDAO;
import com.apm.Tools.eu.bi.SMSTemplateDAO;
import com.apm.Tools.eu.blogic.jdbc.JDBCSMSTemplateDao;
import com.apm.Tools.eu.entity.EmailTemplate;
import com.apm.Tools.web.form.EmailTemplateForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SMSTemplateAction extends ActionSupport implements
		ModelDriven<EmailTemplateForm> {

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();

	EmailTemplateForm emailTemplateForm = new EmailTemplateForm();

	public EmailTemplateForm getEmailTemplateForm() {
		return emailTemplateForm;
	}

	public void setEmailTemplateForm(EmailTemplateForm emailTemplateForm) {
		this.emailTemplateForm = emailTemplateForm;
	}


	public EmailTemplateForm getModel() {
		// TODO Auto-generated method stub
		return emailTemplateForm;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub

		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			SMSTemplateDAO dao = new JDBCSMSTemplateDao(connection);
			ArrayList<EmailTemplate> emailTemplates = dao.getAllSMSTemplates();
			emailTemplateForm.setEmailTemplateList(emailTemplates);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			connection.close();
		}

		return SUCCESS;
	}
	
	
	public String set()throws Exception{
		
		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			SMSTemplateDAO smsTemplateDAO = new JDBCSMSTemplateDao(connection);
			
			String selectedid = request.getParameter("id");
			
			EmailTemplate template = smsTemplateDAO.getEmailTemplate(Integer.parseInt(selectedid));
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+template.getText()+"");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return null;
	}

	public String add() throws Exception {

		return "add";
	}

	public String save() throws Exception {
		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			SMSTemplateDAO dao = new JDBCSMSTemplateDao(connection);
			EmailTemplate emailTemplate = new EmailTemplate();
			emailTemplate.setTemplateName(emailTemplateForm.getTemplateName());
			emailTemplate.setText(emailTemplateForm.getText());
			dao.addSMSTemplate(emailTemplate);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "save";
	}

	public String edit() throws Exception {
		Connection connection = null;
		try {
			int id = Integer.parseInt(request.getParameter("selectedid"));
			connection = Connection_provider.getconnection();
			SMSTemplateDAO dao = new JDBCSMSTemplateDao(connection);
			EmailTemplate template = dao.getEmailTemplate(id);
			emailTemplateForm.setTemplateName(template.getTemplateName());
			emailTemplateForm.setText(template.getText());
			emailTemplateForm.setId(template.getId());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "edit";
	}

	public String update() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			EmailTemplate emailTemplate = new EmailTemplate();
			emailTemplate.setId(emailTemplateForm.getId());
			emailTemplate.setTemplateName(emailTemplateForm.getTemplateName());
			emailTemplate.setText(emailTemplateForm.getText());
			SMSTemplateDAO dao = new JDBCSMSTemplateDao(connection);
			dao.updateSMSTemplate(emailTemplate);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "save";
	}

	public String delete() throws Exception {

		Connection connection = null;
		try {
			int id=Integer.parseInt(request.getParameter("selectedid"));
			connection = Connection_provider.getconnection();
			SMSTemplateDAO dao = new JDBCSMSTemplateDao(connection);
			dao.deleteSMSTemplate(id);		
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally{
			connection.close();
		}

		return "save";
	}

}
