package com.apm.ThirdParties.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Accounts.eu.entity.CashDesk;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.ThirdParties.eu.bi.GPDAO;
import com.apm.ThirdParties.eu.entity.GP;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.Pagination;

public class JDBCGPDAO extends JDBCBaseDAO implements GPDAO{
	
	public JDBCGPDAO(Connection connection){
		
		this.connection = connection;
	}

	public int getTotalGPCount() {
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		String sql = "select count(*) from apm_gp_details";
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

	public ArrayList<GP> getGPList(Pagination pagination) {
		PreparedStatement preparedStatement = null;
		ArrayList<GP>list = new ArrayList<GP>();
		String sql = "";
		if(pagination.sortColumn==null){
			sql = "SELECT id,tptypeid,tpid,gpname,note,fax,email,workno FROM apm_gp_details order by id desc";
		}else{
			sql = "SELECT id,tptypeid,tpid,gpname,note,fax,email,workno FROM apm_gp_details";
		}
		sql = pagination.getSQLQuery(sql);
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				GP gp = new GP();
				gp.setId(rs.getInt(1));
				gp.setThirdPartyTypeId(rs.getString(2));
				gp.setThirdPartyId(rs.getString(3));
				String tpCompanyName = getTpCompanyName(rs.getString(3));
				gp.setThirdPartyCompanyName(tpCompanyName);
				gp.setName(rs.getString(4));
				gp.setDescription(rs.getString(5));
				gp.setFax(rs.getString(6));
				gp.setEmailid(rs.getString(7));
				gp.setWorkphno(rs.getString(8));
				
				list.add(gp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	private String getTpCompanyName(String tpid) {
		PreparedStatement preparedStatement = null;
		int tpId = Integer.parseInt(tpid);
		String result = "";
		String sql = "select company_name from apm_third_party_details where id = "+tpId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<GP> getTPCompanyList() {
		PreparedStatement preparedStatement = null;
		ArrayList<GP>list = new ArrayList<GP>();
		String sql = "SELECT id,third_party_id,company_name FROM apm_third_party_details where third_party_id = 1 and company_name!= '' or company_name!= null group by company_name";		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				GP gp = new GP();
				gp.setId(rs.getInt(1));
				gp.setThirdPartyTypeId(rs.getString(2));
				gp.setThirdPartyCompanyName(rs.getString(3));
				
				list.add(gp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public int saveGPDetail(GP gp) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_gp_details(tptypeid,tpid,gpname,note,fax,email,workno) values(?,?,?,?,?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "1");
			preparedStatement.setString(2, gp.getThirdPartyId());
			preparedStatement.setString(3, gp.getName());
			preparedStatement.setString(4, gp.getDescription());
			preparedStatement.setString(5, gp.getFax());
			preparedStatement.setString(6, gp.getEmailid());
			preparedStatement.setString(7, gp.getWorkphno());
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public GP getGPDetail(int id) {
		PreparedStatement preparedStatement = null;
		GP gp = new GP();
		String sql = "select id,tptypeid,tpid,gpname,note,fax,email,workno from apm_gp_details where id = "+id+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				gp.setId(rs.getInt(1));
				gp.setThirdPartyTypeId(rs.getString(2));
				gp.setThirdPartyId(rs.getString(3));
				String tpCompanyName = getTpCompanyName(rs.getString(3));
				gp.setThirdPartyCompanyName(tpCompanyName);
				gp.setName(rs.getString(4));
				gp.setDescription(rs.getString(5));
				gp.setFax(rs.getString(6));
				gp.setEmailid(rs.getString(7));
				gp.setWorkphno(rs.getString(8));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return gp;
	}

	public int updateGPDetail(GP gp, int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_gp_details set tptypeid = ?,tpid = ?,gpname = ?,note = ?,fax = ?,email = ?,workno = ? where id = "+id+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "1");
			preparedStatement.setString(2, gp.getThirdPartyId());
			preparedStatement.setString(3, gp.getName());
			preparedStatement.setString(4, gp.getDescription());
			preparedStatement.setString(5, gp.getFax());
			preparedStatement.setString(6, gp.getEmailid());
			preparedStatement.setString(7, gp.getWorkphno());
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int deleteGPDetail(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_gp_details where id = "+id+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			result = preparedStatement.executeUpdate();  				
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int getTotalGPCountOfSearch(String searchGP) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String temp[] = searchGP.split(" ");
		StringBuffer sql = new StringBuffer();
		 sql.append("select count(*) from apm_gp_details where");

		for(int i=0;i<temp.length;i++){			
			
			sql.append(" gpname like('%"+temp[i]+"%') or ");
			if(i==(temp.length-1)){
			sql.append("gpname like('%"+temp[i]+"%')");
			}
			else{
				sql.append("gpname like('%"+temp[i]+"%') or ");
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

	public ArrayList<GP> geGPListOfSearch(String searchGP,	Pagination pagination) {
		PreparedStatement preparedStatement = null;
		String temp[] = searchGP.split(" ");
		ArrayList<GP> list = new ArrayList<GP>();
		StringBuffer sql = new StringBuffer();
		sql.append( "SELECT id,tptypeid,tpid,gpname,note,fax,email,workno FROM apm_gp_details where gpname like('%"+searchGP+"%') or");

			for(int i=0;i<temp.length;i++){				
				
				sql.append(" gpname like('%"+temp[i]+"%') or ");
				if(i==(temp.length-1)){
				sql.append("gpname like('%"+temp[i]+"%')");
				}
				else{
					sql.append("gpname like('%"+temp[i]+"%') or ");
				}
				
			}
			
		String sql1 = sql.toString();
		sql1 = pagination.getSQLQuery(sql.toString());
		try{
			preparedStatement = connection.prepareStatement(sql1);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				GP gp = new GP();
				gp.setId(rs.getInt(1));
				gp.setThirdPartyTypeId(rs.getString(2));
				gp.setThirdPartyId(rs.getString(3));
				String tpCompanyName = getTpCompanyName(rs.getString(3));
				gp.setThirdPartyCompanyName(tpCompanyName);
				gp.setName(rs.getString(4));
				gp.setDescription(rs.getString(5));
				gp.setFax(rs.getString(6));
				gp.setEmailid(rs.getString(7));
				gp.setWorkphno(rs.getString(8));
				
				list.add(gp);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public int getGPId(int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select gp_id from apm_patient where id = "+selectedid+" ";
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

}
