package com.apm.common.web.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;



public class DefaultUserAction extends BaseAction{
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpSession session = request.getSession(true);
	
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	public String execute() throws Exception {
		
		if(!verifyLogin(request)){
			return HOMEPAGE;
		}
		
		
		if(loginInfo.getUserType()==2){
			//return "gotodashboard";
			return "maindashboard";
		}
			
		if(loginInfo.getUserType()==4){
			//return "gotoweekdashboard";
			return "maindashboard";
		}
				
				
	
		return HOMEPAGE;
	}
	
	

	

	public String abtus(){
		
		return "abtus";
	}

}
