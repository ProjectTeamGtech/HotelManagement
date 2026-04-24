package com.apm.Tools.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Tools.eu.bi.SMSTemplateDAO;
import com.apm.Tools.eu.entity.EmailTemplate;

public class JDBCSMSTemplateDao implements SMSTemplateDAO {

	Connection connection;

	public JDBCSMSTemplateDao(Connection connection) {
		this.connection = connection;
	}

	
	public ArrayList<EmailTemplate> getAllSMSTemplates() {
		// TODO Auto-generated method stub

		ArrayList<EmailTemplate> templates = new ArrayList<EmailTemplate>();

		try {

			PreparedStatement ps = connection
					.prepareStatement("select * from apm_sms_template");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				EmailTemplate emailTemplate = new EmailTemplate();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String text = rs.getString(3);
				emailTemplate.setId(id);
				emailTemplate.setTemplateName(name);
				emailTemplate.setText(text);
				templates.add(emailTemplate);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return templates;
	}

	
	public int addSMSTemplate(EmailTemplate emailTemplate) {
		// TODO Auto-generated method stub

		int res = 0;
		try {

			PreparedStatement ps = connection
					.prepareStatement("insert into apm_sms_template (name,text) values (?,?) ");
			ps.setString(1, emailTemplate.getTemplateName());
			ps.setString(2, emailTemplate.getText());
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		}

		return res;
	}

	
	public EmailTemplate getEmailTemplate(int id) {
		// TODO Auto-generated method stub
		EmailTemplate emailTemplate = new EmailTemplate();
		try {

			PreparedStatement ps = connection
					.prepareStatement("select * from apm_sms_template where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String templatename = rs.getString("name");
				String text = rs.getString("text");
				emailTemplate.setId(id);
				emailTemplate.setTemplateName(templatename);
				emailTemplate.setText(text);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return emailTemplate;
	}

	
	public int updateSMSTemplate(EmailTemplate emailTemplate) {
		// TODO Auto-generated method stub
		int res = 0;
		try {

			PreparedStatement ps = connection
					.prepareStatement("update apm_sms_template set name=?,text=? where id=? ");
			ps.setString(1, emailTemplate.getTemplateName());
			ps.setString(2, emailTemplate.getText());
			ps.setInt(3, emailTemplate.getId());
			res = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return res;
	}

	
	public int deleteSMSTemplate(int id) {
		// TODO Auto-generated method stub
		int res = 0;
		try {

			PreparedStatement ps = connection
					.prepareStatement("delete from apm_sms_template where id=?");
			ps.setInt(1, id);
			res = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}
