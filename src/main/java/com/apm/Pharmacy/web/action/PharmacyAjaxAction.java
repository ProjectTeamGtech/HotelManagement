package com.apm.Pharmacy.web.action;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.ChargesAccountProcessingDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCChargeAccountProcesDAO;
import com.apm.Appointment.eu.bi.AppointmentDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentDAO;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Breadcrumbs;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.DiaryManagement.web.action.SmsService;
import com.apm.Emr.eu.bi.PrescriptionDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCPrescriptionDAO;
import com.apm.Emr.web.form.EmrForm;
import com.apm.Inventory.eu.bi.InventoryCatalogueDAO;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.bi.ProcurementDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryCatalogueDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryVendorDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCProcurementDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Inventory.eu.entity.Vendor;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Master.eu.bi.DischargeOutcomeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCDischargeOutcomeDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Pharmacy.eu.bi.PharmacyDAO;
import com.apm.Pharmacy.eu.blogic.jdbc.JDBCPharmacyDAO;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.bi.ChargesReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCChargesReportDAO;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import atg.taglib.json.util.JSONObject;

public class PharmacyAjaxAction extends BaseAction implements Preparable,ModelDriven<EmrForm> {
	EmrForm emrForm=new EmrForm();
	private static final Logger log = Logger.getLogger(PharmacyAjaxAction.class);
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession(false);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	private Pagination pagination = new Pagination(20, 1);
	public Pagination getPagination() {
		return pagination;
	}
	

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	public EmrForm getModel() {
		// TODO Auto-generated method stub
		return emrForm;
	}

	public void prepare() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			ArrayList<Master> locationListPharmacy= pharmacyDAO.getAllLocation();
			emrForm.setLocationListPharmacy(locationListPharmacy);
			 
			String location= (String) session.getAttribute("location");
			emrForm.setLocation(location);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String execute() throws Exception {
		if (!verifyLogin(request)) {

			return "login";
		}
		return SUCCESS;
	}
	public String adnewmedicine() throws Exception{
		if (!verifyLogin(request)) {

			return "login";
		}
		Connection connection = null;
		try {
			String location=(String) session.getAttribute("location");
			if(location==null){
				location="0";
			}
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			StringBuilder buffer1 = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer1.append(line);
			}
			String data = buffer1.toString();
			JSONObject jsonObject = new JSONObject(data);
			
			String pid=jsonObject.getString("pid");
		    String tcount=jsonObject.getString("count");
		    String qty=jsonObject.getString("qty");
		    String hdnphclientid = jsonObject.getString("hdnphclientid");
		    String hdnispharmacy = jsonObject.getString("hdnispharmacy");
			String isbarcodeproductsale = jsonObject.getString("isbarcodeproductsale");
		    int tempclientid = Integer.parseInt(hdnphclientid);
		    int ispharmacypatient =0;
			if(hdnispharmacy.equals("0")){
		    	//HIMS USER
				ispharmacypatient =0;
		    }else{
		    	//Pharmacy User
		    	ispharmacypatient =1;
		    }
			
			DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal1 = Calendar.getInstance();
			cal1.add(Calendar.MINUTE, -45);
			String fromdate = dateFormat1.format(cal1.getTime());			
			int res = pharmacyDAO.deleteTempPharmacyTimelyData(fromdate);
			
		    Product product=inventoryProductDAO.getProduct(pid);
		    //  03-03-2019 for qty not greater than stock
		    double stock =Double.parseDouble((product.getStock()));
		    int reqstock = pharmacyDAO.getAllReqStockPharmacy(pid);
		    /*boolean productalreadyadded = pharmacyDAO.checkProductAddedForSameClient(pid,tempclientid,ispharmacypatient,loginInfo.getLoginsessionid());*/
		    double requserstockstock = pharmacyDAO.getAllReqStockPharmacy(pid,tempclientid,ispharmacypatient,loginInfo.getLoginsessionid());
		    double totalavailablestock = (stock - reqstock) + requserstockstock;
		    /*if(Integer.parseInt(qty)>Integer.parseInt(product.getStock())){*/
		    int isinadjustment = inventoryProductDAO.checkProductInAdjustment(pid);
			if(isinadjustment==1){
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("productlist", "3");
				jsonobj.put("avilstock", totalavailablestock);
				String response1 = jsonobj.toString();
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(response1);
			}/*else if(productalreadyadded){
		    	JSONObject jsonobj = new JSONObject();
				jsonobj.put("productlist", "4");
				String response1 = jsonobj.toString();
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(response1);
		    }*/else if(Integer.parseInt(qty)>totalavailablestock){
		    	JSONObject jsonobj = new JSONObject();
				jsonobj.put("productlist", "1");
				jsonobj.put("avilstock", totalavailablestock);
				String response1 = jsonobj.toString();
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(response1);
		    }else{
		    	/*String medicine_shedule= inventoryProductDAO.getMedicineShedule(product.getCatalogueid());*/
			    
			    if(product.getId()>0){
			    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					String fromDate = dateFormat.format(cal.getTime());
			    	String todate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			    	
					boolean chargetempidisavailable = pharmacyDAO.checkMedicineTempChargeLog(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid(),product.getId(),qty,fromDate,todate);
					if(!chargetempidisavailable){
						//Check already patient and user session id available or not
						boolean flag = pharmacyDAO.checkPatientChargeAvailableInLog(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid(),product.getId(),qty,fromDate,todate);
						int chargesessionid=0;
						if(!flag){
							//if entry not present in temp table so insert into temp table
							chargesessionid = pharmacyDAO.insertMedicineChargeLog(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid(),product.getId(),qty,fromDate,todate);
						}else{
							chargesessionid = pharmacyDAO.getPatientChargeAvailableInLog(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid(),product.getId(),qty,fromDate,todate);
						}
						
						int chargetempid=0;
						boolean checkproductalreadyadded = pharmacyDAO.checkProductAddedForSameClient(pid,tempclientid,ispharmacypatient,loginInfo.getLoginsessionid());
						if(checkproductalreadyadded){
							JSONObject jsonobj = new JSONObject();
							jsonobj.put("productlist", "4");
							String response1 = jsonobj.toString();
							response.setContentType("application/json");
							response.setHeader("Cache-Control", "no-cache");
							response.getWriter().write(response1);
						}else{
							//if entry not present in temp table so insert into temp table
							chargetempid = pharmacyDAO.insertMedicineTempChargeLog(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid(),product.getId(),qty,fromDate,todate,chargesessionid,totalavailablestock,product.getSale_price(),isbarcodeproductsale);
							JSONObject jsonobj = new JSONObject();
							jsonobj.put("productlist", "2");
							String response1 = jsonobj.toString();
							response.setContentType("application/json");
							response.setHeader("Cache-Control", "no-cache");
							response.getWriter().write(response1);
						}
					    
					}else{
						JSONObject jsonobj = new JSONObject();
						jsonobj.put("productlist", "2");
						String response1 = jsonobj.toString();
						response.setContentType("application/json");
						response.setHeader("Cache-Control", "no-cache");
						response.getWriter().write(response1);
					}
					
			    } else {
			    	JSONObject jsonobj = new JSONObject();
					jsonobj.put("productlist", "0");
					String response1 = jsonobj.toString();
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().write(response1);
			    }
		    }
		    
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("productlist", "2");
			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);
		} finally {
			connection.close();
		}
		return null;
	}
	
