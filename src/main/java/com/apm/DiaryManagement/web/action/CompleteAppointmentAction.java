package com.apm.DiaryManagement.web.action;

import java.net.HttpCookie;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.jaxen.xom.XOMXPath;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.ChargesAccountProcessingDAO;
import com.apm.Accounts.eu.bi.StatementDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCChargeAccountProcesDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCStatementDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.AssesmentForms.eu.bi.AssessmentFormDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCAssessmentFormDAO;
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
import com.apm.DiaryManagement.web.form.CompleteAppointmentForm;
import com.apm.DiaryManagement.web.form.NotAvailableSlotForm;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Ipd.eu.entity.Ipd;
import com.apm.Log.eu.bi.AccountLogDAO;
import com.apm.Log.eu.blogic.jdbc.JDBCAccountLogDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.bi.ChargesReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCChargesReportDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.common.web.utils.PopulateList;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class CompleteAppointmentAction extends BaseAction implements Preparable, ModelDriven<CompleteAppointmentForm>{
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	//NotAvailableSlotForm notAvailableSlotForm = new NotAvailableSlotForm();
	CompleteAppointmentForm completeAppointmentForm = new CompleteAppointmentForm();
	private Pagination pagination = new Pagination(10, 1);
	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	public String input() throws Exception {
		if(!verifyLogin(request)){
			return "login";
		}
		String x = (String) session.getAttribute("Test");
		String selectedUser = request.getParameter("selectedUser");
		completeAppointmentForm.setUser(selectedUser);
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			
			int result = completeAptmDAO.deleteComplteApmt(loginInfo.getId());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return super.input();
	}
	
	//save payment
	public String savePayment() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			
			String invoiceid = request.getParameter("invoiceid");
			String clientName = request.getParameter("clientName");
			String date = request.getParameter("date");
			String paymode =request.getParameter("paymode");
			String payAmount = request.getParameter("payAmount");
			String clientid = request.getParameter("clientid");
			String discount = request.getParameter("discount");
			
			
			Accounts accounts = new Accounts();
			accounts.setInvoiceid(Integer.parseInt(invoiceid));
			accounts.setClientid(Integer.parseInt(clientid));
			accounts.setClientName(clientName);
			accounts.setHowPaid(paymode);
			accounts.setPayAmount(Integer.parseInt(payAmount));
			accounts.setCommencing(date);
			accounts.setPayby("0");
			
		
			
			int result = completeAptmDAO.savePaymentForInvoice(accounts,discount);
			int result1 = completeAptmDAO.updateInvoiceStatus1(invoiceid,discount);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return null;
	}
	
	
	
	public String selfInvSum() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			String invoiceid = request.getParameter("invoiceid");
			
			int total = completeAptmDAO.getSelfInvoiceTotal(invoiceid);
			
			 
			
				double total1 = 0;
			
				ArrayList<CompleteAppointment> clientChargeListDetail = new ArrayList<CompleteAppointment>();
				connection = Connection_provider.getconnection();
				
				clientChargeListDetail = completeAptmDAO.getSelfPatientChrageDetails(invoiceid);
				completeAppointmentForm.setClientChargeListDetail(clientChargeListDetail);
				
				StringBuffer str = new StringBuffer();
				str.append("<table  id = 'selfChargeDesk1' cellpadding='0' cellspacing='0' class='my-table' > ");
				
				int count=1;

				str.append("<tr>");
				str.append("<th>Id</th> ");
				str.append("<th>PayBy</th> ");
				str.append("<th>Appointment Type</th> ");
				str.append("<th>Charge</th> ");
				
				str.append("</tr>");
				
				for(CompleteAppointment completeAppointment1:clientChargeListDetail){
					
				str.append("<tr>");
				str.append("<td>"+count+"</td>");
				if(completeAppointment1.getPayBuy().equals("0")){
					str.append("<td>Self</td>");
				}else{
					str.append("<td>Third party</td>");
				}
				
				str.append("<td>"+completeAppointment1.getApmtType()+"</td>");
				str.append("<td>"+completeAppointment1.getCharges()+"</td>");
				count = count + 1;
				}
				str.append("</table>");
				for(CompleteAppointment completeAppointment2:clientChargeListDetail){
					total1 = completeAppointment2.getChargeTotal();
				}

				str.append("<tr>");
				str.append("<th colspan='3'>Total</th> ");
				
				str.append("<th colspan='3'>"+Constants.getCurrency(loginInfo)+total+"</th> ");
				str.append("</tr>");
				str.append("<input class = 'form-control' type = 'hidden' id = 'hiddentotal1' name = 'hiddentotal1' value = '"+total+" '>");
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
	
	public String invoiced() throws Exception{
		String appointmentid = request.getParameter("appointmentid");
//		System.out.println(appointmentid);
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			 
			boolean isinvoiced = completeAptmDAO.checkAppointmentInvoiced(appointmentid);
			String result = "0";
			if(isinvoiced){
				result = "1";
			}
			 int upstatus = accountsDAO.resetOpdStatus(Integer.parseInt(appointmentid),0);
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+result+""); 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return null;
	}
	
	public String updateInvoice() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			
			String invoiceid = request.getParameter("invoiceid");
			String action = request.getParameter("action");
			
			String date = DateTimeUtils.getDateinSimpleFormate(new Date());
			String stemp[] = date.split(" ");
			
			String temp[] = stemp[0].split("-");
			date = temp[2] + "-" + temp[1] + "-" + temp[0];
			
			int result = completeAptmDAO.updateInvoive(invoiceid,action,date);
			
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return null;
	}
	
	
	public String updateAccount() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			//logdata
			AccountLogDAO accountLogDAO = new JDBCAccountLogDAO(connection);
			BedDao beddao=new JDBCBedDao(connection);
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
		/*	var clientid = read_cookie("cookieClientId"); 
			var practitionerid = read_cookie("cookiePractitionerId");
			var clientName = read_cookie("cookieUserName");
			var practitionerName = read_cookie("cookiePractitioner");*/
			
			String standard_chargeid=request.getParameter("standard_chargeid");
			String ipdid=request.getParameter("ipdid");
			String clientid = request.getParameter("clientid");
			String practitionerid = request.getParameter("practitionerid");
			String clientName = request.getParameter("clientName");
			String practitionerName = request.getParameter("practitionerName");
			String appointmentid = request.getParameter("appointmentid");
			String action = request.getParameter("action");
			String location = request.getParameter("location");
			String ipd = request.getParameter("ipd");
			String gpriscid = request.getParameter("gpriscid");
			String date= request.getParameter("date");
			String vaccineId=request.getParameter("vaccineId");
			if(date==null){
				
				date= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			} else {
				date = DateTimeUtils.getCommencingDate1(date);
			}
			
			if(date!=null){
				if(date.equals("")){
					date= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
				}
			}
			
			
			String ginvstid = request.getParameter("ginvstid");
			String invreqid="";
			if(ginvstid!=null){
				if(!ginvstid.equals("")){
					invreqid=investigationDAO.getInveReqId(Integer.parseInt(ginvstid));
				}
			}
			String wardid="0";
			
			Bed bed=beddao.getEditIpdData(ipdid);
		    if(bed!=null) {
		      	  
		    	 wardid=bed.getWardid(); 
		    }
		    	
		
			if(standard_chargeid!=null){
				
				location="1";
				action="Charge";
			    ipd="1";	
			}
			if(gpriscid==null){
				gpriscid = "0";
			}
			if(ginvstid==null){
				ginvstid = "0";
			}
			if(ipdid==null){
				ipdid="0";
			}
		
			//int tratmentepisodeid = Integer.parseInt(request.getParameter("tratmentepisodeid"));
			//int treatmenntsessions = Integer.parseInt(request.getParameter("treatmenntsessions"));
			
			
			CompleteAppointment completeAppointment = new CompleteAppointment();
			completeAppointment.setClientId(clientid);
			completeAppointment.setPractitionerId(practitionerid);
			completeAppointment.setUser(clientName);
			completeAppointment.setInvoiceDate(date);
			if(action.equals("cash")){
				action="Charge";
			}
			completeAppointment.setChargeType(action);
			completeAppointment.setAppointmentid(appointmentid);
			completeAppointment.setLocation(location);
			if(appointmentid!=null){
				//  21 dec 2017 appointmentid=0
				if(!appointmentid.equals("0")){
					NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
					NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getOTData(appointmentid);
					if(!notAvailableSlot.getProcedure().equals("0")){
						UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
						UserProfile userProfile = userProfileDAO.getUserprofileDetails(notAvailableSlot.getSurgeonid());
						completeAppointment.setLocation(userProfile.getDiciplineName());
					}
				}
			}
			
			completeAppointment.setPractitionerName(practitionerName);
			
			completeAppointment.setIpd(ipd);
			completeAppointment.setGpriscid(gpriscid);
			completeAppointment.setGinvstid(ginvstid);
			completeAppointment.setAdditionalcharge_id(standard_chargeid);
			completeAppointment.setWardid(wardid);
			int pkgid =0;
			if(!ginvstid.equals("0")){
				pkgid =  investigationDAO.getPkgIDFromInvID(ginvstid);
			}
			completeAppointment.setPkgid(pkgid);
		    
			completeAppointment.setIpdid(Integer.parseInt(ipdid));
			
			ArrayList<Accounts> payByList = completeAptmDAO.getPayByList(appointmentid,loginInfo.getId());
			ArrayList<CompleteAppointment>assesmentList = completeAptmDAO.getCompleteApmtList(appointmentid,loginInfo.getId());
			
			int chkPolicyExcess = 0;
			for(CompleteAppointment c1 : assesmentList){
				if(c1.getApmtType().equalsIgnoreCase(Constants.POLICYEXCESS)){
					chkPolicyExcess = 1;
					completeAppointment.setPolicyExcess(Integer.toString(chkPolicyExcess));
				}
			}
			int selfInvoice = 0;
			int invoice = 0;
			int tpInvoice = 0;
			int selfInvoice1 = 0;
			int invoice1 = 0;
			int tpInvoice1 = 0;
			
			int res = completeAptmDAO.updateArrievedStatus(appointmentid);
			
			for(Accounts accounts : payByList){
				if(accounts.getPayBy()==0){
					completeAppointment.setPayBuy("0");
					
					if(chkPolicyExcess==1){
						completeAppointment.setPolicyExcess("1");
					}else{
						completeAppointment.setPolicyExcess("0");
					}
					
															
					selfInvoice = completeAptmDAO.saveAmpmInvoice(completeAppointment,loginInfo.getId(),loginInfo.getUserId());
					invoice = selfInvoice;
					//logdata
					//selfInvoice1 = accountLogDAO.saveAmpmInvoice(completeAppointment,invoice);
					invoice1 = selfInvoice1;
					
				}else{
					completeAppointment.setPayBuy("1");
					
					completeAppointment.setPolicyExcess("0");
					
					tpInvoice = completeAptmDAO.saveAmpmInvoice(completeAppointment,loginInfo.getId(),loginInfo.getUserId());
					invoice = tpInvoice;
					//logdata
				//	tpInvoice1 = accountLogDAO.saveAmpmInvoice(completeAppointment,invoice);
					invoice1 = tpInvoice1;
					
				}
				MasterDAO masterDAO= new JDBCMasterDAO(connection);
				if(vaccineId!=null){
					for (String vaccineid : vaccineId.split(",")) {
						if(vaccineid.equals("0")){
							continue;
						}
						masterDAO.saveChargeIdOfVaccine(invoice,vaccineid);
					}
		    		
		    	}
				
				
				 IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				 int z = 1;
				for(CompleteAppointment appointment : assesmentList){
					if(accounts.getPayBy() == Integer.parseInt(appointment.getPayBuy())){
						
						if(appointment.getMasterchargetype().equals("INVESTIGATION")){
							String ginventeredcharges = request.getParameter("ginventeredcharges");
							String tempz[] = ginventeredcharges.split("~");
							String chargez = tempz[z];
							appointment.setCharges(chargez);
							z++;
							
							String wid = "0";
							  AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
								int bedid = assessmentFormDAO.getIpdBedno(clientid);
								
								if(bedid!=0){
									BedDao bedDao=new JDBCBedDao(connection);
									
									 ipdid = assessmentFormDAO.getAdmissionid(clientid);
									 bed = bedDao.getEditIpdData(ipdid);
									 wid = bed.getWardid();
								
								}
								
								String tpid = "0";
								ClientDAO clientDAO = new JDBCClientDAO(connection);
								Client client = clientDAO.getClientDetails(clientid);
								tpid = client.getTypeName();
								
								
								if(!ipdDAO.checkifMainTp(tpid)){
									 
									String temptpid= ipdDAO.getFollowerTp(tpid); 
									if(temptpid!=null){
										
										if(!temptpid.equals("0")){
											 tpid=temptpid;  
											 
											 String chargeid = completeAptmDAO.getSelectedChargeId(tpid,completeAppointment.getMasterchargetype(),completeAppointment.getApmtType());
											 completeAppointment.setChargeId(chargeid);
										}
									}
									
								}
								if(loginInfo.isUpdate_investigation_charge()){
									boolean checkinvchargeExist = completeAptmDAO.checkInvchargeExsist(wid,tpid,appointment.getApmtType(),appointment.getMasterchargetype());
									if(checkinvchargeExist){
									
										String previouscharge= completeAptmDAO.getPreviousCharge(wid,tpid,appointment.getApmtType(),appointment.getMasterchargetype(),appointment.getCharges());
										int upd = completeAptmDAO.updateInvestigationCharge(wid,tpid,appointment.getApmtType(),appointment.getMasterchargetype(),appointment.getCharges());
										if(upd>0){
											AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
											int log=0;
											String appid=completeAptmDAO.getAppointmentid(wid,tpid,appointment.getApmtType(),appointment.getMasterchargetype(),appointment.getCharges());
											log= appointmentTypeDAO.insertappointmentlog(loginInfo.getUserId(), appointment.getCharges(), appid,previouscharge);
										}
									}else{
										int ress = completeAptmDAO.saveInvestigationCharge(wid,tpid,appointment.getApmtType(),appointment.getMasterchargetype(),appointment.getCharges());
									}
								}
								
						}
						appointment.setClientId(clientid);
						appointment.setInvestigation_request_id(invreqid);
					    	int result = completeAptmDAO.saveInvoiceAssesment(appointment,invoice);
					    	
					    	
					    	
					    	if(ginvstid!=null){
								if(ginvstid.equals("")||ginvstid.equals("0")){
									
								}else{
									int realInvestigationId=investigationDAO.getinvestigationIdByApmtName(appointment.getApmtType(), invreqid,pkgid);
									investigationDAO.saveChargesToInvestigation(appointment.getCharges(), ""+realInvestigationId, String.valueOf(result),invreqid);
								}
							}
						/*//update product quantity
						boolean checkInventoryproduct = ipdDAO.checkInventoryChargeType(appointment.getMasterchargetype());
						if(!checkInventoryproduct){
							Product product = completeAptmDAO.getInventoryProductDetails(Integer.toString(appointment.getProdid()));
							
							if(product.getStock()==null) {
								  product.setStock("0");
							}
							int rmainQty = Integer.parseInt(product.getStock()) - appointment.getQuantity();
							int update = completeAptmDAO.updateProdStock(rmainQty,Integer.toString(appointment.getProdid()));
						}*/
					}
				}
				
				//logdata
				String status = "Created";
				selfInvoice1 = accountLogDAO.saveAmpmInvoice(completeAppointment,invoice,status,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
				invoice1 = selfInvoice1;
				
			}
			
			
			
		/*	for(CompleteAppointment appointment : assesmentList){
				int result = completeAptmDAO.saveInvoiceAssesment(appointment,invoice);
			}*/
			
			//treatmenntsessions = treatmenntsessions-1;
			//int update = completeAptmDAO.updateTreatmentEpisodeSession(tratmentepisodeid,treatmenntsessions);
			//current date and time in dd/mm/yyyy
			String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
			String datetemp[] = currentDate.split(" ");
			String temp1[] = datetemp[0].split("-");
			String date1 = temp1[0] + "/" + temp1[1] + "/" + temp1[2];
			String time = (datetemp[2]+" "+datetemp[3]);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			NotAvailableSlot n = notAvailableSlotDAO.getApmtDetailsForLog(Integer.parseInt(appointmentid));
			String commencingTemp = n.getCommencing();
			String apmtstatus = "Completed";
			int logsave = notAvailableSlotDAO.saveApmtInLog(Integer.parseInt(appointmentid),date1,time,loginInfo.getUserId(),n.getClientId(),commencingTemp,n.getSTime(),apmtstatus,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
			int updatechargestatus = notAvailableSlotDAO.updateChargeStatus(appointmentid);	
			
			int result = completeAptmDAO.deleteComplteApmt(loginInfo.getId());
			
		
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+invoice+""); 
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return null;
	}
	
	/*public String modifyAccountLog(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			//logdata
			AccountLogDAO accountLogDAO = new JDBCAccountLogDAO(connection);			
		
			String clientid = request.getParameter("clientid");
			String practitionerid = request.getParameter("practitionerid");
			String clientName = request.getParameter("clientName");
			String practitionerName = request.getParameter("practitionerName");
			String appointmentid = request.getParameter("appointmentid");
			String action = request.getParameter("action");
			
			String date = DateTimeUtils.getDateinSimpleFormate(new Date());
			String stemp[] = date.split(" ");
			
			String temp[] = stemp[0].split("-");
			date = temp[2] + "-" + temp[1] + "-" + temp[0];
			
			
			CompleteAppointment completeAppointment = new CompleteAppointment();
			completeAppointment.setClientId(clientid);
			completeAppointment.setPractitionerId(practitionerid);
			completeAppointment.setUser(clientName);
			completeAppointment.setInvoiceDate(date);
			completeAppointment.setChargeType(action);
			completeAppointment.setAppointmentid(appointmentid);
			completeAppointment.setPractitionerName(practitionerName);
			
			ArrayList<Accounts> payByList = completeAptmDAO.getPayByList(appointmentid);
			ArrayList<CompleteAppointment>assesmentList = completeAptmDAO.getCompleteApmtList(appointmentid);
			
			int selfInvoice1 = 0;
			int invoice1 = 0;
			int tpInvoice1 = 0;
			
					
					//logdata
					selfInvoice1 = accountLogDAO.updateAmpmInvoice(completeAppointment);
					invoice1 = selfInvoice1;
					
					
				
			
			
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}*/
	
	
	public String cancel() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			
			int appointmentid = Integer.parseInt(request.getParameter("appointmentid"));
			
			int delete = completeAptmDAO.deleteComplteApmt(appointmentid,loginInfo.getId());
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return null;
	}
	
	
	public String addnewmodifyinvoicecharge() throws Exception {
		if(!verifyLogin(request)){
			return "login";
		}
		
		String chargetypeid = request.getParameter("chargetypeid");
		String invoiceid = request.getParameter("invoiceid");
		String quantity = request.getParameter("quantity");
		String masterchargetype = request.getParameter("masterchargetype");
		String mannualcharge = request.getParameter("mannualcharge");
		String manualprice = request.getParameter("manualprice");
		String visitingconsulatntdr = request.getParameter("visitingconsulatntdr");
		String isindisharecharge = request.getParameter("isindisharecharge");
		String date = request.getParameter("date");
		Connection connection = null;
		
		try{
			if(date==null){
				date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			} else {
				date = DateTimeUtils.getCommencingDate1(date);
			}
			connection = Connection_provider.getconnection();
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
			
			AppointmentType	appointmentType = appointmentTypeDAO.getAppointment(Integer.parseInt(chargetypeid));
			
			if(chargetypeid.equals("0")){
				appointmentType.setName(mannualcharge);
				appointmentType.setCharges(manualprice);
			}
			
			
			CompleteAppointment completeAppointment = completeAptmDAO.getModifyInvoiceDetails(invoiceid);
			
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			boolean checkInvenoryProduct = ipdDAO.checkInventoryChargeType(masterchargetype);
			if(!checkInvenoryProduct){
				Product product = completeAptmDAO.getInventoryProductDetails(chargetypeid);
				appointmentType.setName(product.getProduct_name());
				appointmentType.setCharges(product.getSale_price());
				completeAppointment.setProdid(Integer.parseInt(chargetypeid));
			}
			completeAppointment.setIsindisharecharge(isindisharecharge);
			completeAppointment.setVisitingconsulatntdrid(visitingconsulatntdr);
			
			completeAppointment.setApmtType(appointmentType.getName());
			completeAppointment.setCharges(manualprice);
			completeAppointment.setQuantity(Integer.parseInt(quantity));
			completeAppointment.setMasterchargetype(masterchargetype);
			completeAppointment.setManualcharge(mannualcharge);
			completeAppointment.setAdditionalcharge_id(chargetypeid);
			completeAppointment.setChargeId(String.valueOf(appointmentType.getId()));
			completeAppointment.setInvoiced(completeAptmDAO.getChargesInvoiceIdByApmInvoiveIdNew(invoiceid));
			completeAppointment.setCommencing(date);
			String practitionerId="";
			String practitionerName="";
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ChargesAccountProcessingDAO chargesAccountProcessingDAO=new  JDBCChargeAccountProcesDAO(connection);
			int compulsary_consultant=chargesAccountProcessingDAO.isCompulasryConsultant(masterchargetype);
			if (masterchargetype != null) {
				if (masterchargetype.equals("IPD Visiting Charge") || masterchargetype.equals("Consultation Charge")||compulsary_consultant==1) {
					practitionerId = visitingconsulatntdr;
					practitionerName = userProfileDAO.getFullName(practitionerId);
				}
			}
			
			String taxtypids=DateTimeUtils.isNull(request.getParameter("taxtypes"));
			String chargedescription=DateTimeUtils.isNull(request.getParameter("chargedescription"));
			//lokesh
			if(taxtypids!=null){
				for(String id:taxtypids.split(",")){
					if(id.equals("0")){
						continue;
					}
					String per[]= id.split("~");
					if(per[0].equals("1")){
						completeAppointment.setTax1(per[1]);
					}else if(per[0].equals("2")){
						completeAppointment.setTax2(per[1]);
					}else if(per[0].equals("3")){
						completeAppointment.setTax3(per[1]);
					}
				}
			}
			
			double totaltax=0;
			totaltax=DateTimeUtils.convertToDouble(completeAppointment.getTax1())+DateTimeUtils.convertToDouble(completeAppointment.getTax2())+DateTimeUtils.convertToDouble(completeAppointment.getTax3());
			double taxamt=DateTimeUtils.convertToDouble(completeAppointment.getCharges())*(totaltax/100);
			double amt=DateTimeUtils.convertToDouble(completeAppointment.getCharges())+taxamt;
			amt=(amt*100.00)/100.0;
			completeAppointment.setCharges(""+(amt));
			completeAppointment.setChargedescription(chargedescription);
			
			
			completeAppointment.setPractitionerId(practitionerId);
			completeAppointment.setPractitionerName(practitionerName);
			int save = completeAptmDAO.saveModifyInvoiceNewCharge(completeAppointment,loginInfo.getId());
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return null;
	}
	
	
	public String saveCharge() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Double total = 0.00;
		String totalx = "";
		
		
		String user = request.getParameter("cookieUserName");
		String apmtType = request.getParameter("apmtTypeId");
		
		String charge = request.getParameter("charge");
		String startTime = request.getParameter("cookieStarttime");
		String duration = request.getParameter("cookieDuration");
		String practitionerId = request.getParameter("cookiePractitionerId");
		String clientId = request.getParameter("cookieClientId");
		String practitionerName = request.getParameter("cookiePractitioner");
		String commencing = request.getParameter("cookiecommencing");
		String payBuy = request.getParameter("payBuy");
		String markAppointment = request.getParameter("markAppointment");
		String apppointmentid = request.getParameter("cookieSelectedAppointmentid");
		String masterchargetype  = request.getParameter("chargetype");
		String quantity = request.getParameter("quantity");
		String mannualcharge = request.getParameter("mannualcharge");
		String visitingconsulatntdr = request.getParameter("visitingconsulatntdr");
		String isindisharecharge = request.getParameter("isindisharecharge");
		int result = 0;
		Connection connection = null;
		
		try{
			CompleteAppointment completeAppointment = new CompleteAppointment();
			connection = Connection_provider.getconnection();
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getOTData(apppointmentid);
			if (masterchargetype != null) {
				if (masterchargetype.equals("IPD Visiting Charge") || masterchargetype.equals("Consultation Charge")) {
					practitionerId = visitingconsulatntdr;
					//practitionerName = userProfileDAO.getReferalDrName(visitingconsulatntdr);
					//  16 May 2018
					practitionerName = userProfileDAO.getFullName(practitionerId);
				}
			}
			
			completeAppointment.setUser(user);
			completeAppointment.setIsindisharecharge(isindisharecharge);
			completeAppointment.setVisitingconsulatntdrid(visitingconsulatntdr);
			
			apmtType = notAvailableSlot.getApmtType();
			
			String appointmentTypeName = completeAptmDAO.getAppointmentTypeName(apmtType);
			completeAppointment.setApmtType(notAvailableSlot.getApmttypetext());
			completeAppointment.setCharges(notAvailableSlot.getChargeamout());
			completeAppointment.setStartTime(startTime);
			completeAppointment.setDuration(duration);
			completeAppointment.setPractitionerId(""+notAvailableSlot.getDiaryUserId()+"");
			completeAppointment.setPractitionerName(practitionerName);
			completeAppointment.setClientId(notAvailableSlot.getClientId());
			completeAppointment.setCommencing(notAvailableSlot.getCommencing());
			completeAppointment.setPayBuy(payBuy);
			completeAppointment.setMarkAppointment(markAppointment);
			completeAppointment.setAppointmentid(apppointmentid);
			completeAppointment.setQuantity(Integer.parseInt(quantity));
			completeAppointment.setMasterchargetype(masterchargetype);
			completeAppointment.setAdditionalcharge_id(apmtType);
			completeAppointment.setManualcharge(mannualcharge);
			
			if(!apppointmentid.equals("0")){
				ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
				NotAvailableSlot otobj = chargesReportDAO.getOtAppointmentDetails(Integer.parseInt(apppointmentid));
				if(!otobj.getSurgeon().equals("0")){
					completeAppointment.setMasterchargetype(otobj.getProcedure());
				}
			}
			
			
			String appointmentTYpeText = notAvailableSlotDAO.getAppointmentTypeText(apmtType);
			
			boolean checkAppointTypeExist = completeAptmDAO.checkAppointTypeExist(appointmentTYpeText,apppointmentid,loginInfo.getId());
			if(!checkAppointTypeExist){
				
				result = completeAptmDAO.saveCharge(completeAppointment,apppointmentid,loginInfo.getId());
			}
			
			if(!notAvailableSlot.getProcedure().equals("0")){
				completeAppointment.setApmtType("0");
				//completeAppointment.setMasterchargetype(notAvailableSlot.getProcedure());
				completeAppointment.setMasterchargetype("IP / OP PROCEDURE");
				completeAppointment.setManualcharge(Constants.SURGEON_CHARGE);
				completeAppointment.setCharges(notAvailableSlot.getPsurcharge());
				
				 appointmentTYpeText = completeAppointment.getManualcharge();
				 checkAppointTypeExist = completeAptmDAO.checkAppointTypeExist(appointmentTYpeText,apppointmentid,loginInfo.getId());
				if(!checkAppointTypeExist){
					result = completeAptmDAO.saveCharge(completeAppointment,apppointmentid,loginInfo.getId());
				}
				
				
				completeAppointment.setManualcharge(Constants.ANISTHESIA_CHARGE);
				completeAppointment.setCharges(notAvailableSlot.getPanetcharge());
				
				 appointmentTYpeText = completeAppointment.getManualcharge();
				 checkAppointTypeExist = completeAptmDAO.checkAppointTypeExist(appointmentTYpeText,apppointmentid,loginInfo.getId());
				if(!checkAppointTypeExist){
					result = completeAptmDAO.saveCharge(completeAppointment,apppointmentid,loginInfo.getId());
				}
				
				completeAppointment.setManualcharge(Constants.SIC_CHARGE);
				completeAppointment.setCharges(notAvailableSlot.getSic());
				
				 appointmentTYpeText = completeAppointment.getManualcharge();
				 checkAppointTypeExist = completeAptmDAO.checkAppointTypeExist(appointmentTYpeText,apppointmentid,loginInfo.getId());
				if(!checkAppointTypeExist){
					result = completeAptmDAO.saveCharge(completeAppointment,apppointmentid,loginInfo.getId());
				}
				
				completeAppointment.setManualcharge(Constants.ASSISTING_STAFF_CHARGE);
				if(notAvailableSlot.getAssistaffcharge()!=null){
					if(notAvailableSlot.getAssistaffcharge().equals("")){
						notAvailableSlot.setAssistaffcharge("0");
					}
				}else{
					notAvailableSlot.setAssistaffcharge("0");
				}
				completeAppointment.setCharges(notAvailableSlot.getAssistaffcharge());
				
				 appointmentTYpeText = completeAppointment.getManualcharge();
				 checkAppointTypeExist = completeAptmDAO.checkAppointTypeExist(appointmentTYpeText,apppointmentid,loginInfo.getId());
				if(!checkAppointTypeExist){
					result = completeAptmDAO.saveCharge(completeAppointment,apppointmentid,loginInfo.getId());
				}
				
			}else{
				//  10 July 2018 /to set registration charge
				boolean flag = completeAptmDAO.isclientIdInApmt(clientId);
				if(!flag){
					boolean flag2 = completeAptmDAO.isclientRegisterChargeAdded(clientId,loginInfo.getId());
					if(!flag2){
						double opdregcharge =  completeAptmDAO.getOpdRegCharge();
						if (opdregcharge>0) {
							completeAppointment.setCharges(""+opdregcharge);
							completeAppointment.setManualcharge("OPD Registration Charge");
							completeAppointment.setMasterchargetype("Registration Charge");
							result = completeAptmDAO.saveCharge(completeAppointment,apppointmentid,loginInfo.getId());
						}
					
					}
				}
			}
			
			
			
			//Excess amount if Payee is TP 
			if(payBuy.equals("1")){ 
			boolean checkPolicyExcessExist = completeAptmDAO.checkExcessExist(apppointmentid,clientId,loginInfo.getId()); // Check whether Excess Already entered in db for particular appoint and treatment episode
			if(!checkPolicyExcessExist){ //If not exist then
				int treatmentSession = completeAptmDAO.getTreatmentSession(apppointmentid,clientId); // get  session 
				if(treatmentSession == 1){ // if treatment session is 1 then only raise charge against client for policy Excess amt
				int saveexcess = completeAptmDAO.saveExcessCharge(completeAppointment,loginInfo.getId()); // Save the amount in apm_compt_patient table
				}
			}
			}
			
			
			
			ArrayList<CompleteAppointment> clientChargeListDetail = new ArrayList<CompleteAppointment>();

			clientChargeListDetail = completeAptmDAO.getPatientChrageDetails(clientId,commencing,apppointmentid,loginInfo.getId());
			
			for(CompleteAppointment completeAppointment2:clientChargeListDetail){
				total = completeAppointment2.getChargeTotal();
				totalx = completeAppointment2.getChargeTotalx();
			}
		//	completeAppointmentForm.setChargeTotal(total);
			
			 String textAjax = new String();
			 
			 textAjax = ("<input class = 'form-control' type = 'text' id = 'chargeTotal' name = 'chargeTotal' disabled = 'disabled' value = '"+Constants.getCurrency(loginInfo)+totalx+" '>");
			
			 
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				
				response.getWriter().write(""+textAjax.toString()+""); 
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return null;
	}
	
	//This method now called from BookappointmentAjaxAction 
	public String cashDesk() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		String id = request.getParameter("selectedUser");
		String date = request.getParameter("date");
		if(date==null){
			date="";
		} 
		if(date.equals("")){
			date = DateTimeUtils.getDateinSimpleFormate(new Date());
			String stemp[] = date.split(" ");
			
			String temp[] = stemp[0].split("-");
			date = temp[2] + "-" + temp[1] + "-" + temp[0];
		}  
			
			
		String apmtSlotId = request.getParameter("apmtSlotId");
//		System.out.println(id);
		double total = 0;
		String totalx = "";
		Connection connection = null;
		
		try{
			CompleteAppointment completeAppointment = new CompleteAppointment();
			ArrayList<CompleteAppointment> clientChargeListDetail = new ArrayList<CompleteAppointment>();
			connection = Connection_provider.getconnection();
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			clientChargeListDetail = completeAptmDAO.getPatientChrageDetails(id,date,apmtSlotId,loginInfo.getId());
			completeAppointmentForm.setClientChargeListDetail(clientChargeListDetail);
			
				//total = completeAppointment2.getChargeTotal();
				
					
				
			StringBuffer str = new StringBuffer();
			

			//Practitioner Mail
		/*	str.append("<table  id = 'cashDesk' cellpadding='0' cellspacing='0' class='my-table' > ");
			
			

			str.append("<tr>");
			str.append("<th>Id</th> ");
			str.append("<th>PayBy</th> ");
			str.append("<th>Appointment Type</th> ");
			str.append("<th>Quantity</th> ");
			str.append("<th>Charge</th> ");
			str.append("<th>Delete</th> ");
			str.append("</tr>");*/
			int count = 0;
			
			for(CompleteAppointment completeAppointment1:clientChargeListDetail){
				
			str.append("<tr>");
			//str.append("<td>"+completeAppointment1.getId()+"</td>");
			if(completeAppointment1.getPayBuy().equals("0")){
				str.append("<td>Self</td>");
			}else{
				str.append("<td>Third party</td>");
			}
			str.append("<td>"+completeAppointment1.getMasterchargetype()+"</td>");
			str.append("<td id='invchargenameid"+count+"'>"+completeAppointment1.getApmtType()+"</td>");
			str.append("<td style='text-align:center'>"+completeAppointment1.getQuantity()+"</td>");
			
			if(completeAppointment1.getMasterchargetype().equals("INVESTIGATION")){
				str.append("<td  style='text-align:right'><input readonly='readonly' class='invunitchargecase' type='number' onchange='showinvchargedetails("+completeAppointment1.getId()+",this.id,this.value)' id='invchargeid"+count+"' name='invchargeid"+count+"' value='"+completeAppointment1.getCharges()+"' /></td>");
			}else{
				str.append("<td  style='text-align:right'><input readonly='readonly' class='invunitchargecase' type='number' onchange='showinvchargedetails("+completeAppointment1.getId()+",this.id,this.value)' id='invchargeid"+count+"' name='invchargeid"+count+"' value='"+completeAppointment1.getCharges()+"' /></td>");
			}
			
			double charge = Double.parseDouble(completeAppointment1.getCharges())*completeAppointment1.getQuantity();
			str.append("<td style='text-align:right'>"+Constants.getCurrency(loginInfo)+"<span id='invamtchargeid'>"+DateTimeUtils.changeFormat(charge)+"</span></td>");
			if(count==0){
				str.append("<td><img src='common/images/delete.gif'></img></td>");

			}else{
				str.append("<td onclick = 'confirmedDelete1("+completeAppointment1.getId()+")'><img src='common/images/delete.gif'></img></td>");

			}

			str.append("</tr>");
			count = count+1;
			}
			//str.append("</table>");
			for(CompleteAppointment completeAppointment2:clientChargeListDetail){
				total = completeAppointment2.getChargeTotal();
				totalx = completeAppointment2.getChargeTotalx();
			}

			str.append("<tr style='background-color: #efefef;'>");
			str.append("<th colspan='5' style='font-size: 13px;font-weight: bold;'>Total</th> ");
			
			str.append("<th style='font-size: 13px;font-weight: bold;' colspan='5'>"+Constants.getCurrency(loginInfo)+totalx+"</th> ");
			str.append("</tr>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+""); 
			
			
			
			
				String textAjax = new String();
			 
				textAjax = ("<input class='form-control' type = 'hidden' id = 'hiddenTotal' name = 'hiddenTotal' value = '"+Constants.getCurrency(loginInfo)+totalx+" '>");
			
			 
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				
				response.getWriter().write(""+textAjax.toString()+"");
			
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			connection.close();
		}
		return null;
	}
	
	
	public String invnewcharge() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		String dbid = request.getParameter("dbid");
		String charge = request.getParameter("charge");
		String clientid=request.getParameter("clientid");
		Connection connection = null;
		
		try{
			
			ArrayList<CompleteAppointment> clientChargeListDetail = new ArrayList<CompleteAppointment>();
			connection = Connection_provider.getconnection();
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			
			int upd = completeAptmDAO.updateInveNewCharge(dbid,charge);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+clientid+"");
		}catch (Exception e) {
			e.printStackTrace();
		}
			
			
		return null;
	}
	
	public String submitInvoice() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
	
		String invoiceId = request.getParameter("invoiceId");
		
		double total = 0;
		Connection connection = null;
		
		try{
			
			ArrayList<CompleteAppointment> clientChargeListDetail = new ArrayList<CompleteAppointment>();
			connection = Connection_provider.getconnection();
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			clientChargeListDetail = completeAptmDAO.getSelfPatientChrageDetails(invoiceId);
			completeAppointmentForm.setClientChargeListDetail(clientChargeListDetail);
			
			StringBuffer str = new StringBuffer();
			str.append("<table  id = 'selfChargeDesk' cellpadding='0' cellspacing='0' class='my-table' > ");
			
			int count=1;

			str.append("<tr>");
			str.append("<th>Id</th> ");
			str.append("<th>PayBy</th> ");
			str.append("<th>Appointment Type</th> ");
			str.append("<th>Charge</th> ");
			
			str.append("</tr>");
			
			for(CompleteAppointment completeAppointment1:clientChargeListDetail){
				
			str.append("<tr>");
			str.append("<td>"+count+"</td>");
			if(completeAppointment1.getPayBuy().equals("0")){
				str.append("<td>Self</td>");
			}else{
				str.append("<td>Third party</td>");
			}
			
			str.append("<td>"+completeAppointment1.getApmtType()+"</td>");
			str.append("<td>"+completeAppointment1.getCharges()+"</td>");
			count = count + 1;
			}
			str.append("</table>");
			for(CompleteAppointment completeAppointment2:clientChargeListDetail){
				total = completeAppointment2.getChargeTotal();
			}

			str.append("<tr>");
			str.append("<th colspan='3'>Total</th> ");
			
			str.append("<th colspan='3'>"+Constants.getCurrency(loginInfo)+total+"</th> ");
			str.append("</tr>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+""); 
			
			
			
			
				String textAjax = new String();
			 
				textAjax = ("<input class='form-control' type = 'hidden' id = 'hiddenTotal' name = 'hiddenTotal' value = '"+Constants.getCurrency(loginInfo)+total+" '>");
			
			 
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				
				response.getWriter().write(""+textAjax.toString()+"");
			
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			connection.close();
		}
		return null;
	}
	public String updateStatusInvoice() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		String invoiceId = request.getParameter("invoiceId");
		String invoicenotes = request.getParameter("invoicenotes");
		Connection connection = null;
		
		try{
			
			ArrayList<CompleteAppointment> clientChargeListDetail = new ArrayList<CompleteAppointment>();
			connection = Connection_provider.getconnection();
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			int result = completeAptmDAO.updateInvoiceStatus(invoiceId,invoicenotes);
		}
		catch(Exception e){
			
		}finally{
			
			connection.close();
		}
		
		
		
		return null;
	}
	public String paynow() throws SQLException{
		
		if(!verifyLogin(request)){
			return "login";
		}
			Connection connection = null;
			try{
				
				connection = Connection_provider.getconnection();
				CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
				ArrayList<CompleteAppointment>assesmentList = completeAptmDAO.getAssesmentList(completeAppointmentForm.getPayBuy(),loginInfo.getId());
				completeAppointmentForm.setAssesmentList(assesmentList);
				double charges = 0;
				
				for(CompleteAppointment completeAppointment: assesmentList){
					charges = charges + Double.parseDouble(completeAppointment.getCharges());
					completeAppointmentForm.setUser(completeAppointment.getUser());
				}
				
				completeAppointmentForm.setChargeTotal(Double.toString(charges));
				completeAppointmentForm.setNumberOfChages(assesmentList.size());
				CompleteAppointment completeAppointment = new CompleteAppointment();
				completeAppointmentForm.setPractitionerName(completeAppointmentForm.getPractitionerName());
				completeAppointment = completeAptmDAO.getInsuranceCompanyName(completeAppointmentForm.getClientId());
				completeAppointmentForm.setInsuranceCompany("Company : "+completeAppointment.getInsuranceCompanyName() + " Owner : " + completeAppointment.getInsuranceCompanyOwnerName() + "Address: " + completeAppointment.getInsuranceCompanyAddress());
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				connection.close();
			}
		return "paynow";
	}
	
	public String pendingApmtNowPay() throws Exception{
		return null;
	}
	
	public String invoice() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			
			CompleteAppointment completeAppointment = new CompleteAppointment();
			completeAppointment.setClientId(completeAppointmentForm.getClientId());
			completeAppointment.setPractitionerId(completeAppointmentForm.getPractitionerId());
			completeAppointment.setUser(completeAppointmentForm.getUser());
			if(completeAppointmentForm.getPayBuy().equals(Constants.PAY_BY_THIRD_PARTY)){
				
				completeAppointment = completeAptmDAO.getInsuranceCompanyName(completeAppointmentForm.getClientId());
				completeAppointmentForm.setInsuranceCompany("Company : "+completeAppointment.getInsuranceCompanyName() + " Owner : " + completeAppointment.getInsuranceCompanyOwnerName() + "Address: " + completeAppointment.getInsuranceCompanyAddress());
			}
			completeAppointment.setPractitionerName(completeAppointmentForm.getPractitionerName());
			completeAppointment.setInvoiceDate(completeAppointmentForm.getInvoiceDate());
			
			int invoice = completeAptmDAO.saveAmpmInvoice(completeAppointment,loginInfo.getId(),loginInfo.getUserId());
			
			ArrayList<CompleteAppointment>assesmentList = completeAptmDAO.getAssesmentList(completeAppointmentForm.getPayBuy(),completeAppointmentForm.getTotalassesment(),loginInfo.getId());
			
			for(CompleteAppointment appointment : assesmentList){
				int result = completeAptmDAO.saveInvoiceAssesment(appointment,invoice);
			}
			
			int result = completeAptmDAO.deleteComplteApmt(loginInfo.getId());
			
			completeAppointmentForm.setInvoiceid(invoice);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return "invoice";
	}
	
	
	public String pay() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection  = null;
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			String invoiceid = request.getParameter("invoiceid");
			String payAmount = request.getParameter("payAmount");
			String howpaid = request.getParameter("howpaid");
			String paid = request.getParameter("paid");
			String invoiceDate = request.getParameter("invoiceDate");
			
			int result = accountsDAO.updatePayment(invoiceid,payAmount,howpaid,invoiceDate,paid);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return null;
	}
	
	
	
	
	public String preview() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection  = null;
		try{
			
			connection  = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			ArrayList<Accounts>assesmentList = accountsDAO.getAssesmentList(completeAppointmentForm.getInvoiceid(), 0);
			
			int charges = 0;
			for(Accounts accounts : assesmentList){
				charges = charges + Integer.parseInt(accounts.getCharges().trim());
			}
			
			int payAmount = accountsDAO.getPayAmount(completeAppointmentForm.getInvoiceid());
			
			int debitAmounnt = charges - payAmount;
			
			completeAppointmentForm.setChargeTotal(Integer.toString(charges));
			completeAppointmentForm.setInvoiceAssesmentList(assesmentList);
			completeAppointmentForm.setPaidAmount(Integer.toString(payAmount));
			
			completeAppointmentForm.setDebitAmounnt(Integer.toString(debitAmounnt));
			
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return "preview";
	}
	
	
	
	//unnati 24/04/2014
	
	public String createCharge() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}Connection connection = null;
		try{
		//show practice manager
		
		
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			//start coding
			ArrayList<DiaryManagement>userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
			
			completeAppointmentForm.setUserList(userList);
			
		
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
	 return "createClientCharge";
	}
	
	public String getThirdParty() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		String clientId = request.getParameter("clientId");
		try{
			ThirdParty thirdParty = new ThirdParty();
			connection = Connection_provider.getconnection();
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			thirdParty = completeAptmDAO.getThirdParty(clientId,thirdParty);
			//completeAppointmentForm.setCharge(appointmentType.getCharges());
			 String textAjax = new String();
			 textAjax = ("<b>Invoicee : " +"<input type = 'text' class='form-control' id = 'invoicee' name = 'invoicee' value = '"+thirdParty.getCompanyName()+"'>");
			
			 
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+textAjax.toString()+""); 
				
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		finally{
			connection.close();
		}
		return null;
	}
	
	
	public CompleteAppointmentForm getModel() {
		
		return completeAppointmentForm;
	}

	public void prepare() throws Exception {
		
		completeAppointmentForm.setStartTimeList(PopulateList.startTimeList());
		completeAppointmentForm.setEndTimeList(PopulateList.endTimeList());
		completeAppointmentForm.setApmtDurationList(PopulateList.apmtDurationList());
		Connection connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			
			//ArrayList<Location>locationList = notAvailableSlotDAO.getLocationList(loginInfo.getClinicid());
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Accounts>locationList = accountsDAO.getLocationList(loginInfo.getClinicid());
			
			completeAppointmentForm.setLocationList(locationList);
			
			ArrayList<AppointmentType>appointmentTypeList = notAvailableSlotDAO.getAppointmentTypeList();
			completeAppointmentForm.setAppointmentTypeList(appointmentTypeList);
			
			//start coding
			ArrayList<DiaryManagement>userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
			completeAppointmentForm.setUserList(userList);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
	}


