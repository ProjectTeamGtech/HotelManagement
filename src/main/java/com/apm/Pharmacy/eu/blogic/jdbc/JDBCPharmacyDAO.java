package com.apm.Pharmacy.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import com.apm.Accounts.eu.bi.ChargesAccountProcessingDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCChargeAccountProcesDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.bi.PrescriptionDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCPrescriptionDAO;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.bi.ProcurementDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCProcurementDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.bi.PrescriptionMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCPrescriptionMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Pharmacy.eu.bi.PharmacyDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;
import com.mysql.jdbc.ResultSetMetaData;

public class JDBCPharmacyDAO implements PharmacyDAO {

	Connection connection;
	
	public JDBCPharmacyDAO(Connection connection) {
		
		this.connection=connection;
	}
	
	public ArrayList<Priscription> getPriscListAvailablity(String selectedid) {
		PreparedStatement preparedStatement = null;
		
		ArrayList<Priscription>list = new ArrayList<Priscription>();
		PrescriptionMasterDAO prescriptionMasterDAO=new JDBCPrescriptionMasterDAO(connection);
		InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
		String sql = "SELECT id,lastmodified,mdicinename,dose,frequency,days,type,total,priscdurationtype,notes,mdicineid FROM apm_client_priscription where parentid = "+selectedid+" ";
		
		try{
			
			double subtotal=0;
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				
				if(rs.getString(2)!=null){
					priscription.setDate(DateTimeUtils.getDBDate(rs.getString(2)));
				}else{
					priscription.setDate("");
				}
				
				priscription.setLastmodified(rs.getString(2));
				priscription.setMdicinenametxt(rs.getString(3));
				priscription.setPriscdose(rs.getString(4));
				priscription.setPriscfreq(rs.getString(5));
				priscription.setPriscdays(rs.getString(6));
				priscription.setPrisctype(rs.getString(7));
				priscription.setPrisctotal(rs.getString(8));
				priscription.setPriscdurationtype(rs.getString(9));
				priscription.setDosenotes(rs.getString(10));
				priscription.setMdicinenameid(rs.getString(11));
				
				Priscription priscription2=prescriptionMasterDAO.getPrescriptionDetailsByName(priscription.getMdicinenametxt());
				priscription.setExpiry_date(priscription2.getExpiry_date());
				priscription.setPkg(priscription2.getPkg());
				priscription.setBatch_no(priscription2.getBatch_no());
				
				priscription.setMfg(getmfgmedicine(priscription.getMdicinenameid()));
				
				int count=0;
				
				for(String str:priscription.getPriscdose().split("-")){
					
					 if(!str.equals("0")){
						 
						 int t=Integer.parseInt(str);
						 count=count+t;
					 }
				}
				
				int days=Integer.parseInt(priscription.getPriscdays());
				
				priscription.setRequired(count*days);
				Product product=inventoryProductDAO.getMedicineProductDetails(priscription.getMdicinenameid());
				
				String expiry=DateTimeUtils.converToMMMYYYYforExp(product.getExpiry_date());
				priscription.setExpiry_date(expiry);
				priscription.setAvailable((product.getStock()));
				priscription.setMrp(product.getSale_price());
				priscription.setBatch_no(product.getBatch_no());
				priscription.setProduct_id(String.valueOf(product.getId()));
				
				/*String prodcode=product.getProduct_code();
				ArrayList<Priscription> batches=getAllBatchNo(prodcode);
			//	priscription.setBatches(batches);
*/				
				
				subtotal=subtotal+(Double.parseDouble(priscription.getMrp()));
				
				int t=Integer.parseInt(priscription.getAvailable());
				
				if(count>t){
					priscription.setSstatus(1);
				} else {
					priscription.setSstatus(0);
				}
				
				priscription.setSubtotal(subtotal);
				
				list.add(priscription);
				
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
		
	}

	public int addMedicineBill(CompleteAppointment appointment) {

		int result=0;
		
		try {
			String sql="insert into apm_medicine_bill (payby,date,discount,debit,vat,clientid,chargetype,"
					+ "notes,pclientid,priscid,balance,location,cgst,sgst,time,userid,refundamt,tpid,credit,"
					+ "oldparentid,newparentid,actual_total,actual_discount,disc_type,tempsessionid,grosstotal,"
					+ "grosssubtotal,initial_paymode,final_paymode,phar_ipdid,phar_bedid,phar_wardid,"
					+ "bill_practid,bill_practname,third_party_name_id) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, appointment.getPayBuy());
			ps.setString(2, appointment.getInvoiceDate());
			ps.setDouble(3, appointment.getDiscount());
			ps.setDouble(4, appointment.getTotal());
			ps.setDouble(5, appointment.getVat());
			ps.setString(6, appointment.getClientId());
			ps.setString(7, appointment.getChargeType());
			ps.setString(8, appointment.getNotes());
			ps.setString(9, appointment.getPclientid());
			ps.setInt(10, appointment.getPriscid());
			ps.setString(11, appointment.getBalance());
			ps.setString(12, appointment.getLocation());
			ps.setString(13, appointment.getCgst());
			ps.setString(14, appointment.getSgst());
			ps.setString(15, appointment.getInvoiceTime());
			ps.setString(16, appointment.getUserid());
			ps.setDouble(17, appointment.getRefundAmt());
			ps.setString(18, appointment.getTpid());
			ps.setString(19, appointment.getBalance());
			ps.setString(20, appointment.getOldparentid());
			ps.setString(21, ""+appointment.getNewparentid());
			ps.setString(22, appointment.getActualtotal());
			ps.setString(23, appointment.getActualdiscount());
			ps.setString(24, appointment.getDisc_type());
			ps.setString(25, ""+appointment.getTempsessionid());
			ps.setString(26, appointment.getGrosstotal());
			ps.setString(27, appointment.getGrosssubtotal());
			ps.setString(28, appointment.getInitalpaymode());
			ps.setString(29, appointment.getFinalpaymode());
			ps.setString(30, appointment.getPhar_ipdid());
			ps.setString(31, appointment.getBedid());
			ps.setString(32, appointment.getWardid());
			ps.setString(33, appointment.getPractitionerId());
			ps.setString(34, appointment.getPractitionerName());
			ps.setString(35, appointment.getThird_party_name_id());
			result=ps.executeUpdate();
			
			if(result>0){
				  
				  ResultSet rs=ps.getGeneratedKeys();
				  while(rs.next()){
					   
					    result=rs.getInt(1);
				  }
				  
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public int addMedicineCharges(Priscription priscription, int billno) {

		
		if(priscription.getBillno()!=null){
			 
			 if(priscription.getBillno().equals("")){
				  priscription.setBillno("0");
				  
			 }
		} else {
			 priscription.setBillno("0");
		}
		
		int result=0;
		try {
			
			String sql="insert into apm_medicine_charges (invoiceid,user,charge,"
					+ "practitionerId,practitionerName,clientId,commencing,paybuy,quantity,"
					+ "reqqty,medicineid,pclientid,thirdPartyId,product_id,cgst,sgst,returnbillno,"
					+ "discount_share,tgstamt,gstper,oldchargeid,isbarcodesale,"
					+ "bill_purpice,bill_pur_gst,freeqty,discount,orignal_charge) values " +
					"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1,billno);
			ps.setString(2, priscription.getClientname());
			ps.setString(3, priscription.getMrp());
			ps.setString(4, priscription.getPrectionerid());
			ps.setString(5, priscription.getFullname());
			ps.setString(6, priscription.getClientId());
			ps.setString(7, priscription.getDate());
			ps.setString(8, priscription.getWhopay());
			ps.setInt(9, priscription.getSaleqty());
			ps.setInt(10, priscription.getReqqty());
			ps.setString(11, priscription.getMdicinenameid());
			ps.setString(12, priscription.getPclientid());
			ps.setString(13, priscription.getTpid());
			ps.setString(14, priscription.getProduct_id());
			ps.setString(15, priscription.getCgst());
			ps.setString(16, priscription.getSgst());
			ps.setString(17, priscription.getBillno());
			ps.setString(18, priscription.getSharediscount());
			ps.setString(19, priscription.getTgstamt());
			ps.setString(20, priscription.getGstper());
			ps.setString(21, priscription.getChargeid());
			ps.setString(22, ""+priscription.getBarcodesale());
			ps.setString(23, ""+priscription.getBill_purpice());
			ps.setString(24, ""+priscription.getBill_pur_gst());
			ps.setInt(25, priscription.getFreeqty());
			ps.setString(26, priscription.getDiscount_perproduct());
			ps.setString(27, priscription.getOrignal_mrp());
			result= ps.executeUpdate();
			
			if(result>0){
				
				ResultSet rs=ps.getGeneratedKeys();
				while(rs.next()){
					  result =rs.getInt(1);
				}
			}
			
			
			String date = priscription.getDate();
			int openingstock = getOpeningStock(priscription.getProduct_id());
			boolean checkopningstockexist = checkopeningstockexist(priscription.getProduct_id(),date);
			if(!checkopningstockexist){
				int r = saveOpeningStock(priscription.getProduct_id(),date,openingstock,"0");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}
	
	// new method @ manoj sir
	public int getOpeningStock(String product_id) {
		  PreparedStatement preparedStatement = null;
		  int result = 0;
		  String sql = "SELECT stock FROM inventory_product where id = '"+product_id+"' ";
		  
		  try{
		   preparedStatement = connection.prepareStatement(sql);
		   ResultSet rs = preparedStatement.executeQuery();
		   if(rs.next()){
		    result = rs.getInt(1);
		   }
		   
		  }catch (Exception e) {
		   // TODO: handle exception
		  }
		   
		   
		  return result;
		 }
			public int saveOpeningStock(String product_id, String date,
			   double openingstock, String isconsume) {
			  PreparedStatement preparedStatement = null;
			  int result = 0;
			  String sql = "insert into medicine_opening_stock(prodid, commencing, stock,isconsume) values(?,?,?,?) ";
			  
			  try{
			   preparedStatement = connection.prepareStatement(sql);
			   preparedStatement.setString(1, product_id);
			   preparedStatement.setString(2, date);
			   preparedStatement.setDouble(3, openingstock);
			   preparedStatement.setString(4, isconsume);
			   result = preparedStatement.executeUpdate();
			   
			  }catch (Exception e) {
			   // TODO: handle exception
			  }
			  return result;
			 }

			 public boolean checkopeningstockexist(String product_id, String date) {
				  PreparedStatement preparedStatement = null;
				  boolean result = false;
				  String sql = "select * from medicine_opening_stock where commencing='"+date+"' and prodid="+product_id+" ";
				  
				  try{
				   preparedStatement = connection.prepareStatement(sql);
				   ResultSet rs = preparedStatement.executeQuery();
				   if(rs.next()){
				    result = true;
				   }
				   
				  }catch (Exception e) {
				   // TODO: handle exception
				  }
				  return result;
			 }
	
	public int addEstimateMedicineCharges(Priscription priscription, int billno) {

		int result=0;
		try {
			
			String sql="insert into apm_medicine_estimate_charges (invoiceid,user,charge,practitionerId,practitionerName,clientId,commencing,paybuy,quantity,reqqty,medicineid,pclientid,thirdPartyId,product_id) values " +
					"(?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1,billno);
			ps.setString(2, priscription.getClientname());
			ps.setString(3, priscription.getMrp());
			ps.setString(4, priscription.getPrectionerid());
			ps.setString(5, priscription.getFullname());
			ps.setString(6, priscription.getClientId());
			ps.setString(7, priscription.getDate());
			ps.setString(8, priscription.getWhopay());
			ps.setInt(9, priscription.getSaleqty());
			ps.setInt(10, priscription.getReqqty());
			ps.setString(11, priscription.getMdicinenameid());
			ps.setString(12, priscription.getPclientid());
			ps.setString(13, priscription.getTpid());
			ps.setString(14, priscription.getProduct_id());
			
			result=ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	

	public int updatePriscEmrBill(int billno, String selectedid) {
		
		int result=0;
		try {

			String sql="update apm_client_parent_priscription set billno="+billno+" where id="+selectedid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			result=ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public CompleteAppointment getBillDetails(int billno) {

		CompleteAppointment appointment=new CompleteAppointment();
		try {
			String sql="select id,payby,date,discount,debit,vat,clientid,chargetype,notes,isreturn,balance,"
					+ "pclientid,cgst,sgst,time,retunbillid,refundamt,tpid,location,userid,deleted,edited,"
					+ "edit_note,refundid,phar_ipdid,phar_bedid,phar_wardid,dummybillno,oldparentid,"
					+ "grosssubtotal,bill_practid,bill_practname,bill_edituserid, bill_editdatetime from apm_medicine_bill where id="+billno+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 
				appointment.setId(rs.getInt(1));
				appointment.setPayBuy(rs.getString(2));
				appointment.setInvoiceDate(rs.getString(3));
				appointment.setDiscount(rs.getDouble(4));
				appointment.setTotal(rs.getDouble(5));
				appointment.setVat(rs.getDouble(6));
				appointment.setClientId(""+rs.getInt(7));
				appointment.setChargeType(rs.getString(8));
				appointment.setNotes(rs.getString(9));
				appointment.setIsreturn(rs.getInt(10));
				appointment.setBalance(DateTimeUtils.changeFormat(rs.getDouble(11)));
				appointment.setPclientid(""+rs.getInt(12));
				appointment.setCgst(rs.getString(13));
				appointment.setSgst(rs.getString(14));
				appointment.setInvoiceTime(rs.getString(15));
				appointment.setReturnbillid(rs.getInt(16));
				appointment.setRefundAmt(rs.getDouble(17));
				if(rs.getString(18)!=null){
					if(!rs.getString(18).equals("")){
						appointment.setTpid(rs.getString(18)); 
					} else {
						appointment.setTpid("0");
					}
				} else {
					appointment.setTpid("0");
				}
				appointment.setLocation(rs.getString(19));
				appointment.setUserid(rs.getString(20));
				appointment.setDeleted(rs.getInt(21));
				appointment.setEdited(rs.getInt(22));
				appointment.setEdit_note(rs.getString(23));
				appointment.setRefundid(rs.getInt(24));
				appointment.setPhar_ipdid(""+rs.getInt(25));
				appointment.setBedid(""+rs.getString(26));
				appointment.setWardid(""+rs.getString(27));
				appointment.setDummybillno(rs.getInt(28));
				appointment.setOldparentid(""+rs.getInt(29));
				appointment.setGrosssubtotal(""+rs.getDouble(30));
				appointment.setPractitionerId(DateTimeUtils.isNull(rs.getString(31)));
				appointment.setPractitionerName(DateTimeUtils.isNull(rs.getString(32)));
				appointment.setBill_edituserid(rs.getString(33));
				if(!DateTimeUtils.isNull(rs.getString(34)).equals("")){
					appointment.setBill_editdatetime(DateTimeUtils.getCommencingDate1(rs.getString(34).split(" ")[0])+" "+rs.getString(34).split(" ")[1]);
				}
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return appointment;
	}
	public CompleteAppointment getEstimateBillDetails(String billno) {

		CompleteAppointment appointment=new CompleteAppointment();
		try {
			String sql="select id,payby,date,discount,debit,vat,clientid,chargetype,notes,isreturn,balance,pclientid,cgst,sgst,time,retunbillid,refundamt from apm_medicine_estimate_bill where id="+billno+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 
				appointment.setId(rs.getInt(1));
				appointment.setPayBuy(rs.getString(2));
				appointment.setInvoiceDate(rs.getString(3));
				appointment.setDiscount(rs.getDouble(4));
				appointment.setTotal(rs.getDouble(5));
				appointment.setVat(rs.getDouble(6));
				appointment.setClientId(rs.getString(7));
				appointment.setChargeType(rs.getString(8));
				appointment.setNotes(rs.getString(9));
				appointment.setIsreturn(rs.getInt(10));
				appointment.setBalance(rs.getString(11));
				appointment.setPclientid(rs.getString(12));
				appointment.setCgst(rs.getString(13));
				appointment.setSgst(rs.getString(14));
				appointment.setInvoiceTime(rs.getString(15));
				appointment.setReturnbillid(rs.getInt(16));
				appointment.setRefundAmt(rs.getDouble(17));
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return appointment;
	}

	public ArrayList<Priscription> getMedicineChargesList(int billno,LoginInfo loginInfo) {

		ArrayList<Priscription> list=new ArrayList<Priscription>();
		InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
		try {
			
			StringBuffer buffer=new StringBuffer();
			buffer.append("SELECT apm_medicine_charges.id,apm_medicine_charges.user,apm_medicine_charges.charge,apm_medicine_charges.practitionerId,apm_medicine_charges.practitionerName, ");
			buffer.append("apm_medicine_charges.clientId,apm_medicine_charges.commencing,apm_medicine_charges.paybuy,apm_medicine_charges.quantity, ");
			buffer.append("apm_medicine_charges.reqqty,apm_medicine_charges.medicineid,thirdPartyId,product_id,returnbillno ,tgstamt,discount,orignal_charge ");
			buffer.append("from apm_medicine_charges inner join inventory_product on inventory_product.id=apm_medicine_charges.product_id where invoiceid="+billno+" and apm_medicine_charges.quantity>0 ");
			buffer.append("order by inventory_product.prodname asc ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			 double subtotal=0;
			while(rs.next()){
				   
				  Priscription priscription=new Priscription();
				  priscription.setId(rs.getInt(1));
				  priscription.setClientname(rs.getString(2));
				  priscription.setMrp(DateTimeUtils.changeStringFormat(rs.getString(3)));
				  priscription.setPrectionerid(rs.getString(4));
				  priscription.setFullname(rs.getString(5));
				  priscription.setClientId(rs.getString(6));
				  priscription.setDate(rs.getString(7));
				  priscription.setWhopay(rs.getString(8));
				  priscription.setSaleqty(rs.getInt(9));
				  priscription.setReqqty(rs.getInt(10));
				  double total=Double.parseDouble(priscription.getMrp())*priscription.getSaleqty();
				
					/*
					 * double disc_amt=total*Double.parseDouble(rs.getString(16))/100;
					 * total=total-disc_amt;
					 */
					 
				  String str=DateTimeUtils.changeFormat(total);
				  priscription.setTotal(Double.parseDouble(str));
				  subtotal=subtotal+Double.parseDouble(str);
				  priscription.setSubtotal(Math.round(subtotal*100.0)/100.0);
				  priscription.setMdicinenameid(rs.getString(11));
				  priscription.setTpid(rs.getString(12));
				  priscription.setProduct_id(rs.getString(13));
				  String returnbillno= rs.getString(14);
				  priscription.setTtlgst(rs.getDouble(15));
				  priscription.setDiscount_perproduct(rs.getString(16));
				  priscription.setOrignal_mrp(rs.getString(17));
				  Product product=inventoryProductDAO.getProduct(priscription.getProduct_id());
				  if(returnbillno!=null){
					  
					   if(!returnbillno.equals("0")){
						   priscription.setMdicinenametxt(product.getProduct_name()+" ("+returnbillno+") ");
					   } else {
						   priscription.setMdicinenametxt(product.getProduct_name());
					   }
					  
				  } else {
					  priscription.setMdicinenametxt(product.getProduct_name());
				  }
				  //lokesh
				  
				  if(priscription.getMdicinenametxt()==null){
					  priscription.setMdicinenametxt("");
				  }
				  if(priscription.getMdicinenametxt().length()>=28){
					  priscription.setMdicinenametxt(priscription.getMdicinenametxt().substring(0, 26)+"..");
				  }
				  priscription.setBillno(String.valueOf(billno));
				  priscription.setBatch_no(product.getBatch_no());
				  if(DateTimeUtils.isNull(priscription.getBatch_no()).length()>8){
					  priscription.setBatch_no(priscription.getBatch_no().substring(0, 7)); 
				  }
				  
				  priscription.setHsnno(product.getHsnno());
				  if(priscription.getHsnno()==null){
					  priscription.setHsnno("");
				  }
				  if( DateTimeUtils.isNull(priscription.getHsnno()).length()>4){
					  priscription.setHsnno(priscription.getHsnno().substring(0,3));
				  }
				  
				  priscription.setPurchase_price(product.getPurchase_price());
				  priscription.setSale_price(product.getSale_price());
				  String prodcode=product.getProduct_code();
				  priscription.setMfg(product.getMfg());
				  
				  if(DateTimeUtils.isNull(priscription.getMfg()).length()>3){
					  priscription.setMfg(priscription.getMfg().substring(0,2));
				  }
				  priscription.setPkg("6");
				  priscription.setShelf(product.getShelf());
				  priscription.setVat(product.getVat());
				  priscription.setAvailable((product.getStock()));
				  
				  ArrayList<Priscription> batches=getAllBatchNo(prodcode);
				  priscription.setBatches(batches);
				  int pack=10;
				  if(product.getPack()!=null){
					  if(product.getPack().equals("")){
						  pack=10;
					  } else {
						  pack =Integer.parseInt(product.getPack());
					  }
				  } else {
					  pack=10;  
				  }
				  if(pack==0){
					  pack=10;  
				  }
				  
				  priscription.setPack(pack);
				  double vat= Double.parseDouble(product.getVat());
				  double dividevat=100+vat;
				  double gst= total*vat /dividevat;
				  double av=total-gst;
				  double half= gst/2;
				  priscription.setCgst(DateTimeUtils.changeFormat(half));
				  priscription.setSgst(DateTimeUtils.changeFormat(half));
				  priscription.setAv(DateTimeUtils.changeFormat(av));
				  double stock=0.0;
				  if(product.getStock()!="0"){
					  
					  if(!product.getStock().equals("")){
						   
						  stock=Double.parseDouble(product.getStock());
					  }
					 
				  }
				  if(stock<=10){
					  priscription.setSstatus(1);
				  }
				  
				  if(product.getExpiry_date()!=null){
					  String temp=product.getExpiry_date();
					  String expiry=DateTimeUtils.converToMMMYYYYforExp(temp);
					  priscription.setExpiry_date(expiry);
					  if(temp.contains("-")){
						  String dttemp[]= temp.split("-");
						  String dtyr[]=dttemp[0].split("");
						  String datayr=dttemp[0].substring(2,4);
						  priscription.setNweexp(dttemp[1]+"/"+datayr);
					  }
				  }else {
					priscription.setExpiry_date("N/A");
				  }
				  
				  priscription.setBroughtfwd(Math.round((priscription.getSubtotal()-priscription.getTotal())*100.0)/100.0);
				  priscription.setTotalstr(DateTimeUtils.setDoubleToTwoDigit(priscription.getTotal()));
				  String sheduler = getShedulerFromCatalogueId(product.getCatalogueid());
				  String freeqty =getfreeqty(priscription.getProduct_id(),billno);
				  if(loginInfo.isInfinyte()) {
					  priscription.setFreeqty(Integer.parseInt(freeqty) );
				  }
				  //priscription.setFreeqty(Integer.parseInt(freeqty) );
				  
				  priscription.setMedicine_shedule(sheduler);
				  list.add(priscription);
				  
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}
	private String getfreeqty(String product_id, int billno) {
		
		String freeqty="";
		try {
			String sql="select freeqty from apm_medicine_charges_log where billno="+billno+" and productid="+product_id+" ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs =preparedStatement.executeQuery();
			while (rs.next()) {
				freeqty = DateTimeUtils.isNull(rs.getString(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return freeqty;
		

	}

	private String getShedulerFromCatalogueId(String catalogueid) {
		String name="";
		try {
			String sql="select shedule from inventory_catalogue where id="+catalogueid+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs =preparedStatement.executeQuery();
			while (rs.next()) {
				name = DateTimeUtils.isNull(rs.getString(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	public ArrayList<Priscription> getEstimateMedicineChargesList(String billno) {

		ArrayList<Priscription> list=new ArrayList<Priscription>();
		InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
		try {
			
			String sql="SELECT id,user,charge,practitionerId,practitionerName,clientId,commencing,paybuy,quantity,reqqty,medicineid,thirdPartyId,product_id from apm_medicine_estimate_charges where invoiceid="+billno+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				   
				  Priscription priscription=new Priscription();
				  priscription.setId(rs.getInt(1));
				  priscription.setClientname(rs.getString(2));
				  priscription.setMrp(DateTimeUtils.changeStringFormat(rs.getString(3)));
				  priscription.setPrectionerid(rs.getString(4));
				  priscription.setFullname(rs.getString(5));
				  priscription.setClientId(rs.getString(6));
				  priscription.setDate(rs.getString(7));
				  priscription.setWhopay(rs.getString(8));
				  priscription.setSaleqty(rs.getInt(9));
				  priscription.setReqqty(rs.getInt(10));
				  
				  double total=Double.parseDouble(priscription.getMrp())*priscription.getSaleqty();
				  
				  String str=DateTimeUtils.changeFormat(total);
				  priscription.setTotal(Double.parseDouble(str));
				  priscription.setMdicinenameid(rs.getString(11));
				  priscription.setTpid(rs.getString(12));
				  priscription.setProduct_id(rs.getString(13));
				  
				  Product product=inventoryProductDAO.getProduct(priscription.getProduct_id());
				  priscription.setMdicinenametxt(product.getProduct_name());
				  priscription.setBillno(String.valueOf(billno));
				  priscription.setBatch_no(product.getBatch_no());
				  priscription.setHsnno(product.getHsnno());
				  priscription.setSale_price(product.getSale_price());
				  String prodcode=product.getProduct_code();
				  int pack=10;
				  
				  if(product.getPack()!=null){
					  if(product.getPack().equals("")){
						  pack=10;
					  } else {
						  pack =Integer.parseInt(product.getPack());
					  }
				  } else {
					  pack=10;  
				  }
				  
				  priscription.setPack(pack);
				  priscription.setPurchase_price(product.getPurchase_price());
				  ArrayList<Priscription> batches=getAllBatchNo(prodcode);
				  priscription.setBatches(batches);
				  
				  priscription.setMfg(product.getMfg());
				  priscription.setPkg("6");
				  priscription.setShelf(product.getShelf());
				  priscription.setVat(product.getVat());
				  priscription.setAvailable((product.getStock()));
				  double stock=0.0;
				  if(product.getStock()!="0"){
					  
					  if(!product.getStock().equals("")){
						   
						  stock=Integer.parseInt(product.getStock());
					  }
					  
				  }
				  if(stock<=10){
					  priscription.setSstatus(1);
				  }
				  
				  if(product.getExpiry_date()!=null){
					  String temp=product.getExpiry_date();
					  String expiry=DateTimeUtils.converToMMMYYYYforExp(temp);
					  priscription.setExpiry_date(expiry);
				  }else {
					priscription.setExpiry_date("N/A");
				  }
				  
				  list.add(priscription);
				  
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}

	private ArrayList<Priscription> getAllBatchNo(String prodcode) {

		
		ArrayList<Priscription> list=new ArrayList<Priscription>();
		try {
			
			String sql="select id,batch_no from inventory_product where prodcode='"+prodcode+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				Priscription priscription=new Priscription();
				
				priscription.setBatch_no(rs.getString(2));
				list.add(priscription);
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}

	public int getTotalProcessingChargesCount(String clientid,
			String transactionType, String fromDate, String toDate) {

		int result=0;
		try {
			String sql="";
			 if(transactionType.equals("All")){
				 
				  sql="select count(*) from apm_medicine_bill where clientid="+clientid+" and date between '"+fromDate+"' and '"+toDate+"'"; 
			 } else if(transactionType.equals("Pending Payment")) {
				  sql="select count(*) from apm_medicine_bill where clientid="+clientid+" and date between '"+fromDate+"' and '"+toDate+"'";
			 } else {
				  sql="select count(*) from apm_medicine_bill where clientid="+clientid+" and date between '"+fromDate+"' and '"+toDate+"'";
			 }
			  
			PreparedStatement ps=connection.prepareStatement(sql);   
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				 result=rs.getInt(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Accounts> getAllProcessingCharges(Pagination pagination,
			String clientid, String transactionType, String fromDate,
			String toDate) {

		ArrayList<Accounts> list=new ArrayList<Accounts>();
		
		String sql="";
		try {
			
			 if(transactionType.equals("All")){
				  sql="select id,payby,date,clientid,debit,deliverstatus,discount from apm_medicine_bill where clientid="+clientid+" and date between '"+fromDate+"' and '"+toDate+"' order by id desc "; 
			 } else if(transactionType.equals("Pending Payment")) {
				  sql="select id,payby,date,clientid,debit,deliverstatus,discount from apm_medicine_bill where clientid="+clientid+" and date between '"+fromDate+"' and '"+toDate+"' order by id desc ";
			 } else {
				  sql="select id,payby,date,clientid,debit,deliverstatus,discount from apm_medicine_bill where clientid="+clientid+" and date between '"+fromDate+"' and '"+toDate+"' order by id desc ";
			 }
			
			 sql=pagination.getSQLQuery(sql);
			 PreparedStatement ps=connection.prepareStatement(sql);
			 double result=0; 
			 ResultSet rs=ps.executeQuery();
			 while(rs.next()){
				  
				   result = 0;
					
					Accounts accounts = new Accounts();
					accounts.setId(rs.getInt(1));
					accounts.setPayby(rs.getString(2));
					accounts.setDate(DateTimeUtils.changeDateFormatToddmmyyyy(rs.getString(3)));
					accounts.setClientid(rs.getInt(4));
					accounts.setDebitAmount(rs.getDouble(5));
					accounts.setDeliverstatus(rs.getString(6));
					
			
					double debit = rs.getDouble(5);
					double total = rs.getDouble(5);
					double discount = rs.getDouble(7);
					double r1 = (total*discount)/100;
					total = total-r1;
					result = result + total;
					
					double creditAmount = getCreditAmount(rs.getDouble(1));
					accounts.setCreditCharge(DateTimeUtils.changeFormat(creditAmount));
					double balance = result - creditAmount;
					accounts.setBalance(balance);
					int noOfCharges = getNoOfCharges(rs.getInt(1));
					accounts.setNumberOfChages(noOfCharges);
					accounts.setDiscount(discount);
					
					//Decimal Amount
					accounts.setDebitAmountx(DateTimeUtils.changeFormat(rs.getDouble(5)));
					accounts.setBalancex(DateTimeUtils.changeFormat(balance));
					accounts.setDiscountx(Double.toString(discount));
					
					list.add(accounts);
				 
			 }
			 
			 
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}
	
	
	private double getCreditAmount(double id) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT sum(payment) FROM apm_medicine_payment where billno = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private int getNoOfCharges(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT count(id) FROM apm_charges_payment where chargeinvoiceid = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int recordPaymentMedicine(String billno, double payAmount1,
			String howpaid, String invoiceDate, String clientId, String ccdamt,
			double discount,String paymentnote,String pclientid,String userid,String location, int issame, int paymentseqno) {

		int result=0;
		
		try {

			String sql="insert into apm_medicine_payment (clientid, billno, payment, paymode, date, tpid, paymentnote, crdinvoiceid,pclientid,userid,location,onsamedate,paymentseqno) values " +
					"(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement ps=connection.prepareStatement(sql);
			String datetime=DateTimeUtils.getUKCurrentDataTime("IST");
			ps.setString(1, clientId);
			ps.setString(2, billno);
			ps.setDouble(3, payAmount1);
			ps.setString(4, howpaid);
			ps.setString(5, invoiceDate);
			ps.setString(6, "0");
			ps.setString(7, paymentnote);
			ps.setString(8, "0");	
			ps.setString(9,pclientid);
			ps.setString(10, userid);
			ps.setString(11, location);
			ps.setString(12, ""+issame);
			ps.setString(13, ""+paymentseqno);
			//ps.setString(14, ""+datetime);
			result=ps.executeUpdate();
			
			if(result>0){
				ResultSet rs= ps.getGeneratedKeys();
				if(rs.next()){
					result =rs.getInt(1);
				}
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public int recordEstimatePaymentMedicine(String billno, double payAmount1,
			String howpaid, String invoiceDate, String clientId, String ccdamt,
			double discount,String paymentnote,String pclientid,String userid,String location) {

		int result=0;
		
		try {

			String sql="insert into apm_medicine_estimate_payment (clientid, billno, payment, paymode, date, tpid, paymentnote, crdinvoiceid,pclientid,userid,location) values " +
					"(?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, clientId);
			ps.setString(2, billno);
			ps.setDouble(3, payAmount1);
			ps.setString(4, howpaid);
			ps.setString(5, invoiceDate);
			ps.setString(6, "0");
			ps.setString(7, paymentnote);
			ps.setString(8, "0");	
			ps.setString(9,pclientid);
			ps.setString(10, userid);
			ps.setString(11, location);
			result=ps.executeUpdate();
			
			if(result>0){
				ResultSet rs= ps.getGeneratedKeys();
				if(rs.next()){
					result =rs.getInt(1);
				}
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	
	public double getTotalPaymentReceived(int invoiceid) {
		PreparedStatement preparedStatement = null;
		double result = 0.0;
		String sql = "SELECT sum(payment) FROM apm_medicine_payment where billno ="+invoiceid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	
	public ArrayList<Accounts> getAssesmentList(int invoiceid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		sql.append("select invoiceid,paybuy,commencing from apm_medicine_charges where ");
		sql.append("invoiceid = "+invoiceid+" group by invoiceid ");
		double sumTotal = 0;
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setInvoiceNo(rs.getString(1));
				String invoiceId = rs.getString(1);
				double total = getAmountTotal(invoiceId);
				accounts.setTotalAmount(total);
				sumTotal = sumTotal + total;
				if(rs.getString(3)!=null){
					if(!rs.getString(3).equals("")){
						accounts.setDate(DateTimeUtils.changeDateFormatToddmmyyyy(rs.getString(3)));
					}else{
						accounts.setDate("");
					}
				}else{
					accounts.setDate("");
				}
				
				accounts.setDebitTotal(sumTotal);
				accounts.setPayBy(rs.getInt(2));
				//Decimal Amount
				accounts.setTotalAmountx(DateTimeUtils.changeFormat(total));
				accounts.setDebitTotalx(DateTimeUtils.changeFormat(sumTotal));
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}

	
	public double getDiscount(int invoiceid) {
		double discount = 0;
		PreparedStatement preparedStatement = null;
		
		String sql = "select discount from apm_medicine_bill where id = "+invoiceid+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				discount = rs.getInt(1);
				}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return discount;

	}
	
	public String getmfgmedicine(String medicineid){

		String mfg="";
		try {
			String sql="select mfg from apm_medicine_details where id="+medicineid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 
				mfg=rs.getString(1);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return mfg;
	}
	
	
	
	
	private double getAmountTotal(String invoiceId) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT charge,quantity FROM apm_medicine_charges  where invoiceid = "+invoiceId+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				double charge = rs.getDouble(1) * rs.getInt(2);
				result = result + charge;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int updateDeliverStatus(String status, String priscid) {

		int result=0;
		try {
			
			String sql="update apm_client_parent_priscription set delivered="+status+" where id="+priscid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			
			result=ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public int addNewPharmacyPatient(Client client) {

		int result=0;
		try {
			
			String sql="insert into apm_pharmacy_client (fullname, address, reference, age, gender, date,mobile,pmbalance,abrivationid) values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, client.getClientName());
			ps.setString(2, client.getAddress());
			ps.setString(3, client.getReference());
			ps.setInt(4, client.getAge());
			ps.setString(5, client.getGender());
			ps.setString(6, client.getLastModified());
			ps.setString(7, client.getMobNo());
			ps.setString(8, client.getBalance());
			ps.setString(9, client.getAbrivationid());
			result=ps.executeUpdate();
			
			if(result>0){
				ResultSet rs=ps.getGeneratedKeys();
				
				while(rs.next()){
					 result=rs.getInt(1);
				}
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public int updateBillandStatus(int pharmacyclientid, int billno,
			String status) {

		int result=0;
		try {
			
			String sql="update apm_pharmacy_client set billno="+billno+",deliverstatus="+status+" where id="+pharmacyclientid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			
			result=ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public Priscription getPharmacyPatientDetails(int billno) {
		
		Priscription priscription=new Priscription();
		
		try {
			
			String sql="select id, fullname, address, reference, age, gender, date,billno,deliverstatus,mobile from apm_pharmacy_client where billno="+billno+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				priscription.setPclientid(""+rs.getInt(1)+"");
				priscription.setFullname(rs.getString(2));
				priscription.setAddress(rs.getString(3));
				priscription.setPractitionername(rs.getString(4));
				String agegender=rs.getString(6)+" "+rs.getString(5);
				priscription.setAgeandgender(agegender);
				priscription.setLastmodified(rs.getString(7));
				priscription.setBillno(rs.getString(8));
				priscription.setDelivered(rs.getInt(9));
				priscription.setMobile(rs.getString(10));
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return priscription;
	}

	public int addnewRequestStock(String pid, String qty, String date,String loc) {

		int result=0;
		InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
		try {
			
			Product product=inventoryProductDAO.getProduct(pid);
			String sql="insert into apm_medicine_request (product_id, name, quantity, date,location) values (?,?,?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, pid);
			ps.setString(2, product.getProduct_name());
			ps.setString(3, qty);
			ps.setString(4, date);
			ps.setString(5, loc);
			result=ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Product> getAllRequestedMedicine(String fromdate,
			String todate) {

		ArrayList<Product> list=new ArrayList<Product>();
		
		try {
			String sql="select id,date from apm_medicine_request where date between '"+fromdate+"' and '"+todate+"' order by id desc";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery(); 
			
			while(rs.next()){
				 Product product=new Product();
				 product.setId(rs.getInt(1));
				 product.setLastmodified(DateTimeUtils.getCommencingDate1(rs.getString(2)));
				 ArrayList<Priscription> requestedMedicineList=getrequestedMedicineonDate(rs.getString(2));
				 product.setRequestedMedicineList(requestedMedicineList);
				 list.add(product);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}
	
	
	private ArrayList<Priscription> getrequestedMedicineonDate(String date) {
		
		ArrayList<Priscription> list=new ArrayList<Priscription>();
		
		try {
			String sql="select name,quantity from apm_medicine_request where date ='"+date+"' order by id desc";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				Priscription priscription=new Priscription();
				priscription.setName(rs.getString(1));
				priscription.setReqqty(rs.getInt(2));
				list.add(priscription); 	
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		} 
		
		return list;
	}

	public int updateMedicineBill(CompleteAppointment appointment, int billno) {

		int result=0;
		try {
			
			String sql="update apm_medicine_bill set discount=?,debit=?,notes=?,vat=? where id="+billno+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setDouble(1,appointment.getDiscount());
			ps.setDouble(2,appointment.getTotal());
			ps.setString(3, appointment.getNotes());
			ps.setDouble(4, appointment.getVat());
			
			result=ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public int updateMedicineCharges(Priscription priscription) {

		int result=0;
		try {
			
			String sql="update apm_medicine_charges set quantity=?,commencing=? where id="+priscription.getId()+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			
			ps.setInt(1, priscription.getSaleqty());
			ps.setString(2, priscription.getDate());
			
			result=ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}
	
	public String getPharmacyClientidFromBill(int billno) {

		String clietid="0";
		try {
			
			String sql="select pclientid from apm_medicine_bill where id="+billno+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				  
				clietid=rs.getString(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		} 
		
		return clietid;
	}
	

public Priscription getPharmacyPatient(String pclientid) {
		
		Priscription priscription=new Priscription();
		
		try {
			
			String sql="select id, fullname, address, reference, age, gender, date,billno,deliverstatus,mobile,pmbalance,abrivationid,phar_drid,gstin,dlno from apm_pharmacy_client where id="+pclientid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				priscription.setPclientid(""+rs.getInt(1)+"");
				priscription.setFullname(rs.getString(2));
				priscription.setAddress(rs.getString(3));
				priscription.setPractitionername(rs.getString(4));
				String agegender=rs.getString(6)+" / "+rs.getString(5);
				priscription.setAgeandgender(agegender);
				priscription.setAge(rs.getString(5));
				priscription.setGender(rs.getString(6));
				priscription.setLastmodified(rs.getString(7));
				priscription.setBillno(rs.getString(8));
				priscription.setDelivered(rs.getInt(9));
				priscription.setMobile(rs.getString(10));
				priscription.setBalance(rs.getString(11));
				priscription.setAbrivationid(rs.getString(12));
				priscription.setPractitionerid(rs.getString(13));
				priscription.setGstin(rs.getString(14));
				priscription.setDlno(rs.getString(15));
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return priscription;
	}


	public int addPriscriptionForBill(Priscription priscription){
		
		int result=0;
		try {
			
			String sql="insert into apm_client_parent_priscription (clientid, practitionerid, conditionid, dosenotes, followupcount, followupstype, advoice, english, regional, hindi, prepay, postpay, other, lastmodified, ipdid, followupdate, discharge, dstatus, department, billno, estimate, delivered) " +
					"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1,  priscription.getClientId());
			ps.setString(2,  priscription.getPrectionerid());
			ps.setString(3,  priscription.getConditionid());
			ps.setString(4,  priscription.getDosenotes());
			ps.setString(5,  priscription.getFollowupsqty());
			ps.setString(6,  priscription.getFollowupstype());
			ps.setString(7,  priscription.getAdvoice());
			ps.setString(8,  priscription.getEnglish());
			ps.setString(9,  priscription.getRegional());
			ps.setString(10, priscription.getHindi());
			ps.setString(11, priscription.getPrepay());
			ps.setString(12, priscription.getPostpay());
			ps.setString(13, priscription.getOtherpay());
			ps.setString(14, priscription.getLastmodified());
			ps.setString(15, priscription.getIpdid());
			ps.setString(16, priscription.getFollowupdate());
			ps.setString(17, "0");
			ps.setString(18, "0");
			ps.setString(19, priscription.getDepartment());
			ps.setString(20, "0");
			ps.setString(21, "0");
			ps.setString(22, "0");
			
			result=ps.executeUpdate();
			
			ResultSet rs=ps.getGeneratedKeys();
			while(rs.next()){
				 
				result=rs.getInt(1);
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
		
	}

	public String getBillPaymode(int billno) {


		String paymode="";
		try {
			String sql="select paymode from apm_medicine_payment where billno="+billno+" order by id asc limit 0,1 ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 paymode=rs.getString(1);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return paymode;
	}

	public int addUpdateMedicineRefund(int billno, double reftot,String date) {

		int result=0;
		int id=0;
		try {
			String sql="SELECT id from apm_medicine_refund where billno="+billno+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
					id=rs.getInt(1);
			}
			
			if(id>0){
				
				sql="update apm_medicine_refund set refundamt=?,date=? where id="+id+"";
				PreparedStatement ps1=connection.prepareStatement(sql);
				ps1.setDouble(1, reftot);
				ps1.setString(2, date);
				
				result=ps1.executeUpdate();
			} else {
				sql="insert into apm_medicine_refund (billno, date, refundamt) values (?,?,?) ";
				PreparedStatement ps1=connection.prepareStatement(sql);
				ps1.setInt(1, billno);
				ps1.setString(2,date);
				ps1.setDouble(3, reftot);
				result=ps1.executeUpdate();
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public double getRefundAmt(int billno) {

		double refund=0;
		try {
			String sql="select refundamt from apm_medicine_refund where billno="+billno+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				  
				refund=rs.getDouble(1);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return refund;
	}

	public ArrayList<Priscription> getReportPharmacyList(String searchtext,
			String fromdate, String todate, String from, String paymode,
			String report,String isreturn,String loca) {

		
		boolean oneflag=false;
		EmrDAO emrDAO=new JDBCEmrDAO(connection);
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		BedDao bedDao=new JDBCBedDao(connection);
		IpdDAO ipdDAO=new JDBCIpdDAO(connection);
		ArrayList<Priscription> list=new ArrayList<Priscription>();
		
		try {
			
			StringBuffer buffer=new StringBuffer();
			buffer.append("SELECT apm_medicine_bill.id,apm_medicine_bill.date,debit,discount ");
			buffer.append(",apm_medicine_bill.clientid,apm_medicine_bill.pclientid,priscid,paymode from apm_medicine_bill ");
			buffer.append("inner join apm_medicine_payment on apm_medicine_bill.id=apm_medicine_payment.billno ");
			
			if(searchtext!=null){
				
		   		    buffer.append("inner join apm_pharmacy_client on apm_medicine_bill.pclientid=apm_pharmacy_client.id where fullname like('"+searchtext+"%') or fullname like ('%"+searchtext+"') or apm_medicine_bill.id='"+searchtext+"'  ");
	    		   oneflag=true;
				
			}
			
			if(from!=null){
				if(from.equals("IPD")){
					
					oneflag=true;
					buffer.append("inner join apm_client_parent_priscription on apm_client_parent_priscription.id = apm_medicine_bill.priscid ");
					buffer.append(" where apm_client_parent_priscription.ipdid>0 ");
					
				} else if(from.equals("OPD")){
					 oneflag=true;
					 buffer.append("inner join apm_client_parent_priscription on apm_client_parent_priscription.id = apm_medicine_bill.priscid ");
					 buffer.append(" where apm_client_parent_priscription.ipdid is NULL ");
				}
				
			}
			
			
			if(oneflag){
				buffer.append("and  apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' ");
				if(paymode!=null){
					buffer.append("and paymode='"+paymode+"' ");
				}
			} else {
				buffer.append("where apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' ");
				if(paymode!=null){
					buffer.append("and paymode='"+paymode+"' ");
				}
			}
			
			if(from!=null){
				 
				if(from.equals("NEW")){
					 buffer.append("and apm_medicine_bill.pclientid!=0 ");
				} 
				
			}
			
			if(isreturn.equals("1")){
				buffer.append("and apm_medicine_bill.isreturn=1 ");
			}
			

			if(!loca.equals("0")){
				buffer.append("and apm_medicine_bill.location='"+loca+"' ");
			}
			
			buffer.append("and apm_medicine_bill.tpid='0' ");
			
			buffer.append("group by apm_medicine_bill.pclientid,apm_medicine_bill.clientid ");
			
			/*if(from==null){
				
				//buffer.append("group by apm_medicine_bill.clientid ");
			} else if(from.equals("NEW")){
				
				buffer.append("group by apm_medicine_bill.pclientid ");
			} else {
				buffer.append("group by apm_medicine_bill.clientid ");
			}*/
			
			
			buffer.append("order by apm_medicine_bill.id desc limit 0,20 ");
			
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			double balance=0.0;
			double cardAmt=0;
			
			while(rs.next()){
				
				Priscription priscription=new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setBillno(rs.getString(1));
				priscription.setDate(DateTimeUtils.getCommencingDate1(rs.getString(2)));
				priscription.setDebit(rs.getDouble(3));
				priscription.setDiscount(rs.getDouble(4));
				priscription.setClientId(rs.getString(5));
				priscription.setPclientid(rs.getString(6));
				int selectedid=rs.getInt(7);
				/*String payment1 = rs.getString(8);
				if(payment1.equals("Online")){
					priscription.setPaymode("Card");
				}else {
					priscription.setPaymode(rs.getString(8));
				}*/
				priscription.setPaymode(rs.getString(8));
				
				if(rs.getInt(6)>0){
					
					Priscription pharmacyPatient= getPharmacyPatient(priscription.getPclientid());
					priscription.setFullname(pharmacyPatient.getFullname());
					priscription.setWardname(pharmacyPatient.getAddress());
					
					priscription.setOutp(1);
					String sexage=pharmacyPatient.getAgeandgender();
					priscription.setAgeandgender(sexage);
					priscription.setMobile(pharmacyPatient.getMobile());
					priscription.setBalance(pharmacyPatient.getBalance());
					balance = Double.parseDouble(pharmacyPatient.getBalance());
					
					cardAmt= getCardPaymentAmt(fromdate,todate,priscription.getPclientid(),1);
					if(cardAmt>0){
						priscription.setHasCardPay(1);
						priscription.setCardAmount(DateTimeUtils.changeFormat(cardAmt));
						priscription.setPaymode("Cash/Card");
					}
					
					
				} else {
					
					Client client= clientDAO.getClientDetails(priscription.getClientId());
					String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
					priscription.setMobile(client.getMobNo());
					Calendar calendar=Calendar.getInstance();
					SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
					String date  =dateFormat.format(calendar.getTime());
					try {
						String dobyear[] = client.getDob().split("/");
						String curryear[] = date.split("/");
						int  age = Integer.parseInt(curryear[2]) - Integer.parseInt(dobyear[2]);
						
						client.setAge(age);
					} catch (Exception e) {
					}
					String sexage=client.getGender()+client.getAge();
					priscription.setAgeandgender(sexage);
					String ipdid = emrDAO.getpriscIpdId(selectedid);
					balance = Double.parseDouble(client.getBalance());
					if(ipdid==null){
						ipdid="";
					}
					if(ipdid.equals("") || ipdid.equals("0") ) {
						
						//opd patient
						priscription.setIpdid("0");
						String loc=client.getTown();
						priscription.setFullname(fullname);
						priscription.setWardname(loc);
						priscription.setBalance(client.getBalance());
					} else {
						//ipd patient
						priscription.setIpdid(ipdid);
						Bed bed = bedDao.getEditIpdData(ipdid);
						String wardname= ipdDAO.getIpdWardName(bed.getWardid());
						String bedname = ipdDAO.getIpdBedName(bed.getBedid());
						priscription.setFullname(fullname);
						String location=wardname+"/"+bedname;
						priscription.setWardname(location);
						priscription.setBalance(client.getBalance());
					}
					
					 cardAmt= getCardPaymentAmt(fromdate,todate,priscription.getClientId(),0);
					if(cardAmt>0){
						priscription.setHasCardPay(1);
						priscription.setCardAmount(DateTimeUtils.changeFormat(cardAmt));
						priscription.setPaymode("Cash/Card");
					}
					
				}
				
				double totalamt=0,totaldisc=0,totalrefund=0;
				if(rs.getInt(6)>0){
						totalamt= getTotalDebitAmount(priscription.getPclientid(),fromdate,todate,false);
						totaldisc= getTotalDiscountAmount(priscription.getPclientid(),fromdate,todate,false);
						totalrefund= getTotalRefundAmount(priscription.getPclientid(),fromdate,todate,false);
				} else {
					    totalamt= getTotalDebitAmount(priscription.getClientId(),fromdate,todate,true);
					    totaldisc= getTotalDiscountAmount(priscription.getClientId(),fromdate,todate,true);
					    totalrefund= getTotalRefundAmount(priscription.getClientId(),fromdate,todate,true);
				} 
				double temp=totalamt-(totaldisc-totalrefund);
				if(temp<0){
					temp=0;
				}
				priscription.setTotalamt(temp);
				priscription.setTotaldisc(totaldisc);
				priscription.setTotalrefund(totalrefund);
						
				list.add(priscription);
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}

	public double getCardPaymentAmt(String fromdate, String todate,
			String clientId,int res) {

		double sum=0;
		try {
			
			StringBuffer sql= new StringBuffer();
			sql.append("select sum(payment) from apm_medicine_payment ");
			if(res==1){
				sql.append("where pclientid="+clientId+" ");
			} else {
				sql.append("where clientid="+clientId+" ");
			}
			sql.append("and paymode='Card' and date between '"+fromdate+"' and '"+todate+"' ");
				
			PreparedStatement ps=connection.prepareStatement(sql.toString());
			ResultSet rs =ps.executeQuery();
			
			while(rs.next()){
				 
				sum =rs.getDouble(1);
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return sum;
	}

	private double getTotalRefundAmount(String clientid, String fromdate,
			String todate,boolean flag) {

		double tot=0;
		
		try {
			
			StringBuffer buffer=new StringBuffer();
			buffer.append("select sum(debit) from apm_medicine_bill where ");
			if(flag){
				buffer.append("where clientid="+clientid+" ");
			} else {
				buffer.append("where pclientid="+clientid+" ");
			}
			
			buffer.append("and apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"';");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 
				tot=rs.getDouble(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return tot;
	}

	private double getTotalDebitAmount(String clientId, String fromdate,
			String todate,boolean flag) {
		
		double tot=0;
		try {
			  StringBuffer sql=new StringBuffer();
			
				    sql.append("SELECT sum(payment) from apm_medicine_payment where ");
				    if(flag){  
				    	 sql.append("clientid="+clientId+" and date between '"+fromdate+"' and '"+todate+"' ");
				    } else {
				    	sql.append("pclientid="+clientId+" and date between '"+fromdate+"' and '"+todate+"' ");
				    }
			PreparedStatement ps=connection.prepareStatement(sql.toString());
			ResultSet rs=ps.executeQuery();	    
			while(rs.next()){
				
					tot=rs.getDouble(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return tot;
	}


	private double getTotalDiscountAmount(String clientId, String fromdate,
			String todate,boolean flag) {
		
		double tot=0;
		try {
			  StringBuffer sql=new StringBuffer();
			
				    sql.append("SELECT discount from apm_medicine_bill where ");
				    if(flag){  
				    	 sql.append("clientid="+clientId+" and date between '"+fromdate+"' and '"+todate+"' ");
				    } else {
				    	sql.append("pclientid="+clientId+" and date between '"+fromdate+"' and '"+todate+"' ");
				    }
			PreparedStatement ps=connection.prepareStatement(sql.toString());
			ResultSet rs=ps.executeQuery();	    
			while(rs.next()){
				
					double debit=rs.getDouble(1);
					tot=tot+debit;
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return tot;
	}

	public ArrayList<Priscription> getAllBillListofClient(String clientid,
			String flag, String fromdate, String todate) {

		ArrayList<Priscription> list=new ArrayList<Priscription>();
		try {
			StringBuffer buffer=new StringBuffer();
			buffer.append("SELECT apm_medicine_bill.id,apm_medicine_bill.date from apm_medicine_bill ");
			buffer.append("inner join apm_medicine_payment on apm_medicine_bill.id=apm_medicine_payment.billno where ");
			if(flag.equals("1")){
				buffer.append("apm_medicine_bill.pclientid ="+clientid+" and ");
			} else {
				buffer.append("apm_medicine_bill.clientid ="+clientid+" and ");
			}
			
			buffer.append("apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' ");
			buffer.append("group by apm_medicine_bill.id desc ");
			
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 
				  Priscription priscription=new Priscription();
				  priscription.setId(rs.getInt(1));
				  priscription.setBillno(rs.getString(1));
				  priscription.setDate(rs.getString(2));
				  list.add(priscription);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}

	
	public String getwardlocationofClient(String clientid) {
		
		String str="";
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		IpdDAO ipdDAO=new JDBCIpdDAO(connection);
		BedDao bedDao=new JDBCBedDao(connection);
		try {
			
			String sql="SELECT ipdid from apm_client_parent_priscription where clientid="+clientid+" order by id desc ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 
				 if(rs.getInt(1)>0){
					 
					 Bed bed=bedDao.getEditIpdData(rs.getString(1));
					 String wardname=ipdDAO.getIpdWardName(bed.getWardid());
					 String bedname=ipdDAO.getIpdBedName(bed.getBedid());
					 str=wardname+"/"+bedname;
					 
				 } else {
					 
					  Client client=clientDAO.getClientDetails(clientid);
					  str=client.getTown();
				 }
				
				 break;
			}
		}catch (Exception e) {

			e.printStackTrace();
		}
		
		return str;
	}

	public int getIpdPatientSaleCount(String fromdate,String todate) {

		int result=0;
		
		try {

			StringBuffer buffer=new StringBuffer();
			buffer.append("SELECT count(*) from apm_medicine_bill ");
			buffer.append("inner join apm_medicine_payment on apm_medicine_bill.id=apm_medicine_payment.billno ");
			buffer.append("inner join apm_client_parent_priscription on apm_medicine_bill.priscid=apm_client_parent_priscription.id ");
			buffer.append("where apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' and apm_client_parent_priscription.ipdid>0 ");
			
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			
			while(rs.next() ){
				result=rs.getInt(1);	
			} 
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public int getOpdPatientSaleCount(String fromdate, String todate) {
		int result=0;
		
		try {

			StringBuffer buffer=new StringBuffer();
			buffer.append("SELECT count(*) from apm_medicine_bill ");
			buffer.append("inner join apm_medicine_payment on apm_medicine_bill.id=apm_medicine_payment.billno ");
			buffer.append("inner join apm_client_parent_priscription on apm_medicine_bill.priscid=apm_client_parent_priscription.id ");
			buffer.append("where apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' and apm_client_parent_priscription.ipdid is NULL ");
			
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			
			while(rs.next() ){
				result=rs.getInt(1);	
			} 
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public int getNewpatientSaleCount(String fromdate, String todate) {
		
		int result=0;
		
		try {

			StringBuffer buffer=new StringBuffer();
			buffer.append("SELECT count(*) from apm_medicine_bill ");
			buffer.append("inner join apm_medicine_payment on apm_medicine_bill.id=apm_medicine_payment.billno ");
			buffer.append("where apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' and apm_medicine_bill.pclientid>0 ");
			
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			
			while(rs.next() ){
				result=rs.getInt(1);	
			} 
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public ArrayList<Product> getAllRequestedMedicine() {
		
		ArrayList<Product> list=new ArrayList<Product>();
		InventoryProductDAO inventoryProductDAO= new JDBCInventoryProductDAO(connection);
		try {
			String sql="SELECT id, product_id, name, quantity, date from apm_medicine_request where status=0 group by product_id ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 Product product=new Product();
				 product.setId(rs.getInt(1));
				 product.setMedicinenameid(rs.getString(2));
				 product.setName(rs.getString(3));
				 product.setQuantity(rs.getString(4));
				 product.setProduct_id(rs.getString(2));
				// String genericname=masterDAO.getPrescriptionDetails(product.getMedicinenameid()).getGenericname();
				 String datetime[]=rs.getString(5).split(" ");
				 String date=DateTimeUtils.getCommencingDate1(datetime[0]);
				 product.setDate(date);
				 
			 //	 int soltStock= getSoltStock(rs.getString(2));
				// product.setSoltStock(soltStock);
				 Product productmaster= inventoryProductDAO.getProduct(rs.getString(2));
				 product.setGenericname(productmaster.getGenericname());
				 product.setStock(productmaster.getStock());
				 product.setVendor(productmaster.getVendor());
				 product.setVendor_id(productmaster.getVendor_id());
				 product.setProduct_name(productmaster.getProduct_name());
				 product.setSoltStock(0);
				 product.setPack(productmaster.getPack());
				 product.setMedicine_type(productmaster.getMedicine_type());
				 /*ArrayList<Product> vendorList= getAllVendorListByProduct(product.getMedicinenameid());
				 
				 product.setVendorList(vendorList); */
				 list.add(product);
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public ArrayList<Product> getSaleProdutList(String fromdate,String todate) {
		
		ArrayList<Product> list= new ArrayList<Product>();
		InventoryProductDAO inventoryProductDAO= new JDBCInventoryProductDAO(connection);
		try {
			
			StringBuffer buffer=new StringBuffer();
			buffer.append("select id,product_id,commencing from apm_medicine_charges where commencing between '"+fromdate+"' and '"+todate+"' group by product_id ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				  
				Product product=new Product();
				product.setId(rs.getInt(1));
				product.setProduct_id(rs.getString(2));
				
				Product master= inventoryProductDAO.getProduct(product.getProduct_id());
				int soltStock= getSoltStock(product.getProduct_id(), fromdate, todate);
				product.setVendor_id(master.getVendor_id());
				product.setVendor(master.getVendor());
				product.setProduct_name(master.getProduct_name());
				product.setStock(master.getStock());
				product.setPostatus(getMedicinePoStatus(product.getProduct_id(), rs.getString(3)));
				product.setSoltStock(soltStock);
				product.setPack(master.getPack());
				product.setMedicine_type(master.getMedicine_type());
				product.setGenericname(master.getGenericname());
				
				list.add(product);
				
			}
			
			list.addAll(getAllRequestedMedicine());
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
		return list;
		
	}

	private int getSoltStock(String pid,String fromdate,String todate) {

		int result=0;
		try {
			String sql="select sum(quantity) from apm_medicine_charges where commencing between '"+fromdate+"' and '"+todate+"' and product_id="+pid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 
				  result=rs.getInt(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Product> getAllVendorListByProduct(String medicinenameid) {

		ArrayList<Product> list=new ArrayList<Product>();
		try {
			
			String sql="SELECT id,name from inventory_vendor where products like('%"+medicinenameid+"%')";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				  
				Product product=new Product();
				product.setVendor_id(rs.getString(1));
				product.setVendor(rs.getString(2));
				list.add(product);
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<Priscription> getAllDoctorReportList(String fromdate,
			String todate, String phar_practid) {

		ArrayList<Priscription> list=new ArrayList<Priscription>();
		//UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
		try {
			
			StringBuffer buffer=new StringBuffer();
			/*buffer.append("select apm_client_parent_priscription.id,practitionerid,sum(apm_medicine_bill.debit) from apm_client_parent_priscription ");
			buffer.append("inner join apm_medicine_bill on apm_client_parent_priscription.billno = apm_medicine_bill.id where ");
			buffer.append("apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' ");
			buffer.append("group by apm_client_parent_priscription.practitionerid order by apm_client_parent_priscription.id desc ");*/
			
			//  11-08-2020 for set new dr report
			/*buffer.append("select  apm_parent_prisc.id,practid,sum(apm_medicine_bill.debit),count(*),sum(vat) from apm_parent_prisc ");
			buffer.append("inner join apm_medicine_bill on apm_parent_prisc.id = apm_medicine_bill.newparentid ");
			buffer.append("where practid is not null and practid!=0 ");
			buffer.append("and apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' and apm_medicine_bill.deleted=0 and apm_medicine_bill.isreturn =0 ");
			buffer.append("group by apm_parent_prisc.practid order by apm_parent_prisc.id desc");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Priscription priscription=new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setPractitionerid(rs.getString(2));
				priscription.setDebit(rs.getDouble(3));
				UserProfile userProfile=userProfileDAO.getUserprofileDetails(rs.getInt(2));
				String fullname=userProfile.getFullname();
				priscription.setDescription(userProfile.getDiscription());
				priscription.setMobile(userProfile.getMobile());
				priscription.setPclientid("0");
				priscription.setFullname(fullname);
				priscription.setCount(rs.getInt(4));
				priscription.setVat(rs.getString(5));
				list.add(priscription);
				
			}*/
			//  11-08-2020 for below already commented code
			/*ArrayList<Priscription> arrayList = getAllInternalDoctorReportList(fromdate, todate);
			list.addAll(arrayList);*/
			//  11-08-2020 for set new dr report
			//list.addAll(getAllExternDoctorReportList(fromdate, todate));
			buffer.append("select bill_practid,bill_practname,sum(apm_medicine_bill.debit),count(*),sum(vat) from apm_medicine_bill ");
			buffer.append("where apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' ");
			buffer.append("and apm_medicine_bill.deleted=0 and apm_medicine_bill.isreturn =0 and bill_practid is not null and bill_practid!='' ");
			
			if(!phar_practid.equals("0")){
				buffer.append("and apm_medicine_bill.bill_practid='"+phar_practid+"' ");
			}
			
			buffer.append("group by bill_practid ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Priscription priscription=new Priscription();
				priscription.setPractitionerid(rs.getString(1));
				priscription.setDebit(rs.getDouble(3));
				String fullname=rs.getString(2);
				priscription.setFullname(fullname);
				priscription.setCount(rs.getInt(4));
				priscription.setVat(rs.getString(5));
				double purchseprice = getDrWisePurchasePrice(fromdate,todate,rs.getString(1));
				purchseprice= Math.round(purchseprice * 100.0) / 100.0;
				priscription.setPurchase_price(""+purchseprice);
				double margin = rs.getDouble(3) - purchseprice;
				margin= Math.round(margin * 100.0) / 100.0;
				priscription.setMargintotal(""+margin);
				priscription.setDate(DateTimeUtils.getCommencingDate1(fromdate));
				list.add(priscription);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}

	
	private double getDrWisePurchasePrice(String fromdate, String todate, String string) {
		double val=0;
		try {
			
			StringBuffer buffer=new StringBuffer();
			
			buffer.append("select sum(bill_pur_gst+bill_purpice) from apm_medicine_charges ");
			buffer.append("inner join apm_medicine_bill on apm_medicine_bill.id = apm_medicine_charges.invoiceid ");
			buffer.append("where apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' ");
			buffer.append("and apm_medicine_bill.deleted=0 and apm_medicine_bill.isreturn =0 and bill_practid="+string+" ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				val = rs.getDouble(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return val;
	
	}

	private ArrayList<Priscription> getAllInternalDoctorReportList(String fromdate, String todate) {
		
		return null;
	}

	public ArrayList<Priscription> getAllExternDoctorReportList(String fromdate,
			String todate) {
		
		ArrayList<Priscription> list= new ArrayList<Priscription>();
		
		try {
			
			StringBuffer sql= new StringBuffer();
			sql.append("select sum(debit),apm_pharmacy_client.reference,apm_pharmacy_client.mobile,apm_medicine_bill.pclientid,count(*),sum(vat) from apm_medicine_bill inner join ");
			sql.append("apm_pharmacy_client on apm_medicine_bill.pclientid = apm_pharmacy_client.id ");
			sql.append("where apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' and apm_medicine_bill.isreturn =0 and apm_medicine_bill.deleted=0 ");
			sql.append("group by apm_pharmacy_client.reference order by apm_pharmacy_client.id desc; ");
			
			PreparedStatement  ps=connection.prepareStatement(sql.toString());
			ResultSet rs =ps.executeQuery();
			
			while(rs.next()){
				
				  Priscription priscription= new Priscription();
				  
				  priscription.setDebit(rs.getDouble(1));
				  priscription.setFullname(rs.getString(2));
				  priscription.setMobile(rs.getString(3));
				  priscription.setPclientid(rs.getString(4));
				  priscription.setId(rs.getInt(4));
				  priscription.setPractitionerid("0");
				  priscription.setCount(rs.getInt(5));
					priscription.setVat(rs.getString(6));
				  list.add(priscription);
			}
			
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}
	
	
	public ArrayList<String> getAllBillListByDoctor(String fromdate,
			String todate, String doctorid) {

		ArrayList<String> list=new ArrayList<String>();
		
		try {
			
			StringBuffer buffer=new StringBuffer();
			buffer.append("select apm_client_parent_priscription.id,apm_medicine_bill.id from apm_client_parent_priscription ");
			buffer.append("inner join apm_medicine_bill on apm_client_parent_priscription.billno = apm_medicine_bill.id where ");
			buffer.append("apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"'  ");
			buffer.append("and apm_client_parent_priscription.practitionerid="+doctorid+" order by apm_medicine_bill.id desc ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				  
				String bill=rs.getString(2);
				list.add(bill);   
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}

	
	public int getMedicineRequestedCount() {

		  int result=0;
		  try {
		   String sql="select count(*) from apm_medicine_request ";
		   PreparedStatement ps=connection.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next()){
		    result=rs.getInt(1);
		   }
		   
		  } catch (Exception e) {

		   e.printStackTrace();
		  }
		  
		  return result;
		 }

	public int addUpdateMedicinePoList(String pid, String qty, String commencing) {

		int result=0;
		try {
			
			int status= getMedicinePoStatus(pid, commencing);
			if(status==0){
				 
				 
				  String sql2= "insert into apm_medicine_polist (product_id,qty,commencing) values (?,?,?) ";
				  PreparedStatement ps1=connection.prepareStatement(sql2);
				  ps1.setString(1, pid);
				  ps1.setString(2, qty);
				  ps1.setString(3, commencing);
				  result=ps1.executeUpdate();
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public int getMedicinePoStatus(String pid,String commencing) {
		
		int status=0;
		try {
			
			String sql="select id from apm_medicine_polist where product_id="+pid+" and commencing='"+commencing+"' ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				status=rs.getInt(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
		return status;
	}
	
	
	public ArrayList<Priscription> getPriscPharmacyAvailablity(String selectedid) {
		PreparedStatement preparedStatement = null;
		
		ArrayList<Priscription>list = new ArrayList<Priscription>();
		PrescriptionMasterDAO masterDAO= new JDBCPrescriptionMasterDAO(connection);
		InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
		String sql = "SELECT id,lastmodified,mdicinename,dose,frequency,days,type,total,priscdurationtype,notes,mdicineid FROM apm_client_priscription where parentid = "+selectedid+" ";
		
		try{
			
			double subtotal=0;
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				
				if(rs.getString(2)!=null){
					priscription.setDate(DateTimeUtils.getDBDate(rs.getString(2)));
				}else{
					priscription.setDate("");
				}
				
				priscription.setLastmodified(rs.getString(2));
				priscription.setMdicinenametxt(rs.getString(3));
				priscription.setPriscdose(rs.getString(4));
				priscription.setPriscfreq(rs.getString(5));
				priscription.setPriscdays(rs.getString(6));
				priscription.setPrisctype(rs.getString(7));
				priscription.setPrisctotal(rs.getString(8));
				priscription.setPriscdurationtype(rs.getString(9));
				priscription.setDosenotes(rs.getString(10));
				priscription.setMdicinenameid(rs.getString(11));
				
				Priscription master= masterDAO.getPrescriptionDetails(priscription.getMdicinenameid());
				priscription.setMdicinenametxt(master.getDrug());
				priscription.setMolecules(master.getMolecules());
				int count=0;
				for(String str:priscription.getPriscdose().split("-")){
					
					 if(!str.equals("0")){
						 try {
							 int t=Integer.parseInt(str);
							 count=count+t;
						} catch (Exception e) {
						}
					 }
				}
				
				int days=Integer.parseInt(priscription.getPriscdays());
				
				priscription.setRequired(count*days);
				Product product=inventoryProductDAO.getPriscMedicineByAvailablility(priscription.getMdicinenametxt(),count*days,master.getGenericname());
				if(product.getId()>0){
					
						//product=inventoryProductDAO.getMedicineProductDetails(priscription.getMdicinenameid());
						String expiry=DateTimeUtils.converToMMMYYYYforExp(product.getExpiry_date());
						priscription.setExpiry_date(expiry);
						priscription.setAvailable((product.getStock()));
						priscription.setMrp(product.getSale_price());
						priscription.setBatch_no(product.getBatch_no());
						priscription.setProduct_id(String.valueOf(product.getId()));
						priscription.setShelf(product.getShelf());
						priscription.setVat(product.getVat());
						
						subtotal=subtotal+(Double.parseDouble(priscription.getMrp()));
						
						int t=Integer.parseInt(priscription.getAvailable());
						
						if(count>t){
							priscription.setSstatus(1);
						} else {
							priscription.setSstatus(0);
						}
						priscription.setSubtotal(subtotal);
						list.add(priscription);
				
			   } else {
				      priscription.setOstatus(1);
				      list.add(priscription);
				     
			   }
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
		
	}

	public int updateRequestedStatus(String pid) {

		int result=0;
		try {
			
			String sql="update apm_medicine_request set status=1 where product_id="+pid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			result= ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public String getCardBillInvoice(int billno) {

		String cardBill="";
		try {

			String sql="select paymentnote from apm_medicine_payment where billno="+billno+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				  
				cardBill= rs.getString(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return cardBill;
	}

	public Clinic getPharmacyAddress() {
		PreparedStatement preparedStatement = null;
		Clinic clinic = new Clinic();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT id, clinicname, firstname, lastname, address, city, website, email, phone,instruction1, instruction2, instruction3, instruction4 ");
		sql.append("FROM apm_pharmacy_user ");
		sql.append("where id = 1 ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				clinic.setId(rs.getInt(1));
				clinic.setClinicName(rs.getString(2));
				clinic.setClinicOwner(rs.getString(3) + " " + rs.getString(4));
				clinic.setAddress(rs.getString(5));
				clinic.setCity(rs.getString(6));
				clinic.setWebsiteUrl(rs.getString(7));
				clinic.setEmail(rs.getString(8));
				clinic.setContactNo(rs.getString(9));
				
				clinic.setInst1(rs.getString(10));
				clinic.setInst2(rs.getString(11));
				clinic.setInst3(rs.getString(12));
				clinic.setInst4(rs.getString(13));
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return clinic;
	}

	public int updateLoginaccess(String id, String status,String datetime,String currentuser) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_pharmacy_user set  islogin="+status+",isloginDT='"+datetime+"',isloginBY='"+currentuser+"' where id="+id+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
	
	public int getallusereportcount(String fromdate, String todate) {
		int result=0;
		try {	
			StringBuffer sql = new StringBuffer();
				sql.append("select DISTINCT count(userid) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and userid!=''");
			PreparedStatement ps=connection.prepareStatement(sql.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				result=rs.getInt(1);
			}
		} catch (Exception e) {
		   e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Priscription> getuserreportinfo(Pagination pagination,String fromdate,String todate,String location,String userid1,String selectuserid) {
		ArrayList<Priscription> userlist = new ArrayList<Priscription>();
		try{
			StringBuffer sql = new StringBuffer();
				sql.append("select userid from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and userid!='' ");
				if(!location.equals("0")){
					sql.append("and location="+location+" ");
				}
				if(!userid1.equals("")){
					sql.append(" and userid='"+userid1+"'  ");
				}
				if(!selectuserid.equals("0")){
					sql.append(" and userid='"+selectuserid+"'  ");
				}
				sql.append("group by userid order by id desc ");
				
			String sql1=pagination.getSQLQuery(sql.toString());
			PreparedStatement preparedStatement = connection.prepareStatement(sql1);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Priscription priscription = new Priscription();
				String userid = rs.getString(1);
				Priscription Loc= getPharacyUsrLInfo(userid);
				String loc =Loc.getLocation();
				priscription.setLocation(getLocationName(loc)); 
				String fullname = getusernamefromusertbl(userid);
				String billcount = getbillcountbyusr(userid,fromdate,todate,loc);
				String payment = getpaymentbyusr(userid,fromdate,todate,loc);
				String totalpayment = getttlpaymentbyusr(userid,loc);
				double credit =getTotalCreditByUser(userid,fromdate,todate,loc);
				double totalcash= getTotalCashByUser(userid,fromdate,todate,loc);
				double totalcard = getTotalCardByUser(userid,fromdate,todate,loc);
				double totalcheque= getTotalChequeByUser(userid,fromdate,todate,loc);
				double totalneft= getTotalNeftByUser(userid, fromdate, todate, loc);
				double totalonline= getTotalOnlinePaymentByUser(userid, fromdate, todate, loc);
				
				double totaldisc = getTotalDiscByUser(userid,fromdate,todate,loc);
				double totalReturn = getTotalReturnByUser(userid,fromdate,todate,loc);
				double totalreturnneft=getTotalReturnNeftyUser(userid,fromdate,todate,loc);
				double netcash= totalcash  - totalReturn;
				double receivedtotal= totalcard+totalcash+totalcheque+totalneft+totalonline;
				double nettotal=receivedtotal-totalReturn-totalreturnneft;
				double todayagainstcredit = getTotalAgainstCreditbyuser(userid,fromdate,todate,loc);
				//  13 July 2018
				double totalCredit= getTotalCreditbyUser(location,fromdate,todate,userid);
				double totalcreditReturn = getTotalcreditReturnByUser(userid,fromdate,todate,loc);
				double totalneftamtrec=totalneft-totalreturnneft;
				double totalneftnetamt=totalneft-totalneftamtrec;
				double totalamtref=totalReturn+totalreturnneft;
				priscription.setTotalonline(totalonline);
				priscription.setTotalcreditReturn(totalcreditReturn);
				priscription.setCreditBalance(credit);
				priscription.setReceivedtotal(receivedtotal);
				priscription.setNettotal(nettotal);
				priscription.setTotalrefund(totalReturn);
				priscription.setNetcash(netcash);
				priscription.setNetcard(totalcard);
				priscription.setTotalcard(totalcard);
				priscription.setTotalcheque(totalcheque);
				priscription.setTotalneft(totalneft);
				priscription.setTotaldisc(totaldisc);
				priscription.setTotalcash(totalcash);
				priscription.setTotalReturn(totalReturn);
				priscription.setUserid(userid);
				priscription.setFullname(fullname);
				priscription.setBillcount(billcount);
				priscription.setPayment(payment);
				priscription.setTotalpayment(totalpayment);
				priscription.setTotalreturnneft(totalreturnneft);
				priscription.setTotalneftamtrec(totalneftamtrec);
				priscription.setTotalneftnetamt(totalamtref);
				priscription.setTotalCreditbyuser(DateTimeUtils.changeFormat(totalCredit));
				priscription.setTodayagainstcredit(todayagainstcredit);
				double newcredit= getTotalCreditbyUserNew(location,fromdate,todate,userid);
				priscription.setNewcredit(newcredit);
				userlist.add(priscription);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userlist;
	}

	public double getTotalOnlinePaymentByUser(String userid, String fromdate, String todate, String loc) {
		double res=0;	
		try {
			    String query="";
			    if(!loc.equals("0")){
			    	query = "select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='Online Payment' and userid='"+userid+"' and location="+loc+";";
			    } else {
			    	query = "select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='Online Payment' and userid='"+userid+"';";
			    }
			
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					res = rs.getDouble(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return res;
	
	}

	private double getTotalReturnNeftyUser(String userid, String fromdate, String todate, String loc) {
		double res=0;	
		try {
			    /*String query="";
			    if(!loc.equals("0")){
			    	query = "select sum(debit) from apm_medicine_bill where date between '"+fromdate+"' and '"+todate+"' and isreturn=1 and userid='"+userid+"' and location="+loc+" ;";
			    } else {
			    	query = "select sum(debit) from apm_medicine_bill where date between '"+fromdate+"' and '"+todate+"' and isreturn=1 and userid='"+userid+"' ";
			    }*/
			StringBuffer  query = new StringBuffer();
			query.append("select sum(apm_medicine_payment.refundamt) from apm_medicine_bill ");
			query.append("inner join apm_medicine_payment on apm_medicine_payment.billno =apm_medicine_bill.id ");
			query.append("where apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' and isreturn=1 and apm_medicine_payment.userid='"+userid+"' ");
			query.append("and paymode='NEFT' ");
			if(!loc.equals("0")){
				query.append("and apm_medicine_bill.location="+loc+" ");
			}
			    
				PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					res = rs.getDouble(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return res;
	}

	private double getTotalCreditbyUserNew(String location, String fromdate, String todate, String userid) {
		double temp=0;
		try {
			
			StringBuffer buffer= new StringBuffer();
			buffer.append("select credit from apm_medicine_bill ");
			buffer.append("inner join apm_medicine_payment on apm_medicine_bill.id=apm_medicine_payment.billno ");
			buffer.append("where apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' ");
			buffer.append("and (apm_medicine_bill.tpid=0 or apm_medicine_bill.tpid is NULL) and apm_medicine_bill.isreturn=0 ");
			if(!location.equals("0")){
				buffer.append("and apm_medicine_bill.location="+location+" ");
			}
		    buffer.append("and apm_medicine_payment.paymode!='Hospital' and apm_medicine_payment.userid='"+userid+"' group by apm_medicine_bill.id ");
			
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 
				double res=rs.getDouble(1);
				temp =temp+res;
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return temp;
	}

	private double getTotalAgainstCreditbyuser(String userid, String fromdate, String todate, String location) {
		double temp=0;
		try {
			
			StringBuffer buffer= new StringBuffer();
			buffer.append("select sum(payment) from apm_medicine_bill ");
			buffer.append("inner join apm_medicine_payment on apm_medicine_bill.id=apm_medicine_payment.billno ");
			buffer.append("where apm_medicine_payment.date between '"+fromdate+"' and '"+todate+"' ");
			buffer.append("and (apm_medicine_bill.tpid=0 or apm_medicine_bill.tpid is NULL) and apm_medicine_bill.isreturn=0 ");
			if(!location.equals("0")){
				buffer.append("and apm_medicine_bill.location="+location+" ");
			}
		    buffer.append("and apm_medicine_payment.paymode!='Hospital' and onsamedate='1' and apm_medicine_payment.userid='"+userid+"'  ");
		    
		    buffer.append("group by apm_medicine_bill.id ");
			
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 
				double res=rs.getDouble(1);
				temp =temp+res;
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return temp;
	}

	public double getTotalcreditReturnByUser(String userid, String fromdate, String todate, String loc) {
		double res=0;	
		try {
			StringBuffer  query = new StringBuffer();
			query.append("select sum(apm_medicine_payment.refundamt) from apm_medicine_bill ");
			query.append("inner join apm_medicine_payment on apm_medicine_payment.billno =apm_medicine_bill.id ");
			query.append("where apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' and isreturn=1 and apm_medicine_payment.userid='"+userid+"' ");
			query.append("and paymode='Credit' ");
			if(!loc.equals("0")){
				query.append("and apm_medicine_payment.location="+loc+" ");
			}
			    
				PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					res = rs.getDouble(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return res;
	}

	private double getTotalCreditbyUser(String location, String fromdate, String todate, String userid) {
		double temp=0;
		try {
			
			StringBuffer buffer= new StringBuffer();
			buffer.append("select credit from apm_medicine_bill ");
			buffer.append("inner join apm_medicine_payment on apm_medicine_bill.id=apm_medicine_payment.billno ");
			buffer.append("where apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' ");
			buffer.append("and (apm_medicine_bill.tpid=0 or apm_medicine_bill.tpid is NULL) and apm_medicine_bill.isreturn=0 ");
			if(!location.equals("0")){
				buffer.append("and apm_medicine_bill.location="+location+" ");
			}
		    buffer.append("and apm_medicine_payment.paymode!='Hospital' and  apm_medicine_payment.userid='"+userid+"' group by apm_medicine_bill.id ");
			
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 
				double res=rs.getDouble(1);
				temp =temp+res;
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return temp;
	}

	public double getTotalReturnByUser(String userid, String fromdate,
			String todate,String loc) {
		double res=0;	
		try {
			    /*String query="";
			    if(!loc.equals("0")){
			    	query = "select sum(debit) from apm_medicine_bill where date between '"+fromdate+"' and '"+todate+"' and isreturn=1 and userid='"+userid+"' and location="+loc+" ;";
			    } else {
			    	query = "select sum(debit) from apm_medicine_bill where date between '"+fromdate+"' and '"+todate+"' and isreturn=1 and userid='"+userid+"' ";
			    }*/
			StringBuffer  query = new StringBuffer();
			query.append("select sum(apm_medicine_payment.refundamt) from apm_medicine_bill ");
			query.append("inner join apm_medicine_payment on apm_medicine_payment.billno =apm_medicine_bill.id ");
			query.append("where apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' and isreturn=1 and apm_medicine_payment.userid='"+userid+"' ");
			query.append("and paymode!='Credit' and paymode!='NEFT' ");
			if(!loc.equals("0")){
				query.append("and apm_medicine_bill.location="+loc+" ");
			}
			    
				PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					res = rs.getDouble(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return res;
	}

	private double getTotalDiscByUser(String userid, String fromdate,
			String todate,String loc) {
		double res=0;	
		try {
 			   String query="";
			    if(!loc.equals("0")){
			    	query = "select sum(discount) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"'  userid='"+userid+"' and location="+loc+" and (tpid=0 or tpid is NULL) ;";
			    } else {
			    	query = "select sum(discount) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"'  userid='"+userid+"' and (tpid=0 or tpid is NULL) ";
			    }
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					res = rs.getDouble(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return res;
	}
	
	
	public double getTotalCreditByUser(String userid,String fromdate,String todate,String loc) {
		
		double res=0;	
		try {
				String  query="";
				if(!loc.equals("0")){
					query = "select sum(balance) from apm_medicine_bill where date between '"+fromdate+"' and '"+todate+"' and userid='"+userid+"' and location="+loc+" and (tpid=0 or tpid is NULL) ;";
				} else{
					query = "select sum(balance) from apm_medicine_bill where date between '"+fromdate+"' and '"+todate+"' and userid='"+userid+"' and (tpid=0 or tpid is NULL);";
				}
				
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					res = rs.getDouble(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return res;
		
	}

	public double getTotalCashByUser(String userid, String fromdate,
			String todate,String loc) {
		double res=0;	
		try {
				String  query="";
				if(!loc.equals("0")){
					query = "select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='Cash' and userid='"+userid+"' and location="+loc+" and (tpid=0 or tpid is NULL) ;";
				} else{
					query = "select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='Cash' and userid='"+userid+"' and (tpid=0 or tpid is NULL);";
				}
				
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					res = rs.getDouble(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return res;
	}
	public double getTotalCardByUser(String userid, String fromdate,
			String todate,String loc) {
		double res=0;	
		try {
			    String query="";
			    if(!loc.equals("0")){
			    	query = "select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='Card' and userid='"+userid+"' and location="+loc+";";
			    } else {
			    	query = "select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='Card' and userid='"+userid+"';";
			    }
			
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					res = rs.getDouble(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return res;
	}

	public double getTotalChequeByUser(String userid, String fromdate,
			String todate,String loc) {
		double res=0;	
		try {
			    String query="";
			    if(!loc.equals("0")){
			    	query = "select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='Cheque' and userid='"+userid+"' and location="+loc+";";
			    } else {
			    	query = "select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='Cheque' and userid='"+userid+"';";
			    }
			
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					res = rs.getDouble(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return res;
	}
	public double getTotalNeftByUser(String userid, String fromdate,
			String todate,String loc) {
		double res=0;	
		try {
			    String query="";
			    if(!loc.equals("0")){
			    	query = "select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='NEFT' and userid='"+userid+"' and location="+loc+";";
			    } else {
			    	query = "select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='NEFT' and userid='"+userid+"';";
			    }
			
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					res = rs.getDouble(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return res;
	}
	private String getttlpaymentbyusr(String userid,String loc){
		String paymenttotal="";	
		try {
			    String query="";
			    if(!loc.equals("0")){
			    	query = "select sum(payment) from apm_medicine_payment where userid='"+userid+"' and location="+loc+";";
			    } else {
			    	query = "select sum(payment) from apm_medicine_payment where userid='"+userid+"';";
			    }
			   
			    
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					paymenttotal = rs.getString(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return paymenttotal;
	}

	private String getpaymentbyusr(String userid, String fromdate,String todate,String loc) {
		String paymenttotal="";	
		try {
			   String query="";
			    if(!loc.equals("0")){
			    	query = "select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and userid='"+userid+"' and location="+loc+";";
			    } else {
			    	query = "select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and userid='"+userid+"';";
			    }
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					paymenttotal = rs.getString(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return paymenttotal;
	}

	private String getbillcountbyusr(String userid, String fromdate,String todate,String loc) {
		String billcount="";	
		try {
			    String query="";
			    if(!loc.equals("0")){
			    	query = "select count(billno) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and userid='"+userid+"' and location='"+loc+"' ";
			    } else {
			    	query = "select count(billno) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and userid='"+userid+"';";
			    }
				 
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					billcount = rs.getString(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return billcount;
	}

	public String getusernamefromusertbl(String userid) {
		String fullname="";	
		try {
				String query = "select firstname,lastname from apm_user where userid='"+userid+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					String firstname= rs.getString(1);
					String lastname = rs.getString(2);
					fullname = firstname+" "+lastname;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return fullname;
	}
	public String getmobfromuserid(String userid) {
		String mobile="";	
		try {
				String query = "select mobile from apm_user where userid='"+userid+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					mobile =rs.getString(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return mobile;
	}

	public ArrayList<Priscription> getallbillbyuser(String userid,
			String fromdate, String todate,String loc) {
		ArrayList<Priscription> arrayList = new ArrayList<Priscription>();
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		try {
			String query="" ;
			if(!loc.equals("0")){
				query = "select billno,date,clientid,pclientid,paymode,payment from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and userid='"+userid+"' and location="+loc+" ;";
			} else {
				query = "select billno,date,clientid,pclientid,paymode,payment from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and userid='"+userid+"';";
			}
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setBillno(rs.getString(1));
				priscription.setDate(rs.getString(2));
				priscription.setUserid(userid);
				priscription.setClientId(rs.getString(3));
				priscription.setPclientid(rs.getString(4));
				priscription.setNewpaymentmode(rs.getString(5));
				priscription.setNewpayment(rs.getDouble(6));
				if(!priscription.getClientId().equals("0")){
					 
					   Client client= clientDAO.getClientDetails(priscription.getClientId());
					   String fullname =client.getTitle()+" "+client.getFirstName()+ " "+client.getLastName();
					   priscription.setFullname(fullname);
				} else {
					  Priscription masterClient= getPharmacyPatient(priscription.getPclientid());
					  String fullname= masterClient.getFullname();
					  priscription.setFullname(fullname);
				}
				
				arrayList.add(priscription);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public Product getpatientinfo(String id) {
		Product product = new Product();
		try {
			String sql = "select id,fullname,address,reference,mobile,pmbalance,phar_drid from apm_pharmacy_client where id="+id+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				product.setId(rs.getInt(1));
				product.setFullname(rs.getString(2));
				product.setAddress(rs.getString(3));
				product.setReference(rs.getString(4));
				product.setMobile(rs.getString(5));
				product.setBalance(rs.getString(6));
				product.setPhar_drid(rs.getString(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	public int updatebalance(int pharmacyclientid,String balance) {
		int result=0;
		try {
			String sql ="update apm_pharmacy_client set pmbalance=? where id=? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, balance);
			preparedStatement.setInt(2, pharmacyclientid);
			result = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getpreviousbalance(int pharmacyclientid) {
		String dbbalance="";
		try {
			String sql = "select pmbalance from apm_pharmacy_client where id="+pharmacyclientid+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				dbbalance = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbbalance;
	}

	public int clearbalfromdb(String extpid) {
		int res = 0;
		try {
			if(extpid!=""){
			String sql = "update apm_pharmacy_client set pmbalance='0' where id="+extpid+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updatebalpatient(String balance, String selectedid) {
		int result=0;
		try {
			String sql ="update apm_patient set mbalance=? where id=? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, balance);
			preparedStatement.setInt(2, Integer.parseInt(selectedid));
			result = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;	
	}

	public String getbalofpatient(String patientId) {
		String dbbalance="";
		try {
			String sql = "select mbalance from apm_patient where id="+patientId+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				dbbalance = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbbalance;
	
	}

	public int clearpatientbal(String opdid) {
		int res = 0;
		try {
			String sql = "update apm_patient set mbalance='0' where id="+opdid+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public Priscription getusersetting(String userid, String status) {
		Priscription priscription = new Priscription();
		try {
			String sql = "select id, sale_bill, discount, ledger, account, purchase_order,islogin,SMS,"
					+ "edit_bill,delete_bill,edit_purchaseorder,delete_purchaseorder,edit_catalogue,"
					+ "delete_catalogue,back_date,third_party,requisition_auth,check_stock_available,"
					+ "direct_transfer,inventory_report,return_stock,cancel_indent,return_medicine,"
					+ "indent_approve,approve_po,purchase_edit,cancel_po,"
					+ "pharm_print_backdate,session_added_med,patient_edit from apm_pharmacy_user where id="+userid+""; 
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				priscription.setId(rs.getInt(1));
				priscription.setSale_bill(rs.getString(2));
				priscription.setDisscount(rs.getString(3));
				priscription.setLedger(rs.getString(4));
				priscription.setAccount(rs.getString(5));
				priscription.setPurchase_order(rs.getString(6));
				priscription.setIslogin(rs.getString(7));
				priscription.setSMS(rs.getString(8));
				priscription.setEdit_bill(rs.getString(9));
				priscription.setDelete_bill(rs.getString(10));
				priscription.setEdit_purchaseorder(rs.getString(11));
				priscription.setDelete_purchaseorder(rs.getString(12));
				priscription.setEdit_catalogue(rs.getInt(13));
				priscription.setDelete_catalogue(rs.getInt(14));
				priscription.setAccess_back_date(rs.getInt(15));
				priscription.setTpbill(rs.getInt(16));
				priscription.setRequisition_auth(rs.getString(17));
				priscription.setCheck_stock_available(rs.getString(18));
				priscription.setDirect_transfer(rs.getString(19));
				priscription.setInventory_transfer(rs.getString(20));
				priscription.setReturn_stock(rs.getString(21));
				priscription.setCancel_indent(rs.getString(22));
				priscription.setReturn_medicine(rs.getString(23));
				priscription.setIndent_approve(rs.getString(24));
				priscription.setApprove_ponew(rs.getString(25));
				priscription.setPurchase_edit(rs.getBoolean(26));
				if(priscription.isPurchase_edit()==true){
					priscription.setPurchaseedit("1");
				}else{
					priscription.setPurchaseedit("0");
				}
				priscription.setCancel_po(rs.getString(27));
				priscription.setPharm_print_backdate(rs.getBoolean(28));
				priscription.setSession_added_med(rs.getBoolean(29));
				priscription.setPatient_edit(rs.getBoolean(30));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return priscription;
	}
	public Priscription setUserSaleSettting(String userid, String status,String datetime,String currentuser) {
		Priscription priscription = new Priscription();
		try {
			try{
				String query = "update apm_pharmacy_user set sale_bill='"+status+"',sale_billDT='"+datetime+"',sale_billBY='"+currentuser+"' where id="+userid+"";
				PreparedStatement preparedStatement1 = connection.prepareStatement(query);
				int result = preparedStatement1.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			String sql = "select id, sale_bill from apm_pharmacy_user where id="+userid+""; 
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				priscription.setId(rs.getInt(1));
				priscription.setSale_bill(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return priscription;
	}

	public Priscription setUserDiscountSettting(String userid, String status,String datetime,String currentuser) {
		Priscription priscription = new Priscription();
		try {
			try{
				String query = "update apm_pharmacy_user set discount='"+status+"',discountDT='"+datetime+"',discountBY='"+currentuser+"' where id="+userid+"";
				PreparedStatement preparedStatement1 = connection.prepareStatement(query);
				int result = preparedStatement1.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			String sql = "select id, discount from apm_pharmacy_user where id="+userid+""; 
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				priscription.setId(rs.getInt(1));
				priscription.setDisscount(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return priscription;
	}

	public Priscription setUserLedgerSettting(String userid, String status,String ledgerBY,String currentuser) {
		Priscription priscription = new Priscription();
		try {
			try{
				String query = "update apm_pharmacy_user set ledger='"+status+"',ledgerDT='"+ledgerBY+"',ledgerBY='"+currentuser+"' where id="+userid+"";
				PreparedStatement preparedStatement1 = connection.prepareStatement(query);
				int result = preparedStatement1.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			String sql = "select id, ledger from apm_pharmacy_user where id="+userid+""; 
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				priscription.setId(rs.getInt(1));
				priscription.setLedger(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return priscription;
	}

	public Priscription setUserAccountSettting(String userid, String status,String datetime,String currentuser) {
		Priscription priscription = new Priscription();
		try {
			try{
				String query = "update apm_pharmacy_user set account='"+status+"',accountDT='"+datetime+"',accountBY='"+currentuser+"' where id="+userid+"";
				PreparedStatement preparedStatement1 = connection.prepareStatement(query);
				int result = preparedStatement1.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			String sql = "select id, account from apm_pharmacy_user where id="+userid+""; 
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				priscription.setId(rs.getInt(1));
				priscription.setAccount(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return priscription;
	}

	public Priscription setUserPurchase_orderSettting(String userid,
			String status,String datetime,String currentuser) {
		Priscription priscription = new Priscription();
		try {
			try{
				String query = "update apm_pharmacy_user set purchase_order='"+status+"',purchase_orderDT='"+datetime+"',purchase_orderBY='"+currentuser+"' where id="+userid+"";
				PreparedStatement preparedStatement1 = connection.prepareStatement(query);
				int result = preparedStatement1.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			String sql = "select id, purchase_order from apm_pharmacy_user where id="+userid+""; 
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				priscription.setId(rs.getInt(1));
				priscription.setPurchase_order(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return priscription;
	}
	
	public Priscription setUserSMSSendAuthority(String userid,
			String status,String datetime,String currentuser) {
		Priscription priscription = new Priscription();
		try {
			try{
				String query = "update apm_pharmacy_user set SMS='"+status+"' ,SMSDT='"+datetime+"',SMSBY='"+currentuser+"' where id="+userid+"";
				PreparedStatement preparedStatement1 = connection.prepareStatement(query);
				int result = preparedStatement1.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			String sql = "select id, SMS from apm_pharmacy_user where id="+userid+""; 
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				priscription.setId(rs.getInt(1));
				priscription.setSMS(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return priscription;
	}

	

	public String getIpdIdOfClient(String clientid) {
		
		String ipdid="0";
		try {
			String sql="SELECT ipdid from apm_client_parent_priscription where clientid="+clientid+" order by id desc limit 0,1";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				ipdid =rs.getString(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return ipdid;
	}
	
	public Priscription getMedicineChargesbyid(int id,int qty) {

		InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
		Priscription priscription=new Priscription();
		  
		try {
			
			String sql="SELECT id,user,charge,practitionerId,practitionerName,clientId,commencing,paybuy,quantity,reqqty,medicineid,thirdPartyId,product_id,invoiceid,returnqty from apm_medicine_charges where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				  priscription.setId(rs.getInt(1));
				  priscription.setClientname(rs.getString(2));
				  priscription.setMrp(DateTimeUtils.changeStringFormat(rs.getString(3)));
				  priscription.setPrectionerid(rs.getString(4));
				  priscription.setFullname(rs.getString(5));
				  priscription.setClientId(rs.getString(6));
				  priscription.setDate(rs.getString(7));
				  priscription.setWhopay(rs.getString(8));
				  priscription.setSaleqty(rs.getInt(9));
				  priscription.setReqqty(rs.getInt(10));
				  
				  double total=Double.parseDouble(priscription.getMrp())*priscription.getSaleqty();
				  
				  String str=DateTimeUtils.changeFormat(total);
				  priscription.setTotal(Double.parseDouble(str));
				  priscription.setMdicinenameid(rs.getString(11));
				  priscription.setTpid(rs.getString(12));
				  priscription.setProduct_id(rs.getString(13));
				  priscription.setBillno(rs.getString(14));
				  
				  Product product=inventoryProductDAO.getProduct(priscription.getProduct_id());
				  priscription.setMdicinenametxt(product.getProduct_name());
				  //priscription.setBillno(String.valueOf(billno));
				  priscription.setBatch_no(product.getBatch_no());
				  priscription.setMfg(product.getMfg());
				  priscription.setPkg("6");
				  priscription.setShelf(product.getShelf());
				  priscription.setAvailable((product.getStock()));
				  priscription.setProductname(product.getProduct_name());
				  priscription.setPurchase_price(product.getPurchase_price());
				  if(product.getPack()!=null){
					  if(product.getPack().equals("") || product.getPack().equals("0")){
						  product.setPack("1");
					  }
				  }else{
					  product.setPack("1");
				  }
				  priscription.setPack(Integer.parseInt(product.getPack()));
				  priscription.setBatch_no(product.getBatch_no());
				  //  14-01-2019 // return bill changes must take from apm_medicine_charges
				  //priscription.setSale_price(product.getSale_price());
				  priscription.setSale_price(DateTimeUtils.changeFormat(rs.getDouble(3)));
				  priscription.setStock((product.getStock()));
				  priscription.setMedicineid(product.getMedicinenameid());
				  double stock=0.0;
				  if(product.getStock()!="0"){
					   if(!product.getStock().equals("")){
						   
						  stock=Double.parseDouble(product.getStock());
					  }
				
				  }
				  if(stock==0){
					  priscription.setSstatus(1);
				  }
				  
				  if(product.getExpiry_date()!=null){
					  String temp=DateTimeUtils.getCommencingDate1(product.getExpiry_date());
					  String expiry=DateTimeUtils.converToMMMYYYYforExp(temp);
					  priscription.setExpiry_date(expiry);
				  }else {
					priscription.setExpiry_date("N/A");
				  }
				  //list.add(priscription);
				  //calculate the discount percentages 
				  double discPerGivenEach= getDiscountPerReturnMedicine(priscription.getBillno(),total);
				  priscription.setDiscount(discPerGivenEach);
				  priscription.setDiscper(discPerGivenEach);
				  
				  int returnqty= getReturnQtyofCharge(rs.getString(1)); 
				  returnqty =priscription.getSaleqty()- returnqty; 
				  priscription.setReturnqty(returnqty);
				  priscription.setExpiry_date(product.getExpiry_date());
				  priscription.setQty(qty);
				  priscription.setVat(product.getVat());
				  priscription.setShelf(DateTimeUtils.isNull(product.getShelf()));
				  priscription.setReturnqty(rs.getInt(15));
				  priscription.setSecondary_shelf(DateTimeUtils.isNull(product.getSecondary_shelf()));
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return priscription;
	}

	public double getDiscountPerReturnMedicine(String billno,double temptotal) {
		double t=0;
		try {
			CompleteAppointment completeAppointment= getBillDetails(Integer.parseInt(billno));
		    double total= completeAppointment.getTotal();
		    double disc= completeAppointment.getDiscount();
		    total= total +disc;
		     t=disc*100/ total;
		     //t = Math.round(t);
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return t;
	}

	public ArrayList<Priscription> getipdpatientlist() {
		ArrayList<Priscription> arrayList = new ArrayList<Priscription>();
		try {
			String sql = "SELECT id,clientid FROM ipd_addmission_form where bedid!=0";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int ipdid = rs.getInt(1);			
				String clientid = rs.getString(2);
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				Client client = clientDAO.getClientDetails(clientid);
				Priscription priscription = new Priscription();
				priscription.setClientId(clientid);
				priscription.setIpdid(""+ipdid);
				String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
				priscription.setClientname(clientname);
				arrayList.add(priscription);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public String getclientid(String ipdid) {
		String clientid = "0";
		try {
			String sql = "SELECT clientid FROM ipd_addmission_form where id="+ipdid+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				clientid = rs.getString(1);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientid;
	}
	
	public Product getipdpatientinfo(String id) {
		Product product = new Product();
		try {
			String sql = "select id, fullname, address ,mobno from apm_pharmacy_client where id="+id+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				product.setId(rs.getInt(1));
				product.setFullname(rs.getString(2));
				product.setAddress(rs.getString(3));
				product.setReference(rs.getString(4));
				product.setMobile(rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	public String getpricriptionid(String ipdid) {
		String pricid = "0";
		try {
			String sql = "SELECT id FROM apm_client_parent_priscription where ipdid="+ipdid+" order by id desc limit 0,1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				pricid = rs.getString(1);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pricid;
		}

	public String getclientidfrompriscid(int id) {
		String clientid = "0";
		try {
			String sql = "SELECT clientid FROM apm_medicine_charges where id="+id+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				clientid = rs.getString(1);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientid;
	}

	public int getpriscidfromclientid(String clientid) {
		int selectedid = 0;
		try {
			String sql = "select id from apm_client_parent_priscription where clientid="+clientid+" order by id desc limit 0,1;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				selectedid = rs.getInt(1);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return selectedid;
	}

	public ArrayList<Priscription> getbillno(String ipdid) {
		  ArrayList<Priscription> arrayList = new ArrayList<Priscription>();
		  try {
		   String sql = "select billno from apm_client_parent_priscription where ipdid='"+ipdid+"' and billno!=0";
		   PreparedStatement preparedStatement = connection.prepareStatement(sql);
		   ResultSet rs = preparedStatement.executeQuery();
		   while(rs.next()){
		    Priscription priscription = new Priscription();
		    priscription.setBillno(rs.getString(1));
		    arrayList.add(priscription);
		   }
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  return arrayList;
		 }

	public int getSelectedIdFromIpdid(String ipdid) {
		int selectedid = 0;
		try {
			String sql = "select id from apm_client_parent_priscription where ipdid="+ipdid+" order by id desc limit 0,1;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				selectedid = rs.getInt(1);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return selectedid;
		
	}

	public int setIsReturn(int billno,int refundid) {
		int selectedid = 0;
		try {
			String sql = "update apm_medicine_bill set isreturn=1,refundid="+refundid+" where id="+billno+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			selectedid = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return selectedid;
	}

	public int updateBalanceofBill(String billno, String balance) {

		int result=0;
		double total=0;
		try {
			String sql="select debit from apm_medicine_bill where id="+billno+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				  total =rs.getDouble(1);
			}
			total =total+Double.parseDouble(balance);
			
			String sql2="update apm_medicine_bill set debit=? where id="+billno+"";
			PreparedStatement preparedStatement =connection.prepareStatement(sql2);
			preparedStatement.setDouble(1, total);
			
			result =preparedStatement.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public double getTotalBalanceofUsers(String location,String fromdate,String todate) {
		
		double temp=0;
		try {
			
			StringBuffer buffer= new StringBuffer();
			buffer.append("select sum(balance) from apm_medicine_bill inner join apm_medicine_payment ");
			buffer.append("on apm_medicine_bill.id=apm_medicine_payment.billno  ");
			buffer.append("where apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' ");
			buffer.append("and (apm_medicine_bill.tpid=0 or apm_medicine_bill.tpid is NULL) ");
			if(!location.equals("0")){
				buffer.append("and apm_medicine_bill.location="+location+" ");
			}
			buffer.append("and apm_medicine_payment.paymode!='Hospital' ");
		    
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 
				 temp =rs.getDouble(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return temp;
	}
 
public double getTotalBalanceByLocation(String location,String fromdate,String todate) {
		
		double temp=0;
		try {
			String sql="";
		
			if(location.equals("0")){
				sql="select sum(balance) from apm_medicine_bill where date between '"+fromdate+"' and '"+todate+"' and tpid=0 ";
			} else {
				sql="select sum(balance) from apm_medicine_bill where date between '"+fromdate+"' and '"+todate+"' and location='"+location+"' and tpid=0 ";	
			}
		    
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 
				 temp =rs.getDouble(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return temp;
	}
	public double getTotalCollectionToday(String location,String fromdate,String todate) {

		double res=0;
		try {
			 String sql="";
			 if(!location.equals("0")){
				 sql="select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and location='"+location+"' and (tpid=0 or tpid is NULL) ";
			 } else {
				 sql="select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and (tpid=0 or tpid is NULL) ";
			 }
			 
			 PreparedStatement ps=connection.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
			 while(rs.next()){
				  
				 res=rs.getDouble(1);
			 }
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	public int updateMedicineReturnQty(int id, int returnqty) {

		int result=0;
		int qty=0;
		try {
			
			String sql="select quantity from apm_medicine_charges where id="+id+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				 
				 qty= rs.getInt(1);
			}
			qty= qty-returnqty;
			if(qty<0){
				 qty=0;
			}
			
			String sql2="update apm_medicine_charges set quantity="+qty+" where id="+id+" ";
			PreparedStatement ps1=connection.prepareStatement(sql2);
			result =ps1.executeUpdate();
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public int updateBalanceByBillWise(String clientid,double amt,int status,Priscription priscription,String paynote, String location, LoginInfo loginInfo, String clearbilltotalids) {

		int result=0;
		int parentid=0;
		try {
			
			String sql="";
			StringBuffer buffer = new StringBuffer();
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			if(status==0){
				 //sql="select id,balance,date from apm_medicine_bill where pclientid="+clientid+" and balance>0 order by date asc ";
				 buffer.append("select id,balance,date,debit,apm_medicine_bill.location from apm_medicine_bill where pclientid="+clientid+" and balance>0 ");
				 if(!location.equals("0")){
					 buffer.append("and apm_medicine_bill.location='"+location+"' ");
				 }
				 buffer.append("and id in ("+clearbilltotalids+") ");
				 buffer.append("order by date asc ");
			}else {
				//sql="select id,balance,date from apm_medicine_bill where clientid="+clientid+" and balance>0 order by date asc ";
				 buffer.append("select id,balance,date,debit,apm_medicine_bill.location from apm_medicine_bill where clientid="+clientid+" and balance>0 ");
				 if(!location.equals("0")){
					 buffer.append("and apm_medicine_bill.location='"+location+"' ");
				 }
				 buffer.append("and id in ("+clearbilltotalids+") ");
				 buffer.append("order by date asc ");
			}
			
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs= ps.executeQuery();
			
			 parentid =saveParentBillClearPayment(priscription);
			
			 double discper = (priscription.getDiscount()/amt)*100;
			 discper = Math.round(discper);
			 while(rs.next()){
				
				  int billno= rs.getInt(1); 
				  double bal= rs.getDouble(2);
				  priscription.setBillno(rs.getString(1));
				  priscription.setBalance(DateTimeUtils.changeFormat(bal));
				  //  18 Jul 2018
				  int issame=0;
				  if(rs.getString(3)!=null){
					  if(priscription.getDate()!=null){
						  if(priscription.getDate().equals(rs.getString(3))){
							  issame =1;
						  }
					  }
				  }
				  
				  /*double disc = getPreviousBillDiscount(billno);
				  double totaldisc = disc + priscription.getDiscount();
				  if(disc!=totaldisc){
					  int res = updateBillDiscount(totaldisc,billno);
				  }*/
				  
				  double discvaluetotal = rs.getDouble(4)*discper/100;
				  discvaluetotal = Math.round(discvaluetotal);
				  if(discvaluetotal>0){
		    	  		double totaldebit = rs.getDouble(4)-discvaluetotal;
		    	  		int res = updateBillDiscount(discvaluetotal,billno,totaldebit);
		    	  		ArrayList<Priscription> arrayList = getMedicineChargeList(billno);
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
					    	tempvat=tot*discper/100;
					    	temptot = tot - tempvat;
					    	double dividevat= 100+tvat;
					    	/*double gst= tot*tvat/dividevat;*/
					    	double gst= temptot*tvat/dividevat;
					    	double half= gst/2;
					    	half = Math.round(half*100.0)/100.0;
					    	gst = Math.round(gst*100.0)/100.0;
					    	int es = updateMedicineChargeGST(priscription2.getId(),gst,half,tempvat,tvat);
						}
				  }
				  
				  if(amt>=bal){
			    	  	 double discvalue = bal*discper/100;
			    	  	 discvalue = Math.round(discvalue);
			    	  	 priscription.setPayment(DateTimeUtils.changeFormat(bal));
			    	  	 double totalbal = bal-discvalue;
			    	  	 if(totalbal<0){
							 totalbal=0;
						 }
			    	  	 //  19 Sep 2018 generate pharmacy payment Seq No according location
						 int paymentseqno = getPharmacyPaymentSeqNo(priscription.getLocation());
						 paymentseqno = paymentseqno+1;
			    	     int x=recordPaymentMedicine(String.valueOf(billno),totalbal,priscription.getPaymode(), priscription.getDate(), priscription.getClientId(), "", 0, priscription.getCardno(), priscription.getPclientid(),priscription.getUserid(),priscription.getLocation(),issame,paymentseqno);
			    	     int paymentid = x;
			    	     x=saveChildBillClear(parentid, priscription);
			    	     
			    	     result =updatePaymentNote(x, paynote);
			    	     String sql1="update apm_medicine_bill set balance=0,final_paymode='"+priscription.getPaymode()+"' where id="+billno+"  "; 
			    	     PreparedStatement ps1=connection.prepareStatement(sql1);
			    	     result=  ps1.executeUpdate();
			    	     amt=amt-bal;
			    	     
			    	     boolean flag = checkBillCreditStatus(""+billno);
			    	     if(flag){
			    	    	 int res = updateGstPaymentFlag(""+billno);
			    	     }
			    	     
			    	     if(!priscription.getPaymode().equals("") || !priscription.getPaymode().equals("Credit")){
						  		String locationname = getLocationName(rs.getString(5));
								String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds(locationname);
								String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, priscription.getPaymode(), "0","2",0);
					
								double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
								lbal = lbal + totalbal;
								String credit = "" + totalbal + "";
								String ldebit = "0";
								String product = "xxxxx";
								String partyid = priscription.getClientId();
								String otherclientid =priscription.getPclientid();
								  
								String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
								int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
											ledgerid, lcommencing, "0", 0,otherclientid,"0","0","0",""+paymentid+"",0,0,rs.getString(5));
									
								//second effect
								lbal = 0;
								credit = "0";
								ldebit = "" + totalbal + "";
								product = "xxxxx";
								partyid = priscription.getClientId();
								otherclientid =priscription.getPclientid();
								lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
								saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
											ledgerid, lcommencing, "0", 0,otherclientid,"0","0","0",""+paymentid+"",0,0,rs.getString(5));
						  	}
			    	     
			    	     if (discvalue > 0) {
								String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds("Discount");
								String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, "0", "0","2",0);

								double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
								lbal = lbal + discvalue;
								String credit = "" + discvalue + "";
								String ldebit = "0";
								String product = "" + billno + "";
								String partyid = priscription.getClientId();
								String otherclientid =priscription.getPclientid();
								String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
								int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
										ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,rs.getString(5));
								
								//second effect
								 lbal = 0;
								 credit = "0";
								 ldebit = "" + discvalue + "";
								 product = "xxxxx";
								 partyid = priscription.getClientId();
								 otherclientid =priscription.getPclientid();
								 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
								 saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
										ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,rs.getString(5));
							}
			    	     
			    	     continue;
			    	     
			      } else {
			    	     double temp=bal-amt;
			    	     priscription.setPayment(DateTimeUtils.changeFormat(amt));
			    	     double discvalue = amt*discper/100;
			    	  	 discvalue = Math.round(discvalue);
			    	  	 double totalbal = amt-discvalue;
			    	  	 //  19 Sep 2018 generate pharmacy payment Seq No according location
						 int paymentseqno = getPharmacyPaymentSeqNo(priscription.getLocation());
						 paymentseqno = paymentseqno+1;
						 if(totalbal<0){
							 totalbal=0;
						 }
			    	     int x=recordPaymentMedicine(String.valueOf(billno),totalbal,priscription.getPaymode(), priscription.getDate(), priscription.getClientId(), "", 0, priscription.getCardno(), priscription.getPclientid(),priscription.getUserid(),priscription.getLocation(),issame,paymentseqno);
			    	     int paymentid = x;
			    	     x=saveChildBillClear(parentid, priscription);
			    	     String sql1="update apm_medicine_bill set balance="+temp+",final_paymode='"+priscription.getPaymode()+"' where id="+billno+"  "; 
			    	     PreparedStatement ps1=connection.prepareStatement(sql1);
			    	     result=  ps1.executeUpdate();
			    	     result =updatePaymentNote(x, paynote);
			    	     
			    	     boolean flag = checkBillCreditStatus(""+billno);
			    	     if(flag){
			    	    	 int res = updateGstPaymentFlag(""+billno);
			    	     }
			    	     
			    	     if(!priscription.getPaymode().equals("") || !priscription.getPaymode().equals("Credit")){
						  		String locationname = getLocationName(rs.getString(5));
								String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds(locationname);
								String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, priscription.getPaymode(), "0","2",0);
					
								double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
								lbal = lbal + totalbal;
								String credit = "" + totalbal + "";
								String ldebit = "0";
								String product = "xxxxx";
								String partyid = priscription.getClientId();
								String otherclientid =priscription.getPclientid();
								  
								String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
								int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
											ledgerid, lcommencing, "0", 0,otherclientid,"0","0","0",""+paymentid+"",0,0,rs.getString(5));
									
								//second effect
								lbal = 0;
								credit = "0";
								ldebit = "" + totalbal + "";
								product = "xxxxx";
								partyid = priscription.getClientId();
								otherclientid =priscription.getPclientid();
								lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
								saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
											ledgerid, lcommencing, "0", 0,otherclientid,"0","0","0",""+paymentid+"",0,0,rs.getString(5));
						  	}
			    	     	
			    	     if (discvalue > 0) {
								String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds("Discount");
								String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, "0", "0","2",0);

								double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
								lbal = lbal + discvalue;
								String credit = "" + discvalue + "";
								String ldebit = "0";
								String product = "" + billno + "";
								String partyid = priscription.getClientId();
								String otherclientid =priscription.getPclientid();
								String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
								int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
										ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,rs.getString(5));
								
								//second effect
								 lbal = 0;
								 credit = "0";
								 ldebit = "" + discvalue + "";
								 product = "xxxxx";
								 partyid = priscription.getClientId();
								 otherclientid =priscription.getPclientid();
								 lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
								 saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
										ledgerid, lcommencing, "0", 0,otherclientid,"0","0",""+billno+"","0",0,0,rs.getString(5));
							}
			    	     	
			    	     break;
			      }
				
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return parentid;
	}
	
	
	public int updateMedicineChargeGST(int id, double gst, double half, double tempvat, double tvat) {
		int res =0;
		try {
			String sql ="update apm_medicine_charges set discount_share=?,cgst=?, sgst=?,tgstamt=?,gstper=? where id='"+id+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ""+tempvat);
			preparedStatement.setString(2, ""+half);
			preparedStatement.setString(3, ""+half);
			preparedStatement.setString(4, ""+gst);
			preparedStatement.setString(5, ""+tvat);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Priscription> getMedicineChargeList(int billno) {
		ArrayList<Priscription> arrayList = new ArrayList<Priscription>();
		InventoryProductDAO productDAO = new JDBCInventoryProductDAO(connection);
		try {
			String sql ="select id, invoiceid, charge, quantity, product_id, gstper from apm_medicine_charges where invoiceid='"+billno+"' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setBillno(rs.getString(2));
				priscription.setMrp(DateTimeUtils.changeFormat(rs.getDouble(3)));
				priscription.setQty(rs.getInt(4));
				priscription.setProduct_id(""+rs.getInt(5));
				double gstper = rs.getDouble(6);
				if(gstper==0){
					Product product=productDAO.getProduct(priscription.getProduct_id());
					gstper= Double.parseDouble(product.getVat());
				}
				priscription.setVat(DateTimeUtils.changeFormat(gstper));
				arrayList.add(priscription);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public int updateBillDiscount(double totaldisc, int billno, double totaldebit) {
		int result =0;
		try {
			String sql="update apm_medicine_bill set  discount='"+totaldisc+"',debit='"+totaldebit+"'  where id='"+billno+"' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public double getPreviousBillDiscount(int billno) {
		double result =0;
		try {
			String sql="select discount from apm_medicine_bill where id='"+billno+"' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public double getTotalCardCollectedByUser(String userid,String fromdate,String todate,String location) {
		
		double res=0;
		try {
			
			String sql="";
			if(!location.equals("0")){
				sql="select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='Card' and userid='"+userid+"' and location='"+location+"' ";
			} else {
				sql="select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='Card' and userid='"+userid+"' ";
			}
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				   res=rs.getDouble(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return res;
	}

	public double getTotalCashCollectedByUser(String userid,String fromdate,String todate,String location) {
		
		double res=0;
		try {
			String sql="";
			if(!location.equals("0")){
				sql="select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='Cash' and userid='"+userid+"' and location="+location+" ";
			} else {
				sql="select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='Cash' and userid='"+userid+"' ";	
			}
			
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				   res=rs.getDouble(1);
			}
			 
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return res;
	}

	public ArrayList<Master> getAllLocation() {

		ArrayList<Master> list= new ArrayList<Master>();
		try {
			
			String sql="SELECT id,name from apm_condition where pharmacy=1";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()){
				
				 Master master =new Master();
				 master.setId(rs.getInt(1));
				 master.setName(rs.getString(2));
				 list.add(master);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}
	
	public String getLocationName(String id) {
		
		String name="";
		try {
			String sql="select name from apm_condition where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql); 
			 
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				
				 name= rs.getString(1);
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return name;
	}

	public int deleteMedicalBill(String bill) {

		int result=0;
		try {
			
			String sql="delete from apm_medicine_bill where id="+bill+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			result =ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public int updateBillCharges(int saleqty, String mrp, int id) {
		
		int result=0;
		try {
			String sql="update apm_medicine_charges set charge=?,quantity=? where id="+id+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, mrp);
			ps.setInt(2, saleqty);
			
			result =ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int saveNewBillCharge(CompleteAppointment appointment, int saleqty,
			int mrp, String billno) {
		
		
		return 0;
	}

	public int updatePaymentMedicine(String billno, String total, String date,
			String string, double discount, String vat) {
		int result=0;
		try {
			
			String sql="update apm_medicine_payment set payment='"+total+"',date='"+date+"' where billno="+billno+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			result =ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public int updateBillTotal(String total, String billno,String edit_note) {

		int result=0;
		try {
			
			String sql="update apm_medicine_bill set debit=?,edited=?,edit_note=? where id="+billno+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, total);
			ps.setString(2, "1");
			ps.setString(3, edit_note);
			
			result =ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<String> getAllBillListByExDoctor(String fromdate,
			String todate, String fullname) {

		ArrayList<String> list= new ArrayList<String>();
		
		try {
			String sql="select id from apm_pharmacy_client where reference='"+fullname+"' ;";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()){
				 
				 String pclientid= rs.getString(1); 
				 
				 String sql2="select id from apm_medicine_bill where pclientid="+pclientid+";";
				 PreparedStatement ps2=connection.prepareStatement(sql2);
				 ResultSet rs2= ps2.executeQuery();
				 
				 while(rs2.next()){
					  
					  list.add(rs2.getString(1));
				 }
				
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}
	public Priscription setUserEditBillAuthority(String userid,String status,String datetime,String currentuser) {
		Priscription priscription = new Priscription();
		try {
			try{
				String query = "update apm_pharmacy_user set edit_bill='"+status+"',edit_billDT='"+datetime+"',edit_billBY='"+currentuser+"' where id="+userid+"";
				PreparedStatement preparedStatement1 = connection.prepareStatement(query);
				int result = preparedStatement1.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			String sql = "select id, edit_bill from apm_pharmacy_user where id="+userid+""; 
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				priscription.setId(rs.getInt(1));
				priscription.setEdit_bill(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return priscription;
	}
	public Priscription setUserDeleteBillAuthority(String userid,String status,String datetime,String currentuser) {
		Priscription priscription = new Priscription();
		try {
			try{
				String query = "update apm_pharmacy_user set delete_bill='"+status+"',delete_billDT='"+datetime+"',delete_billBY='"+currentuser+"' where id="+userid+"";
				PreparedStatement preparedStatement1 = connection.prepareStatement(query);
				int result = preparedStatement1.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			String sql = "select id, delete_bill from apm_pharmacy_user where id="+userid+""; 
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				priscription.setId(rs.getInt(1));
				priscription.setDelete_bill(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return priscription;
	}

	public Priscription setUserEditPurchaseOrderAuthority(String userid,
			String status,String datetime,String currentuser) {
		Priscription priscription = new Priscription();
		try {
			try{
				String query = "update apm_pharmacy_user set edit_purchaseorder='"+status+"',edit_purchaseorderDT='"+datetime+"',edit_purchaseorderBY='"+currentuser+"' where id="+userid+"";
				PreparedStatement preparedStatement1 = connection.prepareStatement(query);
				int result = preparedStatement1.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			String sql = "select id, edit_purchaseorder from apm_pharmacy_user where id="+userid+""; 
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				priscription.setId(rs.getInt(1));
				priscription.setEdit_purchaseorder(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return priscription;
	}

	public Priscription setUserDeletePurchaseOrderAuthority(String userid,
			String status,String datetime,String currentuser) {
		Priscription priscription = new Priscription();
		try {
			try{
				String query = "update apm_pharmacy_user set delete_purchaseorder='"+status+"',delete_purchaseorderDT='"+datetime+"',delete_purchaseorderBY='"+currentuser+"' where id="+userid+"";
				PreparedStatement preparedStatement1 = connection.prepareStatement(query);
				int result = preparedStatement1.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			String sql = "select id, delete_purchaseorder from apm_pharmacy_user where id="+userid+""; 
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				priscription.setId(rs.getInt(1));
				priscription.setDelete_purchaseorder(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return priscription;
	}
	public Priscription setUserEditCatalogue(String userid,
			String status,String datetime,String currentuser) {
		Priscription priscription = new Priscription();
		try {
			try{
				String query = "update apm_pharmacy_user set edit_catalogue='"+status+"',edit_catalogueDT='"+datetime+"',edit_catalogueBY='"+currentuser+"' where id="+userid+"";
				PreparedStatement preparedStatement1 = connection.prepareStatement(query);
				int result = preparedStatement1.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			String sql = "select id, edit_catalogue from apm_pharmacy_user where id="+userid+""; 
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				priscription.setId(rs.getInt(1));
				priscription.setEdit_catalogue(rs.getInt(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return priscription;
	}
	public Priscription setUserDeleteCatalogue(String userid,
			String status,String datetime,String currentuser) {
		Priscription priscription = new Priscription();
		try {
			try{
				String query = "update apm_pharmacy_user set delete_catalogue='"+status+"',delete_catalogueDT='"+datetime+"',delete_catalogueBY='"+currentuser+"' where id="+userid+"";
				PreparedStatement preparedStatement1 = connection.prepareStatement(query);
				int result = preparedStatement1.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			String sql = "select id, delete_catalogue from apm_pharmacy_user where id="+userid+""; 
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				priscription.setId(rs.getInt(1));
				priscription.setDelete_catalogue(rs.getInt(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return priscription;
	}
	
	public String getPharmacyLocation(String locid){
		
		String res="";
		try {
			
			String sql="select name from apm_condition where id="+locid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				
				res =rs.getString(1);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		 
		return res;
	}

	public double getPayAmount(int billno) {

		double res=0;
		try {
			String sql="select sum(payment) from apm_medicine_payment where billno="+billno+"  ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				 
				res=rs.getDouble(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return res;
	}

	public double getCahPayAmount(int id) {
		double res=0;
		try {
			String sql="select sum(payment) from apm_medicine_payment where billno="+id+" and paymode='Cash' ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				 
				res=rs.getDouble(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return res;
	}

	public double getCardToday(String location,String fromdate,String todate) {

		double  res=0;
		try {
			 String sql="";
			 if(!location.equals("0")){
				 sql="select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='Card' and location='"+location+"' and (tpid=0 or tpid is NULL) ";
			 } else {
				 sql="select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='Card' and (tpid=0 or tpid is NULL) ";
			 }
			 
			 PreparedStatement ps=connection.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
			 while(rs.next()){
				  
				 res=rs.getDouble(1);
			 }
			
		} catch (Exception e) {

			e.printStackTrace();
		} 
		
		return res;
	}

	public double getCashToday(String location,String fromdate,String todate) {

		double  res=0;
		try {
			 String sql="";
			 if(!location.equals("0")){
				 
				  sql="select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='Cash' and location='"+location+"' and (tpid=0 or tpid is NULL) ";
			 } else {
				  sql="select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='Cash' and (tpid=0 or tpid is NULL) ";
			 }
			 PreparedStatement ps=connection.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
			 while(rs.next()){
				  
				 res=rs.getDouble(1);
			 }
			
		} catch (Exception e) {

			e.printStackTrace();
		} 
		
		return res;
	}

	public double getReturnToday(String location,String fromdate,String todate) {
		double  res=0;
		try {
			 StringBuffer buffer=new StringBuffer();
			 buffer.append("select sum(debit) from apm_medicine_bill inner join apm_medicine_payment ");
			 buffer.append("on apm_medicine_payment.billno=apm_medicine_bill.id where ");
			 buffer.append("apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' and isreturn=1 ");
			 buffer.append("and paymode='Cash' ");
			 if(!location.equals("0")){
				 buffer.append("and apm_medicine_bill.location="+location+" ");
			 }
			 PreparedStatement ps=connection.prepareStatement(buffer.toString());
			 ResultSet rs=ps.executeQuery();
			 while(rs.next()){
				  
				 res=rs.getDouble(1);
			 }
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}
	public double getCreditReturn(String location,String fromdate,String todate) {
		double  res=0;
		try {
			 StringBuffer buffer=new StringBuffer();
			 buffer.append("select sum(credit) from apm_medicine_bill inner join apm_medicine_payment ");
			 buffer.append("on apm_medicine_payment.billno=apm_medicine_bill.id where ");
			 buffer.append("apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' and isreturn=1 ");
			 buffer.append("and paymode='Credit' ");
			 if(!location.equals("0")){
				 buffer.append("and apm_medicine_bill.location="+location+" ");
			 }
			 PreparedStatement ps=connection.prepareStatement(buffer.toString());
			 ResultSet rs=ps.executeQuery();
			 while(rs.next()){
				  
				 res=rs.getDouble(1);
			 }
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}
	public double getHospitalReturn(String location,String fromdate,String todate) {
		double  res=0;
		try {
			 StringBuffer buffer=new StringBuffer();
			 buffer.append("select sum(debit) from apm_medicine_bill inner join apm_medicine_payment ");
			 buffer.append("on apm_medicine_payment.billno=apm_medicine_bill.id where ");
			 buffer.append("apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' and isreturn=1 ");
			 buffer.append("and paymode='Hospital' ");
			 if(!location.equals("0")){
				 buffer.append("and apm_medicine_bill.location="+location+" ");
			 }
			 PreparedStatement ps=connection.prepareStatement(buffer.toString());
			 ResultSet rs=ps.executeQuery();
			 while(rs.next()){
				  
				 res=rs.getDouble(1);
			 }
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	public double getTodayDisc(String location,String fromdate,String todate) {
		double  res=0;
		try {
			 String sql="";
			 if(!location.equals("0")){
				 sql="select sum(discount) from apm_medicine_bill where date between '"+fromdate+"' and '"+todate+"'  and location='"+location+"' and (tpid=0 or tpid is NULL) ";
			 } else {
				 sql="select sum(discount) from apm_medicine_bill where date between '"+fromdate+"' and '"+todate+"' and (tpid=0 or tpid is NULL) ";
			 }
			 
			 PreparedStatement ps=connection.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
			 while(rs.next()){
				  
				 res=rs.getDouble(1);
			 }
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	public int updateBillBalance(String billno) {
		
		int result=0;
		try {
			
			String sql="update apm_medicine_bill set balance=0 where id="+billno+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			result =ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}
	
	public Priscription setinhousepatient(String status) {
		Priscription priscription = new Priscription();
		try {
			try{
				String query = "update apm_pharmacy_user set inhousepatient='"+status+"'";
				PreparedStatement preparedStatement1 = connection.prepareStatement(query);
				int result = preparedStatement1.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			String sql = "select inhousepatient from apm_pharmacy_user"; 
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				priscription.setInhousepatient(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return priscription;
	}

	public String getinhousepatientstatus() {
		String inhousepatient = "";
		try {
			String sql = "select inhousepatient from apm_pharmacy_user";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				inhousepatient = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inhousepatient;
	}

	public String getIpdIdFromClientID(int id) {
		String ipdid="0";
		try {
			String sql="SELECT id from ipd_addmission_form where clientid="+id+" order by id desc limit 0,1";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				ipdid =rs.getString(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return ipdid;
	}

	public String getappointmentinfo(int id) {
		String pract_name="0";
		try {
			String sql="SELECT diaryusername from apm_available_slot where clientid="+id+" order by id desc limit 0,1";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				pract_name =rs.getString(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return pract_name;
	}
	public int validatepharmacyuser(String userid) {
		int result =0;
		try {
			String sql = "select userid from apm_user where userid='"+userid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				result=1;
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateRefundBillBalance(String oldbill, String refund) {

		int result=0;
		double bal=0;
		try {
			String sql="select balance from apm_medicine_bill where id="+oldbill+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()){
				
				 bal= rs.getDouble(1);
			}
			
			double nowbal= bal- Double.parseDouble(refund);
			if(nowbal<0){
				nowbal=0;
			}
			
			String sql1="update apm_medicine_bill set balance="+nowbal+" where id="+oldbill+" ";
			PreparedStatement ps1=connection.prepareStatement(sql1);
			result =ps1.executeUpdate();  
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Priscription> getLocationWiseReport(String fromdate,
			String todate, String location) {

		ArrayList<Priscription> list= new ArrayList<Priscription>();
		try {
			
			StringBuffer buffer =new StringBuffer();
			if(location.equals("0")){
			   buffer.append("SELECT id from apm_pharmacy_location  ");
			} else {
				buffer.append("SELECT id from apm_pharmacy_location where id="+location+" ");
			}
			
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			
			ResultSet rs =ps.executeQuery();
			while(rs.next()){

					String loc =rs.getString(1);
					Priscription priscription=new Priscription();
					priscription.setLocation(getLocationName(loc));
					double cashAmt=getCashToday(loc, fromdate, todate);
					double cardAmt= getCardToday(loc, fromdate, todate);
					double refundAmt= getReturnToday(loc, fromdate, todate);
					double balAmt= getTotalBalanceByLocation(loc,fromdate,todate);
					double total= (cashAmt + cardAmt) - refundAmt;
					priscription.setRefundtotal(refundAmt);
					priscription.setBalance(DateTimeUtils.changeFormat(balAmt));
					priscription.setTotalcard(cardAmt);
					priscription.setTotalcash(cashAmt);
					priscription.setTotal(total);
				    list.add(priscription);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}
	
	public String getClientIDFromBillNo(int billno) {
		String clietid="0";
		try {
			
			String sql="select clientid from apm_medicine_bill where id="+billno+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				  
				clietid=rs.getString(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		} 
		
		return clietid;
	}

	public int returnPayment(String oldbill,String refund) {

		int result=0;
		try {
			double amt=0;
			String sql="select payment from apm_medicine_payment where billno="+oldbill+" where pflag=1  ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				 amt+=  rs.getDouble(1);
			}
			double nopayment= amt- Double.parseDouble(refund);
			if(nopayment<0){
				nopayment=0;
			}
			
			String sql1="update apm_medicine_payment set payment="+nopayment+" where billno="+oldbill+" and pflag=1 ";
			PreparedStatement ps1=connection.prepareStatement(sql1);
			
			result =ps1.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public int updatePFlag(int paymentid) {

		int result=0;
		
		try {
			String sql="update apm_medicine_payment set pflag=1 where id="+paymentid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			result =ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}	
		
		return result;
	}

	public int recordRefundPayment(int paymentid, String refund) {
      int result=0;
		
		try {
			String sql="update apm_medicine_payment set refundamt="+refund+" where id="+paymentid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			result =ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}	
		
		return result;
	}

	public int setReturnBillNumber(String oldbill, int billno) {

		int result=0;
		try {
			String sql="update apm_medicine_bill set retunbillid="+oldbill+" where id="+billno+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			result =ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public String getOriginalBillId(int billno) {

		String oldbill="0";
		try {
			String sql="select retunbillid from apm_medicine_bill where id="+billno+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				oldbill= rs.getString(1);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return oldbill;
	}
	
	public int addEstimateMedicineBill(CompleteAppointment appointment) {

		int result=0;
		
		try {
			String sql="insert into apm_medicine_estimate_bill (payby,date,discount,debit,vat,clientid,chargetype,notes,pclientid,priscid,balance,location,cgst,sgst,time,userid,refundamt) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, appointment.getPayBuy());
			ps.setString(2, appointment.getInvoiceDate());
			ps.setDouble(3, appointment.getDiscount());
			ps.setDouble(4, appointment.getTotal());
			ps.setDouble(5, appointment.getVat());
			ps.setString(6, appointment.getClientId());
			ps.setString(7, appointment.getChargeType());
			ps.setString(8, appointment.getNotes());
			ps.setString(9, appointment.getPclientid());
			ps.setInt(10, appointment.getPriscid());
			ps.setString(11, appointment.getBalance());
			ps.setString(12, appointment.getLocation());
			ps.setString(13, appointment.getCgst());
			ps.setString(14, appointment.getSgst());
			ps.setString(15, appointment.getInvoiceTime());
			ps.setString(16, appointment.getUserid());
			ps.setDouble(17, appointment.getRefundAmt());
			result=ps.executeUpdate();
			
			if(result>0){
				  
				  ResultSet rs=ps.getGeneratedKeys();
				  while(rs.next()){
					   
					    result=rs.getInt(1);
				  }
				  
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteEstimateBill(String estimatebill) {

		int result=0;
		try {
			String sql="delete from apm_medicine_estimate_bill where id='"+estimatebill+"' ";
			PreparedStatement ps=connection.prepareStatement(sql);
			result =ps.executeUpdate();
			
			String sql1="delete from apm_medicine_estimate_charges where invoiceid="+estimatebill+" ";
			PreparedStatement ps1=connection.prepareStatement(sql1);
			result =ps1.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return result;
	}

	public Priscription setBackDateAccess(String userid, String status,String datetime,String currentuser) {
		Priscription priscription = new Priscription();
		try {
			try{
				String query = "update apm_pharmacy_user set back_date='"+status+"' where id="+userid+"";
				PreparedStatement preparedStatement1 = connection.prepareStatement(query);
				int result = preparedStatement1.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			String sql = "select id, back_date from apm_pharmacy_user where id="+userid+""; 
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				priscription.setId(rs.getInt(1));
				priscription.setAccess_back_date(rs.getInt(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return priscription;
	}
	public Priscription setTpBillAccess(String userid, String status,String datetime,String currentuser) {
		Priscription priscription = new Priscription();
		try {
			try{
				String query = "update apm_pharmacy_user set third_party='"+status+"',third_partyDT='"+datetime+"',third_partyBY='"+currentuser+"' where id="+userid+"";
				PreparedStatement preparedStatement1 = connection.prepareStatement(query);
				int result = preparedStatement1.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			String sql = "select id, third_party from apm_pharmacy_user where id="+userid+""; 
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				priscription.setId(rs.getInt(1));
				priscription.setTpbill(rs.getInt(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return priscription;
	}

	public int cancelMedicineBill(String bill) {

		int result=0;
		try {
			String sql="update apm_medicine_bill set deleted=1,debit=0,balance=0 where id="+bill+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			result =ps.executeUpdate();
			
			String sql2="update apm_medicine_payment set payment=0 where billno="+bill+" ";
			PreparedStatement statement= connection.prepareStatement(sql2);
			result =statement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/*public ArrayList<Product> getParentProductTransferData(String fromdate,
			String todate,String loc) {
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		ArrayList<Product> arrayList = new ArrayList<Product>();
		try {
			
			
			StringBuffer buffer = new StringBuffer();
			boolean flag=true;
			buffer.append("select id, request_date, issued_date, from_location, to_location,time,r_status,req_or_trans,admin_status from inventory_parent_transfer_log ");
			
			if(loc.equals("0")){
				flag = false;
			} else {
				buffer.append("where from_location="+loc+" and r_status=2 and admin_status=1 ");
			}
			
			
			if(flag){
				buffer.append("and  request_date between '"+fromdate+"' and '"+todate+"'  ");
			}
			else {
				buffer.append("where r_status=1 and request_date between '"+fromdate+"' and '"+todate+"'  ");
			}
			
			//String sql = "select id, request_date, issued_date, from_location, to_location,time from inventory_parent_transfer_log";
			String sql = buffer.toString();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setParentid(""+rs.getInt(1));
				String request_date = DateTimeUtils.getCommencingDate1(rs.getString(2));
				String issued_date = DateTimeUtils.getCommencingDate1(rs.getString(3));
				String time = rs.getString(6);
				product.setRequest_date(request_date+" "+ time);
				product.setIssued_date(issued_date+" "+time);
				product.setRequest_date(rs.getString(2));
				product.setIssued_date(rs.getString(3));
				String fromlocation = inventoryProductDAO.pharmacyLocationNameByID(rs.getString(4));
				String tolocation = inventoryProductDAO.pharmacyLocationNameByID(rs.getString(5));
				product.setStatus(rs.getString(7));
				product.setFrom_location(fromlocation);
				product.setTo_location(tolocation);
				product.setReq_or_transfer(rs.getString(8));
				arrayList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}*/
	/*public Product getRequestedMedicineid(String s) {
		Product product = new Product();
		try {
			String sql = "";
				sql = "select id, product_id, name, quantity, date, status,location from apm_medicine_request where id="+s+"";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs  = preparedStatement.executeQuery();
			while (rs.next()) {
				product.setId(rs.getInt(1));
				product.setProduct_id(rs.getString(2));
				product.setLocation(rs.getString(7));
				product.setRequest_date(rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}*/

	

	/*public int saveNewMedicineRequestParent(Product product, String req_qty) {
		int result =0;
		try {
			String sql = "insert into inventory_parent_transfer_log (request_date, from_location, time,r_status) values (?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			String data = product.getRequest_date();
			String[] datetime = data.split(" ");
			preparedStatement.setString(1, datetime[0]);
			preparedStatement.setString(2, product.getLocation());
			preparedStatement.setString(3, datetime[1]);
			preparedStatement.setString(4, "0");
			result = preparedStatement.executeUpdate();
			if(result>0)
			{
				ResultSet resultSet=preparedStatement.getGeneratedKeys();
				while(resultSet.next()){
				     result= resultSet.getInt(1);	
				   int res1 =  saveNewMedicineRequestChild(product,req_qty,result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/
	
	public int saveNewMedicineRequestChild(Product product, String req_qty,
			int result) {
		int result1 =0;
		try {
			String sql = "insert into inventory_transfer_log (parentid, old_prodid, new_prodid, qty) values (?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ""+result);
			preparedStatement.setString(2, product.getProduct_id());
			preparedStatement.setString(3, "0");
			preparedStatement.setString(4, req_qty);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result1;
	}
	
	
	public int saveNewMedicineRequestParent(Product product) {
		int result =0;
		try {
			String sql = "insert into inventory_parent_transfer_log (request_date, from_location, time,r_status,req_or_trans) values (?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			String data = product.getRequest_date();
			String[] datetime = data.split(" ");
			preparedStatement.setString(1, datetime[0]);
			preparedStatement.setString(2, product.getLocation());
			preparedStatement.setString(3, datetime[1]);
			preparedStatement.setString(4, "0");
			preparedStatement.setString(5, "0");
			result = preparedStatement.executeUpdate();
			if(result>0)
			{
				ResultSet resultSet=preparedStatement.getGeneratedKeys();
				while(resultSet.next()){
				     result= resultSet.getInt(1);	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Product> getParentProductTransferData(String fromdate,
			String todate,String loc) {
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		ArrayList<Product> arrayList = new ArrayList<Product>();
		try {
			
			
			StringBuffer buffer = new StringBuffer();
			boolean flag=true;
			buffer.append("select id, request_date, issued_date, from_location, to_location,time,r_status,req_or_trans,admin_status from inventory_parent_transfer_log ");
			
			if(loc.equals("0")){
				flag = false;
			} else {
				buffer.append("where from_location="+loc+" and r_status=2 and admin_status=1 ");
			}
			
			
			if(flag){
				buffer.append("and  request_date between '"+fromdate+"' and '"+todate+"'  ");
			}
			else {
				buffer.append("where r_status=1 and request_date between '"+fromdate+"' and '"+todate+"'  ");
			}
			
			//String sql = "select id, request_date, issued_date, from_location, to_location,time from inventory_parent_transfer_log";
			String sql = buffer.toString();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setParentid(""+rs.getInt(1));
				String request_date = DateTimeUtils.getCommencingDate1(rs.getString(2));
				String issued_date = DateTimeUtils.getCommencingDate1(rs.getString(3));
				String time = rs.getString(6);
				product.setRequest_date(request_date+" "+ time);
				product.setIssued_date(issued_date+" "+time);
				product.setRequest_date(rs.getString(2));
				product.setIssued_date(rs.getString(3));
				String fromlocation = inventoryProductDAO.pharmacyLocationNameByID(rs.getString(4));
				String tolocation = inventoryProductDAO.pharmacyLocationNameByID(rs.getString(5));
				product.setStatus(rs.getString(7));
				product.setFrom_location(fromlocation);
				product.setTo_location(tolocation);
				product.setReq_or_transfer(rs.getString(8));
				arrayList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public int updateAproveTransferLog(String parentid, String status, String aprove) {
		int result=0;
		
		try {
			SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Calendar calendar=Calendar.getInstance();
		    String	currentdate =dateFormat.format(calendar.getTime());
		    
			String sql="";
			if(aprove.equals("1")){
				sql="update inventory_parent_transfer_log set r_status="+status+", admin_status=1, admin_aprove_date='"+currentdate+"' where id="+parentid+" ";
			}else{
				sql="update inventory_parent_transfer_log set r_status="+status+",head_aprove_date='"+currentdate+"' where id="+parentid+" ";
			}
			PreparedStatement ps=connection.prepareStatement(sql);
			result= ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public int updateRejectTransferLog(String parentid, String status,String notes, String reject) {
		int result=0;
		try {
			SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Calendar calendar=Calendar.getInstance();
		    String	currentdate =dateFormat.format(calendar.getTime());
			
			String sql="";
			if(reject.equals("1")){
				sql="update inventory_parent_transfer_log set r_status="+status+", admin_notes='"+notes+"', admin_aprove_date='"+currentdate+"' where id="+parentid+" ";
			}else{
				sql="update inventory_parent_transfer_log set r_status="+status+", head_notes='"+notes+"', head_aprove_date='"+currentdate+"' where id="+parentid+" ";
			}
			
			PreparedStatement ps=connection.prepareStatement(sql);
			result= ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	

	public int saveChildRequestedData(String product_id, int id,
			String reqqty, String parentid, String location) {
		int result =0;
		try {
			String sql = "insert into inventory_transfer_log (parentid, old_prodid, new_prodid, qty,location) values (?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ""+parentid);
			preparedStatement.setString(2, ""+product_id);
			preparedStatement.setString(3, ""+id);
			preparedStatement.setString(4, reqqty);
			preparedStatement.setString(5, location);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	public int deleteTempRequestedData(String parentid) {
		int result = 0;
		try {
			String sql ="delete from inventory_transfer_log where parentid="+parentid+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getRequisitionCount(String loc) {
		int result = 0;
		try {
			String sql ="";
			if(loc.equals("0")||loc.equals("")){
				sql ="select count(*) from inventory_parent_transfer_log where r_status=1";
			}else{
				sql ="select count(*) from inventory_parent_transfer_log where r_status=2 and from_location="+loc+"";
			}
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int updatePaymentNote(int payid, String paynote) {

		int result=0;
		try {
			String sql="update apm_medicine_payment set paynote='"+paynote+"',pflag=1 where id="+payid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			result =ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public String getBilPayNote(int billno) {

		String paynote="";
		try {
			String sql="select paynote from apm_medicine_payment where billno="+billno+" order by id asc limit 0,1 ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				paynote=rs.getString(1);
				if(paynote==null){
					paynote="";
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return paynote;
		
	}

	public double getChequePayment(String location, String fromdate,
			String todate) {
		double  res=0;
		try {
			 String sql="";
			 if(!location.equals("0")){
				 sql="select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='Cheque' and location='"+location+"' and (tpid=0 or tpid is NULL) ";
			 } else {
				 sql="select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='Cheque' and (tpid=0 or tpid is NULL)";
			 }
			 
			 PreparedStatement ps=connection.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
			 while(rs.next()){
				  
				 res=rs.getDouble(1);
			 }
			
		} catch (Exception e) {

			e.printStackTrace();
		} 
		
		return res;
	}
	
	public Priscription setRequisition_AuthAccess(String userid, String status,String datetime,String currentuser) {
		Priscription priscription = new Priscription();
		try {
			try{
				String query = "update apm_pharmacy_user set requisition_auth='"+status+"',requisition_authDT='"+datetime+"',requisition_authBY='"+currentuser+"' where id="+userid+"";
				PreparedStatement preparedStatement1 = connection.prepareStatement(query);
				int result = preparedStatement1.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			String sql = "select id, requisition_auth from apm_pharmacy_user where id="+userid+""; 
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				priscription.setId(rs.getInt(1));
				priscription.setRequisition_auth(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return priscription;
	}

	public double getNeftPayment(String location, String fromdate, String todate) {

		double  res=0;
		try {
			 String sql="";
			 if(!location.equals("0")){
				 sql="select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='NEFT' and location='"+location+"' and (tpid=0 or tpid is NULL) ";
			 } else {
				 sql="select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='NEFT' and (tpid=0 or tpid is NULL) ";
			 }
			 
			 PreparedStatement ps=connection.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
			 while(rs.next()){
				  
				 res=rs.getDouble(1);
			 }
			
		} catch (Exception e) {

			e.printStackTrace();
		} 
		
		return res;
	}

	public double getChequePaymentAmt(String fromdate, String todate,
			String clientid, int res) {
		double sum=0;
		try {
			
			StringBuffer sql= new StringBuffer();
			sql.append("select sum(payment) from apm_medicine_payment ");
			if(res==1){
				sql.append("where pclientid="+clientid+" ");
			} else {
				sql.append("where clientid="+clientid+" ");
			}
			sql.append("and paymode='Cheque' and date between '"+fromdate+"' and '"+todate+"' ");
				
			PreparedStatement ps=connection.prepareStatement(sql.toString());
			ResultSet rs =ps.executeQuery();
			
			while(rs.next()){
				 
				sum =rs.getDouble(1);
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return sum;
	}

	public double getNeftPaymentAmt(String fromdate, String todate,
			String clientid, int res) {
		double sum=0;
		try {
			
			StringBuffer sql= new StringBuffer();
			sql.append("select sum(payment) from apm_medicine_payment ");
			if(res==1){
				sql.append("where pclientid="+clientid+" ");
			} else {
				sql.append("where clientid="+clientid+" ");
			}
			sql.append("and paymode='NEFT' and date between '"+fromdate+"' and '"+todate+"' ");
				
			PreparedStatement ps=connection.prepareStatement(sql.toString());
			ResultSet rs =ps.executeQuery();
			
			while(rs.next()){
				 
				sum =rs.getDouble(1);
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return sum;
	}
	
	
   public int updateBalanceByBill(String billno,String balance) {
		
		int result=0;
		try {
			
			String sql="update apm_medicine_bill set balance="+balance+" where id="+billno+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			result =ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}
   
   public int addnewRequestStock(String pid, String qty, String date,String loc,String userid) {
		int result=0;
		InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
		try {
			Product product=inventoryProductDAO.getProduct(pid);
			String sql="insert into apm_medicine_request (product_id, name, quantity, date,location,userid) values (?,?,?,?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, pid);
			ps.setString(2, product.getProduct_name());
			ps.setString(3, qty);
			ps.setString(4, date);
			ps.setString(5, loc);
			ps.setString(6, userid);
			result=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
			return result;
		}
   
   public Product getRequestedMedicineid(String s) {
		Product product = new Product();
		try {
			String sql = "";
			sql = "select id, product_id, name, quantity, date, status,location,userid from apm_medicine_request where id="+s+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs  = preparedStatement.executeQuery();
			while (rs.next()) {
				product.setId(rs.getInt(1));
				product.setProduct_id(rs.getString(2));
				product.setLocation(rs.getString(7));
				product.setRequest_date(rs.getString(5));
				product.setUserid(rs.getString(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}
   public int saveNewMedicineRequestParent(Product product,String userid) {
	   int result =0;
	   try {
	    String sql = "insert into inventory_parent_transfer_log (request_date, from_location, time,r_status,req_or_trans,userid,expected_date,warehouse_id,indentreq) values (?,?,?,?,?,?,?,?,?)";
	    PreparedStatement preparedStatement = connection.prepareStatement(sql);
	    String data = product.getRequest_date();
	    String[] datetime = data.split(" ");
	    preparedStatement.setString(1, datetime[0]);
	    preparedStatement.setString(2, product.getLocation());
	    preparedStatement.setString(3, datetime[1]);
	    preparedStatement.setString(4, "0");
	    preparedStatement.setString(5, "0");
	    preparedStatement.setString(6, userid);
	    preparedStatement.setString(7, product.getExpectedDate());
	    preparedStatement.setString(8, product.getWarehouse_id());
	    preparedStatement.setString(9, ""+product.getIndent());
	    result = preparedStatement.executeUpdate();
	    if(result>0)
	    {
	     ResultSet resultSet=preparedStatement.getGeneratedKeys();
	     while(resultSet.next()){
	          result= resultSet.getInt(1); 
	     }
	    }
	   } catch (Exception e) {
	    e.printStackTrace();
	   }
	   return result;
	  }
   public int saveNewMedicineRequestChild(Product product, String req_qty,
			int result,Product product2) {
		int result1 =0;
		try {
			String sql = "insert into inventory_transfer_log (parentid, old_prodid, new_prodid, qty,userid,location,prod_name) values (?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ""+result);
			preparedStatement.setString(2, product.getProduct_id());
			preparedStatement.setString(3, "0");
			preparedStatement.setString(4, req_qty);
			preparedStatement.setString(5, product.getUserid());
			preparedStatement.setString(6, product.getLocation());
			preparedStatement.setString(7, product2.getProduct_name());
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result1;
	}
   public int updateRequestedStatusById(String s) {
		int result=0;
		try {
			
			String sql="update apm_medicine_request set status=1 where id="+s+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			result= ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}
   public Priscription getPharacyUsrLInfo(String userid) {
		Priscription priscription = new Priscription();
		try {
			String sql = "select userid, sale_bill, discount, ledger, account, islogin,purchase_order,SMS,"
					+ "location,edit_bill,delete_bill,ipdlocation,opdlocation,edit_purchaseorder,delete_purchaseorder,"
					+ "inhousepatient,state,location,edit_catalogue,delete_catalogue,back_date,third_party,requisition_auth,"
					+ "firstname,lastname,check_stock_available,direct_transfer,firstname,lastname,usertype,inventory_report,"
					+ "return_stock,cancel_indent,return_medicine,indent_approve,approve_po,purchase_edit,cancel_po,isdotmatrix,"
					+ "showindentreq,pharm_print_backdate,creditlimit,creditlimitaccess,session_added_med,patient_edit  from apm_pharmacy_user where userid='"+userid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				priscription.setUserid(rs.getString(1));
				priscription.setSale_bill(rs.getString(2));
				priscription.setDisscount(rs.getString(3));
				priscription.setLedger(rs.getString(4));
				priscription.setAccount(rs.getString(5));
				priscription.setIslogin(""+rs.getInt(6));
				priscription.setPurchase_order(rs.getString(7));
				priscription.setSMS(rs.getString(8));
				priscription.setLocation(rs.getString(9));
				priscription.setEdit_bill(rs.getString(10));
				priscription.setDelete_bill(rs.getString(11));
				priscription.setIpdlocation(rs.getString(12));
				priscription.setOpdlocation(rs.getString(13));
				priscription.setEdit_purchaseorder(rs.getString(14));
				priscription.setDelete_purchaseorder(rs.getString(15));
				priscription.setInhousepatient(rs.getString(16));
				priscription.setState(rs.getString(17));
				priscription.setLocation(rs.getString(18));
				priscription.setEdit_catalogue(rs.getInt(19));
				priscription.setDelete_catalogue(rs.getInt(20));
				priscription.setAccess_back_date(rs.getInt(21));
				priscription.setTpbill(rs.getInt(22));
				priscription.setRequisition_auth(rs.getString(23));
				String requi = rs.getString(23);
				priscription.setFullname(rs.getString(24)+" "+ rs.getString(25));
				priscription.setCheck_stock_available(rs.getString(26));
				priscription.setDirect_transfer(rs.getString(27));
				String fullname = rs.getString(28) +" "+rs.getString(29);
			    priscription.setFullname(fullname);
			    priscription.setPharmacyUserType(rs.getInt(30));
			    priscription.setInventory_transfer(rs.getString(31));
			    priscription.setReturn_stock(rs.getString(32));
			    priscription.setCancel_indent(rs.getString(33));
			    priscription.setReturn_medicine(rs.getString(34));
			    priscription.setIndent_approve(rs.getString(35));
			    priscription.setApprove_po(rs.getBoolean(36));
			    priscription.setPurchase_edit(rs.getBoolean(37));
			    priscription.setCancel_po(rs.getString(38));
			    priscription.setIsdotmatrix(rs.getBoolean(39));
			    priscription.setShowindentreq(rs.getInt(40));
			    priscription.setPharm_print_backdate(rs.getBoolean(41));
			    priscription.setCreditlimit(rs.getDouble(42));
			    priscription.setCreditlimitaccess(rs.getBoolean(43));
			    priscription.setSession_added_med(rs.getBoolean(44));
			    priscription.setPatient_edit(rs.getBoolean(45));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return priscription;
	}
   public int updateAproveTransferLog(String parentid, String status, String aprove,String userid) {
		int result=0;
		
		try {
			SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Calendar calendar=Calendar.getInstance();
		    String	currentdate =dateFormat.format(calendar.getTime());
		    
			String sql="";
			if(aprove.equals("1")){
				sql="update inventory_parent_transfer_log set r_status="+status+", admin_status=1, admin_aprove_date='"+currentdate+"',admin_approve_userid='"+userid+"' where id="+parentid+" ";
			}else{
				sql="update inventory_parent_transfer_log set r_status="+status+",head_aprove_date='"+currentdate+"' where id="+parentid+" ";
			}
			PreparedStatement ps=connection.prepareStatement(sql);
			result= ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}


public int updateRejectTransferLog(String parentid, String status,String notes, String reject,String userid) {
		int result=0;
		try {
			SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Calendar calendar=Calendar.getInstance();
		    String	currentdate =dateFormat.format(calendar.getTime());
			
			String sql="";
			if(reject.equals("1")){
				sql="update inventory_parent_transfer_log set r_status="+status+", admin_notes='"+notes+"', admin_aprove_date='"+currentdate+"',admin_approve_userid='"+userid+"' where id="+parentid+" ";
			}else{
				sql="update inventory_parent_transfer_log set r_status="+status+", head_notes='"+notes+"', head_aprove_date='"+currentdate+"' where id="+parentid+" ";
			}
			
			PreparedStatement ps=connection.prepareStatement(sql);
			result= ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}




public int transferRequestedMedicine(String parentid,Product product) {
	InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
	int result=0;
	try {
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=Calendar.getInstance();
		String date=dateFormat.format(calendar.getTime());   
		
		Product product2 = inventoryProductDAO.getProduct(product.getProduct_id());
		ProcurementDAO procurementDAO= new JDBCProcurementDAO(connection);
		
		//minus stock
		double oldstock =Double.parseDouble(product.getStock());
		double tqty = oldstock-Double.parseDouble((product.getReqqty()));
		
//		// reorder level @jitu
//		//min /max reorder level code by jitu
//    	String catalogueid= product2.getCatalogueid(); 
//    	Product pmaster= inventoryProductDAO.getProductCatalogueDetails(catalogueid);
//    	if(pmaster.getMinorder()!=null){
//    		if(pmaster.getMinorder().equals("")){
//    			pmaster.setMinorder("0");
//    		}
//    	}else{
//    		pmaster.setMinorder("0");
//    	}
//    	if(pmaster.getPack()!=null){
//    		if(pmaster.getPack().equals("")){
//    			pmaster.setPack("0");
//    		}
//    	}else{
//    		pmaster.setPack("0");
//    	}
//    	int minorder =Integer.parseInt(pmaster.getMinorder());
//    	int allstock= inventoryProductDAO.getTotalStockProduct(catalogueid);
//    	int pack= Integer.parseInt(pmaster.getPack());
//    	int totstockAfterTransfer= allstock - Integer.parseInt(product.getReqqty());
//    	int nowstock= totstockAfterTransfer/pack;
//    	
//    	if(nowstock<=minorder){
//    		 //add to po que list
//    		product2.setDate(date);
//    		int maxorder=Integer.parseInt(pmaster.getMaxorder());
//    		int orderqty=maxorder- allstock;   
//    		product2.setQty(String.valueOf(orderqty));
//    		//add to po que
//			int res = procurementDAO.saveNewTempPo(product2);
//    	}
		// reorder level @jitu // 
		//min /max reorder level code by jitu// 
		String catalogueid= product.getCatalogueid(); 
		Product pmaster= inventoryProductDAO.getProductCatalogueDetails(catalogueid);
		int minorder =Integer.parseInt(pmaster.getMinorder());
		double allstock= inventoryProductDAO.getTotalStockProduct(catalogueid);
		//  01/10/2019 commented because min order is in qty
		double totstockAfterTransfer= allstock -  Double.parseDouble(product.getReqqty());
		if(totstockAfterTransfer<=minorder){
    		//add to po que list
    		product.setDate(date);
    		int maxorder=Integer.parseInt(pmaster.getMaxorder());
    		double orderqty=maxorder- totstockAfterTransfer; 
    		if(orderqty<0){
    			orderqty=0;
    		}
    		product.setQty(orderqty);
    		//add to po que
			int res = procurementDAO.saveNewTempPo(product);
    	}
		
    	double previousstock = oldstock;
		String query = "update inventory_product set stock="+tqty+" where id="+product.getProduct_id()+"";
		PreparedStatement statement = connection.prepareStatement(query);
		int res = statement.executeUpdate();
		
		//stock log
		String datetime = product.getDateTime();
		int qtyinout=1;
		String source ="Indent Out";
		
		
		double currentstock=tqty;
		double changeqty=Double.parseDouble(product.getReqqty());
		int reslog = inventoryProductDAO.insertIntoProductLog(product.getUserid(),datetime,product.getLocation(),qtyinout,product.getProduct_id(),product.getCatalogueid(),source,currentstock,previousstock,changeqty,"0","0",0,0,Integer.parseInt(parentid),"0");
		
		boolean checkopningstockexist = checkopeningstockexist(product.getProduct_id(),date);
		if(!checkopningstockexist){
			int r = saveOpeningStock(product.getProduct_id(),date,previousstock,"0");
		}
		
		//check medicine already present or not
		//String query2 = "select id,stock from inventory_product where hsnno="+product.getHsnno()+" and expiry_date='"+product.getExpiry_date()+"' and batch_no='"+product.getBatch_no()+"' and prodname='"+product.getProduct_name()+"' and location="+product.getReq_location()+"";
		
		String query2 ="";
		
		query2 = "select id,stock from inventory_product where expiry_date='" + product.getExpiry_date()
			+ "' and batch_no='" + product.getBatch_no() + "' and catalogueid='"+product.getCatalogueid()+"' and location='" + product.getReq_location() + "' and procurement=0";
		
		/*query2 = "select id,stock from inventory_product where global_prodid='"+product2.getGlobal_prodid()+"' and location=" + product.getReq_location() + " and procurement=0";*/
		
		PreparedStatement ps1 = connection.prepareStatement(query2);
		
		ResultSet resultSet = ps1.executeQuery();
		int id =0;
		String stock="0";
		while (resultSet.next()) {
			id=resultSet.getInt(1);
			stock = resultSet.getString(2);
		}
		
		if(id>0){
			//if present then update stock means add stock
			double newqty =Double.parseDouble(stock)+Double.parseDouble(product.getReqqty());
			String query3 = "update inventory_product set stock="+newqty+" where id="+id+"";
			PreparedStatement statement2 = connection.prepareStatement(query3);
			int res1 = statement2.executeUpdate();
			
			//stock log
			qtyinout=0;
			source ="Indent IN";
			
			currentstock=newqty;
			changeqty=Double.parseDouble(product.getReqqty());
			previousstock = Double.parseDouble(stock);
			reslog = inventoryProductDAO.insertIntoProductLog(product.getUserid(),datetime,product.getReq_location(),qtyinout,""+id,product.getCatalogueid(),source,currentstock,previousstock,changeqty,"0","0",0,0,Integer.parseInt(parentid),"0");
			
			boolean checkopningstockexist2 = checkopeningstockexist(""+id,date);
			if(!checkopningstockexist2){
				int r = saveOpeningStock(product.getProduct_id(),date,previousstock,"0");
			}
			
			//  28 Nov 2018 for opeing closing report
			String date1 = date;
			boolean checkopningstockexist1 = checkopeningstockexist(""+id,date1);
			if(checkopningstockexist1){
				Product openingproduct = checkopeningstockexistData(""+id,date1);
				double indentqty =  Integer.parseInt(product.getReqqty())+openingproduct.getIndentqty();
				int ress = updateIndentQtyInOpening(openingproduct,indentqty);
			}
		}else{
			//else add new
			//  03 May 2018
			/*String added_date="";
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			added_date = dateFormat.format(cal.getTime());
			added_date = DateTimeUtils.getCommencingDate1(added_date);*/
			
			StringBuffer sql=new StringBuffer("insert into inventory_product (prodtypeid, supplierid, brandid, prodcode, prodname, mrp, purchaseprice, saleprice, purdiscount, salediscount, weight, unit, description, categoryid,stock,mdicinenameid,expiry_date,tax,lastmodified,batch_no,vat,genericname,cell,pack,medicine_type,mfg,medicine_shedule,freeqty,userid,hsnno,location,catalogueid,added_date,barcode)");
			sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			PreparedStatement ps=connection.prepareStatement(sql.toString());
			ps.setString(1, product2.getSubcategory_id());
			ps.setString(2, product2.getVendor_id());
			ps.setString(3, product2.getBrand_id());
			ps.setString(4, product2.getProduct_code());
			ps.setString(5, product2.getProduct_name());
			ps.setString(6, product2.getMrp());
			ps.setString(7, product2.getPurchase_price());
			ps.setString(8, product2.getSale_price());
			ps.setString(9, product2.getPurchase_discount());
			ps.setString(10, product2.getSale_discount());
			ps.setString(11, product2.getWeight());
			ps.setString(12, product2.getUnit());
			ps.setString(13, product2.getDescription());
			ps.setString(14, product2.getCategory_id());
			ps.setString(15, product.getReqqty());
			ps.setString(16, product2.getMedicinenameid());
			ps.setString(17, product2.getExpiry_date());
			ps.setString(18, product2.getTax());
			ps.setString(19, product2.getLastmodified());
			ps.setString(20, product2.getBatch_no());
			ps.setString(21, product2.getVat());
			ps.setString(22, product2.getGenericname());
			ps.setString(23, "");
			ps.setString(24, product2.getPack());
			ps.setString(25, product2.getMedicine_type());  
			ps.setString(26, product2.getMfg());
			ps.setString(27, product2.getMedicine_shedule());
			ps.setString(28, product2.getFreeqty());
			ps.setString(29, null);
			ps.setString(30, product2.getHsnno());
			ps.setString(31, product.getReq_location());
			ps.setString(32, product2.getCatalogueid());
			ps.setString(33, date);
			ps.setString(34, product2.getBarcode());
			result=ps.executeUpdate();
			if(result>0)
			{
				ResultSet resultSet2=ps.getGeneratedKeys();
				while(resultSet2.next()){
				     id= resultSet2.getInt(1);	
				}
			}
			
			int globleproductid = inventoryProductDAO.updateGlobalProductId(id, product2.getGlobal_prodid());
			
			//stock log
			qtyinout=0;
			source ="Indent IN";
			
			currentstock=Double.parseDouble(product.getReqqty());
			changeqty=Double.parseDouble(product.getReqqty());
			previousstock = 0;
			reslog = inventoryProductDAO.insertIntoProductLog(product.getUserid(),datetime,product.getReq_location(),qtyinout,""+id,product.getCatalogueid(),source,currentstock,previousstock,changeqty,"0","0",0,0,Integer.parseInt(parentid),"0");
			
			boolean checkopningstockexist2 = checkopeningstockexist(""+id,date);
			if(!checkopningstockexist2){
				int r = saveOpeningStock(product.getProduct_id(),date,previousstock,"0");
			}
			
			//  28 Nov 2018 for opeing closing report
			String date1 = date;
			boolean checkopningstockexist1 = checkopeningstockexist(""+id,date1);
			if(checkopningstockexist1){
				Product openingproduct = checkopeningstockexistData(""+id,date1);
				int indentqty =  Integer.parseInt(product.getReqqty())+openingproduct.getIndentqty();
				int ress = updateIndentQtyInOpening(openingproduct,indentqty);
			}
		}
		//int res1 = saveChildRequestedData(product.getProduct_id(),id,product.getReqqty(),parentid,product2.getLocation());
		//int res2 = truncatetemporaytable();
		
		int res5 = updateChildTransferStatus(product.getId(),id);
		
	} catch (Exception e) {
	   e.printStackTrace();
	}
	return result;
}

 private int updateChildTransferStatus(int id,int newid) {
	 int result=0;
	 try {
		 String sql="update inventory_request_temp_log set transfer_status='1',new_prodid='"+newid+"' where id="+id+"";
		 PreparedStatement ps=connection.prepareStatement(sql);
		 result =ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public int updateNotesofParent(String parentid, String notes) {

	 int result=0;
	 try {
		 String sql="update inventory_parent_transfer_log set admin_notes=? where id="+parentid+"";
		 PreparedStatement ps=connection.prepareStatement(sql);
		 ps.setString(1, notes);
		 
		 result =ps.executeUpdate();
		 
	} catch (Exception e) {

		e.printStackTrace();
	}
	 
	 return result;
 }

public double getTotalRecived(String fromdate, String todate, String location) {

	 double total=0;
	try {
		StringBuffer buffer=new StringBuffer();
		buffer.append("select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' ");
		if(!location.equals("0")){
			 buffer.append("and location="+location+" ");
		}
		buffer.append("and apm_medicine_payment.tpid=0  ");
		PreparedStatement ps=connection.prepareStatement(buffer.toString());
		ResultSet rs= ps.executeQuery();
		
		while(rs.next()){
			 
			 total= rs.getDouble(1);
		} 
		
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	return total;
  }

public double getTotalBalance(String fromdate, String todate, String location) {
	double total=0;
	try {
		StringBuffer buffer=new StringBuffer();
		buffer.append("select sum(balance) from apm_medicine_bill where date between '"+fromdate+"' and '"+todate+"' ");
		if(!location.equals("0")){
			 buffer.append("and location="+location+" ");
		}
		buffer.append("and apm_medicine_bill.tpid='0'  ");
		PreparedStatement ps=connection.prepareStatement(buffer.toString());
		ResultSet rs= ps.executeQuery();
		
		while(rs.next()){
			 
			 total= rs.getDouble(1);
		} 
		
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	return total;
}

public double getTotalRefund(String fromdate, String todate, String location) {

	double total=0;
	try {
		StringBuffer buffer=new StringBuffer();
		buffer.append("select sum(debit) from apm_medicine_bill where date between '"+fromdate+"' and '"+todate+"' ");
		if(!location.equals("0")){
			 buffer.append("and location="+location+" ");
		}
		buffer.append("and isreturn=1 and apm_medicine_bill.tpid='0'  ");
		
		PreparedStatement ps=connection.prepareStatement(buffer.toString());
		ResultSet rs= ps.executeQuery();
		
		while(rs.next()){
			 
			 total= rs.getDouble(1);
		} 
		
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	return total;
}

public ArrayList<Priscription> getAllBillHistory(String clientid, String flag,
		String location,String fromdate,String todate, int orderby) {

	ArrayList<Priscription> list=new ArrayList<Priscription>();
	try {
		StringBuffer buffer=new StringBuffer();
		buffer.append("select id,date,debit,balance,time,isreturn,deleted,refundid,discount,discount_status,edited from apm_medicine_bill where ");
	    if(flag.equals("0")){
	    	buffer.append("pclientid="+clientid+" ");
	    } else {
	    	buffer.append("clientid="+clientid+" ");
	    }
	    if(!location.equals("0")){
	    	buffer.append("and location="+location+" ");
	    }
	    if(fromdate!=null&&todate!=null){
	    	buffer.append(" and date between '"+DateTimeUtils.getCommencingDate1(fromdate)+"' and '"+DateTimeUtils.getCommencingDate1(todate)+"'");
	    }
	    if(orderby>0){
	    	 buffer.append("order by id asc");
	    }else{
	    	 buffer.append("order by id desc");
	    }
	   
	    
	    PreparedStatement ps=connection.prepareStatement(buffer.toString());
	    ResultSet rs=ps.executeQuery();
	    int i=0;
	    String firstdate=null;
	    String lastdate = null;
	    while(rs.next()){
	    	
	    	 Priscription priscription=new Priscription();
	    	 priscription.setId(rs.getInt(1));
	    	 priscription.setBillno(rs.getString(1));
	    	 priscription.setDate(DateTimeUtils.getCommencingDate1(rs.getString(2)));
	    	 priscription.setDebit(rs.getDouble(3));
	    	 priscription.setBalance(rs.getString(4));
	    	 priscription.setDateTime(priscription.getDate()+" "+rs.getString(5));
	    	 priscription.setIsreturn(rs.getInt(6));
	    	 priscription.setDeleted(rs.getInt(7));
	    	 priscription.setRefundid(rs.getInt(8));
	    	 priscription.setDiscount_status(rs.getInt(10));
	    	 priscription.setEdited(rs.getInt(11));
	    	 if(rs.getInt(6)>0){
	    		 priscription.setNotes("Sales Return");
	    	 } else {
	    		 priscription.setNotes("Sales");
	    	 }
	    	 priscription.setAbrivationid(clientid);
	    	 priscription.setTotal(rs.getDouble(3));
	    	 String paymode= getBillPaymodeNew(priscription.getId());
	    	 double totalPaid= getTotalPaymentReceived(priscription.getId());
	    	 double totalRefund = getTotalRefundAmount(priscription.getId());
	    	 priscription.setTotalrefund(Math.abs(totalRefund));
	    	 priscription.setTotalpayment(DateTimeUtils.changeFormat(totalPaid));
	    	 if(rs.getInt(6)>0){
	    		 priscription.setTotal(0.00);
	    		 priscription.setTotalrefund(Math.abs(rs.getDouble(3)));
	    	 } else {
	    		 priscription.setTotalrefund(0.00);
	    	 }
	    	 
	    	 priscription.setPaymode(paymode);
	    	 priscription.setDiscount(rs.getDouble(9));
	    	 //lokesh 17 /8
	    	 if(priscription.getDate()!=null){
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				
				String checkdate = dateFormat.format(cal.getTime());
				if(priscription.getDate().equals(checkdate)){
					priscription.setPrintflag(true);
				}else{
					priscription.setPrintflag(false);
				}
			 }
    	 	 if(orderby>0){
 		    	if(i==0){
 		    		firstdate = DateTimeUtils.getCommencingDate1(rs.getString(2));
 		    		lastdate = DateTimeUtils.getCommencingDate1(rs.getString(2));
 		    	}else{
 		    		lastdate = DateTimeUtils.getCommencingDate1(rs.getString(2));
 		    	}
	    	 }else{
	    		 if(i==0){
	    			 firstdate = DateTimeUtils.getCommencingDate1(rs.getString(2));
	    			 lastdate = DateTimeUtils.getCommencingDate1(rs.getString(2));
	 		     }else{
	 		    	firstdate = DateTimeUtils.getCommencingDate1(rs.getString(2));
	 		     }
	    	 }
	    	 priscription.setFirstdate(firstdate);
	    	 priscription.setLastdate(lastdate);
	         list.add(priscription);	 
	         i++;
	    }
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return list;
} 

 public double getTotalRefundAmount(int billno) {
	 
	    double tot=0;
	    try {
	    	String sql="select sum(debit) from apm_medicine_bill where isreturn=1 and retunbillid="+billno+" ";
	    	PreparedStatement ps=connection.prepareStatement(sql);
	    	ResultSet rs=ps.executeQuery();
	    	while(rs.next()){
	    		tot= rs.getDouble(1);
	    	}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	  	return tot;
  }
 
 
 
 
 
 public ArrayList<Product> getChildTempReqData(String parentid) {
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		ArrayList<Product> arrayList = new ArrayList<Product>();
		try {
			//String sql ="select id, parentid, old_prodid, new_prodid, qty, location,status from inventory_request_temp_log where parentid="+parentid+" and status=1";
			String sql ="select id, parentid, old_prodid, new_prodid, qty, location,status from inventory_request_temp_log where parentid="+parentid+" ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setProduct_id(rs.getString(3));
				Product product2 = inventoryProductDAO.getProduct(rs.getString(3));
				product.setProduct_name(product2.getProduct_name());
				product.setLocation(rs.getString(6));
				product.setReqqty(rs.getString(5));
				product.setStock(product2.getStock());
				product.setHsnno(product2.getHsnno());
				product.setExpiry_date(product2.getExpiry_date());
				product.setBatch_no(product2.getBatch_no());
				product.setLocation(product2.getLocation());
				Product product3 = inventoryProductDAO.getParentTransferData(parentid);
				product.setExpectedDate(product3.getExpectedDate());
				product.setReq_location(product3.getLocation());
				//  09 oct 2017 set catalogue id
				product.setCatalogueid(product2.getCatalogueid());
				arrayList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}
 
 public double getTotalHospital(String location, String fromdate, String todate) {

	 double tot=0;
	 try {
	  StringBuffer buffer=new StringBuffer();
	  buffer.append("select debit from apm_medicine_bill inner join apm_medicine_payment on apm_medicine_payment.billno=apm_medicine_bill.id   ");//
	  buffer.append("where apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' and apm_medicine_bill.isreturn=0 ");
	  if(!location.equals("0")){
	   buffer.append("and apm_medicine_bill.location='"+location+"' ");
	  }
	  buffer.append("and paymode='Hospital' group by apm_medicine_bill.id ");
	  
	  PreparedStatement ps=connection.prepareStatement(buffer.toString());
	  ResultSet rs=ps.executeQuery(); 
	  
	  while(rs.next()){
	    double res= rs.getDouble(1);
	    tot= tot+res;
	  }
	  
	 } catch (Exception e) {

	  e.printStackTrace();
	 }
	 return tot;
	 }
 public double getTotalBalance(String clientid, String flag) {

	 double tot=0;  
	 try {
	  
	  String sql="";
	  if(flag.equals("1")){
	   sql="select sum(balance) from apm_medicine_bill where pclientid='"+clientid+"' and (tpid=0 or tpid is NULL) ";
	  } else {
	   sql="select sum(balance) from apm_medicine_bill where clientid='"+clientid+"' and (tpid=0 or tpid is NULL) ";
	  }
	  PreparedStatement ps=connection.prepareStatement(sql);
	  ResultSet rs=ps.executeQuery();
	  
	  while(rs.next()){
	   
	    tot=rs.getDouble(1);
	  }
	  
	 } catch (Exception e) {

	  e.printStackTrace();
	 }
	 
	 return tot;
	  }

public int updateTpidtoPayment(int paymentid, String tpid) {

	int result=0;
	try {
		String sql="update apm_medicine_payment set tpid='"+tpid+"' where id="+paymentid+" ";
		PreparedStatement ps=connection.prepareStatement(sql);
		result =ps.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}
 

 public ArrayList<Priscription> getMedicineReturnCharges(int billid,int oldbill,double discper,int isfromdirectreturn) {

	 ArrayList<Priscription> list=new ArrayList<Priscription>();
	 try {
		    StringBuffer buffer=new StringBuffer();
		    InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			buffer.append("SELECT apm_medicine_charges.id,apm_medicine_charges.user,apm_medicine_charges.charge,apm_medicine_charges.practitionerId,apm_medicine_charges.practitionerName, ");
			buffer.append("apm_medicine_charges.clientId,apm_medicine_charges.commencing,apm_medicine_charges.paybuy,apm_medicine_charges.quantity, ");
			buffer.append("apm_medicine_charges.reqqty,apm_medicine_charges.medicineid,thirdPartyId,product_id,returnqty ");
			buffer.append("from apm_medicine_charges inner join inventory_product on inventory_product.id=apm_medicine_charges.product_id where invoiceid="+billid+" ");
			if(isfromdirectreturn==1){
				buffer.append("and ((quantity+0)-(returnqty+0))>0 ");
			}
			buffer.append("order by inventory_product.prodname asc ");
			
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			ArrayList<String> allreturnbills =getAllReturnBillsofThis(billid);
			int isinadjustment=0;
			while(rs.next()){
				 
				 int qty=0;
				
				 Priscription priscription=new Priscription();
				 int isinadjustment1 = inventoryProductDAO.checkProductInAdjustment(rs.getString(13));
				 if(isinadjustment==0){
					 isinadjustment = isinadjustment1;
				 }
				   priscription.setIsinadjustment(isinadjustment);
				  priscription.setId(rs.getInt(1));
				  priscription.setClientname(rs.getString(2));
				  priscription.setMrp(DateTimeUtils.changeStringFormat(rs.getString(3)));
				  priscription.setPrectionerid(rs.getString(4));
				  priscription.setFullname(rs.getString(5));
				  priscription.setClientId(rs.getString(6));
				  priscription.setDate(rs.getString(7));
				  priscription.setWhopay(rs.getString(8));
				  priscription.setSaleqty(rs.getInt(9));
				  priscription.setReqqty(rs.getInt(10));
				  
                  for(String str: allreturnbills){
                	    
                	   qty=qty+getReturnQtyForBillIfExist(Integer.parseInt(str), rs.getInt(13));
                  }
				  
                  int nowafterreturnqty= rs.getInt(9) - qty;
                  if(nowafterreturnqty<0){
                	  nowafterreturnqty=0;
                  }
				  
                  priscription.setSaleqty(nowafterreturnqty);
                  
				  double total=Double.parseDouble(priscription.getMrp())*priscription.getSaleqty();
				  String str=DateTimeUtils.changeFormat(total);
				  priscription.setTotal(Double.parseDouble(str));
				  priscription.setMdicinenameid(rs.getString(11));
				  priscription.setTpid(rs.getString(12));
				  priscription.setProduct_id(rs.getString(13));
				  
				  Product product=inventoryProductDAO.getProduct(priscription.getProduct_id());
				  String shedule = getCatalogueShedule(product.getCatalogueid());
				  if(shedule.equals("Narcotics")){
					  priscription.setColor("#e05d6f");
					}else if(shedule.equals("H1")){
						priscription.setColor("#e69522");
					}else{
						priscription.setColor("");
					}
				  priscription.setMdicinenametxt(product.getProduct_name());
				  priscription.setBillno(String.valueOf(billid));
				  priscription.setBatch_no(product.getBatch_no());
				  priscription.setHsnno(product.getHsnno());
				  priscription.setPurchase_price(product.getPurchase_price());
				  priscription.setSale_price(product.getSale_price());
				  priscription.setMfg(product.getMfg());
				  priscription.setPkg("6");
				  priscription.setShelf(product.getShelf());
				  priscription.setVat(product.getVat());
				  priscription.setAvailable(product.getStock());
				  int pack=10;
				  if(product.getPack()!=null){
					  if(product.getPack().equals("")){
						  pack=10;
					  } else {
						  pack =Integer.parseInt(product.getPack());
					  }
				  } else {
					  pack=10;  
				  }
				  
				  priscription.setPack(pack);
				  double vat= Double.parseDouble(product.getVat());
				  double dividevat=100+vat;
				  double gst= total*vat /dividevat;
				  double av=total-gst;
				  double half= gst/2;
				  priscription.setCgst(DateTimeUtils.changeFormat(half));
				  priscription.setSgst(DateTimeUtils.changeFormat(half));
				  priscription.setAv(DateTimeUtils.changeFormat(av));
				  double stock=0.0;
				  if(product.getStock()!="0"){
					  
					  if(!product.getStock().equals("")){
						   
						  //stock=Integer.parseInt(product.getStock());
						  stock=Double.parseDouble(product.getStock());
					  }
						
				  }
				  if(stock<=10){
					  priscription.setSstatus(1);
				  }
				  
				  if(product.getExpiry_date()!=null){
					  String temp=product.getExpiry_date();
					  String expiry=DateTimeUtils.converToMMMYYYYforExp(temp);
					  priscription.setExpiry_date(expiry);
				  }else {
					priscription.setExpiry_date("N/A");
				  }
				  
				  priscription.setDiscper(discper);
				  int returnedqty = rs.getInt(9) - rs.getInt(14);
				  priscription.setReturnedqty(""+returnedqty);
				  priscription.setShelf(product.getShelf());
				  priscription.setSecondary_shelf(product.getSecondary_shelf());
				  list.add(priscription);
				
				
			}
		 
	  } catch (Exception e) {

		 e.printStackTrace();
	 }
	 
	 return list;
 }

  
  public String getCatalogueShedule(String catalogueid) {
	  String shedule="";
		try {
			String sql="select shedule from inventory_catalogue where id="+catalogueid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				shedule = rs.getString(1);
				if(shedule==null){
					shedule="";
				}
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}  
		  
		return shedule;
}

private ArrayList<String> getAllReturnBillsofThis(int billid) {

	  ArrayList<String> list=new ArrayList<String>();
	try {
		String sql="select id from apm_medicine_bill where retunbillid="+billid+" ";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			 
				list.add(rs.getString(1));
		}
		
	} catch (Exception e) {

		e.printStackTrace();
	}  
	  
	return list;
}

public int getReturnQtyForBillIfExist(int billid,int productid) {
	  
	   int result=0; 
	   try {
		   
		   String sql="select sum(quantity) from apm_medicine_charges where invoiceid="+billid+" and product_id="+productid+" ";
		   PreparedStatement ps=connection.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   
		   while(rs.next()){
			    result =rs.getInt(1);
		   }
		
	   } catch (Exception e) {

		   e.printStackTrace();
	   }
	  
	  return result;
  }

public double getHospitalPaymentAmt(String fromdate, String todate,
		String clientid, int res) {
	double sum=0;
	try {
		
		StringBuffer sql= new StringBuffer();
		sql.append("select sum(payment) from apm_medicine_payment ");
		if(res==1){
			sql.append("where pclientid="+clientid+" ");
		} else {
			sql.append("where clientid="+clientid+" ");
		}
		sql.append("and paymode='Hospital' and date between '"+fromdate+"' and '"+todate+"' ");
			
		PreparedStatement ps=connection.prepareStatement(sql.toString());
		ResultSet rs =ps.executeQuery();
		
		while(rs.next()){
			 
			sum =rs.getDouble(1);
		}
		
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	return sum;
}

public double getTotalChequeCollectedByUser(String userid, String fromdate,
		String todate, String location) {
	double res=0;
	try {
		String sql="";
		if(!location.equals("0")){
			sql="select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='Cheque' and userid='"+userid+"' and location="+location+" ";
		} else {
			sql="select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='Cheque' and userid='"+userid+"' ";	
		}
		
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			
			   res=rs.getDouble(1);
		}
		 
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return res;
}

public double getTotalNeftCollectedByUser(String userid, String fromdate,
		String todate, String location) {
	double res=0;
	try {
		String sql="";
		if(!location.equals("0")){
			sql="select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='NEFT' and userid='"+userid+"' and location="+location+" ";
		} else {
			sql="select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='NEFT' and userid='"+userid+"' ";	
		}
		
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			
			   res=rs.getDouble(1);
		}
		 
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return res;
}
public double getTotalHospitalCollectedByUser(String userid, String fromdate,
		String todate, String location) {
	double res=0;
	try {
		String sql="";
		if(!location.equals("0")){
			sql="select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='Hospital' and userid='"+userid+"' and location="+location+" ";
		} else {
			sql="select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='Hospital' and userid='"+userid+"' ";	
		}
		
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			
			   res=rs.getDouble(1);
		}
		 
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return res;
}
public ArrayList<Product> getAllRequestedMedFrTransfer(String fromdate,
		String todate,String loc) {
	ArrayList<Product> arrayList = new ArrayList<Product>();
	InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
	todate = todate + " 23:59:59";
	try {
		String sql = "";
		if(loc.equals("0")){
			sql = "select id, product_id, name, quantity, date, status,userid from apm_medicine_request where status=0 and date between '"+fromdate+"' and '"+todate+"' order by id desc";
		} else {
			sql = "select id, product_id, name, quantity, date, status,userid from apm_medicine_request where status=0 and location='"+loc+"' and date between '"+fromdate+"' and '"+todate+"' order by id desc";
		}
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs  = preparedStatement.executeQuery();
		while (rs.next()) {
			Product product = new Product();
			Product product2 = inventoryProductDAO.getProduct(rs.getString(2));
			product.setId(rs.getInt(1));
			product.setProduct_id(rs.getString(2));
			product.setReqqty(rs.getString(4));
			String dateandtime = rs.getString(5);
			String[] data = dateandtime.split(" ");
			String date1 = data[0];
			String simpledate = DateTimeUtils.getCommencingDate1(data[0]);
			String date = simpledate +" "+ data[1];
			product.setRequest_date(date);
			product.setStock(product2.getStock());
			product.setStatus(rs.getString(6));
			product.setProduct_name(product2.getProduct_name());
			product.setGenericname(product2.getGenericname());
			product.setLocation(inventoryProductDAO.pharmacyLocationNameByID(product2.getLocation()));
			String hsnno = product2.getHsnno();
			if(hsnno==null){
				hsnno="";
			}
			product.setHsnno(hsnno);
			product.setBatch_no(product2.getBatch_no());
			product.setUserid(rs.getString(7));
			
			if(product2.getPack()==null){
				product2.setPack("1");
			}
			if(product2.getPack().equals("")){
				product2.setPack("1");
			}
			double amt = Double.parseDouble(product2.getPurchase_price()) / Integer.parseInt(product2.getPack()) ;
			double mrpamt = Double.parseDouble(product2.getMrp()) / Integer.parseInt(product2.getPack()) ;
			double unit1 = Math.round(amt * 100.0) / 100.0;
			double unit2 = Math.round(mrpamt * 100.0) / 100.0;
			product.setSale_price(""+unit1);
			product.setMrp(""+unit2);
			
			arrayList.add(product);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	//arrayList.addAll(getZeroStock());
	
	return arrayList;
}


public ArrayList<Product> getZeroStock() {
	
	ArrayList<Product> list= new ArrayList<Product>();
	InventoryProductDAO productDAO= new JDBCInventoryProductDAO(connection);
	try {
		
		String sql="select id from inventory_product where mrp>0 and stock=0 order by prodname ";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			
			 Product product= productDAO.getProduct(rs.getString(1));
			 product.setStatus("0");
			 list.add(product);
		}
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	return list;
}

public int saveNewMedicineRequestChild(Product product, String req_qty,
		   int result,Product product2,int avail_qty,String comment) {
		  int result1 =0;
		  try {
		   String sql = "insert into inventory_transfer_log (parentid, old_prodid, new_prodid, qty,userid,location,prod_name,avail_qty,comment,catlogueid) values (?,?,?,?,?,?,?,?,?,?)";
		   PreparedStatement preparedStatement = connection.prepareStatement(sql);
		   preparedStatement.setString(1, ""+result);
		   preparedStatement.setString(2, product.getProduct_id());
		   preparedStatement.setString(3, "0");
		   preparedStatement.setString(4, req_qty);
		   preparedStatement.setString(5, product.getUserid());
		   preparedStatement.setString(6, product.getLocation());
		   preparedStatement.setString(7, product2.getProduct_name());
		   preparedStatement.setString(8, ""+avail_qty);
		   preparedStatement.setString(9, comment);
		   preparedStatement.setString(10, product.getCatalogueid());
		   result = preparedStatement.executeUpdate();
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  return result1;
		 }

public Priscription setCheck_stock_availableAccess(String userid, String status,String datetime,String currentuser) {
	Priscription priscription = new Priscription();
	//@k@sh
	try {
		try{
			String query = "update apm_pharmacy_user set check_stock_available='"+status+"',check_stock_availableDT='"+datetime+"',check_stock_availableBY='"+currentuser+"' where id="+userid+"";
			PreparedStatement preparedStatement1 = connection.prepareStatement(query);
			int result = preparedStatement1.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "select id, check_stock_available from apm_pharmacy_user where id="+userid+""; 
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			priscription.setId(rs.getInt(1));
			priscription.setCheck_stock_available(rs.getString(2));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return priscription;
}

public Priscription setDirect_TransferAccess(String userid, String status,String datetime,String currentuser) {
	Priscription priscription = new Priscription();
	//@k@sh
	try {
		try{
			String query = "update apm_pharmacy_user set direct_transfer='"+status+"',direct_transferDT='"+datetime+"',direct_transferBy='"+currentuser+"' where id="+userid+"";
			PreparedStatement preparedStatement1 = connection.prepareStatement(query);
			int result = preparedStatement1.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "select id, direct_transfer from apm_pharmacy_user where id="+userid+""; 
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			priscription.setId(rs.getInt(1));
			priscription.setDirect_transfer(rs.getString(2));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return priscription;
}

public Priscription setInventory_ReportAccess(String userid, String status,String datetime,String currentuser) {
	 Priscription priscription = new Priscription();
	 //ruchi
	 try {
	  try{
	   String query = "update apm_pharmacy_user set inventory_report='"+status+"',inventory_reportDT='"+datetime+"',inventory_reportBY='"+currentuser+"' where id="+userid+"";
	   PreparedStatement preparedStatement1 = connection.prepareStatement(query);
	   int result = preparedStatement1.executeUpdate();
	  }catch (Exception e) {
	   e.printStackTrace();
	  }
	  String sql = "select id, inventory_report from apm_pharmacy_user where id="+userid+""; 
	  PreparedStatement preparedStatement = connection.prepareStatement(sql);
	  ResultSet rs = preparedStatement.executeQuery();
	  while(rs.next()){
	   priscription.setId(rs.getInt(1));
	   priscription.setInventory_transfer(rs.getString(2));
	  }
	 } catch (Exception e) {
	  e.printStackTrace();
	 }
	 return priscription;
	}


public int updateExPatient(int pharmacyclientid, Client client) {
	//@k@sh
	int result=0;
	try {
		String sql ="update apm_pharmacy_client set fullname=?, address=?, reference=?,mobile=? where id=? ";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, client.getFirstName());
		preparedStatement.setString(2, client.getAddress());
		preparedStatement.setString(3, client.getReference());
		preparedStatement.setString(4, client.getMobNo());
		preparedStatement.setInt(5, pharmacyclientid);
		result = preparedStatement.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public ArrayList<Client> getPharmacyClientList() {
	PreparedStatement preparedStatement = null;
	ArrayList<Client>list = new ArrayList<Client>();
	String sql = "SELECT id,fullname, date,mobile,address,reference,abrivationid FROM apm_pharmacy_client order by id desc limit 0,10 ";
	
	try{
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			Client client = new Client();
			client.setId(rs.getInt(1));
			client.setOldclientId("0");
			client.setClientName(rs.getString(2));
			client.setCasualtyid("0");
			client.setWhopay("Client");
			client.setLastModified(rs.getString(3));
			client.setMobNo(rs.getString(4));
			client.setAddress(rs.getString(5));
			client.setReference(rs.getString(6));
			client.setEmail("");
			client.setPostCode("");
			client.setDob("");
			client.setIspharmacy("1");
			client.setAbrivationid(rs.getString(7));
			list.add(client);
		}
		
	}catch (Exception e) {
		// TODO: handle exception
	}
	return list;
}

public ArrayList<Client> getPharmacySearchClientList(String searchClient) {
	PreparedStatement preparedStatement = null;
	ArrayList<Client>list = new ArrayList<Client>();
	String sql = "SELECT id,fullname, date,mobile,address,reference,abrivationid FROM apm_pharmacy_client where fullname like('%"+searchClient+"%') or mobile like ('"+searchClient+"%') or id like ('%"+searchClient+"%') or abrivationid='"+searchClient+"'  ";
	
	try{
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			Client client = new Client();
			client.setId(rs.getInt(1));
			client.setOldclientId("0");
			client.setClientName(rs.getString(2));
			client.setCasualtyid("0");
			client.setWhopay("Client");
			client.setLastModified(rs.getString(3));
			client.setMobNo(rs.getString(4));
			client.setAddress(rs.getString(5));
			client.setReference(rs.getString(6));
			client.setEmail("");
			client.setPostCode("");
			client.setDob("");
			client.setIspharmacy("1");
			client.setAbrivationid(rs.getString(7));
			
			list.add(client);
			
		}
		
	}catch (Exception e) {
		// TODO: handle exception
	}
	return list;
}


public int updatePharmaClient(String val, String column, String clientid) {
	PreparedStatement preparedStatement = null;
	int result = 0;
	String sql = "update apm_pharmacy_client set "+column+" = '"+val+"' where id="+clientid+" ";
	
	try{
		preparedStatement = connection.prepareStatement(sql);
		result = preparedStatement.executeUpdate();
		
	}catch (Exception e) {
		// TODO: handle exception
	}
	
	return result;
}


public int saveNewPharmaPatient(String date, String adfullname, String admobile, String adaddress, String addoctor, String fullname, String abrivationid,String gstin, String dlno) {
	PreparedStatement preparedStatement = null;
	int result = 0;
	String sql = "insert into apm_pharmacy_client(fullname,date,mobile,reference,address,phar_drid,abrivationid,gstin,dlno) values(?,?,?,?,?,?,?,?,?)";
	
	try{
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, adfullname);
		preparedStatement.setString(2, date);
		preparedStatement.setString(3, admobile);
		preparedStatement.setString(4, fullname);
		preparedStatement.setString(5, adaddress);
		preparedStatement.setString(6, addoctor);
		preparedStatement.setString(7, abrivationid);
		preparedStatement.setString(8, gstin);
		preparedStatement.setString(9, dlno);
		result = preparedStatement.executeUpdate();
				
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public int updateClientData(Priscription priscription) {

	int res=0;
	try {
		String sql="update apm_pharmacy_client set fullname=?,address=?,reference=?,mobile=? where id="+priscription.getPclientid()+"";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1, priscription.getFullname());
		ps.setString(2, priscription.getLocation());
		ps.setString(3, priscription.getPractitionername());
		ps.setString(4, priscription.getMobile());
		
		res =ps.executeUpdate();
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return res;
}

public int updatePaymentDateTime(String dateTime, int resultid) {

	int result=0;
	try {
		
		String sql="update apm_medicine_payment set datetime='"+dateTime+"' where id="+resultid+" ";
		PreparedStatement ps=connection.prepareStatement(sql);
		result =ps.executeUpdate();
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	return result;
}

public int getAllPharmacySaleCount(String searchtext, String fromdate, String todate, String location,String isreturn,String paymode) {

	int result=0;
	try {
		
		StringBuffer buffer= new StringBuffer();
		buffer.append("select count(*) from apm_medicine_bill ");
		buffer.append("left join apm_medicine_payment on apm_medicine_bill.id=apm_medicine_payment.billno ");
		buffer.append("where apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"'  ");
		if(!location.equals("0")){
			buffer.append("and apm_medicine_bill.location="+location+" ");
		}
		if(isreturn.equals("1")){
			buffer.append("and apm_medicine_bill.isreturn=1 ");
		}else if(isreturn.equals("2")){
			buffer.append("and apm_medicine_bill.isreturn=0 ");
		}
		if(!paymode.equals("0")){
			buffer.append("and apm_medicine_payment.paymode='"+paymode+"' ");
		}
		buffer.append("and (apm_medicine_bill.tpid='0' || apm_medicine_bill.tpid is NULL) ");
		buffer.append("group by apm_medicine_bill.id desc ");
		PreparedStatement ps=connection.prepareStatement(buffer.toString());
		ResultSet rs =ps.executeQuery();
		
		while(rs.next()){
			 result =result+1;
		}
		
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return result;
}

public int saveParentBillClearPayment(Priscription priscription) {

	int res=0;
	try {
		//  balance_clear table
		String sql="insert into apm_pharmacy_parent_clear_bill (clientid, pclientid, datetime, notes, paymode, paynote,userid,location,discount) values (?,?,?,?,?,?,?,?,?) ";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1, priscription.getClientId());
		ps.setString(2, priscription.getPclientid());
        ps.setString(3, priscription.getDateTime());
        ps.setString(4, priscription.getNotes());
        ps.setString(5, priscription.getPaymode());
        ps.setString(6, priscription.getPaynote());
        ps.setString(7, priscription.getUserid());
        ps.setString(8, priscription.getLocation());
        ps.setString(9, priscription.getDisscount());
		res =ps.executeUpdate();
		
		if(res>0){
			 ResultSet rs=ps.getGeneratedKeys();
			 while(rs.next()){
				 res =rs.getInt(1);
			 }
		}
        
	} catch (Exception e) {

		e.printStackTrace();
	}
	return res;
}

public double getBillBalance(String billno) {

	double res=0;
	try {
		
		String sql="select balance from apm_medicine_bill where id="+billno+"";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()){
			res=rs.getDouble(1);
		}
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	return res;
}

public int saveChildBillClear(int parentid, Priscription priscription) {

	int res= 0;
	try {
		
		String sql="insert into apm_pharmacy_clear_bill (parentid, billid, billbalance, payment, paymode, location, userid) "
				+ " values (?, ?, ?, ?, ?, ?, ?) ";
		
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setInt(1, parentid);
		ps.setString(2, priscription.getBillno());
		ps.setString(3, priscription.getBalance());
		ps.setString(4, priscription.getPayment());
		ps.setString(5, priscription.getPaymode());
		ps.setString(6, priscription.getLocation());
		ps.setString(7, priscription.getUserid());
		res =ps.executeUpdate();
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return res;
}

public Priscription getClearBillReceptInfo(int parentid) {

	Priscription priscription= new Priscription();
		try {
			
			String sql="select id, clientid, pclientid, datetime, notes, paymode, paynote,userid,location,discount from apm_pharmacy_parent_clear_bill where id="+parentid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				 
				   priscription.setId(rs.getInt(1));
				   priscription.setClientId(rs.getString(2));
				   priscription.setPclientid(rs.getString(3));
				   priscription.setDateTime(DateTimeUtils.getDBDate(rs.getString(4)));
				   priscription.setNotes(rs.getString(5));
				   priscription.setPaymode(rs.getString(6));
				   priscription.setPaynote(rs.getString(7));
				   priscription.setUserid(rs.getString(8));
				   priscription.setLocation(rs.getString(9));
				   priscription.setDiscount(rs.getDouble(10));
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	
	return priscription;
}

public ArrayList<Priscription> getClearBillList(int parentid) {

	ArrayList<Priscription> list=new ArrayList<Priscription>();
	try {
		
		/*String sql="select id, billid, billbalance, payment, paymode, location, userid from apm_pharmacy_clear_bill where parentid="+parentid+" ";*/
		StringBuffer buffer = new StringBuffer();
		buffer.append("select apm_pharmacy_clear_bill.id, apm_pharmacy_clear_bill.billid, apm_pharmacy_clear_bill.billbalance, ");
		buffer.append("apm_pharmacy_clear_bill.payment, apm_pharmacy_clear_bill.paymode, apm_pharmacy_clear_bill.location, ");
		buffer.append("apm_pharmacy_clear_bill.userid,apm_pharmacy_parent_clear_bill.discount ");
		buffer.append("from apm_pharmacy_clear_bill ");
		buffer.append("inner join apm_pharmacy_parent_clear_bill on apm_pharmacy_parent_clear_bill.id = apm_pharmacy_clear_bill.parentid ");
		buffer.append("where parentid='"+parentid+"' ");
		PreparedStatement ps=connection.prepareStatement(buffer.toString());
		ResultSet rs =ps.executeQuery();
		while(rs.next()){
			  Priscription  priscription =new Priscription();
			  priscription.setId(rs.getInt(1));
			  priscription.setBillno(rs.getString(2));
			  CompleteAppointment appointment= getBillDetails(rs.getInt(2));
			  priscription.setDateTime(DateTimeUtils.getCommencingDate1(appointment.getInvoiceDate())+" "+appointment.getInvoiceTime());
			  String billpaymode=getBillPaymode(rs.getInt(2));  
			  priscription.setBalance(rs.getString(3));
			  priscription.setPayment(DateTimeUtils.changeStringFormat(rs.getString(4)));
			  priscription.setPaymode(billpaymode);
			  priscription.setLocation(rs.getString(6));
			  priscription.setUserid(rs.getString(7));
			  double total = rs.getDouble(4) -  rs.getDouble(8);
			  priscription.setDiscount(rs.getDouble(8));
			  priscription.setTotal(total);
			  list.add(priscription);
		}
				
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return list;
}

 public Priscription getClearBalBillInfo(String billno) {
	 
	 Priscription priscription =new Priscription();
	 try {
		 
		 String sql="select id, parentid, billid, billbalance, payment, paymode, location, userid from apm_pharmacy_clear_bill where billid="+billno+"  ";
		 PreparedStatement ps=connection.prepareStatement(sql);
		 ResultSet rs =ps.executeQuery();
		 while(rs.next()){
			 
			   priscription.setId(rs.getInt(1));
			   priscription.setParentid(rs.getString(2));
			   priscription.setBillno(rs.getString(3));
			   priscription.setBalance(rs.getString(4));
			   priscription.setPayment(rs.getString(5));
			   priscription.setPaymode(rs.getString(6));
			   priscription.setLocation(rs.getString(7));
			   priscription.setUserid(rs.getString(8));
			 
		 }
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	 
	 return priscription;
 }

public int updateBillCreditReturn(int billno, String refund) {

	int res= 0;
	try {
		String sql="update apm_medicine_bill set credit='"+refund+"' where id="+billno+"";
		PreparedStatement ps=connection.prepareStatement(sql);
		
		res =ps.executeUpdate();
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	return res;
}

public double getTotalCredit(String location, String fromdate, String todate) {
	
	double temp=0.0;
	try {
		
		StringBuffer buffer= new StringBuffer();
		buffer.append("select credit from apm_medicine_bill ");
		buffer.append("inner join apm_medicine_payment on apm_medicine_bill.id=apm_medicine_payment.billno ");
		buffer.append("where apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' ");
		buffer.append("and (apm_medicine_bill.tpid=0 or apm_medicine_bill.tpid is NULL) and apm_medicine_bill.isreturn=0 and credit!='0' and credit!='NaN' ");
		if(!location.equals("0")){
			buffer.append("and apm_medicine_bill.location="+location+" ");
		}
	    buffer.append("and apm_medicine_payment.paymode!='Hospital' group by apm_medicine_bill.id ");
		
		PreparedStatement ps=connection.prepareStatement(buffer.toString());
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			 
			double res=rs.getDouble(1);
			temp =temp+res;
//			System.out.println(res+"  -- "+ temp);
		}
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return temp;
}

public ArrayList<Priscription> getAllPharmacySaleList(Pagination pagination, String searchtext, String fromdate,
		String todate, String location,String isreturn,String paymode, int dontshowreqbill, LoginInfo loginInfo,String payee, String tpid) {

	ArrayList<Priscription> list= new ArrayList<Priscription>();
	PrescriptionDAO prescriptionDAO =new JDBCPrescriptionDAO(connection);
	IpdDAO ipdDAO= new JDBCIpdDAO(connection);
	UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
	ClientDAO clientDAO= new JDBCClientDAO(connection);
	BedDao bedDao =new JDBCBedDao(connection);
	try {
		String date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
		if(searchtext!=null){
			list =new ArrayList<Priscription>();
			ArrayList<Priscription> list1 = prescriptionDAO.getExternalClientData(pagination, fromdate, todate, location, searchtext,paymode,loginInfo,isreturn);
			ArrayList<Priscription> list2 = prescriptionDAO.getInternalClientData(pagination, fromdate, todate, location, searchtext,paymode,loginInfo,isreturn);
			ArrayList<Priscription> list3 = prescriptionDAO.getEstimateBills(pagination, fromdate, todate, location, searchtext,loginInfo);
			list.addAll(list1);
			list.addAll(list2);
			list.addAll(list3);
		} else {
		
		if(dontshowreqbill>0){
			/*ArrayList<Priscription> inLinePatient= prescriptionDAO.getAllPharmacyList(pagination, searchtext, fromdate, todate, "0", "0", location);
			list.addAll(inLinePatient);*/
		}
		
		StringBuffer buffer= new StringBuffer();
		buffer.append("select apm_medicine_bill.id,apm_medicine_bill.date,apm_medicine_bill.debit,apm_medicine_bill.clientid, ");
		buffer.append("apm_medicine_bill.pclientid,isreturn,apm_medicine_bill.refundamt,deleted,balance,apm_medicine_payment.payment,");
		buffer.append("apm_medicine_bill.time,retunbillid,apm_medicine_bill.userid,apm_medicine_payment.paymode,");
		buffer.append("apm_medicine_bill.refundid,apm_medicine_bill.discount,apm_medicine_bill.phar_ipdid,");
		buffer.append("apm_medicine_bill.oldparentid,dummybillno,discount_status,edited,third_party_name_id,actual_discount ");
		buffer.append("from apm_medicine_bill left join apm_medicine_payment on apm_medicine_bill.id=apm_medicine_payment.billno  ");
		buffer.append("where apm_medicine_bill.date between '"+fromdate+"' and '"+todate+" 23:59:59' ");
		if(!location.equals("0")){
			buffer.append("and apm_medicine_bill.location='"+location+"' ");
		}
		if(isreturn.equals("1")){
			buffer.append("and apm_medicine_bill.isreturn=1 ");
		} else if(isreturn.equals("2")) {
			buffer.append("and apm_medicine_bill.isreturn=0 ");
		}
		
		if(!paymode.equals("0")){
			buffer.append("and apm_medicine_payment.paymode='"+paymode+"' ");
		}
		if(payee.equals("2")) {
			buffer.append("and apm_medicine_bill.third_party_name_id=0 ");
		}
		if(!tpid.equals("0")) {
			buffer.append("and apm_medicine_bill.third_party_name_id='"+tpid+"' ");
		}
		if(payee.equals("3")) {
			buffer.append("and apm_medicine_bill.actual_discount=100 ");
		}
		
		buffer.append("and (apm_medicine_bill.tpid='0' || apm_medicine_bill.tpid is NULL) ");
		buffer.append("group by apm_medicine_bill.id desc ");
		
		String sql =buffer.toString();
		if(pagination!=null){
			sql= pagination.getSQLQuery(sql);
		}
		
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		double totalReceived=0;
		double totalRefund=0;
		double totalBalance=0;
		
		while(rs.next()){
			
			Priscription priscription=new Priscription();
			priscription.setId(rs.getInt(1));
			priscription.setBillno(rs.getString(1));
			priscription.setDate(DateTimeUtils.getCommencingDate1(rs.getString(2)));
			priscription.setDebit(rs.getDouble(3));
			priscription.setTotal(rs.getDouble(3));
			priscription.setClientId(rs.getString(4));
			priscription.setPclientid(rs.getString(5));
			priscription.setIsreturn(rs.getInt(6));
			priscription.setReturnbill(rs.getInt(6));
			double refundtot= rs.getDouble(7);
			priscription.setDeleted(rs.getInt(8));
			priscription.setBalance(rs.getString(9));
			double payamt= getTotalPaymentReceived(rs.getInt(1));
			priscription.setPayment(DateTimeUtils.changeFormat(payamt));
			String dateTime = priscription.getDate()+" "+rs.getString(11);
			priscription.setReturnbillid(rs.getInt(12));
			priscription.setUserid(rs.getString(13));
			priscription.setPaymode(rs.getString(14));
			if(paymode.equals("Credit")){
				priscription.setNewpaymentmode(rs.getString(14));
			}else{
				priscription.setNewpaymentmode(getBillPaymodeNew(rs.getInt(1)));
			}
			
			if(rs.getInt(15)>0){
				priscription.setRefundid(rs.getInt(15));
			} else {
				priscription.setRefundid(priscription.getId());
			}
		    priscription.setLastmodified(dateTime);
		    priscription.setDelivered(1);
		    
		    
			if(priscription.getIsreturn()>0 && rs.getDouble(3)==0){
				priscription.setTotal(refundtot);
			}
			
			//  14 July 2018
			int isdeletable=0;
			if(rs.getInt(6)>0){
				totalRefund = totalRefund+rs.getDouble(3);
			}else{
				isdeletable = checkbillalreadydeletedornot(rs.getInt(1));
			}
			priscription.setIsdeletable(isdeletable);
			totalReceived = totalReceived +payamt;
			totalBalance = totalBalance+ rs.getDouble(9);
			priscription.setTotalBalance(totalBalance);
			priscription.setTotalReceived(totalReceived);
			priscription.setTotalrefund(totalRefund);
			
			if(rs.getInt(5)>0){
				  // pharmacy patient
				  Priscription masterClient = getPharmacyPatient(priscription.getPclientid());
				  priscription.setFullname(masterClient.getFullname());
				  priscription.setMobile(masterClient.getMobile());
				  priscription.setPractitionername(masterClient.getPractitionername());
				  priscription.setAddress(masterClient.getAddress());
				  priscription.setAgeandgender(masterClient.getAgeandgender());
				  priscription.setOutp(1);
				  if(!DateTimeUtils.numberCheck(masterClient.getAbrivationid()).equals("0")){
					  priscription.setAbrivationid(masterClient.getAbrivationid());
				  }
			} else {
				   //ipd/opd patient
				   
				   Client client =clientDAO.getClientDetails(priscription.getClientId()); 
				   String fullname =client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();  
				   priscription.setFullname(fullname);
				   String agegender= DateTimeUtils.getAge(client.getDob()) +" "+client.getGender();
				   priscription.setAgeandgender(agegender);
				   int newipdid = rs.getInt(17);
				   //String ipdid = getIpdIdFromClientID(Integer.parseInt(priscription.getClientId()));
				   
				   String ipdid = ""+newipdid;
					String pract_name ="";
					if(ipdid.equals("0")){
						pract_name = getappointmentinfo(Integer.parseInt(priscription.getClientId()));
						priscription.setReqfromlocation("1");
					}else{
						Bed  bed = bedDao.getEditIpdData(ipdid);
						pract_name = profileDAO.getFullName(bed.getPractitionerid());
						String wardname = ipdDAO.getIpdWardName(bed.getWardid());
						//@ruchi to check casualty and icu patient to blink
						String urgentward = checkICUorCasulty(bed.getWardid());
						if (!urgentward.equals("")) {
							priscription.setUrgentward("1");
						}
						//end
						String bedname= ipdDAO.getIpdBedName(bed.getBedid());
						priscription.setWardname(wardname);
						priscription.setBedname(bedname);
						priscription.setReqfromlocation("0");
					}
					if(rs.getInt(18)>0){
						   String reqfromlocation = getRequestFromPriscriptionBill(rs.getString(1));
						   priscription.setReqfromlocation(reqfromlocation);
					}
					priscription.setIpdid(ipdid);
					priscription.setPractitionername(pract_name);
					priscription.setAddress(client.getAddress());
					priscription.setAbrivationid(client.getAbrivationid());
				    priscription.setMobile(client.getMobNo());
				    priscription.setOutp(0);
				    
			}
			priscription.setDummybillno(rs.getInt(19));
			//Pharmacy 30 july 2018
			priscription.setDiscount(rs.getDouble(16));
			
			//lokesh 17-aug
			if(priscription.getDate()!=null){
				
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
				
					String checkdate = dateFormat.format(cal.getTime());
					if(priscription.getDate().equals(checkdate)||loginInfo.isPharm_print_backdate()){
						priscription.setPrintflag(true);
					}else{
						priscription.setPrintflag(false);
				}
			}
			priscription.setDiscount_status(rs.getInt(20));
			priscription.setEdited(rs.getInt(21));
			String third_party_name="";
			if(rs.getInt(23)==100) {
				third_party_name="self free";
				priscription.setThird_party_name(third_party_name);
			}else if(rs.getInt(22)==0) {
				third_party_name="self";
				priscription.setThird_party_name(third_party_name);
			}else {
				third_party_name=getThirdPartyName(rs.getInt(22));
				priscription.setThird_party_name(third_party_name);
			}
			String subcategoryname=getSubcategoryname(location);
			priscription.setSubcategoryname(subcategoryname);
			//delete, balance
			if(rs.getInt(8)==0 && rs.getDouble(9)>0){
				//discount
				if(rs.getDouble(16)>0){
					
				}else if(rs.getInt(20)==0){
					//request status 1
					int res = checkProductReturnAgainstBill(rs.getString(1)); 
					if(res==2){
						priscription.setDiscount_status(3);
					}
				}
			}
			int sameeditdate=0;
			if(date.equals(rs.getString(2))){
				sameeditdate=1;
			}
			priscription.setSameeditdate(sameeditdate);
			list.add(priscription);
			
		}
		
		ArrayList<Priscription> list3 = prescriptionDAO.getEstimateBills(pagination, fromdate, todate, location, searchtext,loginInfo);
		list.addAll(list3);
	}
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return list;
}

public String getThirdPartyName(int third_party_name_id) {
	String name ="";
	try {
		String sql ="select company_name from apm_third_party_details where id='"+third_party_name_id+"' ";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			name=rs.getString(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return name;
	
}

private String getSubcategoryname(String location) {
	String name ="";
	try {
		String sql ="select name from apm_condition where id='"+location+"' ";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			name=rs.getString(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return name;
	
}

public int checkbillalreadydeletedornot(int int1) {
	int res =0;
	try {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select apm_medicine_charges.id from apm_medicine_charges ");
		buffer.append("inner join apm_medicine_bill on apm_medicine_bill.id = apm_medicine_charges.invoiceid ");
		buffer.append("where returnbillno='"+int1+"' and deleted=0 limit 1 ");
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			res = 1;
		}
		if(res==0){
			String sql ="select id from apm_medicine_bill where retunbillid='"+int1+"' and deleted=0 limit 1 ";
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement2.executeQuery();
			while (resultSet.next()) {
				res = 1;
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public String getRequestFromPriscriptionBill(String billno) {
	String name ="1";
	try {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select location_s from apm_medicine_bill ");
		buffer.append("inner join apm_client_parent_priscription on apm_medicine_bill.oldparentid = apm_client_parent_priscription.id ");
		buffer.append("where apm_medicine_bill.id = '"+billno+"' ");
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			name=""+rs.getInt(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return name;
}

private String checkICUorCasulty(String wardid) {
	String name ="";
	try {
		String sql ="select id from apm_ipd_ward where id='"+wardid+"' and (wardname like('%ICU%') or wardname like('%CASUALTY%'))";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			name=rs.getString(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return name;
}

public Priscription setReturn_StockAccess(String userid, String status,String datetime,String currentuser) {
	Priscription priscription = new Priscription();
	//@k@sh//ruchi
	try {
		try{
			String query = "update apm_pharmacy_user set return_stock='"+status+"',return_stockDT='"+datetime+"',return_stockBY='"+currentuser+"' where id="+userid+"";
			PreparedStatement preparedStatement1 = connection.prepareStatement(query);
			int result = preparedStatement1.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "select id, return_stock from apm_pharmacy_user where id="+userid+""; 
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			priscription.setId(rs.getInt(1));
			priscription.setReturn_stock(rs.getString(2));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return priscription;
}

public ArrayList<Product> getAccessList() {

	ArrayList<Product> list=new ArrayList<Product>();
	try {
		
		/*StringBuilder sql=new StringBuilder();
		sql.append("select id,firstname,lastname,phone,userid,location, sale_bill,sale_billDT,sale_billBY, discount,discountDT,discountBY, ledger,ledgerDT,ledgerBY,");
		sql.append("account,accountDT,accountBY, purchase_order,purchase_orderDT,purchase_orderBY,islogin,isloginDT,isloginBY,SMS,SMSDT,SMSBY,edit_bill,edit_billDT,");
		sql.append("edit_billBY,delete_bill,delete_billDT,delete_billBY,edit_purchaseorder,edit_purchaseorderDT,edit_purchaseorderBY,delete_purchaseorder,delete_purchaseorderDT,delete_purchaseorderBY,");
		sql.append("edit_catalogue,edit_catalogueDT,edit_catalogueBY,delete_catalogue,delete_catalogueDT,delete_catalogueBY,back_date,back_dateDT,back_dateBY,third_party,third_partyDT,third_partyBY,");
		sql.append("requisition_auth,requisition_authDT,requisition_authBY,check_stock_available,check_stock_availableDT,check_stock_availableBY,direct_transfer,direct_transferDT,direct_transferBY,");
		sql.append("inventory_report,inventory_reportDT,inventory_reportBY,return_stock,return_stockDT,return_stockBY from apm_pharmacy_user");
		*/
	//	String sql="select id, billid, billbalance, payment, paymode, location, userid from apm_pharmacy_clear_bill where parentid="+parentid+" ";
		String sql1="select b.id,b.firstname,b.lastname,a.name from apm_pharmacy_user as b inner join  apm_condition as a on a.id=b.location where usertype=4 ;";
		
		String sql2="select  sale_bill, discount, ledger, account,SMS,edit_bill,delete_bill,edit_purchaseorder,delete_purchaseorder,edit_catalogue,back_date,third_party,requisition_auth,direct_transfer,inventory_report,return_stock from apm_pharmacy_user where usertype=4 ";
		String sql3="select  sale_billDT, discountDT, ledgerDT, accountDT,SMSDT,edit_billDT,delete_billDT,edit_purchaseorderDT,delete_purchaseorderDT,edit_catalogueDT,back_dateDT,third_partyDT,requisition_authDT,direct_transferDT,inventory_reportDT,return_stockDT from apm_pharmacy_user where usertype=4 ";
		String sql4="select  sale_billBY, discountBY, ledgerBY, accountBY,SMSBY,edit_billBY,delete_billBY,edit_purchaseorderBY,delete_purchaseorderBY,edit_catalogueBY,back_dateBY,third_partyBY,requisition_authBY,direct_transferBY,inventory_reportBY,return_stockBY from apm_pharmacy_user where usertype=4 ";
		PreparedStatement ps1=connection.prepareStatement(sql1.toString());
		ResultSet rs1 =ps1.executeQuery();
		PreparedStatement ps2=connection.prepareStatement(sql2.toString());
		ResultSet rs2 =ps2.executeQuery();
		
		PreparedStatement ps3=connection.prepareStatement(sql3.toString());
		ResultSet rs3 =ps3.executeQuery();
		PreparedStatement ps4=connection.prepareStatement(sql4.toString());
		ResultSet rs4 =ps4.executeQuery();
		
		int rsCount =rs1.getRow();
		int rsCount1=rs2.getRow();
	    ResultSetMetaData rsmdata=(ResultSetMetaData) rs2.getMetaData();
		int count = rsmdata.getColumnCount();
		int srno=1;
		
		while(rs1.next() && rs2.next() && rs3.next() && rs4.next())
		{
			    
				for(int j=1;j<=count;j++)
				{
					String fullname=rs1.getString(2).toString()+" "+rs1.getString(3).toString();
					Product  prod =new Product();
					prod.setApmPharmacyid(rs1.getString(1));
					//if(j==1)
				//	{
						prod.setFirstname(fullname);
						prod.setSrno(""+srno);
						srno++;
						prod.setAccessList("Create Sale Bill");
					   prod.setApmlocation(rs1.getString(4));
				//	}
					if(rs2.getString(j).toString().equals("0"))
					{
						prod.setAccessStatus("Disable");
					}
					else
					{
						prod.setAccessStatus("Enable");
					}
					
					if(rs3.getString(j).toString().equals("0"))
					{
						prod.setAccessListDT("-");
					}
					else
					{
						prod.setAccessListDT(rs3.getString(j));
					}
					if(rs4.getString(j).toString().equals("0"))
					{
						prod.setAccessListBy("-");
						
					}
					else
					{
						prod.setAccessListBy(rs4.getString(j));
						
						
					}
					
					
					
					//prod.setAccessList(rsmdata.getColumnName(j));
					
					
					
					if(j==2){prod.setAccessList("Give Discount");}
					if(j==3){prod.setAccessList("View Stock");}//ledger
					if(j==4){prod.setAccessList("View Profit & Loss");}//account
					//if(j==5){prod.setAccessList("Create Purchase Order");}
					//if(j==5){prod.setAccessList("IsLogin");}
					if(j==5){prod.setAccessList("Send SMS Authority");}
					if(j==6){prod.setAccessList("Edit Bill");}
					if(j==7){prod.setAccessList("Cancel Bill");}
					if(j==8){prod.setAccessList("Edit Purchase Order");}
					if(j==9){prod.setAccessList("Cancel Purchase Order");}
					if(j==10){prod.setAccessList("Edit Catalogue");}
					//if(j==13){prod.setAccessList("Cancel Catalogue");}
					if(j==11){prod.setAccessList("Edit Back Date Bill");}
					if(j==12){prod.setAccessList("Third Party Checkbox");}
					if(j==13){prod.setAccessList("Requisition Authority");}
					//if(j==17){prod.setAccessList("Check Availability Authority");}
					if(j==14){prod.setAccessList("Direct Transfer Authority");}
					if(j==15){prod.setAccessList("View Inventory Report");}
					if(j==16){prod.setAccessList("Return Product Authority");}
					list.add(prod);
				}
				
				
		}
		
				
		
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return list;
}


public Priscription setCancel_IndentAccess(String userid, String status,String datetime,String currentuser) {
	Priscription priscription = new Priscription();
	//@k@sh//ruchi
	try {
		try{
			String query = "update apm_pharmacy_user set cancel_indent='"+status+"',cancel_indentDT='"+datetime+"',cancel_indentBY='"+currentuser+"' where id="+userid+"";
			PreparedStatement preparedStatement1 = connection.prepareStatement(query);
			int result = preparedStatement1.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "select id, cancel_indent from apm_pharmacy_user where id="+userid+""; 
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			priscription.setId(rs.getInt(1));
			priscription.setCancel_indent(rs.getString(2));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return priscription;
}

public int getMaxLastReturnBillId() {

	int res=0;
	try {
		String sql="select refundid from apm_medicine_bill where refundid!=0 order by id desc limit 0,1";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs =ps.executeQuery();
		while(rs.next()){
			
			  res= rs.getInt(1);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return res;
}

public int getCountAccessList() {
	int result=0;
	try {
		
		String sql1="select b.id,b.firstname,b.lastname,a.name from apm_pharmacy_user as b inner join  apm_condition as a on a.id=b.location where usertype=4 ;";
		String sql2="select  sale_bill, discount, ledger, account,SMS,edit_bill,delete_bill,edit_purchaseorder,delete_purchaseorder,edit_catalogue,back_date,third_party,requisition_auth,direct_transfer,inventory_report,return_stock from apm_pharmacy_user where usertype=4 ";
		String sql3="select  sale_billDT, discountDT, ledgerDT, accountDT,SMSDT,edit_billDT,delete_billDT,edit_purchaseorderDT,delete_purchaseorderDT,edit_catalogueDT,back_dateDT,third_partyDT,requisition_authDT,direct_transferDT,inventory_reportDT,return_stockDT from apm_pharmacy_user where usertype=4 ";
		String sql4="select  sale_billBY, discountBY, ledgerBY, accountBY,SMSBY,edit_billBY,delete_billBY,edit_purchaseorderBY,delete_purchaseorderBY,edit_catalogueBY,back_dateBY,third_partyBY,requisition_authBY,direct_transferBY,inventory_reportBY,return_stockBY from apm_pharmacy_user where usertype=4 ";
		PreparedStatement ps1=connection.prepareStatement(sql1.toString());
		ResultSet rs1 =ps1.executeQuery();
		PreparedStatement ps2=connection.prepareStatement(sql2.toString());
		ResultSet rs2 =ps2.executeQuery();
		
		PreparedStatement ps3=connection.prepareStatement(sql3.toString());
		ResultSet rs3 =ps3.executeQuery();
		PreparedStatement ps4=connection.prepareStatement(sql4.toString());
		ResultSet rs4 =ps4.executeQuery();
		
		int rsCount =rs1.getRow();
		int rsCount1=rs2.getRow();
	    ResultSetMetaData rsmdata=(ResultSetMetaData) rs2.getMetaData();
		int count = rsmdata.getColumnCount();
		int srno=1;
		
		while(rs1.next() && rs2.next() && rs3.next() && rs4.next())
		{
			result++;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return result;
}

public ArrayList<Priscription> getPriscAvailableByMolecules(String parentpriscId) {

	PreparedStatement preparedStatement = null;
	
	ArrayList<Priscription>list = new ArrayList<Priscription>();
	PrescriptionMasterDAO masterDAO= new JDBCPrescriptionMasterDAO(connection);
	InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
	String sql = "SELECT id,lastmodified,mdicinename,dose,frequency,days,type,total,priscdurationtype,notes,mdicineid FROM apm_client_priscription where parentid = "+parentpriscId+" ";
	
	try{
		
		double subtotal=0;
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			Priscription priscription = new Priscription();
			priscription.setId(rs.getInt(1));
			
			if(rs.getString(2)!=null){
				priscription.setDate(DateTimeUtils.getDBDate(rs.getString(2)));
			}else{
				priscription.setDate("");
			}
			
			priscription.setLastmodified(rs.getString(2));
			priscription.setMdicinenametxt(rs.getString(3));
			priscription.setPriscdose(rs.getString(4));
			priscription.setPriscfreq(rs.getString(5));
			priscription.setPriscdays(rs.getString(6));
			priscription.setPrisctype(rs.getString(7));
			priscription.setPrisctotal(rs.getString(8));
			priscription.setPriscdurationtype(rs.getString(9));
			priscription.setDosenotes(rs.getString(10));
			priscription.setMdicinenameid(rs.getString(11));
			
			Priscription master= masterDAO.getPrescriptionDetails(priscription.getMdicinenameid());
			priscription.setMdicinenametxt(master.getDrug());
			int count=0;
			for(String str:priscription.getPriscdose().split("-")){
				
				 if(!str.equals("0")){
					 
					 int t=Integer.parseInt(str);
					 count=count+t;
				 }
			}
			
			int days=Integer.parseInt(priscription.getPriscdays());
			
			priscription.setRequired(count*days);
			Product product=inventoryProductDAO.getPriscAvailablilityByMolecules(master.getMolecules(),master.getGenericname(),count*days);
			if(product.getId()>0){
				//product=inventoryProductDAO.getMedicineProductDetails(priscription.getMdicinenameid());
			
			String expiry=DateTimeUtils.converToMMMYYYYforExp(product.getExpiry_date());
			priscription.setExpiry_date(expiry);
			priscription.setAvailable(product.getStock());
			priscription.setMrp(product.getSale_price());
			priscription.setBatch_no(product.getBatch_no());
			priscription.setProduct_id(String.valueOf(product.getId()));
			priscription.setShelf(product.getShelf());
			priscription.setVat(product.getVat());
			
			subtotal=subtotal+(Double.parseDouble(priscription.getMrp()));
			
			int t=Integer.parseInt(priscription.getAvailable());
			
			if(count>t){
				priscription.setSstatus(1);
			} else {
				priscription.setSstatus(0);
			}
			if(t>0){
				list.add(priscription);
			}
			priscription.setSubtotal(subtotal);
			
		   }	
			
			
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return list;
}


public Priscription setReturn_MedicineAccess(String userid, String status,String datetime,String currentuser) {
	Priscription priscription = new Priscription();
	//@k@sh
	try {
		try{
			String query = "update apm_pharmacy_user set return_medicine='"+status+"',return_medicineDT='"+datetime+"',return_medicineBY='"+currentuser+"' where id="+userid+"";
			PreparedStatement preparedStatement1 = connection.prepareStatement(query);
			int result = preparedStatement1.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "select id, return_medicine from apm_pharmacy_user where id="+userid+""; 
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			priscription.setId(rs.getInt(1));
			priscription.setReturn_medicine(rs.getString(2));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return priscription;
}

public int deleteMedicineCharges(String str) { //jitu
	int result=0;
	try {
		
		String sql="delete from apm_medicine_charges where id="+str+"";
		PreparedStatement ps=connection.prepareStatement(sql);
		result =ps.executeUpdate();
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	return result;
}

public int updateGstOfBill(String vat, String cgst, String billno) {

	int result=0;
	try {
		
		String sql="update apm_medicine_bill set vat=?,cgst=?,sgst=? where id="+billno+" ";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1, vat);
		ps.setString(2, cgst);
		ps.setString(3, cgst);
		
		result =ps.executeUpdate();
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return result;
}

public ArrayList<Product> getAllMedicinesofClient(String clientid, String ispharmacy, String location, int isrequest) {

	 ArrayList<Product> list=new ArrayList<Product>();
	 try {
		    StringBuffer buffer=new StringBuffer();
		    InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			buffer.append("SELECT apm_medicine_charges.id,apm_medicine_charges.product_id,apm_medicine_charges.quantity ");
			buffer.append("from apm_medicine_charges inner join apm_medicine_bill on apm_medicine_bill.id=apm_medicine_charges.invoiceid ");
			
			if(ispharmacy.equals("1")){
				buffer.append("where apm_medicine_charges.pclientid="+clientid+" ");
			} else {
				buffer.append("where apm_medicine_charges.clientId="+clientid+" ");
			}
			if(isrequest>0){
				buffer.append("and discount_status!=1 ");
			}
			if(!location.equals("0")){
				buffer.append("and apm_medicine_bill.location='"+location+"' ");
			}
			
			buffer.append("and apm_medicine_bill.isreturn=0 and apm_medicine_bill.deleted=0  ");
			buffer.append("order by apm_medicine_charges.id desc ");
			
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				  Product product= new Product();
				  product.setId(rs.getInt(1));
				  product.setProduct_id(rs.getString(2));
				  String qty=rs.getString(3);
				 
				  Product master= inventoryProductDAO.getProduct(product.getProduct_id());
				  String data = master.getProduct_name() + "-" + master.getGenericname() + "- (" + master.getBatch_no() + "/"
							+ master.getHsnno() + ") (" + master.getExpiry_date() + ") (Rs." + master.getSale_price()
							+ ") (" + qty + ")  ";
				  product.setGenericname(data);
                  list.add(product);
				  
			}
		 
	  } catch (Exception e) {

		 e.printStackTrace();
	 }
	 
	 return list;
}

public int updateRefundAllBalance(String clientId, String payamt, String flag) {

	int result=0;
	
	try {
		double amt= Double.parseDouble(payamt);
		
		StringBuffer buffer= new StringBuffer();
		buffer.append("select id,balance from apm_medicine_bill where isreturn=0 and balance>0 order by date asc   ");
		if(flag.equals("1")){
			  
			 buffer.append("and pclientid="+clientId+" ");
		} else {
			buffer.append("and clientid="+clientId+" ");
		}
		
		PreparedStatement ps=connection.prepareStatement(buffer.toString());
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			 
			 int billno= rs.getInt(1); 
			 double bal= rs.getDouble(2);
		     if(amt>=bal){
		    	     String sql1="update apm_medicine_bill set balance=0 where id="+billno+"  "; 
		    	     PreparedStatement ps1=connection.prepareStatement(sql1);
		    	     result=  ps1.executeUpdate();
		    	     amt=amt-bal;
		    	     continue;
		    	     
		     } else {
		    	    
		    	     double temp=bal-amt;
		    	     String sql1="update apm_medicine_bill set balance="+temp+" where id="+billno+"  "; 
		    	     PreparedStatement ps1=connection.prepareStatement(sql1);
		    	     result=  ps1.executeUpdate();
		    	     break;
		     }
		}
		
		
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return result;
  }

public int updateReturnQty(int saleqty, int chargeid) {

	int result=0;
	try {
		
		String sql="select returnqty from apm_medicine_charges where id="+chargeid+"  ";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			 
			     int qty= rs.getInt(1);
			     saleqty= saleqty+qty;
			     String sql2="update apm_medicine_charges set returnqty="+saleqty+" where id="+chargeid+"   ";
			     PreparedStatement ps2=connection.prepareStatement(sql2);
			     result =ps2.executeUpdate();
		}
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return result;
}

public int getReturnQtyofCharge(String chargeid) {

	int res=0;
	try {
		
		String sql="select returnqty from apm_medicine_charges where id="+chargeid+" ";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			    
			  res= rs.getInt(1);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public ArrayList<Priscription> getAllBillListofClient(String clientid, String flag) {
	ArrayList<Priscription> list=new ArrayList<Priscription>();
	try {
		StringBuffer buffer=new StringBuffer();
		buffer.append("SELECT apm_medicine_bill.id,apm_medicine_bill.date,apm_medicine_bill.refundid,apm_medicine_bill.isreturn from apm_medicine_bill ");
		buffer.append("inner join apm_medicine_payment on apm_medicine_bill.id=apm_medicine_payment.billno where ");
		if(flag.equals("1")){
			buffer.append("apm_medicine_bill.pclientid ="+clientid+" ");
		} else {
			buffer.append("apm_medicine_bill.clientid ="+clientid+" ");
		}
		
		//buffer.append("apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' ");
		buffer.append("group by apm_medicine_bill.id desc ");
		
		PreparedStatement ps=connection.prepareStatement(buffer.toString());
		ResultSet rs=ps.executeQuery();
		double totalbill=0;
		 double totaldisc=0;
		 double totalreturn=0;
		 double totalcash=0;
		 double subtotal =0;
		 double totalbalance =0;
		while(rs.next()){
			 	Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setBillno(rs.getString(1));
				
				if(rs.getInt(4)==1){
    				if(rs.getInt(3)==0){
    					priscription.setBillno("#"+rs.getString(1));
    				} else {
    					priscription.setBillno("#"+rs.getString(3));
    				}
    			}	
				
				priscription.setDate(DateTimeUtils.getCommencingDate1(rs.getString(2)));
				ArrayList<Priscription> priscriptionlist = getMedicineChargesList(priscription.getId(),null);
				priscription.setPriscriptionlist(priscriptionlist);
				int size = priscriptionlist.size();
				if (size > 0) {
					subtotal = priscriptionlist.get(size - 1).getSubtotal();
				} else {
					subtotal = 0;
				}
				totalbill = subtotal + totalbill;
				CompleteAppointment completeAppointment = getBillDetails(priscription.getId());
				double payAmt = getCahPayAmount(priscription.getId());
				String paymode = getBillPaymode(priscription.getId());
				totalcash = totalcash + payAmt;

				if (completeAppointment.getIsreturn() == 1) {
					totalreturn = totalreturn + completeAppointment.getTotal();
				}
				totaldisc = totaldisc + completeAppointment.getDiscount();
				totalbalance = totalbalance+Double.parseDouble(completeAppointment.getBalance());
				double billpayamt = getPayAmount(priscription.getId());
				priscription.setSubtotal(subtotal);
				priscription.setDiscount(completeAppointment.getDiscount());
				priscription.setCgst(completeAppointment.getCgst());
				priscription.setSgst(completeAppointment.getSgst());
				priscription.setBalance(completeAppointment.getBalance());
				priscription.setTotal(completeAppointment.getTotal());
				priscription.setBillpayamt(String.valueOf(billpayamt));
				priscription.setPaymode(paymode);
				priscription.setIsreturn(completeAppointment.getIsreturn());
				priscription.setTotalBalance(totalbalance);
				//last
				priscription.setTotalamt(totalbill);
				priscription.setTotalReturn(totalreturn);
				priscription.setTotaldisc(totaldisc);
				
				
				list.add(priscription);
		}
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return list;
	
}

public Priscription setindent_approveAccess(String userid, String status,String datetime,String currentuser) {
	Priscription priscription = new Priscription();
	//@k@sh
	try {
		try{
			String query = "update apm_pharmacy_user set indent_approve='"+status+"',indent_approveDT='"+datetime+"',indent_approveBY='"+currentuser+"' where id="+userid+"";
			PreparedStatement preparedStatement1 = connection.prepareStatement(query);
			int result = preparedStatement1.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "select id, indent_approve from apm_pharmacy_user where id="+userid+""; 
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			priscription.setId(rs.getInt(1));
			priscription.setIndent_approve(rs.getString(2));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return priscription;
}

public ArrayList<Priscription> getProductwiseReportList(String fromdate, String todate) {
	ArrayList<Priscription> arrayList = new ArrayList<Priscription>();
	try {
		StringBuffer buffer = new StringBuffer();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		buffer.append("SELECT id FROM apm_medicine_bill ");
		buffer.append("where isreturn='1' and date between '"+fromdate+"' and '"+todate+"' order by id desc ");
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		ArrayList<Priscription> medicineChargeList= new ArrayList<Priscription>();
		while (rs.next()) {
			
			String pclientid=getPharmacyClientidFromBill(rs.getInt(1));
			String name="";
			if(!pclientid.equals("0")){						
				Priscription priscription=getPharmacyPatient(pclientid);
				name=priscription.getFullname();
				
			}else{
				String ipdopd=getClientIDFromBillNo(rs.getInt(1));
				Client client = clientDAO.getClientDetails(ipdopd);
				try {
					name = client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
				} catch (Exception e) {
			
				}	
			}
			
			
			int size = medicineChargeList.size();
			int totalqty=0;
			double actualtemptot=0;
			if (size > 0) {
				totalqty = medicineChargeList.get(size - 1).getTotalqty();
				actualtemptot = medicineChargeList.get(size-1).getActualtemptot();
			}
			medicineChargeList = getMedicineChargesList(rs.getInt(1),name,totalqty,actualtemptot);
			arrayList.addAll(medicineChargeList);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

private ArrayList<Priscription> getMedicineChargesList(int billno, String name, int totalqty,double actualtemptot) {
	ArrayList<Priscription> list=new ArrayList<Priscription>();
	InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
	try {
		
		StringBuffer buffer=new StringBuffer();
		buffer.append("SELECT apm_medicine_charges.id,apm_medicine_charges.user,apm_medicine_charges.charge,apm_medicine_charges.practitionerId,apm_medicine_charges.practitionerName, ");
		buffer.append("apm_medicine_charges.clientId,apm_medicine_charges.commencing,apm_medicine_charges.paybuy,apm_medicine_charges.quantity, ");
		buffer.append("apm_medicine_charges.reqqty,apm_medicine_charges.medicineid,thirdPartyId,product_id,returnbillno ");
		buffer.append("from apm_medicine_charges inner join inventory_product on inventory_product.id=apm_medicine_charges.product_id where invoiceid="+billno+" ");
		buffer.append("order by inventory_product.prodname asc ");
		PreparedStatement ps=connection.prepareStatement(buffer.toString());
		ResultSet rs=ps.executeQuery();
		double subtotal=0;
		while(rs.next()){
			   
			  Priscription priscription=new Priscription();
			  priscription.setId(rs.getInt(1));
			  priscription.setClientname(rs.getString(2));
			  priscription.setMrp(DateTimeUtils.changeStringFormat(rs.getString(3)));
			  priscription.setPrectionerid(rs.getString(4));
			  priscription.setFullname(rs.getString(5));
			  priscription.setClientId(rs.getString(6));
			  priscription.setDate(rs.getString(7));
			  priscription.setWhopay(rs.getString(8));
			  priscription.setSaleqty(rs.getInt(9));
			  priscription.setReqqty(rs.getInt(10));
			  double total=Double.parseDouble(priscription.getMrp())*priscription.getSaleqty();
			  String str=DateTimeUtils.changeFormat(total);
			  priscription.setTotal(Double.parseDouble(str));
			  subtotal=subtotal+Double.parseDouble(str);
			  priscription.setSubtotal(subtotal);
			  priscription.setMdicinenameid(rs.getString(11));
			  priscription.setTpid(rs.getString(12));
			  priscription.setProduct_id(rs.getString(13));
			  String returnbillno= rs.getString(14);
			  Product product=inventoryProductDAO.getProduct(priscription.getProduct_id());
			  totalqty = totalqty+rs.getInt(9);
			  priscription.setTotalqty(totalqty);
			  if(returnbillno!=null){
				  
				   if(!returnbillno.equals("0")){
					   priscription.setMdicinenametxt(product.getProduct_name()+" ("+returnbillno+") ");
				   } else {
					   priscription.setMdicinenametxt(product.getProduct_name());
				   }
				  
			  } else {
				  priscription.setMdicinenametxt(product.getProduct_name());
			  }
			  
			  priscription.setBillno(String.valueOf(billno));
			  priscription.setBatch_no(product.getBatch_no());
			  priscription.setHsnno(product.getHsnno());
			  priscription.setPurchase_price(product.getPurchase_price());
			  priscription.setSale_price(product.getSale_price());
			  String prodcode=product.getProduct_code();
			  priscription.setMfg(product.getMfg());
			  priscription.setPkg("6");
			  priscription.setShelf(product.getShelf());
			  priscription.setVat(product.getVat());
			  priscription.setAvailable(product.getStock());
			  
			  ArrayList<Priscription> batches=getAllBatchNo(prodcode);
			  priscription.setBatches(batches);
			  int pack=10;
			  if(product.getPack()!=null){
				  if(product.getPack().equals("")){
					  pack=10;
				  } else {
					  pack =Integer.parseInt(product.getPack());
				  }
			  } else {
				  pack=10;  
			  }
			  
			  priscription.setPack(pack);
			  double discPerGivenEach= getDiscountPerReturnMedicine(priscription.getBillno(),total);
			  double tvat= Double.parseDouble(product.getVat());
			  double temptot=0;
			  double tempvat =0;
			  tempvat=total*discPerGivenEach/100;
			  temptot = total - tempvat;
			  double dividevat= 100+tvat;
			  /*double gst= tot*tvat/dividevat;*/
			  double gst= temptot*tvat/dividevat;
			  double half= gst/2;
			  double av=total-gst;
			  
			  /*  double vat= Double.parseDouble(product.getVat());
			  double dividevat=100+vat;
			  double gst= total*vat /dividevat;
			  double av=total-gst;
			  double half= gst/2;*/
			  priscription.setCgst(DateTimeUtils.changeFormat(Math.round(half*100.0)/100.0));
			  priscription.setSgst(DateTimeUtils.changeFormat(Math.round(half*100.0)/100.0));
			  priscription.setAv(DateTimeUtils.changeFormat(Math.round(av*100.0)/100.0));
			  priscription.setTemptot(DateTimeUtils.changeFormat(Math.round(temptot)));
			  actualtemptot = actualtemptot +Math.round(temptot);
			  priscription.setActualtemptot(actualtemptot);
			  
			  double stock=0.0;
			  if(product.getStock()!="0"){
				  if(!product.getStock().equals("")){
					   
					  stock=Integer.parseInt(product.getStock());
				  }
			  }
			  if(stock<=10){
				  priscription.setSstatus(1);
			  }
			  
			  if(product.getExpiry_date()!=null){
				  String temp=product.getExpiry_date();
				  String expiry=DateTimeUtils.converToMMMYYYYforExp(temp);
				  priscription.setExpiry_date(expiry);
			  }else {
				priscription.setExpiry_date("N/A");
			  }
			  
			  priscription.setClientname(name);
			  list.add(priscription);
			  
		}
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return list;
}

public ArrayList<Priscription> getPriscPharmacyAvailablityNew(String selectedid, String sessionid, String todate, String loc) {
	PreparedStatement preparedStatement = null;
	
	ArrayList<Priscription>list = new ArrayList<Priscription>();
	PrescriptionMasterDAO masterDAO= new JDBCPrescriptionMasterDAO(connection);
	InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
//	String sql = "SELECT id,lastmodified,mdicinename,dose,frequency,days,type,total,priscdurationtype,notes,mdicineid FROM apm_client_priscription where parentid = "+selectedid+" ";
	StringBuffer buffer = new StringBuffer();
	buffer.append("select apm_child_prisc.id,medicinename,medicineid,qty,date,apm_parent_prisc.clientid,default_location_new from apm_child_prisc ");
	buffer.append("inner join apm_parent_prisc on apm_parent_prisc.id =apm_child_prisc.parentid ");
	buffer.append("where parentid="+selectedid+" ");
	try{
		
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.MINUTE, -45);
		String fromdate = dateFormat1.format(cal1.getTime());			
		int res111 = deleteTempPharmacyTimelyData(fromdate);
		
		double subtotal=0;
		int countforcal=0;
		preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		loc = DateTimeUtils.isNull(loc);
		if(loc.equals("")){
			loc="0";
		}
		while(rs.next()){
			countforcal=countforcal+1;
			Priscription priscription = new Priscription();
			priscription.setId(rs.getInt(1));
			
			if(rs.getString(5)!=null){
				priscription.setDate(DateTimeUtils.getDBDate(rs.getString(5)));
			}else{
				priscription.setDate("");
			}
			
			priscription.setLastmodified(rs.getString(5));
			priscription.setMdicinenametxt(rs.getString(2));
			priscription.setMdicinenameid(rs.getString(3));
			
			Priscription master= masterDAO.getPrescriptionDetails(priscription.getMdicinenameid());
			priscription.setMdicinenametxt(master.getDrug());
			priscription.setMdicinenametxtnew(master.getDrug());
			priscription.setMolecules(master.getMolecules());
			priscription.setCountforcal(countforcal);
			int count=0;
			
			priscription.setRequired(rs.getInt(4));
			//Product product=inventoryProductDAO.getPriscMedicineByAvailablility(priscription.getMdicinenametxt(),rs.getInt(4),master.getGenericname());
			int default_location_new = rs.getInt(7);
			int location=0;
			location = Integer.parseInt(loc);
			
			ArrayList<Product> prodidslist = inventoryProductDAO.getPriscMedicineByAvailablilityList(priscription.getMdicinenametxt(),rs.getInt(4),master.getGenericname(),location);
			//  04 July 2018 its not working for same medicine for multiple if there is stock available and batch different
			int res =0;
			int i=0;
			for (Product product2 : prodidslist) {
				Priscription priscription2 = new Priscription();
				priscription2.setCountforcal(countforcal);
				if (i==0) {
					priscription2.setRepeatstatus(1);
				}else{
					priscription2.setRepeatstatus(0);
				}
				i++;
				priscription2.setId(rs.getInt(1));
				
				if(rs.getString(5)!=null){
					priscription2.setDate(DateTimeUtils.getDBDate(rs.getString(5)));
				}else{
					priscription2.setDate("");
				}
				
				priscription2.setLastmodified(rs.getString(5));
				priscription2.setMdicinenametxt(rs.getString(2));
				priscription2.setMdicinenameid(rs.getString(3));
				priscription2.setMdicinenametxt(master.getDrug());
				priscription2.setMdicinenametxtnew(master.getDrug());
				priscription2.setMolecules(master.getMolecules());
				priscription2.setRequired(rs.getInt(4));
				priscription2.setPurchase_price(product2.getPurchase_price());
				if(product2.getPack()!=null){
					if(product2.getPack().equals("") || product2.getPack().equals("0")){
						product2.setPack("1");
					}
				}else{
					product2.setPack("1");
				}
				priscription2.setPack(Integer.parseInt(product2.getPack()));
				priscription2.setSaleqty(product2.getSaleqty());
				res=1;
				if(product2.getId()>0){
						//product=inventoryProductDAO.getMedicineProductDetails(priscription.getMdicinenameid());
						String expiry=DateTimeUtils.converToMMMYYYYforExp(product2.getExpiry_date());
						priscription2.setExpiry_date(expiry);
						priscription2.setAvailable(product2.getStock());
						priscription2.setMrp(product2.getSale_price());
						priscription2.setBatch_no(product2.getBatch_no());
						priscription2.setProduct_id(String.valueOf(product2.getId()));
						priscription2.setShelf(product2.getShelf());
						priscription2.setVat(product2.getVat());
						priscription2.setMdicinenametxt(product2.getProduct_name());
						priscription2.setMdicinenametxtnew(master.getDrug());
						subtotal=subtotal+(Double.parseDouble(priscription2.getMrp()));
						
						int t=Integer.parseInt(priscription2.getAvailable());
						
						if(count>t){
							priscription2.setSstatus(1);
						} else {
							priscription2.setSstatus(0);
						}
						priscription2.setSubtotal(subtotal);
						boolean chargetempidisavailable = checkMedicineTempChargeLog(rs.getInt(6),0,sessionid,product2.getId(),""+product2.getSaleqty(),todate,todate);
						if(!chargetempidisavailable){
							boolean flag =checkPatientChargeAvailableInLog(rs.getInt(6),0,sessionid,product2.getId(),""+product2.getSaleqty(),todate,todate);
							int chargesessionid=0;
							if(!flag){
								//if entry not present in temp table so insert into temp table
								chargesessionid =insertMedicineChargeLog(rs.getInt(6),0,sessionid,product2.getId(),""+product2.getSaleqty(),todate,todate);
							}else{
								chargesessionid = getPatientChargeAvailableInLog(rs.getInt(6),0,sessionid,product2.getId(),""+product2.getSaleqty(),todate,todate);
							}
							double stock = Double.parseDouble(product2.getStock());
							double reqstock = getAllReqStockPharmacy(""+product2.getId());
							double requserstockstock = getAllReqStockPharmacy(""+product2.getId(),rs.getInt(6),0,sessionid);
							double totalavailablestock = (stock - reqstock) + requserstockstock;
							int chargetempid=0;
							//if entry not present in temp table so insert into temp table
							chargetempid = insertMedicineTempChargeLog(rs.getInt(6),0,sessionid,product2.getId(),""+product2.getSaleqty(),todate,todate,chargesessionid,totalavailablestock,product2.getSale_price(),"0");
							if(chargetempid>0){
								priscription2.setChargetempid(chargetempid);
								priscription2.setChargesessionid(chargesessionid);
								list.add(priscription2);
							}
							
						}
						
			
				} else {
					  priscription2.setOstatus(1);
				      list.add(priscription2);
			    }
			}
			if(res==0){
				priscription.setOstatus(1);
				priscription.setRepeatstatus(1);
		        list.add(priscription);
			}
			
			/*if(product.getId()>0){
				
					//product=inventoryProductDAO.getMedicineProductDetails(priscription.getMdicinenameid());
					String expiry=DateTimeUtils.converToMMMYYYYforExp(product.getExpiry_date());
					priscription.setExpiry_date(expiry);
					priscription.setAvailable(product.getStock());
					priscription.setMrp(product.getSale_price());
					priscription.setBatch_no(product.getBatch_no());
					priscription.setProduct_id(String.valueOf(product.getId()));
					priscription.setShelf(product.getShelf());
					priscription.setVat(product.getVat());
					
					subtotal=subtotal+(Double.parseDouble(priscription.getMrp()));
					
					int t=Integer.parseInt(priscription.getAvailable());
					
					if(count>t){
						priscription.setSstatus(1);
					} else {
						priscription.setSstatus(0);
					}
					priscription.setSubtotal(subtotal);
					list.add(priscription);
			
		   } else {
			      priscription.setOstatus(1);
			      list.add(priscription);
			     
		   }*/
			
			
			
			
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return list;
}

public int updatePriscEmrBillnew(int billno, int newparentid) {
	int result=0;
	try {

		String sql="update apm_parent_prisc set billno="+billno+",status='"+1+"' where id="+newparentid+"";
		PreparedStatement ps=connection.prepareStatement(sql);
		result=ps.executeUpdate();
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return result;
}

public Priscription setpo_approveAccess(String userid, String status, String datetime, String currentuser) {
	Priscription priscription = new Priscription();
	//@k@sh
	try {
		try{
			String query = "update apm_pharmacy_user set approve_po='"+status+"',approve_poDT='"+datetime+"',approve_poBY='"+currentuser+"' where id="+userid+"";
			PreparedStatement preparedStatement1 = connection.prepareStatement(query);
			int result = preparedStatement1.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "select id, approve_po from apm_pharmacy_user where id="+userid+""; 
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			priscription.setId(rs.getInt(1));
			priscription.setApprove_ponew(rs.getString(2));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return priscription;
}

public boolean isShowletterhd(){
boolean result=false;
PreparedStatement ps=null;
String sql="select show_letterhd from apm_pharmacy_user where id=1";
try{
	ps= connection.prepareStatement(sql);
	ResultSet rs= ps.executeQuery();
	while(rs.next()){
		result= rs.getBoolean(1);
	}
}catch (Exception e) {
	e.printStackTrace();
}
return result;	
}


public String getBillPaymodeNew(int billno) {
	String paymode="";
	try {
		String sql="select paymode from apm_medicine_payment where billno="+billno+" order by id desc limit 1 ";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			 paymode=rs.getString(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return paymode;
}


public Priscription setCancelPoAcces(String userid, String status, String accessdatetime, String currentUser) {
	Priscription priscription = new Priscription();
	//lokesh
	try {
		try{
			String query = "update apm_pharmacy_user set cancel_po='"+status+"',cancel_poDT='"+accessdatetime+"',cancel_poBY='"+currentUser+"' where id="+userid+"";
			PreparedStatement preparedStatement1 = connection.prepareStatement(query);
			int result = preparedStatement1.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "select id, cancel_po from apm_pharmacy_user where id="+userid+""; 
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			priscription.setId(rs.getInt(1));
			priscription.setCancel_po(rs.getString(2));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return priscription;
}

public Priscription setPurchaseEditAccess(String userid, String status, String accessdatetime, String currentUser) {
	Priscription priscription = new Priscription();
	//lokesh
	try {
		try{
			String query = "update apm_pharmacy_user set purchase_edit='"+status+"',purchase_editDT='"+accessdatetime+"',purchase_editBY='"+currentUser+"' where id="+userid+"";
			PreparedStatement preparedStatement1 = connection.prepareStatement(query);
			int result = preparedStatement1.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "select id, purchase_edit from apm_pharmacy_user where id="+userid+""; 
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			priscription.setId(rs.getInt(1));
			priscription.setPurchaseedit(rs.getString(2));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return priscription;

}

public ArrayList<Priscription> getAllPharmacySaleListNew(Pagination pagination, String searchtext, String fromdate,
		String todate, String location, String isreturn, String paymode, String userid) {
	ArrayList<Priscription> list= new ArrayList<Priscription>();
	try {
		
		StringBuffer buffer= new StringBuffer();
		buffer.append("select apm_medicine_bill.id,apm_medicine_bill.date,apm_medicine_bill.debit,apm_medicine_bill.clientid,apm_medicine_bill.pclientid, ");
		buffer.append("isreturn,apm_medicine_bill.refundamt,deleted,apm_medicine_bill.balance,apm_medicine_payment.payment,apm_medicine_bill.time,retunbillid, ");
		buffer.append("apm_medicine_bill.userid,apm_medicine_payment.paymode,apm_medicine_bill.refundid,apm_medicine_payment.date,apm_condition.name, ");
		buffer.append("apm_medicine_payment.paynote,apm_medicine_payment.userid,discount from apm_medicine_bill ");
		buffer.append("left join apm_medicine_payment on apm_medicine_bill.id=apm_medicine_payment.billno ");
		buffer.append("left join apm_pharmacy_client on apm_pharmacy_client.id = apm_medicine_bill.pclientid ");
		buffer.append("left join apm_patient on apm_patient.id = apm_medicine_bill.clientid ");
		buffer.append("left join apm_condition on apm_condition.id = apm_medicine_bill.location ");
		buffer.append("where apm_medicine_payment.date between '"+fromdate+"' and '"+todate+"' ");
		if(!location.equals("0")){
			buffer.append("and apm_medicine_bill.location='"+location+"' ");
		}
		if(isreturn.equals("1")){
			buffer.append("and apm_medicine_bill.isreturn=1 ");
		} else if(isreturn.equals("2")) {
			buffer.append("and apm_medicine_bill.isreturn=0 ");
		}else if(isreturn.equals("3")){
			buffer.append("and apm_medicine_bill.deleted=1 ");
		}
		
		if(!paymode.equals("0")){
			buffer.append("and apm_medicine_payment.paymode='"+paymode+"' ");
		}
		
		if(searchtext!=null){
			buffer.append("and  (apm_patient.firstname like ('"+searchtext+"%') or apm_patient.surname like ('"+searchtext+"%') or apm_patient.abrivationid like ('"+searchtext+"') ");
			buffer.append("or apm_patient.middlename like ('"+searchtext+"%') or apm_patient.fullname like ('"+searchtext+"%') or apm_medicine_bill.id like ('"+searchtext+"') ");
			buffer.append("or apm_pharmacy_client.fullname like ('"+searchtext+"%') or apm_pharmacy_client.fullname like ('%"+searchtext+"') ) ");
		}
		if(!userid.equals("0")){
			buffer.append("and apm_medicine_payment.userid='"+userid+"' ");
		}
		buffer.append("and (apm_medicine_bill.tpid='0' || apm_medicine_bill.tpid is NULL) ");
		/*buffer.append("group by apm_medicine_bill.id order by  apm_medicine_bill.id  asc ");*/
		buffer.append("order by  apm_medicine_bill.id  asc ");
		String sql =buffer.toString();
		if(pagination!=null){
			sql= pagination.getSQLQuery(sql);
		}
		
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		double totalReceived=0;
		double totalRefund=0;
		double totalBalance=0;
		double totalcredit=0;
		double totaldiscount=0;
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		ClientDAO clientDAO= new JDBCClientDAO(connection);
		BedDao bedDao =new JDBCBedDao(connection);
		UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
		while(rs.next()){
			
			Priscription priscription=new Priscription();
			priscription.setId(rs.getInt(1));
			priscription.setBillno(rs.getString(1));
			priscription.setDate(DateTimeUtils.getCommencingDate1(rs.getString(2)));
			priscription.setDebit(rs.getDouble(3));
			priscription.setTotal(rs.getDouble(3));
			priscription.setClientId(rs.getString(4));
			priscription.setPclientid(rs.getString(5));
			priscription.setIsreturn(rs.getInt(6));
			priscription.setReturnbill(rs.getInt(6));
			double refundtot= rs.getDouble(7);
			priscription.setDeleted(rs.getInt(8));
			priscription.setBalance(rs.getString(9));
			/*double payamt= getTotalPaymentReceived(rs.getInt(1));*/
			double payamt= rs.getDouble(10);
			priscription.setPayment(DateTimeUtils.changeFormat(payamt));
			String dateTime = priscription.getDate()+" "+rs.getString(11);
			priscription.setReturnbillid(rs.getInt(12));
			priscription.setUserid(rs.getString(13));
			priscription.setPaymode(rs.getString(14));
			//priscription.setNewpaymentmode(getBillPaymodeNew(rs.getInt(1)));
			if(rs.getInt(6)>0){
				totalRefund = totalRefund+rs.getDouble(3);
			}
			if(rs.getInt(15)>0){
				priscription.setRefundid(rs.getInt(15));
			} else {
				priscription.setRefundid(priscription.getId());
			}
			priscription.setCardno(rs.getString(18));
		    priscription.setLastmodified(dateTime);
		    String date2="";
		    try {
		    	date2 =DateTimeUtils.getCommencingDate1(rs.getString(16));
			} catch (Exception e) {
				// TODO: handle exception
			}
		    priscription.setPaymentdate(date2);
		    priscription.setDelivered(1);
		    double credit =0;
		    if(rs.getString(14).equals("Credit")){
		    	totalcredit = totalcredit + rs.getDouble(3);
		    	credit = rs.getDouble(3);
		    }
		    priscription.setTotalcredit(DateTimeUtils.changeFormat(totalcredit));
		    priscription.setCredit(credit);
			if(priscription.getIsreturn()>0 && rs.getDouble(3)==0){
				priscription.setTotal(refundtot);
			}
			
			
			//  14 July 2018
			totalReceived = totalReceived +payamt;
			totalBalance = totalBalance+ rs.getDouble(9);
			priscription.setTotalBalance(totalBalance);
			priscription.setTotalReceived(totalReceived);
			priscription.setTotalrefund(totalRefund);
			if(rs.getInt(5)>0){
				  // pharmacy patient
				  Priscription masterClient = getPharmacyPatient(priscription.getPclientid());
				  priscription.setFullname(masterClient.getFullname());
				  priscription.setMobile(masterClient.getMobile());
				  priscription.setPractitionername(masterClient.getPractitionername());
				  priscription.setAddress(masterClient.getAddress());
				  priscription.setAgeandgender(masterClient.getAgeandgender());
				  priscription.setOutp(1);
				  if(!DateTimeUtils.numberCheck(masterClient.getAbrivationid()).equals("0")){
					  priscription.setAbrivationid(masterClient.getAbrivationid());
				  }else{
					  priscription.setAbrivationid("");
				  }
				  
			} else {
				   //ipd/opd patient
				  
				   Client client =clientDAO.getClientDetails(priscription.getClientId()); 
				   String fullname =client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();  
				   priscription.setFullname(fullname);
				   String agegender= DateTimeUtils.getAge(client.getDob()) +" "+client.getGender();
				   priscription.setAgeandgender(agegender);
				   String ipdid = getIpdIdFromClientID(Integer.parseInt(priscription.getClientId()));
					String pract_name ="";
					if(ipdid.equals("0")){
						pract_name = getappointmentinfo(Integer.parseInt(priscription.getClientId()));
					}else{
						
						
						Bed  bed = bedDao.getEditIpdData(ipdid);
						
						pract_name = profileDAO.getFullName(bed.getPractitionerid());
						String wardname = ipdDAO.getIpdWardName(bed.getWardid());
						//@ruchi to check casualty and icu patient to blink
						String urgentward = checkICUorCasulty(bed.getWardid());
						
						if (!urgentward.equals("")) {
							priscription.setUrgentward("1");
						}
						//end
						String bedname= ipdDAO.getIpdBedName(bed.getBedid());
						priscription.setWardname(wardname);
						priscription.setBedname(bedname);
						
					}
					priscription.setIpdid(ipdid);
					priscription.setPractitionername(pract_name);
					priscription.setAddress(client.getAddress());
					priscription.setAbrivationid(client.getAbrivationid());
				    priscription.setMobile(client.getMobNo());
				    priscription.setOutp(0);
				    
			}
			if(rs.getString(17)==null){
				priscription.setLocationame("");
			}else{
				priscription.setLocationame(rs.getString(17));
			}
			priscription.setPaymentuserid(rs.getString(19));
			priscription.setDiscount(rs.getDouble(20));
			totaldiscount=totaldiscount+rs.getDouble(20);
			priscription.setTotaldisc(totaldiscount);

			list.add(priscription);
		}
}catch (Exception e) {
	e.printStackTrace();
}
	return list;
}

public int getAllPharmacySaleCountNew(String searchtext, String fromdate, String todate, String location,
		String isreturn, String paymode, String userid) {

	int result=0;
	try {
		
		/*StringBuffer buffer= new StringBuffer();
		buffer.append("select count(*) from apm_medicine_bill ");
		buffer.append("where apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"'  ");
		if(!location.equals("0")){
			buffer.append("and apm_medicine_bill.location="+location+" ");
		}
		if(isreturn.equals("1")){
			buffer.append("and apm_medicine_bill.isreturn=1 ");
		}*/
		
		StringBuffer buffer= new StringBuffer();
		/*buffer.append("select apm_medicine_bill.id,apm_medicine_bill.date,apm_medicine_bill.debit,apm_medicine_bill.clientid,apm_medicine_bill.pclientid,isreturn,apm_medicine_bill.refundamt,deleted,balance,apm_medicine_payment.payment,apm_medicine_bill.time,retunbillid,apm_medicine_bill.userid,apm_medicine_payment.paymode,apm_medicine_bill.refundid ");
		buffer.append("from apm_medicine_bill left join apm_medicine_payment on apm_medicine_bill.id=apm_medicine_payment.billno  ");*/
		buffer.append("select count(*) from apm_medicine_bill ");
		buffer.append("left join apm_medicine_payment on apm_medicine_bill.id=apm_medicine_payment.billno ");
		buffer.append("left join apm_pharmacy_client on apm_pharmacy_client.id = apm_medicine_bill.pclientid ");
		buffer.append("left join apm_patient on apm_patient.id = apm_medicine_bill.clientid ");
		
		buffer.append("where apm_medicine_payment.date between '"+fromdate+"' and '"+todate+"' ");
		if(!location.equals("0")){
			buffer.append("and apm_medicine_bill.location='"+location+"' ");
		}
		if(isreturn.equals("1")){
			buffer.append("and apm_medicine_bill.isreturn=1 ");
		} else if(isreturn.equals("2")) {
			buffer.append("and apm_medicine_bill.isreturn=0 ");
		}else if(isreturn.equals("3")){
			buffer.append("and apm_medicine_bill.deleted=1 ");
		}
		
		if(!paymode.equals("0")){
			buffer.append("and apm_medicine_payment.paymode='"+paymode+"' ");
		}
		
		if(searchtext!=null){
			buffer.append("and  (apm_patient.firstname like ('"+searchtext+"%') or apm_patient.surname like ('"+searchtext+"%') or apm_patient.abrivationid like ('"+searchtext+"') ");
			buffer.append("or apm_patient.middlename like ('"+searchtext+"%') or apm_patient.fullname like ('"+searchtext+"%') or apm_medicine_bill.id like ('"+searchtext+"') ");
			buffer.append("or apm_pharmacy_client.fullname like ('"+searchtext+"%') or apm_pharmacy_client.fullname like ('%"+searchtext+"') ) ");
		}
		if(!userid.equals("0")){
			buffer.append("and apm_medicine_payment.userid='"+userid+"' ");
		}
		buffer.append("and (apm_medicine_bill.tpid='0' || apm_medicine_bill.tpid is NULL) ");
		buffer.append("order by  apm_medicine_bill.id  asc ");
		
		PreparedStatement ps=connection.prepareStatement(buffer.toString());
		ResultSet rs =ps.executeQuery();
		
		while(rs.next()){
			 result =rs.getInt(1);
		}
		
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return result;
}

public double getTotalAgainstCredit(String location, String fromdate, String todate) {
	double temp=0;
	try {
		
		StringBuffer buffer= new StringBuffer();
		buffer.append("select sum(payment) from apm_medicine_bill ");
		buffer.append("inner join apm_medicine_payment on apm_medicine_bill.id=apm_medicine_payment.billno ");
		buffer.append("where apm_medicine_payment.date between '"+fromdate+"' and '"+todate+"' ");
		buffer.append("and (apm_medicine_bill.tpid=0 or apm_medicine_bill.tpid is NULL) and apm_medicine_bill.isreturn=0 ");
		if(!location.equals("0")){
			buffer.append("and apm_medicine_bill.location="+location+" ");
		}
	    buffer.append("and apm_medicine_payment.paymode!='Hospital' and onsamedate='1' group by apm_medicine_bill.id ");
		
		PreparedStatement ps=connection.prepareStatement(buffer.toString());
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			 
			double res=rs.getDouble(1);
			temp =temp+res;
		}
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return temp;
}

public int updateBillBalance(String billno, double temp) {
	int result=0;
	try {
		 String sql1="update apm_medicine_bill set balance="+temp+" where id="+billno+"  "; 
	     PreparedStatement ps1=connection.prepareStatement(sql1);
	     result=  ps1.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public int getClearBalBillCount(String billno) {
	int res =0;
	 try {
		 String sql="select count(*) from apm_pharmacy_clear_bill where billid="+billno+"  ";
		 PreparedStatement ps=connection.prepareStatement(sql);
		 ResultSet rs =ps.executeQuery();
		 while(rs.next()){
			   res = rs.getInt(1);
		 }
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public ArrayList<Priscription> getClearBalBillList(String billno) {
	ArrayList<Priscription> arrayList = new ArrayList<Priscription>();
	try {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select parentid,payment,datetime,apm_pharmacy_clear_bill.paymode from apm_pharmacy_clear_bill ");
		buffer.append("inner join apm_pharmacy_parent_clear_bill on apm_pharmacy_parent_clear_bill.id =apm_pharmacy_clear_bill.parentid ");
		buffer.append("where billid='"+billno+"'");
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			Priscription priscription = new  Priscription();
			priscription.setParentid(rs.getString(1));
			priscription.setDebit(rs.getDouble(2));
			String datetime="";
			if(rs.getString(3)!=null){
				String[] date = rs.getString(3).split(" ");
				datetime = DateTimeUtils.getCommencingDate1(date[0]) +" "+ date[1];
			}
			priscription.setDateTime(datetime);
			priscription.setPaymode(rs.getString(4));
			arrayList.add(priscription);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

public ArrayList<Priscription> getAllPharmacySaleListRefund(String location, String medids, String hdnispharmacy, String hdnphclientid,LoginInfo loginInfo) {
	ArrayList<Priscription> list= new ArrayList<Priscription>();
	try {
		StringBuffer buffer= new StringBuffer();
		buffer.append("select apm_medicine_bill.id,apm_medicine_bill.date,apm_medicine_bill.debit,apm_medicine_bill.clientid,apm_medicine_bill.pclientid,isreturn,apm_medicine_bill.refundamt,deleted,balance,apm_medicine_payment.payment,apm_medicine_bill.time,retunbillid,apm_medicine_bill.userid,apm_medicine_payment.paymode,apm_medicine_bill.refundid,apm_medicine_bill.discount ");
		buffer.append("from apm_medicine_bill left join apm_medicine_payment on apm_medicine_bill.id=apm_medicine_payment.billno  ");
		buffer.append("left join apm_medicine_charges on apm_medicine_bill.id = apm_medicine_charges.invoiceid ");
		buffer.append("where product_id in ("+medids+") and apm_medicine_bill.retunbillid=0 ");
		if(!location.equals("0")){
			buffer.append("and apm_medicine_bill.location='"+location+"' ");
		}
		
		if(hdnispharmacy.equals("0")){
			buffer.append("and apm_medicine_bill.clientid='"+hdnphclientid+"' ");
		}else{
			buffer.append("and apm_medicine_bill.pclientid='"+hdnphclientid+"' ");
		}
		buffer.append("and (apm_medicine_bill.tpid='0' || apm_medicine_bill.tpid is NULL) ");
		buffer.append("group by apm_medicine_bill.id desc ");
		
		String sql =buffer.toString();
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		double totalReceived=0;
		double totalRefund=0;
		double totalBalance=0;
		while(rs.next()){
			
			Priscription priscription=new Priscription();
			priscription.setId(rs.getInt(1));
			priscription.setBillno(rs.getString(1));
			priscription.setDate(DateTimeUtils.getCommencingDate1(rs.getString(2)));
			priscription.setDebit(rs.getDouble(3));
			priscription.setTotal(rs.getDouble(3));
			priscription.setClientId(rs.getString(4));
			priscription.setPclientid(rs.getString(5));
			priscription.setIsreturn(rs.getInt(6));
			priscription.setReturnbill(rs.getInt(6));
			double refundtot= rs.getDouble(7);
			priscription.setDeleted(rs.getInt(8));
			priscription.setBalance(rs.getString(9));
			double payamt= getTotalPaymentReceived(rs.getInt(1));
			priscription.setPayment(DateTimeUtils.changeFormat(payamt));
			String dateTime = priscription.getDate()+" "+rs.getString(11);
			priscription.setReturnbillid(rs.getInt(12));
			priscription.setUserid(rs.getString(13));
			priscription.setPaymode(rs.getString(14));
			priscription.setNewpaymentmode(getBillPaymodeNew(rs.getInt(1)));
			
			if(rs.getInt(15)>0){
				priscription.setRefundid(rs.getInt(15));
			} else {
				priscription.setRefundid(priscription.getId());
			}
		    priscription.setLastmodified(dateTime);
		    priscription.setDelivered(1);
		    
		    
			if(priscription.getIsreturn()>0 && rs.getDouble(3)==0){
				priscription.setTotal(refundtot);
			}
			
			//  14 July 2018
			if(rs.getInt(6)>0){
				totalRefund = totalRefund+rs.getDouble(3);
			}
			totalReceived = totalReceived +payamt;
			totalBalance = totalBalance+ rs.getDouble(9);
			priscription.setTotalBalance(totalBalance);
			priscription.setTotalReceived(totalReceived);
			priscription.setTotalrefund(totalRefund);
			
			if(rs.getInt(5)>0){
				  // pharmacy patient
				  Priscription masterClient = getPharmacyPatient(priscription.getPclientid());
				  priscription.setFullname(masterClient.getFullname());
				  priscription.setMobile(masterClient.getMobile());
				  priscription.setPractitionername(masterClient.getPractitionername());
				  priscription.setAddress(masterClient.getAddress());
				  priscription.setAgeandgender(masterClient.getAgeandgender());
				  priscription.setOutp(1);
				  
			} else {
				   //ipd/opd patient
				   ClientDAO clientDAO= new JDBCClientDAO(connection);
				   BedDao bedDao =new JDBCBedDao(connection);
				   Client client =clientDAO.getClientDetails(priscription.getClientId()); 
				   String fullname =client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();  
				   priscription.setFullname(fullname);
				   String agegender= DateTimeUtils.getAge(client.getDob()) +" "+client.getGender();
				   priscription.setAgeandgender(agegender);
				   String ipdid = getIpdIdFromClientID(Integer.parseInt(priscription.getClientId()));
					String pract_name ="";
					if(ipdid.equals("0")){
						pract_name = getappointmentinfo(Integer.parseInt(priscription.getClientId()));
					}else{
						
						IpdDAO ipdDAO= new JDBCIpdDAO(connection);
						Bed  bed = bedDao.getEditIpdData(ipdid);
						UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
						pract_name = profileDAO.getFullName(bed.getPractitionerid());
						String wardname = ipdDAO.getIpdWardName(bed.getWardid());
						//@ruchi to check casualty and icu patient to blink
						String urgentward = checkICUorCasulty(bed.getWardid());
						
						if (!urgentward.equals("")) {
							priscription.setUrgentward("1");
						}
						//end
						String bedname= ipdDAO.getIpdBedName(bed.getBedid());
						priscription.setWardname(wardname);
						priscription.setBedname(bedname);
						
					}
					priscription.setIpdid(ipdid);
					priscription.setPractitionername(pract_name);
					priscription.setAddress(client.getAddress());
					priscription.setAbrivationid(client.getAbrivationid());
				    priscription.setMobile(client.getMobNo());
				    priscription.setOutp(0);
				    
			}
			//Pharmacy 30 july 2018
			priscription.setDiscount(rs.getDouble(16));
			
			//lokesh 17/aug for setting print show flag
			
			if(priscription.getDate()!=null){
				
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				
				String checkdate = dateFormat.format(cal.getTime());
				if(priscription.getDate().equals(checkdate)||loginInfo.isPharm_print_backdate()){
					priscription.setPrintflag(true);
				}else{
					priscription.setPrintflag(false);
				}
				}
			list.add(priscription);
			
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return list;
}

public Double getTotalRecivedAmt(String searchtext, String fromdate, String todate, String location, String isreturn,
		String paymode, String userid) {
	double res =0;
	try {
		
		StringBuffer buffer= new StringBuffer();
		buffer.append("select sum(apm_medicine_payment.payment) ");
		buffer.append("from apm_medicine_bill ");
		buffer.append("left join apm_medicine_payment on apm_medicine_bill.id=apm_medicine_payment.billno ");
		buffer.append("left join apm_pharmacy_client on apm_pharmacy_client.id = apm_medicine_bill.pclientid ");
		buffer.append("left join apm_patient on apm_patient.id = apm_medicine_bill.clientid ");
		buffer.append("left join apm_condition on apm_condition.id = apm_medicine_bill.location ");
		buffer.append("where apm_medicine_payment.date between '"+fromdate+"' and '"+todate+"' ");
		if(!location.equals("0")){
			buffer.append("and apm_medicine_bill.location='"+location+"' ");
		}
		if(isreturn.equals("1")){
			buffer.append("and apm_medicine_bill.isreturn=1 ");
		} else if(isreturn.equals("2")) {
			buffer.append("and apm_medicine_bill.isreturn=0 ");
		}else if(isreturn.equals("3")){
			buffer.append("and apm_medicine_bill.deleted=1 ");
		}
		
		if(!paymode.equals("0")){
			buffer.append("and apm_medicine_payment.paymode='"+paymode+"' ");
		}
		
		if(searchtext!=null){
			buffer.append("and  (apm_patient.firstname like ('"+searchtext+"%') or apm_patient.surname like ('"+searchtext+"%') or apm_patient.abrivationid like ('"+searchtext+"') ");
			buffer.append("or apm_patient.middlename like ('"+searchtext+"%') or apm_patient.fullname like ('"+searchtext+"%') or apm_medicine_bill.id like ('"+searchtext+"') ");
			buffer.append("or apm_pharmacy_client.fullname like ('"+searchtext+"%') or apm_pharmacy_client.fullname like ('%"+searchtext+"') ) ");
		}
		if(!userid.equals("0")){
			buffer.append("and apm_medicine_payment.userid='"+userid+"' ");
		}
		buffer.append("and (apm_medicine_bill.tpid='0' || apm_medicine_bill.tpid is NULL) ");
		buffer.append("order by  apm_medicine_bill.id  asc ");
		String sql =buffer.toString();
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			res= rs.getDouble(1);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
}

public int cancelEstimate(String bill) {
	int res =0;
	try {
		String sql="update apm_medicine_estimate_bill set isestimatecancel='1' where id='"+bill+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		res = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public ArrayList<Product> getAllBillsofClient(String clientid, String ispharmacy, String paymodereturn,
		String location) {
	 ArrayList<Product> list=new ArrayList<Product>();
	 try {
		    StringBuffer buffer=new StringBuffer();
		    buffer.append("SELECT apm_medicine_bill.id,apm_medicine_bill.date from apm_medicine_bill ");
			if(ispharmacy.equals("1")){
				buffer.append("where apm_medicine_bill.pclientid="+clientid+" ");
			} else {
				buffer.append("where apm_medicine_bill.clientId="+clientid+" ");
			}
			if(!location.equals("0")){
				buffer.append("and apm_medicine_bill.location='"+location+"'  ");
			}
			
			buffer.append("and apm_medicine_bill.isreturn=0 and deleted=0  ");
			buffer.append("order by apm_medicine_bill.id desc ");
			
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			String ids="0";
			while(rs.next()){
				  Product product= new Product();
				  product.setId(rs.getInt(1));
				  String paymode = getBillPaymodeNew(rs.getInt(1));
				  product.setDate(DateTimeUtils.getCommencingDate1(rs.getString(2)));
				  if(paymode!=null){
					  if(!paymodereturn.equals("Credit")){
						  if(!paymode.equals("Credit")){
							  ids = ids +","+ rs.getInt(1);
							  product.setIds(ids);
							  list.add(product);
						  }
					  }else{
						  if(paymode.equals("Credit")){
							  ids = ids +","+ rs.getInt(1);
							  product.setIds(ids);
							  list.add(product);
						  }
					  }
					 /* if(paymode.equals(paymodereturn)){
						  ids = ids +","+ rs.getInt(1);
						  product.setIds(ids);
						  list.add(product);
					  }*/
				  }
                  
			}
		 
	  } catch (Exception e) {

		 e.printStackTrace();
	 }
	 
	 return list;
}

public ArrayList<Product> getAllBillWise(String clientid, String ispharmacy, String multireturnbillid,int isfromnurse) {
	ArrayList<Product> list=new ArrayList<Product>();
	 try {
		    StringBuffer buffer=new StringBuffer();
		    InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			buffer.append("SELECT apm_medicine_charges.id,apm_medicine_charges.product_id,apm_medicine_charges.quantity,apm_medicine_charges.charge,((quantity+0)-(returnqty+0)),apm_medicine_bill.id ");
			buffer.append("from apm_medicine_charges inner join apm_medicine_bill on apm_medicine_bill.id=apm_medicine_charges.invoiceid ");
			if(isfromnurse>0){
				buffer.append("where apm_medicine_charges.id='"+multireturnbillid+"' ");
			}else{
				buffer.append("where apm_medicine_bill.id in ("+multireturnbillid+" )");
			}
			buffer.append("and ((quantity+0)-(returnqty+0))>0 and apm_medicine_bill.deleted=0 and discount_status!=1 ");
			/*if(ispharmacy.equals("1")){
				buffer.append("where apm_medicine_charges.pclientid="+clientid+" ");
			} else {
				buffer.append("where apm_medicine_charges.clientId="+clientid+" ");
			}
			
			buffer.append("and apm_medicine_bill.isreturn=0  ");*/
			buffer.append("order by apm_medicine_charges.id desc ");
			
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				int isinadjustment = inventoryProductDAO.checkProductInAdjustment(rs.getString(2));
				if(isinadjustment==1){
					continue;
				}
				  Product product= new Product();
				  product.setId(rs.getInt(1));
				  product.setProduct_id(rs.getString(2));
				 /* String qty=rs.getString(3);*/
				  String qty=rs.getString(5);
				  double saleprice = rs.getDouble(4);
				  Product master= inventoryProductDAO.getProduct(product.getProduct_id());
				  /*String data = master.getProduct_name() + "-" + master.getGenericname() + "- (" + master.getBatch_no() + "/"
							+ master.getHsnno() + ") (" + master.getExpiry_date() + ") (Rs." + master.getSale_price()
							+ ") (" + qty + ")  ";*/
				  int res =0;
				  if(DateTimeUtils.isNull(master.getGenericname()).equals("")){
					  res =1;
				  }
				  if(res==1){
					  String data = master.getProduct_name()  + "- Billno. " + rs.getInt(6) + "- (" + master.getBatch_no() + "/"
								+ master.getHsnno() + ") (" + master.getExpiry_date() + ") (Rs." + saleprice
								+ ") (" + qty + ")  ";
					  product.setGenericname(data);
				  }else{
					  String data = master.getProduct_name() + "-" + DateTimeUtils.isNull(master.getGenericname()) + "- Billno. " + rs.getInt(6) + "- (" + master.getBatch_no() + "/"
								+ master.getHsnno() + ") (" + master.getExpiry_date() + ") (Rs." + saleprice
								+ ") (" + qty + ")  ";
					  product.setGenericname(data);
				  }
				  
                 list.add(product);
				  
			}
		 
	  } catch (Exception e) {

		 e.printStackTrace();
	 }
	 
	 return list;
}

public int updateBillRefundBillids(int billno, String returnbillids) {
	int result=0;
	try {
		String sql="update apm_medicine_bill set refundbillids='"+returnbillids+"' where id='"+billno+"' ";
		PreparedStatement ps=connection.prepareStatement(sql);
		result =ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return result;
}

public ArrayList<Priscription> getallTempMedList() {
	PreparedStatement ps= null;
	ArrayList<Priscription>  list= new ArrayList<Priscription>();
	try {
		String sql="    select id, drug,genericname,addeduserId from apm_medicine_details where istempmed='1' ";
		ps= connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			Priscription prisc= new Priscription();
			prisc.setId(rs.getInt(1));
			prisc.setDrug(rs.getString(2));
			prisc.setGenericname(rs.getString(3));
			prisc.setUserid(rs.getString(4));
			list.add(prisc);
		}
				
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list; 
}

public int settemptoPermanat(String id, String date, String userid) {
	int res=0;
	
	try {
		String sql= "update apm_medicine_details set istempmed='0' ,aproveDT='"+date+"', aproveuserId='"+userid+"' where id='"+id+"' ";
		PreparedStatement ps= connection.prepareStatement(sql);
		res= ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public int saveReturnMedicineByNurse(String clientid, String userId, String datetime, String ipdid) {
	int result=0;
	
	try {
		String sql="insert into return_medicine_parent (userid,datetime,clientid,ipdid,status) values (?,?,?,?,?)";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1, userId);
		ps.setString(2, datetime);
		ps.setString(3, clientid);
		ps.setString(4, ipdid);
		ps.setString(5, "0");
		result=ps.executeUpdate();
		if(result>0){
			ResultSet rs=ps.getGeneratedKeys();
			while(rs.next()){
				result=rs.getInt(1);
			}
		}
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return result;
}

public int returnByNurseChild(Priscription priscription) {
	int result=0;
	try {
		String sql="insert into return_medicine_child (parentid,medicine_id,medicine_name,qty,chargeid,prodid) values (?,?,?,?,?,?)";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1, priscription.getParentid());
		ps.setString(2, priscription.getMedicineid());
		ps.setString(3, priscription.getProductname());
		ps.setString(4, ""+priscription.getSaleqty());
		ps.setString(5, ""+priscription.getId());
		ps.setString(6, priscription.getProduct_id());
		result=ps.executeUpdate();
		if(result>0){
			ResultSet rs=ps.getGeneratedKeys();
			while(rs.next()){
				result=rs.getInt(1);
			}
		}
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return result;
}

public ArrayList<Priscription> getReturnMedicineDash(String fromdate, String todate) {
	ArrayList<Priscription> arrayList = new ArrayList<Priscription>();
	try {
		todate = todate +" "+"59:59:59";
		StringBuffer buffer = new StringBuffer();
		/*buffer.append("select return_medicine_parent.id,apm_patient.fullname,concat(apm_user.initial,' ',apm_user.firstname,' ',apm_user.lastname),datetime, ");
		buffer.append("medicine_name,qty,prodid,return_medicine_parent.userid,chargeid,return_medicine_child.returnstatus from return_medicine_parent ");
		buffer.append("inner join return_medicine_child on return_medicine_parent.id = return_medicine_child.parentid ");
		buffer.append("inner join apm_patient on apm_patient.id = return_medicine_parent.clientid ");
		buffer.append("inner join apm_user on apm_user.userid = return_medicine_parent.userid ");
		buffer.append("where datetime between '"+fromdate+"' and '"+todate+"' ");*/
		buffer.append("select return_medicine_parent.id,apm_patient.fullname,concat(apm_user.initial,' ',apm_user.firstname,' ',apm_user.lastname), ");
		buffer.append("datetime, return_medicine_parent.userid,return_medicine_parent.status ");
		buffer.append("from return_medicine_parent ");
		buffer.append("inner join apm_patient on apm_patient.id = return_medicine_parent.clientid ");
		buffer.append("inner join apm_user on apm_user.userid = return_medicine_parent.userid ");
		buffer.append("where datetime between '"+fromdate+"' and '"+todate+"' ");
		buffer.append("order by return_medicine_parent.id desc ");
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			/*Priscription priscription = new Priscription();
			priscription.setId(rs.getInt(1));
			priscription.setClientname(rs.getString(2));
			priscription.setUsername(rs.getString(3));
			priscription.setDateTime(rs.getString(4));
			priscription.setProductname(rs.getString(5));
			priscription.setReturn_qty(rs.getString(6));
			priscription.setProduct_id(rs.getString(7));
			priscription.setUserid(rs.getString(8));
			priscription.setChargeid(rs.getString(9));
			priscription.setReturnstatus(""+rs.getInt(10));
			arrayList.add(priscription);*/
			Priscription priscription = new Priscription();
			priscription.setId(rs.getInt(1));
			priscription.setClientname(rs.getString(2));
			priscription.setUsername(rs.getString(3));
			priscription.setDateTime(rs.getString(4));
			priscription.setUserid(rs.getString(5));
			int res = getCheckDiscountNotRequested(rs.getString(1));
			if(res==1){
				priscription.setReturnstatus(""+2);
			}else{
				priscription.setReturnstatus(""+rs.getInt(6));
			}
			
			arrayList.add(priscription);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

public Priscription getReturnMedicineRequest(String id) {
	Priscription priscription = new  Priscription();
	try {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select return_medicine_parent.clientid,return_medicine_parent.ipdid,qty,invoiceid,return_medicine_child.id from return_medicine_parent ");
		buffer.append("inner join return_medicine_child on return_medicine_parent.id = return_medicine_child.parentid ");
		buffer.append("inner join apm_medicine_charges on return_medicine_child.chargeid = apm_medicine_charges.id ");
		buffer.append("where return_medicine_parent.id='"+id+"' ");
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			priscription.setClientId(rs.getString(1));
			priscription.setIpdid(rs.getString(2));
			priscription.setReturn_qty(rs.getString(3));
			String paymode =  getBillPaymodeNew(rs.getInt(4));
			priscription.setPaymode(paymode);
			priscription.setId(rs.getInt(5));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return priscription;
}

public int updateReturnRequestStatus(String returnrequestid, String returnchargeid, int billno) {
	int res =0;
	try {
		/*String sql="update return_medicine_child set returnstatus='1' where id='"+returnrequestid+"'";*/
		String sql="update return_medicine_parent set status='1',returnbillnos='"+billno+"' where id='"+returnrequestid+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		res = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}
//lokesh
public ArrayList<Priscription> getnewDrReport(String fromdate, String todate,String drname) {
	ArrayList<Priscription> list= new ArrayList<Priscription>();
	PreparedStatement ps= null;
	try {
		fromdate= DateTimeUtils.getCommencingDate1(fromdate);
		todate = DateTimeUtils.getCommencingDate1(todate);
		StringBuffer sql= new StringBuffer();
		sql.append(" select a.id,a.invoiceid,b.prodname,a.charge,a.practitionerName,a.clientId,a.quantity,a.returnqty,b.expiry_date,d.reference, count(*),sum(a.quantity), sum(a.returnqty),b.batch_no,b.purchaseprice,b.saleprice from apm_medicine_charges a  ");
		sql.append(" inner join inventory_product b on b.id=  a.product_id  ");
		sql.append(" left join apm_medicine_bill c on c.id= a.invoiceid  ");
		sql.append(" left join apm_pharmacy_client d on d.id= a.pclientid  ");
		
		sql.append(" where a.commencing between '"+fromdate+"'  and '"+todate+"' ");
		if(!drname.equals("")){
			sql.append(" and a.practitionerName like '%"+drname+"%'");
		}
		sql.append(" group by b.prodname,a.practitionerName,a.charge  ");
		
		ps= connection.prepareStatement(sql.toString());
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			Priscription prisc= new Priscription();
			prisc.setId(rs.getInt(1));
			prisc.setBillno(rs.getString(2));
			prisc.setProductname(rs.getString(3));
			prisc.setCharges(rs.getDouble(4));
			prisc.setPractitionername(rs.getString(5));
			prisc.setQty(rs.getInt(7));
			prisc.setReturnqty(rs.getInt(8));
			prisc.setDate(DateTimeUtils.getCommencingDate1(rs.getString(9)));
			prisc.setCount(rs.getInt(11));
			/*int x= rs.getInt(rs.getInt(13));*/
			prisc.setSumqty(rs.getInt(12));
			prisc.setBatch_no(rs.getString(14));
			prisc.setPurchase_price(rs.getString(15));
			prisc.setSale_price(rs.getString(16));
			prisc.setTotalpayment(String.valueOf(Math.round(prisc.getSumqty()*prisc.getCharges())));
			prisc.setTotal(Math.round(prisc.getSumqty())*Double.parseDouble(prisc.getPurchase_price()));
			list.add(prisc);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

public ArrayList<Priscription> getnewMedicineDialyCountReport(String fromdate, String todate,String location ,String productname) {
	ArrayList<Priscription> list=new  ArrayList<Priscription>();
	PreparedStatement ps= null;
	fromdate=DateTimeUtils.getCommencingDate1(fromdate);
	todate= DateTimeUtils.getCommencingDate1(todate)+" 23:59:59";
	try {
		StringBuffer buffer= new StringBuffer();
		buffer.append(" select a.id,a.invoiceid,b.prodname,a.charge,a.practitionerName,a.clientId,a.quantity,a.returnqty,b.expiry_date,count(product_id), count(*),sum(a.quantity), sum(a.returnqty),b.batch_no,b.purchaseprice,b.saleprice,(stock) from apm_medicine_charges a  ");
		buffer.append("  inner join apm_medicine_bill on apm_medicine_bill.id=a.invoiceid  ");
		buffer.append(" inner join inventory_product b on b.id=  a.product_id  ");
		buffer.append(" where a.commencing between '"+fromdate+"'  and '"+todate+"' ");
		if(!location.equals("")){
			buffer.append("  and  apm_medicine_bill.location='"+location+"' ");
		}
		if(!productname.equals("")){
			buffer.append("  and  b.prodname like '%"+productname+"%' ");
		}
		buffer.append("   and a.returnbillno='0'  ");
		buffer.append(" group by product_id order by count(product_id) desc ;  ");
		buffer.append("   ");
		
		ps= connection.prepareStatement(buffer.toString());
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			Priscription prisc= new Priscription();
			prisc.setProductname(rs.getString(3));
			prisc.setMrp(rs.getString(4));
			prisc.setQty(rs.getInt(7));
			prisc.setReturnqty(rs.getInt(13));
			prisc.setDate(DateTimeUtils.getCommencingDate1(rs.getString(9)));
			prisc.setCount(rs.getInt(12));
			prisc.setBatch_no(rs.getString(14));
			prisc.setPurchase_price(rs.getString(15));
			prisc.setSale_price(rs.getString(16));
			prisc.setCheck_stock_available(rs.getString(17));
			prisc.setTotal(prisc.getCount()*(Double.parseDouble(prisc.getSale_price())));
			double x=(prisc.getCount()*(Double.parseDouble(prisc.getSale_price())));
			 
			 prisc.setTotalid(String.format("%.2f", x));
			list.add(prisc);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

public ArrayList<Priscription> getnewGSTReport(String fromdate, String todate,String paymode,int isreturn,String clinic,String reportby) {
	ArrayList<Priscription> list = new ArrayList<Priscription>();
	PreparedStatement ps= null;
	fromdate= DateTimeUtils.getCommencingDate1(fromdate);
	todate= DateTimeUtils.getCommencingDate1(todate);
	int five=0, tweelve=0,eighteen=0,zero=0;
	try {
		StringBuffer buffer= new StringBuffer();
		buffer.append("  select   date,id from apm_medicine_bill  where date between '"+fromdate+"' and '"+todate+"' and isreturn='"+isreturn+"' and deleted='0'  ");
		if(reportby.equals("1")){
			
		}else{
			buffer.append(" group by date ");
		}
		
		ps= connection.prepareStatement(buffer.toString());
		ResultSet rs= ps.executeQuery();
		
		
	while(rs.next()){
			Priscription prisc= new  Priscription();
			String date=rs.getString(1);
			String billno=rs.getString(2);
		if(clinic.equals("aureus")){
			prisc=getGstDataForAureus(date, date, isreturn, clinic);
		}else{
			/*String paymode1=getpaymodeInPayment(rs.getString(2));*/
			/*prisc= getBillsOfthedate(date, paymode,isreturn);*/
			if(paymode.equals("")){
				prisc=getQuickGSTData(date, date,isreturn,clinic,reportby,billno);
			}else{
				prisc=getQuickGSTDataByPaymode(date, date, paymode,isreturn,reportby,billno);
			}
			
			
			/*prisc= getgstdata1(date, date,paymode);*/
			prisc.setFromdate(DateTimeUtils.getCommencingDate1(date));
			prisc.setTodate(DateTimeUtils.getCommencingDate1(todate));
			prisc.setFivecgst((int)(prisc.getFiveprice()/2));
			prisc.setZerocgst((int)(prisc.getZeroprice()/2));
			prisc.setTweelvecgst((int)(prisc.getTweelveprice()/2));
			prisc.setEighteencgst((int)(prisc.getEighteenprice()/2));
			prisc.setEighttwocgst((int)(prisc.getEighttwoprice()/2));
			String e="";
			double x=prisc.getZeropricemed()-prisc.getZeroprice();
			e=String.format("%.2f", x);
			prisc.setZeronomrp((int)(Double.parseDouble(e)));
			x=prisc.getFivepricemed()-prisc.getFiveprice();
			e=String.format("%.2f", x);
			prisc.setFivenomrp((int)(Double.parseDouble(e)));
			x=prisc.getTweelvepricemed()-prisc.getTweelveprice();
			e=String.format("%.2f", x);
			prisc.setTweelvenomrp((int)(Double.parseDouble(e)));
			
			x=prisc.getEighttwopricemed()-prisc.getEighttwoprice();
			e=String.format("%.2f", x);
			prisc.setEighttwonomrp((int)(Double.parseDouble(e)));
			x=prisc.getEighteenpricemed()-prisc.getEighteenprice();
			e=String.format("%.2f", x);
			prisc.setEighteennomrp((int)(Double.parseDouble(e)));
		}
			list.add(prisc);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}
public String getpaymodeInPayment(String invoiceid,String payment){
	String res="";
	PreparedStatement ps= null;
	String sql="";
	try {
		if(payment.equals("Credit")){
			 sql=" select paymode from apm_medicine_payment where billno='"+invoiceid+"' order by id  limit 1";
		}else{
			sql=" select paymode from apm_medicine_payment where billno='"+invoiceid+"' order by id desc limit 1";
		}
		ps= connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			res=rs.getString(1);
			if(!payment.equals(res)){
				return null;
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}


public ArrayList<Priscription> getBillPayments(String billno) {
	ArrayList<Priscription> arrayList = new ArrayList<Priscription>();
	try {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select id,payment,paymode,date,userid from apm_medicine_payment ");
		buffer.append("where billno='"+billno+"'");
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			Priscription priscription = new  Priscription();
			priscription.setId(rs.getInt(1));
			priscription.setPayment(rs.getString(2));
			priscription.setPaymode(rs.getString(3));
			priscription.setDate(DateTimeUtils.getCommencingDate1(rs.getString(4)));
			priscription.setUserid(rs.getString(5));
			arrayList.add(priscription);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

public int updatePaymode(Priscription priscription) {
	int res =0;
	try {
		String sql="update apm_medicine_payment set paymode=?,paynote=? where id='"+priscription.getId()+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, priscription.getPaymode());
		preparedStatement.setString(2, priscription.getPaynote());
		res = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}


private Priscription getgstdata1(String fromdate, String todate,String paymode){
	Priscription prisc= new Priscription();
	PreparedStatement ps= null;
	int five=0, tweelve=0,eighteen=0,zero=0;
	double fiveprice=0, tweelveprice=0,eighteenprice=0,zeroprice=0;
	double fivepricemed=0, tweelvepricemed=0,eighteenpricemed=0,zeropricemed=0;
	try {
		
		StringBuffer buffer= new StringBuffer();
		buffer.append(" select b.vat,a.cgst,a.sgst,a.charge,a.invoiceid,a.quantity,a.returnqty from apm_medicine_charges a ");
		buffer.append(" inner join inventory_product  b on b.id= a.product_id ");
		buffer.append(" inner join apm_medicine_bill c on c.id= a.invoiceid ");
		buffer.append("  where c.isreturn='0' and a.returnbillno='0'and c.deleted='0'  and a.commencing between '"+fromdate+"' and '"+todate+"'");
		
		ps= connection.prepareStatement(buffer.toString());
		ResultSet rs= ps.executeQuery();
		
		
			
		while(rs.next()){
			String paymode1=getpaymodeInPayment(rs.getString(5),paymode);
			if(paymode1.equals(paymode)){
				double x= rs.getDouble(2)+rs.getDouble(3);
				double y= rs.getDouble(4)*(rs.getDouble(6)-rs.getDouble(7));
				double u= (x*100)/y;
				if(u==0.0){
					double c= prisc.getZeroprice()+(rs.getDouble(2)+rs.getDouble(3));
					String r=String.format("%.2f", c);
					prisc.setZeroprice(Double.parseDouble(r));
					double a=prisc.getZeropricemed()+y;
					r=String.format("%.2f", a);
					prisc.setZeropricemed(Double.parseDouble(r));
				}else if(u<=5.0){
					double c=prisc.getFiveprice()+(rs.getDouble(2)+rs.getDouble(3));
					String r=String.format("%.2f", c);
					prisc.setFiveprice(Double.parseDouble(r));
					double a=prisc.getFivepricemed()+y;
					r=String.format("%.2f", a);
					prisc.setFivepricemed(Double.parseDouble(r));
					 
				}else if(u<=12.0){
					double c =prisc.getTweelveprice()+(rs.getDouble(2)+rs.getDouble(3));
					String r=String.format("%.2f", c);
					prisc.setTweelveprice(Double.parseDouble(r));
					double a=prisc.getTweelvepricemed()+y;
					r=String.format("%.2f", a);
					prisc.setTweelvepricemed(Double.parseDouble(r));
				}else if(u<=18.0){
					double c =prisc.getEighteenprice()+(rs.getDouble(2)+rs.getDouble(3));
					String r=String.format("%.2f", c);
					prisc.setEighteenprice(Double.parseDouble(r));
					double a=prisc.getEighteenpricemed()+y;
					r=String.format("%.2f", a);
					prisc.setEighteenpricemed(Double.parseDouble(r));
				}else if(u<=28.0){
					double c =prisc.getEighttwoprice()+(rs.getDouble(2)+rs.getDouble(3));
					String r=String.format("%.2f", c);
					prisc.setEighttwoprice(Double.parseDouble(r));
					double a=prisc.getEighttwopricemed()+y;
					r=String.format("%.2f", a);
					prisc.setEighttwopricemed(Double.parseDouble(r));
				}
				double d= prisc.getZeropricemed()+prisc.getFivepricemed()+prisc.getTweelvepricemed()+prisc.getEighteenpricemed()+prisc.getEighttwopricemed();
				String r=String.format("%.2f", d);
				prisc.setTotal(Double.parseDouble(r));
				
			}
			}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return prisc;
}

private Priscription getBillsOfthedate(String date , String paymode,int isreturn){
	Priscription prisc = new Priscription();
	PreparedStatement ps= null;
	try {
		String sql="select id,discount,debit from apm_medicine_bill where date between '"+date+"' and '"+date+"'";
		ps= connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			Priscription p1= new Priscription();
			p1=getgstdata2(rs.getString(1),paymode,rs.getDouble(2),(rs.getDouble(2)+rs.getDouble(3)),isreturn);
		/*	prisc.setZeroprice(Double.parseDouble(String.format("%.2f", p1.getZeroprice()+prisc.getZeroprice())));*/
			prisc.setZeropricemed(Double.parseDouble(String.format("%.2f",p1.getZeropricemed()+prisc.getZeropricemed())));
			/*prisc.setFiveprice(Double.parseDouble(String.format("%.2f",p1.getFiveprice()+prisc.getFiveprice())));*/
			prisc.setFivepricemed(Double.parseDouble(String.format("%.2f",p1.getFivepricemed()+prisc.getFivepricemed())));
			/*prisc.setTweelveprice(Double.parseDouble(String.format("%.2f",p1.getTweelveprice()+prisc.getTweelveprice())));*/
			prisc.setTweelvepricemed(Double.parseDouble(String.format("%.2f",prisc.getTweelvepricemed()+p1.getTweelvepricemed())));
			/*prisc.setEighteenprice(Double.parseDouble(String.format("%.2f",prisc.getEighteenprice()+p1.getEighteenprice())));*/
			prisc.setEighteenpricemed(Double.parseDouble(String.format("%.2f",prisc.getEighteenpricemed()+p1.getEighteenpricemed())));
			/*prisc.setEighttwoprice(Double.parseDouble(String.format("%.2f",prisc.getEighttwoprice()+p1.getEighttwoprice())));*/
			prisc.setEighttwopricemed(Double.parseDouble(String.format("%.2f",prisc.getEighttwopricemed()+p1.getEighttwopricemed())));
			prisc.setTotal(Double.parseDouble(String.format("%.2f",p1.getTotal()+prisc.getTotal())));
			/*prisc.setTweelveprice(Double.parseDouble(String.format("%.2f",(prisc.getTweelvepricemed()-(prisc.getTweelvepricemed()*(100/112))))));
*/			prisc.setEighteenprice(Double.parseDouble(String.format("%.2f",prisc.getEighteenpricemed()-(prisc.getEighteenpricemed()*(100.00/118.00)))));
			prisc.setEighttwoprice(Double.parseDouble(String.format("%.2f",prisc.getEighttwopricemed()-(prisc.getEighttwopricemed()*(100.00/128.00)))));
			prisc.setFiveprice(Double.parseDouble(String.format("%.2f",prisc.getFivepricemed()-(prisc.getFivepricemed()*(100.00/105.00)))));
			prisc.setTweelveprice(Double.parseDouble(String.format("%.2f",prisc.getTweelvepricemed()-(prisc.getTweelvepricemed()*(100.00/112.00)))));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return prisc;
}

private Priscription getgstdata2(String invoceid,String paymode,double disc,double totalinvcamt,int isreturn){
	Priscription prisc= new Priscription();
	PreparedStatement ps= null;
	int five=0, tweelve=0,eighteen=0,zero=0;
	double fiveprice=0, tweelveprice=0,eighteenprice=0,zeroprice=0;
	double fivepricemed=0, tweelvepricemed=0,eighteenpricemed=0,zeropricemed=0;
	try {
		
		StringBuffer buffer= new StringBuffer();
		buffer.append(" select b.vat,a.cgst,a.sgst,a.charge,a.invoiceid,a.quantity,a.returnqty from apm_medicine_charges a ");
		buffer.append(" inner join inventory_product  b on b.id= a.product_id ");
		buffer.append(" inner join apm_medicine_bill c on c.id= a.invoiceid ");
		if(isreturn!=0){
			buffer.append("  where c.isreturn='1'  and c.deleted='0' and  a.invoiceid='"+invoceid+"'");
	
		}else{
		buffer.append("  where c.isreturn='0' and a.returnbillno='0' and c.deleted='0' and  a.invoiceid='"+invoceid+"'");
		}
		ps= connection.prepareStatement(buffer.toString());
		ResultSet rs= ps.executeQuery();
		
		
			
		while(rs.next()){
			String paymode1=getpaymodeInPayment(rs.getString(5),paymode);
			if(paymode1==null){
				continue;
			}
			double m1=rs.getDouble(4);
			double m2=rs.getDouble(6);
			double a1=rs.getDouble(2);
			double a2=rs.getDouble(3);
			if(paymode1.equals(paymode)){
				double shareinbill=(m1*m2)/totalinvcamt;
				double shareindisc= shareinbill*disc;
				double x= a1+a2;
				//y is qty * charge on product
				double y= (m1*m2)-shareindisc;
				double u= (x*100)/y;
				if(u==0.0){
					String r="";
					/*double c= prisc.getZeroprice()+(rs.getDouble(2)+rs.getDouble(3));
					String r=String.format("%.2f", c);
					prisc.setZeroprice(Double.parseDouble(r));*/
					double a=prisc.getZeropricemed()+y;
					r=String.format("%.2f", a);
					prisc.setZeropricemed(Double.parseDouble(r));
				}else if(u<=5.0){
					String r="";
					/*double c=prisc.getFiveprice()+((rs.getDouble(2)+rs.getDouble(3))-((5*shareindisc)/100));
					String r=String.format("%.2f", c);
					prisc.setFiveprice(Double.parseDouble(r));*/
					double a=prisc.getFivepricemed()+y;
					r=String.format("%.2f", a);
					prisc.setFivepricemed(Double.parseDouble(r));
					 
				}else if(u<=12.0){
					String r="";
					/*double c =prisc.getTweelveprice()+(rs.getDouble(2)+rs.getDouble(3))-((12*shareindisc)/100);
					String r=String.format("%.2f", c);
					prisc.setTweelveprice(Double.parseDouble(r));*/
					double a=prisc.getTweelvepricemed()+y;
					r=String.format("%.2f", a);
					prisc.setTweelvepricemed(Double.parseDouble(r));
				}else if(u<=18.0){
					String r="";
					/*double c =prisc.getEighteenprice()+((rs.getDouble(2)+rs.getDouble(3))-((18*shareindisc)/100));
					String r=String.format("%.2f", c);
					prisc.setEighteenprice(Double.parseDouble(r));*/
					double a=prisc.getEighteenpricemed()+y;
					r=String.format("%.2f", a);
					prisc.setEighteenpricemed(Double.parseDouble(r));
				}else if(u<=28.0){
					String r="";
					/*double c =prisc.getEighttwoprice()+((rs.getDouble(2)+rs.getDouble(3))-((28*shareindisc)/100));
					String r=String.format("%.2f", c);
					prisc.setEighttwoprice(Double.parseDouble(r));*/
					double a=prisc.getEighttwopricemed()+y;
					r=String.format("%.2f", a);
					prisc.setEighttwopricemed(Double.parseDouble(r));
				}
				
			}
			}
		double d= prisc.getZeropricemed()+prisc.getFivepricemed()+prisc.getTweelvepricemed()+prisc.getEighteenpricemed()+prisc.getEighttwopricemed();
		String r=String.format("%.2f", d);
		prisc.setTotal(Double.parseDouble(r));
			
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return prisc;
}

public int getPharmacyBillSeqNo(String location) {
	int res =0;
	try {
		String sql="select max(pharseqno) from apm_medicine_bill where location='"+location+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			res = rs.getInt(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public int updatePharmacySeqNo(int billno, int billseqno) {
	PreparedStatement preparedStatement = null;
	int result = 0;
	try{
		String sql ="";
		sql = "update apm_medicine_bill set pharseqno='"+billseqno+"' where id='"+billno+"' ";
		preparedStatement = connection.prepareStatement(sql);
		result = preparedStatement.executeUpdate();
	}catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public int getPharmacyPaymentSeqNo(String location) {
	int res =0;
	try {
		String sql="select max(paymentseqno) from apm_medicine_payment where location='"+location+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			res = rs.getInt(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public int updatePharmacyPaymentSeqNo(int paymentid, int paymentseqno) {
	PreparedStatement preparedStatement = null;
	int result = 0;
	try{
		String sql ="";
		sql = "update apm_medicine_payment set paymentseqno='"+paymentseqno+"' where id='"+paymentid+"' ";
		preparedStatement = connection.prepareStatement(sql);
		result = preparedStatement.executeUpdate();
	}catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public Priscription setpharmacy_backdate_print_access(String status, String userid) {
	PreparedStatement ps= null;
	PreparedStatement ps1= null;
	Priscription pr= new Priscription();
	try {
		String sql=" update apm_pharmacy_user set pharm_print_backdate='"+status+"' where id='"+userid+"'";
		ps= connection.prepareStatement(sql);
		int x= ps.executeUpdate();
		String sql1="select id,pharm_print_backdate from apm_pharmacy_user where id="+userid;
		ps1= connection.prepareStatement(sql1);
		ResultSet rs= ps1.executeQuery();
		while(rs.next()){
		 pr.setId(rs.getInt(1));
		 pr.setPharm_print_backdate(rs.getBoolean(2));
		}
				
	} catch (Exception e) {
		e.printStackTrace();
	}
	return pr;
}

public ArrayList<Priscription> gethsnwiseGSTReport(String fromdate, String todate) {
	ArrayList<Priscription> list= new ArrayList<Priscription>();
	PreparedStatement ps=null;
	fromdate= DateTimeUtils.getCommencingDate1(fromdate);
	todate= DateTimeUtils.getCommencingDate1(todate);
	try {
		StringBuffer buffer= new StringBuffer();
		buffer.append(" select apm_medicine_charges.user,apm_medicine_bill.date,invoiceid,debit,discount,apm_medicine_bill.vat,charge,quantity,sum(apm_medicine_charges.cgst)  ");
		buffer.append(" ,sum(apm_medicine_charges.sgst),sum(apm_medicine_charges.charge*apm_medicine_charges.quantity),prodname,batch_no,hsnno,expiry_date,apm_medicine_bill.deleted,count(apm_medicine_charges.product_id)  ");
		buffer.append(" from apm_medicine_charges  ");
		buffer.append(" left join inventory_product on apm_medicine_charges.product_id = inventory_product.id  ");
		buffer.append(" inner join apm_medicine_bill on apm_medicine_charges.invoiceid=apm_medicine_bill.id  ");
		buffer.append(" where apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' and apm_medicine_bill.isreturn='0'  ");
		buffer.append(" group by inventory_product.hsnno  ");
		
		ps= connection.prepareStatement(buffer.toString());
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			Priscription prisc= new Priscription();
			/*prisc.setHsnno(rs.getString(14));
			prisc.setCgst(rs.getString(9));
			prisc.setSgst(rs.getString(10));
			prisc.setCount(rs.getInt(17));
			prisc.setTotal(rs.getInt(11));
			*/
			prisc.setHsnno(rs.getString(14));
			prisc.setCgst(String.format("%.2f", rs.getDouble(9)));
			prisc.setSgst(String.format("%.2f", rs.getDouble(10)));
			prisc.setCount(rs.getInt(17));
			prisc.setTotal(rs.getInt(11));
			list.add(prisc);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

public double getPharClientBalance(String ispharmacy, String clientid) {
	double res =0;
	try {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select sum(balance) from apm_medicine_bill ");
		if(ispharmacy.equals("1")){
			buffer.append("where pclientid='"+clientid+"' ");
		}else{
			buffer.append("where clientid='"+clientid+"' ");
		}
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

public int returnByNurseChildTemp(Priscription priscription, String sessionid) {
	int result=0;
	try {
		String sql="insert into return_medicine_nursing_temp (medicine_id,medicine_name,qty,chargeid,prodid,loginuserinfo) values (?,?,?,?,?,?)";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1, priscription.getMedicineid());
		ps.setString(2, priscription.getProductname());
		ps.setString(3, ""+priscription.getSaleqty());
		ps.setString(4, ""+priscription.getId());
		ps.setString(5, priscription.getProduct_id());
		ps.setString(6, sessionid);
		result=ps.executeUpdate();
		if(result>0){
			ResultSet rs=ps.getGeneratedKeys();
			while(rs.next()){
				result=rs.getInt(1);
			}
		}
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return result;
}

public ArrayList<Priscription> getTempMedicineCharge(String sessionid) {
	ArrayList<Priscription> arrayList = new ArrayList<Priscription>();
	try {
		String tempid="";
		StringBuffer buffer = new StringBuffer();
		buffer.append("select distinct invoiceid from return_medicine_nursing_temp ");
		buffer.append("inner join apm_medicine_charges on apm_medicine_charges.id = return_medicine_nursing_temp.chargeid ");
		buffer.append("where loginuserinfo ='"+sessionid+"' ");
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs =preparedStatement.executeQuery();
		while (rs.next()) {
			if(tempid.equals("")){
				tempid = ""+rs.getInt(1);
			}else{
				tempid = tempid+","+rs.getInt(1);
			}
		}
		if(!tempid.equals("")){
			StringBuffer buffer2 = new StringBuffer();
			buffer2.append("select paymode FROM apm_medicine_payment ");
			buffer2.append("where billno in ("+tempid+") and id IN  ");
			buffer2.append("(select MAX(id) FROM apm_medicine_payment group by billno)");
			buffer2.append("group by paymode ");
			PreparedStatement  preparedStatement2 = connection.prepareStatement(buffer2.toString());
			ResultSet rs1 = preparedStatement2.executeQuery();
			while (rs1.next()) {
				Priscription priscription = new Priscription();
				priscription.setTempids(tempid);
				priscription.setPaymode(rs1.getString(1));
				arrayList.add(priscription);
			}
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

public ArrayList<Priscription> getReturnMedicineTempList(String sessionid, Priscription priscription) {
	ArrayList<Priscription> arrayList = new ArrayList<Priscription>();
	try {
		String tempid="";
		StringBuffer buffer2 = new StringBuffer();
		buffer2.append("select billno FROM apm_medicine_payment ");
		buffer2.append("where billno in ("+priscription.getTempids()+") and id in  ");
		buffer2.append("(select MAX(id) FROM apm_medicine_payment group by billno) and paymode='"+priscription.getPaymode()+"' ");
		PreparedStatement  preparedStatement2 = connection.prepareStatement(buffer2.toString());
		ResultSet rs1 = preparedStatement2.executeQuery();
		while (rs1.next()) {
			if(tempid.equals("")){
				tempid = ""+rs1.getInt(1);
			}else{
				tempid = tempid+","+rs1.getInt(1);
			}
		}
		if(!tempid.equals("")){
			StringBuffer buffer = new StringBuffer();
			buffer.append("select medicine_id,medicine_name,qty,chargeid,prodid from return_medicine_nursing_temp ");
			buffer.append("inner join apm_medicine_charges on apm_medicine_charges.id = return_medicine_nursing_temp.chargeid ");
			buffer.append("where loginuserinfo ='"+sessionid+"' and invoiceid in ("+tempid+")  ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Priscription priscription2 = new Priscription();
				priscription2.setMedicineid(rs.getString(1));
				priscription2.setProductname(rs.getString(2));
				priscription2.setSaleqty(rs.getInt(3));
				priscription2.setId(rs.getInt(4));
				priscription2.setProduct_id(rs.getString(5));
				arrayList.add(priscription2);
			}
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

public int deleteTempNurcingMedicine(String sessionid) {
	int res =0;
	try {
		String sql="delete from return_medicine_nursing_temp where loginuserinfo=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, sessionid);
		res = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}
private Priscription getQuickGSTData(String fromdate, String todate,int isreturn,String clinicid,String reportby,String billno){
	Priscription priscription= new Priscription();
	PreparedStatement ps= null;
	StringBuffer buffer= new StringBuffer();
	buffer.append("   select sum(charge*quantity)-sum(apm_medicine_charges.discount_share),inventory_product.vat,invoiceid from apm_medicine_bill   ");
	buffer.append("   inner join apm_medicine_charges on apm_medicine_bill.id=apm_medicine_charges.invoiceid   ");
	buffer.append("   inner join inventory_product on apm_medicine_charges.product_id =inventory_product.id   ");
	buffer.append("    where apm_medicine_bill.date  between '"+fromdate+"' and '"+todate+"'   ");
	if(isreturn==1){
		buffer.append("   and   apm_medicine_bill.isreturn ='1'  and apm_medicine_bill.deleted='0'  ");
		
	}else{
		buffer.append("   and   apm_medicine_bill.isreturn='0' and apm_medicine_charges.returnbillno='0' and apm_medicine_bill.deleted='0'  ");
		
	}
	if(reportby.equals("1")){
		buffer.append("  and apm_medicine_charges.invoiceid='"+billno+"'  ");
	}
	buffer.append("   group by inventory_product.vat    ");
	
	try {
	
		ps= connection.prepareStatement(buffer.toString());
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			if(rs.getInt(2)==0){
				priscription.setZeropricemed(Math.round(rs.getDouble(1))+priscription.getZeropricemed());
			}
			if(rs.getInt(2)==5){
				priscription.setFivepricemed(Math.round(rs.getDouble(1))+priscription.getFivepricemed());
			}
			if(rs.getInt(2)==12){
				priscription.setTweelvepricemed(Math.round(rs.getDouble(1))+priscription.getTweelvepricemed());
			}
			if(rs.getInt(2)==18){
				priscription.setEighteenpricemed(Math.round(rs.getDouble(1))+priscription.getEighteenpricemed());
			}
			if(rs.getInt(2)==28){
				priscription.setEighttwopricemed(Math.round(rs.getDouble(1))+priscription.getEighttwopricemed());
			}
			
			priscription.setBillno(""+rs.getInt(3));
			
		}
		
		double d= priscription.getZeropricemed()+priscription.getFivepricemed()+priscription.getTweelvepricemed()+priscription.getEighteenpricemed()+priscription.getEighttwopricemed();
		String r=String.format("%.2f", d);
		priscription.setTotal(Double.parseDouble(r));
		
		/*adjustment logic*/
		int debittotal=getTotalDebitofPharmacy(String.valueOf(isreturn), fromdate, todate, "", "",reportby,billno);
		int productttotal=(int)priscription.getTotal();
//		System.out.println(""+debittotal+"   "+productttotal);
		int difference=debittotal-productttotal;
			
			
		if(priscription.getZeropricemed()!=0.0){
			double x=(priscription.getZeropricemed()/productttotal)*100.0;
			double distribution1=((x/100.00)*difference);
			priscription.setZeropricemed(Math.round(priscription.getZeropricemed()+distribution1));
		}
		
		if(priscription.getFivepricemed()!=0.0){
			double x=(priscription.getFivepricemed()/productttotal)*100.0;
			double distribution1=((x/100.00)*difference);
			priscription.setFivepricemed(Math.round(priscription.getFivepricemed()+distribution1));
		}
		
		if(priscription.getEighteenpricemed()!=0.0){
			double x=(priscription.getEighteenpricemed()/productttotal)*100.0;
			double distribution1=((x/100.00)*difference);
			priscription.setEighteenpricemed(Math.round(priscription.getEighteenpricemed()+distribution1));
		}
		if(priscription.getTweelvepricemed()!=0.0){
			double x=(priscription.getTweelvepricemed()/productttotal)*100.0;
			double distribution1=((x/100.00)*difference);
			priscription.setTweelvepricemed(Math.round(priscription.getTweelvepricemed()+distribution1));
		}
		
		if(priscription.getEighttwopricemed()!=0.0){
			double x=(priscription.getEighttwopricemed()/productttotal)*100.0;
			double distribution1=((x/100.00)*difference);
			priscription.setEighttwopricemed(Math.round(priscription.getEighttwopricemed()+distribution1));
		}
		
			
		
		
		priscription.setEighteenprice(Double.parseDouble(String.format("%.2f",priscription.getEighteenpricemed()-(priscription.getEighteenpricemed()*(100.00/118.00)))));
		priscription.setEighttwoprice(Double.parseDouble(String.format("%.2f",priscription.getEighttwopricemed()-(priscription.getEighttwopricemed()*(100.00/128.00)))));
		priscription.setFiveprice(Double.parseDouble(String.format("%.2f",priscription.getFivepricemed()-(priscription.getFivepricemed()*(100.00/105.00)))));
		priscription.setTweelveprice(Double.parseDouble(String.format("%.2f",priscription.getTweelvepricemed()-(priscription.getTweelvepricemed()*(100.00/112.00)))));

		double d1= priscription.getZeropricemed()+priscription.getFivepricemed()+priscription.getTweelvepricemed()+priscription.getEighteenpricemed()+priscription.getEighttwopricemed();
		String r1=String.format("%.2f", d1);
		priscription.setTotal(Double.parseDouble(r1));
	} catch (Exception e) {
		e.printStackTrace();
	}
	return priscription;
}

public boolean checkPatientAvailableInLog(int tempclientid, int ispharmacypatient, String loginsessionid, int isfordiscount) {
	boolean flag = false;
	try {
		String sql="select id from temp_pharmacy_sale where clientid=? and ispharmacy=? and loginsessionid=? and isfordiscount=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, ""+tempclientid);
		preparedStatement.setString(2, ""+ispharmacypatient);
		preparedStatement.setString(3, loginsessionid);
		preparedStatement.setString(4, ""+isfordiscount);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			flag = true;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return flag;
}

public int insertTempPharSaleSession(int tempclientid, int ispharmacypatient, String loginsessionid, int isfordiscount, String discsmt, String distype, String discinper) {
	int result=0;
	try {
		
		String sql="insert into temp_pharmacy_sale (clientid, ispharmacy, loginsessionid,isfordiscount,discsmt,distype,discinper) values (?,?,?,?,?,?,?)";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1, ""+tempclientid);
		ps.setString(2, ""+ispharmacypatient);
		ps.setString(3, loginsessionid);
		ps.setString(4, ""+isfordiscount);
		ps.setString(5, discsmt);
		ps.setString(6, distype);
		ps.setString(7, discinper);
		result=ps.executeUpdate();
		
		if(result>0){
			ResultSet rs=ps.getGeneratedKeys();
			
			while(rs.next()){
				 result=rs.getInt(1);
			}
		}
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return result;
}

public boolean checkTempIdInPharmacyBill(int tempsessionid) {
	boolean flag = false;
	try {
		String sql="select id from apm_medicine_bill where tempsessionid=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, ""+tempsessionid);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			flag = true;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return flag;
}

public int deleteTempPharmacySession(String loginsessionid) {
	int result=0;
	try {
		String sql="delete from temp_pharmacy_sale where loginsessionid=?";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1, loginsessionid);
		result =ps.executeUpdate();
	} catch (Exception e) {

		e.printStackTrace();
	}
	return result;
}

public CompleteAppointment getTempPharDiscountData(int tempclientid, int ispharmacypatient, String loginsessionid,
		int isfordiscount) {
	CompleteAppointment completeAppointment = new CompleteAppointment();
	try {
		String sql="select discsmt,distype,discinper from temp_pharmacy_sale where clientid=? and ispharmacy=? and loginsessionid=? and isfordiscount=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, ""+tempclientid);
		preparedStatement.setString(2, ""+ispharmacypatient);
		preparedStatement.setString(3, loginsessionid);
		preparedStatement.setString(4, ""+isfordiscount);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			completeAppointment.setActualdiscount(DateTimeUtils.changeFormat(rs.getDouble(1)));
			completeAppointment.setDisc_type(""+rs.getInt(2));
			completeAppointment.setDiscperc(DateTimeUtils.changeFormat(rs.getDouble(3)));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return completeAppointment;
}

	public Product checkopeningstockexistData(String productid, String date) {
		PreparedStatement preparedStatement = null;
		Product product = new Product();
		String sql = "select id, prodid, commencing, stock, isconsume, indentqty, purchaseqty from medicine_opening_stock where commencing='"
				+ date + "' and prodid=" + productid + " ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				product.setId(rs.getInt(1));
				product.setIndentqty(rs.getInt(6));
				product.setPurchaseqty(rs.getInt(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	public int updateIndentQtyInOpening(Product openingproduct, double indentqty) {
		int res =0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("update medicine_opening_stock set indentqty=? where id=? ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			preparedStatement.setDouble(1, indentqty);
			preparedStatement.setString(2, ""+openingproduct.getId());
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	
	private Priscription getQuickGSTDataByPaymode(String fromdate, String todate,String paymode,int isreturn,String reportby,String billno){
		Priscription priscription= new Priscription();
		PreparedStatement ps= null;
		StringBuffer buffer= new StringBuffer();
		buffer.append("   select sum(charge*quantity)-sum(apm_medicine_charges.discount_share),inventory_product.vat from apm_medicine_bill   ");
		buffer.append("   inner join apm_medicine_charges on apm_medicine_bill.id=apm_medicine_charges.invoiceid   ");
		buffer.append("      ");
		buffer.append("   inner join inventory_product on apm_medicine_charges.product_id =inventory_product.id   ");
		
		buffer.append("    where apm_medicine_bill.date  between '"+fromdate+"' and '"+todate+"'   ");
		if(isreturn==1){
			buffer.append("   and   apm_medicine_bill.isreturn='1'  and apm_medicine_bill.initial_paymode='"+paymode+"'  ");
		}else{
			buffer.append("   and   apm_medicine_bill.isreturn='0' and apm_medicine_charges.returnbillno='0' and apm_medicine_bill.deleted='0' and apm_medicine_bill.initial_paymode='"+paymode+"'  ");
			}
		if(reportby.equals("1")){
			buffer.append("  and apm_medicine_charges.invoiceid='"+billno+"'  ");
		}
		
		
		buffer.append("   group by inventory_product.vat    ");
		
		try {
		
			ps= connection.prepareStatement(buffer.toString());
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				if(rs.getInt(2)==0){
					priscription.setZeropricemed(Math.round(rs.getDouble(1)));
				}
				if(rs.getInt(2)==5){
					priscription.setFivepricemed(Math.round(rs.getDouble(1)));
				}
				if(rs.getInt(2)==12){
					priscription.setTweelvepricemed(Math.round(rs.getDouble(1)));
				}
				if(rs.getInt(2)==18){
					priscription.setEighteenpricemed(Math.round(rs.getDouble(1)));
				}
				if(rs.getInt(2)==28){
					priscription.setEighttwopricemed(Math.round(rs.getDouble(1)));
				}
				
				priscription.setBillno(billno);
			}
/*				
 */
			double d= priscription.getZeropricemed()+priscription.getFivepricemed()+priscription.getTweelvepricemed()+priscription.getEighteenpricemed()+priscription.getEighttwopricemed();
			String r=String.format("%.2f", d);
			priscription.setTotal(Double.parseDouble(r));
				/*adjustment logic*/
				int debittotal=getTotalDebitofPharmacy(String.valueOf(isreturn), fromdate, todate, "", paymode,reportby,billno);
				int productttotal=(int)priscription.getTotal();
//				System.out.println(""+debittotal+"   "+productttotal);
				int difference=debittotal-productttotal;
				int parts=2;
				if(priscription.getZeropricemed()!=0.0){
					double x=(priscription.getZeropricemed()/productttotal)*100.0;
					double distribution1=((x/100.00)*difference);
					priscription.setZeropricemed(Math.round(priscription.getZeropricemed()+distribution1));
				}
				
				if(priscription.getFivepricemed()!=0.0){
					double x=(priscription.getFivepricemed()/productttotal)*100.0;
					double distribution1=((x/100.00)*difference);
					priscription.setFivepricemed(Math.round(priscription.getFivepricemed()+distribution1));
				}
				
				if(priscription.getEighteenpricemed()!=0.0){
					double x=(priscription.getEighteenpricemed()/productttotal)*100.0;
					double distribution1=((x/100.00)*difference);
					priscription.setEighteenpricemed(Math.round(priscription.getEighteenpricemed()+distribution1));
				}
				if(priscription.getTweelvepricemed()!=0.0){
					double x=(priscription.getTweelvepricemed()/productttotal)*100.0;
					double distribution1=((x/100.00)*difference);
					priscription.setTweelvepricemed(Math.round(priscription.getTweelvepricemed()+distribution1));
				}
				
				if(priscription.getEighttwopricemed()!=0.0){
					double x=(priscription.getEighttwopricemed()/productttotal)*100.0;
					double distribution1=((x/100.00)*difference);
					priscription.setEighttwopricemed(Math.round(priscription.getEighttwopricemed()+distribution1));
				}
				
				
				
				priscription.setEighteenprice(Double.parseDouble(String.format("%.2f",priscription.getEighteenpricemed()-(priscription.getEighteenpricemed()*(100.00/118.00)))));
				priscription.setEighttwoprice(Double.parseDouble(String.format("%.2f",priscription.getEighttwopricemed()-(priscription.getEighttwopricemed()*(100.00/128.00)))));
				priscription.setFiveprice(Double.parseDouble(String.format("%.2f",priscription.getFivepricemed()-(priscription.getFivepricemed()*(100.00/105.00)))));
				priscription.setTweelveprice(Double.parseDouble(String.format("%.2f",priscription.getTweelvepricemed()-(priscription.getTweelvepricemed()*(100.00/112.00)))));

				double d1= priscription.getZeropricemed()+priscription.getFivepricemed()+priscription.getTweelvepricemed()+priscription.getEighteenpricemed()+priscription.getEighttwopricemed();
				String r1=String.format("%.2f", d1);
				priscription.setTotal(Double.parseDouble(r1));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return priscription;
	}

	public ArrayList<Priscription> getReturnAllMedicineByNurceList(String id, String clientid, String ispharmacy, String loginsessionid, String datetime) {
		ArrayList<Priscription> arrayList = new ArrayList<Priscription>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select qty,return_medicine_child.id,chargeid from return_medicine_parent ");
			buffer.append("inner join return_medicine_child on return_medicine_parent.id = return_medicine_child.parentid ");
			buffer.append("inner join apm_medicine_charges on return_medicine_child.chargeid = apm_medicine_charges.id ");
			buffer.append("where return_medicine_parent.id='"+id+"' ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Priscription priscription= getMedicineChargesbyid(rs.getInt(3),rs.getInt(1));
				boolean flag = checkReturnProductInTemp(rs.getString(3), clientid, ispharmacy, loginsessionid);
				boolean flag1 = checkBillCancelFromChargeId(rs.getInt(3));
				if(!flag1){
					if(!flag){
						priscription.setChargeid(rs.getString(3));
						
						if(priscription.getQty()>(priscription.getSaleqty()-priscription.getReturnqty())){
							priscription.setReturnqty(0);
							int res = insertIntoPharReturnTemp(rs.getString(3), clientid, ispharmacy, loginsessionid, datetime, "0");
							priscription.setReturnchargeid(res);
							if(res>0){
								arrayList.add(priscription);
							}
						}else{
							priscription.setReturnqty(priscription.getQty());
							int res = insertIntoPharReturnTemp(rs.getString(3), clientid, ispharmacy, loginsessionid, datetime, ""+priscription.getQty());
							priscription.setReturnchargeid(res);
							if(res>0){
								arrayList.add(priscription);
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	
private boolean checkBillCancelFromChargeId(int int1) {
		boolean flag = true;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select deleted from apm_medicine_bill ");
			buffer.append("inner join apm_medicine_charges on apm_medicine_charges.invoiceid=apm_medicine_bill.id ");
			buffer.append("where apm_medicine_charges.id='"+int1+"'");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				flag = rs.getBoolean(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

public double getDecimalBillAmount(String billid){
	double ammount=0;
	PreparedStatement ps= null;
	try {
		String sql=" select sum(charge*quantity)-sum(apm_medicine_charges.discount_share) from  apm_medicine_charges where  invoiceid='"+billid+"'";
		ps= connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			ammount=rs.getDouble(1);
		}
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	return ammount;
}

public Priscription getBifurcationOfPharmacyBill(String billid) {
	Priscription priscription= new Priscription();
	PreparedStatement ps= null;
	try {
		StringBuffer buffer= new StringBuffer();
		buffer.append(" select sum(tgstamt),gstper,sum(charge*quantity)-sum(discount_share) from apm_medicine_charges where invoiceid='"+billid+"'  group by gstper ");
		ps= connection.prepareStatement(buffer.toString());
		
		ResultSet rs= ps.executeQuery();
		double totalgst=0,totalamt=0;
		while(rs.next()){
			int gst=rs.getInt(2);
			double ammount = Math.round(rs.getDouble(1)*100.0)/100.0;
			double ammountttl = Math.round(rs.getDouble(3)*100.0)/100.0;
			if(gst==0){
				priscription.setZerocgst(ammount);
				priscription.setZeropricemed(Math.round(rs.getDouble(3)*100.0)/100.0);
			}else if(gst==5){
				priscription.setFivecgst(ammount);
				priscription.setFivepricemed(Math.round(rs.getDouble(3)*100.0)/100.0);
			}else if(gst==12){
				priscription.setTweelvecgst(ammount);
				priscription.setTweelvepricemed(Math.round(rs.getDouble(3)*100.0)/100.0);
			}else if(gst==18){
				priscription.setEighteencgst(ammount);
				priscription.setEighteenpricemed(Math.round(rs.getDouble(3)*100.0)/100.0);
			}else if(gst==28){
				priscription.setEighttwocgst(ammount);
				priscription.setEighttwopricemed(Math.round(rs.getDouble(3)*100.0)/100.0);
			}
			priscription.setTotalgst(totalgst+ammount);
			totalgst=priscription.getTotalgst();
			
			priscription.setTotalamt(totalamt+ammountttl);
			totalamt=priscription.getTotalamt();
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return priscription;
}

public double getPaymentAgainstCredit(String fromdate, String todate, String paymode) {
	StringBuffer buffer= new StringBuffer();
	PreparedStatement ps= null;
	double res=0;
	try {
		buffer.append(" select apm_medicine_bill.id from apm_medicine_bill   ");
		buffer.append("  inner join apm_medicine_payment on apm_medicine_bill.id=apm_medicine_payment.billno ");
		buffer.append(" where apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' ");
		buffer.append("  and (apm_medicine_bill.tpid=0 or apm_medicine_bill.tpid is NULL) and apm_medicine_bill.isreturn=0 ");
		buffer.append("  and paymode='Credit' ");
		
		ps= connection.prepareStatement(buffer.toString());
		double x=0;
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			
			StringBuffer buffer1= new StringBuffer();
			buffer1.append(" select sum(payment) from  apm_medicine_payment  ");
			buffer1.append(" where billno='"+rs.getInt(1)+"' and paymode='"+paymode+"'  ");
			buffer1.append("   ");
			buffer1.append("   ");
			PreparedStatement p2= connection.prepareStatement(buffer1.toString());
			ResultSet rs1= p2.executeQuery();
			while(rs1.next()){
				res= rs1.getDouble(1)+x;
				x=res;
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public int updateBillFinalPaymode(String billno, String paymode) {
	int result=0;
	try {
		 String sql1="update apm_medicine_bill set final_paymode='"+paymode+"' where id="+billno+"  "; 
	     PreparedStatement ps1=connection.prepareStatement(sql1);
	     result=  ps1.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public boolean checkBillCreditStatus(String billno) {
	boolean flag = false;
	try {
		String sql ="select * from apm_medicine_payment where billno='"+billno+"' and paymode='Credit'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			flag = true;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return flag;
}

public int updateGstPaymentFlag(String billno) {
	int result=0;
	try {
		 String sql1="update apm_medicine_payment set paymentflag='1' where billno="+billno+"  "; 
	     PreparedStatement ps1=connection.prepareStatement(sql1);
	     result=  ps1.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public ArrayList<Priscription> getAllBillListofClientNew(String clientid, String flag, String fromdate, String todate, int orderby) {
	ArrayList<Priscription> list=new ArrayList<Priscription>();
	try {
		StringBuffer buffer=new StringBuffer();
		buffer.append("SELECT apm_medicine_bill.id,apm_medicine_bill.date,apm_medicine_bill.refundid,apm_medicine_bill.isreturn from apm_medicine_bill ");
		buffer.append("inner join apm_medicine_payment on apm_medicine_bill.id=apm_medicine_payment.billno where ");
		if(flag.equals("1")){
			buffer.append("apm_medicine_bill.pclientid ="+clientid+" ");
		} else {
			buffer.append("apm_medicine_bill.clientid ="+clientid+" ");
		}
		if(fromdate!=null && todate!=null){
			buffer.append("and apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' ");
		}
		buffer.append("group by apm_medicine_bill.id  ");
		if(orderby>0){
	    	 buffer.append("order by apm_medicine_bill.id asc");
	    }else{
	    	 buffer.append("order by apm_medicine_bill.id desc");
	    }
		
		PreparedStatement ps=connection.prepareStatement(buffer.toString());
		ResultSet rs=ps.executeQuery();
		double totalbill=0;
		 double totaldisc=0;
		 double totalreturn=0;
		 double totalcash=0;
		 double subtotal =0;
		 double totalbalance =0;
		while(rs.next()){
			 	Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setBillno(rs.getString(1));
				
				if(rs.getInt(4)==1){
    				if(rs.getInt(3)==0){
    					priscription.setBillno("#"+rs.getString(1));
    				} else {
    					priscription.setBillno("#"+rs.getString(3));
    				}
    			}	
				
				priscription.setDate(DateTimeUtils.getCommencingDate1(rs.getString(2)));
				ArrayList<Priscription> priscriptionlist = getMedicineChargesList(priscription.getId(), null);
				priscription.setPriscriptionlist(priscriptionlist);
				int size = priscriptionlist.size();
				if (size > 0) {
					subtotal = priscriptionlist.get(size - 1).getSubtotal();
				} else {
					subtotal = 0;
				}
				CompleteAppointment completeAppointment = getBillDetails(priscription.getId());
				double newsubtotal = 0;
				double newtotaldisc=0;
				if(rs.getInt(4)==0){
					newsubtotal = subtotal;
					newtotaldisc = completeAppointment.getDiscount();
				}
				totaldisc = totaldisc + newtotaldisc;
				totalbill = newsubtotal + totalbill;
				
				double payAmt = getCahPayAmount(priscription.getId());
				String paymode = getBillPaymode(priscription.getId());
				totalcash = totalcash + payAmt;

				if (completeAppointment.getIsreturn() == 1) {
					if(completeAppointment.getTotal()>0){
						totalreturn = totalreturn + completeAppointment.getTotal();
					}else{
						totalreturn = totalreturn + completeAppointment.getRefundAmt();
					}
				}
				
				totalbalance = totalbalance+Double.parseDouble(completeAppointment.getBalance());
				double billpayamt = getPayAmount(priscription.getId());
				priscription.setSubtotal(subtotal);
				priscription.setDiscount(completeAppointment.getDiscount());
				priscription.setCgst(completeAppointment.getCgst());
				priscription.setSgst(completeAppointment.getSgst());
				priscription.setBalance(completeAppointment.getBalance());
				if(rs.getInt(4)==1){
					if(completeAppointment.getTotal()>0){
						priscription.setTotal(completeAppointment.getTotal());
					}else{
						priscription.setTotal(completeAppointment.getRefundAmt());
					}
					
				}else{
					priscription.setTotal(completeAppointment.getTotal());
				}
				
				priscription.setBillpayamt(String.valueOf(billpayamt));
				priscription.setPaymode(paymode);
				priscription.setIsreturn(completeAppointment.getIsreturn());
				priscription.setTotalBalance(totalbalance);
				//last
				priscription.setTotalamt(totalbill);
				priscription.setTotalReturn(totalreturn);
				priscription.setTotaldisc(totaldisc);
				
				
				list.add(priscription);
		}
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return list;
}

public double getTotalPaidAmountBYclient(int clientid, String fromdate, String todate, String balance,
		int ispharmacist) {
	double totalPaid=0;
	try {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select apm_medicine_bill.id from apm_medicine_bill ");
		buffer.append("where isreturn=0 and apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' ");
		//buffer.append("and balance>"+balance+"  ");
		if(ispharmacist>0){
			buffer.append("and pclientid='"+clientid+"' ");
		}else{
			buffer.append("and clientid='"+clientid+"' ");
		}
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			 double paid= getTotalPaymentReceived(rs.getInt(1));
			 totalPaid = totalPaid +paid;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return totalPaid;
}

public ArrayList<Priscription> getAllPharmacySaleListNew(Pagination pagination, String searchtext, String fromdate,
		String todate, String location, String isreturn, String paymode, int dontshowreqbill, LoginInfo loginInfo) {
	ArrayList<Priscription> list= new ArrayList<Priscription>();
	PrescriptionDAO prescriptionDAO =new JDBCPrescriptionDAO(connection);
	try {
		ArrayList<Priscription> inLinePatient= prescriptionDAO.getAllPharmacyList(pagination, searchtext, fromdate, todate, "0", "0", location,loginInfo);
		list.addAll(inLinePatient);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

public int getAllPharmacySaleCountNew(String searchText, String fromdate, String todate, String location,
		String isreturn, String paymode) {

	int res =0;
	if(todate!=null){
		todate = todate + " 23:59:59";
	}
	
	try {
		
		int result=0;
		if(todate!=null){
			todate = todate + " 23:59:59";
		}
		
		try {
			StringBuffer sql = new StringBuffer();
			if(todate!=null){
				todate = todate + " 23:59:59";
			}
			if(searchText==null){
				sql.append("select count(*) ");
				sql.append("from apm_client_parent_priscription ");
				sql.append("inner join apm_parent_prisc on apm_client_parent_priscription.id = apm_parent_prisc.oldparentid ");
				sql.append("where apm_parent_prisc.date between '"+fromdate+"' and '"+todate+"' and apm_parent_prisc.billno='0' ");
				if(!location.equals("0")){
					sql.append("and apm_parent_prisc.default_location_new='"+location+"' ");
				}
				sql.append("group by apm_client_parent_priscription.id ");
				sql.append("order by apm_client_parent_priscription.id ");
			}else{
				sql.append("select count(*) ");
				sql.append("from apm_client_parent_priscription ");
				sql.append("inner join apm_parent_prisc on apm_client_parent_priscription.id = apm_parent_prisc.oldparentid ");
				sql.append("inner join apm_patient on apm_patient.id = apm_client_parent_priscription.clientid ");
				sql.append("where apm_parent_prisc.date between '"+fromdate+"' and '"+todate+"' and apm_parent_prisc.billno='0' ");
				sql.append("and (firstname like('"+searchText+"%') or surname like('"+searchText+"%')or middlename like('"+searchText+"%') or fullname like('"+searchText+"%') or abrivationid='"+searchText+"') ");
				if(!location.equals("0")){
					sql.append("and apm_parent_prisc.default_location_new='"+location+"' ");
				}
				sql.append("group by apm_client_parent_priscription.id ");
				sql.append("order by apm_client_parent_priscription.id ");
			}
			
			PreparedStatement ps=connection.prepareStatement(sql.toString());
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				result=result  +1;
			}
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return result;

	} catch (Exception e) {

		e.printStackTrace();
	}

	return res;
}

public int getCreditReturnPharmacy(String fromdate, String todate) {
	int res=0;
	try {
		PreparedStatement ps= connection.prepareStatement(" select  sum(debit) from apm_medicine_bill where date between '"+fromdate+"' and '"+todate+"' and final_paymode='Credit' and isreturn='1'  ");
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			res= rs.getInt(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return 0;
}

public ArrayList<Priscription> getpaymentAgainstCredit(String fromdate, String todate, String isreturn,String paymode) {
	ArrayList<Priscription> list=new ArrayList<Priscription>();
	try {
		StringBuffer buffer= new StringBuffer();
		buffer.append("  select debit,payment,paymode,billno,apm_medicine_payment.date,apm_medicine_payment.userid,isreturn,apm_medicine_bill.date from apm_medicine_bill ");
		buffer.append(" inner join  apm_medicine_payment on apm_medicine_payment.billno =apm_medicine_bill.id  ");
		buffer.append(" where apm_medicine_payment.date between '"+DateTimeUtils.getCommencingDate1(fromdate)+"' and '"+DateTimeUtils.getCommencingDate1(todate)+"'   ");
		buffer.append("  and initial_paymode='Credit' and paymode!='Credit'   ");
		if(!paymode.equals("")){
			buffer.append("  and paymode='"+paymode+"'");
		}
		PreparedStatement ps= connection.prepareStatement(buffer.toString());
		int totalpay=0;
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			Priscription priscription= new Priscription();
			priscription.setDebit(rs.getDouble(1));
			priscription.setPayment(rs.getString(2));
			priscription.setPaymode(rs.getString(3));
			priscription.setBillno(rs.getString(4));
			priscription.setDate(DateTimeUtils.getCommencingDate1(rs.getString(5)));
			priscription.setUserid(rs.getString(6));
			priscription.setIsreturn(rs.getInt(7));
			priscription.setTotaldays(rs.getInt(2)+totalpay);
			priscription.setDateTime(DateTimeUtils.getCommencingDate1(rs.getString(8)));
			totalpay=priscription.getTotaldays();
			list.add(priscription);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

public int getBillNoFromPaymentid(String change_paymode_id) {
	int billno=0;
	try {
		String sql ="select billno from apm_medicine_payment where id='"+change_paymode_id+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			billno = rs.getInt(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return billno;
} 
public int updateBillInitialPaymode(String billno, String paymode) {
	int result=0;
	try {
		 String sql1="update apm_medicine_bill set initial_paymode='"+paymode+"' where id="+billno+"  "; 
	     PreparedStatement ps1=connection.prepareStatement(sql1);
	     result=  ps1.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public int updateBillDebit(double totaldebit, int billno) {
	int result=0;
	try {
		 String sql1="update apm_medicine_bill set debit='"+totaldebit+"' where id='"+billno+"'  "; 
	     PreparedStatement ps1=connection.prepareStatement(sql1);
	     result=  ps1.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

private int getTotalDebitofPharmacy(String isreturn, String fromdate,String todate,String billid,String paymode, String reportby, String billidno){
	int debit=0;
	try {
		StringBuffer sql= new StringBuffer();
		sql.append("select sum(debit) from apm_medicine_bill where date between '"+fromdate+"' and '"+todate+"' and deleted='0' ");
		
		sql.append(" and isreturn='"+isreturn+"' ");
		if(!billid.equals("")){
			sql.append(" and id='"+billid+"' ");
		}
		if(!paymode.equals("")){
			sql.append(" and initial_paymode='"+paymode+"' ");
		}
		if(reportby.equals("1")){
			sql.append(" and id='"+billidno+"'");
		}
		PreparedStatement ps= connection.prepareStatement(""+sql.toString());
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			debit= rs.getInt(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return debit;
}

public boolean checkPatientChargeAvailableInLog(int tempclientid, int ispharmacypatient, String loginsessionid, int productid,
		String qty, String fromDate, String todate) {
	boolean flag = false;
	try {
		String sql="select id from apm_medicine_charges_log where clientid=? and ispharmacy=? and loginsessionid=? and productid=? and status=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, ""+tempclientid);
		preparedStatement.setString(2, ""+ispharmacypatient);
		preparedStatement.setString(3, loginsessionid);
		preparedStatement.setString(4, ""+productid);
		preparedStatement.setString(5, "0");
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			flag = true;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return flag;
}

public int insertMedicineChargeLog(int tempclientid, int ispharmacypatient, String loginsessionid, int productid, String qty,
		String fromDate, String todate) {
	int result=0;
	try {
		String sql="insert into apm_medicine_charges_log (clientid, ispharmacy, loginsessionid,productid,reqqty,datetime,status) values (?,?,?,?,?,?,?)";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1, ""+tempclientid);
		ps.setString(2, ""+ispharmacypatient);
		ps.setString(3, loginsessionid);
		ps.setString(4, ""+productid);
		ps.setString(5, qty);
		ps.setString(6, todate);
		ps.setString(7, "0");
		result=ps.executeUpdate();
		if(result>0){
			ResultSet rs=ps.getGeneratedKeys();
			while(rs.next()){
				 result=rs.getInt(1);
			}
		}
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return result;
}

public int deleteProductFromSession(String chargesessionid) {
	int res = 0;
	try {
		String sql="delete from apm_medicine_charges_log where id='"+chargesessionid+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		res = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public int updateProductFromSession(String chargesessionid, String saleqty,String freeqty,String discount_perproduct) {
	int result=0;
	try {
		 String sql1="update apm_medicine_charges_log set reqqty='"+saleqty+"',freeqty='"+freeqty+"',discount_per_product='"+discount_perproduct+"' where id="+chargesessionid+"  "; 
	     PreparedStatement ps1=connection.prepareStatement(sql1);
	     result=  ps1.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public int updateChargeSessionStatus(int tempclientid, int ispharmacypatient, String loginsessionid, int status) {
	int result=0;
	try {
		 String sql1="";
		 if(status==2){
			 sql1="update apm_medicine_charges_log set status='"+status+"' where clientid=? and ispharmacy=? and loginsessionid=? and status=1  ";
		 }else{
			 sql1="update apm_medicine_charges_log set status='"+status+"' where clientid=? and ispharmacy=? and loginsessionid=? and status=0  ";
		 }
		  
	     PreparedStatement ps1=connection.prepareStatement(sql1);
	     ps1.setString(1, ""+tempclientid);
	     ps1.setString(2, ""+ispharmacypatient);
	     ps1.setString(3, loginsessionid);
	     result=  ps1.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public int updateSessionChargeBillno(int tempclientid, int ispharmacypatient, String loginsessionid, int billno) {
	int result=0;
	try {
		 String sql1="update apm_medicine_charges_log set billno='"+billno+"' where clientid=? and ispharmacy=? and loginsessionid=? and status=1  "; 
	     PreparedStatement ps1=connection.prepareStatement(sql1);
	     ps1.setString(1, ""+tempclientid);
	     ps1.setString(2, ""+ispharmacypatient);
	     ps1.setString(3, loginsessionid);
	     result=  ps1.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public int deleteTempChargePharmacySessionLog(String loginsessionid, String clientId, int ispharmacy) {
	int res = 0;
	try {
		String sql="delete from apm_medicine_charges_log where clientid='"+clientId+"' and ispharmacy='"+ispharmacy+"' and loginsessionid=? and status=0";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, loginsessionid);
		res = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public int deletePharmacyBill(String sessinbillno) {
	int res = 0;
	try {
		String sql="delete from apm_medicine_bill where id='"+sessinbillno+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		res = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public int insertMedicineTempChargeLog(int tempclientid, int ispharmacypatient, String loginsessionid, int productid,
		String qty, String fromDate, String todate, int chargesessionid, double totalavailablestock, String sale_price, String isbarcodeproductsale) {
	int result=0;
	try {
		String sql="insert into pharmacy_sale_temp_charge (clientid, ispharmacy, loginsessionid,productid,reqqty,datetime,chargesessionid,availablestock,unit_price,barcodesale) values (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1, ""+tempclientid);
		ps.setString(2, ""+ispharmacypatient);
		ps.setString(3, loginsessionid);
		ps.setString(4, ""+productid);
		ps.setString(5, qty);
		ps.setString(6, todate);
		ps.setString(7, ""+chargesessionid);
		ps.setDouble(8, totalavailablestock);
		ps.setString(9, sale_price);
		ps.setString(10, isbarcodeproductsale);
		result=ps.executeUpdate();
		if(result>0){
			ResultSet rs=ps.getGeneratedKeys();
			while(rs.next()){
				 result=rs.getInt(1);
			}
		}
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return result;
}

public int deleteTempPharmacyData(String loginsessionid) {
	int result=0;
	try {
		String sql="delete from pharmacy_sale_temp_charge where loginsessionid=?";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1, loginsessionid);
		result =ps.executeUpdate();
	} catch (Exception e) {

		e.printStackTrace();
	}
	return result;
}

public int updateProductFromTemp(String chargetempid, String saleqty,String freeqty,String discount_perproduct) {
	int result=0;
	try {
		 String sql1="update pharmacy_sale_temp_charge set reqqty='"+saleqty+"',freeqty='"+freeqty+"',discount_per_product='"+discount_perproduct+"' where id="+chargetempid+"  "; 
	     PreparedStatement ps1=connection.prepareStatement(sql1);
	     result=  ps1.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public int deleteProductFromTemp(String chargetempid) {
	int res = 0;
	try {
		String sql="delete from pharmacy_sale_temp_charge where id='"+chargetempid+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		res = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public Vector<Priscription> getTempMedicineList(int tempclientid, int ispharmacypatient, String loginsessionid) {
	Vector<Priscription> arrayList = new Vector<Priscription>();
	try {
		String sql="select id, productid, reqqty,chargesessionid,availablestock,unit_price,barcodesale,freeqty,discount_per_product from pharmacy_sale_temp_charge where clientid='"+tempclientid+"' and ispharmacy='"+ispharmacypatient+"' and loginsessionid='"+loginsessionid+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		//preparedStatement.setString(1, loginsessionid);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			Priscription priscription = new Priscription();
			priscription.setId(rs.getInt(1));
			priscription.setProduct_id(rs.getString(2));
			priscription.setQty(rs.getInt(3));
			priscription.setChargesessionid(rs.getInt(4));
			priscription.setAvailablestock(rs.getInt(5));
			priscription.setUnit_price(rs.getDouble(6));
			priscription.setBarcodesale(rs.getInt(7));
			priscription.setFreeqty(rs.getInt(8));
			priscription.setDiscount_perproduct(rs.getString(9));
			arrayList.add(priscription);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

Priscription getGstDataForAureus(String fromdate, String todate,int isreturn,String clinicid){
	Priscription priscription= new Priscription();
	try{
		PreparedStatement ps= null;
		StringBuffer buffer= new StringBuffer();
		buffer.append("   select sum(apm_medicine_charges.cgst+apm_medicine_charges.sgst),inventory_product.vat,sum(charge*quantity)-sum(apm_medicine_charges.discount_share)  from apm_medicine_bill   ");
		buffer.append("   inner join apm_medicine_charges on apm_medicine_bill.id=apm_medicine_charges.invoiceid   ");
		buffer.append("   inner join inventory_product on apm_medicine_charges.product_id =inventory_product.id   ");
		buffer.append("    where apm_medicine_bill.date  between '"+fromdate+"' and '"+todate+"'   ");
		if(isreturn==1){
			buffer.append("   and   apm_medicine_bill.isreturn ='1'  and apm_medicine_bill.deleted='0'  ");
			
		}else{
			buffer.append("   and   apm_medicine_bill.isreturn='0' and apm_medicine_charges.returnbillno='0' and apm_medicine_bill.deleted='0'  ");
			
		}
		buffer.append("   group by inventory_product.vat    ");
		 ps= connection.prepareStatement(buffer.toString());
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			if(rs.getInt(2)==0){
				priscription.setZeropricemed(Math.round(rs.getDouble(3)));
			}else if(rs.getInt(2)==5){
				priscription.setFivecgst(Double.parseDouble(new DecimalFormat("##.##").format(rs.getDouble(1)/2.0)));
			}else if(rs.getInt(2)==12){
				priscription.setTweelvecgst(Double.parseDouble(new DecimalFormat("##.##").format(rs.getDouble(1)/2.0)));
			}else if(rs.getInt(2)==18){
				priscription.setEighteencgst(Double.parseDouble(new DecimalFormat("##.##").format(rs.getDouble(1)/2.0)));
			}else if(rs.getInt(2)==28){
				priscription.setEighttwocgst(Double.parseDouble(new DecimalFormat("##.##").format(rs.getDouble(1)/2.0)));
			}		
		}
		
		double five= (100.0*(priscription.getFivecgst()*2.0))/5;
		double twelve= (100.0*(priscription.getTweelvecgst()*2.0))/12;
		double eighteen= (100.0*(priscription.getEighteencgst()*2.0))/18;
		double eightwo= (100.0*(priscription.getEighttwocgst()*2.0))/28;
		
		priscription.setFivenomrp(Double.parseDouble(new DecimalFormat("##.##").format(five)));
		priscription.setTweelvenomrp(Double.parseDouble(new DecimalFormat("##.##").format(twelve)));
		priscription.setEighteennomrp(Double.parseDouble(new DecimalFormat("##.##").format(eighteen)));
		priscription.setEighttwonomrp(Double.parseDouble(new DecimalFormat("##.##").format(eightwo)));
		
		priscription.setFivepricemed(Double.parseDouble(new DecimalFormat("##.##").format((priscription.getFivecgst()*2.0)+priscription.getFivenomrp())));
		priscription.setTweelvepricemed(Double.parseDouble(new DecimalFormat("##.##").format((priscription.getTweelvecgst()*2.0)+priscription.getTweelvenomrp())));
		priscription.setEighteenpricemed(Double.parseDouble(new DecimalFormat("##.##").format((priscription.getEighteencgst()*2.0)+priscription.getEighteennomrp())));
		priscription.setEighttwopricemed(Double.parseDouble(new DecimalFormat("##.##").format((priscription.getEighttwocgst()*2.0)+priscription.getEighttwonomrp())));
		
		double d= priscription.getZeropricemed()+priscription.getFivepricemed()+priscription.getTweelvepricemed()+priscription.getEighteenpricemed()+priscription.getEighttwopricemed();
		String r=String.format("%.2f", d);
		priscription.setTotal(Double.parseDouble(r));
		
		/*adjustment logic*/
		int debittotal=getTotalDebitofPharmacy(String.valueOf(isreturn), fromdate, todate, "", "","","");
		int productttotal=(int)priscription.getTotal();
//		System.out.println(""+debittotal+"   "+productttotal);
		int difference=debittotal-productttotal;
		/*priscription.setZeropricemed(priscription.getZeropricemed()+(double)difference);*/
		priscription.setZerosgst(Math.abs(priscription.getZeropricemed()));
		double d1= priscription.getZeropricemed()+priscription.getFivepricemed()+priscription.getTweelvepricemed()+priscription.getEighteenpricemed()+priscription.getEighttwopricemed();
		String r1=String.format("%.2f", d1);
		priscription.setTotal(Double.parseDouble(r1));
		priscription.setFromdate(DateTimeUtils.getCommencingDate1(fromdate));
		priscription.setTtlgross(Double.parseDouble(new DecimalFormat("##.##").format(priscription.getZeropricemed()+priscription.getFivenomrp()+priscription.getTweelvenomrp()+priscription.getEighteennomrp()+priscription.getEighttwonomrp())));
		priscription.setTtlgst(Double.parseDouble(new DecimalFormat("##.##").format(priscription.getTotal()-priscription.getTtlgross())));
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	return priscription;
}

public Bed getIPDActiveDataFromClientid(String string) {
	Bed bed = new Bed();
	try {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select ipd_addmission_form.id,bedid,ipd_addmission_form.wardid from ipd_addmission_form ");
		buffer.append("inner join apm_ipd_bed on ipd_addmission_form.bedid =  apm_ipd_bed.id ");
		buffer.append("where clientid='"+string+"' ");
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		bed.setIpdid("0");
		bed.setBedid("0");
		bed.setWardid("0");
		while (rs.next()) {
			bed.setIpdid(rs.getString(1));
			bed.setBedid(rs.getString(2));
			bed.setWardid(rs.getString(3));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return bed;
}

public int deletePharmacyBillCharges(String sessinbillno) {
	int res = 0;
	try {
		String sql="delete from apm_medicine_charges where invoiceid='"+sessinbillno+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		res = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public int deletePharmacyBillPayment(String sessinbillno) {
	int res = 0;
	try {
		String sql="delete from apm_medicine_payment where billno='"+sessinbillno+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		res = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public ArrayList<Priscription> getMedicineChargesListFast(int billno) {
	ArrayList<Priscription> list=new ArrayList<Priscription>();
	try {
		StringBuffer buffer=new StringBuffer();
		buffer.append("SELECT apm_medicine_charges.id,apm_medicine_charges.user,apm_medicine_charges.charge,apm_medicine_charges.practitionerId,apm_medicine_charges.practitionerName, ");
		buffer.append("apm_medicine_charges.clientId,apm_medicine_charges.commencing,apm_medicine_charges.paybuy,apm_medicine_charges.quantity, ");
		buffer.append("apm_medicine_charges.reqqty,apm_medicine_charges.medicineid,thirdPartyId,product_id,returnbillno ,tgstamt,apm_medicine_charges.oldchargeid ");
		buffer.append("from apm_medicine_charges inner join inventory_product on inventory_product.id=apm_medicine_charges.product_id where invoiceid="+billno+" ");
		buffer.append("order by inventory_product.prodname asc ");
		PreparedStatement ps=connection.prepareStatement(buffer.toString());
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			  Priscription priscription=new Priscription();
			  priscription.setSaleqty(rs.getInt(9));
			  priscription.setProduct_id(rs.getString(13));
			  priscription.setChargeid(""+rs.getInt(16));
			  list.add(priscription);
			  
		}
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return list;
}

public boolean checkMedicineTempChargeLog(int tempclientid, int ispharmacypatient, String loginsessionid, int id,
		String qty, String fromDate, String todate) {
	boolean flag = false;
	try {
		String sql="select * from pharmacy_sale_temp_charge where clientid=? and ispharmacy=? and loginsessionid=? and productid=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, ""+tempclientid);
		preparedStatement.setString(2, ""+ispharmacypatient);
		preparedStatement.setString(3, loginsessionid);
		preparedStatement.setString(4, ""+id);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			flag = true;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return flag;
}

public int getAllPharmacyChargeCount(int billno) {
	int res = 0;
	try {
		String sql ="select count(*) from apm_medicine_charges where invoiceid='"+billno+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			res = rs.getInt(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public int deletePharmacyBillData(String sessinbillno, String sessionuserid, String sessiondatetime) {
	try {
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		//int deleteresul = deletePharmacyBill(sessinbillno);
		int r=cancelMedicineBill(sessinbillno);
		ArrayList<Priscription> chargeList= getMedicineChargesListFast(Integer.parseInt(sessinbillno));
		String loc="";
		 for(Priscription priscription1: chargeList){
			 int previousstock = inventoryProductDAO.getPreviousStock(priscription1.getProduct_id());
			//update qty
			int result11=inventoryProductDAO.updateMedicineQty(priscription1.getSaleqty(),priscription1.getProduct_id(),1);
			
			Product product = inventoryProductDAO.getProduct(priscription1.getProduct_id());
			loc = product.getLocation();
			//stock log
			String datetime = sessiondatetime;
			int qtyinout=0;
			String source ="Pharmacy Sale System Cancel";
			int currentstock=inventoryProductDAO.getPreviousStock(priscription1.getProduct_id());
			int changeqty=priscription1.getSaleqty();
			int reslog = inventoryProductDAO.insertIntoProductLog(sessionuserid,datetime,product.getLocation(),qtyinout,priscription1.getProduct_id(),product.getCatalogueid(),source,currentstock,previousstock,changeqty,"0","0",Integer.parseInt(sessinbillno),0,0,"0");
			
			String datetime1[]=datetime.split(" ");
			String date = datetime1[0];
			int openingstock = previousstock;
			boolean checkopningstockexist = checkopeningstockexist(priscription1.getProduct_id(),date);
			if(!checkopningstockexist){
				int r1 = saveOpeningStock(priscription1.getProduct_id(),date,openingstock,"0");
			}
		 }
		 int res = inventoryProductDAO.saveUpDeletePharmacyBill(sessionuserid,loc,"Bill delete due to connection loss.",sessinbillno);
		/*int deletecharge = deletePharmacyBillCharges(sessinbillno);
		int deletepayment = deletePharmacyBillPayment(sessinbillno);*/

	} catch (Exception e) {
		e.printStackTrace();
	}
	return 0;
}

public int getPatientChargeAvailableInLog(int tempclientid, int ispharmacypatient, String loginsessionid, int id,
		String qty, String fromDate, String todate) {
	int res =0;
	try {
		String sql="select id from apm_medicine_charges_log where clientid=? and ispharmacy=? and loginsessionid=? and productid=? and status=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, ""+tempclientid);
		preparedStatement.setString(2, ""+ispharmacypatient);
		preparedStatement.setString(3, loginsessionid);
		preparedStatement.setString(4, ""+id);
		preparedStatement.setString(5, "0");
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			res = rs.getInt(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public int deleteFromSessionLog(String loginsessionid) {
	int res = 0;
	try {
		String sql="delete from apm_medicine_charges_log where loginsessionid=? and status=0";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, loginsessionid);
		res = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public int deleteTempPharmacySaleData(String loginsessionid, String clientid, int isphamrmacy) {
	int result=0;
	try {
		String sql="delete from pharmacy_sale_temp_charge where loginsessionid=? and clientid=? and ispharmacy=?";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1, loginsessionid);
		ps.setString(2, clientid);
		ps.setString(3, ""+isphamrmacy);
		result =ps.executeUpdate();
	} catch (Exception e) {

		e.printStackTrace();
	}
	return result;
}

public boolean checkPatientInTempSaleData(String loginsessionid, String clientid, int isphamrmacy) {
	boolean flag = false;
	try {
		String sql="select * from pharmacy_sale_temp_charge where loginsessionid=? and clientid=? and ispharmacy=?";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1, loginsessionid);
		ps.setString(2, clientid);
		ps.setString(3, ""+isphamrmacy);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			flag = true;
		}
	} catch (Exception e) {

		e.printStackTrace();
	}
	return flag;
}

public boolean checkReturnProductInTemp(String chargeid, String hdnphclientid, String hdnispharmacy, String loginsessionid) {
	boolean flag = false;
	try {
		String sql="select * from pharmacy_return_temp_charge where loginsessionid=? and clientid=? and ispharmacy=? and chargeid=?";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1, loginsessionid);
		ps.setString(2, hdnphclientid);
		ps.setString(3, hdnispharmacy);
		ps.setString(4, chargeid);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			flag = true;
		}
	} catch (Exception e) {

		e.printStackTrace();
	}
	return flag;
}

public int insertIntoPharReturnTemp(String chargeid, String hdnphclientid, String hdnispharmacy, String loginsessionid,
		String datetime, String qty) {
	int result=0;
	try {
		String sql="insert into pharmacy_return_temp_charge (clientid, ispharmacy, loginsessionid,chargeid,reqqty,datetime) values (?,?,?,?,?,?)";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1, hdnphclientid);
		ps.setString(2, hdnispharmacy);
		ps.setString(3, loginsessionid);
		ps.setString(4, chargeid);
		ps.setString(5, qty);
		ps.setString(6, datetime);
		result=ps.executeUpdate();
		if(result>0){
			ResultSet rs=ps.getGeneratedKeys();
			while(rs.next()){
				 result=rs.getInt(1);
			}
		}
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return result;
}

public Vector<Priscription> getReturnTempMedicineList(int hdnphclientid, int hdnispharmacy, String loginsessionid) {
	Vector<Priscription> arrayList = new Vector<Priscription>();
	try {
		String sql="select id, chargeid, reqqty from pharmacy_return_temp_charge where clientid='"+hdnphclientid+"' and ispharmacy='"+hdnispharmacy+"' and loginsessionid=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, loginsessionid);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			Priscription priscription = new Priscription();
			priscription.setId(rs.getInt(1));
			priscription.setChargeid(""+rs.getInt(2));
			priscription.setQty(rs.getInt(3));
			arrayList.add(priscription);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

public int updateProductFromReturnTemp(String tempreturnid, String returnqty) {
	int result=0;
	try {
		 String sql1="update pharmacy_return_temp_charge set reqqty='"+returnqty+"' where id="+tempreturnid+"  "; 
	     PreparedStatement ps1=connection.prepareStatement(sql1);
	     result=  ps1.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public int deleteProductFromReturnTemp(String tempreturnid) {
	int res = 0;
	try {
		String sql="delete from pharmacy_return_temp_charge where id='"+tempreturnid+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		res = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public int deleteTempReturnPharmacyData(String clientid, String ispharmacy, String loginsessionid) {
	int result=0;
	try {
		String sql="delete from pharmacy_return_temp_charge where loginsessionid=? and clientid=? and ispharmacy=?";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1, loginsessionid);
		ps.setString(2, clientid);
		ps.setString(3, ispharmacy);
		result =ps.executeUpdate();
	} catch (Exception e) {

		e.printStackTrace();
	}
	return result;
}


public int deletePharmacyReturnBillData(String sessinbillno) {
	try {
		InventoryProductDAO productDAO = new JDBCInventoryProductDAO(connection);
		int deleteresul = deletePharmacyBill(sessinbillno);
   	 	ArrayList<Priscription> chargeList= getMedicineChargesListFast(Integer.parseInt(sessinbillno));
		 for(Priscription priscription1: chargeList){
			//update qty
			int result11=productDAO.updateMedicineQty(priscription1.getSaleqty(),priscription1.getProduct_id(),0);
			int result12 = updateReturnQtyMinus(priscription1.getSaleqty(), priscription1.getChargeid());
		 }
		int deletecharge = deletePharmacyBillCharges(sessinbillno);
		int deletepayment = deletePharmacyBillPayment(sessinbillno);

	} catch (Exception e) {
		e.printStackTrace();
	}
	return 0;
}

public int updateReturnQtyMinus(int saleqty, String chargeid) {

	int result=0;
	try {
		
		String sql="select returnqty from apm_medicine_charges where id='"+chargeid+"'  ";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			     int qty= rs.getInt(1);
			     saleqty= qty-saleqty;
			     String sql2="update apm_medicine_charges set returnqty="+saleqty+" where id='"+chargeid+"'   ";
			     PreparedStatement ps2=connection.prepareStatement(sql2);
			     result =ps2.executeUpdate();
		}
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return result;
}

public Priscription getPharmacySaleAndDiscount(String fromdate, String todate, String paymode, int isreturn, String location) {
	Priscription priscription = new Priscription();
	try {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select sum(debit+discount),sum(debit),sum(discount) from apm_medicine_bill ");
		buffer.append("where date between '"+fromdate+"' and '"+todate+"' ");
		if(paymode!=null){
			buffer.append("and initial_paymode='"+paymode+"' ");
		}
		if(isreturn==0){
			buffer.append("and isreturn=0 ");
		}else{
			buffer.append("and isreturn=1 ");
		}
		if(!location.equals("0")){
			buffer.append("and location='"+location+"' ");
		}
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			double actualtotal = Math.round(rs.getDouble(1) * 100.0)/100.0;
			double debit = Math.round(rs.getDouble(2) * 100.0)/100.0;
			double discount = Math.round(rs.getDouble(3) * 100.0)/100.0;
			priscription.setActualtemptot(actualtotal);
			priscription.setDebit(debit);
			priscription.setDiscount(discount);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return priscription;
}

public double getPaidAmt(String fromdate, String todate, String paymode, String location,int isoutstanding, int onsamedate) {
	double sum=0;
	try {
		StringBuffer sql= new StringBuffer();
		sql.append("select sum(payment) from apm_medicine_bill ");
		sql.append("inner join apm_medicine_payment on apm_medicine_payment.billno = apm_medicine_bill.id ");
		if(isoutstanding==0){
			sql.append("where apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' and apm_medicine_payment.date between '"+fromdate+"' and '"+todate+"' ");
			if(onsamedate==0){
				sql.append("and onsamedate=0 ");
			}else{
				sql.append("and onsamedate=1 ");
			}
		}else{
			sql.append("where apm_medicine_bill.date not between '"+fromdate+"' and '"+todate+"' and apm_medicine_payment.date between '"+fromdate+"' and '"+todate+"' ");
		}
		if(paymode!=null){
			sql.append("and paymode='"+paymode+"' ");
		}
		if(!location.equals("0")){
			sql.append("and apm_medicine_bill.location='"+location+"' ");
		}
		PreparedStatement ps=connection.prepareStatement(sql.toString());
		ResultSet rs =ps.executeQuery();
		while(rs.next()){
			sum =Math.round(rs.getDouble(1) * 100.0)/100.0;
		}
	} catch (Exception e) {

		e.printStackTrace();
	}
	return sum;
}

public int getAllReqStockPharmacy(String pid) {
	int res =0;
	try {
		String sql="select sum(reqqty) from pharmacy_sale_temp_charge where productid='"+pid+"' ";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			res = rs.getInt(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public int getAllReqStockPharmacy(String pid, int tempclientid, int ispharmacypatient, String loginsessionid) {
	int res =0;
	try {
		String sql="select sum(reqqty) from pharmacy_sale_temp_charge where productid='"+pid+"' and clientid='"+tempclientid+"' and ispharmacy='"+ispharmacypatient+"' and loginsessionid=? ";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, loginsessionid);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			res = rs.getInt(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public int deleteTempPharmacyTimelyData(String fromdate) {
	int result=0;
	try {
		String sql="delete from pharmacy_sale_temp_charge where datetime<='"+fromdate+"'";
		PreparedStatement ps=connection.prepareStatement(sql);
		result =ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public int checkDummyBillExist(String billno, String dummybillno) {
	int res =0;
	try {
		String sql ="select * from apm_medicine_bill where dummybillno='"+dummybillno+"' and id!='"+billno+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			res =1;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public int updateDummyBillNo(String billno, String dummybillno, String dummybilldate) {
	int result=0;
	try {
		 String sql1="update apm_medicine_bill set dummybillno='"+dummybillno+"',date='"+dummybilldate+"' where id="+billno+"  "; 
	     PreparedStatement ps1=connection.prepareStatement(sql1);
	     result=  ps1.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public int saveDummyBillnoChange(String billno, String dummybillno, String dummybilldate, String datetime,
		String userId) {
	int result=0;
	try {
		String sql="insert into dummy_bill_update_log (billno, dummybillno, dummybilldate,datetime,userid) values (?,?,?,?,?)";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1, billno);
		ps.setString(2, dummybillno);
		ps.setString(3, dummybilldate);
		ps.setString(4, datetime);
		ps.setString(5, userId);
		result=ps.executeUpdate();
		if(result>0){
			ResultSet rs=ps.getGeneratedKeys();
			while(rs.next()){
				 result=rs.getInt(1);
			}
		}
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return result;
}

public boolean checkIpdPatientStatus(int int1) {
	boolean flag = false;
	try {
		String sql ="select ipdid from apm_client_parent_priscription where id='"+int1+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			if(rs.getInt(1)>0){
				flag =true;
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return flag;
}

public int updateProductSalePriceFromTemp(String chargetempid, String sale_price) {
	int result=0;
	try {
		 String sql1="update pharmacy_sale_temp_charge set unit_price='"+sale_price+"' where id="+chargetempid+"  "; 
	     PreparedStatement ps1=connection.prepareStatement(sql1);
	     result=  ps1.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public Priscription getPharmacyBillDetails(String billno) {
	Priscription priscription = new Priscription();
	try {
		String sql ="select debit,discount,clientid,pclientid,balance from apm_medicine_bill where id='"+billno+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			priscription.setDebit(rs.getDouble(1));
			priscription.setDiscount(rs.getDouble(2));
			priscription.setClientId(""+rs.getInt(3));
			priscription.setPclientid(""+rs.getInt(4));
			priscription.setCreditBalance(rs.getDouble(5));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return priscription;
}

public int savePharmacyDiscountRequest(String billno, String debit, String requestdisc_disctype, String requestdisc_amt,
		String userid, String datetime,String discinper, Priscription priscription, int disc_requeststatus) {
	int result=0;
	try {
		String sql="insert into pharmacy_discount_request (total_amt, disc_type, disc_amt,billno,req_userid,req_date,discinper,clientid,pclientid,status) values (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1, debit);
		ps.setString(2, requestdisc_disctype);
		ps.setString(3, requestdisc_amt);
		ps.setString(4, billno);
		ps.setString(5, userid);
		ps.setString(6, datetime);
		ps.setString(7, discinper);
		ps.setString(8, priscription.getClientId());
		ps.setString(9, priscription.getPclientid());
		ps.setString(10, ""+disc_requeststatus);
		result=ps.executeUpdate();
		if(result>0){
			ResultSet rs=ps.getGeneratedKeys();
			while(rs.next()){
				 result=rs.getInt(1);
			}
		}
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return result;
}

public int updateDiscountBillStatus(String billno, int val) {
	int result=0;
	try {
		 String sql1="update apm_medicine_bill set discount_status='"+val+"' where id="+billno+"  "; 
	     PreparedStatement ps1=connection.prepareStatement(sql1);
	     result=  ps1.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public ArrayList<Priscription> getDiscountList(String fromdate, String todate, String searchtext) {
	ArrayList<Priscription> arrayList = new ArrayList<Priscription>();
	//ClientDAO clientDAO = new JDBCClientDAO(connection);
	try {
		todate = todate+" "+"59:59:59";
		//String sql ="select id, total_amt, disc_type, disc_amt, billno, req_userid, req_date, approve_userid, approve_date, approve_note, clientid, pclientid, status,discinper,disc_delete from pharmacy_discount_request where req_date between '"+fromdate+"' and '"+todate+"' order by id desc";
		StringBuffer buffer = new StringBuffer();
		buffer.append("select pharmacy_discount_request.id, total_amt, disc_type, disc_amt, pharmacy_discount_request.billno, req_userid, req_date, ");
		buffer.append("approve_userid, approve_date, approve_note, clientid, pclientid, pharmacy_discount_request.status, ");
		buffer.append("discinper,disc_delete,apm_pharmacy_client.fullname,concat(apm_patient.firstname,' ',apm_patient.middlename,' ',apm_patient.surname) ");
		buffer.append("from pharmacy_discount_request ");
		buffer.append("left join apm_patient on apm_patient.id =pharmacy_discount_request.clientid ");
		buffer.append("left join apm_pharmacy_client on apm_pharmacy_client.id =pharmacy_discount_request.pclientid ");
		buffer.append("where req_date between '"+fromdate+"' and '"+todate+"' ");
		if(!searchtext.equals("")){
			buffer.append("and (apm_pharmacy_client.fullname like ('%"+searchtext+"%') or pharmacy_discount_request.billno='"+searchtext+"' ");
			buffer.append("or apm_patient.firstname like ('"+searchtext+"%') or apm_patient.surname like ('"+searchtext+"%') or apm_patient.abrivationid like ('"+searchtext+"') ");
			buffer.append("or apm_patient.middlename like ('"+searchtext+"%') or apm_patient.fullname like ('"+searchtext+"%')) ");
		}
		
		buffer.append("order by id desc ");
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			Priscription priscription = new Priscription();
			priscription.setId(rs.getInt(1));
			priscription.setTotalamt(rs.getDouble(2));
			priscription.setDisc_type(rs.getInt(3));
			priscription.setDiscount(rs.getDouble(4));
			priscription.setBillno(""+rs.getInt(5));
			priscription.setRequestuserid(rs.getString(6));
			priscription.setDate(rs.getString(7));
			priscription.setApprove_userid(rs.getString(8));
			priscription.setClientId(""+rs.getInt(11));
			priscription.setPclientid(""+rs.getInt(12));
			priscription.setStatus(""+rs.getInt(13));
			priscription.setDiscinper(rs.getDouble(14));
			priscription.setDisc_delete(rs.getInt(15));
			if (!priscription.getPclientid().equals("0")) {
				//Priscription priscription1 = getPharmacyPatient(priscription.getPclientid());
				priscription.setClientname(rs.getString(16));
			} else {
				//Client client = clientDAO.getClientDetails(priscription.getClientId());
				priscription.setClientname(rs.getString(17));
			}
			
			arrayList.add(priscription);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

public Priscription getDiscountData(int id) {
	Priscription priscription = new Priscription();
	try {
		String sql ="select id, total_amt, disc_type, disc_amt, billno, req_userid, req_date, approve_userid, approve_date, approve_note, clientid, pclientid, status,discinper from pharmacy_discount_request where id='"+id+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			
			priscription.setId(rs.getInt(1));
			priscription.setTotalamt(rs.getDouble(2));
			priscription.setDisc_type(rs.getInt(3));
			priscription.setDiscount(rs.getDouble(4));
			priscription.setBillno(""+rs.getInt(5));
			priscription.setRequestuserid(rs.getString(6));
			priscription.setDate(rs.getString(7));
			priscription.setApprove_userid(rs.getString(8));
			priscription.setClientId(""+rs.getInt(11));
			priscription.setPclientid(""+rs.getInt(12));
			priscription.setStatus(""+rs.getInt(13));
			priscription.setDiscinper(rs.getDouble(14));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return priscription;
}

public int updateDiscountApproveNotes(int id, String approve_notes, int status, String userid, String datetime) {
	int result=0;
	try {
		 String sql1="update pharmacy_discount_request set approve_userid='"+userid+"',approve_date='"+datetime+"',approve_note='"+approve_notes+"',status='"+status+"' where id="+id+"  "; 
	     PreparedStatement ps1=connection.prepareStatement(sql1);
	     result=  ps1.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public int updateBillVatNew(double cgst, double gst, String billno, int disc_type, double discount) {
	int result=0;
	try {
		 String sql1="update apm_medicine_bill set vat='"+billno+"',cgst='"+cgst+"',sgst='"+cgst+"',disc_type='"+disc_type+"',actual_discount='"+discount+"' where id="+billno+"  "; 
	     PreparedStatement ps1=connection.prepareStatement(sql1);
	     result=  ps1.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public int checkpaymentdoneagainstbill(String billno) {
	int res=0;
	try {
		String sql="select paymode from apm_medicine_payment where billno="+billno+" and payment>0 and paymode!='Credit' order by id desc limit 1 ";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			res =1;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public int getByDefaultPharmacyLocation() {
	int id=0;
	try {
		String sql="select default_phar_location from apm_user where id=1";
		PreparedStatement ps=connection.prepareStatement(sql); 
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			id= rs.getInt(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return id;
}

public int getAllOnlineSaleCountNew(String searchText, String fromdate, String todate, String location, String isreturn,
		String paymode, LoginInfo loginInfo) {
	int res =0;
	if(todate!=null){
		todate = todate + " 23:59:59";
	}
	
	try {
		
		int result=0;
		if(todate!=null){
			todate = todate + " 23:59:59";
		}
		
		try {
			StringBuffer sql = new StringBuffer();
			if(todate!=null){
				todate = todate + " 23:59:59";
			}
			if(searchText==null){
				sql.append("select count(*) ");
				sql.append("from apm_client_parent_priscription ");
				sql.append("inner join apm_parent_prisc on apm_client_parent_priscription.id = apm_parent_prisc.oldparentid ");
				sql.append("where apm_parent_prisc.date between '"+fromdate+"' and '"+todate+"' and apm_parent_prisc.billno='0' and apm_client_parent_priscription.prisc_delete=0 ");
				sql.append("and directtransfer=1 ");
				if(!location.equals("0")){
					sql.append("and apm_parent_prisc.default_location_new='"+location+"' ");
				}
				if(loginInfo.isBalgopal()){
					sql.append("and apm_client_parent_priscription.discharge=0 and apm_client_parent_priscription.admission=0 ");
				}
				sql.append("group by apm_client_parent_priscription.id ");
				sql.append("order by apm_client_parent_priscription.id ");
			}else{
				sql.append("select count(*) ");
				sql.append("from apm_client_parent_priscription ");
				sql.append("inner join apm_parent_prisc on apm_client_parent_priscription.id = apm_parent_prisc.oldparentid ");
				sql.append("inner join apm_patient on apm_patient.id = apm_client_parent_priscription.clientid ");
				sql.append("where apm_parent_prisc.date between '"+fromdate+"' and '"+todate+"' and apm_parent_prisc.billno='0' and apm_client_parent_priscription.prisc_delete=0 ");
				sql.append("and directtransfer=1 ");
				sql.append("and (firstname like('"+searchText+"%') or surname like('"+searchText+"%')or middlename like('"+searchText+"%') or fullname like('"+searchText+"%') or abrivationid='"+searchText+"') ");
				if(!location.equals("0")){
					sql.append("and apm_parent_prisc.default_location_new='"+location+"' ");
				}
				if(loginInfo.isBalgopal()){
					sql.append("and apm_client_parent_priscription.discharge=0 and apm_client_parent_priscription.admission=0 ");
				}
				sql.append("group by apm_client_parent_priscription.id ");
				sql.append("order by apm_client_parent_priscription.id ");
			}
			
			PreparedStatement ps=connection.prepareStatement(sql.toString());
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				result=result  +1;
			}
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return result;

	} catch (Exception e) {

		e.printStackTrace();
	}

	return res;
}

public int checkProductReturnAgainstBill(String billno) {
	int res =0;
	try {
		String sql="select * from apm_medicine_charges where (quantity-returnqty)!=quantity and invoiceid='"+billno+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			res = 2;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public ArrayList<Master> getAllLocationNew() {
	ArrayList<Master> list= new ArrayList<Master>();
	try {
		
		String sql="SELECT id,name from apm_condition where ispharmacy=1";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		
		while(rs.next()){
			
			 Master master =new Master();
			 master.setId(rs.getInt(1));
			 master.setName(rs.getString(2));
			 list.add(master);
		}
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return list;
}

public int updatePharDiscountReq(String id, String userId, String datetime) {
	int res=0;
	try {
		String sql ="update pharmacy_discount_request set disc_delete=1,disc_del_userid='"+userId+"',disc_del_datetime='"+datetime+"' where id='"+id+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		res = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public int updateGrossTotalAndDisc(String billno, double grosstotal, String requestdisc_amt,
		String requestdisc_disctype) {
	int res=0;
	try {
		String sql ="update apm_medicine_bill set disc_type='"+requestdisc_disctype+"',actual_discount='"+requestdisc_amt+"',grosstotal='"+grosstotal+"' where id='"+billno+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		res = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public int getCheckDiscountNotRequested(String id) {
	int res =0;
	try {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select qty,return_medicine_child.id,chargeid from return_medicine_parent ");
		buffer.append("inner join return_medicine_child on return_medicine_parent.id = return_medicine_child.parentid ");
		buffer.append("inner join apm_medicine_charges on return_medicine_child.chargeid = apm_medicine_charges.id ");
		buffer.append("inner join apm_medicine_bill on apm_medicine_bill.id = apm_medicine_charges.invoiceid ");
		buffer.append("where return_medicine_parent.id='"+id+"' and discount_status=1 ");
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			res=1;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public double getTotalBalanceNew(String clientid, String flag, String totalids) {


	 double tot=0;  
	 try {
	  
	  String sql="";
	  if(flag.equals("1")){
	   sql="select sum(balance) from apm_medicine_bill where pclientid='"+clientid+"'  and id in ("+totalids+") ";
	   //and (tpid=0 or tpid is NULL)
	  } else {
	   sql="select sum(balance) from apm_medicine_bill where clientid='"+clientid+"'  and id in ("+totalids+") ";
	   //and (tpid=0 or tpid is NULL)
	  }
	  PreparedStatement ps=connection.prepareStatement(sql);
	  ResultSet rs=ps.executeQuery();
	  
	  while(rs.next()){
	   
	    tot=rs.getDouble(1);
	  }
	  
	 } catch (Exception e) {

	  e.printStackTrace();
	 }
	 
	 return tot;
	  
}

public ArrayList<Product> getAllMedicinesForIPDReturn(String clientid, String ispharmacy, String location, int isrequest) {


	 ArrayList<Product> list=new ArrayList<Product>();
	 try {
		    StringBuffer buffer=new StringBuffer();
		    InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			buffer.append("SELECT apm_medicine_charges.id,apm_medicine_charges.product_id,apm_medicine_charges.quantity,(quantity+0)-(returnqty+0) ");
			buffer.append("from apm_medicine_charges inner join apm_medicine_bill on apm_medicine_bill.id=apm_medicine_charges.invoiceid ");
			
			if(ispharmacy.equals("1")){
				buffer.append("where apm_medicine_charges.pclientid="+clientid+" ");
			} else {
				buffer.append("where apm_medicine_charges.clientId="+clientid+" ");
			}
			if(isrequest>0){
				buffer.append("and discount_status!=1 ");
			}
			if(!location.equals("0")){
				buffer.append("and apm_medicine_bill.location='"+location+"' ");
			}
			
			buffer.append("and apm_medicine_bill.isreturn=0 and apm_medicine_bill.deleted=0 and ((quantity+0)-(returnqty+0))>0  ");
			buffer.append("order by apm_medicine_charges.id desc ");
			
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				  Product product= new Product();
				  product.setId(rs.getInt(1));
				  product.setProduct_id(rs.getString(2));
				  String qty=rs.getString(3);
				  int newqty = rs.getInt(4);
				  int id = rs.getInt(1);
				  int returnqty = getNurseReturnQty(id);
				  int finalqty = newqty - returnqty;
				  if(finalqty>0){
					  Product master= inventoryProductDAO.getProduct(product.getProduct_id());
					  String data = master.getProduct_name() + "-" + master.getGenericname() + "- (" + master.getBatch_no() + "/"
								+ master.getHsnno() + ") (" + master.getExpiry_date() + ") (Rs." + master.getSale_price()
								+ ") (" + finalqty + ")  ";
					  product.setGenericname(data);
	                 list.add(product);
				  }
			}
		 
	  } catch (Exception e) {

		 e.printStackTrace();
	 }
	 
	 return list;

}

public int getNurseReturnQty(int id) {
	int res =0;
	try {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select sum(qty) from return_medicine_child ");
		buffer.append("inner join return_medicine_parent on return_medicine_parent.id = return_medicine_child.parentid ");
		buffer.append("where status=0 and chargeid='"+id+"' ");
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			res = rs.getInt(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public Priscription getNursePrescriptionParent(String id) {
	Priscription priscription = new  Priscription();
	try {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select return_medicine_parent.clientid,return_medicine_parent.ipdid from return_medicine_parent ");
		buffer.append("where return_medicine_parent.id='"+id+"' ");
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			priscription.setClientId(rs.getString(1));
			priscription.setIpdid(rs.getString(2));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return priscription;
}

public int updateBillIpdId(String ipdid, int billno) {
	int res=0;
	try {
		String sql ="update apm_medicine_bill set phar_ipdid='"+ipdid+"' where id='"+billno+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		res = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public CompleteAppointment getBillIpdIdfromMultiId(String returnbillids) {
	CompleteAppointment appointment=new CompleteAppointment();
	try {
		String sql="select phar_ipdid,phar_bedid,phar_wardid from apm_medicine_bill where id in ("+returnbillids+") and phar_ipdid>0 order by id desc limit 1 ";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		appointment.setStatus("0");
		while(rs.next()){
			appointment.setStatus("1");
			appointment.setPhar_ipdid(""+rs.getInt(1));
			appointment.setBedid(""+rs.getInt(2));
			appointment.setWardid(""+rs.getInt(3));
		}
		
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return appointment;
}

public int updatePharmacyRefundIpdId(String bedid, String wardid, String phar_ipdid, int billno) {
	int res=0;
	try {
		String sql ="update apm_medicine_bill set phar_ipdid='"+phar_ipdid+"',phar_bedid='"+bedid+"',phar_wardid='"+wardid+"' where id='"+billno+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		res = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public ArrayList<Priscription> getReturnMedicineCharges(int billno) {


	 ArrayList<Priscription> list=new ArrayList<Priscription>();
	 try {
		    StringBuffer buffer=new StringBuffer();
		    buffer.append("SELECT (returnqty+0),inventory_product.prodname ");
			buffer.append("from apm_medicine_charges inner join inventory_product on inventory_product.id=apm_medicine_charges.product_id where invoiceid="+billno+" ");
			buffer.append("and (returnqty+0)>0 ");
			buffer.append("order by inventory_product.prodname asc ");
			
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
		
			while(rs.next()){
				 Priscription priscription=new Priscription();
				 priscription.setReturnedqty(rs.getString(1));
				 priscription.setMdicinenametxtnew(rs.getString(2));
				 list.add(priscription);
			}
		 
	  } catch (Exception e) {

		 e.printStackTrace();
	 }
	 
	 return list;

}

public ArrayList<Product> getsessionAddedPharMedList(String location) {
	ArrayList<Product> arrayList = new ArrayList<Product>();
	try {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select prodname,pharmacy_sale_temp_charge.datetime,pharmacy_sale_temp_charge.reqqty, ");
		buffer.append("ispharmacy,apm_pharmacy_client.fullname,apm_patient.fullname from pharmacy_sale_temp_charge ");
		buffer.append("inner join inventory_product on inventory_product.id = pharmacy_sale_temp_charge.productid ");
		buffer.append("left join apm_patient on apm_patient.id=pharmacy_sale_temp_charge.clientid ");
		buffer.append("left join apm_pharmacy_client on apm_pharmacy_client.id=pharmacy_sale_temp_charge.clientid ");
		buffer.append("where inventory_product.location='"+location+"' ");
		buffer.append("order by pharmacy_sale_temp_charge.datetime asc ");
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			Product product = new Product();
			product.setProd_name(rs.getString(1));
			product.setDateTime(rs.getString(2));
			product.setReqqty(rs.getString(3));
			if(rs.getInt(4)==0){
				product.setFullname(rs.getString(6));
			}else{
				product.setFullname(rs.getString(5));
			}
			arrayList.add(product);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

public Priscription setSession_Added_Med(String status, String userid) {

	PreparedStatement ps= null;
	PreparedStatement ps1= null;
	Priscription pr= new Priscription();
	try {
		String sql=" update apm_pharmacy_user set session_added_med='"+status+"' where id='"+userid+"'";
		ps= connection.prepareStatement(sql);
		int x= ps.executeUpdate();
		String sql1="select id,session_added_med from apm_pharmacy_user where id="+userid;
		ps1= connection.prepareStatement(sql1);
		ResultSet rs= ps1.executeQuery();
		while(rs.next()){
		 pr.setId(rs.getInt(1));
		 pr.setPharm_print_backdate(rs.getBoolean(2));
		}
				
	} catch (Exception e) {
		e.printStackTrace();
	}
	return pr;

}

public int checkProductInSale(String string) {
	int res =0;
	try {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from pharmacy_sale_temp_charge where productid in ("+string+") ");
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			res =1;
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public int getChargeIdFromProductId(int newproductid, String clientid, String ispharmacy, String paymodereturn, String location) {
		int res =0;
	 try {
		    StringBuffer buffer=new StringBuffer();
		    InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			buffer.append("SELECT apm_medicine_charges.id,apm_medicine_charges.product_id,apm_medicine_bill.id ");
			buffer.append("from apm_medicine_charges inner join apm_medicine_bill on apm_medicine_bill.id=apm_medicine_charges.invoiceid ");
			if(ispharmacy.equals("1")){
				buffer.append("where apm_medicine_bill.pclientid="+clientid+" ");
			} else {
				buffer.append("where apm_medicine_bill.clientId="+clientid+" ");
			}
			if(!location.equals("0")){
				buffer.append("and apm_medicine_bill.location='"+location+"'  ");
			}
			buffer.append("and apm_medicine_charges.product_id='"+newproductid+"' ");
			buffer.append("and ((quantity+0)-(returnqty+0))>0 and apm_medicine_bill.deleted=0 and discount_status!=1 ");
			buffer.append("and apm_medicine_bill.isreturn=0  ");
			buffer.append("order by apm_medicine_charges.id desc ");
			
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				int isinadjustment = inventoryProductDAO.checkProductInAdjustment(rs.getString(2));
				if(isinadjustment==1){
					continue;
				}
				String paymode = getBillPaymodeNew(rs.getInt(3));
				if(paymode!=null){
					  if(!paymodereturn.equals("Credit")){
						  if(!paymode.equals("Credit")){
							  res = rs.getInt(1);
							  break;
						  }
					  }else{
						  if(paymode.equals("Credit")){
							  res = rs.getInt(1);
							  break;
						  }
					  }
				}
			}
		 
	  } catch (Exception e) {

		 e.printStackTrace();
	 }
	 
	 return res;

}

public ArrayList<Priscription> getPriscInSaleList(String fromdate, String todate) {


	ArrayList<Priscription> priscriptions = new ArrayList<Priscription>();
	ClientDAO clientDAO  = new JDBCClientDAO(connection);
	if(todate!=null){
		todate = todate + " 23:59:59";
	}
	
	try {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select apm_client_parent_priscription.id, apm_client_parent_priscription.clientid, practitionerid, ");
		sql.append("apm_client_parent_priscription.conditionid,dosenotes,followupcount, followupstype, advoice, english, ");
		sql.append("regional, hindi,prepay, postpay, other, lastmodified, apm_client_parent_priscription.ipdid, ");
		sql.append("dstatus,apm_client_parent_priscription.billno,estimate,delivered,location_s,apm_client_parent_priscription.userid,prisc_tokenno,apm_parent_prisc.id ");
		sql.append("from apm_client_parent_priscription ");
		sql.append("inner join apm_parent_prisc on apm_client_parent_priscription.id = apm_parent_prisc.oldparentid ");
		sql.append("where apm_parent_prisc.date between '"+fromdate+"' and '"+todate+"' and apm_parent_prisc.billno='0' and apm_client_parent_priscription.prisc_delete=0 ");
		sql.append("and directtransfer=1 and prisc_token_status=1 ");
		/*if(!location.equals("0")){
			sql.append("and apm_parent_prisc.default_location_new='"+location+"' ");
		}*/
		sql.append("order by apm_parent_prisc.id desc ");
		PreparedStatement ps = connection.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			Priscription priscription = new Priscription();
			int id = rs.getInt(1);
			String clientid = rs.getString(2);
			Client client = clientDAO.getClientDetails(clientid);
			String fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
			priscription.setFullname(fullname);
			priscription.setMobile(client.getMobNo());
			priscription.setAbrivationid(client.getAbrivationid());
			String conditionid = rs.getString(4);
			String dosenotes = rs.getString(5);
			String folloupcount = rs.getString(6);
			String followupstype = rs.getString(7);
			String advoice = rs.getString(8);
			String english = rs.getString(9);
			String regional = rs.getString(10);
			String hindi = rs.getString(11);
			String prepay = rs.getString(12);
			String postpay = rs.getString(13);
			String other = rs.getString(14);
			
			String lastmodified = "";
			if(rs.getString(15)!=null){
				lastmodified = DateTimeUtils.getDBDate(rs.getString(15));
			}
			String ipdid = rs.getString(16);
			//Bed bed = bedDao.getEditIpdData(ipdid);
			
			String dstatus = rs.getString(17);
			priscription.setDstatus(dstatus);
			priscription.setBillno(rs.getString(18));
			
			priscription.setEstimate(rs.getInt(19));
			priscription.setDelivered(rs.getInt(20));
			priscription.setLocation(""+rs.getInt(21));
			
			if(ipdid==null || ipdid.equals("0")){
				priscription.setIpdid("0");
			}else{
				priscription.setIpdid(ipdid);
			}
			
			/*String wardname=ipdDAO.getIpdWardName(bed.getWardid());
			priscription.setWardname(wardname);
			String bedname = ipdDAO.getIpdBedName(bed.getBedid());
			priscription.setBedname(bedname);*/

			priscription.setId(id);
			priscription.setClientId(clientid);
			priscription.setConditionid(conditionid);
			priscription.setDosenotes(dosenotes);
			priscription.setFollowupsqty(folloupcount);
			priscription.setFollowupstype(followupstype);
			priscription.setAdvoice(advoice);
			priscription.setEnglish(english);
			priscription.setRegional(regional);
			priscription.setHindi(hindi);
			priscription.setOutp(0);
			priscription.setPrepay(prepay);
			priscription.setPostpay(postpay);
			priscription.setOtherpay(other);
			priscription.setLastmodified(lastmodified);
			priscription.setOpdipdprisc("1");
			priscription.setRequestuserid(rs.getString(22));
			priscription.setTokenno(rs.getString(23));
			priscription.setId(rs.getInt(24));
			priscriptions.add(priscription);	
		}

	} catch (Exception e) {

		e.printStackTrace();
	}

	return priscriptions;

}

public ArrayList<Priscription> getPrintPriscReqcsv(String newparentid) {
	ArrayList<Priscription> arrayList = new ArrayList<Priscription>();
	try {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select apm_child_prisc.medicineid,apm_child_prisc.medicinename,apm_patient.mobno,title,apm_patient.fullname, ");
		buffer.append("apm_child_prisc.qty,apm_client_priscription.dose ");
		buffer.append("from apm_child_prisc ");
		buffer.append("inner join apm_patient on apm_patient.id = apm_child_prisc.clientid ");
		buffer.append("inner join apm_client_priscription on apm_client_priscription.id = apm_child_prisc.oldchildid ");
		buffer.append("where apm_child_prisc.parentid="+newparentid+" ");
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			Priscription priscription = new Priscription();
			priscription.setMedicineid(rs.getString(1));
			priscription.setMdicinenametxt(rs.getString(2));
			priscription.setMobile(rs.getString(3));
			priscription.setFullname(DateTimeUtils.isNull(rs.getString(4)+" "+rs.getString(5)));
			priscription.setQty(rs.getInt(6));
			priscription.setDosage(rs.getString(7));
			arrayList.add(priscription);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

public ArrayList<String> getPatientNameData(String val) {
	ArrayList<String> arrayList = new ArrayList<String>();
	try {
		String sql ="select distinct SUBSTRING_INDEX(fullname, ' ', 1) from apm_pharmacy_client where SUBSTRING_INDEX(fullname, ' ', 1) like '"+val+"%' limit 5";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			arrayList.add(rs.getString(1));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

public String getappointmentinfopractid(int id) {

	String pract_name="0";
	try {
		String sql="SELECT diaryuserid from apm_available_slot where clientid="+id+" order by id desc limit 0,1";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()){
			pract_name =rs.getString(1);
		}
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	return pract_name;

}

public Priscription getLastBillDrDetails(int i, String clientid) {
	Priscription priscription = new Priscription();
	try {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select bill_practid,bill_practname from apm_medicine_bill where isreturn=0  ");
		if(i==1){
			buffer.append("and pclientid='"+clientid+"' ");
		}else{
			buffer.append("and clientid='"+clientid+"' ");
		}
		buffer.append("order by id desc limit 1 ");
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			priscription.setPractitionerid(rs.getString(1));
			priscription.setPractitionername(rs.getString(2));
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return priscription;
}

public int getBillPharmacyAvailablityNew(String selectedid, String sessionid, String todate, String loc, String hdnphclientid, int hdnispharmacy) {
	int res =0;
	PreparedStatement preparedStatement = null;
	StringBuffer buffer = new StringBuffer();
	buffer.append("select catalogueid,quantity from apm_medicine_charges ");
	buffer.append("inner join inventory_product on inventory_product.id = apm_medicine_charges.product_id ");
	buffer.append("where invoiceid="+selectedid+" ");
	try{
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.MINUTE, -45);
		String fromdate = dateFormat1.format(cal1.getTime());			
		int res111 = deleteTempPharmacyTimelyData(fromdate);
		
		double subtotal=0;
		int countforcal=0;
		preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		loc = DateTimeUtils.isNull(loc);
		if(loc.equals("")){
			loc="0";
		}
		while(rs.next()){
			res=1;
			ArrayList<Product> prodidslist = getPriscMedicineByAvailablilityList(rs.getString(1),rs.getInt(2),loc);
			//  04 July 2018 its not working for same medicine for multiple if there is stock available and batch different
			int i=0;
			for (Product product2 : prodidslist) {
				boolean chargetempidisavailable = checkMedicineTempChargeLog(Integer.parseInt(hdnphclientid),hdnispharmacy,sessionid,product2.getId(),""+product2.getSaleqty(),todate,todate);
				if(!chargetempidisavailable){
					boolean flag =checkPatientChargeAvailableInLog(Integer.parseInt(hdnphclientid),hdnispharmacy,sessionid,product2.getId(),""+product2.getSaleqty(),todate,todate);
					int chargesessionid=0;
					if(!flag){
						//if entry not present in temp table so insert into temp table
						chargesessionid =insertMedicineChargeLog(Integer.parseInt(hdnphclientid),hdnispharmacy,sessionid,product2.getId(),""+product2.getSaleqty(),todate,todate);
					}else{
						chargesessionid = getPatientChargeAvailableInLog(Integer.parseInt(hdnphclientid),hdnispharmacy,sessionid,product2.getId(),""+product2.getSaleqty(),todate,todate);
					}
					double stock = Double.parseDouble(product2.getStock());
					double reqstock = getAllReqStockPharmacy(""+product2.getId());
					double requserstockstock = getAllReqStockPharmacy(""+product2.getId(),Integer.parseInt(hdnphclientid),hdnispharmacy,sessionid);
					double totalavailablestock = (stock - reqstock) + requserstockstock;
					int chargetempid=0;
					//if entry not present in temp table so insert into temp table
					chargetempid = insertMedicineTempChargeLog(Integer.parseInt(hdnphclientid),hdnispharmacy,sessionid,product2.getId(),""+product2.getSaleqty(),todate,todate,chargesessionid,totalavailablestock,product2.getSale_price(),"0");
				}
				
			}
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return res;
}

private ArrayList<Product> getPriscMedicineByAvailablilityList(String catalogueid, int int1, String loc) {
	
	ArrayList<Product> arrayList = new ArrayList<Product>();
	try {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select inventory_product.id,inventory_product.supplierid,inventory_catalogue.product_name, ");
		buffer.append("inventory_product.mrp,inventory_product.purchaseprice,inventory_product.saleprice,inventory_product.stock, ");
		buffer.append("inventory_product.mdicinenameid,inventory_product.expiry_date,inventory_product.batch_no,inventory_product.vat, ");
		buffer.append("inventory_catalogue.genericname,inventory_product.cell,inventory_product.pack,inventory_product.medicine_type,inventory_product.mfg ");
		buffer.append("from inventory_product inner join inventory_catalogue on inventory_product.catalogueid=inventory_catalogue.id ");
		buffer.append("where inventory_catalogue.id='"+catalogueid+"' ");
		buffer.append("and inventory_product.saleprice is not null and inventory_product.mrp>0 and stock>0 and inventory_product.pack>0  ");
		buffer.append("and inventory_product.location='"+loc+"' ");
		buffer.append("order by inventory_product.expiry_date asc ");

		PreparedStatement ps = connection.prepareStatement(buffer.toString());
		ResultSet rs = ps.executeQuery();
		int count =0;
		while (rs.next()) {
			Product product = new Product();
			boolean flag = false;
			count = count +rs.getInt(7);
			if(count>=int1){
				/*product.setSaleqty(rs.getInt(7)-(count-rs.getInt(7)));*/
				product.setSaleqty(int1-(count-rs.getInt(7)));
				flag= true;
			}else{
				product.setSaleqty(rs.getInt(7));
			}
			product.setId(rs.getInt(1));
			product.setVendor_id(rs.getString(2));
			product.setProduct_name(rs.getString(3));
			product.setMrp(rs.getString(4));
			product.setPurchase_price(rs.getString(5));
			product.setSale_price(DateTimeUtils.changeFormat(rs.getDouble(6)));
			product.setStock(rs.getString(7));
			product.setMedicinenameid(rs.getString(8));
			product.setExpiry_date(rs.getString(9));
			product.setBatch_no(rs.getString(10));
			product.setVat(rs.getString(11));
			product.setGenericname(rs.getString(12));
			product.setShelf(rs.getString(13));
			product.setPack(rs.getString(14));
			product.setMedicine_type(rs.getString(15));
			product.setMfg(rs.getString(16));
			arrayList.add(product);
			if(flag){
				break;
			}
			
		}
	} catch (Exception e) {

		e.printStackTrace();
	}
	return arrayList;
}

public boolean checkifSequenceExist(String cdate) {
	PreparedStatement preparedStatement = null;
	boolean result = false;
	String sql = "select id from apm_patient_seqno_pharmacy where commencing = '"+cdate+"' ";
	try{
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()){
			result = true;
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}
public int getSqeunceNumber(String cdate) {
	PreparedStatement preparedStatement = null;
	int result = 0;
	String sql = "select max(seqno) from apm_patient_seqno_pharmacy where commencing = '"+cdate+"' ";
	
	try{
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()){
			result = rs.getInt(1);
		}
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	
	return result;
}

public int InserCdateSeq(String cdate,int seqno) {
	PreparedStatement preparedStatement = null;
	int result = 0;
	String sql = "insert into apm_patient_seqno_pharmacy(commencing,seqno) values(?,?) ";
	
	try{
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, cdate);
		preparedStatement.setInt(2, seqno);
		result = preparedStatement.executeUpdate();
		
		if(result == 1){
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if(resultSet.next()){
				result = resultSet.getInt(1);  
			}
		}
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public int updateNewPharmaPatient(String adfullname, String admobile, String adaddress, String addoctor,
		String fullname, String editpclinetid) {
	int res =0;
	try {
		String sql ="update apm_pharmacy_client set fullname=?,mobile=?,address=?,phar_drid=?,reference=? where id=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, adfullname);
		preparedStatement.setString(2, admobile);
		preparedStatement.setString(3, adaddress);
		preparedStatement.setString(4, addoctor);
		preparedStatement.setString(5, fullname);
		preparedStatement.setString(6, editpclinetid);
		res = preparedStatement.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public int pharmacypatientupdatelog(Priscription priscription, String adfullname, String admobile, String adaddress,
		String addoctor, String editpclinetid, String datetime) {
	int res=0;
	try {
		String sql="insert into pharmacy_patient_updatelog "
				+ "(clientid, old_fullname, new_fullname, old_mobile, new_mobile, "
				+ "old_address,new_address,old_phar_drid,new_phar_drid,datetime) values (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1, editpclinetid);
		ps.setString(2, priscription.getFullname());
		ps.setString(3, adfullname);
		ps.setString(4, priscription.getMobile());
		ps.setString(5, admobile);
		ps.setString(6, priscription.getAddress());
		ps.setString(7, adaddress);
		ps.setString(8, priscription.getPractitionerid());
		ps.setString(9, addoctor);
		ps.setString(10, datetime);
		res=ps.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
	
}

public int updatepharmacyaccess(String status, String userid, String colname) {
	PreparedStatement ps= null;
	int res =0;
	try {
		String sql=" update apm_pharmacy_user set "+colname+"='"+status+"' where id='"+userid+"'";
		ps= connection.prepareStatement(sql);
		int x= ps.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public double getOnlineReturn(String location, String fromdate, String todate) {

	double  res=0;
	try {
		 StringBuffer buffer=new StringBuffer();
		 buffer.append("select sum(debit) from apm_medicine_bill inner join apm_medicine_payment ");
		 buffer.append("on apm_medicine_payment.billno=apm_medicine_bill.id where ");
		 buffer.append("apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' and isreturn=1 ");
		 buffer.append("and paymode='Online Payment' ");
		 if(!location.equals("0")){
			 buffer.append("and apm_medicine_bill.location="+location+" ");
		 }
		 PreparedStatement ps=connection.prepareStatement(buffer.toString());
		 ResultSet rs=ps.executeQuery();
		 while(rs.next()){
			  
			 res=rs.getDouble(1);
		 }
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	return res;

}

public double getOnlinePayment(String location, String fromdate, String todate) {


	double  res=0;
	try {
		 String sql="";
		 if(!location.equals("0")){
			 sql="select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='Online Payment' and location='"+location+"' and (tpid=0 or tpid is NULL) ";
		 } else {
			 sql="select sum(payment) from apm_medicine_payment where date between '"+fromdate+"' and '"+todate+"' and paymode='Online Payment' and (tpid=0 or tpid is NULL) ";
		 }
		 
		 PreparedStatement ps=connection.prepareStatement(sql);
		 ResultSet rs=ps.executeQuery();
		 while(rs.next()){
			  
			 res=rs.getDouble(1);
		 }
		
	} catch (Exception e) {

		e.printStackTrace();
	} 
	
	return res;

}

public double getCardReturn(String location, String fromdate, String todate) {


	double  res=0;
	try {
		 StringBuffer buffer=new StringBuffer();
		 buffer.append("select sum(debit) from apm_medicine_bill inner join apm_medicine_payment ");
		 buffer.append("on apm_medicine_payment.billno=apm_medicine_bill.id where ");
		 buffer.append("apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"' and isreturn=1 ");
		 buffer.append("and paymode='Card' ");
		 if(!location.equals("0")){
			 buffer.append("and apm_medicine_bill.location="+location+" ");
		 }
		 PreparedStatement ps=connection.prepareStatement(buffer.toString());
		 ResultSet rs=ps.executeQuery();
		 while(rs.next()){
			  
			 res=rs.getDouble(1);
		 }
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	return res;


}

public int getPreviousSaleQty(int id) {
	int res=0;
	try {
		String sql ="select quantity from apm_medicine_charges where id='"+id+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			res = rs.getInt(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public int updateMedicineChargesQty(int newqty, int id) {
	int res =0;
	try {
		String sql ="update apm_medicine_charges set quantity='"+newqty+"' where id='"+id+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		res = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public ArrayList<Priscription> getMedicineReturnChargesList(int billno) {
	ArrayList<Priscription> arrayList = new ArrayList<Priscription>();
	try {
		String sql ="select id, charge, quantity, product_id, gstper from apm_medicine_charges where invoiceid="+billno+"";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			Priscription priscription = new Priscription();
			priscription.setId(rs.getInt(1));
			priscription.setCharges(rs.getDouble(2));
			priscription.setQty(rs.getInt(3));
			priscription.setProduct_id(rs.getString(4));
			priscription.setGstper(rs.getString(5));
			arrayList.add(priscription);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

public int updateEditPharmacyCharges(Priscription priscription) {
	int res =0;
	try {
		//cgst,sgst,discount_share,tgstamt,bill_purpice,bill_pur_gst 
		String sql ="update apm_medicine_charges set cgst=?,sgst=?,discount_share=?,"
				+ "tgstamt=?,bill_purpice=?,bill_pur_gst=? where id='"+priscription.getId()+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, priscription.getCgst());
		preparedStatement.setString(2, priscription.getSgst());
		preparedStatement.setString(3, priscription.getSharediscount());
		preparedStatement.setString(4, priscription.getTgstamt());
		preparedStatement.setString(5, ""+priscription.getBill_purpice());
		preparedStatement.setString(6, ""+priscription.getBill_pur_gst());
		res = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return 0;
}

public int updatePharmacyEditBill(int billno, double discount1, double grosssubtotal, double grosstotal, double balance,
		double totalvats, String discper, String notes, String datetime, String userid) {
	int res =0;
	try {
		double half= totalvats/2;
		half = Math.round(half*100.0)/100.0;
		String sql="update apm_medicine_bill set discount=?, debit=?, vat=?, balance=?, "
				+ "cgst=?, sgst=?, edited=?,credit=?, actual_total=?, actual_discount=?, "
				+ "disc_type=?, grosstotal=?, grosssubtotal=?, edit_note=?,"
				+ "bill_edituserid=?, bill_editdatetime=?  where id="+billno+"";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, ""+Math.round(discount1*100.0)/100.0);
		preparedStatement.setString(2, ""+balance);
		preparedStatement.setString(3, ""+Math.round(totalvats*100.0)/100.0);
		preparedStatement.setString(4, ""+balance);
		preparedStatement.setString(5, ""+half);
		preparedStatement.setString(6, ""+half);
		preparedStatement.setString(7, ""+1);
		preparedStatement.setString(8, ""+balance);
		preparedStatement.setString(9, ""+grosssubtotal);
		preparedStatement.setString(10, ""+Math.round(Double.parseDouble(discper)*100.0)/100.0);
		preparedStatement.setString(11, ""+0);
		preparedStatement.setString(12, ""+Math.round(grosstotal*100.0)/100.0);
		preparedStatement.setString(13, ""+grosssubtotal);
		preparedStatement.setString(14, notes);
		preparedStatement.setString(15, userid);
		preparedStatement.setString(16, datetime);
		res =preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public boolean checkProductAddedForSameClient(String pid, int tempclientid, int ispharmacypatient,
		String loginsessionid) {

	boolean res =false;
	try {
		String sql="select * from pharmacy_sale_temp_charge where productid='"+pid+"' and clientid='"+tempclientid+"' and ispharmacy='"+ispharmacypatient+"' and loginsessionid=? ";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, loginsessionid);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			res= true;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;

}

public int getPharmacyPatientCount(String searchtext) {
	int res =0;
	try {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select count(*) from apm_pharmacy_client ");
		buffer.append("left join apm_user on apm_user.id = apm_pharmacy_client.phar_drid ");
		if(!searchtext.equals("")){
			buffer.append("where apm_pharmacy_client.fullname like '"+searchtext+"%' or apm_pharmacy_client.abrivationid='"+searchtext+"' or apm_pharmacy_client.mobile='"+searchtext+"' ");
		}
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			res=rs.getInt(1);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public ArrayList<Client> getPharmacyPatientList(Pagination pagination, String searchtext) {
	ArrayList<Client> arrayList = new ArrayList<Client>();
	try {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select apm_pharmacy_client.id, apm_pharmacy_client.fullname, apm_pharmacy_client.address, ");
		buffer.append("apm_pharmacy_client.mobile, apm_pharmacy_client.abrivationid,apm_pharmacy_client.reference, ");
		buffer.append("apm_pharmacy_client.phar_drid,concat(apm_user.initial,' ',apm_user.firstname,' ',apm_user.lastname) ");
		buffer.append("from apm_pharmacy_client ");
		buffer.append("left join apm_user on apm_user.id = apm_pharmacy_client.phar_drid ");
		if(!searchtext.equals("")){
			buffer.append("where apm_pharmacy_client.fullname like '"+searchtext+"%' or apm_pharmacy_client.abrivationid='"+searchtext+"' or apm_pharmacy_client.mobile='"+searchtext+"' ");
		}
		buffer.append("order by id desc ");
		String sql="";
		sql=pagination.getSQLQuery(buffer.toString());
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			Client client = new Client();
			client.setId(rs.getInt(1));
			client.setFullname(rs.getString(2));
			client.setAddress(rs.getString(3));
			client.setMobNo(rs.getString(4));
			if(DateTimeUtils.numberCheck(rs.getString(5)).equals("0")){
				client.setAbrivationid("");
			}else{
				client.setAbrivationid(rs.getString(5));
			}
			if(rs.getInt(7)>0){
				client.setDiaryUser(rs.getString(8));
			}else{
				client.setDiaryUser(rs.getString(6));
			}
			arrayList.add(client);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

@Override
public String getfakedatebyClientid(String fakclientid) {
	// TODO Auto-generated method stub
	
	PreparedStatement preparedStatement=null;
	String result="";
	try {
		String sql="select min(id),date from apm_medicine_bill where clientid= '"+fakclientid+"' ";
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			
			result=rs.getString(2);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

@Override
public String getdatebyClientid(String fakclientid) {
	PreparedStatement preparedStatement=null;
	String result="";
	try {
		String sql="select min(id),date from apm_parent_prisc where clientid= '"+fakclientid+"' ";
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			
			result=rs.getString(2);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
	
}

@Override
public int getproductid(int billno) {
	PreparedStatement preparedStatement=null;
	int prodid=0;
	try {
		String sql="select product_id from apm_medicine_charges where invoiceid= '"+billno+"' limit 0,1";
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			
			prodid=rs.getInt(1);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return prodid;
	
}

@Override
public int getCatalogueid(int productid) {
	PreparedStatement preparedStatement=null;
	int catalogueid=0;
	try {
		String sql="select catalogueid from inventory_product where id= '"+productid+"' ";
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			
			catalogueid=rs.getInt(1);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return catalogueid;
	
}

@Override
public int getsubcategoryid(int cataloguid) {
	PreparedStatement preparedStatement=null;
	int subcategoryid=0;
	try {
		String sql="select subcategoryid from inventory_catalogue where id= '"+cataloguid+"' ";
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			
			subcategoryid=rs.getInt(1);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return subcategoryid;
	
}

@Override
public String getsubcategorynameById(int subcategoryid) {
	PreparedStatement preparedStatement=null;
	String subcategoryname="";
	try {
		String sql="select name from inventory_subcategory where id= '"+subcategoryid+"' ";
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			
			subcategoryname=rs.getString(1);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return subcategoryname;
	
}

@Override
public int getlocationforParihar(String subcategoryname) {
	PreparedStatement preparedStatement=null;
	int location=0;
	try {
		String sql="select id from apm_condition where name= '"+subcategoryname+"' ";
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			
			location=rs.getInt(1);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return location;
	
}

@Override
public int getUpdatelocationforParihar(int parlocation, int billno) {
	int result=0;
	try {

		String sql="update apm_medicine_bill set location="+parlocation+" where id="+billno+"";
		PreparedStatement ps=connection.prepareStatement(sql);
		result=ps.executeUpdate();
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return result;
	
}

@Override
public ArrayList<Priscription> getAllPharmacySaleListNew1(Pagination pagination, String searchtext, String fromdate,
		String todate, String location, String isreturn, String paymode, String userid, LoginInfo loginInfo) {
	ArrayList<Priscription> list= new ArrayList<Priscription>();
	try {
		
		StringBuffer buffer= new StringBuffer();
		buffer.append("select apm_medicine_bill.id,apm_medicine_bill.date,apm_medicine_bill.debit,apm_medicine_bill.clientid,apm_medicine_bill.pclientid, ");
		buffer.append("isreturn,apm_medicine_bill.refundamt,deleted,apm_medicine_bill.balance,apm_medicine_payment.payment,apm_medicine_bill.time,retunbillid, ");
		buffer.append("apm_medicine_bill.userid,apm_medicine_payment.paymode,apm_medicine_bill.refundid,apm_medicine_payment.date,apm_condition.name, ");
		buffer.append("apm_medicine_payment.paynote,apm_medicine_payment.userid,discount,apm_medicine_payment.billno,isreturn from apm_medicine_bill ");
		buffer.append("left join apm_medicine_payment on apm_medicine_bill.id=apm_medicine_payment.billno ");
		buffer.append("left join apm_pharmacy_client on apm_pharmacy_client.id = apm_medicine_bill.pclientid ");
		buffer.append("left join apm_patient on apm_patient.id = apm_medicine_bill.clientid ");
		buffer.append("left join apm_condition on apm_condition.id = apm_medicine_bill.location ");
		if(loginInfo.isTime_utility_report()) {
			buffer.append("where apm_medicine_payment.datetime between '"+fromdate+"' and '"+todate+"' ");
		}else {
			buffer.append("where apm_medicine_payment.date between '"+fromdate+"' and '"+todate+"' ");
		}
		
		//buffer.append("where apm_medicine_payment.date between '"+fromdate+"' and '"+todate+"' ");
		if(!location.equals("0")){
			buffer.append("and apm_medicine_bill.location='"+location+"' ");
		}
		if(isreturn.equals("1")){
			buffer.append("and apm_medicine_bill.isreturn=1 ");
		} else if(isreturn.equals("2")) {
			buffer.append("and apm_medicine_bill.isreturn=0 ");
		}else if(isreturn.equals("3")){
			buffer.append("and apm_medicine_bill.deleted=1 ");
		}
		
		if(!paymode.equals("0")){
			buffer.append("and apm_medicine_payment.paymode='"+paymode+"' ");
		}
		
		if(searchtext!=null){
			buffer.append("and  (apm_patient.firstname like ('"+searchtext+"%') or apm_patient.surname like ('"+searchtext+"%') or apm_patient.abrivationid like ('"+searchtext+"') ");
			buffer.append("or apm_patient.middlename like ('"+searchtext+"%') or apm_patient.fullname like ('"+searchtext+"%') or apm_medicine_bill.id like ('"+searchtext+"') ");
			buffer.append("or apm_pharmacy_client.fullname like ('"+searchtext+"%') or apm_pharmacy_client.fullname like ('%"+searchtext+"') ) ");
		}
		if(!userid.equals("0")){
			buffer.append("and apm_medicine_payment.userid='"+userid+"' ");
		}
		buffer.append("and (apm_medicine_bill.tpid='0' || apm_medicine_bill.tpid is NULL) ");
		/*buffer.append("group by apm_medicine_bill.id order by  apm_medicine_bill.id  asc ");*/
		buffer.append("order by  apm_medicine_bill.id  asc ");
		String sql =buffer.toString();
		/*
		 * if(pagination!=null){ sql= pagination.getSQLQuery(sql); }
		 */
		
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		double totalrefundonline=0;
		double totalReceived=0;
		double totalRefund=0;
		double totalBalance=0;
		double totalcredit=0;
		double totaldiscount=0;
		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		ClientDAO clientDAO= new JDBCClientDAO(connection);
		BedDao bedDao =new JDBCBedDao(connection);
		UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
		while(rs.next()){
			
			Priscription priscription=new Priscription();
			priscription.setId(rs.getInt(1));
			priscription.setBillno(rs.getString(1));
			priscription.setDate(DateTimeUtils.getCommencingDate1(rs.getString(2)));
			priscription.setDebit(rs.getDouble(3));
			priscription.setTotal(rs.getDouble(3));
			priscription.setClientId(rs.getString(4));
			priscription.setPclientid(rs.getString(5));
			priscription.setIsreturn(rs.getInt(6));
			priscription.setReturnbill(rs.getInt(6));
			double refundtot= rs.getDouble(7);
			priscription.setDeleted(rs.getInt(8));
			priscription.setBalance(rs.getString(9));
			/*double payamt= getTotalPaymentReceived(rs.getInt(1));*/
			double payamt= rs.getDouble(10);
			priscription.setPayment(DateTimeUtils.changeFormat(payamt));
			String dateTime = priscription.getDate()+" "+rs.getString(11);
			priscription.setReturnbillid(rs.getInt(12));
			priscription.setUserid(rs.getString(13));
			priscription.setPaymode(rs.getString(14));
			//priscription.setNewpaymentmode(getBillPaymodeNew(rs.getInt(1)));
			//for parihar paymode cash
			double refundamt=getrefundamt(rs.getDouble(21));
			double refundamtonline=getrefundamtonline(rs.getDouble(21));
			if(loginInfo.isParihar()) {
				totalRefund=totalRefund+refundamt;
				totalrefundonline=totalrefundonline+refundamtonline;
			}else {
				if(rs.getInt(6)>0){
				totalRefund = totalRefund+rs.getDouble(3);
			   }
			}
			priscription.setRefund_amt(refundamt);
			if(rs.getInt(15)>0){
				priscription.setRefundid(rs.getInt(15));
			} else {
				priscription.setRefundid(priscription.getId());
			}
			priscription.setCardno(rs.getString(18));
		    priscription.setLastmodified(dateTime);
		    String date2="";
		    try {
		    	date2 =DateTimeUtils.getCommencingDate1(rs.getString(16));
			} catch (Exception e) {
				// TODO: handle exception
			}
		    priscription.setPaymentdate(date2);
		    priscription.setDelivered(1);
		    double credit =0;
		    if(rs.getString(14).equals("Credit")){
		    	totalcredit = totalcredit + rs.getDouble(3);
		    	credit = rs.getDouble(3);
		    }
		    priscription.setTotalcredit(DateTimeUtils.changeFormat(totalcredit));
		    priscription.setCredit(credit);
			if(priscription.getIsreturn()>0 && rs.getDouble(3)==0){
				priscription.setTotal(refundtot);
			}
			
			
			//  14 July 2018
			totalReceived = totalReceived +payamt;
			totalBalance = totalBalance+ rs.getDouble(9);
			priscription.setTotalBalance(totalBalance);
			priscription.setTotalReceived(totalReceived);
			priscription.setTotalrefund(totalRefund);
			priscription.setTotalrefund_online(totalrefundonline);
			if(rs.getInt(5)>0){
				  // pharmacy patient
				  Priscription masterClient = getPharmacyPatient(priscription.getPclientid());
				  priscription.setFullname(masterClient.getFullname());
				  priscription.setMobile(masterClient.getMobile());
				  priscription.setPractitionername(masterClient.getPractitionername());
				  priscription.setAddress(masterClient.getAddress());
				  priscription.setAgeandgender(masterClient.getAgeandgender());
				  priscription.setOutp(1);
				  if(!DateTimeUtils.numberCheck(masterClient.getAbrivationid()).equals("0")){
					  priscription.setAbrivationid(masterClient.getAbrivationid());
				  }else{
					  priscription.setAbrivationid("");
				  }
				  
			} else {
				   //ipd/opd patient
				  
				   Client client =clientDAO.getClientDetails(priscription.getClientId()); 
				   String fullname =client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();  
				   priscription.setFullname(fullname);
				   String agegender= DateTimeUtils.getAge(client.getDob()) +" "+client.getGender();
				   priscription.setAgeandgender(agegender);
				   String ipdid = getIpdIdFromClientID(Integer.parseInt(priscription.getClientId()));
					String pract_name ="";
					if(ipdid.equals("0")){
						pract_name = getappointmentinfo(Integer.parseInt(priscription.getClientId()));
					}else{
						
						
						Bed  bed = bedDao.getEditIpdData(ipdid);
						
						pract_name = profileDAO.getFullName(bed.getPractitionerid());
						String wardname = ipdDAO.getIpdWardName(bed.getWardid());
						//@ruchi to check casualty and icu patient to blink
						String urgentward = checkICUorCasulty(bed.getWardid());
						
						if (!urgentward.equals("")) {
							priscription.setUrgentward("1");
						}
						//end
						String bedname= ipdDAO.getIpdBedName(bed.getBedid());
						priscription.setWardname(wardname);
						priscription.setBedname(bedname);
						
					}
					priscription.setIpdid(ipdid);
					priscription.setPractitionername(pract_name);
					priscription.setAddress(client.getAddress());
					priscription.setAbrivationid(client.getAbrivationid());
				    priscription.setMobile(client.getMobNo());
				    priscription.setOutp(0);
				    
			}

			if(rs.getString(17)==null){
				priscription.setLocationame("");
			}else{
				priscription.setLocationame(rs.getString(17));
			}
			
			priscription.setPaymentuserid(rs.getString(19));
			priscription.setDiscount(rs.getDouble(20));
			totaldiscount=totaldiscount+rs.getDouble(20);
			priscription.setTotaldisc(totaldiscount);
			list.add(priscription);
		}
}catch (Exception e) {
	e.printStackTrace();
}
	return list;
	
}

public String getLocationfromBillNo(int billno) {
	PreparedStatement preparedStatement=null;
	String location="";
	try {
		String sql="select location from apm_medicine_bill where id= '"+billno+"' ";
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			location=(rs.getString(1));
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return location;
	
}

public String getlocationname(String location_id) {
	PreparedStatement preparedStatement=null;
	String location_name="";
	try {
		String sql="select name from apm_condition where id= '"+location_id+"' ";
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			location_name=(rs.getString(1));
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return location_name;
	
}

private double getrefundamtonline(double billno) {
	PreparedStatement preparedStatement=null;
	double refundamtonline=0;
	try {
		String sql="select refundamt from apm_medicine_payment where billno= '"+billno+"' and paymode='Online Payment' ";
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			
			refundamtonline=rs.getDouble(1);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return refundamtonline;
	
}

private double getrefundamt(double billno) {
	PreparedStatement preparedStatement=null;
	double refundamt=0;
	try {
		String sql="select refundamt from apm_medicine_payment where billno= '"+billno+"' and paymode='Cash' ";
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			
			refundamt=rs.getDouble(1);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return refundamt;
	
}

@Override
public int getAllPharmacySaleCountNew1(String searchtext, String fromdate, String todate, String location,
		String isreturn, String paymode, String userid, LoginInfo loginInfo) {
	int result=0;
	try {
		
		/*StringBuffer buffer= new StringBuffer();
		buffer.append("select count(*) from apm_medicine_bill ");
		buffer.append("where apm_medicine_bill.date between '"+fromdate+"' and '"+todate+"'  ");
		if(!location.equals("0")){
			buffer.append("and apm_medicine_bill.location="+location+" ");
		}
		if(isreturn.equals("1")){
			buffer.append("and apm_medicine_bill.isreturn=1 ");
		}*/
		
		StringBuffer buffer= new StringBuffer();
		/*buffer.append("select apm_medicine_bill.id,apm_medicine_bill.date,apm_medicine_bill.debit,apm_medicine_bill.clientid,apm_medicine_bill.pclientid,isreturn,apm_medicine_bill.refundamt,deleted,balance,apm_medicine_payment.payment,apm_medicine_bill.time,retunbillid,apm_medicine_bill.userid,apm_medicine_payment.paymode,apm_medicine_bill.refundid ");
		buffer.append("from apm_medicine_bill left join apm_medicine_payment on apm_medicine_bill.id=apm_medicine_payment.billno  ");*/
		buffer.append("select count(*) from apm_medicine_bill ");
		buffer.append("left join apm_medicine_payment on apm_medicine_bill.id=apm_medicine_payment.billno ");
		buffer.append("left join apm_pharmacy_client on apm_pharmacy_client.id = apm_medicine_bill.pclientid ");
		buffer.append("left join apm_patient on apm_patient.id = apm_medicine_bill.clientid ");
		
		buffer.append("where apm_medicine_payment.datetime between '"+fromdate+"' and '"+todate+"' ");
		if(!location.equals("0")){
			buffer.append("and apm_medicine_bill.location='"+location+"' ");
		}
		if(isreturn.equals("1")){
			buffer.append("and apm_medicine_bill.isreturn=1 ");
		} else if(isreturn.equals("2")) {
			buffer.append("and apm_medicine_bill.isreturn=0 ");
		}else if(isreturn.equals("3")){
			buffer.append("and apm_medicine_bill.deleted=1 ");
		}
		
		if(!paymode.equals("0")){
			buffer.append("and apm_medicine_payment.paymode='"+paymode+"' ");
		}
		
		if(searchtext!=null){
			buffer.append("and  (apm_patient.firstname like ('"+searchtext+"%') or apm_patient.surname like ('"+searchtext+"%') or apm_patient.abrivationid like ('"+searchtext+"') ");
			buffer.append("or apm_patient.middlename like ('"+searchtext+"%') or apm_patient.fullname like ('"+searchtext+"%') or apm_medicine_bill.id like ('"+searchtext+"') ");
			buffer.append("or apm_pharmacy_client.fullname like ('"+searchtext+"%') or apm_pharmacy_client.fullname like ('%"+searchtext+"') ) ");
		}
		if(!userid.equals("0")){
			buffer.append("and apm_medicine_payment.userid='"+userid+"' ");
		}
		buffer.append("and (apm_medicine_bill.tpid='0' || apm_medicine_bill.tpid is NULL) ");
		buffer.append("order by  apm_medicine_bill.id  asc ");
		
		PreparedStatement ps=connection.prepareStatement(buffer.toString());
		ResultSet rs =ps.executeQuery();
		
		while(rs.next()){
			 result =rs.getInt(1);
		}
		
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return result;
	
}

@Override
public ArrayList<Priscription> getAllPaymentList(String fromdate, String todate,String location,double totalRefund,double totalRefundonline) {
	ArrayList<Priscription> arrayList = new ArrayList<Priscription>();
	StringBuffer buffer= new StringBuffer();
	try {
		buffer.append("select sum(payment),paymode from apm_medicine_payment inner join apm_medicine_bill on apm_medicine_bill.id=apm_medicine_payment.billno where datetime between '"+fromdate+"' and '"+todate+"' ");
		
		if(!location.equals("0")){
			buffer.append("and apm_medicine_bill.location='"+location+"' ");
		}
		buffer.append("group by paymode ");
		String sql =buffer.toString();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			Priscription priscription=new Priscription();
			if(rs.getString(2).equals("Cash")) {
				priscription.setTotalamt(rs.getDouble(1)-totalRefund);
			}else if(rs.getString(2).equals("Online Payment")){
				priscription.setTotalamt(rs.getDouble(1)-totalRefundonline);
			}else {
				priscription.setTotalamt(rs.getDouble(1));
			}
			//priscription.setTotalamt(rs.getDouble(1));
			priscription.setPaymode(rs.getString(2));
			arrayList.add(priscription);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

@Override
public double getTotaldiscount(String fromdate, String todate, String location) {
	StringBuffer buffer= new StringBuffer();
	double totaldiscount=0;
	try {
		buffer.append("select sum(discount) from apm_medicine_bill inner join apm_medicine_payment on apm_medicine_payment.billno=apm_medicine_bill.id where datetime between '"+fromdate+"' and '"+todate+"' ");
		
		if(!location.equals("0")){
			buffer.append("and apm_medicine_bill.location='"+location+"' ");
		}
		//buffer.append("group by paymode ");
		String sql =buffer.toString();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			totaldiscount=rs.getDouble(1);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return totaldiscount;
}

@Override
public double getTotalRefundAmount(String fromdate, String todate, String location) {
	StringBuffer buffer= new StringBuffer();
	double totaldiscount=0;
	try {
        buffer.append("select sum(refundamt) from apm_medicine_payment inner join apm_medicine_bill on apm_medicine_bill.id=apm_medicine_payment.billno where datetime between '"+fromdate+"' and '"+todate+"' ");
		
		if(!location.equals("0")){
			buffer.append("and apm_medicine_bill.location='"+location+"' ");
		}
		
		//buffer.append("group by paymode ");
		String sql =buffer.toString();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			totaldiscount=rs.getDouble(1);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return totaldiscount;
}

@Override
public String getthirdpartynameidByClientid(String clientId) {
	
	PreparedStatement preparedStatement=null;
	String third_name_id="";
	try {
		String sql="select third_party_name_id from apm_patient where id= '"+clientId+"' ";
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			third_name_id=(rs.getString(1));
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return third_name_id;
}

}

