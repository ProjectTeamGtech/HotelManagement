
package com.apm.Expence.eu.bi.blogic.jdbc;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.struts2.convention.annotation.Results;

import com.apm.Appointment.eu.entity.Category;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.Expence.eu.bi.ExpenManagementDAO;
import com.apm.Expence.eu.entity.Expence;
import com.apm.Master.eu.entity.Master;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;

import ij.measure.ResultsTable;

public class JDBCExpenceManagementDAO extends JDBCBaseDAO implements ExpenManagementDAO{
	
	
	public JDBCExpenceManagementDAO(Connection connection){
		this.connection = connection;
	}
	
	public int getTotalPaymentVoucher(String fromdate, String todate, String expenseType, String range, String paymentmode)
	{
		int result=0;
		 try {
			 String sql="select count(*) from apm_expence_management";
			 StringBuilder  builder = new StringBuilder();
			 builder.append("select count(*) from apm_expence_management ");
			 builder.append("where caldate between '"+fromdate+"' and '"+todate+"' ");
			 if(!paymentmode.equals("0")){
				 builder.append("and paidby='"+paymentmode+"' ");
			 }
			 if(!expenseType.equals("0")){
				 builder.append("and category='"+expenseType+"' ");
			 }
			 if(!range.equals("0")){
				 if(range.equals("1")){
					 builder.append("and amount<1000 ");
				 }else if(range.equals("2")){
					 builder.append("and amount>2500 and amount<5000 ");
				 }else if(range.equals("3")){
					 builder.append("and amount>5000 and amount<10000 ");
				 }else if(range.equals("4")){
					 builder.append("and amount>10000 ");
				 }
			 }
			 
			 PreparedStatement ps=connection.prepareStatement(builder.toString());
			 ResultSet rs=ps.executeQuery();
			 
			 while(rs.next()){
				 
				   result=rs.getInt(1);
			 }
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Expence> getExpenceList(String fromdate, String todate, String expenseType, String range, String paymentmode,String action) {
		PreparedStatement preparedStatement = null;
		ArrayList<Expence>list = new ArrayList<Expence>();
		//String sql = "select id, caldate, amount, merchant, category, paidby, comments, lastmodified,currency,status from apm_expence_management order by id desc";
		
		StringBuilder  builder = new StringBuilder();
		 builder.append("select id, caldate, amount, merchant, category, paidby, comments, lastmodified,currency,status,xpayment,credit,parentid,cbal,ctype from apm_expence_management ");
		 builder.append("where caldate between '"+fromdate+"' and '"+todate+"' ");
		 if(!paymentmode.equals("0")){
			 builder.append("and xpayment='"+paymentmode+"' ");
		 }
		 if(!expenseType.equals("0")){
			 builder.append("and category='"+expenseType+"' ");
		 }
		 if(!range.equals("0")){
			 if(range.equals("1")){
				 builder.append("and amount<1000 ");
			 }else if(range.equals("2")){
				 builder.append("and amount>2500 and amount<5000 ");
			 }else if(range.equals("3")){
				 builder.append("and amount>5000 and amount<10000 ");
			 }else if(range.equals("4")){
				 builder.append("and amount>10000 ");
			 }
		 }
		builder.append("and iscancel=0 ");
		
		if(action.equals("1")){
			builder = new StringBuilder();
			 builder.append("select id, caldate, amount, merchant, category, paidby, comments, lastmodified,currency,status,xpayment,credit,parentid,cbal,ctype from apm_expence_management ");
			 builder.append("where category='"+expenseType+"' ");
			 builder.append("and iscancel=0 ");
			 
		}
		
		try{
			preparedStatement = connection.prepareStatement(builder.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double total =0;
			double ctotal = 0;
			
			while(rs.next()){
				Expence expence = new Expence();
				expence.setId(rs.getInt(1));
				String date=rs.getString(2);
				expence.setAmount(rs.getString(3));
				expence.setMerchant(rs.getString(4));
				expence.setCategory(rs.getString(5));
				expence.setPaidby(rs.getString(6));
				expence.setComments(rs.getString(7));
				expence.setLastmodified(rs.getString(8));
				expence.setCurrency(rs.getString(9));
				expence.setStatus(rs.getString(10));
				expence.setXpayment(rs.getString(11));
				expence.setCredit(DateTimeUtils.changeFormat(rs.getDouble(12)));
				expence.setParantid(rs.getString(13));
				expence.setClosingBal(DateTimeUtils.changeFormat(rs.getDouble(14))+ " " + rs.getString(15));
				
				if(expence.getXpayment().equals("Payment")){
					String ledgername = getPaymentLedgerName(expence.getParantid());
					expence.setCategory(ledgername);
				}
				
				if(date!=null) {
					date=DateTimeUtils.getCommencingDate2(date);
				}
				
				ctotal = ctotal + rs.getDouble(12);
				expence.setCtotal(DateTimeUtils.changeFormat(ctotal));
				
				
				total = total + rs.getDouble(3);
				expence.setTotalamount(total);
				expence.setCaldate(date);
				
				
				
				
				
				
				list.add(expence);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	private double getCrBalance(String expenseType,String fdate,String tdate,String column) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT amount FROM apm_expence_management where caldate between '"+fdate+"' and '"+tdate+"' "
				+ " and category = '"+expenseType+"'  and "+column+" !=0 order by id desc limit 0,1 ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	private String getPaymentLedgerName(String parantid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT ledger_master.name  FROM apm_parent_expence_management "
				+ "inner join ledger_master on  ledger_master.id = apm_parent_expence_management.ledgerid "
				+ " where apm_parent_expence_management.id = "+parantid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public double getExpenceTotal() {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT sum(amount) FROM apm_expence_management ";
		
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

	public int addReportData(String totalExpenceCheckbox, String date,String reportname) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_expence_report(reportid, lastmodified, reportname) values(?,?,?)";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, totalExpenceCheckbox);
			preparedStatement.setString(2, date);
			preparedStatement.setString(3, reportname);
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Expence> getReportList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Expence>list = new ArrayList<Expence>();
		String sql = "SELECT id,  lastmodified,reportname FROM apm_expence_report order by id desc";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Expence expence = new Expence();
				expence.setId(rs.getInt(1));
				expence.setLastmodified(rs.getString(2));
				expence.setReportName(rs.getString(3));
				
				list.add(expence);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public Expence getReportIdList(String id) {
		PreparedStatement preparedStatement = null;
		Expence expence = new Expence();
		String sql = "SELECT reportid,reportname,lastmodified FROM apm_expence_report where id = "+id+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				expence.setReportIdList(rs.getString(1));
				expence.setReportName(rs.getString(2));
				expence.setCaldate(DateTimeUtils.getIndianDateTimeFormat(rs.getString(3)));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return expence;
	}
	
	

	public ArrayList<Expence> getReportExpenseList(String reportidlist) {
		PreparedStatement preparedStatement = null;
		ArrayList<Expence>list = new ArrayList<Expence>();
		String sql = "select id, caldate, amount, merchant, category, paidby, comments, lastmodified,currency, status from apm_expence_management where id in("+reportidlist+") ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Expence expence = new Expence();
				expence.setId(rs.getInt(1));
                String date=rs.getString(2);
				expence.setAmount(rs.getString(3));
				expence.setMerchant(rs.getString(4));
				expence.setCategory(rs.getString(5));
				expence.setPaidby(rs.getString(6));
				expence.setComments(rs.getString(7));
				expence.setLastmodified(rs.getString(8));
				expence.setCurrency(rs.getString(9));
				
				if(date!=null) {
					date=DateTimeUtils.getCommencingDate2(date);
				}
				expence.setCaldate(date);
				list.add(expence);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	
	
	
	
	public ArrayList<Expence> getReportExpenseListByid(String reportidlist,int parentid,String epayment) {
		PreparedStatement preparedStatement = null;
		ArrayList<Expence> list = new ArrayList<Expence>();
		String sql = "select id, caldate, amount, merchant, category, paidby, comments, lastmodified,"
				+ "currency, status,createdate,createbyid,credit,transid from apm_expence_management "
				+ "where parentid = "+parentid+" ";
		
		Expence p = geteoficialledgerdetails(Integer.parseInt(reportidlist),1);
		if(epayment.equals("Journal")){
			p = geteoficialledgerdetails(Integer.parseInt(reportidlist),0);
		}
		
		if(epayment.equals("Purchase")){
			//p = geteoficialledgerdetails(Integer.parseInt(reportidlist),0);
			p.setCategory("");
			p.setPaidby("");
			p.setXpayment("Purchase");
		}
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Expence expence = new Expence();
				expence.setId(rs.getInt(1));
                String date=rs.getString(2);
				expence.setAmount(rs.getString(3));
				if(p.getXpayment().equals("Payment")){
					expence.setAmount(rs.getString(13));
				}
				expence.setMerchant(rs.getString(4));
				expence.setCategory(rs.getString(5));
				if(p.getXpayment().equals("Receipt")){
					expence.setCategory(p.getCategory());
				}
				
				if(p.getXpayment().equals("Contra")){
					if(rs.getInt(14)==1){
						expence.setCategory(rs.getString(5) + " (Deposited)");
					}else{
						expence.setCategory(rs.getString(5) + " (Withdrawl)");
					}
				}
				
				expence.setPaidby(rs.getString(6));
				expence.setComments(rs.getString(7));
				expence.setLastmodified(rs.getString(8));
				expence.setCurrency(rs.getString(9));
				expence.setStatus(rs.getString(10));
				expence.setCreatedate(rs.getString(11));
				expence.setCreateby(rs.getString(12));
				expence.setCredit(rs.getString(13));
				
				if(date!=null) {
					date=DateTimeUtils.getCommencingDate2(date);
				}
				expence.setCaldate(date);
				expence.setXpayment(epayment);
				
				if(epayment.equals("Payment") || epayment.equals("Receipt")){
					expence.setShowcd("0");
				}else{
					expence.setShowcd("1");
				}
				
				
				list.add(expence);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	public Expence geteoficialledgerdetails(int parentid,int type) {
		PreparedStatement preparedStatement = null;
		Expence expence = new Expence();
		String sql = "select ledger_master.name,paymode,xpayment from ledger_master inner join ledger_sheet on "
				+ " ledger_sheet.ledgerid = ledger_master.id "
				+ " where expenceid = "+parentid+" and ofcialledgerid = "+type+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				expence.setCategory(rs.getString(1));
				expence.setPaidby(rs.getString(2));
				expence.setXpayment(rs.getString(3));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return expence;
	}

	public double getReportExpenceTotal(String reportidlist) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT sum(amount) FROM apm_expence_management where id in("+reportidlist+") ";
		
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

	public ArrayList<Expence> getAllCategories(String xpayment) {
		ArrayList<Expence> categories = new ArrayList<Expence>();
		
		String ltype = "1";
		if(xpayment.equals("Contra")){
			ltype = "2";
		}

		  try {

		   String sql = "select id,name from ledger_master where ltype="+ltype+" order by name ";
		   
		  /* if(xpayment.equals("Contra")){
			   sql = "SELECT id,name FROM bankname_list ";
		   }*/

		   PreparedStatement ps = connection.prepareStatement(sql);
		   ResultSet rs = ps.executeQuery();
		   while (rs.next()) {

		    Expence expence = new Expence();
		    expence.setId(rs.getInt(1));
		    expence.setName(rs.getString(2));
		    //expence.setDescription(rs.getString(3));
		    categories.add(expence);
		    
		   }

		  } catch (Exception e) {

		   e.printStackTrace();
		  }
		  return categories;
	}

	public int addPaymentVoucher(Expence expence,int parentid,String epayment) {
		 int result = 0;

		  try {
			  
			  int t = 0;

			  String dbdateformat=DateTimeUtils.getCommencingDate(expence.getCaldate());
			 
			  DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			    Date date=new Date();
			 String datestr=dateFormat1.format(date).toString();
			  
			  
			  		String sql="insert into apm_expence_management (caldate,amount,merchant,category,paidby,comments,lastmodified,currency,status,createbyid,createdate,parentid,credit,lledgerid,xpayment,transid,cbal,ctype) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			  		PreparedStatement ps=connection.prepareStatement(sql);
			  		ps.setString(1, dbdateformat);
			  		ps.setString(2, expence.getAmount());
			  		ps.setString(3, expence.getMerchant());
			  		ps.setString(4, expence.getCategory());
		            ps.setString(5, expence.getPaidby());
		            ps.setString(6, expence.getComments());
		            ps.setString(7, expence.getLastmodified());
		            ps.setString(8, expence.getCurrency());
		            ps.setString(9, "paid");
		            ps.setString(10, expence.getUserid());
		            ps.setString(11, datestr);
		            ps.setInt(12, parentid);
		            ps.setString(13, expence.getCredit());
		            ps.setString(14, expence.getLedgerid());
		            ps.setString(15, epayment);
		            ps.setString(16, expence.getTransid());
		            
		            
		            double cr = 0;
		            double dr = 0;
		            boolean isopening = checkOpening(expence.getCategory());
		            if(isopening){
		            	Master master = getLastClosingBal(expence.getCategory());
		            	if(master.getCtype().equals("Dr")){
		            		dr = Double.parseDouble(expence.getCredit()) + Double.parseDouble(master.getCbal());
		            	}else{
		            		dr = Double.parseDouble(expence.getCredit());
		            	}
		            	if(master.getCtype().equals("Cr")){
		            		cr = Double.parseDouble(expence.getAmount()) + Double.parseDouble(master.getCbal());
		            	}else{
		            		cr = Double.parseDouble(expence.getAmount());
		            	}
		            }else{
		               dr = Double.parseDouble(expence.getCredit());
				       cr = Double.parseDouble(expence.getAmount());
		            }
		            
		           
		            String ctype = "";
		            double cbal = 0;
		            
		            
		            
		            if(dr > cr ){
		            	cbal = dr - cr;
		            	ctype = "Dr";
		            }
		            
		            if(cr > dr){
		            	cbal = cr - dr;
		            	ctype = "Cr";
		            }
		            
		           
		            
		            ps.setString(17, ""+cbal+"");
		            ps.setString(18, ctype);
		            
		   result=ps.executeUpdate();
		   
		   if (result == 1) {
				ResultSet resultSet = ps.getGeneratedKeys();
				if (resultSet.next()) {
					result = resultSet.getInt(1);
				}
			}
		            
		  } catch (Exception e) {

		   e.printStackTrace();

		  }

		  return result;
	}

	private Master getLastClosingBal(String category) {
		PreparedStatement preparedStatement = null;
		Master master = new Master();
		String sql = "SELECT cbal,ctype FROM apm_expence_management where category = '"+category+"' "
				+ " order by id desc limit 0,1 ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				master.setCbal(rs.getString(1));
				master.setCtype(rs.getString(2));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	private boolean checkOpening(String category) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM apm_expence_management where category = '"+category+"'   ";
		
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

	public Expence getPaymentVoucher(String selectedid) {
		  Expence expence=new Expence();
		  
		  try {
		   
		    String sql="select caldate,amount,merchant,category,paidby,comments,lastmodified,currency from apm_expence_management where id='"+selectedid+"'";   
		   
		    PreparedStatement ps=connection.prepareStatement(sql);
		    ResultSet rs=ps.executeQuery();
		    while(rs.next()) {
		     
		      expence.setCaldate(rs.getString(1));
		      expence.setAmount(rs.getString(2));
		      expence.setMerchant(rs.getString(3));
		      expence.setCategory(rs.getString(4));
		      expence.setPaidby(rs.getString(5));
		      expence.setComments(rs.getString(6));
		      expence.setLastmodified(rs.getString(7));
		      expence.setId(Integer.parseInt(selectedid));
		      expence.setCurrency(rs.getString(8));
		      
		    }
		    
		    String date=DateTimeUtils.getCommencingDate2(expence.getCaldate());
		    expence.setCaldate(date);
		  } catch (Exception e) {

		     e.printStackTrace();
		  }
		  
		  return expence;
	}

	public int updatePaymentVoucher(Expence expence) {
		 int result=0;
		  
		  try {
		   
			String date=expence.getCaldate();  
			
		   date=DateTimeUtils.getCommencingDate(date);
		   
			DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		    Date date1=new Date();
		 String datestr=dateFormat1.format(date1).toString();
			  
		   String sql="update apm_expence_management set caldate=?,amount=?,merchant=?,category=?,paidby=?,comments=?,currency=?,editbyid=?,editbydate=? where id="+expence.getId()+"";
		   PreparedStatement ps=connection.prepareStatement(sql);
		   ps.setString(1, date);
		   ps.setString(2, expence.getAmount());
		   ps.setString(3, expence.getMerchant());
		   ps.setString(4, expence.getCategory());
		   ps.setString(5, expence.getPaidby());
		   ps.setString(6, expence.getComments());
		   ps.setString(7, expence.getCurrency());
		   ps.setString(8, expence.getUserid());
		   ps.setString(9, datestr);
		   result=ps.executeUpdate();
		   
		   
		  } catch (Exception e) {

		     e.printStackTrace();
		  }
		  
		  return result;
	}

	public int deleteExpenses(String id,String uid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    Date date=new Date();
	 String datestr=dateFormat1.format(date).toString();
	//	String sql = "delete from apm_expence_management where id = "+id+" ";
		String sql="update apm_expence_management set status='cancel',cancelbyid="+uid+",cancelbydate="+datestr+" where id = "+id+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Expence> getSortedExpenceList(Expence expence) {

		ArrayList<Expence> list=new ArrayList<Expence>();
		
		try {
			String sql="";
			 //StringBuilder sql=new StringBuilder();
			//String sql="select id,caldate,amount,merchant,category,paidby,comments,lastmodified,currency from apm_expence_management where caldate BETWEEN '"+expence.getFromdate()+"' AND '"+expence.getTodate()+"'";
		
		  if(expence.getRangevalue().equals(""))
		  {
			  sql="select id,caldate,amount,merchant,category,paidby,comments,lastmodified,currency from apm_expence_management where category='"+expence.getExpensetypevalue()+"' or paidby='"+expence.getPaymentmodevalue()+"' or caldate BETWEEN '"+expence.getFromdate()+"' AND '"+expence.getTodate()+"'";
		  }
		  else
		  {
			  if(expence.getRangevalue().equals("<1000"))
			     {
				  String[] rate=expence.getRangevalue().split("<");
				  int rate1=Integer.parseInt(rate[1]);
				  sql="select id,caldate,amount,merchant,category,paidby,comments,lastmodified,currency from apm_expence_management where category='"+expence.getExpensetypevalue()+"' or paidby='"+expence.getPaymentmodevalue()+"' or amount <"+rate1+" or caldate BETWEEN '"+expence.getFromdate()+"' AND '"+expence.getTodate()+"'";
			     }
			     else if(expence.getRangevalue().equals(">10000"))
			     {
			    	 String[] rate=expence.getRangevalue().split(">");
					  int rate1=Integer.parseInt(rate[1]);
			    	 sql="select id,caldate,amount,merchant,category,paidby,comments,lastmodified,currency from apm_expence_management where category='"+expence.getExpensetypevalue()+"' or paidby='"+expence.getPaymentmodevalue()+"' or amount >'"+rate1+"' or caldate BETWEEN '"+expence.getFromdate()+"' AND '"+expence.getTodate()+"'";
			     }
			     else
			     {
			    	 sql="select id,caldate,amount,merchant,category,paidby,comments,lastmodified,currency from apm_expence_management where category='"+expence.getExpensetypevalue()+"' or paidby='"+expence.getPaymentmodevalue()+"' or amount BETWEEN'"+expence.getAmount()+"' or caldate BETWEEN '"+expence.getFromdate()+"' AND '"+expence.getTodate()+"'";
			     }
		  }
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
			
				Expence expence2=new Expence();
				expence2.setId(rs.getInt(1));
				expence2.setCaldate(DateTimeUtils.getCommencingDate2(rs.getString(2)));
				expence2.setAmount(rs.getString(3));
				expence2.setMerchant(rs.getString(4));
				expence2.setCategory(rs.getString(5));
				expence2.setPaidby(rs.getString(6));
				expence2.setComments(rs.getString(7));
				expence2.setLastmodified(rs.getString(8));
				expence2.setCurrency(rs.getString(9));
				list.add(expence2);
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return list;
	}

	public double getExpenceTotal(String fromdate, String todate) {

		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT sum(amount) FROM apm_expence_management where caldate between '"+fromdate+"' and '"+todate+"'";
		
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

	public ArrayList<Expence> getAllCurrencies() {

		ArrayList<Expence> list=new ArrayList<Expence>();
		
		try {
			
			String sql="select id,name,value from apm_currency";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				Expence expence=new Expence();
				expence.setId(rs.getInt(1));
				expence.setName(rs.getString(2));
				expence.setValue(rs.getString(3));
				list.add(expence);
			}
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		
		return list;
	}

	public int getCurrencyValue(String curr) {

		int result=0;
		try {
			
			String sql="select value from apm_currency where name='"+curr+"'";
			
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
	
	

	public int saveParentExpData(String pledgerid, String ptype, String pmode, 
			String paymantto, String pcommencing,
			String uid,int pno,int rno,int cno,int jno,int purno) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String sql = "insert into apm_parent_expence_management(ledgerid, ptype, pmode, pto, commencing, userid,pno, rno, cno, jno, cyear, purno) "
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?) ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, pledgerid);
			preparedStatement.setString(2, ptype);
			preparedStatement.setString(3, pmode);
			preparedStatement.setString(4, paymantto);
			preparedStatement.setString(5, pcommencing);
			preparedStatement.setString(6, uid);
			
			preparedStatement.setInt(7, pno);
			preparedStatement.setInt(8, rno);
			preparedStatement.setInt(9, cno);
			preparedStatement.setInt(10, jno);
			
			preparedStatement.setInt(11, year);
			preparedStatement.setInt(12, purno);
			
			result = preparedStatement.executeUpdate();

			if (result == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					result = resultSet.getInt(1);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getExpenceParentID(String id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT parentid FROM apm_expence_management where id = "+id+" ";
		
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

	public Expence getExpenceParentDetail(int parentid) {
		PreparedStatement preparedStatement = null;
		Expence expence = new Expence();
		String sql = "SELECT id, ledgerid, ptype, pmode, pto, commencing, userid FROM apm_parent_expence_management where id = "+parentid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				expence.setId(rs.getInt(1));
				expence.setLedgerid(rs.getString(2));
				expence.setPtype(rs.getString(3));
				expence.setPmode(rs.getString(4));
				expence.setPto(rs.getString(5));
				expence.setCommencing(rs.getString(6));
				expence.setUserid(rs.getString(7));
			}
		
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return expence;
	}

	public ArrayList<Master> getDebitorList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "select id,name from ledger_debitor ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				
				list.add(master);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public int saveNewLedgerDebitors(String debitor) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into ledger_debitor(name) values(?) ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, debitor);
			result = preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int cancelExpense(String id,String delete_reason) {
		  int result = 0;
		  try {
		   String sql = "update apm_expence_management set iscancel=?,cancelnote=? where id="+id+"";
		   PreparedStatement ps = connection.prepareStatement(sql);
		   ps.setString(1, "1");
		   ps.setString(2, delete_reason);
		   result = ps.executeUpdate();
		  } catch (Exception e) {

		   e.printStackTrace();
		  }

		  return result;
	}

	public String getDebtorname(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select name from ledger_debitor where id = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int getExpenceMaxno(String column) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select max("+column+") from apm_parent_expence_management ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
				result++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public Expence getVoucherDetails(String selectedid) {
  Expence expence=new Expence();
		  
		  try {
		   
		    String sql="select caldate,amount,merchant,category,paidby,comments,lastmodified,currency,credit,lledgerid,xpayment,parentid,transid from apm_expence_management where id='"+selectedid+"'";   
		   
		    PreparedStatement ps=connection.prepareStatement(sql);
		    ResultSet rs=ps.executeQuery();
		    while(rs.next()) {
		     
		      expence.setCaldate(rs.getString(1));
		      expence.setAmount(rs.getString(2));
		      expence.setMerchant(rs.getString(3));
		      expence.setCategory(rs.getString(4));
		      expence.setPaidby(rs.getString(5));
		      expence.setComments(rs.getString(6));
		      expence.setLastmodified(rs.getString(7));
		      expence.setId(Integer.parseInt(selectedid));
		      expence.setCurrency(rs.getString(8));
		      expence.setCredit(rs.getString(9));
		      expence.setLedgerid(rs.getString(10));
		      expence.setXpayment(rs.getString(11));
		      expence.setTransid(rs.getString(13));
		      Expence ex= getExpenceParentDetail(rs.getInt(12));
		      expence.setPto(ex.getPto());
		    }
		    
		    String date=DateTimeUtils.getCommencingDate2(expence.getCaldate());
		    expence.setCaldate(date);
		  } catch (Exception e) {

		     e.printStackTrace();
		  }
		  
		  return expence;

	}

	public int updateExpence(String selectedid, String amt, String caldate, String description,String cname) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_expence_management set "
				+ " caldate='"+caldate+"',"+cname+"='"+amt+"',comments='"+description+"' "
						+ " where id = "+selectedid+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int updateExpenceLedger(String editcatgoryid, String amt, String colname,String expenceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update ledger_sheet set "+colname+" = '"+amt+"' where ledgerid = '"+editcatgoryid+"' and expenceid='"+expenceid+"' ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public String getExpLedgid(String editcatgoryid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "Select * from ledger_master where name = '"+editcatgoryid+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateExpenceLedgerNew(String ledgerid, String amt, String colname, String procurementid, String invoicecolmn) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update ledger_sheet set "+colname+" = "+amt+" where ledgerid = '"+ledgerid+"' and "+invoicecolmn+"='"+procurementid+"' and "+colname+"!='0' ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int updateJvExpence(String selectedid, String credit, String amount, String caldate, String description) {
		
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_expence_management set "
				+ " caldate='"+caldate+"',amount='"+amount+"',credit="+credit+",comments='"+description+"' "
						+ " where id = "+selectedid+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateJvExpenceLedger(String catid, String credit, String amount, String selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update ledger_sheet set credit = '"+credit+"', "
				+ " debit='"+amount+"' where ledgerid = '"+catid+"' and expenceid='"+selectedid+"' ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteLedgerEntery(String id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from ledger_sheet where expenceid = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getOfficiallederid(String id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select ledgerid from ledger_sheet where expenceid = "+id+" and ofcialledgerid = '1'";
		
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

	public String getPtoName(int oficicledgerid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select name,paymode from ledger_master where id = "+oficicledgerid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1) + " ("+rs.getString(2)+")";
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public String getVendorName(int vendoridnew) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select name from ledger_master where vendorid = "+vendoridnew+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs  = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int checkforvendor(String ledgerid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select vendorid from ledger_master where id = "+ledgerid+" ";
		
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

	public String getLedgerPaymentMode(String ledgerid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select paymode from ledger_master where id = "+ledgerid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getExpenceVendorid(String vexpenseType) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select vendorid from ledger_master where name = '"+vexpenseType+"' ";
		
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

	public Master getVendorDetails(int vendorid) {
		PreparedStatement preparedStatement = null;
		Master master = new Master();
		String sql = "select name,address from inventory_vendor where id = "+vendorid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				master.setName(rs.getString(1));
				master.setAddress(rs.getString(2));
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return master;
	}

	public double getDbLedgerAmount(String ledgerid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "select amount from ledger_amount where ledgerid = "+ledgerid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateLedgerAmount(String ledgerid, double lamount) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update ledger_amount set amount = "+lamount+" where ledgerid = "+ledgerid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return result;
	}

	public double getEditLedgerAmount(String editledgername) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "select sum(debit) from ledger_sheet where ledgerid = "+editledgername+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getParentExpLedgerId(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT ledgerid FROM apm_expence_management inner join apm_parent_expence_management "
				+ " on apm_parent_expence_management.id = apm_expence_management.parentid "
				+ " where apm_expence_management.id = "+id+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int upddateExpenceId(int expenceid,int ledgerid,String xpayment) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update ledger_sheet set expenceid="+expenceid+",xpayment='"+xpayment+"' where id="+ledgerid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updatePaymentVoucher(int expenceid, int paymentids) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_expence_management set inv_paymentid='"+paymentids+"' where id="+expenceid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updatePaymentLedgerSheet(int saveledger, int paymentids) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update ledger_sheet set proc_paymentid='"+paymentids+"' where id="+saveledger+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int truncateLedgerAmount() {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "truncate ledger_amount ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int refreshLedgerAmount() {
		int result = 0;
		try {
			PreparedStatement preparedStatement = null;
			StringBuffer sql = new StringBuffer();
			SimpleDateFormat sf= new SimpleDateFormat("yyyy-MM-dd");
			String fromDate=sf.format(DateTimeUtils.getFinanacialYearStartAndEndDates(new Date())[0]);
			String toDate= sf.format(DateTimeUtils.getFinanacialYearStartAndEndDates(new Date())[1]);
			sql.append("SELECT ledgerid,name, sum(debit) FROM ledger_sheet ");
			sql.append("inner join ledger_master on ledger_master.id = ledger_sheet.ledgerid ");
			sql.append("where ltype in(0,3) and gcommencing between '"+fromDate+"' and '"+toDate+"' group by ledgerid ");
		
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				int ledgerid = rs.getInt(1);
				String lname = rs.getString(2);
				double expncpayment = getExcpencePayment(ledgerid,fromDate,toDate);
				double amount = rs.getDouble(3);
				if(amount>=expncpayment){
					amount = rs.getDouble(3) - expncpayment;
				}
				int i = inserLedgerAmount(ledgerid,lname,amount);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private int inserLedgerAmount(int ledgerid, String lname, double amount) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into ledger_amount(name, amount, ledgerid) values(?,?,?) ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, lname);
			preparedStatement.setString(2, ""+amount+"");
			preparedStatement.setInt(3, ledgerid);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	private double getExcpencePayment(int id,String fromDate, String toDate) {

		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "select sum(credit) from ledger_sheet where expnctype = 2  and ledgerid = "+id+" and gcommencing between '"+fromDate+"' and '"+toDate+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateLedgerName(Master master) {
		try {
			String sql="update ledger_master set name=? where id=?";
			PreparedStatement ps= connection.prepareStatement(sql);
			ps.setString(1, master.getName());
			ps.setString(2, ""+master.getId());
			int x=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int delLedgerWithStatus(int ledgerid) {
		int status=0;
		
		if(checkIfLedgerUsed(ledgerid)){
			status=1;
		}else{
			if(ledgerid!=0){
				deleteLedgerMaster(ledgerid);
				removeLedgerFromLedgerHead(ledgerid);
				status=2;
			}
			
		}
		
		return status;
	}

	private void deleteLedgerMaster(int ledgerid){
		try {
			String sql="delete from  ledger_master  where id='"+ledgerid+"'";
			PreparedStatement ps= connection.prepareStatement(sql);
			
			int x=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	boolean checkIfLedgerUsed(int ledgerid){
		boolean flag=false;
		try {
			String sql="select SUM(debit),SUM(credit),COUNT(*) from ledger_sheet where ledgerid='"+ledgerid+"'";
			PreparedStatement ps= connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				int count=rs.getInt(3);
				double amt=rs.getDouble(1)+rs.getDouble(2);
				if(count<=1&&amt==0){
					
				}else{
					flag=true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	private void removeLedgerFromLedgerHead(int ledgerid){
		try {
			String sql="SELECT services,id FROM ledger_ahead_master where services like '%,"+ledgerid+",%'";
			PreparedStatement ps= connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				String services=DateTimeUtils.isNull(rs.getString(1));
				String serw=services.replace(","+ledgerid+",", ",");
				updateLedgerHeadServices(serw, rs.getInt(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
private void updateLedgerHeadServices(String ser,int id){
	try {
		String sql="update ledger_ahead_master set services=? where id=?";
		PreparedStatement ps= connection.prepareStatement(sql);
		ps.setString(1, ser);
		ps.setString(2, ""+id);
		int x=ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}

public void savePatientToLedgerMaster(String clientId) {
	try {
		if(!DateTimeUtils.isNull(clientId).equals("")){
			ClientDAO clientDAO= new JDBCClientDAO(connection);
			Client client= clientDAO.getPatient(DateTimeUtils.convertToInteger(clientId));
			String ledgerName=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName()+" ["+client.getAbrivationid()+"]";
			if(getLedgerIdOfClient(clientId)==0){
				saveNewLedgerToClient(ledgerName, "0", "0", "0", clientId);
			}
			
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}

private int saveNewLedgerToClient(String hdnnewledger, String howpaid,String bname,String ltype, String clientId) {
	PreparedStatement preparedStatement = null;
	int result = 0;
	String sql = "insert into ledger_master(name,paymode,bnkname,ltype,clientId_ledger) values(?,?,?,?,?)";
	
	try{
		
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, hdnnewledger);
		preparedStatement.setString(2, howpaid);
		preparedStatement.setString(3, bname);
		preparedStatement.setString(4, ltype);
		preparedStatement.setString(5, clientId);
		result = preparedStatement.executeUpdate();
		
		if (result == 1) {
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				result = resultSet.getInt(1);
			}
		}
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public int getLedgerIdOfClient(String client) {
	int res=0;
	try {
	PreparedStatement ps= connection.prepareStatement(" select id from ledger_master where clientId_ledger='"+client+"'");	
	ResultSet rs=ps.executeQuery();
	while (rs.next()) {
		res=rs.getInt(1);
	}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}
}