//unnati 25th march

public String getApmtCharge() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	Connection connection = null;
	String apmtType = request.getParameter("apmtTypeId");
	try{
		AppointmentType appointmentType = new AppointmentType();
		connection = Connection_provider.getconnection();
		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
		appointmentType = completeAptmDAO.getAptmTypeCharge(apmtType,appointmentType);
		//completeAppointmentForm.setCharge(appointmentType.getCharges());
		 String textAjax = new String();
		 textAjax = ("<input type = 'text' class='form-control' id = 'charge' name = 'charge' size = '5' value = '"+Constants.getCurrency(loginInfo)+appointmentType.getCharges()+"'  >");
		
		 
				response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+textAjax.toString()+""); 
			
		
		
	}
	catch (Exception e) {
		// TODO: handle exception
	}
	finally{
		connection.close();
	}
	return null;
}

public String getApChargeAndDuration() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	Connection connection = null;
	String apmtType = request.getParameter("apmtTypeId");
	try{
		AppointmentType appointmentType = new AppointmentType();
		connection = Connection_provider.getconnection();
		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
		appointmentType = completeAptmDAO.getAptmTypeCharge(apmtType,appointmentType);
		//completeAppointmentForm.setCharge(appointmentType.getCharges());
		 String textAjax = new String();
		 textAjax = ("<input type = 'text' class='form-control' id = 'charge' name = 'charge' value = '"+Constants.getCurrency(loginInfo)+appointmentType.getChargesx()+"'>");
		 String textAjax1 = new String();		 textAjax1 = (" Duration: " +"<input type = 'text' class='form-control' id = 'duration'  name = 'duration' value = '"+appointmentType.getDuration()+"'>");
		 
				response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+textAjax.toString()+""); 
			response.getWriter().write(""+textAjax1.toString()+""); 
		
		
		
	}
	catch (Exception e) {
		// TODO: handle exception
	}
	finally{
		connection.close();
	}
	return null;
}

