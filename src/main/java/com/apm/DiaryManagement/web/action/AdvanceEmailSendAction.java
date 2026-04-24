package com.apm.DiaryManagement.web.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.ChargesAccountProcessingDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCChargeAccountProcesDAO;
import com.apm.Accounts.web.action.ChargeAccountProcessingAction;
import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.Appointment.eu.entity.Appointment;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCCompleteAptmDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Master.eu.entity.Master;
import com.apm.Mis.eu.bi.MisChartDAO;
import com.apm.Mis.eu.blogic.jdbc.JDBCMisChartDAO;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Tools.web.action.AllTemplateAction;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;

import com.opensymphony.xwork2.ActionContext;

public class AdvanceEmailSendAction  extends BaseAction{
	
	
	HttpServletRequest request = ServletActionContext.getRequest();
//	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpSession session = request.getSession(true);
	
	String result = "";
	
	
	
	@Override
	public String execute() throws Exception {
		/*for(int i=0;i<50;i++)

		{
			
			//EmbeddedImageEmailUtil.sendMailForForgotPassword("manojmishra443@gmail.com", "", "hello world", "hello world");

		System.out.println(i);

		}*/
		
		
		if(session.getAttribute("logininfo")!=null){
				
			
			/*for(int i=0;i<100;i++)

			{
				
				EmbeddedImageEmailUtil.sendMailForForgotPassword("manojmishra443@gmail.com", "", "hello world", "hello world");

			System.out.println(i);

			}*/
			
			
			Connection connection = null;
			LoginInfo loginInfo =  (LoginInfo)session.getAttribute("logininfo");
			try{
				
						
				
				AllTemplateAction allTemplateAction = new AllTemplateAction();
				
				connection = getconnection(loginInfo.getDbName());
				NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
				
				int duration = notAvailableSlotDAO.getAdDurationData(loginInfo.getClinicid());
				
				if(duration!=0){
					DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
					//get remainder date
					
					String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					
					String temp[] = date.split(" ");
					
					String dt = temp[0];
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar c = Calendar.getInstance();
					c.setTime(sdf.parse(dt));
					
					
					
					c.add(Calendar.DATE, duration);  // number of days to add
					dt = sdf.format(c.getTime());  // dt is now the new date
					
					c.add(Calendar.DATE, -duration);
					String emailsenddate = sdf.format(c.getTime());
//					System.out.println(emailsenddate);
					
					ArrayList<String>apmtidList = notAvailableSlotDAO.getRemainderAppointmentList(dt);
					
					if(apmtidList.size()>0){
						for(String appointmentid : apmtidList ){
							ArrayList<NotAvailableSlot>appointmentList = diaryManagementDAO.getAppintmentSendMailDetails(Integer.parseInt(appointmentid));
							
							for(NotAvailableSlot notAvailableSlot : appointmentList){
								UserProfile userProfile = notAvailableSlot.getUserDEtails();
								Client client = notAvailableSlot.getClientDetails();
								Location location = notAvailableSlot.getLocationDetails();
								
								
								
								
								if(emailsenddate.equals(temp[0]) && notAvailableSlot.getAd()==0)	{
									allTemplateAction.emailSend(userProfile.getFullname(), client.getFirstName(), notAvailableSlot.getSTime(), notAvailableSlot.getDuration(), userProfile.getEmail(), client.getEmail(), notAvailableSlot.getCommencing(), location.getLocationname(),location.getContactNo(),location.getAddress(),location.getLocationid(),notAvailableSlot.getClientId(),userProfile.getQualification(),Integer.parseInt(appointmentid),userProfile.getDiciplineName(),connection,loginInfo);
									
									int upd = notAvailableSlotDAO.updateAD(appointmentid);
									
									  //send sms
									
								    ClientDAO clientDAO = new JDBCClientDAO(connection);
								  //  NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
								    NotAvailableSlot notAvailableSlot1 = notAvailableSlotDAO.getApmtDetailsForLog(Integer.parseInt(appointmentid));
								    UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
								    Client clients = clientDAO.getClientDetails(notAvailableSlot1.getClientId());
								    UserProfile userProfiles  = userProfileDAO.getUserprofileDetails(notAvailableSlot1.getDiaryUserId());
								   
								    String message = AllTemplateAction.getAppointmentSMSText(notAvailableSlot1.getClientId(), Integer.parseInt(appointmentid), connection, loginInfo);
								    

								    
								    if(loginInfo.getCountry().equals("India")){
								    	String templateid = userProfileDAO.getSMSTemplateID("Appointment SMS");
								    	SmsService s = new SmsService();
								    	s.sendSms(message, clients.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
//								    	s.sendSms(message, userProfiles.getMobile(), loginInfo, new EmailLetterLog());
								    }
								    
								}
								
							}
						}
						
					}
				}
				
			
				
				BedDao bedDao=new JDBCBedDao(connection);
				ClientDAO clientDAO= new JDBCClientDAO(connection);
				CompleteAptmDAO completeAptmDAO= new JDBCCompleteAptmDAO(connection);
				AppointmentTypeDAO appointmentTypeDAO= new JDBCAppointmentTypeDAO(connection);
				// for auto std charge setup 
				
				String date1= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
				String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			   
		/*		try {
					
					ArrayList<Bed> ipdPatientStdSetupList= bedDao.getAllIpdStdPatientList();
					for(Bed bed :ipdPatientStdSetupList){
						
						
						CompleteAppointment appointment=new CompleteAppointment();
		    			appointment.setClientId(bed.getClientid());
		    			appointment.setPractitionerId(bed.getPractitionerid());
		    			appointment.setChargeType("Charge");
		    			appointment.setLocation("1");
		    		    appointment.setAdditionalcharge_id("001");
		    		    appointment.setIpdid(bed.getId());
		    		    appointment.setInvoiceDate(date1);
		    		    appointment.setIpd("1");
		    		    appointment.setAppointmentid("0");
		    		    appointment.setGinvstid("0");
		    		    appointment.setWardid(bed.getWardid());
		    		    
		    		    Client client= clientDAO.getClientDetails(bed.getClientid());
		    		    
		    		    if(client.getWhopay()!=null){
		    		    	
		    		    	if(client.getWhopay().equals("Self") || client.getWhopay().equals("Client")){
		    		    	       
		    		    		appointment.setPolicyExcess("0");
		    		    		appointment.setPayBuy("0");
		    		    	} else {
		    		    		appointment.setPolicyExcess("1");
		    		    		appointment.setPayBuy("1");
		    		    	}
		    		    }
		    		    
		    		    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    		    Calendar calendar=Calendar.getInstance();
		    		    Date date= f.parse(bed.getAdmissiondate());
		    		    calendar.setTime(date);
		    		    calendar.add(Calendar.DATE, 1);
		    		    
		    		    String nowdateString = f.format(calendar.getTime());
		    		    int res= datetime.compareTo(nowdateString);
		    		    int invoiceid=0;
		    		    if(res==0 || res>0) {   //nowdate is greater or equal to another
		    		    	
		    		    	ArrayList<Master> chargeList=appointmentTypeDAO.getStandardChargeList(bed.getWardid(), bed.getTpid(), client.getWhopay());
		    		    	
		    		    	if(chargeList.size()!=0){
		    		    		invoiceid=completeAptmDAO.saveStndCharge(appointment.getClientId(),bed.getIpdid(), "0");
			        		    invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId());
			        		    res =bedDao.updateStdChargeSetup(bed.getIpdid());
		    		    	}
		        		    
		        		    String nowDate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
		        			String stddate= bed.getAdmissiondate().split(" ")[0];
		        			long diff= DateTimeUtils.getDifferenceOfTwoDateDBFormat(stddate, nowDate);
		        			int qty =(int) diff;
		        			if(qty<0){
		        				 qty=0;
		        			}
		        			if(qty==0){
		        				qty=1;
		        			} else {
		        				//qty++;
		        			}
		        		    
		        		    String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
		        		    appointment.setUser(fullname);
		        		    appointment.setCommencing(date1);    
		        		    
		        		    for(Master master:chargeList){
		      		    	  
		     		    	   String chargeId=String.valueOf(master.getId());
		     		    	   appointment.setApmtType(master.getName());
		     		    	   appointment.setCharges(master.getCharge());
		     		    	   appointment.setAdditionalcharge_id(chargeId);
		     		    	   appointment.setMasterchargetype("Bed Charge");
		     		    	   //appointment.setMasterchargetype("Accommodation Charges");
		     		    	   appointment.setQuantity(qty);
		     		    	   appointment.setStdflag("1");
		     		    	   res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
		         		       int result= appointmentTypeDAO.saveStdCharge(bed.getIpdid(),chargeId,res,"1",datetime,"");
		     		    	   
		     		       }
		        		    
		    		    }
		    		    
						
					}
					
					
					
				} catch (Exception e) {

				} */
			
				
				
				
				
				
				
				
				
				
				
				
				
			
				

				//for auto charge
			//	ClinicDAO clinicDAO=new JDBCClinicDAO(connection);
				
				ArrayList<Bed> bedlist=bedDao.getAllIpdActivePatients();
				String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				String stemp[] = date.split(" ");
				
				
				String stime[]=stemp[1].split(":");
				String time=stemp[1];
				                  				
				date =stemp[0];
				
				String nowdate= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				for(Bed bed:bedlist) {
					
					 /*if(bed.getClientid().equals("16036")){
							System.out.println("hello"); 
						 }*/
					    // if autocharges is on
					    boolean isAutochargeOn= completeAptmDAO.isAutoChargedOn(bed.getClientid());
					   // isAutochargeOn = false;
							if(isAutochargeOn){
						
								
							// All Bed charges and Standard changes will be fetched of active ipd patients

							ArrayList<CompleteAppointment> invoicelist=completeAptmDAO.getListofActiveCharges(bed.getId(),bed.getClientid(),loginInfo);

							//ArrayList<CompleteAppointment> invoicelist=completeAptmDAO.getListofActiveCharges(bed.getId(),bed.getClientid());

							//ArrayList<CompleteAppointment> invoicelist =completeAptmDAO.getActiveStandardCharges(bed.getId()); 
								
								if(invoicelist.size()!=0){
								 
								 	 for(CompleteAppointment appointment:invoicelist) {
								 		
								 			   
								 		  		int res=0;
								 			  // String autoChargeTIme=clinicDAO.getAutoChargeTime(String.valueOf(loginInfo.getClinicid()));
								 			   //int res=autoChargeTIme.compareTo(time);
								 			   
								 			   AppointmentType appointmentType= appointmentTypeDAO.getMasterCharge(appointment.getAdditionalcharge_id());
								 			   
								 			   try {
												   //parse and compare the time 
								 				   String stdDate= appointment.getCommencing();
								 				   String stdTime= appointment.getStartTime();
								 				   if(stdTime!=null){
								 					   if(stdTime.equals("")){
								 						   stdTime="00:00:00"; 
								 					   }
								 				   } else {
								 					   stdTime="00:00:00";
								 				   }
								 				   String dateTime= stdDate+" "+stdTime;
								 				   
								 				   SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								 				   Date date2 =f.parse(dateTime);
								 				   Calendar calendar=Calendar.getInstance();
								 				   calendar.setTime(date2);
								 				   int addedHours= Integer.parseInt(appointmentType.getHours());
								 				   if(appointment.getStdflag().equals("1")){
								 					   
								 					    if(addedHours>=24){
								 					    	
								 					    }else {
								 					    	calendar.add(Calendar.DATE, 1);
								 					    }
								 					    
								 				   }
								 				    
								 				   calendar.add(Calendar.HOUR, addedHours);
								 				   
								 				   String stdDateTime= f.format(calendar.getTime());
								 				   
								 				   res =nowdate.compareTo(stdDateTime);
								 				   
								 				 
								 				  
									 			   if(res==0 || res>0) { //if date is equal or greater than
		
									 				   	 if(appointmentType.getRatio()==null){
									 				   		 appointmentType.setRatio("0");
									 				   	 }
									 				     int ratio= Integer.parseInt(appointmentType.getRatio());
									 				     
									 				     // if ratio is zero means full charge
									 				     
									 				    String logcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
								 						
								 						String templ[] = bed.getAdmissiondate().split(" ");
								 						String ldate1 = DateTimeUtils.getCommencingDate1(templ[0]);
								 						String times[] = templ[1].split(":");
								 						String hh = times[0];
								 						String mm = times[1];
								 						
								 						//String loglastmodified = logcommencing + " " + hh + ":" + mm + ":20" ;
								 						String loglastmodified = DateTimeUtils.getCommencingDate1(bed.getAdmissiondate()) + " " + hh + ":" + mm + ":20" ;
								 						String cutofftime = bedDao.getHospitalCutoffTime(loginInfo.getClinicid());
								 			            if(!cutofftime.equals("0")){
								 			            	loglastmodified = logcommencing + " " + cutofftime + ":20" ;
								 			            }
								 			            
								 			            
								 			            
								 			            
								 			            //compare cutoff time
								 			            
								 			        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
								 			  		String aDateString = logcommencing + " "+hh+":"+mm+":00.0";
								 			  		if(!cutofftime.equals("0")){
								 			  			aDateString = logcommencing + " "+cutofftime+":00.0";
								 			  		}
								 			  		Date dateee = sdf.parse(aDateString);
//								 			  		System.out.println("reference date:"+dateee);

								 			  		Calendar cal = Calendar.getInstance();
								 			  		cal.setTime(dateee);
								 			  		//cal.add(Calendar.HOUR, 36);
//								 			  		System.out.println("added one and half days to reference date: "+cal.getTime());
								 			  		
								 			  		SimpleDateFormat fs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
								 					fs.setTimeZone(TimeZone.getTimeZone(loginInfo.getTimeZone()));
//								 					System.out.println(fs.format(GregorianCalendar.getInstance().getTime()));

								 					String newDateString = fs.format(GregorianCalendar.getInstance().getTime());
								 			  		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
								 			  		Date newDate = sdf.parse(newDateString);
//								 			  		System.out.println("new date to compare with reference date : "+newDate);

								 			  		Calendar newCal = Calendar.getInstance();
								 			  		newCal.setTime(newDate);
								 			  		
								 			  		if(!cal.after(newCal)){
//								 			  		    System.out.println("date is valid");
								 			  		}
								 			  		//  07 Aug 2018 auto charge check for already charge apply or not
								 			  		boolean flag = completeAptmDAO.checkAutoChargeAppliedStatus(bed.getWardid(),bed.getBedid(),bed.getId(),logcommencing);
								 			  		
								 			  		if(!flag){
								 			  			//if false then not available so apply auto charge
								 			  			
								 			  			if(ratio==0){   
									 				    	/* String sdate = completeAptmDAO.getIpdLogLastCommencing(bed.getId());
									 				    	String stempp[] = sdate.split(" ");
									 						sdate = DateTimeUtils.getCommencingDate1(stempp[0]) + " " + stempp[1];*/
									 						
									 						String edate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
									 						String temp[] = edate.split(" ");
									 						edate = DateTimeUtils.getCommencingDate1(temp[0]) + " " + temp[1];
									 				    	 
									 				    	/*int qty1 =(int)DateTimeUtils.getDifferanceofDateWithTime(sdate, edate, Integer.parseInt(appointmentType.getHours()));
									 				    	int result=completeAptmDAO.updateAutocharge(appointment.getId(),stempp[0],bed.getWardid(),time,qty1);*/
									 						
									 						//save new charge for new day
									 						
									 															 			  		
									 						if(!cal.after(newCal)){
									 			            
									 						appointment.setAppointmentid("0");
									 						appointment.setChargeType("Charge");
											    			appointment.setLocation("1");
											    			appointment.setIpd("1");
									 						 int invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId(),loginInfo.getUserId());
									 						 
									 						
									 						 String logshifteddate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
								 					            int log1 = bedDao.saveBedChangeLog(bed,loglastmodified,bed.getId(),logcommencing,logshifteddate,1);
									 						 
									 						 Client client = clientDAO.getClientDetails(bed.getClientid());
									 						
									 						 
									 						ArrayList<Master> chargeList=appointmentTypeDAO.getBedShiftingStandardChargeList(bed.getWardid(), client.getTypeName(), client.getWhopay(),loginInfo);
									 						 
									 						 for(Master m1:chargeList){
									 					    	  
									 					    	  appointment.setCommencing(temp[0]); 
									 					    	   appointment.setApmtType(m1.getName());
									 					    	   appointment.setCharges(m1.getCharge());
									 					    	   appointment.setAdditionalcharge_id(String.valueOf(m1.getId()));
									 					    	   appointment.setMasterchargetype("Bed Charge");
									 					    	   if(loginInfo.getIskunal()==1){
									 					    		   appointment.setMasterchargetype(m1.getMasterchargetype());
									 					    	   }
									 					    	  //appointment.setMasterchargetype("Accommodation Charges");
									 					    	  
									 					          // Bed bed2 = appointmentTypeDAO.getLogwardId(m1.getDate(),bed.getId());
									 					    	   
									 					    	   appointment.setWardid(bed.getWardid());
									 					    	   appointment.setBedid(bed.getBedid());
									 					    	   appointment.setIpdid(bed.getId());
									 					    	   appointment.setLogid(Integer.toString(log1));
									 					    	   
									 					    	 
									 					    	

									 					    	   
									 				    	   		appointment.setQuantity(1);

									 					    	    res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
									 						 }  
									 					    }
									 				     }
									 				     else { 
									 				    	  // else half charges
									 				    	 
									 				    	if(!cal.after(newCal)){
									 				    	 CompleteAppointment completeAppointment=completeAptmDAO.getInvoiceAsessmentDetails(appointment.getId());
									 				    	 completeAppointment.setStartTime(time);
									 				    	 completeAppointment.setCommencing(date);
									 				    	 int chargeAmt= Integer.parseInt(appointmentType.getCharges());
									 				    	 //update half qty
									 				    	 int amt= chargeAmt/2;
									 				    	 int result= completeAptmDAO.getHalfInvoiceAssesmentIfExist(appointment.getAdditionalcharge_id(),bed.getIpdid(),loginInfo);
									 				    	 if(result==0){
									 				    		 result=completeAptmDAO.saveInvoiceAssementWithHalf(completeAppointment,amt);
									 				    		 int temp=completeAptmDAO.updateAutochargeDate(appointment.getId(),date,bed.getWardid(),time);
									 				    	 } else {
									 				    		 
									 				    		 String sdate = completeAptmDAO.getIpdLogLastCommencing(bed.getId());
											 				    	String stempp[] = sdate.split(" ");
											 						sdate = DateTimeUtils.getCommencingDate1(stempp[0]) + " " + stempp[1];
											 						
											 						String edate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
											 						String temp[] = edate.split(" ");
											 						edate = DateTimeUtils.getCommencingDate1(temp[0]) + " " + temp[1];
											 				    	 
											 				    	int qty1 =(int)DateTimeUtils.getDifferanceofDateWithTime(sdate, edate, Integer.parseInt(appointmentType.getHours()));
									 				    		result=completeAptmDAO.updateAutocharge(result,stempp[0],bed.getWardid(),time,qty1);
									 				    	 }
									 				    	 
									 				    	}
									 				    	 
									 				     }
								 			  		}
								 			  	   }
								 				   
								 			   } catch (Exception e) {
		
								 				   //e.printStackTrace();
								 			   }
								 			  
								 		 
								 	 }
							 }
						
							} 
				}
				
				
				
				
				
				
				
				
			/*	if(loginInfo.getUserType()==2){
					return "gotodashboard";
				}
				
				
				
				if(loginInfo.getUserType()==4){
					return "gotoweekdashboard";
				}
				*/
				
				//Ipd bed log
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				MisChartDAO misChartDAO = new JDBCMisChartDAO(connection);
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				String todate = dateFormat.format(cal.getTime());
				todate = DateTimeUtils.getCommencingDate1(todate);
					
				Calendar cal1 = Calendar.getInstance();
				cal1.add(Calendar.DATE, -1);
				String newdate = dateFormat.format(cal1.getTime());
				newdate = DateTimeUtils.getCommencingDate1(newdate);
				
				//  02-06-2020 Store data for maindashboard
				
				boolean maindashboarddatastore = misChartDAO.checkMaindashboardDataSaved(newdate);
				if(!maindashboarddatastore){
					String yesterday = newdate;
					int ipdOldPatient = misChartDAO.getInHousePatients(todate, todate);
					int ipdnewadmission = misChartDAO.getIpdNewAdmission(todate, todate);
					int today_discharge = misChartDAO.getDischargePatients(todate, todate);
					int totalbed1 = misChartDAO.getAvailableBed(date,date);
					 
					//IPD
					int yesterday_admission = misChartDAO.getIpdNewAdmission(todate, todate);
					int yesterday_discharge = misChartDAO.getDischargePatients(yesterday, yesterday);
					int total_inhouse = ipdOldPatient-ipdnewadmission+today_discharge;
					int total_vacant_bed = totalbed1 -total_inhouse;
					
					//OPD
					int totalopdseen = misChartDAO.getBookedAppointment(yesterday, yesterday);
					int totalopdcompleted = misChartDAO.getOPDCompletedAppointment(yesterday, yesterday);
					int totalopddna = misChartDAO.getOPDdnaAppointment(yesterday, yesterday);
					
					//Patient gender wise
					int newadmitted = misChartDAO.getNewRegistration(yesterday, yesterday);
					int newmaleadmitted = misChartDAO.getNewRegistrationGenderWise(yesterday, yesterday,"Male");
					int newfemaleadmitted = misChartDAO.getNewRegistrationGenderWise(yesterday, yesterday,"Female");
					double totalmale = 0;
					double totalfemale = 0;
					if(newadmitted>0){
						totalmale = (newmaleadmitted / newadmitted)*100;
						totalfemale = (newfemaleadmitted / newadmitted)*100;
					}
					totalmale= Math.round(totalmale * 100.0) / 100.0;
					totalfemale= Math.round(totalfemale * 100.0) / 100.0;
					
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
					
					double upto20=0;
					double upto40=0;
					double upto60=0;
					double above60=0;
					
					double upto20count= misChartDAO.getDOBWisePatientReg(yesterday, yesterday,currentdate,upto20year);
					double upto40count=misChartDAO.getDOBWisePatientReg(yesterday, yesterday,upto21year,upto40year);
					double upto60count=misChartDAO.getDOBWisePatientReg(yesterday, yesterday,upto41year,upto60year);
					double above60count=misChartDAO.getDOBWisePatientReg(yesterday, yesterday,upto61year,year100);
					if(newadmitted>0){
						upto20 = (upto20count / newadmitted)*100;
						upto40 = (upto40count / newadmitted)*100;
						upto60 = (upto60count / newadmitted)*100;
						above60 = (above60count / newadmitted)*100;
					}
					upto20= Math.round(upto20 * 100.0) / 100.0;
					upto40= Math.round(upto40 * 100.0) / 100.0;
					upto60= Math.round(upto60 * 100.0) / 100.0;
					above60= Math.round(above60 * 100.0) / 100.0;
					
					if(loginInfo.isAyushman()) {
					   ArrayList<Master> wardList = bedDao.getAllWardListNewAyushman();
					   for (Master master : wardList) {
						    int ipdOldPatient1 = misChartDAO.getInHousePatients1(todate, todate,master.getId());
							int ipdnewadmission1 = misChartDAO.getIpdNewAdmission1(todate, todate,master.getId());
							int today_discharge1 = misChartDAO.getDischargePatients1(todate, todate,master.getId());
							 int totalbed12 = misChartDAO.getAvailableBed1(date,date,master.getId());
							 
							 int total_inhouse1 = ipdOldPatient1-ipdnewadmission1+today_discharge1;
								int total_vacant_bed1 = totalbed12 -total_inhouse1;
						   int res = misChartDAO.saveWardwiseMainDashboardData(yesterday,yesterday_admission,total_inhouse1,total_vacant_bed1,master.getId());
					  }
					}
					int res = misChartDAO.saveMainDashboardData(yesterday,yesterday_admission,yesterday_discharge,
							total_inhouse,total_vacant_bed,totalopdseen,totalopdcompleted,totalopddna,totalmale,totalfemale,
							upto20,upto40,upto60,above60);
					
				}
				
				Boolean flag = ipdDAO.checkInhousePatientExists(newdate);
				String ipdids="";
				if(!flag){
					int ipdOldPatient = misChartDAO.getInHousePatients(todate, todate);
					int ipdnewadmission = misChartDAO.getIpdNewAdmission(todate, todate);
					int dischargepatient = misChartDAO.getDischargePatients(newdate, newdate);
					int total = dischargepatient+ipdOldPatient-ipdnewadmission;
					int totalbed1 = misChartDAO.getAvailableBed(date,date);
					
					ArrayList<String> ipdOldPatientlist =  misChartDAO.getInHousePatientList(todate, todate);
					ArrayList<String> ipdnewadmissionlist = misChartDAO.getIpdNewAdmissionList(todate, todate);
					//ipdids =misChartDAO.getDischargePatientList(newdate, newdate);
					ArrayList<String> ipddischargelist = misChartDAO.getDischargePatientList1(newdate, newdate);
					/*for (String string : ipdOldPatientlist) {
						boolean flag1 = false;
						for (String string1 : ipdnewadmissionlist) {
							if(string.equals(string1)){
								flag1=true;
								break;
							}
						}
						if(!flag1){
							if(ipdids!=null){
								if(ipdids.equals("")){
									ipdids = string;
								}else{
									ipdids = ipdids+","+string;
								}
							}else{
								ipdids = string;
							}
						}
					}*/
					ipdOldPatientlist.removeAll(ipdnewadmissionlist);
					ipdOldPatientlist.addAll(ipddischargelist);
					for (String string : ipdOldPatientlist) {
						if(ipdids!=null){
							if(ipdids.equals("")){
								ipdids = string;
							}else{
								ipdids =ipdids+","+string;
							}
						}
					}
					int res = ipdDAO.saveInhousePatient(total,newdate,totalbed1,ipdids);
				}	
				//  save department wise and ward wise count 05 Oct 2018
				boolean iswardwisedatastore =  ipdDAO.checkWardWiseDataStored(newdate);
				if(!iswardwisedatastore){
					if(ipdids!=null){
						if(ipdids.equalsIgnoreCase("")){
							ArrayList<String> ipdOldPatientlist =  misChartDAO.getInHousePatientList(todate, todate);
							ArrayList<String> ipdnewadmissionlist = misChartDAO.getIpdNewAdmissionList(todate, todate);
							ArrayList<String> ipddischargelist = misChartDAO.getDischargePatientList1(newdate, newdate);
							ipdOldPatientlist.removeAll(ipdnewadmissionlist);
							ipdOldPatientlist.addAll(ipddischargelist);
							for (String string : ipdOldPatientlist) {
								if(ipdids!=null){
									if(ipdids.equals("")){
										ipdids = string;
									}else{
										ipdids =ipdids+","+string;
									}
								}
							}
						}
					}else{
						ArrayList<String> ipdOldPatientlist =  misChartDAO.getInHousePatientList(todate, todate);
						ArrayList<String> ipdnewadmissionlist = misChartDAO.getIpdNewAdmissionList(todate, todate);
						ArrayList<String> ipddischargelist = misChartDAO.getDischargePatientList1(newdate, newdate);
						ipdOldPatientlist.removeAll(ipdnewadmissionlist);
						ipdOldPatientlist.addAll(ipddischargelist);
						for (String string : ipdOldPatientlist) {
							if(ipdids!=null){
								if(ipdids.equals("")){
									ipdids = string;
								}else{
									ipdids =ipdids+","+string;
								}
							}
						}
					}
					ArrayList<String> wardlist = ipdDAO.getWardListFromIpdids(ipdids);
					for (String wardid: wardlist) {
						int total = misChartDAO.getInHousePatientsWardWise(todate, todate,ipdids,wardid);
						int totalbed1 = misChartDAO.getAvailableBedWardWise(date,date,ipdids,wardid);
						String ipdwardwiseids = misChartDAO.getInHousePatientsWardWiseIds(todate, todate,ipdids,wardid);
						int res = ipdDAO.saveInhousePatientWardWise(total,newdate,totalbed1,ipdwardwiseids,wardid);
					}
				}
				boolean ispractwisedatastore =  ipdDAO.checkPractWiseDataStored(newdate);
				
				if(!ispractwisedatastore){
					if(ipdids!=null){
						if(ipdids.equalsIgnoreCase("")){
							ArrayList<String> ipdOldPatientlist =  misChartDAO.getInHousePatientList(todate, todate);
							ArrayList<String> ipdnewadmissionlist = misChartDAO.getIpdNewAdmissionList(todate, todate);
							ArrayList<String> ipddischargelist = misChartDAO.getDischargePatientList1(newdate, newdate);
							ipdOldPatientlist.removeAll(ipdnewadmissionlist);
							ipdOldPatientlist.addAll(ipddischargelist);
							for (String string : ipdOldPatientlist) {
								if(ipdids!=null){
									if(ipdids.equals("")){
										ipdids = string;
									}else{
										ipdids =ipdids+","+string;
									}
								}
							}
						}
					}else{
						ArrayList<String> ipdOldPatientlist =  misChartDAO.getInHousePatientList(todate, todate);
						ArrayList<String> ipdnewadmissionlist = misChartDAO.getIpdNewAdmissionList(todate, todate);
						ArrayList<String> ipddischargelist = misChartDAO.getDischargePatientList1(newdate, newdate);
						ipdOldPatientlist.removeAll(ipdnewadmissionlist);
						ipdOldPatientlist.addAll(ipddischargelist);
						for (String string : ipdOldPatientlist) {
							if(ipdids!=null){
								if(ipdids.equals("")){
									ipdids = string;
								}else{
									ipdids =ipdids+","+string;
								}
							}
						}
					}
					ArrayList<String> practlist = ipdDAO.getPractListFromIpdids(ipdids);
					for (String practid: practlist) {
						int total = misChartDAO.getInHousePatientsPractWise(todate, todate,ipdids,practid);
						String ipdwardwiseids = misChartDAO.getInHousePatientsPractWiseIds(todate, todate,ipdids,practid);
						int res = ipdDAO.saveInhousePatientPractWise(total,newdate,ipdwardwiseids);
					}
					
				}
				
			
				
			if(loginInfo.isShow_unpost()==true){
				ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
				DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal3 = Calendar.getInstance();
				cal3.add(Calendar.DATE, -2); 
				String date4= dateFormat3.format(cal3.getTime());
				String podate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				int res = chargesAccountProcessingDAO.updateInvoicePost(date4,loginInfo.getUserId(),podate);
			}
				
				
			/*if(loginInfo.isBdaysms()){	
			//lokesh bdaysms
				DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal2 = Calendar.getInstance();
				
				String Date1 = dateFormat2.format(cal2.getTime());
				String dob1[]= Date1.split("/");
				String dob= dob1[0]+"/"+dob1[1]+"/";		
				ArrayList<Client> bdaylist= clientDAO.getbdaylistPatients(dob, loginInfo);
			}	
			*/
			
			
			if(loginInfo.isBdaysms()){	
				//lokesh bdaysms
					DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
					Calendar cal2 = Calendar.getInstance();
					
					String Date1 = dateFormat2.format(cal2.getTime());
					String dob1[]= Date1.split("/");
					String dob= dob1[0]+"/"+dob1[1]+"/";
					/*ClientDAO clientDAO= new JDBCClientDAO(connection);*/
					if(isTriggerTime("09:00")){
						ArrayList<Client> bdaylist= clientDAO.getbdaylistPatients(dob, loginInfo);
					}
					
				}	
			if(loginInfo.isSimpliclinic()) {
				if(loginInfo.isBdaysms()) {
					DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
					Calendar cal2 = Calendar.getInstance();
					
					String Date1 = dateFormat2.format(cal2.getTime());
					String anniversary1[]= Date1.split("/");
					String anniversary= anniversary1[0]+"/"+anniversary1[1]+"/";
					if(isTriggerTime("09:00")){
						ArrayList<Client> anniversarylist=clientDAO.getAnniversarylistPatient(anniversary,loginInfo);
					}
				}
			}
			
			/*UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
			if(userProfileDAO.getWeekVaccineAccess()){
				DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal3 = Calendar.getInstance();
				cal3.add(Calendar.DATE, 7); 
				String Date1= dateFormat3.format(cal3.getTime());
				notAvailableSlotDAO.getAllClientVaccinations(Date1, loginInfo);
			}*/
				
			//17 August 2018 Save inventory 
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String todaydate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			//10-08-2019 Picachu
			if(loginInfo.isStock_log()){
				if(loginInfo.getOpening_locations()!=null){
					for (String string : loginInfo.getOpening_locations().split(",")) {
						if(string.equals("0")){
							continue;
						}
						boolean flags = inventoryProductDAO.checkInventoryProductLog(todaydate,string);
						if(!flags){
							int res =inventoryProductDAO.saveInventoryStockLog(todaydate,string);
						}
					}
				}
			}
			
			//  31-01-2019
			
			DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal11 = Calendar.getInstance();
			cal11.add(Calendar.DATE, -1);
			String todates = dateFormat1.format(cal11.getTime());
			
			Calendar cal12 = Calendar.getInstance();
			cal12.add(Calendar.DATE, -2);
			String previousdate = dateFormat1.format(cal12.getTime());
			/*previousdate = DateTimeUtils.getCommencingDate1(previousdate);*/
		/*	boolean catdatastorestatus =  checkInventoryCatalogueStatus(previousdate,connection);*/
			if(loginInfo.isOpening_closeing_on()){/*
				for (String string : loginInfo.getOpening_locations().split(",")) {
					if(string.equals("0")){
						continue;
					}
					boolean flags = inventoryProductDAO.checkInventoryOpeningStockLog(todates,string);
					if(!flags){
						String location_filter = string;
						
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
							int openingstock=0;
							double openingvalue =0;
							int qtyin=0;
							double qtyinvalue =0;
							int qtyout=0;
							double qtyoutvalue =0;
							double salevalue = 0;
							int closingstock=0;
							double closingvalue =0;
							Product product2 = inventoryProductDAO.getYesterdayClosingData(previousdate,catalogueid,location_filter);
							opeingstockvalue = product2.getTotalclosingvalue();
							openingstock = product2.getTotalclosingstock();
							openingvalue = product2.getClosing();
							
							boolean flagss = inventoryProductDAO.checkProductInProductLog(todates,catalogueid,todates,location_filter);
							if(flagss){
								Product qtyin_product = inventoryProductDAO.getCatalogueProductIn(todates,catalogueid,string,todates);
								Product qtyin_product_update_pro = inventoryProductDAO.getCatalogueProductUpdateProIn(todates,catalogueid,string,todates); 
								
								qtyin = qtyin_product.getTotalqty() + qtyin_product_update_pro.getTotalqty();
								qtyinvalue = qtyin_product.getTotal_amount() + qtyin_product_update_pro.getTotal_amount();
								
								Product qtyout_product = inventoryProductDAO.getCatalogueProductOut(todates,catalogueid,string,todates,1);
								Product qtyout_product_update_pro = inventoryProductDAO.getCatalogueProductUpdateProOut(todates,catalogueid,string,todates);
								Product qtyout_product_sale = inventoryProductDAO.getCatalogueProductSaleOut(todates,catalogueid,string,todates);
								
								qtyout = qtyout_product.getTotalqty() + qtyout_product_update_pro.getTotalqty()+qtyout_product_sale.getTotalqty() ;
								qtyoutvalue = qtyout_product.getTotal_amount() + qtyout_product_update_pro.getTotal_amount()+qtyout_product_sale.getTotal_amount(); 
								salevalue = qtyout_product.getSalevalue() + qtyout_product_update_pro.getSalevalue()+qtyout_product_sale.getSalevalue(); 
							}
							closingstock=openingstock+qtyin-qtyout;
							closingvalue=openingvalue+qtyinvalue-qtyoutvalue;
							int unknownqty = 0;
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
			*/}
			boolean catdatastorestatus =  true;
			if(!catdatastorestatus){/*
				try {
					String fromDate = previousdate;
					String toDate = previousdate;
					
					ArrayList<String> arrayList = new ArrayList<String>();
					arrayList.add("32");
					arrayList.add("33");
					arrayList.add("34");
					arrayList.add("35");
					arrayList.add("36");
				for (String string : arrayList) {
					String location_filter = string;
					StringBuffer buffer = new StringBuffer();
					buffer.append("select distinct inventory_catalogue.id from inventory_catalogue ");
					buffer.append("inner join inventory_product on inventory_product.catalogueid = inventory_catalogue.id  ");
					buffer.append("where procurement=0 and product_name is not null and inventory_product.location='"+location_filter+"' ");
					buffer.append("order by inventory_product.prodname ");
					PreparedStatement ps=connection.prepareStatement(buffer.toString());
					ResultSet rs =ps.executeQuery();
					while(rs.next()){
						Product product = new Product();
						
						String catalogueid = rs.getString(1);
						double opeingstockvalue =0;
						int openingstock=0;
						boolean checklastday = inventoryProductDAO.checkPreviousDataCatAvailable(previousdate,catalogueid,location_filter);
						if(checklastday){
							Product product2 = inventoryProductDAO.getYesterdayClosingData(previousdate,catalogueid,location_filter);
							opeingstockvalue = product2.getTotalclosingvalue();
							openingstock = product2.getTotalclosingstock();
						}else{
							//Opening- Purchase qty + return from patient + return to store - Patient sale - Return to supplier
							//- Consume -Direct transfer -Request transfer =before fromdate
							//Purchase qty =before fromdate this plus
							Product purbeforeproduct = inventoryProductDAO.getPuchaseProductData(catalogueid,fromDate,location_filter);  
							
							//Return from patient before fromdate this plus
							Product retpatientbeforeproduct = inventoryProductDAO.getReturnPatientProductData(catalogueid,fromDate,location_filter);
							
							//Patient Sale Before Fromdate this minus
							Product salebefore = inventoryProductDAO.getPatientSaleBeforeDate(catalogueid,fromDate,location_filter);
							
							//Return to supplier before fromdate this minus
							Product returntosuppplierbefore = inventoryProductDAO.getReturnToSupplierBeforeDate(catalogueid,fromDate,location_filter); 
							
							//Consume to user or patient this minus
							Product consumebefore = inventoryProductDAO.getConsumeBeforeDate(catalogueid,fromDate,location_filter);
							
							int directtransferout =0; 
							int requesttransferout =0;
							int retruntransferin =0;
							double returntransfervaluein =0;
							double directtransfervalueout =0;
							double requesttransfervalueout =0;
							
							int directtransferin =0;
							int requesttransferin =0;
							int returntransferout =0;
							double directtransfervaluein=0;
							double requesttransfervaluein=0;
							double returntransfervalueout=0;
							if(!location_filter.equals("0")){
								//Direct transfer out before fromdate
								Product directtransferproductout = inventoryProductDAO.getDirectTransferBefore(catalogueid,fromDate,location_filter);
								
								//Request Transfer out before fromdate
								Product requesttransferproductout = inventoryProductDAO.getRequestTransferBefore(catalogueid,fromDate,location_filter);
								
								//Return transfer in before fromdate
								Product returntransferproductin = inventoryProductDAO.getReturnTransferBefore(catalogueid,fromDate,location_filter);
								
								directtransferout = directtransferproductout.getTotalqty();
								requesttransferout = requesttransferproductout.getTotalqty();
								retruntransferin = returntransferproductin.getTotalqty();
								returntransfervaluein = returntransferproductin.getTotal_amount();
								directtransfervalueout = directtransferproductout.getTotal_amount();
								requesttransfervalueout = requesttransferproductout.getTotal_amount();
								//Direct transfer in before  fromdate
								Product directtransferproductin = inventoryProductDAO.getDirectTransferBeforeIn(catalogueid,fromDate,location_filter);
								
								//Request Transfer in before  fromdate
								Product requesttransferproductin = inventoryProductDAO.getRequestTransferBeforeIn(catalogueid,fromDate,location_filter);
								
								//Return transfer out before  fromdate
								Product returntransferproductout = inventoryProductDAO.getReturnTransferBeforeOut(catalogueid,fromDate,location_filter);
							
								directtransferin =directtransferproductin.getTotalqty();
								requesttransferin =requesttransferproductin.getTotalqty();
								returntransferout =returntransferproductout.getTotalqty();
								directtransfervaluein=directtransferproductin.getTotal_amount();
								requesttransfervaluein=requesttransferproductin.getTotal_amount();
								returntransfervalueout  = returntransferproductout.getTotal_amount();
							}
							
							Product adjustmentbeforout = inventoryProductDAO.getAdjustmentDataBeforeOut(catalogueid,fromDate,location_filter);
							
							Product adjustmentbeforin = inventoryProductDAO.getAdjustmentDataBeforeIn(catalogueid,fromDate,location_filter);
							
							//opening stock calculation
							//Opening= Purchase qty + return from patient + return to store - Patient sale - Return to supplier
							//- Consume -Direct transfer -Request transfer =before fromdate
							openingstock = purbeforeproduct.getTotalqty() + retpatientbeforeproduct.getTotalqty() 
									-salebefore.getTotalqty() - returntosuppplierbefore.getTotalqty() - consumebefore.getTotalqty() 
									- directtransferout - requesttransferout + retruntransferin 
									+ directtransferin + requesttransferin- returntransferout  - adjustmentbeforout.getTotalqty() + adjustmentbeforin.getTotalqty();
							
							if(openingstock<0){
								openingstock=0;
							}
							
							//Opening Stock Value - purchase qty pur value + return from patient sale price = Before Fromdate;
							opeingstockvalue = purbeforeproduct.getTotal_amount() + retpatientbeforeproduct.getTotal_amount() 
													- salebefore.getTotal_amount() - returntosuppplierbefore.getTotal_amount() - consumebefore.getTotal_amount()
													- directtransfervalueout - requesttransfervalueout + returntransfervaluein
													+ directtransfervaluein +requesttransfervaluein - returntransfervalueout - adjustmentbeforout.getTotal_amount() + adjustmentbeforin.getTotal_amount();
							
							if(opeingstockvalue<0){
								opeingstockvalue=0;
							}
						}
						
						
						
						//Qty in-  Purchase qty +  return from patient + retruntransfer  = in fromdate and todate
						
						//Purchase qty =  in fromdate and todate
						Product purproduct = inventoryProductDAO.getPuchaseProductDataBetween(catalogueid,fromDate,toDate,location_filter);  
						
						//return from patient =  in fromdate and todate
						Product returnpatientqty = inventoryProductDAO.getReturnPatientProductDataBetween(catalogueid,fromDate,toDate,location_filter);
						
						// retruntransfer = in fromdate and todate 
						int retruntransferqtyin =0;
						double retruntransferqtyinvalue=0;
						int directtransferqtyin=0;
						int requesttransferqtyin =0;
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
						
						int qtyin = purproduct.getTotalqty() + returnpatientqty.getTotalqty() 
									+retruntransferqtyin + directtransferqtyin +requesttransferqtyin + adjustmentbetweenin.getTotalqty() ;
						
						double qtyinvalue = purproduct.getTotal_amount() + returnpatientqty.getTotal_amount() 
									+retruntransferqtyinvalue + directtransferqtyinvalue +requesttransferqtyinvalue + adjustmentbetweenin.getTotal_amount() ;
						
						//Qty out - Patient sale + Return to Supplier + Consume + direct transfer + request return  = in Fromdate and Todate
						
						// retruntransfer = in fromdate and todate 
						int directtransferqtyout =0;
						double directtransferqtyoutvalue=0;
						int requesttransferqtyout =0;
						double requesttransferqtyoutvalue=0;
						
						int returntransferqtyout =0;
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
						
						int qtyout = salepatient.getTotalqty()+returntosuppplier.getTotalqty()
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
						int closingstock = openingstock +qtyin - qtyout;
						
						//double closingvalue = unitprice * closingstock;
						double closingvalue = opeingstockvalue + qtyinvalue - stockvalue;
						int unknownqty = 0;
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
					 	ps1.setString(1, todates);
					 	ps1.setString(2, previousdate);
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
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			*/
				
			
			}
			 String sms_type="";
				//vaccination sms
				if(loginInfo.isImmusms()){
					DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal3 = Calendar.getInstance();
					cal3.add(Calendar.DATE, 1); 
					String Date1= dateFormat3.format(cal3.getTime());
					boolean is1daysms = true;
					sms_type="Vaccination";
					loginInfo.setSms_type(sms_type);
					notAvailableSlotDAO.getAllClientVaccinations(Date1, loginInfo,is1daysms);
				}
				
				//vaccination sms 7 days ago
				if(loginInfo.isVaci_sms_7day()){
					DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal3 = Calendar.getInstance();
					cal3.add(Calendar.DATE, 7); 
					String Date1= dateFormat3.format(cal3.getTime());
					boolean is1daysms = false;
					sms_type="Vaccination";
					loginInfo.setSms_type(sms_type);
					notAvailableSlotDAO.getAllClientVaccinations(Date1, loginInfo,is1daysms);
				}
				
				//deworming sms
				if(loginInfo.isImmusms()){
					DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal3 = Calendar.getInstance();
					cal3.add(Calendar.DATE, 1); 
					String Date1= dateFormat3.format(cal3.getTime());
					boolean is1daysms = true;
					sms_type="deworming";
					loginInfo.setSms_type(sms_type);
					notAvailableSlotDAO.getAllClientdeworming(Date1, loginInfo,is1daysms);
				}
				//deworming sms 7 days ago
				if(loginInfo.isVaci_sms_7day()){
					DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal3 = Calendar.getInstance();
					cal3.add(Calendar.DATE, 1); 
					String Date1= dateFormat3.format(cal3.getTime());
					boolean is1daysms = true;
					sms_type="deworming";
					loginInfo.setSms_type(sms_type);
					notAvailableSlotDAO.getAllClientdeworming(Date1, loginInfo,is1daysms);
				}
				
				if(loginInfo.isBdaysms()){	
					//lokesh bdaysms
						DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
						Calendar cal2 = Calendar.getInstance();
						
						String Date1 = dateFormat2.format(cal2.getTime());
						String dob1[]= Date1.split("/");
						String dob= dob1[0]+"/"+dob1[1]+"/";
						//ClientDAO clientDAO= new JDBCClientDAO(connection);
						sms_type="Birthday";
						loginInfo.setSms_type(sms_type);
						if(isTriggerTime("09:00")){
							ArrayList<Client> bdaylist= clientDAO.getbdaylistPatients(dob, loginInfo);
						}
						
				}
				 if(true){
						DateFormat dateFormat3 = new SimpleDateFormat("dd-MM-yyyy");
						Calendar cal3 = Calendar.getInstance();
						cal3.add(Calendar.DATE, 1); 
						 SmsService s= new SmsService();
						 WhatsAppService wsms=new WhatsAppService();
						 UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
						 ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
						//ClientDAO clientDAO= new JDBCClientDAO(connection); 
						String Date1= dateFormat3.format(cal3.getTime());
						 ArrayList<Client> followuplist=clientDAO.getallfollowupsToDash("", "", Date1, Date1);
						 Clinic clinic1 = clinicDAO.getWhatsappActive(loginInfo.getId());
						 sms_type="Follow Up";
						 loginInfo.setSms_type(sms_type);
						 for(Client client:followuplist){
							 String message ="";
							 loginInfo.setPatient_name(client.getClientName());
							 if(client.getState().equals("0")){
								 //String message="Your Follow up is Scheduled on "+Date1+" by "+client.getDiaryUser()+" -"+loginInfo.getClinicName()+"";
								 if(loginInfo.isParihar()) {
								  message="प्रिय "+client.getClientName()+" आपका फालोअप अपाइंटमेंट दिनांक "+Date1+" को डाॅ. "+client.getDiaryUser()+", परिहार हास्पिटल कवर्धा के साथ निर्धारित है";
								 
								  
								 }else if(loginInfo.isAyushman()){
									 
								  message = "Dear Parents "+client.getClientName()+" Follow up of your child is scheduled on "+Date1+" by "+client.getDiaryUser()+" - "+loginInfo.getClinicName()+"";
								  wsms.sendMsgvacc(loginInfo,client.getMobNo(), message,connection);
								 }else {
								
								  message = "Dear Parents\nFollow up of your child is scheduled on "+Date1+" by "+client.getDiaryUser()+" - "+loginInfo.getClinicName()+"";
								 }
								 String templateid = userProfileDAO.getSMSTemplateID("FollowUp");
								 s.sendvaccineSms(message, client.getMobNo(), loginInfo, new EmailLetterLog(),connection,templateid);
									clientDAO.setfollowupsmsflag(""+client.getId());
									
									if(clinic1.getWsms()==1 && !loginInfo.isAyushman()){
										 String msg="प्रिय "+client.getClientName()+" आपका फालोअप अपाइंटमेंट दिनांक "+Date1+" को डाॅ. "+client.getDiaryUser()+", "+loginInfo.getClinicName()+" के साथ निर्धारित है। ";
										 wsms.sendMsgvacc(loginInfo,client.getMobNo(), msg,connection);
									}
							 }
						 }
					}
				 
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				connection.close();
			}
			
				
		}
		
		
		return "wait";
	}
	
	
	

	
	
