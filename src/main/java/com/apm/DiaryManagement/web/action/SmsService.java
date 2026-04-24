package com.apm.DiaryManagement.web.action;







import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.action.LoginAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;


public final class SmsService {
	/*HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession(false);
	LoginInfo loginInfo=LoginHelper.getLoginInfo(request);*/

	private static final Logger log = Logger.getLogger(SmsService.class);
	/** SMS Account User Id */
	//private final String SMS_ACC_USER_ID = "5611";
	private final String SMS_ACC_USER_ID = "GMSOFT";
	
	/** SMS Account Password */
	private final String SMS_ACC_PASSWORD = " l41P$Nv!";
	
	/** SMS Account Sender Id */
	private final String SMS_ACC_SENDER_ID = "GMSOFT";

	/** SMS URL Host */
	private final String SMS_URL_HOST = "http://www.smsjust.com/blank/sms/user/urlsms.php?";
	//private final String SMS_URL_HOST = "http://localhost:8080/api/pushsms.php?";
	
	/** SMS Sending URL Query string
	{0} - Recipients Mobile Number (multiple number can be added by separating with comma)
	{1} - Message Text  */
	/*private final String SMS_URL_QUERY = "username="+ SMS_ACC_USER_ID +
										 "&pass="+ SMS_ACC_PASSWORD +
										 "&senderid="+ SMS_ACC_SENDER_ID+
										 "&dest_mobileno={0}&message={1}&response=Y"; */
	private final String SMS_URL_QUERY = "username="+ SMS_ACC_USER_ID +
									 "&pass="+ SMS_ACC_PASSWORD +
									 "&senderid="+ SMS_ACC_SENDER_ID+
									 "&dest_mobileno={0}&message={1}&response=Y"; 
	
	/** SMS Url */
	private final String SMS_URL = SMS_URL_HOST + SMS_URL_QUERY;
	
	/** URL Encoder for white space */
	private final String ENCODE_SPACE = "%20";
	
	/** URL Encoder for comma */
	private final String ENCODE_COMMA = "%2C";
	
	/** URL Encoder for line feed (new line) */
	private final String ENCODE_LF = "%0A";
	
	
	private final String ENCODE_AND ="%26";
	
	/**
	 * Default constructor, Don't make it public or private
	 * It should be visible only within package so keep visibility default
	 */
	public SmsService(){ }
	
	/**
	 * Send sms by using sms provider
	 * 
	 * @param mobileNo mobile number to send sms
	 * @param messageText message text
	 * @return true if sms sent successfully, else false
	 */
	public synchronized boolean sendSms(String messageText, String  mobileNo, LoginInfo loginInfo, EmailLetterLog emailLetterLog, String templateid){
		boolean isSmsSent = false;
		boolean isunicode=false;
		if(loginInfo.isBalgopal()){
			if(DateTimeUtils.isNull(loginInfo.getClinicUserid()).equals("raibhattar") 
					|| DateTimeUtils.isNull(loginInfo.getClinicUserid()).equals("raiprachi")){
				boolean isfrombalgopal=false;
				isSmsSent = sendSmsHospital(messageText,mobileNo,loginInfo,emailLetterLog,isunicode,isfrombalgopal,templateid);
			}else{
				boolean isfrombalgopal=true;
				isSmsSent = sendSmsHospital(messageText,mobileNo,loginInfo,emailLetterLog,isunicode,isfrombalgopal,templateid);
			}
		}else if(DateTimeUtils.isNull(loginInfo.getClinicUserid()).equals("raibhattar") 
				|| DateTimeUtils.isNull(loginInfo.getClinicUserid()).equals("raiprachi")){
			boolean isfrombalgopal=false;
			isSmsSent = sendSmsHospital(messageText,mobileNo,loginInfo,emailLetterLog,isunicode,isfrombalgopal,templateid);
		}else if(loginInfo.isParihar()) {
			boolean isfrombalgopal=false;
			isSmsSent = sendSmsHospital(messageText,mobileNo,loginInfo,emailLetterLog,isunicode,isfrombalgopal,templateid);
		}else if(loginInfo.isLmh()) {
			boolean isfrombalgopal=false;
			isSmsSent = sendSmsHospital(messageText,mobileNo,loginInfo,emailLetterLog,isunicode,isfrombalgopal,templateid);
		}else{
		
			//pinacle sms serivce
			//isSmsSent = sendSmsManas(messageText,mobileNo,loginInfo,emailLetterLog,0);
			
			//sms leases
			isSmsSent = sendSmsLeaseService(messageText,mobileNo,loginInfo,emailLetterLog,isunicode,templateid);
		}
		return isSmsSent;
		
	}
	