public String deleteCashDesk() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	int result = 0;
	int id =Integer.parseInt(request.getParameter("selectedid"));
	Connection connection = null;
	Client client = new Client();
	CompleteAppointment completeAppointment = new CompleteAppointment();
	try{
		
		connection = Connection_provider.getconnection();
		
		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
		completeAppointment=completeAptmDAO.getChargeDetailsTemp(id);
		int res=completeAptmDAO.insertModify_invoice_deleted(completeAppointment,loginInfo);
		/*CompleteAppointment comp = completeAptmDAO.getInvntryCompProdDetails(id);
		
		Product product = completeAptmDAO.getInventoryProductDetails(Integer.toString(comp.getProdid()));
		int rmainQty = Integer.parseInt(product.getStock()) + comp.getQuantity();
		int update = completeAptmDAO.updateProdStock(rmainQty,completeAppointment.getApmtType());*/
		
		result = completeAptmDAO.deleteCash(id,completeAppointment,loginInfo.getId());
	}
	catch (Exception e) {
		// TODO: handle exception
	}
	finally{
		connection.close();
	}
	return null;
}



public String pendingCharges() {
	if(!verifyLogin(request)){
		return "login";
	}
	return "pendingCharges";
}

public String showPendingCharges() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	String date = completeAppointmentForm.getCommencing();
	String practitioner = completeAppointmentForm.getDiaryUser();
	String location = completeAppointmentForm.getLocation();
	
	
	
	if(practitioner==null){
		practitioner = (String) session.getAttribute("diaryUser");
	}
	if(date==null){
		date = (String) session.getAttribute("date");
	}
	if(location==null){
		location = (String) session.getAttribute("location");
	}
	if(practitioner==null){
		completeAppointmentForm.getDiaryUser1();
	}
	if(date==null){
		completeAppointmentForm.getCommencing1();
	}
	if(location==null){
		completeAppointmentForm.getLocation1();
	}
	String temp[] = null ;
	if(!date.equals("")){
	 temp= date.split("/");
	date = temp[2]+"-"+temp[1]+"-"+temp[0];
	}
	if(practitioner.equals("0")){
		practitioner = "";
	}
	if(location.equals("0")){
		location = "";
	}
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
		int totalCount = completeAptmDAO.getTotalPendingApmtChargesCount(practitioner,date,location);
		pagination.setPreperties(totalCount);
		ArrayList<CompleteAppointment> pendingChargesList = new ArrayList<CompleteAppointment>();
		pendingChargesList = completeAptmDAO.getPendingApmtCharges(practitioner,date,location,pagination);
		pagination.setPage_records(pendingChargesList.size());
		completeAppointmentForm.setTotalRecords(totalCount);
		completeAppointmentForm.setPagerecords(Integer.toString(pagination.getPage_records()));
		completeAppointmentForm.setPendingChargesList(pendingChargesList);
		completeAppointmentForm.setDiaryUser1(practitioner);
		//completeAppointmentForm.setLocation1(location);
		completeAppointmentForm.setCommencing1(date);
	
		
		
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		
		connection.close();
	}
	if(!date.equals("")){
	date = temp[0]+"/"+temp[1]+"/"+temp[2];
	}
	completeAppointmentForm.setCommencing(date);
	completeAppointmentForm.setDiaryUser(practitioner);
	completeAppointmentForm.setLocation(location);
	session.setAttribute("diaryUser", practitioner);
	session.setAttribute("date", date);
	session.setAttribute("location", location);
	return "pendingCharges";
}

