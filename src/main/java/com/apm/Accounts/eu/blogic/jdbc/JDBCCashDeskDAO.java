package com.apm.Accounts.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Accounts.eu.bi.CashDeskDAO;
import com.apm.Accounts.eu.entity.CashDesk;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.opensymphony.xwork2.Preparable;

public class JDBCCashDeskDAO extends JDBCBaseDAO implements CashDeskDAO{

	public JDBCCashDeskDAO(Connection connection){
		this.connection = connection;
	}

	public int getTotalCashDeskCount() {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(*) from apm_client_cashdesk";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<CashDesk> getCashDeskList(Pagination pagination) {
		PreparedStatement preparedStatement = null;
		ArrayList<CashDesk> list = new ArrayList<CashDesk>();
		String sql = "";
		if(pagination.sortColumn==null){
			sql = "select apm_client_cashdesk.id,apm_client_cashdesk.clientid,apm_client_cashdesk.amount,apm_client_cashdesk.datetime,apm_patient.title,apm_patient.firstname,apm_patient.surname FROM apm_client_cashdesk inner join apm_patient on apm_client_cashdesk.clientid = apm_patient.id order by id desc";
		}else{
			sql = "select apm_client_cashdesk.id,apm_client_cashdesk.clientid,apm_client_cashdesk.amount,apm_client_cashdesk.datetime,apm_patient.title,apm_patient.firstname,apm_patient.surname FROM apm_client_cashdesk inner join apm_patient on apm_client_cashdesk.clientid = apm_patient.id";
			
		}
			//String sql = "select id,clientid,amount,datetime from apm_client_cashdesk";
			sql = pagination.getSQLQuery(sql);
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				CashDesk cashDesk = new CashDesk();
				cashDesk.setId(rs.getInt(1));
				cashDesk.setClientId(rs.getInt(2));				
				cashDesk.setAmount(rs.getInt(3));
				cashDesk.setDatetime(rs.getString(4));
				
				String clientName = ""+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7)+"";
				cashDesk.setClientName(clientName);
				
				list.add(cashDesk);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	private String getClientName(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT title,firstname,surname FROM apm_patient where id = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				
				result = rs.getString(1) + " " + rs.getString(2)+ " " + rs.getString(3);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return result;
	}

	public int getTotalCashDeskCountOfSearch(int id, String searchClient) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String temp[] = searchClient.split(" ");
		StringBuffer sql = new StringBuffer();
		 sql.append("select count(*) from apm_client_cashdesk INNER JOIN apm_patient ON apm_client_cashdesk.clientid = apm_patient.id where apm_client_cashdesk.clientid = "+id+" and");

		for(int i=0;i<temp.length;i++){
			
			
			sql.append(" apm_patient.firstname like('%"+temp[i]+"%') or ");
			if(i==(temp.length-1)){
			sql.append("apm_patient.surname like('%"+temp[i]+"%')");
			}
			else{
				sql.append("apm_patient.surname like('%"+temp[i]+"%') or ");
			}
			
		}
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<CashDesk> getCashDeskListOfSearch(int id,String searchClient, Pagination pagination) {
		PreparedStatement preparedStatement = null;
		String temp[] = searchClient.split(" ");
		ArrayList<CashDesk> list = new ArrayList<CashDesk>();
		StringBuffer sql = new StringBuffer();
		sql.append("select apm_client_cashdesk.id,apm_client_cashdesk.clientid,apm_client_cashdesk.amount,apm_client_cashdesk.datetime,"
				+ "apm_patient.title,apm_patient.firstname,apm_patient.surname from apm_client_cashdesk INNER JOIN "
				+ "apm_patient ON apm_client_cashdesk.clientid = apm_patient.id where");

			for(int i=0;i<temp.length;i++){
				
				
				sql.append(" apm_patient.firstname like('%"+temp[i]+"%') or ");
				if(i==(temp.length-1)){
				sql.append("apm_patient.surname like('%"+temp[i]+"%')");
				}
				else{
					sql.append("apm_patient.surname like('%"+temp[i]+"%') or ");
				}
				
			}
		String sql1 = sql.toString();
		sql1 = pagination.getSQLQuery(sql.toString());
		try{
			preparedStatement = connection.prepareStatement(sql1);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				CashDesk cashDesk = new CashDesk();
				cashDesk.setId(rs.getInt(1));
				cashDesk.setClientId(rs.getInt(2));
				cashDesk.setAmount(rs.getInt(3));
				cashDesk.setDatetime(rs.getString(4));
				cashDesk.setTitle(rs.getString(5));
				cashDesk.setFirstName(rs.getString(6));
				cashDesk.setLastName(rs.getString(7));
				
				String clientName = rs.getString(5) + " " + rs.getString(6)+ " " + rs.getString(7);
				cashDesk.setClientName(clientName);
				
				list.add(cashDesk);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public int saveCashDesk(CashDesk cashDesk) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_client_cashdesk(clientid,amount,datetime) values (?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cashDesk.getClientId());
			preparedStatement.setInt(2, cashDesk.getAmount());
			preparedStatement.setString(3, DateTimeUtils.getCurrentDateInDDMMYYYYCasting());
			
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public CashDesk getCashDeskData(int selectedid) {
		PreparedStatement preparedStatement = null;		
		String sql = "SELECT id,clientid,amount,datetime FROM apm_client_cashdesk where id = "+selectedid+" ";
		CashDesk cashDesk = new CashDesk();		
		try{			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				cashDesk.setId(rs.getInt(1));
				cashDesk.setClientId(rs.getInt(2));
				cashDesk.setAmount(rs.getInt(3));
				cashDesk.setDatetime(rs.getString(4));
				
				String clientName = getClientName(rs.getInt(2));
				cashDesk.setClientName(clientName);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return cashDesk;
	}

	public int updateCashdesk(int selectedid ,int amount) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_client_cashdesk set amount=? where id = ?";
		
		try{			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, amount);
			preparedStatement.setInt(2, selectedid);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteCashDesk(int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_client_cashdesk  where id = ?";
		
		try{			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, selectedid);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean isClientIdExist(int clientId) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from apm_client_cashdesk where clientid = "+clientId+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int updateSaveCashdesk(CashDesk cashDesk) {
		PreparedStatement preparedStatement = null;
		int clientId = cashDesk.getClientId();
		int amount = cashDesk.getAmount();
		int result = 0;
		String sql = "update apm_client_cashdesk set amount=? where clientid = ?";
		try{
			preparedStatement = connection.prepareStatement(sql);
			int amt = getCashdeskAmount(clientId);
			int totalamount = amt+amount;
			preparedStatement.setInt(1, totalamount);
			preparedStatement.setInt(2, clientId);
			
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int getCashdeskAmount(int clientId) {
		PreparedStatement preparedStatement = null;
		int amt = 0;
		String sql = "select amount from apm_client_cashdesk where clientId = "+clientId+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				amt = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return amt;
	}

	public int saveCashDeskTransaction(int clientId, double paymentAmount) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_cashdesk_transaction(clientid,paymentAmount) values(?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, clientId);
			preparedStatement.setDouble(2, paymentAmount);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int getTransactionCashdeskAmount(int clientId) {
		PreparedStatement preparedStatement = null;
		int result = 0,amt = 0,tamt = 0;
		String sql = "select sum(paymentAmount) from apm_cashdesk_transaction where clientId = "+clientId+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()){
				amt = getCashdeskAmount(clientId);
				tamt = rs.getInt(1);
				if(amt>tamt){
				result = amt-tamt;
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
