package com.apm.Pharmacy.web.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.poi.util.SystemOutLogger;
import org.json.simple.JSONObject;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.AdditionalDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAdditionalDAO;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.FinderDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCFinderDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.DiaryManagement.web.action.EmbeddedImageEmailUtil;
import com.apm.DiaryManagement.web.action.SlotAvailableAction;
import com.apm.DiaryManagement.web.action.SmsService;
import com.apm.DiaryManagement.web.action.WhatsAppService;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Inventory.eu.bi.InventoryCatalogueDAO;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryCatalogueDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Log.eu.bi.EmailLetterLogDAO;
import com.apm.Log.eu.blogic.jdbc.JDBCEmailLetterLogDAO;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Pharmacy.eu.bi.PharmacyDAO;
import com.apm.Pharmacy.eu.blogic.jdbc.JDBCPharmacyDAO;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Tools.eu.bi.EmailTemplateDAO;
import com.apm.Tools.eu.blogic.jdbc.JDBCEmailTemplateDAO;
import com.apm.Tools.eu.entity.EmailTemplate;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Encryption;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;

public class Tra {

	public static void main(String[] args) {
		
		
		try {
			//new Tra().callapi();
			//new Tra().saveProductCode();
			//15 June 2018
			//new Tra().saveCriticalValue();
			//  28 May 2018
			//new Tra().checkOTIpdID();
			//new Tra().addlastmodifieddata();

			/*new Tra().addindentlastmodifieddata();*/

			//new Tra().addindentlastmodifieddata();
//prghagre@bitbucket.org/phprashant/smartcare.git
			//new Tra().addirectindetlastmodifieddata();
			//new Tra().addFromCatalogue();
			//new Tra().addPatientFullName();
			//new Tra().updateCataloguIdToMedicine();
			//new Tra().addIndentIDs();
		    //	new Tra().addtoCatalogue();
		//	new Tra().addToMedicineMasterFromCatalogue();
			//new Tra().copyToConditionFromLocation();
			//new Tra().updateLocation();
			//new Tra().updateProdTyepe();
			 //new Tra().addCGSTTOLIFESPring();
			//new Tra().copyToSubcategory();
			/*Class.forName("com.mysql.jdbc.Driver");
			//con=DriverManager.getConnection("jdbc:mysql://185.141.164.86:3306/"+database+"","root","mysql");
			//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/apmCbstec","Balvinder001","Deepak001");
			Connection	connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/chraisnh",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//con=DriverManager.getConnection("jdbc:mysql://185.141.164.86:3306/nkpsims",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database+"","root","mysql");
			System.out.println("done");
			String sql="select billno,location from apm_medicine_payment";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()){
				
				   String billno= rs.getString(1);
				   String location= rs.getString(2);
				
				   String sql1="update apm_medicine_bill set userid='"+location+"' where id="+billno+"  ";
				   PreparedStatement ps1=connection.prepareStatement(sql1);
				   int res= ps1.executeUpdate();
				   
				
			}
			
			System.out.println("ok");*/
			
			//new Tra().addAbrivationid();
			//For change inventory cataloge
			//new Tra().changeCatalogueid();
			
			//new Tra().indentisfromrequestdata();
			//new Tra().indentisfromdirectdata();
			//new Tra().indentisfromprocurmentdata();
			//new Tra().neslonsetchargeshare();
			//new Tra().aureusCatalogueToMaster();
			//new Tra().checkProductCatalogueid();
		/*	new Tra().AddDataToSeqNo("1");
			System.out.println("All done");
			new Tra().AddDataToSeqNo("2");
			System.out.println("All done");
			new Tra().AddDataToSeqNo("3");
			System.out.println("All done");*/
			
			/*new Tra().generateProductWiseDiscount();*/
			/*new Tra().delteRepeatedCGHS();*/
			//new Tra().setMedicinePaymentFlagForCredit();
			//new Tra().saveFreeQtyInProcurement();
//			new Tra().updategenricName();
			//new Tra().genertaeIpdAbbrId();
			/*new Tra().updateCatalogueLog();*/
			/*new Tra().updateCatalogueStock();*/

			/*new Tra().generatedatabackend();*/
			//new Tra().autoChargeTPCommencingDate();
			/*new Tra().generatedatabackend();*/
			//new Tra().autoChargeTPCommencingDate();
			//new Tra().deleteDuplicateCatalogueLogData();
			/*new Tra().updateIpdInCharges();*/
			//new Tra().updateNextDayStockLog();
			//new Tra().genrateseqdate();
			//new Tra().updateAlreadyDiscount();
			//new Tra().missingfielddata();
			//new Tra().wrongEntryEntered();
			//new Tra().updateSupplierIDFromProcurement();
			//new Tra().removeGRNPurchaseEntry();
			//new Tra().asisgnInvoiceIdToCharges();
			//new Tra().generatedatabackend();
			//new Tra().updateCatalogueInLog();
			//new Tra().updateGlobalIDBalgopal();
			//new Tra().updateGlobleProductId();
			/*new Tra().updateBalgopalIndentLocation();*/
			//new Tra().AddDataToSeqNo("6");
			/*System.out.println(new Tra().removeBreaks(""));
			System.out.println(new Tra().removeBreaks("aa bc<br>"));
			System.out.println(new Tra().removeBreaks("<br>aa<br>"));
			System.out.println(new Tra().removeBreaks("<br><br>sadsds"));
			System.out.println(new Tra().removeBreaks("<br>Tff"));
			System.out.println(new Tra().removeBreaks("<br><br>"));
			System.out.println(new Tra().removeBreaks("<br><br><br>"));
			System.out.println(new Tra().removeBreaks("<br>"));
			System.out.println(new Tra().removeBreaks("xyz"));*/
			
			//new Tra().updateUnknownQtyValue();
			//new Tra().testCode();
			//new Tra().generatedatabackend();
			//new Tra().generateProductLogBackend();
			//new Tra().updatePharmacyDisc();
			//new Tra().insertBalgopalCatalogueLog();
			//new Tra().chngediscoutappliedstatus();
			//new Tra().saveManualStockEntryInStock();
			//new Tra().testingofrslast();
			//new Tra().testmariadb();
			//new Tra().addnewcatalogueproductNewfromold();
			//new Tra().testpharmacycalculation();
			//new Tra().fileHandler();
			
			//this code use for aureus inventory_opening closing year by year
			//new Tra().newcatalogueLog();
			
			//new Tra().changeExpiryDateOfProdcut();
			//new Tra().adjustmentParentidStore();
			
			//code update sale price and making from calculation for manual stock entered
			//new Tra().updateSalePriceAccordingToMRPPack();
			
			//code for change userid store in normal to encrypted.
			//new Tra().updatePasswordInEncrypted();
		
			//code for check gmail email
//			new Tra().testMail();
			//new Tra().testDate();
			
			//new Tra().testSMS();
			//new Tra().testspaceremoval();
			//new Tra().testexpriaydate();
			
			//This code for previous ipd old abrivationid of balgopal 
			//new Tra().generateIPDAbrivation();
			
			//This code for his abrivation change to manas
			//new Tra().generateNewIPDAbrivation();
			
			//this code for adv, and ref old abrivationid of balgopal 
			//new Tra().generateADVREFAbrivation();
			
			//new test view table
			//new Tra().testview();
			
			//new Tra().testifelse();
			//new Tra().testrecursion();
			//new Tra().changeMedicineNameInGujrat();
			//new Tra().getFinancialYearStartDate();
			//new Tra().teststring();
			//new Tra().removeProductCodeFromProductName();
			//new Tra().vpnconnectiontest();
			//new Tra().testpasswordecryption();
			//new Tra().updateGRNLastPurPriceInCatalogue();
			//new Tra().encryptPassword();
			//new Tra().TestDatetimeFunction();
			//createFileImage();
			//new Tra().showImage();
			//new Tra().setMasterChargeType();
//			new Tra().removeDuplicatePatientData();
//			new Tra().addadminuser();
//			new Tra().changeAbrivationId();
			//new Tra().sendMail();
			//new Tra().updateVaccinationSchedule();
			//new Tra().updateRefundAbrivation();
	
			//new Tra().fakePatientStatus();
			//new Tra().checkSMSOfBalgopal();
			//new Tra().checkBalgopalSMSNew();
			//new Tra().lmhDeptOpd();
			//new Tra().WhatsAppServiceTest();
			//new Tra().updatenewVaccine();
			//new Tra().updatenewDob();
			new Tra().insertpariharVaccinedata();
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		
	}
	
	

	private void insertpariharVaccinedata() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/parihar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			String sql="select id,dob,gender  from apm_patient  where newdob > '2010-05-28' and vacc_flag=0";
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt(1));
				Client client=new Client();
		        client.setId(rs.getInt(1));
		        client.setDob(rs.getString(2));
		        client.setGender(rs.getString(3));
				ArrayList<Master> vacinlist = new ArrayList<Master>();
				vacinlist=notAvailableSlotDAO.getVaccineListbyid(0,client);
				for (Master master : vacinlist) {
					String dob = client.getDob();

					dob = DateTimeUtils.getCommencingDatePicker(dob);
					int schedule = Integer.parseInt(master.getVacine_scheduledon());
					SimpleDateFormat birthDate = new SimpleDateFormat("dd-MM-yyyy");
					Date date = birthDate.parse(dob);
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					String scheduledate = "";
					cal.add(Calendar.DAY_OF_YEAR, schedule);
					scheduledate = birthDate.format(cal.getTime());
					master.setScheduledate(scheduledate);
					int count=notAvailableSlotDAO.getClientCount(""+client.getId(),master.getId());
					if(count==0) {
					int x = notAvailableSlotDAO.setdatatoVacinationInfo1(master, ""+client.getId());
					System.out.println("");
					}
				}
				int update=notAvailableSlotDAO.updateVaccineflag(client.getId());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}



	private void updatenewDob() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/parihar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			String sql="select id,dob from apm_patient where id between 3819 and 7298";
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			int count=0;
			while(rs.next()) {
				System.out.println(rs.getString(2));
				String dob=DateTimeUtils.getCommencingDate(rs.getString(2));
				System.out.println(dob);
				String update="update apm_patient set newdob='"+dob+"' where id="+rs.getInt(1)+" and dob!=''";
				PreparedStatement pst1=connection.prepareStatement(update);
				int res=pst1.executeUpdate();
				count++;
			}
			System.out.println(count);
			
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
	}



	private void WhatsAppServiceTest() {
		try {
			System.out.println("Start code");
			boolean flag=false;
		//	boolean flag = new WhatsAppService().sendMsg("", "Happy New Year");
			if(flag){
				System.out.println("Success");
			}else{
				System.out.println("Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}



	private void lmhDeptOpd() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://117.236.76.142/vspm",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			String sql="select id,commencing,reqdatetime from department_opd where commencing!=DATE_FORMAT(reqdatetime, '%Y-%m-%d') and dept=117";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				System.out.println("id:"+rs.getInt(1));
				String resDate="";
				resDate = rs.getString(2)+" "+rs.getString(3).split(" ")[1];
				System.out.println(rs.getString(2)+"    "+resDate);
				String query = "update department_opd set reqdatetime='"+resDate+"' where id='"+rs.getInt(1)+"'";
				PreparedStatement preparedStatement2 = connection.prepareStatement(query);
				int res = preparedStatement2.executeUpdate();
			
			}
			System.out.println("Done");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}



	private void checkBalgopalSMSNew() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/balgopal",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			this.connection = connection;
			SmsService s = new SmsService();
			String message="";
			String templateid="";
			//done
			//message ="Dr. Ashok Bhattar Clinic : Dear Parents {#var#} à¤†à¤ªà¤•à¤¾ {#var#} {#var#} à¤•à¤¾ à¤Ÿà¥€à¤•à¤¾à¤•à¤°à¤£ {#var#} à¤ªà¤° à¤¨à¤¿à¤°à¥�à¤§à¤¾à¤°à¤¿à¤¤ à¤¹à¥ˆ| Phone: {#var#}";
			//message ="Dear Parents\nToken no: 74 You have Booked appointment on 06-10-2022 at 17:45 PM with Dr. Ashok Bhattar Clinic";
			
			//templateid = userProfileDAO.getSMSTemplateID("Vaccination");
			NotAvailableSlotDAO availableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			
			templateid = "1507166444628087428";
			LoginInfo loginInfo = new LoginInfo();
			loginInfo.setBalgopal(true);
			loginInfo.setClinicUserid("raibhattar");
			loginInfo.setSms_access(true);
			message = availableSlotDAO.hindiBdaySMSText(""+70323, loginInfo);
			s.sendvaccineSms(message, "8999673551", loginInfo, new EmailLetterLog(),connection,templateid);
			
			templateid = "1507166444621740343";
			loginInfo.setClinicUserid("balgopal");
			message = availableSlotDAO.hindiBdaySMSText(""+70323, loginInfo);
			s.sendvaccineSms(message, "8999673551", loginInfo, new EmailLetterLog(),connection,templateid);
			System.out.println("Done");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	private void checkSMSOfBalgopal() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/balgopal",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			this.connection = connection;
			DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal3 = Calendar.getInstance();
			cal3.add(Calendar.DATE, 7); 
			String Date1= dateFormat3.format(cal3.getTime());
			boolean is1daysms = false;
			getAllClientVaccinations(Date1,is1daysms);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Master> getAllClientVaccinations(String date, boolean is1daysms){
		ArrayList<Master> list= new ArrayList<Master>();
		PreparedStatement ps=null;
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append(" select a.id,a.masterid,a.mastername,concat(b.title,' ',b.firstname,' ',b.surname),b.mobno,a.clientid  from apm_vacination_data a ");
			buffer.append("  inner join apm_patient b on b.id= a.clientid  where a.duedate ='"+date+"' and a.smsflag='0' and b.mobno is not null and b.mobno!=''   ");
			ps= connection.prepareStatement(buffer.toString());
			ResultSet rs= ps.executeQuery();
			Clinic clinic = new Clinic();
		    ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
		    clinic = clinicDAO.getCliniclistDetails(1);
			while(rs.next()){
				Master master= new Master();
				master.setId(rs.getInt(1));
				master.setMasterid(rs.getInt(2));
				master.setVacinname(rs.getString(3));
				master.setClientname(rs.getString(4));
				master.setMobileNo(rs.getString(5));
				master.setClientid(rs.getString(6));
				if(DateTimeUtils.isNull(rs.getString(5)).equals("")){
					continue;
				}
				boolean smssent= true;
				if(smssent){
					UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
					String templateid = userProfileDAO.getSMSTemplateID("Vaccination");
					Connection con=connection;
					String msg ="BALgOapal" +" : "+master.getClientname() +" à¤†à¤ªà¤•à¤¾ "+master.getVacinname()+" à¤•à¤¾ à¤Ÿà¥€à¤•à¤¾à¤•à¤°à¤£ "+DateTimeUtils.getCommencingDate1(date)+" à¤ªà¤° à¤¨à¤¿à¤°à¥�à¤§à¤¾à¤°à¤¿à¤¤ à¤¹à¥ˆ| Phone: "+clinic.getLandLine()+" ";
					System.out.println(msg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}




	private void fakePatientStatus() {
		try {
			System.out.println("Start");
			Class.forName("com.mysql.jdbc.Driver");
			//Connection connection = DriverManager.getConnection("jdbc:mysql://49.248.210.212/vspm",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/vspm",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			String sql="select id from apm_patient where fake_status=1 and id>24303 ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			int count =0;
			while (rs.next()) {
				System.out.println("PatientId:"+rs.getInt(1)+"\tCount"+(++count));
				String query = "update department_opd set fakestatus=1 where clientid='"+rs.getInt(1)+"' and fakestatus!=1 ";
				PreparedStatement preparedStatement2 = connection.prepareStatement(query);
				int res = preparedStatement2.executeUpdate();
			}
			System.out.println("End");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}



	private void updateRefundAbrivation() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/balgopal",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			String sql="select invtypenew,id from apm_credit_account where invtypenew in (8,9) and date between '2022-04-01' and '2022-04-30 23:59:59' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			AdditionalDAO additionalDAO = new JDBCAdditionalDAO(connection);
			System.out.println("Start");
			while (rs.next()) {
				additionalDAO.advRefFinacialYearGen(rs.getString(1),rs.getString(2));
			}
			System.out.println("End");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}



	public void updateVaccinationSchedule() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			/*Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/balgopal",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");*/
			Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.2.150/raibhattar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			String sql="select clientid from apm_vacination_data where clientid between 959 and 3445 group by clientid order by (clientid+0)  ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String clientid=rs.getString(1);
				String type ="0";
				immunizationProcess(clientid, type,false,connection);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void immunizationProcess(String clientid,String type,boolean fromVital,Connection connection){
		
		try {
			System.out.println("Start");
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			Client client = clientDAO.getPatient(Integer.parseInt(clientid));
			boolean ischangedob = true;
			if (type != null) {
				if (!type.equals("")) {
					client.setVacine_type(Integer.parseInt(type));
				}
			} else {
				client.setVacine_type(0);
			}
			String lmpdate = "";
			ArrayList<Master> vacinlist = new ArrayList<Master>();
			vacinlist = notAvailableSlotDAO.getVaccinationdata(client);
			if (vacinlist.size() != 0) {
				for (Master master : vacinlist) {
					master.getVacine_scheduledon();
					String dob = client.getDob();

					dob = DateTimeUtils.getCommencingDatePicker(dob);
					if (client.getVacine_type() == 1) {
						// dob is lmp date fr gynic
						dob = notAvailableSlotDAO.lmpDAte(clientid);
						lmpdate = dob;
						if(fromVital||DateTimeUtils.isNull(dob).equals("")){
							dob = notAvailableSlotDAO.lmpDateFromVital(clientid);
							lmpdate = dob;
						}
					} else if (client.getVacine_type() == 2) {
						DateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
						Calendar cal2 = Calendar.getInstance();
						dob = dateFormat2.format(cal2.getTime());
					}
					int schedule = Integer.parseInt(master.getVacine_scheduledon());
					SimpleDateFormat birthDate = new SimpleDateFormat("dd-MM-yyyy");
					Date date = birthDate.parse(dob);
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					String scheduledate = "";
					cal.add(Calendar.DAY_OF_YEAR, schedule);
					scheduledate = birthDate.format(cal.getTime());
					master.setScheduledate(scheduledate);

					int depndvalue = 0;
					if (master.getVacine_dependson() != null) {
						if (!master.getVacine_dependson().equals("")) {
							depndvalue = notAvailableSlotDAO.getvacinedependacyvale(master.getVacine_dependson());
						}
					}
					SimpleDateFormat checkdate = new SimpleDateFormat("dd-MM-yyyy");

					String exclude = DateTimeUtils.isNull(master.getVacine_excludes());
					for (int i = 0; i < exclude.length(); i++) {
						Date chkdateobj = checkdate.parse(master.getScheduledate());
						Calendar chkdatecal = Calendar.getInstance();
						chkdatecal.setTime(chkdateobj);
						char day = (exclude.charAt(i));
						switch (day) {
						case '1':
							if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
								chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
								master.setScheduledate(checkdate.format(chkdatecal.getTime()));
							}
							break;
						case '2':
							if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
								chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
								master.setScheduledate(checkdate.format(chkdatecal.getTime()));
							}
							break;
						case '3':
							if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
								chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
								master.setScheduledate(checkdate.format(chkdatecal.getTime()));
							}
							break;
						case '4':
							if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
								chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
								master.setScheduledate(checkdate.format(chkdatecal.getTime()));
							}
							break;
						case '5':
							if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
								chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
								master.setScheduledate(checkdate.format(chkdatecal.getTime()));
							}
							break;
						case '6':
							if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
								chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
								master.setScheduledate(checkdate.format(chkdatecal.getTime()));
							}
							break;
						case '7':
							if (chkdatecal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
								chkdatecal.add(Calendar.DAY_OF_YEAR, 1);
								master.setScheduledate(checkdate.format(chkdatecal.getTime()));
							}
							break;

						default:
							break;
						}
					}

					int checkexist = notAvailableSlotDAO
							.checkvacinationimmunizationajaxData(String.valueOf(master.getId()), clientid);
					if (checkexist == 0) {
						// if in means record does not exist
						int x = notAvailableSlotDAO.setdatatoVacinationInfo(master, clientid);
						// inserts data
						DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						Calendar cal3 = Calendar.getInstance();
						cal3.add(Calendar.DATE, 1);
						String tomarrow = dateFormat.format(cal3.getTime());
						if (master.getScheduledate().equals(tomarrow)) {
							// if tomarrow is vacinedate
							boolean smssent = notAvailableSlotDAO.checksmsflag(master.getId(), clientid);
							if (!smssent) {
								/*
								 * int w=
								 * notAvailableSlotDAO.setsmsflag(master.getId()
								 * , clientid);
								 */
							}
						}

					} else {
						if (master.getDependson() > 0) {
							String depenondate = DateTimeUtils
									.isNull(notAvailableSlotDAO.getGivenDate(master.getDependson(), clientid));
							int scheduledep = Integer.parseInt(master.getVacine_scheduledon());
							
							//Shouldnt change the date if given date s there 
							String currentsGivenDate = DateTimeUtils
									.isNull(notAvailableSlotDAO.getGivenDate(master.getId(), clientid));
							if(currentsGivenDate.equals("")){
								if (depenondate.contains("-")) {
									SimpleDateFormat depndongivendatedate = new SimpleDateFormat("yyyy-MM-dd");
									Date dependdate = depndongivendatedate.parse(depenondate);
									Calendar calnew = Calendar.getInstance();
									calnew.setTime(dependdate);
									String dependate = "";

									calnew.add(Calendar.DAY_OF_YEAR, master.getDeppendsonDays());
									dependate = depndongivendatedate.format(calnew.getTime());
									/*
									 * if(calnew.get(Calendar.DAY_OF_WEEK)==Calendar
									 * .SUNDAY){ calnew.add(Calendar.DAY_OF_YEAR,1);
									 * dependate=depndongivendatedate.format(calnew.
									 * getTime()); }
									 */
									master.setScheduledate(DateTimeUtils.getCommencingDate1(dependate));
									notAvailableSlotDAO.updatedueDateOfVaccine(clientid, "" + master.getId(), dependate);
								}
							}
						
						}

						if (ischangedob) {
							int dobchngsuccess = updatedueDateOfVaccine(clientid,
									"" + master.getId(), DateTimeUtils.getCommencingDate1(master.getScheduledate()),
									master.getVacinname(),connection);
						}
					}

					String savedauedate = notAvailableSlotDAO.getDueDate("" + master.getId(), clientid);
					if (!DateTimeUtils.isNull(savedauedate).equals("")) {
						master.setScheduledate(savedauedate);

					}
					master.setVacine_period(DateTimeUtils.getAge1onAddmissionimmu(dob, master.getScheduledate()));

				}
			}
			System.out.println("End "+clientid);
			/*ArrayList<Master> list1 = new ArrayList<Master>();
			for (Master master : vacinlist) {
				if (master != null) {

					list1.add(master);
				}
			}
			type = DateTimeUtils.numberCheck(type);*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void deleteVaccinationData(String clientid, Connection connection) {
		try {
			String sql="select masterid from apm_vacination_data where clientid='"+clientid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int masterid = rs.getInt(1);
				String query ="select * from apm_vacination_master where id='"+masterid+"'";
				PreparedStatement preparedStatement2 = connection.prepareStatement(query);
				ResultSet rs1 =preparedStatement2.executeQuery();
				if(rs1.next()){
					
				}else{
					String sqlQuery ="delete from apm_vacination_data where masterid='"+masterid+"' and clientid='"+clientid+"'";
					PreparedStatement preparedStatement3 = connection.prepareStatement(sqlQuery);
					int res = preparedStatement3.executeUpdate();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}



	public int updatedueDateOfVaccine(String clientid, String masterid, String date,String name,Connection connection) {
		int res=0;
		try {
			PreparedStatement ps= connection.prepareStatement(" update apm_vacination_data set duedate='"+date+"',mastername=? where  masterid='"+masterid+"' and clientid='"+clientid+"'  ");
			ps.setString(1, name);
			res=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}



	void testMail1(){ 
		String host="mail.javatpoint.com";  
		final String user="sonoojaiswal@javatpoint.com";//change accordingly  
		final String password="xxxxx";//change accordingly  
	    
		String to="sonoojaiswal1987@gmail.com";//change accordingly  
		
		//Get the session object  
		Properties props = new Properties();  
		props.put("mail.smtp.host",host);  
		props.put("mail.smtp.auth", "true");  
	     
		Session session = Session.getDefaultInstance(props,  
	    new javax.mail.Authenticator() {  
	      protected PasswordAuthentication getPasswordAuthentication() {  
	    	  return new PasswordAuthentication(user,password);  
	      }  
	    });  
	  
	   //Compose the message  
	    try {  
	    	MimeMessage message = new MimeMessage(session);  
	    	message.setFrom(new InternetAddress(user));  
	    	message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
	    	message.setSubject("javatpoint");  
	    	message.setText("This is simple program of sending email using JavaMail API");  
	       
	    	//send the message  
	    	Transport.send(message);  
	    	System.out.println("message sent successfully...");  
	     } catch (MessagingException e) {
	    	 e.printStackTrace();
	     }  
	 }  

	private void sendMail() {
    	
    	StringBuffer str = new StringBuffer();
    	
    	String to = "";
    	String cc ="";
    	str.append("");
    	
    	
        // sets SMTP server properties
    	/*final String userName = "cbstecdemotest@gmail.com";
		final String password = "cbstech1234";*/
    	final String userName = "yuvicare.hims@manasindustry.com";
		final String password = "CareYuvi#$2807";
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.zoho.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);
       
       // Connection connection = null;
       
        try{
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
        Message msg = new MimeMessage(session);
        
        Address[] toUsers = InternetAddress.parse(to);
        msg.addRecipients(Message.RecipientType.TO, toUsers);
        
        Address[] ccUsers = InternetAddress.parse(cc);
        msg.addRecipients(Message.RecipientType.CC, ccUsers);
        
 
        // creates a new e-mail message
        
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(to) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject("Test Mail");
        msg.setSentDate(new Date());
 
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(str.toString(), "text/html");
 
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        msg.setContent(multipart);        
		Transport.send(msg);
		System.out.println("Done");
        }catch(Exception e){
			e.printStackTrace();
			System.out.println("Error");
		}
        
    }


	private void changeAbrivationId() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/vspm",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			String sql = "select id  from apm_patient where id>12362 and id<=13085";
			FinderDAO finderDAO=new JDBCFinderDAO(connection);
			PreparedStatement ps=connection.prepareStatement(sql);
			LoginInfo loginInfo=new LoginInfo();
			ResultSet rs =ps.executeQuery();
			int i=1;
			 System.gc();
			 Runtime.getRuntime().gc();;
			while (rs.next()) {
				String abrevate=finderDAO.generateAbrivationId(loginInfo,null);
				String sql1="update apm_patient set abrivationid='"+abrevate+"' where id="+rs.getInt(1)+"";
				PreparedStatement preparedStatement=connection.prepareStatement(sql1);
				preparedStatement.executeUpdate();
				System.out.println(rs.getInt(1));
				i++;
				if((i % 500)==0){
					 System.out.println("Total Memory (in bytes): " + Runtime.getRuntime().totalMemory());
			          System.out.println("Free Memory (in bytes): " + Runtime.getRuntime().freeMemory());
			          System.out.println("Max Memory (in bytes): " + Runtime.getRuntime().maxMemory());
					 System.gc();
					 Runtime.getRuntime().gc();
					 System.out.println("Total Memory (in bytes): " + Runtime.getRuntime().totalMemory());
			          System.out.println("Free Memory (in bytes): " + Runtime.getRuntime().freeMemory());
			          System.out.println("Max Memory (in bytes): " + Runtime.getRuntime().maxMemory());
				}
			}
			System.out.println("Updation Done");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	private void addadminuser() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://49.248.210.212/vspm",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		String sql = "select userid,password from apm_user where id<=91";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs =ps.executeQuery();
		while (rs.next()) {
			Connection connection1 = DriverManager.getConnection("jdbc:mysql://49.248.210.212/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			String sq="insert into apm_user (userid,password,clinicid,usertype) value(?,?,?,?)";
			
			PreparedStatement preparedStatement=connection1.prepareStatement(sq);
			preparedStatement.setString(1, rs.getString(1));
			preparedStatement.setString(2, rs.getString(2));
			preparedStatement.setString(3, "vspm");
			preparedStatement.setString(4, "4");
			preparedStatement.executeUpdate();
			
			
		}
	}


	private void removeDuplicatePatientData() {
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/vspm",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		String sql = "select id,count(*),oldclientid from fake_patient  group by oldClientId having count(*)>1";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs =ps.executeQuery();
		int x=0;
		while(rs.next()){
				String query ="select count(*) from fake_patient where oldClientId='"+rs.getString(3)+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					if(resultSet.getInt(1)>1){
						String sql1="delete from fake_patient where id!='"+rs.getInt(1)+"' and oldClientId='"+rs.getString(3)+"'";
						PreparedStatement ps1=connection.prepareStatement(sql1);
						int res =ps1.executeUpdate();
						System.out.println("Deleted ID:"+resultSet.getInt(1));
					}
					
				}
				System.out.println("I:"+x);
				
				
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	private void setMasterChargeType() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://49.248.210.212/vspmdemo",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			
			String sql ="select chargeType from apm_appointment_type group by chargeType";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String chargetype= rs.getString(1);
				
				String query = "select * from apm_newchargetype where name=?";
				PreparedStatement preparedStatement2 = connection.prepareStatement(query);
				preparedStatement2.setString(1, chargetype);
				ResultSet resultSet = preparedStatement2.executeQuery();
				if(resultSet.next()){
					
				}else{
					String insertSql = "insert into apm_newchargetype(name) values(?)";
					PreparedStatement preparedStatement3 = connection.prepareStatement(insertSql);
					preparedStatement3.setString(1, chargetype);
					int res = preparedStatement3.executeUpdate();
				}
					
			}
			System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	private void showImage() {
		try {
			System.out.println("Connection Establishing...");
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/balgopal",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("Connection Established");
			System.out.println("Verify table exist or not in process....");
			
			String filecontent ="";
			System.out.println("Get image string from database");
			String sql ="select image from demo_file where id=1;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				filecontent = rs.getString(1);
			}
			System.out.println("Creating Directory.");
			File file = new File("D:/ManasLogo");
			file.mkdirs();
			System.out.println("Directory Created");
			
			FileOutputStream fileOutputStream = new FileOutputStream("D:/ManasLogo/manaslogo.png");
			byte[] bytes = filecontent.getBytes();
			fileOutputStream.write(bytes);
			fileOutputStream.close();
			
			System.out.println("Image String Convert to byte");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static void createFileImage() {
		try {
			System.out.println("Connection Establishing...");
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/balgopal",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("Connection Established");
			System.out.println("Verify table exist or not in process....");
			boolean flag = tableAlreadyExist(connection);
			
			if(!flag){
				System.out.println("Table creation process start...");
				//Create table through code
				String createSql ="create table demo_file(id int(10) unsigned NOT NULL AUTO_INCREMENT,image blob, PRIMARY KEY (id)) ;";
				PreparedStatement preparedStatement =connection.prepareStatement(createSql);
				int res = preparedStatement.executeUpdate();
				System.out.println("Table Created");
			}
			
			InputStream  fileInputStream = new FileInputStream("D:/Learning/Java File Operation/ .jpeg");
			/*int size = fileInputStream.available();
			byte[] bs = new byte[size];
			fileInputStream.read(bs);
			String fileimage = new String(bs);*/
			
			String query ="insert into demo_file(image) values(?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setBlob(1, fileInputStream);
			int res = preparedStatement.executeUpdate();
			fileInputStream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static boolean tableAlreadyExist(Connection connection2) {
		boolean flag= true;
		try {
			String sql ="select * from demo_file limit 1";
			PreparedStatement preparedStatement = connection2.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}


	private void TestDatetimeFunction() {
		try {
			
			String availabledate ="2022-08-12";
			
			boolean val =DateTimeUtils.validateDateBetweenRange(availabledate);
			System.out.println(val);
			/*Date date = new Date();
			System.out.println(date);
			System.out.println();
			LocalDate localDate = LocalDate.now();
			LocalDate olderdate = localDate.minusMonths(6);
			String currentDate = localDate.toString();
			String olderDate = olderdate.toString();
			System.out.println("Current Date"+localDate);
			System.out.println("Older Date"+olderdate);
			System.out.println("Current String Date"+currentDate);*/
			
			/*String currentDate = "2021-08-12";
			String olderDate = "2021-02-12";
			
		 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Date todate = sdf.parse(currentDate);
	        Date fromdate = sdf.parse(olderDate);
	        Date specificdate = sdf.parse(availabledate);
	        
	        
	        System.out.println("date1    : " + sdf.format(todate));
	        System.out.println("date2    : " + sdf.format(fromdate));
	        System.out.println("Specific : " +sdf.format(specificdate));
	       
	        if(specificdate.getTime() >= fromdate.getTime() && specificdate.getTime() <= todate.getTime()){
	            System.out.println("testDate is within the date range.");
	        }else{
	            System.out.println("testDate is NOT within the date range.");
	        }*/
			
	        
	        
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	

	private void encryptPassword() {
		try {
			String password = "User@123";
			String encPassword = Encryption.encryptSHA(password);
			System.out.println(encPassword);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}


	private void updateGRNLastPurPriceInCatalogue() {
		Connection connection =null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			//connection=DriverManager.getConnection("jdbc:mysql://192.168.2.151/balgopal",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://localhost/balgopal",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			
			StringBuffer buffer =new StringBuffer();
			//buffer.append("select id,product_name from inventory_catalogue");
			buffer.append("select max(inventory_procurement.id),inventory_procurement.catalogueid from inventory_procurement ");
			buffer.append("inner join inventory_product on inventory_product.id = inventory_procurement.prodid ");
			buffer.append("where procurement=0 ");
			buffer.append("group by inventory_procurement.catalogueid order by (inventory_procurement.catalogueid+0) asc ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			int i=0;
			while(rs.next()){
				String sql ="select purprice from inventory_procurement where id="+rs.getInt(1)+"";
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
					String sql2="update inventory_catalogue set purchase_price=? where id='"+rs.getInt(2)+"'";
					PreparedStatement ps2=connection.prepareStatement(sql2);
					ps2.setString(1, resultSet.getString(1));
					int res1 =ps2.executeUpdate();
					System.out.println(""+rs.getInt(2));
				}
				i++;
			}
			System.out.println("ok done");
			System.out.println("Count"+i);
			connection.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void testpasswordecryption() {
		try {
			String encPassword = Encryption.encryptSHA(" @123");
			System.out.println(encPassword);
			
			String date ="1st Nov 2000";
			String sufix=date.split(" ")[0];
			String s=sufix.substring(sufix.length()-2,sufix.length());
			System.out.println(s);
			Date d= new SimpleDateFormat("DD'"+s+"' MMM yyyy").parse(date);
			System.out.print(new SimpleDateFormat("dd/MM/yyyy").format(d) + "\t\t");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	private void vpnconnectiontest() {

		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://192.168.2.151/balgopal",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}


	private void removeProductCodeFromProductName() {
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost/balgopal",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			StringBuffer buffer =new StringBuffer();
			//buffer.append("select id,product_name from inventory_catalogue");
			buffer.append("select id,prodname from inventory_product where  prodname like ?");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ps.setString(1, "omo");
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				String drug="";
				String sql=rs.getString(2);
				String[] temp = sql.split("");
				int i=0;
				for (String string : temp) {
					if(i<=6){
						i++;
						continue;
					}
					drug = drug+""+string;
					i++;
				}
				
				String sql2="update inventory_product set prodname=? where id='"+rs.getInt(1)+"'";
				PreparedStatement ps2=connection.prepareStatement(sql2);
				ps2.setString(1, drug);
				int res1 =ps2.executeUpdate();
				System.out.println("Old :" +rs.getString(2)+". New:"+drug);
			}
			System.out.println("ok done");
			
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void teststring() {
		try {
			String sql="1001 - Acetazolamide Tablet 250mg";
			String[] temp = sql.split("");
			String data="";
			int i=0;
			for (String string : temp) {
				if(i<=6){
					i++;
					continue;
				}
				data = data+""+string;
			}
			System.out.println(""+data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	private void changeMedicineNameInGujrat() {
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost/mangalayatan",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			StringBuffer buffer =new StringBuffer();
			buffer.append("select id,drug from apm_medicine_details where id between '655' and '691' ");
			//1-198
			//220-401
			//410-530
			//536-627
			//655-691
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				String drug="";
				String sql=rs.getString(2);
				String[] temp = sql.split("");
				int i=1;
				for (String string : temp) {
					if(i<=6){
						i++;
						continue;
					}
					drug = drug+""+string;
					i++;
				}
				
				String sql2="update apm_medicine_details set drug=? where id='"+rs.getInt(1)+"'";
				PreparedStatement ps2=connection.prepareStatement(sql2);
				ps2.setString(1, drug);
				int res1 =ps2.executeUpdate();
				System.out.println("Old :" +rs.getString(2)+". New:"+drug);
			}
			System.out.println("ok done");
			
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void getFinancialYearStartDate(){
		String yearrangenew = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		try {
			
				Date todaysDate= dateFormat.parse("02-01-2020");
				String todaydateString = dateFormat.format(todaysDate);
				String currentYearString=todaydateString.split("-")[2];
				Date financialYearStartDate=dateFormat.parse("01-04-"+currentYearString);
				String startDateString="",endDateString="";
				if(todaysDate.after(financialYearStartDate)){
					startDateString="01-04-"+currentYearString;
					endDateString="31-03-"+(DateTimeUtils.convertToInteger(currentYearString)+1);
				}else{
					startDateString="01-04-"+(DateTimeUtils.convertToInteger(currentYearString)-1);
					endDateString="31-03-"+currentYearString;
				}
				//System.out.println(startDateString+"--------------------------"+endDateString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	
	private void testview() {
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost/chraibgh",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			StringBuffer buffer =new StringBuffer();
			buffer.append("update view_ipd_addmission_form set practitionerid=27 where id=9288 ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			int res = ps.executeUpdate();
			System.out.println("ok done");
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}


	private void generateADVREFAbrivation() {

		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost/raiprachi",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			StringBuffer buffer =new StringBuffer();
			buffer.append("select id,fin_yr_seq from apm_credit_account where fin_yr_seq is not null and fin_yr_seq like '%ADV%' and id in(2142,1653)");
			//buffer.append("select id,fin_yr_seq from apm_credit_account where fin_yr_seq is not null and fin_yr_seq like '%OPR%'");
			//buffer.append("select id,fin_yr_seq from apm_credit_account where fin_yr_seq is not null and fin_yr_seq like '%IPR%'");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				String abrivationid="";
				String prefix="";
				String yearrange="";
				String tempipdabrstr="";
				String ipdabrstr= "";
				String[] templength = rs.getString(2).split("/");
				if(templength.length>1){
					//2019-2020/ADV/2
					String[] tempabrivationid=rs.getString(2).split("/");
					
					//set adv
					prefix = tempabrivationid[1];
					
					//set yearrange
					String[] data = tempabrivationid[0].split("-");
					String[] data1 = data[0].split("");
					String[] data2 = data[1].split("");
					yearrange = data1[2]+""+data1[3]+"-"+data2[2]+""+data2[3];
					
					//set sequance
					String temp = tempabrivationid[2];
					int l = temp.length();
					if (l == 3) {
						ipdabrstr = "0" + tempabrivationid[2];
					} else if (l == 2) {
						ipdabrstr = "00" + tempabrivationid[2];
					} else if (l == 1) {
						ipdabrstr = "000" + tempabrivationid[2];
					} else {
						ipdabrstr = "" + tempabrivationid[2];
					}
				}else{
					String[] tempabrivationid=rs.getString(2).split("");
					//ADV20211140
					for (int i = 0; i < tempabrivationid.length; i++) {
						if(i==0 || i==1 || i==2){
							//IP
							prefix = prefix+""+tempabrivationid[i];
						}else if(i==3 || i==4){
							//20
							yearrange = yearrange+""+tempabrivationid[i];
						}else if(i==5){
							//-2
							yearrange = yearrange+"-"+tempabrivationid[i];
						}else if(i==6){
							//1
							yearrange = yearrange+""+tempabrivationid[i];
						}else{
							tempipdabrstr = tempipdabrstr+""+tempabrivationid[i];
						}
					}
					
					String temp = tempipdabrstr;
					int l = temp.length();
					if (l == 3) {
						ipdabrstr = "0" + tempipdabrstr;
					} else if (l == 2) {
						ipdabrstr = "00" + tempipdabrstr;
					} else if (l == 1) {
						ipdabrstr = "000" + tempipdabrstr;
					} else {
						ipdabrstr = "" + tempipdabrstr;
					}
				}
				
				
				
				//set in one
				abrivationid = prefix+ "" + yearrange + "/" + ipdabrstr;
				String sql2="update apm_credit_account set fin_yr_seq=? where id='"+rs.getInt(1)+"'";
				PreparedStatement ps2=connection.prepareStatement(sql2);
				ps2.setString(1, abrivationid);
				int res1 =ps2.executeUpdate();
				System.out.println("Old :" +rs.getString(2)+". New:"+abrivationid);
			}
			System.out.println("ok done");
			
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	
	}


	private void generateNewIPDAbrivation() {
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost/chraibgh",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			StringBuffer buffer =new StringBuffer();
			buffer.append("select id,ipdabrivationid from ipd_addmission_form where id between '8290' and '8292' ");
			//buffer.append("select id,ipdabrivationid from ipd_addmission_form where id between '9870' and '9888' ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				String abrivationid="";
				String[] tempabrivationid=rs.getString(2).split("");
				
				String prefix="";
				String yearrange="";
				String tempipdabrstr="";
				//IP20212
				for (int i = 0; i < tempabrivationid.length; i++) {
					if(i==0 || i==1){
						//IP
						prefix = prefix+""+tempabrivationid[i];
					}else if(i==2 || i==3){
						//20
						yearrange = yearrange+""+tempabrivationid[i];
					}else if(i==4){
						//-2
						yearrange = yearrange+"-"+tempabrivationid[i];
					}else if(i==5){
						//1
						yearrange = yearrange+""+tempabrivationid[i];
					}else{
						tempipdabrstr = tempipdabrstr+""+tempabrivationid[i];
					}
				}
				String ipdabrstr= "";
				String temp = tempipdabrstr;
				int l = temp.length();
				if (l == 3) {
					ipdabrstr = "0" + tempipdabrstr;
				} else if (l == 2) {
					ipdabrstr = "00" + tempipdabrstr;
				} else if (l == 1) {
					ipdabrstr = "000" + tempipdabrstr;
				} else {
					ipdabrstr = "" + tempipdabrstr;
				}
				
				//set in one
				abrivationid = prefix+ "" + yearrange + "/" + ipdabrstr;
				String sql2="update ipd_addmission_form set ipdabrivationid=? where id='"+rs.getInt(1)+"'";
				PreparedStatement ps2=connection.prepareStatement(sql2);
				ps2.setString(1, abrivationid);
				int res1 =ps2.executeUpdate();
				System.out.println("Old :" +rs.getString(2)+". New:"+abrivationid);
			}
			System.out.println("ok done");
			
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}


	private void generateIPDAbrivation() {
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost/chraibgh",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			StringBuffer buffer =new StringBuffer();
			buffer.append("select id,ipdabrivationid from ipd_addmission_form where id between '1' and '8289' ");
			//buffer.append("select id,ipdabrivationid from ipd_addmission_form where id between '9870' and '9888' ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				String abrivationid="";
				String[] tempabrivationid=rs.getString(2).split("/");
				
				//set ip or cs or dc
				String[] tempprefix = tempabrivationid[0].split("");
				String prefix = tempprefix[2]+tempprefix[3];
				
				//set yearrange
				String[] data = tempabrivationid[1].split("-");
				String[] data1 = data[0].split("");
				String[] data2 = data[1].split("");
				String yearrange = data1[2]+""+data1[3]+"-"+data2[2]+""+data2[3];
				
				//set sequance
				String temp = tempabrivationid[2];
				int l = temp.length();
				String ipdabrstr = "";
				if (l == 3) {
					ipdabrstr = "0" + tempabrivationid[2];
				} else if (l == 2) {
					ipdabrstr = "00" + tempabrivationid[2];
				} else if (l == 1) {
					ipdabrstr = "000" + tempabrivationid[2];
				} else {
					ipdabrstr = "" + tempabrivationid[2];
				}
				//set in one
				abrivationid = prefix+ "" + yearrange + "/" + ipdabrstr;
				String sql2="update ipd_addmission_form set ipdabrivationid=? where id='"+rs.getInt(1)+"'";
				PreparedStatement ps2=connection.prepareStatement(sql2);
				ps2.setString(1, abrivationid);
				int res1 =ps2.executeUpdate();
				System.out.println("Old :" +rs.getString(2)+". New:"+abrivationid);
			}
			System.out.println("ok done");
			
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}


	private void testspaceremoval() {
		try {
			String name="COMBIFLAM TAB 20           TAB 20     ";
			System.out.println("before:"+name);;
			String newname = name.trim().replaceAll("\\s{2,}", " ");
			System.out.println("after:"+newname);;
			
			String splittest = "2019-2020/ADV/8";
			String splitval = "ADV20211133";
			System.out.println("1st size"+splittest.split("/").length);
			System.out.println("2nd size"+splitval.split("/").length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void testexpriaydate() {
		try {
			String dateStr = "Mon Jun 18 00:00:00 IST 2012";
			DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
			Date date = (Date)formatter.parse(dateStr);
			System.out.println(date);        

			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" +         cal.get(Calendar.YEAR);
			String year = ""+cal.get(Calendar.YEAR);
			String month =  ""+(cal.get(Calendar.MONTH) + 1);
			if(Integer.parseInt(month)<10){
				month ="0"+month;
			}
			String day ="30";
			String expirydate =year+"-"+month+"-"+day;
			System.out.println("expirydate : " + expirydate);
			System.out.println("formatedDate : " + formatedDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	private void testSMS() {


		Connection connection = null;
		try {
			String practitionerid = "2";
			String clientId ="12885";
			String apmtid = "24373";
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("" + Constants.DB_HOST + ":3306/aureus?useUnicode=true&characterEncoding=UTF-8", ""+Constants.DB_USER+"", ""+Constants.DB_PWD+"");
			EmrDAO emrDAO = new JDBCEmrDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(practitionerid));
			Client client = clientDAO.getClientDetails(clientId);
			String fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
			
			String msg=""+fullname+", below medicine prescribed by "+userProfile.getFullname()+",";
			ArrayList<Priscription> arrayList = emrDAO.getPrescriptionListOPDGiven(apmtid);
			ArrayList<String> arrayList1 = emrDAO.getInvestigationOPDGiven(apmtid);
			int i=1;
			for (Priscription priscription : arrayList) {
				String regional = emrDAO.getregionalText(priscription.getDosage());
				String qty = DateTimeUtils.numberCheck(priscription.getDr_qty());
				int newqty = (int) Double.parseDouble(qty);
				//String message =i+")"+priscription.getMdicinenametxt()+" "+newqty+" "+priscription.getDosage()+".";
				String message =i+")"+priscription.getMdicinenametxt()+" "+newqty+" "+regional.toString()+".";
				//String message =i+")"+priscription.getMdicinenametxt()+" "+newqty+"ÃƒÂ Ã‚Â¤Ã‚Â¸ÃƒÂ Ã‚Â¤Ã‚ÂªÃƒÂ Ã‚Â¥Ã¯Â¿Â½ÃƒÂ Ã‚Â¤Ã‚Â¤ÃƒÂ Ã‚Â¤Ã‚Â¾ÃƒÂ Ã‚Â¤Ã‚Â¹ ÃƒÂ Ã‚Â¤Ã‚Â®ÃƒÂ Ã‚Â¥Ã¢â‚¬Â¡ÃƒÂ Ã‚Â¤Ã¯Â¿Â½ ÃƒÂ Ã‚Â¤Ã‚Â¦ÃƒÂ Ã‚Â¥Ã¢â‚¬Â¹ ÃƒÂ Ã‚Â¤Ã‚Â¬ÃƒÂ Ã‚Â¤Ã‚Â¾ÃƒÂ Ã‚Â¤Ã‚Â°.";
				msg = msg +" "+message;
				i++;
			}
			int j=1;
			String msg1 ="";
			for (String string : arrayList1) {
				if(j==1){
					msg1 = string;
				}else{
					msg1 = msg1 +", "+string;
				}
				
				j++;
			}
			if(j!=1){
				msg = msg +"Test- "+ msg1;
			}
			
			String mobno =client.getMobNo();
			//String mobno ="7972155768";
			LoginInfo loginInfo = new LoginInfo();
			loginInfo.setSms_access(true);
			//sendSms(msg, mobno, loginInfo, new EmailLetterLog());
			//s.sendvaccineSms(msg, mobno, loginInfo,  new EmailLetterLog(), connection);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//private final String SMS_ACC_USER_ID = "5611";
	private final String SMS_ACC_USER_ID = "GMSOFT";
	
	/** SMS Account Password */
	private final String SMS_ACC_PASSWORD = " l41P$Nv!";
	
	/** SMS Account Sender Id */
	private final String SMS_ACC_SENDER_ID = "GMSOFT";
	
	private final String SMS_URL_HOST = "http://www.smsjust.com/blank/sms/user/urlsms.php?";
	
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

	public boolean sendSms(String messageText, String  mobileNo, LoginInfo loginInfo, EmailLetterLog emailLetterLog){
		
		boolean isSmsSent = false;		// initially sms sent is false
		
		String smsUrl = SMS_URL.replace("{0}", mobileNo).replace("{1}", messageText);
		smsUrl = smsUrl+"&msgtype=UNI";
		smsUrl = smsUrl.replace(" ", ENCODE_SPACE).replace(",", ENCODE_COMMA).replace("\n", ENCODE_LF);
		
		try{
				URL url = new URL(smsUrl);	// get url from url string
				
				URLConnection urlConnection = url.openConnection();	// open url connection
				urlConnection.connect();	// connect to url
				
				HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;	// get http url connection object from url connection
				int responseCode = httpConnection.getResponseCode();	// get response code
				
				if(responseCode == HttpURLConnection.HTTP_OK){	// if response code is HTTP+OK then message sent successfully 
					isSmsSent = true;
					System.out.println("sms sent");
				}else{
					System.out.println("sms error");
				}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return isSmsSent;
		
	}

	private void testDate() {
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			String todate = dateFormat.format(cal.getTime());
			todate = DateTimeUtils.getCommencingDate1(todate);
				
			Calendar cal1 = Calendar.getInstance();
			cal1.add(Calendar.DATE, -1);
			String newdate = dateFormat.format(cal1.getTime());
			newdate = DateTimeUtils.getCommencingDate1(newdate);
			
			//Patient age wise
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int year20less = year - 20;
			int year40less = year - 40;
			int year60less = year - 60;
			
			//upto 20year
			String currentdate =  todate.split("-")[2]+"/"+todate.split("-")[1]+"/"+todate.split("-")[0];
			String upto20year = todate.split("-")[2]+"/"+todate.split("-")[1]+"/"+year20less;
			
			//21 to 40 year
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			String year20lessdate = year20less+"-"+todate.split("-")[1]+"-"+todate.split("-")[2];
			Date d=sdf1.parse(year20lessdate);
			Calendar calender1 = Calendar.getInstance();
			calender1.setTime(d);
			calender1.add(Calendar.DATE, -1);
			String year21=sdf1.format(calender1.getTime());
			String upto21year =year21.split("-")[2]+"/"+year21.split("-")[1]+"/"+year21.split("-")[0];
			String upto40year = todate.split("-")[2]+"/"+todate.split("-")[1]+"/"+year40less;
			
			//41 year to 60 year
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			String year40lessdate = year40less+"-"+todate.split("-")[1]+"-"+todate.split("-")[2];
			Date dd=sdf2.parse(year40lessdate);
			Calendar calender2 = Calendar.getInstance();
			calender2.setTime(dd);
			calender2.add(Calendar.DATE, -1);
			String year41=sdf2.format(calender2.getTime());
			String upto41year =year41.split("-")[2]+"/"+year41.split("-")[1]+"/"+year41.split("-")[0];
			String upto60year = todate.split("-")[2]+"/"+todate.split("-")[1]+"/"+year60less;
			
			// 61 year +
			SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
			String year60lessdate = year60less+"-"+todate.split("-")[1]+"-"+todate.split("-")[2];
			Date ddd=sdf2.parse(year60lessdate);
			Calendar calender3 = Calendar.getInstance();
			calender3.setTime(ddd);
			calender3.add(Calendar.DATE, -1);
			String year61=sdf2.format(calender3.getTime());
			String upto61year =year61.split("-")[2]+"/"+year61.split("-")[1]+"/"+year61.split("-")[0];
			String year100 = "03/06/1818";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void testMail() {
		Connection connection = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost/balgopal",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getAvailableSlotdata(10);
			
			String drid = ""+notAvailableSlot.getDiaryUserId();
			
			String to = "";
			String cc = "";
			String subject = "OPD";
			String notes = ""+notAvailableSlot.getClientName()+" your appointment has been confirm for telemed conference call "
					+ "with "+notAvailableSlot.getDiaryUser()+" on date "+DateTimeUtils.getCommencingDate1(notAvailableSlot.getCommencing())+" in between "+notAvailableSlot.getsTime()+" and "+notAvailableSlot.getEndTime()+". Please click on below link. ";
			notes = notes+" "+"https://193.46.199.49:8443/YUVICARE/Pureseva?title=&firstname=&lastname=&email=&clinicid=aureus&mob=&date=&diaryuserid="+drid+"&gender=&dob=&uhid=AH/200406002";
			
			notes = notes +" "+"<a href='https://193.46.199.49:8443/YUVICARE/Pureseva?title=&firstname=&lastname=&email=&clinicid=aureus&mob=&date=&diaryuserid="+drid+"&gender=&dob=&uhid=AH/200406002'>Click Here</a>";
			notes = notes +" "+"<a href='https://193.46.199.49:8443/YUVICARE/Pureseva?title=&firstname=&lastname=&email=&clinicid=aureus&mob=&date=&diaryuserid="+drid+"&gender=&dob=&uhid=AH/200406002'><button>Click Here</button></a>";
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String d1 = dateFormat.format(date);
			String[] temp = d1.split("\\s+");
			String date1 = temp[0];
			String time = temp[1];
			
			String type = "Letter";
			
	    	 String from = "yuvicare.hims@manasindustry.com";
		     String host = "smtp.zoho.com";
		     final String username = "yuvicare.hims@manasindustry.com";
			 final String password = "CareYuvi#$2807";
			 
			 /*String from = "pharmacyaegeus@gmail.com";
		     String host = "smtp.gmail.com";
		     final String username = "pharmacyaegeus@gmail.com";
			 final String password = "aegeus@123";*/
		      
		      Properties props = new Properties();
		      props.put("mail.smtp.auth", "true");
		      props.put("mail.smtp.starttls.enable", "true");
		      props.put("mail.smtp.host", host);   
		      props.put("mail.smtp.port", "587");

		      // Get the Session object.
		      Session session = Session.getInstance(props,
		         new javax.mail.Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		               return new PasswordAuthentication(username, password);
		            }
		      });

		      
		       Message message = new MimeMessage(session);

		   	   message.setFrom(new InternetAddress(from));

			   message.setRecipients(Message.RecipientType.TO,
		              InternetAddress.parse(to));

			   message.setSubject(subject);

			   message.setContent(notes,"text/html");
			   			   
	   			String status = "Not Delivered";
	   			Transport.send(message);          
	   			status = "Delivered";
	   			System.out.println(""+status);
	   			
		      } catch (MessagingException e) {
		    	  e.printStackTrace();
		    	  throw new RuntimeException(e);
		      } catch (Exception e) {
		    	  e.printStackTrace();
			}
			System.out.println("Done");
		
	}


	private void updatePasswordInEncrypted() {
		//code for change userid store in normal to encrypted.
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/welltreat",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://103.242.186.21/chraibgh",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			connection=DriverManager.getConnection("jdbc:mysql://localhost/mangalayatan",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			StringBuffer buffer =new StringBuffer();
			buffer.append("select id, userid, password from apm_admin ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				String encPassword = Encryption.encryptSHA(rs.getString(3));
				String sql2="update apm_admin set password=? where id='"+rs.getInt(1)+"'";
				PreparedStatement ps2=connection.prepareStatement(sql2);
				ps2.setString(1, encPassword);
				int res1 =ps2.executeUpdate();
				System.out.println("Userid :" +rs.getString(2));
			}
			System.out.println("ok done");
			
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}


	private void updateSalePriceAccordingToMRPPack() {
		//code update sale price and making from calculation for manual stock entered
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/welltreat",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://103.242.186.21/chraibgh",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			connection=DriverManager.getConnection("jdbc:mysql://localhost/mangalayatan",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			StringBuffer buffer =new StringBuffer();
			buffer.append("select id, mrp, saleprice,pack from inventory_product where ismanual=1 ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				double mrp = rs.getDouble(2);
				int pack = rs.getInt(4);
				if(pack==0){
					pack=1;
				}
				double newsaleprice = mrp/pack;
				newsaleprice = Math.round(newsaleprice * 100.0)/100.0;
				String sql2="update inventory_product set saleprice='"+newsaleprice+"' where id='"+rs.getInt(1)+"'";
				PreparedStatement ps2=connection.prepareStatement(sql2);
				int res1 =ps2.executeUpdate();
				System.out.println("id :" +rs.getString(1));
			}
			System.out.println("ok done");
			
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}


	private void adjustmentParentidStore() {
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/welltreat",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://103.242.186.21/chraibgh",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://localhost/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			StringBuffer buffer =new StringBuffer();
			buffer.append("SELECT adjustment_data.userid, datetime, location,adjustment_data.id FROM adjustment_data ");
			buffer.append("inner join inventory_product on inventory_product.id = adjustment_data.product_id ");
			buffer.append("where adj_parentid=0 ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				String sql1="insert into adjustment_parent_data(status_type, requested_userid, requested_date,request_status,request_location,id) values(?,?,?,?,?,?)";
				PreparedStatement ps1=connection.prepareStatement(sql1);
				ps1.setString(1, ""+0);
				ps1.setString(2, rs.getString(1));
				ps1.setString(3, rs.getString(2));
				ps1.setString(4, ""+1);
				ps1.setString(5, rs.getString(3));
				ps1.setString(6, rs.getString(4));
				int res =ps1.executeUpdate();
				
				String sql2="update adjustment_data set adj_parentid='"+rs.getInt(4)+"' where id='"+rs.getInt(4)+"'";
				PreparedStatement ps2=connection.prepareStatement(sql2);
				int res1 =ps2.executeUpdate();
			}
			System.out.println("ok done");
			
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void changeExpiryDateOfProdcut() {

		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/mangalayatan",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://58.84.56.17/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			connection=DriverManager.getConnection("jdbc:mysql://localhost/mangalayatan",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			String sql="select id,expiry_date from inventory_product where id between 499 and 522 ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				String date = rs.getString(2);
				String[] datetime = date.split("-");
				String newdatetime = datetime[2]+"-"+datetime[1]+"-"+datetime[0];
				String sql1="update inventory_product set expiry_date='"+newdatetime+"' where id='"+rs.getInt(1)+"'";
				PreparedStatement ps1=connection.prepareStatement(sql1);
				int res =ps1.executeUpdate();
			}
			System.out.println("ok done");
			
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	private void testCode(){
		for(int i=0;i<5;i++){
			for(int j=0;j<=i;j++){
				System.out.print("*");
			}	
			System.out.println("");
		}
		
		
		int c=0;
		String str="perficient";
		for(String st:str.split("")){
			if(st.equals("e")){
				c++;
			}
		}
		System.out.println(c);
		
		Object obj=new Object();

		
	}
	
private void fileHandler(){
	try {
		File file= new File("Lowdo.txt");
		if (file.createNewFile()) {
			System.out.println("Created at"+file.getAbsolutePath());
		}
		FileWriter writer=new FileWriter(file);
		writer.write("Bhokaat Jaa");
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	
	
	private void testnew() {
		Connection connection =null;
		try {
			System.out.println("start");
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			String sql ="select * from apm_medicine_bill where id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "'%"+ 10 + "%'");
			ResultSet rs =preparedStatement.executeQuery();
			while (rs.next()) {
				System.out.println("Okay");
			}
			System.out.println("end");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	private void testpharmacycalculation() {
		try {
			String discinper="10";
			String subbtotal ="13943.38";
			String balance ="";
			double temp= (Double.parseDouble(discinper)*Double.parseDouble(subbtotal))/100;
			balance = ""+(Double.parseDouble(subbtotal) - temp);
			balance = ""+Math.round(Double.parseDouble(balance)*100)/100;
			System.out.println(""+balance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void addnewcatalogueproductNewfromold() {
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Calendar cal = Calendar.getInstance();
		    String date = dateFormat.format(cal.getTime());  
			String sql ="select categoryid, subcategoryid, product_name, mrp, purchase_price, sale_price, gst, "
					+ "genericname, pack, mfg, deleted, hsnno, shedule, description, molecules,"
					+ "minorder, maxorder, maxconsume, product_code "
					+ "from aureus.inventory_catalogue where location=33 and categoryid=17 ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			
			System.out.println("Start");
			while (rs.next()) {
				String sql1 = "insert into inventory_catalogue (categoryid, subcategoryid, product_name, mrp, purchase_price, sale_price,"
						+ " gst, datetime, location, lastmodified, genericname, pack, mfg,deleted, hsnno,shedule,description,"
						+ "molecules,minorder,maxorder,maxconsume,product_code) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps = connection.prepareStatement(sql1);
				ps.setString(1, rs.getString(1));
				ps.setString(2, rs.getString(2));
				ps.setString(3, rs.getString(3));
				ps.setString(4, rs.getString(4));
				ps.setString(5, rs.getString(5));
				ps.setString(6, rs.getString(6));
				ps.setString(7, rs.getString(7));
				ps.setString(8, date);
				ps.setString(9, ""+36);
				ps.setString(10, date.split(" ")[0]);
				ps.setString(11, rs.getString(8));
				ps.setString(12, rs.getString(9));
				ps.setString(13, rs.getString(10));
				ps.setString(14, rs.getString(11));
				ps.setString(15, rs.getString(12));
				ps.setString(16, rs.getString(13));
				ps.setString(17, rs.getString(14));
				ps.setString(18, rs.getString(15));
				ps.setString(19, rs.getString(16));
				ps.setString(20, rs.getString(17));
				ps.setString(21, rs.getString(18));
				ps.setString(22, rs.getString(19));
				int result = ps.executeUpdate();
			}
			System.out.println("End");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void testmariadb() {
		Connection connection =null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("Driver loded");
			//connection=DriverManager.getConnection("jdbc:mysql://103.242.186.21/chraibgh",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307/test?user=root&password=mysql");
			System.out.println("connection establish");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception");
		}
	}


	private void saveManualStockEntryInStock() {
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://103.242.186.21/chraibgh",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String sql ="select id,catalogueid,location,manual_stock,manual_date from inventory_product where ismanual=1 and manual_stock>0 ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			String datetime = "",product_id="",catalogueid="",location="";
			String source="Manual_Entry";
			System.out.println("Start");
			while (rs.next()) {
				product_id= rs.getString(1);
				catalogueid = rs.getString(2);
				location =rs.getString(3);
				datetime = rs.getString(5)+" "+"00:00:00";
				int res = inventoryProductDAO.insertIntoProductLog("chraibgh", datetime, location, 0, product_id, catalogueid, source, rs.getInt(4), 0, rs.getInt(4), "0", "0", 0, 0, 0, "0");
			}
			System.out.println("End");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	private void testingofrslast() {

		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://58.84.56.17/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			connection=DriverManager.getConnection("jdbc:mysql://localhost/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			//String sql="select discount_request.id from apm_charges_invoice inner join discount_request on discount_request.invoiceid=apm_charges_invoice.id where disc_request=2 and deleted!=1 ";
			StringBuffer buffer = new StringBuffer();
			String location_filter ="32";
			buffer.append("select inventory_catalogue.id from inventory_catalogue ");
			buffer.append("inner join inventory_product on inventory_product.catalogueid = inventory_catalogue.id  ");
			buffer.append("where procurement=0 and product_name is not null and inventory_product.location='"+location_filter+"'  ");
			buffer.append("group by inventory_catalogue.id order by inventory_catalogue.id  ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			int res =0;
			if (rs.last()) {
				res = rs.getRow();
			}
			System.out.println("ok done:"+res);
			
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	private void chngediscoutappliedstatus() {

		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://58.84.56.17/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			String sql="select discount_request.id from apm_charges_invoice inner join discount_request on discount_request.invoiceid=apm_charges_invoice.id where disc_request=2 and deleted!=1 ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			int i =0;
			while(rs.next()){
					String sql1="update discount_request set deleted='1' where id='"+rs.getInt(1)+"'";
					PreparedStatement ps1=connection.prepareStatement(sql1);
					int res =ps1.executeUpdate();
					i++;
					System.out.println(rs.getInt(1)+"-"+i);
					
			}
			System.out.println("ok done");
			
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}




	private void insertBalgopalCatalogueLog() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/chraibgh",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			ArrayList<String> arrayList = new ArrayList<String>();
			arrayList.add("33");
		 	arrayList.add("36");
		 	//-Xms512m -Xmx4g
			long differ = DateTimeUtils.getDifferenceOfTwoDateDBFormat("2019-06-12", "2019-06-12");
			int k = (int)differ;
			for (int i=0; i <= k; i++) {
			   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			   Date d=sdf1.parse("2019-06-12");
			   Calendar cal = Calendar.getInstance();
			   cal.setTime(d);
			   cal.add(Calendar.DATE, i);
			   String dt=sdf1.format(cal.getTime());
			   
			   SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			   Date d2=sdf1.parse(dt);
			   Calendar cal1 = Calendar.getInstance();
			   cal1.setTime(d2);
			   cal1.add(Calendar.DATE, -1);
			   String previousdate=sdf2.format(cal1.getTime());
			   
			   System.out.println("previousdate:"+previousdate+", currentdate:"+dt);
				for (String string : arrayList) {
					String location_filter = string;
					String fromDate = dt;
					String todates = dt;
					
						StringBuffer buffer = new StringBuffer();
						buffer.append("select inventory_catalogue.id from inventory_catalogue ");
						buffer.append("inner join inventory_product on inventory_product.catalogueid = inventory_catalogue.id  ");
						buffer.append("where procurement=0 and product_name is not null and inventory_product.location='"+location_filter+"'  ");
						buffer.append("group by inventory_catalogue.id order by inventory_catalogue.id  ");
						PreparedStatement ps=connection.prepareStatement(buffer.toString());
						ResultSet rs =ps.executeQuery();
						while(rs.next()){
							String catalogueid = rs.getString(1);
							double opeingstockvalue =0;
							double openingstock=0;
							double openingvalue =0;
							double qtyin=0;
							double qtyinvalue =0;
							double qtyout=0;
							double qtyoutvalue =0;
							double salevalue = 0;
							double closingstock=0;
							double closingvalue =0;
							Product product2 = inventoryProductDAO.getYesterdayClosingData(previousdate,catalogueid,location_filter);
							opeingstockvalue = product2.getTotalclosingvalue();
							openingstock = product2.getTotalclosingstock();
							openingvalue = product2.getClosing();
							
							boolean flagss = inventoryProductDAO.checkProductInProductLog(todates,catalogueid,todates,location_filter,"");
							if(flagss){
								Product qtyin_product = inventoryProductDAO.getCatalogueProductIn(todates,catalogueid,string,todates,"");
								Product qtyin_product_update_pro = inventoryProductDAO.getCatalogueProductUpdateProIn(todates,catalogueid,string,todates,""); 
								
								qtyin = qtyin_product.getTotalqty() + qtyin_product_update_pro.getTotalqty();
								qtyinvalue = qtyin_product.getTotal_amount() + qtyin_product_update_pro.getTotal_amount();
								
								Product qtyout_product = inventoryProductDAO.getCatalogueProductOut(todates,catalogueid,string,todates,1,"");
								Product qtyout_product_update_pro = inventoryProductDAO.getCatalogueProductUpdateProOut(todates,catalogueid,string,todates,"");
								Product qtyout_product_sale = inventoryProductDAO.getCatalogueProductSaleOut(todates,catalogueid,string,todates);
								
								qtyout = qtyout_product.getTotalqty() + qtyout_product_update_pro.getTotalqty()+qtyout_product_sale.getTotalqty() ;
								qtyoutvalue = qtyout_product.getTotal_amount() + qtyout_product_update_pro.getTotal_amount()+qtyout_product_sale.getTotal_amount(); 
								salevalue = qtyout_product.getSalevalue() + qtyout_product_update_pro.getSalevalue()+qtyout_product_sale.getSalevalue(); 
							}
							Product manualin = inventoryProductDAO.getManualStockEntry(fromDate,todates,catalogueid,location_filter);
							qtyin = qtyin + manualin.getTotalqty();
							qtyinvalue = qtyinvalue + manualin.getTotal_amount();
							closingstock=openingstock+qtyin-qtyout;
							closingvalue=openingvalue+qtyinvalue-qtyoutvalue;
							double unknownqty = 0;
							if((openingstock +qtyin)<qtyout){
								closingstock =0;
								closingvalue =0;
								unknownqty = qtyout - (openingstock +qtyin);
							}
							
							String sql1="insert into inventory_catalogue_log (lastmodfied, date, location, opening_stock, opeing_value, qty_in, qty_in_value, qty_out, qty_out_value, stock_value, Unknown_qty, closing_stock, closing_value, catalogueid) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						 	PreparedStatement ps1=connection.prepareStatement(sql1);
						 	ps1.setString(1, todates);
						 	ps1.setString(2, todates);
						 	ps1.setString(3, location_filter);
						 	ps1.setString(4, ""+openingstock);
						 	ps1.setString(5, DateTimeUtils.changeFormat(Math.round(opeingstockvalue * 100.0)/100.0));
						 	ps1.setString(6, ""+qtyin);
						 	ps1.setString(7, DateTimeUtils.changeFormat(qtyinvalue));
						 	ps1.setString(8, ""+qtyout);
						 	ps1.setString(9, DateTimeUtils.changeFormat(Math.round(qtyoutvalue * 100.0)/100.0));
						 	ps1.setString(10, DateTimeUtils.changeFormat(Math.round(salevalue * 100.0)/100.0));
						 	ps1.setString(11, ""+unknownqty);
						 	ps1.setString(12, ""+closingstock);
						 	ps1.setString(13, DateTimeUtils.changeFormat(Math.round(closingvalue * 100.0)/100.0));
						 	ps1.setString(14, catalogueid);
						 	int res= ps1.executeUpdate();
							
						}
					
				}
			}
			
			System.out.println("ok done");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

			
		
		
	}


	private void updatePharmacyDisc() {
		try {
			System.out.println("Start");
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			Priscription discountdata = pharmacyDAO.getDiscountData(113);
			//Priscription priscription = pharmacyDAO.getPharmacyBillDetails(discountdata.getBillno());
			String userid = "drkhalil";
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Calendar cal = Calendar.getInstance();
		    String datetime = dateFormat.format(cal.getTime());  
			
			String billno1=discountdata.getBillno();
			int billno = Integer.parseInt(billno1);
			double discvaluetotal = 0;
			CompleteAppointment appointment=pharmacyDAO.getBillDetails(billno);
			if(discountdata.getDisc_type()==0){
				discvaluetotal = discountdata.getTotalamt()*discountdata.getDiscinper()/100;
			}else{
				discvaluetotal = discountdata.getDiscount();
			}
			
			discvaluetotal = Math.round(discvaluetotal);
			  if(discvaluetotal>0){
				  
		  		double totaldebit = discountdata.getTotalamt()-discvaluetotal;
		  		int res = pharmacyDAO.updateBillDiscount(discvaluetotal,billno,totaldebit);
		  		ArrayList<Priscription> arrayList = pharmacyDAO.getMedicineChargeList(billno);
		  		double totalgst=0;
		  		for (Priscription priscription2 : arrayList) {
					double tvat= Double.parseDouble(priscription2.getVat());
			    	double tot= 0;
			    	try{
			    		tot= priscription2.getQty() * Double.parseDouble(priscription2.getMrp());
			    	}catch (Exception e) {
						// TODO: handle exception
					}
			    	double temptot=0;
			    	double tempvat =0;
			    	tempvat=tot*discountdata.getDiscinper()/100;
			    	temptot = tot - tempvat;
			    	double dividevat= 100+tvat;
			    	/*double gst= tot*tvat/dividevat;*/
			    	double gst= temptot*tvat/dividevat;
			    	double half= gst/2;
			    	half = Math.round(half*100.0)/100.0;
			    	gst = Math.round(gst*100.0)/100.0;
			    	int es = pharmacyDAO.updateMedicineChargeGST(priscription2.getId(),gst,half,tempvat,tvat);
			    	totalgst = totalgst+gst;
				}
				res =pharmacyDAO.updateDiscountBillStatus(discountdata.getBillno(),2);
		  		int resss = pharmacyDAO.updateDiscountApproveNotes(discountdata.getId(),"Approved",1,userid,datetime);
		  		//int ress = pharmacyDAO.updateBalanceByBill(discountdata.getBillno(), DateTimeUtils.changeFormat(totaldebit));
		  		double temp = Double.parseDouble(appointment.getBalance())-discvaluetotal;
				temp = Math.round(temp*100)/100;
	  	  		int d=pharmacyDAO.updateBillBalance(""+billno,temp);
		  		double cgst = totalgst/2;
		  		int ress = pharmacyDAO.updateBillVatNew(Math.round(cgst*100.0)/100.0,Math.round(totalgst*100.0)/100.0,discountdata.getBillno(),discountdata.getDisc_type(),discountdata.getDiscount());
		  	  }
			  System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	private void generateProductLogBackend() {

		try {
			//Indivisual Product wise back log
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//-Xms512m -Xmx4g
			//InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String location_filter = "36";
			String previousdate = "2019-03-31";
			String fromDate = "2019-04-01";
			String toDate = "2019-11-08";
			StringBuffer buffer = new StringBuffer();
			/*buffer.append("select id from inventory_product where location=33 and procurement=0 and added_date<'2019-03-31'  ");*/
			buffer.append("select id from inventory_product where location=36 and procurement=0 ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				String productid = rs.getString(1);
				System.out.println("Product Id:"+productid);
				
				double opeingstockvalue =0;
				double openingstock=0;
				double openingvalue =0;
				
				Product product2 = getYesterdayClosingData(previousdate,productid,location_filter);
				opeingstockvalue = product2.getTotalclosingvalue();
				openingstock = product2.getTotalclosingstock();
				openingvalue = product2.getClosing();
				
				//Purchase qty =  in fromdate and todate
				Product purproduct = getPuchaseProductDataBetweenNew(productid,fromDate,toDate,location_filter);  
				
				//return from patient =  in fromdate and todate
				Product returnpatientqty = getReturnPatientProductDataBetweenNew(productid,fromDate,toDate,"0");
				
				// retruntransfer = in fromdate and todate 
				double retruntransferqtyin =0;
				double retruntransferqtyinvalue=0;
				double directtransferqtyin=0;
				double requesttransferqtyin =0;
				double directtransferqtyinvalue=0;
				double requesttransferqtyinvalue=0;
				if(!location_filter.equals("0")){
					//Return transfer between date 
					Product returntransferproductin = getReturnTransferBetweenDateNew(productid,fromDate,location_filter,toDate);
					retruntransferqtyin = returntransferproductin.getTotalqty();
					retruntransferqtyinvalue = returntransferproductin.getTotal_amount();
					
					//Direct transfer between 
					Product directtransferproductin = getDirectTransferBetweenDateInNew(productid,fromDate,location_filter,toDate);
					
					//Request Transfer between
					Product requesttransferproductin = getRequestTransferBetweenDateInNew(productid,fromDate,location_filter,toDate);
					
					directtransferqtyin = directtransferproductin.getTotalqty();
					requesttransferqtyin = requesttransferproductin.getTotalqty();
					directtransferqtyinvalue = directtransferproductin.getTotal_amount();
					requesttransferqtyinvalue = requesttransferproductin.getTotal_amount();
				}
				
				Product adjustmentbetweenin = getAdjustmentDataBetweenInNew(productid,fromDate,location_filter,toDate);
				
				double qtyin = purproduct.getTotalqty() +returnpatientqty.getTotalqty() 
							+retruntransferqtyin + directtransferqtyin +requesttransferqtyin + adjustmentbetweenin.getTotalqty() ;
				
				double qtyinvalue = purproduct.getTotal_amount() + returnpatientqty.getTotal_amount() 
							+retruntransferqtyinvalue + directtransferqtyinvalue +requesttransferqtyinvalue + adjustmentbetweenin.getTotal_amount() ;
				
				//Qty out - Patient sale + Return to Supplier + Consume + direct transfer + request return  = in Fromdate and Todate
				
				// retruntransfer = in fromdate and todate 
				double directtransferqtyout =0;
				double directtransferqtyoutvalue=0;
				double requesttransferqtyout =0;
				double requesttransferqtyoutvalue=0;
				double returntransferqtyout =0;
				double returntransferqtyoutvalue=0;
				double directsaleprice=0;
				double returnsaleprice=0;
				double requestsaleprice=0;
				if(!location_filter.equals("0")){
					//Direct transfer between 
					Product directtransferproductout = getDirectTransferBetweenDateNew(productid,fromDate,location_filter,toDate);
					
					//Request Transfer between
					Product requesttransferproductout = getRequestTransferBetweenDateNew(productid,fromDate,location_filter,toDate);
					
					directtransferqtyout = directtransferproductout.getTotalqty();
					requesttransferqtyout = requesttransferproductout.getTotalqty();
					directtransferqtyoutvalue = directtransferproductout.getTotal_amount();
					requesttransferqtyoutvalue = requesttransferproductout.getTotal_amount();
					
					//Return to store between
					Product returntransferproductin = getReturnTransferBetweenDateOutNew(productid,fromDate,location_filter,toDate);
					returntransferqtyout = returntransferproductin.getTotalqty();
					returntransferqtyoutvalue = returntransferproductin.getTotal_amount();
					
					directsaleprice = directtransferproductout.getSalepricetotal();
					returnsaleprice = returntransferproductin.getSalepricetotal();
					requestsaleprice = requesttransferproductout.getSalepricetotal();
				}
				
				//Patient Sale between Fromdate and Todate
				Product salepatient = getPatientSaleBetweenDateNew(productid,fromDate,toDate,"0");
				
				//Return to supplier in between fromdate and todate
				Product returntosuppplier = getReturnToSupplierBetwwenDateNew(productid,fromDate,toDate,location_filter); 
				
				//Consume to user or patient between Fromdate and Todate
				Product consume = getConsumeBetweenDateNew(productid,fromDate,toDate,location_filter);
				
				Product adjustmentbetweenOut = getAdjustmentDatabetweenOutNew(productid,fromDate,location_filter,toDate);
				
				double qtyout = salepatient.getTotalqty()+returntosuppplier.getTotalqty()
							+consume.getTotalqty() + directtransferqtyout +requesttransferqtyout 
							+returntransferqtyout + adjustmentbetweenOut.getTotalqty();
				
				//Stock value =  Patient sale unit price + Return to Supplier purchase price + Consume purchase price
				double stockvalue =  salepatient.getTotal_amount() + returntosuppplier.getTotal_amount()
										+consume.getTotal_amount() +directtransferqtyoutvalue +requesttransferqtyoutvalue 
										+returntransferqtyoutvalue + adjustmentbetweenOut.getTotal_amount();
				
				//sale price total 
				double saleprice = salepatient.getSalepricetotal() +returntosuppplier.getSalepricetotal()
										+consume.getSalepricetotal() +directsaleprice +returnsaleprice 
										+requestsaleprice + adjustmentbetweenOut.getSalepricetotal();
				
				//Closing - opening + qtyin - qtyout
				double closingstock = openingstock +qtyin - qtyout;
				
				//double closingvalue = unitprice * closingstock;
				double closingvalue = opeingstockvalue + qtyinvalue - stockvalue;
				double unknownqty = 0;
				double unknownvalue =0;
				if((openingstock +qtyin)<qtyout){
					/*closingstock =0;
					closingvalue =0;*/
					unknownqty = qtyout - (openingstock +qtyin);
					unknownvalue = stockvalue - (opeingstockvalue + qtyinvalue);
					/*closingstock = Math.abs(unknownqty);
					closingvalue = Math.abs(unknownvalue);*/
				}
				double closningvalue = (openingvalue + qtyinvalue + Math.abs(unknownvalue)) - stockvalue;
				closningvalue = Math.abs(closningvalue);
				//double unitprice = getUnitPriceFromCatalogueid(catalogueid);
				
			 	String sql1="   ";
			 	StringBuffer buffer2 = new StringBuffer();
			 	buffer2.append("insert into inventory_catalogue_log (lastmodfied, date, location, opening_stock,");
			 	buffer2.append("opeing_value, qty_in, qty_in_value, qty_out, qty_out_value, stock_value, Unknown_qty,");
			 	buffer2.append("closing_stock, closing_value, catalogueid,unknown_value,opening,closing,");
			 	buffer2.append("grn_qty,grn_value,return_phar_qty,return_phar_value,return_trans_qty_in,return_trans_value_in,");
			 	buffer2.append("direct_trans_qty_in,direct_trans_value_in,request_trans_qty_in,request_trans_value_in,");
			 	buffer2.append("adjust_qty_in,adjust_value_in,direct_trans_qty_out,direct_trans_value_out,");
			 	buffer2.append("request_trans_qty_out,request_trans_value_out,return_trans_qty_out,return_trans_value_out,");
			 	buffer2.append("direct_trans_svalue_out, request_trans_svalue_out, return_trans_svalue_out, phar_qty_out, ");
			 	buffer2.append("phar_value_out, phar_svalue_out, return_supp_qty, return_supp_value, return_supp_svalue, consume_qty, ");
			 	buffer2.append("consume_value, consume_svalue, adjust_out_qty, adjust_out_value, adjust_out_svalue)");
			 	buffer2.append("values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,");
			 	buffer2.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			 	PreparedStatement ps1=connection.prepareStatement(buffer2.toString());
			 	ps1.setString(1, fromDate);
			 	ps1.setString(2, toDate);
			 	ps1.setString(3, location_filter);
			 	ps1.setString(4, ""+openingstock);
			 	ps1.setString(5, DateTimeUtils.changeFormat(Math.round(opeingstockvalue * 100.0)/100.0));
			 	ps1.setString(6, ""+qtyin);
			 	ps1.setString(7, DateTimeUtils.changeFormat(qtyinvalue));
			 	ps1.setString(8, ""+qtyout);
			 	ps1.setString(9, DateTimeUtils.changeFormat(Math.round(stockvalue * 100.0)/100.0));
			 	ps1.setString(10, DateTimeUtils.changeFormat(Math.round(saleprice * 100.0)/100.0));
			 	ps1.setString(11, ""+unknownqty);
			 	ps1.setString(12, ""+closingstock);
			 	ps1.setString(13, DateTimeUtils.changeFormat(Math.round(closingvalue * 100.0)/100.0));
			 	ps1.setString(14, rs.getString(1));
			 	ps1.setString(15, DateTimeUtils.changeFormat(Math.round(unknownvalue * 100.0)/100.0));
			 	ps1.setString(16, DateTimeUtils.changeFormat(Math.round(openingvalue * 100.0)/100.0));
			 	ps1.setString(17, DateTimeUtils.changeFormat(Math.round(closningvalue * 100.0)/100.0));
			 	
			 	ps1.setString(18, ""+purproduct.getTotalqty());
			 	ps1.setString(19, ""+purproduct.getTotal_amount());
			 	ps1.setString(20, ""+returnpatientqty.getTotalqty());
			 	ps1.setString(21, ""+returnpatientqty.getTotal_amount());
			 	ps1.setString(22, ""+retruntransferqtyin);
			 	ps1.setString(23, ""+retruntransferqtyinvalue);
			 	ps1.setString(24, ""+directtransferqtyin);
			 	ps1.setString(25, ""+directtransferqtyinvalue);
			 	ps1.setString(26, ""+requesttransferqtyin);
			 	ps1.setString(27, ""+requesttransferqtyinvalue);
			 	ps1.setString(28, ""+adjustmentbetweenin.getTotalqty());
			 	ps1.setString(29, ""+adjustmentbetweenin.getTotal_amount());
			 	
			 	ps1.setString(30, ""+directtransferqtyout);
			 	ps1.setString(31, ""+directtransferqtyoutvalue);
			 	ps1.setString(32, ""+requesttransferqtyout);
			 	ps1.setString(33, ""+requesttransferqtyoutvalue);
			 	ps1.setString(34, ""+returntransferqtyout);
			 	ps1.setString(35, ""+returntransferqtyoutvalue);
			 	ps1.setString(36, ""+directsaleprice);
			 	ps1.setString(37, ""+requestsaleprice);
			 	ps1.setString(38, ""+returnsaleprice);
			 	ps1.setString(39, ""+salepatient.getTotalqty());
			 	ps1.setString(40, ""+salepatient.getTotal_amount());
			 	ps1.setString(41, ""+salepatient.getSalepricetotal());
			 	ps1.setString(42, ""+returntosuppplier.getTotalqty());
			 	ps1.setString(43, ""+returntosuppplier.getTotal_amount());
			 	ps1.setString(44, ""+returntosuppplier.getSalepricetotal());
			 	ps1.setString(45, ""+consume.getTotalqty());
			 	ps1.setString(46, ""+consume.getTotal_amount());
			 	ps1.setString(47, ""+consume.getSalepricetotal());
			 	ps1.setString(48, ""+adjustmentbetweenOut.getTotalqty());
			 	ps1.setString(49, ""+adjustmentbetweenOut.getTotal_amount());
			 	ps1.setString(50, ""+adjustmentbetweenOut.getSalepricetotal());
			 	
			 	
			 	int res= ps1.executeUpdate();
				/*}*/
			}
			
			System.out.println("ok done");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}


	private Product getPatientSaleBetweenDateNew(String productid, String fromDate, String toDate,
			String location_filter) {

		Product product= new Product();
		try {
			toDate = toDate +" "+ "59:59:59";
			StringBuffer buffer= new StringBuffer();
			buffer.append("select sum(quantity),sum(quantity*charge),sum(quantity*(purchaseprice/pack))  ");
			buffer.append("from apm_medicine_charges ");
			buffer.append("inner join apm_medicine_bill on apm_medicine_charges.invoiceid = apm_medicine_bill.id ");
			buffer.append("inner join inventory_product on inventory_product.id = apm_medicine_charges.product_id ");
			buffer.append("where apm_medicine_bill.isreturn =0 and inventory_product.id='"+productid+"' and date between '"+fromDate+"' and '"+toDate+"' and apm_medicine_bill.deleted=0  ");
			if(!location_filter.equals("0")){
				buffer.append("and apm_medicine_bill.location='"+location_filter+"' ");
			}
			PreparedStatement preparedStatement=connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			product.setSalepricetotal(0);
			while (rs.next()) {
				product.setSalepricetotal(rs.getDouble(2));
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return product;
	
	}


	private Product getReturnPatientProductDataBetweenNew(String productid, String fromDate, String toDate,
			String location_filter) {

		Product product= new Product();
		try {
			toDate = toDate +" "+ "59:59:59";
			StringBuffer buffer= new StringBuffer();
			buffer.append("select sum(quantity),sum(quantity*charge),sum(quantity*(purchaseprice/pack))  ");
			buffer.append("from apm_medicine_charges ");
			buffer.append("inner join apm_medicine_bill on apm_medicine_charges.invoiceid = apm_medicine_bill.id ");
			buffer.append("inner join inventory_product on inventory_product.id = apm_medicine_charges.product_id ");
			buffer.append("where apm_medicine_bill.isreturn =1  and date between '"+fromDate+"' and '"+toDate+"' and apm_medicine_bill.deleted=0  ");
			if(!location_filter.equals("0")){
				buffer.append("and apm_medicine_bill.location='"+location_filter+"' ");
			}
			if(productid!=null){
				buffer.append("and inventory_product.id='"+productid+"' ");
			}
			PreparedStatement preparedStatement=connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			while (rs.next()) {
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return product;
	
	}


	private Product getAdjustmentDatabetweenOutNew(String productid, String fromDate, String location_filter,
			String toDate) {

		Product product = new Product();
		try {
			toDate = toDate +" "+ "59:59:59";
			StringBuffer buffer = new StringBuffer();
			buffer.append("select sum(adjustment_data.change_qty),sum(purchaseprice)/sum(pack),sum(adjustment_data.change_qty*(purchaseprice/pack)),sum(adjustment_data.change_qty*saleprice)   ");
			buffer.append("from adjustment_data ");
			buffer.append("inner join inventory_product on inventory_product.id = adjustment_data.product_id ");
			buffer.append("where adjustment_type!=2 and inventory_product.id='"+productid+"' and adjustment_data.datetime between '"+fromDate+"' and '"+toDate+"' ");
			if(!location_filter.equals("0")){
				buffer.append("and inventory_product.location='"+location_filter+"' ");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double total=0.0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(total));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			product.setSalepricetotal(0);
			while (rs.next()) {
				product.setSalepricetotal(rs.getDouble(4));
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	
	}


	private Product getConsumeBetweenDateNew(String productid, String fromDate, String toDate, String location_filter) {

		Product product = new Product();
		try {
			toDate = toDate +" "+ "59:59:59";
			StringBuffer buffer= new StringBuffer();
			buffer.append("select sum(indent_patient_transfer_log.qty),sum(purchaseprice)/sum(pack),sum(indent_patient_transfer_log.qty*(purchaseprice/pack)),sum(indent_patient_transfer_log.qty*saleprice) ");
			buffer.append("from indent_patient_transfer_log ");
			buffer.append("inner join inventory_product on  inventory_product.id = indent_patient_transfer_log.prodid ");
			buffer.append("where inventory_product.id='"+productid+"' and indent_patient_transfer_log.datetime between '"+fromDate+"' and '"+toDate+"' ");
			if(!location_filter.equals("0")){
				buffer.append("and indent_patient_transfer_log.fromlocation='"+location_filter+"' ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			product.setSalepricetotal(0);
			while(rs.next()){
				product.setSalepricetotal(rs.getDouble(4));
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return product;
	
	}


	private Product getReturnToSupplierBetwwenDateNew(String productid, String fromDate, String toDate,
			String location_filter) {

		Product product = new Product();
		try {
			toDate = toDate +" "+ "59:59:59";
			StringBuffer buffer= new StringBuffer();
			buffer.append("SELECT sum(inventory_product_return_log.qty),sum(purchaseprice)/sum(pack),sum(inventory_product_return_log.qty*(purchaseprice/pack)),sum(saleprice*inventory_product_return_log.qty) ");
			buffer.append("from inventory_product_return_log ");
			buffer.append("inner join inventory_product on  inventory_product.id = inventory_product_return_log.productid ");
			buffer.append("where status!=0 and inventory_product.id='"+productid+"' and datetime between '"+fromDate+"' and '"+toDate+"' and inventory_product_return_log.iscancel=0 ");
			if(!location_filter.equals("0")){
				buffer.append("and inventory_product.location='"+location_filter+"' ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			product.setSalepricetotal(0);
			while(rs.next()){
				product.setSalepricetotal(rs.getDouble(4));
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return product;
	
	}


	private Product getReturnTransferBetweenDateOutNew(String productid, String fromDate, String location_filter,
			String toDate) {

		Product product = new Product();
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append("select sum(inventory_transfer_log.qty),sum(purchaseprice)/sum(pack),sum(inventory_transfer_log.qty*(purchaseprice/pack)),sum(inventory_transfer_log.qty*saleprice) from inventory_transfer_log ");
			buffer.append("inner join inventory_parent_transfer_log on inventory_parent_transfer_log.id=inventory_transfer_log.parentid ");
			buffer.append("inner join inventory_product on  inventory_product.id = inventory_transfer_log.old_prodid ");
			buffer.append("where inventory_parent_transfer_log.req_or_trans=2 and inventory_parent_transfer_log.issued_date between '"+fromDate+"' and '"+toDate+"' and inventory_parent_transfer_log.deleted=0 ");
			buffer.append("and inventory_product.id='"+productid+"' ");
			if (!location_filter.equals("0")) {
				buffer.append("and from_location ='" + location_filter + "' ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			product.setSalepricetotal(0);
			while(rs.next()){
				product.setSalepricetotal(rs.getDouble(4));
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return product;
	
	}


	private Product getRequestTransferBetweenDateNew(String productid, String fromDate, String location_filter,
			String toDate) {

		Product product = new Product();
		try {
			toDate = toDate +" "+ "59:59:59";
			StringBuffer buffer=new StringBuffer();
			buffer.append("SELECT sum(inventory_request_temp_log.qty),sum(purchaseprice)/sum(pack),sum(inventory_request_temp_log.qty*(purchaseprice/pack)),sum(inventory_request_temp_log.qty*saleprice)   ");
			buffer.append("FROM inventory_request_temp_log ");
			buffer.append("inner join inventory_parent_transfer_log on inventory_parent_transfer_log.id=inventory_request_temp_log.parentid ");
			buffer.append("inner join inventory_product on  inventory_product.id = inventory_request_temp_log.old_prodid ");
			buffer.append("where transfer_date between '"+fromDate+"' and '"+toDate+"' and inventory_parent_transfer_log.deleted=0 ");
			buffer.append("and inventory_product.id='"+productid+"' ");
			if (!location_filter.equals("0")) {
				buffer.append("and inventory_request_temp_log.location ='" + location_filter + "' ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			product.setSalepricetotal(0);
			while(rs.next()){
				product.setSalepricetotal(rs.getDouble(4));
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return product;
	
	}


	private Product getDirectTransferBetweenDateNew(String productid, String fromDate, String location_filter,
			String toDate) {

		Product product = new Product();
		try {
			toDate = toDate +" "+ "59:59:59";
			StringBuffer buffer= new StringBuffer();
			//req_or_transfer==1
			buffer.append("select sum(inventory_transfer_log.qty),sum(purchaseprice)/sum(pack),sum(inventory_transfer_log.qty*(purchaseprice/pack)),sum(inventory_transfer_log.qty*saleprice) from inventory_transfer_log ");
			buffer.append("inner join inventory_parent_transfer_log on inventory_parent_transfer_log.id=inventory_transfer_log.parentid ");
			buffer.append("inner join inventory_product on  inventory_product.id = inventory_transfer_log.old_prodid ");
			buffer.append("where inventory_parent_transfer_log.req_or_trans=1 and inventory_parent_transfer_log.issued_date between '"+fromDate+"' and '"+toDate+"' and inventory_parent_transfer_log.deleted=0 ");
			buffer.append("and inventory_product.id='"+productid+"' ");
			if (!location_filter.equals("0")) {
				buffer.append("and from_location ='" + location_filter + "' ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			product.setSalepricetotal(0);
			while(rs.next()){
				product.setSalepricetotal(rs.getDouble(4));
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return product;
	
	}


	private Product getAdjustmentDataBetweenInNew(String productid, String fromDate, String location_filter,
			String toDate) {

		Product product = new Product();
		try {
			toDate = toDate +" "+ "59:59:59";
			StringBuffer buffer = new StringBuffer();
			buffer.append("select sum(adjustment_data.change_qty),sum(purchaseprice)/sum(pack),sum(adjustment_data.change_qty*(purchaseprice/pack))   ");
			buffer.append("from adjustment_data ");
			buffer.append("inner join inventory_product on inventory_product.id = adjustment_data.product_id ");
			buffer.append("where adjustment_type=2  and adjustment_data.datetime between '"+fromDate+"' and '"+toDate+"' ");
			if(productid!=null){
				buffer.append("and inventory_product.id='"+productid+"' ");
			}
			if(!location_filter.equals("0")){
				buffer.append("and inventory_product.location='"+location_filter+"' ");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double total=0.0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(total));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			while (rs.next()) {
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	
	}


	private Product getRequestTransferBetweenDateInNew(String productid, String fromDate, String location_filter,
			String toDate) {

		Product product = new Product();
		try {
			StringBuffer buffer=new StringBuffer();
			buffer.append("SELECT sum(inventory_request_temp_log.qty),sum(purchaseprice)/sum(pack),sum(inventory_request_temp_log.qty*(purchaseprice/pack))  ");
			buffer.append("FROM inventory_request_temp_log ");
			buffer.append("inner join inventory_parent_transfer_log on inventory_parent_transfer_log.id=inventory_request_temp_log.parentid ");
			buffer.append("inner join inventory_product on  inventory_product.id = inventory_request_temp_log.old_prodid ");
			buffer.append("where transfer_date between '"+fromDate+"' and '"+toDate+"' and inventory_parent_transfer_log.deleted=0 ");
			if(productid!=null){
				buffer.append("and inventory_product.id='"+productid+"' ");
			}
			if (!location_filter.equals("0")) {
				buffer.append("and from_location ='" + location_filter + "' ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			while(rs.next()){
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return product;
	
	}


	private Product getDirectTransferBetweenDateInNew(String productid, String fromDate, String location_filter,
			String toDate) {

		Product product = new Product();
		try {
			StringBuffer buffer= new StringBuffer();
			//req_or_transfer==1
			buffer.append("select sum(inventory_transfer_log.qty),sum(purchaseprice)/sum(pack),sum(inventory_transfer_log.qty*(purchaseprice/pack)) from inventory_transfer_log ");
			buffer.append("inner join inventory_parent_transfer_log on inventory_parent_transfer_log.id=inventory_transfer_log.parentid ");
			buffer.append("inner join inventory_product on  inventory_product.id = inventory_transfer_log.old_prodid ");
			buffer.append("where inventory_parent_transfer_log.req_or_trans=1 and inventory_parent_transfer_log.issued_date between '"+fromDate+"' and '"+toDate+"' and inventory_parent_transfer_log.deleted=0 ");
			if(productid!=null){
				buffer.append("and inventory_product.id='"+productid+"' ");
			}
			
			if (!location_filter.equals("0")) {
				buffer.append("and to_location ='" + location_filter + "' ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			while(rs.next()){
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return product;
	
	}


	private Product getReturnTransferBetweenDateNew(String productid, String fromDate, String location_filter,
			String toDate) {

		Product product = new Product();
		try {
			toDate = toDate +" "+ "59:59:59";
			StringBuffer buffer= new StringBuffer();
			buffer.append("select sum(inventory_transfer_log.qty),sum(purchaseprice)/sum(pack),sum(inventory_transfer_log.qty*(purchaseprice/pack)) from inventory_transfer_log ");
			buffer.append("inner join inventory_parent_transfer_log on inventory_parent_transfer_log.id=inventory_transfer_log.parentid ");
			buffer.append("inner join inventory_product on  inventory_product.id = inventory_transfer_log.old_prodid ");
			buffer.append("where inventory_parent_transfer_log.req_or_trans=2 and inventory_parent_transfer_log.issued_date between '"+fromDate+"' and '"+toDate+"' and inventory_parent_transfer_log.deleted=0 ");
			
			if(productid!=null){
				buffer.append("and inventory_product.id='"+productid+"' ");
			}
			
			if (!location_filter.equals("0")) {
				buffer.append("and to_location ='" + location_filter + "' ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			while(rs.next()){
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return product;
	
	}


	private Product getPuchaseProductDataBetweenNew(String productid, String fromDate, String toDate,
			String location_filter) {

		Product product= new Product();
		try {
			toDate = toDate +" "+ "59:59:59";
			StringBuffer buffer = new StringBuffer();
			buffer.append("select sum((inventory_procurement.qty+0) + (inventory_product.freeqty+0)),sum(purchaseprice)/sum(pack),sum(inventory_procurement.qty*(purchaseprice/pack))+sum(inventory_product.freeqty * (purchaseprice/pack)),   ");
			buffer.append("sum(inventory_product.freeqty),sum(inventory_procurement.qty),sum(inventory_procurement.qty*(purchaseprice/pack)),sum(inventory_product.freeqty * (purchaseprice/pack)) ");
			buffer.append("from inventory_procurement ");
			buffer.append("inner join inventory_product on inventory_product.id = inventory_procurement.prodid ");
			buffer.append("where  inventory_procurement.date between '"+fromDate+"' and '"+toDate+"' and  procurement='0'  and inventory_procurement.deleted=0 and confirm=1 and gudreceipt=1 ");
			buffer.append("and  inventory_procurement.iscancel=0 ");
			if(!location_filter.equals("0")){
				buffer.append("and inventory_product.location='"+location_filter+"' ");
			}
			if(productid!=null){
				buffer.append("and inventory_product.id="+productid+" ");
			}
			
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			product.setGrnqtyamtttl(0);
			product.setGrnfreeqtyamtttl(0);
			while (rs.next()) {
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
				product.setGrnqtyamtttl(rs.getDouble(6));
				product.setGrnfreeqtyamtttl(rs.getDouble(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	
	}


	private void insertToOnOffStdChild(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/chraibgh",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			StringBuffer buffer = new StringBuffer();
			buffer.append(" select id, ondatetime, offdatetime,chargeid from apm_std_onoff_charge ");
			buffer.append("");
			PreparedStatement ps= connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				int id=rs.getInt(1);
				String onTime[]=DateTimeUtils.isNull(rs.getString(2)).split(",");
				String offTime[]=DateTimeUtils.isNull(rs.getString(3)).split(",");
				String chargeid=rs.getString(4);
				Pattern pattern = Pattern.compile("\\w{2}-\\w{2}-\\w{4} \\w{2}:\\w{2}:\\w{2}");
				for (int i = 0; i < offTime.length; i++) {
					String ontimetemp="",offtimetemp="";
					try {
						 ontimetemp=DateTimeUtils.isNull(onTime[i]);
						 offtimetemp=DateTimeUtils.isNull(offTime[i]);
					} catch (Exception e) {
						continue;
					}
					
					
//					 String subonn[]=ontimetemp.split("/\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}:\\d{2}/");
				
					 
					if(ontimetemp.contains(":")&&offtimetemp.contains(":")){
						String sql="  insert into apm_std_onoff_charge_child(child_masterid, child_parentid, onuserid, offuserid, oncommencing, offcommencing, ondate, offdate)";
						sql=sql+" values(?,?,?,?,?,?,?,?)";
						 Matcher matcher= pattern.matcher(ontimetemp);
						 if(matcher.find()){
							 ontimetemp=matcher.group();
						 }
						 Matcher matcher1= pattern.matcher(offtimetemp);
						 if(matcher1.find()){
							 offtimetemp=matcher1.group();
						 }
						 boolean isdoesntExist=ifChargeExist(""+id, ontimetemp, offtimetemp, connection);
						if(isdoesntExist){
							System.out.println(ontimetemp+"  ---  "+id+"-----"+offtimetemp);
							PreparedStatement pschild=connection.prepareStatement(sql);
							pschild.setString(1, chargeid);
							pschild.setString(2, ""+id);
							pschild.setString(3, "chraibgh");
							pschild.setString(4,  "chraibgh");
							String dt[]=ontimetemp.split(" ");
							String dt1[]=offtimetemp.split(" ");
							pschild.setString(5, DateTimeUtils.getCommencingDate1(dt[0])+" "+dt[1]);
							pschild.setString(6, DateTimeUtils.getCommencingDate1(dt1[0])+" "+dt1[1]);
							pschild.setString(7, ontimetemp);
							pschild.setString(8, offtimetemp);
							int s=pschild.executeUpdate();
						}
						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
boolean ifChargeExist(String parentid, String ondate, String offdate,Connection connection){
	boolean flag=true;
	try {
		PreparedStatement ps= connection.prepareStatement("select * from apm_std_onoff_charge_child where child_parentid='"+parentid+"' and ondate='"+ondate+"' and offdate='"+offdate+"'");
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			flag=false;
		}
				
	} catch (Exception e) {
		e.printStackTrace();
	}
	return flag;
}	
	
	//this code use for aureus inventory_opening closing year by year
	//   30-03-2020 reused
	private void newcatalogueLog() {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			//ArrayList<String> arrayList = new ArrayList<String>();
			//-Xms512m -Xmx4g
			
			String location_filter = "33";
			//is from which date you want to start
			String fromDate = "2019-04-01";
			//is to which date you want to end
			String toDate = "2020-03-29";
			//this is previous date of stored data
			String previousdate= "2019-03-31";
			StringBuffer buffer = new StringBuffer();
			buffer.append("select inventory_catalogue.id from inventory_catalogue ");
			buffer.append("inner join inventory_product on inventory_product.catalogueid = inventory_catalogue.id  ");
			buffer.append("where procurement=0 and product_name is not null and inventory_product.location='"+location_filter+"' ");
			buffer.append("and inventory_catalogue.id in (2105+0) ");
			buffer.append("group by inventory_catalogue.id order by inventory_catalogue.id  ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				String catalogueid = rs.getString(1);
				System.out.println("Catalogueid:"+catalogueid);
				double opeingstockvalue =0;
				double openingstock=0;
				double openingvalue =0;
				//Its 1st time data provided 
				opeingstockvalue = 0.0;
				openingstock = 0;
				openingvalue = 0.0;
				
				//If its second time
				Product product2 = getYesterdayClosingData(previousdate,catalogueid,location_filter);
				opeingstockvalue = product2.getTotalclosingvalue();
				openingstock = product2.getTotalclosingstock();
				openingvalue = product2.getClosing();
				
				//Purchase qty =  in fromdate and todate
				Product purproduct = inventoryProductDAO.getPuchaseProductDataBetween(catalogueid,fromDate,toDate,location_filter);  
				
				//return from patient =  in fromdate and todate
				Product returnpatientqty = inventoryProductDAO.getReturnPatientProductDataBetween(catalogueid,fromDate,toDate,location_filter);
				
				// retruntransfer = in fromdate and todate 
				double retruntransferqtyin =0;
				double retruntransferqtyinvalue=0;
				double directtransferqtyin=0;
				double requesttransferqtyin =0;
				double directtransferqtyinvalue=0;
				double requesttransferqtyinvalue=0;
				if(!location_filter.equals("0")){
					//Return transfer between date 
					Product returntransferproductin = inventoryProductDAO.getReturnTransferBetweenDate(catalogueid,fromDate,location_filter,toDate);
					retruntransferqtyin = returntransferproductin.getTotalqty();
					retruntransferqtyinvalue = returntransferproductin.getTotal_amount();
					
					//Direct transfer between 
					Product directtransferproductin = inventoryProductDAO.getDirectTransferBetweenDateIn(catalogueid,fromDate,location_filter,toDate);
					
					//Request Transfer between
					Product requesttransferproductin = inventoryProductDAO.getRequestTransferBetweenDateIn(catalogueid,fromDate,location_filter,toDate);
					
					directtransferqtyin = directtransferproductin.getTotalqty();
					requesttransferqtyin = requesttransferproductin.getTotalqty();
					directtransferqtyinvalue = directtransferproductin.getTotal_amount();
					requesttransferqtyinvalue = requesttransferproductin.getTotal_amount();
				}
				
				Product adjustmentbetweenin = inventoryProductDAO.getAdjustmentDataBetweenIn(catalogueid,fromDate,location_filter,toDate);
				
				double qtyin = purproduct.getTotalqty() + returnpatientqty.getTotalqty() 
							+retruntransferqtyin + directtransferqtyin +requesttransferqtyin + adjustmentbetweenin.getTotalqty() ;
				
				double qtyinvalue = purproduct.getTotal_amount() + returnpatientqty.getTotal_amount() 
							+retruntransferqtyinvalue + directtransferqtyinvalue +requesttransferqtyinvalue + adjustmentbetweenin.getTotal_amount() ;
				
				//Qty out - Patient sale + Return to Supplier + Consume + direct transfer + request return  = in Fromdate and Todate
				
				// retruntransfer = in fromdate and todate 
				double directtransferqtyout =0;
				double directtransferqtyoutvalue=0;
				double requesttransferqtyout =0;
				double requesttransferqtyoutvalue=0;
				double returntransferqtyout =0;
				double returntransferqtyoutvalue=0;
				double directsaleprice=0;
				double returnsaleprice=0;
				double requestsaleprice=0;
				if(!location_filter.equals("0")){
					//Direct transfer between 
					Product directtransferproductout = inventoryProductDAO.getDirectTransferBetweenDate(catalogueid,fromDate,location_filter,toDate);
					
					//Request Transfer between
					Product requesttransferproductout = inventoryProductDAO.getRequestTransferBetweenDate(catalogueid,fromDate,location_filter,toDate);
					
					directtransferqtyout = directtransferproductout.getTotalqty();
					requesttransferqtyout = requesttransferproductout.getTotalqty();
					directtransferqtyoutvalue = directtransferproductout.getTotal_amount();
					requesttransferqtyoutvalue = requesttransferproductout.getTotal_amount();
					
					//Return to store between
					Product returntransferproductin = inventoryProductDAO.getReturnTransferBetweenDateOut(catalogueid,fromDate,location_filter,toDate);
					returntransferqtyout = returntransferproductin.getTotalqty();
					returntransferqtyoutvalue = returntransferproductin.getTotal_amount();
					
					directsaleprice = directtransferproductout.getSalepricetotal();
					returnsaleprice = returntransferproductin.getSalepricetotal();
					requestsaleprice = requesttransferproductout.getSalepricetotal();
				}
				
				//Patient Sale between Fromdate and Todate
				Product salepatient = inventoryProductDAO.getPatientSaleBetweenDate(catalogueid,fromDate,toDate,location_filter);
				
				//Return to supplier in between fromdate and todate
				Product returntosuppplier = inventoryProductDAO.getReturnToSupplierBetwwenDate(catalogueid,fromDate,toDate,location_filter); 
				
				//Consume to user or patient between Fromdate and Todate
				Product consume = inventoryProductDAO.getConsumeBetweenDate(catalogueid,fromDate,toDate,location_filter);
				
				Product adjustmentbetweenOut = inventoryProductDAO.getAdjustmentDatabetweenOut(catalogueid,fromDate,location_filter,toDate);
				
				double qtyout = salepatient.getTotalqty()+returntosuppplier.getTotalqty()
							+consume.getTotalqty() + directtransferqtyout +requesttransferqtyout 
							+returntransferqtyout + adjustmentbetweenOut.getTotalqty();
				
				//Stock value =  Patient sale unit price + Return to Supplier purchase price + Consume purchase price
				double stockvalue =  salepatient.getTotal_amount()+ returntosuppplier.getTotal_amount()
										+consume.getTotal_amount() +directtransferqtyoutvalue +requesttransferqtyoutvalue 
										+returntransferqtyoutvalue + adjustmentbetweenOut.getTotal_amount();
				
				//sale price total 
				double saleprice = salepatient.getSalepricetotal()+ returntosuppplier.getSalepricetotal()
										+consume.getSalepricetotal() +directsaleprice +returnsaleprice 
										+requestsaleprice + adjustmentbetweenOut.getSalepricetotal();
				
				//Closing - opening + qtyin - qtyout
				double closingstock = openingstock +qtyin - qtyout;
				
				//double closingvalue = unitprice * closingstock;
				double closingvalue = opeingstockvalue + qtyinvalue - stockvalue;
				double unknownqty = 0;
				double unknownvalue =0;
				if((openingstock +qtyin)<qtyout){
					/*closingstock =0;
					closingvalue =0;*/
					unknownqty = qtyout - (openingstock +qtyin);
					unknownvalue = stockvalue - (opeingstockvalue + qtyinvalue);
					closingstock = Math.abs(unknownqty);
					closingvalue = Math.abs(unknownvalue);
				}
				double closningvalue = (openingvalue + qtyinvalue + Math.abs(unknownvalue)) - stockvalue;
				closningvalue = Math.abs(closningvalue);
				//double unitprice = getUnitPriceFromCatalogueid(catalogueid);
				
			 	String sql1="   ";
			 	StringBuffer buffer2 = new StringBuffer();
			 	buffer2.append("insert into inventory_catalogue_log (lastmodfied, date, location, opening_stock,");
			 	buffer2.append("opeing_value, qty_in, qty_in_value, qty_out, qty_out_value, stock_value, Unknown_qty,");
			 	buffer2.append("closing_stock, closing_value, catalogueid,unknown_value,opening,closing,");
			 	buffer2.append("grn_qty,grn_value,return_phar_qty,return_phar_value,return_trans_qty_in,return_trans_value_in,");
			 	buffer2.append("direct_trans_qty_in,direct_trans_value_in,request_trans_qty_in,request_trans_value_in,");
			 	buffer2.append("adjust_qty_in,adjust_value_in,direct_trans_qty_out,direct_trans_value_out,");
			 	buffer2.append("request_trans_qty_out,request_trans_value_out,return_trans_qty_out,return_trans_value_out,");
			 	buffer2.append("direct_trans_svalue_out, request_trans_svalue_out, return_trans_svalue_out, phar_qty_out, ");
			 	buffer2.append("phar_value_out, phar_svalue_out, return_supp_qty, return_supp_value, return_supp_svalue, consume_qty, ");
			 	buffer2.append("consume_value, consume_svalue, adjust_out_qty, adjust_out_value, adjust_out_svalue)");
			 	buffer2.append("values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,");
			 	buffer2.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			 	PreparedStatement ps1=connection.prepareStatement(buffer2.toString());
			 	ps1.setString(1, fromDate);
			 	ps1.setString(2, toDate);
			 	ps1.setString(3, location_filter);
			 	ps1.setString(4, ""+openingstock);
			 	ps1.setString(5, DateTimeUtils.changeFormat(Math.round(opeingstockvalue * 100.0)/100.0));
			 	ps1.setString(6, ""+qtyin);
			 	ps1.setString(7, DateTimeUtils.changeFormat(qtyinvalue));
			 	ps1.setString(8, ""+qtyout);
			 	ps1.setString(9, DateTimeUtils.changeFormat(Math.round(stockvalue * 100.0)/100.0));
			 	ps1.setString(10, DateTimeUtils.changeFormat(Math.round(saleprice * 100.0)/100.0));
			 	ps1.setString(11, ""+unknownqty);
			 	ps1.setString(12, ""+closingstock);
			 	ps1.setString(13, DateTimeUtils.changeFormat(Math.round(closingvalue * 100.0)/100.0));
			 	ps1.setString(14, rs.getString(1));
			 	ps1.setString(15, DateTimeUtils.changeFormat(Math.round(unknownvalue * 100.0)/100.0));
			 	ps1.setString(16, DateTimeUtils.changeFormat(Math.round(openingvalue * 100.0)/100.0));
			 	ps1.setString(17, DateTimeUtils.changeFormat(Math.round(closningvalue * 100.0)/100.0));
			 	
			 	ps1.setString(18, ""+purproduct.getTotalqty());
			 	ps1.setString(19, ""+purproduct.getTotal_amount());
			 	ps1.setString(20, ""+returnpatientqty.getTotalqty());
			 	ps1.setString(21, ""+returnpatientqty.getTotal_amount());
			 	ps1.setString(22, ""+retruntransferqtyin);
			 	ps1.setString(23, ""+retruntransferqtyinvalue);
			 	ps1.setString(24, ""+directtransferqtyin);
			 	ps1.setString(25, ""+directtransferqtyinvalue);
			 	ps1.setString(26, ""+requesttransferqtyin);
			 	ps1.setString(27, ""+requesttransferqtyinvalue);
			 	ps1.setString(28, ""+adjustmentbetweenin.getTotalqty());
			 	ps1.setString(29, ""+adjustmentbetweenin.getTotal_amount());
			 	
			 	ps1.setString(30, ""+directtransferqtyout);
			 	ps1.setString(31, ""+directtransferqtyoutvalue);
			 	ps1.setString(32, ""+requesttransferqtyout);
			 	ps1.setString(33, ""+requesttransferqtyoutvalue);
			 	ps1.setString(34, ""+returntransferqtyout);
			 	ps1.setString(35, ""+returntransferqtyoutvalue);
			 	ps1.setString(36, ""+directsaleprice);
			 	ps1.setString(37, ""+requestsaleprice);
			 	ps1.setString(38, ""+returnsaleprice);
			 	ps1.setString(39, ""+salepatient.getTotalqty());
			 	ps1.setString(40, ""+salepatient.getTotal_amount());
			 	ps1.setString(41, ""+salepatient.getSalepricetotal());
			 	ps1.setString(42, ""+returntosuppplier.getTotalqty());
			 	ps1.setString(43, ""+returntosuppplier.getTotal_amount());
			 	ps1.setString(44, ""+returntosuppplier.getSalepricetotal());
			 	ps1.setString(45, ""+consume.getTotalqty());
			 	ps1.setString(46, ""+consume.getTotal_amount());
			 	ps1.setString(47, ""+consume.getSalepricetotal());
			 	ps1.setString(48, ""+adjustmentbetweenOut.getTotalqty());
			 	ps1.setString(49, ""+adjustmentbetweenOut.getTotal_amount());
			 	ps1.setString(50, ""+adjustmentbetweenOut.getSalepricetotal());
			 	
			 	//int res= ps1.executeUpdate();
				/*}*/
			}
			
			System.out.println("ok done");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void updateUnknownQtyValue() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			
			  
					StringBuffer buffer = new StringBuffer();
					//buffer.append("select id, date, location, catalogueid, unknown_value from inventory_catalogue_log where unknown_qty>0 and unknown_value=0 ");
					buffer.append("select id, date, location, catalogueid, unknown_value from inventory_catalogue_log where unknown_qty>0 and date<'2018-08-02' and catalogueid=1936 group by catalogueid order by date ");
					PreparedStatement ps=connection.prepareStatement(buffer.toString());
					ResultSet rs =ps.executeQuery();
					/*System.out.println("previousdate:"+previousdate+", currentdate:"+dt+", location:"+location_filter)*/;
					while(rs.next()){
						long differ = DateTimeUtils.getDifferenceOfTwoDateDBFormat(rs.getString(2), "2018-08-02");
						int k = (int)differ;
						for (int i=0; i <= k; i++) {
							 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
							   Date d=sdf1.parse(rs.getString(2));
							   Calendar cal = Calendar.getInstance();
							   cal.setTime(d);
							   cal.add(Calendar.DATE, i);
							   String dt=sdf1.format(cal.getTime());
							   
							   SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
							   Date d2=sdf1.parse(dt);
							   Calendar cal1 = Calendar.getInstance();
							   cal1.setTime(d2);
							   cal1.add(Calendar.DATE, -1);
							   String previousdate=sdf2.format(cal1.getTime());
							   
							   
							   
							   
							   
							   
							   
							   
							   
							   
							   
							   
							   
							   
							   
							   
							   
						   	   System.out.println("previousdate:"+previousdate+", currentdate:"+dt+"catalogueid="+rs.getString(4)+"");
							   String location_filter = rs.getString(3);
							 
								Product product = new Product();
								String catalogueid = rs.getString(4);
								
								/*boolean isapplied = checkInventoryCatalogueStatus(fromDate,catalogueid,location_filter);
								if(!isapplied){*/
								
									Product product2 = inventoryProductDAO.getYesterdayClosingData(previousdate,catalogueid,location_filter);
									double previous_closingvalue = product2.getTotalclosingvalue();
									
									Product product3 = getCurentStockDataLog(dt,catalogueid,location_filter);
									
									
									//double closingvalue = unitprice * closingstock;
									double closing = product3.getOpeningstockvalue() + product3.getTotalqtyinvalue() - product3.getTotalstockvalue();
									double unknownvlue =0;
									if(closing<0){
										unknownvlue = Math.abs(unknownvlue);
										closing =0;
									}
									
									
									//double closing = previous_closingvalue + product3.getTotalqtyinvalue() + unknownvlue -  product3.getTotalstockvalue();
									//double unitprice = getUnitPriceFromCatalogueid(catalogueid);
									
								
								 	String sql1="update inventory_catalogue_log set unknown_value=?,closing_value=?,opeing_value=? where id='"+product3.getId()+"'";
								 	PreparedStatement ps1=connection.prepareStatement(sql1);
								 	ps1.setString(1, DateTimeUtils.changeFormat(Math.abs(unknownvlue)));
								 	ps1.setString(2, DateTimeUtils.changeFormat(Math.abs(closing)));
								 	ps1.setString(3, DateTimeUtils.changeFormat(Math.abs(previous_closingvalue)));
								 	int res= ps1.executeUpdate();
						}
					  /*}*/
					}
				
			
			
			System.out.println("ok done");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

			
		
	}
	
	private Product getCurentStockDataLog(String previousdate, String catalogueid, String location_filter) {
		Product product = new Product();
		try {
			String sql ="select opeing_value, qty_in_value,qty_out_value,id from inventory_catalogue_log where date='"+previousdate+"' and location='"+location_filter+"' and catalogueid='"+catalogueid+"' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			product.setOpeningstockvalue(0);
			product.setTotalqtyinvalue(0);
			product.setTotalstockvalue(0);
			if (rs.next()) {
				product.setOpeningstockvalue(rs.getDouble(1));
				product.setTotalqtyinvalue(rs.getDouble(2));
				product.setTotalstockvalue(rs.getDouble(3));
				product.setId(rs.getInt(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	String removeBreaks(String str){
		str=DateTimeUtils.isNull(str);
		if (str.matches("^[a-zA-Z]*$")) {
	        str="";
	    }
		
		return str;
	}
	
	
	
	private void updateBalgopalIndentLocation() {
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost/chraibgh",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			UserProfileDAO  userProfileDAO = new JDBCUserProfileDAO(connection);
			String sql ="select discription,userid from apm_user where indentlocations=''";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("Start");
			while (rs.next()) {
				String indentlocation =userProfileDAO.getLocationFromDiciplineID(rs.getString(1));
				String query = "update apm_user set indentlocations='"+indentlocation+"' where userid='"+rs.getString(2)+"'";
				PreparedStatement preparedStatement2 = connection.prepareStatement(query);
				int res = preparedStatement2.executeUpdate();
			}
			System.out.println("done");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private void updateCatalogueInLog() {
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			StringBuffer buffer = new StringBuffer();
			buffer.append("select product_stock_log.id,productid,inventory_product.catalogueid from product_stock_log ");
			buffer.append("inner join inventory_product on inventory_product.id = product_stock_log.productid ");
			buffer.append("where product_stock_log.catalogueid is null ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				String sql1="update product_stock_log set catalogueid='"+rs.getInt(3)+"' where id='"+rs.getInt(1)+"'  ";
				PreparedStatement ps1=connection.prepareStatement(sql1);
				int res =ps1.executeUpdate();
			}
			System.out.println("ok done of procurement");
			
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private void updateGlobalIDBalgopal() {
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34/chraibgh",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			connection=DriverManager.getConnection("jdbc:mysql://103.252.170.227/nelson",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			StringBuffer buffer = new StringBuffer();
			buffer.append("select id from inventory_product where global_prodid=0");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				String sql1="update inventory_product set global_prodid='"+rs.getInt(1)+"' where id='"+rs.getInt(1)+"' and global_prodid=0 ";
				PreparedStatement ps1=connection.prepareStatement(sql1);
				int res =ps1.executeUpdate();
			}
			System.out.println("ok done of procurement");
			
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void updateGlobleProductId() {
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//connection=DriverManager.getConnection("jdbc:mysql://103.252.170.227/nelson",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			connection=DriverManager.getConnection("jdbc:mysql://58.84.56.17/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			StringBuffer buffer = new StringBuffer();
			buffer.append("select prodid from inventory_procurement ");
			buffer.append("inner join inventory_product on inventory_product.id = inventory_procurement.prodid ");
			buffer.append("where global_prodid=0 ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				String sql1="update inventory_product set global_prodid='"+rs.getInt(1)+"' where id='"+rs.getInt(1)+"' ";
				PreparedStatement ps1=connection.prepareStatement(sql1);
				int res =ps1.executeUpdate();
			}
			System.out.println("ok done of procurement");
			
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void adjustexpiryproduct() {
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://localhost/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://114.143.231.10:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://103.252.170.227:3306/nelson",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			System.out.println("ok");
			StringBuffer buffer = new StringBuffer();
			buffer.append("select inventory_product.id,inventory_product.stock ");
			buffer.append("from inventory_product ");
			buffer.append("where inventory_product.expiry_date<'2019-05-25' and inventory_product.expiry_date!='' ");
			buffer.append("and inventory_product.stock>0 and location=33 ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			int i=0;
			while(rs.next()){
				  String id= rs.getString(1);
				  String adj_type= "4";
				  String pre_stock= ""+rs.getInt(2);
				  String change_stock= "0";
				  String comment = "already expired and return to supplier by mannually";
				  String userid= "sarangwadatkar";
				  String adj_change_stock_indi = ""+rs.getInt(2);
				  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				  Calendar cal = Calendar.getInstance();
				  String date = dateFormat.format(cal.getTime());
				  
				  int stock = Integer.parseInt(pre_stock);
				  int qty = Integer.parseInt(change_stock);
				  int changerqty=Integer.parseInt(adj_change_stock_indi);
				  
				  int qtyinout=1;
					if(adj_type.equals("2")){
						qtyinout =0;
					}
				  int res = inventoryProductDAO.updateProductStocks(id,qty);
				  int adjustmentid=0;
				  if(res>0){
					  //adjustmentid = inventoryProductDAO.insertAdjustmentProductData(id,adj_type,stock,qty,changerqty,comment,userid,date);
				  }
				 
				//stock log
				  	Product product=inventoryProductDAO.getProduct(id);
					String datetime = date;
					
					String source ="Product Adjustment";
					if(adjustmentid>0){
						int reslog = inventoryProductDAO.insertIntoProductLog(userid,datetime,product.getLocation(),qtyinout,id,product.getCatalogueid(),source,qty,stock,changerqty,"0","0",0,adjustmentid,0,"0");
						
						String date1 =  date.split(" ")[0];
						boolean checkopningstockexist = pharmacyDAO.checkopeningstockexist(id,date1);
						if(!checkopningstockexist){
							int r = pharmacyDAO.saveOpeningStock(id,date1,stock,"0");
						}
					}
					
					
			}
			System.out.println("ok done");
			
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		}
private void removeGRNPurchaseEntry() {
	Connection connection =null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://localhost/nelson",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://114.143.231.10:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://103.252.170.227:3306/nelson",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		System.out.println("ok");
		String sql="select source, id, qty_in_out, productid,change_qty from product_stock_log where  productid='' ";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs =ps.executeQuery();
		int i=0;
		while(rs.next()){
			String querry="";	
			int res = removeDuplicateDoubleEntry(rs.getInt(2),connection);
				
		}
		System.out.println("ok done");
		
		connection.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		
	}
private int removeDuplicateDoubleEntry(int id, Connection connection2) {
	int res =0;
	try {
		String sql="delete from product_stock_log where id='"+id+"'";
		PreparedStatement preparedStatement = connection2.prepareStatement(sql);
		res = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}
private void updateSupplierIDFromProcurement() {
	Connection connection =null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://localhost/nelson",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://114.143.231.10:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://103.252.170.227:3306/nelson",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/nelson",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		System.out.println("ok");
		String sql="select vendorid,inventory_product.id from inventory_product inner join inventory_procurement on inventory_procurement.prodid = inventory_product.id where supplierid is null";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs =ps.executeQuery();
		int i=0;
		while(rs.next()){
				String sql1="update inventory_product set supplierid='"+rs.getInt(1)+"' where id='"+rs.getInt(2)+"' ";
				PreparedStatement ps1=connection.prepareStatement(sql1);
				int res =ps1.executeUpdate();
				System.out.println(rs.getInt(2));
				i++;
		}
		System.out.println("ok done");
		
		connection.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		
	}
private void wrongEntryEntered() {
	Connection connection = null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://localhost/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		System.out.println("ok");
		long differ = DateTimeUtils.getDifferenceOfTwoDateDBFormat("2019-01-31", "2019-02-05");
		int k = (int)differ;
		/*for (int i=k; i > 0; i--) {*/
		  /* SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		   Date d=sdf1.parse("2019-01-31");
		   Calendar cal = Calendar.getInstance();
		   cal.setTime(d);
		   cal.add(Calendar.DATE, i);
		   String dt=sdf1.format(cal.getTime());
		   
		   SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		   Date d2=sdf1.parse(dt);
		   Calendar cal1 = Calendar.getInstance();
		   cal1.setTime(d2);
		   cal1.add(Calendar.DATE, 1);
		   String nextdate=sdf2.format(cal1.getTime());*/
		   String dt= "2019-01-11";
		   System.out.println("currentdate:"+dt);
		   /*System.out.println("nextdate:"+nextdate+", currentdate:"+dt);*/
		   String nextdate = "2019-01-12";
		   StringBuffer buffer = new StringBuffer();
		   buffer.append("select opening_stock,closing_stock,catalogueid from inventory_catalogue_log where date='"+nextdate+"' and location=33 ");
		   PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		   ResultSet rs = preparedStatement.executeQuery();
		   int iii=0;
		   while (rs.next()) {
			   System.out.println(""+(++iii));
			   String sql ="select opening_stock,closing_stock,catalogueid from inventory_catalogue_log where date='"+dt+"' and location=33 and catalogueid='"+rs.getString(3)+"'";
			   PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
			   ResultSet resultSet = preparedStatement2.executeQuery();
			   if (resultSet.next()) {
				   if(rs.getInt(1)!=resultSet.getInt(2)){
					   System.out.println("nextdate:"+nextdate+", currentdate:"+dt);
					   System.out.println("Wrong Entry:"+rs.getInt(3));
					   changeWrongEntry(nextdate,dt,rs.getInt(3),connection);
				   }
			   }else{
				   System.out.println("nextdate:"+nextdate+", currentdate:"+dt);
				   System.out.println("Missing entry"+rs.getInt(3));
				   changeWrongEntry(nextdate,dt,rs.getInt(3),connection);
			   }
		   }
		/*}*/
		System.out.println("ok done");
		connection.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
private void changeWrongEntry(String nextdate, String dt, int int1, Connection connection2) {
	try {
		String location_filter ="33";
		String fromDate =dt;
		String toDate =dt;
		Product product = new Product();
		String catalogueid = ""+int1;
		double opeingstockvalue =0;
		double openingstock=0;
		
		//Qty in-  Purchase qty +  return from patient + retruntransfer  = in fromdate and todate
		
		//Purchase qty =  in fromdate and todate
		Product purproduct = getPuchaseProductDataBetween(catalogueid,fromDate,toDate,location_filter);  
		
		//return from patient =  in fromdate and todate
		Product returnpatientqty = getReturnPatientProductDataBetween(catalogueid,fromDate,toDate,location_filter);
		
		// retruntransfer = in fromdate and todate 
		double retruntransferqtyin =0;
		double retruntransferqtyinvalue=0;
		double directtransferqtyin=0;
		double requesttransferqtyin =0;
		double directtransferqtyinvalue=0;
		double requesttransferqtyinvalue=0;
		if(!location_filter.equals("0")){
			//Return transfer between date 
			Product returntransferproductin = getReturnTransferBetweenDate(catalogueid,fromDate,location_filter,toDate);
			retruntransferqtyin = returntransferproductin.getTotalqty();
			retruntransferqtyinvalue = returntransferproductin.getTotal_amount();
			
			//Direct transfer between 
			Product directtransferproductin = getDirectTransferBetweenDateIn(catalogueid,fromDate,location_filter,toDate);
			
			//Request Transfer between
			Product requesttransferproductin = getRequestTransferBetweenDateIn(catalogueid,fromDate,location_filter,toDate);
			
			directtransferqtyin = directtransferproductin.getTotalqty();
			requesttransferqtyin = requesttransferproductin.getTotalqty();
			directtransferqtyinvalue = directtransferproductin.getTotal_amount();
			requesttransferqtyinvalue = requesttransferproductin.getTotal_amount();
		}
		
		Product adjustmentbetweenin = getAdjustmentDataBetweenIn(catalogueid,fromDate,location_filter,toDate);
		
		double qtyin = purproduct.getTotalqty() + returnpatientqty.getTotalqty() 
					+retruntransferqtyin + directtransferqtyin +requesttransferqtyin + adjustmentbetweenin.getTotalqty() ;
		
		double qtyinvalue = purproduct.getTotal_amount() + returnpatientqty.getTotal_amount() 
					+retruntransferqtyinvalue + directtransferqtyinvalue +requesttransferqtyinvalue + adjustmentbetweenin.getTotal_amount() ;
		
		//Qty out - Patient sale + Return to Supplier + Consume + direct transfer + request return  = in Fromdate and Todate
		
		// retruntransfer = in fromdate and todate 
		double directtransferqtyout =0;
		double directtransferqtyoutvalue=0;
		double requesttransferqtyout =0;
		double requesttransferqtyoutvalue=0;
		
		double returntransferqtyout =0;
		double returntransferqtyoutvalue=0;
		
		double directsaleprice=0;
		double returnsaleprice=0;
		double requestsaleprice=0;
		
		if(!location_filter.equals("0")){
			//Direct transfer between 
			Product directtransferproductout = getDirectTransferBetweenDate(catalogueid,fromDate,location_filter,toDate);
			
			//Request Transfer between
			Product requesttransferproductout = getRequestTransferBetweenDate(catalogueid,fromDate,location_filter,toDate);
			
			directtransferqtyout = directtransferproductout.getTotalqty();
			requesttransferqtyout = requesttransferproductout.getTotalqty();
			directtransferqtyoutvalue = directtransferproductout.getTotal_amount();
			requesttransferqtyoutvalue = requesttransferproductout.getTotal_amount();
			
			//Return to store between
			Product returntransferproductin = getReturnTransferBetweenDateOut(catalogueid,fromDate,location_filter,toDate);
			returntransferqtyout = returntransferproductin.getTotalqty();
			returntransferqtyoutvalue = returntransferproductin.getTotal_amount();
			
			directsaleprice = directtransferproductout.getSalepricetotal();
			returnsaleprice = returntransferproductin.getSalepricetotal();
			requestsaleprice = requesttransferproductout.getSalepricetotal();
		}
		
		//Patient Sale between Fromdate and Todate
		Product salepatient = getPatientSaleBetweenDate(catalogueid,fromDate,toDate,location_filter);
		
		//Return to supplier in between fromdate and todate
		Product returntosuppplier = getReturnToSupplierBetwwenDate(catalogueid,fromDate,toDate,location_filter); 
		
		//Consume to user or patient between Fromdate and Todate
		Product consume = getConsumeBetweenDate(catalogueid,fromDate,toDate,location_filter);
		
		Product adjustmentbetweenOut = getAdjustmentDatabetweenOut(catalogueid,fromDate,location_filter,toDate);
		
		double qtyout = salepatient.getTotalqty()+returntosuppplier.getTotalqty()
					+consume.getTotalqty() + directtransferqtyout +requesttransferqtyout 
					+returntransferqtyout + adjustmentbetweenOut.getTotalqty();
		
		//Stock value =  Patient sale unit price + Return to Supplier purchase price + Consume purchase price
		double stockvalue =  salepatient.getTotal_amount()+ returntosuppplier.getTotal_amount()
								+consume.getTotal_amount() +directtransferqtyoutvalue +requesttransferqtyoutvalue 
								+returntransferqtyoutvalue + adjustmentbetweenOut.getTotal_amount();
		
		//sale price total 
		double saleprice = salepatient.getSalepricetotal()+ returntosuppplier.getSalepricetotal()
								+consume.getSalepricetotal() +directsaleprice +returnsaleprice 
								+requestsaleprice + adjustmentbetweenOut.getSalepricetotal();
		
		
		//opening = closing - qtyin + qtyout
		
		Product product2 = getNextOpeingData(nextdate,catalogueid,location_filter);
		
		double closingstock =product2.getTotalclosingstock();
		openingstock = closingstock -qtyin + qtyout;
		
		//double closingvalue = unitprice * closingstock;
		double closingvalue =  product2.getTotalclosingvalue();
		opeingstockvalue = closingvalue - qtyinvalue + stockvalue;
		
		if(openingstock<0){
			openingstock=0;
		}
		
		if(opeingstockvalue<0){
			opeingstockvalue=0;
		}
		
		double unknownqty = 0;
		if((openingstock +qtyin)<qtyout){
			closingstock =0;
			closingvalue =0;
			unknownqty = qtyout - (openingstock +qtyin);
		}
		
		product.setUnknownqty(unknownqty);
		product.setOpeningstock(""+openingstock);
		product.setOpeningstockvalue(Math.round(opeingstockvalue * 100.0)/100.0);
		product.setPurchaseqty(qtyin);
		product.setSale(""+qtyout);
		product.setSalevalue(Math.round(stockvalue * 100.0)/100.0);
		product.setClosingstock(""+closingstock);
		product.setSv(DateTimeUtils.changeFormat(Math.round(closingvalue * 100.0)/100.0));
		product.setQtyinvalue(DateTimeUtils.changeFormat(qtyinvalue));
		product.setSale_price(DateTimeUtils.changeFormat(Math.round(saleprice * 100.0)/100.0));
		boolean flagcheck = ispresentdata(dt,location_filter,int1);
	 	if(!flagcheck){
	 		String sql1="insert into inventory_catalogue_log (lastmodfied, date, location, opening_stock, opeing_value, qty_in, qty_in_value, qty_out, qty_out_value, stock_value, Unknown_qty, closing_stock, closing_value, catalogueid) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		 	PreparedStatement ps1=connection2.prepareStatement(sql1);
		 	ps1.setString(1, dt);
		 	ps1.setString(2, dt);
		 	ps1.setString(3, location_filter);
		 	ps1.setString(4, ""+openingstock);
		 	ps1.setString(5, DateTimeUtils.changeFormat(Math.round(opeingstockvalue * 100.0)/100.0));
		 	ps1.setString(6, ""+qtyin);
		 	ps1.setString(7, DateTimeUtils.changeFormat(qtyinvalue));
		 	ps1.setString(8, ""+qtyout);
		 	ps1.setString(9, DateTimeUtils.changeFormat(Math.round(stockvalue * 100.0)/100.0));
		 	ps1.setString(10, DateTimeUtils.changeFormat(Math.round(saleprice * 100.0)/100.0));
		 	ps1.setString(11, ""+unknownqty);
		 	ps1.setString(12, ""+closingstock);
		 	ps1.setString(13, DateTimeUtils.changeFormat(Math.round(closingvalue * 100.0)/100.0));
		 	ps1.setString(14, ""+int1);
		 	int res= ps1.executeUpdate();
	 	}else{
	 		String sql="update inventory_catalogue_log set opening_stock=?,qty_in=?,qty_in_value=?,qty_out=?,qty_out_value=?,stock_value=?,Unknown_qty=?,closing_stock=?,closing_value=? where catalogueid='"+int1+"' and location='"+location_filter+"' and date='"+dt+"'";
	 		PreparedStatement preparedStatement = connection2.prepareStatement(sql);
	 		int res = preparedStatement.executeUpdate();
	 	}
		System.out.println("done");
	
	} catch (Exception e) {
		e.printStackTrace();
	}
}
private boolean ispresentdata(String dt, String location_filter, int int1) {
	boolean flag = false;
	try {
		String sql ="select * from inventory_catalogue_log where location='"+location_filter+"' and catalogueid='"+int1+"' and date='"+dt+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			flag = true;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return flag;
}
private void missingfielddata() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			long differ = DateTimeUtils.getDifferenceOfTwoDateDBFormat("2019-01-01", "2019-02-05");
			int k = (int)differ;
			for (int i=k; i > 0; i--) {
			   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			   Date d=sdf1.parse("2019-01-01");
			   Calendar cal = Calendar.getInstance();
			   cal.setTime(d);
			   cal.add(Calendar.DATE, i);
			   String dt=sdf1.format(cal.getTime());
			   System.out.println("currentdate:"+dt);
			   StringBuffer buffer = new StringBuffer();
			   buffer.append("select count(*) from inventory_catalogue_log where date='"+dt+"' and location=33 and catalogueid in (");
			   buffer.append("2937,2216,4558,3826,5006,5007,5008,1182,1951,3887,4136,3935,3217,5129,1566,3964,1567,1460,1451,4493,1242,1243,1244,1245,1246,2298,1196,4561,2887,3139,2022,4572,2522,1896,1472,1473,1474,2844,4399,2248,2249,2250,3810,4347,1200,1201,2316,5021,1803,3537,4764,1068,2054,2055,2927,2928,1188,1189,4834,4457,3582,3581,4969,1431,1739,2087,2413,4883,2838,2836,2837,2049,2019,4932,2102,3459,4110,2993,3960,2123,2841,1902,1901,3158,1471,2896,2895,1069,3907,2526,2434,2577,2544,2870,4194,3784,3540,4763,4181,2978,1454,1463,1711,1453,1734,3134,3133,4827,5001,2269,4469,4470,1071,1070,2752,4818,4420,2246,2312,1638,3361,3689,3985,3215,3216,3212,3673,3213,3674,2929,3313,3371,4171,2368,2073,1970,4686,2133,2933,4012,2783,3561,5084,5085,5083,1072,1073,1074,1829,1839,1838,4898,4912,4549,2979,2980,4946,4896,2176,4970,2325,2195,2196,4737,1464,4882,2888,1538,1539,1903,2539,3493,4668,2171,2237,2218,3885,3886,1425,1426,1876,2957,2958,3880,1844,4511,2563,4348,2605,4791,2155,2154,1911,3503,4525,4514,3378,5047,2538,4219,2561,2562,3813,980,2256,");
			   buffer.append("1893,1894,4913,4756,1091,1092,1093,1868,4446,1954,4702,2825,1523,2936,2421,2470,4535,2471,1194,4073,1745,3825,4635,5067,4876,3556,3990,3862,1906,1905,4730,1558,1846,1845,2212,3121,2699,4338,2839,3726,3011,4243,4396,1744,2282,2890,4637,2891,4831,4343,4047,3522,1475,5079,2112,4957,3756,3569,4534,3755,2277,4074,1516,1517,2930,1295,4403,4404,4405,4832,1856,2056,1339,4390,2828,2827,2120,3066,3067,2713,4512,2625,4070,5009,2153,1281,1075,1076,3048,4100,4101,1606,1178,4575,1639,4644,1465,1456,3458,2789,3629,5015,4786,4729,1722,2646,1935,2645,1936,1205,3009,3750,3819,3820,1965,1964,3824,2222,1963,5022,3986,3987,4492,3853,1670,4010,5130,4757,4260,4259,3815,3816,2970,2441,1102,1103,3369,3797,2261,1532,1535,1533,1534,2722,2723,3912,2632,2631,4800,1461,1462,4518,2403,2404,2399,2401,2402,2400,2398,2342,1202,3555,2241,4391,3359,2295,1938,2931,2943,5065,3857,4937,4210,4352,1284,1283,5051,1285,4358,1799,1800,1801,1802,4361,4359,4360,1433,1432,4728,2549,3385,3324,3325,3326,3327,3328,2127,3938,4122,2026,1929,1527,1526,2158,2159");
			   buffer.append(",2131,2132,3618,2058,2059,2057,3065,3620,1556,1554,3496,3137,3130,2757,4402,2012,3152,2382,2266,1673,4108,4544,4545,3683,2962,2965,3619,2860,2859,1691,1692,3584,3383,1255,3631,1888,2013,2381,2010,2840,1077,1541,1542,2457,2456,3639,3640,4955,3856,4872,3570,4893,3495,2177,4733,4734,4552,4991,1908,5074,5075,4458,4449,1698,1740,3049,3148,3147,1824,1825,2600,2519,2518,3549,5080,1378,4095,4667,3902,4476,1011,1078,3821,3822,4497,4498,3890,3891,3894,5131,3708,3928,3829,4151,4407,4408,3275,3817,3818,4923,3709,3710,5142,2446,4817,1166,1165,1164,3690,1167,4188,3016,3017,1573,1574,3479,3781,2091,3190,976,1041,1009,1000,1241,1172,1012,5023,4962,1301,2361,2732,1923,3006,4291,4665,3314,4191,2944,3013,1323,1324,4200,4568,2808,4577,4578,3873,1294,1054,1055,4639,4934,4933,1528,1529,1530,4112,3733,2117,2463,2138,3536,3360,4760,1519,3551,3003,3002,2335,2950,2939,3274,4297,4298,4299,2710,2708,2709,2707,2711,3501,2790,4947,4948,2831,3245,4725,1564,3664,2020,4930,1079,2543,1080,1082,1081,3788,1270,1595,1926,3860,3945,3731,3365,1571");
			   buffer.append(",2392,1572,1864,1865,2243,2244,1292,1291,1290,1289,2995,1031,1032,1033,1034,1035,4920,3823,1817,4020,3538,1104,2834,4766,4337,1325,4528,4289,3753,4953,1083,2129,4727,3762,1147,1659,1769,2767,4847,1565,2560,2139,3627,2974,3481,3482,3961,4351,1884,1892,3900,1641,2097,5011,2152,4897,1575,1576,2990,3500,4170,2268,2766,3008,3007,2124,1914,1912,1913,3965,1832,1833,3671,4355,1508,4451,2125,3461,2688,2706,2545,5002,2430,2431,2432,4975,1277,1256,1257,1258,1259,1261,1262,1260,1263,1264,1265,1266,1267,1268,988,989,5098,1934,3638,982,2445,3630,2313,3851,2314,4336,1842,1700,1701,1702,2251,2252,1148,4998,2703,2704,2702,4048,2318,997,996,4927,4928,3933,3934,1830,1831,4114,4115,2475,1328,1329,4705,4704,1480,1436,1481,4295,1445,3666,1693,5042,5045,4706,1961,1561,1549,4806,4807,3980,957,959,958,4105,4994,5036,5037,4859,4858,2362,2336,1203,2416,3783,2705,2410,4253,2327,3999,1448,4692,1458,1467,4951,1444,4741,1476,1883,1058,1059,3896,3895,2140,1060,2771,3249,3250,2843,2194,2192,1449,1976,4654,3908,3930,3909,4192,4004,2737,2738,1");
			   buffer.append("105,3184,1854,1855,4570,2497,4276,1379,1851,4659,1852,1861,2496,2245,2477,4954,3248,4076,4731,3131,2383,2384,1957,1956,3852,1958,3334,2807,2411,2374,2375,3502,1468,3668,3667,2780,972,4944,2963,2791,1273,4656,1275,1594,3268,3269,4421,2716,2717,2934,2935,4911,4628,3855,3796,1878,4794,4795,1875,1874,2150,4833,4830,4829,5030,1106,1889,2558,2023,4185,3812,4571,1866,5034,4935,4986,4113,4892,2691,3125,3126,3124,3127,3143,3144,3142,3145,1962,3903,2236,1871,1872,4186,4642,3947,4468,1968,4121,4195,3460,3462,3628,4643,2820,2821,2107,4848,1312,1307,1308,1306,1309,1310,1311,1313,1314,1315,4559,2386,2571,2575,3942,2494,2493,4888,2156,1886,1879,1880,4079,3512,3510,4952,3511,2116,3665,2521,2320,2321,3923,4198,4242,3625,3626,5100,1424,1427,1969,1672,1779,4543,2531,2532,4383,2344,2276,2788,4959,5026,4495,1820,1819,1818,1806,1805,3905,3906,3515,5005,4158,2274,2308,3198,3200,3197,3199,4787,4341,4345,962,963,964,960,961,2714,2988,3849,3948,330,5031,4106,4392,4393,2360,2727,2508,2509,4349,4350,3858,3276,2281,4732,2985,2121,2122,37");
			   buffer.append("39,4723,1578,1579,3624,3583,4626,4627,2585,3559,2072,1477,2071,1738,1450,3846,1708,1707,1705,1706,1703,1704,1614,1615,3611,2135,2136,2994,3679,2100,2879,2835,4547,1713,2014,1714,1749,1750,1715,1716,2185,4846,2876,2877,2289,4821,2290,2291,4172,5025,2809,2775,2343,2389,4090,3541,2178,1736,5053,4940,1212,1213,1214,3748,1679,1682,1685,1681,1768,1762,1765,1675,1676,1773,1663,1777,1666,1680,1684,1664,1776,1759,1760,2769,4576,1057,4447,5070,5071,1293,1094,1272,5000,1271,4662,4664,4503,4207,2428,2425,1807,2498,1320,4842,2043,2892,2181,5010,998,999,2239,3910,4097,4224,2610,4738,4736,2592,2450,2092,3844,2964,368,1171,3580,3560,1170,2525,1721,3899,3949,2952,2953,2954,2955,1916,1917,1918,1919,2700,4353,2701,2506,2505,3019,3021,4849,3499,1947,4799,2021,1149,1150,3740,3741,2908,3468,3866,3864,3867,3566,3567,3865,3568,3564,3466,3565,3467,3465,4929,978,2412,5077,5049,2884,4189,1755,4254,1683,1661,1766,2975,4104,4050,4174,4881,1227,1228,1232,1229,1230,1231,3963,977,2173,2415,4241,2174,2175,2813,2193,2810,2414,2182,2814,2815,2");
			   buffer.append("448,2377,2581,1826,3474,3791,2262,4148,4149,1945,2627,1276,1084,4450,2897,4193,1107,2734,2735,4539,1583,2449,3677,3356,2051,2050,4540,1466,1183,1184,1185,1186,1187,5097,3847,1151,1152,1153,1154,1873,3959,2516,4340,2527,4152,4926,2550,3047,2143,2144,4072,3576,3577,3578,3976,3977,3312,3932,1109,1108,3187,2130,3861,1163,4542,1155,2823,1247,1250,1249,1248,2961,4201,1251,1253,1252,1254,1168,4432,3978,3979,2620,3913,1796,1156,1157,2170,2215,3477,3246,3247,4494,4173,1890,1694,2996,1522,1521,3545,2528,3610,3497,3455,3827,3492,2770,5043,2458,4082,3836,4140,2880,2811,2433,2756,4154,4153,5132,5133,5134,4150,5135,1822,1823,4019,2535,1907,1922,2755,1921,3989,2364,2363,2365,4164,4225,4629,4366,2088,2161,2134,2345,2492,3992,2128,2478,2481,2479,2530,2247,4434,3244,3789,2881,2882,4902,3982,3983,2476,3140,3141,3122,3123,1735,1665,2482,3256,3260,3258,1545,1546,1547,4296,3917,3487,3494,2594,2804,4862,3270,2829,3271,1110,3830,3831,3832,4266,4267,4925,4921,3790,3273,3259,3272,2832,4144,4331,2733,1737,1158,1552,1553,4354,3382,1592,");
			   buffer.append("3918,1161,1288,1131,1162,1159,1160,1132,1133,1887,1173,1174,1175,1176,1177,1442,1443,2440,2739,4556,1452,4943,2495,5004,1930,1677,1757,4197,2333,2334,4416,1457,4965,1134,3208,1699,1435,1434,1925,1169,4471,1224,4179,4722,2118,2906,2905,4184,4557,3746,4974,2940,3749,3845,2447,2242,2394,2395,2396,1569,1568,2025,2271,2270,4691,1593,3687,2044,1636,5035,1927,3534,4931,1660,4663,4092,4093,1510,1511,1420,2037,2595,2145,2468,1859,1860,3180,2146,2784,2819,2534,4945,4819,2319,2513,4049,3516,3517,2103,2697,2197,1813,2548,2816,2369,1881,1882,2725,2726,2267,2265,4163,1959,4894,4640,1136,1135,4160,4159,4541,965,966,967,968,3605,979,3018,981,1095,1096,1097,1098,2593,1669,1668,4466,2263,2264,1590,3785,2972,3787,3786,2973,1089,2275,3177,1974,1975,2040,2041,5052,3535,2208,1973,2547,2618,4439,2619,3780,1429,1430,1428,1380,2322,2460,2459,3574,1584,3575,3901,1582,2418,3173,2439,3336,3337,2272,2254,3129,2514,1577,1111,1112,1113,1015,2623,2622,4968,1137,3634,2204,2205,1138,4532,3179,5078,1139,1994,4286,4287,3672,3051,2533,2754,2523,");
			   buffer.append("2524,1518,2390,2510,2512,2511,3063,3062,1560,1559,2728,1732,1733,3505,1024,1025,2141,5063,2473,1021,1022,1023,1239,1240,3984,1747,1140,1141,1142,1143,2898,1746,4107,4961,4251,1121,1122,1123,1144,1145,1146,1119,1118,1115,1116,1117,2587,1125,1126,1124,2760,2223,2938,2224,4300,1127,1128,1589,2408,2409,4824,1949,2326,3257,2765,4368,3384,4726,1225,4808,3478,2454,3379,2833,3153,4554,3508,1600,1602,2273,4724,4417,3176,4456,4942,3513,4573,3850,3814,2341,3255,2871,1099,5101,4085,2090,1904,1100,2329,2330,2989,3721,1984,2024,2629,2630,5143,2491,4685,1469,1470,1695,2751,2847,2850,2874,1891,4162,3898,3210,1330,4569,4397,2359,4263,1810,1322,4199,2805,2099,1326,4527,4255,3317,4178,1752,1596,1597,3267,1848,1850,1849,2641,2009,1933,1932,3519,2211,2151,2487,2480,2183,1899,4533,2200,2932,3553,2875,2878,3264,3266,3265,2089,1418,2039,3950,2724,4365,1531,2692,3156,4051,4785,4394,2951,1562,2219,2220,2221,2712,2229,2230,3722,3723,2588,2589,4546,1960,2035,2310,2761,3937,2240,4875,4874,1841,5066,1599,2759,1598,3119,2910,2373,3146,4094");
			   buffer.append(",2886,2764,2885,4137,1981,4837,2507,1897,4996,2918,2238,2165,3688,2210,2606,2966,3366,4887,4835,2542,2540,2541,1869,3848,1120,1026,4333,3951,3064,4936,4987,3859,1101,1607,1608,1512,1609,2016,2719,2203,2758,2486,3637,4553,4739,5120,2812,1439,1438,3754,1509,1114,2257,2294,3491,4560,3261,1447,1980,1446,2324,4477,1953,3370,3997,2126,3929,3367,3368,1303,3996,3995,3507,1622,4697,4696,4701,3794,3504,4467,2451,2184,1885,4939,4146,4147,4145,624,785,625,2372,2472,3595,3596,4502,4440,3764,5046,4412,1051,1052,1053,1050,2900,2901,2899,1717,1613,4909,4264,1642,2504,1286,2763,4564,4143,4236,4861,1640,1513,1279,1278,1870,2799,2283,1643,2872,1950,3488,3904,3480,4138,2042,3716,3715,3717,1712,1748,2894,1421,2624,2720,2721,1603,4183,3642,3520,3521,2309,1828,5082,3752,2696,2992,3745,3924,4762,4277,2822,2232,2260,4919,1858,1381,4089,2999,5013,5032,2553,4234,2233,5012,1386,4454,4660,2235,2015,4508,3001,3000,4483,4480,4482,4484,4486,4485,4481,5058,4941,4960,4966,3884,4906,4908,4828,1515,4615,4856,4617,1129,2036,4448,2690,1741,4294,4");
			   buffer.append("474,3838,2207,1742,3841,1743,3463,3464,3635,2517,3039,3585,1730,1731,3252,3586,5081,4853,3747,2110,4513,4472,4836,1697,1459,1696,2736,4325,4326,2861,1601,2046,4491,1877,3509,4997,1440,1441,2824,4788,4406,4452,1604,2889,4993,700,4078,2557,2556,1197,1198,1199,2307,3514,3854,2167,4223,2106,3680,3681,1581,1580,4250,4400,4976,2101,4077,3214,3329,3330,3331,3332,3333,2858,2093,2826,2201,2202,2142,4579,2551,2552,2137,4262,4261,3678,2883,2806,1327,4530,4290,3211,1514,4068,2323,2376,4958,1967,4157,1013,1014,1046,1085,1086,1087,1088,4815,1042,1043,1044,1045,1317,1017,1018,1019,1020,1305,1047,1048,3554,3658,3657,3659,3660,3661,2157,1966,1612,1422,1423,2941,4465,4688,3811,2679,1540,1543,1544,2455,2842,2287,2288,4823,2286,3168,2869,2681,2108,2109,2179,2180,4356,4357,4798,4071,3991,4703,4005,3381,4227,4228,4850,5127,5128,1985,4141,5038,3185,5039,4519,4288,4364,1944,2226,3357,3358,4693,2731,2296,2297,2520,3732,2166,1928,2164,3579,2678,4445,4444,4441,4442,4221,2864,2866,2865,3012,4014,4013,1719,1720,2845,3188,1931,4142,4550,4");
			   buffer.append("551,5136,4744,2198,2199,2490,2572,1090,4066,4067,4401,4499,3711,3712,4758,4735,4099,5024,2075,2076,3490,1204,3489,4567,2698,2444,3563,1437,3946,2328,3355,1570,1924,4825,2715,2718,2830,2340,2339,4362,4363,4272,4273,4274,4275,4339,4857,990,3207,991,992,993,994,4419,995,3539,4080,4069,3782,2206,2387,777,3889,3893,3189,2768,2379,3372,1840,2337,1551,1563,1550,782,3552,786,784,1297,2848,2849,2852,2851,4641,2338,2311,4655,1867,1040,3069,3881,3882,1816,1815,4613,3020,3621,1287,2559,2907,2582,2388,2580,1637,1130,2753,1234,1235,1236,1237,1238,1233,1061,1269,3053,3052,3364,1181,1180,1179,2694,2695,1222,4652,4332,4687,3050,4500,4501,4487,1063,1062,2393,1002,1003,1004,1005,1006,1007,1008,1001,4211,4395,4398,969,970,971,2417,1804,1191,1192,1190,4202,2119,5099,5064,1982,1983,2576,2997,5102,4689,1979,1977,1978,3263,2209,4956,1037,1827,4007,2357,2959,2358,4256,4938,986,985,987,4761,1937,3835,2442,1790,4574,3550,3562,3015,2599,1781,1753,1955,2584,3318,3321,3319,3320,2104,1419,1690,2689,3843,2306,2011,1016,1038,1039,3724,3725,4");
			   buffer.append("531,2893,1709,2873,1710,4885,4884,2047,2048,2355,2356,4809,1900,4566,4864,3839,3840,1537,1536,2391,2856,2855,2854,2853,3347,3155,4096,2515,2949,1455,2947,4088,1195,2984,1555,2762,2225,4205,2798,2960,4213,2452,2052,2053,3178,2914,3157,845,4139,3277,4838,4682,4008,1064,1065,3888,2172,4759,3962,4865,4778,4802,4109,4135,4863,1484,1487,1482,1483,1485,1486,2969,2971,5003,4052,2626,4268,4265,3842,3195,4740,2213,1678,1772,1662,1761,3335,3183,4562,3186,3181,3182,3421,3105,3104,3608,3609,4563,3420,4505,3729,3730,1591,4252,4367,4335,4011,2168,3132,3128,5044,3868,3869,2443,4292,4293,1206,1207,1208,1209,2060,3931,2729,861,1210,1211,1193,4196,1798,1754,3547,3548,2730,1863,3010,3253,3254,4342,1217,1218,1219,1220,1221,1972,3005,3004,1971,2293,2292,1066,2774,2773,3744,3175,3154,1920,3641,2045,4161,1610,1611,983,4431,4435,1049,3941,4438,4436,4190,4479,4478,1915,1843,2607,3014,1385,2105,4083,3498,4507,4506,4102,3767,4443,3167,1010,1056,1557,2529,2462,2604,905,3636,1316,1280,1223,2453,2846,2578,2579,907,4860,3632,3633,4081,1282,");
			   buffer.append("2461,3685,3684,3686,3506,3921,3837,1644,1847,1067,1036,4187,973,974,975,4418,4666,1948,1215,1027,1216,1030,1028,1029,3518,2435,1723,2693,4767,4793,4792,4526,4075,2555,4995,2648,2018,2017,1524,1525,4638,4006,2740,2467,1853,1857,3068,4327,4279,5073,4789,2596,1383,2278,2787,2474,1382,2554,2863,5014,2786,4344,2397,2998,4280,2785,4278,2332,2537,1910,1909,1895,1718,1751,3622,3623,1618,1619,1620,1621,1616,1617,4091,2772,4217,2162,1674,1588,2483,2255,1587,1585,1586,1821,2909,1797,1836,1274,4134,5033,4220,4222,1686,1687,1688,1689,2597,4745,3919,1520,4117,4116,4118,2370,2380,2371,4784,4504,4488,4489,2096,2094,4111,4453,3944,3943,4820,1943,1942,1946,4840,2647,3922,2903,2904,2902,3262,1725,1726,1727,1724,1728,1729,1940,2583,4890,3380,4743,3662,2163,4520,1478,1479,1941,1939,3643,4555,4165,4904,2160,1898,1952,4437,2948,3251,4653,2378,1605,2317,2782)");
			   PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			   ResultSet rs = preparedStatement.executeQuery();
			   while (rs.next()) {
				   System.out.println(rs.getString(1)+",");
			   }
			}
			System.out.println("ok done");
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
private void updateAlreadyDiscount() {
	Connection connection =null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://localhost/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34/kunalhospital",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		
		System.out.println("ok");
		String sql="select charge,quantity,gstper,cgst,sgst,tgstamt,discount_share,id from apm_medicine_charges where invoiceid='208'";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs =ps.executeQuery();
		int i=0;
		while(rs.next()){
			
			Priscription priscription=new Priscription();
			
	    	String sale_price = ""+rs.getDouble(1);
	    	String reqqty = ""+rs.getInt(2);
	    	if(reqqty!=null){
	    		if(reqqty.equals("")){
	    			reqqty="0";
	    		}
	    	}
	    	
	    	double tvat= rs.getDouble(3);
	    	double tot= 0;
	    	try{
	    		tot= rs.getInt(2) * rs.getDouble(1);
	    	}catch (Exception e) {
	    		
	    	}
	    	double temptot=0;
	    	double tempvat =0;
	    	tempvat=tot*10/100;
	    	temptot = tot - tempvat;
	    	double dividevat= 100+tvat;
	    	double gst= temptot*tvat/dividevat;
	    	double half= gst/2;
	    	half = Math.round(half*100.0)/100.0;
	    	gst = Math.round(gst*100.0)/100.0;
	    	
	    	priscription.setTgstamt(DateTimeUtils.changeFormat(gst));
	    	priscription.setGstper(""+rs.getDouble(3));
	    	priscription.setSharediscount(DateTimeUtils.changeFormat(tempvat));
	    	priscription.setCgst(DateTimeUtils.changeFormat(half));
	    	priscription.setSgst(DateTimeUtils.changeFormat(half));
	    	int id = rs.getInt(8);
	    	updateAlreadyDiscountJDBC(priscription,id,connection);
		}
		System.out.println("ok done");
		connection.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
private void updateAlreadyDiscountJDBC(Priscription priscription, int id, Connection connection2) {
	try {
		String sql="update apm_medicine_charges set cgst=?,sgst=?,tgstamt=?,discount_share=? where id='"+id+"'";
		PreparedStatement preparedStatement = connection2.prepareStatement(sql);
		preparedStatement.setString(1, priscription.getCgst());
		preparedStatement.setString(2, priscription.getSgst());
		preparedStatement.setString(3, priscription.getTgstamt());
		preparedStatement.setString(4, priscription.getSharediscount());
		int res = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}
private void updateNextDayStockLog() {
	//When we have opening stock of previous date
	// and need to find out next day stock
	try {
		Class.forName("com.mysql.jdbc.Driver");
		//connection=DriverManager.getConnection("jdbc:mysql://59.97.236.76:3306/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://114.143.231.10:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/welltreat",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		/*System.out.println("ok");*/
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("33");
	 	//arrayList.add("36");
	//-Xms512m -Xmx4g
		long differ = DateTimeUtils.getDifferenceOfTwoDateDBFormat("2019-04-01", "2020-04-10");
		int k = (int)differ;
		for (int i=0; i <= k; i++) {
		   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		   Date d=sdf1.parse("2019-04-04");
		   Calendar cal = Calendar.getInstance();
		   cal.setTime(d);
		   cal.add(Calendar.DATE, i);
		   String dt=sdf1.format(cal.getTime());
		   
		   SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		   Date d2=sdf1.parse(dt);
		   Calendar cal1 = Calendar.getInstance();
		   cal1.setTime(d2);
		   cal1.add(Calendar.DATE, -1);
		   String previousdate=sdf2.format(cal1.getTime());
		   
		   
		   System.out.println("previousdate:"+previousdate+", currentdate:"+dt);
			for (String string : arrayList) {
				String location_filter = string;
				String fromDate = dt;
				String toDate = dt;
				StringBuffer buffer = new StringBuffer();
				buffer.append("select inventory_catalogue.id from inventory_catalogue ");
				buffer.append("inner join inventory_product on inventory_product.catalogueid = inventory_catalogue.id  ");
				buffer.append("where procurement=0 and product_name is not null and inventory_product.location='"+location_filter+"' and inventory_catalogue.lastmodified<='"+dt+"' ");
				//buffer.append("where product_name is not null and inventory_product.location='"+location_filter+"' ");
				//buffer.append("and inventory_catalogue.id in (2522) ");
				buffer.append("group by inventory_catalogue.id order by inventory_catalogue.id  ");
				PreparedStatement ps=connection.prepareStatement(buffer.toString());
				ResultSet rs =ps.executeQuery();
				/*System.out.println("previousdate:"+previousdate+", currentdate:"+dt+", location:"+location_filter)*/;
				while(rs.next()){

					/*Product product = new Product();*/
					String catalogueid = rs.getString(1);
					double opeingstockvalue =0;
					double openingstock=0;
					double openingvalue =0;
					/*boolean isapplied = checkInventoryCatalogueStatus(fromDate,catalogueid,location_filter);
					if(!isapplied){*/
					
						Product product2 = getYesterdayClosingData(previousdate,catalogueid,location_filter);
						opeingstockvalue = product2.getTotalclosingvalue();
						openingstock = product2.getTotalclosingstock();
						openingvalue = product2.getClosing();
						//Qty in-  Purchase qty +  return from patient + retruntransfer  = in fromdate and todate
						
						//Purchase qty =  in fromdate and todate
						Product purproduct = inventoryProductDAO.getPuchaseProductDataBetween(catalogueid,fromDate,toDate,location_filter);  
						
						//return from patient =  in fromdate and todate
						Product returnpatientqty = inventoryProductDAO.getReturnPatientProductDataBetween(catalogueid,fromDate,toDate,location_filter);
						
						// retruntransfer = in fromdate and todate 
						double retruntransferqtyin =0;
						double retruntransferqtyinvalue=0;
						double directtransferqtyin=0;
						double requesttransferqtyin =0;
						double directtransferqtyinvalue=0;
						double requesttransferqtyinvalue=0;
						if(!location_filter.equals("0")){
							//Return transfer between date 
							Product returntransferproductin = inventoryProductDAO.getReturnTransferBetweenDate(catalogueid,fromDate,location_filter,toDate);
							retruntransferqtyin = returntransferproductin.getTotalqty();
							retruntransferqtyinvalue = returntransferproductin.getTotal_amount();
							
							//Direct transfer between 
							Product directtransferproductin = inventoryProductDAO.getDirectTransferBetweenDateIn(catalogueid,fromDate,location_filter,toDate);
							
							//Request Transfer between
							Product requesttransferproductin = inventoryProductDAO.getRequestTransferBetweenDateIn(catalogueid,fromDate,location_filter,toDate);
							
							directtransferqtyin = directtransferproductin.getTotalqty();
							requesttransferqtyin = requesttransferproductin.getTotalqty();
							directtransferqtyinvalue = directtransferproductin.getTotal_amount();
							requesttransferqtyinvalue = requesttransferproductin.getTotal_amount();
						}
						
						Product adjustmentbetweenin = inventoryProductDAO.getAdjustmentDataBetweenIn(catalogueid,fromDate,location_filter,toDate);
						
						double qtyin = purproduct.getTotalqty() + returnpatientqty.getTotalqty() 
									+retruntransferqtyin + directtransferqtyin +requesttransferqtyin + adjustmentbetweenin.getTotalqty() ;
						
						double qtyinvalue = purproduct.getTotal_amount() + returnpatientqty.getTotal_amount() 
									+retruntransferqtyinvalue + directtransferqtyinvalue +requesttransferqtyinvalue + adjustmentbetweenin.getTotal_amount() ;
						
						//Qty out - Patient sale + Return to Supplier + Consume + direct transfer + request return  = in Fromdate and Todate
						
						// retruntransfer = in fromdate and todate 
						double directtransferqtyout =0;
						double directtransferqtyoutvalue=0;
						double requesttransferqtyout =0;
						double requesttransferqtyoutvalue=0;
						double returntransferqtyout =0;
						double returntransferqtyoutvalue=0;
						double directsaleprice=0;
						double returnsaleprice=0;
						double requestsaleprice=0;
						if(!location_filter.equals("0")){
							//Direct transfer between 
							Product directtransferproductout = inventoryProductDAO.getDirectTransferBetweenDate(catalogueid,fromDate,location_filter,toDate);
							
							//Request Transfer between
							Product requesttransferproductout = inventoryProductDAO.getRequestTransferBetweenDate(catalogueid,fromDate,location_filter,toDate);
							
							directtransferqtyout = directtransferproductout.getTotalqty();
							requesttransferqtyout = requesttransferproductout.getTotalqty();
							directtransferqtyoutvalue = directtransferproductout.getTotal_amount();
							requesttransferqtyoutvalue = requesttransferproductout.getTotal_amount();
							
							//Return to store between
							Product returntransferproductin = inventoryProductDAO.getReturnTransferBetweenDateOut(catalogueid,fromDate,location_filter,toDate);
							returntransferqtyout = returntransferproductin.getTotalqty();
							returntransferqtyoutvalue = returntransferproductin.getTotal_amount();
							
							directsaleprice = directtransferproductout.getSalepricetotal();
							returnsaleprice = returntransferproductin.getSalepricetotal();
							requestsaleprice = requesttransferproductout.getSalepricetotal();
						}
						
						//Patient Sale between Fromdate and Todate
						Product salepatient = inventoryProductDAO.getPatientSaleBetweenDate(catalogueid,fromDate,toDate,location_filter);
						
						//Return to supplier in between fromdate and todate
						Product returntosuppplier = inventoryProductDAO.getReturnToSupplierBetwwenDate(catalogueid,fromDate,toDate,location_filter); 
						
						//Consume to user or patient between Fromdate and Todate
						Product consume = inventoryProductDAO.getConsumeBetweenDate(catalogueid,fromDate,toDate,location_filter);
						
						Product adjustmentbetweenOut = inventoryProductDAO.getAdjustmentDatabetweenOut(catalogueid,fromDate,location_filter,toDate);
						
						double qtyout = salepatient.getTotalqty()+returntosuppplier.getTotalqty()
									+consume.getTotalqty() + directtransferqtyout +requesttransferqtyout 
									+returntransferqtyout + adjustmentbetweenOut.getTotalqty();
						
						//Stock value =  Patient sale unit price + Return to Supplier purchase price + Consume purchase price
						double stockvalue =  salepatient.getTotal_amount()+ returntosuppplier.getTotal_amount()
												+consume.getTotal_amount() +directtransferqtyoutvalue +requesttransferqtyoutvalue 
												+returntransferqtyoutvalue + adjustmentbetweenOut.getTotal_amount();
						
						//sale price total 
						double saleprice = salepatient.getSalepricetotal()+ returntosuppplier.getSalepricetotal()
												+consume.getSalepricetotal() +directsaleprice +returnsaleprice 
												+requestsaleprice + adjustmentbetweenOut.getSalepricetotal();
						
						
						
						//Closing - opening + qtyin - qtyout
						double closingstock = openingstock +qtyin - qtyout;
						
						//double closingvalue = unitprice * closingstock;
						/*double closingvalue = opeingstockvalue + qtyinvalue - stockvalue;
						int unknownqty = 0;
						double unknownvalue =0;
						if((openingstock +qtyin)<qtyout){
							closingstock =0;
							closingvalue =0;
							unknownqty = qtyout - (openingstock +qtyin);
							unknownvalue = stockvalue - (opeingstockvalue + qtyinvalue);
						}
						double closningvalue = (openingvalue + qtyinvalue + Math.abs(unknownvalue)) - stockvalue;
						closningvalue = Math.abs(closningvalue);*/
						double closingvalue = opeingstockvalue + qtyinvalue - stockvalue;
						double unknownqty = 0;
						double unknownvalue =0;
						if((openingstock +qtyin)<qtyout){
							/*closingstock =0;
							closingvalue =0;*/
							unknownqty = qtyout - (openingstock +qtyin);
							unknownvalue = stockvalue - (opeingstockvalue + qtyinvalue);
							closingstock = Math.abs(unknownqty);
							closingvalue = Math.abs(unknownvalue);
						}
						double closningvalue = (openingvalue + qtyinvalue + Math.abs(unknownvalue)) - stockvalue;
						closningvalue = Math.abs(closningvalue);
						//double unitprice = getUnitPriceFromCatalogueid(catalogueid);
						
						/*product.setUnknownqty(unknownqty);
						product.setOpeningstock(""+openingstock);
						product.setOpeningstockvalue(Math.round(opeingstockvalue * 100.0)/100.0);
						product.setPurchaseqty(qtyin);
						product.setSale(""+qtyout);
						product.setSalevalue(Math.round(stockvalue * 100.0)/100.0);
						product.setClosingstock(""+closingstock);
						product.setSv(DateTimeUtils.changeFormat(Math.round(closingvalue * 100.0)/100.0));
						product.setQtyinvalue(DateTimeUtils.changeFormat(qtyinvalue));
						product.setSale_price(DateTimeUtils.changeFormat(Math.round(saleprice * 100.0)/100.0));*/
						
						StringBuffer buffer2 = new StringBuffer();
					 	buffer2.append("insert into inventory_catalogue_log (lastmodfied, date, location, opening_stock,");
					 	buffer2.append("opeing_value, qty_in, qty_in_value, qty_out, qty_out_value, stock_value, Unknown_qty,");
					 	buffer2.append("closing_stock, closing_value, catalogueid,unknown_value,opening,closing,");
					 	buffer2.append("grn_qty,grn_value,return_phar_qty,return_phar_value,return_trans_qty_in,return_trans_value_in,");
					 	buffer2.append("direct_trans_qty_in,direct_trans_value_in,request_trans_qty_in,request_trans_value_in,");
					 	buffer2.append("adjust_qty_in,adjust_value_in,direct_trans_qty_out,direct_trans_value_out,");
					 	buffer2.append("request_trans_qty_out,request_trans_value_out,return_trans_qty_out,return_trans_value_out,");
					 	buffer2.append("direct_trans_svalue_out, request_trans_svalue_out, return_trans_svalue_out, phar_qty_out, ");
					 	buffer2.append("phar_value_out, phar_svalue_out, return_supp_qty, return_supp_value, return_supp_svalue, consume_qty, ");
					 	buffer2.append("consume_value, consume_svalue, adjust_out_qty, adjust_out_value, adjust_out_svalue)");
					 	buffer2.append("values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,");
					 	buffer2.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					 	PreparedStatement ps1=connection.prepareStatement(buffer2.toString());
					 	ps1.setString(1, dt);
					 	ps1.setString(2, dt);
					 	ps1.setString(3, location_filter);
					 	ps1.setString(4, ""+openingstock);
					 	ps1.setString(5, DateTimeUtils.changeFormat(Math.round(opeingstockvalue * 100.0)/100.0));
					 	ps1.setString(6, ""+qtyin);
					 	ps1.setString(7, DateTimeUtils.changeFormat(qtyinvalue));
					 	ps1.setString(8, ""+qtyout);
					 	ps1.setString(9, DateTimeUtils.changeFormat(Math.round(stockvalue * 100.0)/100.0));
					 	ps1.setString(10, DateTimeUtils.changeFormat(Math.round(saleprice * 100.0)/100.0));
					 	ps1.setString(11, ""+unknownqty);
					 	ps1.setString(12, ""+closingstock);
					 	ps1.setString(13, DateTimeUtils.changeFormat(Math.round(closingvalue * 100.0)/100.0));
					 	ps1.setString(14, rs.getString(1));
					 	ps1.setString(15, DateTimeUtils.changeFormat(Math.round(unknownvalue * 100.0)/100.0));
					 	ps1.setString(16, DateTimeUtils.changeFormat(Math.round(openingvalue * 100.0)/100.0));
					 	ps1.setString(17, DateTimeUtils.changeFormat(Math.round(closningvalue * 100.0)/100.0));
					 	
					 	ps1.setString(18, ""+purproduct.getTotalqty());
					 	ps1.setString(19, ""+purproduct.getTotal_amount());
					 	ps1.setString(20, ""+returnpatientqty.getTotalqty());
					 	ps1.setString(21, ""+returnpatientqty.getTotal_amount());
					 	ps1.setString(22, ""+retruntransferqtyin);
					 	ps1.setString(23, ""+retruntransferqtyinvalue);
					 	ps1.setString(24, ""+directtransferqtyin);
					 	ps1.setString(25, ""+directtransferqtyinvalue);
					 	ps1.setString(26, ""+requesttransferqtyin);
					 	ps1.setString(27, ""+requesttransferqtyinvalue);
					 	ps1.setString(28, ""+adjustmentbetweenin.getTotalqty());
					 	ps1.setString(29, ""+adjustmentbetweenin.getTotal_amount());
					 	
					 	ps1.setString(30, ""+directtransferqtyout);
					 	ps1.setString(31, ""+directtransferqtyoutvalue);
					 	ps1.setString(32, ""+requesttransferqtyout);
					 	ps1.setString(33, ""+requesttransferqtyoutvalue);
					 	ps1.setString(34, ""+returntransferqtyout);
					 	ps1.setString(35, ""+returntransferqtyoutvalue);
					 	ps1.setString(36, ""+directsaleprice);
					 	ps1.setString(37, ""+requestsaleprice);
					 	ps1.setString(38, ""+returnsaleprice);
					 	ps1.setString(39, ""+salepatient.getTotalqty());
					 	ps1.setString(40, ""+salepatient.getTotal_amount());
					 	ps1.setString(41, ""+salepatient.getSalepricetotal());
					 	ps1.setString(42, ""+returntosuppplier.getTotalqty());
					 	ps1.setString(43, ""+returntosuppplier.getTotal_amount());
					 	ps1.setString(44, ""+returntosuppplier.getSalepricetotal());
					 	ps1.setString(45, ""+consume.getTotalqty());
					 	ps1.setString(46, ""+consume.getTotal_amount());
					 	ps1.setString(47, ""+consume.getSalepricetotal());
					 	ps1.setString(48, ""+adjustmentbetweenOut.getTotalqty());
					 	ps1.setString(49, ""+adjustmentbetweenOut.getTotal_amount());
					 	ps1.setString(50, ""+adjustmentbetweenOut.getSalepricetotal());
					 	
					 	int res = ps1.executeUpdate();
					/*}*/
				}
			}
		}
		
		System.out.println("ok done");
		
	} catch (Exception e) {
		e.printStackTrace();
	}

		
	}
private void pharmacycorrectbill() {
	Connection connection =null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://localhost/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		connection=DriverManager.getConnection("jdbc:mysql://114.143.231.11/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		System.out.println("ok");
		String sql="select  productid, reqqty,clientid, ispharmacy,datetime from apm_medicine_charges_log where id in (4649,4650,4652,4653,4655)";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs =ps.executeQuery();
		int i=0;
		while(rs.next()){
			Product product=inventoryProductDAO.getProduct(rs.getString(1));
			
			Priscription priscription=new Priscription();
			if(rs.getInt(4)>0){
				priscription.setPclientid(rs.getString(3));
			    priscription.setClientId("0");
			}else{
				priscription.setPclientid("0");
			    priscription.setClientId(rs.getString(3));
			}
		    
			String mdicinenameid ="0";
	    	String sale_price = product.getSale_price();
	    	String reqqty = ""+rs.getInt(2);
	    	if(reqqty!=null){
	    		if(reqqty.equals("")){
	    			reqqty="0";
	    		}
	    	}
	    	String date[] = rs.getString(5).split(" ");
	    	priscription.setProduct_id(""+product.getId());
	    	priscription.setMdicinenameid(mdicinenameid);
	    	priscription.setSaleqty(Integer.parseInt(reqqty));
	    	priscription.setReqqty(Integer.parseInt(reqqty));
	    	priscription.setMrp(sale_price);
	    	priscription.setFullname("");
	    	priscription.setClientname("");
	    	priscription.setWhopay("0");
	    	priscription.setDate(date[0]);
	    
	    	if(priscription.getMrp()!=null){
	    		if(priscription.getMrp().equals("")){
	    			priscription.setMrp("0");
	    		}
	    	}else{
	    		priscription.setMrp("0");
	    	}
	    	
	    	double tvat= Double.parseDouble(product.getVat());
	    	double tot= 0;
	    	try{
	    		tot= priscription.getSaleqty() * Double.parseDouble(priscription.getMrp());
	    	}catch (Exception e) {
	    		
	    	}
	    	double temptot=0;
	    	double tempvat =0;
	    	/*if(discounttype.equals("0")){
	    		tempvat=tot*Double.parseDouble(discinper)/100;
	    	}else{
	    		tempvat=tot*Double.parseDouble(discinper)/100;
	    	}*/
	    	//herre add charge %
	    	tempvat=tot*5/100;
	    	temptot = tot - tempvat;
	    	double dividevat= 100+tvat;
	    	double gst= temptot*tvat/dividevat;
	    	double half= gst/2;
	    	half = Math.round(half*100.0)/100.0;
	    	gst = Math.round(gst*100.0)/100.0;
	    	
	    	priscription.setTgstamt(DateTimeUtils.changeFormat(gst));
	    	priscription.setGstper(product.getVat());
	    	priscription.setSharediscount(DateTimeUtils.changeFormat(tempvat));
	    	
	    	priscription.setCgst(DateTimeUtils.changeFormat(half));
	    	priscription.setSgst(DateTimeUtils.changeFormat(half));
	    	try {
	    		if(product.getStock()!=null){
	    			if(product.getStock().equals("")){
	    				product.setStock("0");
	    			}
	    		}else{
	    			product.setStock("0");
	    		}
	    		/*if(!product.getStock().equals("0")){*/
	    			int previousstock = inventoryProductDAO.getPreviousStock(priscription.getProduct_id());
	    			//add userid and billno
	    			int billno = 113320;
	    			String userid="pranitaopd";
	    			int result=pharmacyDAO.addMedicineCharges(priscription,billno);
	    			result=inventoryProductDAO.updateMedicineQty(priscription.getSaleqty(),priscription.getProduct_id(),0);
	    		
	    			//stock log
					String datetime = rs.getString(5);
					int qtyinout=1;
					String source ="Pharmacy Sale";
					int currentstock=inventoryProductDAO.getPreviousStock(priscription.getProduct_id());
					int changeqty=priscription.getSaleqty();
					int reslog = inventoryProductDAO.insertIntoProductLog(userid,datetime,product.getLocation(),qtyinout,priscription.getProduct_id(),product.getCatalogueid(),source,currentstock,previousstock,changeqty,"0","0",billno,0,0,"0");
	    			
					String date2 =  date[0];
					boolean checkopningstockexist = pharmacyDAO.checkopeningstockexist(priscription.getProduct_id(),date2);
					if(!checkopningstockexist){
						int r = pharmacyDAO.saveOpeningStock(priscription.getProduct_id(),date2,previousstock,"0");
					}
					
	    			// reorder level @jitu
	    			//min /max reorder level code by jitu
	    			/*String catalogueid= product.getCatalogueid(); 
	    			Product pmaster= productDAO.getProductCatalogueDetails(catalogueid);
	        	
	    			if(pmaster.getMinorder()!=null){
	    				if(pmaster.getMinorder().equals("")){
	    					pmaster.setMinorder("0");
	    				}
	    			}else{
	    				pmaster.setMinorder("0");
	    			}
	        	
	    			int minorder =Integer.parseInt(pmaster.getMinorder());
	    			int allstock= productDAO.getTotalStockProduct(catalogueid);
	    			if(pmaster.getPack()!=null){
	    				if(pmaster.getPack().equals("")){
	    					pmaster.setPack("1");
	    				}
	    			}else{
	    				pmaster.setPack("1");
	    			}
	    			int pack= Integer.parseInt(pmaster.getPack());
	    			if(pack==0){
	    				if(product.getPack()!=null){
	    					if(!product.getPack().equals("")){
	    						pack = Integer.parseInt(product.getPack());
	    					}else {
	    						pack=1;
	    					}
	    				}else{
	    					pack=1;
	    				}
	    			}
	    			int nowstock= allstock/pack;
	        	
	        	if(nowstock<=minorder){
	        		
	        		
	        		 //add to po que list
	        		product.setDate(date);
	        		//  26-06-2018 error while billing
	        		if(pmaster.getMaxorder()!=null){
	        			if(pmaster.getMaxorder().equals("")){
	        				pmaster.setMaxorder("0");
	        			}
	        		}else{
	        			pmaster.setMaxorder("0");
	        		}
	        		int maxorder=Integer.parseInt(pmaster.getMaxorder());
	        		int orderqty=maxorder- allstock;   
	        		product.setQty(String.valueOf(orderqty));
	        		//add to po que
	    			int res = procurementDAO.saveNewTempPo(product);
	        	}*/
	    		
	    	/*}*/
	    } catch (Exception e) {
    		
		}
			
			
		}
		System.out.println("ok done");
		
		connection.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		
	}
private void deleteDuplicateCatalogueLogData() {
	Connection connection =null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://localhost/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		System.out.println("ok");
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("33");
	 	arrayList.add("36");
		long differ = DateTimeUtils.getDifferenceOfTwoDateDBFormat("2018-06-30", "2018-10-01");
		int k = (int)differ;
		for (int i=k; i > 0; i--) {
		   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		   Date d=sdf1.parse("2018-06-30");
		   Calendar cal = Calendar.getInstance();
		   cal.setTime(d);
		   cal.add(Calendar.DATE, i);
		   String dt=sdf1.format(cal.getTime());
		   for (String string : arrayList) {
				 //String sql="select inventory_catalogue_log.id,catalogueid,date,location from inventory_catalogue_log where date between '2019-01-01' and '2019-01-31'";
				String sql="select inventory_catalogue_log.id,catalogueid,date,location from inventory_catalogue_log where date between '"+dt+"' and '"+dt+"' and location='"+string+"' group by catalogueid having count(*)>1";
				PreparedStatement ps=connection.prepareStatement(sql);
				ResultSet rs =ps.executeQuery();
				int x=0;
				while(rs.next()){
						String query ="select count(*) from inventory_catalogue_log where date between '"+rs.getString(3)+"' and '"+rs.getString(3)+"' and catalogueid='"+rs.getInt(2)+"' and location='"+rs.getInt(4)+"'";
						PreparedStatement preparedStatement = connection.prepareStatement(query);
						ResultSet resultSet = preparedStatement.executeQuery();
						while (resultSet.next()) {
							if(resultSet.getInt(1)>1){
								String sql1="delete from inventory_catalogue_log where date between '"+rs.getString(3)+"' and '"+rs.getString(3)+"' and catalogueid='"+rs.getInt(2)+"' and location='"+rs.getInt(4)+"' and  id!='"+rs.getInt(1)+"'";
								PreparedStatement ps1=connection.prepareStatement(sql1);
								int res =ps1.executeUpdate();
								System.out.println("Deleted not ID:"+rs.getInt(1)+", Date:"+rs.getString(3)+", location:"+rs.getInt(4)+",catId.:"+rs.getInt(2));
							}
							
						}
						System.out.println("I:"+x);
						i++;
						
				}
			}
		}  
		System.out.println("ok done");
		
		connection.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}
private void autoChargeTPCommencingDate() {
	Connection connection =null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://localhost/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		System.out.println("ok");
		String sql="select id,commencing,tpcommencing from apm_invoice_assesments where tpcommencing is null";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs =ps.executeQuery();
		int i=0;
		while(rs.next()){
				String datetime = rs.getString(2) +" "+"07:00:00";
				String sql1="update apm_invoice_assesments set tpcommencing='"+datetime+"' where id='"+rs.getInt(1)+"'";
				PreparedStatement ps1=connection.prepareStatement(sql1);
				int res =ps1.executeUpdate();
				System.out.println(rs.getInt(1));
				i++;
		}
		System.out.println("ok done");
		
		connection.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
private void updateCatalogueStock() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//connection=DriverManager.getConnection("jdbc:mysql://59.97.236.76:3306/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://114.143.231.10:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/welltreat",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			connection=DriverManager.getConnection("jdbc:mysql://59.97.236.76:3306/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			
			ArrayList<String> arrayList = new ArrayList<String>();
			//arrayList.add("32");
				arrayList.add("33");
			 	arrayList.add("36");
			  
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String dt = dateFormat.format(cal.getTime());
				for (String string : arrayList) {
					String location_filter = string;
					String fromDate = dt;
					String toDate = dt;
					StringBuffer buffer = new StringBuffer();
					buffer.append("select inventory_catalogue.id,sum(stock),sum(inventory_product.stock*(inventory_product.purchaseprice/inventory_product.pack)) from inventory_catalogue ");
					buffer.append("inner join inventory_product on inventory_product.catalogueid = inventory_catalogue.id  ");
					buffer.append("where procurement=0 and product_name is not null and inventory_product.location='"+location_filter+"' ");
					buffer.append("group by inventory_catalogue.id ");
					PreparedStatement ps=connection.prepareStatement(buffer.toString());
					ResultSet rs =ps.executeQuery();
					 System.out.println("previousdate:"+fromDate+", currentdate:"+toDate+", location:"+location_filter);
					while(rs.next()){
						Product product = new Product();
						String catalogueid = rs.getString(1);
						double opeingstockvalue =0;
						double openingstock=0;
						
						boolean isapplied = checkInventoryCatalogueStatus(fromDate,catalogueid,location_filter);
						if(!isapplied){
						
						//Qty in-  Purchase qty +  return from patient + retruntransfer  = in fromdate and todate
						
						//Purchase qty =  in fromdate and todate
						Product purproduct = getPuchaseProductDataBetween(catalogueid,fromDate,toDate,location_filter);  
						
						//return from patient =  in fromdate and todate
						Product returnpatientqty = getReturnPatientProductDataBetween(catalogueid,fromDate,toDate,location_filter);
						
						// retruntransfer = in fromdate and todate 
						double retruntransferqtyin =0;
						double retruntransferqtyinvalue=0;
						double directtransferqtyin=0;
						double requesttransferqtyin =0;
						double directtransferqtyinvalue=0;
						double requesttransferqtyinvalue=0;
						if(!location_filter.equals("0")){
							//Return transfer between date 
							Product returntransferproductin = getReturnTransferBetweenDate(catalogueid,fromDate,location_filter,toDate);
							retruntransferqtyin = returntransferproductin.getTotalqty();
							retruntransferqtyinvalue = returntransferproductin.getTotal_amount();
							
							//Direct transfer between 
							Product directtransferproductin = getDirectTransferBetweenDateIn(catalogueid,fromDate,location_filter,toDate);
							
							//Request Transfer between
							Product requesttransferproductin = getRequestTransferBetweenDateIn(catalogueid,fromDate,location_filter,toDate);
							
							directtransferqtyin = directtransferproductin.getTotalqty();
							requesttransferqtyin = requesttransferproductin.getTotalqty();
							directtransferqtyinvalue = directtransferproductin.getTotal_amount();
							requesttransferqtyinvalue = requesttransferproductin.getTotal_amount();
						}
						
						Product adjustmentbetweenin = getAdjustmentDataBetweenIn(catalogueid,fromDate,location_filter,toDate);
						
						double qtyin = purproduct.getTotalqty() + returnpatientqty.getTotalqty() 
									+retruntransferqtyin + directtransferqtyin +requesttransferqtyin + adjustmentbetweenin.getTotalqty() ;
						
						double qtyinvalue = purproduct.getTotal_amount() + returnpatientqty.getTotal_amount() 
									+retruntransferqtyinvalue + directtransferqtyinvalue +requesttransferqtyinvalue + adjustmentbetweenin.getTotal_amount() ;
						
						//Qty out - Patient sale + Return to Supplier + Consume + direct transfer + request return  = in Fromdate and Todate
						
						// retruntransfer = in fromdate and todate 
						double directtransferqtyout =0;
						double directtransferqtyoutvalue=0;
						double requesttransferqtyout =0;
						double requesttransferqtyoutvalue=0;
						
						double returntransferqtyout =0;
						double returntransferqtyoutvalue=0;
						
						double directsaleprice=0;
						double returnsaleprice=0;
						double requestsaleprice=0;
						
						if(!location_filter.equals("0")){
							//Direct transfer between 
							Product directtransferproductout = getDirectTransferBetweenDate(catalogueid,fromDate,location_filter,toDate);
							
							//Request Transfer between
							Product requesttransferproductout = getRequestTransferBetweenDate(catalogueid,fromDate,location_filter,toDate);
							
							directtransferqtyout = directtransferproductout.getTotalqty();
							requesttransferqtyout = requesttransferproductout.getTotalqty();
							directtransferqtyoutvalue = directtransferproductout.getTotal_amount();
							requesttransferqtyoutvalue = requesttransferproductout.getTotal_amount();
							
							//Return to store between
							Product returntransferproductin = getReturnTransferBetweenDateOut(catalogueid,fromDate,location_filter,toDate);
							returntransferqtyout = returntransferproductin.getTotalqty();
							returntransferqtyoutvalue = returntransferproductin.getTotal_amount();
							
							directsaleprice = directtransferproductout.getSalepricetotal();
							returnsaleprice = returntransferproductin.getSalepricetotal();
							requestsaleprice = requesttransferproductout.getSalepricetotal();
						}
						
						//Patient Sale between Fromdate and Todate
						Product salepatient = getPatientSaleBetweenDate(catalogueid,fromDate,toDate,location_filter);
						
						//Return to supplier in between fromdate and todate
						Product returntosuppplier = getReturnToSupplierBetwwenDate(catalogueid,fromDate,toDate,location_filter); 
						
						//Consume to user or patient between Fromdate and Todate
						Product consume = getConsumeBetweenDate(catalogueid,fromDate,toDate,location_filter);
						
						Product adjustmentbetweenOut = getAdjustmentDatabetweenOut(catalogueid,fromDate,location_filter,toDate);
						
						double qtyout = salepatient.getTotalqty()+returntosuppplier.getTotalqty()
									+consume.getTotalqty() + directtransferqtyout +requesttransferqtyout 
									+returntransferqtyout + adjustmentbetweenOut.getTotalqty();
						
						//Stock value =  Patient sale unit price + Return to Supplier purchase price + Consume purchase price
						double stockvalue =  salepatient.getTotal_amount()+ returntosuppplier.getTotal_amount()
												+consume.getTotal_amount() +directtransferqtyoutvalue +requesttransferqtyoutvalue 
												+returntransferqtyoutvalue + adjustmentbetweenOut.getTotal_amount();
						
						//sale price total 
						double saleprice = salepatient.getSalepricetotal()+ returntosuppplier.getSalepricetotal()
												+consume.getSalepricetotal() +directsaleprice +returnsaleprice 
												+requestsaleprice + adjustmentbetweenOut.getSalepricetotal();
						
						
						
						/*//Closing - opening + qtyin - qtyout
						int closingstock = openingstock +qtyin - qtyout;
						
						//double closingvalue = unitprice * closingstock;
						double closingvalue = opeingstockvalue + qtyinvalue - stockvalue;
						int unknownqty = 0;
						if((openingstock +qtyin)<qtyout){
							closingstock =0;
							closingvalue =0;
							unknownqty = qtyout - (openingstock +qtyin);
						}*/
						
						//opening = closing - qtyin + qtyout
						double closingstock =rs.getInt(2);
						openingstock = closingstock -qtyin + qtyout;
						
						//double closingvalue = unitprice * closingstock;
						double closingvalue =  rs.getDouble(3);
						opeingstockvalue = closingvalue - qtyinvalue + stockvalue;
						
						if(openingstock<0){
							openingstock=0;
						}
						
						if(opeingstockvalue<0){
							opeingstockvalue=0;
						}
						
						
						double unknownqty = 0;
						if((openingstock +qtyin)<qtyout){
							closingstock =0;
							closingvalue =0;
							unknownqty = qtyout - (openingstock +qtyin);
						}
						
						
						//double unitprice = getUnitPriceFromCatalogueid(catalogueid);
						
						product.setUnknownqty(unknownqty);
						product.setOpeningstock(""+openingstock);
						product.setOpeningstockvalue(Math.round(opeingstockvalue * 100.0)/100.0);
						product.setPurchaseqty(qtyin);
						product.setSale(""+qtyout);
						product.setSalevalue(Math.round(stockvalue * 100.0)/100.0);
						product.setClosingstock(""+closingstock);
						product.setSv(DateTimeUtils.changeFormat(Math.round(closingvalue * 100.0)/100.0));
						product.setQtyinvalue(DateTimeUtils.changeFormat(qtyinvalue));
						product.setSale_price(DateTimeUtils.changeFormat(Math.round(saleprice * 100.0)/100.0));
					
					 	String sql1="insert into inventory_catalogue_log (lastmodfied, date, location, opening_stock, opeing_value, qty_in, qty_in_value, qty_out, qty_out_value, stock_value, Unknown_qty, closing_stock, closing_value, catalogueid) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					 	PreparedStatement ps1=connection.prepareStatement(sql1);
					 	ps1.setString(1, dt);
					 	ps1.setString(2, dt);
					 	ps1.setString(3, location_filter);
					 	
					 	ps1.setString(4, ""+openingstock);
					 	ps1.setString(5, DateTimeUtils.changeFormat(Math.round(opeingstockvalue * 100.0)/100.0));
					 	ps1.setString(6, ""+qtyin);
					 	
					 	ps1.setString(7, DateTimeUtils.changeFormat(qtyinvalue));
					 	ps1.setString(8, ""+qtyout);
					 	ps1.setString(9, DateTimeUtils.changeFormat(Math.round(stockvalue * 100.0)/100.0));
					 	
					 	ps1.setString(10, DateTimeUtils.changeFormat(Math.round(saleprice * 100.0)/100.0));
					 	ps1.setString(11, ""+unknownqty);
					 	ps1.setString(12, ""+closingstock);
					 	
					 	ps1.setString(13, DateTimeUtils.changeFormat(Math.round(closingvalue * 100.0)/100.0));
					 	ps1.setString(14, rs.getString(1));
					 	int res= ps1.executeUpdate();
					}
				}
			}
			
			System.out.println("ok done");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
private boolean checkInventoryCatalogueStatus(String newdate, String catalogueid, String location_filter ) {
	boolean flag = false;
	try {
		String sql ="select * from inventory_catalogue_log where date='"+newdate+"' and catalogueid='"+catalogueid+"' and location='"+location_filter+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			flag = true;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return flag;
}
	
	Connection connection =null;
	private void updateCatalogueLog() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//connection=DriverManager.getConnection("jdbc:mysql://59.97.236.76:3306/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://114.143.231.10:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/welltreat",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			
			ArrayList<String> arrayList = new ArrayList<String>();
			arrayList.add("33");
		 	/*arrayList.add("36");*/
			/*arrayList.add("33");
			arrayList.add("34");
			arrayList.add("35");*/
			  /* SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			   Date d=sdf1.parse("2018-01-07");
			   Calendar cal = Calendar.getInstance();
			   cal.setTime(d);
			   cal.add(Calendar.DATE, 1);
			   String dt=sdf1.format(cal.getTime());*/
			
			/*long differ = DateTimeUtils.getDifferenceOfTwoDateDBFormat("2018-06-01", "2019-01-31");*/
			long differ = DateTimeUtils.getDifferenceOfTwoDateDBFormat("2019-01-10", "2019-01-12");
			int k = (int)differ;
			for (int i=k; i > 0; i--) {
			   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			   Date d=sdf1.parse("2019-01-10");
			   Calendar cal = Calendar.getInstance();
			   cal.setTime(d);
			   cal.add(Calendar.DATE, i);
			   String dt=sdf1.format(cal.getTime());
			   
			   SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			   Date d2=sdf1.parse(dt);
			   Calendar cal1 = Calendar.getInstance();
			   cal1.setTime(d2);
			   cal1.add(Calendar.DATE, 1);
			   String nextdate=sdf2.format(cal1.getTime());
			   
			   
			   System.out.println("nextdate:"+nextdate+", currentdate:"+dt);
				for (String string : arrayList) {
					String location_filter = string;
					String fromDate = dt;
					String toDate = dt;
					StringBuffer buffer = new StringBuffer();
					buffer.append("select distinct inventory_catalogue.id from inventory_catalogue ");
					buffer.append("inner join inventory_product on inventory_product.catalogueid = inventory_catalogue.id  ");
					buffer.append("where procurement=0 and product_name is not null and inventory_product.location='"+location_filter+"' and inventory_catalogue.lastmodified<='"+nextdate+"' ");
					buffer.append("order by inventory_product.prodname ");
					PreparedStatement ps=connection.prepareStatement(buffer.toString());
					ResultSet rs =ps.executeQuery();
					 System.out.println("nextdate:"+nextdate+", currentdate:"+dt+", location:"+location_filter);
					 int i2=0;
					while(rs.next()){
						Product product = new Product();
						
						String catalogueid = rs.getString(1);
						double opeingstockvalue =0;
						double openingstock=0;
						boolean isapplied = checkInventoryCatalogueStatus(fromDate,catalogueid,location_filter);
						/*boolean isapplied = true;*/
						if(!isapplied){
						//Qty in-  Purchase qty +  return from patient + retruntransfer  = in fromdate and todate
						
						//Purchase qty =  in fromdate and todate
						Product purproduct = getPuchaseProductDataBetween(catalogueid,fromDate,toDate,location_filter);  
						
						//return from patient =  in fromdate and todate
						Product returnpatientqty = getReturnPatientProductDataBetween(catalogueid,fromDate,toDate,location_filter);
						
						// retruntransfer = in fromdate and todate 
						double retruntransferqtyin =0;
						double retruntransferqtyinvalue=0;
						double directtransferqtyin=0;
						double requesttransferqtyin =0;
						double directtransferqtyinvalue=0;
						double requesttransferqtyinvalue=0;
						if(!location_filter.equals("0")){
							//Return transfer between date 
							Product returntransferproductin = getReturnTransferBetweenDate(catalogueid,fromDate,location_filter,toDate);
							retruntransferqtyin = returntransferproductin.getTotalqty();
							retruntransferqtyinvalue = returntransferproductin.getTotal_amount();
							
							//Direct transfer between 
							Product directtransferproductin = getDirectTransferBetweenDateIn(catalogueid,fromDate,location_filter,toDate);
							
							//Request Transfer between
							Product requesttransferproductin = getRequestTransferBetweenDateIn(catalogueid,fromDate,location_filter,toDate);
							
							directtransferqtyin = directtransferproductin.getTotalqty();
							requesttransferqtyin = requesttransferproductin.getTotalqty();
							directtransferqtyinvalue = directtransferproductin.getTotal_amount();
							requesttransferqtyinvalue = requesttransferproductin.getTotal_amount();
						}
						
						Product adjustmentbetweenin = getAdjustmentDataBetweenIn(catalogueid,fromDate,location_filter,toDate);
						
						double qtyin = purproduct.getTotalqty() + returnpatientqty.getTotalqty() 
									+retruntransferqtyin + directtransferqtyin +requesttransferqtyin + adjustmentbetweenin.getTotalqty() ;
						
						double qtyinvalue = purproduct.getTotal_amount() + returnpatientqty.getTotal_amount() 
									+retruntransferqtyinvalue + directtransferqtyinvalue +requesttransferqtyinvalue + adjustmentbetweenin.getTotal_amount() ;
						
						//Qty out - Patient sale + Return to Supplier + Consume + direct transfer + request return  = in Fromdate and Todate
						
						// retruntransfer = in fromdate and todate 
						double directtransferqtyout =0;
						double directtransferqtyoutvalue=0;
						double requesttransferqtyout =0;
						double requesttransferqtyoutvalue=0;
						
						double returntransferqtyout =0;
						double returntransferqtyoutvalue=0;
						
						double directsaleprice=0;
						double returnsaleprice=0;
						double requestsaleprice=0;
						
						if(!location_filter.equals("0")){
							//Direct transfer between 
							Product directtransferproductout = getDirectTransferBetweenDate(catalogueid,fromDate,location_filter,toDate);
							
							//Request Transfer between
							Product requesttransferproductout = getRequestTransferBetweenDate(catalogueid,fromDate,location_filter,toDate);
							
							directtransferqtyout = directtransferproductout.getTotalqty();
							requesttransferqtyout = requesttransferproductout.getTotalqty();
							directtransferqtyoutvalue = directtransferproductout.getTotal_amount();
							requesttransferqtyoutvalue = requesttransferproductout.getTotal_amount();
							
							//Return to store between
							Product returntransferproductin = getReturnTransferBetweenDateOut(catalogueid,fromDate,location_filter,toDate);
							returntransferqtyout = returntransferproductin.getTotalqty();
							returntransferqtyoutvalue = returntransferproductin.getTotal_amount();
							
							directsaleprice = directtransferproductout.getSalepricetotal();
							returnsaleprice = returntransferproductin.getSalepricetotal();
							requestsaleprice = requesttransferproductout.getSalepricetotal();
						}
						
						//Patient Sale between Fromdate and Todate
						Product salepatient = getPatientSaleBetweenDate(catalogueid,fromDate,toDate,location_filter);
						
						//Return to supplier in between fromdate and todate
						Product returntosuppplier = getReturnToSupplierBetwwenDate(catalogueid,fromDate,toDate,location_filter); 
						
						//Consume to user or patient between Fromdate and Todate
						Product consume = getConsumeBetweenDate(catalogueid,fromDate,toDate,location_filter);
						
						Product adjustmentbetweenOut = getAdjustmentDatabetweenOut(catalogueid,fromDate,location_filter,toDate);
						
						double qtyout = salepatient.getTotalqty()+returntosuppplier.getTotalqty()
									+consume.getTotalqty() + directtransferqtyout +requesttransferqtyout 
									+returntransferqtyout + adjustmentbetweenOut.getTotalqty();
						
						//Stock value =  Patient sale unit price + Return to Supplier purchase price + Consume purchase price
						double stockvalue =  salepatient.getTotal_amount()+ returntosuppplier.getTotal_amount()
												+consume.getTotal_amount() +directtransferqtyoutvalue +requesttransferqtyoutvalue 
												+returntransferqtyoutvalue + adjustmentbetweenOut.getTotal_amount();
						
						//sale price total 
						double saleprice = salepatient.getSalepricetotal()+ returntosuppplier.getSalepricetotal()
												+consume.getSalepricetotal() +directsaleprice +returnsaleprice 
												+requestsaleprice + adjustmentbetweenOut.getSalepricetotal();
						
						
						
						/*//Closing - opening + qtyin - qtyout
						int closingstock = openingstock +qtyin - qtyout;
						
						//double closingvalue = unitprice * closingstock;
						double closingvalue = opeingstockvalue + qtyinvalue - stockvalue;
						int unknownqty = 0;
						if((openingstock +qtyin)<qtyout){
							closingstock =0;
							closingvalue =0;
							unknownqty = qtyout - (openingstock +qtyin);
						}*/
						
						//double unitprice = getUnitPriceFromCatalogueid(catalogueid);
						
						//opening = closing - qtyin + qtyout
						
						Product product2 = getNextOpeingData(nextdate,catalogueid,location_filter);
						
						double closingstock =product2.getTotalclosingstock();
						openingstock = closingstock -qtyin + qtyout;
						
						//double closingvalue = unitprice * closingstock;
						double closingvalue =  product2.getTotalclosingvalue();
						opeingstockvalue = closingvalue - qtyinvalue + stockvalue;
						
						if(openingstock<0){
							openingstock=0;
						}
						
						if(opeingstockvalue<0){
							opeingstockvalue=0;
						}
						
						
						double unknownqty = 0;
						if((openingstock +qtyin)<qtyout){
							closingstock =0;
							closingvalue =0;
							unknownqty = qtyout - (openingstock +qtyin);
						}
						
						
						product.setUnknownqty(unknownqty);
						product.setOpeningstock(""+openingstock);
						product.setOpeningstockvalue(Math.round(opeingstockvalue * 100.0)/100.0);
						product.setPurchaseqty(qtyin);
						product.setSale(""+qtyout);
						product.setSalevalue(Math.round(stockvalue * 100.0)/100.0);
						product.setClosingstock(""+closingstock);
						product.setSv(DateTimeUtils.changeFormat(Math.round(closingvalue * 100.0)/100.0));
						product.setQtyinvalue(DateTimeUtils.changeFormat(qtyinvalue));
						product.setSale_price(DateTimeUtils.changeFormat(Math.round(saleprice * 100.0)/100.0));
					
					 	String sql1="insert into inventory_catalogue_log (lastmodfied, date, location, opening_stock, opeing_value, qty_in, qty_in_value, qty_out, qty_out_value, stock_value, Unknown_qty, closing_stock, closing_value, catalogueid) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					 	PreparedStatement ps1=connection.prepareStatement(sql1);
					 	ps1.setString(1, dt);
					 	ps1.setString(2, dt);
					 	ps1.setString(3, location_filter);
					 	
					 	ps1.setString(4, ""+openingstock);
					 	ps1.setString(5, DateTimeUtils.changeFormat(Math.round(opeingstockvalue * 100.0)/100.0));
					 	ps1.setString(6, ""+qtyin);
					 	
					 	ps1.setString(7, DateTimeUtils.changeFormat(qtyinvalue));
					 	ps1.setString(8, ""+qtyout);
					 	ps1.setString(9, DateTimeUtils.changeFormat(Math.round(stockvalue * 100.0)/100.0));
					 	
					 	ps1.setString(10, DateTimeUtils.changeFormat(Math.round(saleprice * 100.0)/100.0));
					 	ps1.setString(11, ""+unknownqty);
					 	ps1.setString(12, ""+closingstock);
					 	
					 	ps1.setString(13, DateTimeUtils.changeFormat(Math.round(closingvalue * 100.0)/100.0));
					 	ps1.setString(14, rs.getString(1));
					 	int res= ps1.executeUpdate();
					 	i2=i2+1;
					 	System.out.println(i2);
					}
				}
			}
			}
			
			
			
			System.out.println("ok done");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private Product getNextOpeingData(String nextdate, String catalogueid, String location_filter) {
		Product product = new Product();
		try {
			String sql ="select opening_stock, opeing_value from inventory_catalogue_log where date='"+nextdate+"' and location='"+location_filter+"' and catalogueid='"+catalogueid+"' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			product.setTotalclosingstock(0);
			product.setTotalclosingvalue(0);
			if (rs.next()) {
				product.setTotalclosingstock(rs.getInt(1));
				product.setTotalclosingvalue(rs.getDouble(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}
	private Product getYesterdayClosingData(String previousdate, String catalogueid, String location_filter) {
		Product product = new Product();
		try {
			String sql ="select closing_stock, closing_value,closing from inventory_catalogue_log where date='"+previousdate+"' and location='"+location_filter+"' and catalogueid='"+catalogueid+"' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			product.setTotalclosingstock(0);
			product.setTotalclosingvalue(0);
			product.setClosing(0);
			if (rs.next()) {
				product.setTotalclosingstock(rs.getInt(1));
				product.setTotalclosingvalue(rs.getDouble(2));
				product.setClosing(rs.getDouble(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}


	private boolean checkPreviousDataCatAvailable(String previousdate, String catalogueid, String location_filter) {
		boolean flag = false;
		try {
			String sql ="select * from inventory_catalogue_log where date='"+previousdate+"' and location='"+location_filter+"' and catalogueid='"+catalogueid+"' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


	private Product getAdjustmentDatabetweenOut(String catalogueid, String fromDate, String location_filter,
			String toDate) {
		Product product = new Product();
		try {
			toDate = toDate +" "+ "59:59:59";
			StringBuffer buffer = new StringBuffer();
			buffer.append("select sum(adjustment_data.change_qty),sum(purchaseprice)/sum(pack),sum(adjustment_data.change_qty*(purchaseprice/pack)),sum(adjustment_data.change_qty*saleprice)   ");
			buffer.append("from adjustment_data ");
			buffer.append("inner join inventory_product on inventory_product.id = adjustment_data.product_id ");
			buffer.append("where adjustment_type!=2 and inventory_product.catalogueid='"+catalogueid+"' and adjustment_data.datetime between '"+fromDate+"' and '"+toDate+"' ");
			if(!location_filter.equals("0")){
				buffer.append("and inventory_product.location='"+location_filter+"' ");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double total=0.0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(total));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			product.setSalepricetotal(0);
			while (rs.next()) {
				product.setSalepricetotal(rs.getDouble(4));
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	private Product getAdjustmentDataBetweenIn(String catalogueid, String fromDate, String location_filter, String toDate) {
		Product product = new Product();
		try {
			toDate = toDate +" "+ "59:59:59";
			StringBuffer buffer = new StringBuffer();
			buffer.append("select sum(adjustment_data.change_qty),sum(purchaseprice)/sum(pack),sum(adjustment_data.change_qty*(purchaseprice/pack))   ");
			buffer.append("from adjustment_data ");
			buffer.append("inner join inventory_product on inventory_product.id = adjustment_data.product_id ");
			buffer.append("where adjustment_type=2 and inventory_product.catalogueid='"+catalogueid+"' and adjustment_data.datetime between '"+fromDate+"' and '"+toDate+"' ");
			if(!location_filter.equals("0")){
				buffer.append("and inventory_product.location='"+location_filter+"' ");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double total=0.0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(total));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			while (rs.next()) {
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	private Product getAdjustmentDataBeforeIn(String catalogueid, String fromDate, String location_filter) {
		Product product = new Product();
		try {
			fromDate = fromDate +" "+ "59:59:59";
			StringBuffer buffer = new StringBuffer();
			buffer.append("select sum(adjustment_data.change_qty),sum(purchaseprice)/sum(pack),sum(adjustment_data.change_qty*(purchaseprice/pack))   ");
			buffer.append("from adjustment_data ");
			buffer.append("inner join inventory_product on inventory_product.id = adjustment_data.product_id ");
			buffer.append("where adjustment_type=2 and inventory_product.catalogueid='"+catalogueid+"' and adjustment_data.datetime<'"+fromDate+"' ");
			if(!location_filter.equals("0")){
				buffer.append("and inventory_product.location='"+location_filter+"' ");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double total=0.0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(total));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			while (rs.next()) {
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	private Product getAdjustmentDataBeforeOut(String catalogueid, String fromDate, String location_filter) {
		Product product = new Product();
		try {
			fromDate = fromDate +" "+ "59:59:59";
			StringBuffer buffer = new StringBuffer();
			buffer.append("select sum(adjustment_data.change_qty),sum(purchaseprice)/sum(pack),sum(adjustment_data.change_qty*(purchaseprice/pack))   ");
			buffer.append("from adjustment_data ");
			buffer.append("inner join inventory_product on inventory_product.id = adjustment_data.product_id ");
			buffer.append("where adjustment_type!=2 and inventory_product.catalogueid='"+catalogueid+"' and adjustment_data.datetime<'"+fromDate+"' ");
			if(!location_filter.equals("0")){
				buffer.append("and inventory_product.location='"+location_filter+"' ");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double total=0.0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(total));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			while (rs.next()) {
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	private double getUnitPriceFromCatalogueid(String catalogueid) {
		double res = 0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select purchaseprice/pack from inventory_procurement ");
			buffer.append("inner join inventory_product on inventory_product.id = inventory_procurement.prodid ");
			buffer.append("where inventory_product.catalogueid='"+catalogueid+"' and inventory_product.procurement=0 ");
			buffer.append("order by inventory_product.id desc limit 1 ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private Product getReturnTransferBetweenDateOut(String catalogueid, String fromDate, String location_filter,
			String toDate) {
		Product product = new Product();
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append("select sum(inventory_transfer_log.qty),sum(purchaseprice)/sum(pack),sum(inventory_transfer_log.qty*(purchaseprice/pack)),sum(inventory_transfer_log.qty*saleprice) from inventory_transfer_log ");
			buffer.append("inner join inventory_parent_transfer_log on inventory_parent_transfer_log.id=inventory_transfer_log.parentid ");
			buffer.append("inner join inventory_product on  inventory_product.id = inventory_transfer_log.old_prodid ");
			buffer.append("where inventory_parent_transfer_log.req_or_trans=2 and inventory_parent_transfer_log.issued_date between '"+fromDate+"' and '"+toDate+"' and inventory_parent_transfer_log.deleted=0 ");
			buffer.append("and inventory_product.catalogueid='"+catalogueid+"' ");
			if (!location_filter.equals("0")) {
				buffer.append("and from_location ='" + location_filter + "' ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			product.setSalepricetotal(0);
			while(rs.next()){
				product.setSalepricetotal(rs.getDouble(4));
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return product;
	}

	private Product getRequestTransferBetweenDateIn(String catalogueid, String fromDate, String location_filter,
			String toDate) {
		Product product = new Product();
		try {
			StringBuffer buffer=new StringBuffer();
			buffer.append("SELECT sum(inventory_request_temp_log.qty),sum(purchaseprice)/sum(pack),sum(inventory_request_temp_log.qty*(purchaseprice/pack))  ");
			buffer.append("FROM inventory_request_temp_log ");
			buffer.append("inner join inventory_parent_transfer_log on inventory_parent_transfer_log.id=inventory_request_temp_log.parentid ");
			buffer.append("inner join inventory_product on  inventory_product.id = inventory_request_temp_log.old_prodid ");
			buffer.append("where transfer_date between '"+fromDate+"' and '"+toDate+"' and inventory_parent_transfer_log.deleted=0 ");
			buffer.append("and inventory_product.catalogueid='"+catalogueid+"' ");
			if (!location_filter.equals("0")) {
				buffer.append("and from_location ='" + location_filter + "' ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			while(rs.next()){
				/*totalqty = totalqty + rs.getInt(1);
				double pack =rs.getDouble(3);
				if(pack==0){
					pack=1.0;
				}
				double unitprice = rs.getDouble(2)/pack;
				total = total +  (unitprice*rs.getInt(1));
				product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
				product.setTotal_amount(total);
				product.setTotalqty(totalqty);*/
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return product;
	}

	private Product getDirectTransferBetweenDateIn(String catalogueid, String fromDate, String location_filter,
			String toDate) {
		Product product = new Product();
		try {
			StringBuffer buffer= new StringBuffer();
			//req_or_transfer==1
			buffer.append("select sum(inventory_transfer_log.qty),sum(purchaseprice)/sum(pack),sum(inventory_transfer_log.qty*(purchaseprice/pack)) from inventory_transfer_log ");
			buffer.append("inner join inventory_parent_transfer_log on inventory_parent_transfer_log.id=inventory_transfer_log.parentid ");
			buffer.append("inner join inventory_product on  inventory_product.id = inventory_transfer_log.old_prodid ");
			buffer.append("where inventory_parent_transfer_log.req_or_trans=1 and inventory_parent_transfer_log.issued_date between '"+fromDate+"' and '"+toDate+"' and inventory_parent_transfer_log.deleted=0 ");
			buffer.append("and inventory_product.catalogueid='"+catalogueid+"' ");
			if (!location_filter.equals("0")) {
				buffer.append("and to_location ='" + location_filter + "' ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			while(rs.next()){
				/*totalqty = totalqty + rs.getInt(1);
				double pack =rs.getDouble(3);
				if(pack==0){
					pack=1.0;
				}
				double unitprice = rs.getDouble(2)/pack;
				total = total +  (unitprice*rs.getInt(1));
				product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
				product.setTotal_amount(total);
				product.setTotalqty(totalqty);*/
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return product;
	}

	private Product getReturnTransferBeforeOut(String catalogueid, String fromDate, String location_filter) {
		Product product = new Product();
		try {
			StringBuffer buffer= new StringBuffer();
			//out
			buffer.append("select sum(inventory_transfer_log.qty),sum(purchaseprice)/sum(pack),sum(inventory_transfer_log.qty*(purchaseprice/pack)) from inventory_transfer_log ");
			buffer.append("inner join inventory_parent_transfer_log on inventory_parent_transfer_log.id=inventory_transfer_log.parentid ");
			buffer.append("inner join inventory_product on  inventory_product.id = inventory_transfer_log.old_prodid ");
			buffer.append("where inventory_parent_transfer_log.req_or_trans=2 and inventory_parent_transfer_log.issued_date<'"+fromDate+"' ");
			buffer.append("and inventory_product.catalogueid='"+catalogueid+"' and inventory_parent_transfer_log.deleted=0 ");
			if (!location_filter.equals("0")) {
				buffer.append("and from_location ='" + location_filter + "' ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			while(rs.next()){
				/*totalqty = totalqty + rs.getInt(1);
				double pack =rs.getDouble(3);
				if(pack==0){
					pack=1.0;
				}
				double unitprice = rs.getDouble(2)/pack;
				total = total +  (unitprice*rs.getInt(1));
				product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
				product.setTotal_amount(total);
				product.setTotalqty(totalqty);*/
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return product;
	}

	private Product getRequestTransferBeforeIn(String catalogueid, String fromDate, String location_filter) {
		Product product = new Product();
		try {
			StringBuffer buffer=new StringBuffer();
			//in
			buffer.append("SELECT sum(inventory_request_temp_log.qty),sum(purchaseprice)/sum(pack),sum(inventory_request_temp_log.qty*(purchaseprice/pack))  ");
			buffer.append("FROM inventory_request_temp_log ");
			buffer.append("inner join inventory_parent_transfer_log on inventory_parent_transfer_log.id=inventory_request_temp_log.parentid ");
			buffer.append("inner join inventory_product on  inventory_product.id = inventory_request_temp_log.old_prodid ");
			buffer.append("where transfer_date<'"+fromDate+"' ");
			buffer.append("and inventory_product.catalogueid='"+catalogueid+"' and inventory_parent_transfer_log.deleted=0 ");
			if (!location_filter.equals("0")) {
				buffer.append("and from_location ='" + location_filter + "' ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			while(rs.next()){
				/*totalqty = totalqty + rs.getInt(1);
				double pack =rs.getDouble(3);
				if(pack==0){
					pack=1.0;
				}
				double unitprice = rs.getDouble(2)/pack;
				total = total +  (unitprice*rs.getInt(1));
				product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
				product.setTotal_amount(total);
				product.setTotalqty(totalqty);*/
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return product;
	}

	private Product getDirectTransferBeforeIn(String catalogueid, String fromDate, String location_filter) {
		Product product = new Product();
		try {
			StringBuffer buffer= new StringBuffer();
			//req_or_transfer==1 // in
			buffer.append("select sum(inventory_transfer_log.qty),sum(purchaseprice)/sum(pack),sum(inventory_transfer_log.qty*(purchaseprice/pack)) from inventory_transfer_log ");
			buffer.append("inner join inventory_parent_transfer_log on inventory_parent_transfer_log.id=inventory_transfer_log.parentid ");
			buffer.append("inner join inventory_product on  inventory_product.id = inventory_transfer_log.old_prodid ");
			buffer.append("where inventory_parent_transfer_log.req_or_trans=1 and inventory_parent_transfer_log.issued_date<'"+fromDate+"' ");
			buffer.append("and inventory_product.catalogueid='"+catalogueid+"' and inventory_parent_transfer_log.deleted=0 ");
			if (!location_filter.equals("0")) {
				buffer.append("and to_location ='" + location_filter + "' ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			while(rs.next()){
				/*totalqty = totalqty + rs.getInt(1);
				double pack =rs.getDouble(3);
				if(pack==0){
					pack=1.0;
				}
				double unitprice = rs.getDouble(2)/pack;
				total = total +  (unitprice*rs.getInt(1));
				product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
				product.setTotal_amount(total);
				product.setTotalqty(totalqty);*/
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return product;
	}


	private Product getRequestTransferBetweenDate(String catalogueid, String fromDate, String location_filter,
			String toDate) {
		Product product = new Product();
		try {
			toDate = toDate +" "+ "59:59:59";
			StringBuffer buffer=new StringBuffer();
			buffer.append("SELECT sum(inventory_request_temp_log.qty),sum(purchaseprice)/sum(pack),sum(inventory_request_temp_log.qty*(purchaseprice/pack)),sum(inventory_request_temp_log.qty*saleprice)   ");
			buffer.append("FROM inventory_request_temp_log ");
			buffer.append("inner join inventory_parent_transfer_log on inventory_parent_transfer_log.id=inventory_request_temp_log.parentid ");
			buffer.append("inner join inventory_product on  inventory_product.id = inventory_request_temp_log.old_prodid ");
			buffer.append("where transfer_date between '"+fromDate+"' and '"+toDate+"' and inventory_parent_transfer_log.deleted=0 ");
			buffer.append("and inventory_product.catalogueid='"+catalogueid+"' ");
			if (!location_filter.equals("0")) {
				buffer.append("and inventory_request_temp_log.location ='" + location_filter + "' ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			product.setSalepricetotal(0);
			while(rs.next()){
				product.setSalepricetotal(rs.getDouble(4));
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return product;
	}

	private Product getDirectTransferBetweenDate(String catalogueid, String fromDate, String location_filter,
			String toDate) {
		Product product = new Product();
		try {
			toDate = toDate +" "+ "59:59:59";
			StringBuffer buffer= new StringBuffer();
			//req_or_transfer==1
			buffer.append("select sum(inventory_transfer_log.qty),sum(purchaseprice)/sum(pack),sum(inventory_transfer_log.qty*(purchaseprice/pack)),sum(inventory_transfer_log.qty*saleprice) from inventory_transfer_log ");
			buffer.append("inner join inventory_parent_transfer_log on inventory_parent_transfer_log.id=inventory_transfer_log.parentid ");
			buffer.append("inner join inventory_product on  inventory_product.id = inventory_transfer_log.old_prodid ");
			buffer.append("where inventory_parent_transfer_log.req_or_trans=1 and inventory_parent_transfer_log.issued_date between '"+fromDate+"' and '"+toDate+"' and inventory_parent_transfer_log.deleted=0 ");
			buffer.append("and inventory_product.catalogueid='"+catalogueid+"' ");
			if (!location_filter.equals("0")) {
				buffer.append("and from_location ='" + location_filter + "' ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			product.setSalepricetotal(0);
			while(rs.next()){
				product.setSalepricetotal(rs.getDouble(4));
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return product;
	}

	private Product getReturnTransferBetweenDate(String catalogueid, String fromDate, String location_filter,
			String toDate) {
		Product product = new Product();
		try {
			toDate = toDate +" "+ "59:59:59";
			StringBuffer buffer= new StringBuffer();
			buffer.append("select sum(inventory_transfer_log.qty),sum(purchaseprice)/sum(pack),sum(inventory_transfer_log.qty*(purchaseprice/pack)) from inventory_transfer_log ");
			buffer.append("inner join inventory_parent_transfer_log on inventory_parent_transfer_log.id=inventory_transfer_log.parentid ");
			buffer.append("inner join inventory_product on  inventory_product.id = inventory_transfer_log.old_prodid ");
			buffer.append("where inventory_parent_transfer_log.req_or_trans=2 and inventory_parent_transfer_log.issued_date between '"+fromDate+"' and '"+toDate+"' and inventory_parent_transfer_log.deleted=0 ");
			buffer.append("and inventory_product.catalogueid='"+catalogueid+"' ");
			if (!location_filter.equals("0")) {
				buffer.append("and to_location ='" + location_filter + "' ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			while(rs.next()){
				/*totalqty = totalqty + rs.getInt(1);
				double pack =rs.getDouble(3);
				if(pack==0){
					pack=1.0;
				}
				double unitprice = rs.getDouble(2)/pack;
				total = total +  (unitprice*rs.getInt(1));
				product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
				product.setTotal_amount(total);
				product.setTotalqty(totalqty);*/
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return product;
	}

	private Product getReturnTransferBefore(String catalogueid, String fromDate, String location_filter) {
		Product product = new Product();
		try {
			StringBuffer buffer= new StringBuffer();
			//from location out
			buffer.append("select sum(inventory_transfer_log.qty),sum(purchaseprice)/sum(pack),sum(inventory_transfer_log.qty*(purchaseprice/pack)) from inventory_transfer_log ");
			buffer.append("inner join inventory_parent_transfer_log on inventory_parent_transfer_log.id=inventory_transfer_log.parentid ");
			buffer.append("inner join inventory_product on  inventory_product.id = inventory_transfer_log.old_prodid ");
			buffer.append("where inventory_parent_transfer_log.req_or_trans=2 and inventory_parent_transfer_log.issued_date<'"+fromDate+"' ");
			buffer.append("and inventory_product.catalogueid='"+catalogueid+"' and inventory_parent_transfer_log.deleted=0 ");
			if (!location_filter.equals("0")) {
				buffer.append("and to_location ='" + location_filter + "' ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			while(rs.next()){
				/*totalqty = totalqty + rs.getInt(1);
				double pack =rs.getDouble(3);
				if(pack==0){
					pack=1.0;
				}
				double unitprice = rs.getDouble(2)/pack;
				total = total +  (unitprice*rs.getInt(1));
				product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
				product.setTotal_amount(total);
				product.setTotalqty(totalqty);*/
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return product;
	}

	private Product getRequestTransferBefore(String catalogueid, String fromDate, String location_filter) {
		Product product = new Product();
		try {
			StringBuffer buffer=new StringBuffer();
			//from location out
			buffer.append("SELECT sum(inventory_request_temp_log.qty),sum(purchaseprice)/sum(pack),sum(inventory_request_temp_log.qty*(purchaseprice/pack))  ");
			buffer.append("FROM inventory_request_temp_log ");
			buffer.append("inner join inventory_parent_transfer_log on inventory_parent_transfer_log.id=inventory_request_temp_log.parentid ");
			buffer.append("inner join inventory_product on  inventory_product.id = inventory_request_temp_log.old_prodid ");
			buffer.append("where transfer_date<'"+fromDate+"' and inventory_parent_transfer_log.deleted=0 ");
			buffer.append("and inventory_product.catalogueid='"+catalogueid+"' ");
			if (!location_filter.equals("0")) {
				buffer.append("and inventory_request_temp_log.location ='" + location_filter + "' ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			while(rs.next()){
				/*totalqty = totalqty + rs.getInt(1);
				double pack =rs.getDouble(3);
				if(pack==0){
					pack=1.0;
				}
				double unitprice = rs.getDouble(2)/pack;
				total = total +  (unitprice*rs.getInt(1));
				product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
				product.setTotal_amount(total);
				product.setTotalqty(totalqty);*/
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return product;
	}

	private Product getDirectTransferBefore(String catalogueid, String fromDate, String location_filter) {
		Product product = new Product();
		try {
			StringBuffer buffer= new StringBuffer();
			//req_or_transfer==1 out
			buffer.append("select sum(inventory_transfer_log.qty),sum(purchaseprice)/sum(pack),sum(inventory_transfer_log.qty*(purchaseprice/pack)) from inventory_transfer_log ");
			buffer.append("inner join inventory_parent_transfer_log on inventory_parent_transfer_log.id=inventory_transfer_log.parentid ");
			buffer.append("inner join inventory_product on  inventory_product.id = inventory_transfer_log.old_prodid ");
			buffer.append("where inventory_parent_transfer_log.req_or_trans=1 and inventory_parent_transfer_log.issued_date<'"+fromDate+"' and inventory_parent_transfer_log.deleted=0 ");
			buffer.append("and inventory_product.catalogueid='"+catalogueid+"' ");
			if (!location_filter.equals("0")) {
				buffer.append("and from_location ='" + location_filter + "' ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			while(rs.next()){
				/*totalqty = totalqty + rs.getInt(1);
				double pack =rs.getDouble(3);
				if(pack==0){
					pack=1.0;
				}
				double unitprice = rs.getDouble(2)/pack;
				total = total +  (unitprice*rs.getInt(1));
				product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
				product.setTotal_amount(total);
				product.setTotalqty(totalqty);*/
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return product;
	}

	private Product getConsumeBetweenDate(String catalogueid, String fromDate, String toDate, String location_filter) {
		Product product = new Product();
		try {
			toDate = toDate +" "+ "59:59:59";
			StringBuffer buffer= new StringBuffer();
			buffer.append("select sum(indent_patient_transfer_log.qty),sum(purchaseprice)/sum(pack),sum(indent_patient_transfer_log.qty*(purchaseprice/pack)),sum(indent_patient_transfer_log.qty*saleprice) ");
			buffer.append("from indent_patient_transfer_log ");
			buffer.append("inner join inventory_product on  inventory_product.id = indent_patient_transfer_log.prodid ");
			buffer.append("where inventory_product.catalogueid='"+catalogueid+"' and indent_patient_transfer_log.datetime between '"+fromDate+"' and '"+toDate+"' ");
			if(!location_filter.equals("0")){
				buffer.append("and indent_patient_transfer_log.fromlocation='"+location_filter+"' ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			product.setSalepricetotal(0);
			while(rs.next()){
				product.setSalepricetotal(rs.getDouble(4));
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return product;
	}

	private Product getReturnToSupplierBetwwenDate(String catalogueid, String fromDate, String toDate, String location_filter) {
		Product product = new Product();
		try {
			toDate = toDate +" "+ "59:59:59";
			StringBuffer buffer= new StringBuffer();
			buffer.append("SELECT sum(inventory_product_return_log.qty),sum(purchaseprice)/sum(pack),sum(inventory_product_return_log.qty*(purchaseprice/pack)),sum(saleprice*inventory_product_return_log.qty) ");
			buffer.append("from inventory_product_return_log ");
			buffer.append("inner join inventory_product on  inventory_product.id = inventory_product_return_log.productid ");
			buffer.append("where status!=0 and inventory_product.catalogueid='"+catalogueid+"' and datetime between '"+fromDate+"' and '"+toDate+"' and inventory_product_return_log.iscancel=0 ");
			if(!location_filter.equals("0")){
				buffer.append("and inventory_product.location='"+location_filter+"' ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			product.setSalepricetotal(0);
			while(rs.next()){
				product.setSalepricetotal(rs.getDouble(4));
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return product;
	}

	private Product getPatientSaleBetweenDate(String catalogueid, String fromDate, String toDate, String location_filter) {
		Product product= new Product();
		try {
			toDate = toDate +" "+ "59:59:59";
			StringBuffer buffer= new StringBuffer();
			buffer.append("select sum(quantity),sum(quantity*charge),sum(quantity*(purchaseprice/pack))  ");
			buffer.append("from apm_medicine_charges ");
			buffer.append("inner join apm_medicine_bill on apm_medicine_charges.invoiceid = apm_medicine_bill.id ");
			buffer.append("inner join inventory_product on inventory_product.id = apm_medicine_charges.product_id ");
			buffer.append("where apm_medicine_bill.isreturn =0 and inventory_product.catalogueid='"+catalogueid+"' and date between '"+fromDate+"' and '"+toDate+"' and apm_medicine_bill.deleted=0  ");
			if(!location_filter.equals("0")){
				buffer.append("and apm_medicine_bill.location='"+location_filter+"' ");
			}
			PreparedStatement preparedStatement=connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			product.setSalepricetotal(0);
			while (rs.next()) {
				product.setSalepricetotal(rs.getDouble(2));
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return product;
	}
	

	private Product getReturnPatientProductDataBetween(String catalogueid, String fromDate, String toDate, String location_filter) {
		Product product= new Product();
		try {
			toDate = toDate +" "+ "59:59:59";
			StringBuffer buffer= new StringBuffer();
			buffer.append("select sum(quantity),sum(quantity*charge),sum(quantity*(purchaseprice/pack))  ");
			buffer.append("from apm_medicine_charges ");
			buffer.append("inner join apm_medicine_bill on apm_medicine_charges.invoiceid = apm_medicine_bill.id ");
			buffer.append("inner join inventory_product on inventory_product.id = apm_medicine_charges.product_id ");
			buffer.append("where apm_medicine_bill.isreturn =1 and inventory_product.catalogueid='"+catalogueid+"' and date between '"+fromDate+"' and '"+toDate+"' and apm_medicine_bill.deleted=0  ");
			if(!location_filter.equals("0")){
				buffer.append("and apm_medicine_bill.location='"+location_filter+"' ");
			}
			PreparedStatement preparedStatement=connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			while (rs.next()) {
				/*totalqty = totalqty + rs.getInt(1);
				double pack =rs.getDouble(3);
				if(pack==0){
					pack=1.0;
				}
				double unitprice = rs.getDouble(2)/pack;
				total = total +  (unitprice*rs.getInt(1));
				product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
				product.setTotal_amount(total);
				product.setTotalqty(totalqty);*/
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return product;
	}

	private Product getPuchaseProductDataBetween(String catalogueid, String fromDate, String toDate, String location_filter) {
		Product product= new Product();
		try {
			toDate = toDate +" "+ "59:59:59";
			StringBuffer buffer = new StringBuffer();
			/*buffer.append("select inventory_procurement.qty,inventory_product.purchaseprice,inventory_product.pack from inventory_procurement  ");
			buffer.append("inner join inventory_product on inventory_product.id = inventory_procurement.prodid ");
			buffer.append("where inventory_product.catalogueid="+catalogueid+" and inventory_procurement.date between '"+fromDate+"' and '"+toDate+"' and  procurement='0'  and inventory_procurement.deleted=0 ");
			if(!location_filter.equals("0")){
				buffer.append("and inventory_product.location='"+location_filter+"' ");
			}*/
			buffer.append("select sum((inventory_procurement.qty+0) + (inventory_product.freeqty+0)),sum(purchaseprice)/sum(pack),sum(inventory_procurement.qty*(purchaseprice/pack))+sum(inventory_product.freeqty * (purchaseprice/pack)),sum(inventory_product.freeqty),sum(inventory_procurement.qty)   ");
			buffer.append("from inventory_procurement ");
			buffer.append("inner join inventory_product on inventory_product.id = inventory_procurement.prodid ");
			buffer.append("where inventory_product.catalogueid="+catalogueid+" and inventory_procurement.date between '"+fromDate+"' and '"+toDate+"' and  procurement='0'  and inventory_procurement.deleted=0 and confirm=1 and gudreceipt=1 ");
			buffer.append("and  inventory_procurement.iscancel=0 ");
			if(!location_filter.equals("0")){
				buffer.append("and inventory_product.location='"+location_filter+"' ");
			}
			
			
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			while (rs.next()) {
				/*totalqty = totalqty + rs.getInt(1);
				double pack =rs.getDouble(3);
				if(pack==0){
					pack=1.0;
				}
				double unitprice = rs.getDouble(2)/pack;
				total = total +  (unitprice*rs.getInt(1));
				product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
				product.setTotal_amount(total);
				product.setTotalqty(totalqty);*/
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	private Product getConsumeBeforeDate(String catalogueid, String fromDate, String location_filter) {
		Product product= new Product();
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append("select sum(indent_patient_transfer_log.qty),sum(indent_patient_transfer_log.qty*(purchaseprice/pack)) ");
			buffer.append("from indent_patient_transfer_log ");
			buffer.append("inner join inventory_product on  inventory_product.id = indent_patient_transfer_log.prodid ");
			buffer.append("where inventory_product.catalogueid='"+catalogueid+"' and indent_patient_transfer_log.datetime<'"+fromDate+"' ");
			if(!location_filter.equals("0")){
				buffer.append("and indent_patient_transfer_log.fromlocation='"+location_filter+"' ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			while (rs.next()) {
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(2) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return product;
	}

	private Product getReturnToSupplierBeforeDate(String catalogueid, String fromDate, String location_filter) {
		Product product= new Product();
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append("SELECT sum(inventory_product_return_log.qty),sum(inventory_product_return_log.qty*(purchaseprice/pack)) ");
			buffer.append("from inventory_product_return_log ");
			buffer.append("inner join inventory_product on  inventory_product.id = inventory_product_return_log.productid ");
			buffer.append("where status!=0 and inventory_product.catalogueid='"+catalogueid+"' and datetime<'"+fromDate+"' and inventory_product_return_log.iscancel=0 ");
			if(!location_filter.equals("0")){
				buffer.append("and inventory_product.location='"+location_filter+"' ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			while (rs.next()) {
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(2) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(2));
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return product;
	}

	private Product getPatientSaleBeforeDate(String catalogueid, String fromDate, String location_filter) {
		Product product= new Product();
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append("select sum(quantity),sum(quantity*(purchaseprice/pack))  ");
			buffer.append("from apm_medicine_charges ");
			buffer.append("inner join apm_medicine_bill on apm_medicine_charges.invoiceid = apm_medicine_bill.id ");
			buffer.append("inner join inventory_product on inventory_product.id = apm_medicine_charges.product_id ");
			buffer.append("where apm_medicine_bill.isreturn =0 and inventory_product.catalogueid='"+catalogueid+"' and date<'"+fromDate+"' and apm_medicine_bill.deleted=0  ");
			if(!location_filter.equals("0")){
				buffer.append("and apm_medicine_bill.location='"+location_filter+"' ");
			}
			PreparedStatement preparedStatement=connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			while (rs.next()) {
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(2) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return product;
	}

	private Product getReturnPatientProductData(String catalogueid, String fromDate, String location_filter) {
		Product product= new Product();
		try {
			StringBuffer buffer= new StringBuffer();
			/*buffer.append("select quantity,inventory_product.purchaseprice,inventory_product.pack  ");
			buffer.append("from apm_medicine_charges ");
			buffer.append("inner join apm_medicine_bill on apm_medicine_charges.invoiceid = apm_medicine_bill.id ");
			buffer.append("inner join inventory_product on inventory_product.id = apm_medicine_charges.product_id ");
			buffer.append("where apm_medicine_bill.isreturn =1 and inventory_product.catalogueid='"+catalogueid+"' and date<'"+fromDate+"'  ");
			if(!location_filter.equals("0")){
				buffer.append("and inventory_product.location='"+location_filter+"' ");
			}*/
			buffer.append("select sum(quantity),sum(quantity*charge),sum(quantity*(purchaseprice/pack))  ");
			buffer.append("from apm_medicine_charges ");
			buffer.append("inner join apm_medicine_bill on apm_medicine_charges.invoiceid = apm_medicine_bill.id ");
			buffer.append("inner join inventory_product on inventory_product.id = apm_medicine_charges.product_id ");
			buffer.append("where apm_medicine_bill.isreturn =1 and inventory_product.catalogueid='"+catalogueid+"' and date<'"+fromDate+"' and apm_medicine_bill.deleted=0  ");
			if(!location_filter.equals("0")){
				buffer.append("and apm_medicine_bill.location='"+location_filter+"' ");
			}
			PreparedStatement preparedStatement=connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double total=0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			while (rs.next()) {
				/*totalqty = totalqty + rs.getInt(1);
				double pack =rs.getDouble(3);
				if(pack==0){
					pack=1.0;
				}
				double unitprice = rs.getDouble(2)/pack;
				total = total +  (unitprice*rs.getInt(1));
				product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0)/100.0));
				product.setTotal_amount(total);
				product.setTotalqty(totalqty);*/
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return product;
	}

	private Product getPuchaseProductData(String catalogueid, String fromDate, String location_filter) {
		Product product = new Product();
		try {
			StringBuffer buffer = new StringBuffer();
		/*	buffer.append("select inventory_procurement.qty,purchaseprice,pack from inventory_procurement  ");
			buffer.append("inner join inventory_product on inventory_product.id = inventory_procurement.prodid ");
			buffer.append("where inventory_product.catalogueid="+catalogueid+" and inventory_procurement.date<'"+fromDate+"' and  procurement='0'  and inventory_procurement.deleted=0 ");
			if(!location_filter.equals("0")){
				buffer.append("and inventory_product.location='"+location_filter+"' ");
			}*/
			buffer.append("select sum(inventory_procurement.qty),sum(purchaseprice)/sum(pack),sum(inventory_procurement.qty*(purchaseprice/pack))+sum(inventory_product.freeqty * (purchaseprice/pack)),sum(inventory_product.freeqty),sum(inventory_procurement.qty)   ");
			buffer.append("from inventory_procurement ");
			buffer.append("inner join inventory_product on inventory_product.id = inventory_procurement.prodid ");
			buffer.append("where inventory_product.catalogueid="+catalogueid+" and inventory_procurement.date<'"+fromDate+"' and  procurement='0'  and inventory_procurement.deleted=0 and confirm=1 and gudreceipt=1 ");
			buffer.append("and inventory_procurement.iscancel=0 ");
			if(!location_filter.equals("0")){
				buffer.append("and inventory_product.location='"+location_filter+"' ");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double total=0.0;
			int totalqty=0;
			product.setTotal(DateTimeUtils.changeFormat(total));
			product.setTotalqty(totalqty);
			product.setTotal_amount(total);
			while (rs.next()) {
				/*totalqty = totalqty + rs.getInt(1);
				double pack =rs.getDouble(3);
				if(pack==0){
					pack=1.0;
				}
				double unitprice = rs.getDouble(2)/pack;
				total = total +  (unitprice*rs.getInt(1));*/
				/*totalqty = rs.getInt(1);
				total = rs.getDouble(3);*/
				product.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(3) * 100.0)/100.0));
				product.setTotalqty(rs.getInt(1));
				product.setTotal_amount(rs.getDouble(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}



	private void updateVendorIdInProduct() {
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//connection=DriverManager.getConnection("jdbc:mysql://59.97.236.76:3306/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://114.143.231.10:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/welltreat",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://localhost/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			StringBuffer buffer = new StringBuffer();
			buffer.append("select inventory_product.id,inventory_parent_procurement.vendorid from inventory_product ");
			buffer.append("inner join inventory_procurement on inventory_procurement.prodid = inventory_product.id ");
			buffer.append("inner join inventory_parent_procurement on inventory_parent_procurement.id = inventory_procurement.procurementid ");
			buffer.append("where supplierid is null");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			int i=0;
			while(rs.next()){
				String sql2="update inventory_product set supplierid='"+rs.getString(2)+"' where inventory_product.id ='"+rs.getInt(1)+"' ";
				PreparedStatement ps1=connection.prepareStatement(sql2);
				int res1 =ps1.executeUpdate();
				System.out.println(""+rs.getInt(1));
			}
			System.out.println("ok done");
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	private void updategenricName() {
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			connection=DriverManager.getConnection("jdbc:mysql://59.97.236.76:3306/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://114.143.231.10:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/welltreat",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			String sql="select id,genericname from inventory_catalogue where genericname!='GEN' and genericname!='GN' and genericname!='' and genericname is not null and genericname!='-' and genericname!='--' and genericname!='*';  ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			int i=0;
			while(rs.next()){
				String sql2="update inventory_product set genericname='"+rs.getString(2)+"' where inventory_product.catalogueid ='"+rs.getInt(1)+"' "
						+ "and (genericname='GEN' or genericname='GN' or genericname='' "
						+ "or genericname is null or genericname='-' or genericname='--' or genericname='*') ";
				PreparedStatement ps1=connection.prepareStatement(sql2);
				int res1 =ps1.executeUpdate();
			}
			System.out.println("ok done");
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	private void saveFreeQtyInProcurement() {
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//connection=DriverManager.getConnection(""+Constants.DB_HOST+"/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://114.143.231.10:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/welltreat",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			String sql="select freeqty,inventory_product.id from inventory_product inner join inventory_procurement on inventory_procurement.prodid =inventory_product.id where freeqty!='0' ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			int i=0;
			while(rs.next()){
				String sql2="update inventory_procurement set remainfreeqty='"+rs.getInt(1)+"' where inventory_procurement.prodid ='"+rs.getInt(2)+"' ";
				PreparedStatement ps1=connection.prepareStatement(sql2);
				int res1 =ps1.executeUpdate();
			}
			System.out.println("ok done");
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	private void updateProcurementPaymentParentid() {
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//connection=DriverManager.getConnection(""+Constants.DB_HOST+"/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://114.143.231.10:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/welltreat",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			String sql="SELECT inventory_paymentpo.id,commencing,payamount,vendorid FROM inventory_paymentpo inner join inventory_parent_procurement on inventory_parent_procurement.id =inventory_paymentpo.procurementid where inventory_paymentpo.parentid=0";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			int i=0;
			while(rs.next()){
				int result = 0;
				String sql1 = "insert into inventory_paymentpo_parent(vendorid, datetime, total_amt,totalpaid) values(?,?,?,?)";
				PreparedStatement preparedStatement = connection.prepareStatement(sql1);
				preparedStatement.setString(1, rs.getString(4));
				preparedStatement.setString(2, rs.getString(2));
				preparedStatement.setString(3, ""+rs.getDouble(3));
				preparedStatement.setString(4, ""+rs.getDouble(3));
				result = preparedStatement.executeUpdate();
				if(result>0){
					ResultSet rs1= preparedStatement.getGeneratedKeys();
					if(rs1.next()){
						result =rs1.getInt(1);
						String sql2="update inventory_paymentpo set parentid='"+result+"' where id='"+rs.getInt(1)+"' ";
						PreparedStatement ps1=connection.prepareStatement(sql2);
						int res1 =ps1.executeUpdate();
					}
				}
			}
			System.out.println("ok done");
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	private void checkProductCatalogueid() {
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//connection=DriverManager.getConnection("jdbc:mysql://localhost/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://114.143.231.10:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			String sql="select id,catalogueid,Procode from inventory_product  where location='105'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			int i=0;
			while(rs.next()){
					String user="select id from inventory_catalogue where id='"+rs.getInt(2)+"'";
					PreparedStatement statement = connection.prepareStatement(user);
					ResultSet mainset = statement.executeQuery();
					if (mainset.next()) {
						
					}else{
						String userquery="select id from inventory_catalogue where product_code='"+rs.getString(3)+"' and product_code!=''";
						PreparedStatement statement1 = connection.prepareStatement(userquery);
						ResultSet res = statement1.executeQuery();
						if(res.next()){
							String sql1="update inventory_product set catalogueid='"+res.getString(1)+"' where id='"+rs.getInt(1)+"' ";
							PreparedStatement ps1=connection.prepareStatement(sql1);
							int res1 =ps1.executeUpdate();
							System.out.println(rs.getInt(1));
							i++;
						}
					}
			}
			System.out.println("ok done");
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	private void saveProductCode() {
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//connection=DriverManager.getConnection("jdbc:mysql://localhost/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://114.143.231.10:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			String sql="select id,catalogueid from inventory_product  where location='105'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			int i=0;
			while(rs.next()){
					String user="select product_code from inventory_catalogue where id='"+rs.getInt(2)+"'";
					PreparedStatement statement = connection.prepareStatement(user);
					ResultSet mainset = statement.executeQuery();
					while (mainset.next()) {
						String sql1="update inventory_product set Procode='"+mainset.getString(1)+"' where id='"+rs.getInt(1)+"' ";
						PreparedStatement ps1=connection.prepareStatement(sql1);
						int res =ps1.executeUpdate();
						System.out.println(rs.getInt(1));
						i++;
					}
			}
			System.out.println("ok done");
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	private void aureusCatalogueToMaster() {
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/mpbetulsadar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://localhost/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://114.143.231.10:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://103.252.170.227:3306/nelson",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://58.84.56.17:3306/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			String sql="select id,categoryid,product_name,genericname from inventory_catalogue where location=33 group by product_name";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			int i=0;
			while(rs.next()){
					/*String user="select practitionerId,id from apm_invoice_assesments where chargeId='"+rs.getInt(1)+"' and practitionerId is not null";
					PreparedStatement statement = connection.prepareStatement(user);
					ResultSet mainset = statement.executeQuery();
					while (mainset.next()) {*/
					//insert into apm_condition (name,pharmacy) values (?,?)
				        String sql2="select drug from apm_medicine_details where drug=? ";
				        PreparedStatement ps2=connection.prepareStatement(sql2);
				        ps2.setString(1, rs.getString(3));
						ResultSet rs1=ps2.executeQuery();
						boolean flag = false;
						while (rs1.next()) {
							flag =true;
						}
						if(!flag){
							String sql1="insert into apm_medicine_details (categeory,drug,genericname,catalogueid) values (?,?,?,?) ";
							PreparedStatement ps1=connection.prepareStatement(sql1);
							ps1.setString(1, rs.getString(2));
							ps1.setString(2, rs.getString(3));
							ps1.setString(3, rs.getString(4));
							ps1.setString(4, rs.getString(1));
							int res =ps1.executeUpdate();
							System.out.println(rs.getInt(1));
							i++;
						
						}
					/*}*/
					
			}
			System.out.println("ok done");
			
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	




	private void neslonsetchargeshare() {
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://localhost/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://114.143.231.10:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://103.252.170.227:3306/nelson",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			String sql="select id from apm_appointment_type  where shareablecharge='1'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			int i=0;
			while(rs.next()){
					String user="select practitionerId,id from apm_invoice_assesments where chargeId='"+rs.getInt(1)+"' and practitionerId is not null";
					PreparedStatement statement = connection.prepareStatement(user);
					ResultSet mainset = statement.executeQuery();
					while (mainset.next()) {
						String sql1="update apm_invoice_assesments set sharedrid='"+mainset.getInt(1)+"',isshareablecharge=1 where id='"+mainset.getInt(2)+"' ";
						PreparedStatement ps1=connection.prepareStatement(sql1);
						int res =ps1.executeUpdate();
						System.out.println(rs.getInt(1));
						i++;
					}
					
			}
			System.out.println("ok done");
			
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	private void indentisfromrequestdata() {
	Connection connection =null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://localhost/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://114.143.231.10:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://103.252.170.227:3306/nelson",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		System.out.println("ok");
		String sql="select new_prodid from inventory_request_temp_log  where new_prodid!='0'";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs =ps.executeQuery();
		int i=0;
		while(rs.next()){
				
				String sql1="update inventory_product set isfromindent='1' where id='"+rs.getInt(1)+"' ";
				PreparedStatement ps1=connection.prepareStatement(sql1);
				int res =ps1.executeUpdate();
				System.out.println(rs.getInt(1));
				i++;
		}
		System.out.println("ok done");
		
		connection.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

private void indentisfromdirectdata() {
	Connection connection =null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://localhost/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://114.143.231.10:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://103.252.170.227:3306/nelson",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		System.out.println("ok");
		StringBuffer buffer= new StringBuffer();
		buffer.append("select new_prodid from inventory_transfer_log ");
		buffer.append("inner join inventory_parent_transfer_log on inventory_parent_transfer_log.id = inventory_transfer_log.parentid ");
		buffer.append("where req_trans_ret!='0' ");
		PreparedStatement ps=connection.prepareStatement(buffer.toString());
		ResultSet rs =ps.executeQuery();
		int i=0;
		while(rs.next()){
				
				String sql1="update inventory_product set isfromindent='1' where id='"+rs.getInt(1)+"' ";
				PreparedStatement ps1=connection.prepareStatement(sql1);
				int res =ps1.executeUpdate();
				System.out.println(rs.getInt(1));
				i++;
		}
		System.out.println("ok done");
		
		connection.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

private void indentisfromprocurmentdata() {
	Connection connection =null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://localhost/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://114.143.231.10:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://103.252.170.227:3306/nelson",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		System.out.println("ok");
		StringBuffer buffer= new StringBuffer();
		String sql="select prodid from inventory_procurement  where prodid is not null";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs =ps.executeQuery();
		int i=0;
		while(rs.next()){
				String sql1="update inventory_product set isfromindent='1' where id='"+rs.getInt(1)+"' ";
				PreparedStatement ps1=connection.prepareStatement(sql1);
				int res =ps1.executeUpdate();
				System.out.println(rs.getInt(1));
				i++;
		}
		System.out.println("ok done");
		
		connection.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}



	private void changeCatalogueid() {
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://localhost/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://114.143.231.10:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://103.252.170.227:3306/nelson",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			StringBuffer buffer = new StringBuffer();
			buffer.append("select id,product_name FROM inventory_catalogue where id>47750 ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			int i=0;
			while(rs.next()){
				String sql ="select id,prodname FROM inventory_product where id >36579 and location=34";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					if(rs.getString(2).equals(resultSet.getString(2))){
						String sql1="update inventory_product set catalogueid='"+rs.getString(1)+"' where id='"+resultSet.getInt(1)+"' ";
						PreparedStatement ps1=connection.prepareStatement(sql1);
						int res =ps1.executeUpdate();
						System.out.println("Catalogue id:"+ rs.getInt(1));
						System.out.println("Product id:"+ resultSet.getInt(1));
						System.out.println("------------------------------------");
						i++;
					}
				}
				System.out.println("============================================");
			}
			System.out.println("ok done");
			
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	private void saveCriticalValue() {
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			connection=DriverManager.getConnection("jdbc:mysql://localhost/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://114.143.231.10:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection=DriverManager.getConnection("jdbc:mysql://103.252.170.227:3306/nelson",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT apm_client_investigation.id,apm_investigation_name.critical_value FROM apm_client_investigation ");
			buffer.append("inner join apm_investigation_name on apm_investigation_name.id = apm_client_investigation.invnameid ");
			buffer.append("where apm_client_investigation.critical_value is null and apm_investigation_name.critical_value is not null ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			int i=0;
			while(rs.next()){
						String sql1="update apm_client_investigation set critical_value='"+rs.getString(2)+"' where id='"+rs.getInt(1)+"' ";
						PreparedStatement ps1=connection.prepareStatement(sql1);
						int res =ps1.executeUpdate();
					System.out.println(rs.getInt(1));
					i++;
			}
			System.out.println("ok done");
			
			connection.close();
			
		} catch (Exception e) {
			try {
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}




	private void checkOTIpdID() {
		Connection connection=null;
		 try {
			 
			   
			    Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
				//connection=DriverManager.getConnection("jdbc:mysql://114.143.231.10:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
				StringBuffer buffer = new StringBuffer();
				buffer.append("select ipdno,clientid,id from apm_available_slot where ipdno>'0' and discharge_procedure is not null ");
				PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					StringBuilder builder = new StringBuilder();
					builder.append("select id from ipd_addmission_form where clientid='"+rs.getString(2)+"' and id='"+rs.getString(1)+"'");
					PreparedStatement preparedStatement2 = connection.prepareStatement(builder.toString());
					ResultSet resultSet = preparedStatement2.executeQuery();
					if(resultSet.next()) {
						
					}else{
						String sql ="update apm_available_slot set ipdno='0' where id='"+rs.getInt(3)+"'";
						PreparedStatement statement = connection.prepareStatement(sql);
						int res = statement.executeUpdate();
						System.out.println(""+rs.getInt(3));
					}
					
				}
				
				connection.close();
			
				System.out.println("ok updated..");
				
			 
		} catch (Exception e) {

			e.printStackTrace();
		}
	}




	private void addAbrivationid() {
		Connection connection=null;
		 try {
			 
			   
			    Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
				//connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
				/*String sql="select id from apm_patient where id<7195 and id>2107";
				PreparedStatement ps=connection.prepareStatement(sql);
				ResultSet rs =ps.executeQuery();
				int x=2098;
				while(rs.next()){
					  String abivationid = "AKDC/2015"+x;
					  String sql2="update apm_patient set abrivationid='"+abivationid+"' where id="+rs.getInt(1)+" ";
					  PreparedStatement statement=connection.prepareStatement(sql2);
					  int res= statement.executeUpdate();
					  System.out.println(x);
					  x++;
					
				}*/
				StringBuffer buffer = new StringBuffer();
				buffer.append("select SUBSTRING_INDEX(lastModified, ' ', 1),count(id) from apm_patient ");
				buffer.append("where id>7194 and id<9883 group by SUBSTRING_INDEX(lastModified, ' ', 1) order by id ");
				PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					String date = rs.getString(1);
					String date2 = rs.getString(1)+" "+"59:59:59";
					StringBuilder builder = new StringBuilder();
					builder.append("select SUBSTRING_INDEX(lastModified, ' ', 1),id from apm_patient ");
					builder.append("where id>7194 and id<9883 ");
					builder.append("and lastModified between '"+date+"' and  '"+date2+"' order by id ");
					int x=1;
					PreparedStatement preparedStatement2 = connection.prepareStatement(builder.toString());
					ResultSet resultSet = preparedStatement2.executeQuery();
					while (resultSet.next()) {
						int id = resultSet.getInt(2);
						String[] lastdate = rs.getString(1).split("-"); 
						String[] year = lastdate[0].split("0");
						String abivationid = "AKDC"+year[1]+lastdate[1]+Integer.parseInt(lastdate[2])+x;
						String sql2="update apm_patient set abrivationid='"+abivationid+"' where id="+id+" ";
						PreparedStatement statement=connection.prepareStatement(sql2);
						int res= statement.executeUpdate();
						System.out.println(x+":"+id);
						x++;
					}
				}
				
				connection.close();
			
				System.out.println("ok updated..");
				
			 
		} catch (Exception e) {

			e.printStackTrace();
		}
	}


	private void updateCataloguIdToMedicine() {

		Connection connection=null;
		 try {
			 
			    int catalogueid= 0;
			    Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
				String sql="select id,drug from apm_medicine_details ";
				PreparedStatement ps=connection.prepareStatement(sql);
				ResultSet rs =ps.executeQuery();
				
				while(rs.next()){
					
					  int id= rs.getInt(1);
					  String product_name= rs.getString(2);
					  
					  catalogueid =getProdNameIdIfFound(connection,product_name);   
					  
					  String sql2="update apm_medicine_details set catalogueid="+catalogueid+" where id="+id+" ";
					  PreparedStatement statement=connection.prepareStatement(sql2);
					  int res= statement.executeUpdate();
					
				}
				
				connection.close();
			
				System.out.println("ok master medicine updated..");
				
			 
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
	}


	private int getProdNameIdIfFound(Connection connection, String product_name) {
			
		   int result=0;
			try {
				
				String sql="select id from inventory_catalogue where product_name='"+product_name+"' order by id limit 0,1 ";
				PreparedStatement ps=connection.prepareStatement(sql);
				ResultSet rs =ps.executeQuery();
				while(rs.next()){
					    
					   result =rs.getInt(1);
				}
				
				
			} catch (Exception e) {

				e.printStackTrace();
			}
		    return result;
	}


	public void addtoCatalogue(){
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/chraisnh",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			InventoryCatalogueDAO catalogueDAO =new JDBCInventoryCatalogueDAO(connection);
		   ArrayList<Product> list= new ArrayList<Product>();	
			
		   SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
		   String dateTime = f.format(GregorianCalendar.getInstance().getTime());

		   
		   StringBuffer buffer= new StringBuffer();
		   buffer.append("SELECT medicine_type,categoryid,prodname,mrp,purchaseprice,saleprice,vat,genericname,lastmodified,location,pack,mfg,hsnno,medicine_type ");
		   buffer.append("from inventory_product where mrp>0 and prodname is NOT NULL and catalogueid=0 ");
		   buffer.append("group by prodname ");
		   
		   PreparedStatement ps=connection.prepareStatement(buffer.toString());
		   ResultSet rs =ps.executeQuery();
		   while(rs.next()){
			   
			     Product product =new Product();
			     
			     int subid= rs.getInt(1);
			     if(subid==27){
			    	 subid=15;
			     }
			     if(subid==19){
			    	 subid=38;
			     }
			     if(subid==20){
			    	 subid=39;
			     }
			     if(subid==21){
			    	 subid=40;
			     }
			     if(subid==22){
			    	 subid=41;
			     }
			     if(subid==23){
			    	 subid=42;
			     }
			     if(subid==24){
			    	 subid=43;
			     }

			     if(subid==25){
			    	 subid=44;
			     }
			     if(subid==26){
			    	 subid=45;
			     }
			     if(subid==28){
			    	 subid=46;
			     }
			     if(subid==30){
			    	 subid=1;
			     }
			     if(subid>30){
			    	 subid=1;
			     }
			     
			    product.setSubcategory_id(String.valueOf(subid));
			     product.setCategory_id(rs.getString(2));
			     product.setProduct_name(rs.getString(3));
			     product.setMrp(rs.getString(4));
			     product.setPurchase_price(rs.getString(5));
			     product.setSale_price(rs.getString(6));
			     product.setVat(rs.getString(7));
			     if(product.getVat()==null){
			    	 product.setVat("0");
			     }
			     if(product.getVat().equals("")){
			    	 product.setVat("0");
			     }
			     product.setGenericname(rs.getString(8));
			     product.setLastmodified(rs.getString(9));
			     product.setLocation(rs.getString(10));
			     product.setPack(rs.getString(11));
			     product.setMfg(rs.getString(12));
			     product.setHsnno(rs.getString(13));
			     product.setDateTime(dateTime);
			     list.add(product);
			   
		   }
		   
			
		   for(Product product:list){
			       int catalogueid= catalogueDAO.isExistThisName(product.getProduct_name());
			       if(catalogueid==0){
			    	  catalogueid = catalogueDAO.saveNewCatalogue(product);
			    	   int res= catalogueDAO.updateCatalogueIdInProduct(catalogueid,product.getProduct_name());
			       } else {
			    	   int res= catalogueDAO.updateCatalogueIdInProduct(catalogueid,product.getProduct_name());
			       }
		   }
		   
		   System.out.println("ok..done");
		   
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	
	
	public void addCGSTTOLIFESPring() {
		
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/ngplifesp",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			String sql="select id,vat from apm_medicine_bill where cgst=0 and sgst=0 order by id desc;";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			
			while(rs.next()){
				
				 int id=rs.getInt(1);
				 double vat= rs.getDouble(2);
				 if(vat==0){
					 continue;
				 }
				 double gst= vat/2;
				 String  temp =DateTimeUtils.changeFormat(gst);
				 String sql1="update apm_medicine_bill set cgst=?,sgst=? where id="+id+" ";
				 PreparedStatement ps1=connection.prepareStatement(sql1);
				 ps1.setString(1, temp);
				 ps1.setString(2, temp);
				 
				 int res =ps1.executeUpdate();
				
			}
			System.out.println("ok done");
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
	}
	
	public void copyToSubcategory(){
		

		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/ngplifesp",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			
			String sql="select id,medicine_type from inventory_product";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				
				   int id=rs.getInt(1);
				   String type= rs.getString(2);
				   String sql1="update inventory_product set prodtypeid='"+type+"' where id="+id+" ";
				   PreparedStatement ps1=connection.prepareStatement(sql1);
				   int res= ps1.executeUpdate();
			}
			System.out.println("ok done");
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (Exception e2) {
			}
		}
	}
	
	
public void copyToConditionFromLocation(){
		

		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/chraisnh",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("ok");
			
			String sql="select name from apm_pharmacy_location";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				
				   String name= rs.getString(1);
				   String sql1="insert into apm_condition (name,pharmacy) values (?,?)";
				   PreparedStatement ps1=connection.prepareStatement(sql1);
				   ps1.setString(1, name);
				   ps1.setInt(2, 1);
				   
				   int res= ps1.executeUpdate();
			}
			System.out.println("ok done");
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (Exception e2) {
			}
		}
	}

public void addToMedicineMasterFromCatalogue(){
	

	Connection connection =null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/chraisnh",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		System.out.println("ok");
		
		String sql="select product_name,genericname from inventory_catalogue where categoryid=2 ";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs =ps.executeQuery();
		while(rs.next()){
			
			   String name= rs.getString(1);
			   String genericname= rs.getString(2);
			   String sql1="insert into apm_medicine_details (drug,genericname) values (?,?)";
			   PreparedStatement ps1=connection.prepareStatement(sql1);
			   ps1.setString(1, name);
			   ps1.setString(2, genericname);
			   int res= ps1.executeUpdate();
		}
		System.out.println("ok done");
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	finally {
		try {
			connection.close();
		} catch (Exception e2) {
		}
	}
}


   public void updateLocation() {
	   
	   Connection connection=null;
	      try {
	    	    Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/chraisnh",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
				System.out.println("ok");
				
				int loc=32;
				for(int i=1;i<=31;i++){
					
					String sql="update inventory_product_log set location="+loc+" where location="+i+"";
					PreparedStatement ps=connection.prepareStatement(sql);
					int res= ps.executeUpdate();
					loc++;
				}
			
				System.out.println("ok done");
				
		} catch (Exception e) {

			e.printStackTrace();
		}
	     finally {
	    	 try {
	    		 connection.close();
			} catch (Exception e2) {
			}
	     }
	      
   }
public void updateProdTyepe() {
	   
	   Connection connection=null;
	      try {
	    	    Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/chraisnh",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
				System.out.println("ok"); //48
				
					String sql="select catalogueid from inventory_product where medicine_type=31 group by catalogueid;";
					PreparedStatement ps=connection.prepareStatement(sql);
					ResultSet rs =ps.executeQuery();
					while(rs.next()){
						 
						   String sql1="update inventory_catalogue set subcategoryid=48 where id="+rs.getInt(1)+"";
						  PreparedStatement ps1=connection.prepareStatement(sql1);
						  int res= ps1.executeUpdate();
						
     				  }
 				
				System.out.println("ok done");
				
		} catch (Exception e) {

			e.printStackTrace();
		}
	     finally {
	    	 try {
	    		 connection.close();
			} catch (Exception e2) {
			}
	     }
	      
   }
   
public void addIndentIDs() {
	
	Connection connection =null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/ngplifesp",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		connection=DriverManager.getConnection("jdbc:mysql://localhost/chraisnh",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		System.out.println("ok");
		String sql="SELECT id,req_or_trans FROM inventory_parent_transfer_log";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs =ps.executeQuery();
		
		while(rs.next()){
			if(rs.getInt(2)==1){
				String sql1="update inventory_transfer_log set req_trans_ret=1 where parentid="+rs.getInt(1)+" ";
				PreparedStatement ps1=connection.prepareStatement(sql1);
				int res =ps1.executeUpdate();
			}else if(rs.getInt(2)==2){
				String sql1="update inventory_transfer_log set req_trans_ret=2 where parentid="+rs.getInt(1)+" ";
				PreparedStatement ps1=connection.prepareStatement(sql1);
				int res =ps1.executeUpdate();
			}
		}
		System.out.println("ok done");
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	
}
	
public void addFromCatalogue() {
	Connection connection =null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/ngplifesp",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		System.out.println("ok");
		String sql="SELECT id,product_name,gst FROM inventory_catalogue";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs =ps.executeQuery();
		int i=0;
		while(rs.next()){
				String sql1="update inventory_product set catalogueid='"+rs.getInt(1)+"',vat='"+rs.getString(3)+"' where prodname='"+rs.getString(2)+"' ";
				PreparedStatement ps1=connection.prepareStatement(sql1);
				int res =ps1.executeUpdate();
				System.out.println(""+rs.getString(2)+"");
				System.out.println(rs.getInt(1));
				i++;
		}
		System.out.println("ok done");
		
		connection.close();
		
	} catch (Exception e) {
		try {
			connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		e.printStackTrace();
	}
}


private void addPatientFullName() {
	Connection connection =null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		connection=DriverManager.getConnection("jdbc:mysql://localhost/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://114.143.231.10:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://103.252.170.227:3306/nelson",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		System.out.println("ok");
		String sql="SELECT id,firstname,surname,fullname FROM apm_patient where fullname is null";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs =ps.executeQuery();
		int i=0;
		while(rs.next()){
				if(rs.getString(4)==null){
					String fullname = rs.getString(2)+" "+rs.getString(3);
					String sql1="update apm_patient set fullname='"+fullname+"' where id='"+rs.getInt(1)+"' ";
					PreparedStatement ps1=connection.prepareStatement(sql1);
					int res =ps1.executeUpdate();
				}
				System.out.println(rs.getInt(1));
				i++;
		}
		System.out.println("ok done");
		
		connection.close();
		
	} catch (Exception e) {
		try {
			connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		e.printStackTrace();
	}
}

private void addlastmodifieddata() {
	Connection connection =null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://114.143.231.10:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://localhost/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		System.out.println("ok");
		String sql="select prodid,date FROM inventory_procurement where prodid is not null";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs =ps.executeQuery();
		int i=0;
		while(rs.next()){
				String sql1="update inventory_product set added_date='"+rs.getString(2)+"' where id='"+rs.getInt(1)+"' and added_date is null ";
				PreparedStatement ps1=connection.prepareStatement(sql1);
				int res =ps1.executeUpdate();
				System.out.println(rs.getInt(1));
				i++;
		}
		System.out.println("ok done");
		
		connection.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

private void addindentlastmodifieddata() {
	Connection connection =null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://localhost/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		connection=DriverManager.getConnection("jdbc:mysql://114.143.231.10:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		System.out.println("ok");
		String sql="select new_prodid,transfer_date from inventory_request_temp_log  where new_prodid!='0'";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs =ps.executeQuery();
		int i=0;
		while(rs.next()){
				String[] datetime = rs.getString(2).split(" ");
				String sql1="update inventory_product set added_date='"+datetime[0]+"' where id='"+rs.getInt(1)+"' and added_date is null";
				PreparedStatement ps1=connection.prepareStatement(sql1);
				int res =ps1.executeUpdate();
				System.out.println(rs.getInt(1));
				i++;
		}
		System.out.println("ok done");
		
		connection.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

private void addirectindetlastmodifieddata() {
	Connection connection =null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://localhost/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		connection=DriverManager.getConnection("jdbc:mysql://114.143.231.10:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		System.out.println("ok");
		StringBuffer buffer= new StringBuffer();
		buffer.append("select new_prodid,request_date from inventory_transfer_log ");
		buffer.append("inner join inventory_parent_transfer_log on inventory_parent_transfer_log.id = inventory_transfer_log.parentid ");
		buffer.append("where req_trans_ret!='0'");
		PreparedStatement ps=connection.prepareStatement(buffer.toString());
		ResultSet rs =ps.executeQuery();
		int i=0;
		while(rs.next()){
				String[] datetime = rs.getString(2).split(" ");
				String sql1="update inventory_product set added_date='"+rs.getString(2)+"' where id='"+rs.getInt(1)+"' and added_date is null";
				PreparedStatement ps1=connection.prepareStatement(sql1);
				int res =ps1.executeUpdate();
				System.out.println(rs.getInt(1));
				i++;
		}
		System.out.println("ok done");
		
		connection.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

private void AddDataToSeqNo(String itype){
	Connection connection= null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		String sql="select id from apm_charges_invoice where itype='"+itype+"' and id>12805 order by date, time";
		int count=1189;
		connection=DriverManager.getConnection("jdbc:mysql://103.242.186.21:3306/chraibgh",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		System.out.println("ok");
		PreparedStatement ps= connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		while (rs.next()) {
			String sql1="";
			if(itype.equals("1")){
				sql1=" update apm_charges_invoice set opdseqno="+count+" where id="+rs.getInt(1)+"";
			}else if (itype.equals("2")) {
				sql1=" update apm_charges_invoice set ipdseqno="+count+" where id="+rs.getInt(1)+"";
			}else if (itype.equals("3")) {
				sql1=" update apm_charges_invoice set invstseqno="+count+" where id="+rs.getInt(1)+"";
			}else if (itype.equals("6")) {
				sql1=" update apm_charges_invoice set vaccinationseqno="+count+" where id="+rs.getInt(1)+"";
			}
			PreparedStatement ps2= connection.prepareStatement(sql1); 
			int x= ps2.executeUpdate();
			System.out.println(count);
			count= count+1;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}

private void generateProductWiseDiscount() throws SQLException {
	Connection connection=null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://localhost/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		System.out.println("ok");
		StringBuffer buffer= new StringBuffer();
		buffer.append("    select apm_medicine_bill.id,discount,debit,(charge*quantity),apm_medicine_charges.id from apm_medicine_bill ");
		buffer.append("  inner join apm_medicine_charges on apm_medicine_bill.id=apm_medicine_charges.invoiceid ");
		buffer.append(" where apm_medicine_bill.date  between '2018-05-01' and '2018-09-30'  ");
		PreparedStatement ps= connection.prepareStatement(buffer.toString());
		ResultSet rs= ps.executeQuery();
		double debit=0.0;
		double shareinbill=0.0;
		double shareindiscount=0.0;
		while(rs.next()){
			debit=rs.getDouble(3)+rs.getDouble(2);
			//if disc is not zero
			if(rs.getInt(2)!=0){
				shareinbill=rs.getDouble(4)/debit;
				shareindiscount= shareinbill*rs.getDouble(2);
				shareindiscount=Double.parseDouble(String.format("%.2f", shareindiscount));
				int x=updateDiscInCharge(connection, rs.getInt(5), shareindiscount);
				
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		connection.close();
	}
}
	
private int updateDiscInCharge(Connection connection, int id,double amount){
	int res=0;
	try {
		String sql="update apm_medicine_charges set discount_share='"+amount+"' where id="+id+"";
		PreparedStatement ps= connection.prepareStatement(sql);
		res= ps.executeUpdate();
		System.out.println(id);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

private void delteRepeatedCGHS(){
Connection connection= null;
PreparedStatement ps= null;
try {
	Class.forName("com.mysql.jdbc.Driver");
	//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/akdcnagpur",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
	//connection=DriverManager.getConnection("jdbc:mysql://localhost/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
	connection=DriverManager.getConnection("jdbc:mysql://114.143.231.10:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
	String sql="select count(*),name from apm_appointment_type where chargeType='CGHS'  group by name having count(*)>1 ";
	ps=connection.prepareStatement(sql);
	ResultSet rs= ps.executeQuery();
	
	while(rs.next()){
		String sql1= "select id from apm_appointment_type where name='"+rs.getString(2)+"' and chargeType='CGHS'";
		PreparedStatement preparedStatement= connection.prepareStatement(sql1);
		ResultSet rs1= preparedStatement.executeQuery();
		int count =0;
		while(rs1.next()){
			count= count+1;
			if(count>1){
				String sql2="delete from apm_appointment_type where id="+rs1.getInt(1)+" and chargeType='CGHS'";
				PreparedStatement ps2= connection.prepareStatement(sql2);
				int x= ps2.executeUpdate();
			}
		}
		
	}
} catch (Exception e) {
	e.printStackTrace();
}
}

private void setMedicinePaymentFlagForCredit(){
	PreparedStatement ps= null;
	Connection connection= null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		StringBuffer buffer= new StringBuffer();
		buffer.append("   select billno from apm_medicine_bill     inner join apm_medicine_payment on apm_medicine_bill.id=apm_medicine_payment.billno   ");
		buffer.append("    where apm_medicine_bill.date between '2018-11-01' and '2018-11-01'  ");
		buffer.append("   and (apm_medicine_bill.tpid=0 or apm_medicine_bill.tpid is NULL) and apm_medicine_bill.isreturn=0   ");
		buffer.append("    group by apm_medicine_payment.billno having count(billno)>1   ");
	
		ps= connection.prepareStatement(buffer.toString());
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			PreparedStatement ps2= connection.prepareStatement(" update apm_medicine_payment set paymentflag='1'  where paymode= 'Credit' and billno='"+rs.getString(1)+"' ");
			int x=ps2.executeUpdate();
			System.out.println(""+rs.getString(1));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}

private void genertaeIpdAbbrId(){
	PreparedStatement ps= null;
	Connection connection= null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://59.97.236.76:3306/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
	StringBuffer buffer= new StringBuffer();
	buffer.append("  select id,admissiondsate from ipd_addmission_form where admissiondsate between '2018-01-01 00:00:00' and '2018-12-31 23:59:59' and casualty='1' and ipdabrivationid is null ");
	
	ps= connection.prepareStatement(buffer.toString());
	IpdDAO ipdDAO= new JDBCIpdDAO(connection);
	ResultSet rs= ps.executeQuery();
	while(rs.next()){
		String date= rs.getString(2);
		String ipdid= rs.getString(1);
		String temp1[]= date.split("-");
		//temp0 is ipdabid year
		String year= temp1[0];
		String action="0";
		 int maxipdabr=ipdDAO.getMaxIPDAbrivation(year,action);
		 String ipdabrfinal="";
		 int yr = Integer.parseInt(year)%1000;
		    int newmaxipdabr=maxipdabr+1;
		    String temp= String.valueOf(newmaxipdabr);
		    int l=temp.length();
		    String ipdabrstr="";
		    if(l==3){
		    	ipdabrstr="0"+newmaxipdabr;
		    }else if(l==2){
		    	ipdabrstr="00"+newmaxipdabr;
		    }else if(l==1){
		    	ipdabrstr="000"+newmaxipdabr;
		    }else{
		    	ipdabrstr=""+newmaxipdabr;
		    }
		    String abrivaationcode=ipdDAO.getclinicAbrivationCode();
		    ipdabrfinal=abrivaationcode+"IP/"+yr+"/"+ipdabrstr;
		    
		    int res=ipdDAO.insertIPDAbrivation(year, newmaxipdabr,action);

		    PreparedStatement ps2= connection.prepareStatement("update  ipd_addmission_form set ipdabrivationid=? where id='"+ipdid+"' ");
		    ps2.setString(1, ipdabrfinal);
		    int res1=ps2.executeUpdate();
		    System.out.println(""+ipdabrfinal);
	}
	} catch (Exception e) {
		e.printStackTrace();
	}
}

private void setPayment(){
	try {
		Connection connection= null;
		StringBuffer buffer= new StringBuffer();
		Class.forName("com.mysql.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
	
		buffer.append("  select initial_paymode,final_paymode,paymode,billno from apm_medicine_bill   ");
		buffer.append("  inner join apm_medicine_payment on apm_medicine_payment.billno=apm_medicine_bill.id   ");
		buffer.append("   and apm_medicine_payment.date between '2019-01-01'  and '2019-01-29'  ");
		buffer.append("    where final_paymode !=paymode and paymode!='Credit' ");
		PreparedStatement ps= connection.prepareStatement(buffer.toString());
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			String sql="update apm_medicine_bill set initial_paymode ='"+rs.getString(3)+"' where id='"+rs.getInt(4)+"'";
			PreparedStatement ps1=connection.prepareStatement(sql);
			int x= ps1.executeUpdate();
			System.out.println(""+rs.getInt(4));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}
private void generatedatabackend(){
	Connection connection= null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		//connection=DriverManager.getConnection("jdbc:mysql://59.97.236.76:3306/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://114.143.231.10:3306/sevenstar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/welltreat",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		connection=DriverManager.getConnection("jdbc:mysql://58.84.56.17:3306/aureus",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		String fromdate="2019-06-05";
		String todate="2019-06-05";
		
		String sql="select id,discount,debit from apm_medicine_bill where id in(45665,45666,45305)  ";
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("  SELECT apm_medicine_bill.id,discount,debit,discount,invoiceid,(debit),(sum(charge *quantity)-sum(discount_share)),   ");
		buffer.append("  ((sum(charge *quantity)-sum(discount_share)) - debit )as difference,date   ");
		buffer.append("  FROM apm_medicine_charges inner join apm_medicine_bill on apm_medicine_bill.id=apm_medicine_charges.invoiceid   ");
		buffer.append("    where date between '"+fromdate+"' and '"+todate+"'  ");
		buffer.append("  group by invoiceid   ");
		buffer.append("     ");
		
		
		PreparedStatement ps= connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		
		while(rs.next()){
			double disc=rs.getDouble(2);
			double debit=rs.getDouble(3);
			double x=(disc/debit)*100;
			System.out.println(""+fromdate+"    ----"+rs.getString(1));
			if(true){
				double prodamt=getproductAmt(rs.getString(1),connection);
				double actual_disc= prodamt-debit;
				double disc_per=(actual_disc/prodamt)*100;
			/*int i=getTheBillChargeDone(rs.getString(1),connection);*/
				int i = settleextagst(rs.getString(1), disc_per, connection);
			}
		}
		System.out.println("Done All");
	}catch(Exception e){
		e.printStackTrace();
	}
}
	private int getTheBillChargeDone(String billid,Connection connection){
		try {
			
			StringBuffer buffer= new StringBuffer();
			buffer.append(" select (charge*quantity)-(discount_share),inventory_product.vat,apm_medicine_charges.id from apm_medicine_charges ");
			buffer.append(" inner join inventory_product on apm_medicine_charges.product_id =inventory_product.id  ");
			buffer.append(" where invoiceid='"+billid+"' and vat!='0' ");
			
			PreparedStatement ps= connection.prepareStatement(buffer.toString());
			ResultSet rs= ps.executeQuery();
			double total=0;
			while(rs.next()){
				double productcost=rs.getDouble(1);
				int gst= rs.getInt(2);
				double gstamt= (productcost)-(productcost*(100.0/(100.0+gst)));
				System.out.println(gstamt);
				total=total+gstamt;
				gstamt= Double.parseDouble(new DecimalFormat("##.##").format(gstamt));
				updateGSTInCharge(rs.getString(3), gstamt,connection);
				
			}
			int res=updateGSTinBill(billid, total,connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	private int updateGSTInCharge(String chargeid,double amt,Connection connection){
		try {
			double cgst=Double.parseDouble(new DecimalFormat("##.##").format((amt)/2.0));;
			double tgst=Double.parseDouble(new DecimalFormat("##.##").format((amt)));
			
			PreparedStatement ps=connection.prepareStatement("update apm_medicine_charges set cgst='"+cgst+"' , sgst='"+cgst+"',tgstamt='"+tgst+"' where id='"+chargeid+"'");
			int i=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	private int updateGSTinBill(String chargeid,double amt,Connection connection){
		try {
			double cgst=Double.parseDouble(new DecimalFormat("##.##").format((amt)/2.0));;
			
		
			PreparedStatement ps=connection.prepareStatement("update apm_medicine_bill set cgst='"+cgst+"' , sgst='"+cgst+"',vat='"+amt+"' where id='"+chargeid+"'");
			int i=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
private double getproductAmt(String billid,Connection connection){
	double amt= 0;
	try {
		PreparedStatement ps= connection.prepareStatement("select sum(charge* quantity) from apm_medicine_charges where invoiceid='"+billid+"' ");
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			amt= rs.getDouble(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return amt;
}

private int settleextagst(String invid, double disc_per,Connection connection){
	int res=0;
	try {
		StringBuffer buffer=new StringBuffer();
		buffer.append("    select (charge*quantity),inventory_product.vat,apm_medicine_charges.id from apm_medicine_charges   ");
		buffer.append("  	inner join inventory_product on apm_medicine_charges.product_id =inventory_product.id   ");
		buffer.append("  where invoiceid='"+invid+"' ");
		PreparedStatement ps = connection.prepareStatement(""+buffer.toString());	
		ResultSet rs = ps.executeQuery();
		double total=0;
		while(rs.next()){
			double prodwithdisc=(disc_per/100.0)*rs.getDouble(1);
			double actualprodprice=rs.getDouble(1)-prodwithdisc;
			double gstamt= (actualprodprice)-(actualprodprice*(100.0/(100.0+rs.getDouble(2))));
			int x=updateGSTInCharge(rs.getString(3), gstamt, connection);
			total=total+gstamt;
			gstamt= Double.parseDouble(new DecimalFormat("##.##").format(gstamt));
		
		}
		int res1=updateGSTinBill(invid, total,connection);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return 0;
}

private void updateIpdInCharges(){
	String fromdate="2018-06-01";
	String todate="2018-06-31";
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/nelson",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		
		PreparedStatement ps= connection.prepareStatement("select id, ipdid from apm_charges_invoice where itype='2' and ipdid !='0' and date between '"+fromdate+"' and '"+todate+"' ");
		ResultSet rs= ps.executeQuery();
		while (rs.next()) {
			int ipdid= rs.getInt(2);
			int maininvoiceid=rs.getInt(1);
			String chargeids=getchargeids(connection, maininvoiceid);
			System.out.println("************"+chargeids);
			PreparedStatement ps2= connection.prepareStatement("update apm_invoice_assesments set ipdid='"+ipdid+"' where invoiceid in ("+chargeids+")");
			int update=ps2.executeUpdate();
		}
		System.out.println("+++++++++++++++Done");
	} catch (Exception e) {
		e.printStackTrace();
	}
}

private String getchargeids(Connection connection, int maininvoiceid){
	String res="0";
	try {
		PreparedStatement ps=connection.prepareStatement("select invoiceid from apm_charges_assesment where chargeinvoiceid='"+maininvoiceid+"'");
		ResultSet rs= ps.executeQuery();
		while (rs.next()) {
			res=res+","+rs.getInt(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}


private void testDouble(){
	try {
		double number=1000.0;
		DecimalFormat twoPlaces = new DecimalFormat("0.00");
		for(int i=0;i<5;i++){
			System.out.println("-88->> "+new Double((twoPlaces.format(number))));
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

private void genrateseqdate(){
	try {
		
	    
	    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    Calendar cal = Calendar.getInstance();
	    String todaydate = dateFormat.format(cal.getTime());
	    String endfinancialyeardate="31-03-"+todaydate.split("-")[2];
	    
	    todaydate="01-04-2019";
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date today = sdf.parse(todaydate);
        Date lastdayoffinyr = sdf.parse(endfinancialyeardate);
	    
	    if(today.after(lastdayoffinyr)){
	    	System.out.println("today After final date");
	    }
	    if(today.before(lastdayoffinyr)){
	    	System.out.println("today before final date");
	    }
	    if(today.equals(lastdayoffinyr)){
	    	System.out.println("today before final date");
	    }
	    
	    int year=Integer.parseInt(todaydate.split("-")[2]);
    	
    	String yearrange=year+"-"+(year+1); 
    	System.out.println("today before final date"+yearrange);
    	
	} catch (Exception e) {
		e.printStackTrace();
	}
}


public void jsonPrint(){
	try {
		JSONObject jsonObject= new JSONObject();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}



public void asisgnInvoiceIdToCharges(){
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/chraibgh",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		
		PreparedStatement ps=connection.prepareStatement("select id from apm_charges_invoice ");
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			String maininvoiceid=rs.getString(1);
			PreparedStatement ps2=connection.prepareStatement(" select invoiceid from apm_charges_assesment where chargeinvoiceid='"+maininvoiceid+"'");
			ResultSet rs2=ps2.executeQuery();
			while (rs2.next()) {
				PreparedStatement ps3=connection.prepareStatement("update apm_invoice_assesments set invoiced='"+maininvoiceid+"' where  invoiceid='"+rs2.getString(1)+"' and invoiced='0'");
				int x=ps3.executeUpdate();
				System.out.println("id----"+rs2.getString(1));
			}
		}
		System.out.println("DoneAll");
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void saveDebitOfReturnPharmacy(){
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/chraibgh",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		String sql=" select id,debit,refundamt from apm_medicine_bill where debit=0 and isreturn='1' and refundamt!=0 ";
		PreparedStatement ps=connection.prepareStatement("select id from apm_charges_invoice ");
	} catch (Exception e) {
		e.printStackTrace();
	}
}



public void callapi(){
	StringBuilder strBuf = new StringBuilder();  
    
    HttpURLConnection conn=null;
    BufferedReader reader=null;
    try{  
        //Declare the connection to weather api url
        URL url = new URL("http://yuvicare.com:8080/YUVIAPI/rest/statelist");  
        conn = (HttpURLConnection)url.openConnection();  
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("clinicid", "balgopal");
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("HTTP GET Request Failed with Error code : "
                          + conn.getResponseCode());
        }
        
        //Read the content from the defined connection
        //Using IO Stream with Buffer raise highly the efficiency of IO
        reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
        String output = null;  
        while ((output = reader.readLine()) != null)  
        {
            strBuf.append(output);  
            System.out.println(output);
        }
       
    }catch(MalformedURLException e) {  
        e.printStackTrace();   
    }catch(IOException e){  
        e.printStackTrace();   
    }
    finally
    {
        if(reader!=null)
        {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null)
        {
            conn.disconnect();
        }
    }

}
public static void exceptionMail( String excenotes) {

	Connection connection = null;
	
	try{
		
		String to = "shubham.dharammali@manasindustry.com";
		String cc = "";
		
		String notes=excenotes;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String d1 = dateFormat.format(date);
		String[] temp = d1.split("\\s+");
		String date1 = temp[0];
		String time = temp[1];
		String subject = "Exception " +date1+" "+time;
		String type = "Letter";
		
    	 String from = "yuvicare.hims@manasindustry.com";
	     String host = "smtp.zoho.com";
	     final String username = "yuvicare.hims@manasindustry.com";
		 final String password = "CareYuvi#$2807";
		 
	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);   
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	            }
	      });

	      
	       Message message = new MimeMessage(session);

	   	   message.setFrom(new InternetAddress(from));

		   message.setRecipients(Message.RecipientType.TO,
	              InternetAddress.parse(to));

		   message.setSubject(subject);

		   message.setContent(notes,"text/html");
		   			   
   			String status = "Not Delivered";
   			Transport.send(message);          
   			status = "Delivered";
   			System.out.println(""+status);
   			
	      } catch (MessagingException e) {
	    	  e.printStackTrace();
	    	  throw new RuntimeException(e);
	      } catch (Exception e) {
	    	  e.printStackTrace();
		}
		System.out.println("Done");
	

}

public void updatenewVaccine() {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/parihar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		//Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.2.150/raibhattar",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		String sql="select clientid from apm_vacination_data where clientid between 43490 and 47716 group by clientid order by (clientid+0)  ";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			String clientid=rs.getString(1);
			String type ="0";
			//immunizationProcess(clientid, type,false,connection);
			Client client = clientDAO.getPatient(Integer.parseInt(clientid));
			ArrayList<Master> vacinlist = new ArrayList<Master>();
			vacinlist=notAvailableSlotDAO.getVaccineListbyid(0,client);
			for (Master master : vacinlist) {
				String dob = client.getDob();

				dob = DateTimeUtils.getCommencingDatePicker(dob);
				int schedule = Integer.parseInt(master.getVacine_scheduledon());
				SimpleDateFormat birthDate = new SimpleDateFormat("dd-MM-yyyy");
				Date date = birthDate.parse(dob);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				String scheduledate = "";
				cal.add(Calendar.DAY_OF_YEAR, schedule);
				scheduledate = birthDate.format(cal.getTime());
				master.setScheduledate(scheduledate);
				int count=notAvailableSlotDAO.getClientCount(clientid,master.getId());
				if(count==0) {
				int x = notAvailableSlotDAO.setdatatoVacinationInfo1(master, clientid);
				System.out.println(clientid);
				}
			}
			
		}
		System.out.println("Done");
	} catch (Exception e) {
		e.printStackTrace();
	}
}



}