public String redirectToPending() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	
		String practitioner = request.getParameter("practitionerId");
		String date = request.getParameter("apmtDate");
		String location = request.getParameter("location");
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			int totalCount = completeAptmDAO.getTotalPendingApmtChargesCount(practitioner,date,location);
			pagination.setPreperties(totalCount);
			ArrayList<CompleteAppointment> pendingChargesList = new ArrayList<CompleteAppointment>();
			pendingChargesList = completeAptmDAO.getPendingApmtCharges(practitioner,date,location,pagination);
			pagination.setPage_records(pendingChargesList.size());
			completeAppointmentForm.setTotalRecords(totalCount);
			completeAppointmentForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			completeAppointmentForm.setPendingChargesList(pendingChargesList);
			completeAppointmentForm.setDiaryUser1(practitioner);
			//completeAppointmentForm.setLocation1(location);
			completeAppointmentForm.setCommencing1(date);
		
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		String temp[] = null ;
		if(!date.equals("")){
			 temp= date.split("-");
			 date = temp[2]+"/"+temp[1]+"/"+temp[0];
			}
		
		completeAppointmentForm.setCommencing(date);
		completeAppointmentForm.setDiaryUser(practitioner);
		completeAppointmentForm.setLocation(location);
		session.setAttribute("diaryUser", practitioner);
		session.setAttribute("date", date);
		session.setAttribute("location", location);
		

	return "pendingCharges";
}


public String indiviqty() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	String id = request.getParameter("id");
	String qty = request.getParameter("qty");
	
	Connection connection = null;
	
	try{
		connection = Connection_provider.getconnection();
		CompleteAppointment completeAppointment = new CompleteAppointment();
		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
		
		int upd = completeAptmDAO.updateIndivQty(id,qty);
		
	}catch (Exception e) {
		// TODO: handle exception
	}finally{
		
		connection.close();
	}
	
	return null;
}


public String individisc() throws Exception{
	
	if(!verifyLogin(request)){
		return "login";
	}
	String id = request.getParameter("id");
	String gdiscamt = request.getParameter("gdiscamt");
	String maindisc = request.getParameter("gdiscamt");
	String unitcharge = request.getParameter("unitcharge");
	String disctype = request.getParameter("disctype");
	
	Connection connection = null;
	
	try{
		connection = Connection_provider.getconnection();
		CompleteAppointment completeAppointment = new CompleteAppointment();
		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
		int qty= completeAptmDAO.getqantityOfCharge(Integer.parseInt(id));
		if(disctype.equals("0")){
			Double discountedamt= (Double.parseDouble(unitcharge)*qty)/100*Double.parseDouble(gdiscamt);
			gdiscamt=String.valueOf(discountedamt);
			}
		double charge = (Double.parseDouble(unitcharge)*qty) - Double.parseDouble(gdiscamt) ;
		charge= charge/qty;
		
		int upd = completeAptmDAO.updateIndivisualDisc(gdiscamt,id,charge,disctype,maindisc);
		gdiscamt=maindisc;
		
	}catch (Exception e) {
		e.printStackTrace();
	}finally{
		
		connection.close();
	}
	
	return null;
}

public String updteassementlist() throws Exception{
	
	if(!verifyLogin(request)){
		return "login";
	}
	String id = request.getParameter("id");
	String gdiscamt = request.getParameter("gdiscamt");
	String maindisc = request.getParameter("gdiscamt");
	String disctype = request.getParameter("disctype");
//	System.out.println(id);
	double total = 0;
	Connection connection = null;
	
	try{
		CompleteAppointment completeAppointment = new CompleteAppointment();
		ArrayList<CompleteAppointment> clientChargeListDetail = new ArrayList<CompleteAppointment>();
		connection = Connection_provider.getconnection();
		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
		clientChargeListDetail = completeAptmDAO.getModifiInvoiceAsessmentDetails(id);
		
		int result = completeAptmDAO.deleteComplteApmt(loginInfo.getId());
			//total = completeAppointment2.getChargeTotal();
			
		for(CompleteAppointment c1 : clientChargeListDetail){
			
			if(!gdiscamt.equals("0")){
				if(disctype.equals("0")){
					c1.setDiscperc(maindisc);
				Double discountedamt= Double.parseDouble(c1.getUnitcharge())/100*Double.parseDouble(gdiscamt);
				gdiscamt=String.valueOf(discountedamt);
				}else{
					c1.setDisc_amount(maindisc);
				}
				c1.setChargedisc(gdiscamt);
				double unitcharge = Double.parseDouble(c1.getUnitcharge());
				double qty = c1.getQuantity();
				
				
				double charge = unitcharge;
				charge = charge - Double.parseDouble(gdiscamt);
				c1.setCharges(Double.toString(charge));
			}
			gdiscamt=maindisc;
			 result = completeAptmDAO.saveModifyInvoiceChargeNewUpdate(c1,id,loginInfo.getId(),disctype);
			
			/*boolean checkAppointTypeExist = completeAptmDAO.checkAppointTypeExist(c1.getApmtType(),c1.getAppointmentid());
			if(!checkAppointTypeExist){
					int result = completeAptmDAO.saveModifyInvoiceChargeNewUpdate(c1,id);
			}
 */
		}		
			
		
		
		//Akasg 28 feb 2018 set session list for entry in refund table
		ArrayList<CompleteAppointment> clientChargeListDetail1 = completeAptmDAO.getModifyInvoiceAsessmentDetailsTemp(id,loginInfo.getId());
		session.setAttribute("clientChargeListDetailforRefund", clientChargeListDetail1);

		
		
	}
	catch (Exception e) {
		// TODO: handle exception
	}finally{
		
		connection.close();
	}
	
	return null;
	
}


