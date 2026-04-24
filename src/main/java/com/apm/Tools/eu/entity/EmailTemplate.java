package com.apm.Tools.eu.entity;

import java.io.File;

public class EmailTemplate {

	private int id;

	private String templateName;

	private String headerNote;

	private String body1Note;

	private String body2Note;

	private String footerNote;

	private String title;

	private File userImage;

	private String templateData;

	private String templateId;

	private String userName;

	private String text;

	private String telemedPharmacy;
	
	
	public String getTelemedPharmacy() {
		return telemedPharmacy;
	}

	public void setTelemedPharmacy(String telemedPharmacy) {
		this.telemedPharmacy = telemedPharmacy;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	private String adDuration;

	public String getAdDuration() {
		return adDuration;
	}

	public void setAdDuration(String adDuration) {
		this.adDuration = adDuration;
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

	public String getUserImageFileName() {
		return userImageFileName;
	}

	public void setUserImageFileName(String userImageFileName) {
		this.userImageFileName = userImageFileName;
	}

	public String getUserImageContentType() {
		return userImageContentType;
	}

	public void setUserImageContentType(String userImageContentType) {
		this.userImageContentType = userImageContentType;
	}

	private String userImageFileName;

	private String userImageContentType;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

}