public String newpatientsale() throws Exception{
	if (!verifyLogin(request)) {

		return "login";
	}
		Connection connection=null;
		String sessinbillno="0";
		int chargetempcount=0;
		int chargeaddedcount=0;
		int tempclientid=0;
		int ispharmacypatient =0;
		String sessiondatetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		String sessionuserid = loginInfo.getUserId();
		try {
			connection=Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			InventoryProductDAO productDAO=new JDBCInventoryProductDAO(connection);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			PrescriptionDAO prescriptionDAO = new JDBCPrescriptionDAO(connection);
			String notes="";
			String fullname=emrForm.getFullname();
			String agegender=emrForm.getAgeandgender();
			String paymode=emrForm.getPaymode();
			String address=emrForm.getWardname();
			String practitioner=emrForm.getPractitionerName();
			String phar_practid = emrForm.getPhar_practid();
			String dateTime=emrForm.getDateTime();
			String mobile= emrForm.getMobno();
			String extpid = request.getParameter("extpid");
			String tpid= request.getParameter("tpid");
			String priscid= emrForm.getPriscid();
			String previous_balance = emrForm.getPrevious_balance();
			String payamt= emrForm.getPayamt();
			String balance = emrForm.getBalance();
			String cgst= emrForm.getCgst();
			String sgst= emrForm.getSgst();
			String date= DateTimeUtils.getCommencingDate1(dateTime); 
			notes =emrForm.getNotes();
			//int freeqty=emrForm.getFreeqty();
			String oldparentid = emrForm.getOldparentid();
			int newparentid = emrForm.getNewparentid();
			priscid = oldparentid;
			int isdirectsale = emrForm.getIsdirectsale();
			//  16 Nov 2018 For Pharmacy Repeat bill issue
			tempclientid = Integer.parseInt(emrForm.getHdnphclientid());
		   
			if(emrForm.getHdnispharmacy().equals("0")){
		    	//HIS USER
				ispharmacypatient =0;
		    }else{
		    	//Pharmacy User
		    	ispharmacypatient =1;
		    }
			
			int updatechargesession = pharmacyDAO.updateChargeSessionStatus(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid(),1);
			
			//Check already patient and user session id available or not
			boolean flag = pharmacyDAO.checkPatientAvailableInLog(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid(),0);
			if(!flag){
				//if entry not present in temp table so insert into temp table
				int tempsessionid = pharmacyDAO.insertTempPharSaleSession(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid(),0,"0","0","0");
				
				//Second filter check already tempid in sale bill
				/*boolean flag2 = pharmacyDAO.checkTempIdInPharmacyBill(tempsessionid);
				if(!flag2){*/
					//It means its 1st bill not repeat bill
					if(balance==null){
						balance="0";
					}
					if(tpid==null){
						tpid="0";
					}
					if(tpid.equals("")){
						tpid="0";
					}
					if(balance.equals("")){
						balance="0";
					}
					
					String paynote="";
					String loc= (String) session.getAttribute("location");
					
					Client client=new Client();
					client.setClientName(fullname);
					if(agegender!=null){
						 if(!agegender.equals("")){
							 try {
								 String data[]=agegender.split("/");
								 client.setGender(data[0]);
								 client.setAge(Integer.parseInt(data[1]));
							 } catch (Exception e) {
								 e.printStackTrace();
							 }
						 }
					}
					
					if(paymode!=null){
						if(paymode.equals("Card")){
							paynote= request.getParameter("card");
						}
						if(paymode.equals("Cheque")){
							paynote= request.getParameter("chequeno");
						}
						if(paymode.equals("NEFT") || paymode.equals("Online Payment")){
							paynote= request.getParameter("neftid");
						}
					}else{
						paymode="";
					}
					client.setAddress(address);
					client.setReference(practitioner);
					client.setLastModified(dateTime);
					client.setFirstName(fullname);
					client.setMobNo(mobile);
					client.setBalance(balance);
					int pharmacyclientid= Integer.parseInt(emrForm.getHdnphclientid());
					
						if(extpid=="" || extpid.equals("") || extpid.equals("0")){
							int result = pharmacyDAO.updatebalance(pharmacyclientid,balance);
						}else{
						   pharmacyclientid= Integer.parseInt(extpid);
						   String bal= pharmacyDAO.getpreviousbalance(pharmacyclientid);
						   if(bal==null){
							   bal="0";
						   }else if(bal.equals("")){
							   bal="0";
						   }
						   double temp =Double.parseDouble(bal) + Double.parseDouble(balance);
					       int result = pharmacyDAO.updatebalance(pharmacyclientid,String.valueOf(temp));
					       int res = pharmacyDAO.updateExPatient(pharmacyclientid,client);
						}
						String vat=emrForm.getVat();
						if(vat!=null){
							if(vat.equals("")){
								vat ="0";
							}
						}else{
							vat ="0";
						}

						String discounttype ="";
					    String salediscount = "";
					    String discinper = "";
						if(paymode.equals("Card")){
							boolean fla4 = pharmacyDAO.checkPatientAvailableInLog(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid(),1);
							if(fla4){
								CompleteAppointment completeAppointment = pharmacyDAO.getTempPharDiscountData(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid(),1);
								discounttype = completeAppointment.getDisc_type();
							    salediscount = completeAppointment.getActualdiscount();
							    discinper = completeAppointment.getDiscperc();
							}else{
								discounttype = emrForm.getDiscounttype();
							    salediscount = emrForm.getSalediscount();
							    discinper = emrForm.getDiscinper();
							}
						}else{
							discounttype = emrForm.getDiscounttype();
						    salediscount = emrForm.getSalediscount();
						    discinper = emrForm.getDiscinper();
						}
					    
						
					    if(discounttype!=null){
					    	if(discounttype.equals("")){
					    		discounttype="1";
					    	}
					    }else{
					    	discounttype="1";
					    }
					    
					    if(salediscount!=null){
					    	if(salediscount.equals("")){
					    		salediscount="0";
					    	}
					    }else{
					    	salediscount="0";
					    }
					    
					    if(discinper!=null){
					    	if(discinper.equals("")){
					    		discinper="0";
					    	}
					    }else{
					    	discinper="0";
					    }
					    
					    double discount=0;
					    String total=emrForm.getTotal();
					    String subbtotal = emrForm.getSubbtotal();
					    if(subbtotal!=null){
					    	if(subbtotal.equals("")){
					    		subbtotal="0";
					    	}
					    }else{
					    	subbtotal="0";
					    }
					    
					    if(discounttype.equals("1")){
					    	discount = Double.parseDouble(salediscount);
					    }else{
					    	discount= Double.parseDouble(subbtotal) * Double.parseDouble(salediscount)/100;
					    }
					    discount = Math.round(discount * 100.0) / 100.0;
					    
					    //  17-12-2018
					    String grosstotal = emrForm.getGrosstotal();
					    String grosssubtotal = emrForm.getGrosssubtotal();
					    
					    String clientname=fullname;
					    CompleteAppointment appointment=new CompleteAppointment();
					    appointment.setGrosssubtotal(grosssubtotal);
					    appointment.setGrosstotal(grosstotal);
					    appointment.setClientId("0");
					    appointment.setPclientid(""+pharmacyclientid+"");
					    if(emrForm.getHdnispharmacy().equals("0")){
					    	appointment.setClientId(Integer.toString(pharmacyclientid));
					    	appointment.setPclientid("0");
					    }
					    if(appointment.getPclientid().equals("0")) {
					       String third_party_name_id=pharmacyDAO.getthirdpartynameidByClientid(appointment.getClientId());
					       appointment.setThird_party_name_id(third_party_name_id);
					    }
					    
					    appointment.setPayBuy("Client");
					    appointment.setCharges(""+total+"");
					    appointment.setChargeType("");
					    appointment.setVat(Double.parseDouble(vat));
					    appointment.setDiscount(discount);
					    appointment.setTotal(Double.parseDouble(total));
					    appointment.setNotes(notes);
					    
					    appointment.setPriscid(0);
					    String time=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
					    appointment.setInvoiceDate(date);
					    appointment.setInvoiceTime(time);
					    appointment.setBalance(balance);
					    appointment.setLocation(loc);
					    appointment.setCgst(cgst);
					    appointment.setTpid(tpid);
					    appointment.setSgst(sgst);
					    appointment.setPayamt(payamt);
					    appointment.setUserid(loginInfo.getUserId());
					    appointment.setOldparentid(oldparentid);
					    appointment.setNewparentid(newparentid);
					    
					    //  12-nov-2018 actual amount store, discttype and actual discount amount in table 
					    appointment.setDisc_type(discounttype);
					    appointment.setActualdiscount(salediscount);
					    appointment.setActualtotal(subbtotal);
					    appointment.setTempsessionid(tempsessionid);
					    appointment.setInitalpaymode(paymode);
					    appointment.setFinalpaymode(paymode);
					    appointment.setBedid("0");
			    		appointment.setWardid("0");
			    		appointment.setPhar_ipdid("0");
					    //  14-02-2019
					    if(isdirectsale>0){
					    	if(ispharmacypatient==0){
					    		Bed bed = pharmacyDAO.getIPDActiveDataFromClientid(""+tempclientid);
					    		appointment.setBedid(bed.getBedid());
					    		appointment.setWardid(bed.getWardid());
					    		appointment.setPhar_ipdid(bed.getIpdid());
					    	}
					    }else{
/*					    	String ipdid =emrForm.getPhar_ipdid();
					    	String bedid = emrForm.getPhar_bedid();
					    	String wardid = emrForm.getPhar_wardid();
					    	appointment.setBedid(bedid);
				    		appointment.setWardid(wardid);
				    		appointment.setPhar_ipdid(ipdid);*/
					    	if(ispharmacypatient==0){
					    		Bed bed = pharmacyDAO.getIPDActiveDataFromClientid(""+tempclientid);
					    		appointment.setBedid(bed.getBedid());
					    		appointment.setWardid(bed.getWardid());
					    		appointment.setPhar_ipdid(bed.getIpdid());
					    	}
					    }
					    
					    /*ArrayList<Priscription> tempmedicinelist = pharmacyDAO.getTempMedicineList(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid());*/
					    Vector<Priscription> tempmedicinelist = pharmacyDAO.getTempMedicineList(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid());
					    chargetempcount =  tempmedicinelist.size();
					    
					    if(chargetempcount==0){
					    	return "errorpage";
					    }
					    boolean creditbillerror=false;
					    if(paymode.equals("Credit")){
					    	double tot=Double.parseDouble(payamt);
					    	if(tot>0){
					    		if(Double.parseDouble(balance)==0){
					    			creditbillerror = true;
					    			double temp= (Double.parseDouble(discinper)*Double.parseDouble(grosssubtotal))/100;
					    			balance = ""+(Double.parseDouble(subbtotal) - temp);
					    			balance = ""+Math.round(Double.parseDouble(balance)*100)/100;
					    			appointment.setBalance(balance);
					    		}
					    	}
					    }
					    appointment.setPractitionerId(phar_practid);
					    appointment.setPractitionerName(practitioner);
					    int billno=pharmacyDAO.addMedicineBill(appointment);
					   
					    int updatesessiondata = pharmacyDAO.updateSessionChargeBillno(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid(),billno);
					    
					    sessinbillno = ""+billno;
					    
					    //  19 Sep 2018 generate pharmacy bill Seq No according location
					    int billseqno = pharmacyDAO.getPharmacyBillSeqNo(appointment.getLocation());
					    billseqno = billseqno+1;
					    int savebillseqresult = pharmacyDAO.updatePharmacySeqNo(billno,billseqno);
					    
					    //update in new patient recors
					    int result=pharmacyDAO.updateBillandStatus(pharmacyclientid,billno,"1");
					    
					    if(priscid!=null){
					    	if(!priscid.equals("") || !priscid.equals("0")){
					    		 result=pharmacyDAO.updatePriscEmrBill(billno,priscid);
					    		 result = pharmacyDAO.updatePriscEmrBillnew(billno,newparentid);
					    		 int ressul1 = prescriptionDAO.updatePriscTokenStatus(newparentid,2);
					    	}
					    }
					    
					    //delete if estimate bill
					    //String estimatebill= emrForm.getEstimatebill();
					    //result =pharmacyDAO.deleteEstimateBill(estimatebill);
					    
					    Priscription priscription=new Priscription();
					    priscription.setPclientid(appointment.getPclientid());
					    priscription.setClientId(appointment.getClientId());
					    //String nowdate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
					    double totaldebit =0;
					   int ii = 1;
					    
					    for(Priscription prisc1:tempmedicinelist){
					    	if(prisc1==null){
					    		
					    		int deletestatus = pharmacyDAO.deletePharmacyBillData(sessinbillno,sessionuserid,sessiondatetime);
					    		return "errorpage";
					    	}
					    	if(prisc1.getId()==0){
					    		int deletestatus = pharmacyDAO.deletePharmacyBillData(sessinbillno,sessionuserid,sessiondatetime);
					    		return "errorpage";
					    	}
					    	String mdicinenameid =request.getParameter("mdicinenameid"+prisc1.getId());
					    	String sale_price = request.getParameter("sale_price"+prisc1.getId());
					    	String reqqty = request.getParameter("reqqty"+prisc1.getId());
					    	String freeqty=request.getParameter("freeqty"+prisc1.getId());
					    	String dis_perproduct=request.getParameter("discount_perproduct"+prisc1.getId());
					    	priscription.setOrignal_mrp(sale_price);
					    	if(!dis_perproduct.equals("0")) {
					    		Double sale_price1=Double.parseDouble(sale_price)*(100-Double.parseDouble(dis_perproduct))/100;
					    		sale_price=Double.toString(sale_price1);
					    	}
					    	if(reqqty!=null){
					    		if(reqqty.equals("")){
					    			reqqty="0";
					    		}
					    	}else{
					    		reqqty="0";
					    	}
					    	
					    	if(freeqty!=null){
					    		if(freeqty.equals("")){
					    			freeqty="0";
					    		}
					    	}else{
					    		freeqty="0";
					    	}
					    	
					    	priscription.setBarcodesale(prisc1.getBarcodesale());
					    	priscription.setProduct_id(prisc1.getProduct_id());
					    	priscription.setMdicinenameid(mdicinenameid);
					    	Product product=productDAO.getProduct(prisc1.getProduct_id());
					    	priscription.setSaleqty(prisc1.getQty());
					    	priscription.setReqqty(Integer.parseInt(reqqty));
					    	priscription.setMrp(sale_price);
					    	priscription.setFullname(practitioner);
					    	priscription.setClientname(clientname);
					    	priscription.setWhopay("0");
					    	priscription.setDate(date);
					    	priscription.setFreeqty(Integer.parseInt(freeqty));
					    	priscription.setDiscount_perproduct((dis_perproduct));
					    
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
					    		log.debug("@@@@@@@@@@@@@@@@@@@@@@@"+e.getMessage());
								SmsService s = new SmsService();
							    //s.sendSms(e.getMessage()+"bill no:"+sessinbillno+" "+loginInfo.getClinicUserid(), "9764434837", loginInfo, new EmailLetterLog());
							    //s.sendSms(e.getMessage()+"bill no:"+sessinbillno+" "+loginInfo.getClinicUserid(), "8055056015", loginInfo, new EmailLetterLog());
					 		}
					    	double temptot=0;
					    	double tempvat =0;
					    	double gst=0;
					    	/*if(discounttype.equals("0")){
					    		tempvat=tot*Double.parseDouble(discinper)/100;
					    	}else{
					    		tempvat=tot*Double.parseDouble(discinper)/100;
					    	}*/
					    	tempvat=tot*Double.parseDouble(discinper)/100;
					    	temptot = tot - tempvat;
					    	double dividevat= 100+tvat;
					    	if(loginInfo.isSaimed() || loginInfo.isInfinyte()) {
					    		gst= temptot*tvat/100;
					    	}else {
					    	    gst= temptot*tvat/dividevat;
					    	}
					    	//double gst= temptot*tvat/dividevat;
					    	double half= gst/2;
					    	half = Math.round(half*100.0)/100.0;
					    	gst = Math.round(gst*100.0)/100.0;
					    	totaldebit = totaldebit + temptot;
					    	priscription.setTgstamt(DateTimeUtils.changeFormat(gst));
					    	priscription.setGstper(product.getVat());
					    	priscription.setSharediscount(DateTimeUtils.changeFormat(tempvat));
					    	
					    	priscription.setCgst(DateTimeUtils.changeFormat(half));
					    	priscription.setSgst(DateTimeUtils.changeFormat(half));
					    	priscription.setPrectionerid(phar_practid);
					    	try {
					    		if(product.getStock()!="0"){
					    			if(product.getStock().equals("")){
					    				product.setStock("0");
					    			}
					    		}else{
					    			product.setStock("0");
					    		}
					    		/*if(!product.getStock().equals("0")){*/
					    			//akash 30-07-2020
					    			double bill_purpice=product.getIndividual_pur_price()*priscription.getSaleqty();
					    			double bill_pur_gst=product.getIndi_purchase_gst()*priscription.getSaleqty();
					    			priscription.setBill_purpice(bill_purpice);
					    			priscription.setBill_pur_gst(bill_pur_gst);
					    			int previousstock = productDAO.getPreviousStock(priscription.getProduct_id());
					    			int chargeid=pharmacyDAO.addMedicineCharges(priscription,billno);
					    			if(chargeid==0){
					    				int deletebill =pharmacyDAO.deletePharmacyBillData(sessinbillno,sessionuserid,sessiondatetime);
					    				return "errorpage";
					    			}
					    			result=productDAO.updateMedicineQty(priscription.getSaleqty(),priscription.getProduct_id(),0);
					    			
					    			if(priscription.getFreeqty()!=0) {
					    				result=productDAO.updateMedicineQty(priscription.getFreeqty(),priscription.getProduct_id(),0);
					    				
					    			}
					    			
					    		    
					    			//stock log
									String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
									int qtyinout=1;
									String source ="Pharmacy Sale";
									int currentstock=productDAO.getPreviousStock(priscription.getProduct_id());
									int changeqty=priscription.getSaleqty();
									int reslog = productDAO.insertIntoProductLog(loginInfo.getUserId(),datetime,product.getLocation(),qtyinout,priscription.getProduct_id(),product.getCatalogueid(),source,currentstock,previousstock,changeqty,"0","0",billno,0,0,"0");
					    			
									String date2 =  DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
									boolean checkopningstockexist = pharmacyDAO.checkopeningstockexist(priscription.getProduct_id(),date2);
									if(!checkopningstockexist){
										int r = pharmacyDAO.saveOpeningStock(priscription.getProduct_id(),date2,previousstock,"0");
									}
									
					    			// reorder level @jitu // 
					    			//min /max reorder level code by jitu// 
					    			String catalogueid= product.getCatalogueid(); 
					    			Product pmaster= productDAO.getProductCatalogueDetails(catalogueid);
					        		int minorder =Integer.parseInt(pmaster.getMinorder());
					    			double allstock= productDAO.getTotalStockProduct(catalogueid);
					    			//  01/10/2019 commented because min order is in qty
					    			/*int pack= Integer.parseInt(pmaster.getPack());
					    			if(pack==0){
					    				pack = Integer.parseInt(DateTimeUtils.numberCheck1(product.getPack()));
					    			}
					    			int nowstock= allstock/pack;*/
					        	
						        	if(allstock<=minorder){
						        		//add to po que list
						        		product.setDate(date);
						        		//  26-06-2018 error while billing
						        		int maxorder=Integer.parseInt(pmaster.getMaxorder());
						        		double orderqty=maxorder- allstock; 
						        		if(orderqty<0){
						        			orderqty=0;
						        		}
						        		product.setQty((orderqty));
						        		//add to po que
						    			int res = procurementDAO.saveNewTempPo(product);
						        	}
					    		
					    	/*}*/
					    } catch (Exception e) {
				    		log.debug("@@@@@@@@@@@@@@@@@@@@@@@"+e.getMessage());
				    		SmsService s = new SmsService();
						    //s.sendSms(e.getMessage()+"bill no:"+sessinbillno+" "+loginInfo.getClinicUserid(), "9764434837", loginInfo, new EmailLetterLog());
						    //s.sendSms(e.getMessage()+"bill no:"+sessinbillno+" "+loginInfo.getClinicUserid(), "8055056015", loginInfo, new EmailLetterLog());
						}
					    	
					    	//update inventory
					    	//result=productDAO.updateMedicineQty(priscription.getSaleqty(),priscription.getMdicinenameid(),0);
					    	//chargeaddedcount = chargeaddedcount+1;
					    }
					      //for parihar
					    if(loginInfo.isParihar()) {
		    			  int productid=pharmacyDAO.getproductid(billno);
		    			  int cataloguid=pharmacyDAO.getCatalogueid(productid);
		    			  int subcategoryid=pharmacyDAO.getsubcategoryid(cataloguid);
		    			  String subcategoryname=pharmacyDAO.getsubcategorynameById(subcategoryid);
		    			  int parlocation=pharmacyDAO.getlocationforParihar(subcategoryname);
		    			  result=pharmacyDAO.getUpdatelocationforParihar(parlocation,billno);
			           }
					    chargeaddedcount = pharmacyDAO.getAllPharmacyChargeCount(billno);
					   
					    if(chargeaddedcount==chargetempcount){
					    
					    
					    //int updatedebit = pharmacyDAO.updateBillDebit(Math.round(totaldebit),billno);
					    
					    int updatechargesession2 = pharmacyDAO.updateChargeSessionStatus(tempclientid,ispharmacypatient,loginInfo.getLoginsessionid(),2);
					    
					   double tot=Double.parseDouble(payamt);
					   if(paymode.equals("Credit")){
						   if(tot>0){
							   tot =0.0; 
						   }
					   }
					   //record payment
					  /* String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());*/
					   String datetime=  date+" "+time;
					   //  19 Sep 2018 generate pharmacy payment Seq No according location
					   int paymentseqno = pharmacyDAO.getPharmacyPaymentSeqNo(loc);
					   paymentseqno = paymentseqno+1;
					   result=pharmacyDAO.recordPaymentMedicine(String.valueOf(billno),tot,paymode,date,appointment.getClientId(),"0",discount,notes,appointment.getPclientid(),loginInfo.getUserId(),loc,0,paymentseqno);
					   int paymentid =result;
					   int d= pharmacyDAO.updatePaymentNote(result,paynote);
					   result =pharmacyDAO.updatePaymentDateTime(datetime,result);
					   //int paymentseqnores = pharmacyDAO.updatePharmacyPaymentSeqNo(result, paymentseqno);
					   if(!tpid.equals("0")){
					    	d=pharmacyDAO.updateTpidtoPayment(result,tpid);
					   }
					  
					   CompleteAppointment completeAppointment=pharmacyDAO.getBillDetails(billno);
					   ArrayList<Priscription> medicineChargeList=pharmacyDAO.getMedicineChargesList(billno,loginInfo);
					   double subtotal=0;
					   for(Priscription p:medicineChargeList){
						  subtotal=subtotal+(Double.parseDouble(p.getMrp())*p.getSaleqty());
					   }
					  
					   if(previous_balance==null){
						   previous_balance="0";
					   }
					  emrForm.setPrevious_balance(previous_balance);
					  emrForm.setSubtotal(DateTimeUtils.changeFormat(subtotal));
					  emrForm.setMedicineChargeList(medicineChargeList);
					  emrForm.setClientId(completeAppointment.getClientId());
					  emrForm.setTotal(DateTimeUtils.changeFormat(completeAppointment.getTotal()));
					  String disc=String.valueOf(completeAppointment.getDiscount());
					  emrForm.setDiscount(DateTimeUtils.changeStringFormat(disc));
					  emrForm.setVat(String.valueOf(vat));
					  emrForm.setCgst(completeAppointment.getCgst());
					  emrForm.setSgst(completeAppointment.getSgst());
					  emrForm.setBillno(billno);
					
					  
					  //26-12-2019   if direct discount greater then 10 then entry in direct applied
					  //discinper
					  if(loginInfo.isMax_phar_discount() || loginInfo.getUserType()==2 || loginInfo.getSuperadminid()==1){
						  if(Double.parseDouble(discinper)>10){
							  int disc_requeststatus=2;
							  int res = pharmacyDAO.savePharmacyDiscountRequest(""+billno,subbtotal,discounttype,salediscount,loginInfo.getUserId(),datetime,discinper,priscription,disc_requeststatus);
						  }
					  }
					 
					 
					  
					  
					  //20 OCT 2018   ledger work
					  // ledger for credit invoice
					  ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
					  double debit = completeAppointment.getTotal();
					  String clientid ="";
					  String pclientid="";
					  if(emrForm.getHdnispharmacy().equals("0")){
						  	clientid = ""+pharmacyclientid;
						  	pclientid ="0";
					  }else{
						  	clientid = "0";
						  	pclientid =""+pharmacyclientid;
					  }
					  
					  if (billno > 0) {
						  String locationname = pharmacyDAO.getLocationName(loc);
						  String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds(locationname);
						  String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, "0", "0","2",0);
			
						  double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
						  lbal = lbal + debit;
						  String credit = "" + debit + "";
						  String ldebit = "0";
						  String product = "xxxxx";
						  String partyid = clientid;
						  String otherclientid =pclientid;
						  
						  String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						  int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
									ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,loc);
							
						  //second effect
						  lbal = 0;
						  credit = "0";
						  ldebit = "" + debit + "";
						  product = "xxxxx";
						  partyid = clientid;
						  otherclientid =pclientid;
						  lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						  saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
									ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,loc);
						}
					  	
					  	if (discount > 0) {
							String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds("Discount");
							String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, "0", "0","2",0);
		
							double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
							lbal = lbal + discount;
							String credit = "" + discount + "";
							String ldebit = "0";
							String product = "" + billno + "";
							String partyid = clientid;
							String otherclientid =pclientid;
							String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
									ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,loc);
							
							//second effect
							 lbal = 0;
							 credit = "0";
							 ldebit = "" + discount + "";
							 product = "xxxxx";
							 partyid = clientid;
							 otherclientid =pclientid;
							 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							 saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
									ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,loc);
						}
					  	
					  	if(Double.parseDouble(vat)>0){
					  		String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds("GST");
							String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, "0", "0","2",0);
		
							double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
							lbal = lbal + Double.parseDouble(vat);
							String credit = "" + Double.parseDouble(vat) + "";
							String ldebit = "0";
							String product = "" + billno + "";
							String partyid = clientid;
							String otherclientid =pclientid;
							
							String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
									ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,loc);
							
							//second effect
							 lbal = 0;
							 credit = "0";
							 ldebit = "" + Double.parseDouble(vat) + "";
							 product = "xxxxx";
							 partyid = clientid;
							 otherclientid =pclientid;
							 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							 saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
									ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,loc);
					  	}
					  	
					  	if(!paymode.equals("") || !paymode.equals("Credit")){
					  		String locationname = pharmacyDAO.getLocationName(loc);
							String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds(locationname);
							String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, paymode, "0","2",0);
				
							double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
							lbal = lbal + tot;
							String credit = "" + tot + "";
							String ldebit = "0";
							String product = "xxxxx";
							String partyid = clientid;
							String otherclientid =pclientid;
							  
							String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
										ledgerid, lcommencing, "0", 0,otherclientid,"0","0","0",""+paymentid+"",0,0,loc);
								
							//second effect
							lbal = 0;
							credit = "0";
							ldebit = "" + tot + "";
							product = "xxxxx";
							partyid = clientid;
							otherclientid =pclientid;
							lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
							saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
										ledgerid, lcommencing, "0", 0,otherclientid,"0","0","0",""+paymentid+"",0,0,loc);
					  	}
					  	
					    Clinic clinic = new Clinic();
						ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
						clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
						
						Location location = clinicDAO.getRegisterdLication();
						clinic.setLocationname(location.getAddress());
						
						clinic.setCurDateTime(DateTimeUtils.getCommencingDate1(date));
						session.setAttribute("billno", billno);
						session.setAttribute("selectedid", 0);
						
						session.setAttribute("clinicinfo", clinic);
						session.setAttribute("clientinfo", client);
						if(creditbillerror){
							SmsService s = new SmsService();
						    //s.sendSms("credit bill check bill no:"+sessinbillno+" "+loginInfo.getClinicUserid(), "9764434837", loginInfo, new EmailLetterLog());
						}
					    }else{
					    	int deletestatus = pharmacyDAO.deletePharmacyBillData(sessinbillno,sessionuserid,sessiondatetime);
					    	return "errorpage";
					    }
				}
				/*int res1 = pharmacyDAO.deleteTempPharmacyData(loginInfo.getLoginsessionid());*/
			
				int res1 = pharmacyDAO.deleteTempPharmacySaleData(loginInfo.getLoginsessionid(),""+tempclientid,ispharmacypatient);
				int resss = pharmacyDAO.deleteTempChargePharmacySessionLog(loginInfo.getLoginsessionid(),""+tempclientid,ispharmacypatient);
			/*}*/
			  
			  
		} catch (Exception e) {
				e.printStackTrace();
				log.debug("@@@@@@@@@@@@@@@@@@@@@@@"+e.getMessage());
	 			if(connection==null){
					connection=Connection_provider.getconnection();
				}
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				int deletestatus = pharmacyDAO.deletePharmacyBillData(sessinbillno,sessionuserid,sessiondatetime);
				/*int res1 = pharmacyDAO.deleteTempPharmacyData(loginInfo.getLoginsessionid());*/
				int res1 = pharmacyDAO.deleteTempPharmacySaleData(loginInfo.getLoginsessionid(),""+tempclientid,ispharmacypatient);
				int resss = pharmacyDAO.deleteTempChargePharmacySessionLog(loginInfo.getLoginsessionid(),""+tempclientid,ispharmacypatient);
				SmsService s = new SmsService();
			    //s.sendSms(e.getMessage()+"bill no:"+sessinbillno+" "+loginInfo.getClinicUserid(), "9764434837", loginInfo, new EmailLetterLog());
			    //s.sendSms(e.getMessage()+"bill no:"+sessinbillno+" "+loginInfo.getClinicUserid(), "8055056015", loginInfo, new EmailLetterLog());
			  return "errorpage";
		   
		}finally {
			if(connection==null){
				connection=Connection_provider.getconnection();
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				int deletestatus = pharmacyDAO.deletePharmacyBillData(sessinbillno,sessionuserid,sessiondatetime);
				/*int res1 = pharmacyDAO.deleteTempPharmacyData(loginInfo.getLoginsessionid());*/
				int res1 = pharmacyDAO.deleteTempPharmacySaleData(loginInfo.getLoginsessionid(),""+tempclientid,ispharmacypatient);
				int resss = pharmacyDAO.deleteTempChargePharmacySessionLog(loginInfo.getLoginsessionid(),""+tempclientid,ispharmacypatient);
				SmsService s = new SmsService();
			    //s.sendSms("connection close. bill no:"+sessinbillno+" "+loginInfo.getClinicUserid(), "9764434837", loginInfo, new EmailLetterLog());
			    //s.sendSms("connection close bill no:"+sessinbillno+" "+loginInfo.getClinicUserid(), "8055056015", loginInfo, new EmailLetterLog());
				return "errorpage";
			}
			connection.close();
		}
		
		
		return "redirectbill";
	}



