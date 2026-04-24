package com.apm.InstantMessage.web.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;






































import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.web.action.EmbeddedImageEmailUtil;
import com.apm.DiaryManagement.web.action.SendSms;
import com.apm.InstantMessage.eu.bi.InstantMsgDAO;
import com.apm.InstantMessage.eu.blogic.jdbc.JDBCInstantMsgDAO;
import com.apm.InstantMessage.eu.entity.InstantMsg;
import com.apm.InstantMessage.web.form.InstantMsgForm;
import com.apm.Log.eu.bi.EmailLetterLogDAO;
import com.apm.Log.eu.blogic.jdbc.JDBCEmailLetterLogDAO;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.ThirdParties.eu.bi.TPADao;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCTPADao;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.Tools.eu.bi.EmailTemplateDAO;
import com.apm.Tools.eu.blogic.jdbc.JDBCEmailTemplateDAO;
import com.apm.Tools.eu.entity.EmailTemplate;
import com.apm.Tools.web.action.AllTemplateAction;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import atg.taglib.json.util.JSONObject;



public class InstantMsgAction extends BaseAction implements Preparable, ModelDriven<InstantMsgForm>{
	
	InstantMsgForm instantMsgForm = new InstantMsgForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);

	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	private Pagination pagination = new Pagination(10, 1);
	
	//ArrayList<EmailTemplate> templateNameList = new ArrayList<EmailTemplate>();
	String userName = "";
	
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
			
			InstantMsgDAO instantMsgDAO = new JDBCInstantMsgDAO(connection);
			String emailId = instantMsgDAO.getUserEmailId(loginInfo.getId());
			ArrayList<InstantMsg> inboxList = new ArrayList<InstantMsg>();
			inboxList =	instantMsgDAO.getAllInbox(loginInfo.getId()); 
			instantMsgForm.setInboxList(inboxList);
			int unreadMailtotal = instantMsgDAO.getUnreadMailTotal(loginInfo.getId());
			instantMsgForm.setInboxTotal(unreadMailtotal);
			session.setAttribute("inboxList", inboxList);
			ArrayList<InstantMsg> sendMailList = new ArrayList<InstantMsg>();
			sendMailList = instantMsgDAO.getsendMailList(loginInfo.getId());
			instantMsgForm.setSentMailTotal(sendMailList.size());
			ArrayList<InstantMsg>draftList = instantMsgDAO.getDraftmail(loginInfo.getId());
			instantMsgForm.setDraftList(draftList);
			instantMsgForm.setDraftTotal(draftList.size());
			
			/*ArrayList<EmailTemplate> templateNameList = new ArrayList<EmailTemplate>();
			instantMsgForm.setTemplateNameList(templateNameList);*/
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		
		return "allMailsSucess";
	}
	public String getInbox() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			
			InstantMsgDAO instantMsgDAO = new JDBCInstantMsgDAO(connection);
			ArrayList<InstantMsg> inboxList = new ArrayList<InstantMsg>();
			inboxList =	instantMsgDAO.getAllInbox(loginInfo.getId()); 
			instantMsgForm.setInboxList(inboxList);
			int unreadMailtotal = instantMsgDAO.getUnreadMailTotal(loginInfo.getId());
			instantMsgForm.setInboxTotal(unreadMailtotal);
			ArrayList<InstantMsg> sendMailList = new ArrayList<InstantMsg>();
			sendMailList = instantMsgDAO.getsendMailList(loginInfo.getId());
			instantMsgForm.setSentMailTotal(sendMailList.size());
			session.setAttribute("inboxList", inboxList);
			ArrayList<InstantMsg>draftList = instantMsgDAO.getDraftmail(loginInfo.getId());
			instantMsgForm.setDraftList(draftList);
			instantMsgForm.setDraftTotal(draftList.size());

			
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		return "allMailsSucess";
		
	}
	
	public String sentMailListDisplay() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			
			InstantMsgDAO instantMsgDAO = new JDBCInstantMsgDAO(connection);
			ArrayList<InstantMsg> sendMailList = new ArrayList<InstantMsg>();
			sendMailList = instantMsgDAO.getsendMailList(loginInfo.getId());
			instantMsgForm.setSendMailList(sendMailList);
			instantMsgForm.setSentMailTotal(sendMailList.size());
			StringBuffer str = new StringBuffer();
			
			str.append("<table class='table table-hover table-condensed table-bordered table-striped ' > ");
			str.append("<tbody>");
			for(InstantMsg instantMsg:sendMailList){
			str.append("<tr>");
			str.append("<td onclick = 'showSentMailDetails("+instantMsg.getId()+")'>To: "+instantMsg.getReceiverEmailId()+"  "+instantMsg.getSubject()+" - "+instantMsg.getDate()+" "+instantMsg.getTime()+" </td>");
			str.append("</tr>");
			}
			str.append("</tbody>");
			str.append("</table>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+str.toString()+""); 
			
			
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		return null;
	}
	
	public String showChats() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			
			InstantMsgDAO instantMsgDAO = new JDBCInstantMsgDAO(connection);
			ArrayList<InstantMsg> chatList = new ArrayList<InstantMsg>();
			chatList = instantMsgDAO.getChatList(loginInfo.getId());
			StringBuffer str = new StringBuffer();
			
			str.append("<table class='table table-hover table-condensed table-bordered table-striped ' > ");
			str.append("<tbody>");
			for(InstantMsg instantMsg:chatList){
			str.append("<tr>");
			str.append("<td onclick = showChatDetails('"+instantMsg.getId()+"','"+instantMsg.getSenderUserId()+"','"+instantMsg.getReceiverUserId()+"')>"+instantMsg.getSenderUserId()+"   "+instantMsg.getChatText()+"</td>");
			str.append("</tr>");
			}
			str.append("</tbody>");
			str.append("</table>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+str.toString()+""); 
			
			
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		return null;
	}
	
	public String showChatDetails() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		int id = Integer.parseInt(request.getParameter("id"));
		String senderUserId = request.getParameter("senderUserId");
		String receiverUserId = request.getParameter("receiverUserId");
		  Connection connection = null;
			try{
				connection = Connection_provider.getconnection();
				InstantMsgDAO instantMsgDAO = new JDBCInstantMsgDAO(connection);
				ArrayList<InstantMsg>conversationList = new ArrayList<InstantMsg>();
				conversationList = instantMsgDAO.getChatConversation(id,senderUserId,receiverUserId);
				
				StringBuffer str = new StringBuffer();
				
			/*	str.append("<div style = 'background-color: rgb(242, 246, 250);height: 500px;'>");
				str.append("<br>");
				str.append("<b>"+instantMsg.getSubject()+"</b>");
				str.append("<hr style = 'border: 1px solid #ccc;'>");
				str.append("<div class = 'col-lg-10 col-md-10'>");
				str.append("<b>"+instantMsg.getSenderEmailId()+"</b>");
				str.append("</div>");
				str.append("<div class = 'col-lg-2 col-md-2'>");
				str.append("<b>"+instantMsg.getDate()+" "+instantMsg.getTime()+" </b>");
				str.append("</div>");
				str.append("<br>");
				str.append("<br>");
				str.append("<div class = 'col-lg-12 col-md-12'>");
				str.append(instantMsg.getBody());*/
				str.append("<div style = 'background-color: rgb(242, 246, 250);height: 500px;overflow: auto;'>");
				str.append("<br>");
				str.append("<b>Chat with  "+senderUserId+"</b>");
				str.append("<br>");
				str.append("<br>");
				int count = 0;
				for(InstantMsg im:conversationList){
					
					if(count%2==0){
						str.append("<div class = 'col-lg-9 col-md-9'>");
						str.append("<b>"+im.getSenderUserId()+"</b>");
						str.append("</div>");
						str.append("<div class = 'col-lg-3 col-md-3'>");
						str.append(""+im.getDate()+" "+im.getTime()+"");
						str.append("</div>");
						str.append("<br>");
						str.append("<div class = 'col-lg-9 col-md-9'>");
						str.append(""+im.getChatText()+"");
						str.append("</div>");
						str.append("<br>");
						
						
					}
					else{
					
						str.append("<div class = 'col-lg-9 col-md-9'>");
						str.append("<b>"+im.getReceiverUserId()+"</b>");
						str.append("</div>");
						str.append("<div class = 'col-lg-3 col-md-3'>");
						str.append(""+im.getDate()+" "+im.getTime()+"");
						str.append("</div>");
						str.append("<br>");
						str.append("<div class = 'col-lg-9 col-md-9'>");
						str.append(""+im.getChatText()+"");
						str.append("</div>");
						str.append("<br>");
						
					}
					
					count++;
				}
				str.append("</div>");
			
				
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				
				response.getWriter().write(""+str.toString()+""); 
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally {
				connection.close();
			}
		return null;
	}
	
