package com.apm.common.web.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;

public class LogoutAction extends BaseAction{
	
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	HttpSession session = request.getSession(true);
	public String execute() throws Exception {
		

		
		
		/*HttpSession session = request.getSession(true);
		
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		
		String apmuserlist = (String)session.getAttribute("apmuserlist");
		
		String temp[] = apmuserlist.split(",");
		
		StringBuffer str = new StringBuffer();
		
		for(int i=0;i<temp.length;i++){
			if(!temp[i].equals(loginInfo.getUserId())){
				str.append(temp[i] + ",");
			}
		}
		
		  if(str.length()!=0){
	        	String result = str.substring(0, str.length()-1);
	        	session.setAttribute("apmuserlist", result);
	        }*/
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			
			//connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin","root","mysql");
			connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			
			session.removeAttribute("ipddash_sessionbedlist");
			session.removeAttribute("ipddash_firsttime");
			session.removeAttribute("ipddash_totalbookedbed");
			int update = clinicDAO.updateLogoutStatus(loginInfo.getUserId());
			
			String userid = loginInfo.getUserId();
			connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			clinicDAO = new JDBCClinicDAO(connection);
			int del = clinicDAO.deleteApmloggedUser(userid);
			
			if(loginInfo.getUserType()==5){
				LoginHelper.removeLoginInfo(request);
//				request.setAttribute("redirUrl", "http://"+loginInfo.getLinkaddress()+":8080/YUVICARE/Pureseva?title=&firstname=&lastname=&email=&clinicid="+loginInfo.getClinicUserid()+"&mob=&date=&diaryuserid=&gender=&dob=&uhid=");
//			    return "redir";	
				return "esclogout";
			}else{
				LoginHelper.removeLoginInfo(request);
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		
		
		return SUCCESS;
	}
	
	public String mob(){
		try{
			
			LoginHelper.removeLoginInfo(request);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "esclogout";
	}
public String logoutwithsession() throws SQLException {
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		
		//connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin","root","mysql");
		connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		
		ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
		
		session.removeAttribute("ipddash_sessionbedlist");
		session.removeAttribute("ipddash_firsttime");
		session.removeAttribute("ipddash_totalbookedbed");
		int update = clinicDAO.updateLogoutStatus(loginInfo.getUserId());
		
		String userid = loginInfo.getUserId();
		connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		clinicDAO = new JDBCClinicDAO(connection);
		int del = clinicDAO.deleteApmloggedUser(userid);
		session.removeAttribute(loginInfo.getUserId());
		if(loginInfo.getUserType()==5){
			LoginHelper.removeLoginInfo(request);
//			request.setAttribute("redirUrl", "http://"+loginInfo.getLinkaddress()+":8080/YUVICARE/Pureseva?title=&firstname=&lastname=&email=&clinicid="+loginInfo.getClinicUserid()+"&mob=&date=&diaryuserid=&gender=&dob=&uhid=");
//		    return "redir";	
			return "esclogout";
		}else{
			LoginHelper.removeLoginInfo(request);
		}
		
		
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		
		connection.close();
	}
	
	
	
	return SUCCESS;
}
}