public String gettempsaledata() throws Exception{
	if (!verifyLogin(request)) {

		return "login";
	}
	Connection connection = null;
	try {
		String location=(String) session.getAttribute("location");
		if(location==null){
			location="0";
		}
		connection = Connection_provider.getconnection();
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		String hdnphclientid = jsonObject.getString("hdnphclientid");
	    String hdnispharmacy = jsonObject.getString("hdnispharmacy");
	    setSaleTempdata(Integer.parseInt(hdnphclientid),Integer.parseInt(hdnispharmacy),0);
	} catch (Exception e) {
		e.printStackTrace();
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("productlist", "0");
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} finally {
		connection.close();
	}
	return null;
}
public void setSaleTempdata(int hdnphclientid, int hdnispharmacy, int type){
	
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		String location=(String) session.getAttribute("location");
		if(location==null){
			location="0";
		}
	 	int count=0;
	    int sr=0;
	    Vector<Priscription> tempmedicinelist = pharmacyDAO.getTempMedicineList(hdnphclientid,hdnispharmacy,loginInfo.getLoginsessionid());
	    StringBuffer buffer=new StringBuffer();
	    for (Priscription priscription : tempmedicinelist) {
	    	Product product = inventoryProductDAO.getProduct(priscription.getProduct_id());
	    	
	    	//  28-03-2019 for qty not greater than stock
		    double stock =Double.parseDouble(product.getStock());
		    double reqstock = pharmacyDAO.getAllReqStockPharmacy(priscription.getProduct_id());
		    double requserstockstock = pharmacyDAO.getAllReqStockPharmacy(priscription.getProduct_id(),hdnphclientid,hdnispharmacy,loginInfo.getLoginsessionid());
		    double totalavailablestock = (stock - reqstock) + requserstockstock;
	    	
	    	
	    	String medicine_shedule= inventoryProductDAO.getMedicineShedule(product.getCatalogueid());
	    	String ex=product.getExpiry_date();
		    String expiry=DateTimeUtils.converToMMMYYYYforExp(ex);
		    
		    String color="#777";
		    /*if(medicine_shedule.equals("Regular")) {
		     color="#777";
		    } else if(medicine_shedule.equals("Narcotics")){
		     color="#e05d6f";
		    } else if(medicine_shedule.equals("H1")){
		     color="#e69522";
		    }*/
		    sr = sr+1;
		    count = priscription.getId();
		    int qty = priscription.getQty();
		    int chargesessionid = priscription.getChargesessionid();
		    int chargetempid = priscription.getId();
		    int freeqty=priscription.getFreeqty();
		    String discount_perproduct=priscription.getDiscount_perproduct();
			/*
			 * if(discount_perproduct>0) {
			 * disprice=priscription.getUnit_price()*discount_perproduct/100;
			 * //priscription.setUnit_price(disprice); System.out.println(disprice); }
			 */
		    
		    if(medicine_shedule.equals("Regular")) {
		    	 buffer.append("<tr>");
		    } else if(medicine_shedule.equals("Narcotics")){
		    	 buffer.append("<tr style='background-color: #e05d6f;'>");
		    } else if(medicine_shedule.equals("H1")){
		    	 buffer.append("<tr style='background-color: #e69522;'>");
		    }else if(medicine_shedule.equals("High Risk Medicine")){
		    	 buffer.append("<tr style='background-color: #9381cc;'>");
		    }else if(medicine_shedule.equals("Vaccination")){
		    	 buffer.append("<tr style='background-color: #e0acdafc;'>");
		    }
		    buffer.append("<td class='text-center'>"+sr+"</td>");
		    buffer.append("<td style='color:"+color+" '>"+product.getProduct_name()+" <input type='hidden' class='pclass' value='"+count+"' /> <input type='hidden' name='id"+chargetempid+"' value='"+product.getId()+"' /> <input type='hidden' name='product_id"+chargetempid+"' id='product_id"+count+"' value='"+priscription.getProduct_id()+"' /> </td>");
		    buffer.append("<td class='text-center' id='labelreq"+count+"'>"+qty+"</td> <input type='hidden' name='reqqty"+chargetempid+"' value='"+qty+"' id='req"+count+"' />");
		    
		    buffer.append("<input type='hidden' id='pur_price"+count+"' value='"+product.getPurchase_price()+"' /> <input type='hidden' value='"+product.getPack()+"' id='pack"+count+"'/> ");
		    /*buffer.append("<td class='text-center'>"+product.getStock()+" </td>");*/
		    /*buffer.append("<td class='text-center'>"+priscription.getAvailablestock()+" </td>");*/
		    buffer.append("<td class='text-center'>"+totalavailablestock+" </td>");
		    buffer.append("<td class='text-center'>"+product.getBatch_no()+" </td>");
		    buffer.append("<td class='text-center'>"+expiry+" </td>");
		    buffer.append("<td class='text-center'><input   type='text' name='saleqty"+chargetempid+"' onchange='changeQtyFromSale("+count+","+chargesessionid+","+chargetempid+")' id='sale"+count+"' value='"+qty+"' class='form-control text-center' style='height: 20px !important;background-color: rgba(255, 225, 225, 0.59);'></td>");
		    if(loginInfo.isInfinyte()) {
			    buffer.append("<td class='text-center'><input   type='text' name='freeqty"+chargetempid+"' onchange='changeQtyFromSale("+count+","+chargesessionid+","+chargetempid+")' id='freeqty"+count+"' value='"+freeqty+"' class='form-control text-center' style='height: 20px !important;background-color: rgba(255, 225, 225, 0.59);'></td>");
			    buffer.append("<td class='text-center'><input   type='text' name='discount_perproduct"+chargetempid+"' onchange='changeQtyFromSale1("+count+","+chargesessionid+","+chargetempid+","+priscription.getUnit_price()+")' id='discount"+count+"'  value='"+discount_perproduct+"' class='form-control text-center' style='height: 20px !important;background-color: rgba(255, 225, 225, 0.59);'></td>");

             }else {
 			    buffer.append("<td class='text-center hidden'><input   type='text' name='freeqty"+chargetempid+"' onchange='changeQtyFromSale("+count+","+chargesessionid+","+chargetempid+")' id='freeqty"+count+"' value='"+freeqty+"' class='form-control text-center' style='height: 20px !important;background-color: rgba(255, 225, 225, 0.59);'></td>");
 			    buffer.append("<td class='text-center hidden' ><input   type='text' name='discount_perproduct"+chargetempid+"' onchange='changeQtyFromSale1("+count+","+chargesessionid+","+chargetempid+","+priscription.getUnit_price()+")' id='discount"+count+"'  value='"+discount_perproduct+"' class='form-control text-center' style='height: 20px !important;background-color: rgba(255, 225, 225, 0.59);'></td>");

             }
		    //buffer.append("<td class='text-center'><input   type='text' name='discount_perproduct"+chargetempid+"' onchange='changeQtyFromSale1("+count+","+chargesessionid+","+chargetempid+","+priscription.getUnit_price()+")' id='discount"+count+"'  value='"+discount_perproduct+"' class='form-control text-center' style='height: 20px !important;background-color: rgba(255, 225, 225, 0.59);'></td>");

		    if(loginInfo.getSuperadminid()==1 || loginInfo.isPprice_sale()){
		    	double p_price=Math.round(product.getIndividual_pur_price()*100.0)/100.0;
		    	buffer.append("<td class='text-center'>"+p_price+" </td>");
		    }
		    
		    if(loginInfo.isPurchase_edit_pharmacy()){
		    	buffer.append("<td class='text-center'><input type='number' value='"+priscription.getUnit_price()+"' name='sale_price"+chargetempid+"' onchange='changePriceFromSale("+count+","+chargesessionid+","+chargetempid+",this.value)' id='mrp"+count+"' class='form-control' style='height: 20px !important;text-align: right;' /> <input type='hidden' value='"+priscription.getUnit_price()+"' id='sale_price"+count+"' /> </td>");
		    }else{
		    	buffer.append("<td class='text-center'><input type='number' value='"+priscription.getUnit_price()+"' name='sale_price"+chargetempid+"' onchange='changePriceFromSale("+count+","+chargesessionid+","+chargetempid+",this.value)' id='mrp"+count+"' class='form-control' style='height: 20px !important;text-align: right;' readonly/> <input type='hidden' value='"+priscription.getUnit_price()+"' id='sale_price"+count+"' /> </td>");
		    }
		    buffer.append("<td style='text-align: center;text-transform: uppercase;'>"+DateTimeUtils.isNull(product.getShelf())+" <input type='hidden' name='vat"+chargetempid+"' id='vat"+count+"' value='"+product.getVat()+"'> </td>");
		    buffer.append("<td style='text-align: center;text-transform: uppercase;'>"+DateTimeUtils.isNull(product.getSecondary_shelf())+"</td>");
		    buffer.append("<td class='text-right'>Rs.<label id='totalmrp"+count+"'>00.00</label></td>");
		   /* buffer.append("<input type hidden id='prodd"+count+"' value='"+product.getStock()+"'/>");*/
		    /*buffer.append("<input type hidden id='prodd"+count+"' value='"+priscription.getAvailablestock()+"'/>");*/
		    buffer.append("<input type hidden id='prodd"+count+"' value='"+totalavailablestock+"'/>");
		    
		    //min check with stock @jitu
		    Product pmaster= inventoryProductDAO.getProductCatalogueDetailsforsale(product.getCatalogueid(),location);
		    int allstock= inventoryProductDAO.getTotalStockProductforsale(product.getCatalogueid(),location); 
		    int pack= Integer.parseInt(pmaster.getPack());
		    if(pack==0){
		    	pack=1;
		    }
		    int nowstock=allstock/pack ;
		    UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
		    UserProfile userProfile= userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
		    int medlimit=inventoryProductDAO.getMedLimit(userProfile.getUserid());
		    if(nowstock<medlimit){
		    	/*buffer.append("<td class='text-center'><a href='#' onclick=deleteRow(this)><i class='fa fa-minus-circle' style='color:#c50404;font-size: 17px;padding-top: 2px;' aria-hidden='true'></i></a></td><td class='text-center'><a href='#' onclick='requestStock("+pid+","+count+")' title='Request Stock'><i class='fa fa-cart-plus' aria-hidden='true' style='font-size: 17px;padding-top: 2px;'></i></a></td>");*/
		    	buffer.append("<td class='text-center'><a href='#' onclick=deleteRowFromSession(this,"+chargesessionid+","+count+","+chargetempid+")><i class='fa fa-minus-circle' style='color:#c50404;font-size: 17px;padding-top: 2px;' aria-hidden='true'></i></a></td><td class='text-center'><a href='#' onclick='requestStock("+priscription.getProduct_id()+","+count+")' title='Request Stock'><i class='fa fa-cart-plus' aria-hidden='true' style='font-size: 17px;padding-top: 2px;'></i></a></td>");
		    } else {
		    	/*buffer.append("<td class='text-center' id='tdbutton"+count+"'><a href='#' onclick=deleteRow(this)><i class='fa fa-minus-circle' style='color:#c50404;font-size: 17px;padding-top: 2px;' aria-hidden='true'></i></a></td>");*/
		    	buffer.append("<td class='text-center' id='tdbutton"+count+"'><a href='#' onclick=deleteRowFromSession(this,"+chargesessionid+","+count+","+chargetempid+")><i class='fa fa-minus-circle' style='color:#c50404;font-size: 17px;padding-top: 2px;' aria-hidden='true'></i></a></td>");
		    }
		    buffer.append("<input type='hidden' id='tmedicineid"+count+"' name='mdicinenameid"+chargetempid+"' value='"+product.getMedicinenameid()+"' />");
		    buffer.append("</tr>");
		}
	   	if(type==0){
	   		JSONObject jsonobj = new JSONObject();
			jsonobj.put("productlist", buffer.toString());
			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);
	   	}else{
	   		response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+buffer.toString()+"");
	   	}
	    
	} catch (Exception e) {
		try {
			if(type==0){
		   		JSONObject jsonobj = new JSONObject();
				jsonobj.put("productlist", "");
				String response1 = jsonobj.toString();
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(response1);
		   	}else{
		   		response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("");
		   	}
		} catch (Exception e2) {
			// TODO: handle exception
		}
		e.printStackTrace();
	}
}

public String changeproductfromsession() throws Exception {
	if (!verifyLogin(request)) {

		return "login";
	}
  	Connection connection=null; 
  	try {
  		connection=Connection_provider.getconnection();
  		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
  		String chargesessionid = request.getParameter("chargesessionid");
  		String saleqty = request.getParameter("saleqty");
  		String chargetempid= request.getParameter("chargetempid");
  		String hdnphclientid = request.getParameter("hdnphclientid");
  		String hdnispharmacy= request.getParameter("hdnispharmacy");
  		String freeqty=request.getParameter("freeqty");
  		String discount_perproduct=request.getParameter("discount_perprodt");
  		int res = pharmacyDAO.updateProductFromSession(chargesessionid,saleqty,freeqty,discount_perproduct);
  		int res1 = pharmacyDAO.updateProductFromTemp(chargetempid,saleqty,freeqty,discount_perproduct);
  		setSaleTempdata(Integer.parseInt(hdnphclientid),Integer.parseInt(hdnispharmacy),1);
  		/*response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("");*/
			
		} catch (Exception e) {

			e.printStackTrace();
		}
  	finally {
  	
  		 connection.close();
  	}
  	
  	return null;
   }

public String deleteproductfromsession() throws Exception {
	if (!verifyLogin(request)) {

		return "login";
	}
  	Connection connection=null; 
  	try {
  		connection=Connection_provider.getconnection();
  		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
  		String chargesessionid = request.getParameter("chargesessionid");
  		String chargetempid = request.getParameter("chargetempid");
  		
  		String hdnphclientid = request.getParameter("hdnphclientid");
  		String hdnispharmacy= request.getParameter("hdnispharmacy");
  		
  		int res = pharmacyDAO.deleteProductFromSession(chargesessionid);
  		int res1 = pharmacyDAO.deleteProductFromTemp(chargetempid);
  		
  		setSaleTempdata(Integer.parseInt(hdnphclientid),Integer.parseInt(hdnispharmacy),1);
  		/*response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("");*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
  	finally {
  	
  		 connection.close();
  	}
  	
  	return null;
   }

public String checkpatientintemp() throws Exception{
	if (!verifyLogin(request)) {

		return "login";
	}
	Connection connection = null;
	try {
		String location=(String) session.getAttribute("location");
		if(location==null){
			location="0";
		}
		connection = Connection_provider.getconnection();
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		String hdnphclientid = jsonObject.getString("hdnphclientid");
	    String hdnispharmacy = jsonObject.getString("hdnispharmacy");
	    boolean checkpatientintemp = pharmacyDAO.checkPatientInTempSaleData(loginInfo.getLoginsessionid(),hdnphclientid,Integer.parseInt(hdnispharmacy));
		int res =0;
	    if(checkpatientintemp){
	    	res =1;
		}
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("productlist", ""+res);
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} catch (Exception e) {
		e.printStackTrace();
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("productlist", "0");
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} finally {
		connection.close();
	}
	return null;
}



public String addtempreturndata() throws Exception{
	if (!verifyLogin(request)) {

		return "login";
	}
	Connection connection = null;
	try {
		String location=(String) session.getAttribute("location");
		if(location==null){
			location="0";
		}
		connection = Connection_provider.getconnection();
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		String hdnphclientid = jsonObject.getString("hdnphclientid");
	    String hdnispharmacy = jsonObject.getString("hdnispharmacy");
	    setReturnTempdata(Integer.parseInt(hdnphclientid),Integer.parseInt(hdnispharmacy),0);
	} catch (Exception e) {
		e.printStackTrace();
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("productlist", "0");
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} finally {
		connection.close();
	}
	return null;
}

public void setReturnTempdata(int hdnphclientid, int hdnispharmacy, int type){
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		String location=(String) session.getAttribute("location");
		if(location==null){
			location="0";
		}
	 	int count=0;
	    int sr=0;
	    Vector<Priscription> tempmedicinelist = pharmacyDAO.getReturnTempMedicineList(hdnphclientid,hdnispharmacy,loginInfo.getLoginsessionid());
	    StringBuffer buffer=new StringBuffer();
	    for (Priscription priscription1 : tempmedicinelist) {
	    	  Priscription priscription= pharmacyDAO.getMedicineChargesbyid(Integer.parseInt(priscription1.getChargeid()),0);
	          Product product=inventoryProductDAO.getProduct(priscription.getProduct_id());
	          String medicine_shedule= inventoryProductDAO.getMedicineShedule(product.getCatalogueid());
	    	  String ex=product.getExpiry_date();
	          String expiry=DateTimeUtils.converToMMMYYYYforExp(ex);
	          count = Integer.parseInt(priscription1.getChargeid());
	          if(medicine_shedule.equals("Regular")) {
			    	 buffer.append("<tr>");
			  } else if(medicine_shedule.equals("Narcotics")){
			    	 buffer.append("<tr style='background-color: #e05d6f;'>");
			  } else if(medicine_shedule.equals("H1")){
			    	 buffer.append("<tr style='background-color: #e69522;'>");
			  }else if(medicine_shedule.equals("High Risk Medicine")){
			    	 buffer.append("<tr style='background-color: #9381cc;'>");
			  }else if(medicine_shedule.equals("Vaccination")){
			    	 buffer.append("<tr style='background-color: #e0acdafc;'>");
			  }
	          String color="#777";
	          /*if(medicine_shedule.equals("Regular")) {
	            color="#777";
	          } else if(medicine_shedule.equals("Narcotics")){
	            color="#e05d6f";
	          } else if(medicine_shedule.equals("H1")){
	            color="#e69522";
	          }*/
	          int returnqty =priscription.getSaleqty() - priscription.getReturnqty(); 
	          sr=sr+1;
	          buffer.append("<td>"+sr+"</td>");
	          buffer.append("<td style=color:"+color+" '>"+product.getProduct_name()+" <input type='hidden' class='pclass' value='"+count+"' /> <input type='hidden' name='id"+count+"' id='id"+count+"'  value='"+priscription1.getChargeid()+"' /> <input type='hidden' name='product_id"+count+"' id='product_id"+count+"' value='"+product.getId()+"' /> </td>");
	          buffer.append("<td id='labelreq"+count+"'>"+priscription.getSaleqty()+"</td> <input type='hidden' name='tempsale"+count+"' value='"+returnqty+"' id='tempsale"+count+"' />");
	         /* buffer.append("<td> "+product.getHsnno()+" </td>");*/
	          /*buffer.append("<td>"+returnqty+"/"+product.getBatch_no()+"/"+expiry+" <input type='hidden' id='pur_price"+count+"' value='"+product.getPurchase_price()+"' /> <input type='hidden' value='"+product.getPack()+"' id='pack"+count+"'/> <input type='hidden' name='medicines["+count+"].billno' id='billno"+count+"' value='"+priscription.getBillno()+"' /> </td>");*/
	          buffer.append("<input type='hidden' id='pur_price"+count+"' value='"+product.getPurchase_price()+"' /> <input type='hidden' value='"+product.getPack()+"' id='pack"+count+"'/> <input type='hidden' name='billno"+count+"' id='billno"+count+"' value='"+priscription.getBillno()+"' /> ");
	          
	          buffer.append("<td>"+returnqty+"</td>");
	          buffer.append("<td>"+product.getBatch_no()+"</td>");
	          buffer.append("<td>"+expiry+"</td>");
	          buffer.append("<td><input type='number' name='returnqty"+count+"' onchange='changeQtyFromReturn("+priscription1.getId()+","+priscription1.getChargeid()+")' id='returnqty"+count+"' value='"+priscription1.getQty()+"' class='form-control' style='height: 20px !important;background-color: rgba(255, 225, 225, 0.59);'></td>");
	         /* if(loginInfo.isPurchase_edit_pharmacy()){
	        	  buffer.append("<td><input type='number' value='"+priscription.getSale_price()+"' name='sale_price' onchange='calRefundTotaltemp()' id='mrp"+count+"' class='form-control' style='height: 20px !important;text-align: right;background-color: rgba(255, 225, 225, 0.59);' /> <input type='hidden' value='"+priscription.getSale_price()+"' id='sale_price"+count+"' /> </td>");
	          }else{
	        	  buffer.append("<td><input type='number' value='"+priscription.getSale_price()+"' name='sale_price' onchange='calRefundTotaltemp()' id='mrp"+count+"' class='form-control' style='height: 20px !important;text-align: right;background-color: rgba(255, 225, 225, 0.59);' readonly/> <input type='hidden' value='"+priscription.getSale_price()+"' id='sale_price"+count+"' /> </td>");  
	          }*/
	          buffer.append("<td><input type='number' value='"+priscription.getSale_price()+"' name='sale_price"+count+"' onchange='calRefundTotaltemp()' id='mrp"+count+"' class='form-control' style='height: 20px !important;text-align: right;background-color: rgba(255, 225, 225, 0.59);' readonly/> <input type='hidden' value='"+priscription.getSale_price()+"' id='sale_price"+count+"' /> </td>");
	          buffer.append("<td style='text-align: center;text-transform: uppercase;'>"+DateTimeUtils.isNull(product.getShelf())+" <input type='hidden' name='vat"+count+"' id='vat"+count+"' value='"+product.getVat()+"'> </td>");
	          buffer.append("<td style='text-align: center;text-transform: uppercase;'>"+DateTimeUtils.isNull(product.getSecondary_shelf())+"</td>");
	          buffer.append("<td  class='text-right'>Rs.<label id='totalmrp"+count+"'>00.00</label> ");
	          buffer.append("<input type='hidden' id='prodd"+count+"' value='"+product.getStock()+"'/>");
	          buffer.append("<input type='hidden' id='discper"+count+"' name='indidiscount"+count+"' value='"+priscription.getDiscount()+"'/>");
	          buffer.append("</td>");
	          double stock=Double.parseDouble(product.getStock());
	          if(stock>100.0){
	        	  buffer.append("<td class='text-center' id='tdbutton"+count+"'><a href='#' onclick=deleteReturnRowFromSession("+priscription1.getId()+","+priscription1.getChargeid()+")><i class='fa fa-minus-circle' style='color:#c50404;font-size: 17px;padding-top: 2px;' aria-hidden='true'></i></a></td>");
	          } else {
	        	  buffer.append("<td class='text-center'><a href='#' onclick=deleteReturnRowFromSession("+priscription1.getId()+","+priscription1.getChargeid()+")><i class='fa fa-minus-circle' style='color:#c50404;font-size: 17px;padding-top: 2px;' aria-hidden='true'></i></a></td><td class='text-center'><a href='#' onclick='requestStock("+product.getId()+","+count+")' title='Request Stock'><i class='fa fa-cart-plus' aria-hidden='true' style='font-size: 17px;padding-top: 2px;'></i></a></td>");
	          }
	          buffer.append("<input type='hidden' id='totalrefundrs"+count+"' name='totalrefundrs"+count+"'  />");
	          buffer.append("<input type='hidden' id='tmedicineid"+count+"' name='mdicinenameid"+count+"' value='"+product.getMedicinenameid()+"' />");
	          buffer.append("</tr>");
	    }	
	   	if(type==0){
	   		JSONObject jsonobj = new JSONObject();
			jsonobj.put("productlist", buffer.toString());
			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);
	   	}else{
	   		response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+buffer.toString()+"");
	   	}
	    
	} catch (Exception e) {
		try {
			if(type==0){
		   		JSONObject jsonobj = new JSONObject();
				jsonobj.put("productlist", "");
				String response1 = jsonobj.toString();
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(response1);
		   	}else{
		   		response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("");
		   	}
		} catch (Exception e2) {
			// TODO: handle exception
		}
		e.printStackTrace();
	}
}