	private boolean checkInventoryCatalogueStatus(String newdate, Connection connection) {
		boolean flag = false;
		try {
			String sql ="select * from inventory_catalogue_log where date='"+newdate+"'";
			java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}






	/*private void saveInventoryStockLog(Connection connection, String todaydate) {
		try {
			//  17 Aug 2018
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			int res = inventoryProductDAO.saveInventoryStockLog(todaydate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}*/






	public  Connection getconnection(String dbname) throws SQLException
	{
		/*LoginInfo loginInfo = (LoginInfo)session.getAttribute("logininfo");
		String dbname = "";
		if(loginInfo!=null){
			dbname = loginInfo.getUserId();
		}
		*/
		
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/apm","root","mysql");
			//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/apmCbstec","Balvinder001","Deepak001");
			con=DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+dbname+"",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbname+"","root","mysql");
//			System.out.println("done");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
	
public boolean isTriggerTime(String triggerTime){
		
		boolean flag=false;
		try {
			DateFormat ss= new SimpleDateFormat("dd:MM:yyyy HH:mm");
			Date checkTime= ss.parse( new SimpleDateFormat("dd:MM:yyyy").format(new Date())+" "+triggerTime);
			Date nowTime= new Date();
			if(nowTime.after(checkTime)){
				flag= true;
			}else{
				flag= false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	

}