public String assesmentList() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	String id = request.getParameter("id");
	
//	System.out.println(id);
	double total = 0;
	Connection connection = null;
	
	try{
		CompleteAppointment completeAppointment = new CompleteAppointment();
		ArrayList<CompleteAppointment> clientChargeListDetail = new ArrayList<CompleteAppointment>();
		connection = Connection_provider.getconnection();
		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
		clientChargeListDetail = completeAptmDAO.getAsessmentDetails(id);
		completeAppointmentForm.setClientChargeListDetail(clientChargeListDetail);
		
			//total = completeAppointment2.getChargeTotal();
			
				
			
		StringBuffer str = new StringBuffer();
		
		

		//Practitioner Mail
	//	str.append("<table  id = 'cashDesk1' cellpadding='0' cellspacing='0' class='my-table' > ");
		
		

		/*str.append("<tr>");
		str.append("<th>Id</th> ");
		str.append("<th>PayBy</th> ");
		str.append("<th>Appointment Type</th> ");
		str.append("<th>Charge</th> ");
		str.append("<th>Quantity</th> ");
		str.append("<th>Delete</th> ");
		str.append("</tr>");*/
		int count = 1;
		for(CompleteAppointment completeAppointment1:clientChargeListDetail){
			
		str.append("<tr>");
		
		if(completeAppointment1.getPayBuy().equals("0")){
			str.append("<td>Self</td>");
		}else{
			str.append("<td>Third party</td>");
		}
		
		str.append("<td>"+completeAppointment1.getMasterchargetype()+"</td>");
		str.append("<td>"+completeAppointment1.getApmtType()+"</td>");
	
		
		/*if(count==0){
			str.append("<td><img src='common/images/delete.gif'></img></td>");

		}else{
			str.append("<td onclick = 'makeInActive("+completeAppointment1.getId()+")'><img src='common/images/delete.gif'></img></td>");

		}*/
		str.append("<td style='text-align:center'>"+completeAppointment1.getQuantity()+"</td>");
		str.append("<td style='text-align:right'>"+Constants.getCurrency(loginInfo)+completeAppointment1.getCharges()+"</td>");
		double amount = Double.parseDouble(completeAppointment1.getCharges()) * completeAppointment1.getQuantity();
		str.append("<td style='text-align:right'>"+DateTimeUtils.changeFormat(amount)+"</td>");
		str.append("<td onclick = 'makeInActive("+completeAppointment1.getId()+","+count+")'><img src='common/images/delete.gif'></img></td>");

		str.append("</tr>");
		count = count+1;
		}
		//str.append("</table>");
		for(CompleteAppointment completeAppointment2:clientChargeListDetail){
			total = completeAppointment2.getChargeTotal();
		}

		str.append("<tr>");
		str.append("<th  colspan='5'>Total</th> ");
		
		str.append("<th style='text-align:center' colspan='5'>"+Constants.getCurrency(loginInfo)+DateTimeUtils.changeFormat(total)+"</th> ");
		str.append("</tr>");
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+str.toString()+""); 
		
		
		
		
			String textAjax = new String();
		 
			textAjax = ("<input class='form-control' type = 'hidden' id = 'hiddenTotal1' name = 'hiddenTotal' value = '"+Constants.getCurrency(loginInfo)+total+" '>");
		
		 
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+textAjax.toString()+"");
		
		
		
	}
	catch (Exception e) {
		// TODO: handle exception
	}finally{
		
		connection.close();
	}
	
	return null;
}


public String upteinvoicecharge() throws SQLException{
	
	if(!verifyLogin(request)){
		return "login";
	}
	
	String invoiceid = request.getParameter("invoiceid");
	
	Connection connection = null;
	
	try{
		
		connection = Connection_provider.getconnection();
		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		StatementDAO statementDAO = new JDBCStatementDAO(connection);
		ChargesAccountProcessingDAO chargesAccountProcessingDAO=new JDBCChargeAccountProcesDAO(connection);
		//ArrayList<CompleteAppointment> clientChargeListDetail1 = completeAptmDAO.getModifyInvoiceAsessmentDetailsTemp(id,loginInfo.getId());
		ArrayList<CompleteAppointment> refundlist =(ArrayList<CompleteAppointment>) session.getAttribute("clientChargeListDetailforRefund");
		
		ArrayList<CompleteAppointment>assesmentList = completeAptmDAO.getUpdateSaveInvoiceCompleteApmtList(invoiceid,loginInfo.getId());
		// 
		String newinvoiceid = invoiceid;
		String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		int chargeinvoiceid = completeAptmDAO.getChargeInvoiceid(invoiceid);
		
		double creditAmount = statementDAO.getCreditAmount(chargeinvoiceid);
		int paymentdone =0;
		if(creditAmount>0){
			paymentdone =1;
		}
		String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		
		//  14 Nov 2018 get discper and amount
		CompleteAppointment completeAppointment1 = completeAptmDAO.getInvoiceDiscountByChargeId(""+chargeinvoiceid);
		double discper=0;
		if(completeAppointment1.getDisc_type().equals("1")){
			discper = (Double.parseDouble(completeAppointment1.getDisc_amount())/completeAppointment1.getTotal())*100;
		}else{
			discper = completeAppointment1.getDiscount();
		}
		
		if(assesmentList.size()>0){
			String wardid = completeAptmDAO.getWardIDFromInvoice(invoiceid);
			int delete = completeAptmDAO.deleteAssesmentData(invoiceid);
			
			for(CompleteAppointment appointment : assesmentList){
				appointment.setIsfrommodify(true);
				appointment.setWardid(wardid);
				int compulsary_consultant=chargesAccountProcessingDAO.isCompulasryConsultant(appointment.getMasterchargetype());
				if(compulsary_consultant==1){
					appointment.setApmtType(appointment.getApmtType().split("-")[0]);
				}
				int result = completeAptmDAO.saveInvoiceAssesment(appointment,Integer.parseInt(invoiceid));
				
				
				/*if(appointment.getUnitcharge().equals("0")){
					int upd = completeAptmDAO.updateUnitCharge(result,appointment.getUnitcharge());
				}*/
				
				//update product quantity
				boolean checkInventoryproduct = ipdDAO.checkInventoryChargeType(appointment.getMasterchargetype());
				if(!checkInventoryproduct){
					/*Product product = completeAptmDAO.getInventoryProductDetails(Integer.toString(appointment.getProdid()));
					if(appointment.getProdid()>0){
						int rmainQty = Integer.parseInt(product.getStock()) - appointment.getQuantity();
						int update = completeAptmDAO.updateProdStock(rmainQty,Integer.toString(appointment.getProdid()));
					}*/
				}
			}
			
			
			
			
			/*if(loginInfo.getIskunal()==1){
				ArrayList<CompleteAppointment>assesmentListgrouped = completeAptmDAO.getUpdateSaveInvoiceCompleteApmtListGrouped(invoiceid,loginInfo.getId());
				for(CompleteAppointment appointment :assesmentListgrouped){
					if(appointment.getInvoiced()>0){
						completeAptmDAO.kunalChargesUpdateProccess(""+appointment.getInvoiced(), appointment.getDiscperc(), appointment.getDisc_amount(),appointment.getApmtType());
					}
				}
			}*/
			int chargeInvoiceid = completeAptmDAO.getChargeInvoiceid(invoiceid);
			
			double debitAmount = completeAptmDAO.getModifyInvoiceTotalDebitAmmount(chargeInvoiceid);
			
			int update = completeAptmDAO.updateModifyInvoiceDebitAmmount(debitAmount,chargeInvoiceid);
			
			
			
			//for less qty
			for (CompleteAppointment completeAppointment : assesmentList) {
				
				for(CompleteAppointment appointment1 : refundlist){
						if(completeAppointment.getId()==appointment1.getId()){
							if(completeAppointment.getQuantity()<appointment1.getQuantity()){
								double qty = appointment1.getQuantity() - completeAppointment.getQuantity();
								/*double amount = Double.parseDouble(appointment1.getCharges()) * qty;
								double preamount = Double.parseDouble(appointment1.getCharges()) * appointment1.getQuantity();
								double totalamount = preamount - amount;*/
								
								if(!completeAppointment.getChargedisc().equals("0")){
									double charge = Double.parseDouble(appointment1.getCharges());
									charge = charge - Double.parseDouble(appointment1.getChargedisc());
									completeAppointment.setCharges(Double.toString(charge));
								}
								appointment1.setCharges(appointment1.getCharges());
								appointment1.setQuantity(qty);
								appointment1.setDate(date);
								if(appointment1.getCharges()!=null){
									if(appointment1.getCharges().equals("")){
										appointment1.setCharges("0");
									}
								}else{
									appointment1.setCharges("0");
								}
								//  14 Nov 2018 save discount on perticular charge for refund perfect value
								double discamount =  (discper/100)*(Double.parseDouble(appointment1.getCharges())*qty);
								discamount = Math.round(discamount);
								discper = Math.round(discper);
								appointment1.setDisc_amount(DateTimeUtils.changeFormat(discamount));
								appointment1.setDiscperc(DateTimeUtils.changeFormat(discper));
								//edit less qty than previous qty remove entry
								int result = completeAptmDAO.saveInvoiceDeletedCharge(appointment1,newinvoiceid,loginInfo.getId(),0,chargeinvoiceid,paymentdone,datetime,loginInfo.getUserId());
								if(completeAppointment1.getDisc_type().equals("1")){
									CompleteAppointment completeAppointment2 = completeAptmDAO.getInvoiceDiscountByChargeId(""+chargeinvoiceid);
									double newdiscamt = Double.parseDouble(completeAppointment2.getDisc_amount()) - discamount;
									newdiscamt = Math.round(newdiscamt);
									if(newdiscamt<0){
										newdiscamt=0;
									}
									int res = completeAptmDAO.updateInvoiceDiscount(newdiscamt,chargeinvoiceid);
								}
							}
							break;
						}
				}
			}
			// for delete charge
			for(CompleteAppointment appointment1 : refundlist){
				boolean flag = false;
				for (CompleteAppointment completeAppointment : assesmentList) {
					if(completeAppointment.getId()==appointment1.getId()){
						flag = false;
						break;
					}
					flag = true;
				}
				if(flag){
					//delete entry here
					//  14 Nov 2018 save discount on perticular charge for refund perfect value
					double qty = appointment1.getQuantity();
					double discamount =  (discper/100)*(Double.parseDouble(appointment1.getCharges())*qty);
					discamount = Math.round(discamount);
					discper = Math.round(discper);
					appointment1.setDisc_amount(DateTimeUtils.changeFormat(discamount));
					appointment1.setDiscperc(DateTimeUtils.changeFormat(discper));
					
					appointment1.setDate(date);
					int result = completeAptmDAO.saveInvoiceDeletedCharge(appointment1,newinvoiceid,loginInfo.getId(),1,chargeinvoiceid,paymentdone,datetime,loginInfo.getUserId());
					if(completeAppointment1.getDisc_type().equals("1")){
						CompleteAppointment completeAppointment2 = completeAptmDAO.getInvoiceDiscountByChargeId(""+chargeinvoiceid);
						double newdiscamt = Double.parseDouble(completeAppointment2.getDisc_amount()) - discamount;
						newdiscamt = Math.round(newdiscamt);
						if(newdiscamt<0){
							newdiscamt=0;
						}
						int res = completeAptmDAO.updateInvoiceDiscount(newdiscamt,chargeinvoiceid);
					}
				}
			}
			
		}else{
			//29 March 2018 last charge not delete for that means if there is only one charge in invoice
			int delete = completeAptmDAO.deleteAssesmentData(invoiceid);
			//all charges delete code here
			for(CompleteAppointment appointment1 : refundlist){
				//  14 Nov 2018 save discount on perticular charge for refund perfect value
				double qty = appointment1.getQuantity();
				double discamount =  (discper/100)*(Double.parseDouble(appointment1.getCharges())*qty);
				discamount = Math.round(discamount);
				discper = Math.round(discper);
				appointment1.setDisc_amount(DateTimeUtils.changeFormat(discamount));
				appointment1.setDiscperc(DateTimeUtils.changeFormat(discper));
				appointment1.setDate(date);
				int result = completeAptmDAO.saveInvoiceDeletedCharge(appointment1,newinvoiceid,loginInfo.getId(),1,chargeinvoiceid,paymentdone,datetime,loginInfo.getUserId());
				if(completeAppointment1.getDisc_type().equals("1")){
					CompleteAppointment completeAppointment2 = completeAptmDAO.getInvoiceDiscountByChargeId(""+chargeinvoiceid);
					double newdiscamt = Double.parseDouble(completeAppointment2.getDisc_amount()) - discamount;
					newdiscamt = Math.round(newdiscamt);
					if(newdiscamt<0){
						newdiscamt=0;
					}
					int res = completeAptmDAO.updateInvoiceDiscount(newdiscamt,chargeinvoiceid);
				}
			}
		}
		//  20/11/2018 Save modify user details
		int res=completeAptmDAO.saveModifyInvoiceLog(loginInfo.getUserId(),datetime,String.valueOf(chargeinvoiceid),0,invoiceid);
		
		
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		connection.close();
	}
	
	
	return null;
}


