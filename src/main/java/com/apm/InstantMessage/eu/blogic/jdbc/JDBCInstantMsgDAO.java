package com.apm.InstantMessage.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.InstantMessage.eu.bi.InstantMsgDAO;
import com.apm.InstantMessage.eu.entity.InstantMsg;
import com.apm.Master.eu.entity.Master;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;

public class JDBCInstantMsgDAO extends JDBCBaseDAO implements InstantMsgDAO{
	
	public JDBCInstantMsgDAO(Connection connection){
		this.connection = connection;
		
	}

	public ArrayList<InstantMsg> getAllContacts() {
		PreparedStatement preparedStatement = null;
		ArrayList<InstantMsg>list = new ArrayList<InstantMsg>();
		String sql = "SELECT id,initial,firstname,lastname,jobtitle,email FROM apm_user where usertype = 4";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				InstantMsg instantMsg = new InstantMsg();
				instantMsg.setId(rs.getInt(1));
				instantMsg.setName(rs.getString(2) +" " + rs.getString(3) + " " + rs.getString(4) );
				instantMsg.setDesignation(rs.getString(5));
				instantMsg.setEmailId(rs.getString(6));
				list.add(instantMsg);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getUserEmailId(int id) {
		PreparedStatement preparedStatement = null;
	String emailId ="";
		String sql = "SELECT email FROM apm_user where id = "+id+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				//InstantMsg instantMsg = new InstantMsg();
				
				emailId = rs.getString(1);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return emailId;
	}

	public ArrayList<InstantMsg> getAllInbox(int id) {
		PreparedStatement preparedStatement = null;
		ArrayList<InstantMsg>list = new ArrayList<InstantMsg>();
		String sql = "SELECT id,sender_email_id,subject,body_text,date,time,send_mail_id,status FROM apm_inbox where receiver_id   = "+id+" order by id desc";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				InstantMsg instantMsg = new InstantMsg();
				instantMsg.setId(rs.getInt(1));
				instantMsg.setSenderEmailId(rs.getString(2));
				instantMsg.setSubject(rs.getString(3));
				instantMsg.setBody(rs.getString(4));
				instantMsg.setDate(rs.getString(5));
				instantMsg.setTime(rs.getString(6));
				ArrayList<InstantMsg>attachmentslist = getAttachments(rs.getString(7));
				instantMsg.setStatus(rs.getString(8));
				instantMsg.setAttachmentslist(attachmentslist);
				
				list.add(instantMsg);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private ArrayList<InstantMsg> getAttachments(String id) {
		
		PreparedStatement preparedStatement = null;
		ArrayList<InstantMsg>list = new ArrayList<InstantMsg>();
		String sql = "SELECT filename FROM apm_email_attachments where mail_id = "+id+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				InstantMsg instantMsg = new InstantMsg();
				
				 instantMsg.setAttachFileName(rs.getString(1));
				 list.add(instantMsg);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	public int saveSendMessage(InstantMsg instantMsg) {
		int result = 0;
		int id = 0;
		PreparedStatement pstm = null;
		String sql = "insert into apm_send_mail(sender_id,sender_email_id,receiver_email_ids,subject,body_text,date,time) values (?,?,?,?,?,?,?)";
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setInt(1, instantMsg.getSenderId());
			pstm.setString(2, instantMsg.getSenderEmailId());
			pstm.setString(3, instantMsg.getReceiverEmailId());
			pstm.setString(4, instantMsg.getSubject());
			pstm.setString(5, instantMsg.getBody());
			pstm.setString(6, instantMsg.getDate());
			pstm.setString(7, instantMsg.getTime());
			
			result = pstm.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = pstm.getGeneratedKeys();
				if(resultSet.next()){
					id = resultSet.getInt(1);  
				}
			}
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
		}
		return id;
	}

	public int saveEmailAttachmentFiles(int id, String fileName) {
		int result = 0;
		
		PreparedStatement pstm = null;
		String sql = "insert into apm_email_attachments(mail_id,filename) values (?,?)";
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.setString(2, fileName);
			result = pstm.executeUpdate();
			
			
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
		}
		return result;
	}

	public int getRecieverId(String email) {
		PreparedStatement preparedStatement = null;
		int id = 0;
			String sql = "SELECT id FROM apm_user where email = '"+email+"' ";
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					
					
					id = rs.getInt(1);
					
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return id;
	}