public String changeproductretrunfromsession() throws Exception {
	if (!verifyLogin(request)) {

		return "login";
	}
  	Connection connection=null; 
  	try {
  		connection=Connection_provider.getconnection();
  		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
  		String tempreturnid = request.getParameter("tempreturnid");
  		String returnqty = request.getParameter("returnqty");
  		String chargeid= request.getParameter("chargeid");
  		String hdnphclientid = request.getParameter("hdnphclientid");
  		String hdnispharmacy= request.getParameter("hdnispharmacy");
  		int res1 = pharmacyDAO.updateProductFromReturnTemp(tempreturnid,returnqty);
  		setReturnTempdata(Integer.parseInt(hdnphclientid),Integer.parseInt(hdnispharmacy),1);
  		/*response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("");*/
			
		} catch (Exception e) {

			e.printStackTrace();
		}
  	finally {
  	
  		 connection.close();
  	}
  	
  	return null;
   }

public String deletereturnproductfromsession() throws Exception {
	if (!verifyLogin(request)) {

		return "login";
	}
  	Connection connection=null; 
  	try {
  		connection=Connection_provider.getconnection();
  		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
  		String tempreturnid = request.getParameter("tempreturnid");
  		String chargeid = request.getParameter("chargeid");
  		
  		String hdnphclientid = request.getParameter("hdnphclientid");
  		String hdnispharmacy= request.getParameter("hdnispharmacy");
  		
  		int res1 = pharmacyDAO.deleteProductFromReturnTemp(tempreturnid);
  		
  		setReturnTempdata(Integer.parseInt(hdnphclientid),Integer.parseInt(hdnispharmacy),1);
  		/*response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("");*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
  	finally {
  	
  		 connection.close();
  	}
  	
  	return null;
   }

public String adnewretrunmedicine() throws Exception{
	if (!verifyLogin(request)) {

		return "login";
	}
	Connection connection = null;
	try {
		String location=(String) session.getAttribute("location");
		if(location==null){
			location="0";
		}
		connection = Connection_provider.getconnection();
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		String chargeid=jsonObject.getString("chargeid");
	    String tcount=jsonObject.getString("count");
	    String qty=jsonObject.getString("qty");
	    String hdnphclientid = jsonObject.getString("hdnphclientid");
	    String hdnispharmacy = jsonObject.getString("hdnispharmacy");
		
	    Priscription priscription= pharmacyDAO.getMedicineChargesbyid(Integer.parseInt(chargeid),0);
	    if(Integer.parseInt(qty)>(priscription.getSaleqty()-priscription.getReturnqty())){
    		JSONObject jsonobj = new JSONObject();
 	    	jsonobj.put("productlist", "1");
 			String response1 = jsonobj.toString();
 			response.setContentType("application/json");
 			response.setHeader("Cache-Control", "no-cache");
 			response.getWriter().write(response1);
    	 }else{
    		 boolean flag = pharmacyDAO.checkReturnProductInTemp(chargeid, hdnphclientid, hdnispharmacy, loginInfo.getLoginsessionid());
    		    if(flag){
    		    	JSONObject jsonobj = new JSONObject();
    		    	jsonobj.put("productlist", "0");
    				String response1 = jsonobj.toString();
    				response.setContentType("application/json");
    				response.setHeader("Cache-Control", "no-cache");
    				response.getWriter().write(response1);
    		    }else{
    	    		String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
    	 	    	int res = pharmacyDAO.insertIntoPharReturnTemp(chargeid, hdnphclientid, hdnispharmacy, loginInfo.getLoginsessionid(), datetime, qty);
    	 	    	JSONObject jsonobj = new JSONObject();
    	 	    	jsonobj.put("productlist", "2");
    	 			String response1 = jsonobj.toString();
    	 			response.setContentType("application/json");
    	 			response.setHeader("Cache-Control", "no-cache");
    	 			response.getWriter().write(response1);
    		    }
    	 }
	    
	    
	} catch (Exception e) {
		e.printStackTrace();
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("productlist", "");
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} finally {
		connection.close();
	}
	return null;
}
public String dailysalereport()throws Exception{
	if (!verifyLogin(request)) {

		return "login";
	}
	  Connection connection=null;
	     try {
		 	/*ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule= false;
			int modulecount =0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if(breadcrumbs.getName().equals("Daily Sale Report New")){
					isavilablemodule =true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				}else{
					indentflowlist.add(breadcrumbs);
				}
			}
			if(!isavilablemodule){
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Daily Sale Report New");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("dailysalereportPharmacyAjax");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Daily Sale Report New");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist); */
	    	 
	    
	      connection=Connection_provider.getconnection();
	      PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
	      UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection); 
	      String fromdate=emrForm.getFromdate();
	      String todate=emrForm.getTodate();
	     
	      
	      String location =(String) session.getAttribute("location");
	      if(location==null){
	       location="0";
	      }
	      if(fromdate!=null){
	       if(fromdate.equals("")){
	         Calendar calendar=Calendar.getInstance();
	         SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	         fromdate=dateFormat.format(calendar.getTime());
	       } else {
	        
	        fromdate=DateTimeUtils.getCommencingDate1(fromdate);
	        
	       }
	      }else {
	        Calendar calendar=Calendar.getInstance();
	        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	        fromdate=dateFormat.format(calendar.getTime());
	      }
	      
	      if(todate!=null){
	       if(todate.equals("")){
	         Calendar calendar=Calendar.getInstance();
	         SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	         todate=dateFormat.format(calendar.getTime());
	       }
	       else {
	        todate=DateTimeUtils.getCommencingDate1(todate);
	       }
	      }else {
	        Calendar calendar=Calendar.getInstance();
	        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	        todate=dateFormat.format(calendar.getTime());
	      }
	      Priscription priscription=new Priscription();
	      session.setAttribute("report", priscription);
	    
	      int isreturn= 0;
	    
	  	  //cash
	      Priscription salecashprisc = pharmacyDAO.getPharmacySaleAndDiscount(fromdate,todate,"Cash",isreturn,location);
	      emrForm.setSalecash(salecashprisc.getActualtemptot());
	      emrForm.setDiscountsalecash(salecashprisc.getDiscount());
	      emrForm.setNetsalecash(salecashprisc.getDebit());
	      
	      //card 
	      Priscription salecardprisc = pharmacyDAO.getPharmacySaleAndDiscount(fromdate,todate,"Card",isreturn,location);
	      emrForm.setSalecard(salecardprisc.getActualtemptot());
	      emrForm.setDiscountsalecard(salecardprisc.getDiscount());
	      emrForm.setNetsalecard(salecardprisc.getDebit());
	      
	      //cheque 
	      Priscription salechequeprisc = pharmacyDAO.getPharmacySaleAndDiscount(fromdate,todate,"Cheque",isreturn,location);
	      emrForm.setSalecheque(salechequeprisc.getActualtemptot());
	      emrForm.setDiscountsalecheque(salechequeprisc.getDiscount());
	      emrForm.setNetsalecheque(salechequeprisc.getDebit());
	      
	      //neft 
	      Priscription saleneftprisc = pharmacyDAO.getPharmacySaleAndDiscount(fromdate,todate,"NEFT",isreturn,location);
	      emrForm.setSaleneft(saleneftprisc.getActualtemptot());
	      emrForm.setDiscountsaleneft(saleneftprisc.getDiscount());
	      emrForm.setNetsaleneft(saleneftprisc.getDebit());
	      
	      //credit 
	      Priscription salecreditprisc = pharmacyDAO.getPharmacySaleAndDiscount(fromdate,todate,"Credit",isreturn,location);
	      emrForm.setSalecredit(salecreditprisc.getActualtemptot());
	      emrForm.setDiscountsalecredit(salecreditprisc.getDiscount());
	      emrForm.setNetsalecredit(salecreditprisc.getDebit());
	      
	      //hospital 
	      Priscription salehospitalprisc = pharmacyDAO.getPharmacySaleAndDiscount(fromdate,todate,"Hospital",isreturn,location);
	      emrForm.setSalehospital(salehospitalprisc.getActualtemptot());
	      emrForm.setDiscountsalehospital(salehospitalprisc.getDiscount());
	      emrForm.setNetsalehospital(salehospitalprisc.getDebit());
	      
	      //online Payment
	      Priscription saleonlinepaymentprisc = pharmacyDAO.getPharmacySaleAndDiscount(fromdate,todate,"Online Payment",isreturn,location);
	      emrForm.setSaleonlinepayment(saleonlinepaymentprisc.getActualtemptot());
	      emrForm.setDiscountsaleonlinepayment(saleonlinepaymentprisc.getDiscount());
	      emrForm.setNetsaleonlinepayment(saleonlinepaymentprisc.getDebit());
	      
	      //total of sale 
	      Priscription totalsaleprisc = pharmacyDAO.getPharmacySaleAndDiscount(fromdate,todate,null,isreturn,location);
	      emrForm.setSaletotal(totalsaleprisc.getActualtemptot());
	      emrForm.setDiscountsaletotal(totalsaleprisc.getDiscount());
	      emrForm.setNetsaletotal(totalsaleprisc.getDebit());
	      
	      double cashpayments = pharmacyDAO.getPaidAmt(fromdate, todate, "Cash", location,0,0);
	      double cardpayments = pharmacyDAO.getPaidAmt(fromdate, todate, "Card", location,0,0);
	      double chequepayments = pharmacyDAO.getPaidAmt(fromdate, todate, "Cheque", location,0,0);
	      double neftpayments = pharmacyDAO.getPaidAmt(fromdate, todate, "NEFT", location,0,0);
	      double creditpayments = pharmacyDAO.getPaidAmt(fromdate, todate, "Credit", location,0,0);
	      double hospitalpayments = pharmacyDAO.getPaidAmt(fromdate, todate, "Hospital", location,0,0);
	      double totalpayments = pharmacyDAO.getPaidAmt(fromdate, todate, null, location,0,0);
	      double onlinepaymentpayments = pharmacyDAO.getPaidAmt(fromdate, todate, "Online Payment", location,0,0);
	      
	      emrForm.setCashpayments(cashpayments);
	      emrForm.setCardpayments(cardpayments);
	      emrForm.setChequepayments(chequepayments);
	      emrForm.setNeftpayments(neftpayments);
	      emrForm.setCreditpayments(creditpayments);
	      emrForm.setHospitalpayments(hospitalpayments);
	      emrForm.setTotalpayments(totalpayments);
	      emrForm.setOnlinepaymentpayments(onlinepaymentpayments);
	      
	      double outcashpayments = pharmacyDAO.getPaidAmt(fromdate, todate, "Cash", location,1,0);
	      double outcardpayments = pharmacyDAO.getPaidAmt(fromdate, todate, "Card", location,1,0);
	      double outchequepayments = pharmacyDAO.getPaidAmt(fromdate, todate, "Cheque", location,1,0);
	      double outneftpayments = pharmacyDAO.getPaidAmt(fromdate, todate, "NEFT", location,1,0);
	      double outcreditpayments = pharmacyDAO.getPaidAmt(fromdate, todate, "Credit", location,1,0);
	      double outhospitalpayments = pharmacyDAO.getPaidAmt(fromdate, todate, "Hospital", location,1,0);
	      double outtotalpayments = pharmacyDAO.getPaidAmt(fromdate, todate, null, location,1,0);
	      double outonlinepaymentpayments = pharmacyDAO.getPaidAmt(fromdate, todate, "Online Payment", location,1,0);
	      
	      double cashpaymentsonsamedate = pharmacyDAO.getPaidAmt(fromdate, todate, "Cash", location,0,1);
	      double cardpaymentsonsamedate = pharmacyDAO.getPaidAmt(fromdate, todate, "Card", location,0,1);
	      double chequepaymentsonsamedate = pharmacyDAO.getPaidAmt(fromdate, todate, "Cheque", location,0,1);
	      double neftpaymentsonsamedate = pharmacyDAO.getPaidAmt(fromdate, todate, "NEFT", location,0,1);
	      double creditpaymentsonsamedate = pharmacyDAO.getPaidAmt(fromdate, todate, "Credit", location,0,1);
	      double hospitalpaymentsonsamedate = pharmacyDAO.getPaidAmt(fromdate, todate, "Hospital", location,0,1);
	      double totalpaymentsonsamedate = pharmacyDAO.getPaidAmt(fromdate, todate, null, location,0,1);
	      double onlinepaymentsonsamedate = pharmacyDAO.getPaidAmt(fromdate, todate, "Online Payment", location,0,1);
	      
	      emrForm.setOutcashpayments(Math.round((outcashpayments+cashpaymentsonsamedate) * 100.0)/100.0);
	      emrForm.setOutcardpayments(Math.round((outcardpayments + cardpaymentsonsamedate) * 100.0)/100.0);
	      emrForm.setOutchequepayments(Math.round((outchequepayments + chequepaymentsonsamedate) * 100.0)/100.0);
	      emrForm.setOutneftpayments(Math.round((outneftpayments + neftpaymentsonsamedate) * 100.0)/100.0);
	      emrForm.setOutcreditpayments(Math.round((outcreditpayments + creditpaymentsonsamedate) * 100.0)/100.0);
	      emrForm.setOuthospitalpayments(Math.round((outhospitalpayments + hospitalpaymentsonsamedate) * 100.0)/100.0);
	      emrForm.setOuttotalpayments(Math.round((outtotalpayments + totalpaymentsonsamedate) * 100.0)/100.0);
	      emrForm.setOutonlinepaymentpayments(Math.round((outonlinepaymentpayments+onlinepaymentsonsamedate) * 100.0)/100.0);
	      
	      double totalcashpayments = cashpayments + outcashpayments + cashpaymentsonsamedate;
	      double totalcardpayments = cardpayments + outcardpayments + cardpaymentsonsamedate;
	      double totalchequepayments = chequepayments + outchequepayments + chequepaymentsonsamedate;
	      double totalneftpayments = neftpayments + outneftpayments + neftpaymentsonsamedate;
	      double totalcreditpayments = creditpayments + outcreditpayments + creditpaymentsonsamedate;
	      double totalhospitalpayments = hospitalpayments + outhospitalpayments + hospitalpaymentsonsamedate;
	      double totaltotalpayments = totalpayments + outtotalpayments + totalpaymentsonsamedate;
	      double totalonlinepaymentpayments = onlinepaymentpayments + outonlinepaymentpayments + onlinepaymentsonsamedate;
	      
	      emrForm.setTotalcashpayments(Math.round(totalcashpayments*100.0)/100.0);
	      emrForm.setTotalcardpayments(Math.round(totalcardpayments*100.0)/100.0);
	      emrForm.setTotalchequepayments(Math.round(totalchequepayments*100.0)/100.0);
	      emrForm.setTotalneftpayments(Math.round(totalneftpayments*100.0)/100.0);
	      emrForm.setTotalcreditpayments(Math.round(totalcreditpayments*100.0)/100.0);
	      emrForm.setTotalhospitalpayments(Math.round(totalhospitalpayments*100.0)/100.0);
	      emrForm.setTotaltotalpayments(Math.round(totaltotalpayments*100.0)/100.0);
	      emrForm.setTotalonlinepaymentpayments(Math.round(totalonlinepaymentpayments*100.0)/100.0);
	      
	      isreturn = 1;
	      Priscription returncashprisc = pharmacyDAO.getPharmacySaleAndDiscount(fromdate,todate,"Cash",isreturn,location);
	      Priscription returncardprisc = pharmacyDAO.getPharmacySaleAndDiscount(fromdate,todate,"Card",isreturn,location);
	      Priscription returnchequeprisc = pharmacyDAO.getPharmacySaleAndDiscount(fromdate,todate,"Cheque",isreturn,location);
	      Priscription returnneftprisc = pharmacyDAO.getPharmacySaleAndDiscount(fromdate,todate,"NEFT",isreturn,location);
	      Priscription returncreditprisc = pharmacyDAO.getPharmacySaleAndDiscount(fromdate,todate,"Credit",isreturn,location);
	      Priscription returnhospitalprisc = pharmacyDAO.getPharmacySaleAndDiscount(fromdate,todate,"Hospital",isreturn,location);
	      Priscription totalreturnprisc = pharmacyDAO.getPharmacySaleAndDiscount(fromdate,todate,null,isreturn,location);
	      Priscription returnonlinepaymentprisc = pharmacyDAO.getPharmacySaleAndDiscount(fromdate,todate,"Online Payment",isreturn,location);
	      
	      emrForm.setReturncash(returncashprisc.getDebit());
	      emrForm.setReturncard(returncardprisc.getDebit());
	      emrForm.setReturncheque(returnchequeprisc.getDebit());
	      emrForm.setReturnneft(returnneftprisc.getDebit());
	      emrForm.setReturncredit(returncreditprisc.getDebit());
	      emrForm.setReturnhospital(returnhospitalprisc.getDebit());
	      emrForm.setReturntotal(totalreturnprisc.getDebit());
	      emrForm.setReturnonlinepayment(returnonlinepaymentprisc.getDebit());
	      
	      
	      double netcashcollection=0,netcardcollection=0,netchequecollection=0,
	    		  netneftcollection=0,netcreditcollection=0,nethospitalcollection=0,
	    		  nettotalcollection=0,netonlinepaymentcollection=0;
	      netcashcollection = totalcashpayments - returncashprisc.getDebit();
	      netcardcollection = totalcardpayments - returncardprisc.getDebit();
	      netchequecollection = totalchequepayments - returnchequeprisc.getDebit();
	      netneftcollection = totalneftpayments - returnneftprisc.getDebit();
	      netcreditcollection = totalcreditpayments - returncreditprisc.getDebit();
	      nethospitalcollection = totalhospitalpayments - returnhospitalprisc.getDebit();
	      nettotalcollection = totaltotalpayments - totalreturnprisc.getDebit();
	      netonlinepaymentcollection = totalonlinepaymentpayments - returnonlinepaymentprisc.getDebit();
	      
	      emrForm.setNetcashcollection(Math.round(netcashcollection*100.0)/100.0);
	      emrForm.setNetcardcollection(Math.round(netcardcollection*100.0)/100.0);
	      emrForm.setNetchequecollection(Math.round(netchequecollection*100.0)/100.0);
	      emrForm.setNetneftcollection(Math.round(netneftcollection*100.0)/100.0);
	      emrForm.setNetcreditcollection(Math.round(netcreditcollection*100.0)/100.0);
	      emrForm.setNethospitalcollection(Math.round(nethospitalcollection*100.0)/100.0);
	      emrForm.setNettotalcollection(Math.round(nettotalcollection*100.0)/100.0);
	      emrForm.setNetonlinepaymentcollection(Math.round(netonlinepaymentcollection*100.0)/100.0);
	      
	      Calendar calendar=Calendar.getInstance();
	      SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	      String date=dateFormat.format(calendar.getTime());
	      emrForm.setDateTime(date);
	       
	      fromdate =DateTimeUtils.getCommencingDate1(fromdate);
	      todate =DateTimeUtils.getCommencingDate1(todate);
	      emrForm.setFromdate(fromdate);
	      emrForm.setTodate(todate);
	      
	      UserProfile userProfile= userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
	      emrForm.setClinicName(userProfile.getClinicname());
	      emrForm.setClinicaddress(userProfile.getAddress());
	      emrForm.setLandLine(userProfile.getPhone());
	      emrForm.setFullname(userProfile.getFullname());
	      emrForm.setEmail(userProfile.getEmail());
	      emrForm.setPlace(userProfile.getCity());
	      emrForm.setWebsiteUrl(userProfile.getWebsite());
	      emrForm.setLocation(pharmacyDAO.getLocationName(location));
	      emrForm.setUserid(loginInfo.getUserId());
	   
	     } catch (Exception e) {

	      e.printStackTrace();
	     }
	     finally {
	      connection.close();   
	     }
	return "dailysalereport";
}