public String showDraftMailList() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		
		InstantMsgDAO instantMsgDAO = new JDBCInstantMsgDAO(connection);
		ArrayList<InstantMsg> draftList = new ArrayList<InstantMsg>();
		draftList = instantMsgDAO.getDraftmail(loginInfo.getId());
		ArrayList<InstantMsg> attachmentList = new ArrayList<InstantMsg>();
		
	
		StringBuffer str = new StringBuffer();
		
		str.append("<table class='table table-hover table-condensed table-bordered table-striped ' > ");
		str.append("<tbody>");
		for(InstantMsg instantMsg:draftList){
		str.append("<tr>");
		str.append("<td class='draft' data-id='"+ instantMsg.getId() +"' data-receivermailid = '"+instantMsg.getReceiverEmailId()+"' data-sub= '"+instantMsg.getSubject()+"' data-emailbody = '"+instantMsg.getBody()+"'> Draft: "+instantMsg.getReceiverEmailId()+"  "+instantMsg.getSubject()+" - "+instantMsg.getDate()+" "+instantMsg.getTime()+" </td>");
		str.append("</tr>");
		
		
		}
		str.append("</tbody>");
		str.append("</table>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().write(""+str.toString()+""); 
		
		
		
		
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		connection.close();
	}
	return null;
		
	}

	public String showDraftAttachments() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		int id = Integer.parseInt(request.getParameter("id"));
		  Connection connection = null;
			try{
				connection = Connection_provider.getconnection();
				InstantMsgDAO instantMsgDAO = new JDBCInstantMsgDAO(connection);
				InstantMsg instantMsg = new InstantMsg();
				
				ArrayList<InstantMsg> attachmentList = new ArrayList<InstantMsg>();
				attachmentList = instantMsgDAO.getDraftAttachmentList(id);
				
				//InstantMsg [] draftFile =  attachmentList.toArray(new InstantMsg[attachmentList.size()]);
	        	 String[] draftFile = new String[attachmentList.size()];

//				T [] countries = list.toArray(new T[list.size()]);
				StringBuffer str = new StringBuffer();
				
				if(attachmentList.size()!=0){
					
					str.append("<br>");
					 String filePath = request.getRealPath("/liveData/emailAttachments/");
					 for(InstantMsg im : attachmentList){
						str.append("<a href = '"+filePath+"/"+im.getAttachFileName()+"'>"+im.getAttachFileName()+"</a>");
						str.append("<a href = 'downloadAttachInstantMsg?filename="+im.getAttachFileName()+"' style = 'margin-left: 20px;' >Download</a>");
						//str.append(""+im.getAttachFileName()+"");
						str.append("<br>");
					}
				}
				
				session.setAttribute("draftId", id);
				session.setAttribute("draftAttachments", attachmentList);
				session.setAttribute("draftFile", draftFile);
				String emailType = "DraftEmail";
				session.setAttribute("emailType", emailType);
				str.append("</div>");
				str.append("</div>");
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				
				response.getWriter().write(""+str.toString()+""); 
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally {
				connection.close();
			}
		return null;
	}
	
	
	
	public String uploadSendMail() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		String curUrl = request.getContextPath();
		String actionstatus = instantMsgForm.getActionStatus();
		String to = instantMsgForm.getToEmailId();
        String cc = "";
        String subject = instantMsgForm.getSubject();
        String bodyText = instantMsgForm.getBody();
        Email email = new Email();
        Connection connection = null;
        int activity = 1;
		try{
			connection = Connection_provider.getconnection();
			
        if(instantMsgForm.getFileUploadFileName()!=null ){
       
        	 String[] attachFiles = new String[instantMsgForm.getFileUpload().length];
		
		 // copy the uploaded files into pre-configured location
        for (int i = 0; i < instantMsgForm.getFileUpload().length; i++) {
            File uploadedFile = instantMsgForm.getFileUpload()[i];
            String fileName = instantMsgForm.getFileUploadFileName()[i];
            String filePath = request.getRealPath("/liveData/emailAttachments/");
            File destFile = new File(filePath + File.separator + fileName);
            System.out.println("Server path:" + filePath);
            try {
                FileUtils.copyFile(uploadedFile, destFile);
            } catch (IOException ex) {
                System.out.println("Could not copy file " + fileName);
                ex.printStackTrace();
            }
        }
        
        for (int i = 0; i < instantMsgForm.getFileUpload().length; i++) {
        	
        	 String fileName = instantMsgForm.getFileUploadFileName()[i];
        	 String filePath = request.getRealPath("/liveData/emailAttachments/");
        	 attachFiles[i] = "/"+filePath+"/"+fileName+"";
        	 
        }
		
        String[] combine=null;;
        String[] draftAttachFiles = (String[]) session.getAttribute("draftFile");
        String emailType = (String) session.getAttribute("emailType");
        System.out.println(emailType);
        
        String draft= "DraftEmail";
        if(emailType==null){
        	combine = attachFiles;
        }
        else if(emailType.equals(draft)){
        	 combine = (String [])join(attachFiles,draftAttachFiles);
        }
        
        String type = "Letter";
		int appointmentid = 0;
		int invoiceid = 0;
		EmailLetterLog emailLetterLog = new EmailLetterLog();
		emailLetterLog.setAppointmentid(appointmentid);
		emailLetterLog.setInvoiceid(invoiceid);
		emailLetterLog.setType(type);
        
        email.sendMailAttachment(connection,to, cc, subject, bodyText, combine,loginInfo,emailLetterLog);
       
        
        }
        else{
        	
        	/*String twitter = request.getRealPath("/img/Entypo_f309(0)_32.png/");      	    
       	    File file = new File(twitter);
        	String img = "<a href='www.twitter.com'><img src=\"cid:image1\" /></a>";
        	String fb = request.getRealPath("/img/Entypo_f30c(0)_32.png/");      	    
       	    file = new File(fb);
        	String img1 = "<a href='www.facebook.com'><img src=\"cid:image2\" /></a>";
        	String gml = request.getRealPath("/img/Entypo_f30f(0)_32.png/");      	    
       	    file = new File(gml);
        	String img2 = "<a href='accounts.google.com'><img src=\"cid:image3\" /></a>";
        	String data = bodyText+"   "+img+" "+img1+" "+img2;
        	Map<String, String> inlineImages = new HashMap<String, String>();
		    inlineImages.put("image1", twitter);
		    inlineImages.put("image2", fb);
		    inlineImages.put("image3", gml);*/
        	
        	Map<String, String> inlineImages = new HashMap<String, String>();
        	
        	String type = "Letter";
			int appointmentid = 0,invoiceid = 0;
			EmailLetterLog emailLetterLog = new EmailLetterLog();
			emailLetterLog.setAppointmentid(appointmentid);
			emailLetterLog.setInvoiceid(invoiceid);
			emailLetterLog.setType(type);
			
        	email.sendMail(connection,to, cc, subject, bodyText,loginInfo,inlineImages,emailLetterLog);
        }
      
			InstantMsgDAO instantMsgDAO = new JDBCInstantMsgDAO(connection);
			
			String userName = request.getParameter("userName");		
			if(userName == null){
				userName = "";
			}
			
		if(userName.equalsIgnoreCase("Client")){
			int clientId = Integer.parseInt(request.getParameter("id"));
	        ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
	        String CheckMailToSend = clinicDAO.IsMailSend(loginInfo.getId());
	        if(CheckMailToSend.equals("true")){
			int result1 = instantMsgDAO.saveActivity(clientId,activity);
	        }
		}
		
			String emailId = instantMsgDAO.getUserEmailId(loginInfo.getId());
			String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
			String datetemp[] = currentDate.split(" ");
			String temp1[] = datetemp[0].split("-");
			String date = temp1[0] + "/" + temp1[1] + "/" + temp1[2];

			InstantMsg instantMsg = new InstantMsg();
			instantMsg.setSenderId(loginInfo.getId());
			instantMsg.setSenderEmailId(emailId);
			instantMsg.setReceiverEmailId(to);
			instantMsg.setSubject(subject);
			instantMsg.setBody(bodyText);
			instantMsg.setDate(date);
			instantMsg.setTime(datetemp[2]+" "+datetemp[3]);
			if(actionstatus.equals("Cancel")){
				int id = instantMsgDAO.saveDraft(instantMsg);
				
				
				if(instantMsgForm.getFileUploadFileName()!=null ){
					for (int i = 0; i < instantMsgForm.getFileUpload().length; i++) {
			            String fileName = instantMsgForm.getFileUploadFileName()[i];
			            int result = instantMsgDAO.saveDraftAttachmentFiles(id,fileName);
			        }
					
				}
				
			}
			
			else{
				
			int id = instantMsgDAO.saveSendMessage(instantMsg);
			
			String temp[] = to.split(",");
			for(int i=0;i<temp.length;i++){
				instantMsg.setSenderId(loginInfo.getId());
				instantMsg.setSenderEmailId(emailId);
				instantMsg.setSubject(subject);
				instantMsg.setBody(bodyText);
				int reciverId = instantMsgDAO.getRecieverId(temp[i]);
				instantMsg.setReceiverId(reciverId);
				instantMsg.setReceiverEmailId(temp[i]);
				instantMsg.setId(id);
				instantMsg.setDate(date);
				instantMsg.setTime(datetemp[2]+" "+datetemp[3]);
				int inbox = instantMsgDAO.saveInbox(instantMsg);
			}	
			
			if(instantMsgForm.getFileUploadFileName()!=null ){
			for (int i = 0; i < instantMsgForm.getFileUpload().length; i++) {
	            String fileName = instantMsgForm.getFileUploadFileName()[i];
	            int result = instantMsgDAO.saveEmailAttachmentFiles(id,fileName);
	        }
			String emailType = (String) session.getAttribute("emailType");
		    String draft= "DraftEmail";
			if(emailType.equals(draft)){
			String draftId = (String) session.getAttribute("draftId");
			int del = instantMsgDAO.deleteDraftMail(id);
			int delattachment = instantMsgDAO.deleteDraftMailattachments(id);
			ArrayList<InstantMsg> draftFile = (ArrayList<InstantMsg>) session.getAttribute("draftAttachments");
			if(draftFile.size()!=0){
				for (InstantMsg i : draftFile) {
		            String fileName = i.getAttachFileName();
		            int result = instantMsgDAO.saveEmailAttachmentFiles(id,fileName);
		        }
				
			}
			}
			
		}
			String emailType = (String) session.getAttribute("emailType");
		    String draft= "DraftEmail";
			if(emailType.equals(draft)){
			int draftId = (Integer) session.getAttribute("draftId");
			int del = instantMsgDAO.deleteDraftMail(draftId);
			int delattachment = instantMsgDAO.deleteDraftMailattachments(draftId);
			}
			
		}
        
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		getInbox();
		return "allMailsSucess";
	}
	public String showDetailsOfInboxMail() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		int id = Integer.parseInt(request.getParameter("id"));
		  Connection connection = null;
			try{
				connection = Connection_provider.getconnection();
				InstantMsgDAO instantMsgDAO = new JDBCInstantMsgDAO(connection);
				InstantMsg instantMsg = new InstantMsg();
				instantMsg = instantMsgDAO.getInboxMailDetails(id);
				int updatestatus = instantMsgDAO.updateStatus(id);
				
				int sendMailId = instantMsgDAO.getSendMailId(id);
				ArrayList<InstantMsg> attachmentList = new ArrayList<InstantMsg>();
				attachmentList = instantMsgDAO.getAttachmentList(sendMailId);
				StringBuffer str = new StringBuffer();
				
				str.append("<div style = 'background-color: rgb(242, 246, 250);height: 500px;'>");
				str.append("<br>");
				str.append("<b>"+instantMsg.getSubject()+"</b>");
				str.append("<hr style = 'border: 1px solid #ccc;'>");
				str.append("<div class = 'col-lg-10 col-md-10'>");
				str.append("<b>"+instantMsg.getSenderEmailId()+"</b>");
				str.append("</div>");
				str.append("<div class = 'col-lg-2 col-md-2'>");
				str.append("<b>"+instantMsg.getDate()+" "+instantMsg.getTime()+" </b>");
				str.append("</div>");
				str.append("<br>");
				str.append("<br>");
				str.append("<div class = 'col-lg-12 col-md-12'>");
				str.append(instantMsg.getBody());
				str.append("</div>");
				str.append("<div class = 'col-lg-8 col-md-8'>");
				if(attachmentList.size()!=0){
					str.append("<b>Attachments</b>");
					str.append("<br>");
					 String filePath = request.getRealPath("/liveData/emailAttachments/");
					 for(InstantMsg im : attachmentList){
						str.append("<a href = '"+filePath+"/"+im.getAttachFileName()+"'>"+im.getAttachFileName()+"</a>");
						
						str.append("<a href = 'downloadAttachInstantMsg?filename="+im.getAttachFileName()+"' style = 'margin-left: 20px;' >Download</a>");
						
						str.append("<br>");
					}
				}
				str.append("</div>");
				str.append("</div>");
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				
				response.getWriter().write(""+str.toString()+""); 
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally {
				connection.close();
			}
		return null;
	}
	public String downloadAttach() throws IOException{
		if(!verifyLogin(request)){
			return "login";
		}
		String userImageFileName = request.getParameter("filename");
		FileInputStream in = null;
		ServletOutputStream out = null;
		HttpServletRequest servletRequest=(HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
		 File file=new File(servletRequest.getRealPath("/liveData/emailAttachments/"+userImageFileName+""));
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

	public String showDetailsOfSentMail() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		int id = Integer.parseInt(request.getParameter("id"));
		  Connection connection = null;
			try{
				connection = Connection_provider.getconnection();
				InstantMsgDAO instantMsgDAO = new JDBCInstantMsgDAO(connection);
				InstantMsg instantMsg = new InstantMsg();
				instantMsg = instantMsgDAO.getSentMailDetails(id);
				ArrayList<InstantMsg> attachmentList = new ArrayList<InstantMsg>();
				
				attachmentList = instantMsgDAO.getAttachmentList(id);
				StringBuffer str = new StringBuffer();
				
				str.append("<div style = 'background-color: rgb(242, 246, 250);height: 500px;'>");
				str.append("<br>");
				str.append("<b>"+instantMsg.getSubject()+"</b>");
				str.append("<hr style = 'border: 1px solid #ccc;'>");
				str.append("<div class = 'col-lg-10 col-md-10'>");
				str.append("<b>"+instantMsg.getSenderEmailId()+"</b>");
				str.append("<br>");
				str.append("To"+instantMsg.getReceiverEmailId());
				str.append("</div>");
				str.append("<div class = 'col-lg-2 col-md-2'>");
				str.append("<b>"+instantMsg.getDate()+" "+instantMsg.getTime()+" </b>");
				str.append("</div>");
				str.append("<br>");
				str.append("<br>");
				str.append("<div class = 'col-lg-10 col-md-10'>");
				str.append(instantMsg.getBody());
				str.append("</div>");
				str.append("<div class = 'col-lg-8 col-md-8'>");
				if(attachmentList.size()!=0){
					str.append("<b>Attachments</b>");
					str.append("<br>");
					 String filePath = request.getRealPath("/liveData/emailAttachments/");
					 for(InstantMsg im : attachmentList){
						str.append("<a href = '"+filePath+"/"+im.getAttachFileName()+"'>"+im.getAttachFileName()+"</a>");
						
						str.append("<a href = 'downloadAttachInstantMsg?filename="+im.getAttachFileName()+"' style = 'margin-left: 20px;' >Download</a>");
						

						str.append("<br>");
						
					}
				}
				str.append("</div>");
				str.append("</div>");
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				
				response.getWriter().write(""+str.toString()+""); 
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally {
				connection.close();
			}
		return null;
	}
	
	public static String[] join(String [] ... parms) {
	    // calculate size of target array
	    int size = 0;
	    for (String[] array : parms) {
	      size += array.length;
	    }

	    String[] result = new String[size];

	    int j = 0;
	    for (String[] array : parms) {
	      for (String s : array) {
	        result[j++] = s;
	      }
	    }
	    return result;
	  }
	
	public String searchTemplatePatient() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		String searchClient = request.getParameter("searchText");
		Connection connection = null;
		InstantMsg instantMsg = new InstantMsg();
		//ArrayList<InstantMsg> allUserList = new ArrayList<InstantMsg>();
		ArrayList<Client> allPatientList = new ArrayList<Client>();
		//ArrayList<UserProfile> allUserList = new ArrayList<UserProfile>();
	//	ArrayList<ThirdParty> tpCompanyNameList = new ArrayList<ThirdParty>();
	//	ArrayList<ThirdParty> surgeryCompanyNameList = new ArrayList<ThirdParty>(); 
		
		//String userName = request.getParameter("userName");
		try{
			connection = Connection_provider.getconnection();
			
			//InstantMsgDAO instantMsgDAO = new JDBCInstantMsgDAO(connection);
			
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			//UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			//ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
			
			//if(userName.equalsIgnoreCase("Client")){
				allPatientList = clientDAO.getClient(searchClient,loginInfo.getId(),"");
			/*}
			
			else if(userName.equalsIgnoreCase("Practitioner")){
				allUserList = userProfileDAO.getAllPractitioner(searchClient,loginInfo.getId());
			}
			else if(userName.equalsIgnoreCase("ThirdParty")){
				tpCompanyNameList = thirdPartyDAO.getSearchTPCompanyNameList(searchClient,loginInfo.getId());
			}
			else if(userName.equalsIgnoreCase("GP")){
				surgeryCompanyNameList = thirdPartyDAO.getSearchSurgeryNameList(searchClient,loginInfo.getId());
			}*/
			//allUserList = instantMsgDAO.getAllPatient(loginInfo.getId());
			
			//instantMsgForm.setAllUserList(allUserList);
			instantMsgForm.setAllPatientList(allPatientList);
			//instantMsgForm.setAjaxTypeNameList(tpCompanyNameList);		
			//instantMsgForm.setSurgeryCompanyNameList(surgeryCompanyNameList);
			
			StringBuffer str = new StringBuffer();
			
			str.append("<table class='table table-hover table-condensed table-bordered table-striped ' > ");
			str.append("<thead>");
			str.append("<tr class='bg-info'>");
			str.append("<th>Id</th> ");

			str.append("<th>Name</th> ");
			//str.append("<th>Client Id</th> ");
			str.append("<th>Mobile</th> ");
			str.append("<th>Email</th> ");
			//str.append("<th>PostCode</th> ");
			//str.append("<th>Dob</th> ");

			str.append("</tr>");
			str.append("</thead>");
			
			str.append("<tbody>");
		//	if(userName.equalsIgnoreCase("Client")){				
				for(Client client1:allPatientList){
					String name = client1.getTitle()+" "+client1.getFirstName()+" "+client1.getLastName(); 		
				str.append("<tr>");
				str.append("<td>0000"+client1.getId()+"</td>");
	
				String firstName= client1.getFirstName();
				str.append("<td style='cursor: pointer;'; onclick = setUserDetail('"+client1.getId()+"')>"+name+"</td>");
				//if(client1.getOldclientId()==null){
				//	client1.setOldclientId("");
				//}
				//str.append("<td>"+client1.getOldclientId()+"</td>");
				str.append("<td>"+client1.getMobNo()+"</td>");
				str.append("<td>"+client1.getEmail()+"</td>");
				//str.append("<td>"+client1.getPostCode()+"</td>");
				//str.append("<td>"+client1.getDob()+"</td>");
	
				str.append("</tr>");
				}
				/*}else if(userName.equalsIgnoreCase("Practitioner")){				
				for(UserProfile userProfile:allUserList){
					String name = userProfile.getFirstname()+" "+userProfile.getLastname(); 		
				str.append("<tr>");
				str.append("<td>0000"+userProfile.getId()+"</td>");
	
				String firstName= userProfile.getFirstname();
				str.append("<td style='cursor: pointer;' onclick = setUserDetail('"+userProfile.getId()+"')>"+name+"</td>");
				
				//str.append("<td>"+userProfile.getId()+"</td>");
				str.append("<td>"+userProfile.getMobile()+"</td>");
				str.append("<td>"+userProfile.getEmail()+"</td>");
				//str.append("<td>"+userProfile.get+"</td>");
				//str.append("<td>"+userProfile.getd+"</td>");
	
				str.append("</tr>");
				}
			}else if(userName.equalsIgnoreCase("ThirdParty")){				
				for(ThirdParty thirdParty:tpCompanyNameList){
					String name = thirdParty.getCompanyName(); 		
				str.append("<tr>");
				str.append("<td>0000"+thirdParty.getId()+"</td>");
	
				//String firstName= client1.getFirstName();
			//	str.append("<td style='cursor: pointer;'; onclick = setUserDetail('"+name+"','"+thirdParty.getId()+"')>"+name+"</td>");
				str.append("<td style='cursor: pointer;'; onclick = setUserDetail('"+thirdParty.getId()+"')>"+name+"</td>");
				
				
			//	str.append("<td>"+client1.getOldclientId()+"</td>");
				str.append("<td>"+thirdParty.getCompnyTelephone()+"</td>");
				str.append("<td>"+thirdParty.getCompnyEmail()+"</td>");
				//str.append("<td>"+thirdParty.getPostcode()+"</td>");
				//str.append("<td>"+thirdParty.getd+"</td>");
	
				str.append("</tr>");
				}
			
		}else if(userName.equalsIgnoreCase("GP")){				
			for(ThirdParty thirdParty:surgeryCompanyNameList){
				String companyName = thirdParty.getCompanyName(); 		
			str.append("<tr>");
			str.append("<td>0000"+thirdParty.getId()+"</td>");
			str.append("<td style='cursor: pointer;'; onclick = setUserDetail('"+thirdParty.getId()+"')>"+companyName+"</td>");
			
			str.append("<td>"+thirdParty.getCompnyTelephone()+"</td>");
			str.append("<td>"+thirdParty.getCompnyEmail()+"</td>");
			
			str.append("</tr>");
			}
		}
		*/
			str.append("<tbody>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+str.toString()+""); 
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return null;
	}
	
	public String showUserList() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		InstantMsg instantMsg = new InstantMsg();
		//ArrayList<InstantMsg> allUserList = new ArrayList<InstantMsg>();
		ArrayList<Client> allPatientList = new ArrayList<Client>();
	/*	ArrayList<UserProfile> allUserList = new ArrayList<UserProfile>();
		ArrayList<ThirdParty> tpCompanyNameList = new ArrayList<ThirdParty>();
		ArrayList<ThirdParty> surgeryCompanyNameList = new ArrayList<ThirdParty>(); */
		
		//String userName = request.getParameter("userName");
		
		try{
			connection = Connection_provider.getconnection();
			
			//InstantMsgDAO instantMsgDAO = new JDBCInstantMsgDAO(connection);
			
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			/*UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);*/
			
			//if(userName.equalsIgnoreCase("Client")){
				allPatientList = clientDAO.getAllPatient(loginInfo.getId(),"");
		//	}
		/*	
			else if(userName.equalsIgnoreCase("Practitioner")){
				allUserList = userProfileDAO.getAllPractitioner(loginInfo.getId());
			}
			else if(userName.equalsIgnoreCase("ThirdParty")){
				tpCompanyNameList = thirdPartyDAO.getTPCompanyNameList(loginInfo.getId());
			}
			else if(userName.equalsIgnoreCase("GP")){
				surgeryCompanyNameList = thirdPartyDAO.getSurjeryCompanyDetailsList(loginInfo.getId());
			}*/
			//allUserList = instantMsgDAO.getAllPatient(loginInfo.getId());
			
			//instantMsgForm.setAllUserList(allUserList);
			instantMsgForm.setAllPatientList(allPatientList);
			//instantMsgForm.setTpCompanyNameList(tpCompanyNameList);		
			//instantMsgForm.setSurgeryCompanyNameList(surgeryCompanyNameList);
			
			StringBuffer str = new StringBuffer();
			
			str.append("<table class='table table-hover table-condensed table-bordered table-striped ' > ");
			str.append("<thead>");
			str.append("<tr class='bg-info'>");
			str.append("<th>Id</th> ");

			str.append("<th>Name</th> ");
			//str.append("<th>Client Id</th> ");
			str.append("<th>Mobile</th> ");
			str.append("<th>Email</th> ");
			//str.append("<th>PostCode</th> ");
			//str.append("<th>Dob</th> ");

			str.append("</tr>");
			str.append("</thead>");
			
			str.append("<tbody>");
			
			//if(userName.equalsIgnoreCase("Client")){				
				for(Client client1:allPatientList){
					String name = client1.getTitle()+" "+client1.getFirstName()+" "+client1.getLastName(); 		
				str.append("<tr>");
				str.append("<td>0000"+client1.getId()+"</td>");
	
				String firstName= client1.getFirstName();
				str.append("<td style='cursor: pointer;'; onclick = setUserDetail('"+client1.getId()+"')>"+name+"</td>");
				//if(client1.getOldclientId()==null){
				//	client1.setOldclientId("");
				//}
				//str.append("<td>"+client1.getOldclientId()+"</td>");
				str.append("<td>"+client1.getMobNo()+"</td>");
				str.append("<td>"+client1.getEmail()+"</td>");
				//str.append("<td>"+client1.getPostCode()+"</td>");
				//str.append("<td>"+client1.getDob()+"</td>");
	
				str.append("</tr>");
				}
				
			/*}else if(userName.equalsIgnoreCase("Practitioner")){				
				for(UserProfile userProfile:allUserList){
					String name = userProfile.getFirstname()+" "+userProfile.getLastname(); 		
				str.append("<tr>");
				str.append("<td>0000"+userProfile.getId()+"</td>");
	
				String firstName= userProfile.getFirstname();
				str.append("<td style='cursor: pointer;' onclick = setUserDetail('"+userProfile.getId()+"')>"+name+"</td>");
				
				//str.append("<td>"+userProfile.getId()+"</td>");
				str.append("<td>"+userProfile.getMobile()+"</td>");
				str.append("<td>"+userProfile.getEmail()+"</td>");
				//str.append("<td>"+userProfile.get+"</td>");
				//str.append("<td>"+userProfile.getd+"</td>");
	
				str.append("</tr>");
				}
				
			}else if(userName.equalsIgnoreCase("ThirdParty")){
				for(ThirdParty thirdParty:tpCompanyNameList){
					String name = thirdParty.getCompanyName(); 	
					str.append("<tr>");
					str.append("<td>0000"+thirdParty.getId()+"</td>");
					
					//str.append("<td  onclick = setUserDetail('"+name+"','"+thirdParty.getId()+"')>"+name+"</td>");
					
					//str.append("<td onclick = setUserDetail('"+name+"','"+thirdParty.getId()+"')>"+name+"</td>");
					str.append("<td style='cursor: pointer;' onclick = setUserDetail('"+thirdParty.getId()+"')>"+name+"</td>");
					
					str.append("<td>"+thirdParty.getCompnyTelephone()+"</td>");
					str.append("<td>"+thirdParty.getCompnyEmail()+"</td>");
					str.append("</tr>");
				}
				
			 }else if(userName.equalsIgnoreCase("GP")){				
				for(ThirdParty thirdParty:surgeryCompanyNameList){
					String companyName = thirdParty.getCompanyName(); 		
				str.append("<tr>");
				str.append("<td>0000"+thirdParty.getId()+"</td>");	
				str.append("<td style='cursor: pointer;'; onclick = setUserDetail('"+thirdParty.getId()+"')>"+companyName+"</td>");
				str.append("<td>"+thirdParty.getCompnyTelephone()+"</td>");
				str.append("<td>"+thirdParty.getCompnyEmail()+"</td>");					
				str.append("</tr>");
				}
				}*/
			
			str.append("</tbody>");
			str.append("</table>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+str.toString()+""); 
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			connection.close();
		}
		
		return null;
		
	}
	
	public String getPatientDetails() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		String id = request.getParameter("id");
		session.setAttribute("id", id);
		//String userName = request.getParameter("userName");
		Connection connection = null;
		//Client client = new Client();
		try{
			
			connection = Connection_provider.getconnection();
			
			StringBuffer str = new StringBuffer();
			String email = "";
			
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			/*UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);*/
			
			//if(userName.equalsIgnoreCase("Client")){	
				String fullName = clientDAO.getClientFullName(id);				
				str.append(fullName);			
				email = clientDAO.getClientEmail(id);
				int  appointmentid = emailTemplateDAO.getLatestAppointmentId(id);
			/*}
			else if(userName.equalsIgnoreCase("Practitioner")){	
				String fullName = userProfileDAO.getFullName(id);				
				str.append(fullName);			
				email = userProfileDAO.getUserEmail(id);
			}
			else if(userName.equalsIgnoreCase("ThirdParty")){	
				String fullName = thirdPartyDAO.getFullName(id);				
				str.append(fullName);			
				email = thirdPartyDAO.getThirdPartyEmail(id);
			}
			else if(userName.equalsIgnoreCase("GP")){	
				String fullName = thirdPartyDAO.getFullName(id);				
				str.append(fullName);			
				email = thirdPartyDAO.getThirdPartyEmail(id);
			}*/
			
			String data = ""+str.toString()+"/"+email+"/"+appointmentid+"";
			System.out.println(str.toString());
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+data+""); 		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return null;
	}

	
	public String showTemplateList() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		userName = request.getParameter("userName");
		try{
			connection = Connection_provider.getconnection();
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			ArrayList<EmailTemplate> templateNameList = new ArrayList<EmailTemplate>();
			
			if(!userName.equals("")){
			
				templateNameList = emailTemplateDAO.getEmailTemplateNameList(userName,loginInfo.getId());
			
			}
			else{
				templateNameList = emailTemplateDAO.getEmailTemplateNameList();
			}
			
			 StringBuffer dropDownAjax = new StringBuffer();
				dropDownAjax.append("<select id='templateName' name = 'templateName'  onchange='showTemplateDetails(this.id)' class='form-control showToolTip chosen' data-toggle='tooltip'>");
					dropDownAjax.append("<option value = '0'>Select Template </option>");
					if(templateNameList.size()!=0){
						for(EmailTemplate emailTemplate : templateNameList){
							dropDownAjax.append("<option value = '"+emailTemplate.getId()+"'>"+emailTemplate.getTemplateName()+"</option>");
							
						}
						
					}
					
					
				dropDownAjax.append("</select>");
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				
				response.getWriter().write(""+dropDownAjax.toString()+""); 
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		return null;
	}
	
	
	public String printLetter() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			ArrayList<EmailTemplate> templateNameList = new ArrayList<EmailTemplate>();
			templateNameList = emailTemplateDAO.getEmailTemplateNameList();
			instantMsgForm.setTemplateNameList(templateNameList);
			
			//set selected clientid from session
			if(session.getAttribute("sessionselectedclientid")!=null){
				String clientid = (String)session.getAttribute("sessionselectedclientid");
				instantMsgForm.setId(Integer.parseInt(clientid));
				Client client = clientDAO.getSelectedSessionClientDetails(clientid);
				instantMsgForm.setUser(client.getClientName());
			}
			else {
				String clientid=request.getParameter("clientid");
				instantMsgForm.setId(Integer.parseInt(clientid));
				Client client = clientDAO.getSelectedSessionClientDetails(clientid);
				instantMsgForm.setUser(client.getClientName());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
			
		return "printLetter";
	}
	
	public String getGPName() throws IOException, SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
		connection = Connection_provider.getconnection();
		int patientId = Integer.parseInt(request.getParameter("patientId"));
		ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
		ThirdParty thirdParty = new ThirdParty();
		int tpId = thirdPartyDAO.getThirdPartyId(patientId);
		thirdParty = thirdPartyDAO.getGPDetailLetter(tpId);
		String companyName = thirdParty.getCompanyName();	
		String companyEmail = thirdParty.getCompnyEmail();
		
		//String gpData = emailTemplateDAO.getGPData();
		
		
		String data = ""+tpId+"/"+companyName+"/"+companyEmail+"";
		
		
		//System.out.println(str.toString());
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+data+""); 
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public String set(){
		String to = request.getParameter("to");
		String cc = request.getParameter("cc");
		String subject = request.getParameter("subject");
		String notes = request.getParameter("emailBody").toString();
		int appointmentid = Integer.parseInt(request.getParameter("editAppointId"));
		String clientId = request.getParameter("clientid");
		
		session.setAttribute("eto", to);
		session.setAttribute("ecc", cc);
		session.setAttribute("esubject", subject);
		session.setAttribute("enotes", notes);
		session.setAttribute("eapmtid", appointmentid);
		session.setAttribute("eclientid", clientId);
		
		return "tpadashboard";
	}
	
	
	
	public String sendLetterEmailTp() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			String to = instantMsgForm.getTo();  
			String cc = instantMsgForm.getCc();
			String subject = instantMsgForm.getSubject();
			String notes = instantMsgForm.getBody();
			int appointmentid = Integer.parseInt(instantMsgForm.getEditAppointId());
			String clientId = instantMsgForm.getClientid();
			String treatmentEpisodeId= instantMsgForm.getTreatmentEpisodeId();
		
			Email email = new Email();
			   // Email.sendMail(connection, to, cc, subject, notes, loginInfo, inlineImages);
			    
			    String type = "Letter";
				//int appointmentid = 0;
				int invoiceid = 0;
				EmailLetterLog emailLetterLog = new EmailLetterLog();
				EmailLetterLogDAO emailLetterLogDAO = new JDBCEmailLetterLogDAO(connection);
				//String clientId = emailLetterLogDAO.getClientIdByAptmentId(appointmentid);
				emailLetterLog.setClientId(clientId);
				emailLetterLog.setAppointmentid(appointmentid);
				emailLetterLog.setInvoiceid(invoiceid);
				emailLetterLog.setType(type);
				
				String attachFiles[] = {};
				
				 // copy the uploaded files into pre-configured location
				if(instantMsgForm.getFileUpload()!=null){
					attachFiles = new String[instantMsgForm.getFileUpload().length];
					if(instantMsgForm.getFileUpload().length>0){
						   for (int i = 0; i < instantMsgForm.getFileUpload().length; i++) {
					            File uploadedFile = instantMsgForm.getFileUpload()[i];
					            String fileName = instantMsgForm.getFileUploadFileName()[i];
					           // String filePath = request.getRealPath("/liveData/emailAttachments/");
					            
					            String workingDirectory = System.getProperty("user.dir");
								String absoluteFilePath = workingDirectory + File.separator + loginInfo.getClinicUserid() + File.separator +  fileName;
					            
					            File destFile = new File(absoluteFilePath, fileName);
					            System.out.println("Server path:" + absoluteFilePath);
					            try {
					                FileUtils.copyFile(uploadedFile, destFile);
					            } catch (IOException ex) {
					                System.out.println("Could not copy file " + fileName);
					                ex.printStackTrace();
					            }
					        }
					        
					        for (int i = 0; i < instantMsgForm.getFileUpload().length; i++) {
					        	
					       /* 	 String fileName = instantMsgForm.getFileUploadFileName()[i];
					        	 String filePath = request.getRealPath("/liveData/emailAttachments/");
					        	 attachFiles[i] = "/"+filePath+"/"+fileName+"";*/
					        	 
					        	 String fileName = instantMsgForm.getFileUploadFileName()[i];
					        	 String workingDirectory = System.getProperty("user.dir");
								 String absoluteFilePath = workingDirectory + File.separator + loginInfo.getClinicUserid() + File.separator +  fileName;
								 attachFiles[i] = absoluteFilePath + File.separator + fileName;
					        	 
					        }
							
					}
				}
			
				/* 	String logo = "<img src=\"cid:image1\" width=\"20%\" height=\"10%\" />";
				 	notes = logo + notes;
				  Map<String, String> mapInlineImages = new HashMap<String, String>();
				  
				  String fileName = "banner.jpeg";
				  String workingDirectory = System.getProperty("user.dir");
					String absoluteFilePath = workingDirectory + File.separator + loginInfo.getClinicUserid() + File.separator +  fileName +  File.separator +  fileName;
				  
		          mapInlineImages.put("image1", request.getRealPath("liveData/clinicLogo/"+loginInfo.getClinicLogo()));*/
				
			    email.sendPrintLetterMail(connection,to, cc, subject, notes,loginInfo,emailLetterLog,attachFiles);
			    
			    
			    if(treatmentEpisodeId!=null){
			    	
			    	  //update treatment episode
			    	  TPADao tpaDao= new JDBCTPADao(connection);
			    	  int res= tpaDao.updateTreatmentEpisodetoSubmit(treatmentEpisodeId);
			    }
			    
			    
			    //send sms
			  /*  ClientDAO clientDAO = new JDBCClientDAO(connection);
			    NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			    NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getApmtDetailsForLog(appointmentid);
			    UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			    Client client = clientDAO.getClientDetails(clientId);
			    UserProfile userProfile  = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());
			   
			    String message = AllTemplateAction.getAppointmentSMSText(clientId, appointmentid, connection, loginInfo);
			    
			    if(loginInfo.getCountry().equals("India")){
			    	SendSms.send(message, client.getMobNo(), loginInfo, emailLetterLog);
			    	SendSms.send(message, userProfile.getMobile(), loginInfo, emailLetterLog);
			    }*/
			    
			    
			    //EmbeddedImageEmailUtil.send(connection, loginInfo.getId(), to, cc, subject, notes, inlineImages, loginInfo);
				//int result = emailTemplateDAO.saveEmailLogDetails(to, cc, subject, notes, date1,time);
				//EmbeddedImageEmailUtil.sendMail(connection,loginInfo.getId(),to, cc, subject, notes,loginInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
			
		}
		
		return "tpadashboard";
		
	}
	
	
	public String sendLetterEmail() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
		connection = Connection_provider.getconnection();
		
		String to = (String)session.getAttribute("eto");
		String cc = (String)session.getAttribute("ecc");
		String subject = (String)session.getAttribute("esubject");
		String notes = (String)session.getAttribute("enotes");
		int appointmentid = DateTimeUtils.convertToInteger((String)session.getAttribute("eapmtid"));
		String clientId = (String)session.getAttribute("eclientid");
		String treatmentEpisodeId= request.getParameter("treatmentEpisodeId"); 
		
	/*	String twitter = request.getRealPath("/img/Entypo_f309(0)_32.png/");      	    
   	    
		File file = new File(twitter);
    	String img = "<a href='www.twitter.com'><img src=\"cid:image1\" /></a>";
    	String fb = request.getRealPath("/img/Entypo_f30c(0)_32.png/");      	    
   	  
    	file = new File(fb);
    	String img1 = "<a href='www.facebook.com'><img src=\"cid:image2\" /></a>";
    	String gml = request.getRealPath("/img/Entypo_f30f(0)_32.png/");      	    
   	    file = new File(gml);
    	String img2 = "<a href='accounts.google.com'><img src=\"cid:image3\" /></a>";
    	String data = notes+"   "+img+" "+img1+" "+img2;
    	Map<String, String> inlineImages = new HashMap<String, String>();
	    inlineImages.put("image1", twitter);
	    inlineImages.put("image2", fb);
	    inlineImages.put("image3", gml);*/
	    
	    Email email = new Email();
	   // Email.sendMail(connection, to, cc, subject, notes, loginInfo, inlineImages);
	    
	    String type = "Letter";
		//int appointmentid = 0;
		int invoiceid = 0;
		EmailLetterLog emailLetterLog = new EmailLetterLog();
		EmailLetterLogDAO emailLetterLogDAO = new JDBCEmailLetterLogDAO(connection);
		//String clientId = emailLetterLogDAO.getClientIdByAptmentId(appointmentid);
		emailLetterLog.setClientId(clientId);
		emailLetterLog.setAppointmentid(appointmentid);
		emailLetterLog.setInvoiceid(invoiceid);
		emailLetterLog.setType(type);
		
		String attachFiles[] = {};
		
		 // copy the uploaded files into pre-configured location
		if(instantMsgForm.getFileUpload()!=null){
			attachFiles = new String[instantMsgForm.getFileUpload().length];
			if(instantMsgForm.getFileUpload().length>0){
				   for (int i = 0; i < instantMsgForm.getFileUpload().length; i++) {
			            File uploadedFile = instantMsgForm.getFileUpload()[i];
			            String fileName = instantMsgForm.getFileUploadFileName()[i];
			           // String filePath = request.getRealPath("/liveData/emailAttachments/");
			            
			            String workingDirectory = System.getProperty("user.dir");
						String absoluteFilePath = workingDirectory + File.separator + loginInfo.getClinicUserid() + File.separator +  fileName;
			            
			            File destFile = new File(absoluteFilePath, fileName);
			            System.out.println("Server path:" + absoluteFilePath);
			            try {
			                FileUtils.copyFile(uploadedFile, destFile);
			            } catch (IOException ex) {
			                System.out.println("Could not copy file " + fileName);
			                ex.printStackTrace();
			            }
			        }
			        
			        for (int i = 0; i < instantMsgForm.getFileUpload().length; i++) {
			        	
			       /* 	 String fileName = instantMsgForm.getFileUploadFileName()[i];
			        	 String filePath = request.getRealPath("/liveData/emailAttachments/");
			        	 attachFiles[i] = "/"+filePath+"/"+fileName+"";*/
			        	 
			        	 String fileName = instantMsgForm.getFileUploadFileName()[i];
			        	 String workingDirectory = System.getProperty("user.dir");
						 String absoluteFilePath = workingDirectory + File.separator + loginInfo.getClinicUserid() + File.separator +  fileName;
						 attachFiles[i] = absoluteFilePath + File.separator + fileName;
			        	 
			        }
					
			}
		}
	
		/* 	String logo = "<img src=\"cid:image1\" width=\"20%\" height=\"10%\" />";
		 	notes = logo + notes;
		  Map<String, String> mapInlineImages = new HashMap<String, String>();
		  
		  String fileName = "banner.jpeg";
		  String workingDirectory = System.getProperty("user.dir");
			String absoluteFilePath = workingDirectory + File.separator + loginInfo.getClinicUserid() + File.separator +  fileName +  File.separator +  fileName;
		  
          mapInlineImages.put("image1", request.getRealPath("liveData/clinicLogo/"+loginInfo.getClinicLogo()));*/
		
	    email.sendPrintLetterMail(connection,to, cc, subject, notes,loginInfo,emailLetterLog,attachFiles);
	    
	    
	    if(treatmentEpisodeId!=null){
	    	
	    	  //update treatment episode
	    	  TPADao tpaDao= new JDBCTPADao(connection);
	    	  int res= tpaDao.updateTreatmentEpisodetoSubmit(treatmentEpisodeId);
	    }
	    
	    
	    //send sms
	  /*  ClientDAO clientDAO = new JDBCClientDAO(connection);
	    NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
	    NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getApmtDetailsForLog(appointmentid);
	    UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
	    Client client = clientDAO.getClientDetails(clientId);
	    UserProfile userProfile  = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());
	   
	    String message = AllTemplateAction.getAppointmentSMSText(clientId, appointmentid, connection, loginInfo);
	    
	    if(loginInfo.getCountry().equals("India")){
	    	SendSms.send(message, client.getMobNo(), loginInfo, emailLetterLog);
	    	SendSms.send(message, userProfile.getMobile(), loginInfo, emailLetterLog);
	    }*/
	    
	    
	    //EmbeddedImageEmailUtil.send(connection, loginInfo.getId(), to, cc, subject, notes, inlineImages, loginInfo);
		//int result = emailTemplateDAO.saveEmailLogDetails(to, cc, subject, notes, date1,time);
		//EmbeddedImageEmailUtil.sendMail(connection,loginInfo.getId(),to, cc, subject, notes,loginInfo);
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		return null;
	}
	
	public String sendLetterEmailAttached(){
		try {
			Connection connection= Connection_provider.getconnection();
			BufferedReader br=request.getReader();
			String line="";
			String inputjson="";
			if((line=br.readLine())!=null){
				inputjson=inputjson+line;
			}
			
			JSONObject jsonObject= new JSONObject(inputjson);
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(jsonObject.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public InstantMsgForm getModel() {
		// TODO Auto-generated method stub
		return instantMsgForm;
	}


	public void prepare() throws Exception {
		
		
	}
}
