package com.apm.Tools.eu.bi;

import java.util.ArrayList;

import com.apm.Tools.eu.entity.EmailTemplate;
import com.apm.common.utils.Pagination;

public interface EmailTemplateDAO {	
	
	int getTotalEmailTemplateCount();

	ArrayList<EmailTemplate> getEmailTemplateList(Pagination pagination);

	int saveEmailTemplate(EmailTemplate emailTemplate, int id);

	EmailTemplate getEmailTemplate(int id, EmailTemplate emailTemplate);

	int updateEmailTemplate(EmailTemplate emailTemplate, int id);

	int deleteEmailTemplate(int id, EmailTemplate emailTemplate);

	EmailTemplate getEmailTemplatePreview(int selectedid);

	ArrayList<EmailTemplate> getEmailTemplateNameList();

	int getTotalLogoCount();

	ArrayList<EmailTemplate> getLogoList(Pagination pagination);

	int saveLogo(String logoName);

	EmailTemplate getLogoDetails(int id);

	int updateLogo(EmailTemplate emailTemplate);

	int deleteLogo(int id);

	int getLatestAppointmentId(String id);

	String getEmailTemplateData(String tempId);

	ArrayList<EmailTemplate> getEmailTemplateNameList(String userName,int id);

	int SaveEmailConfugureInfo(EmailTemplate emailTemplate, int id);

	EmailTemplate getEmailConfigureDetails(int i);

	int updateEmailConfugureInfo(EmailTemplate emailTemplate, int id);

	EmailTemplate getEditEmailConfigureDetails(int selectedId);

	int getLatestAppointmentIdByPract(String userid);

	String getClientId(String userid);

	int getGPId(int selectedid);

	/*int updatePasswordEmailConfugure(EmailTemplate emailTemplate, int id);
*/
	String getUserNameOfTemplate(String tempId);

	String showEmailConfigurePwd(int id);

	ArrayList<EmailTemplate> getFileNameBytemplate(String tempId);

	int deleteAttachment(String id);

	
	

	

	

}
