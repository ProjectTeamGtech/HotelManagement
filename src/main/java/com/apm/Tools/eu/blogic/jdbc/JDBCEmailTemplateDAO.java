package com.apm.Tools.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Tools.eu.bi.EmailTemplateDAO;
import com.apm.Tools.eu.entity.EmailTemplate;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.Encryption;
import com.apm.common.utils.Pagination;

public class JDBCEmailTemplateDAO extends JDBCBaseDAO implements EmailTemplateDAO{
	
	public JDBCEmailTemplateDAO(Connection connection){
		this.connection = connection;
		
	}

	public int getTotalEmailTemplateCount() {
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		String sql = "select count(*) from apm_email_template";
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

	public ArrayList<EmailTemplate> getEmailTemplateList(Pagination pagination) {
		PreparedStatement preparedStatement = null;
		ArrayList<EmailTemplate> list = new ArrayList<EmailTemplate>();
		String sql = "select id,template_name,header_text,body1_text,body2_text,footer_text,userName,templateId from apm_email_template";
		sql = pagination.getSQLQuery(sql);
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				EmailTemplate emailTemplate = new EmailTemplate();
				emailTemplate.setId(rs.getInt(1));
				emailTemplate.setTemplateName(rs.getString(2));
				emailTemplate.setHeaderNote(rs.getString(3));
				emailTemplate.setBody1Note(rs.getString(4));
				emailTemplate.setBody2Note(rs.getString(5));
				emailTemplate.setFooterNote(rs.getString(6));	
				emailTemplate.setUserName(rs.getString(7));
				emailTemplate.setTemplateId(rs.getString(8));
				list.add(emailTemplate);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public int saveEmailTemplate(EmailTemplate emailTemplate, int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_email_template(template_name,clinic_id,header_text,body1_text,body2_text,footer_text,userName) values(?,?,?,?,?,?,?)";
		try{	
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, emailTemplate.getTemplateName());
			preparedStatement.setInt(2, id);
			preparedStatement.setString(3, emailTemplate.getHeaderNote());
			preparedStatement.setString(4, emailTemplate.getBody1Note());
			preparedStatement.setString(5, emailTemplate.getBody2Note());
			preparedStatement.setString(6, emailTemplate.getFooterNote());
			preparedStatement.setString(7, emailTemplate.getUserName());
			
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public EmailTemplate getEmailTemplate(int id, EmailTemplate emailTemplate) {
		PreparedStatement preparedStatement = null;
		String sql = "select id,template_name,header_text,body1_text,body2_text,footer_text,userName from apm_email_template where id ="+id+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				emailTemplate.setId(rs.getInt(1));
				emailTemplate.setTemplateName(rs.getString(2));
				emailTemplate.setHeaderNote(rs.getString(3));
				emailTemplate.setBody1Note(rs.getString(4));
				emailTemplate.setBody2Note(rs.getString(5));
				emailTemplate.setFooterNote(rs.getString(6));
				emailTemplate.setUserName(rs.getString(7));
			}			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return emailTemplate;
	}

	public int updateEmailTemplate(EmailTemplate emailTemplate, int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_email_template set template_name = ?, header_text = ?, body1_text = ?, body2_text = ?, footer_text = ?,userName = ? where id = "+id+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, emailTemplate.getTemplateName());
			preparedStatement.setString(2, emailTemplate.getHeaderNote());
			preparedStatement.setString(3, emailTemplate.getBody1Note());
			preparedStatement.setString(4, emailTemplate.getBody2Note());
			preparedStatement.setString(5, emailTemplate.getFooterNote());
			preparedStatement.setString(6, emailTemplate.getUserName());
			
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int deleteEmailTemplate(int id, EmailTemplate emailTemplate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_email_template where id = "+id+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public EmailTemplate getEmailTemplatePreview(int selectedid) {
		PreparedStatement preparedStatement = null;
		EmailTemplate emailTemplate = new EmailTemplate();
		String sql = "select id,template_name,header_text,body1_text,body2_text,footer_text from apm_email_template where id ="+selectedid+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				emailTemplate.setId(rs.getInt(1));
				emailTemplate.setTemplateName(rs.getString(2));
				emailTemplate.setHeaderNote(rs.getString(3));
				emailTemplate.setBody1Note(rs.getString(4));
				emailTemplate.setBody2Note(rs.getString(5));
				emailTemplate.setFooterNote(rs.getString(6));
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return emailTemplate;
	}

	public ArrayList<EmailTemplate> getEmailTemplateNameList() {
		PreparedStatement preparedStatement = null;
		ArrayList<EmailTemplate> list = new ArrayList<EmailTemplate>();
		String sql = "select id,template_name,templateId from apm_email_template";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				EmailTemplate emailTemplate = new EmailTemplate();
				emailTemplate.setId(rs.getInt(1));
				emailTemplate.setTemplateName(rs.getString(2));
				emailTemplate.setTemplateId(rs.getString(3));
				list.add(emailTemplate);				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public int getTotalLogoCount() {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(*) from apm_logo";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<EmailTemplate> getLogoList(Pagination pagination) {
		PreparedStatement preparedStatement = null;
		ArrayList<EmailTemplate> list = new ArrayList<EmailTemplate>();
		String sql = "select id, logo from apm_logo";
		sql = pagination.getSQLQuery(sql);
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()){
				EmailTemplate emailTemplate = new EmailTemplate();
				emailTemplate.setId(rs.getInt(1));
				emailTemplate.setUserImageFileName(rs.getString(2));
				list.add(emailTemplate);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public int saveLogo(String logoName) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_logo(logo) values(?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, logoName);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public EmailTemplate getLogoDetails(int id) {
		PreparedStatement preparedStatement = null;
		String sql = "select id,logo from apm_logo where id = "+id+" ";
		EmailTemplate emailTemplate = new EmailTemplate();
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){				
			
			emailTemplate.setId(rs.getInt(1));
			emailTemplate.setUserImageFileName(rs.getString(2));
			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return emailTemplate;
	}

	public int updateLogo(EmailTemplate emailTemplate) {
		int id = emailTemplate.getId();
		int result = 0;
		PreparedStatement preparedStatement = null;
		String sql = "update apm_logo set logo = ? where id = "+id+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, emailTemplate.getUserImageFileName());
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int deleteLogo(int id) {
		PreparedStatement preparedStatement = null;
		String sql = "delete from apm_logo where id = "+id+"";
		return 0;
	}

	public int getLatestAppointmentId(String id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select max(id) from apm_available_slot where clientId = "+id+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public String getEmailTemplateData(String tempId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select body2_text from apm_email_template where id = "+tempId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			
		}
		return result;
	}

	public ArrayList<EmailTemplate> getEmailTemplateNameList(String userName,int id) {
				
		PreparedStatement preparedStatement = null;
		ArrayList<EmailTemplate> list = new ArrayList<EmailTemplate>();
		String sql = "select id,template_name,templateId from apm_email_template where userName = '"+userName+"' order by id desc ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				EmailTemplate emailTemplate = new EmailTemplate();
				emailTemplate.setId(rs.getInt(1));
				emailTemplate.setTemplateName(rs.getString(2));
				emailTemplate.setTemplateId(rs.getString(3));
				list.add(emailTemplate);				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	
	public int SaveEmailConfugureInfo(EmailTemplate emailTemplate,int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_email_configure (userName,password,hostName,loginId) values (?,?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, emailTemplate.getEmailUserName());
			preparedStatement.setString(2, emailTemplate.getEmailPassword());
			preparedStatement.setString(3, emailTemplate.getEmailHostName());
			preparedStatement.setInt(4, id);

			result = preparedStatement.executeUpdate();
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	/*public int saveEmailLogDetails(String to, String cc, String subject,
			String notes, String date1, String time) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_email_log(invoice_id,to1,cc,subject,body_text,date,time,filename,type) values (?,?,?,?,?,?,?,?,?)";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			//preparedStatement.setInt(1, invoiceid);
			preparedStatement.setString(2, to);
			preparedStatement.setString(3, cc);
			preparedStatement.setString(4, subject);
			preparedStatement.setString(5, notes);
			preparedStatement.setString(6, date1);
			preparedStatement.setString(7, time);
			//preparedStatement.setString(8, filename);
			preparedStatement.setString(9, "Invoice");
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
*/

	public EmailTemplate getEmailConfigureDetails(int id) {
		PreparedStatement preparedStatement = null;
		EmailTemplate emailTemplate = new EmailTemplate();
		//  12 Oct 2018 there is only one id bt when aproved by any user then take that id so not getdata so commented
		//String sql = "select id,userName,password,hostName,ad from apm_email_configure where loginId = "+id+"";
		String sql = "select id,userName,password,hostName,ad,telemedPharmacy from apm_email_configure where loginId = 1";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
			emailTemplate.setEmailConfigureId(rs.getString(1));
			emailTemplate.setEmailUserName(rs.getString(2));
			emailTemplate.setEmailPassword(rs.getString(3));
			emailTemplate.setEmailHostName(rs.getString(4));
			emailTemplate.setAdDuration(rs.getString(5));
			emailTemplate.setTelemedPharmacy(rs.getString(6));
			emailTemplate.setId(id);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return emailTemplate;
	}

	public int updateEmailConfugureInfo(EmailTemplate emailTemplate, int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_email_configure set  userName = ?,password = ?,hostName = ?,ad=?  where loginId = "+id+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			//String encPassword = Encryption.encryptSHA(emailTemplate.getEmailPassword());
			preparedStatement.setString(1, emailTemplate.getEmailUserName());
			preparedStatement.setString(2, emailTemplate.getEmailPassword());
			preparedStatement.setString(3, emailTemplate.getEmailHostName());
			preparedStatement.setString(4, emailTemplate.getAdDuration());
			
		

			result = preparedStatement.executeUpdate();
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
		return result;
	}

	public EmailTemplate getEditEmailConfigureDetails(int selectedId) {
		PreparedStatement preparedStatement = null;
		EmailTemplate emailTemplate = new EmailTemplate();
		String sql = "select id,userName,password,hostName,loginId,ad from apm_email_configure where id = "+selectedId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
			emailTemplate.setEmailConfigureId(rs.getString(1));
			emailTemplate.setEmailUserName(rs.getString(2));
			emailTemplate.setEmailPassword(rs.getString(3));
			emailTemplate.setEmailHostName(rs.getString(4));
			emailTemplate.setId(rs.getInt(5));
			emailTemplate.setAdDuration(rs.getString(6));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return emailTemplate;
	}

	public int getLatestAppointmentIdByPract(String userid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select max(id) from apm_available_slot where diaryuserid = "+userid+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public String getClientId(String userid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select id from apm_patient where third_party_name_id = "+userid+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int getGPId(int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select id from apm_gp_details where tpid = "+selectedid+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	/*public int updatePasswordEmailConfugure(EmailTemplate emailTemplate, int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_email_configure set password =?  where loginId = "+id+"";
		try{
			
			String encPassword = Encryption.encryptSHA(emailTemplate.getEmailPassword());
			preparedStatement = connection.prepareStatement(sql);
			//preparedStatement.setString(1, emailTemplate.getEmailUserName());
			preparedStatement.setString(1, encPassword);
			//preparedStatement.setString(2, emailTemplate.getEmailHostName());
			//preparedStatement.setInt(3, id);

			result = preparedStatement.executeUpdate();
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
		return result;
	}
*/
	public String getUserNameOfTemplate(String tempId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select userName from apm_email_template where id = "+tempId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			
		}
		return result;
	}

	public String showEmailConfigurePwd(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select password from apm_email_configure where loginId = "+id+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			
		}
		return result;
	}

	@Override
	public ArrayList<EmailTemplate> getFileNameBytemplate(String tempId) {
		PreparedStatement preparedStatement = null;
		ArrayList<EmailTemplate> list = new ArrayList<EmailTemplate>();
		String sql = "select filename,id from mail_attachment where templateid = "+tempId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				EmailTemplate emailTemplate =new EmailTemplate();
				
				emailTemplate.setUserImageFileName(rs.getString(1));
				emailTemplate.setId(rs.getInt(2));
				list.add(emailTemplate);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	
	}

	@Override
	public int deleteAttachment(String id) {
		int result = 0;

		try {
			String sql = "delete from mail_attachment where id ='"+id+"' ";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
		
	}
	

	
	
}
