package com.apm.DiaryManagement.web.action;

import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.common.web.action.LoginAction;
import com.apm.common.web.common.helper.LoginInfo;

public class SendSms {
	public static final String USERNAME = "pranamconsultancy";
	public static final String PASSWORD = "Advanced";
	
	private static final Logger log = Logger.getLogger(SendSms.class);
	public static void send(String message,String recipient,LoginInfo loginInfo,EmailLetterLog emailLetterLog){
		
		try{
			
			message = message.replaceAll(" ", "%20");
			String requestUrl = "http://www.smsjust.com/blank/sms/user/urlsms.php?username="+USERNAME+"&pass="+PASSWORD+"&senderid=ESCAPQ&message="+message+"&dest_mobileno="+recipient+"&response=Y";
			
			URL url = new URL(requestUrl);
			HttpURLConnection uc = (HttpURLConnection)url.openConnection();
//			System.out.println(uc.getResponseMessage());
			uc.disconnect();
		} catch(Exception ex) {
			log.debug("sendsms"+ex.getMessage());
//			System.out.println(ex.getMessage());
		}
		
	}
}


