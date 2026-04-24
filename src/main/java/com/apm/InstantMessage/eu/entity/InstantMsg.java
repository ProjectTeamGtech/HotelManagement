package com.apm.InstantMessage.eu.entity;

import java.util.ArrayList;

public class InstantMsg {
	private int id;
	private String name;
	private String emailId;
	private String designation;
	private String toEmailId;
	private String ccEmailId;
	private String subject;
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
	private String status;
	private String senderUserId;
	private String receiverUserId;
	private String chatText;

	
	
	public String getChatText() {
		return chatText;
	}
	public void setChatText(String chatText) {
		this.chatText = chatText;
	}
	public String getSenderUserId() {
		return senderUserId;
	}
	public void setSenderUserId(String senderUserId) {
		this.senderUserId = senderUserId;
	}
	
	public String getReceiverUserId() {
		return receiverUserId;
	}
	public void setReceiverUserId(String receiverUserId) {
		this.receiverUserId = receiverUserId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
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
	
}
