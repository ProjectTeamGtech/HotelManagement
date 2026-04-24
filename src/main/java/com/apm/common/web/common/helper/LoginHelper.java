package com.apm.common.web.common.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;







public class LoginHelper {
	
	/** Key to save log in status in session */
	private static final String LOGGED_IN = "LOGGED_IN";

	/** Key to save user information in session */
	private static final String LOGIN_INFO = "LOGIN_INFO";

	/**
	 * 
	 * verify login status of user
	 * 
	 * @param session current session map
	 * @return true if user is logged in, else false
	 */
	public static boolean verifyLoggedIn(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		boolean loginVerify = false;
		
		if( (session != null) && (session.getAttribute(LOGIN_INFO) != null) && session.getAttribute(LOGGED_IN) != null ){
			loginVerify = Boolean.parseBoolean(session.getAttribute(LOGGED_IN).toString());
		}
		return loginVerify;		// get login info from session and return it as boolean
	}
	/**
	 * 
	 * save user in session
	 * 
	 * @param session current session map
	 * @param userInfo user information object
	 */
	public static void saveLoginInfo(HttpServletRequest request, LoginInfo loginInfo) {
		HttpSession session = request.getSession();
		if(session != null){
			session.setAttribute(LOGGED_IN, true);			// set login status true
			session.setAttribute(LOGIN_INFO, loginInfo);		// set user information
		}
	}
	/**
	 * 
	 * get user information from session
	 * 
	 * @param session current session map
	 * @return user information
	 */
	public static LoginInfo getLoginInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session != null){
			return (LoginInfo)session.getAttribute(LOGIN_INFO);
		}
		else{
			return null;
		}
	}
	/**
	 *  remove user from session
	 * 
	 * @param session current session map
	 */
	public static void removeLoginInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session != null){
			session.setAttribute(LOGGED_IN, false);	// set login status false
			session.removeAttribute(LOGIN_INFO);		// remove user information
			session.removeAttribute("selectedBranch");
			session.removeAttribute("selectedBranchID");
			session.removeAttribute("logininfo");
			session.removeAttribute("sessionselectedclientid");
			session.removeAttribute("apmuserlist");
			session.removeAttribute("diaryYear");
			session.removeAttribute("sessioncommencing");
			session.removeAttribute("sessionadmissionid");
			session.removeAttribute("invstList");
			session.removeAttribute("priscList");
			session.removeAttribute("invstypelist");
			session.removeAttribute("delPriscList");
			session.removeAttribute("scount");
			session.removeAttribute("appclientid");
			session.removeAttribute("logintimedrid");
			//session.removeAttribute("openedb");
			
		}		
	}
}