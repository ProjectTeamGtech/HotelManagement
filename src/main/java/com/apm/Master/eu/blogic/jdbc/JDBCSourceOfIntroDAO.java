package com.apm.Master.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Master.eu.bi.SourceOfIntroDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.SourceOfIntro;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;

public class JDBCSourceOfIntroDAO extends JDBCBaseDAO implements SourceOfIntroDAO{

	public JDBCSourceOfIntroDAO(Connection connection){
		this.connection = connection;
		
	}

	public int getTotalSourceOfIntroCount() {
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		String sql = "select count(*) from apm_sourceofintro";
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

	public ArrayList<SourceOfIntro> getsourceOfIntroList(Pagination pagination) {
		PreparedStatement preparedStatement = null;
		ArrayList<SourceOfIntro>list = new ArrayList<SourceOfIntro>();
		String sql = "";
		if(pagination.sortColumn==null){
			sql = "SELECT id,sourceOfIntro,description,dateTime FROM apm_sourceofintro order by id desc";
		}else{
			sql = "SELECT id,sourceOfIntro,description,dateTime FROM apm_sourceofintro";
		}
		sql = pagination.getSQLQuery(sql);
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				SourceOfIntro sourceOfIntro = new SourceOfIntro();
				sourceOfIntro.setId(rs.getInt(1));
				sourceOfIntro.setSourceName(rs.getString(2));
				sourceOfIntro.setDescription(rs.getString(3));
				sourceOfIntro.setDateTime(rs.getString(4));
				
				list.add(sourceOfIntro);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public int saveSourceOfIntro(SourceOfIntro sourceOfIntro) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_sourceofintro(sourceOfIntro,description,dateTime) values(?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, sourceOfIntro.getSourceName());
			preparedStatement.setString(2, sourceOfIntro.getDescription());
			preparedStatement.setString(3, DateTimeUtils.getCurrentDateInDDMMYYYYCasting());
			
			result = preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	public SourceOfIntro getSourceOfIntro(int id, SourceOfIntro sourceOfIntro) {

		PreparedStatement preparedStatement = null;
		
		String sql = "SELECT id,sourceOfIntro,description,dateTime FROM apm_sourceofintro where id = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				sourceOfIntro.setId(rs.getInt(1));
				sourceOfIntro.setSourceName(rs.getString(2));
				sourceOfIntro.setDescription(rs.getString(3));
				sourceOfIntro.setDateTime(rs.getString(4));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return sourceOfIntro;
	}

	public int updateSourceOfIntro(SourceOfIntro sourceOfIntro, int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_sourceofintro set sourceOfIntro = ?,description = ? where id = "+id+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, sourceOfIntro.getSourceName());
			preparedStatement.setString(2, sourceOfIntro.getDescription());
			//preparedStatement.setTimestamp(3, DateTimeUtils.getCurrentDateInSQLCasting());
			
			result = preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	public int deleteSourceOfIntro(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_sourceofintro where id = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public ArrayList<Master> getMasterList() {
		// TODO Auto-generated method stub
		ArrayList<Master> masters=new ArrayList<Master>();
		
		try {
			
			  PreparedStatement ps=connection.prepareStatement("select * from apm_select_master order by mastername");
			  ResultSet rs=ps.executeQuery();
			  while(rs.next())
			  {    
				    Master master=new Master();
				    int id=rs.getInt(1);
				    String mastername=rs.getString(2);
				    master.setId(id);
				    master.setName(mastername);
				    masters.add(master);
			  }
            			
		} catch (Exception e) {

		   e.printStackTrace();
		}
			
		return masters;
	}
}
