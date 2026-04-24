package com.apm.common.web.form;
public class ResetPasswordForm {
	private String emailId;
	private String password;
	private String confirmPassword;
	private String oldPassword;
	private String errpassword;

	
	
	
	public String getErrpassword() {
		return errpassword;
	}

	public void setErrpassword(String errpassword) {
		this.errpassword = errpassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
