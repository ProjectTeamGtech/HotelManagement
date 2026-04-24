package com.apm.Inventory.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.ArrayList;

import com.a.a.a.a.a.b;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.bi.PoPaymenytDAO;
import com.apm.Inventory.eu.bi.ProcurementDAO;
import com.apm.Inventory.eu.entity.Procurement;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Inventory.eu.entity.Vendor;
import com.apm.Pharmacy.eu.bi.PharmacyDAO;
import com.apm.Pharmacy.eu.blogic.jdbc.JDBCPharmacyDAO;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;

public class JDBCPoPaymengtDAO extends JDBCBaseDAO implements PoPaymenytDAO{
	
	public JDBCPoPaymengtDAO(Connection connection){
		this.connection = connection;
	}

	public ArrayList<Procurement> getPoPaymentList(String location,String searchtext, Pagination pagination) {
		
		
		ProcurementDAO procurementDAO= new JDBCProcurementDAO(connection); 
		
		PreparedStatement preparedStatement = null;
		ArrayList<Procurement>list  = new ArrayList<Procurement>();
		String sql  = "";
		
		/*if(!location.equals("0")){
			sql  = "SELECT id,prodid,date,vendorid,total,procurementid,brandid,voucherno,total FROM inventory_procurement where voucherno is NOT NULL and location='"+location+"' group by vendorid order by id desc";
		} else {
			sql  = "SELECT id,prodid,date,vendorid,total,procurementid,brandid,voucherno,total FROM inventory_procurement where voucherno is NOT NULL  group by vendorid order by id desc";
		}*/
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT inventory_procurement.id,prodid,date,inventory_procurement.vendorid,total,procurementid,brandid,voucherno,total FROM inventory_procurement ");
		buffer.append("inner join inventory_parent_procurement on inventory_procurement.procurementid =inventory_parent_procurement.id ");
		if(searchtext!=null){
			buffer.append("inner join inventory_vendor on inventory_vendor.id = inventory_procurement.vendorid ");
		}
		if(!location.equals("0")){
			buffer.append("where voucherno is NOT NULL and location='"+location+"' ");
		} else {
			buffer.append("where voucherno is NOT NULL ");
		}
		if(searchtext!=null){
			buffer.append("and name like ('"+searchtext+"%') ");
		}
		buffer.append("and isdelivermemo=0 and voucherno!='' ");
		buffer.append("group by vendorid order by id desc ");
		try{
			String sql1 = pagination.getSQLQuery(buffer.toString());
			preparedStatement = connection.prepareStatement(sql1);
			ResultSet rs = preparedStatement.executeQuery();
			double totalorderamt=0;
			double paidpayment=0;
			double returnsettledamt=0;
			double totalpaidamt=0;
			double balanceamt=0;
			
			while(rs.next()){
				Procurement procurement = new Procurement();
				procurement.setId(rs.getInt(1));
				
				String prodname = getProdName(rs.getInt(2));
				procurement.setProduct(prodname);
				
				procurement.setDate(DateTimeUtils.getCommencingDate1(rs.getString(3)));
				
				InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
				Vendor vendor=vendorDAO.getVendor(rs.getString(4));
				procurement.setVendor_id(rs.getString(4));
				procurement.setVendor(vendor.getName());
				
				procurement.setProcurementid(rs.getString(6));
				double total=getTotalAountoFVendor(procurement.getVendor_id());
				procurement.setTotal(DateTimeUtils.changeFormat(total));				
				String brandname = getBrandName(rs.getString(7));
				procurement.setBrand(brandname);
				
				//get balance
				double sumofpayment =  getTotalPaid(procurement.getVendor_id()); //getSumOfPayment(rs.getString(6));
				Procurement procurement2 = getPaymentAmtOfRefund(procurement.getVendor_id());
				procurement.setPaidpayment(procurement2.getPaidpayment());
				procurement.setReturnpayment(procurement2.getReturnpayment());
				procurement.setPaymentAmount(DateTimeUtils.changeFormat(sumofpayment));
				double balance = Double.parseDouble(procurement.getTotal()) - sumofpayment;
				if(balance<0){
					balance=0;
				}
				
				procurement.setPurchase_price(rs.getString(9));
				
				
				procurement.setBalance(DateTimeUtils.changeFormat(balance));
				
				Product product =procurementDAO.getVendorReturnAccountData(rs.getString(4));
				if(product!=null){
					procurement.setDebit(product.getDebit());
					procurement.setCredit(product.getCredit());
				} else {
					procurement.setDebit("00.00");
					procurement.setCredit("00.00");
				}
				
				procurement.setVoucherno(rs.getString(8));
				
				totalorderamt=totalorderamt+total;
				paidpayment=paidpayment+Double.parseDouble(procurement2.getPaidpayment());
				returnsettledamt=returnsettledamt+Double.parseDouble(procurement2.getReturnpayment());
				totalpaidamt=totalpaidamt+sumofpayment;
				balanceamt=balanceamt+balance;
				
				procurement.setTotalorderamt(totalorderamt);
				procurement.setTotalpaymentamt(paidpayment);
				procurement.setTotalreturnsettledamt(returnsettledamt);
				procurement.setTotalpaidamt(totalpaidamt);
				procurement.setTotalbalanceamt(balanceamt);
				
				list.add(procurement);
				
				
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	
	
	
	public Procurement getPaymentAmtOfRefund(String vendor_id) {
		Procurement procurement = new Procurement();
		try {
			String sql="select sum(total_amt),sum(sumofreturn) from inventory_paymentpo_parent where vendorid='"+vendor_id+"' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				 procurement.setPaidpayment(DateTimeUtils.changeFormat(rs.getDouble(1)));
				 procurement.setReturnpayment(DateTimeUtils.changeFormat(rs.getDouble(2)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return procurement;
	}

	public double getTotalSubByProcurement(String procurementid) {
		
		double result=0;
		try {
			
			String sql="select sum(total) from inventory_procurement where procurementid="+procurementid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				result=rs.getDouble(1);
			} 
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}
	
	
	private String getProdName(int prodid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT prodname FROM inventory_product where id = "+prodid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public Procurement getPaymentHistoryDetails(String procurementid) {
		PreparedStatement preparedStatement = null;
		Procurement procurement = new Procurement();
		String sql  = "SELECT id,prodid,date,vendorid,sum(total),brandid FROM inventory_procurement where procurementid = "+procurementid+" group by vendorid ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				procurement.setId(rs.getInt(1));
				
				String prodname = getProdName(rs.getInt(2));
				procurement.setProd_id(rs.getString(2));
				procurement.setProduct(prodname);
				
				procurement.setDate(rs.getString(3));
				
				InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
				Vendor vendor=vendorDAO.getVendor(rs.getString(4));
				procurement.setVendor_id(rs.getString(4));
				procurement.setVendor(vendor.getName());
				
				
				procurement.setTotal(rs.getString(5));
				
				String brandname = getBrandName(rs.getString(6));
				procurement.setBrand(brandname);
				
				
				
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return procurement;
	}

	private String getBrandName(String brandid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT name FROM inventory_brandname where id = "+brandid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int savePayment(Procurement procurement, int paymentids) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into inventory_paymentpo(procurementid, payamount, paytype, bankname, chequeno, commencing, chequetype, handoverto,voucherno,cheq_receiver,parentid,bankid) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, procurement.getProcurementid());
			preparedStatement.setString(2, procurement.getPaymentAmount());
			preparedStatement.setString(3, procurement.getPaymentType());
			preparedStatement.setString(4, procurement.getBankName());
			preparedStatement.setString(5, procurement.getCheqNo());
			preparedStatement.setString(6, procurement.getPaymentDate());
			preparedStatement.setString(7, procurement.getCheqType());
			preparedStatement.setString(8, procurement.getHandoverTo());
			preparedStatement.setString(9, procurement.getVoucherno());
			preparedStatement.setString(10,procurement.getCheq_receiver());
			preparedStatement.setString(11, ""+paymentids);
			preparedStatement.setString(12, procurement.getBankid());
			result = preparedStatement.executeUpdate();
			
			
			if(result>0){
				ResultSet rs= preparedStatement.getGeneratedKeys();
				if(rs.next()){
					result =rs.getInt(1);
				}
				
			}
			
		}catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Procurement> getPaymentRecieptList(String selectedid,String vendor) {
		PreparedStatement preparedStatement = null;
		ArrayList<Procurement>list = new ArrayList<Procurement>();
		String sql = "SELECT handoverto,paytype,chequeno,chequetype,payamount,commencing FROM inventory_paymentpo where procurementid = "+selectedid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Procurement procurement = new Procurement();
				procurement.setHandoverTo(rs.getString(1));
				procurement.setPaymentType(rs.getString(2));
				procurement.setCheqNo(rs.getString(3));
				procurement.setCheqType(rs.getString(4));
				procurement.setPaymentAmount(rs.getString(5));
				procurement.setPaymentDate(rs.getString(6));
				procurement.setVendor(vendor);
				
				list.add(procurement);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}

	
	public ArrayList<Procurement> getPaymentRecieptListByVendor(String vendorid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Procurement>list = new ArrayList<Procurement>();
		InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
		ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
		StringBuffer buffer= new StringBuffer();
		buffer.append("SELECT handoverto,paytype,chequeno,chequetype,sum(payamount),commencing,voucherno,cheq_receiver,inventory_paymentpo.id,inventory_paymentpo.procurementid,inventory_paymentpo_parent.id,total_amt,sumofreturn FROM inventory_paymentpo ");
		buffer.append("inner join inventory_parent_procurement on inventory_paymentpo.procurementid= inventory_parent_procurement.id ");
		buffer.append("inner join inventory_paymentpo_parent on inventory_paymentpo_parent.id = inventory_paymentpo.parentid ");
		buffer.append("where inventory_parent_procurement.vendorid="+vendorid+" ");
		buffer.append("group by inventory_paymentpo.parentid  order by inventory_paymentpo_parent.id desc");
		
		try{
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double totalamt=0;
			while(rs.next()){
				Procurement procurement = new Procurement();
				totalamt = totalamt + rs.getDouble(5);
				procurement.setTotalnetamt(totalamt);
				procurement.setHandoverTo(rs.getString(1));
				procurement.setPaymentType(rs.getString(2));
				procurement.setCheqNo(rs.getString(3));
				procurement.setCheqType(rs.getString(4));
				procurement.setPaymentAmount(rs.getString(5));
				procurement.setPaymentDate(rs.getString(6));
				procurement.setVoucherno(rs.getString(7));
				procurement.setCheq_receiver(rs.getString(8));
				Vendor vendor=vendorDAO.getVendor(vendorid);
				procurement.setVendor(vendor.getName());
				procurement.setPaymentNo(rs.getInt(9));
				 int res=procurementDAO.getProcurmentSeqNo(rs.getString(10));
				 String grnno="";
				 if(res>0){
					 grnno=String.valueOf(res);
				 }else{
					 grnno=rs.getString(10);
				 }
				 procurement.setGrnno(grnno);
				 procurement.setParentid(""+rs.getInt(11));
				 procurement.setPaidpayment(DateTimeUtils.changeFormat(rs.getDouble(12)));
				 procurement.setReturnpayment(DateTimeUtils.changeFormat(rs.getDouble(13)));
				 list.add(procurement);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public double getSumOfPayment(String selectedid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT sum(payamount) FROM inventory_paymentpo where procurementid = "+selectedid+" group by procurementid  ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	public double getTotalPaid(String vendor_id) {
		double result=0;
		try {
			StringBuffer buffer=new StringBuffer();
			buffer.append("select sum(payamount) from inventory_paymentpo inner join ");
			buffer.append("inventory_parent_procurement on inventory_paymentpo.procurementid= inventory_parent_procurement.id where ");
			buffer.append("inventory_parent_procurement.vendorid="+vendor_id+" ");
			
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				  
				  result= rs.getDouble(1);
			} 
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public double getTotalAmount(String vendor_id) {

		double result=0;
		try {
			StringBuffer buffer=new StringBuffer();
			buffer.append("select sum(total) from inventory_procurement where vendorid="+vendor_id+" and voucherno is NOT NULL ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 
				 result=rs.getDouble(1);
			}
			 double vat = getTotalVat(vendor_id);
			 double discount= getTotalDiscount(vendor_id);
			 double debit= getTotalDedit(vendor_id);
			 double credit =getTotalCredit(vendor_id);
			 double surcharge= getTotalSurcharge(vendor_id);
			 
			 double plus=result+vat+credit+surcharge; 
			 double minus=discount+debit; 
			 
			 result = plus-minus;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public double getTotalAountoFVendor(String vendorid) {

		double tot=0;
		try {
			String sql="select procurementid,voucherno from inventory_procurement where vendorid="+vendorid+" and deleted!=1 and voucherno is NOT NULL group by procurementid ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery(); 
			while(rs.next()){
				String procurementid =rs.getString(1);
				String voucherno= rs.getString(2);
				double temp= getTotalVoucherAmount(voucherno, procurementid, vendorid);
				tot= tot+temp;
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return tot;
	}
	
	
	
	public double getTotalVat(String vendor_id) {

		double result=0;
		try {
			StringBuffer buffer=new StringBuffer();
			buffer.append("select sum(vat) from inventory_vendor_procurement where vendorid="+vendor_id+" and voucherno is NOT NULL ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 
				 result=rs.getDouble(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public double getTotalCredit(String vendor_id) {

		double result=0;
		try {
			StringBuffer buffer=new StringBuffer();
			buffer.append("select sum(credit) from inventory_vendor_procurement where vendorid="+vendor_id+" and voucherno is NOT NULL ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 
				 result=rs.getDouble(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public double getTotalSurcharge(String vendor_id) {

		double result=0;
		try {
			StringBuffer buffer=new StringBuffer();
			buffer.append("select sum(surcharge) from inventory_vendor_procurement where vendorid="+vendor_id+" and voucherno is NOT NULL ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 
				 result=rs.getDouble(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public double getTotalDedit(String vendor_id) {

		double result=0;
		try {
			StringBuffer buffer=new StringBuffer();
			buffer.append("select sum(debit) from inventory_vendor_procurement where vendorid="+vendor_id+" and voucherno is NOT NULL ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 
				 result=rs.getDouble(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public double getTotalDiscount(String vendor_id) {

		double result=0;
		try {
			StringBuffer buffer=new StringBuffer();
			buffer.append("select sum(discount) from inventory_vendor_procurement where vendorid="+vendor_id+" and voucherno is NOT NULL ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 
				 result=rs.getDouble(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Product> getAllVoucherList(String vendorid) {

		ArrayList<Product> list= new ArrayList<Product>();
		ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		try {
			//String sql="SELECT id,voucherno,prodid,procurementid,date from inventory_procurement where vendorid="+vendorid+" and deleted=0 group by procurementid order by id desc";
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT inventory_procurement.id,voucherno,prodid,procurementid,date,inventory_procurement.location from inventory_procurement ");
			buffer.append("inner join inventory_parent_procurement on inventory_procurement.procurementid = inventory_parent_procurement.id ");
			buffer.append("where inventory_parent_procurement.vendorid="+vendorid+" and deleted=0 and isdelivermemo=0 and prodid is not null group by procurementid order by id desc");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			double totalpaidamt=0;
			double totalbalance =0;
			double totalAmt =0;
			while(rs.next()){
				 Product product=new Product();
				 product.setId(rs.getInt(1));
				 product.setVoucherno(rs.getString(2));
				 product.setProduct_id(rs.getString(3));
				 product.setProcurementid(rs.getString(4));
				 double total= getTotalVoucherAmount(product.getVoucherno(),product.getProcurementid(),vendorid);
				 product.setTotal(DateTimeUtils.changeFormat(total));
				 product.setDate(DateTimeUtils.getCommencingDate1(rs.getString(5)));
				 product.setVendor_id(vendorid);
				 product.setLocation(rs.getString(6));
				 String locationName = pharmacyDAO.getLocationName(rs.getString(6));
				 product.setLocationName(locationName);
				 double paidAmt= getPaidAmtofVoucher(product.getVoucherno(), product.getProcurementid());
				 totalpaidamt =totalpaidamt+paidAmt;
				 double balance= Double.parseDouble(product.getTotal()) - paidAmt ;
				 totalbalance = totalbalance+ balance;
				 totalAmt = totalAmt+total;
				 product.setPayAmount(DateTimeUtils.changeFormat(paidAmt));
				 product.setBalance(DateTimeUtils.changeFormat(balance));
				 product.setTotalpaidamt(totalpaidamt);
				 product.setTotalbalance(totalbalance);
				 product.setTotalAmt(totalAmt);
				 int res=procurementDAO.getProcurmentSeqNo(rs.getString(4));
				 String grnno="";
				 if(res>0){
					 grnno=String.valueOf(res);
				 }else{
					 grnno=rs.getString(4);
				 }
				 product.setGrnno(Integer.parseInt(grnno));
				 if(balance>0){
					 list.add(product);
				 }
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		} 
		
		return list;
	}

	
	public double getPaidAmtofVoucher(String voucherno,String procurementid) {
		
		double result=0;
		try {
			
			String sql="select sum(payamount) from inventory_paymentpo where voucherno=? and procurementid='"+procurementid+"' ;";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, voucherno);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 
				 result =rs.getDouble(1);
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
		
	}

	public double getTotalVoucherAmount(String voucherno,String procurementid, int iscomplt) {

		double result=0;
		try {
			
			//String sql="select sum(total) from inventory_procurement where voucherno='"+voucherno+"' and procurementid="+procurementid+" ";
			StringBuffer buffer = new StringBuffer();
			buffer.append("select sum(total) from inventory_procurement ");
			if(iscomplt>0){
				buffer.append("where oldprocid="+procurementid+" ");
			}else{
				buffer.append("where voucherno=? and procurementid="+procurementid+" ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ps.setString(1, voucherno);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
					result=rs.getDouble(1);
			}
			double disc= getTotalVoucherDiscount(voucherno,procurementid);
			double vat= getTotalVoucherVat(voucherno,procurementid);
			double sucharge =getTotalVoucherSurcharge(voucherno,procurementid);
			double debit =getTotalVoucherDebit(voucherno,procurementid);
			double credit =getTotalVoucherCredit(voucherno,procurementid);
			
			double plus=vat+sucharge+credit+result;
			double minus= disc+debit;
			double net = plus -minus;
			result =Math.round(net);
		    //result =(net);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}
	public double getTotalVoucherAmount(String voucherno,String procurementid,String vendorid) {

		double result=0;
		try {
			
			String sql="select sum(total) from inventory_procurement where voucherno=? and procurementid="+procurementid+" and vendorid="+vendorid+" and deleted!=1 ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, voucherno);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
					result=rs.getDouble(1);
			}
			double disc= getTotalVoucherDiscount(voucherno,procurementid,vendorid);
			double vat= getTotalVoucherVat(voucherno,procurementid,vendorid);
			double sucharge =getTotalVoucherSurcharge(voucherno,procurementid,vendorid);
			double debit =getTotalVoucherDebit(voucherno,procurementid,vendorid);
			double credit =getTotalVoucherCredit(voucherno,procurementid,vendorid);
			
			double plus=vat+sucharge+credit+result;
			double minus= disc+debit;
			double net = plus -minus;
			result =Math.round(net);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
	private double getTotalVoucherCredit(String voucherno,String procurementid) {
		double tot=0;
		try {
			
			String sql="select sum(credit) from inventory_vendor_procurement where voucherno=? and procurementid="+procurementid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, voucherno);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				tot= rs.getDouble(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return tot;
	}
	private double getTotalVoucherCredit(String voucherno,String procurementid,String vendorid) {
		double tot=0;
		try {
			
			String sql="select sum(credit) from inventory_vendor_procurement where voucherno=? and procurementid="+procurementid+" and vendorid="+vendorid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, voucherno);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				tot= rs.getDouble(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return tot;
	}

	private double getTotalVoucherDebit(String voucherno,String procurementid,String vendorid) {
		double tot=0;
		try {
			
			String sql="select sum(debit) from inventory_vendor_procurement where voucherno=? and procurementid="+procurementid+" and vendorid="+vendorid+" "; 
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, voucherno);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				tot= rs.getDouble(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return tot;
	}
	private double getTotalVoucherDebit(String voucherno,String procurementid) {
		double tot=0;
		try {
			
			String sql="select sum(debit) from inventory_vendor_procurement where voucherno=? and procurementid="+procurementid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, voucherno);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				tot= rs.getDouble(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return tot;
	}

	public double getTotalVoucherSurcharge(String voucherno,String procurementid) {

		double tot=0;
		try {
			
			String sql="select sum(surcharge) from inventory_vendor_procurement where voucherno=? and procurementid="+procurementid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, voucherno);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				tot= rs.getDouble(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return tot;
	}
	public double getTotalVoucherSurcharge(String voucherno,String procurementid,String vendorid) {

		double tot=0;
		try {
			
			String sql="select sum(surcharge) from inventory_vendor_procurement where voucherno=? and procurementid="+procurementid+" and vendorid="+vendorid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, voucherno);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				tot= rs.getDouble(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return tot;
	}

	public double getTotalVoucherVat(String voucherno,String procurementid) {

		double result=0;
		try {
			
			String sql="select sum(vat) from inventory_vendor_procurement where voucherno=? and procurementid="+procurementid+"  ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, voucherno);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
					result=rs.getDouble(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}
	public double getTotalVoucherVat(String voucherno,String procurementid,String vendorid) {

		double result=0;
		try {
			
			String sql="select sum(vat) from inventory_vendor_procurement where voucherno=? and procurementid="+procurementid+" and vendorid="+vendorid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, voucherno);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
					result=rs.getDouble(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}
	
	public double getTotalVoucherDiscount(String voucherno,String procurementid) {

		double result=0;
		try {
			
			String sql="select sum(discount) from inventory_vendor_procurement where voucherno=? and procurementid="+procurementid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, voucherno);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
					result=rs.getDouble(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}
	public double getTotalVoucherDiscount(String voucherno,String procurementid,String vendorid) {

		double result=0;
		try {
			
			String sql="select sum(discount) from inventory_vendor_procurement where voucherno=? and procurementid="+procurementid+" and vendorid="+vendorid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, voucherno);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
					result=rs.getDouble(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public int getTotalPayment(String location, String searchtext) {
		int res=0;
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT inventory_procurement.id FROM inventory_procurement ");
		if(searchtext!=null){
			buffer.append("inner join inventory_vendor on inventory_vendor.id = inventory_procurement.vendorid ");
		}
		if(!location.equals("0")){
			buffer.append("where voucherno is NOT NULL and location='"+location+"' ");
		} else {
			buffer.append("where voucherno is NOT NULL ");
		}
		if(searchtext!=null){
			buffer.append("and name like ('"+searchtext+"%') ");
		}
		buffer.append("group by vendorid order by id desc ");
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				res++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Procurement> getProductReturnListByVendor(String vendorid) {

		ArrayList<Procurement> list= new ArrayList<Procurement>();
		try {
			
			/*String sql="select id,grnreturnid,dateTime,total from inventory_return_vendor_account where vendorid="+vendorid+" ";*/
			String sql="select id,grnreturnid,dateTime,aproved_amt from inventory_return_vendor_account where aproved_amt>0 and status =0 and vendorid="+vendorid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			String grnreturnids = "0";
			while(rs.next()){
				  
				Procurement procurement =new Procurement();
				procurement.setId(rs.getInt(1));
				procurement.setGrnreturnid(rs.getString(2));
				procurement.setDate(DateTimeUtils.getDBDate(rs.getString(3)));
				procurement.setTotal(rs.getString(4));
				grnreturnids = grnreturnids+","+rs.getString(2);
				procurement.setGrnreturnids(grnreturnids);
				list.add(procurement);
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	public double getTotalReturedAmount(String vendorid) {

		double res=0;
		try {
			String sql="SELECT sum(aproved_amt) from inventory_return_vendor_account where aproved_amt>0 and status =0 and  vendorid="+vendorid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 res =rs.getDouble(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	public int updateProcurmnetPaymentStatus(String procurementid) {
		int result=0;
		try {
			String sql="update inventory_procurement set payment_status='1' where procurementid="+procurementid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
		
	}

	public Procurement getPaymentDetailsProcurement(String payid) {
		Procurement procurement= new Procurement();
		ProcurementDAO procurementDAO= new JDBCProcurementDAO(connection);
		InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
		
		PreparedStatement preparedStatement= null;
		try {
			StringBuffer  buffer= new StringBuffer();
			buffer.append("  select procurementid,payamount,paytype,bankname,chequeno,commencing,chequetype,handoverto,voucherno,cheq_receiver from inventory_paymentpo ");
			buffer.append("  where id="+payid+" ");
			
			preparedStatement= connection.prepareStatement(buffer.toString());
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()){
				procurement.setId(rs.getInt(1));
				procurement.setAmount(rs.getString(2));
				procurement.setPaymentType(rs.getString(3));
				procurement.setBankName(rs.getString(4));
				procurement.setCheqNo(rs.getString(5));
				procurement.setDate(DateTimeUtils.getCommencingDate1(rs.getString(6)));
				procurement.setCheqType(rs.getString(7));
				procurement.setHandoverTo(rs.getString(8));
				procurement.setVoucherno(rs.getString(9));
				procurement.setCheq_receiver(rs.getString(10));
				
				Procurement p1= procurementDAO.getProcurementDataByProcurementID(String.valueOf(procurement.getId()));
				Vendor vendor= new Vendor();
				vendor= inventoryVendorDAO.getVendor(p1.getVendor_id());
				procurement.setVendor(vendor.getName());
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return procurement;
	}

	public int saveParentPaymentData(String newvendorid, String userId, String date, String total_amt, String sumofreturn, String refundcheck, double totalpaid,int isfromledger) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into inventory_paymentpo_parent(vendorid, datetime, userid,total_amt,sumofreturn,refundcheck,totalpaid,isfromledger) values(?,?,?,?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, newvendorid);
			preparedStatement.setString(2, date);
			preparedStatement.setString(3, userId);
			preparedStatement.setString(4, total_amt);
			preparedStatement.setString(5, sumofreturn);
			preparedStatement.setString(6, refundcheck);
			preparedStatement.setString(7, DateTimeUtils.changeFormat(totalpaid));
			preparedStatement.setString(8, ""+isfromledger);
			result = preparedStatement.executeUpdate();
			
			
			if(result>0){
				ResultSet rs= preparedStatement.getGeneratedKeys();
				if(rs.next()){
					result =rs.getInt(1);
				}
				
			}
			
		}catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public int updateReturnPaymentDoneStatus(String grnreturnids, int paymentids) {
		int res=0;
		PreparedStatement ps=null;
		try {
			String sql="update inventory_return_vendor_account set paymentid=?,status=?  where grnreturnid='"+grnreturnids+"'";
			ps=connection.prepareStatement(sql);
			ps.setString(1, ""+paymentids);
			ps.setString(2, "1");
			res=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Procurement> getPaymentDetailsProcurementParent(String payid) {
		ProcurementDAO procurementDAO= new JDBCProcurementDAO(connection);
		InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
		ArrayList<Procurement> arrayList = new ArrayList<Procurement>();
		PreparedStatement preparedStatement= null;
		try {
			StringBuffer  buffer= new StringBuffer();
			buffer.append("  select procurementid,payamount,paytype,bankname,chequeno,commencing,chequetype,handoverto,voucherno,cheq_receiver from inventory_paymentpo ");
			buffer.append("inner join inventory_paymentpo_parent on inventory_paymentpo_parent.id = inventory_paymentpo.parentid ");
			buffer.append(" where inventory_paymentpo_parent.id="+payid+" ");
			
			preparedStatement= connection.prepareStatement(buffer.toString());
			ResultSet rs=preparedStatement.executeQuery();
			double total=0;
			while(rs.next()){
				Procurement procurement = new Procurement();
				procurement.setId(rs.getInt(1));
				procurement.setAmount(rs.getString(2));
				procurement.setPaymentType(rs.getString(3));
				procurement.setBankName(rs.getString(4));
				procurement.setCheqNo(rs.getString(5));
				procurement.setDate(DateTimeUtils.getCommencingDate1(rs.getString(6)));
				procurement.setCheqType(rs.getString(7));
				procurement.setHandoverTo(rs.getString(8));
				procurement.setVoucherno(rs.getString(9));
				procurement.setCheq_receiver(rs.getString(10));
				Procurement p1= procurementDAO.getProcurementDataByProcurementID(String.valueOf(procurement.getId()));
				Vendor vendor= new Vendor();
				vendor= inventoryVendorDAO.getVendor(p1.getVendor_id());
				procurement.setVendor(vendor.getName());
				procurement.setAddress(vendor.getAddress());
				total = total+rs.getDouble(2);
				total = Math.round(total);
				procurement.setTotal(DateTimeUtils.changeFormat(total));
				int res=procurementDAO.getProcurmentSeqNo(rs.getString(1));
				String grnno="";
				if(res>0){
					grnno=String.valueOf(res);
				}else{
					grnno=rs.getString(1);
				}
				procurement.setProSeqNo(grnno);
				arrayList.add(procurement);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public Procurement getPaymentDetailsParent(String payid) {
		Procurement procurement = new Procurement();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select id, vendorid, datetime, userid, total_amt, sumofreturn, refundcheck, totalpaid from inventory_paymentpo_parent where id ='"+payid+"'");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				procurement.setId(rs.getInt(1));
				procurement.setVendor_id(rs.getString(2));
				procurement.setUserid(rs.getString(4));
				procurement.setTotal_amt(DateTimeUtils.changeFormat(rs.getDouble(5)));
				procurement.setSumofreturn(DateTimeUtils.changeFormat(rs.getDouble(6)));
				procurement.setRefundcheck(""+rs.getInt(7));
				procurement.setTotalpaid(DateTimeUtils.changeFormat(rs.getDouble(8)));
				String[] data = rs.getString(3).split(" ");
				String date1  ="";
				if(data.length<1){
					String date = DateTimeUtils.getCommencingDate1(data[0]);
					date1 = date;
				}else{
					String date = DateTimeUtils.getCommencingDate1(data[0]);
					date1 = date + " " + data[1];
				}
				
				procurement.setDate(date1);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return procurement;
	}

	public int getVendorIdFromLegder(String name) {
		int res = 0;
		try {
			String sql ="select vendorid from ledger_master where name='"+name+"'";
			PreparedStatement preparedStatement  = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
}
