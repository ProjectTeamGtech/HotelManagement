package com.apm.sendemail.java4s;


import java.sql.Connection;
import java.util.Timer;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.apm.common.web.action.LoginAction;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.sendemail.javaConstants.Constants;


	public class DBScheduler
	{
		
		private static final Logger log = Logger.getLogger(DBScheduler.class);

		public void callScheduler(Connection connection,LoginInfo loginInfo,HttpServletRequest request,int appointmentid) throws Exception
		{
			log.debug("*****************appointment4");

			System.out.println("Scheduler Starterd...");
			ReadPropertiesFile.readConfig();
			Timer timer = new Timer();

			timer.scheduleAtFixedRate(new Testing(connection,loginInfo,request,appointmentid), getTimePrecision(Constants.delay), getTimePrecision(Constants.timetoquery));

		}

		public long getTimePrecision(String value) throws Exception
		{
			long  l = 0;
			String val="";
			try
			{
				if(value.endsWith("d") || value.endsWith("D"))
				{
					val  = value.substring(0,value.length()-1);
					l = Long.parseLong(val) * 24 * 60 * 60 * 1000;
				}

				else if(value.endsWith("h") || value.endsWith("H"))
				{

				 val  = value.substring(0,value.length()-1);
				 l = Long.parseLong(val) * 60 * 60 * 1000;

				}
				else if(value.endsWith("m") || value.endsWith("M"))
				{
					 val  = value.substring(0,value.length()-1);
					 l = Long.parseLong(val) * 60 * 1000;
				}
				else if(value.endsWith("s") || value.endsWith("S"))
				{

					val  = value.substring(0,value.length()-1);
					l = Long.parseLong(val) * 1000;
				}
				else
				{

					l = Long.parseLong(value);
				}

			}
			catch(Exception e)
			{

	            throw new Exception(e);
			}

			return l;
		}
		public static void main(String a[]) throws Exception
		{
			/*DBScheduler dbs = new DBScheduler();
			dbs.callScheduler();*/
		}

	}