	private boolean sendSmsLeaseService(String messageText, String mobileNo, LoginInfo loginInfo,
			EmailLetterLog emailLetterLog, boolean isunicode, String templateid) {
		boolean isSmsSent = false;		// initially sms sent is false
		// replace query parameters with actual mobile number and message text
		/*String hospitalsms_url ="http://msgalert.in/api/push?auth=b6df86456713e64b18aebade9c67f87a&mobno="+mobileNo+"&msg="+messageText+"";*/
		/*String hospitalsms_url ="http://msgalert.in/api?userid=balgopal&password=143balgopal&mobno="+mobileNo+"&msg="+messageText+"&senderid=MsgAlt&route=4";*/
		try{
			String msgtype="text";
			messageText = messageText.replace("&", ENCODE_AND);
			if(isunicode){
				//unicode
				messageText=URLEncoder.encode(messageText, "UTF-8");
				msgtype="unicode";
			}
			
			//sms leases
			String hospitalsms_url ="http://smsweb.smsleases.com/app/smsapi/index.php?key=35F25629FD83E8&campaign=0&routeid=9&type="+msgtype+"&contacts="+mobileNo+"&senderid=SMSSPT&msg="+messageText+"";
			
			//sms balgopal
			//String hospitalsms_url ="http://msgalert.in/api?userid=balgopal&password=143balgopal&mobno="+mobileNo+"&msg="+messageText+"&senderid=MsgAlt&route=4";
			String smsUrl = hospitalsms_url.replace("{0}", mobileNo).replace("{1}", messageText);
			
			// encode url by replacing space, comma and new line with corresponding encoding code
			smsUrl = smsUrl.replace(" ", ENCODE_SPACE).replace(",", ENCODE_COMMA).replace("\n", ENCODE_LF);
			boolean templeteidpresent=false;
			if(!DateTimeUtils.isNull(templateid).equals("")){
				templeteidpresent = true;
			}
			if(loginInfo.isSms_access() && templeteidpresent){
				URL url = new URL(smsUrl);	// get url from url string
				
				URLConnection urlConnection = url.openConnection();	// open url connection
				urlConnection.connect();	// connect to url
				
				HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;	// get http url connection object from url connection
				int responseCode = httpConnection.getResponseCode();	// get response code
				log.debug("sms@@@@@@@@@@"+ responseCode );
				log.debug("sms@@@@@@@@@@"+ urlConnection );
				
				if(responseCode == HttpURLConnection.HTTP_OK){	// if response code is HTTP+OK then message sent successfully 
					isSmsSent = true;
					Connection connection=null; 
					try {
						connection=Connection_provider.getconnection();
						MasterDAO masterDAO=new JDBCMasterDAO(connection);
						int res=masterDAO.updateSMSCounter(1,messageText);
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						connection.close();
					}
				}
			}
			
			
		}catch (Exception e) {
			log.debug("sms@@@@@@@@@@"+ e.getMessage() );
			
			e.printStackTrace();
			log.debug("sms@@@@@@@@@@"+ e.getMessage() );
		}
		
		return isSmsSent;
	}

