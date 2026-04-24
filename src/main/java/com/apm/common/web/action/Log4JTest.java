package com.apm.common.web.action;

import org.apache.log4j.PropertyConfigurator;

public class Log4JTest {
	
	public static void main(String[] args) {
		   PropertyConfigurator.configure("log4j.properties");
		   
	        //Log in console in and log file
	       // logger.debug("Log4j appender configuration is successful !!");
		   
		   System.out.println("success");
	}

}
