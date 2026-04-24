package com.apm.Master.eu.blogic.jdbc;

import com.apm.Master.eu.bi.MedicineTypeDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.MedicineType;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.Pagination;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class JDBCMedicineTypeDAO extends JDBCBaseDAO implements MedicineTypeDAO {
 public JDBCMedicineTypeDAO(Connection connection)
 {
	 this.connection = connection;
 }
 
 public ArrayList<MedicineType> getAllmedicinetypeList(String searchText,Pagination pagination) {
		ArrayList<MedicineType> users = new ArrayList<MedicineType>();
		try {
			String sql ="";
			if (searchText!=null) {
				sql = "select id,name,isstrip from apm_medicine_type where name like ('"+searchText+"%')";
			} else {
				sql = "select id,name,isstrip from apm_medicine_type";
			}
			if(pagination!=null){
				sql=pagination.getSQLQuery(sql);
			}
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				MedicineType master = new MedicineType();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				String a=rs.getString(3).toString();
				if(a.equals("1"))
				{
					master.setIsstrip("True");
				}
				else
				{
					master.setIsstrip("False");
				}
				
				
				users.add(master);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

 public int addproductypelist(MedicineType master) {
		int result = 0;
		try {
			String query = "insert into apm_medicine_type(name,isstrip) values(?,?)";
			PreparedStatement stmt = connection.prepareStatement(query); 
			stmt.setString(1, master.getName());
			String a=master.getIsstrip();
			if(a==null){
			 a="off";
			 
			}
			
			
			if(a.equals("on"))
			{
				stmt.setInt(2, 1);
			}
			else
			{
				stmt.setInt(2, 0);
			}
			
			
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
 
 public int updateproductypelist(MedicineType master) {
		int result = 0;
		try {
		
			String query = "update apm_medicine_type set  name=? ,isstrip=? where id="+master.getId();
			PreparedStatement stmt = connection.prepareStatement(query); 
			stmt.setString(1, master.getName());
			String a=master.getIsstrip();
			if(a==null){
			 a="off";
			 
			}
			
			
			if(a.equals("on"))
			{
				stmt.setString(2, "1");
			}
			else
			{
				stmt.setString(2, "0");
			}
			
			
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
 
 
 public int deleteproductlist(MedicineType master){
	 int result = 0;
	 try {
		String query="delete from apm_medicine_type  where id="+master.getId();
		PreparedStatement stmt=connection.prepareStatement(query);
		result=stmt.executeUpdate();
		 
	} catch (Exception e) {
		e.printStackTrace();
	}
	 return result;
 }
 
 public MedicineType getmedicinetypemasterinfo(MedicineType master) {
	 MedicineType master2 = new MedicineType();
		try {
			String query= "select id,name,isstrip from apm_medicine_type where id="+master.getId();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				master2.setId(rs.getInt(1));
				master2.setName(rs.getString(2));
				master2.setIsstrip(rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master2;
 
}



public int getTotalmedicinetypeCount() {
	
	int result=0;
	try {
		String sql="select count(*) from apm_medicine_type";
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


 }
