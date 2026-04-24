package com.apm.common.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;





import com.apm.common.web.common.helper.LoginHelper;
import com.opensymphony.xwork2.ActionSupport;



public class BaseAction extends ActionSupport {
	
	 private static final long serialVersionUID = -2613425890762568273L;
	
	 /** session will be injected by framework at runtime using setter injection */
	 private Map<String, Object> session;				
	  
	 /* Start : Return types as String for actions */
	 /* These will be used by subclasses only so declared as protected */
	 
	 /** homepage of site */
	 protected static final String HOMEPAGE = "homepage";	
	 
	 /** to return populate */
	 protected static final String POPULATE = "populate";	
	 
	 /** to return login */
	 protected static final String LOGIN = "login";
	 
	 /** to return activation page */
	 protected static final String ACTIVATE = "activate";
	 
	 protected static final String ADD_PG = "addPg";
	 
	 protected static final String ADD_QL = "addQl";
	 
	 protected static final String ADD_CT = "addCt";
	 
	 protected static final String ADD_CE = "addCe";
	 
	 protected static final String ADD_PE = "addPe";
	 
	 protected static final String ADD_PROJECT = "add";

	 public boolean verifyLogin(HttpServletRequest request){
			
			return LoginHelper.verifyLoggedIn(request);
		}

	 
	 
	 
	
	
	
}