public String checkdummybillnoexist() throws Exception {
	if (!verifyLogin(request)) {

		return "login";
	}
  	Connection connection=null; 
  	try {
  		connection=Connection_provider.getconnection();
  		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
  		String billno = request.getParameter("billno");
  		String dummybillno = request.getParameter("dummybillno");
  		String dummybilldate = request.getParameter("dummybilldate");
  		
  		int res = pharmacyDAO.checkDummyBillExist(billno,dummybillno);
  		
  		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+res+"");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
  	finally {
  	
  		 connection.close();
  	}
  	
  	return null;
   }

public String savedummybillno() throws Exception {
	if (!verifyLogin(request)) {

		return "login";
	}
  	Connection connection=null; 
  	try {
  		connection=Connection_provider.getconnection();
  		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
  		String billno = request.getParameter("billno");
  		String dummybillno = request.getParameter("dummybillno");
  		String dummybilldate = request.getParameter("dummybilldate");
  		dummybilldate = DateTimeUtils.getCommencingDate1(dummybilldate);
  		int res = pharmacyDAO.updateDummyBillNo(billno,dummybillno,dummybilldate);
  		if(res>0){
  			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
  			int res1= pharmacyDAO.saveDummyBillnoChange(billno,dummybillno,dummybilldate,datetime,loginInfo.getUserId());
  		}
  		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+res+"");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
  	finally {
  	
  		 connection.close();
  	}
  	
  	return null;
   }

public String priscagainstsalereport() throws Exception{
	if (!verifyLogin(request)) {
		return "login";
	}
	Connection connection=null; 
	try {
		
		/*ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
		ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
		if (session.getAttribute("indentflowlist") != null) {
			indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
		}
		boolean isavilablemodule= false;
		int modulecount =0;
		for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
			breadcrumbs.setIscurrent(false);
			breadcrumbs.setSqno(modulecount);
			modulecount++;
			if(breadcrumbs.getName().equals("Prescription Against Sale Report")){
				isavilablemodule =true;
				breadcrumbs.setIscurrent(true);
				indentflowlist.add(breadcrumbs);
				break;
			}else{
				indentflowlist.add(breadcrumbs);
			}
		}
		if(!isavilablemodule){
			Breadcrumbs breadcrumbs = new Breadcrumbs();
			breadcrumbs.setName("Prescription Against Sale Report");
			breadcrumbs.setOn(true);
			breadcrumbs.setSqno(modulecount);
			breadcrumbs.setUrllink("priscagainstsalereportPharmacyAjax");
			breadcrumbs.setIscurrent(true);
			breadcrumbs.setShowingname("Prescription Against Sale Report");
			indentflowlist.add(breadcrumbs);
		}
		session.setAttribute("indentflowlist",indentflowlist); 
		*/
		
		connection=Connection_provider.getconnection();
  		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
  		PrescriptionDAO prescriptionDAO = new JDBCPrescriptionDAO(connection);
  		String formdate = emrForm.getFromdate();
  		String todate = emrForm.getTodate();
  		
  		if (formdate == null) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			// cal.add(Calendar.DATE, -7);
			formdate = dateFormat.format(cal.getTime());
			formdate = DateTimeUtils.getCommencingDate1(formdate);
		} else {

			if (formdate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				// cal.add(Calendar.DATE, -7);
				formdate = dateFormat.format(cal.getTime());
				formdate = DateTimeUtils.getCommencingDate1(formdate);
				// fromdate = null;
			} else {
				formdate = DateTimeUtils.getCommencingDate1(formdate);
			}
		}

		if (todate == null) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			todate = dateFormat.format(cal.getTime());
			todate = DateTimeUtils.getCommencingDate1(todate);
		} else {
			if (todate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				todate = dateFormat.format(cal.getTime());
				todate = DateTimeUtils.getCommencingDate1(todate);
				// todate = null;
			} else {
				todate = DateTimeUtils.getCommencingDate1(todate);
			}
		}
		ArrayList<Priscription>  priscriptionlist = prescriptionDAO.getPriscriptionAgainstSale(formdate,todate);
		emrForm.setPriscriptionlist(priscriptionlist);
  		emrForm.setFromdate(DateTimeUtils.getCommencingDate1(formdate));
  		emrForm.setTodate(DateTimeUtils.getCommencingDate1(todate));
  	} catch (Exception e) {
		e.printStackTrace();
	}
	return "priscagainstsalereport";
}


public String changeproductfromsessionsaleprice() throws Exception {
	if (!verifyLogin(request)) {

		return "login";
	}
  	Connection connection=null; 
  	try {
  		connection=Connection_provider.getconnection();
  		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
  		String chargesessionid = request.getParameter("chargesessionid");
  		String sale_price = request.getParameter("sale_price");
  		String chargetempid= request.getParameter("chargetempid");
  		String hdnphclientid = request.getParameter("hdnphclientid");
  		String hdnispharmacy= request.getParameter("hdnispharmacy");
  		int res1 = pharmacyDAO.updateProductSalePriceFromTemp(chargetempid,sale_price);
  		setSaleTempdata(Integer.parseInt(hdnphclientid),Integer.parseInt(hdnispharmacy),1);
  		/*response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("");*/
			
		} catch (Exception e) {

			e.printStackTrace();
		}
  	finally {
  	
  		 connection.close();
  	}
  	
  	return null;
   }
public String getpharmdatafordiscount() throws Exception{
	if (!verifyLogin(request)) {
		return "login";
	}
	Connection connection = null;
	try {
		String location=(String) session.getAttribute("location");
		if(location==null){
			location="0";
		}
		connection = Connection_provider.getconnection();
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		String billno = jsonObject.getString("billno");
		Priscription priscription = pharmacyDAO.getPharmacyBillDetails(billno);
		String paymode = pharmacyDAO.getBillPaymodeNew(Integer.parseInt(billno));
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("debit", priscription.getDebit());
		jsonobj.put("clientid", priscription.getClientId());
		jsonobj.put("pclientid", priscription.getPclientid());
		jsonobj.put("billno", billno);
		jsonobj.put("paymode", paymode);
		jsonobj.put("balance", priscription.getCreditBalance());
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} catch (Exception e) {
		 e.printStackTrace();
	} finally {
		connection.close();
	}
	return null;
}

public String requestdiscount() throws Exception{
	if (!verifyLogin(request)) {
		return "login";
	}
	Connection connection = null;
	try {
		String location=(String) session.getAttribute("location");
		if(location==null){
			location="0";
		}
		connection = Connection_provider.getconnection();
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		String billno = jsonObject.getString("billno");
		String debit = jsonObject.getString("debit");
		String requestdisc_disctype = jsonObject.getString("requestdisc_disctype");
		String requestdisc_amt = jsonObject.getString("requestdisc_amt");
		String discinper = jsonObject.getString("discinper");
		String requestdisc_typee = jsonObject.getString("requestdisc_typee");
		String userid = loginInfo.getUserId();
		String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		if(requestdisc_typee.equals("0")){
			  CompleteAppointment appointment=pharmacyDAO.getBillDetails(Integer.parseInt(billno));
			  double requestdisc_amt1=0;
			  if(requestdisc_disctype.equals("0")){
				  requestdisc_amt1= (Double.parseDouble(requestdisc_amt)*appointment.getTotal())/100;
			  }else{
				  requestdisc_amt1 = Double.parseDouble(requestdisc_amt);
			  }
			  double totaldisc=Math.abs(requestdisc_amt1);
			  //double val = Math.round(requestdisc_amt1 * 100) / 100;
			 /* double totaldebit = Double.parseDouble(appointment.getGrosssubtotal())-totaldisc;*/
			  double totaldebit = appointment.getTotal()-totaldisc;
			  double grosstotal =  Math.round(totaldebit* 100.0) / 100.0;
			  double val = Math.round(totaldebit * 100) / 100;
			  int res = pharmacyDAO.updateBillDiscount(totaldisc,Integer.parseInt(billno),val);
			  ArrayList<Priscription> arrayList = pharmacyDAO.getMedicineChargeList(Integer.parseInt(billno));
  	  		  double vat =0;
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
			    	tempvat=tot*Double.parseDouble(discinper)/100;
			    	temptot = tot - tempvat;
			    	double dividevat= 100+tvat;
			    	double gst= temptot*tvat/dividevat;
			    	double half= gst/2;
			    	half = Math.round(half*100.0)/100.0;
			    	gst = Math.round(gst*100.0)/100.0;
			    	vat = vat +gst;
			    	int es = pharmacyDAO.updateMedicineChargeGST(priscription2.getId(),gst,half,tempvat,tvat);
			 }
			int ressss =pharmacyDAO.updateGrossTotalAndDisc(billno,grosstotal,requestdisc_amt,requestdisc_disctype);
			double temp = Double.parseDouble(appointment.getBalance())-totaldisc;
			temp = Math.round(temp*100)/100;
  	  		int d=pharmacyDAO.updateBillBalance(billno,temp);
  	  		vat =  Math.round(vat*100.0)/100.0;
  	  		double cgst = vat/2;
  	  		cgst = Math.round(cgst*100.0)/100.0;
  	  		d = pharmacyDAO.updateGstOfBill(DateTimeUtils.changeFormat(vat), DateTimeUtils.changeFormat(cgst), billno);
		}else{
			Priscription priscription = pharmacyDAO.getPharmacyBillDetails(billno);
			int res = pharmacyDAO.savePharmacyDiscountRequest(billno,debit,requestdisc_disctype,requestdisc_amt,userid,datetime,discinper,priscription,0);
			res =pharmacyDAO.updateDiscountBillStatus(billno,1);
		}
		 
		JSONObject jsonobj = new JSONObject();
		
		jsonobj.put("billno", billno);
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} catch (Exception e) {
		 e.printStackTrace();
	} finally {
		connection.close();
	}
	return null;
}

public String checkpaymentdoneagainstbill() throws Exception{
	if (!verifyLogin(request)) {
		return "login";
	}
	Connection connection = null;
	try {
		String location=(String) session.getAttribute("location");
		if(location==null){
			location="0";
		}
		connection = Connection_provider.getconnection();
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		String billno = jsonObject.getString("billno");
		int res = pharmacyDAO.checkpaymentdoneagainstbill(billno);
		if(res==0){
			res =pharmacyDAO.checkProductReturnAgainstBill(billno);
		}
		JSONObject jsonobj = new JSONObject();
		
		jsonobj.put("paymentstatus", res);
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} catch (Exception e) {
		 e.printStackTrace();
	} finally {
		connection.close();
	}
	return null;
}

public String checkbarcodedata() throws Exception{
	if (!verifyLogin(request)) {

		return "login";
	}
	Connection connection = null;
	try {
		String location=(String) session.getAttribute("location");
		if(location==null){
			location="0";
		}
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		String pid = jsonObject.getString("pid");
		String nonsystembarcode = jsonObject.getString("nonsystembarcode");
		nonsystembarcode= DateTimeUtils.isNull(nonsystembarcode);
		int newproductid =0;
		if(nonsystembarcode.equals("0")){
			newproductid = inventoryProductDAO.getProductIdFromGlobalID(pid,location);
			if(newproductid==0){
				  boolean flag = inventoryProductDAO.getCheckStockFromGlobalID(pid,location);
				  if(flag){
					  newproductid =-3;
				  }
			}
		}else if(nonsystembarcode.equals("1")){
			newproductid = inventoryProductDAO.getProductIdFromBarcode(pid,location);
			if(newproductid==0){
				newproductid=-2;
			}
		}
	    
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("productlist", ""+newproductid);
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}
	return null;
}
public String deletepharmacydiscrequest() throws Exception {
	if (!verifyLogin(request)) {

		return "login";
	}
  	Connection connection=null; 
  	try {
  		connection=Connection_provider.getconnection();
  		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
  		String billno = request.getParameter("val");
  		String id = request.getParameter("id");
  		String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
  		int res = pharmacyDAO.updatePharDiscountReq(id,loginInfo.getUserId(),datetime);
  		res = pharmacyDAO.updateDiscountBillStatus(billno, 0);
  		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+res+"");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
  	finally {
  	
  		 connection.close();
  	}
  	
  	return null;
   }

	public String getpatientbal() throws Exception {
	    
	    Connection connection=null; 
	    try {
	  
	      connection=Connection_provider.getconnection();
	      PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
	      String clientid=request.getParameter("clientid");
	      String flag= request.getParameter("flag");
	      String discountbalstatus = request.getParameter("discountbalstatus");
	      String totalids = request.getParameter("totalids");
	      String name="";
	    
	      String data="";
	      double totalbal=0;
	      if(flag.equals("1")){
	       
	       Priscription priscription=pharmacyDAO.getPharmacyPatient(clientid);
	       name=priscription.getFullname();
	       totalbal =pharmacyDAO.getTotalBalanceNew(clientid,flag,totalids);
	      } else {
	       ClientDAO clientDAO=new JDBCClientDAO(connection);
	       Client client=clientDAO.getClientDetails(clientid);
	       name=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
	       totalbal =pharmacyDAO.getTotalBalanceNew(clientid,flag,totalids);
	      }
	      data=flag+"~"+clientid+"~"+name+"~"+DateTimeUtils.changeFormat(totalbal)+"~"+discountbalstatus+"~"+totalids;
	      response.setContentType("text/html");
	   response.setHeader("Cache-Control", "no-cache");
	   response.getWriter().write(data); 
	      
	 } catch (Exception e) {
	
	  e.printStackTrace();
	 }finally{
			
			connection.close();
		}
	    
	    return null;
	   }
public String getstoreproductforbarcode() throws Exception{
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			String id= request.getParameter("id");
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			ArrayList<Product> inventoryPriscList= inventoryProductDAO.geProductListFast("2",id,loginInfo.isLmh());
			StringBuffer buffer= new StringBuffer();
			//buffer.append("<label>Select Product</label>");     
			buffer.append("<select id='newmedicine' onchange='showprodpackajax(this.value)' class='form-control chosen'  name='mdicinename' >");  // onchange='setProdCatandType(this.value)'
			buffer.append("<option value='0'>Select Product</option>");
			for(Product product:inventoryPriscList){
			  buffer.append("<option value='"+product.getId()+"'>"+product.getGenericname()+"</option>");
			}
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString()); 	 
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		return null;
	}
