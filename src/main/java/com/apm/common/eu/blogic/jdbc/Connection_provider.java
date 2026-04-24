package com.apm.common.eu.blogic.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.RollingFileAppender;
import org.apache.struts2.ServletActionContext;

import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.common.web.utils.PopulateList;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;



public class Connection_provider {

	private static final Logger log = Logger.getLogger(Connection_provider.class);
	
	static HttpServletRequest request = null;
	
	// welcome Priyanka.
	
public static Connection getconnection() throws SQLException
{
	 request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	
	HttpSession session = request.getSession(true);
	
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	
	int dbType = 0;
	
	if(session.getAttribute("dbTypes")!=null){
		 dbType = (Integer)session.getAttribute("dbTypes");
	}
	
	String database = "";
	
	if(dbType == 0){
		database = "admin";
		if(loginInfo!=null){
			database = loginInfo.getDbName();
		}
	}else{
		database = "apm";
	}
	
	
	Connection con=null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+database+"",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		System.out.println("done");
		session.setAttribute("hisconnection", con);
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
		//log.debug("##########################"+e.getMessage());
		DatabaseMetaData dmd = con.getMetaData();
		String url = dmd.getURL() + " username = " + dmd.getUserName();
		log.debug("##########################"+url);
		log.debug("@@@@@@@@@"+Constants.DB_HOST + "/" + e.getMessage());
		System.out.println(url);
	}
	return con;
}



public static void main(String[] args) throws SQLException {
	Connection_provider.getconnection();
	
}
}


