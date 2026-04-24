package com.apm.Master.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Master.eu.bi.DeclarationDAO;
import com.apm.Master.eu.entity.Declaration;
import com.apm.Master.eu.entity.Master;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

public class JDBCDeclarationDAO extends JDBCBaseDAO implements DeclarationDAO{
	
	public JDBCDeclarationDAO(Connection connection){
		this.connection = connection;
		
	}

	public ArrayList<Declaration> getDeclarationList(Pagination pagination,String searchText) {
		PreparedStatement preparedStatement = null;
		ArrayList<Declaration>list = new ArrayList<Declaration>();
		String sql="";
		if(searchText!=null){
			sql = "SELECT id,declaration_text,title FROM apm_declaration where title like '"+searchText+"%' order by id desc";
		}else{
			sql = "SELECT id,declaration_text,title FROM apm_declaration order by id desc";
		}
		//String sql = "SELECT id,declaration_text,title FROM apm_declaration";
		sql = pagination.getSQLQuery(sql);
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Declaration declaration = new Declaration();
				declaration.setId(rs.getInt(1));
				declaration.setDeclarationNotes(rs.getString(2));
				declaration.setTitle(rs.getString(3));
				list.add(declaration);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getTotalDeclarationCount(String searchText) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql ="";
		if(searchText!=null){
			sql = "select count(*) from apm_declaration where title like '"+searchText+"%'";
		}else{
			sql = "select count(*) from apm_declaration";
		}
		//String sql = "select count(*) from apm_declaration";
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

	public int saveDeclaration(Declaration declaration,int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_declaration(declaration_text,clinic_id,title) values(?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, declaration.getDeclarationNotes());
			preparedStatement.setInt(2, id);
			preparedStatement.setString(3, declaration.getTitle());

			result = preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	public Declaration getDeclaration(int id, Declaration declaration) {
		PreparedStatement preparedStatement = null;
		
		String sql = "SELECT id,declaration_text,title FROM apm_declaration where id = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				declaration.setId(rs.getInt(1));
				declaration.setDeclarationNotes(rs.getString(2));
				declaration.setTitle(rs.getString(3));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return declaration;
	}

	public int updateDeclaration(Declaration declaration, int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_declaration set declaration_text = ?,title = ? where id = "+id+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, declaration.getDeclarationNotes());
			preparedStatement.setString(2, declaration.getTitle());
			
			result = preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	public int deleteDeclaration(int id, Declaration declaration) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_declaration where id = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public ArrayList<Master> getMasterList(LoginInfo loginInfo) {
		// TODO Auto-generated method stub
		ArrayList<Master> masters=new ArrayList<Master>();
		PreparedStatement ps=null;
		try {
			 if(loginInfo.getId()==1){
			       ps=connection.prepareStatement("select * from apm_select_master order by mastername");
			  }
			  else{
			       ps=connection.prepareStatement("select * from apm_select_master where id!=22 order by mastername");
			  }
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
