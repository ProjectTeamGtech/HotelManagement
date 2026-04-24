package com.apm.DiaryManagement.web.action;




import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


 
public class SendMailTLS {
	
	/** plain text content type */
	private  String CONTENT_PLAIN_TEXT = "text/plain";
	
	/** html content type */
	private  String CONTENT_HTML = "text/html";
 
	public boolean sendMail(String recipients, String subject, String body) throws Exception {
 
		boolean mailsent = false;
		
		final String username = "cbstecdemotest@gmail.com";
		final String password = "cbstech1234";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(recipients));
			message.setSubject(subject);
			message.setContent(body, CONTENT_HTML);
 
			Transport.send(message);
 
			mailsent = true;
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return mailsent;
	}
	
	public static void main(String[] args) {
		
		SendMailTLS mailTLS = new SendMailTLS();
		try {
			mailTLS.sendMail("unnati0125@gmail.com", "Test", "Email Test");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}