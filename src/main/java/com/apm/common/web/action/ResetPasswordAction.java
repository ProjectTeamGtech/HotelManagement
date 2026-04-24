package com.apm.common.web.action;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.DiaryManagement.web.action.EmbeddedImageEmailUtil;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.common.eu.bi.ResetPasswordDAO;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.eu.blogic.jdbc.JDBCResetPassword;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.common.web.form.ResetPasswordForm;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class ResetPasswordAction extends BaseAction implements ModelDriven<ResetPasswordForm>{
	ResetPasswordForm resetPasswordForm = new ResetPasswordForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	public String input(){
		return "forgotPassword";
	}
	
	public String forgotPaswd(){
		//  24 jan 2018
		String address = "";
		try {
			Connection connection = null;
			String emailId = resetPasswordForm.getEmailId();
			connection = Connection_provider.getconnection();
			ResetPasswordDAO resetPasswordDAO = new JDBCResetPassword(connection);
			address = resetPasswordDAO.getlinkAddress(emailId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String emailId = resetPasswordForm.getEmailId();
		String subject = "Reset Your HIS Account Password";
		StringBuffer str1 = new StringBuffer();
		str1.append("<table width = '50%'> ");
		str1.append("<tr>");
		str1.append("<td colspan ='2'>Dear User,</td>");
		str1.append("</tr>");
		str1.append("<tr>");
		str1.append("<td>You have requested to reset your password. Please follow below process to reset:"
				+ "Please click on the link Reset Password,  your will be taken to HIS, where you can change your password.</td> ");
		str1.append("</tr>");
		
		
		str1.append("<tr>");
		//str1.append("<td><a href='http://myohost:8080/APM1.1/changeResetPassword' target = 'null'>Reset Password</a></td>");		
		//str1.append("<td><a href='http://localhost:8080/APM21Feb/changeResetPassword' target = 'null'>Reset Password</a></td>");
		str1.append("<td><a href='http://"+address+":8080/HISLIVE/changeResetPassword' target = ''>Reset Password</a></td>");
		//str1.append("<td><a href='http://localhost:8080/HISTEST/changeResetPassword' target = ''>Reset Password</a></td>");
		//str1.append("<td><a href='http://154.70.153.191:8080/HIS/changeResetPassword' target = ''>Reset Password</a></td>");
		//str1.append("<td><a href='http://localhost:8080/HIS6NEW/changeResetPassword' target = ''>Reset Password</a></td>");
		str1.append("</tr>");
		str1.append("</br>");
		str1.append("<tr>");
		
		str1.append("<td>Thank you for your HIS.</td> ");
		str1.append("</tr>");
		str1.append("<tr>");
		str1.append("<td>care@escapeq.com</td>");
		str1.append("</tr>");
		str1.append("</table>");
		
		//EmbeddedImageEmailUtil.sendMailForForgotPassword(emailId, "", subject, str1.toString());
		EmbeddedImageEmailUtil.sendMailForForgotPasswordNew(emailId, "", subject, str1.toString());
		
		
		return "linkSendSucess";
	}
	
	public String change(){
		
		return "resetPasswordPage";
	}
	
	public String modify() throws Exception{
		String emailId = resetPasswordForm.getEmailId();
		String password = resetPasswordForm.getPassword();
		Connection connection = null;
		try{
		connection = Connection_provider.getconnection();
		ResetPasswordDAO resetPasswordDAO = new JDBCResetPassword(connection);
		int result = resetPasswordDAO.updatePassword(emailId,password);
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return "modifiedSucess";
	}
	public String checkEmailId(){
		try{
		Connection connection = null;
		String emailId = (String)request.getParameter("emailId");
		
		connection = Connection_provider.getconnection();
		
		
		ResetPasswordDAO resetPasswordDAO = new JDBCResetPassword(connection);
		// check if user with given user id already exist
		boolean emailIdExist = resetPasswordDAO.isEmailIDExist(emailId);

		// if user id already exist then set response 'false'
		if(emailIdExist){
			response.getWriter().write("false");
		}else{	// else set response 'true'
			response.getWriter().write("true");
		}}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public String changePasswordPage(){
		
		return "changePasswordPage";
	}
	public String changePaswd() throws Exception{
		
		String password = resetPasswordForm.getPassword();
		
		String oldPassword = resetPasswordForm.getPassword();
		Connection connection = null;
		try{
			connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");

			ResetPasswordDAO resetPasswordDAO = new JDBCResetPassword(connection);
			int result = resetPasswordDAO.chnagePswd(loginInfo.getUserId(),password);
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		

		return "changePaswdSucess";
	}
	public String checkcheckThisOldPswd()throws Exception{
		try{
			Connection connection = null;
			String oldpassword=resetPasswordForm.getOldPassword();
			
			connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			
			
			ResetPasswordDAO resetPasswordDAO = new JDBCResetPassword(connection);
			// check if user with given user id already exist
			boolean emailIdExist = resetPasswordDAO.isOldPassword(oldpassword,loginInfo.getUserId());

			// if user id already exist then set response 'false'
			if(emailIdExist){
				
				resetPasswordForm.setOldPassword(oldpassword);
				resetPasswordForm.setErrpassword(null);
				//resetPasswordForm.setErrpassword("");
			}else{	// else set response 'true'
				resetPasswordForm.setOldPassword(oldpassword);
				resetPasswordForm.setErrpassword("old password is wrong");
			}
		     connection.close();	
		  }
		    
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				
			}
			return "changePasswordPage";
		
	}
	public ResetPasswordForm getModel() {
		// TODO Auto-generated method stub
		return resetPasswordForm;
	}
	
	public String updateuserpwd() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			String mobile = request.getParameter("mobile");
			String userid = request.getParameter("userid");
			//  01 feb 2018
			String confirm_pwd = request.getParameter("confirmPassword");
			//String confirm_pwd = request.getParameter("confirm_pwd");
			int id = userProfileDAO.getIdFromUserId(loginInfo.getUserId());
			
			UserProfile userProfile = new UserProfile();
			userProfile.setPassword(confirm_pwd);
			userProfile.setId(id);
			userProfile.setPhone(mobile);
			userProfile.setMobile(mobile);
			
			int res = userProfileDAO.updateLocalUserAdmin(loginInfo.getUserId(),confirm_pwd);
			
			int selectedid = userProfileDAO.updatePharmacyUserPwdbyId(userProfile);
			
			connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			userProfileDAO = new JDBCUserProfileDAO(connection);
			int result= userProfileDAO.updateAdminPharmacyUsrwd(userProfile, loginInfo.getClinicUserid(),loginInfo.getUserId()); 
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return "logoutuser";
	}
	
	public String validateoldpassword()throws Exception{
		try{
			Connection connection = null;
			String oldpassword=request.getParameter("oldpassword");
			
			connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			
			
			ResetPasswordDAO resetPasswordDAO = new JDBCResetPassword(connection);
			// check if user with given user id already exist
			boolean emailIdExist = resetPasswordDAO.isOldPassword(oldpassword,loginInfo.getUserId());

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+emailIdExist+"");
			
			connection.close();	
		  }
		    
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				
			}
			return null;
		
	}
	

}
