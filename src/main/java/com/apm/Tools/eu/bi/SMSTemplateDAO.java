package com.apm.Tools.eu.bi;

import java.util.ArrayList;

import com.apm.Tools.eu.entity.EmailTemplate;

public interface SMSTemplateDAO {

	public ArrayList<EmailTemplate> getAllSMSTemplates();

	public int addSMSTemplate(EmailTemplate emailTemplate);
	
	public EmailTemplate getEmailTemplate(int id);
	
	public int updateSMSTemplate(EmailTemplate emailTemplate);
	
	public int deleteSMSTemplate(int id);
}
