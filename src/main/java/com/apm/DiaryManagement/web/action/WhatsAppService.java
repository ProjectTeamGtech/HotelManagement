package com.apm.DiaryManagement.web.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;

import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.common.helper.LoginInfo;

public class WhatsAppService {
	
	/** URL Encoder for white space */
	private final String ENCODE_SPACE = "%20";
	
	/** URL Encoder for comma */
	private final String ENCODE_COMMA = "%2C";
	
	/** URL Encoder for line feed (new line) */
	private final String ENCODE_LF = "%0A";
	
	
	private final String ENCODE_AND ="%26";
	
	public boolean sendMsg(LoginInfo loginInfo, String mobileNumber, String message)throws Exception{
		Connection connection=null;
		boolean isMsgSent = false;		// initially sms sent is false
		try{
			String hospitalsms_url="";
			connection=Connection_provider.getconnection();
			MasterDAO masterdao=new JDBCMasterDAO(connection);
			message=URLEncoder.encode(message, "UTF-8");
			String apikey=masterdao.getApikey();
		
			//hospitalsms_url="http://private.itswhatsapp.com/wapp/api/send?apikey=9352645df93e438e871cc59ad88c6ad5&mobile="+mobileNumber+"&msg="+message+"";
			hospitalsms_url="http://private.itswhatsapp.com/wapp/api/send?apikey="+apikey+"&mobile="+mobileNumber+"&msg="+message+"";

			String msgUrl = hospitalsms_url.replace("{0}", mobileNumber).replace("{1}", message);
			// encode url by replacing space, comma and new line with corresponding encoding code
			msgUrl = msgUrl.replace(" ", ENCODE_SPACE).replace(",", ENCODE_COMMA).replace("\n", ENCODE_LF);
			System.out.println(msgUrl);
			URL url = new URL(msgUrl);	// get url from url string
				
			URLConnection urlConnection = url.openConnection();	// open url connection
			urlConnection.connect();	// connect to url
				
			HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;	// get http url connection object from url connection
			int responseCode = httpConnection.getResponseCode();	// get response code
			String date_time = DateTimeUtils.getUKCurrentDataTime("India");
			if(responseCode == HttpURLConnection.HTTP_OK){	// if response code is HTTP+OK then message sent successfully 
				isMsgSent = true;
				String error="";
				int status=1;
				int smslog=0;
				 smslog=masterdao.insertSmsdata(loginInfo,msgUrl,date_time,mobileNumber,status,error);
				try {
					
					int res=masterdao.updatewhatsappcount(1,message);
					 smslog=masterdao.insertSmsdata(loginInfo,msgUrl,date_time,mobileNumber,status,error);
				} catch (Exception e) {
					e.printStackTrace();
					 smslog=masterdao.insertSmsdata(loginInfo,msgUrl,date_time,mobileNumber,0,e.getMessage());
				}
			}
			
			
								
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		return isMsgSent;
	
	}

	public boolean sendMsgvacc(LoginInfo loginInfo, String mobileNumber, String message, Connection connection) {
	//	Connection connection=null;
		boolean isMsgSent = false;		// initially sms sent is false
		try{
			String hospitalsms_url="";
			//connection=Connection_provider.getconnection();
			MasterDAO masterdao=new JDBCMasterDAO(connection);
			message=URLEncoder.encode(message, "UTF-8");
			String apikey=masterdao.getApikey();
			//hospitalsms_url="http://private.itswhatsapp.com/wapp/api/send?apikey=9352645df93e438e871cc59ad88c6ad5&mobile="+mobileNumber+"&msg="+message+"";
			hospitalsms_url="http://private.itswhatsapp.com/wapp/api/send?apikey="+apikey+"&mobile="+mobileNumber+"&msg="+message+"";

			String msgUrl = hospitalsms_url.replace("{0}", mobileNumber).replace("{1}", message);
			// encode url by replacing space, comma and new line with corresponding encoding code
			msgUrl = msgUrl.replace(" ", ENCODE_SPACE).replace(",", ENCODE_COMMA).replace("\n", ENCODE_LF);
			System.out.println(msgUrl);
			URL url = new URL(msgUrl);	// get url from url string
				
			URLConnection urlConnection = url.openConnection();	// open url connection
			urlConnection.connect();	// connect to url
				
			HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;	// get http url connection object from url connection
			int responseCode = httpConnection.getResponseCode();	// get response code
			String date_time = DateTimeUtils.getUKCurrentDataTime("India");
			String error="";
			int status=1;
			int smslog=0;
			 smslog=masterdao.insertSmsdata(loginInfo,msgUrl,date_time,mobileNumber,status,error);
			if(responseCode == HttpURLConnection.HTTP_OK){	// if response code is HTTP+OK then message sent successfully 
				isMsgSent = true;
			
				try {
					
					
					int res=masterdao.updatewhatsappcount(1,message);
					 smslog=masterdao.insertSmsdata(loginInfo,msgUrl,date_time,mobileNumber,status,error);
				} catch (Exception e) {
					e.printStackTrace();
					 smslog=masterdao.insertSmsdata(loginInfo,msgUrl,date_time,mobileNumber,0,e.getMessage());
				}
			}
			
			
								
		}catch (Exception e) {
			e.printStackTrace();
		}
		return isMsgSent;
		
	}
	
	
}