public String updtecashdesk() throws Exception{
	
	if(!verifyLogin(request)){
		return "login";
	}
	String id = request.getParameter("id");
	
//	System.out.println(id);
	double total = 0;
	Connection connection = null;
	
	try{
		//CompleteAppointment completeAppointment = new CompleteAppointment();
		ArrayList<CompleteAppointment> clientChargeListDetail = new ArrayList<CompleteAppointment>();
		connection = Connection_provider.getconnection();
		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
		clientChargeListDetail = completeAptmDAO.getModifyInvoiceAsessmentDetailsTemp(id,loginInfo.getId());
		completeAppointmentForm.setClientChargeListDetail(clientChargeListDetail);
		
		
			//total = completeAppointment2.getChargeTotal();
		
		CompleteAppointment completeAppointment = completeAptmDAO.getModifyInvoiceDetails(id);
		
	
				
			
		StringBuffer str = new StringBuffer();
		

		//Practitioner Mail
		
		if(completeAppointment.getPayBuy().equals("0")){
			str.append("<input type='hidden' name='hdnpayby' id='hdnpayby' value='Self'/>");
			str.append("<input type='hidden' name='hdnchargeto' id='hdnchargeto' value='"+completeAppointment.getClient()+"'/>");
		}else{
			str.append("<input type='hidden' name='hdnpayby' id='hdnpayby' value='Third Party'/>");
			
			CompleteAppointment cmp =  completeAptmDAO.getInsuranceCompanyName(completeAppointment.getClientId());
			String tpName = cmp.getInsuranceCompanyName();
			str.append("<input type='hidden' name='hdnchargeto' id='hdnchargeto' value='"+tpName+"'/>");
		}
		
		/*str.append("<table  id = 'cashDesk1' cellpadding='0' cellspacing='0' class='my-table' > ");
		
		

		str.append("<tr>");
		str.append("<th>Id</th> ");
		str.append("<th>PayBy</th> ");
		str.append("<th>Appointment Type</th> ");
		str.append("<th>Charge</th> ");
		str.append("<th>Quantity</th> ");
		str.append("<th>Delete</th> ");
		str.append("</tr>");*/
		AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
		int count = 0;
		for(CompleteAppointment completeAppointment1:clientChargeListDetail){
			count = count+1;
		str.append("<tr>");
		str.append("<input type='hidden' class='ajclassnew' value='" + count + "' class='form-control' />");
		
		str.append("<input type='hidden' id='perunitprice" + count + "' value='" + completeAppointment1.getCharges() + "' class='form-control' />");
		str.append("<input type='hidden' id='perunitqty" + count + "'  value='" + completeAppointment1.getQuantity() + "' class='form-control' />");
		
		str.append("<td style='width:8%'>"+DateTimeUtils.getCommencingDate1(completeAppointment1.getCommencing())+"</td>");
		//str.append("<td>"+completeAppointment1.getId()+"</td>");
		if(completeAppointment1.getPayBuy().equals("0")){
			str.append("<td>Self</td>");
		}else{
			str.append("<td>Third party</td>");
		}
		str.append("<td>"+completeAppointment1.getMasterchargetype()+"</td>");
		str.append("<td>"+completeAppointment1.getApmtType()+"</td>");
		String tpcode=accountsDAO.getTpChargeCode(Integer.parseInt(completeAppointment1.getChargeId()), completeAppointment1.getStd_charge_id());
		str.append("<td>"+tpcode+"</td>");
		
		//str.append("<td style='text-align:center' id='qty"+count+"' onclick='changeQty("+count+","+completeAppointment1.getQuantity()+")'>"+completeAppointment1.getQuantity()+"</td>");
		str.append("<td style='text-align: center' ><input style='width:45%;' class='form-control' onchange='updateIndivQuantity(this.value,"+completeAppointment1.getId()+","+count+")' type='number' id='qty"+count+"' name='qty"+count+"' value='"+completeAppointment1.getQuantity()+"'> </td>");
		
		String unitcharge = "0";
		if(completeAppointment1.getChargedisc().equals("0")){
			unitcharge = completeAppointment1.getCharges();
			
		}else{
			unitcharge = completeAppointment1.getUnitcharge();
		}
		completeAppointment1.setCharges(unitcharge);
		
		double amount = Double.parseDouble(completeAppointment1.getCharges()) * completeAppointment1.getQuantity();
		amount = amount - Double.parseDouble(completeAppointment1.getChargedisc());
		
		double tax=DateTimeUtils.convertToDouble(completeAppointment1.getTotaltax());
		
		
		if(loginInfo.isPcsAdmin()){
			double showunitCharge=(DateTimeUtils.convertToDouble(completeAppointment1.getCharges()))/(1.0+(tax/100.0)); 
			str.append("<td style='text-align:right;width:10%' >"+Constants.getCurrency(loginInfo)+"<label id='mrp"+count+"' class='hidden'>"+DateTimeUtils.changeFormat(Double.parseDouble(completeAppointment1.getCharges()))+"</label><label>"+DateTimeUtils.changeFormat(showunitCharge)+"</label></td>");
		}else{
			str.append("<td style='text-align:right;width:10%' >"+Constants.getCurrency(loginInfo)+"<label id='mrp"+count+"'>"+DateTimeUtils.changeFormat(Double.parseDouble(completeAppointment1.getCharges()))+"</label></td>");
		}
		
		
		str.append("<td style='text-align:right;width:8%' ><label id='discshow"+count+"'>"+completeAppointment1.getDiscperc()+"</label></td>");
		str.append("<td style='text-align:center'><input style='width:41%;' readonly='readonly' class='form-control' type='number' onchange='setindivisdualdisc(this.value,"+completeAppointment1.getId()+","+unitcharge+")' name='chargedisc' id='chargedisc' value='"+completeAppointment1.getChargedisc()+"'>");
		
		/*amount=((amount+taxamt)*100)/100;*/
		str.append("<td style='text-align:right' id='tottemp"+count+"'>"+DateTimeUtils.changeFormat(amount)+"</td>");
		str.append("<input type='hidden' id='perunittotalprice" + count + "'  value='" + amount + "' class='form-control' />");
		
		/*if(count==0){
			str.append("<td><img src='common/images/delete.gif'></img></td>");

		}else{
			str.append("<td onclick = 'makeInActive("+completeAppointment1.getId()+")'><img src='common/images/delete.gif'></img></td>");

		}*/
		str.append("<td onclick = 'confirmedDelete1("+completeAppointment1.getId()+","+amount+","+count+")'><img src='common/images/delete.gif'></img></td>");

		str.append("</tr>");
		str.append("<input type='hidden' id='unitmt"+count+"' value='"+DateTimeUtils.changeFormat(Double.parseDouble(completeAppointment1.getCharges()))+"' />");
		str.append("<input type='hidden' id='chrgeid"+count+"' value='"+completeAppointment1.getId()+"' />");
		
		}
		
		//str.append("</table>");
		for(CompleteAppointment completeAppointment2:clientChargeListDetail){
			total = completeAppointment2.getChargeTotal();
		}

		str.append("<input type='hidden' id='counttemp' value='"+count+"' />");
		str.append("<tr>");
		str.append("<th></th> ");
		str.append("<th></th> ");
		str.append("<th></th> ");
		str.append("<th></th> ");
		str.append("<th></th> ");
		str.append("<th></th> ");
		str.append("<th></th> ");
		str.append("<th style='text-align:right'>Total</th> ");
		str.append("<th style='text-align:right' colspan='5' >"+Constants.getCurrency(loginInfo)+"<label id='total'>"+total+"</label></th> ");
		str.append("</tr>");
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+str.toString()+""); 
		
		
		
		
			/*String textAjax = new String();
		 
			textAjax = ("<input class='form-control' type = 'hidden' id = 'hiddenTotal1' name = 'hiddenTotal' value = '"+Constants.getCurrency(loginInfo)+total+" '>");
		
		 
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+textAjax.toString()+"");*/
		
		
		
	}
	catch (Exception e) {
		// TODO: handle exception
	}finally{
		
		connection.close();
	}
	
	return null;
	
	
}

public String assesmentListTemp() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	String id = request.getParameter("id");
	
//	System.out.println(id);
	double total = 0;
	Connection connection = null;
	
	try{
		CompleteAppointment completeAppointment = new CompleteAppointment();
		ArrayList<CompleteAppointment> clientChargeListDetail = new ArrayList<CompleteAppointment>();
		connection = Connection_provider.getconnection();
		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
		clientChargeListDetail = completeAptmDAO.getAsessmentDetailsTemp(id,loginInfo.getId());
		completeAppointmentForm.setClientChargeListDetail(clientChargeListDetail);
		
			//total = completeAppointment2.getChargeTotal();
			
				
			
		StringBuffer str = new StringBuffer();
		

		//Practitioner Mail
		/*str.append("<table  id = 'cashDesk1' cellpadding='0' cellspacing='0' class='my-table' > ");
		
		

		str.append("<tr>");
		str.append("<th>Id</th> ");
		str.append("<th>PayBy</th> ");
		str.append("<th>Appointment Type</th> ");
		str.append("<th>Charge</th> ");
		str.append("<th>Delete</th> ");
		str.append("</tr>");*/
		int count = 1;
		for(CompleteAppointment completeAppointment1:clientChargeListDetail){
			
		str.append("<tr>");
		//str.append("<td>"+completeAppointment1.getId()+"</td>");
		if(completeAppointment1.getPayBuy().equals("0")){
			str.append("<td>Self</td>");
		}else{
			str.append("<td>Third party</td>");
			
		}
		
		
		str.append("<td>"+completeAppointment1.getMasterchargetype()+"</td>");
		str.append("<td>"+completeAppointment1.getApmtType()+"</td>");
		str.append("<td style='text-align:center'>"+completeAppointment1.getQuantity()+"</td>");
		str.append("<td style='text-align:right'>"+Constants.getCurrency(loginInfo)+completeAppointment1.getCharges()+"</td>");
		double amount = Double.parseDouble(completeAppointment1.getCharges()) * completeAppointment1.getQuantity();
		str.append("<td style='text-align:right'>"+DateTimeUtils.changeFormat(amount)+"</td>");
		/*if(count==0){
			str.append("<td><img src='common/images/delete.gif'></img></td>");

		}else{
			str.append("<td onclick = 'makeInActive("+completeAppointment1.getId()+")'><img src='common/images/delete.gif'></img></td>");

		}*/
		str.append("<td onclick = 'confirmedDelete12("+completeAppointment1.getId()+")'><img src='common/images/delete.gif'></img></td>");

		str.append("</tr>");
		count = count+1;
		}
		str.append("</table>");
		for(CompleteAppointment completeAppointment2:clientChargeListDetail){
			total = completeAppointment2.getChargeTotal();
		}

		str.append("<tr>");
		str.append("<th colspan='5'>Total</th> ");
		
		str.append("<th style='text-align:center' colspan='5'>"+Constants.getCurrency(loginInfo)+total+"</th> ");
		str.append("</tr>");
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+str.toString()+""); 
		
		
		
		
			String textAjax = new String();
		 
			textAjax = ("<input class='form-control' type = 'hidden' id = 'hiddenTotal1' name = 'hiddenTotal' value = '"+Constants.getCurrency(loginInfo)+total+" '> <input class='form-control' type = 'hidden' id = 'hiddenTotal1' name = 'hiddenTotal1' value = '"+total+" '>");
		
		 
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+textAjax.toString()+"");
		
		
		
	}
	catch (Exception e) {
		// TODO: handle exception
	}finally{
		
		connection.close();
	}
	
	return null;
	
}
public String makeInActive() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	int result = 0;
	int id =Integer.parseInt(request.getParameter("id"));
	Connection connection = null;
	try{
		
		connection = Connection_provider.getconnection();
		
		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
		
		//result = completeAptmDAO.inActiveAsessment(id);
		result = completeAptmDAO.saveIdOfAssessment(id);

	}
	catch (Exception e) {
		// TODO: handle exception
	}finally{
		
		connection.close();
	}
	
	return null;
}

