package com.apm.InstantMessage.web.action;



import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.apm.DiaryManagement.web.action.EmbeddedImageEmailUtil;
import com.apm.Log.eu.bi.EmailLetterLogDAO;
import com.apm.Log.eu.blogic.jdbc.JDBCEmailLetterLogDAO;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.common.helper.LoginInfo;
 
/**
 * 
 * 
 * This utility class provides a functionality to send an HTML e-mail message
 * with embedded images.
 * @author www.codejava.net
 *
 */
public class Email {
 
	private static final Logger log = Logger.getLogger(Email.class);
    
    
    
    public static void sendMailAttachment(Connection connection, String to,String cc,String subject,String bodyText,String[] attachFiles, LoginInfo loginInfo,EmailLetterLog emailLetterLog) throws Exception{
    	
    	String from = loginInfo.getEmailUserName();
    	String host = loginInfo.getEmailHostName();

    	StringBuffer str = new StringBuffer();
    	str.append(bodyText);
    	//str.append("<br><br><a href='www.twitter.com'><img src='img/Entypo_f309(0)_32.png'></a>&nbsp;&nbsp;<a href='www.facebook.com'><img src='img/Entypo_f30c(0)_32.png'></a>&nbsp;&nbsp;<a href='www.gmail.com'><img src='img/Entypo_f30f(0)_32.png'></a>&nbsp;<br>");
    	String img = "<img src=\"cid:image1\" />";
    	str.append(img);
    	bodyText.concat(str.toString());
    	
    	final String userName = loginInfo.getEmailUserName();
		final String password = loginInfo.getEmailPassword();
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);
        
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
        
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            //message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); 
           
            Address[] toUsers = InternetAddress.parse(to);
            message.addRecipients(Message.RecipientType.TO, toUsers);
            
            Address[] ccUsers = InternetAddress.parse(cc);
            message.addRecipients(Message.RecipientType.CC, ccUsers);
            
            message.setSubject(subject);
            message.setSentDate(new Date());
            
            //
            // Set the email message text.
            //
            MimeBodyPart messagePart = new MimeBodyPart();
           // messagePart.setText(bodyText);
            messagePart.setContent(bodyText,"text/html");
            System.out.println(bodyText);
            
            //
     
            // creates multi-part
            Multipart multipart = new MimeMultipart();
        
            multipart.addBodyPart(messagePart);
     
