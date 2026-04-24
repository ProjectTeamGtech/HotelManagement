package com.apm.Master.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.commons.collections.functors.WhileClosure;

import com.apm.Master.eu.bi.DischargeStatusDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

public class JDBCDischargeStatus implements DischargeStatusDAO {

	Connection connection = null;

	public JDBCDischargeStatus(Connection connection) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
	}

	
	public ArrayList<Master> getAllDischargeStatus(Pagination pagination) {
		// TODO Auto-generated method stub
		ArrayList<Master> masters = new ArrayList<Master>();
		try {

			String sql="select * from apm_discharge_status order by id desc";
			sql=pagination.getSQLQuery(sql);
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Master master = new Master();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String description = rs.getString(3);
				master.setId(id);
				master.setName(name);
				master.setDescription(description);
				masters.add(master);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return masters;
	}

	
	public int addDischargeStatus(Master master) {
		// TODO Auto-generated method stub
		int result = 0;
		try {

			PreparedStatement ps = connection
					.prepareStatement("insert into apm_discharge_status (name,description) values (?,?)");
			ps.setString(1, master.getName());
			ps.setString(2, master.getDescription());
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	
	public Master getMaster(int id) {
		// TODO Auto-generated method stub
		Master master = new Master();
		try {

			PreparedStatement ps = connection
					.prepareStatement("select * from apm_discharge_status where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String name = rs.getString("name");
				String description = rs.getString("description");
				master.setId(id);
				master.setName(name);
				master.setDescription(description);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return master;
	}

	
	public int updateDischargeStatus(Master master) {
		// TODO Auto-generated method stub
		int result = 0;

		try {

			PreparedStatement ps = connection
					.prepareStatement("update apm_discharge_status set name=?,description=? where id=?");
			ps.setString(1, master.getName());
			ps.setString(2, master.getDescription());
			ps.setInt(3, master.getId());
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	
	public int deleteDischargeStatus(int id) {
		// TODO Auto-generated method stub
		int result = 0;
		try {

			PreparedStatement ps = connection
					.prepareStatement("delete from apm_discharge_status where id=?");
			ps.setInt(1, id);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	
	public ArrayList<Master> getMasterList(LoginInfo loginInfo) {
		// TODO Auto-generated method stub
		ArrayList<Master> masters = new ArrayList<Master>();
        PreparedStatement ps=null;
		try {

			 if(loginInfo.getId()==1){
			       ps=connection.prepareStatement("select * from apm_select_master order by mastername");
			  }
			  else{
			       ps=connection.prepareStatement("select * from apm_select_master where id!=22 order by mastername");
			  }
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				int id = rs.getInt(1);
				String mastername = rs.getString(2);
				master.setId(id);
				master.setName(mastername);
				masters.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return masters;
	}


	public int getTotalCount() {

		int result=0;
		try {
			
		     String sql="select count(*) from apm_discharge_status";	
			 PreparedStatement ps=connection.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
		     while(rs.next()) {
		    	 result=rs.getInt(1);
		     }
			 
		} catch (Exception e) {

		   e.printStackTrace();
		}
		return result;
	}


	public String getDischargeStatusById(int statusid) {
		String result="";
		try {
			PreparedStatement ps=connection.prepareStatement("select description from apm_discharge_status where id="+statusid+" ");
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				result=rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