public String addNewCharge1() throws SQLException{

	
	
	
	if(!verifyLogin(request)){
		return "login";
	}
	
	String chargetype =  request.getParameter("chargetype");
	String quantity =  request.getParameter("quantity");
	
	String user = request.getParameter("cname");
	String apmtType = request.getParameter("apmttype");
	String apmttypeid = request.getParameter("apmttypeid");
	
	String charge = request.getParameter("charge");
	String startTime = request.getParameter("starttime");
	//String duration = request.getParameter("cookieDuration");
	String practitionerId = request.getParameter("pid");
	String clientId = request.getParameter("cid");
	String practitionerName = request.getParameter("pname");
	String commencing = request.getParameter("date");
	String payBuy = request.getParameter("payby");
	String mannualcharge = request.getParameter("mannualcharge");
	//String markAppointment = request.getParameter("markAppointment");
	String apppointmentid = request.getParameter("id");
	/*String temp[] = charge.split(Constants.getCurrency(loginInfo));
	charge = temp[1];*/
	
	int result = 0;
	Connection connection = null;
	
	try{
		CompleteAppointment completeAppointment = new CompleteAppointment();
		connection = Connection_provider.getconnection();
		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		
		
		String masterchargetype = request.getParameter("masterchargetype");
		String inventprodid = request.getParameter("inventprodid");
		
		boolean checkinventoryproduct = ipdDAO.checkInventoryChargeType(masterchargetype);
		if(!checkinventoryproduct){
			Product product = completeAptmDAO.getInventoryProductDetails(inventprodid);
			apmtType = product.getProduct_name();
			completeAppointment.setProdid(Integer.parseInt(inventprodid));
			
		}
		
		completeAppointment.setUser(user);
		
		
		//String appointmentTypeName = completeAptmDAO.getAppointmentTypeName(apmtType);
		completeAppointment.setQuantity(Integer.parseInt(quantity));
		completeAppointment.setMasterchargetype(chargetype);
		completeAppointment.setApmtType(apmtType);
		completeAppointment.setCharges(charge);
		completeAppointment.setStartTime(startTime);
		//completeAppointment.setDuration(duration);
		completeAppointment.setPractitionerId(practitionerId);
		completeAppointment.setPractitionerName(practitionerName);
		completeAppointment.setClientId(clientId);
		completeAppointment.setCommencing(commencing);
		completeAppointment.setPayBuy(payBuy);
		completeAppointment.setMarkAppointment("1");
		completeAppointment.setAppointmentid(apppointmentid);
		ArrayList<CompleteAppointment> oldAssessmentList = new ArrayList<CompleteAppointment>();
		oldAssessmentList = completeAptmDAO.getOldAssessmnetList(apppointmentid);
		for(CompleteAppointment c1 : oldAssessmentList){
			CompleteAppointment c2 = new CompleteAppointment();

			c2.setUser(c1.getClient());
			c2.setApmtType(c1.getApmtType());
			c2.setCharges(c1.getCharges());
			c2.setStartTime(c1.getStartTime());
			c2.setDuration(c1.getDuration());
			c2.setPractitionerId(c1.getPractitionerId());
			c2.setPractitionerName(c1.getPractitionerName());
			c2.setClientId(c1.getClientId());
			c2.setCommencing(c1.getCommencing());
			c2.setPayBuy(c1.getPayBuy());
			c2.setMarkAppointment(c1.getMarkAppointment());
			c2.setAppointmentid(apppointmentid);
			c2.setQuantity(c1.getQuantity());
			c2.setMasterchargetype(c1.getMasterchargetype());
			
			
			boolean checkAppointTypeExist = completeAptmDAO.checkAppointTypeExist(c1.getApmtType(),apppointmentid,loginInfo.getId());
			if(!checkAppointTypeExist){

				result = completeAptmDAO.saveChargeNewUpdate(c2,loginInfo.getId());
			}
 
		}
		
		//add mannual charges
		completeAppointment.setManualcharge(mannualcharge);
		completeAppointment.setApmttypeid(apmttypeid);
		
		if(completeAppointment.getApmttypeid().equals("0")){
			completeAppointment.setApmtType(completeAppointment.getManualcharge());
			apmtType = completeAppointment.getManualcharge();
			
		}
		
		boolean checkAppointTypeExist = completeAptmDAO.checkAppointTypeExist(apmtType,apppointmentid,loginInfo.getId());
		if(!checkAppointTypeExist){
			
			result = completeAptmDAO.saveChargeNewUpdate(completeAppointment,loginInfo.getId());
			
			
		}
		
		
	
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally{
		connection.close();
	}
	return null;
	
}



public String opdcomplete() throws Exception{
	
	Connection connection=null;
	try {
		
		connection=Connection_provider.getconnection();
		CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection);
		NotAvailableSlotDAO notAvailableSlotDAO= new JDBCNotAvailableSlotDAO(connection);
		String appointmentid=request.getParameter("appointmentid");
		
		int res = completeAptmDAO.updateCompStatus(appointmentid);
		int res1=notAvailableSlotDAO.saveDateOfOPDEvents(appointmentid,"complete_datetime");
	} catch (Exception e) {

		e.printStackTrace();
	}finally{
		
		connection.close();
	}
	
	return null;
}








public String redirectToNot() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	String id = request.getParameter("id");
	Connection connection = null;
	try{
		
		connection = Connection_provider.getconnection();
		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
		
		String clientid = request.getParameter("clientid");
		String practitionerid = request.getParameter("practitionerid");
		String clientName = request.getParameter("clientName");
		String practitionerName = request.getParameter("practitionerName");
		String appointmentid = request.getParameter("appointmentid");
		
		ArrayList<String> invoiceIdList = new ArrayList<String>();
		invoiceIdList = completeAptmDAO.getInvoiceIdList(appointmentid);
		
		
		int deleteInvoice = completeAptmDAO.deleteInvoiceEntry(appointmentid);
		int deleteAssessment = completeAptmDAO.deleteInvoiceAssessment(appointmentid);
		
		
		//String action = request.getParameter("action");
		
		String date = DateTimeUtils.getDateinSimpleFormate(new Date());
		String stemp[] = date.split(" ");
		
		String temp[] = stemp[0].split("-");
		date = temp[2] + "-" + temp[1] + "-" + temp[0];
		
		String action = "Charge";
		CompleteAppointment completeAppointment = new CompleteAppointment();
		completeAppointment.setClientId(clientid);
		completeAppointment.setPractitionerId(practitionerid);
		completeAppointment.setUser(clientName);
		completeAppointment.setInvoiceDate(date);
		completeAppointment.setChargeType(action);
		completeAppointment.setAppointmentid(appointmentid);
		completeAppointment.setPractitionerName(practitionerName);
		
		
		
		AccountLogDAO accountLogDAO = new JDBCAccountLogDAO(connection);
		String status = "Reversed";
		//int id1 = Integer.parseInt(id);
		for(String invoiceId : invoiceIdList){
			int id1 = Integer.parseInt(invoiceId);
		int reverse = accountLogDAO.saveAmpmInvoice(completeAppointment,id1,status,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
		}
		
	}
	catch(Exception e){
		e.printStackTrace();
	}finally{
		
		connection.close();
	}
	return null;
}


public String updateChargeAccount() throws Exception{
	if(!verifyLogin(request)){
		return "login";
	}
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
		
		

		//logdata
		AccountLogDAO accountLogDAO = new JDBCAccountLogDAO(connection);
		
	
		
		String clientid = request.getParameter("clientid");
		String practitionerid = request.getParameter("practitionerid");
		String clientName = request.getParameter("clientName");
		String practitionerName = request.getParameter("practitionerName");
		String appointmentid = request.getParameter("appointmentid");
		String action = request.getParameter("action");
		String location = request.getParameter("location");
		
		String date = DateTimeUtils.getDateinSimpleFormate(new Date());
		String stemp[] = date.split(" ");
		
		String temp[] = stemp[0].split("-");
		date = temp[2] + "-" + temp[1] + "-" + temp[0];
		
		
		CompleteAppointment completeAppointment = new CompleteAppointment();
		completeAppointment.setClientId(clientid);
		completeAppointment.setPractitionerId(practitionerid);
		completeAppointment.setUser(clientName);
		completeAppointment.setInvoiceDate(date);
		completeAppointment.setChargeType(action);
		completeAppointment.setAppointmentid(appointmentid);
		completeAppointment.setPractitionerName(practitionerName);
		completeAppointment.setCommencing(date);
		completeAppointment.setLocation(location);
		
		
		
		ArrayList<Accounts>apmInvoiceList = completeAptmDAO.getApmInvoiceList(appointmentid);
		
		int updatelocation = completeAptmDAO.updateInvoiceLocation(appointmentid,location);
		
		int selfInvoiceid = 0;
		int tpInvoiceid = 0;
		int invoiceId = 0;
		ArrayList<Accounts> payByList = completeAptmDAO.getPayByList(appointmentid,loginInfo.getId());

		for(Accounts accounts : apmInvoiceList){
			
			if(payByList.size() == apmInvoiceList.size()){
				if(accounts.getPayBy()==0){
					selfInvoiceid = accounts.getId();
					invoiceId = selfInvoiceid;
				}else{
					tpInvoiceid = accounts.getId();
					invoiceId = tpInvoiceid;
				}
			}
			
		
			/*else{
				
				if(accounts.getPayBy()==0){
					selfInvoiceid = accounts.getId();
						tpInvoiceid = completeAptmDAO.createInvoice(completeAppointment.getClientId(),completeAppointment.getPractitionerId(),completeAppointment.getClient(),completeAppointment.getPractitionerName(),completeAppointment.getCommencing(),appointmentid,location);
					
						invoiceId = tpInvoiceid;
					
					
				}else{
					tpInvoiceid = accounts.getId();
						selfInvoiceid = completeAptmDAO.createInvoice(completeAppointment.getClientId(),completeAppointment.getPractitionerId(),completeAppointment.getClient(),completeAppointment.getPractitionerName(),completeAppointment.getCommencing(),appointmentid,location);
					
						invoiceId = selfInvoiceid;
					
				}
			}*/
			
			
		}
		
		
		
		ArrayList<CompleteAppointment>assesmentList = completeAptmDAO.getCompleteApmtList(appointmentid,loginInfo.getId());
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		for(CompleteAppointment c1 : assesmentList){
			boolean checkAppointTypeExist = completeAptmDAO.checkAppointTypeExistInAssessment(c1.getApmtType(),appointmentid);
			if(!checkAppointTypeExist){
				
				if(c1.getPayBuy().equals("1")){

					int save  = completeAptmDAO.saveInvoiceAssesment(c1, tpInvoiceid);
				}
				else{
					int save  = completeAptmDAO.saveInvoiceAssesment(c1, selfInvoiceid);

				}			
			}
			
			boolean checkInventoryproduct = ipdDAO.checkInventoryChargeType(c1.getMasterchargetype());
			if(!checkInventoryproduct){
				Product product = completeAptmDAO.getInventoryProductDetails(Integer.toString(c1.getProdid()));
				double rmainQty = Integer.parseInt(product.getStock()) - c1.getQuantity();
				int update = completeAptmDAO.updateProdStock(rmainQty,Integer.toString(c1.getProdid()));
			}
		
			
		}
		ArrayList<CompleteAppointment> tempDeleteList = completeAptmDAO.getTempDeleteAssessmnetList();
		for(CompleteAppointment ctemp : tempDeleteList){
			int result = completeAptmDAO.inActiveAsessment(ctemp.getId());

		}
		String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
		String datetemp[] = currentDate.split(" ");
		String temp1[] = datetemp[0].split("-");
		String date1 = temp1[0] + "/" + temp1[1] + "/" + temp1[2];
		String time = (datetemp[2]+" "+datetemp[3]);
		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		NotAvailableSlot n = notAvailableSlotDAO.getApmtDetailsForLog(Integer.parseInt(appointmentid));
		String commencingTemp = n.getCommencing();
		String apmtstatus = "Completed Modified";
		int logsave = notAvailableSlotDAO.saveApmtInLog(Integer.parseInt(appointmentid),date1,time,loginInfo.getUserId(),n.getClientId(),commencingTemp,n.getSTime(),apmtstatus,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
		int result = completeAptmDAO.deleteComplteApmt(loginInfo.getId());
		int del =  completeAptmDAO.deleteTempAssessmnet();
		
		//logdata
		 
		String status = "Modified";
		int resultlog = accountLogDAO.saveAmpmInvoice(completeAppointment,invoiceId,status,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
	
	

	}catch(Exception e){
		e.printStackTrace();
	}finally{
		
		connection.close();
	}
	
	return null;
}


 
public String updateqty() throws Exception  {

	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection);
		String id=request.getParameter("id");
		String qty=request.getParameter("qty");
		
		int result=completeAptmDAO.updateQtyInvoice(id,qty);
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+qty+""); 
		
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	finally {
		
		connection.close();
	}
	return null;
}

public String refundcashdesk() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	String id = request.getParameter("selectedUser");
	String date = request.getParameter("date");
	if(date==null){
		date="";
	} 
	if(date.equals("")){
		date = DateTimeUtils.getDateinSimpleFormate(new Date());
		String stemp[] = date.split(" ");
		
		String temp[] = stemp[0].split("-");
		date = temp[2] + "-" + temp[1] + "-" + temp[0];
	}  
		
		
	String apmtSlotId = request.getParameter("apmtSlotId");
//	System.out.println(id);
	double total = 0;
	String totalx = "";
	Connection connection = null;
	
	try{
		CompleteAppointment completeAppointment = new CompleteAppointment();
		ArrayList<CompleteAppointment> clientChargeListDetail = new ArrayList<CompleteAppointment>();
		connection = Connection_provider.getconnection();
		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
		clientChargeListDetail = completeAptmDAO.getPatientChrageDetails(id,date,apmtSlotId,loginInfo.getId());
		completeAppointmentForm.setClientChargeListDetail(clientChargeListDetail);
		
			//total = completeAppointment2.getChargeTotal();
			
				
			
		StringBuffer str = new StringBuffer();
		

		//Practitioner Mail
	/*	str.append("<table  id = 'cashDesk' cellpadding='0' cellspacing='0' class='my-table' > ");
		
		

		str.append("<tr>");
		str.append("<th>Id</th> ");
		str.append("<th>PayBy</th> ");
		str.append("<th>Appointment Type</th> ");
		str.append("<th>Quantity</th> ");
		str.append("<th>Charge</th> ");
		str.append("<th>Delete</th> ");
		str.append("</tr>");*/
		int count = 0;
		
		for(CompleteAppointment completeAppointment1:clientChargeListDetail){
			
		str.append("<tr>");
		//str.append("<td>"+completeAppointment1.getId()+"</td>");
		str.append("<td></td>");
		if(completeAppointment1.getPayBuy().equals("0")){
			str.append("<td>Self</td>");
		}else{
			str.append("<td>Third party</td>");
		}
		str.append("<td>"+completeAppointment1.getMasterchargetype()+"</td>");
		str.append("<td id='invchargenameid"+count+"'>"+completeAppointment1.getApmtType()+"</td>");
		str.append("<td style='text-align:center'>"+completeAppointment1.getQuantity()+"</td>");
		
		if(completeAppointment1.getMasterchargetype().equals("INVESTIGATION")){
			str.append("<td  style='text-align:right'><input class='invunitchargecase' type='number' onchange='showinvchargedetails("+completeAppointment1.getId()+",this.id,this.value)' id='invchargeid"+count+"' name='invchargeid"+count+"' value='"+completeAppointment1.getCharges()+"' /></td>");
		}else{
			str.append("<td  style='text-align:right'><input readonly='readonly' class='invunitchargecase' type='number' onchange='showinvchargedetails("+completeAppointment1.getId()+",this.id,this.value)' id='invchargeid"+count+"' name='invchargeid"+count+"' value='"+completeAppointment1.getCharges()+"' /></td>");
		}
		
		double charge = Double.parseDouble(completeAppointment1.getCharges())*completeAppointment1.getQuantity();
		str.append("<td style='text-align:right'>"+Constants.getCurrency(loginInfo)+"<span id='invamtchargeid'>"+DateTimeUtils.changeFormat(charge)+"</span></td>");
		/*if(count==0){
			str.append("<td><img src='common/images/delete.gif'></img></td>");

		}else{
			str.append("<td onclick = 'confirmedDelete1("+completeAppointment1.getId()+")'><img src='common/images/delete.gif'></img></td>");

		}*/
		str.append("<td onclick = 'confirmedrefundDelete1("+completeAppointment1.getId()+")'><img src='common/images/delete.gif'></img></td>");
		str.append("</tr>");
		count = count+1;
		}
		//str.append("</table>");
		for(CompleteAppointment completeAppointment2:clientChargeListDetail){
			total = completeAppointment2.getChargeTotal();
			totalx = completeAppointment2.getChargeTotalx();
		}

		str.append("<tr style='background-color: #efefef;'>");
		str.append("<th colspan='5' style='font-size: 13px;font-weight: bold;'>Total</th> ");
		
		str.append("<th style='font-size: 13px;font-weight: bold;' colspan='5'>"+Constants.getCurrency(loginInfo)+totalx+"</th> ");
		str.append("</tr>");
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+str.toString()+""); 
		
		
		
		
			String textAjax = new String();
		 
			textAjax = ("<input class='form-control' type = 'hidden' id = 'hiddenTotal' name = 'hiddenTotal' value = '"+Constants.getCurrency(loginInfo)+totalx+" '> <input class='form-control' type = 'hidden' id = 'hiddenTotal1' name = 'hiddenTotal1' value = '"+totalx+" '>");
		
		 
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+textAjax.toString()+"");
		
		
		
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	finally{
		connection.close();
	}
	return null;
}