public String showclientaccount() throws Exception{
	
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		String abrivationid="";
		String fullname="";
		String location=(String)session.getAttribute("location");
		if(location==null){
			location="0";
		}
		String clientid= request.getParameter("clientid");
		String flag=request.getParameter("flag");
		if(flag.equals("0")){
			Priscription priscription=pharmacyDAO.getPharmacyPatient(clientid);
			abrivationid=priscription.getAbrivationid();
			fullname =priscription.getFullname();
		} else {
			Client client=clientDAO.getClientDetails(clientid);
			abrivationid=client.getAbrivationid();
			fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName(); 
		}
		
		if(abrivationid!=null){
			if(abrivationid.equals("") || abrivationid.equals("0")){
				abrivationid=clientid;
			}
		} else {
			abrivationid=clientid;
		}
		
		String fromdate="";
		String todate="";
		fromdate= request.getParameter("fromdate");
		todate= request.getParameter("todate");
		int orderby =0;
		if(loginInfo.isPhar_print_seq()){
			orderby =1;
		}
		ArrayList<Priscription> clientBillHistory= pharmacyDAO.getAllBillHistory(clientid,flag,location,fromdate,todate,orderby);
		String abrivation=clientDAO.getClinicAbrivation(loginInfo.getClinicid());
		StringBuffer buffer=new StringBuffer();
		int i=1;
		double totalBill=0;
		double totalpaid=0;
		double totalbal=0;
		double totalrefund=0;
		//  10 Aug 2018
		int discountbalstatus=0;
		double totaldiscount =0;
		fullname =fullname.toUpperCase();
		int discount_status=0;
		fromdate="";
		todate="";
		for(Priscription priscription:clientBillHistory){
			fromdate = priscription.getFirstdate();
			todate = priscription.getLastdate();
			totalBill= totalBill+priscription.getTotal();
			totalpaid=totalpaid+Double.parseDouble(priscription.getTotalpayment());
			double bal=Double.parseDouble(priscription.getBalance());
			totalbal=totalbal+bal; 
			
			totaldiscount = totaldiscount + priscription.getDiscount();
			if(priscription.getDiscount()>0){
				if(bal>0){
					discountbalstatus=1;
				}
			}
			if(discount_status==1){
				
			}else{
				discount_status = priscription.getDiscount_status();
			}
			totalrefund= totalrefund+priscription.getTotalrefund();
			buffer.append("<tr>");
			int retirnproduct=0;
			buffer.append("<td>");
			if(bal>0){
				if(priscription.getDiscount_status()==1){
					
				}else{
					buffer.append("<input type='hidden' id='paybillbalce"+priscription.getId()+"' value='"+bal+"'  >");
					buffer.append("<input type='checkbox' checked='checked' class='akashpaybil'  onchange='addbalforclear()' value='"+priscription.getId()+"' >");
					/*if(priscription.getDiscount_status()==0){
						int res = pharmacyDAO.checkProductReturnAgainstBill(String.valueOf(priscription.getId())); 
						if(res==2){
							retirnproduct=1;
						}else{
							buffer.append("<input type='hidden' id='paybillbalce"+priscription.getId()+"' value='"+bal+"'  >");
        					buffer.append("<input type='checkbox' checked='checked' class='akashpaybil'  onchange='addbalforclear()' value='"+priscription.getId()+"' >");
						}
					}else{
						buffer.append("<input type='hidden' id='paybillbalce"+priscription.getId()+"' value='"+bal+"'  >");
    					buffer.append("<input type='checkbox' checked='checked' class='akashpaybil'  onchange='addbalforclear()' value='"+priscription.getId()+"' >");
					}*/
				}
			}
			buffer.append("</td>");
			buffer.append("<td>"+i+"</td>");
			buffer.append("<td>"+priscription.getNotes()+"</td>");
			//lokesh
			if(priscription.isPrintflag()||loginInfo.isPharm_print_backdate()){

    			buffer.append("<td><a href='#' onclick=openBlankPopup('viewbillPharmacy?selectedid=0&billno="+priscription.getId()+"')>");
    			
    			if(priscription.getIsreturn()==1){
    				if(priscription.getRefundid()==0){
    					buffer.append("("+abrivation+""+priscription.getId()+")");
    				} else {
    					buffer.append("("+abrivation+""+priscription.getRefundid()+")");
    				}
    			} else {
    				buffer.append("("+abrivation+""+priscription.getId()+")");
    			}
    			if(priscription.getEdited()==1){
    				buffer.append("<b style='color: red;' class='hidden-print'>[Modified]</b>");
    			}
    			
    			buffer.append("</a></td>");
    			
			}else{
				buffer.append("<td>");
    			if(priscription.getIsreturn()==1){
    				if(priscription.getRefundid()==0){
    					buffer.append("("+abrivation+""+priscription.getId()+")");
    				} else {
    					buffer.append("("+abrivation+""+priscription.getRefundid()+")");
    				}
    			} else {
    				buffer.append("("+abrivation+""+priscription.getId()+")");
    			}
    			if(priscription.getEdited()==1){
    				buffer.append("<b style='color: red;' class='hidden-print'>[Modified]</b>");
    			}
    			buffer.append("</td>");
    			
    			
			}
			buffer.append("<td>"+priscription.getDateTime()+"</td>");
			buffer.append("<td>"+priscription.getPaymode()+"</td>");
			buffer.append("<td style='text-align:right'>Rs."+DateTimeUtils.changeFormat(priscription.getDiscount())+"</td>");
			buffer.append("<td style='text-align:right'>Rs."+DateTimeUtils.changeFormat(priscription.getTotal())+"</td>");
			buffer.append("<td style='text-align:right'>Rs."+priscription.getTotalpayment()+"</td>");
			if(bal>0){
				if(priscription.getDiscount_status()==1){
					buffer.append("<td style='text-align:right;color:red'><label style='color:red' > Rs."+priscription.getBalance()+"</label></td>");
				}else{
					buffer.append("<td style='text-align:right;color:red'><a href='#' style='color:red' onclick='openBalPopupNew("+priscription.getId()+")'> Rs."+priscription.getBalance()+"</a></td>");
				}
			} else {
				buffer.append("<td style='text-align:right'>Rs."+priscription.getBalance()+"</td>");
			}
			
			buffer.append("<td style='text-align:right'>Rs."+priscription.getTotalrefund()+"</td>");
			buffer.append("<td>");
				if(bal>0){
					if(priscription.getDiscount()>0){
						if(priscription.getDiscount_status()==2){
							buffer.append("Applied");
						}else{
							buffer.append("Direct Disc.");
							buffer.append("");
						}
					}else{
						if(priscription.getDiscount_status()==0){
							int res = pharmacyDAO.checkProductReturnAgainstBill(String.valueOf(priscription.getId())); 
							if(res==2){
								buffer.append("Product Return");
							}else{
								buffer.append("<a href='#' onclick='applypharmacydiscount("+priscription.getId()+")'> Request</a>");
							}
						}else if(priscription.getDiscount_status()==1){
							buffer.append("Requested");
						}else if(priscription.getDiscount_status()==2){
							buffer.append("Applied");
						}
					}
				}
			buffer.append("</td>");
			Priscription priscclearbill = pharmacyDAO.getClearBalBillInfo(String.valueOf(priscription.getId()));
			if(priscription.isPrintflag()){
				buffer.append("<td><a href='#' onclick='showpatientpaymentmode("+priscription.getId()+")'>Change</a></td>");
			}else{
				buffer.append("<td></td>");
			}
			if(!priscclearbill.getParentid().equals("0")){
				buffer.append("<td><a href='#' onclick=openBlankPopup('printbalreceiptPharmacy?parentid="+priscclearbill.getParentid()+"') style='color: #d9534f;'> Receipt</a></td>");
			} else {
				buffer.append("<td></td>");
			}
			
			if(priscription.getIsreturn()==1 || priscription.getDeleted()==1){
				buffer.append("<td></td>");
			} else {
				buffer.append("<td class='hidden'><a href='#' onclick=openSamePopup('returnmedicinePharmacy?selectedid=0&billno="+priscription.getId()+"') title='Return'><i class='fa fa-reply' aria-hidden='true'></i></a></td>");
			}
			if(priscription.getDeleted()==1){
			       buffer.append("<td></td>");	
			} else {
				if(loginInfo.isDelete_bill() || loginInfo.getUserType()==2) {
					buffer.append("<td></td>");
				}else{
					buffer.append("<td></td>");
				}
			}
			//  30 July 2018
			int count = pharmacyDAO.getClearBalBillCount(String.valueOf(priscription.getId()));
			if(count>1){
				buffer.append("<td><a href='#' onclick='showpartpaymentpatient("+priscription.getId()+")'>RECEIPT("+count+")</a></td>");
			}else{
				buffer.append("<td></td>");
			}
			
			
			i++;
		}
		
		buffer.append("~");
		buffer.append("<td></td> <td></td><td></td><td></td><td></td><td>Total</td>");
		buffer.append("<td style='text-align: right;'>Rs."+DateTimeUtils.changeFormat(totaldiscount)+"</td>");
		buffer.append("<td style='text-align: right;'>Rs."+DateTimeUtils.changeFormat(totalBill)+"</td>");
		buffer.append("<td style='text-align: right;'>Rs."+DateTimeUtils.changeFormat(totalpaid)+"</td>");
		if(flag.equals("0")){
			/*if(totalbal>0){
				if(discount_status==1){
					buffer.append("<td style='text-align: right;'><label style='color:red' >Rs."+DateTimeUtils.changeFormat(totalbal)+"</label></td>");
				}else{
					buffer.append("<td style='text-align: right;'><a href='#' style='color:red' onclick='openclearBal("+clientid+",1,"+discountbalstatus+")' >Rs."+DateTimeUtils.changeFormat(totalbal)+"</a></td>");
				}
			} else {
				buffer.append("<td style='text-align: right;'><a href='#' style='' >Rs."+DateTimeUtils.changeFormat(totalbal)+"</a></td>");
			}*/
			buffer.append("<td style='text-align: right;'><a href='#' style='' >Rs."+DateTimeUtils.changeFormat(totalbal)+"</a></td>");
			
		} else {
			/*if(totalbal>0){
				if(discount_status==1){
					buffer.append("<td style='text-align: right;'><label style='color:red' >Rs."+DateTimeUtils.changeFormat(totalbal)+"</label></td>");
				}else{
					buffer.append("<td style='text-align: right;'><a href='#' style='color:red' onclick='openclearBal("+clientid+",0,"+discountbalstatus+")' >Rs."+DateTimeUtils.changeFormat(totalbal)+"</a></td>");
				}
			}else {
				buffer.append("<td style='text-align: right;'><a href='#' style='' >Rs."+DateTimeUtils.changeFormat(totalbal)+"</a></td>");
			}
			*/
			buffer.append("<td style='text-align: right;'><a href='#' style='' >Rs."+DateTimeUtils.changeFormat(totalbal)+"</a></td>");
			
		}
		buffer.append("<td style='text-align: right;'>Rs."+DateTimeUtils.changeFormat(totalrefund)+"</td>");
		buffer.append("<td></td><td></td><td></td><td></td>");
		String data=fullname+" &nbsp;|&nbsp; "+abrivationid;
		buffer.append("~"+data);
		
		//  28-08-2019
		int newflag =0;
		if(flag.equals("0")){
			newflag=1;
		}
		buffer.append("~"+clientid+"~"+newflag+"~"+discountbalstatus+"~"+fromdate+"~"+todate);
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(buffer.toString()); 
		
	} catch (Exception e) {
	
		e.printStackTrace();
	}
	finally {
		connection.close();
	}
	return null;
}
public String getgrnwithposelectedlist() throws Exception {
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);
	
		String val = jsonObject.getString("val");
		String isfromworkorder = jsonObject.getString("isfromworkorder");
		String location = (String) session.getAttribute("location");
		if (location == null) {
			location = "0";
		}
		if(location.equals("")){
			location="0";
		}
		
		StringBuffer builder = new StringBuffer(); 
		
		ArrayList<Product> selectedPOList = new ArrayList<Product>();
		/*if (session.getAttribute("selectedgrnwithpoids") != null) {
			String tempoids = (String) session.getAttribute("selectedgrnwithpoids");
			selectedPOList = procurementDAO.getRequestedPoListByIDs(tempoids,location);
		}*/
		selectedPOList = procurementDAO.getRequestedPoListFromTempData(loginInfo.getLoginsessionid(),location,isfromworkorder);
		int countsss=0;
		for (Product product : selectedPOList) {
			builder.append("<tr style='color: black !important;'>");
			builder.append("<td>"+(countsss+1)+"<input type='hidden' value='"+product.getId()+"' name='products["+countsss+"].id' />  ");
			builder.append("<input type='hidden' class='longpoclass' value='"+countsss+"'> ");
			builder.append("</td>");
			if(!loginInfo.isLmh()){
				builder.append("<td>"+product.getIndent()+"</td>");
				builder.append("<td>"+DateTimeUtils.isNull(product.getFrom_location())+"</td>");
				builder.append("<td>"+DateTimeUtils.isNull(product.getReq_userid())+"</td>");
			}
			builder.append("<td>"+DateTimeUtils.isNull(product.getPro_code())+"</td>");
			builder.append("<td>"+product.getProduct_name()+"");
			builder.append("<input type='hidden' value='"+product.getGrn_with_po_tempid()+"' name='products["+countsss+"].grn_with_po_tempid' />");
			builder.append("<input type='hidden' value='"+product.getProduct_id()+"' name='products["+countsss+"].product_id' />");
			builder.append("<input type='hidden' value='"+product.getCatalogueid()+"' id='catalogueid"+countsss+"' name='products["+countsss+"].catalogueid' />");
			builder.append("<input type='hidden' value='"+product.getMaxorder()+"' id='maxorder"+countsss+"' />");
			builder.append("<input type='hidden' value='"+product.getMinorder()+"' id='minorder"+countsss+"' />");
			builder.append("<input type='hidden' value='"+product.getPurchase_price()+"' id='previouspurprice"+countsss+"' />");
			builder.append("<textarea rows='3' name='products["+countsss+"].poDescription' onchange=saveintotemppodata_poDescription("+countsss+",'poDescription',this.value,"+product.getGrn_with_po_tempid()+")  class='form-control' id='poDescription"+countsss+"' >"+product.getPoDescription()+"</textarea>");
			builder.append("</td>");
			
			if(loginInfo.isLmh()){
				builder.append("<td>");
				builder.append("<input type='number' name='products["+countsss+"].poHsnno' onchange=saveintotemppodata_poHsnno("+countsss+",'poHsnno',this.value,"+product.getGrn_with_po_tempid()+")  class='form-control' id='poHsnno"+countsss+"' value='"+product.getPoHsnno()+"' />");
				builder.append("</td>");
			}if(loginInfo.isLmh()) {
				builder.append("<td>"+product.getUom()+"</td>");
			}
			
			builder.append("<td>"+product.getStock()+"</td>");
			builder.append("<td><b>"+product.getConsumed()+"</b> ("+product.getFromdate()+" to "+product.getTodate()+")</td>");
			builder.append("<td><input type='text' onchange=saveintotemppodata_rate("+countsss+",'rate',this.value,"+product.getGrn_with_po_tempid()+") value='"+product.getPurchase_price()+"' class='form-control' id='rate"+countsss+"'  name='products["+countsss+"].rate' /></td>");
			builder.append("<td>"+product.getDiscount()+"</td>");
			builder.append("<td>"+product.getFreeqty()+"</td>");
			builder.append("<td>");
			builder.append("<select class='form-control' onchange=saveintotemppodata("+countsss+",'gst',this.value,"+product.getGrn_with_po_tempid()+") name='products["+countsss+"].vat' id='vat"+countsss+"' >");
			if(DateTimeUtils.isNull(product.getVat()).equals("")){
				builder.append("<option value='0' selected='selected'>0%</option>");
				builder.append("<option value='5'>5%</option>");
				builder.append("<option value='12'>12%</option>");
				builder.append("<option value='18'>18%</option>");
				builder.append("<option value='28'>28%</option>");
			}else if(product.getVat().equals("0")){
				builder.append("<option value='0' selected='selected'>0%</option>");
				builder.append("<option value='5'>5%</option>");
				builder.append("<option value='12'>12%</option>");
				builder.append("<option value='18'>18%</option>");
				builder.append("<option value='28'>28%</option>");
			}else if(product.getVat().equals("5")){
				builder.append("<option value='0'>0%</option>");
				builder.append("<option value='5' selected='selected'>5%</option>");
				builder.append("<option value='12'>12%</option>");
				builder.append("<option value='18'>18%</option>");
				builder.append("<option value='28'>28%</option>");
			}else if(product.getVat().equals("12")){
				builder.append("<option value='0'>0%</option>");
				builder.append("<option value='5'>5%</option>");
				builder.append("<option value='12' selected='selected'>12%</option>");
				builder.append("<option value='18'>18%</option>");
				builder.append("<option value='28'>28%</option>");
			}else if(product.getVat().equals("18")){
				builder.append("<option value='0'>0%</option>");
				builder.append("<option value='5'>5%</option>");
				builder.append("<option value='12'>12%</option>");
				builder.append("<option value='18' selected='selected'>18%</option>");
				builder.append("<option value='28'>28%</option>");
			}else if(product.getVat().equals("28")){
				builder.append("<option value='0'>0%</option>");
				builder.append("<option value='5'>5%</option>");
				builder.append("<option value='12'>12%</option>");
				builder.append("<option value='18'>18%</option>");
				builder.append("<option value='28' selected='selected'>28%</option>");
			}
			builder.append("</select>");
			builder.append("</td>");
			builder.append("<td>"+product.getMinorder()+"</td>");
			builder.append("<td>"+product.getMaxorder()+"</td>");
			builder.append("<td>");
			builder.append("<select class='form-control chosen' onchange=saveintotemppodata("+countsss+",'supplier',this.value,"+product.getGrn_with_po_tempid()+") name='products["+countsss+"].vendor_id' id='vendoridsss"+countsss+"' >");
			builder.append("<option value='0'>Select Supplier</option>");
			for (Vendor vendor : product.getVendorlist()) {
				if(DateTimeUtils.isNull(vendor.getGrnpovendorid()).equals(DateTimeUtils.isNull(product.getVendor_id()))){
					builder.append("<option selected='selected' value='"+vendor.getId()+"'>"+vendor.getData()+"</option>");
				}else{
					builder.append("<option value='"+vendor.getId()+"'>"+vendor.getData()+"</option>");
				}
			}
			builder.append("</td>");
			builder.append("<td>");
			builder.append("<input type='number' name='products["+countsss+"].qty' onchange=saveintotemppodata_qty("+countsss+",'req_qty',this.value,"+product.getGrn_with_po_tempid()+")  class='form-control' id='qty"+countsss+"' value='"+product.getQty()+"' />");
			builder.append("</td>");
			builder.append("<td>");
			builder.append("<input type='hidden' name='products["+countsss+"].status' value='"+countsss+"' id='status"+countsss+"'>");
			builder.append("<article>");
			builder.append("<ul class='vertical default_list' id=''>");
			if(product.getNewpo()==1){
				builder.append("<li><label class='checkbox checkbox-custom-alt m-0 mt-5'><input  value='"+countsss+"' type='checkbox' checked='checked' class='form-control case' /><i></i> </label></li>");
			}else{
				builder.append("<li><label class='checkbox checkbox-custom-alt m-0 mt-5'><input  value='"+countsss+"' type='checkbox'  class='form-control case' /><i></i> </label></li>");
			}
			builder.append("</ul>");
			builder.append("</article>");
			builder.append("</td>");
			builder.append("</tr>");
			countsss++;
		}
		
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("list5", builder.toString());
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}
	return null;
}


public String updatintotemppodata() throws Exception {
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);
	
		String val = jsonObject.getString("val");
		String colname = jsonObject.getString("colname");
		String tempdatagrnid = jsonObject.getString("tempdatagrnid");
		int res = procurementDAO.updateTempGRNPOData(colname,tempdatagrnid,val);
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("list5", res);
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}
	return null;
}

