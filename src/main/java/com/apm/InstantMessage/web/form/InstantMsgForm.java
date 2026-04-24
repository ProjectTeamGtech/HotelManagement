package com.apm.InstantMessage.web.form;

import java.io.File;
import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.InstantMessage.eu.entity.InstantMsg;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.Tools.eu.entity.EmailTemplate;

/**
 * @author Windows User
 *
 */
public class InstantMsgForm {
	private ArrayList<InstantMsg> inboxList;
	private File[] fileUpload;
    private String[] fileUploadFileName;
    private String[] fileUploadContentType;
    private int id;
	private String name;
	private String emailId;
	private String designation;
	private String toEmailId;
	private String ccEmailId;
	private String subject;
	private String cc;
	private String to;
	private String clientid;
	private String treatmentEpisodeId;
	private String body;
	private String userId;
	private String from;
	private int senderId;
	private int receiverId;
	private String senderEmailId;
	private String receiverEmailId;
	private String date;
	private String time;
	private String attachFileName;
	private ArrayList<InstantMsg>attachmentslist;
	private ArrayList<InstantMsg>sendMailList;
	private ArrayList<InstantMsg>draftList;
	private int inboxTotal;
	private int sentMailTotal;
	private int draftTotal;
	private String status;
	private String actionStatus;
	private String user;
	private String editAppointId;
	
	
	private ArrayList<EmailTemplate> templateNameList;
	
	ArrayList<Client> allPatientList = new ArrayList<Client>();
	
	ArrayList<UserProfile> allUserList = new ArrayList<UserProfile>();
	
	private ArrayList<ThirdParty> surgeryCompanyNameList;
	
	private ArrayList<ThirdParty> tpCompanyNameList;
	
	
	
	
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public ArrayList<ThirdParty> getTpCompanyNameList() {
		return tpCompanyNameList;
	}
	public void setTpCompanyNameList(ArrayList<ThirdParty> tpCompanyNameList) {
		this.tpCompanyNameList = tpCompanyNameList;
	}
	
	
	public ArrayList<ThirdParty> getSurgeryCompanyNameList() {
		return surgeryCompanyNameList;
	}
	public void setSurgeryCompanyNameList(
			ArrayList<ThirdParty> surgeryCompanyNameList) {
		this.surgeryCompanyNameList = surgeryCompanyNameList;
	}
	
	public ArrayList<Client> getAllPatientList() {
		return allPatientList;
	}

	public void setAllPatientList(ArrayList<Client> allPatientList) {
		this.allPatientList = allPatientList;
	}

	public ArrayList<UserProfile> getAllUserList() {
		return allUserList;
	}

	public void setAllUserList(ArrayList<UserProfile> allUserList) {
		this.allUserList = allUserList;
	}

	public ArrayList<ThirdParty> getAjaxTypeNameList() {
		return ajaxTypeNameList;
	}

	public void setAjaxTypeNameList(ArrayList<ThirdParty> ajaxTypeNameList) {
		this.ajaxTypeNameList = ajaxTypeNameList;
	}

	ArrayList<ThirdParty> ajaxTypeNameList = new ArrayList<ThirdParty>();
	
	

	public ArrayList<EmailTemplate> getTemplateNameList() {
		return templateNameList;
	}

	public void setTemplateNameList(ArrayList<EmailTemplate> templateNameList) {
		this.templateNameList = templateNameList;
	}

	public String getActionStatus() {
		return actionStatus;
	}

	public void setActionStatus(String actionStatus) {
		this.actionStatus = actionStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<InstantMsg> getSendMailList() {
		return sendMailList;
	}

	public void setSendMailList(ArrayList<InstantMsg> sendMailList) {
		this.sendMailList = sendMailList;
	}

	public ArrayList<InstantMsg> getDraftList() {
		return draftList;
	}

	public void setDraftList(ArrayList<InstantMsg> draftList) {
		this.draftList = draftList;
	}

	public int getInboxTotal() {
		return inboxTotal;
	}

	public void setInboxTotal(int inboxTotal) {
		this.inboxTotal = inboxTotal;
	}

	

	public int getSentMailTotal() {
		return sentMailTotal;
	}

	public void setSentMailTotal(int sentMailTotal) {
		this.sentMailTotal = sentMailTotal;
	}

	public int getDraftTotal() {
		return draftTotal;
	}

	public void setDraftTotal(int draftTotal) {
		this.draftTotal = draftTotal;
	}

	
	
    public ArrayList<InstantMsg> getAttachmentslist() {
		return attachmentslist;
	}

	public void setAttachmentslist(ArrayList<InstantMsg> attachmentslist) {
		this.attachmentslist = attachmentslist;
	}

	public String getAttachFileName() {
		return attachFileName;
	}

	public void setAttachFileName(String attachFileName) {
		this.attachFileName = attachFileName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	public String getSenderEmailId() {
		return senderEmailId;
	}

	public void setSenderEmailId(String senderEmailId) {
		this.senderEmailId = senderEmailId;
	}

	public String getReceiverEmailId() {
		return receiverEmailId;
	}

	public void setReceiverEmailId(String receiverEmailId) {
		this.receiverEmailId = receiverEmailId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getToEmailId() {
		return toEmailId;
	}

	public void setToEmailId(String toEmailId) {
		this.toEmailId = toEmailId;
	}

	public String getCcEmailId() {
		return ccEmailId;
	}

	public void setCcEmailId(String ccEmailId) {
		this.ccEmailId = ccEmailId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public File[] getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File[] fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String[] getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String[] fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	public String[] getFileUploadContentType() {
		return fileUploadContentType;
	}

	public void setFileUploadContentType(String[] fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}

	

	public ArrayList<InstantMsg> getInboxList() {
		return inboxList;
	}

	public void setInboxList(ArrayList<InstantMsg> inboxList) {
		this.inboxList = inboxList;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public String getTreatmentEpisodeId() {
		return treatmentEpisodeId;
	}
	public void setTreatmentEpisodeId(String treatmentEpisodeId) {
		this.treatmentEpisodeId = treatmentEpisodeId;
	}
	public String getEditAppointId() {
		return editAppointId;
	}
	public void setEditAppointId(String editAppointId) {
		this.editAppointId = editAppointId;
	}

	
	
}