	private boolean sendSmsHospital(String messageText, String mobileNo, LoginInfo loginInfo,EmailLetterLog emailLetterLog, boolean isunicode, boolean isfrombalgopal, String templateid) {
		boolean isSmsSent = false;		// initially sms sent is false
		// replace query parameters with actual mobile number and message text
		/*String hospitalsms_url ="http://msgalert.in/api/push?auth=b6df86456713e64b18aebade9c67f87a&mobno="+mobileNo+"&msg="+messageText+"";*/
		/*String hospitalsms_url ="http://msgalert.in/api?userid=balgopal&password=143balgopal&mobno="+mobileNo+"&msg="+messageText+"&senderid=MsgAlt&route=4";*/
		int x=0;
		try{
			String hospitalsms_url="";
			messageText = messageText.replace("&", ENCODE_AND);
			if(isfrombalgopal){
				//Balgopal [Sender ID - BGHOSP]
				if(isunicode){
					messageText=URLEncoder.encode(messageText, "UTF-8");
					//unicode
					//hospitalsms_url ="http://msgalert.in/api/push?auth=bbed6ad9a27f82b25c4c6456b8583400&mobno="+mobileNo+"&msg="+messageText+"";
					//hospitalsms_url="http://msgalert.in/api/push?auth=ce984b259f8097cba6d9755ba7849309&mobno="+mobileNo+"&msg="+messageText+"";
					//hospitalsms_url="http://msgalert.in/api?userid=balgopal&password=143balgopal&mobno="+mobileNo+"&msg="+messageText+"&senderid=BGHOSP&route=4&unicode=1&template_id="+templateid+"";
					hospitalsms_url="http://msgalert.in/apiv2?authkey=ec7aec9860bf4ec8ea6f5bfee2557a82&senderid=BGHOSP&numbers="+mobileNo+"&message="+messageText+"&route=4&unicode=1";
				}else{
					//String senderid="Yuvicr";
					//normal sms
					//hospitalsms_url="http://msgalert.in/api/push?auth=a6cd0cc04e6fb465e2bbe85898aab0cc&mobno="+mobileNo+"&msg="+messageText+"";
					//hospitalsms_url="http://msgalert.in/api?userid=balgopal&password=143balgopal&mobno="+mobileNo+"&msg="+messageText+"&senderid=BGHOSP&route=4&unicode=0&template_id="+templateid+"";
					//hospitalsms_url="http://msgalert.in/api?userid=Vinod Mishra&password=123456&mobno="+mobileNo+"&msg="+messageText+"&senderid="+senderid+"&route=4&unicode=0&template_id="+templateid+"";
					hospitalsms_url="http://msgalert.in/apiv2?authkey=ec7aec9860bf4ec8ea6f5bfee2557a82&senderid=BGHOSP&numbers="+mobileNo+"&message="+messageText+"&route=4&unicode=1";

				}
			}else if(loginInfo.isParihar()) {
				messageText=URLEncoder.encode(messageText, "UTF-8");
				String senderid="PRIHRH";
				//hospitalsms_url="http://wbox.icansms.com/API/WebSMS/Http/v1.0a/index.php?username=parihar&password=parihar@123&sender="+senderid+"&to="+mobileNo+"&message="+messageText+"&reqid=1&format={json|text}&route_id=532&Template_ID="+templateid+"&PE_ID=1701166125144084796";
				hospitalsms_url="http://wbox.icansms.com/API/WebSMS/Http/v1.0a/index.php?username=parihar&password=parihar@123&sender="+senderid+"&to="+mobileNo+"&message="+messageText+"&format={text}&route_id=532&Template_ID="+templateid+"&PE_ID=1701166125144084796&msgtype=unicode";

			}else if(loginInfo.isSjivh()) {
				messageText=URLEncoder.encode(messageText, "UTF-8");
				String senderid="SANIVH";
				//hospitalsms_url="http://wbox.icansms.com/API/WebSMS/Http/v1.0a/index.php?username=parihar&password=parihar@123&sender="+senderid+"&to="+mobileNo+"&message="+messageText+"&reqid=1&format={json|text}&route_id=532&Template_ID="+templateid+"&PE_ID=1701166125144084796";
				hospitalsms_url="http://msgalert.in/apiv2?authkey=fa1530fd256e713274b83d4d003e5d06&senderid="+senderid+"&numbers="+mobileNo+"&message="+messageText+"&route=4&unicode=1";

			}else if(loginInfo.isLmh()) {
				messageText=URLEncoder.encode(messageText, "UTF-8");
				//String senderid="PRIHRH";
				hospitalsms_url="https://sms.oriontele.co.in/api/mt/SendSMS?user=RDDCRC&password=Dcrc@123&senderid=RDDCRC&channel=Trans&DCS=8&flashsms=0&number="+mobileNo+"&text="+messageText+"&DLTTemplateId="+templateid+"";

			}else{
				//Bhatter clinic and Prachi clinic [Sender ID - BGCLNC]
				String senderid="BGCLNC";
				if(DateTimeUtils.isNull(loginInfo.getClinicUserid()).equals("raiprachi") && templateid.equals("1507163101554691948")){
					senderid = "PBCLNC";
				}
				if(isunicode){
					messageText=URLEncoder.encode(messageText, "UTF-8");
					//unicode
					//hospitalsms_url="http://msgalert.in/api/push?auth=ca50e84893e2d30aa71a6f46a7f319f7&mobno="+mobileNo+"&msg="+messageText+"";
					//hospitalsms_url="http://msgalert.in/api?userid=balgopal&password=143balgopal&mobno="+mobileNo+"&msg="+messageText+"&senderid="+senderid+"&route=4&unicode=1&template_id="+templateid+"";
					hospitalsms_url="http://msgalert.in/apiv2?authkey=ec7aec9860bf4ec8ea6f5bfee2557a82&senderid=BGCLNC&numbers="+mobileNo+"&message="+messageText+"&route=4&unicode=1";

				}else{
					//normal sms
					//hospitalsms_url="http://msgalert.in/api/push?auth=f678dbd6bb86bb7f5704ecc16c5722d1&mobno="+mobileNo+"&msg="+messageText+"";
					//hospitalsms_url="http://msgalert.in/api?userid=balgopal&password=143balgopal&mobno="+mobileNo+"&msg="+messageText+"&senderid="+senderid+"&route=4&unicode=0&template_id="+templateid+"";
					hospitalsms_url="http://msgalert.in/apiv2?authkey=ec7aec9860bf4ec8ea6f5bfee2557a82&senderid=BGCLNC&numbers="+mobileNo+"&message="+messageText+"&route=4&unicode=1";

				}
			}
			
			String smsUrl = hospitalsms_url.replace("{0}", mobileNo).replace("{1}", messageText);
			
			// encode url by replacing space, comma and new line with corresponding encoding code
			log.debug("sms URL :==========> "+smsUrl);
			smsUrl = smsUrl.replace(" ", ENCODE_SPACE).replace(",", ENCODE_COMMA).replace("\n", ENCODE_LF);
			log.debug("sms URL :==========> "+smsUrl);
			
			boolean templeteidpresent=false;
			if(!DateTimeUtils.isNull(templateid).equals("")){
				templeteidpresent = true;
			}
			
			if(loginInfo.isSms_access() && templeteidpresent){
				URL url = new URL(smsUrl);	// get url from url string
				
				log.debug("sms URL :==========> "+smsUrl);
				URLConnection urlConnection = url.openConnection();	// open url connection
				urlConnection.connect();	// connect to url
				
				HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;	// get http url connection object from url connection
				int responseCode = httpConnection.getResponseCode();	// get response code
				
				
				log.debug("sms@@@@@@@@@@"+ responseCode );
				log.debug("sms@@@@@@@@@@"+ urlConnection );
				log.debug("sms URL :==========> "+smsUrl);
				if(responseCode == HttpURLConnection.HTTP_OK){	// if response code is HTTP+OK then message sent successfully 
					isSmsSent = true;
					Connection connection=null; 
					String date_time = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					String error="";
					connection=DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+loginInfo.getDbName()+"",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
					MasterDAO masterDAO=new JDBCMasterDAO(connection);
					
					// HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			            
			            // Set the request method to POST
			         //   connection.setRequestMethod("POST");
			            
					try {
						
						int res=masterDAO.updateSMSCounter(1,messageText);
						int status=1;
						int smslog=masterDAO.insertSmsdata(loginInfo,smsUrl,date_time,mobileNo,status,error);
					} catch (Exception e) {
						e.printStackTrace();
						int smslog=masterDAO.insertSmsdata(loginInfo,smsUrl,date_time,mobileNo,0,e.getMessage());
					}finally {
						connection.close();
					}
				}
			}
			
			
		}catch (Exception e) {
			
			log.debug("sms@@@@@@@@@@"+ e.getMessage() );
			
			e.printStackTrace();
			log.debug("sms@@@@@@@@@@"+ e.getMessage() );
		}
		
		return isSmsSent;
	}