public String getcataloguewiselistforsale() throws Exception {
	  if (!verifyLogin(request)) {
			return "login";
		 }
	Connection connection=null; 
	try {
		connection=Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		String location =(String) session.getAttribute("location");
		if(location==null){
			location="0";
		}
		
		//ArrayList<Product> allMedicieneList = inventoryProductDAO.geProductList("2",location);
		ArrayList<Product> allMedicieneList1 = inventoryProductDAO.getCatalogueListForSale("2",location);
		StringBuffer buffer= new StringBuffer();
		
		
		buffer.append("<select class='form-control chosen' id='newcataloguesaleid' onchange='getProductListForSale(this.value)' style='width:100% !important' name='newcataloguesaleid'  >");
		buffer.append("<option value='0'>Select Product</option>");
		for(Product product:allMedicieneList1){
			buffer.append("<option value='"+product.getId()+"'>"+product.getGenericname()+"</option>");
		}
		buffer.append("</select>");
		buffer.append("~~~.~~~");
		
		buffer.append("<select class='form-control chosen' id='newmedicine' name='mdicinename' style='width:90% !important'  >");
		buffer.append("<option value='0'>Select Batchwise Product</option>");
		buffer.append("</select>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+buffer.toString()+"");
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	finally {
	
		 connection.close();
	}
	
	return null;
 }

public String getallmedicinelistforsale() throws Exception {
	  if (!verifyLogin(request)) {
			return "login";
		 }
	Connection connection=null; 
	try {
		connection=Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		String location =(String) session.getAttribute("location");
		if(location==null){
			location="0";
		}
		String val = request.getParameter("val");
		ArrayList<Product> allMedicieneList = inventoryProductDAO.getProductListForSale(val,location);
		StringBuffer buffer= new StringBuffer();
		buffer.append("<select class='form-control chosen' id='newmedicine' name='mdicinename' style='width:90% !important' >");
		buffer.append("<option value='0'>Select Batchwise Product</option>");
		for(Product product:allMedicieneList){
			  buffer.append("<option value='"+product.getId()+"'>"+product.getGenericname()+"</option>");
		}
		buffer.append("</select>");
		response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+buffer.toString()+"");
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	finally {
	
		 connection.close();
	}
	
	return null;
 }

public String sessionAddedPharMed() throws Exception {
	if(!verifyLogin(request)){
		return "login";
	}
	   Connection connection=null;
	   try {
		   connection=Connection_provider.getconnection();
		   PharmacyDAO pharmacyDAO= new JDBCPharmacyDAO(connection);
		   String location=(String)session.getAttribute("location");
		   
		   if(location==null){
			   location= "0";
		   }
		  
		   ArrayList<Product> productList = pharmacyDAO.getsessionAddedPharMedList(location);
		   emrForm.setProductList(productList);
		  
	   } catch (Exception e) {

		   e.printStackTrace();
	   }
	   finally {
		   connection.close();
	   }
	   return "sessionAddedPharMed";
}

public String checkreturnsalebarcodedata() throws Exception{
	if (!verifyLogin(request)) {

		return "login";
	}
	Connection connection = null;
	try {
		String location=(String) session.getAttribute("location");
		if(location==null){
			location="0";
		}
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		String pid = jsonObject.getString("pid");
		String clientid= jsonObject.getString("clientid");
  		String ispharmacy= jsonObject.getString("ispharmacy");
  		String paymode= jsonObject.getString("paymode");
		String nonsystembarcode = jsonObject.getString("nonsystembarcode");
		nonsystembarcode= DateTimeUtils.isNull(nonsystembarcode);
		int newproductid =0;
		int chargeid=0;
		if(nonsystembarcode.equals("0")){
			newproductid = inventoryProductDAO.getProductIdFromGlobalIDWIthoutStckCheck(pid,location);
			if(newproductid!=0){
				chargeid = pharmacyDAO.getChargeIdFromProductId(newproductid,clientid,ispharmacy,paymode,location);  
			}
		}else if(nonsystembarcode.equals("1")){
			newproductid = inventoryProductDAO.getProductIdFromBarcode(pid,location);
			if(newproductid==0){
				newproductid=-2;
			}else{
				newproductid = inventoryProductDAO.getProductIdFromGlobalIDWIthoutStckCheck(pid,location);
				if(newproductid!=0){
					chargeid = pharmacyDAO.getChargeIdFromProductId(newproductid,clientid,ispharmacy,paymode,location);  
				}
			}
		}
	    
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("productlist", ""+newproductid);
		jsonobj.put("chargeid", ""+chargeid);
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}
	return null;
}

public String deletemultiplepriscproduct() throws Exception {
	if (!verifyLogin(request)) {

		return "login";
	}
  	Connection connection=null; 
  	try {
  		connection=Connection_provider.getconnection();
  		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
  		
  		String hdnphclientid = request.getParameter("hdnphclientid");
  		String hdnispharmacy= request.getParameter("hdnispharmacy");
  		
  		int res1 = pharmacyDAO.deleteTempPharmacySaleData(loginInfo.getLoginsessionid(),hdnphclientid,Integer.parseInt(hdnispharmacy));
		int resss = pharmacyDAO.deleteTempChargePharmacySessionLog(loginInfo.getLoginsessionid(),hdnphclientid,Integer.parseInt(hdnispharmacy));
  		
  		setSaleTempdata(Integer.parseInt(hdnphclientid),Integer.parseInt(hdnispharmacy),1);
  			
		} catch (Exception e) {
			e.printStackTrace();
		}
  	finally {
  	
  		 connection.close();
  	}
  	
  	return null;
   }

public String setallmedicineslistdaata() throws Exception {
	  if (!verifyLogin(request)) {
			return "login";
		 }
	Connection connection=null; 
	try {
		connection=Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		String location =(String) session.getAttribute("location");
			if(location==null){
				location="0";
			}
		
		ArrayList<Product> allMedicieneList = inventoryProductDAO.geProductList("2",location);
		StringBuffer buffer= new StringBuffer();
		buffer.append("<select class='form-control chosen' id='newmedicine' name='mdicinename' style='width:50px' >");
		buffer.append("<option value='0'>Select Product</option>");
		for(Product product:allMedicieneList){
			
			  buffer.append("<option value='"+product.getId()+"'>"+product.getGenericname()+"</option>");
		}
		response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+buffer.toString()+"");
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	finally {
	
		 connection.close();
	}
	
	return null;
 }

public String setnewtokenentry() throws Exception{
	 	if (!verifyLogin(request)) {
			return "login";
		}
	   	Connection connection = null;
	 try {
		connection = Connection_provider.getconnection();
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		String fromdate = request.getParameter("fromdate");
		String todate = request.getParameter("todate");
		
		if(fromdate!=null){
			if(!fromdate.equals("")){
			    fromdate=DateTimeUtils.getCommencingDate1(fromdate);
			}else {
				fromdate=null;
			}
		} else {
			fromdate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String str[]=fromdate.split(" ");
			fromdate=str[0];	
		}
		
		if(todate!=null){
			if(!todate.equals("")){
			  todate=DateTimeUtils.getCommencingDate1(todate);
			}else {
				todate=null;
			}
		} else {
			todate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String str[]=todate.split(" ");
			todate=str[0];
		}
		
		ArrayList<Priscription> priscriptionlist = new ArrayList<Priscription>();
		priscriptionlist = pharmacyDAO.getPriscInSaleList(fromdate,todate);
		StringBuffer buffer = new StringBuffer();
		
		for (Priscription priscription : priscriptionlist) {
			buffer.append("<tr>");
			buffer.append("<td>"+priscription.getLastmodified()+"</td>");
			buffer.append("<td>TN-"+priscription.getTokenno()+"</td>");
			buffer.append("<td>"+priscription.getAbrivationid()+"</td>");
			buffer.append("<td>"+priscription.getFullname()+"</td>");
			buffer.append("<td style='text-align:center;background-color: rgba(255, 163, 163, 0.93);'>Sale In Process</td>");
			buffer.append("</tr>");
			
		}
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+buffer.toString()+"");
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		connection.close();
	}
	 return null;
}


public String printpriscreqcsv() throws Exception{
	 if (!verifyLogin(request)) {
		return "login";
	 }
	 Connection connection = null;
	 try {
		connection = Connection_provider.getconnection();
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		String newparentid = request.getParameter("newparentid");
		
		ArrayList<Priscription> priscriptionlist = new ArrayList<Priscription>();
		priscriptionlist = pharmacyDAO.getPrintPriscReqcsv(newparentid);
		StringBuffer buffer = new StringBuffer();
		for (Priscription priscription : priscriptionlist) {
			buffer.append("<tr>");
			buffer.append("<td>"+priscription.getMedicineid()+"</td>");
			buffer.append("<td>"+priscription.getMdicinenametxt()+"</td>");
			buffer.append("<td></td>");
			buffer.append("<td>"+priscription.getMobile()+"</td>");
			buffer.append("<td>"+priscription.getFullname()+"</td>");
			buffer.append("<td>"+priscription.getQty()+"</td>");
			buffer.append("<td>"+priscription.getDosage()+"</td>");
			buffer.append("</tr>");
		}
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+buffer.toString()+"");
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		connection.close();
	}
	 return null;
}

public String searchParticularPatient() throws SQLException{
		if(!verifyLogin(request)){
		return "login";
	}
	String searchClient = request.getParameter("searchText");
	Connection connection = null;
	ArrayList<Client> allPatientList = new ArrayList<Client>();
	try{
		connection = Connection_provider.getconnection();
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		BedDao bedDao = new JDBCBedDao(connection);
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		allPatientList = clientDAO.getClient(searchClient,loginInfo.getId(),"");
		StringBuffer str = new StringBuffer();
						
		//get pharmacy patient list
		ArrayList<Client> pharmacySearchClientList = pharmacyDAO.getPharmacySearchClientList(searchClient);
		allPatientList.addAll(pharmacySearchClientList);
		
		//mrdForm.setAllPatientList(allPatientList);
		
		
		str.append("<table class='table table-bordered' > ");
		str.append("<thead>");
		str.append("<tr class='bg-info'>");
		str.append("<th>PATIENT ID</th> ");
		str.append("<th>Name</th> ");
		str.append("<th>REGISTERED</th> ");
		str.append("<th>Mobile</th> ");
		str.append("<th>Address</th> ");
		str.append("<th class='hidden'>Email</th> ");
		str.append("<th class='hidden'>PostCode</th> ");
		str.append("<th class='hidden'>Dob</th> ");
		str.append("<th>LastModified</th> ");

		str.append("</tr>");
		str.append("</thead>");
		
		str.append("<tbody>");
		for(Client client1:allPatientList){
			String name = client1.getClientName();
			String ipdid = "0";
			if(client1.getIspharmacy().equals("0")){
				name = client1.getFirstName()+" "+client1.getMiddlename()+" "+client1.getLastName();
			}
			String color = "";
			str.append("<tr style='background-color:"+color+"' >");
			if(!DateTimeUtils.numberCheck(client1.getAbrivationid()).equals("0")){
				str.append("<td>"+client1.getAbrivationid()+"</td>");
			}else{
				str.append("<td>"+client1.getId()+"</td>");
			}
			String mobno = client1.getMobNo();
			if (DateTimeUtils.isNull(mobno).equals("")) {
				mobno = "0";
			}
			str.append("<td style='cursor: pointer;'; onclick= setInhousePatientInfo('"+client1.getId()+"','"+ipdid+"','"+client1.getIspharmacy()+"') >"+name+"</td>");
			if(client1.getIspharmacy().equals("0")){
				str.append("<td>IPD / OPD</td>");
			}else{
				str.append("<td>Pharmacy</td>");
			}
			str.append("<td>"+client1.getMobNo()+"</td>");
			str.append("<td>"+DateTimeUtils.isNull(client1.getAddress())+"</td>");
			str.append("<td class='hidden'>"+client1.getEmail()+"</td>");
			str.append("<td class='hidden'>"+client1.getPostCode()+"</td>");
			str.append("<td class='hidden'>"+client1.getDob()+"</td>");
			if(client1.getLastModified()==null){
				client1.setLastModified("");
			}
			str.append("<td>"+client1.getLastModified()+"</td>");
			str.append("</tr>");
		}
		str.append("</tbody>");
		str.append("</table>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().write(""+str.toString()+""); 
		
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	finally{
		connection.close();
	}
	return null;
}

public String showAllPatientList() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	
	Connection connection = null;
	Client client = new Client();
	ArrayList<Client> allPatientList = new ArrayList<Client>();
	try{
		connection = Connection_provider.getconnection();
		
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		allPatientList = clientDAO.getAllPatient(loginInfo.getId(),"");
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		
		//get pharmacy patient list
		ArrayList<Client>pharmacyClientList = pharmacyDAO.getPharmacyClientList();
		pharmacyClientList.addAll(allPatientList);
		//BedDao bedDao = new JDBCBedDao(connection);
		//mrdForm.setAllPatientList(allPatientList);
		
		StringBuffer str = new StringBuffer();
		str.append("<table class='table table-bordered' > ");
		str.append("<thead>");
		str.append("<tr class='bg-info'>");
		str.append("<th>Patient ID</th> ");
		str.append("<th>Name</th> ");
		str.append("<th>Registered</th> ");
		str.append("<th>Mobile</th> ");
		str.append("<th>Address</th> ");
		str.append("<th class='hidden'>Email</th> ");
		str.append("<th class='hidden'>PostCode</th> ");
		str.append("<th class='hidden'>Dob</th> ");
		str.append("<th>LastModified</th> ");

		str.append("</tr>");
		str.append("</thead>");
		
		str.append("<tbody>");
		for(Client client1:pharmacyClientList){
			String name = client1.getClientName();
			String ipdid = "0";
			if(client1.getIspharmacy().equals("0")){
				/*ipdid = pharmacyDAO.getIpdIdFromClientID(client1.getId());
				String pract_name  = client1.getClientName();
				if(ipdid.equals("0")){
					pract_name = pharmacyDAO.getappointmentinfo(client1.getId());
				}else{
					Bed  bed = bedDao.getEditIpdData(ipdid);
					UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
					pract_name = profileDAO.getName(bed.getPractitionerid());
					
				}*/
				name = client1.getFirstName()+" "+client1.getMiddlename()+" "+client1.getLastName();
			}
			 	
			String color = "";
			if(!client1.getCasualtyid().equals("0")){
				color = "#f5a0b4";
			}
			str.append("<tr style='background-color:"+color+"' >");
		
			if(!DateTimeUtils.numberCheck(client1.getAbrivationid()).equals("0")){
				str.append("<td>"+client1.getAbrivationid()+"</td>");
			}else{
				str.append("<td>"+client1.getId()+"</td>");
			}
			
			
		
			String mobno = client1.getMobNo();
			String address = "-";
			if(client1.getAddress()!=null) {
				
				String[] address1 = client1.getAddress().split(" ");
				
				for (int i = 0; i < address1.length; i++) {
					address = address+"-"+address1[i];
					
				}
				
			}
			if (mobno!=null) {
				if (mobno.equals("")) {
					mobno = "0";
				}
			}
			str.append("<td style='cursor: pointer;'; onclick= setInhousePatientInfo('"+client1.getId()+"','"+ipdid+"','"+client1.getIspharmacy()+"') >"+name+"</td>");
			if(client1.getIspharmacy().equals("0")){
				str.append("<td>IPD / OPD</td>");
			}else{
				str.append("<td>Pharmacy</td>");
			}
			str.append("<td>"+client1.getMobNo()+"</td>");
			str.append("<td>"+DateTimeUtils.isNull(client1.getAddress())+"</td>");
			str.append("<td class='hidden'>"+client1.getEmail()+"</td>");
			str.append("<td class='hidden'>"+client1.getPostCode()+"</td>");
			str.append("<td class='hidden'>"+client1.getDob()+"</td>");
			if(client1.getLastModified()==null){
				client1.setLastModified("");
			}
			str.append("<td>"+client1.getLastModified()+"</td>");
	
			str.append("</tr>");
		}
		str.append("</tbody>");
		str.append("</table>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().write(""+str.toString()+""); 
		
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	finally{
		connection.close();
	}
	
	return null;
}

public String getpatientnamedata() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		StringBuffer str = new StringBuffer();
		String val = request.getParameter("val");
		ArrayList<String> arrayList = new ArrayList<String>();
		if(!DateTimeUtils.isNull(val).equals("")){
			arrayList = pharmacyDAO.getPatientNameData(val);
		}
		for (String string : arrayList) {
			str.append("<option value="+string+">"+string+"</option>");
		}
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+str.toString()+""); 
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	finally{
		connection.close();
	}
	
	return null;
}


public String savedrdetailsinhis() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		String phardoc_inital = request.getParameter("phardoc_inital");
		String phardoc_firstname = request.getParameter("phardoc_firstname");
		String phardoc_lastname = request.getParameter("phardoc_lastname");
		String phardoc_address = request.getParameter("phardoc_address");
		String phardoc_qualification = request.getParameter("phardoc_qualification");
		UserProfile userProfile = new UserProfile();
		
		userProfile.setFirstname(phardoc_firstname);
		userProfile.setLastname(phardoc_lastname);
		userProfile.setInitial(phardoc_inital);
		userProfile.setUserType(4);
		userProfile.setJobtitle("Pharmacy Doctor");
		userProfile.setFullname(phardoc_inital+" "+phardoc_firstname+" "+phardoc_lastname);
		userProfile.setMobile("");
		userProfile.setEmail("");
		userProfile.setDiciplineName("0");
		userProfile.setFees("0");
		userProfile.setIsvisitingconsultant(false);
		userProfile.setIssurgeon(false);
		userProfile.setIsanesthesiologist(false);
		userProfile.setIsreferred(false);
		userProfile.setExistingdrid("0");
		userProfile.setIsmlc(false);
		userProfile.setQualification(DateTimeUtils.isNull(phardoc_qualification));
		userProfile.setSharepercentage("0");
		userProfile.setAddress(phardoc_address);
		int res = userProfileDAO.saveVisitingCosultant(userProfile, loginInfo.getId(),loginInfo.getClinicName());
		
		ArrayList<UserProfile> pharmacydrlist = userProfileDAO.getPharmacyDoctorList();
		StringBuffer buffer = new StringBuffer();
		buffer.append("<label>Doctor Name</label>");
		buffer.append("<select id='addoctor' class='form-control chosen-select'  name='addoctor' >");  // onchange='setProdCatandType(this.value)'
		buffer.append("<option value='0'>Select Doctor</option>");
		for(UserProfile userProfile2:pharmacydrlist){
		  buffer.append("<option value='"+userProfile2.getId()+"'>"+userProfile2.getName()+"</option>");
		}
		buffer.append("</select>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+buffer.toString()+""); 
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	finally{
		connection.close();
	}
	
	return null;
}

public String openpatientpreviousbill() throws Exception{
	
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
		String location=(String)session.getAttribute("location");
		if(location==null){
			location="0";
		}
		String clientid= request.getParameter("clientid");
		String flag=request.getParameter("ispharmacy");
		
		String fromdate="";
		String todate="";
		fromdate= request.getParameter("fromdate");
		todate= request.getParameter("todate");
		int orderby =0;
		
		if(flag.equals("0")){
			flag = "1";
		}else{
			flag = "0";
		}
		if(fromdate.equals("")){
			fromdate =null;
			todate =null;
		}
		
		ArrayList<Priscription> clientBillHistory= pharmacyDAO.getAllBillHistory(clientid,flag,location,fromdate,todate,orderby);
		StringBuffer buffer=new StringBuffer();
		int i=1;
		fromdate="";
		todate="";
		for(Priscription priscription:clientBillHistory){
			fromdate = priscription.getFirstdate();
			todate = priscription.getLastdate();
			buffer.append("<tr>");
			buffer.append("<td>"+i+"</td>");
			buffer.append("<td>"+priscription.getNotes()+"</td>");
			buffer.append("<td><a href='#' onclick=openBlankPopup('viewbillPharmacy?selectedid=0&billno="+priscription.getId()+"')>");
			if(priscription.getIsreturn()==1){
				if(priscription.getRefundid()==0){
					buffer.append(""+priscription.getId()+"");
				} else {
					buffer.append(""+priscription.getRefundid()+"");
				}
			} else {
				buffer.append(""+priscription.getId()+"");
			}
			buffer.append("</a></td>");
			buffer.append("<td>"+priscription.getDateTime()+"</td>");
			buffer.append("<td>"+priscription.getPaymode()+"</td>");
			buffer.append("<td style='text-align:right'>Rs."+DateTimeUtils.changeFormat(priscription.getDiscount())+"</td>");
			buffer.append("<td style='text-align:right'>Rs."+DateTimeUtils.changeFormat(priscription.getTotal())+"</td>");
			buffer.append("<td style='text-align:right'>Rs."+priscription.getTotalpayment()+"</td>");
			buffer.append("<td style='text-align:right'>Rs."+priscription.getBalance()+"</td>");
			buffer.append("<td style='text-align:right'>Rs."+priscription.getTotalrefund()+"</td>");
			if(priscription.getIsreturn()==0){
				buffer.append("<td style='text-align:center;background-color: rgba(255, 163, 163, 0.93);'><a href='#' onclick='repeatbillsale("+priscription.getId()+")'>Sale</a></td>");
			}else{
				buffer.append("<td></td>");
			}
			
			buffer.append("</tr>");
			i++;
		}
		buffer.append("~~~");
		buffer.append(""+fromdate+"");
		buffer.append("~~~");
		buffer.append(""+todate+"");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(buffer.toString()); 
		
	} catch (Exception e) {
	
		e.printStackTrace();
	}
	finally {
		connection.close();
	}
	return null;
}


