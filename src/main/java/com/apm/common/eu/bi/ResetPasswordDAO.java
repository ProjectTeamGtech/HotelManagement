package com.apm.common.eu.bi;

public interface ResetPasswordDAO {

	int updatePassword(String emailId, String password);

	boolean isEmailIDExist(String emailId);

	boolean isOldPassword(String oldpassword, String userId);

	int chnagePswd(String userId, String password);

	String getlinkAddress(String emailId);

}