	private boolean sendSmsManas(String messageText, String mobileNo, LoginInfo loginInfo,EmailLetterLog emailLetterLog, int istype) {
		boolean isSmsSent = false;		// initially sms sent is false
		// replace query parameters with actual mobile number and message text
		String smsUrl = SMS_URL.replace("{0}", mobileNo).replace("{1}", messageText);
		
		if(istype==1){
			smsUrl = smsUrl+"&msgtype=UNI";
		}
		
		// encode url by replacing space, comma and new line with corresponding encoding code
		smsUrl = smsUrl.replace(" ", ENCODE_SPACE).replace(",", ENCODE_COMMA).replace("\n", ENCODE_LF);
		
		try{
			if(loginInfo.isSms_access()){
				URL url = new URL(smsUrl);	// get url from url string
				
				URLConnection urlConnection = url.openConnection();	// open url connection
				urlConnection.connect();	// connect to url
				
				HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;	// get http url connection object from url connection
				int previouscount = getsmsbalance();
				int responseCode = httpConnection.getResponseCode();	// get response code
				 /*String data ="";
				 if (200 == responseCode) {
					 	InputStream inputStream = httpConnection.getInputStream();
				        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		
					    StringBuilder response = new StringBuilder();
					    String currentLine;
		
					    while ((currentLine = in.readLine()) != null) 
					        response.append(currentLine);
					    data = response.toString();
				 } */
				
				log.debug("sms@@@@@@@@@@"+ responseCode );
				log.debug("sms@@@@@@@@@@"+ urlConnection );
				
				if(responseCode == HttpURLConnection.HTTP_OK){	// if response code is HTTP+OK then message sent successfully 
					isSmsSent = true;
					int aftercount = getsmsbalance();
				    int finalcount= previouscount -aftercount;
					Connection connection=null; 
					try {
						
						connection=Connection_provider.getconnection();
						MasterDAO masterDAO=new JDBCMasterDAO(connection);
						
						DateFormat dateFormat = new SimpleDateFormat("MM");
					    Calendar cal = Calendar.getInstance();
					    String month = dateFormat.format(cal.getTime());
					  
					    DateFormat dateFormat1 = new SimpleDateFormat("yyyy");
					    Calendar cal1 = Calendar.getInstance();
					    String year = dateFormat1.format(cal1.getTime());
					    
					    if(finalcount>0){
					    	 int ress = masterDAO.updateSMSMonthCount(month,year,finalcount);
					    }
						int res=masterDAO.updateSMSCounter(1,messageText);
						
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						connection.close();
					}
				}
			}
			
			
		}catch (Exception e) {
			log.debug("sms@@@@@@@@@@"+ e.getMessage() );
			
			e.printStackTrace();
			log.debug("sms@@@@@@@@@@"+ e.getMessage() );
		}
		
		return isSmsSent;
	}

