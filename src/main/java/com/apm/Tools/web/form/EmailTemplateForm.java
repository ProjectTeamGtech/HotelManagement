package com.apm.Tools.web.form;

import java.io.File;
import java.util.ArrayList;

import com.apm.Tools.eu.entity.EmailTemplate;

public class EmailTemplateForm {

	private int id;

	private String templateName;

	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	private String headerNote;

	private String body1Note;

	private String body2Note;

	private String footerNote;

	private String pagerecords;

	private int totalRecords;

	private String message;

	ArrayList<EmailTemplate> emailTemplateList;

	ArrayList<EmailTemplate> templateNameList;

	private File userImage;

	private String userImageFileName;

	private String templateData;

	private String templateId;

	private String userName;

	private String checkMailSend;

	private ArrayList<EmailTemplate> adDurationList = null;

	private String adDuration;

	public ArrayList<EmailTemplate> getAdDurationList() {
		return adDurationList;
	}

	public void setAdDurationList(ArrayList<EmailTemplate> adDurationList) {
		this.adDurationList = adDurationList;
	}

	public String getAdDuration() {
		return adDuration;
	}

	public void setAdDuration(String adDuration) {
		this.adDuration = adDuration;
	}

	public String getCheckMailSend() {
		return checkMailSend;
	}

	public void setCheckMailSend(String checkMailSend) {
		this.checkMailSend = checkMailSend;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTemplateData() {
		return templateData;
	}

	public void setTemplateData(String templateData) {
		this.templateData = templateData;
	}

	public File getUserImage() {
		return userImage;
	}

	public void setUserImage(File userImage) {
		this.userImage = userImage;
	}

	public String getUserImageContentType() {
		return userImageContentType;
	}

	public void setUserImageContentType(String userImageContentType) {
		this.userImageContentType = userImageContentType;
	}

	private String userImageContentType;

	ArrayList<EmailTemplate> logoList;

	public ArrayList<EmailTemplate> getLogoList() {
		return logoList;
	}

	public void setLogoList(ArrayList<EmailTemplate> logoList) {
		this.logoList = logoList;
	}

	public String getUserImageFileName() {
		return userImageFileName;
	}

	public void setUserImageFileName(String userImageFileName) {
		this.userImageFileName = userImageFileName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ArrayList<EmailTemplate> getTemplateNameList() {
		return templateNameList;
	}

	public void setTemplateNameList(ArrayList<EmailTemplate> templateNameList) {
		this.templateNameList = templateNameList;
	}

	public ArrayList<EmailTemplate> getEmailTemplateList() {
		return emailTemplateList;
	}

	public void setEmailTemplateList(ArrayList<EmailTemplate> emailTemplateList) {
		this.emailTemplateList = emailTemplateList;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getHeaderNote() {
		return headerNote;
	}

	public void setHeaderNote(String headerNote) {
		this.headerNote = headerNote;
	}

	public String getBody1Note() {
		return body1Note;
	}

	public void setBody1Note(String body1Note) {
		this.body1Note = body1Note;
	}

	public String getBody2Note() {
		return body2Note;
	}

	public void setBody2Note(String body2Note) {
		this.body2Note = body2Note;
	}

	public String getPagerecords() {
		return pagerecords;
	}

	public void setPagerecords(String pagerecords) {
		this.pagerecords = pagerecords;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFooterNote() {
		return footerNote;
	}

	public void setFooterNote(String footerNote) {
		this.footerNote = footerNote;
	}

	private String emailConfigureId;

	private String emailUserName;

	private String emailPassword;

	private String emailConfirmPassword;

	private String emailHostName;

	public String getEmailConfigureId() {
		return emailConfigureId;
	}

	public void setEmailConfigureId(String emailConfigureId) {
		this.emailConfigureId = emailConfigureId;
	}

	public String getEmailUserName() {
		return emailUserName;
	}

	public void setEmailUserName(String emailUserName) {
		this.emailUserName = emailUserName;
	}

	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	public String getEmailConfirmPassword() {
		return emailConfirmPassword;
	}

	public void setEmailConfirmPassword(String emailConfirmPassword) {
		this.emailConfirmPassword = emailConfirmPassword;
	}

	public String getEmailHostName() {
		return emailHostName;
	}

	public void setEmailHostName(String emailHostName) {
		this.emailHostName = emailHostName;
	}

	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
