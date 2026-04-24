package com.apm.Master.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Master.eu.bi.UnitDAO;
import com.apm.Master.eu.entity.Unit;
import com.apm.Master.web.form.UnitForm;

public class JDBCUnitDAO implements UnitDAO{

	UnitForm unitForm=new UnitForm();
	
	public Connection con=null;
	
	public JDBCUnitDAO(Connection con){
		
		this.con=con;
		
	}

	@Override
	public int saveUnit(Unit unit) {
		// TODO Auto-generated method stub
		
		
		PreparedStatement pmt=null;
		int result=0;
		
		try {
			String sql="insert into apm_unitmaster(unit)values(?)";
			pmt=con.prepareStatement(sql);
			
			pmt.setString(1, unit.getUnitMeasurement());
			
			result=pmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public ArrayList<Unit> getallunitlist(String searchtext) {
		// TODO Auto-generated method stub
		
		ArrayList<Unit>list=new ArrayList<Unit>();
		PreparedStatement pmt=null;
		
		try {
			
			String sql="";
			
			if(searchtext!=null){
				
				 sql="select id,unit from apm_unitmaster where unit like ('"+searchtext+"%')";
				
			}else{
				
				 sql="select id,unit from apm_unitmaster";
			}
			
			
			
			pmt=con.prepareStatement(sql);
			
			ResultSet rs=pmt.executeQuery();
			
			while(rs.next()){
				
				Unit unit=new Unit();
				
				unit.setId(rs.getString(1));
				unit.setUnitMeasurement(rs.getString(2));
				
				list.add(unit);
				
				}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public Unit geteditUnit(String selectedid) {
		// TODO Auto-generated method stub
		
		PreparedStatement pmt=null;
		
		Unit unit=new Unit();
		
		try {
			
			String sql="select unit from apm_unitmaster where id="+selectedid+"";
			pmt=con.prepareStatement(sql);
			
			ResultSet rs=pmt.executeQuery();
			
			while(rs.next()){
				
				unit.setId(selectedid);
				unit.setUnitMeasurement(rs.getString(1));
				}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return unit;
	}

	@Override
	public int getupdateunit(Unit unit) {
		// TODO Auto-generated method stub
		
		PreparedStatement pmt=null;
		int result=0;
		
		try {
			
			String sql="update apm_unitmaster set unit=? where id=?";
			pmt=con.prepareStatement(sql);
			
			pmt.setString(1, unit.getUnitMeasurement());
			pmt.setString(2, unit.getId());
			
			result=pmt.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int deleteUnitdata(String selectedid) {
		// TODO Auto-generated method stub
		
		int result=0;
		PreparedStatement pmt=null;
		
		try {
			
			String sql="delete from apm_unitmaster where id="+selectedid+"";
			
            pmt=con.prepareStatement(sql);
			result=pmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
}