	public static void main(String[] args) {
		SmsService s = new SmsService();
		String sms="&#2310;&#2346;&#2325;&#2366;&#2354;&#2360;&#2368;&#2325;&#2352;&#2339; &#2342;&#2367;&#2344;&#2366;&#2306;&#2325; &#2407;&#2411;-&#2406;&#2415;-&#2408;&#2406;&#2407;&#2414; &#2325;&#2379; &#2344;&#2367;&#2352;&#2381;&#2343;&#2366;&#2352;&#2367;&#2340;&#2325;&#2367;&#2351;&#2366;&#2327;&#2351;&#2366; &#2361;&#2376;";
		String msg = "Appointment Cancelled on 01-10-2015 at 10:05 AM with MyClinic 3 Bond Street Bond Gate BondStreet 02476641214 ";
		String msgtext = "ÃƒÂ Ã‚Â¤Ã¢â‚¬Â ÃƒÂ Ã‚Â¤Ã‚ÂªÃƒÂ Ã‚Â¤Ã¢â‚¬Â¢ÃƒÂ Ã‚Â¤Ã‚Â¾  test ÃƒÂ Ã‚Â¤Ã¢â‚¬Â¢ÃƒÂ Ã‚Â¤Ã‚Â¾ ÃƒÂ Ã‚Â¤Ã…Â¸ÃƒÂ Ã‚Â¥Ã¢â€šÂ¬ÃƒÂ Ã‚Â¤Ã¢â‚¬Â¢ÃƒÂ Ã‚Â¤Ã‚Â¾ÃƒÂ Ã‚Â¤Ã¢â‚¬Â¢ÃƒÂ Ã‚Â¤Ã‚Â¾ÃƒÂ Ã‚Â¤Ã‚Â°ÃƒÂ Ã‚Â¤Ã‚Â£  date ÃƒÂ Ã‚Â¤Ã¢â‚¬Â¢ÃƒÂ Ã‚Â¥Ã¢â‚¬Â¹ ÃƒÂ Ã‚Â¤Ã‚Â¨ÃƒÂ Ã‚Â¤Ã‚Â¿ÃƒÂ Ã‚Â¤Ã‚Â°ÃƒÂ Ã‚Â¥Ã¯Â¿Â½ÃƒÂ Ã‚Â¤Ã‚Â§ÃƒÂ Ã‚Â¤Ã‚Â¾ÃƒÂ Ã‚Â¤Ã‚Â°ÃƒÂ Ã‚Â¤Ã‚Â¿ÃƒÂ Ã‚Â¤Ã‚Â¤ ÃƒÂ Ã‚Â¤Ã¢â‚¬Â¢ÃƒÂ Ã‚Â¤Ã‚Â¿ÃƒÂ Ã‚Â¤Ã‚Â¯ÃƒÂ Ã‚Â¤Ã‚Â¾ ÃƒÂ Ã‚Â¤Ã¢â‚¬â€�ÃƒÂ Ã‚Â¤Ã‚Â¯ÃƒÂ Ã‚Â¤Ã‚Â¾ ÃƒÂ Ã‚Â¤Ã‚Â¹ÃƒÂ Ã‚Â¥Ã¢â‚¬Â¡";
		//s.sendSms(msgtext, "9764434837", new LoginInfo(),new EmailLetterLog());
		//int ss=s.testmethod();
		
	}
	