public String opdlastappointment(){
	Connection connection = null;
	String clientid = request.getParameter("clientid");
	try {
		
		StringBuffer buffer=new StringBuffer();
		connection=Connection_provider.getconnection();
		CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection);
		CompleteAppointment completeAppointment=completeAptmDAO.getopdlastappointment(clientid,loginInfo);
		String commencing=DateTimeUtils.isNull(completeAppointment.getCommencing());
		if(!commencing.equals("")){
		 completeAppointment.setCommencing(DateTimeUtils.getCommencingDate1(completeAppointment.getCommencing()));
		}
		buffer.append(completeAppointment.getApmtType()+"~"+completeAppointment.getCommencing()+"~"+completeAppointment.getCharges()+"~"+completeAppointment.getDuration());
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+buffer.toString()+""); 
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
	
}


public String updateAccountvet()throws Exception {

	if(!verifyLogin(request)){
		return "login";
	}
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
		//logdata
		AccountLogDAO accountLogDAO = new JDBCAccountLogDAO(connection);
		BedDao beddao=new JDBCBedDao(connection);
		InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
	/*	var clientid = read_cookie("cookieClientId"); 
		var practitionerid = read_cookie("cookiePractitionerId");
		var clientName = read_cookie("cookieUserName");
		var practitionerName = read_cookie("cookiePractitioner");*/
		
		String standard_chargeid=request.getParameter("standard_chargeid");
		String ipdid=request.getParameter("ipdid");
		String clientid = request.getParameter("clientid");
		String practitionerid = request.getParameter("practitionerid");
		String clientName = request.getParameter("clientName");
		String practitionerName = request.getParameter("practitionerName");
		String appointmentid = request.getParameter("appointmentid");
		String action = request.getParameter("action");
		String location = request.getParameter("location");
		String ipd = request.getParameter("ipd");
		String gpriscid = request.getParameter("gpriscid");
		String date= request.getParameter("date");
		String vaccineId=request.getParameter("vaccineId");
		if(date==null){
			
			date= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
		} else {
			date = DateTimeUtils.getCommencingDate1(date);
		}
		
		if(date!=null){
			if(date.equals("")){
				date= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			}
		}
		
		
		String ginvstid = request.getParameter("ginvstid");
		String invreqid="";
		if(ginvstid!=null){
			if(!ginvstid.equals("")){
				invreqid=investigationDAO.getInveReqId(Integer.parseInt(ginvstid));
			}
		}
		String wardid="0";
		
		Bed bed=beddao.getEditIpdData(ipdid);
	    if(bed!=null) {
	      	  
	    	 wardid=bed.getWardid(); 
	    }
	    	
	
		if(standard_chargeid!=null){
			
			location="1";
			action="Charge";
		    ipd="1";	
		}
		if(gpriscid==null){
			gpriscid = "0";
		}
		if(ginvstid==null){
			ginvstid = "0";
		}
		if(ipdid==null){
			ipdid="0";
		}
	
		//int tratmentepisodeid = Integer.parseInt(request.getParameter("tratmentepisodeid"));
		//int treatmenntsessions = Integer.parseInt(request.getParameter("treatmenntsessions"));
		
		
		CompleteAppointment completeAppointment = new CompleteAppointment();
		completeAppointment.setClientId(clientid);
		completeAppointment.setPractitionerId(practitionerid);
		completeAppointment.setUser(clientName);
		completeAppointment.setInvoiceDate(date);
		if(action.equals("cash")){
			action="Charge";
		}
		completeAppointment.setChargeType(action);
		completeAppointment.setAppointmentid(appointmentid);
		completeAppointment.setLocation(location);
		if(appointmentid!=null){
			//  21 dec 2017 appointmentid=0
			if(!appointmentid.equals("0")){
				NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
				NotAvailableSlot notAvailableSlot = notAvailableSlotDAO.getOTData(appointmentid);
				if(!notAvailableSlot.getProcedure().equals("0")){
					UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
					UserProfile userProfile = userProfileDAO.getUserprofileDetails(notAvailableSlot.getSurgeonid());
					completeAppointment.setLocation(userProfile.getDiciplineName());
				}
			}
		}
		
		completeAppointment.setPractitionerName(practitionerName);
		
		completeAppointment.setIpd(ipd);
		completeAppointment.setGpriscid(gpriscid);
		completeAppointment.setGinvstid(ginvstid);
		completeAppointment.setAdditionalcharge_id(standard_chargeid);
		completeAppointment.setWardid(wardid);
		int pkgid =0;
		if(!ginvstid.equals("0")){
			pkgid =  investigationDAO.getPkgIDFromInvID(ginvstid);
		}
		completeAppointment.setPkgid(pkgid);
	    
		completeAppointment.setIpdid(Integer.parseInt(ipdid));
		
		ArrayList<Accounts> payByList = completeAptmDAO.getPayByList(appointmentid,loginInfo.getId());
		ArrayList<CompleteAppointment>assesmentList = completeAptmDAO.getCompleteApmtList(appointmentid,loginInfo.getId());
		
		int chkPolicyExcess = 0;
		for(CompleteAppointment c1 : assesmentList){
			if(c1.getApmtType().equalsIgnoreCase(Constants.POLICYEXCESS)){
				chkPolicyExcess = 1;
				completeAppointment.setPolicyExcess(Integer.toString(chkPolicyExcess));
			}
		}
		int selfInvoice = 0;
		int invoice = 0;
		int tpInvoice = 0;
		int selfInvoice1 = 0;
		int invoice1 = 0;
		int tpInvoice1 = 0;
		
		int res = completeAptmDAO.updateArrievedStatus(appointmentid);
		
		for(Accounts accounts : payByList){
			if(accounts.getPayBy()==0){
				completeAppointment.setPayBuy("0");
				
				if(chkPolicyExcess==1){
					completeAppointment.setPolicyExcess("1");
				}else{
					completeAppointment.setPolicyExcess("0");
				}
				
														
				selfInvoice = completeAptmDAO.saveAmpmInvoice(completeAppointment,loginInfo.getId(),loginInfo.getUserId());
				invoice = selfInvoice;
				//logdata
				//selfInvoice1 = accountLogDAO.saveAmpmInvoice(completeAppointment,invoice);
				invoice1 = selfInvoice1;
				
			}else{
				completeAppointment.setPayBuy("1");
				
				completeAppointment.setPolicyExcess("0");
				
				tpInvoice = completeAptmDAO.saveAmpmInvoice(completeAppointment,loginInfo.getId(),loginInfo.getUserId());
				invoice = tpInvoice;
				//logdata
			//	tpInvoice1 = accountLogDAO.saveAmpmInvoice(completeAppointment,invoice);
				invoice1 = tpInvoice1;
				
			}
			MasterDAO masterDAO= new JDBCMasterDAO(connection);
			if(vaccineId!=null){
				for (String vaccineid : vaccineId.split(",")) {
					if(vaccineid.equals("0")){
						continue;
					}
					masterDAO.saveChargeIdOfMedicine(invoice,vaccineid);
				}
	    		
	    	}
			
			
			 IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			 int z = 1;
			for(CompleteAppointment appointment : assesmentList){
				if(accounts.getPayBy() == Integer.parseInt(appointment.getPayBuy())){
					
					if(appointment.getMasterchargetype().equals("INVESTIGATION")){
						String ginventeredcharges = request.getParameter("ginventeredcharges");
						String tempz[] = ginventeredcharges.split("~");
						String chargez = tempz[z];
						appointment.setCharges(chargez);
						z++;
						
						String wid = "0";
						  AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
							int bedid = assessmentFormDAO.getIpdBedno(clientid);
							
							if(bedid!=0){
								BedDao bedDao=new JDBCBedDao(connection);
								
								 ipdid = assessmentFormDAO.getAdmissionid(clientid);
								 bed = bedDao.getEditIpdData(ipdid);
								 wid = bed.getWardid();
							
							}
							
							String tpid = "0";
							ClientDAO clientDAO = new JDBCClientDAO(connection);
							Client client = clientDAO.getClientDetails(clientid);
							tpid = client.getTypeName();
							
							
							if(!ipdDAO.checkifMainTp(tpid)){
								 
								String temptpid= ipdDAO.getFollowerTp(tpid); 
								if(temptpid!=null){
									
									if(!temptpid.equals("0")){
										 tpid=temptpid;  
										 
										 String chargeid = completeAptmDAO.getSelectedChargeId(tpid,completeAppointment.getMasterchargetype(),completeAppointment.getApmtType());
										 completeAppointment.setChargeId(chargeid);
									}
								}
								
							}
							if(loginInfo.isUpdate_investigation_charge()){
								boolean checkinvchargeExist = completeAptmDAO.checkInvchargeExsist(wid,tpid,appointment.getApmtType(),appointment.getMasterchargetype());
								if(checkinvchargeExist){
								
									String previouscharge= completeAptmDAO.getPreviousCharge(wid,tpid,appointment.getApmtType(),appointment.getMasterchargetype(),appointment.getCharges());
									int upd = completeAptmDAO.updateInvestigationCharge(wid,tpid,appointment.getApmtType(),appointment.getMasterchargetype(),appointment.getCharges());
									if(upd>0){
										AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
										int log=0;
										String appid=completeAptmDAO.getAppointmentid(wid,tpid,appointment.getApmtType(),appointment.getMasterchargetype(),appointment.getCharges());
										log= appointmentTypeDAO.insertappointmentlog(loginInfo.getUserId(), appointment.getCharges(), appid,previouscharge);
									}
								}else{
									int ress = completeAptmDAO.saveInvestigationCharge(wid,tpid,appointment.getApmtType(),appointment.getMasterchargetype(),appointment.getCharges());
								}
							}
							
					}
					appointment.setClientId(clientid);
					appointment.setInvestigation_request_id(invreqid);
				    	int result = completeAptmDAO.saveInvoiceAssesment(appointment,invoice);
				    	
				    	
				    	
				    	if(ginvstid!=null){
							if(ginvstid.equals("")||ginvstid.equals("0")){
								
							}else{
								int realInvestigationId=investigationDAO.getinvestigationIdByApmtName(appointment.getApmtType(), invreqid,pkgid);
								investigationDAO.saveChargesToInvestigation(appointment.getCharges(), ""+realInvestigationId, String.valueOf(result),invreqid);
							}
						}
					/*//update product quantity
					boolean checkInventoryproduct = ipdDAO.checkInventoryChargeType(appointment.getMasterchargetype());
					if(!checkInventoryproduct){
						Product product = completeAptmDAO.getInventoryProductDetails(Integer.toString(appointment.getProdid()));
						
						if(product.getStock()==null) {
							  product.setStock("0");
						}
						int rmainQty = Integer.parseInt(product.getStock()) - appointment.getQuantity();
						int update = completeAptmDAO.updateProdStock(rmainQty,Integer.toString(appointment.getProdid()));
					}*/
				}
			}
			
			//logdata
			String status = "Created";
			selfInvoice1 = accountLogDAO.saveAmpmInvoice(completeAppointment,invoice,status,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
			invoice1 = selfInvoice1;
			
		}
		
		
		
	/*	for(CompleteAppointment appointment : assesmentList){
			int result = completeAptmDAO.saveInvoiceAssesment(appointment,invoice);
		}*/
		
		//treatmenntsessions = treatmenntsessions-1;
		//int update = completeAptmDAO.updateTreatmentEpisodeSession(tratmentepisodeid,treatmenntsessions);
		//current date and time in dd/mm/yyyy
		String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
		String datetemp[] = currentDate.split(" ");
		String temp1[] = datetemp[0].split("-");
		String date1 = temp1[0] + "/" + temp1[1] + "/" + temp1[2];
		String time = (datetemp[2]+" "+datetemp[3]);
		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		NotAvailableSlot n = notAvailableSlotDAO.getApmtDetailsForLog(Integer.parseInt(appointmentid));
		String commencingTemp = n.getCommencing();
		String apmtstatus = "Completed";
		int logsave = notAvailableSlotDAO.saveApmtInLog(Integer.parseInt(appointmentid),date1,time,loginInfo.getUserId(),n.getClientId(),commencingTemp,n.getSTime(),apmtstatus,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
		int updatechargestatus = notAvailableSlotDAO.updateChargeStatus(appointmentid);	
		
		int result = completeAptmDAO.deleteComplteApmt(loginInfo.getId());
		
	
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+invoice+""); 
		
		
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		
		connection.close();
	}
	
	return null;

}
}