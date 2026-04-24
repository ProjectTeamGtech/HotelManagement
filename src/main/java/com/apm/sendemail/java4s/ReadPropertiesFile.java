package com.apm.sendemail.java4s;



import java.io.FileInputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.apm.common.web.utils.PopulateList;
import com.apm.sendemail.javaConstants.Constants;
import com.opensymphony.xwork2.ActionContext;


public class ReadPropertiesFile
{
	
	public  static void readConfig() throws Exception
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		try
		{

		    Properties pro = new Properties();
		    String path =  request.getRealPath("props/java4_Props.properties");

		    pro.load(new FileInputStream(path));	   

		    Constants.delay = pro.getProperty("delay");
		    Constants.timetoquery = pro.getProperty("timetoquery");
		    Constants.setFrom = pro.getProperty("setFrom");
		    Constants.setPassword = pro.getProperty("setPassword");
		    Constants.emailTO = pro.getProperty("emailTO");	  		   

		}
		catch(Exception e)
		{
            throw new Exception(e);
		}

	}

}