public String repeatbillsaledata() throws Exception{
	if (!verifyLogin(request)) {

		return "login";
	}
	Connection connection = null;
	try {
		String location=(String) session.getAttribute("location");
		if(location==null){
			location="0";
		}
		connection = Connection_provider.getconnection();
		PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		String hdnphclientid = jsonObject.getString("hdnphclientid");
	    String hdnispharmacy = jsonObject.getString("hdnispharmacy");
	    String billno = jsonObject.getString("billno");
	    
	    String loc= (String) session.getAttribute("location");
		if(loc==null){
			loc="0";
		}
	    
	    int res1 = pharmacyDAO.deleteTempPharmacySaleData(loginInfo.getLoginsessionid(),hdnphclientid,Integer.parseInt(hdnispharmacy));
		int resss = pharmacyDAO.deleteTempChargePharmacySessionLog(loginInfo.getLoginsessionid(),hdnphclientid,Integer.parseInt(hdnispharmacy));
		String todate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		int res = pharmacyDAO.getBillPharmacyAvailablityNew(billno,loginInfo.getLoginsessionid(),todate,loc,hdnphclientid,Integer.parseInt(hdnispharmacy));
	    
	    JSONObject jsonobj = new JSONObject();
		jsonobj.put("productlist", "1");
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}
	return null;
}

public String getpharmacypatientdata() throws Exception{
	if (!verifyLogin(request)) {

		return "login";
	}
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		String hdnphclientid = jsonObject.getString("hdnphclientid");
	    String hdnispharmacy = jsonObject.getString("hdnispharmacy");
	    
	    Priscription priscription=pharmacyDAO.getPharmacyPatient(hdnphclientid);
	    
	    JSONObject jsonobj = new JSONObject();
		jsonobj.put("pharid", priscription.getPclientid());
		jsonobj.put("pharmobile", DateTimeUtils.isNull(priscription.getMobile()));
		jsonobj.put("pharaddress", DateTimeUtils.isNull(priscription.getAddress()));
		jsonobj.put("phardrid", DateTimeUtils.numberCheck(priscription.getPractitionerid()));
		jsonobj.put("pharfullname", DateTimeUtils.isNull(priscription.getFullname()));
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}
	return null;
}


public String updatepatient() throws Exception{
	 if (!verifyLogin(request)) {

			return "login";
		}
	 String adfullname = request.getParameter("adfullname");
	 String admobile = request.getParameter("admobile");
	 String adaddress = request.getParameter("adaddress");
	 String addoctor = request.getParameter("addoctor");
	 String editpclinetid = request.getParameter("editpclinetid");
	 Connection connection = null;
	 try{
		connection = Connection_provider.getconnection();
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		Priscription priscription=pharmacyDAO.getPharmacyPatient(editpclinetid);
		String fullname = userProfileDAO.getFullName(addoctor);
		String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		int r = pharmacyDAO.updateNewPharmaPatient(adfullname,admobile,adaddress,addoctor,fullname,editpclinetid);
		
		if(r>0){
			pharmacyDAO.pharmacypatientupdatelog(priscription,adfullname,admobile,adaddress,addoctor,editpclinetid,datetime);
		}
		 
	 }catch (Exception e) {
		e.printStackTrace();
	}finally{
		
		connection.close();
	}
	 
	 return null;
}


public String addtempjobworkissuedata() throws Exception{
	if (!verifyLogin(request)) {

		return "login";
	}
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		String issue_date = jsonObject.getString("issue_date");
	    String issue_vendorid = jsonObject.getString("issue_vendorid");
	    String issue_isinventory = jsonObject.getString("issue_isinventory");
	    String issue_catalogueid = jsonObject.getString("issue_catalogueid");
	    String issue_productname = jsonObject.getString("issue_productname");
	    String qty = jsonObject.getString("qty");
	    String exp_date = jsonObject.getString("exp_date");
	    String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
	    int checkalreadyproduct =0;
	    if(issue_isinventory.equals("0")){
	    	checkalreadyproduct = inventoryProductDAO.checkJobWorkIssueTempId(loginInfo.getLoginsessionid(),issue_catalogueid);
	    }else{
	    	checkalreadyproduct = inventoryProductDAO.checkJobWorkIssueTempName(loginInfo.getLoginsessionid(),issue_productname);
	    }
	    if(checkalreadyproduct==0){
	    	int res = inventoryProductDAO.insertIntoTempJobWorkIssue(loginInfo.getLoginsessionid(),issue_date,issue_vendorid,issue_isinventory,issue_catalogueid,issue_productname,qty,exp_date,datetime);
	    }
	    
	    JSONObject jsonobj = new JSONObject();
		jsonobj.put("checkalreadyproduct", checkalreadyproduct);
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}
	return null;
}

public String gettempjobworkissuedata() throws Exception {
	if (!verifyLogin(request)) {
		return "login";
	}
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		String issue_date = jsonObject.getString("issue_date");
	    
		ArrayList<Product> arrayList = inventoryProductDAO.getJobWorkIssueTempData(loginInfo.getLoginsessionid());
		StringBuffer buffer = new StringBuffer();
		int count=0;
		for (Product product : arrayList) {
			buffer.append("<tr>");
			buffer.append("<td>"+(++count)+"<input type='hidden' class='dclass' value='"+count+"' /></td>");
			if(product.getIsinventory()==0){
				buffer.append("<td>Inventory</td>");
			}else{
				buffer.append("<td>Non-Inventory</td>");
			}
			buffer.append("<td>"+product.getProduct_name()+"</td>");
			buffer.append("<td>"+product.getQty()+"</td>");
			buffer.append("<td>"+product.getExpectedDate()+"<input type='hidden' id='isssue_rmark_temp"+count+"' value='"+DateTimeUtils.isNull(product.getRemark())+"' /></td>");
			buffer.append("<td><textarea id='isssue_rmark"+count+"' class='form-control' rows='2' placeholder='Enter Remark' onchange='updatetempjobworkissue("+product.getId()+",this.value)' ></textarea></td>");
			buffer.append("<td><a href='#' onclick=deletetempjobworkissue("+product.getId()+") ><i class='fa fa-times fa-2x text-danger' ></i></a></td>");
			buffer.append("</tr>");
		}
		
		
	    JSONObject jsonobj = new JSONObject();
		jsonobj.put("tempjobtitledada", buffer.toString());
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}
	return null;
	
}


public String deletetempjobworkissuedata() throws Exception{
	if (!verifyLogin(request)) {
		return "login";
	}
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		String issue_date = jsonObject.getString("issue_date");
	    
		int res = inventoryProductDAO.deleteTempJobWorkIssueData(loginInfo.getLoginsessionid());
	    
	    JSONObject jsonobj = new JSONObject();
		//jsonobj.put("pharid", priscription.getPclientid());
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}
	return null;
}


public String updatetempjobworkissuedata() throws Exception{
	if (!verifyLogin(request)) {

		return "login";
	}
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		String id = jsonObject.getString("id");
	    String val = jsonObject.getString("val");
	    
	    int res =inventoryProductDAO.updateTempJobWorkIssueRemark(id,val);
	    
	    
	    JSONObject jsonobj = new JSONObject();
		jsonobj.put("checkalreadyproduct", res);
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}
	return null;
}

public String deletetempjobworkissueentry() throws Exception{
	if (!verifyLogin(request)) {

		return "login";
	}
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		String id = jsonObject.getString("id");
	    
	    int res =inventoryProductDAO.deleteTempJobWorkIssueEntry(id);
	    
	    
	    JSONObject jsonobj = new JSONObject();
		jsonobj.put("checkalreadyproduct", res);
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}
	return null;
}


public String savetempjobworkissuedata() throws Exception{
	if (!verifyLogin(request)) {

		return "login";
	}
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		String issue_date = jsonObject.getString("issue_date");
	    String vendorid = jsonObject.getString("issue_vendorid");
	    issue_date = DateTimeUtils.getCommencingDate1(issue_date);
	    int res = 0;
	    String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
	    ArrayList<Product> arrayList = inventoryProductDAO.getJobWorkIssueTempData(loginInfo.getLoginsessionid());
	    int parentid = inventoryProductDAO.insertJobWorkIssue(issue_date,vendorid,datetime,loginInfo.getUserId());
	    
	    for (Product product : arrayList) {
	    	res = inventoryProductDAO.insertJobTitleIssueChild(parentid,product);
		}
	    
	    JSONObject jsonobj = new JSONObject();
		jsonobj.put("checkalreadyproduct", res);
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}
	return null;
}

public String checkreceiptjobwork() throws Exception{
	if (!verifyLogin(request)) {

		return "login";
	}
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		String receipt_vendorid = jsonObject.getString("receipt_vendorid");
	    ArrayList<Product> arrayList = inventoryProductDAO.getJobWorkPendingRequest(receipt_vendorid);
	    int count=0;
	    StringBuffer buffer = new StringBuffer();
	    for (Product product : arrayList) {
			buffer.append("<tr id='receipt_tr"+product.getId()+"'>");
			buffer.append("<td>"+(++count)+"</td>");
			buffer.append("<td>"+product.getParentid()+"</td>");
			buffer.append("<td>"+product.getDateTime()+"</td>");
			buffer.append("<td>"+product.getUserid()+"</td>");
			buffer.append("<td>"+product.getDate()+"</td>");
			if(product.getIsinventory()==0){
				buffer.append("<td>Inventory</td>");
			}else{
				buffer.append("<td>Non-Inventory</td>");
			}
			buffer.append("<td>"+product.getProduct_name()+"</td>");
			buffer.append("<td>"+product.getQty()+"</td>");
			buffer.append("<td>"+product.getExpectedDate()+"</td>");
			buffer.append("<td style='padding: 0 !important;'>"+DateTimeUtils.isNull(product.getRemark())+"</td>");
			buffer.append("<td>");
			buffer.append("<input type='hidden' class='receiptclass' value='"+product.getId()+"' />");
			buffer.append("<select id='receipt_status"+product.getId()+"' onchange='changecolorofreceipt(this.value,"+product.getId()+")' class='form-control chosen-select' name='receipt_status"+product.getId()+"' >");
			buffer.append("<option value='0'>Select</option>");
			buffer.append("<option value='1'>Repaired</option>");
			buffer.append("<option value='2'>Un-Repaired</option>");
			buffer.append("</select>");
			buffer.append("</td>");
			//buffer.append("<td style='padding: 0 !important;'><textarea rows='4' class='form-control' name='comment"+product.getId()+"' placeholder='Enter receipt comment' id='receipt_comment"+product.getId()+"' /></td>");
			buffer.append("<td style='padding: 0 !important;'><input type='text' class='form-control' name='receipt_comment"+product.getId()+"' placeholder='Enter receipt comment' id='receipt_comment"+product.getId()+"' /></td>");
			buffer.append("</tr>");
		}
	    
	    JSONObject jsonobj = new JSONObject();
		jsonobj.put("productreceiptlist", buffer.toString());
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}
	return null;
}

public String getcataloguelistforisssue() throws Exception{
	if (!verifyLogin(request)) {

		return "login";
	}
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		StringBuilder buffer1 = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer1.append(line);
		}
		String data = buffer1.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		String issue_date = jsonObject.getString("issue_date");
	    
		ArrayList<Product> arrayList = inventoryProductDAO.getCatalogueListForJobWork();
		StringBuffer buffer = new StringBuffer();
		buffer.append("<div class='form-group'>");
		buffer.append("<label>Select Product</label>");
		buffer.append("<select id='issue_catalogueid' name='issue_catalogueid' class='form-control chosen-select'>");
		buffer.append("<option value='0'>Select</option>");
		for (Product product : arrayList) {
			buffer.append("<option value='"+product.getId()+"'>"+product.getProduct_name()+"</option>");
		}
		buffer.append("</select>");
		buffer.append("</div>");
		
	    JSONObject jsonobj = new JSONObject();
		jsonobj.put("cataloguelist", buffer.toString());
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}
	return null;
}

public String deletejobwork() throws Exception {
	if(!verifyLogin(request)){
		return "login";
	}
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
		String parentid= request.getParameter("parentid");
		String delete_reason= request.getParameter("delete_reason");
		String userid = loginInfo.getUserId();
		String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		int res = inventoryProductDAO.cancelJobWork(parentid,delete_reason,userid,datetime);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""); 
	} catch (Exception e) {
		e.printStackTrace();
	}
	finally{
		connection.close();
	}
	
	return null;
}

public String validateuhid() throws Exception {
	Connection connection = null;
	try {

		if (!verifyLogin(request)) {
			return "login";
		}
		
		connection = Connection_provider.getconnection();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		
		StringBuffer strbuffer = new StringBuffer();
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);

		String searchText = jsonObject.getString("searchText");
		
		int id = clientDAO.getPureSevaClientid(searchText);
		
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("datalistdata", id);
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);

	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String checkproductinsale() throws Exception{

	Connection connection = null;
	try {
		if (!verifyLogin(request)) {
			return "login";
		}
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);
	
		String prodids = jsonObject.getString("prodids");
		
		//int res = inventoryProductDAO.checkProductCodeExist(val);
		
		int isinadjustment = inventoryProductDAO.checkProductInAdjustment(prodids);
		if(isinadjustment==0){
			isinadjustment = pharmacyDAO.checkProductInSale(prodids);
			if(isinadjustment==1){
				isinadjustment =2;
			}
		}
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("val1", isinadjustment);
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;

}

public String getLatestStock() throws Exception {
    
    Connection connection=null; 
    try {
  
      connection=Connection_provider.getconnection();
      InventoryProductDAO  inventoryProductDAO = new JDBCInventoryProductDAO(connection);
      String productid=request.getParameter("productid");
      double data= inventoryProductDAO.getLatestStock(productid);
      response.setContentType("text/html");
	  response.setHeader("Cache-Control", "no-cache");
	  response.getWriter().write(""+data+""); 
      
	 } catch (Exception e) {
		 e.printStackTrace();
	 }finally{
		connection.close();
	 }
    
    return null;
   }


public String checkLatestStock() throws Exception{

	Connection connection = null;
	try {
		if (!verifyLogin(request)) {
			return "login";
		}
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);
	
		String prodids = jsonObject.getString("prodids");
		
		double data1= inventoryProductDAO.getLatestStock(prodids);
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("val1", data1);
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;

}

public String getNDSCharges() throws Exception{
	Connection connection = null;
	try {
		if (!verifyLogin(request)) {
			return "login";
		}
		connection = Connection_provider.getconnection();
		
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);
	
		String department = jsonObject.getString("department");
		String patientType = jsonObject.getString("patientType");
		String payerType = jsonObject.getString("payerType");
		String payer = jsonObject.getString("payer");
		
		ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		ThirdParty thirdParty1 = thirdPartyDAO.getNDSids(patientType,payerType,payer);
		ArrayList<ThirdParty> arrayList = new ArrayList<>();
		/*if(!department.equals("")){*/
			if(thirdParty1.getIds().equals("0,,0")){
				thirdParty1.setIds("0");
			}
			arrayList = thirdPartyDAO.getNDSCharges(department,thirdParty1.getIds());
		/*}*/
		
		StringBuffer stringBuffer = new StringBuffer();
		for (ThirdParty thirdParty : arrayList) {
			stringBuffer.append("<tr>");
			stringBuffer.append("<td><input type='checkbox' class='nds_services' value='"+thirdParty.getId()+"'></td>");
			stringBuffer.append("<td>"+thirdParty.getChargeName()+"</td>");
			stringBuffer.append("<td>"+DateTimeUtils.isNull(thirdParty.getCode())+"</td>");
			stringBuffer.append("<td>"+DateTimeUtils.isNull(thirdParty.getChargeAmount())+"</td>");
			stringBuffer.append("<td>"+thirdParty.getMasterCharge()+"</td>");
			stringBuffer.append("</tr>");
		}
		
		StringBuffer buffer2 = new StringBuffer();
		ArrayList<ThirdParty> arrayList2 =thirdPartyDAO.getNDSChargesList(thirdParty1.getIds());
		for (ThirdParty thirdParty : arrayList2) {
			buffer2.append("<tr>");
			buffer2.append("<td>"+thirdParty.getMasterCharge()+"</td>");
			buffer2.append("<td>"+thirdParty.getChargeName()+"<input type='hidden' class='nds_charges' value='"+thirdParty.getId()+"'></td>");
			buffer2.append("<td>"+DateTimeUtils.isNull(thirdParty.getCode())+"</td>");
			buffer2.append("<td>"+DateTimeUtils.isNull(thirdParty.getChargeAmount())+"</td>");
			buffer2.append("<td style='text-align: center;'><a href='#' onclick='deleteNDS("+thirdParty.getId()+")' ><i class='fa fa-times fa-2x text-danger' ></i></a></td>");
			buffer2.append("</tr>");
		}
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("val1", stringBuffer);
		jsonobj.put("val2", buffer2);
		jsonobj.put("ndsId", thirdParty1.getIds());
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String saveNDScharges()throws Exception{
	Connection connection = null;
	try {
		if (!verifyLogin(request)) {
			return "login";
		}
		connection = Connection_provider.getconnection();
		
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);
	
		String department = jsonObject.getString("department");
		String patientType = jsonObject.getString("patientType");
		String payerType = jsonObject.getString("payerType");
		String payer = jsonObject.getString("payer");
		String ids = jsonObject.getString("ids");
		//String ndsId = jsonObject.getString("ndsId");
		ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		
		ids = ids+','+0;
		if(ids.equals("0,,0")){
			ids = "0,0,0";
		}
		
		ThirdParty thirdParty1 = thirdPartyDAO.getNDSids(patientType,payerType,payer);
		//boolean flag = thirdPartyDAO.checkAlreadyNDSIds(department,patientType,payerType,payer,ids);
		int saveid= 0;
		if(thirdParty1.getId()!=0){
			saveid= thirdPartyDAO.updateNonDiscountServices(department,patientType,payerType,payer,ids,thirdParty1.getId());
		}else{
			saveid= thirdPartyDAO.saveNonDiscountServices(department,patientType,payerType,payer,ids);
		}
		
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("val1", saveid);
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String setTypeNameDropDown() throws Exception{
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		 
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		 ArrayList<Client>ajaxTypeNameList = clientDAO.getThirdTypeNameList(id);
		

		 StringBuffer dropDownAjax = new StringBuffer();
			dropDownAjax.append("<select id='typeName1' name='typeName' onchange='changeNDSDepartment()'  class='form-control showToolTip chosen' data-toggle='tooltip'>");
				dropDownAjax.append("<option value = '0'>Select Payer</option>");
				if(ajaxTypeNameList.size()!=0){
					for(Client client : ajaxTypeNameList){
						dropDownAjax.append("<option value = '"+client.getId()+"'>"+client.getThirdPartyCompanyName()+"</option>");
					}
					
				}
			dropDownAjax.append("</select>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+dropDownAjax.toString()+""); 
		
	}catch (Exception e) {
		e.printStackTrace();
	}finally{
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return null;
	
	
}
public String getsupplierlocation()throws Exception{
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
		
		StringBuffer buffer = new StringBuffer();
		BufferedReader bufferedReader = request.getReader();
		String line ;
		while ((line = bufferedReader.readLine()) != null) {
			buffer.append(line);
		}
		
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);
		
		String location = jsonObject.getString("warehouse_id");
		
		ArrayList<Vendor> vendorList = inventoryVendorDAO.getAllVendorList(location);
		
		StringBuffer buffer2 = new StringBuffer();
		buffer2.append("<label>Select Supplier</label>");
		buffer2.append("<select class='form-control chosen' name='supplierId' id='supplierId' >");
		buffer2.append("<option value='0'>Select Supplier</option>");
		for (Vendor vendor : vendorList) {
			buffer2.append("<option value='" + vendor.getId() + "'>" + vendor.getData() + "</option>");
		}
		buffer2.append("</select>");
		
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("vendorList", buffer2.toString());
		
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+jsonObject2.toString()+"");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

}
