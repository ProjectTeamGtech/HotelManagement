package com.apm.common.web.common.helper;





import java.util.Map;

/**
 * 
 * Session Helper is used for session related operation like saving user
 * in session, removing user from session, getting user information etc.
 * Each method of this class requires current session object, which should 
 * be passed by calling method.  
 *
 * @author Yogesh Wankhede
 * @copyright Techior Solutions 2010
 * @date Sep 2, 2010
 * 
 */
public class SessionHelper {
	
	/** Key to save log in status in session */
	private static final String LOGGED_IN = "LOGGED_IN";
	
	/** Key to save user information in session */
	private static final String LOGIN_INFO = "LOGIN_INFO";
	
	/** Key to save login input box visibility option in session */
	private static final String SHOW_LOGIN = "SHOW_LOGIN";
	
	/** Key to save current profile option */
	private static final String CURRENT_PROFILE_OPTION = "CURRENT_PROFILE_OPTION";
	
	/**Key to save UserType */
	private static final String USER_TYPE = "USER_TYPE";
	
	private static final String CURRENT_RESUME_RESULT_OPTION = "CURRENT_RESUME_RESULT_OPTION";
	
	private static final String REDIRECT_PAGE = "REDIRECT_PAGE";
	
	/** Key to save JOBLIST in status in session */
	private static final String JOBLIST_IN = "JOBLIST_IN";
	
	/** Key to save JOB LIST information in session */
	private static final String JOBLIST_INFO = "JOBLIST_INFO";
	
	/**Key to save manage resume information */
	private static final String RESUME_MANAGE_INFO = "RESUME_MANAGE_INFO";
	
	private static final String RESUME_MANAGE_IN = "RESUME_MANAGE_IN";
	
	/** Key to save APPLIED JOBLIST in status in session */
	private static final String APPLIED_JOBLIST_IN = "APPLIED_JOBLIST_IN";
	
	/** Key to save APPLIED JOB LIST information in session */
	private static final String APPLIED_JOBLIST_INFO = "APPLIED_JOBLIST_INFO";
	
	private static final String POST_JOB_STATUS_OPTION = "POST_JOB_STATUS_OPTION";


	/**
	 * 
	 * verify login status of user
	 * 
	 * @param session current session map
	 * @return true if user is logged in, else false
	 */
	public static boolean verifyLoggedIn(Map<String,Object> session) {
		if( (session == null) || (session.get(LOGIN_INFO) == null) ){
			return false;
		}
		return (Boolean)session.get(LOGGED_IN);		// get login info from session and return it as boolean
	}

	/**
	 * 
	 * save user in session
	 * 
	 * @param session current session map
	 * @param userInfo user information object
	 */
	public static void saveLoginInfo(Map<String,Object> session, LoginInfo loginInfo) {
		if(session != null){
			session.put(LOGGED_IN, true);			// set login status true
			session.put(LOGIN_INFO, loginInfo);		// set user information
		}
	}
	
	/**
	 *  remove user from session
	 * 
	 * @param session current sesion map
	 */
	public static void removeLoginInfo(Map<String,Object> session) {
		if(session != null){
			session.put(LOGGED_IN, false);	// set login status false
			session.remove(LOGIN_INFO);		// remove user information
		}		
	}
	
	
	
	/**
	 * 
	 * get user information from session
	 * 
	 * @param session current session map
	 * @return user information
	 */
	public static LoginInfo getLoginInfo(Map<String,Object> session) {
		if(session != null){
			return (LoginInfo)session.get(LOGIN_INFO);
		}else{
			return null;
		}
	}
	
	
	/**
	 * Set login box visibility 
	 */
	public static void showLogin (Map<String,Object> session, boolean status){
		if(session != null){
			session.put(SHOW_LOGIN, status);
		}
	}
	
	public static void setCurrentProfileOption (Map<String, Object> session, String profileOption){
		if(session != null){
			session.put(CURRENT_PROFILE_OPTION, profileOption);
		}
	}
	
	public static String getCurrentProfileOption (Map<String, Object> session){
		if(session != null){
			return (String)session.get(CURRENT_PROFILE_OPTION);
		}else{
			return null;
		}
	}
	
	public static void setUserType(Map<String, Object> session, String userType){
		if(session != null){
			session.put(USER_TYPE, userType);
		}
	}
	
	public static String getUserType(Map<String, Object> session){
		if(session != null){
			return (String)session.get(USER_TYPE);
		}else{
			return null;
		}
		
	}
	
	public static void removeUserType(Map<String,Object> session) {
		
		if(session != null){
			
			session.remove(USER_TYPE);		// remove user information
		}		
	}
	
	
	public static void setCurrentPostJobStatusOption(Map<String, Object> session, String postJobOption){
		if(session != null){
			session.put(POST_JOB_STATUS_OPTION, postJobOption);
		}
	}
	
	public static String getCurrentPostJobStatusOption(Map<String, Object> session){
		if(session != null){
			return (String)session.get(POST_JOB_STATUS_OPTION);
		}else{
			return null;
		}
	}
	
	public static void setCurrentResumeResultOption (Map<String, Object> session, String resumeResultOption){
		if(session != null){
			session.put(CURRENT_RESUME_RESULT_OPTION, resumeResultOption);
		}
	}
	
	public static String getCurrentResumeResultOption (Map<String, Object> session){
		if(session != null){
			return (String)session.get(CURRENT_RESUME_RESULT_OPTION);
		}else{
			return null;
		}
	}
	
	
	
	public static void setRedirectPage (Map<String, Object> session, String redirectPage){
		if(session != null){
			session.put(REDIRECT_PAGE, redirectPage);
		}
	}
	
	public static String getRedirectPage (Map<String, Object> session){
		if(session != null){
			return (String)session.get(REDIRECT_PAGE);
		}else{
			return null;
		}
	}

}