	public int getsmsbalance() {
		int res =1;
		try {
			String smsUrl1 = "http://www.smsjust.com/sms/user/balance_check.php?username="+SMS_ACC_USER_ID+"&pass="+SMS_ACC_PASSWORD+"";
			URL url1 = new URL(smsUrl1);	// get url from url string
			URLConnection urlConnection1 = url1.openConnection();	// open url connection
			urlConnection1.connect();	// connect to url
			HttpURLConnection httpConnection1 = (HttpURLConnection) urlConnection1;	// get http url connection object from url connection
			int responseCode1 = httpConnection1.getResponseCode();
			InputStream inputStream;
		    if (200 == responseCode1) {
		        inputStream = httpConnection1.getInputStream();
		        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

			    StringBuilder response = new StringBuilder();
			    String currentLine;

			    while ((currentLine = in.readLine()) != null) 
			        response.append(currentLine);
			    String data = "0";
			    if(response.toString().split(":").length>1){
			    	data = response.toString().split(":")[1];
			    }
			    res = Integer.parseInt(data);
		    } 

		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public synchronized boolean sendvaccineSms( String messageText, String  mobileNo, LoginInfo loginInfo, EmailLetterLog emailLetterLog,Connection con,String templateid){
		
		boolean isSmsSent = false;
		boolean isunicode=true;
		if(loginInfo.isBalgopal()){
			if(DateTimeUtils.isNull(loginInfo.getClinicUserid()).equals("raibhattar")
					|| DateTimeUtils.isNull(loginInfo.getClinicUserid()).equals("raiprachi")){
				boolean isfrombalgopal=false;
				isSmsSent = sendSmsHospital(messageText,mobileNo,loginInfo,emailLetterLog,isunicode,isfrombalgopal,templateid);
			}else{
				boolean isfrombalgopal=true;
				isSmsSent = sendSmsHospital(messageText,mobileNo,loginInfo,emailLetterLog,isunicode,isfrombalgopal,templateid);
			}
		}else if(DateTimeUtils.isNull(loginInfo.getClinicUserid()).equals("raibhattar")
				|| DateTimeUtils.isNull(loginInfo.getClinicUserid()).equals("raiprachi")){
			boolean isfrombalgopal=false;
			isSmsSent = sendSmsHospital(messageText,mobileNo,loginInfo,emailLetterLog,isunicode,isfrombalgopal,templateid);
		}else if(loginInfo.isParihar()) {
			boolean isfrombalgopal=false;
			isSmsSent = sendSmsHospital(messageText,mobileNo,loginInfo,emailLetterLog,isunicode,isfrombalgopal,templateid);
		}else if(loginInfo.isSjivh()) {
			boolean isfrombalgopal=false;
			isSmsSent = sendSmsHospital(messageText,mobileNo,loginInfo,emailLetterLog,isunicode,isfrombalgopal,templateid);
		}else{
			//pinnacle
			//isSmsSent = sendSmsManas(messageText,mobileNo,loginInfo,emailLetterLog,1);
			
			//sms leases
			isSmsSent = sendSmsLeaseService(messageText,mobileNo,loginInfo,emailLetterLog,isunicode,templateid);
		}
		
		/*boolean isSmsSent = false;		// initially sms sent is false
		try{
			if(loginInfo.isSms_access()){
				//messageText=URLEncoder.encode(messageText, "UTF-8");
				
				// replace query parameters with actual mobile number and message text
				String smsUrl = SMS_URL.replace("{0}", mobileNo).replace("{1}", messageText)+"&msgtype=UNI";
				
				// encode url by replacing space, comma and new line with corresponding encoding code
				smsUrl = smsUrl.replace(" ", ENCODE_SPACE).replace(",", ENCODE_COMMA).replace("\n", ENCODE_LF);
				
				URL url = new URL(smsUrl);	// get url from url string
				
				URLConnection urlConnection = url.openConnection();	// open url connection
				urlConnection.connect();	// connect to url
				
				HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;	// get http url connection object from url connection
				int previouscount = getsmsbalance();	
				int responseCode = httpConnection.getResponseCode();	// get response code
	         	
				if(responseCode == HttpURLConnection.HTTP_OK){	// if response code is HTTP+OK then message sent successfully 
					isSmsSent = true;
					int aftercount = getsmsbalance();
					int finalcount= previouscount -aftercount;
					Connection connection=null; 
					try {
						connection=con;
						MasterDAO masterDAO=new JDBCMasterDAO(connection);
						DateFormat dateFormat = new SimpleDateFormat("MM");
						Calendar cal = Calendar.getInstance();
						String month = dateFormat.format(cal.getTime());
						  
						DateFormat dateFormat1 = new SimpleDateFormat("yyyy");
						Calendar cal1 = Calendar.getInstance();
						String year = dateFormat1.format(cal1.getTime());
						if(finalcount>0){
					    	int ress = masterDAO.updateSMSMonthCount(month,year,finalcount);
					    }
						int res=masterDAO.updateSMSCounter(1,messageText);
					
						
					} catch (Exception e) {

						e.printStackTrace();
					}
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}*/
		
		return isSmsSent;
		
	}
	

	
}