            // adds attachments
            if (attachFiles != null && attachFiles.length > 0) {
                for (String filePath : attachFiles) {
                    MimeBodyPart attachPart = new MimeBodyPart();
     
                    try {
                        attachPart.attachFile(filePath);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
     
                    multipart.addBodyPart(attachPart);
                }
            }
     
            // sets the multi-part as e-mail's content
            message.setContent(multipart);
     
            // sends the e-mail
            
            ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
   			String CheckMailToSend = clinicDAO.IsMailSend(loginInfo.getClinicid());
   			if(CheckMailToSend.equals("true")){
            Transport.send(message);
   			
            System.out.println("Mail send");
   			}
            
            
            
        } catch (MessagingException e) {
            e.printStackTrace();
            log.debug("*******************"+e.getMessage());
        }
        finally {
			connection.close();
		}
    }
 
    public static void sendMail(Connection connection ,String to,String cc,String subject,String bodyText, LoginInfo loginInfo, Map<String, String> mapInlineImages,EmailLetterLog emailLetterLog) throws Exception{
    	
	 	String from = loginInfo.getEmailUserName();
 		String host = loginInfo.getEmailHostName();
 		
 		StringBuffer str = new StringBuffer();
    	str.append(bodyText);
    	
 		final String userName = loginInfo.getEmailUserName();
		final String password = loginInfo.getEmailPassword();
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);
        
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
        
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            //message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); 
           
            Address[] toUsers = InternetAddress.parse(to);
            message.addRecipients(Message.RecipientType.TO, toUsers);
            
            Address[] ccUsers = InternetAddress.parse(cc);
            message.addRecipients(Message.RecipientType.CC, ccUsers);
            
            message.setSubject(subject);
            message.setSentDate(new Date());
            
            MimeBodyPart messagePart = new MimeBodyPart();
            // messagePart.setText(bodyText);
             messagePart.setContent(str.toString(),"text/html");
             System.out.println(bodyText);
             
             //
      
             // creates multi-part
             Multipart multipart = new MimeMultipart();
         
             multipart.addBodyPart(messagePart);
             
             // adds inline image attachments
             if (mapInlineImages != null && mapInlineImages.size() > 0) {
                 Set<String> setImageID = mapInlineImages.keySet();
                  
                 for (String contentId : setImageID) {
                     MimeBodyPart imagePart = new MimeBodyPart();
                     imagePart.setHeader("Content-ID", "<" + contentId + ">");
                     imagePart.setDisposition(MimeBodyPart.INLINE);
                      
                     String imageFilePath = mapInlineImages.get(contentId);
                     try {
                         imagePart.attachFile(imageFilePath);
                     } catch (IOException ex) {
                         ex.printStackTrace();
                     }
      
                     multipart.addBodyPart(imagePart);
                 }
             }
             
             message.setContent(multipart);
      
            // sends the e-mail
             ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
    			String CheckMailToSend = clinicDAO.IsMailSend(loginInfo.getClinicid());
    			if(CheckMailToSend.equals("true")){
            Transport.send(message);
            System.out.println("Mail send");
    			}
            
            
            
        } catch (MessagingException e) {
            e.printStackTrace();
            log.debug("*******************"+e.getMessage());
        }
        finally {
			connection.close();
		}
    }
    
    
 public static void sendPrintLetterMail(Connection connection ,String to,String cc,String subject,String bodyText, LoginInfo loginInfo,EmailLetterLog emailLetterLog,String attachFiles[]) throws Exception{
	 
	 	String from = loginInfo.getEmailUserName();
 		String host = loginInfo.getEmailHostName(); 
 		
 		StringBuffer str = new StringBuffer();
    	str.append(bodyText);
    	
 		final String userName = loginInfo.getEmailUserName();
		final String password = loginInfo.getEmailPassword();
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);
        
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
        
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            //message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); 
           
            Address[] toUsers = InternetAddress.parse(to);
            message.addRecipients(Message.RecipientType.TO, toUsers);
            
            Address[] ccUsers = InternetAddress.parse(cc);
            message.addRecipients(Message.RecipientType.CC, ccUsers);
            
            message.setSubject(subject);
            message.setSentDate(new Date());
            
            MimeBodyPart messagePart = new MimeBodyPart();
            // messagePart.setText(bodyText);
             messagePart.setContent(str.toString(),"text/html");
             System.out.println(bodyText);
             
             //
      
             // creates multi-part
             Multipart multipart = new MimeMultipart();
         
             multipart.addBodyPart(messagePart);
             
             // adds attachments
             if (attachFiles != null && attachFiles.length > 0) {
                 for (String filePath : attachFiles) {
                     MimeBodyPart attachPart = new MimeBodyPart();
      
                     try {
                         attachPart.attachFile(filePath);
                     } catch (IOException ex) {
                         ex.printStackTrace();
                     }
      
                     multipart.addBodyPart(attachPart);
                 }
             }
             
           
      
             
             // adds inline image attachments
         /*  if (mapInlineImages != null && mapInlineImages.size() > 0) {
                 Set<String> setImageID = mapInlineImages.keySet();
                  
                 for (String contentId : setImageID) {
                     MimeBodyPart imagePart = new MimeBodyPart();
                     imagePart.setHeader("Content-ID", "<" + contentId + ">");
                     imagePart.setDisposition(MimeBodyPart.INLINE);
                      
                     String imageFilePath = mapInlineImages.get(contentId);
                     try {
                         imagePart.attachFile(imageFilePath);
                     } catch (IOException ex) {
                         ex.printStackTrace();
                     }
      
                     multipart.addBodyPart(imagePart);
                 }
             }
             */
             message.setContent(multipart);
      
            // sends the e-mail
             ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
             EmailLetterLogDAO emailLetterLogDAO = new JDBCEmailLetterLogDAO(connection);
         	 String status = "Not Delivered";
    		 String CheckMailToSend = clinicDAO.IsMailSend(loginInfo.getClinicid());
    		 emailLetterLog.setLastModified(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
    		 if(CheckMailToSend.equals("true")){
    			 Transport.send(message);
    			 System.out.println("Mail send");
           
    			 //save email letter log method
    			 status = "Delivered"; 
    			 int result = emailLetterLogDAO.saveEmailLetterLogDetails(from, to, subject, emailLetterLog,status,"0");
    			 
    			 emailLetterLog.setType("Email");
    			 int es = emailLetterLogDAO.saveEmailLetterLogDetails(from, to, subject, emailLetterLog,status,"0"); 
    		 }else{
             	 System.out.println("sorry..pdf not sent");
             	 //save email letter log method
             	 
             	
            	 int result = emailLetterLogDAO.saveEmailLetterLogDetails(from, to, subject, emailLetterLog,status,"0");
            	 
            	 emailLetterLog.setType("Email");
            	 int es = emailLetterLogDAO.saveEmailLetterLogDetails(from, to, subject, emailLetterLog,status,"0"); 
             }
           
            
        } catch (MessagingException e) {
            e.printStackTrace();
            log.debug("*******************"+e.getMessage());
        }
        finally {
			connection.close();
		}
    }
    
    public static void main(String[] args) {
    	String[] attachFiles = new String[3];
        attachFiles[0] = "C:/SendMailSSL.java";
        attachFiles[1] = "C:/SendMailSSL.java";
        attachFiles[2] = "C:/SendMailSSL.java";
    	
		//sendMailAttachment("unnatigavhale@gmail.com", "unnati0125@gmail.com", "hi", "hello", attachFiles);
	}
}
