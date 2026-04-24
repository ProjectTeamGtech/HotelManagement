package com.apm.TreatmentEpisode.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.Appointment.eu.entity.Appointment;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCCompleteAptmDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.TreatmentEpisode.eu.bi.TreatmentEpisodeDAO;
import com.apm.TreatmentEpisode.eu.blogic.jdbc.JDBCTreatmentEpisode;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.TreatmentEpisode.web.form.TreatmentEpisodeForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class TreatmentEpisodeAction extends BaseAction implements Preparable,
		ModelDriven<TreatmentEpisodeForm> {

	HttpServletRequest request = (HttpServletRequest) ActionContext
			.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse) ActionContext
			.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	TreatmentEpisodeForm treatmwntEpisodeForm = new TreatmentEpisodeForm();
	private Pagination pagination = new Pagination(10, 1);

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public String execute() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		/*
		 * Connection connection = null;
		 * 
		 * try{
		 * 
		 * connection = Connection_provider.getconnection(); TreatmentEpisodeDAO
		 * treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
		 * ArrayList<TreatmentEpisode>treatmentEpisodeList = new
		 * ArrayList<TreatmentEpisode>();
		 * 
		 * treatmentEpisodeList = treatmentEpisodeDAO.getTreatmentEpisodeList();
		 * treatmwntEpisodeForm.setTreatmentEpisodeList(treatmentEpisodeList);
		 * treatmwntEpisodeForm.setClient(treatmwntEpisodeForm.getClient());
		 * 
		 * 
		 * }catch (Exception e) { e.printStackTrace(); } finally{
		 * 
		 * connection.close(); }
		 */

		return "showTreatmentEpisode";
	}

	public String lastapmt() throws Exception{
		if (!verifyLogin(request)) {
			return "login";
		}
		String docid=request.getParameter("docid");
		String clientid = request.getParameter("clientid");
		String apmtId = request.getParameter("apmtId");
		String tpid = request.getParameter("tpid");
		String payby = request.getParameter("payby");
		String commencing = DateTimeUtils.getCommencingDate(request.getParameter("commencing"));
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(
					connection);
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(
					connection);
			
			// from this code we are getting patients last treatment department
			NotAvailableSlot notAvailableSlot = treatmentEpisodeDAO
					.getLastAppointmentData(clientid);
			String treatmentId = treatmentEpisodeDAO
					.getTreatmentEpisodeId(apmtId);
			
			//code to get doctors department
			String dept=treatmentEpisodeDAO.getDepartmentBydocId(docid);
			
			String refenddate = "";
			String refFromDate = "";
			if(apmtId.equals("0")){
				refenddate = treatmentEpisodeDAO.getRefEndDate(notAvailableSlot.getTreatmentEpisodeId());
				refFromDate = treatmentEpisodeDAO.getRefFromDate(notAvailableSlot.getTreatmentEpisodeId());
			}else{
				refenddate = treatmentEpisodeDAO.getRefEndDate(treatmentId);
				refFromDate = treatmentEpisodeDAO.getRefFromDate(treatmentId);
			}
			
			//changes made for balgopal opd charges to be set by deafault 13/08/2024
			
			  if(loginInfo.isBalgopal())
			  {
				  
				String appointmentype= opdappointment(clientid,commencing,docid,tpid,payby);
			    notAvailableSlot.setApmtType(appointmentype);
			  }else if(loginInfo.isMarkhosp()) {
				  

				  long differ=0; 
				  long  newdiffer=0;
				  String apmt_type="";
				  String apmt_name="";
				  String last_opd_date=appointmentTypeDAO.getLastopddatebyclientid(clientid); 
				  String last_charge_apmt=appointmentTypeDAO.getLastChargedappointment(clientid);
				  String current_date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0]; 
				  int count=appointmentTypeDAO.getavailableslotclientcount(clientid);
				  

				  if(last_opd_date.equals("")) 
				  { 
					  differ=DateTimeUtils.getDiffofTwoDates(current_date, commencing); 
				  }else {
			          differ=DateTimeUtils.getDiffofTwoDates(last_opd_date, commencing); 
			      }
				  
				  // new changes in markhosp appointment 
				  
				  if(!last_charge_apmt.equals("")) {
					  
					  newdiffer=DateTimeUtils.getDiffofTwoDates(last_charge_apmt, commencing); 
				  }
				  
				  if(!last_opd_date.equals("")) {
				  
				  String temp1[]=last_opd_date.split("-");
				  String month1=temp1[1];
				  String year1=temp1[0];
				  
				  String temp2[]=commencing.split("-");
				  String month2=temp2[1];
				  String year2=temp2[0];
				  
				  if(!month1.equals(month2)) {
					  int month_days=DateTimeUtils.getlastmonthdate(Integer.parseInt(month1),Integer.parseInt(year1));
					  
					  if(month_days==28) {
						  differ=differ-3;
						  newdiffer=newdiffer-3;
					  }else if(month_days==29) { 
						  differ=differ-2;
						  newdiffer=newdiffer-2;
					  }else if(month_days==30) {
						  differ=differ-1;
						  newdiffer=newdiffer-1;
					  }else if(month_days==31) {
						  differ=differ+1;
						  newdiffer=newdiffer+1;
					  }
					  
				  }
				  
		     }
				  
				  
				  
				  long opddays=0;
				  long dischargedays=0;
				  
				  NotAvailableSlot notAvailable=appointmentTypeDAO.getAdmissiondatebyid(clientid);
				  String dischargedate=DateTimeUtils.isNull(notAvailable.getDischargedate());
				  
				  if(!dischargedate.equals("")) {
					  
					  dischargedays=DateTimeUtils.getDatesum(notAvailable.getDischargedate());
					  
				  }
				  
				  if(!last_opd_date.equals("")) {
					  opddays=DateTimeUtils.getDatesum(last_opd_date);
				  }
				  
				  if(dischargedays>opddays) {
					  apmt_name="Free Follow Up";
				      docid="0"; 
				  }else {
				  
		               if(tpid.equals("97")) {
					   
		                   apmt_name="SECL ON CASH OPD";
					       docid="0";
					   
				       }else if(tpid.equals("98")) {
					   
				    	   apmt_name="SECL Patient OPD";
					       docid="0";
				       }else if(tpid.equals("99")) {
					       
				    	   apmt_name="CGHS OPD";
					       docid="0";
				       } else if(tpid.equals("0") || DateTimeUtils.isNull(tpid).equals("")) {
				    	   
				    	   if(DateTimeUtils.isNull(notAvailableSlot.getDept()).equals(dept) || DateTimeUtils.isNull(notAvailableSlot.getDept()).equals("")) {
				  
				           if(last_opd_date.equals("") && differ==0) {
					            apmt_name="New OPD";
				           }else if(newdiffer>7) {
					            apmt_name="Follow UP";
				           }else if(newdiffer<=7){
					            apmt_name="7 DAY FREE FOLLOWUP";
					            docid="0";
				           }
				    	 }else {
				    		 
                              if(last_opd_date.equals("") && differ==0) {
						            apmt_name="New OPD";
					           }else if(newdiffer>7) {
						            apmt_name="Follow UP";
					           }else if(newdiffer<=7){
						            apmt_name="Follow UP";
						            //docid="0";
					           }
				    	 }
			           }
                 
		        }
				  apmt_type=appointmentTypeDAO.getApppointmentIdbyDocid(docid,apmt_name,tpid);
				  notAvailableSlot.setApmtType(apmt_type);
			  }else if(loginInfo.isAyushman()) {
				  long differ=0; 
				  String apmt_name="";
				  String last_opd_date=appointmentTypeDAO.getLastopddatebyclientid(clientid); 
				  String current_date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0]; 
				  double charge=appointmentTypeDAO.getavailableslotCharges(clientid);
				  

				  if(last_opd_date.equals("")) 
				  { 
					  differ=DateTimeUtils.getDiffofTwoDates(current_date, commencing); 
				  }else {
			          differ=DateTimeUtils.getDiffofTwoDates(last_opd_date, commencing); 
			      }
				  
				  
				  if(!last_opd_date.equals("")) {
				  String temp1[]=last_opd_date.split("-");
				  String month1=temp1[1];
				  String year1=temp1[0];
				  
				  String temp2[]=commencing.split("-");
				  String month2=temp2[1];
				  String year2=temp2[0];
				  int updtopd_count=0;
				  if(!month1.equals(month2)) {
					  int month_days=DateTimeUtils.getlastmonthdate(Integer.parseInt(month1),Integer.parseInt(year1));
					  
					  if(month_days==28) {
						  differ=differ-3;
					  }else if(month_days==29) { 
						  differ=differ-2;
					  }else if(month_days==30) {
						  differ=differ-1;
					  }else if(month_days==31) {
						  differ=differ+1;
						 
					  }
				  }
				 }
				 
				  if(differ<=6 && charge>0 ) {
					  notAvailableSlot.setApmtType("3204");
					  // updtopd_count=appointmentTypeDAO.updateOpd_count(0,clientid);
				  }else {
					  notAvailableSlot.setApmtType("0");
					  // updtopd_count=appointmentTypeDAO.updateOpd_count(1,clientid);
				  }
			}
			
			String str = notAvailableSlot.getApmtType() + "/"
					+DateTimeUtils.isNull(notAvailableSlot.getTreatmentEpisodeId()) + "/"
					+DateTimeUtils.isNull(notAvailableSlot.getDuration()) + "/"
					+DateTimeUtils.isNull(notAvailableSlot.getTreatmentSessions()) + "/"
					+DateTimeUtils.isNull(notAvailableSlot.getUsedsession())+ "/" + treatmentId
					+ "/" +DateTimeUtils.isNull(notAvailableSlot.getWhopay())+ "/" + refenddate + "/" + refFromDate;

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return null;
	}

	
	
	private String opdappointment(String clientid, String commencing, String docid, String tpid, String payby) {
		   Connection connection=null;
		   String apmttype="";
		try {
			  connection = Connection_provider.getconnection();
			  AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
			  TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
			  long differ=0; 
			  String last_opd_date=appointmentTypeDAO.getLastopddatebyclientid(clientid); 
			  String current_date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0]; 
			  int count=appointmentTypeDAO.getavailableslotclientcount(clientid);
		     
			
			
			  if(last_opd_date.equals("")) 
			  { 
				  
				  differ=DateTimeUtils.getDiffofTwoDates(current_date, commencing); 
				 
			  }else {
		          differ=DateTimeUtils.getDiffofTwoDates(last_opd_date, commencing); 
		      }
			  
			  
			
			  long opddays=0;
			  long admitdays=0;
			  long dischargedays=0;
			  long commencingdays=0;
			  NotAvailableSlot notAvailableSlot=appointmentTypeDAO.getAdmissiondatebyid(clientid);
			  
			  if(!last_opd_date.equals("")) {
			  String temp1[]=last_opd_date.split("-");
			  String month1=temp1[1];
			  String year1=temp1[0];
			  
			  String temp2[]=commencing.split("-");
			  String month2=temp2[1];
			  String year2=temp2[0];
			  
			  if(!month1.equals(month2)) {
				  int month_days=DateTimeUtils.getlastmonthdate(Integer.parseInt(month1),Integer.parseInt(year1));
				  
				  if(month_days==28) {
					  differ=differ-3;
				  }else if(month_days==29) { 
					  differ=differ-2;
				  }else if(month_days==30) {
					  differ=differ-1;
				  }else if(month_days==31) {
					  differ=differ+1;
						 
				  }
			   }
			  }
			  
			  String admitdate=DateTimeUtils.isNull(notAvailableSlot.getAdmitdate()).split(" ")[0];
			   if(!admitdate.equals("")) {
			       admitdays=DateTimeUtils.getDatesum(admitdate);
			    }
			   if(!last_opd_date.equals("")) {
				   opddays=DateTimeUtils.getDatesum(last_opd_date);
				}
			   if(!DateTimeUtils.isNull(notAvailableSlot.getDischargedate()).equals("")) {
				   dischargedays=DateTimeUtils.getDatesum(notAvailableSlot.getDischargedate());
			   }
			   
			   commencingdays=DateTimeUtils.getDatesum(commencing);
			  
			   if(dischargedays!=0  && dischargedays<=commencingdays && dischargedays>opddays) {
				   //&& !notAvailableSlot.getWhopay().equals("0")
				   long diff=0;
				   diff=DateTimeUtils.getDiffofTwoDates(notAvailableSlot.getDischargedate(), commencing); 
				   if((!notAvailableSlot.getWhopay().equals("86") && !notAvailableSlot.getWhopay().equals("94")) && (!docid.equals("22") && !docid.equals("39")) && !tpid.equals("0") && !DateTimeUtils.isNull(tpid).equals("")) {
					 
					   
				    if(diff<=7) {
					 apmttype="17105";
				    }else {
				      apmttype="17103";	
				    }
				     //int updtopd_count=appointmentTypeDAO.updateOpd_count(0,clientid);
				   
				   }else if(notAvailableSlot.getWhopay().equals("86") || notAvailableSlot.getWhopay().equals("94")) {
					 
					   apmttype="17103";
				  
				   } else if(docid.equals("22") || docid.equals("39")) {
						  if(diff==0 || diff>3 ) {
							  if(docid.equals("22")) {
								  apmttype="24164";   
							  }else {
							      apmttype="21998"; 
							  }
						  }else {
							     apmttype="3";
						  }
				   }else {
					   
					   
					       if(diff<=7) {
							 apmttype="3";
						    }else {
						      apmttype="1";	
						    }
						/*
						 * if((diff==0 && count==0 )|| diff>15) { apmttype="1"; }else
						 * if(last_opd_date.equals(commencing) && count!=0) { apmttype="3"; }else
						 * if(differ<=7) { if(count==1 || count==3) { apmttype="3"; }else if(count==2) {
						 * apmttype="2"; }else if(count==4) { apmttype="1"; } }
						 */
				   }
				  
				  
			  } else if(docid.equals("22") || docid.equals("39")) {
				  if(differ==0 || differ>3 ) {
					  if(docid.equals("22")) {
						  apmttype="24164";   
					  }else {
					      apmttype="21998"; 
					  }
				  }else {
					     apmttype="3";
				  }
			  }
			  
			  else if(!tpid.equals("0") && !DateTimeUtils.isNull(tpid).equals("")) {
				  
				 // if(tpid.equals("86") || tpid.equals("94") ) {
					  if((differ==0 && count==0 )|| differ>7) { 
		    	            apmttype="17103"; 
		               }else if(last_opd_date.equals(commencing) && count!=0) {
		    	            apmttype="17105"; 
		               }else if(differ<=7) {
		    	          if(count==1 || count==3) {
		    		          apmttype="17105";   
		    	       }else if(count==2) {
		    		         apmttype="17104"; 	 
		    	       }else if(count==4) {
		    		        apmttype="17103";
		    	       }
		              }
				  //}
				  
				  
			  }else {
		               if((differ==0 && count==0 )|| differ>15) { 
		    	            apmttype="1"; 
		               }else if(last_opd_date.equals(commencing) && count!=0) {
		    	            apmttype="3"; 
		               }else if(differ<=7) {
		    	          if(count==1 || count==3) {
		    		          apmttype="3";   
		    	       }else if(count==2) {
		    		         apmttype="2"; 	 
		    	       }else if(count==4 || count==0) {
		    		        apmttype="1";
		    	       }
		              }else if(differ>7 && differ<=15) {
		            	  if(count==3 || count==2) {
		            		  apmttype="1";
		            	  }if(count==1) {
		            		  apmttype="2"; 
		            	  }
		              }
		         }
			  
			  
					/*
					 * if(!docid.equals("22") && !docid.equals("39") && !tpid.equals("94") &&
					 * !tpid.equals("86") && payby.equals("Third Party")) { // boolean
					 * admitdate=treatmentEpisodeDAO.isAdmitdateexist(clientid); }
					 */
				/*
				 * else if(differ<=7 && count%2==0) { notAvailableSlot.setApmtType("2"); }else
				 * if(differ>7 && differ<=15 && count%2!=0) { notAvailableSlot.setApmtType("2");
				 * }
				 */
		      System.out.println(differ); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return apmttype;
	}

	public String save() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		String client = request.getParameter("client");
		String clientId = request.getParameter("clientId");
		String date = request.getParameter("date");
		String diaryUser = request.getParameter("diaryUser");
		String treatmentEpisodeName = request
				.getParameter("treatmentEpisodeName");
		String referalDate = request.getParameter("referalDate");
		String referralName = request.getParameter("referralName");
		String referralSource = request.getParameter("referralSource");
		boolean urgent = Boolean.parseBoolean(request.getParameter("urgent"));
		String ipdopd = request.getParameter("ipdopd");
		/*
		 * String referralContact = request.getParameter("referralContact");
		 * String referralLetter = request.getParameter("referralLetter");
		 */
		String payby = request.getParameter("payby");
		String invoicee = request.getParameter("invoicee");
		String authorisationCode = request.getParameter("authorisationCode");
		String spendLimit = request.getParameter("spendLimit");
		String approvedLimit = request.getParameter("approvedLimit");
		String consultationLimit = request.getParameter("consultationLimit");
		String treatmentType = request.getParameter("treatmentType");
		String referalendDate = request.getParameter("referalendDate");
		String empname=request.getParameter("empname");
		
		/*
		 * if(payby.equals("Client")){ spendLimit = "1"; }
		 * if(consultationLimit==""){ consultationLimit = "0"; }
		 */

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(
					connection);
			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(
					connection);

			TreatmentEpisode treatmentEpisode = new TreatmentEpisode();

			treatmentEpisode.setClientId(Integer.parseInt(clientId));
			treatmentEpisode.setTreatmentStartDate(date);
			treatmentEpisode.setClientName(client);
			treatmentEpisode.setDiaryUser(diaryUser);
			treatmentEpisode.setTreatmentEpisodeName(treatmentEpisodeName);
			treatmentEpisode.setReferalDate(referalDate);
			treatmentEpisode.setReferralName(referralName);
			treatmentEpisode.setReferralSource(referralSource);
			treatmentEpisode.setUrgent(urgent);
			treatmentEpisode.setIpdopd(ipdopd);
			treatmentEpisode.setReferalendDate(referalendDate);
			/*
			 * treatmentEpisode.setReferralContact(referralContact);
			 * treatmentEpisode.setReferralLetter(referralLetter);
			 */
			treatmentEpisode.setPayby(payby);
			treatmentEpisode.setInvoicee(invoicee);
			treatmentEpisode.setAuthorisationCode(authorisationCode);
			treatmentEpisode.setSpendLimit(spendLimit);
			treatmentEpisode.setApprovedLimit(approvedLimit);
			treatmentEpisode.setConsultationLimit(consultationLimit);
			treatmentEpisode.setCondition(treatmentType);
			treatmentEpisode.setEmpname(empname);
			
			treatmentEpisode.setDischargeLetter(treatmwntEpisodeForm
					.getDischargeLetter());

			int result = treatmentEpisodeDAO
					.saveTreatmentEpisode(treatmentEpisode,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}
		return null;
	}

	public String change() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		String sessions = request.getParameter("sessions");
		String treatmentepisodeid = request.getParameter("tratmentepisodeid");
		try {

			connection = Connection_provider.getconnection();
			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(
					connection);
			int reult = treatmentEpisodeDAO.updateConsultationLimit(sessions,
					treatmentepisodeid);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}
		return null;
	}

	public String set() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		String clientId = request.getParameter("clientid");
		String payby = request.getParameter("payby");
		try {

			if (payby.equals("tp")) {
				payby = Constants.PAY_BY_THIRD_PARTY;
			}

			connection = Connection_provider.getconnection();
			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(
					connection);

			ArrayList<TreatmentEpisode> treatmentEpisodeList = treatmentEpisodeDAO
					.getTreatmentEpisodeList(clientId, payby);
			TreatmentEpisode treatmentEpisode1 = treatmentEpisodeDAO
					.getLastTreatmentEpi(clientId);

			StringBuffer str = new StringBuffer();
			str.append("<select name='treatmentEpisode' id='treatmentEpisode' class='form-control showToolTip chosen' title = 'Select Episode'  onchange = 'setEpisodeDetails(this.value)'>");
			// str.append("<option value='"+treatmentEpisode1.getId()+"'>"+treatmentEpisode1.getTreatmentEpisodeName()+"</option>");

			/*if (treatmentEpisodeList.size() == 1) {
				for (TreatmentEpisode treatmentEpisode : treatmentEpisodeList) {
					str.append("<option value='0'>Select Treatment Episode</option>");
					str.append("<option value='" + treatmentEpisode.getId()
							+ "' selected>"
							+ treatmentEpisode.getTreatmentEpisodeName()
							+ "</option>");
				}
			} else {
				str.append("<option value='0'>Select Treatment Episode</option>");
				for (TreatmentEpisode treatmentEpisode : treatmentEpisodeList) {
					str.append("<option value='" + treatmentEpisode.getId()
							+ "'>" + treatmentEpisode.getTreatmentEpisodeName()
							+ "</option>");
				}
			}*/
			
			str.append("<option value='0'>Select Treatment Episode</option>");
			for (TreatmentEpisode treatmentEpisode : treatmentEpisodeList) {
				str.append("<option value='" + treatmentEpisode.getId()
						+ "'>" + treatmentEpisode.getTreatmentEpisodeName()
						+ "</option>");
			}
			str.append("</select>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String details() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		String tratmentepisodeid = request.getParameter("tratmentepisodeid");
		String selectedAppointmentid = request
				.getParameter("cookieSelectedAppointmentid");
		try {
			
			

			connection = Connection_provider.getconnection();
			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(
					connection);
			
			//truncate temp charges
			
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			int result = completeAptmDAO.deleteComplteApmt(loginInfo.getId());

			TreatmentEpisode treatmentEpisode = treatmentEpisodeDAO
					.getTreatmentEpisodeDetails(tratmentepisodeid,
							selectedAppointmentid);
			NotAvailableSlot notAvailableSlot = treatmentEpisodeDAO.getWhoPay(selectedAppointmentid);
			

			String tpnotes = "";
			
			if(notAvailableSlot.getWhopay().equals(Constants.PAY_BY_CLIENT)){
				tpnotes = treatmentEpisodeDAO.getClientNotes(notAvailableSlot.getClientId());
			}else{
				tpnotes = treatmentEpisodeDAO.getTPNotes(selectedAppointmentid);
			}

			StringBuffer str = new StringBuffer();
			str.append(treatmentEpisode.getAuthorisationCode() + "*"
					+ treatmentEpisode.getConsultationLimit() + "*"
					+ treatmentEpisode.getPayby() + "*"
					+ treatmentEpisode.getUsedSession() + "*" + tpnotes);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String getSessionsDetails() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		String tratmentepisodeid = request.getParameter("id");
		try {

			connection = Connection_provider.getconnection();
			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(
					connection);

			TreatmentEpisode treatmentEpisode = treatmentEpisodeDAO
					.getTreatmentEpisodeSessionDetails(tratmentepisodeid);
			
			String refenddate = treatmentEpisodeDAO.getRefEndDate(tratmentepisodeid);
			String reffromdate = treatmentEpisodeDAO.getRefFromDate(tratmentepisodeid);

			StringBuffer str = new StringBuffer();
			str.append(treatmentEpisode.getId() + "/"
					+ treatmentEpisode.getUsedSession() + "/" + treatmentEpisode.getSessions() + "/" + refenddate + "/" + reffromdate);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String thirdparty() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		String clientId = request.getParameter("clientid");
		try {

			connection = Connection_provider.getconnection();
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(
					connection);
			CompleteAppointment completeAppointment = completeAptmDAO
					.getInsuranceCompanyName(clientId);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(
					"" + completeAppointment.getInsuranceCompanyName() + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}
		return null;
	}

	public String move() throws Exception{
		setFromData();
		return "showTreatmentEpisode";
	}

	private void setFromData() throws Exception {

		String id = "0";

		id = Integer.toString(treatmwntEpisodeForm.getClientId());
		if (id.equals("0") || id == null) {
			id = Integer.toString(treatmwntEpisodeForm.getClientId1());
			String clientName = treatmwntEpisodeForm.getClientName1();
			treatmwntEpisodeForm.setClient(clientName);
			treatmwntEpisodeForm.setClientId(Integer.parseInt(id));
		}
		if (id.equals("0")) {
			id = (String) session.getAttribute("clientId");
			String clientName = (String) session.getAttribute("clientName");
			treatmwntEpisodeForm.setClient(clientName);
			treatmwntEpisodeForm.setClientId(Integer.parseInt(id));

		}

		String clientId = id;
		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(
					connection);
			int totalCount = treatmentEpisodeDAO
					.getTotalTreatmentEpisodeCount(clientId);
			pagination.setPreperties(totalCount);
			ArrayList<TreatmentEpisode> treatmentEpisodeList = new ArrayList<TreatmentEpisode>();

			treatmentEpisodeList = treatmentEpisodeDAO
					.getAllTreatmentEpisodeList(clientId, pagination);
			pagination.setPage_records(treatmentEpisodeList.size());
			treatmwntEpisodeForm.setTotalRecords(totalCount);
			treatmwntEpisodeForm.setPagerecords(Integer.toString(pagination
					.getPage_records()));
			treatmwntEpisodeForm.setTreatmentEpisodeList(treatmentEpisodeList);
			treatmwntEpisodeForm.setClient(treatmwntEpisodeForm.getClient());
			treatmwntEpisodeForm.setClientId1(treatmwntEpisodeForm
					.getClientId());
			treatmwntEpisodeForm.setClientName1(treatmwntEpisodeForm
					.getClient());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	public String show() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(
					connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			// set selected clientid from session
			if (session.getAttribute("sessionselectedclientid") != null) {
				String clientid = (String) session
						.getAttribute("sessionselectedclientid");
				treatmwntEpisodeForm.setClientId(Integer.parseInt(clientid));
				Client client = clientDAO
						.getSelectedSessionClientDetails(clientid);
				treatmwntEpisodeForm.setClient(client.getClientName());
			}
			else {
					    String clientid = request.getParameter("clientid");
						treatmwntEpisodeForm.setClientId(Integer.parseInt(clientid));
						Client client = clientDAO.getSelectedSessionClientDetails(clientid);
						treatmwntEpisodeForm.setClient(client.getClientName());
			}
			

			String id = "0";

			id = Integer.toString(treatmwntEpisodeForm.getClientId());
			if (id.equals("0") || id == null) {
				id = Integer.toString(treatmwntEpisodeForm.getClientId1());
				String clientName = treatmwntEpisodeForm.getClientName1();
				treatmwntEpisodeForm.setClient(clientName);
				treatmwntEpisodeForm.setClientId(Integer.parseInt(id));
			}
			if (id.equals("0")) {
				id = (String) session.getAttribute("clientId");
				String clientName = (String) session.getAttribute("clientName");
				treatmwntEpisodeForm.setClient(clientName);
				treatmwntEpisodeForm.setClientId(Integer.parseInt(id));

			}

			String clientId = id;

			int totalCount = treatmentEpisodeDAO
					.getTotalTreatmentEpisodeCount(clientId);
			pagination.setPreperties(totalCount);
			ArrayList<TreatmentEpisode> treatmentEpisodeList = new ArrayList<TreatmentEpisode>();

			treatmentEpisodeList = treatmentEpisodeDAO
					.getAllTreatmentEpisodeList(clientId, pagination);
			pagination.setPage_records(treatmentEpisodeList.size());
			treatmwntEpisodeForm.setTotalRecords(totalCount);
			treatmwntEpisodeForm.setPagerecords(Integer.toString(pagination
					.getPage_records()));
			treatmwntEpisodeForm.setTreatmentEpisodeList(treatmentEpisodeList);
			treatmwntEpisodeForm.setClient(treatmwntEpisodeForm.getClient());
			treatmwntEpisodeForm.setClientId1(treatmwntEpisodeForm
					.getClientId());
			treatmwntEpisodeForm.setClientName1(treatmwntEpisodeForm
					.getClient());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return "showTreatmentEpisode";
	}

	public String addPage() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(
					connection);
			ArrayList<TreatmentEpisode> sourceOfReferralList = treatmentEpisodeDAO
					.getSourceOfReferralList();
			treatmwntEpisodeForm.setSourceOfReferralList(sourceOfReferralList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return "addTreatmentPage";
	}

	public String edit() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		String id = request.getParameter("selectedid");

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
			ArrayList<TreatmentEpisode> sourceOfReferralList = treatmentEpisodeDAO.getSourceOfReferralList();
			treatmwntEpisodeForm.setSourceOfReferralList(sourceOfReferralList);
			TreatmentEpisode treatmentEpisode = new TreatmentEpisode();

			treatmentEpisode = treatmentEpisodeDAO.getParticularTreatEpiDetails(id);
			String clientId = Integer.toString(treatmentEpisode.getClientId());
			session.setAttribute("clientId", clientId);
			String clientName = treatmentEpisode.getClientName();
			session.setAttribute("clientName", clientName);
			treatmwntEpisodeForm.setId(treatmentEpisode.getId());
			treatmwntEpisodeForm.setClientId(treatmentEpisode.getClientId());
			treatmwntEpisodeForm.setClientId1(treatmentEpisode.getClientId());
			treatmwntEpisodeForm.setTreatmentStartDate(treatmentEpisode.getTreatmentStartDate());
			treatmwntEpisodeForm.setClientName(treatmentEpisode.getClientName());
			treatmwntEpisodeForm.setDiaryUser(treatmentEpisode.getDiaryUser());
			treatmwntEpisodeForm.setTreatmentEpisodeName(treatmentEpisode.getTreatmentEpisodeName());
			treatmwntEpisodeForm.setReferalDate(treatmentEpisode.getReferalDate());
			treatmwntEpisodeForm.setReferralName(treatmentEpisode.getReferralName());
			treatmwntEpisodeForm.setReferralSource(treatmentEpisode.getReferralSource());
			treatmwntEpisodeForm.setReferralContact(treatmentEpisode.getReferralContact());
			treatmwntEpisodeForm.setReferralLetter(treatmentEpisode.getReferralLetter());
			treatmwntEpisodeForm.setPayby(treatmentEpisode.getPayby());
			treatmwntEpisodeForm.setAuthorisationCode(treatmentEpisode.getAuthorisationCode());
			treatmwntEpisodeForm.setSpendLimit(treatmentEpisode.getSpendLimit());
			treatmwntEpisodeForm.setConsultationLimit(treatmentEpisode.getConsultationLimit());
			treatmwntEpisodeForm.setDischargeLetter(treatmentEpisode.getDischargeLetter());
			treatmwntEpisodeForm.setClient(treatmentEpisode.getClientName());
			treatmwntEpisodeForm.setInvoicee(treatmentEpisode.getInvoicee());
			treatmwntEpisodeForm.setTreatmentType(treatmentEpisode.getTreatmentType());
			treatmwntEpisodeForm.setPatientId(clientId);
			treatmwntEpisodeForm.setApprovedLimit(treatmentEpisode.getApprovedLimit());
			
			String refdate = treatmentEpisode.getReferalDate();
			if(refdate!=null || refdate.equals("")){
				refdate = DateTimeUtils.getCommencingDate2(refdate);
			}
			String refenddate = treatmentEpisode.getReferalendDate();
			if(refenddate!=null){
				refenddate = DateTimeUtils.getCommencingDate2(refenddate);
			}
			
			String treatmentEpisodeData = treatmwntEpisodeForm.getTreatmentType() +  "*" + treatmwntEpisodeForm.getTreatmentEpisodeName() +
					"*" + refdate + "*" + treatmwntEpisodeForm.getReferralName() + "*" + treatmwntEpisodeForm.getReferralSource() +
					"*" + treatmwntEpisodeForm.getPayby() + "*" +  treatmwntEpisodeForm.getInvoicee() + "*" + treatmwntEpisodeForm.getAuthorisationCode() +
					"*" + treatmwntEpisodeForm.getSpendLimit() + "*"+treatmwntEpisodeForm.getApprovedLimit()+ "*"+ treatmwntEpisodeForm.getConsultationLimit() + "*" + treatmentEpisode.isUrgent() + 
					"*" + treatmentEpisode.getIpdopd() + "*" + refenddate;
			
			
			
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(treatmentEpisodeData);
			
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}
	
	public String editsave() throws Exception{
		
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		
		try {
			
			//var url = "editsaveTreatmentEpisode?selectedid="+selectedid+"&conitionid="+conitionid+"&tepisodename="+tepisodename+"&referalDate="+referalDate+"&referralName="+referralName+"&referralSource="+referralSource+"&payby="+payby+"&invoicee="+invoicee+"&authorisationCode="+authorisationCode+"&spendLimit="+spendLimit+"&consultationLimit="+consultationLimit+" ";
			
			String selectedid = request.getParameter("selectedid");
			String conitionid = request.getParameter("conitionid");
			String tepisodename = request.getParameter("tepisodename");
			
			String referalDate = request.getParameter("referalDate");
			String referralSource = request.getParameter("referralSource");
			String referralName = request.getParameter("referralName");
			String payby = request.getParameter("payby");
			String authorisationCode = request.getParameter("authorisationCode");
			String spendLimit = request.getParameter("spendLimit");
			String approvedLimit = request.getParameter("approvedLimit");
			String consultationLimit = request.getParameter("consultationLimit");
			String invoicee = request.getParameter("invoicee");
			boolean urgent = Boolean.parseBoolean("urgent");
			String ipdopd = request.getParameter("ipdopd");
			String referalendDate = request.getParameter("referalendDate");
			
			
			
			connection = Connection_provider.getconnection();
			
			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
			
			TreatmentEpisode treatmentEpisode = new TreatmentEpisode();
			
			treatmentEpisode.setCondition(conitionid);
			treatmentEpisode.setTreatmentEpisodeName(tepisodename);
			treatmentEpisode.setReferalDate(referalDate);
			treatmentEpisode.setReferralName(referralName);
			treatmentEpisode.setReferralSource(referralSource);
			treatmentEpisode.setPayby(payby);
			treatmentEpisode.setAuthorisationCode(authorisationCode);
			treatmentEpisode.setSpendLimit(spendLimit);
			treatmentEpisode.setConsultationLimit(consultationLimit);
			treatmentEpisode.setInvoicee(invoicee);
			treatmentEpisode.setUrgent(urgent);
			treatmentEpisode.setIpdopd(ipdopd);
			treatmentEpisode.setReferalendDate(referalendDate);
			treatmentEpisode.setApprovedLimit(approvedLimit);
			int update = treatmentEpisodeDAO.editsave(treatmentEpisode,selectedid);
			
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			connection.close();
		}
		
		return null;
	}

	public String updateSave() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(
					connection);
			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(
					connection);

			TreatmentEpisode treatmentEpisode = new TreatmentEpisode();
			int id = treatmwntEpisodeForm.getId();
			treatmentEpisode.setClientId(treatmwntEpisodeForm.getClientId());
			treatmentEpisode.setTreatmentStartDate(treatmwntEpisodeForm
					.getTreatmentStartDate());
			treatmentEpisode.setClientName(treatmwntEpisodeForm.getInvoicee());
			treatmentEpisode.setDiaryUser(treatmwntEpisodeForm.getDiaryUser());
			treatmentEpisode.setTreatmentEpisodeName(treatmwntEpisodeForm
					.getTreatmentEpisodeName());
			treatmentEpisode.setReferalDate(treatmwntEpisodeForm
					.getReferalDate());
			treatmentEpisode.setReferralName(treatmwntEpisodeForm
					.getReferralName());
			treatmentEpisode.setReferralSource(treatmwntEpisodeForm
					.getReferralSource());
			treatmentEpisode.setReferralContact(treatmwntEpisodeForm
					.getReferralContact());
			treatmentEpisode.setReferralLetter(treatmwntEpisodeForm
					.getReferralLetter());
			treatmentEpisode.setPayby(treatmwntEpisodeForm.getPayby());
			treatmentEpisode.setAuthorisationCode(treatmwntEpisodeForm
					.getAuthorisationCode());
			treatmentEpisode
					.setSpendLimit(treatmwntEpisodeForm.getSpendLimit());
			treatmentEpisode.setConsultationLimit(treatmwntEpisodeForm
					.getConsultationLimit());
			treatmentEpisode.setDischargeLetter(treatmwntEpisodeForm
					.getDischargeLetter());
			treatmentEpisode.setInvoicee(treatmwntEpisodeForm.getInvoicee());
			treatmentEpisode.setTreatmentType(treatmwntEpisodeForm
					.getTreatmentType());

			int result = treatmentEpisodeDAO.updateTreatmentEpisode(
					treatmentEpisode, id);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return "updateSave";
	}

	public String delete() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		String id = request.getParameter("selectedid");
		String clientId = request.getParameter("clientId");
		String clientName = request.getParameter("clientName");
		int result = 0;
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			TreatmentEpisode treatmentEpisode = new TreatmentEpisode();
			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(
					connection);

			session.setAttribute("clientId", clientId);

			session.setAttribute("clientName", clientName);
			result = treatmentEpisodeDAO.deleteTreatmentEpisode(id);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}
		return "deleteTreatmentEpisode";
	}

	public String checkName() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}
		String treatmentName = request.getParameter("treatmentEpisodeName");
		String client = request.getParameter("client");
		String clientId = request.getParameter("clientId");
		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(
					connection);
			boolean treatmentNameExist = treatmentEpisodeDAO
					.isTreatmentNameExist(treatmentName, clientId);

			// if name already exist then set response 'false'
			if (treatmentNameExist) {
				response.getWriter().write("false");

			} else { // else set response 'true'
				response.getWriter().write("true");

			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String updatePreviousSessions() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		String id = request.getParameter("id");
		String currentdate = "";
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		currentdate = df.format(date);
		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(
					connection);
			int result = treatmentEpisodeDAO.updateSessions(id);
			ArrayList<TreatmentEpisode> list = treatmentEpisodeDAO
					.getSessionsId(id, currentdate);

			for (TreatmentEpisode treatmentEpisode : list) {
				int usedSession = Integer.parseInt(treatmentEpisode
						.getUsedSession());
				int r1 = treatmentEpisodeDAO.updateSessions(
						treatmentEpisode.getId(), usedSession);
			}

		} catch (Exception e) {

		}finally{
			connection.close();
		}
		return null;
	}
	
	
	
	public String clientaddress()throws Exception{
		
		if (!verifyLogin(request)) {
			return "login";
		}
		
		//var url = "clientaddressTreatmentEpisode?clientid="+patientId+"&treatmentepisodeid="+editTreatmentEpisode+" ";
		
		String clientid = request.getParameter("clientid");
		String appointmentid = request.getParameter("appointmentid");
		//String treatmentepisodeid = request.getParameter("treatmentepisodeid");
		
		Connection connection = null;
		
		try{
			
			
			connection = Connection_provider.getconnection();
			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			
			NotAvailableSlot na = treatmentEpisodeDAO.getAppointmentData(appointmentid);
			String treatmentepisodeid = na.getTreatmentEpisodeId();
			
			Client client = clientDAO.getPatient(Integer.parseInt(clientid));
			
			
			String clientDetails = client.getTitle()+" " + client.getFirstName()+" "+client.getLastName() + "," + client.getAddress() + "," + client.getTown() + "," + client.getPostCode() + "," + client.getDob();
			
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
		    CompleteAppointment	completeAppointment = completeAptmDAO.getInsuranceCompanyName(clientid);
		    String tpName = completeAppointment.getInsuranceCompanyName();
			
		    TreatmentEpisode treatmentEpisode = treatmentEpisodeDAO.getTreatmentEpisodeData(treatmentepisodeid);
		    String treatmenEpisodedata = treatmentEpisode.getTreatmentEpisodeName() + " " + treatmentEpisode.getSessions() + " " + "Sessions";
		    
		    ArrayList<NotAvailableSlot>reportStsuList = treatmentEpisodeDAO.getReportStatusList(treatmentepisodeid);
		    StringBuffer strid = new StringBuffer();
		    for(NotAvailableSlot notAvailableSlot : reportStsuList){
		    	strid.append(notAvailableSlot.getId() + ",");
		    }
			
		    String str = clientDetails + "#" + tpName  + "#" + treatmenEpisodedata + "#" + strid.toString() + "#" + treatmentEpisode.isReportsent();
		    
		    response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(str); 
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		
		return null;
	}
	
	
	public String savereport()throws Exception{
		if (!verifyLogin(request)) {
			return "login";
		}
		//var url = "savereportTreatmentEpisode?appointmentid="+usapmtid+"&treatmentepisodeid="+editTreatmentEpisode+"&usent="+usent+"&sentdate="+sentdate+"&sentnote="+sentnote+" ";
		Connection connection = null;
		
		try{
			
			connection = Connection_provider.getconnection();
			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
			
			String appointmentid = request.getParameter("appointmentid");
			//String treatmentepisodeid = request.getParameter("treatmentepisodeid");
			NotAvailableSlot na = treatmentEpisodeDAO.getAppointmentData(appointmentid);
			String treatmentepisodeid = na.getTreatmentEpisodeId();
			boolean usent = Boolean.parseBoolean(request.getParameter("usent"));
			String sentDate = request.getParameter("sentdate");
			String sentNote = request.getParameter("sentnote");
			
			boolean tpreportsent = Boolean.parseBoolean(request.getParameter("tpreportsent"));
			
			int update = treatmentEpisodeDAO.updateReportStatus(usent,sentDate,sentNote,appointmentid);
			
			int updatetpepisode = treatmentEpisodeDAO.updateSentReport(treatmentepisodeid,tpreportsent,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return null;
	}
	
	
	public String status() throws SQLException{
		if (!verifyLogin(request)) {
			return "login";
		}
		
		//var url = "statusTreatmentEpisode?appointmentid="+editAppointId+"&treatmentepisodeid="+editTreatmentEpisode+" ";
		String appointmentid = request.getParameter("appointmentid");
		//String treatmentepisodeid = request.getParameter("treatmentepisodeid");
		
		
		
		Connection connection = null;
		
		try{
			
			connection = Connection_provider.getconnection();
			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
			
			NotAvailableSlot na = treatmentEpisodeDAO.getAppointmentData(appointmentid);
			String treatmentepisodeid = na.getTreatmentEpisodeId();
			
			ArrayList<NotAvailableSlot>reportStsuList = treatmentEpisodeDAO.getReportStatusList(treatmentepisodeid);
			
			StringBuffer str = new StringBuffer();
			StringBuffer strid = new StringBuffer();
			
			for(NotAvailableSlot notAvailableSlot : reportStsuList){
				str.append("<div class='col-lg-2 col-md-2'>"+notAvailableSlot.getCommencing()+"</div>");
				str.append("<div class='col-lg-3 col-md-3'>"+notAvailableSlot.getApmttypetext()+"</div>");
				
				if(notAvailableSlot.isSent()){
					str.append("<div class='col-lg-1 col-md-1'><input type='checkbox' name='usent"+notAvailableSlot.getId()+"' id='usent"+notAvailableSlot.getId()+"' checked='true'></div>");
				}else{
					str.append("<div class='col-lg-1 col-md-1'><input type='checkbox' name='usent"+notAvailableSlot.getId()+"' id='usent"+notAvailableSlot.getId()+"'></div>");
				}
				
				if(notAvailableSlot.getSentdate()!=null){
					if(!notAvailableSlot.getSentdate().equals("")){
						String dt = DateTimeUtils.getCommencingDate2(notAvailableSlot.getSentdate());
						notAvailableSlot.setSentdate(dt);
					}
				}
				
				str.append("<div class='col-lg-2 col-md-2'><input type='textbox' name='sentdate"+notAvailableSlot.getId()+"' id='sentdate"+notAvailableSlot.getId()+"' value='"+notAvailableSlot.getSentdate()+"' size='8'></div>");
				str.append("<div class='col-lg-4 col-md-4'><textarea name='sentNote"+notAvailableSlot.getId()+"' id='sentNote"+notAvailableSlot.getId()+"' rows='2' cols='20'>"+notAvailableSlot.getSentNote()+"</textarea></div>");
				
				str.append("<br>");
			}
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+str.toString()+""); 
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		
		return null;
	}
	

	public TreatmentEpisodeForm getModel() {

		return treatmwntEpisodeForm;
	}

	public void prepare() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(
					connection);

			ArrayList<DiaryManagement> userList = notAvailableSlotDAO
					.getUserList(loginInfo.getId());
			treatmwntEpisodeForm.setUserList(userList);

			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(
					connection);
			ArrayList<TreatmentEpisode> sourceOfReferralList = treatmentEpisodeDAO
					.getSourceOfReferralList();
			treatmwntEpisodeForm.setSourceOfReferralList(sourceOfReferralList);

			ArrayList<Client> condtitionList = new ArrayList<Client>();
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			condtitionList = clientDAO.getTreatmentTypeList();
			treatmwntEpisodeForm.setCondtitionList(condtitionList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

	}
	public String treatmenttemp()throws Exception{
		 Connection connection=null;
		 try {
		  
		  connection=Connection_provider.getconnection();
		  TreatmentEpisodeDAO teDAO=new JDBCTreatmentEpisode(connection);
		  String id= request.getParameter("id");
		  String data= teDAO.getTreatmentTemplateData(id);
		  
		  response.setContentType("text/html");
		  response.setHeader("Cache-Control", "no-cache");
		  
		  response.getWriter().write(""+data+""); 
		  
		 } catch (Exception e) {

		  e.printStackTrace();
		 }
		 finally{
		  
		 }
		 
		 return null;
		}

}