	public int saveInbox(InstantMsg instantMsg) {
		int result = 0;
		
		PreparedStatement pstm = null;
		String sql = "insert into apm_inbox(sender_id,sender_email_id,receiver_id,receiver_email_id,subject,body_text,send_mail_id,date,time,status) values (?,?,?,?,?,?,?,?,?,?)";
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setInt(1, instantMsg.getSenderId());
			pstm.setString(2, instantMsg.getSenderEmailId());
			pstm.setInt(3, instantMsg.getReceiverId());
			pstm.setString(4, instantMsg.getReceiverEmailId());
			pstm.setString(5, instantMsg.getSubject());
			pstm.setString(6, instantMsg.getBody());
			pstm.setInt(7, instantMsg.getId());
			pstm.setString(8, instantMsg.getDate());
			pstm.setString(9, instantMsg.getTime());
			pstm.setString(10, "unread");
			
			result = pstm.executeUpdate();
			
			
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
		}
		return result;
	}

	public ArrayList<InstantMsg> getsendMailList(int id) {
		PreparedStatement preparedStatement = null;
		ArrayList<InstantMsg>list = new ArrayList<InstantMsg>();
		String sql = "SELECT id,sender_email_id,receiver_email_ids,subject,body_text,date,time FROM apm_send_mail where sender_id   = "+id+" order by id desc";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				InstantMsg instantMsg = new InstantMsg();
				instantMsg.setId(rs.getInt(1));
				instantMsg.setSenderEmailId(rs.getString(2));
				instantMsg.setReceiverEmailId(rs.getString(3));
				instantMsg.setSubject(rs.getString(4));
				instantMsg.setBody(rs.getString(5));
				instantMsg.setDate(rs.getString(6));
				instantMsg.setTime(rs.getString(7));
				ArrayList<InstantMsg>attachmentslist = getAttachments(rs.getString(1));
				instantMsg.setAttachmentslist(attachmentslist);
				list.add(instantMsg);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public InstantMsg getInboxMailDetails(int id) {
		PreparedStatement preparedStatement = null;
		InstantMsg instantMsg = new InstantMsg();
		String sql = "SELECT id,sender_email_id,subject,body_text,date,time,send_mail_id FROM apm_inbox where id = "+id+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
			
				instantMsg.setId(rs.getInt(1));
				instantMsg.setSenderEmailId(rs.getString(2));
				instantMsg.setSubject(rs.getString(3));
				instantMsg.setBody(rs.getString(4));
				instantMsg.setDate(rs.getString(5));
				instantMsg.setTime(rs.getString(6));
				ArrayList<InstantMsg>attachmentslist = getAttachments(rs.getString(7));
				instantMsg.setAttachmentslist(attachmentslist);
				
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return instantMsg;
	}

	public InstantMsg getSentMailDetails(int id) {
		PreparedStatement preparedStatement = null;
		InstantMsg instantMsg = new InstantMsg();
		String sql = "SELECT id,sender_email_id,receiver_email_ids,subject,body_text,date,time FROM apm_send_mail where id = "+id+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				
				instantMsg.setId(rs.getInt(1));
				instantMsg.setSenderEmailId(rs.getString(2));
				instantMsg.setReceiverEmailId(rs.getString(3));
				instantMsg.setSubject(rs.getString(4));
				instantMsg.setBody(rs.getString(5));
				instantMsg.setDate(rs.getString(6));
				instantMsg.setTime(rs.getString(7));
				ArrayList<InstantMsg>attachmentslist = getAttachments(rs.getString(1));
				instantMsg.setAttachmentslist(attachmentslist);
			
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return instantMsg;
	}

	public int updateStatus(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_inbox set status = 'read' where id = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<InstantMsg> getAttachmentList(int id) {
		PreparedStatement preparedStatement = null;
		ArrayList<InstantMsg>list = new ArrayList<InstantMsg>();
		
		String sql = "SELECT filename FROM apm_email_attachments where mail_id = "+id+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				InstantMsg instantMsg = new InstantMsg();
				
				 instantMsg.setAttachFileName(rs.getString(1));
				 list.add(instantMsg);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getSendMailId(int id) {
		PreparedStatement preparedStatement = null;
		 int id1 = 0;
			String sql = "SELECT send_mail_id FROM apm_inbox where id = "+id+" ";
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					
					
					id1 = rs.getInt(1);
					
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return id1;
	}

	public int getUnreadMailTotal(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		String sql = "SELECT count(*) FROM apm_inbox where receiver_id   = "+id+" and status = 'unread'";

		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int saveDraft(InstantMsg instantMsg) {
		int result = 0;
		int id = 0;
		PreparedStatement pstm = null;
		String sql = "insert into apm_email_draft(sender_id,sender_email_id,receiver_email_ids,subject,body,date,time) values (?,?,?,?,?,?,?)";
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setInt(1, instantMsg.getSenderId());
			pstm.setString(2, instantMsg.getSenderEmailId());
			pstm.setString(3, instantMsg.getReceiverEmailId());
			pstm.setString(4, instantMsg.getSubject());
			pstm.setString(5, instantMsg.getBody());
			pstm.setString(6, instantMsg.getDate());
			pstm.setString(7, instantMsg.getTime());
			
			result = pstm.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = pstm.getGeneratedKeys();
				if(resultSet.next()){
					id = resultSet.getInt(1);  
				}
			}
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
		}
		return id;
	}

	public ArrayList<InstantMsg> getDraftmail(int id) {
		PreparedStatement preparedStatement = null;
		ArrayList<InstantMsg>list = new ArrayList<InstantMsg>();
		String sql = "SELECT id,sender_id,sender_email_id,receiver_email_ids,subject,body,date,time FROM apm_email_draft where sender_id = "+id+" order by id desc";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				InstantMsg instantMsg = new InstantMsg();
				instantMsg.setId(rs.getInt(1));
				instantMsg.setSenderId(rs.getInt(2));
				instantMsg.setSenderEmailId(rs.getString(3));
				instantMsg.setReceiverEmailId(rs.getString(4));
				instantMsg.setSubject(rs.getString(5));
				instantMsg.setBody(rs.getString(6));
				instantMsg.setDate(rs.getString(7));
				instantMsg.setTime(rs.getString(8));
				
				list.add(instantMsg);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int saveDraftAttachmentFiles(int id, String fileName) {
		int result = 0;
		
		PreparedStatement pstm = null;
		String sql = "insert into apm_email_draft_attachments(draft_id,filename) values (?,?)";
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.setString(2, fileName);
			result = pstm.executeUpdate();
			
			
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
		}
		return result;
	}

	public ArrayList<InstantMsg> getDraftAttachmentList(int id) {
		PreparedStatement preparedStatement = null;
		ArrayList<InstantMsg>list = new ArrayList<InstantMsg>();
		
		String sql = "SELECT filename FROM apm_email_draft_attachments where draft_id = "+id+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				InstantMsg instantMsg = new InstantMsg();
				
				 instantMsg.setAttachFileName(rs.getString(1));
				 list.add(instantMsg);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int deleteDraftMail(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_email_draft where id = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteDraftMailattachments(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_email_draft_attachments where draft_id = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int saveChatMessage(InstantMsg instantMsg) {
		int result = 0;
		//  29 jan 2018 permission given by manoj sir
		/*PreparedStatement pstm = null;
		String sql = "insert into apm_user_chat(sender_user_id,receiver_user_id,text,date,time,sender_id) values (?,?,?,?,?,?)";
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setString(1, instantMsg.getSenderUserId());
			pstm.setString(2, instantMsg.getReceiverUserId());
			pstm.setString(3, instantMsg.getChatText());
			pstm.setString(4, instantMsg.getDate());
			pstm.setString(5, instantMsg.getTime());
			pstm.setInt(6, instantMsg.getSenderId());
			result = pstm.executeUpdate();
			
			
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
		}*/
		return result;
	}

	public ArrayList<InstantMsg> getChatList(int id) {
		PreparedStatement preparedStatement = null;
		ArrayList<InstantMsg>list = new ArrayList<InstantMsg>();
		String sql = "SELECT id,sender_user_id,receiver_user_id,text,date,time,sender_id FROM apm_user_chat where sender_id = "+id+" order by id desc";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				InstantMsg instantMsg = new InstantMsg();
				instantMsg.setId(rs.getInt(1));
				instantMsg.setSenderUserId(rs.getString(2));
				instantMsg.setReceiverUserId(rs.getString(3));
				instantMsg.setChatText(rs.getString(4));
				instantMsg.setDate(rs.getString(5));
				instantMsg.setTime(rs.getString(6));
				instantMsg.setSenderId(rs.getInt(7));
				list.add(instantMsg);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<InstantMsg> getChatConversation(int id, String senderUserId,
			String receiverUserId) {
		PreparedStatement preparedStatement = null;
		ArrayList<InstantMsg>list = new ArrayList<InstantMsg>();
		String sql = "SELECT id,sender_user_id,receiver_user_id,text,date,time,sender_id FROM apm_user_chat where sender_user_id = '"+senderUserId+"' and receiver_user_id = '"+receiverUserId+"' order by id ASC";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				InstantMsg instantMsg = new InstantMsg();
				instantMsg.setId(rs.getInt(1));
				instantMsg.setSenderUserId(rs.getString(2));
				instantMsg.setReceiverUserId(rs.getString(3));
				instantMsg.setChatText(rs.getString(4));
				instantMsg.setDate(rs.getString(5));
				instantMsg.setTime(rs.getString(6));
				instantMsg.setSenderId(rs.getInt(7));
				list.add(instantMsg);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int saveActivity(int clientId, int activity) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_activity (clientId,activity) values(?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, clientId);
			preparedStatement.setInt(2, activity);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			
		}
		return result;
	}

	

}
